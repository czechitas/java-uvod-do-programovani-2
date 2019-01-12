package cz.czechitas.miny;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;
//import java.util.*;
import java.util.*;
import java.util.List;

public class HlavniOkno extends JFrame {
    //public static int cas = 0;
    private int sirkaPole;
    private int vyskaPole;
    private int pocetMin;
    private int obtiznost;
    JPanel contentPane;
    JPanel panel1;
    JLabel labCas;
    JTextField txfCas;
    JLabel labMiny;
    JTextField txfMiny;
    JLabel labOK;
    List<Policko> policka;            //seznam vsech policek na hraci desce - pro 1 hru se nemeni
    List<Integer> sousede;            //seznam, do ktereho se ukladaji indexy sousedu vybraneho policka
    List<Integer> otocit;             //seznam, do ktereho se ukladaji indexy policek urcenych k otoceni
    List<Integer> kontrola;           //seznam, do ktereho se ukladaji indexy policek urcenych pro dalsi testovani, jestli jsou k otoceni
    static int velikostPolicka = 30;  //rozmery ctverecku na hraci plose
    static int velikostOkraje = 5;
    Random rand = new Random();
    Timer casovac;
    int cas;
    int fiktivniPocetZbylychMin;      //pocet min vypisovanych do okna - odecitaji se miny urcene hracem
    int spatneUrcenaMina = 0;         //je na hraci plose urcene policko hracem jako mina, ktere ve skut. minou neni?



    /*  Parametry jsou tride predavane z dialogoveho okna TazaciOkno  */

    public HlavniOkno(int sirkaPole, int vyskaPole, int pocetMin, int obtiznost) {             // konstruktor
        this.sirkaPole = sirkaPole;
        this.vyskaPole = vyskaPole;
        this.pocetMin = pocetMin;
        this.obtiznost = obtiznost;

        initComponents();
    }

    /*  Metoda reagujici na otevreni hlavniho okna s hrou. Deklaruji se seznamy
     a spusti se vlastni hra  */

    private void priOtevreniOkna(WindowEvent e) {
        policka = new ArrayList<Policko>();
        sousede = new ArrayList<Integer>();
        otocit = new ArrayList<Integer>();
        kontrola = new ArrayList<Integer>();
        spustHru();
    }

    /*  Metoda reagujici na kliknuti na libovolne hraci policko. Reaguje pouze na jeste neotocena
        policka, rozlisuje levy klik (neni mina) a pravy klik (je mina). Pokud je pravym tlacitkem hracem
        chybne nastaveno, ze je tam mina, lze to levym klikem spravit a otoci se cislo. Pokud se klikne
        levym tlacitkem na minu, dojde k vybuchu     */

    private void priKliknutiMysiNaPolicko(MouseEvent e) {
        Policko policko = (Policko) e.getSource();
        if (policko.jeOtocene!= true) {
            if (e.getButton()==1) {                                    // stisk leveho tlacitka mysi

                if (policko.jeMina != true) {                          // pokud zde neni mina
                    if (policko.pocetOkolnichMin != 0) {               // pokud policko sousedi s minou (minama)
                        policko.ukazCislo(policko);                    // otoci se cislo s poctem okolnich min
                        if(policko.jeOznacenoJakoMina == true) {
                            spatneUrcenaMina--;
                            fiktivniPocetZbylychMin++;                             // pricte minu
                            txfMiny.setText("" + fiktivniPocetZbylychMin);
                        }
                    } else {
                        odkryjNulovaPolicka(policko);                  // pokus nesousedi s zadnou minou, spusti
                    }                                                  // se metoda otacejici cely prazdny blok
                    if(policko.jeOznacenoJakoMina == true) {
                        spatneUrcenaMina--;
                    }

                } else vybuch();                                       // pokud je zde mina, dojde k vybuchu
                policko.nastavOtoceni();                               // oznaci policko v seznamu jako otocene
                if (testVyhry() == true) vyhrano();                    // otestuje, jesti je vyhrano
            }   else if (e.getButton()==3)  {                          // stisk pravym tlacitkem mysi
                    policko.ukazMinu(policko, 255, 255, 153, "*");     // zobrazi minu
                    if(policko.jeOznacenoJakoMina == false)  {         // odecte minu z celkoveho poctu
                        fiktivniPocetZbylychMin--;
                    }
                    txfMiny.setText("" + fiktivniPocetZbylychMin);     // a cislo zapise do dialogoveho okna
                    policko.nastavOznaceniJakoMina();                  // oznaci policko v seznamu, ze je hrac povazuje za minu
                    if (policko.jeMina == false) spatneUrcenaMina++;     // pomocna promenna pro test vyhry
            }
        }
    }

