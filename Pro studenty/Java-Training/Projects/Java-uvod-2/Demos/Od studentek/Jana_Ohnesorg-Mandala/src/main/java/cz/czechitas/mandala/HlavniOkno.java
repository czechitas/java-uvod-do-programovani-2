package cz.czechitas.mandala;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import net.miginfocom.swing.*;
import net.sevecek.util.*;

import static java.lang.Math.*;

public class HlavniOkno extends JFrame {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    JMenuBar menu1;
    JMenu menuSoubor;
    JMenuItem mnItemOtevrit;
    JMenuItem mnItemZnovuOtevrit;
    JMenuItem mnItemUlozit;
    JMenuItem mnItemUlozitJako;
    JMenuItem mnItemUlozitAZavrit;
    JMenuItem mnItemZavrit;
    JMenu menuPaleta;
    JMenuItem mnItemPaleta1;
    JMenuItem mnItemPaleta2;
    JMenuItem mnItemPaleta3;
    JMenuItem mnItemOdstiny;
    JMenuItem mnItemUzivatelska;
    JLabel labBarva0;
    JLabel labBarva1;
    JLabel labBarva2;
    JLabel labBarva3;
    JLabel labBarva4;
    JLabel labBarva5;
    JLabel labHelp;
    JLabel labObrazek;
    JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    JPanel contentPane;
    MigLayout migLayoutManager;

    BufferedImage obrazek;
    private boolean jeEditovatelne = false;
    Color zvolenaBarva;
    File otevrenySoubor;

    List<Color> seznamBarev1 = Arrays.asList(
            new Color(106, 114, 143),
            new Color(159, 204, 148),
            new Color(195, 194, 183),
            new Color(230, 204, 138),
            new Color(200, 97, 79));

    List<Color> seznamBarev2 = Arrays.asList(
            new Color(95, 255, 245),
            new Color(234, 234, 112),
            new Color(238, 181, 117),
            new Color(172, 131, 141),
            new Color(52, 31, 37));

    List<Color> seznamBarev3 = Arrays.asList(
            new Color(225, 0, 0),
            new Color(27, 47, 228),
            new Color(17, 187, 34),
            new Color(242, 227, 43),
            new Color(151, 151, 151));

    public HlavniOkno() {
        initComponents();
    }

    // obsluzne metody
    private void priOtevreniOkna(WindowEvent e) {
        nastavUzivatelskouPaletu(new Color(0, 204, 204));
    }

    private void priVyberuMenuPaleta1(ActionEvent e) {
        nastavDefinovanouPaletu(seznamBarev1);
        priVyberuNastavenePalety();
    }

    private void priVyberuMenuPaleta2(ActionEvent e) {
        nastavDefinovanouPaletu(seznamBarev2);
        priVyberuNastavenePalety();
    }

    private void priVyberuMenuPaleta3(ActionEvent e) {
        nastavDefinovanouPaletu(seznamBarev3);
        priVyberuNastavenePalety();
    }

    private void priVyberuMenuOdstiny(ActionEvent e) {
        priVyberuNastavenePalety();
        Color vybranaBarva = (Color) JColorChooser.showDialog(null, "Vyber základní barvu, budou dopočítány tmavší odstíny", Color.white);
        if (vybranaBarva != null) {
            nastavUzivatelskouPaletu(vybranaBarva);
        } else {
            JOptionPane.showMessageDialog(this, "Nevybral jsi žádnou barvu.\nByla nastavena bílá.", "Zvol barvu", JOptionPane.INFORMATION_MESSAGE);
            nastavUzivatelskouPaletu(Color.white);
        }

    }

    private void priVyberuMenuUzivatelska(ActionEvent e) {
        jeEditovatelne = true;
        vymazX();
        zvolenaBarva = null;
        labHelp.setText("Levé tlačítko: Výběr barvy. Pravé tlačítko: Nastavení barvy.");
        labBarva0.setBackground(Color.white);
        labBarva1.setBackground(Color.white);
        labBarva2.setBackground(Color.white);
        labBarva3.setBackground(Color.white);
        labBarva4.setBackground(Color.white);
        labBarva5.setBackground(Color.white);

    }

