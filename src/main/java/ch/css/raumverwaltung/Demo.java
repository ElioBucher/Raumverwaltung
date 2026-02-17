package ch.css.raumverwaltung;
import java.time.LocalDateTime;

public class Demo {
    public static void main(String[] args) {
        Raumverwaltung raum = new Raumverwaltung();

        Reservation res1 = raum.resRaum(11, LocalDateTime.of(2026, 6, 1, 8, 0), LocalDateTime.of(2026, 6, 1, 10, 0));
        Reservation res2 = raum.resRaum(11, LocalDateTime.of(2026, 9, 1, 8, 0), LocalDateTime.of(2026, 9, 1, 10, 0));
        Reservation res3 = raum.resRaum(11, LocalDateTime.of(2026, 6, 1, 8, 0), LocalDateTime.of(2026, 6, 1, 10, 0));
        Reservation res4 = raum.resRaum(11, LocalDateTime.of(2026, 2, 1, 8, 0), LocalDateTime.of(2026, 2, 1, 10, 0));
        Reservation res5= raum.resRaum(11, LocalDateTime.of(2026, 4, 1, 8, 0), LocalDateTime.of(2026, 4, 1, 10, 0));



        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);
        System.out.println(res5);

    }
}
