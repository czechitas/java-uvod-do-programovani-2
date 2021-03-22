package cz.czechitas.objekty;

import java.awt.*;

public class SpousteciTrida {

    public static void main(String[] args) {
        String jmeno;
        int mujVek;
        double mojeVyska;

        jmeno = "Kamil";
        mujVek = 36;
        mojeVyska = 1.80;

        System.out.println("Ahoj, zdravi ");
        System.out.println( jmeno );
        System.out.println("Muj vek je ");
        System.out.println( mujVek );
        System.out.println("Moje vyska je ");
        System.out.println(mojeVyska);

        for (int i = 0; i < 5; i++) {
            System.out.println(i + ". kralik");
        }

        Color barvaMehoTricka;
        Dimension rozmeryMehoPocitace;

        barvaMehoTricka = new Color(57, 186, 251);
        System.out.println(barvaMehoTricka);
        Color barvaTrickaKamila;
        barvaTrickaKamila = barvaMehoTricka;
        System.out.println(barvaTrickaKamila);

        int modraSlozkaMehoTricka;
        modraSlozkaMehoTricka = barvaMehoTricka.getBlue();
        System.out.println(modraSlozkaMehoTricka);

        rozmeryMehoPocitace = new Dimension(385, 270);
        System.out.println(rozmeryMehoPocitace);
        System.out.println(rozmeryMehoPocitace.getWidth());
        System.out.println(rozmeryMehoPocitace.getHeight());
    }

}
