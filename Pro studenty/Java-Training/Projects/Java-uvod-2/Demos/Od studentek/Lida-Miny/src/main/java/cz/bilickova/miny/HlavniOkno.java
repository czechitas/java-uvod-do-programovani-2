package cz.bilickova.miny;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.String;
import java.util.*;
import java.util.List;
import javax.imageio.*;
import javax.swing.*;
import net.sevecek.util.swing.*;

import static java.lang.String.*;

public class HlavniOkno extends JFrame {

    JMenuBar menuBar;
    JMenu menuHra;
    JMenuItem menuItemKonec;
    JMenu menuNova;
    JMenuItem menuItemZacatecnik;
    JMenuItem menuItemPokrocily;
    JMenuItem menuItemExpert;
    JPanel panelInfo;
    JLabel label1;
    JLabel labPocetMin;
    JLabel label2;
    JLabel labCas;
    JPanel contentPane;
    JKeyboard klavesnice;
    List<JButton> plocha = new ArrayList<JButton>();
    List<JLabel> popisky;
    static final int ZACATECNIK = 10;
    static final int POKROCILY = 15;
    static final int EXPERT = 20;
    static final int OKRAJE = 5;
    int velikostPolicka = 25;
    int uroven = ZACATECNIK;
    Image mina;
    JTimer casovac;
    int minuty;
    int vteriny;

    public HlavniOkno() {
        initComponents();
    }

    private void priOtevreniOkna(WindowEvent e) {
        novaHra();
        nastavKlavesnici();
    }

    // nahrává obrázek miny
    private void uploadPictures() {
        try {
            mina = ImageIO.read(getClass().getResourceAsStream("mina.png"));
        }
        catch (IOException ex) {
            throw new RuntimeException("nepodařilo se nahrát obrázek");
        }
    }

    private void novaHra() {
        uklidHraciPole();
        nastavInfoPanel();
        vytvorHraciPole();
    }

    private void nastavPocitadlo() {
        if (casovac == null) {
            casovac = new JTimer(1000, e -> priTiknutiCasovace());
        }
        casovac.start();
        minuty = 0;
        vteriny = 0;
    }

    private void priTiknutiCasovace() {
        labCas.setText(minuty + ":" + vteriny);
        vteriny++;
        if (vteriny % 60 == 0) {
            minuty++;
            vteriny = vteriny - 60;
        }
    }

    // nastaví počet min do info panelu hry
    private void nastavPocetMin() {
         labPocetMin.setText(valueOf(uroven));
    }

    private void nastavInfoPanel() {
        nastavPocitadlo();
        nastavPocetMin();
    }

    private void vytvorHraciPole() {
        vytvorPlochu();
        rozlozMiny();
        prepocitejHraciPlan();
    }

    // projde všechny políčka a nastaví u nich počty min v jejich okolí
    private void prepocitejHraciPlan(){
        int hodnotaPopisku;
        //když na políčku není mina, tak nastavím popisek = počet min v okolí
        for (JLabel popisek : popisky) {
            hodnotaPopisku = Integer.parseInt(popisek.getText().toString());
            if (hodnotaPopisku != -1) {
                nastavPopisek(popisky.indexOf(popisek));
            }
        }
    }

    // smaze všechno z plochy, aby se mohla vytvořit nová hrací plocha
    private void uklidHraciPole() {
        //remove(contentPane);
        smazPolicka();
        smazPopisky();
        nastavPocitadlo();
    }

    // smaze vsechny popisky (labely) z plochy
    private void smazPopisky() {
        if (popisky != null) {
            for (JLabel popisek : popisky) {
                remove(popisek);
        }
        }
    }

    // smaže všechny policka (Buttony) z plochy
    private void smazPolicka() {
        if (plocha != null) {
            for (JButton policko : plocha) {
                remove(policko);
            }
        }
        repaint();
    }

    //vytvoří políčka a popisky pod nimi
    private void vytvorPlochu() {
        plocha = new ArrayList<JButton>();
        popisky = new ArrayList<JLabel>();
        nastavVelikostOkna();
        for (int i = 0; i < uroven; i++) {
            for (int j = 0; j < uroven; j++) {
                vytvorPolicko(i, j);
                vytvorPopisek(i, j);
            }
        }
    }

