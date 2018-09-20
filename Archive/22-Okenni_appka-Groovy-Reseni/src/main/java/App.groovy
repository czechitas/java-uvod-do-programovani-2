import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.SwingConstants
import javax.swing.WindowConstants
import java.awt.Dimension
import java.awt.Font



JFrame okno;
JLabel napis;
Font vetsiFont;
Dimension rozmeryOkna;

okno = new JFrame("Dnesni datum");
rozmeryOkna = new Dimension(400, 300);
okno.size = rozmeryOkna;
okno.locationRelativeTo = null;
okno.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE;

vetsiFont = new Font("Tahoma", Font.PLAIN, 20);

napis = new JLabel("Dneska je 23. 6. 2018");
napis.horizontalAlignment = SwingConstants.CENTER;
napis.font = vetsiFont;
okno.add(napis);

okno.visible = true;
