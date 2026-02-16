package ch.css.raumverwaltung;

public class Raum {
    private final int raumnummer;
    private final int kapazitaet;
    Status status = Status.frei;

    public Raum (int raumnummer, int kapazitaet, Status status) {

        if (!(raumnummer >= 100 && raumnummer <= 999) || (kapazitaet < 2)) {
            throw new InvalidDatasException ("Ungültige Raumnummer oder Kapazität");
        }

        this.raumnummer = raumnummer;
        this.kapazitaet = kapazitaet;
        this.status = status;
    }

    public boolean istFrei() {
        if (status == Status.frei) {
            return true;
        } else {
            return false;
        }
    }


    public int getRaumnummer() {
        return raumnummer;
    }

    public int getKapazitaet() {
        return kapazitaet;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}