    private void priVyberuMenuOtevrit(ActionEvent e) {
        JFileChooser dialog;
        if (otevrenySoubor == null) {
            dialog = new JFileChooser(".");
        } else {
            dialog = new JFileChooser(otevrenySoubor.getParentFile());
        }
        dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
        dialog.setMultiSelectionEnabled(false);
        dialog.addChoosableFileFilter(new FileNameExtensionFilter("Obrázky (*.png)", "png"));
        int vysledek = dialog.showOpenDialog(this);
        if (vysledek != JFileChooser.APPROVE_OPTION) {
            return;
        }

        otevrenySoubor = dialog.getSelectedFile();
        nahrajObrazek(otevrenySoubor);
    }

    private void priVyberuMenuUlozit(ActionEvent e) {
        ulozObrazek(otevrenySoubor);
    }

    private void priVyberuMenuUlozitJako(ActionEvent e) {
        ulozitJako();
    }

    private void priVyberuMenuUlozitAZavrit(ActionEvent e) {
        ulozObrazek(otevrenySoubor);
        dispose();
    }

    private void priVyberuMenuZavrit(ActionEvent e) {
        dispose();
    }

    private void priVyberuMenuZnovuOtevrit(ActionEvent e) {
        nahrajObrazek(otevrenySoubor);
    }

    private void priStiskuTlacitkaBarva(MouseEvent e) {
        JLabel stisknutaBarva = (JLabel) e.getSource();
        if (SwingUtilities.isRightMouseButton(e)) {
            if (jeEditovatelne) {
                Color vybranaBarva = JColorChooser.showDialog(null, "", Color.white);
                if (vybranaBarva != null) {
                    stisknutaBarva.setBackground(vybranaBarva);
                }
                vymazX();
                zvolenaBarva = stisknutaBarva.getBackground();
                stisknutaBarva.setText("X");
            }

        } else {
            vymazX();
            zvolenaBarva = stisknutaBarva.getBackground();
            stisknutaBarva.setText("X");
        }
    }

    private void labPriStiskuLabObrazek(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        vyplnObrazek(obrazek, x, y, zvolenaBarva);
        labObrazek.repaint();
    }

    // vybarveni obrazku
    /**
     * Vyplni <code>BufferedImage obrazek</code>
     * na pozicich <code>int x</code>, <code>int y</code>
     * barvou <code>Color barva</code>
     */
    public void vyplnObrazek(BufferedImage obrazek, int x, int y, Color barva) {
        if (barva == null) {
            barva = new Color(255, 255, 0);
            JOptionPane.showMessageDialog(this, "Pro vybarvení je nutné vybrat barvu levým tlačítkem.\nByla nastavena žlutá.", "Zvol barvu", JOptionPane.INFORMATION_MESSAGE);
        }

        // Zamez vyplnovani mimo rozsah
        if (x < 0 || x >= obrazek.getWidth() || y < 0 || y >= obrazek.getHeight()) {
            return;
        }

        WritableRaster pixely = obrazek.getRaster();
        int[] novyPixel = new int[] {barva.getRed(), barva.getGreen(), barva.getBlue(), barva.getAlpha()};
        int[] staryPixel = new int[] {255, 255, 255, 255};
        staryPixel = pixely.getPixel(x, y, staryPixel);

        // Pokud uz je pocatecni pixel obarven na cilovou barvu, nic nemen
        if (pixelyMajiStejnouBarvu(novyPixel, staryPixel)) {
            return;
        }

        // Zamez prebarveni cerne cary
        int[] cernyPixel = new int[] {0, 0, 0, staryPixel[3]};
        if (pixelyMajiStejnouBarvu(cernyPixel, staryPixel)) {
            return;
        }

        vyplnovaciSmycka(pixely, x, y, novyPixel, staryPixel);
    }

