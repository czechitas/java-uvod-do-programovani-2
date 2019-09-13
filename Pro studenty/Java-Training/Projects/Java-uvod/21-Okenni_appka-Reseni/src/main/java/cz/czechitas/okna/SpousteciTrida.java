package cz.czechitas.okna;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class SpousteciTrida {

    public static void main(String[] args) {
        JFrame okno;
        JLabel napis;
        Font vetsiPismo;

        okno = new JFrame("Dnesni datum");
        okno.setSize(400, 300);
        okno.setLocationRelativeTo(null);
        okno.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        napis = new JLabel("Ahoj, zdravi Kamil");
        napis.setHorizontalAlignment(SwingConstants.CENTER);
        okno.add(napis);

        vetsiPismo = new Font("Arial", Font.BOLD, 36);
        napis.setFont(vetsiPismo);

        Random generatorNahodnychCisel;
        generatorNahodnychCisel = new Random();

        int nahodneCislo1 = generatorNahodnychCisel.nextInt(256);
        int nahodneCislo2 = generatorNahodnychCisel.nextInt(256);
        int nahodneCislo3 = generatorNahodnychCisel.nextInt(256);
        Color nahodnaBarva;
        nahodnaBarva = new Color(
                nahodneCislo1, nahodneCislo2, nahodneCislo3);
        napis.setForeground(nahodnaBarva);

        int nahodneCislo4 = generatorNahodnychCisel.nextInt(256);
        int nahodneCislo5 = generatorNahodnychCisel.nextInt(256);
        int nahodneCislo6 = generatorNahodnychCisel.nextInt(256);
        Color nahodnaBarva2 = new Color(
                nahodneCislo4, nahodneCislo5, nahodneCislo6);
        napis.setBackground(nahodnaBarva2);
        napis.setOpaque(true);

        okno.setVisible(true);
    }

}
