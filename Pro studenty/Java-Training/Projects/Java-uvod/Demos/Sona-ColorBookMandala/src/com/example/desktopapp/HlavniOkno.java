package com.example.desktopapp;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.*;
import net.sevecek.util.*;
import sun.awt.image.*;

public class HlavniOkno extends JFrame {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    JMenuBar menuBar1;
    JMenu menuMenu;
    JMenuItem menuItemOpen;
    JMenuItem menuItemSave;
    JRadioButton radbtnPencil;
    JLabel labPencil;
    JRadioButton radbtnDropper;
    JLabel labDropper;
    JTextField txarWidth;
    JLabel labPx;
    JPanel panelColors;
    JLabel labBlack;
    JLabel labDarkGrey;
    JLabel labCrimson;
    JLabel labRed;
    JLabel labOrange;
    JLabel labSun;
    JLabel labDarkGreen;
    JLabel labBlue;
    JLabel labDarkBlue;
    JLabel labPurple;
    JLabel labWhite;
    JLabel labLightGrey;
    JLabel labBrown;
    JLabel labPink;
    JLabel labYellow;
    JLabel labSand;
    JLabel labLightGreen;
    JLabel labSky;
    JLabel labGreyBlue;
    JLabel labLila;
    JLabel labRandom;
    JLabel labNote;
    JRadioButton radbtnFill;
    JLabel labFill;
    JLabel labAccept;
    JLabel labR;
    JTextField txarR;
    JLabel labG;
    JTextField txarG;
    JLabel labB;
    JTextField txarB;
    JLabel labColorPreview;
    JLabel labSet;
    JLabel labMain;
    JPanel panel2;
    JLabel labMandala1;
    JLabel labMandala2;
    JLabel labMandala3;
    JLabel labMandala4;
    JLabel labMandala5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    JPanel contentPane;
    BufferedImage mandala;
    BufferedImage obrazek;
    Color actualColor;
    int getPencilWidthInt;

    File file1;
    File file2;
    File file3;
    File file4;
    File file5;

    String filepath;

    public HlavniOkno() {
        initComponents();
    }

    private void thisWindowOpened(WindowEvent e) {
        loadMandala(file1, "/com/example/desktopapp/Mandala1.png");

        labColorPreview.setBackground(Color.white);
        updateColorData();

        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(radbtnPencil);
        radioButtonGroup.add(radbtnFill);
        radioButtonGroup.add(radbtnDropper);
        radioButtonGroup.setSelected(radbtnFill.getModel(), true);

        txarWidth.setText("5");
        updatePencilWidth();
    }

    //MENU
    private void menuItemSaveAction(ActionEvent e) {
        saveAs();
    }

