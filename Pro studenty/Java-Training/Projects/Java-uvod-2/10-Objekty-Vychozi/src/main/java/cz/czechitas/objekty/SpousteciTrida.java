package cz.czechitas.objekty;

import cz.czechitas.objekty.dates.SimpleDate;
import cz.czechitas.objekty.dates.SimpleTime;
import net.sevecek.console.TextTerminal;
import java.awt.*;
import java.util.Random;

public class SpousteciTrida {

    public static void main(String[] args) {
        System.out.println("Ahoj, zdravim vas!");

        TextTerminal console;
        console = new TextTerminal();
        console.println("Ahoj, opet vas zdravim!");

        String jmeno;
        int mujVek;
        double mojeVyska;
        jmeno = "Kamil";
        mujVek = 36;
        mojeVyska = 1.80;

        console.println("Ahoj, zdravi vas ");
        console.println(jmeno);
        console.println("Muj vek je ");
        console.println(mujVek);
        console.println("Moje vyska je ");
        console.println(mojeVyska);
    }

}
