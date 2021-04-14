package cz.czechitas.milenakm;

import net.sevecek.util.DoubleFormatter;
import java.util.Scanner;

public class SpousteciTrida {

    public static void main(String[] args) {
        DoubleFormatter prevodnikCisel;
        Scanner console;
        prevodnikCisel = new DoubleFormatter("0.##");
        console = new Scanner(System.in);
        System.out.print("Zadejte vzdalenost v milich: ");

        String mileText;
        Double mile;
        double km;
        String kmText;
        mileText = console.nextLine();
        mile = prevodnikCisel.parse(mileText);
        km = mile * 1.609344;
        kmText = prevodnikCisel.print(km);

        System.out.println("Vzdalenost v km: " + kmText);
    }

}
