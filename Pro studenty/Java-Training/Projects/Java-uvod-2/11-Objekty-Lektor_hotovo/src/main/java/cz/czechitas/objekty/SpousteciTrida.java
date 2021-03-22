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
        datumNarozeni = new SimpleDate(rokNarozeni, mesicNarozeni, denNarozeni);
        console.println("Den v tydnu, kdy jste se narodili: " + datumNarozeni.getDayOfWeek());

        SimpleDate dnesek;
        DateDuration vek;
        dnesek = new SimpleDate();
        vek = dnesek.between(datumNarozeni);
        console.println("Mate " + vek.getYears() + " let, " + vek.getMonths() + " mesicu a " + vek.getDays() + " dni.");

        int vekVeDnech;
        vekVeDnech = dnesek.betweenTotalDays(datumNarozeni);
        console.println("Coz znamena, ze mate celkove " + vekVeDnech + " dni.");
    }

}
