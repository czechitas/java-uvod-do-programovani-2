import java.awt.Color
import java.awt.Dimension
import java.time.LocalDate



String jmeno;
int vek;

jmeno = "Kamil";
vek = 36;

System.out.print("Ahoj, zdravi ");
System.out.println( jmeno );
System.out.print("Jeho stari je ");
System.out.println( vek );

Color zluta;
Dimension rozmery;
LocalDate dneska;

zluta = new Color(255, 255, 0);
System.out.println(zluta);

rozmery = new Dimension(400, 300);
System.out.println(rozmery);

dneska = LocalDate.of(2018, 6, 23);
System.out.print("Dnes je ");
System.out.println(dneska);
