package cz.czechitas.milenakm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;
import net.sevecek.util.*;

public class HlavniOkno extends JFrame {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    JLabel labMile;
    JTextField editMile;
    JButton btnProvedPrevod;
    JLabel labKilometry;
    JTextField editKilometry;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    JPanel contentPane;
    MigLayout migLayoutManager;

    public HlavniOkno() {
        initComponents();
    }

    private void priStiskuBtnProvedPrevod(ActionEvent e) {
        DoubleFormatter prevodnikCisel;
        prevodnikCisel = new DoubleFormatter("0.##");

        String mileText;
        Double mile;
        double km;
        String kmText;
        mileText = editMile.getText();
        mile = prevodnikCisel.parse(mileText);
        km = mile * 1.609344;
        kmText = prevodnikCisel.print(km);

        editKilometry.setText(kmText);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        labMile = new JLabel();
        editMile = new JTextField();
        btnProvedPrevod = new JButton();
        labKilometry = new JLabel();
        editKilometry = new JTextField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("P\u0159evod m\u00edl\u00ed na km");
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 0,hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[grow,fill]" +
            "[fill]",
            // rows
            "[grow,fill]" +
            "[]" +
            "[]" +
            "[]" +
            "[grow]"));
        this.contentPane = (JPanel) this.getContentPane();
        this.contentPane.setBackground(this.getBackground());
        LayoutManager layout = this.contentPane.getLayout();
        if (layout instanceof MigLayout) {
            this.migLayoutManager = (MigLayout) layout;
        }

        //---- labMile ----
        labMile.setText("Vzd\u00e1lenost v m\u00edl\u00edch:");
        labMile.setHorizontalAlignment(SwingConstants.TRAILING);
        labMile.setFont(labMile.getFont().deriveFont(labMile.getFont().getSize() + 4f));
        contentPane.add(labMile, "cell 1 1");

        //---- editMile ----
        editMile.setFont(editMile.getFont().deriveFont(editMile.getFont().getSize() + 4f));
        editMile.setColumns(8);
        contentPane.add(editMile, "cell 2 1 2 1");

        //---- btnProvedPrevod ----
        btnProvedPrevod.setText("Prov\u00e9st p\u0159evod");
        btnProvedPrevod.setFont(btnProvedPrevod.getFont().deriveFont(btnProvedPrevod.getFont().getSize() + 4f));
        btnProvedPrevod.addActionListener(e -> priStiskuBtnProvedPrevod(e));
        this.getRootPane().setDefaultButton(btnProvedPrevod);
        contentPane.add(btnProvedPrevod, "cell 2 2");

        //---- labKilometry ----
        labKilometry.setText("Kilometry:");
        labKilometry.setHorizontalAlignment(SwingConstants.TRAILING);
        labKilometry.setFont(labKilometry.getFont().deriveFont(labKilometry.getFont().getSize() + 4f));
        contentPane.add(labKilometry, "cell 1 3");

        //---- editKilometry ----
        editKilometry.setFont(editKilometry.getFont().deriveFont(editKilometry.getFont().getSize() + 4f));
        editKilometry.setColumns(8);
        contentPane.add(editKilometry, "cell 2 3 2 1");
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}
