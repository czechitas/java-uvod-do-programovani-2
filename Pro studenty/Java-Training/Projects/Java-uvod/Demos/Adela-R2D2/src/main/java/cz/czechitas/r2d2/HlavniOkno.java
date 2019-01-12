package cz.czechitas.r2d2;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.Timer;
import net.sevecek.util.swing.*;

public class HlavniOkno extends JFrame {

    JLabel labRobot;
    JLabel labZed1;
    JLabel labZed2;
    JLabel labZed3;
    JLabel labZed4;
    JLabel labZed5;
    JLabel labZed6;
    JLabel labZed7;
    JLabel labLuke;
    JLabel labZed8;
    JLabel labZed9;
    JLabel labZed10;
    JLabel labZed11;
    JLabel labZed12;
    JLabel labZed13;
    JLabel labZed14;
    JLabel labZed15;
    JLabel labZed16;
    JLabel labZed17;
    JLabel labTroop1;
    JLabel labTroop2;
    JLabel labTroop3;
    JLabel labLeia;
    JLabel labObivan;
    JLabel labZed18Cil;
    JLabel labZed19;
    JPanel contentPane;
    int posun = 5;
    Point puvodniPoziceRobota;
    Timer casovac;
    JKeyboard klavesnice;
    ArrayList<JLabel> seznamZdi;
    ArrayList<JLabel> seznamTroops;

    public HlavniOkno() {
        initComponents();
    }

    private void PriStiskuKlavesyPohybRobota(ActionEvent e) {
        puvodniPoziceRobota = labRobot.getLocation();
        int vyskaOkna = contentPane.getHeight();
        int vyskaRobota = labRobot.getHeight();
        int sirkaOkna = contentPane.getWidth();
        int sirkaRobota = labRobot.getWidth();
        Point poziceRobota = labRobot.getLocation();
        int x = poziceRobota.x;
        int y = poziceRobota.y;

        labRobot.setLocation(poziceRobota);

        //pohyb robota doprava
        if (klavesnice.isKeyDown(KeyEvent.VK_RIGHT)) {
            labRobot.setIcon(new ImageIcon(getClass().getResource("/r2d2-vpravo.png")));
            if (x >= sirkaOkna - sirkaRobota) {
                x = x;
            } else {
                x = x + posun;
            }
            labRobot.setLocation(x, y);
        }
        //pohyb robota doleva
        if (klavesnice.isKeyDown(KeyEvent.VK_LEFT)) {
            labRobot.setIcon(new ImageIcon(getClass().getResource("/r2d2-vlevo.png")));
            if (x <= 0) {
                x = x;
            } else {
                x = x - posun;
            }
            labRobot.setLocation(x, y);
        }
        //pohyb robota nahoru
        if (klavesnice.isKeyDown(KeyEvent.VK_UP)) {
            if (y <= 0) {
                y = y;
            } else {
                y = y - posun;
            }
            labRobot.setLocation(x, y);
        }
        //pohyb robota dolu
        if (klavesnice.isKeyDown(KeyEvent.VK_DOWN)) {
            if (y <= vyskaOkna - vyskaRobota) {
                y = y;
            } else {
                y = y - posun;
            }
            y = y + posun;
            labRobot.setLocation(x, y);
        }
        //co se stane pri stretu se zdi
        if (kolizeSeZdi() || (x < 0) || ((x + labRobot.getWidth()) > (contentPane.getWidth()))) {
            labRobot.setLocation(puvodniPoziceRobota);
        }
        //co se stane pri  stretu s vojaky
        if (kolizeSTroops()) {
            playWav("/auu.wav");
            startHry();
        }
        //co se stane pri stretu s Obivanem
        if (detekujKolizi(labObivan, labRobot)) {
            playWav("/obivan.wav");
            labObivan.setVisible(false);
            labLeia.setVisible(true);
        }
        //co se stane pri stretu s Leiou
        if (detekujKolizi(labLeia, labRobot)) {
            playWav("/leia.wav");
            labLeia.setVisible(false);
            labLuke.setVisible(true);
        }
        //co se stane pri stretu s Lukem
        if (detekujKolizi(labLuke, labRobot)) {
            playWav("/luke.wav");
            labLuke.setVisible(false);
            labZed19.setVisible(false);
            labZed18Cil.setBackground(Color.yellow);
        }
        //co se stane pri stretu s cilem
        if (detekujKolizi(labZed18Cil, labRobot)) {
            playWav("/cil.wav");
            labZed18Cil.setVisible(false);
        }
    }

