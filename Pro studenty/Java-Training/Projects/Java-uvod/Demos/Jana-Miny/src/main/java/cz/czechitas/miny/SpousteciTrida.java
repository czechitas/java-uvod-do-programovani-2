package cz.czechitas.miny;

import javax.swing.*;
import net.sevecek.util.swing.*;

public class SpousteciTrida {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SpousteciTrida::run);
    }

    public static void run() {
        SwingExceptionHandler.install();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (NullPointerException | ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Nepodarilo se nastavit System look and feel pro Swing");
        }

        TazaciOkno okenko = new TazaciOkno();
        okenko.setVisible(true);
    }

}
