package ch.css.raumverwaltung;
import java.time.LocalDateTime;
import java.util.Scanner;

public class InteractionDemo {
    public static void main(String[] args) {
        Raumverwaltung rv = new Raumverwaltung();
        Scanner input = new Scanner(System.in);
        boolean running = true;

        rv.resRaum(
                11,
                LocalDateTime.of(2026, 6, 1, 8, 0),
                LocalDateTime.of(2026, 6, 1, 10, 0),
                new Benutzer(1, "Alice", Rolle.STUDENT)
        );

        rv.resRaum(
                11,
                LocalDateTime.of(2026, 6, 1, 10, 59),
                LocalDateTime.of(2026, 6, 1, 12, 0),
                new Benutzer(1, "Alice", Rolle.STUDENT)
        );

        while (running) {
        System.out.println("Wähle aus:\n1. Alle Räume anzeigen\n2. Alle Reservierungen anzeigen\n3. Alle Benutzer anzeigen\nKlicke F um zu schliessen\n\nDeine Auswahl: ");
        String choice = input.nextLine();

        if (choice.equals("F") || choice.equals("f")) {
            System.out.println("Programm wird geschlossen...");
            running = false;
            continue;
        }

            switch (choice) {
                case "1":
                    rv.showAllRaeume();
                    break;

                case "2":
                    rv.showAllReservationen();
                    break;

                case "3":
                    rv.showAllBenutzer();
                    break;

                default:
                    System.out.println("Ungültige Auswahl!");
                    break;
            }
            System.out.println();
        }
        input.close();
    }
}
