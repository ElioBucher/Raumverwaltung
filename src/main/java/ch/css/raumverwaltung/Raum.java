package ch.css.raumverwaltung;

public class Raum {
    private final int raumnummer;
    private final int kapazitaet;
    Status status = Status.frei;

    public Raum (int raumnummer, int kapazitaet) {

        if (!(raumnummer >= 100 && raumnummer <= 999) || (kapazitaet < 2)) {
            throw new InvalidDatasException ("Ungültige Raumnummer oder Kapazität");
        }

        this.raumnummer = raumnummer;
        this.kapazitaet = kapazitaet;
    }


}