    /**
     * Provede skutecne vyplneni pomoci zasobniku
     */
    private void vyplnovaciSmycka(WritableRaster raster, int x, int y, int[] novaBarva, int[] nahrazovanaBarva) {
        Rectangle rozmery = raster.getBounds();
        int[] aktualniBarva = new int[] {255, 255, 255, 255};

        Deque<Point> zasobnik = new ArrayDeque<>(rozmery.width * rozmery.height);
        zasobnik.push(new Point(x, y));
        while (zasobnik.size() > 0) {
            Point point = zasobnik.pop();
            x = point.x;
            y = point.y;
            if (!pixelyMajiStejnouBarvu(raster.getPixel(x, y, aktualniBarva), nahrazovanaBarva)) {
                continue;
            }

            // Najdi levou zed, po ceste vyplnuj
            int levaZed = x;
            do {
                raster.setPixel(levaZed, y, novaBarva);
                levaZed--;
            }
            while (levaZed >= 0 && pixelyMajiStejnouBarvu(raster.getPixel(levaZed, y, aktualniBarva), nahrazovanaBarva));
            levaZed++;

            // Najdi pravou zed, po ceste vyplnuj
            int pravaZed = x;
            do {
                raster.setPixel(pravaZed, y, novaBarva);
                pravaZed++;
            }
            while (pravaZed < rozmery.width && pixelyMajiStejnouBarvu(raster.getPixel(pravaZed, y, aktualniBarva), nahrazovanaBarva));
            pravaZed--;

            // Pridej na zasobnik body nahore a dole
            for (int i = levaZed; i <= pravaZed; i++) {
                if (y > 0 && pixelyMajiStejnouBarvu(raster.getPixel(i, y - 1, aktualniBarva), nahrazovanaBarva)) {
                    if (!(i > levaZed && i < pravaZed
                            && pixelyMajiStejnouBarvu(raster.getPixel(i - 1, y - 1, aktualniBarva), nahrazovanaBarva)
                            && pixelyMajiStejnouBarvu(raster.getPixel(i + 1, y - 1, aktualniBarva), nahrazovanaBarva))) {
                        zasobnik.add(new Point(i, y - 1));
                    }
                }
                if (y < rozmery.height - 1 && pixelyMajiStejnouBarvu(raster.getPixel(i, y + 1, aktualniBarva), nahrazovanaBarva)) {
                    if (!(i > levaZed && i < pravaZed
                            && pixelyMajiStejnouBarvu(raster.getPixel(i - 1, y + 1, aktualniBarva), nahrazovanaBarva)
                            && pixelyMajiStejnouBarvu(raster.getPixel(i + 1, y + 1, aktualniBarva), nahrazovanaBarva))) {
                        zasobnik.add(new Point(i, y + 1));
                    }
                }
            }
        }
    }

    /**
     * Vrati true pokud RGB hodnoty v polich jsou stejne. Pokud jsou ruzne, vraci false
     */
    private boolean pixelyMajiStejnouBarvu(int[] barva1, int[] barva2) {
        return barva1[0] == barva2[0] && barva1[1] == barva2[1] && barva1[2] == barva2[2];
    }


    // prace se souborem
    private void nahrajObrazek(File soubor) {
        try {
            obrazek = ImageIO.read(soubor);
        } catch (IOException ex) {
            throw new ApplicationPublicException(ex, "Nepodařilo se nahrát obrázek mandaly ze souboru " + soubor.getAbsolutePath());
        }
        labObrazek.setIcon(new ImageIcon(obrazek));
        labObrazek.setMinimumSize(new Dimension(obrazek.getWidth(), obrazek.getHeight()));
        pack();
        setMinimumSize(getSize());
    }

