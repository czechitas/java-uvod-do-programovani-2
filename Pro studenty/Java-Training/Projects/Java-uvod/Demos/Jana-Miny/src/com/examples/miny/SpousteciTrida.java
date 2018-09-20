package com.examples.miny;

import javax.swing.*;

public class SpousteciTrida {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SpousteciTrida::run);

    }

    public static void run() {
        TazaciOkno okenko = new TazaciOkno();
        okenko.setVisible(true);

    }

}
