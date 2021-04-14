package cz.czechitas.objekty;

import cz.czechitas.objekty.dates.SimpleDate;
import cz.czechitas.objekty.dates.SimpleTime;
import net.sevecek.console.TextTerminal;
import java.awt.*;
import java.util.Random;

public class SpousteciTrida {

    public static void main(String[] args) {
        TextTerminal console;
        console = new TextTerminal();

        /*
        String jmeno;
        int mujVek;
        double mojeVyska;
        jmeno = "Kamil";
        mujVek = 36;
        mojeVyska = 1.80;

        // System.out.println("Ahoj, zdravi vas ");
        console.println("Ahoj, zdravi vas ");
        console.println( jmeno );
        console.println("Muj vek je ");
        console.println( mujVek );
        console.println("Moje vyska je ");
        console.println(mojeVyska);
        */

        /*
        Color barvaKamilovaTricka;
        barvaKamilovaTricka = new Color(57, 186, 251);
        console.print("Moje tricko ma tuto ");
        console.setTextColor(barvaKamilovaTricka);
        console.println("barvu");
        console.setTextColor(null);

        int modraSlozkaKamilovaTricka;
        modraSlozkaKamilovaTricka = barvaKamilovaTricka.getBlue();
        console.println(modraSlozkaKamilovaTricka);

        Dimension rozmeryMehoPocitace;
        rozmeryMehoPocitace = new Dimension(385, 270);
        console.println(rozmeryMehoPocitace);
        console.println(rozmeryMehoPocitace.getWidth());
        console.println(rozmeryMehoPocitace.getHeight());
        */

        //---------------------------------------------------------------------

        /*
        SimpleDate dnesek;
        dnesek = new SimpleDate();
        console.print("Dnes je ");
        console.print(dnesek.getDay() + ". ");
        console.print(dnesek.getMonth() + ". ");
        console.println(dnesek.getYear() + "");
        */

        /*
        SimpleTime aktualniCas;
        aktualniCas = new SimpleTime();
        console.print("Prave je ");
        console.print(aktualniCas.getHour() + ":");
        console.print(aktualniCas.getMinute() + ":");
        console.println(aktualniCas.getSecond());
        */

        /*
        SimpleTime zacatekWorkshopu;
        TimeDuration dobaStudia;
        zacatekWorkshopu = new SimpleTime(9, 30);
        dobaStudia = aktualniCas.between(zacatekWorkshopu);
        console.println(dobaStudia);
        console.println(dobaStudia.getHours());
        console.println(dobaStudia.getMinutes());
        */

        /*
        int denNarozeni;
        int mesicNarozeni
        int rokNarozeni;
        console.print("Zadejte den narozeni: ");
        denNarozeni = console.readInt();
        console.print("Zadejte mesic narozeni: ");
        mesicNarozeni = console.readInt();
        console.print("Zadejte rok narozeni: ");
        rokNarozeni = console.readInt();
        SimpleDate datumNarozeni;
        var datumNarozeni = new SimpleDate(rokNarozeni, mesicNarozeni, denNarozeni);
        console.println("Den v tydnu, kdy jste se narodili: " + datumNarozeni.getDayOfWeek());

        var vek = dnesek.between(datumNarozeni);
        console.println("Mate " + vek.getYears() + " let, " + vek.getMonths() + " mesicu a " + vek.getDays() + " dni.");

        var vekVeDnech = dnesek.betweenTotalDays(datumNarozeni);
        console.println("Coz znamena, ze mate celkove " + vekVeDnech + " dni.");
         */


        /*
        // Vypiste aktualni datum a co je dneska za den (pondeli, utery, ...)
        SimpleDate dnesek;
        dnesek = new SimpleDate();
        console.println("Dnes je "
                + dnesek.getDay() + ". "
                + dnesek.getMonth() + ". "
                + dnesek.getYear() + ", coz je "
                + dnesek.getDayOfWeek());
         */

        /*
        // Program vypise, kolik ma aktualni mesic dni (28, 29, 30 nebo 31)
        SimpleDate dnesek;
        int delkaMesice;
        dnesek = new SimpleDate();
        delkaMesice = dnesek.lengthOfMonth();
        console.println("Aktulani mesic ma " + delkaMesice + " dni.");
         */

        /*
        // Vypiste, jestli je dneska prestupny rok nebo ne.
        SimpleDate dnesek;
        boolean jeAktualniRokPrestupny;
        dnesek = new SimpleDate();
        jeAktualniRokPrestupny = dnesek.isLeapYear();
        if (jeAktualniRokPrestupny) {
            console.println("Rok " + dnesek.getYear() + " je prestupny.");
        } else {
            console.println("Rok " + dnesek.getYear() + " neni prestupny.");
        }
         */

        /*
        // Vypiste, kolik casu zbyva do obeda.
        SimpleTime praveTed;
        SimpleTime obedovyCas;
        TimeDuration rozdil;
        praveTed = new SimpleTime();
        obedovyCas = new SimpleTime(12, 0);
        rozdil = obedovyCas.between(praveTed);
        console.println("Do obeda zbyva " + rozdil.getHours() + ":" + rozdil.getMinutes());
         */

        /*
        // Vypiste, jake datum je za 2 tydny a za 4 tydny.
        SimpleDate dnesek;
        SimpleDate datumZaDvaTydny;
        SimpleDate datumZaCtyriTydny;
        dnesek = new SimpleDate();
        datumZaDvaTydny = dnesek.plusWeeks(2);
        datumZaCtyriTydny = dnesek.plusWeeks(4);
        console.println("Za 2 tydny bude " + datumZaDvaTydny.getDay() + ". "
                + datumZaDvaTydny.getMonth() + ". "
                + datumZaDvaTydny.getYear());
        console.println("Za 4 tydny bude " + datumZaCtyriTydny.getDay() + ". "
                + datumZaCtyriTydny.getMonth() + ". "
                + datumZaCtyriTydny.getYear());
         */

        /*
        // Vypiste, kolik uz uplynulo dni od zacatku roku a kolik zbyva dni do konce roku.
        SimpleDate dnesek;
        SimpleDate zacatekRoku;
        SimpleDate konecRoku;
        int odZacatkuRoku;
        int doKonceRoku;
        dnesek = new SimpleDate();
        zacatekRoku = new SimpleDate(dnesek.getYear(), 1, 1);
        konecRoku = new SimpleDate(dnesek.getYear(), 12, 31);
        odZacatkuRoku = dnesek.betweenTotalDays(zacatekRoku);
        doKonceRoku = konecRoku.betweenTotalDays(dnesek);
        console.println("Od zacatku roku uplynulo " + odZacatkuRoku + " dni, do konce roku zbyva " + doKonceRoku + " dni.");
         */

        /*
        // Načtěte od uživatele čas, kdy se dneska probudil, a rekne uzivateli, jak dlouho uz je dneska vzhuru.
        int hodinaVstavani;
        int minutaVstavani;
        SimpleTime vstavaniCas;
        SimpleTime aktualniCas;
        TimeDuration dobaVzhuru;
        console.print("Zadejte hodinu, kdy jste dneska vstavali: ");
        hodinaVstavani = console.readInt();
        console.print("Zadejte minutu, kdy jste dneska vstavali: ");
        minutaVstavani = console.readInt();
        vstavaniCas = new SimpleTime(hodinaVstavani, minutaVstavani);
        aktualniCas = new SimpleTime();
        dobaVzhuru = aktualniCas.between(vstavaniCas);
        console.println("Jste vzhuru " + dobaVzhuru.getHours() + " h " + dobaVzhuru.getMinutes() + " min");
        */

        // Vypište, na které dny v týdnu připadají letošní státní svátky (Datum + DenVTydnu, Datum + DenVTydnu, ...)
        SimpleDate dnesek;
        int soucasnyRok;
        SimpleDate svatek1;
        SimpleDate svatek2;
        SimpleDate svatek3;
        SimpleDate svatek4;
        SimpleDate svatek5;
        SimpleDate svatek6;
        SimpleDate svatek7;
        SimpleDate svatek8;
        SimpleDate svatek9;
        SimpleDate svatek10;
        SimpleDate svatek11;
        var dnesek = new SimpleDate();
        var soucasnyRok = dnesek.getYear();
        var svatek1 = new SimpleDate(soucasnyRok, 1, 1);
        var svatek2 = new SimpleDate(soucasnyRok, 5, 1);
        var svatek3 = new SimpleDate(soucasnyRok, 5, 8);
        var svatek4 = new SimpleDate(soucasnyRok, 7, 5);
        var svatek5 = new SimpleDate(soucasnyRok, 7, 6);
        var svatek6 = new SimpleDate(soucasnyRok, 9, 28);
        var svatek7 = new SimpleDate(soucasnyRok, 10, 28);
        var svatek8 = new SimpleDate(soucasnyRok, 11, 17);
        var svatek9 = new SimpleDate(soucasnyRok, 12, 24);
        var svatek10 = new SimpleDate(soucasnyRok, 12, 25);
        var svatek11 = new SimpleDate(soucasnyRok, 12, 26);
        console.println("Svatek " + svatek1.getDay() + ". " + svatek1.getMonth() + ". je " + svatek1.getDayOfWeek());
        console.println("Svatek " + svatek2.getDay() + ". " + svatek2.getMonth() + ". je " + svatek2.getDayOfWeek());
        console.println("Svatek " + svatek3.getDay() + ". " + svatek3.getMonth() + ". je " + svatek3.getDayOfWeek());
        console.println("Svatek " + svatek4.getDay() + ". " + svatek4.getMonth() + ". je " + svatek4.getDayOfWeek());
        console.println("Svatek " + svatek5.getDay() + ". " + svatek5.getMonth() + ". je " + svatek5.getDayOfWeek());
        console.println("Svatek " + svatek6.getDay() + ". " + svatek6.getMonth() + ". je " + svatek6.getDayOfWeek());
        console.println("Svatek " + svatek7.getDay() + ". " + svatek7.getMonth() + ". je " + svatek7.getDayOfWeek());
        console.println("Svatek " + svatek8.getDay() + ". " + svatek8.getMonth() + ". je " + svatek8.getDayOfWeek());
        console.println("Svatek " + svatek9.getDay() + ". " + svatek9.getMonth() + ". je " + svatek9.getDayOfWeek());
        console.println("Svatek " + svatek10.getDay() + ". " + svatek10.getMonth() + ". je " + svatek10.getDayOfWeek());
        console.println("Svatek " + svatek11.getDay() + ". " + svatek11.getMonth() + ". je " + svatek11.getDayOfWeek());
    }

}
