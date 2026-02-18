package ch.css.raumverwaltung;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.Map;

public class Raumverwaltung {

    private final HashMap<Integer, Raum> raeume = new HashMap<>();
    private final Map<Integer, Benutzer> benutzerMap = new HashMap<>();

    public Raumverwaltung() {
        raeume.put(600, new Raum(600, 18, Status.frei));

        benutzerMap.put(1, new Benutzer(1, "Alice", Rolle.STUDENT));
        benutzerMap.put(2, new Benutzer(2, "Bob", Rolle.ADMIN));
    }

    public Raum getRaum(int raumnummer) {
        if (raumnummer < 100 || raumnummer > 999) {
            throw new InvalidDatasException("Ungültige Raumnummer!");
        }
        Raum aktRaum = raeume.get(raumnummer);
        if (aktRaum == null) {
            throw new InvalidDatasException("Raum existiert nicht!");
        }
        return aktRaum;

    }

    public Reservation resRaum(int anzahlPersonen, LocalDateTime start, LocalDateTime end, Benutzer benutzer) {
        Raum kleinsterRaum = null;

        for (Raum aktRaum : raeume.values()) {
            if (aktRaum.getKapazitaet() >= anzahlPersonen && aktRaum.istFrei(start, end)) {
                if (kleinsterRaum == null || aktRaum.getKapazitaet() < kleinsterRaum.getKapazitaet()) {
                    kleinsterRaum = aktRaum;
                }
            }
        }
        if (kleinsterRaum == null) {
            throw new InvalidDatasException("Keinen passenden Raum gefunden!");
        }
        Reservation reservation = new Reservation(kleinsterRaum, start, end, benutzer);
        kleinsterRaum.addRes(reservation);
        kleinsterRaum.setStatus(Status.besetzt);
        return reservation;
    }

    public void sperrRaum(int raumnummer, Benutzer benutzer) {
        if (benutzer == null) {
            throw new InvalidDatasException("Benutzer darf nicht null sein!");
        }

        Raum aktRaum = getRaum(raumnummer);

        if (benutzer.getRolle() != Rolle.ADMIN) {
            throw new InvalidDatasException("Nur Admins können Räume sperren!");
        }

        if (aktRaum.getStatus() == Status.besetzt) {
            throw new InvalidDatasException("Der Raum ist besetzt und kann nicht gesperrt werden!");
        }

        if (aktRaum.getStatus() == Status.gesperrt) {
            throw new InvalidDatasException("Der Raum ist bereits gesperrt!");
        }

        aktRaum.setStatus(Status.gesperrt);
    }

    public void resetRaum(int raumnummer) {
        Raum aktRaum = getRaum(raumnummer);
        if (aktRaum.getStatus() == Status.frei) {
            throw new InvalidDatasException("Der Raum ist bereits frei!");
        }
        if (aktRaum.getStatus() == Status.gesperrt) {
            throw new InvalidDatasException("Der Raum ist gesperrt und kann nicht zurückgesetzt werden!");
        }
        aktRaum.setStatus(Status.frei);
    }

    public void resetRaum(Raum raum) {
        if (raum == null) {
            throw new InvalidDatasException("Raum darf nicht null sein!");
        }
        resetRaum(raum.getRaumnummer());
    }

    public void showAllRaeume() {
        for (Raum raum : raeume.values()) {
            System.out.println(
                    "Raum: " + raum.getRaumnummer() +
                            ", Kapazität: " + raum.getKapazitaet() +
                            ", Status: " + raum.getStatus() + "\n" +
                            "================================\n"
            );
        }
    }

    public void showAllReservationen() {

        for (Raum raum : raeume.values()) {
            for (Reservation reservation : raum.getReservationen()) {
                System.out.println(
                        "Raum: " + reservation.getRaum().getRaumnummer() + "\n" +
                                "Kapazität: " + reservation.getRaum().getKapazitaet() + "\n" +
                                "Status: " + reservation.getRaum().getStatus() +
                                " von " + reservation.getBenutzer().getName() + "\n" +
                                "Start: " + reservation.getStart() + "\n" +
                                "Ende: " + reservation.getEnd() + "\n" +
                                "================================\n"
                );
            }
        }
    }

    public void showAllBenutzer() {

        if (benutzerMap.isEmpty()) {
            System.out.println("Keine Benutzer vorhanden.");
        } else {
            for (Benutzer benutzer : benutzerMap.values()) {
                System.out.println(
                        "ID: " + benutzer.getId() +
                                ", Name: " + benutzer.getName() +
                                ", Rolle: " + benutzer.getRolle() + "\n" +
                                "================================\n"
                );
            }
        }
    }
}