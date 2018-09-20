package com.examples.miny;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/* Trida reprezentujici 1 policko na hraci desce
*/

public class Policko extends JLabel {
    public boolean jeMina;
    public boolean jeOtocene;
    public int pocetOkolnichMin;
    public boolean jeZkontrolovano;
    public boolean jeOznacenoJakoMina;

    // metoda nastavi vybranemu policku, ze je v nem mina
    public void priradMinu(Policko policko)    {
      //  policko.setBackground(Color.yellow);
        policko.setOpaque(true);
        policko.jeMina = true;
    }

    //testovani, jestli je v danem policku ulozena mina
    public boolean jeMina(Policko policko)  {
        return policko.jeMina;
    }

    //metoda oznaci policka, ktera uz byla pri hre otocena
    public void nastavOtoceni() {
        jeOtocene = true;
    }

    //testovani, jestli policko bylo ve hre otocene
    public boolean jeOtocene() {
        return jeOtocene;
    }

    //metoda, pomoci ktera se testuje, jestli
    public void nastavZkontrolovano() {
        jeZkontrolovano = true;
    }

    public boolean JeZkontrolovano() {
        return jeZkontrolovano;
    }

    //metoda, ktera policku nastavi, ze si hrac mysli, ze je to mina
    public void nastavOznaceniJakoMina() {
        jeOznacenoJakoMina = true;
    }

    //testovani, jestli policko hrac oznacil jako minu
    public boolean jeOznacenoJakoMina() {
        return jeOznacenoJakoMina;
    }

    //metoda, ktera danemu policku priradi pocet okolnich min
    public void priradCislo(Policko policko, int pocet) {
        pocetOkolnichMin = pocet;
    }

    //metoda, ktera do hraciho okna vypise policku, na ktere se kliklo, pocet okolnich min
    public void ukazCislo(Policko policko) {
        switch (policko.pocetOkolnichMin) {
            case 1:
                policko.setForeground(Color.blue);
                break;
            case 2:
                policko.setForeground(Color.green);
                break;
            case 3:
                policko.setForeground(Color.red);
                break;
            case 4:
                policko.setForeground(Color.darkGray);
                break;
            case 5:
                policko.setForeground(Color.orange);
                break;
            case 6:
                policko.setForeground(Color.magenta);
                break;
            case 7:
                policko.setForeground(Color.yellow);
                break;
            case 8:
                policko.setForeground(Color.black);
                break;
        }
        policko.setText("" + policko.pocetOkolnichMin);
    }

    //metoda, ktera v hracim okne zvyrazni policko, ktere nesousedi s zadnou minou
    public void nastavNulaMin(Policko policko) {
        policko.setBorder(new BevelBorder(BevelBorder.LOWERED));
        policko.setBackground(Color.pink);
        policko.setOpaque(true);
    }

    //metoda, ktera nakresli minu do policka a barevne ho zvyrazni - pouziva se pro minu urcenou hracem i pri vybuchu
    public void ukazMinu(Policko policko, int r, int g, int b, String text)  {
        //policko.setFont(policko.getFont().deriveFont(policko.getFont().getStyle() | Font.BOLD, policko.getFont().getSize() + 10f));
        policko.setBackground(new Color(r, g, b));
        policko.setText(text);
        policko.setOpaque(true);
    }
}
