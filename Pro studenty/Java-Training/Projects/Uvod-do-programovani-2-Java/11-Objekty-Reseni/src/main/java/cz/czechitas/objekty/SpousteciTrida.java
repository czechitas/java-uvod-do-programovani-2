package cz.czechitas.objekty;

import java.awt.*;
import java.time.*;
import java.util.*;
import net.sevecek.util.*;

public class SpousteciTrida {

    public static void main(String[] args) {
        String jmeno;
        int mujVek;
        double mojeVyska;

        jmeno = "Kamil";
        jmeno = new String('K', 'a', 'm', 'i', 'l');
        mujVek = 36;
        mojeVyska = 1.80;

        System.out.println("Ahoj, zdravi ");
        System.out.println( jmeno );
        System.out.println("Muj vek je ");
        System.out.println( mujVek );
        System.out.println("Moje vyska je ");
        System.out.println(mojeVyska);

        Color barvaMehoTricka;
        Dimension rozmeryMehoPocitace;

        barvaMehoTricka = new Color(0, 200, 0);
        System.out.println(barvaMehoTricka);
        Color barvaTrickaKamila;
        barvaTrickaKamila = barvaMehoTricka;
        System.out.println(barvaTrickaKamila);

        int modraSlozkaMehoTricka;
        modraSlozkaMehoTricka = barvaMehoTricka.getBlue();
        System.out.println(modraSlozkaMehoTricka);

        rozmeryMehoPocitace = new Dimension(385, 270);
        System.out.println(rozmery);

        LocalDate dnesek;
        dnesek = LocalDate.of(2018, 6, 23);
        System.out.println("Dnes je ");
        System.out.println(dnesek);
        System.out.println(dnesek.getYear());

        LocalDate vanoce;
        vanoce = LocalDate.of(2018, 12, 24);
        System.out.println(vanoce);

        // Vypiste cele datum v ceskem formatu, az to budete mit, ZELENA karta
        System.out.println(dnesek.getDayOfMonth() + ". "
                + dnesek.getMonthValue() + ". " + dnesek.getYear());


        LocalTime praveTed;
        praveTed = LocalTime.of(10, 51);
        System.out.println(praveTed);

        LocalDateTime dneskaTedka;
        dneskaTedka = LocalDateTime.of(dnesek, praveTed);
        System.out.println(dneskaTedka);

        // Bonus: zjistete z dneskaTedka vlastnost datumu a z ni vlastnost den
        // a vypiste na obrazovku
        int den;
        den = dnesHnedTed.toLocalDate().getDayOfMonth();
        System.out.println(den);



        Random generatorNahodnychCisel;
        generatorNahodnychCisel = new Random();

        int hozenoNaKostce = generatorNahodnychCisel.nextInt(6) + 1;
        System.out.print("Kostka: ");
        System.out.println(hozenoNaKostce);


        Scanner nacitacZKlavesnice = new Scanner(System.in);
        String radek;

        System.out.println("Zadejte svoji hmotnost");
        radek = scanner.readLine();

        double mojeHmotnost;
        DoubleFormatter prevodnikCisel;
        prevodnikCisel = new DoubleFormatter("0.##");
        mojeHmotnost = prevodniCisel.convert(radek);
        System.out.println(textHmotnosti);
    }

}