    // rozloží počet min, který je stejný jako konstanta úrovně
    private void rozlozMiny() {
        Random random = new Random();
        int index;
        int pocetMin = uroven;
        JLabel popisek;
        int hodnotaPopisku;
        while (pocetMin > 0) {
            index = random.nextInt(uroven*uroven-1);
            popisek = popisky.get(index);
            hodnotaPopisku = Integer.parseInt(popisek.getText().toString());
            if (hodnotaPopisku != -1) {
                popisek.setText("-1");
                pocetMin--;
            }
        }
    }

    // upraví velikost okna podle vygenerovaného hracího plánu
    private void nastavVelikostOkna() {
        contentPane.setLocation(0, panelInfo.getHeight());
        contentPane.setSize(new Dimension(uroven*velikostPolicka+25, uroven*velikostPolicka+75));
        //contentPane.setPreferredSize(new Dimension(uroven*velikostPolicka, uroven*velikostPolicka));
        this.setSize(contentPane.getWidth(), contentPane.getHeight()+panelInfo.getHeight());
    }

    // vytvoření a nastavení políčka (Buttonu) + přidání na plochu a do Listu "plocha" + nastavení eventu na kliknutí myši
    private void vytvorPolicko(int i, int j) {
        JButton policko;
        policko = new JButton();
        int startX = 0;
        int startY = panelInfo.getHeight();
        int souradniceX = (startX + OKRAJE + i*velikostPolicka);
        int souradniceY = (startY + OKRAJE + j*velikostPolicka);
        contentPane.add(policko);
        plocha.add(policko);
        policko.setMaximumSize(new Dimension(velikostPolicka, velikostPolicka));
        policko.setMinimumSize(new Dimension(velikostPolicka, velikostPolicka));
        policko.setPreferredSize(new Dimension(velikostPolicka, velikostPolicka));
        policko.setText("");
        policko.setVisible(true);
        policko.setFocusable(false);
        policko.setBounds(new Rectangle(new Point(souradniceX, souradniceY), policko.getPreferredSize()));
        policko.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                poKliknutiMysiNaPolicko(e);
            }
        });
    }

    // vytvoří jeden popisek s defaultním nastavením hodnoty "0"
    private void vytvorPopisek(int i, int j) {
        JLabel popisek;
        popisek = new JLabel();
        Font boldFont = popisek.getFont().deriveFont(Font.BOLD);
        int startX = 0;
        int startY = panelInfo.getHeight();
        int souradniceX = (startX + OKRAJE + i*velikostPolicka);
        int souradniceY = (startY + OKRAJE + j*velikostPolicka);
        contentPane.add(popisek);
        popisky.add(popisek);
        popisek.setMaximumSize(new Dimension(velikostPolicka, velikostPolicka));
        popisek.setMinimumSize(new Dimension(velikostPolicka, velikostPolicka));
        popisek.setPreferredSize(new Dimension(velikostPolicka, velikostPolicka));
        popisek.setText("0");
        popisek.setFont(boldFont);
        popisek.setVisible(true);
        popisek.setBounds(new Rectangle(new Point(souradniceX, souradniceY), popisek.getPreferredSize()));
        popisek.setHorizontalAlignment(SwingConstants.CENTER);
        popisek.setVerticalAlignment(SwingConstants.CENTER);
    }

    private void nastavKlavesnici() {
        klavesnice = new JKeyboard();
    }

    private void poKliknutiMysiNaPolicko(MouseEvent e) {
        JButton policko = (JButton) e.getComponent();
        policko.setVisible(false);
        remove(policko);
        repaint();
        int index = plocha.indexOf(policko);
        JLabel popisek = popisky.get(index);
        String textPolicka = popisek.getText().toString();
        int hodnotaPolicka = Integer.parseInt(textPolicka);
        if (hodnotaPolicka == -1) {
            gameOver();
            return;
        } else {
            odkryjPopisek(index);
            odkryjOkoli(index);
            kontrolaVitezstvi();
        }
    }

    // konstroluje, zda na ploše nezbyl počet neodkrytých políček stejný jako je počet rozložených min (odpovídá úrovni hry)
    private void kontrolaVitezstvi() {
        int pocetNeodkrytychPoli = 0;
        for (JButton policko : plocha) {
            if (policko.isVisible()) {
                pocetNeodkrytychPoli++;
            }
        }
        if (pocetNeodkrytychPoli == uroven) {
            vitezstvi();
        }
    }

    // akce, když hráč zvítezí - tohle bude ještě vylepšeno
    private void vitezstvi () {
        //zastavit casovac
        //zobrazit informaci, ze byly odkryty vsechny miny
        casovac.stop();
        JOptionPane.showMessageDialog(null, "Čas: " + minuty + ":" + vteriny, "Vítězství!", JOptionPane.CLOSED_OPTION);
        //casovac.removeActionListener(e -> priTiknutiCasovace());
    }

    // podle počtu min v okolí si popisek nastaví hodnotu a tomu odpovídající barvu
    private void nastavPopisek(int index) {
        JLabel popisek = popisky.get(index);
        int minyOkoli = kolikMinJeVOkoli(index);
        if (minyOkoli != 0) {
            popisek.setText(valueOf(minyOkoli));
            switch (minyOkoli) {
                case 1: {
                    popisek.setForeground(new Color(56, 142, 60));
                    break;}
                case 2: {
                    popisek.setForeground(new Color(211, 47, 47));
                    break;}
                case 3: {
                    popisek.setForeground(new Color(48, 63, 159));
                    break;}
                case 4: {
                    popisek.setForeground(new Color(123, 31, 162));
                    break;}
                default: {
                    break;
                }
            }
        }
    }

    // když se smaže Button nad políčkem, tak promění čísla na symboly, která vidí uživatel
    private void odkryjPopisek(int index) {
        JLabel popisek = popisky.get(index);
        String popisekText = popisek.getText().toString();
        if (popisekText.contains(" ")) {
            return;
        }
        int hodnotaPolicka = Integer.parseInt(popisekText);
        if (hodnotaPolicka == -1) {
            popisek.setText("*");
            //popisek.setIcon(new ImageIcon(mina));
        }
        if (hodnotaPolicka == 0) {
            popisek.setText(" ");
        }
    }

    // určí, kolik min je v okolí políčka s indexem "index"
    private int kolikMinJeVOkoli (int index) {
        List<JLabel> okoli = urciOkoli(index);
        return spocitejMinyOkoli(okoli);
    }

    // určí všechny sousedy, ošetřuje i rohy a první řádek a poslední řádek, vrací List sousedů
    private List<JLabel> urciOkoli(int index) {
        List<JLabel> okoli = new ArrayList<JLabel>();
        JLabel popisek;

        //ošetření indexu 0
        if (index == 0) {
            popisek = popisky.get(index+1);
            okoli.add(popisek);
            popisek = popisky.get(index+uroven);
            okoli.add(popisek);
            popisek = popisky.get(index+uroven+1);
            okoli.add(popisek);
            return okoli;
        }

        // ošetření posledního prvku
        if (index == uroven*uroven-1) {
            popisek = popisky.get(index-1);
            okoli.add(popisek);
            popisek = popisky.get(index-uroven);
            okoli.add(popisek);
            popisek = popisky.get(index-uroven-1);
            okoli.add(popisek);
            return okoli;
        }

        // ošetření levý dolní roh
        if (index == (uroven-1)) {
            popisek = popisky.get(index-1);
            okoli.add(popisek);
            popisek = popisky.get(index+uroven);
            okoli.add(popisek);
            popisek = popisky.get(index+uroven-1);
            okoli.add(popisek);
            return okoli;
        }

        // ošetření pravý horní roh
        if (index == (uroven*(uroven-1))) {
            popisek = popisky.get(index+1);
            okoli.add(popisek);
            popisek = popisky.get(index-uroven);
            okoli.add(popisek);
            popisek = popisky.get(index-uroven+1);
            okoli.add(popisek);
            return okoli;
        }

        //ošetření prvního řádku
        if (index % uroven == 0) {
            // stejný řádek
            // dolni radek
            popisek = popisky.get(index+1);
            okoli.add(popisek);
            popisek = popisky.get(index+uroven);
            okoli.add(popisek);
            popisek = popisky.get(index+uroven+1);
            okoli.add(popisek);
            popisek = popisky.get(index-uroven);
            okoli.add(popisek);
            popisek = popisky.get(index-uroven+1);
            okoli.add(popisek);
            return okoli;
        }

        //ošetření posledního řádku
        if (index % uroven == (uroven-1)) {
            //stejný řádek
            // horní řádek
            popisek = popisky.get(index-1);
            okoli.add(popisek);
            popisek = popisky.get(index+uroven);
            okoli.add(popisek);
            popisek = popisky.get(index+uroven-1);
            okoli.add(popisek);
            popisek = popisky.get(index-uroven);
            okoli.add(popisek);
            popisek = popisky.get(index-uroven-1);
            okoli.add(popisek);
            return okoli;
        }

        /*// ošetření prvního sloupce - ošetří obecná podmínka na index v rozmezí 0 - uroven*uroven-1
        if (index / uroven == 0) {
            //stejný sloupec
            //sloupec vpravo
        }

        //ošetření levého sloupce  - ošetří obecná podmínka na index v rozmezí 0 - uroven*uroven-1
        if (index / uroven == (uroven-1)) {
            //stejný sloupec
            //sloupec vlevo
        } */

        if ((index-1 >= 0) && (index - 1 < uroven*uroven-1)) {
            popisek = popisky.get(index - 1);
            okoli.add(popisek);
        }
        if ((index+uroven-1 >= 0) && (index+uroven-1 < uroven*uroven-1)) {
            popisek = popisky.get(index + uroven - 1);
            okoli.add(popisek);
        }
        if (((index-uroven-1) >= 0 ) && ((index-uroven-1) < uroven*uroven-1)){
            popisek = popisky.get(index-uroven-1);
            okoli.add(popisek);
        }
        if (((index+1) >= 0 ) && ((index+1) < uroven*uroven-1)){
            popisek = popisky.get(index+1);
            okoli.add(popisek);
        }
        if (((index+uroven+1) >= 0 ) && ((index+uroven+1) < uroven*uroven-1)){
            popisek = popisky.get(index+uroven+1);
            okoli.add(popisek);
        }
        if (((index-uroven+1) >= 0 ) && ((index-uroven+1) < uroven*uroven-1)){
            popisek = popisky.get(index-uroven+1);
            okoli.add(popisek);
        }
        if (((index+uroven) >= 0 ) && ((index+uroven) < uroven*uroven-1)){
            popisek = popisky.get(index+uroven);
            okoli.add(popisek);
        }
        if (((index-uroven) >= 0 ) && ((index-uroven) < uroven*uroven-1)){
            popisek = popisky.get(index-uroven);
            okoli.add(popisek);
        }
        return okoli;
    }

    // projde List sousedů a podle hodnoty -1 uríč, kolik je v okolí políčka min
    private int spocitejMinyOkoli(List<JLabel> okoli) {
        int pocetMin = 0;
        String sousedText;
        int sousedHodnota;
        for (JLabel soused : okoli) {
            sousedText = soused.getText().toString();
            if (sousedText.contains(" ")) {
            }  else {
                sousedHodnota = Integer.parseInt(sousedText);
                if (sousedHodnota == -1) {
                    pocetMin++;
                }
            }

        }
        return pocetMin;
    }

    // hráč odkyl minu
    private void gameOver() {
        casovac.stop();
        odkryjCelouPlochu();
    }

    // smaže všechny políčka a odkryje tak labely, které se musí převést na podobu pro uživatele
    private void odkryjCelouPlochu() {
        smazPolicka();
        for (JLabel popisek : popisky) {
            odkryjPopisek(popisky.indexOf(popisek));
        }
    }

    // když hráč klikne na pole, v jehož okolí není mina, tak okryje všechny sousedy, kteří taky nesousedí s minou - tohle by šlo ještě hráčsky zpříjemnit
    private void odkryjOkoli(int index) {
        List<JLabel> okoli = urciOkoli(index);
        String sousedText;
        for (JLabel soused : okoli) {
            sousedText = soused.getText().toString();
            if (sousedText.contains(" ") || sousedText.contains("0")) {
                odkryjPopisek(popisky.indexOf(soused));
                plocha.get(popisky.indexOf(soused)).setVisible(false);
                remove(plocha.get(popisky.indexOf(soused)));
            }
            if (sousedText.contains("0")) {
                odkryjOkoli(popisky.indexOf(soused));
            }
        }

    }

    private void menuItemZacatecnik(ActionEvent e) {
        uroven = ZACATECNIK;
        novaHra();
    }

    private void menuItemPokrocily(ActionEvent e) {
        uroven = POKROCILY;
        novaHra();
    }

    private void menuItemExpert(ActionEvent e) {
        uroven = EXPERT;
        novaHra();
    }

    private void menuItemKonec(ActionEvent e) {
        casovac.stop();
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private void initComponents() {
        menuBar = new JMenuBar();
        menuHra = new JMenu();
        menuItemKonec = new JMenuItem();
        menuNova = new JMenu();
        menuItemZacatecnik = new JMenuItem();
        menuItemPokrocily = new JMenuItem();
        menuItemExpert = new JMenuItem();
        panelInfo = new JPanel();
        label1 = new JLabel();
        labPocetMin = new JLabel();
        label2 = new JLabel();
        labCas = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Miny");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                priOtevreniOkna(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        this.contentPane = (JPanel) this.getContentPane();
        this.contentPane.setBackground(this.getBackground());

        //======== menuBar ========
        {

            //======== menuHra ========
            {
                menuHra.setText("Hra");

                //---- menuItemKonec ----
                menuItemKonec.setText("Konec");
                menuItemKonec.addActionListener(e -> menuItemKonec(e));
                menuHra.add(menuItemKonec);
            }
            menuBar.add(menuHra);

            //======== menuNova ========
            {
                menuNova.setText("Nov\u00e1");

                //---- menuItemZacatecnik ----
                menuItemZacatecnik.setText("Za\u010d\u00e1te\u010dn\u00edk");
                menuItemZacatecnik.addActionListener(e -> menuItemZacatecnik(e));
                menuNova.add(menuItemZacatecnik);

                //---- menuItemPokrocily ----
                menuItemPokrocily.setText("Pokro\u010dil\u00fd");
                menuItemPokrocily.addActionListener(e -> menuItemPokrocily(e));
                menuNova.add(menuItemPokrocily);

                //---- menuItemExpert ----
                menuItemExpert.setText("Expert");
                menuItemExpert.addActionListener(e -> menuItemExpert(e));
                menuNova.add(menuItemExpert);
            }
            menuBar.add(menuNova);
        }
        setJMenuBar(menuBar);

        //======== panelInfo ========
        {
            panelInfo.setLayout(null);

            //---- label1 ----
            label1.setText("Po\u010det min:");
            panelInfo.add(label1);
            label1.setBounds(10, 5, 68, label1.getPreferredSize().height);

            //---- labPocetMin ----
            labPocetMin.setText("0");
            labPocetMin.setHorizontalAlignment(SwingConstants.CENTER);
            labPocetMin.setFont(new Font("Segoe UI", Font.BOLD, 12));
            panelInfo.add(labPocetMin);
            labPocetMin.setBounds(70, 5, 27, labPocetMin.getPreferredSize().height);

            //---- label2 ----
            label2.setText("\u010cas:");
            panelInfo.add(label2);
            label2.setBounds(new Rectangle(new Point(120, 5), label2.getPreferredSize()));

            //---- labCas ----
            labCas.setText("0:00");
            labCas.setHorizontalAlignment(SwingConstants.CENTER);
            labCas.setFont(new Font("Segoe UI", Font.BOLD, 12));
            panelInfo.add(labCas);
            labCas.setBounds(145, 5, 65, labCas.getPreferredSize().height);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panelInfo.getComponentCount(); i++) {
                    Rectangle bounds = panelInfo.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panelInfo.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panelInfo.setMinimumSize(preferredSize);
                panelInfo.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panelInfo);
        panelInfo.setBounds(-5, 0, 390, 26);

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
    }
}