    private void ulozObrazek(File soubor) {
        try {
            ImageIO.write(obrazek, "png", soubor);
        } catch (IOException ex) {
            throw new ApplicationPublicException(ex, "Nepodařilo se uložit obrázek mandaly do souboru " + soubor.getAbsolutePath());
        }
    }

    private void ulozitJako() {
        JFileChooser dialog;
        dialog = new JFileChooser(".");

        int vysledek = dialog.showSaveDialog(this);
        if (vysledek != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File soubor = dialog.getSelectedFile();
        if (!soubor.getName().contains(".") && !soubor.exists()) {
            soubor = new File(soubor.getParentFile(), soubor.getName() + ".png");
        }
        if (soubor.exists()) {
            int potvrzeni = JOptionPane.showConfirmDialog(this, "Soubor " + soubor.getName() + " už existuje.\nChcete jej přepsat?", "Přepsat soubor?", JOptionPane.YES_NO_OPTION);
            if (potvrzeni == JOptionPane.NO_OPTION) {
                return;
            }
        }
        ulozObrazek(soubor);
        otevrenySoubor = soubor;
    }

    //pracovni metody
    private void nastavDefinovanouPaletu(List<Color> seznamBarev) {
        labBarva0.setBackground(Color.WHITE);
        labBarva1.setBackground(seznamBarev.get(0));
        labBarva2.setBackground(seznamBarev.get(1));
        labBarva3.setBackground(seznamBarev.get(2));
        labBarva4.setBackground(seznamBarev.get(3));
        labBarva5.setBackground(seznamBarev.get(4));
        labHelp.setText("Levé tlačíko: Výběr barvy");
    }

    private void nastavUzivatelskouPaletu(Color zadanaBarva) {
        labBarva0.setBackground(Color.white);
        labBarva1.setBackground(zadanaBarva);
        labBarva2.setBackground(spocitejBarvu(zadanaBarva, 0.8));
        labBarva3.setBackground(spocitejBarvu(zadanaBarva, 0.6));
        labBarva4.setBackground(spocitejBarvu(zadanaBarva, 0.4));
        labBarva5.setBackground(spocitejBarvu(zadanaBarva, 0.2));
        labHelp.setText("Levé tlačíko: Výběr barvy");
    }

    private Color spocitejBarvu(Color barva, double pomer) {
        int cervena = barva.getRed();
        int zelena = barva.getGreen();
        int modra = barva.getBlue();
        if (cervena == 0 && zelena == 0 && modra == 0) {
            cervena = 100;
            zelena = 100;
            modra = 100;
        }
        int newCervena = (int) round(cervena * pomer);
        int newZelena = (int) round(zelena * pomer);
        int newModra = (int) round(modra * pomer);
        return (new Color(newCervena, newZelena, newModra));
    }

    private void vymazX() {
        labBarva0.setText("");
        labBarva1.setText("");
        labBarva2.setText("");
        labBarva3.setText("");
        labBarva4.setText("");
        labBarva5.setText("");
    }

    private void priVyberuNastavenePalety() {
        jeEditovatelne = false;
        vymazX();
        zvolenaBarva = null;
            }

    //okno
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        menu1 = new JMenuBar();
        menuSoubor = new JMenu();
        mnItemOtevrit = new JMenuItem();
        mnItemZnovuOtevrit = new JMenuItem();
        mnItemUlozit = new JMenuItem();
        mnItemUlozitJako = new JMenuItem();
        mnItemUlozitAZavrit = new JMenuItem();
        mnItemZavrit = new JMenuItem();
        menuPaleta = new JMenu();
        mnItemPaleta1 = new JMenuItem();
        mnItemPaleta2 = new JMenuItem();
        mnItemPaleta3 = new JMenuItem();
        mnItemOdstiny = new JMenuItem();
        mnItemUzivatelska = new JMenuItem();
        labBarva0 = new JLabel();
        labBarva1 = new JLabel();
        labBarva2 = new JLabel();
        labBarva3 = new JLabel();
        labBarva4 = new JLabel();
        labBarva5 = new JLabel();
        labHelp = new JLabel();
        labObrazek = new JLabel();
        label1 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mandala");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                priOtevreniOkna(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets rel,hidemode 3",
            // columns
            "[24,fill]" +
            "[30,fill]" +
            "[30,fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[grow,fill]",
            // rows
            "[35,fill]" +
            "[190]" +
            "[]"));
        this.contentPane = (JPanel) this.getContentPane();
        this.contentPane.setBackground(this.getBackground());
        LayoutManager layout = this.contentPane.getLayout();
        if (layout instanceof MigLayout) {
            this.migLayoutManager = (MigLayout) layout;
        }

        //======== menu1 ========
        {

            //======== menuSoubor ========
            {
                menuSoubor.setText("Soubor");

                //---- mnItemOtevrit ----
                mnItemOtevrit.setText("Otev\u0159\u00edt obr\u00e1zek");
                mnItemOtevrit.addActionListener(e -> priVyberuMenuOtevrit(e));
                menuSoubor.add(mnItemOtevrit);

                //---- mnItemZnovuOtevrit ----
                mnItemZnovuOtevrit.setText("Znovu otev\u0159\u00edt obr\u00e1zek");
                mnItemZnovuOtevrit.addActionListener(e -> priVyberuMenuZnovuOtevrit(e));
                menuSoubor.add(mnItemZnovuOtevrit);
                menuSoubor.addSeparator();

                //---- mnItemUlozit ----
                mnItemUlozit.setText("Ulo\u017eit");
                mnItemUlozit.addActionListener(e -> priVyberuMenuUlozit(e));
                menuSoubor.add(mnItemUlozit);

                //---- mnItemUlozitJako ----
                mnItemUlozitJako.setText("Ulo\u017eit jako");
                mnItemUlozitJako.addActionListener(e -> priVyberuMenuUlozitJako(e));
                menuSoubor.add(mnItemUlozitJako);
                menuSoubor.addSeparator();

                //---- mnItemUlozitAZavrit ----
                mnItemUlozitAZavrit.setText("Ulo\u017eit a zav\u0159\u00edt");
                mnItemUlozitAZavrit.addActionListener(e -> priVyberuMenuUlozitAZavrit(e));
                menuSoubor.add(mnItemUlozitAZavrit);

                //---- mnItemZavrit ----
                mnItemZavrit.setText("Zav\u0159\u00edt program");
                mnItemZavrit.addActionListener(e -> priVyberuMenuZavrit(e));
                menuSoubor.add(mnItemZavrit);
            }
            menu1.add(menuSoubor);

            //======== menuPaleta ========
            {
                menuPaleta.setText("Barevn\u00e1 paleta");

                //---- mnItemPaleta1 ----
                mnItemPaleta1.setText("P\u0159eddefinovan\u00e1 paleta 1");
                mnItemPaleta1.setIcon(new ImageIcon(getClass().getResource("/ikona1.png")));
                mnItemPaleta1.addActionListener(e -> priVyberuMenuPaleta1(e));
                menuPaleta.add(mnItemPaleta1);

                //---- mnItemPaleta2 ----
                mnItemPaleta2.setText("P\u0159eddefinovan\u00e1 paleta 2");
                mnItemPaleta2.setIcon(new ImageIcon(getClass().getResource("/ikona2.png")));
                mnItemPaleta2.addActionListener(e -> priVyberuMenuPaleta2(e));
                menuPaleta.add(mnItemPaleta2);

                //---- mnItemPaleta3 ----
                mnItemPaleta3.setText("P\u0159eddefinovan\u00e1 paleta 3");
                mnItemPaleta3.setIcon(new ImageIcon(getClass().getResource("/ikona3.png")));
                mnItemPaleta3.addActionListener(e -> priVyberuMenuPaleta3(e));
                menuPaleta.add(mnItemPaleta3);
                menuPaleta.addSeparator();

                //---- mnItemOdstiny ----
                mnItemOdstiny.setText("Dopo\u010det barev z jedn\u00e9 barvy");
                mnItemOdstiny.addActionListener(e -> priVyberuMenuOdstiny(e));
                menuPaleta.add(mnItemOdstiny);
                menuPaleta.addSeparator();

                //---- mnItemUzivatelska ----
                mnItemUzivatelska.setText("U\u017eivatelsk\u00e9 barvy");
                mnItemUzivatelska.addActionListener(e -> priVyberuMenuUzivatelska(e));
                menuPaleta.add(mnItemUzivatelska);
            }
            menu1.add(menuPaleta);
        }
        setJMenuBar(menu1);

        //---- labBarva0 ----
        labBarva0.setFont(labBarva0.getFont().deriveFont(labBarva0.getFont().getSize() + 4f));
        labBarva0.setHorizontalAlignment(SwingConstants.CENTER);
        labBarva0.setOpaque(true);
        labBarva0.setBorder(LineBorder.createBlackLineBorder());
        labBarva0.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                priStiskuTlacitkaBarva(e);
            }
        });
        contentPane.add(labBarva0, "cell 1 0,width 30:30:30,height 30:30:30");

        //---- labBarva1 ----
        labBarva1.setFont(labBarva1.getFont().deriveFont(labBarva1.getFont().getSize() + 4f));
        labBarva1.setHorizontalAlignment(SwingConstants.CENTER);
        labBarva1.setOpaque(true);
        labBarva1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                priStiskuTlacitkaBarva(e);
            }
        });
        contentPane.add(labBarva1, "cell 2 0,width 30:30:30,height 30:30:30");

        //---- labBarva2 ----
        labBarva2.setHorizontalAlignment(SwingConstants.CENTER);
        labBarva2.setFont(labBarva2.getFont().deriveFont(labBarva2.getFont().getSize() + 4f));
        labBarva2.setOpaque(true);
        labBarva2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                priStiskuTlacitkaBarva(e);
            }
        });
        contentPane.add(labBarva2, "cell 3 0,width 30:30:30,height 30:30:30");

        //---- labBarva3 ----
        labBarva3.setHorizontalAlignment(SwingConstants.CENTER);
        labBarva3.setFont(labBarva3.getFont().deriveFont(labBarva3.getFont().getSize() + 4f));
        labBarva3.setOpaque(true);
        labBarva3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                priStiskuTlacitkaBarva(e);
            }
        });
        contentPane.add(labBarva3, "cell 4 0,width 30:30:30,height 30:30:30");

        //---- labBarva4 ----
        labBarva4.setHorizontalAlignment(SwingConstants.CENTER);
        labBarva4.setFont(labBarva4.getFont().deriveFont(labBarva4.getFont().getSize() + 4f));
        labBarva4.setOpaque(true);
        labBarva4.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                priStiskuTlacitkaBarva(e);
            }
        });
        contentPane.add(labBarva4, "cell 5 0,width 30:30:30,height 30:30:30");

        //---- labBarva5 ----
        labBarva5.setHorizontalAlignment(SwingConstants.CENTER);
        labBarva5.setFont(labBarva5.getFont().deriveFont(labBarva5.getFont().getSize() + 4f));
        labBarva5.setOpaque(true);
        labBarva5.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                priStiskuTlacitkaBarva(e);
            }
        });
        contentPane.add(labBarva5, "cell 6 0,width 30:30:30,height 30:30:30");
        contentPane.add(labHelp, "cell 7 0");

        //---- labObrazek ----
        labObrazek.setIcon(new ImageIcon(getClass().getResource("/mandala1.png")));
        labObrazek.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                labPriStiskuLabObrazek(e);
            }
        });
        contentPane.add(labObrazek, "cell 0 1 8 1,align left top,grow 0 0");
        pack();
        setLocationRelativeTo(null);

        //---- label1 ----
        label1.setText("text");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

}