    private void saveAs() {
        JFileChooser dialog;
        dialog = new JFileChooser(".");

        int vysledek = dialog.showSaveDialog(this);
        if (vysledek != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = dialog.getSelectedFile();
        if (!file.getName().contains(".") && !file.exists()) {
            file = new File(file.getParentFile(), file.getName() + ".png");
        }
        if (file.exists()) {
            int potvrzeni = JOptionPane.showConfirmDialog(this, "Soubor " + file.getName() + " už existuje.\nChcete jej přepsat?", "Přepsat soubor?", JOptionPane.YES_NO_OPTION);
            if (potvrzeni == JOptionPane.NO_OPTION) {
                return;
            }
        }
        savePicture(file);
    }

    private void savePicture(File file) {
        try {
            ImageIO.write(mandala, "png", file);
        } catch (IOException ex) {
            throw new ApplicationPublicException(ex, "Nepodařilo se uložit obrázek mandaly do souboru " + file.getAbsolutePath());
        }
    }

    private void menuItemOpenAction(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                filepath = file.getAbsolutePath();
            }
            loadMandala(file, filepath);
        }
    }
    //

    //MANDALAS
    private void loadMandala (File soubor, String path) {
        if (soubor == null) {
            try {
                mandala = ImageIO.read(getClass().getResourceAsStream(path));
            } catch (IOException ex) {
                throw new ApplicationPublicException(ex, "Nepodařilo se nahrát zabudovaný obrázek mandaly");
            }
        } else {
            try {
                mandala = ImageIO.read(soubor);
            } catch (IOException ex) {
                throw new ApplicationPublicException(ex, "Nepodařilo se nahrát obrázek mandaly ze souboru " + soubor.getAbsolutePath());
            }
        }
        labMain.setIcon(new ImageIcon(mandala));
        labMain.setMinimumSize(new Dimension(mandala.getWidth(), mandala.getHeight()));
        pack();
        setMinimumSize(getSize());
    }

    private void onClickLabMandala1(MouseEvent e) {
        loadMandala(file1, "/com/example/desktopapp/Mandala1.png");
    }

    private void onClickLabMandala2(MouseEvent e) {
        loadMandala(file2, "/com/example/desktopapp/Mandala2.png");
    }

    private void onClickLabMandala3(MouseEvent e) {
        loadMandala(file3, "/com/example/desktopapp/Mandala3.png");
    }

    private void onClickLabMandala4(MouseEvent e) {
        loadMandala(file4, "/com/example/desktopapp/Mandala4.png");
    }

    private void onClickLabMandala5(MouseEvent e) {
        loadMandala(file5, "/com/example/desktopapp/Mandala5.png");
    }
    //

    //BUTTONS
    private void onClickLabAccept(MouseEvent e) {
        updatePencilWidth();
    }

    private void updatePencilWidth() {
        String txarWidthText = txarWidth.getText();
        int width;
        int widthnew;

        try {
            width = Integer.parseInt(txarWidthText);
        } catch (NumberFormatException ex) {
            txarWidth.setBackground(Color.red);
            return;
        }
        widthnew = width;
        txarWidth.setBackground(Color.white);

        if (widthnew > 500) {
            txarWidth.setText("500");
            widthnew = 500;
        } else if (widthnew <= 0) {
            txarWidth.setText("1");
            widthnew = 1;
        }

        getPencilWidthInt = widthnew;
    }

    private void onClickLabRandom(MouseEvent e) {
        Random();
    }

    private void onClickLabSet(MouseEvent e) {
        String red = txarR.getText();
        String green = txarG.getText();
        String blue = txarB.getText();
        int r;
        int g;
        int b;
        int rnew;
        int gnew;
        int bnew;

        //TRY CATCH IF FOR RED
        try {
            r = Integer.parseInt(red);
        } catch (NumberFormatException ex) {
            txarR.setBackground(Color.red);
            return;
        }
        rnew = r;
        txarR.setBackground(Color.white);

        if (rnew > 255) {
            txarR.setText("255");
            rnew = 255;
        } else if (rnew < 0) {
            txarR.setText("0");
            rnew = 0;
        }

        //TRY CATCH IF FOR GREEN
        try {
            g = Integer.parseInt(green);
        } catch (NumberFormatException ex) {
            txarG.setBackground(Color.red);
            return;
        }
        txarG.setBackground(Color.white);
        gnew = g;

        if (gnew > 255) {
            txarG.setText("255");
            gnew = 255;
        } else if (gnew < 0) {
            txarG.setText("0");
            gnew = 0;
        }

        //TRY CATCH IF FOR BLUE
        try {
            b = Integer.parseInt(blue);
        } catch (NumberFormatException ex) {
            txarB.setBackground(Color.red);
            return;
        }
        bnew = b;
        txarB.setBackground(Color.white);

        if (bnew > 255) {
            txarB.setText("255");
            bnew = 255;
        } else if (bnew < 0) {
            txarB.setText("0");
            bnew = 0;
        } 
            catchColor(rnew,gnew,bnew);
    }
    //

    //COLORS MENU
    private void onClickLabBlack(MouseEvent e) {
        determineColor(labBlack);
    }

    private void onClickLabWhite(MouseEvent e) {
        determineColor(labWhite);
    }

    private void onClickLabDarkGrey(MouseEvent e) {
        determineColor(labDarkGrey);
    }

    private void onClickLabLightGrey(MouseEvent e) {
        determineColor(labLightGrey);
    }

    private void onClickLabCrimson(MouseEvent e) {
        determineColor(labCrimson);
    }

    private void onClickLabBrown(MouseEvent e) {
        determineColor(labBrown);
    }

    private void onClickLabRed(MouseEvent e) {
        determineColor(labRed);
    }

    private void onClickLabPink(MouseEvent e) {
        determineColor(labPink);
    }

    private void onClickLabOrange(MouseEvent e) {
        determineColor(labOrange);
    }

    private void onClickLabYellow(MouseEvent e) {
        determineColor(labYellow);
    }

    private void onClickLabSun(MouseEvent e) {
        determineColor(labSun);
    }

    private void onClickLabSand(MouseEvent e) {
        determineColor(labSand);
    }

    private void onClickLabDarkGreen(MouseEvent e) {
        determineColor(labDarkGreen);
    }

    private void onClickLabLightGreen(MouseEvent e) {
        determineColor(labLightGreen);
    }

    private void onClickLabBlue(MouseEvent e) {
        determineColor(labBlue);
    }

    private void onClickLabSky(MouseEvent e) {
        determineColor(labSky);
    }

    private void onClickLabDarkBlue(MouseEvent e) {
        determineColor(labDarkBlue);
    }

    private void onClickLabGreyBlue(MouseEvent e) {
        determineColor(labGreyBlue);
    }

    private void onClickLabPurple(MouseEvent e) {
        determineColor(labPurple);
    }

    private void onClickLabLila(MouseEvent e) {
        determineColor(labLila);
    }

    private void determineColor(JLabel label) {
        actualColor = label.getBackground();
        labColorPreview.setBackground(actualColor);
        updateColorData();
    }

    private void updateColorData() {
        actualColor = labColorPreview.getBackground();
        int r = actualColor.getRed();
        int g = actualColor.getGreen();
        int b = actualColor.getBlue();
        String red = Integer.toString(r);
        String green = Integer.toString(g);
        String blue = Integer.toString(b);
        txarR.setText(red);
        txarG.setText(green);
        txarB.setText(blue);
    }

    private void catchColor(int r, int g, int b) {
        Color catchColor = new Color(r,g,b);
        labColorPreview.setBackground(catchColor);
        updateColorData();
    }

    private void Random() {
        Random randomColorGenerator;
        randomColorGenerator = new Random();

        int r = randomColorGenerator.nextInt(256);
        int g = randomColorGenerator.nextInt(256);
        int b = randomColorGenerator.nextInt(256);

        catchColor(r,g,b);
    }

    private void dropColor (int x, int y) {
        WritableRaster pixely = obrazek.getRaster();
        int[] pixelColorArray = new int[] {255, 255, 255, 255};
        pixelColorArray = pixely.getPixel(x, y, pixelColorArray);
        int r = pixelColorArray[0];
        int g = pixelColorArray[1];
        int b = pixelColorArray[2];
        catchColor(r,g,b);
    }
    //

    //FILL IN BLANK SPACES
    private void onDragLabMain(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (radbtnPencil.isSelected()) {
            PencilDrawning(x, y, getPencilWidthInt);
        } if (radbtnFill.isSelected()) {
            FillShape(x, y, actualColor, labMain);
        } if (radbtnDropper.isSelected()) {
            dropColor(x, y);
        }
    }

    private void PencilDrawning(int x, int y, int sirkapera) {
        Graphics2D stetec = (Graphics2D) mandala.getGraphics();
        stetec.setColor(actualColor);
        stetec.setStroke(new BasicStroke(sirkapera, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        stetec.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        stetec.drawLine(x, y, x, y);
        labMain.repaint(x-sirkapera/2, y-sirkapera/2, x+sirkapera/2, y+sirkapera/2);
    }

    public void FillShape(int x, int y, Color barvaVyplne, JLabel label) {
        if (barvaVyplne == null) {
            barvaVyplne = new Color(255, 255, 0);
        }


        ImageIcon icon = (ImageIcon) label.getIcon();
        Image puvodniImage = icon.getImage();
        if (puvodniImage instanceof BufferedImage) {
            obrazek = (BufferedImage) puvodniImage;
        } else if (puvodniImage instanceof ToolkitImage) {
            obrazek = ((ToolkitImage) puvodniImage).getBufferedImage();
        } else {
            obrazek = new BufferedImage(puvodniImage.getWidth(null), puvodniImage.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);
            obrazek.getGraphics().drawImage(puvodniImage, 0, 0, null);
        }


        if (x < 0 || x >= obrazek.getWidth() || y < 0 || y >= obrazek.getHeight()) {
            return;
        }

        WritableRaster pixely = obrazek.getRaster();
        int[] novyPixel = new int[] {barvaVyplne.getRed(), barvaVyplne.getGreen(), barvaVyplne.getBlue(), barvaVyplne.getAlpha()};
        int[] staryPixel = new int[] {255, 255, 255, 255};
        staryPixel = pixely.getPixel(x, y, staryPixel);

        // Pokud uz je pocatecni pixel obarven na cilovou barvu, nic nemen
        if (isEqualRgb(novyPixel, staryPixel)) {
            return;
        }

        // Zamez prebarveni cerne cary
        int[] cernyPixel = new int[] {0, 0, 0, staryPixel[3]};
        if (isEqualRgb(cernyPixel, staryPixel)) {
            return;
        }

        floodLoop(pixely, x, y, novyPixel, staryPixel);

        ImageIcon obrazekJakoIkonka = new ImageIcon(obrazek);
        label.setIcon(obrazekJakoIkonka);
    }
    // Recursively fills surrounding pixels of the old color

    private void floodLoop(WritableRaster raster, int x, int y, int[] newColor, int[] originalColor) {
        Rectangle bounds = raster.getBounds();
        int[] currentColor = new int[] {255, 255, 255, 255};

        Deque<Point> stack = new ArrayDeque<>();
        stack.push(new Point(x, y));
        while (stack.size() > 0) {
            Point point = stack.pop();
            x = point.x;
            y = point.y;

            // finds the left side, filling along the way
            int fillL = x;
            do {
                raster.setPixel(fillL, y, newColor);
                fillL--;
            } while (fillL >= 0 && isEqualRgb(raster.getPixel(fillL, y, currentColor), originalColor));
            fillL++;

            // find the right right side, filling along the way
            int fillR = x;
            do {
                raster.setPixel(fillR, y, newColor);
                fillR++;
            } while (fillR < bounds.width - 1 && isEqualRgb(raster.getPixel(fillR, y, currentColor), originalColor));
            fillR--;

            // checks if applicable up or down
            for (int i = fillL; i <= fillR; i++) {
                if (y > 0 && isEqualRgb(raster.getPixel(i, y - 1, currentColor), originalColor)) {
                    stack.add(new Point(i, y - 1));
                }
                if (y < bounds.height - 1 && isEqualRgb(raster.getPixel(i, y + 1, currentColor), originalColor)) {
                    stack.add(new Point(i, y + 1));
                }
            }
        }
    }
    // Returns true if RGB arrays are equivalent, false otherwise

    private boolean isEqualRgb(int[] color1, int[] color2) {
        // Could use Arrays.equals(int[], int[]), but this is probably a little faster...
        return color1[0] == color2[0] && color1[1] == color2[1] && color1[2] == color2[2] && color1[3] == color2[3];
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        menuBar1 = new JMenuBar();
        menuMenu = new JMenu();
        menuItemOpen = new JMenuItem();
        menuItemSave = new JMenuItem();
        radbtnPencil = new JRadioButton();
        labPencil = new JLabel();
        radbtnDropper = new JRadioButton();
        labDropper = new JLabel();
        txarWidth = new JTextField();
        labPx = new JLabel();
        panelColors = new JPanel();
        labBlack = new JLabel();
        labDarkGrey = new JLabel();
        labCrimson = new JLabel();
        labRed = new JLabel();
        labOrange = new JLabel();
        labSun = new JLabel();
        labDarkGreen = new JLabel();
        labBlue = new JLabel();
        labDarkBlue = new JLabel();
        labPurple = new JLabel();
        labWhite = new JLabel();
        labLightGrey = new JLabel();
        labBrown = new JLabel();
        labPink = new JLabel();
        labYellow = new JLabel();
        labSand = new JLabel();
        labLightGreen = new JLabel();
        labSky = new JLabel();
        labGreyBlue = new JLabel();
        labLila = new JLabel();
        labRandom = new JLabel();
        labNote = new JLabel();
        radbtnFill = new JRadioButton();
        labFill = new JLabel();
        labAccept = new JLabel();
        labR = new JLabel();
        txarR = new JTextField();
        labG = new JLabel();
        txarG = new JTextField();
        labB = new JLabel();
        txarB = new JTextField();
        labColorPreview = new JLabel();
        labSet = new JLabel();
        labMain = new JLabel();
        panel2 = new JPanel();
        labMandala1 = new JLabel();
        labMandala2 = new JLabel();
        labMandala3 = new JLabel();
        labMandala4 = new JLabel();
        labMandala5 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Color Book - Mandala Edition");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 0,hidemode 3",
            // columns
            "[sizegroup 1,fill]unrel" +
            "[sizegroup 1,fill]para" +
            "[sizegroup 1,fill]0" +
            "[fill]para" +
            "[fill]para" +
            "[fill]para" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[grow,fill]0" +
            "[fill]",
            // rows
            "[]rel" +
            "[fill]" +
            "[]" +
            "[grow]" +
            "[grow]"));
        this.contentPane = (JPanel) this.getContentPane();
        this.contentPane.setBackground(this.getBackground());

        //======== menuBar1 ========
        {

            //======== menuMenu ========
            {
                menuMenu.setText("Menu");

                //---- menuItemOpen ----
                menuItemOpen.setText("Open");
                menuItemOpen.addActionListener(e -> menuItemOpenAction(e));
                menuMenu.add(menuItemOpen);

                //---- menuItemSave ----
                menuItemSave.setText("Save");
                menuItemSave.addActionListener(e -> menuItemSaveAction(e));
                menuMenu.add(menuItemSave);
            }
            menuBar1.add(menuMenu);
        }
        setJMenuBar(menuBar1);
        contentPane.add(radbtnPencil, "cell 0 1");

        //---- labPencil ----
        labPencil.setIcon(new ImageIcon(getClass().getResource("/com/example/desktopapp/pencil.png")));
        contentPane.add(labPencil, "cell 0 1");
        contentPane.add(radbtnDropper, "cell 1 1");

        //---- labDropper ----
        labDropper.setIcon(new ImageIcon(getClass().getResource("/com/example/desktopapp/dropper.png")));
        contentPane.add(labDropper, "cell 1 1");

        //---- txarWidth ----
        txarWidth.setColumns(3);
        txarWidth.setText("500");
        txarWidth.setHorizontalAlignment(SwingConstants.TRAILING);
        txarWidth.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        contentPane.add(txarWidth, "cell 2 1");

        //---- labPx ----
        labPx.setText("px");
        labPx.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
        contentPane.add(labPx, "cell 2 1");

        //======== panelColors ========
        {
            panelColors.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "0[sizegroup 1,fill]0" +
                "[sizegroup 1,fill]0" +
                "[sizegroup 1,fill]0" +
                "[sizegroup 1,fill]0" +
                "[sizegroup 1,fill]0" +
                "[sizegroup 1,fill]0" +
                "[sizegroup 1,fill]0" +
                "[sizegroup 1,fill]0" +
                "[sizegroup 1,fill]0" +
                "[sizegroup 1,fill]0",
                // rows
                "0[sizegroup 1]0" +
                "[sizegroup 1]0"));

            //---- labBlack ----
            labBlack.setBorder(new LineBorder(Color.white));
            labBlack.setOpaque(true);
            labBlack.setBackground(new Color(1, 1, 1));
            labBlack.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabBlack(e);
                }
            });
            panelColors.add(labBlack, "cell 0 0,grow,wmin 30,hmin 30");

            //---- labDarkGrey ----
            labDarkGrey.setBorder(new LineBorder(Color.white));
            labDarkGrey.setOpaque(true);
            labDarkGrey.setBackground(new Color(127, 127, 127));
            labDarkGrey.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabDarkGrey(e);
                }
            });
            panelColors.add(labDarkGrey, "cell 1 0,grow");

            //---- labCrimson ----
            labCrimson.setBorder(new LineBorder(Color.white));
            labCrimson.setOpaque(true);
            labCrimson.setBackground(new Color(136, 0, 21));
            labCrimson.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabCrimson(e);
                }
            });
            panelColors.add(labCrimson, "cell 2 0,grow");

            //---- labRed ----
            labRed.setBorder(new LineBorder(Color.white));
            labRed.setOpaque(true);
            labRed.setBackground(Color.red);
            labRed.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabRed(e);
                }
            });
            panelColors.add(labRed, "cell 3 0,grow");

            //---- labOrange ----
            labOrange.setBorder(new LineBorder(Color.white));
            labOrange.setOpaque(true);
            labOrange.setBackground(new Color(255, 153, 0));
            labOrange.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabOrange(e);
                }
            });
            panelColors.add(labOrange, "cell 4 0,grow");

            //---- labSun ----
            labSun.setBorder(new LineBorder(Color.white));
            labSun.setOpaque(true);
            labSun.setBackground(Color.yellow);
            labSun.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabSun(e);
                }
            });
            panelColors.add(labSun, "cell 5 0,grow");

            //---- labDarkGreen ----
            labDarkGreen.setBorder(new LineBorder(Color.white));
            labDarkGreen.setOpaque(true);
            labDarkGreen.setBackground(new Color(0, 153, 51));
            labDarkGreen.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabDarkGreen(e);
                }
            });
            panelColors.add(labDarkGreen, "cell 6 0,grow");

            //---- labBlue ----
            labBlue.setBorder(new LineBorder(Color.white));
            labBlue.setOpaque(true);
            labBlue.setBackground(new Color(51, 153, 255));
            labBlue.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabBlue(e);
                }
            });
            panelColors.add(labBlue, "cell 7 0,grow");

            //---- labDarkBlue ----
            labDarkBlue.setBorder(new LineBorder(Color.white));
            labDarkBlue.setOpaque(true);
            labDarkBlue.setBackground(new Color(0, 51, 204));
            labDarkBlue.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabDarkBlue(e);
                }
            });
            panelColors.add(labDarkBlue, "cell 8 0,grow");

            //---- labPurple ----
            labPurple.setBorder(new LineBorder(Color.white));
            labPurple.setOpaque(true);
            labPurple.setBackground(new Color(153, 0, 204));
            labPurple.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabPurple(e);
                }
            });
            panelColors.add(labPurple, "cell 9 0,grow");

            //---- labWhite ----
            labWhite.setBorder(new LineBorder(Color.white));
            labWhite.setOpaque(true);
            labWhite.setBackground(Color.white);
            labWhite.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabWhite(e);
                }
            });
            panelColors.add(labWhite, "cell 0 1,grow");

            //---- labLightGrey ----
            labLightGrey.setBorder(new LineBorder(Color.white));
            labLightGrey.setOpaque(true);
            labLightGrey.setBackground(new Color(195, 195, 195));
            labLightGrey.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabLightGrey(e);
                }
            });
            panelColors.add(labLightGrey, "cell 1 1,grow");

            //---- labBrown ----
            labBrown.setBorder(new LineBorder(Color.white));
            labBrown.setOpaque(true);
            labBrown.setBackground(new Color(185, 122, 87));
            labBrown.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabBrown(e);
                }
            });
            panelColors.add(labBrown, "cell 2 1,grow");

            //---- labPink ----
            labPink.setBorder(new LineBorder(Color.white));
            labPink.setOpaque(true);
            labPink.setBackground(new Color(255, 153, 204));
            labPink.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabPink(e);
                }
            });
            panelColors.add(labPink, "cell 3 1,grow");

            //---- labYellow ----
            labYellow.setBorder(new LineBorder(Color.white));
            labYellow.setOpaque(true);
            labYellow.setBackground(new Color(255, 204, 0));
            labYellow.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabYellow(e);
                }
            });
            panelColors.add(labYellow, "cell 4 1,grow");

            //---- labSand ----
            labSand.setBorder(new LineBorder(Color.white));
            labSand.setOpaque(true);
            labSand.setBackground(new Color(239, 228, 176));
            labSand.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabSand(e);
                }
            });
            panelColors.add(labSand, "cell 5 1,grow");

            //---- labLightGreen ----
            labLightGreen.setBorder(new LineBorder(Color.white));
            labLightGreen.setOpaque(true);
            labLightGreen.setBackground(new Color(153, 255, 0));
            labLightGreen.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabLightGreen(e);
                }
            });
            panelColors.add(labLightGreen, "cell 6 1,grow");

            //---- labSky ----
            labSky.setBorder(new LineBorder(Color.white));
            labSky.setOpaque(true);
            labSky.setBackground(new Color(153, 204, 255));
            labSky.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabSky(e);
                }
            });
            panelColors.add(labSky, "cell 7 1,grow");

            //---- labGreyBlue ----
            labGreyBlue.setBorder(new LineBorder(Color.white));
            labGreyBlue.setOpaque(true);
            labGreyBlue.setBackground(new Color(0, 102, 153));
            labGreyBlue.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabGreyBlue(e);
                }
            });
            panelColors.add(labGreyBlue, "cell 8 1,grow");

            //---- labLila ----
            labLila.setBorder(new LineBorder(Color.white));
            labLila.setOpaque(true);
            labLila.setBackground(new Color(204, 153, 255));
            labLila.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabLila(e);
                }
            });
            panelColors.add(labLila, "cell 9 1,grow");
        }
        contentPane.add(panelColors, "cell 4 1 1 2,aligny center,growy 0");

        //---- labRandom ----
        labRandom.setText("Random");
        labRandom.setHorizontalAlignment(SwingConstants.CENTER);
        labRandom.setFont(new Font("Segoe UI Light", labRandom.getFont().getStyle(), labRandom.getFont().getSize() + 2));
        labRandom.setBackground(new Color(204, 204, 204));
        labRandom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClickLabRandom(e);
            }
        });
        contentPane.add(labRandom, "cell 5 1 1 2,aligny center,grow 100 0");

        //---- labNote ----
        labNote.setText("Choose your own color (accepts only numbers 0-255)");
        labNote.setFont(labNote.getFont().deriveFont(labNote.getFont().getSize() - 2f));
        labNote.setVerticalAlignment(SwingConstants.BOTTOM);
        contentPane.add(labNote, "cell 6 1 4 1");
        contentPane.add(radbtnFill, "cell 0 2");

        //---- labFill ----
        labFill.setIcon(new ImageIcon(getClass().getResource("/com/example/desktopapp/fill.png")));
        contentPane.add(labFill, "cell 0 2");

        //---- labAccept ----
        labAccept.setText("Set ");
        labAccept.setHorizontalAlignment(SwingConstants.CENTER);
        labAccept.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        labAccept.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClickLabAccept(e);
            }
        });
        contentPane.add(labAccept, "cell 2 2");

        //---- labR ----
        labR.setText("R");
        labR.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        contentPane.add(labR, "cell 6 2");

        //---- txarR ----
        txarR.setText("0");
        txarR.setColumns(3);
        txarR.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contentPane.add(txarR, "cell 6 2");

        //---- labG ----
        labG.setText("G");
        labG.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        contentPane.add(labG, "cell 7 2");

        //---- txarG ----
        txarG.setColumns(3);
        txarG.setText("0");
        txarG.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contentPane.add(txarG, "cell 7 2");

        //---- labB ----
        labB.setText("B");
        labB.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        contentPane.add(labB, "cell 8 2");

        //---- txarB ----
        txarB.setColumns(3);
        txarB.setText("0");
        txarB.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contentPane.add(txarB, "cell 8 2");

        //---- labColorPreview ----
        labColorPreview.setBackground(new Color(1, 1, 1));
        labColorPreview.setOpaque(true);
        labColorPreview.setBorder(new LineBorder(Color.white));
        contentPane.add(labColorPreview, "cell 9 2,wmin 30,hmin 30");

        //---- labSet ----
        labSet.setText("Set");
        labSet.setHorizontalAlignment(SwingConstants.CENTER);
        labSet.setFont(new Font("Segoe UI Light", labSet.getFont().getStyle(), labSet.getFont().getSize() + 2));
        labSet.setBackground(new Color(204, 204, 204));
        labSet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClickLabSet(e);
            }
        });
        contentPane.add(labSet, "cell 10 2,growx");

        //---- labMain ----
        labMain.setHorizontalAlignment(SwingConstants.LEFT);
        labMain.setVerticalAlignment(SwingConstants.TOP);
        labMain.setBackground(Color.white);
        labMain.setOpaque(true);
        labMain.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                onDragLabMain(e);
            }
        });
        labMain.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                onDragLabMain(e);
            }
        });
        contentPane.add(labMain, "cell 0 3 12 2,grow,width 850,height 850");

        //======== panel2 ========
        {
            panel2.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[fill]",
                // rows
                "0[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]"));

            //---- labMandala1 ----
            labMandala1.setIcon(new ImageIcon(getClass().getResource("/com/example/desktopapp/Mandala1Small.png")));
            labMandala1.setBorder(LineBorder.createBlackLineBorder());
            labMandala1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabMandala1(e);
                }
            });
            panel2.add(labMandala1, "cell 0 0");

            //---- labMandala2 ----
            labMandala2.setIcon(new ImageIcon(getClass().getResource("/com/example/desktopapp/Mandala2Small.png")));
            labMandala2.setBorder(LineBorder.createBlackLineBorder());
            labMandala2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabMandala2(e);
                }
            });
            panel2.add(labMandala2, "cell 0 1");

            //---- labMandala3 ----
            labMandala3.setIcon(new ImageIcon(getClass().getResource("/com/example/desktopapp/Mandala3Small.png")));
            labMandala3.setBorder(LineBorder.createBlackLineBorder());
            labMandala3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabMandala3(e);
                }
            });
            panel2.add(labMandala3, "cell 0 2");

            //---- labMandala4 ----
            labMandala4.setIcon(new ImageIcon(getClass().getResource("/com/example/desktopapp/Mandala4Small.png")));
            labMandala4.setBorder(LineBorder.createBlackLineBorder());
            labMandala4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabMandala4(e);
                }
            });
            panel2.add(labMandala4, "cell 0 3");

            //---- labMandala5 ----
            labMandala5.setIcon(new ImageIcon(getClass().getResource("/com/example/desktopapp/Mandala5Small.png")));
            labMandala5.setBorder(LineBorder.createBlackLineBorder());
            labMandala5.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onClickLabMandala5(e);
                }
            });
            panel2.add(labMandala5, "cell 0 4");
        }
        contentPane.add(panel2, "cell 12 3 1 2,aligny top,growy 0");
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}
