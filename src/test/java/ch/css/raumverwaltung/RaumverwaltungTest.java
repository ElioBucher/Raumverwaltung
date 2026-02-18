package ch.css.raumverwaltung;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RaumverwaltungTest {

    @Test
    void testGetRaum() {
        Raumverwaltung rv = new Raumverwaltung();
        Raum raum = rv.getRaum(600);
        assertNotNull(raum);
        assertEquals(600, raum.getRaumnummer());
        assertEquals(18, raum.getKapazitaet());
        assertEquals(Status.frei, raum.getStatus());
    }

    @Test
    void shouldThrowExceptionForInvalidRaumnummer() {
        Raumverwaltung rv = new Raumverwaltung();
        assertThrows(InvalidDatasException.class, () -> rv.getRaum(99));
        assertThrows(InvalidDatasException.class, () -> rv.getRaum(1000));
    }

    @Test
    void shouldThrowExceptionForNonExistingRaum() {
        Raumverwaltung rv = new Raumverwaltung();
        assertThrows(InvalidDatasException.class, () -> rv.getRaum(601));
    }

    @Test
    void shouldGetTinestPossibleRaum() {
        Raumverwaltung rv = new Raumverwaltung();
        Benutzer benutzer = new Benutzer(1, "Alice", Rolle.STUDENT);
        Reservation res = rv.resRaum(10, LocalDateTime.now(), LocalDateTime.now().plusHours(1), benutzer);
        assertNotNull(res);
        assertEquals(600, res.getRaum().getRaumnummer());
    }

    @Test
    void shouldThrowExceptionWhenNoRaumAvailable() {
        Raumverwaltung rv = new Raumverwaltung();
        Benutzer benutzer = new Benutzer(1, "Alice", Rolle.STUDENT);
        rv.resRaum(18, LocalDateTime.now(), LocalDateTime.now().plusHours(1), benutzer);
        assertThrows(InvalidDatasException.class, () -> rv.resRaum(1, LocalDateTime.now(), LocalDateTime.now().plusHours(1), benutzer));
    }

    @Test
    void shouldThrowExceptionWhenUserIsNull() {
        Raumverwaltung rv = new Raumverwaltung();
        assertThrows(InvalidDatasException.class, () -> rv.sperrRaum(600, null));
    }

    @Test
    void shouldThrowExceptionWhenUserIsNotAdmin() {
        Raumverwaltung rv = new Raumverwaltung();
        Benutzer benutzer = new Benutzer(1, "Alice", Rolle.STUDENT);
        assertThrows(InvalidDatasException.class, () -> rv.sperrRaum(600, benutzer));
    }

    @Test
    void shouldThrowExceptionWhenRaumIsBesetzt() {
        Raumverwaltung rv = new Raumverwaltung();
        Benutzer benutzer = new Benutzer(1, "Alice", Rolle.STUDENT);
        rv.resRaum(18, LocalDateTime.now(), LocalDateTime.now().plusHours(1), benutzer);
        Benutzer admin = new Benutzer(2, "Bob", Rolle.ADMIN);
        assertThrows(InvalidDatasException.class, () -> rv.sperrRaum(600, admin));
    }

    @Test
    void shouldThrowExceptionWhenRaumIsAlreadyGesperrt() {
        Raumverwaltung rv = new Raumverwaltung();
        Benutzer admin = new Benutzer(2, "Bob", Rolle.ADMIN);
        rv.sperrRaum(600, admin);
        assertThrows(InvalidDatasException.class, () -> rv.sperrRaum(600, admin));
    }

    @Test
    void shouldSperrRaum() {
        Raumverwaltung rv = new Raumverwaltung();
        Benutzer admin = new Benutzer(2, "Bob", Rolle.ADMIN);
        rv.sperrRaum(600, admin);
        Raum raum = rv.getRaum(600);
        assertEquals(Status.gesperrt, raum.getStatus());
    }

    @Test
    void shouldThrowExceptionWhenRaumIsAlreadyFrei() {
        Raumverwaltung rv = new Raumverwaltung();
        assertThrows(InvalidDatasException.class, () -> rv.resetRaum(600));
    }

    @Test
    void shouldResetRaum() {
        Raumverwaltung rv = new Raumverwaltung();
        Benutzer benutzer = new Benutzer(1, "Alice", Rolle.STUDENT);
        rv.resRaum(18, LocalDateTime.now(), LocalDateTime.now().plusHours(1), benutzer);
        rv.resetRaum(600);
        Raum raum = rv.getRaum(600);
        assertEquals(Status.frei, raum.getStatus());
    }

    // Meine zusätzlichen Features Tests
    @Test
    void shouldReturnStringRepresentationOfReservation() {
         Raumverwaltung rv = new Raumverwaltung();
         Benutzer benutzer = new Benutzer(1, "Alice", Rolle.STUDENT);
         Reservation res = rv.resRaum(10, LocalDateTime.of(2024, 6, 1, 10, 0), LocalDateTime.of(2024, 6, 1, 12, 0), benutzer);
         String expected = "Reservation:\n" +
                 "Raum = 600\n" +
                 "Kapazität = 18\n" +
                 "besetzt von 2024-06-01T10:00 bis 2024-06-01T12:00\n" +
                 "================================\n";
         assertEquals(expected, res.toString());
     }
}