package ch.css.raumverwaltung;
import java.time.LocalDateTime;


public class Reservation {
    private final Raum raum;
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Reservation(Raum raum, LocalDateTime start, LocalDateTime end) {
        if (raum == null || start == null || end == null) {
            throw new InvalidDatasException("Zu wenig Informationen für die Reservierung!");
        }
        if (start.isAfter(end)) {
            throw new InvalidDatasException("Startzeitpunkt muss vor Endzeitpunkt liegen!");
        }

        this.raum = raum;
        this.start = start;
        this.end = end;
    }

    public boolean schneidetSich(LocalDateTime start, LocalDateTime end) {
        if (this.start.isBefore(end) && this.end.isAfter(start)) {
            return true;
        }
        return false;
    }

    public Raum getRaum() {
        return raum;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    // AI
    @Override
    public String toString() {
        return "Reservation:\n" +
                "Raum = " + raum.getRaumnummer() + "\n" +
                "Kapazität = " + raum.getKapazitaet() + "\n" +
                raum.getStatus() + " von " + start + " bis " + end + "\n" +
                "================================\n";
    }
    // AI

}