Lektorské zápisky a doporučení
==============================

Instalace
---------

Studentky si musejí udělat instalaci na kurz v klidu doma. Instalace je ve formě přednastaveného .zip archívu.
Tedy: *Není to jen popis*, jaké produkty si mají stáhnout a nainstalovat, ale naopak je to celé už nainstalované a nastavené.
Jeden zip, který si jen rozbalí a jede to. Tohle je hrozně důležité, protože toho nastavování je v javovém světě hodně
(IntelliJ na JDK, Maven na JDK, IntelliJ na Maven, IntelliJ na Tomcat, Maven na Tomcat, Maven na repository, a tuny dalšího).
Myšlenka je podobná třeba XAMPPu, u kterého taky stačí jen stáhnout, rozbalit a jet.

Detailní pokyny k instalaci (webové stránky, které se zasílají studentkám): <br/>
https://javainstall.czechitas.cz/community/



Příklady
--------

Studentky si stáhly instalacku, ale ta je univerzální pro všechny moje javové kurzy.
Na workshopu si ještě budou muset stáhnout příklady, které si vloží nejlépe do
`C:\Java-Training\Projects\Java-uvod-2` na Windows nebo `$HOME/Java-Training/Projects/Java-uvod-2` na Macu.
Doporučuji připravit si soubor `.zip` se všemy příklady, nahrát ho na GDrive a nechat sdílet s kýmkoliv, kdo dostane odkaz.
Do prezentace pak dát bit.ly na onen odkaz na GDrive.


### Maven repository

Pozor, příklady používají Maven. Prvně spuštěný Maven má tendenci stáhnout půlku internetu.
Zkontrolujte si proto, jestli v dané učebně je dost silný internet.


### Správné otevírání projektu v IntelliJ IDEA

*POZOR* při otevírání projektu v IntelliJ IDEA! V otevíracím dialogu IntelliJ IDEA se vybírá celá složka s projektem.
Složka s projektem je taková složka, která obsahuje podsložku `.idea` nebo importovatelnou konfiguraci (`pom.xml`, `build.gradle`).
Když člověk otevře správnou projektovou složku, má nahoře běhovou konfiguraci a zelenou šipku Run.
Pokud ale člověk vybere omylem nadřazenou složku, která neobsahuje projekt (tzn. neobsahuje `.idea`),
IDEA se úplně stupidně přepne do režimu importu a naimportuje existující složku jako nový projekt
(a vytvoří si novou podsložku `.idea`). Dopadne to tak, že všechny třídy jsou červené,
nefunguje code completion, nejsou nastavené žádné knihovny a appka nejde spustit.
Nejlepší řešení je takový paskvil projektu prostě zavřít a otevřít projekt ještě jednou ve správné složce.
Studentkám je dobré několikrát zopakovat a ukázat, že se projektová složka pozná podle přítomnosti `src`
(na Linuxu a Macu totiž `.idea` není vidět, protože soubory začínající tečkou jsou skryté)
a dát pozor, aby si to vždycky otevřely správně.



Scénář
------

1. Společná práce - instanciování některých tříd v Java SE
* String
* Color
* LocalDate
* LocalTime
* LocalDateTime

Nepovinně:
* Dimension
* Random
* Scanner

2. Samostatná práce

3. (Oběd)

4. Diskuse
* O tom, co se studentky naučily
* Třída
* Objekt
* Vlastnost
* Metoda
* Proměnná
* Druhy programovacích jazyků
* Strojový kód
* Systémové (C, C++, Go, Rust)
* Aplikační jazyky (Java, C#, Swift, Kotlin)
* Skriptovací jazyky (JavaScript, Python, PHP)
* Co je to webová aplikace, androidová aplikace, desktopová aplikace
* Co je to cloud
    * Cloudový file hosting
    * Cloudové dokumenty (Google Apps)
    * Cloudový web app hosting
* Cokoliv, co bude studentky zajímat
* Pozvání na dlouhodobý kurz Java 1

    Tip pro studentky k přečtení: <br/>
    https://www.itnetwork.cz/java/oop/java-tutorial-uvod-do-objektove-orientovaneho-programovani

    Gary Explains: <br/>
    https://www.youtube.com/watch?v=GI_V3yzVDtA


5. Společné seznámení s herním enginem **Kočka, myš, sýr**
    * Vypracovat dohromady pár jednodušších úkolů. Inspirace dle `Zadani.docx`.

6. Samostatná práce s herním enginem **Kočka, myš, sýr**
    * Samostatně si studentky už dál mohou zpracovávat další úkoly ze `Zadani.docx`
      nebo vylepšovat dle své libovůle.



Video z minulých běhů
---------------------

Pro inspiraci, odkazy na videa z minula:

Úvod do programování 2 - Java - 1. 12. 2019 <br/>
https://www.youtube.com/watch?v=zq98nLJdsiQ

Úvod do programování 2 - Java - 14. 9. 2019 <br/>
https://www.youtube.com/watch?v=1A0v5PuvwHo

Úvod do OOP v Javě - 28. 7. 2018 <br/>
https://www.youtube.com/watch?v=BWYp0kePluw

Úvod do Javy - 23. 6. 2018 <br/>
https://www.youtube.com/watch?v=_4wxGOGb7Hg

Úvod do Javy - 27. 1. 2018 <br/>
https://www.youtube.com/watch?v=TQov3-c5BZs
