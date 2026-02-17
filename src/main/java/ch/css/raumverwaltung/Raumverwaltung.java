package ch.css.raumverwaltung;
import java.util.HashMap;
import java.time.LocalDateTime;

public class Raumverwaltung {

    private final HashMap<Integer, Raum> raeume = new HashMap<>();

    public Raumverwaltung() {
        raeume.put(600, new Raum(600, 18, Status.frei));
        raeume.put(602, new Raum(602, 6, Status.frei));
        raeume.put(603, new Raum(603, 12, Status.frei));
        raeume.put(605, new Raum(605, 24, Status.frei));
        raeume.put(610, new Raum(610, 12, Status.frei));
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

    public Reservation resRaum(int anzahlPersonen, LocalDateTime start, LocalDateTime end) {
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
        Reservation reservation = new Reservation(kleinsterRaum, start, end);
        kleinsterRaum.addRes(reservation);
        kleinsterRaum.setStatus(Status.besetzt);
        return reservation;
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
}

