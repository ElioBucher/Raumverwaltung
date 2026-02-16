package ch.css.raumverwaltung;

public class Demo {
    public static void main(String[] args) {
        Raumverwaltung raum = new Raumverwaltung();

        Raum res1 = raum.resRaum(12);
        Raum res2 = raum.resRaum(6);
        Raum res3 = raum.resRaum(17);

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}
