package com.examples.miny;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.*;

//   Trida pro zobrazeni dialogoveho okna s volbou obtiznosti hry

public class TazaciOkno extends JFrame {

    JLabel labObtiznost;
    JRadioButton btnLehka;
    JRadioButton btnStredni;
    JRadioButton btnTezka;
    JLabel labOK;

    final int MALY_ROZMER = 5;
    final int STREDNI_ROZMER = 16;
    final int VELKY_ROZMER = 30;
    final int MALY_POCET_MIN = 10;
    final int STREDNI_POCET_MIN = 40;
    final int VELKY_POCET_MIN = 99;

    public TazaciOkno() {
        initComponents();
    }

    private void labOKMouseClicked(MouseEvent e) {
        int sirkaPole, vyskaPole, pocetMin, obtiznost;

        if (btnTezka.isSelected() == true ) {
            sirkaPole = VELKY_ROZMER;
            vyskaPole = STREDNI_ROZMER;
            pocetMin = VELKY_POCET_MIN;
            obtiznost = 3;
        }  else if (btnStredni.isSelected() == true)  {
            sirkaPole = STREDNI_ROZMER;
            vyskaPole = STREDNI_ROZMER;
            pocetMin = STREDNI_POCET_MIN;
            obtiznost = 2;
        }  else {
            sirkaPole = STREDNI_ROZMER;
            vyskaPole = MALY_ROZMER;
            pocetMin = MALY_POCET_MIN;
            obtiznost = 1;
        }

        this.setVisible(false);

        HlavniOkno okno = new HlavniOkno(sirkaPole, vyskaPole, pocetMin, obtiznost);
        okno.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        labObtiznost = new JLabel();
        btnLehka = new JRadioButton();
        btnStredni = new JRadioButton();
        btnTezka = new JRadioButton();
        labOK = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(13, 47));
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
                "insets 0,hidemode 3,gap 0 0",
                // columns
                "[14,grow,fill]" +
                        "[14,grow,fill]" +
                        "[84,grow,left]" +
                        "[55,grow,fill]" +
                        "[14,grow,fill]",
                // rows
                "[35,grow,fill]" +
                        "[12,grow]" +
                        "[34,grow]" +
                        "[35,grow]" +
                        "[31,grow,fill]" +
                        "[0,grow]" +
                        "[27,grow,fill]" +
                        "[14,grow]"));

        //---- labObtiznost ----
        labObtiznost.setText("Zvolte obt\u00ed\u017enost:");
        labObtiznost.setFont(labObtiznost.getFont().deriveFont(labObtiznost.getFont().getSize() + 4f));
        contentPane.add(labObtiznost, "cell 1 0 3 1");

        //---- btnLehka ----
        btnLehka.setText("Lehk\u00e1");
        btnLehka.setFont(btnLehka.getFont().deriveFont(btnLehka.getFont().getSize() + 3f));
        btnLehka.setSelected(true);
        contentPane.add(btnLehka, "cell 2 2");

        //---- btnStredni ----
        btnStredni.setText("St\u0159edn\u00ed");
        btnStredni.setFont(btnStredni.getFont().deriveFont(btnStredni.getFont().getSize() + 3f));
        contentPane.add(btnStredni, "cell 2 3");

        //---- btnTezka ----
        btnTezka.setText("T\u011b\u017ek\u00e1");
        btnTezka.setFont(btnTezka.getFont().deriveFont(btnTezka.getFont().getSize() + 3f));
        contentPane.add(btnTezka, "cell 2 4");

        //---- labOK ----
        labOK.setText("OK");
        labOK.setHorizontalAlignment(SwingConstants.CENTER);
        labOK.setBorder(new BevelBorder(BevelBorder.RAISED));
        labOK.setFont(labOK.getFont().deriveFont(labOK.getFont().getSize() + 4f));
        labOK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                labOKMouseClicked(e);
            }
        });
        contentPane.add(labOK, "cell 3 6");
        pack();
        setLocationRelativeTo(getOwner());

        //---- buttonGroup1 ----
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(btnLehka);
        buttonGroup1.add(btnStredni);
        buttonGroup1.add(btnTezka);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


}
