package cz.czechitas.miny;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/* Trida pro zobrazeni dialogoveho okna na konci hry - pri vyhre, prohre i predcasnem ukonceni
   vybira se mezi novou hrou (dialog Tazaci okno) nebo ukoncenim     */

public class OknoKonec extends JFrame {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private String textKonec;
    JLabel labVyhralJsi;
    JButton btnAno;
    JButton btnNe;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public OknoKonec(String textKonec) {
        this.textKonec = textKonec;
        initComponents();
    }

    private void btnAnoActionPerformed(ActionEvent e) {
        this.setVisible(false);
        TazaciOkno okenko = new TazaciOkno();
        okenko.setVisible(true);
    }

    private void btnNeActionPerformed(ActionEvent e) {
        HlavniOkno okno = new HlavniOkno(1,1,1,1);
        okno.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        labVyhralJsi = new JLabel();
        btnAno = new JButton();
        btnNe = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
                "insets 0,hidemode 3",
                // columns
                "[20,grow,fill]" +
                        "[70,grow,fill]" +
                        "[52,grow,fill]" +
                        "[67,grow,fill]" +
                        "[14,grow,fill]",
                // rows
                "[18,grow]" +
                        "[14,grow,sizegroup 1,fill]" +
                        "[2,grow]" +
                        "[10,grow,sizegroup 1,fill]" +
                        "[11,grow]"));

        //---- labVyhralJsi ----
        labVyhralJsi.setText(textKonec);
        labVyhralJsi.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(labVyhralJsi, "cell 1 1 3 1,growx");

        //---- btnAno ----
        btnAno.setText("Nová hra");
        btnAno.addActionListener(e -> btnAnoActionPerformed(e));
        contentPane.add(btnAno, "cell 1 3,growy");

        //---- btnNe ----
        btnNe.setText("Konec");
        btnNe.addActionListener(e -> btnNeActionPerformed(e));
        contentPane.add(btnNe, "cell 3 3,growy");
        setSize(312, 178);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}


