package ch.css.raumverwaltung;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Test
    void testValidReservation() {
        Raum raum = new Raum(101, 10);
        Benutzer benutzer = new Benutzer(1, "Max Mustermann", Rolle.ADMIN);
        LocalDateTime start = LocalDateTime.of(2024, 6, 10, 10, 0);
        LocalDateTime end = LocalDateTime.of(2024, 6, 10, 12, 0);
        Reservation reservation = new Reservation(raum, start, end, benutzer);
    }

    @Test
    void shouldThrowExceptionForMissingInfo() {

        assertThrows(InvalidDatasException.class, () -> {
            new Reservation(null, LocalDateTime.now(), LocalDateTime.now().plusHours(1), new Benutzer(1, "Max Mustermann", Rolle.ADMIN));
        });
        assertThrows(InvalidDatasException.class, () -> {
            new Reservation(new Raum(101, 10), null, LocalDateTime.now().plusHours(1), new Benutzer(1, "Max Mustermann", Rolle.ADMIN));
        });
        assertThrows(InvalidDatasException.class, () -> {
            new Reservation(new Raum(101, 10), LocalDateTime.now(), null, new Benutzer(1, "Max Mustermann", Rolle.ADMIN));
        });
        assertThrows(InvalidDatasException.class, () -> {
            new Reservation(new Raum(101, 10), LocalDateTime.now(), LocalDateTime.now().plusHours(1), null);
        });

    }

    @Test
    void shouldReturnTrueForOverlappingReservations() {

        Raum raum = new Raum(101, 10);
        Benutzer benutzer = new Benutzer(1, "Max Mustermann", Rolle.ADMIN);
        LocalDateTime start1 = LocalDateTime.of(2024, 6, 10, 10, 0);
        LocalDateTime end1 = LocalDateTime.of(2024, 6, 10, 12, 0);
        Reservation reservation = new Reservation(raum, start1, end1, benutzer);
        LocalDateTime start2 = LocalDateTime.of(2024, 6, 10, 11, 0);
        LocalDateTime end2 = LocalDateTime.of(2024, 6, 10, 13, 0);
        assertTrue(reservation.schneidetSich(start2, end2));
    }



}