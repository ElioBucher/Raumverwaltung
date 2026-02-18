package ch.css.raumverwaltung;
import java.time.LocalDateTime;

public class Demo {
    public static void main(String[] args) {
            Raumverwaltung rv = new Raumverwaltung();

            Benutzer alice = new Benutzer(1, "Alice", Rolle.STUDENT);

            Reservation r1 = rv.resRaum(
                    11,
                    LocalDateTime.of(2026, 6, 1, 8, 0),
                    LocalDateTime.of(2026, 6, 1, 10, 0),
                    alice
            );

            System.out.println(r1);
            System.out.println(r1.getBenutzer());
            System.out.println(r1.getRaum());
        }
    }
