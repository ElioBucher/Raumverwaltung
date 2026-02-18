package ch.css.raumverwaltung;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Raum {
    private final int raumnummer;
    private final int kapazitaet;
    private Status status;
    private final List<Reservation> reservationen = new ArrayList<>();

    public Raum (int raumnummer, int kapazitaet, Status status) {

        if (!(raumnummer >= 100 && raumnummer <= 999) || (kapazitaet < 2)) {
            throw new InvalidDatasException ("Ungültige Raumnummer oder Kapazität");
        }

        this.raumnummer = raumnummer;
        this.kapazitaet = kapazitaet;
        this.status = status;
    }

    public Raum(int raumnummer, int kapazitaet) {

        this(raumnummer, kapazitaet, Status.frei);
    }

    public boolean istFrei(LocalDateTime start, LocalDateTime end) {
        for (Reservation reservation : reservationen) {
            if (reservation.schneidetSich(start, end)) {
                return false;
            }
        }
        return true;
    }

    public void addRes(Reservation reservation) {

        reservationen.add(reservation);
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

    // AI
    @Override
    public String toString() {
        return "Raum: \n" +
                "Raumnummer = " + raumnummer + "\n" +
                "Kapazität = " + kapazitaet + "\n" +
                "Status = " + status + "\n\n" +
                "================================\n";
    }
    // bis hier AI

    public List<Reservation> getReservationen() {
        return reservationen;
    }
}