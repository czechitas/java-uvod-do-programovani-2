Lektorske zapisky a doporuceni
==============================

Instalace
---------

Studentky si museji udelat instalaci na kurz v klidu doma. Instalace je ve forme prednastaveneho .zip archivu.
Tedy, *neni to jen popis*, jake produkty si maji stahnout a nainstalovat, ale naopak je to cele uz nainstalovane a nastavene.
Jeden zip, ktery si jen rozbali a jede to. Tohle je hrozne dulezite, protoze toho nastavovani je javovem svete hodne
(IntelliJ na JDK, Maven na JDK, IntelliJ na Maven, IntelliJ na Tomcat, Maven na Tomcat, Maven na repository, a tuny dalsiho).
Myslenka je podobna treba XAMPPu, ktery se taky da jen stahnout, rozbalit a jet.

Detailni pokyny k instalaci (webove stranky, ktere se zasilaji studentkam): <br/>
https://javainstall.czechitas.cz/community/



Priklady
--------

Studentky si stahly instalacku, ale ta je univerzalni pro vsechny moje javove kurzy.
Na workshopu si jeste budou potrebovat stahnout priklady, ktere si vlozi nejlepe do
`C:\Java-Training\Projects\Java-uvod` na Windows (nebo `~/Java-Training/Projects/Java-uvod` na Macu).
Doporucuji pripravit si soubor `.zip` se vsemy priklady, nahrat ho na GDrive a nechat sdilet s kymkoliv, kdo dostane odkaz.
Do prezentace pak dat bit.ly na onen odkaz na GDrive.


### Maven repository

Pozor, priklady pouzivaji Maven. Prvne spusteny Maven ma tendenci stahnout pulku internetu.
Zkontrolujte si proto, jestli v dane ucebne je dost silny internet.


### Spravne otevirani projektu v IntelliJ IDEA

*POZOR* pri otevirani projektu v IntelliJ IDEA! V oteviracim dialogu IntelliJ IDEA se vybira cela slozka s projektem.
Slozka s projektem je takova slozka, ktera obsahuje podslozky ".idea" a "src".
Kdyz clovek otevre spravnou projektovou slozku, ma nahore behovou konfiguraci a zelenou sipku Run.
Pokud ale clovek vybere omylem nadrazenou slozku, ktera neobsahuje projekt (tzn. neobsahuje ".idea"),
IDEA se uplne stupidne prepne do rezimu importu a naimportuje existujici slozku jako novy projekt
(a vytvori si novou podslozku ".idea"). Dopadne to tak, ze vsechny tridy jsou cervene,
nefunguje code completion, nejsou nastavene zadne knihovny a appka nejde spustit.
Nejlepsi reseni je takovy paskvil projektu proste zavrit a otevrit projekt jeste jednou ve spravne slozce.
Studentkam je dobre nekolikrat zopakovat a ukazat, ze se projektova slozka pozna podle pritomnosti "src"
(na Linuxu a Macu totiz ".idea" neni videt, protoze soubory zacinajici teckou jsou skryte)
a dat pozor, aby si to vzdycky otevrely spravne.



Scenar
------

1. Spolecna prace - instanciovani nekterych trid v Java SE
    * String
    * Color
    * LocalDate
    * LocalTime
    * LocalDateTime
    
    Nepovinne:
    * Dimension
    * Random
    * Scanner
    * DoubleFormatter

2. Spolecna prace - instanciovani vizualnich trid z JFC Swing
    * JFrame
    * JLabel
    * Font

3. (Obed)

4. Spolecne seznameni s hernim enginem Kocka, mys, syr
    * Vypracovat dohromady par jednodussich ukolu. Inspirace dle Zadani.docx.

5. Samostatna prace s hernim enginem Kocka, mys, syr
    * Samostatne si studentky uz dal mohou zpracovavat dalsi ukoly ze Zadani.docx
      nebo vylepsovat dle sve libovule.

6. Diskuse
    * O tom, co se studentky naucily
        * Trida
        * Objekt
            * Vlastnost
            * Metoda
        * Promenna
    * Druhy programovacich jazyku
        * Strojovy kod
        * Strukturovane jazyky (C, Pascal)
        * Objektove jazyky (Java, C#)
    * Druhy behu
        * Kompilovane jazyky
        * Skriptovaci jazyky
    * Co je to webova aplikace, androidova aplikace, desktopova aplikace
    * Co je to cloud
        * Cloudovy file hosting
        * Cloudove dokumenty (Google Apps)
        * Cloudovy web app hosting
    * Cokoliv, co bude studentky zajimat
    * Pozvani na dlouhodoby kurz Java 1

    Tip pro studentky na zdroj k precteni: <br/>
    https://www.itnetwork.cz/java/oop/java-tutorial-uvod-do-objektove-orientovaneho-programovani

    Gary Explains: <br/>
    https://www.youtube.com/watch?v=GI_V3yzVDtA


Video z minulych behu
---------------------

Pro inspiraci, odkazy na videa z minula:

Uvod do Javy 27. 1. 2018 <br/>
https://www.youtube.com/watch?v=TQov3-c5BZs

Uvod do Javy 23. 6. 2018 <br/>
https://www.youtube.com/watch?v=_4wxGOGb7Hg

Uvod do OOP v Jave 28. 7. 2018 <br/>
https://www.youtube.com/watch?v=BWYp0kePluw