    private void playWav(String wav) {
        // muzu to napsat takhle do zavorky za try, aby se odkazy na hraci soubory na konci try bloku samy zavrely
        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(wav).openStream())) {  //cesta k souborum v class
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) { //vsechny vyjimky muzu napsat vedle sebe do jednoho radku, protoze na ne reaguju stejne
            return; // ignore now
        }
    }

    private boolean detekujKolizi(JLabel label1, JLabel label2) {
        Point souradniceLab1 = label1.getLocation();
        int ax = souradniceLab1.x;
        int ay = souradniceLab1.y;

        int bx = ax + label1.getWidth();
        int by = ay + label1.getHeight();

        Point souradniceLab2 = label2.getLocation();
        int cx = souradniceLab2.x;
        int cy = souradniceLab2.y;

        int dx = cx + label2.getWidth();
        int dy = cy + label2.getHeight();

        if (ax <= dx && ay <= dy && cx <= bx && cy <= by && label1.isVisible() && label2.isVisible()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean kolizeSeZdi() {
        for (JLabel labZed : seznamZdi) {
            if (detekujKolizi(labZed, labRobot)) {
                return true;
            }
        }
        return false;
    }

    private boolean kolizeSTroops() {
        for (JLabel labTroop : seznamTroops) {
            if (detekujKolizi(labTroop, labRobot)) {
                return true;
            }
        }
        return false;
    }

    private void priOtevreniOkna(WindowEvent e) {
        casovac = new Timer(50, it -> PriStiskuKlavesyPohybRobota(it));
        casovac.start();
        seznamZdi = new ArrayList<>();
        klavesnice = new JKeyboard();
        seznamZdi.add(labZed1);
        seznamZdi.add(labZed2);
        seznamZdi.add(labZed3);
        seznamZdi.add(labZed4);
        seznamZdi.add(labZed5);
        seznamZdi.add(labZed6);
        seznamZdi.add(labZed7);
        seznamZdi.add(labZed8);
        seznamZdi.add(labZed9);
        seznamZdi.add(labZed10);
        seznamZdi.add(labZed11);
        seznamZdi.add(labZed12);
        seznamZdi.add(labZed13);
        seznamZdi.add(labZed14);
        seznamZdi.add(labZed15);
        seznamZdi.add(labZed16);
        seznamZdi.add(labZed17);
        seznamZdi.add(labZed19);

        seznamTroops = new ArrayList<>();
        seznamTroops.add(labTroop1);
        seznamTroops.add(labTroop2);
        seznamTroops.add(labTroop3);

        startHry();
    }

    private void startHry() {
        labRobot.setIcon(new ImageIcon(getClass().getResource("/r2d2-vlevo.png")));
        labRobot.setBounds(new Rectangle(new Point(0, 30), labRobot.getPreferredSize()));
        labLeia.setVisible(false);
        labLeia.setBounds(new Rectangle(new Point(380, 425), labLeia.getPreferredSize()));
        labLuke.setVisible(false);
        labObivan.setVisible(true);
        labZed18Cil.setBackground(new Color(51, 204, 255));
        labZed18Cil.setVisible(true);
        labZed19.setVisible(true);

    }

    private void priZavreniOkna(WindowEvent e) {
        casovac.stop();
    }

    private void initComponents() {
        labRobot = new JLabel();
        labZed1 = new JLabel();
        labZed2 = new JLabel();
        labZed3 = new JLabel();
        labZed4 = new JLabel();
        labZed5 = new JLabel();
        labZed6 = new JLabel();
        labZed7 = new JLabel();
        labLuke = new JLabel();
        labZed8 = new JLabel();
        labZed9 = new JLabel();
        labZed10 = new JLabel();
        labZed11 = new JLabel();
        labZed12 = new JLabel();
        labZed13 = new JLabel();
        labZed14 = new JLabel();
        labZed15 = new JLabel();
        labZed16 = new JLabel();
        labZed17 = new JLabel();
        labTroop1 = new JLabel();
        labTroop2 = new JLabel();
        labTroop3 = new JLabel();
        labLeia = new JLabel();
        labObivan = new JLabel();
        labZed18Cil = new JLabel();
        labZed19 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("R2D2");
        setBackground(new Color(0, 0, 51));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                priZavreniOkna(e);
            }
            @Override
            public void windowOpened(WindowEvent e) {
                priOtevreniOkna(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        this.contentPane = (JPanel) this.getContentPane();
        this.contentPane.setBackground(this.getBackground());

        //---- labRobot ----
        labRobot.setIcon(new ImageIcon(getClass().getResource("/r2d2-vlevo.png")));
        contentPane.add(labRobot);
        labRobot.setBounds(new Rectangle(new Point(0, 30), labRobot.getPreferredSize()));

        //---- labZed1 ----
        labZed1.setBackground(new Color(51, 204, 255));
        labZed1.setOpaque(true);
        contentPane.add(labZed1);
        labZed1.setBounds(0, 0, 605, 20);

        //---- labZed2 ----
        labZed2.setBackground(new Color(51, 204, 255));
        labZed2.setOpaque(true);
        contentPane.add(labZed2);
        labZed2.setBounds(0, 500, 605, 25);

        //---- labZed3 ----
        labZed3.setOpaque(true);
        labZed3.setBackground(new Color(51, 204, 255));
        contentPane.add(labZed3);
        labZed3.setBounds(0, 105, 20, 395);

        //---- labZed4 ----
        labZed4.setOpaque(true);
        labZed4.setBackground(new Color(51, 204, 255));
        contentPane.add(labZed4);
        labZed4.setBounds(220, 20, 20, 205);

        //---- labZed5 ----
        labZed5.setOpaque(true);
        labZed5.setBackground(new Color(51, 204, 255));
        contentPane.add(labZed5);
        labZed5.setBounds(90, 305, 20, 200);

        //---- labZed6 ----
        labZed6.setOpaque(true);
        labZed6.setBackground(new Color(51, 204, 255));
        contentPane.add(labZed6);
        labZed6.setBounds(220, 325, 20, 110);

        //---- labZed7 ----
        labZed7.setOpaque(true);
        labZed7.setBackground(new Color(51, 204, 255));
        contentPane.add(labZed7);
        labZed7.setBounds(310, 100, 20, 350);

        //---- labLuke ----
        labLuke.setIcon(new ImageIcon(getClass().getResource("/luke.png")));
        contentPane.add(labLuke);
        labLuke.setBounds(520, 20, 65, 70);

        //---- labZed8 ----
        labZed8.setBackground(new Color(51, 204, 255));
        labZed8.setOpaque(true);
        contentPane.add(labZed8);
        labZed8.setBounds(20, 105, 130, 20);

        //---- labZed9 ----
        labZed9.setBackground(new Color(51, 204, 255));
        labZed9.setOpaque(true);
        contentPane.add(labZed9);
        labZed9.setBounds(115, 205, 105, 20);

        //---- labZed10 ----
        labZed10.setBackground(new Color(51, 204, 255));
        labZed10.setOpaque(true);
        contentPane.add(labZed10);
        labZed10.setBounds(110, 305, 130, 20);

        //---- labZed11 ----
        labZed11.setBackground(new Color(51, 204, 255));
        labZed11.setOpaque(true);
        contentPane.add(labZed11);
        labZed11.setBounds(425, 285, 100, 20);

        //---- labZed12 ----
        labZed12.setOpaque(true);
        labZed12.setBackground(new Color(51, 204, 255));
        contentPane.add(labZed12);
        labZed12.setBounds(585, 20, 20, 400);

        //---- labZed13 ----
        labZed13.setBackground(new Color(51, 204, 255));
        labZed13.setOpaque(true);
        contentPane.add(labZed13);
        labZed13.setBounds(165, 415, 55, 20);

        //---- labZed14 ----
        labZed14.setOpaque(true);
        labZed14.setBackground(new Color(51, 204, 255));
        contentPane.add(labZed14);
        labZed14.setBounds(490, 125, 20, 80);

        //---- labZed15 ----
        labZed15.setBackground(new Color(51, 204, 255));
        labZed15.setOpaque(true);
        contentPane.add(labZed15);
        labZed15.setBounds(510, 400, 75, 20);

        //---- labZed16 ----
        labZed16.setOpaque(true);
        labZed16.setBackground(new Color(51, 204, 255));
        contentPane.add(labZed16);
        labZed16.setBounds(400, 20, 20, 120);

        //---- labZed17 ----
        labZed17.setOpaque(true);
        labZed17.setBackground(new Color(51, 204, 255));
        contentPane.add(labZed17);
        labZed17.setBounds(425, 305, 20, 145);

        //---- labTroop1 ----
        labTroop1.setIcon(new ImageIcon(getClass().getResource("/troop.png")));
        contentPane.add(labTroop1);
        labTroop1.setBounds(new Rectangle(new Point(25, 130), labTroop1.getPreferredSize()));

        //---- labTroop2 ----
        labTroop2.setIcon(new ImageIcon(getClass().getResource("/troop.png")));
        contentPane.add(labTroop2);
        labTroop2.setBounds(480, 210, 37, 70);

        //---- labTroop3 ----
        labTroop3.setIcon(new ImageIcon(getClass().getResource("/troop.png")));
        contentPane.add(labTroop3);
        labTroop3.setBounds(385, 285, 37, 70);

        //---- labLeia ----
        labLeia.setIcon(new ImageIcon(getClass().getResource("/leia.png")));
        contentPane.add(labLeia);
        labLeia.setBounds(new Rectangle(new Point(380, 425), labLeia.getPreferredSize()));

        //---- labObivan ----
        labObivan.setIcon(new ImageIcon(getClass().getResource("/obivanKenobi.png")));
        contentPane.add(labObivan);
        labObivan.setBounds(new Rectangle(new Point(170, 335), labObivan.getPreferredSize()));

        //---- labZed18Cil ----
        labZed18Cil.setBackground(new Color(51, 204, 255));
        labZed18Cil.setOpaque(true);
        contentPane.add(labZed18Cil);
        labZed18Cil.setBounds(590, 420, 15, 80);

        //---- labZed19 ----
        labZed19.setOpaque(true);
        labZed19.setBackground(new Color(51, 204, 255));
        contentPane.add(labZed19);
        labZed19.setBounds(585, 420, 10, 80);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(605, 550);
        setLocationRelativeTo(null);
    }
}
