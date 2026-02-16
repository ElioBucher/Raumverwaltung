package ch.css.raumverwaltung;
import java.util.HashMap;

public class Raumverwaltung {

    private final HashMap<Integer, Raum> raeume = new HashMap<>();

    public Raum getRaum (int raumnummer) {
        if (raumnummer < 100 || raumnummer > 999) {
            throw new InvalidDatasException ("Ungültige Raumnummer!");
        }
            Raum aktRaum = raeume.get(raumnummer);
            if (aktRaum == null) {
                throw new InvalidDatasException ("Raum existiert nicht!");
            } else {
                return aktRaum;
            }
        }

    public Raum passenderRaum (int anzahlPersonen) {
        for (Raum aktRaum : raeume.values()) {
            if (aktRaum.getKapazitaet() >= anzahlPersonen && aktRaum.istFrei()){
                aktRaum.setStatus(Status.besetzt);
                return aktRaum;
            }
        }
        throw new InvalidDatasException("Kein passender Raum gefunden");
    }

    public void resetRaum (int raumnummer) {
        Raum aktRaum = raeume.get(raumnummer);
        if (aktRaum.getStatus() != Status.frei) {
            aktRaum.setStatus(Status.frei);
        }
        else {
            throw new InvalidDatasException("Der Raum ist bereits frei!");
        }
    }
}