    /* metoda reagujici na stisknuti tlacitka Nova hra/Konec v hlavnim okne hry.
    Otevre se nove dialogove okno OknoKonec            */
    private void labOKMouseClicked(MouseEvent e) {
        casovac.stop();
        this.setVisible(false);
        OknoKonec oknoKonec = new OknoKonec("Chceš hrát znovu?");
        oknoKonec.setVisible(true);
    }

    /* metoda spoustejici vlastni hru. Spusti metody nastavujici obsah oken, vytvori seznam
    vsech policek, náhodne prideli miny a pro kazde policko vypocte pocet okolnich min. Spusti casovac
     */
    private void spustHru() {
        fiktivniPocetZbylychMin = pocetMin;
        txfMiny.setText("" + fiktivniPocetZbylychMin);
        aktivujPolicka();
        generujMiny();
        nastavCasovac();
        vypoctiPocetOkolnichMin();
    }

    /*  metoda, ktera geometricky priradi jednotliva policka na hraci okno, vykresli je a vytvori z nich
    seznam "policka" typu policko
     */
    private void aktivujPolicka() {
        int souradniceX = 0;
        int souradniceY = 0;
        for (int i=0; i<vyskaPole; i++)
          for (int j=0; j<sirkaPole; j++) {
                Policko policko = new Policko();
                //---- policko ----
                policko.setPreferredSize(new Dimension(30, 30));
                policko.setFont(policko.getFont().deriveFont(policko.getFont().getStyle() | Font.BOLD, policko.getFont().getSize() + 10f));
                policko.setHorizontalAlignment(SwingConstants.CENTER);
                policko.setBorder(new BevelBorder(BevelBorder.RAISED));
                policko.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        priKliknutiMysiNaPolicko(e);
                    }
                });
                contentPane.add(policko);
                souradniceX = j*velikostPolicka+velikostOkraje;
                souradniceY = i*velikostPolicka+velikostOkraje;
                policko.setBounds(new Rectangle(new Point(souradniceX, souradniceY), policko.getPreferredSize()));
                policka.add(policko);
            }
    }

    /*  metoda, ktera nahodne rozlozi miny do hraciho pole (vypocte indexy seznamu)
     */
    private void generujMiny()  {
        Random random = new Random() ;
        int zbyleMiny = pocetMin;
        int nahodnyIndex;
        Policko jeTam;
        while(zbyleMiny>0)  {
            nahodnyIndex = random.nextInt(policka.size())  ;
            if (policka.get(nahodnyIndex).jeMina==false)  {
                policka.get(nahodnyIndex).priradMinu(policka.get(nahodnyIndex));
                zbyleMiny--;
            }
         }
    }

    /*  metoda, ktera nastavi casovac na 1 sekundu a spusti ho
     */
    private void nastavCasovac() {
        casovac = new Timer(1000, e -> priTiknutiCasovace());
        cas = 0;
        casovac.start();
    }

    /*  metoda, ktera podle intervalu casovace (zde 1 s) vypisuje cas do txtoveho pole v hracim okne
     */
    private void priTiknutiCasovace()    {
         txfCas.setText(""+cas);
         cas++;
    }

    /* metoda, ktera pro jednotliva policka ulozena v seznamu "policka" vypocte, s kolika minama sousedi
       vola k tomu metodu vyhledavajici vsechny sousedy daneho policka
     */
    private void vypoctiPocetOkolnichMin() {
        sousede.clear();
        for(Policko policko : policka) {
            sousede = najdiSousedy(policko);
            int pocetOkolnichMin = 0;
            for(Integer soused : sousede) {
                if (policka.get(soused).jeMina == true) {
                    pocetOkolnichMin++;
                }
            }
            policko.priradCislo(policko, pocetOkolnichMin);
            sousede.clear();
        }
    }

    /* metoda, ktera najde vsechny sousedy daneho policka a jejich indexy ulozi do seznamu "sousede".
     Zohlednuje krajni a rohove prvky.

     */
    private ArrayList najdiSousedy(Policko policko) {
        int zacatekCykluX;
        int zacatekCykluY;
        int konecCykluX;
        int konecCykluY;
        int indexPolicka;
        int a;                     // lokalni promenna, do ktere se uklada vypocteny index policka
        //testovani umisteni policka v ramci hraciho pole
        indexPolicka = policka.indexOf(policko);
        if ((indexPolicka % sirkaPole) == 0) zacatekCykluY = indexPolicka;            //levy okraj hraciho pole
        else zacatekCykluY = indexPolicka - 1;
        if ((indexPolicka % sirkaPole) == sirkaPole - 1) konecCykluY = indexPolicka;  //pravy okraj hraciho pole
        else konecCykluY = indexPolicka + 1;
        if (indexPolicka < sirkaPole) zacatekCykluX = 0;                              //horni okraj hraciho pole
        else zacatekCykluX = -1;
        if (indexPolicka > (sirkaPole * vyskaPole) - sirkaPole - 1) konecCykluX = 0;  //dolni okraj hraciho pole
        else konecCykluX = 1;

        for (int x = zacatekCykluX; x <= konecCykluX; x++) {                          //radky
            for (int y = zacatekCykluY; y <= konecCykluY; y++) {                      //sloupce
                a = y + x * sirkaPole;                                                //vypocet indexu policka
                if (a != indexPolicka) {                                              //vylouceni sama sebe
                    sousede.add(a);
                }
            }
        }
        return (ArrayList) sousede;                                                   //vraci vytvoreny seznam indexu sousedu
    }

    /* metoda s cinnostmi v pripade vybuchu miny (klik levym tlacitkem na policko s minou)
       vypne casovac, ukaze vsechny miny a cervene je zvyrazni a otevre okno s nabidkou dalsi hry nebo konce
     */
    private void vybuch() {
        casovac.stop();
        for(Policko policko : policka) {
            if ((policko.jeMina == true)) policko.ukazMinu(policko, 255, 0, 0, "*");
        }
        OknoKonec oknoKonec = new OknoKonec("Bohužel jsi prohrál. Chceš hrát znovu?");
        oknoKonec.setVisible(true);
    }

    /* metoda, ktera prohleda okoli policka nesousediciho s zadnou minou (kde je vypocteny pocet okolnich min 0)
     a najde vsechna stejna policka, a dale vsechna policka na okraji takove plochy, ktere uz s nejakou minou sousedi.
     Vsechna tato policka ulozi do seznamu "otocit". Pri prohledavani si vytvari pomocny seznam "kontrola", do ktereho
     si uklada indexy policek s nulovym poctem min, u kterych se pak dale dela kontrola sousedu.
     */
    private void odkryjNulovaPolicka(Policko policko) {
        int indexPolicka;
        indexPolicka = policka.indexOf(policko);

        kontrola.clear();
        otocit.clear();
        kontrola.add(indexPolicka) ;               //index testovaneho policka ulozi do seznamu ke kontrole sousedu
        otocit.add(indexPolicka);                  //a zaroven do seznamu urceneho k otoceni
        while (kontrola.size()!=0) {               //pokud jeste jsou nejaka policka k otestovani
            indexPolicka = kontrola.get(0);        //vezme prvni policko ze seznamu
            sousede.clear();
            sousede = najdiSousedy(policka.get(indexPolicka));          //najde vsechny sousedy
            for (Integer soused : sousede) {
                /*zjistuje, jestli testovany soused uz neni v seznamech ke kontrole nebo otoceni, neni uz otoceny
                 nebo nebyl zkontrolovany. */
                if ((kontrola.contains(soused)) == false && (otocit.contains(soused)) == false
                        && policka.get(soused).jeZkontrolovano == false && policka.get(soused).jeOtocene == false)  {
                    if (policka.get(soused).pocetOkolnichMin == 0) {       //pokud ma testovany soused 0 min v okoli
                        kontrola.add(soused);                              //je prirazen do seznamu ke kontrole
                    }
                }
                policka.get(soused).nastavZkontrolovano();                 //zkontrolovanemu sousedovi se nastavi, ze byl zkontrolovan
                if (otocit.contains(soused) == false) otocit.add(soused);                                        //a prida se do seznamu urceneho k otoceni
            }
            kontrola.remove(0) ;                                           //odebrání 1. (jiz zkontrolovaneho) ze seznamu ke kontrole
        }

        for (Integer otoc : otocit) {                                      //prochazi policka urcena k otoceni
                if (policka.get(otoc).pocetOkolnichMin == 0) {             //policko ma 0 okolnich min
                    policka.get(otoc).nastavNulaMin(policka.get(otoc));    //- otoceni policka
                } else policka.get(otoc).ukazCislo(policka.get(otoc));     //policko je okrajove - ma v sousedstvi minu
                policka.get(otoc).nastavOtoceni();                         //- otoceni policka
                if(policka.get(otoc).jeOznacenoJakoMina == true) {
                    spatneUrcenaMina--;
                    fiktivniPocetZbylychMin++;                             // pricte minu
                    policka.get(otoc).jeOznacenoJakoMina = false;
                    txfMiny.setText("" + fiktivniPocetZbylychMin);
                }
        }
    }

    /* metoda, ktera testuje vyhru. Projde seznam vsech policek a kontroluje, zda nenajde neotocena policka,
    ktera maji v sousedstvi nejakou minu, nebo nenajde policko, kde neni mina a hrac ji mylne oznacil za minu
     */
    private boolean testVyhry()    {
        boolean vyhra = true;
        for(Policko policko : policka) {
            if ((policko.jeMina == false) && (policko.jeOtocene == false))   vyhra = false;
            if (spatneUrcenaMina > 0) vyhra = false;
        }
        return vyhra;
        
    }

    /* metoda, ktera v pripade vyhry vypne casovac a otevre dialogove okno, ktere nabidne novou hru nebo konec
     */
    private void vyhrano() {
        casovac.stop();
        OknoKonec oknoKonec = new OknoKonec("Gratuluji, vyhrál jsi. Chceš hrát znovu?");
        oknoKonec.setVisible(true);
    }

    /* nevyuzita metoda, kterou jsem chtela zavirat hlavni okno v pripade nove hry, ale musim to vyresit jinak
       nefunguj
     */
    public void konec(HlavniOkno okno) {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        panel1 = new JPanel();
        labCas = new JLabel();
        txfCas = new JTextField();
        labMiny = new JLabel();
        txfMiny = new JTextField();
        labOK = new JLabel();
        int zacLabCas, zacTxfCas, zacLabMiny, zacTxfMiny, zacLabOK;

        if (obtiznost == 3) {
            zacLabCas = 20;
            zacTxfCas = 70;
            zacLabMiny = 720;
            zacTxfMiny = 805;
            zacLabOK = 435;
        }   else {
                zacLabCas = 10;
                zacTxfCas = 50;
                zacLabMiny = 325;
                zacTxfMiny = 400;
                zacLabOK = 190;
            }

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Miny");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                priOtevreniOkna(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        this.contentPane = (JPanel) this.getContentPane();
        contentPane.setSize(sirkaPole*velikostPolicka +55, vyskaPole*velikostPolicka+55);

        //======== panel1 ========
        {
            //panel1.setSize(sirkaPole*velikostPolicka, 50);
            panel1.setLayout(null);
            panel1.setBorder(LineBorder.createBlackLineBorder());

            //---- labCas ----
            labCas.setText("\u010cas:");
            panel1.add(labCas);
            labCas.setBounds(zacLabCas, 10, 30, 35);

            //---- txfCas ----
            txfCas.setEditable(false);
            txfCas.setForeground(Color.white);
            txfCas.setBackground(Color.gray);
            txfCas.setHorizontalAlignment(SwingConstants.CENTER);
            txfCas.setFont(new Font("Segoe UI", Font.BOLD, 20));
            panel1.add(txfCas);
            txfCas.setBounds(zacTxfCas, 10, 80, 35);

            //---- labMiny ----
            labMiny.setText("Po\u010det min:");
            panel1.add(labMiny);
            labMiny.setBounds(zacLabMiny, 10, 70, 35);

            //---- txfMiny ----
            txfMiny.setEditable(false);
            txfMiny.setForeground(Color.white);
            txfMiny.setBackground(Color.gray);
            txfMiny.setHorizontalAlignment(SwingConstants.CENTER);
            txfMiny.setFont(new Font("Segoe UI", Font.BOLD, 20));
            panel1.add(txfMiny);
            txfMiny.setBounds(zacTxfMiny, 10, 80, 35);

            //---- labOK ----
            labOK.setText("Nová hra/Konec");
            labOK.setHorizontalAlignment(SwingConstants.CENTER);
            labOK.setBorder(new BevelBorder(BevelBorder.RAISED));
            labOK.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    labOKMouseClicked(e);
                }
            });
            panel1.add(labOK);
            labOK.setBounds(zacLabOK, 10, 100, 35);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(0, vyskaPole*velikostPolicka+velikostOkraje*2, sirkaPole*velikostPolicka+velikostOkraje*2, 55);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}




