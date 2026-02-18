package ch.css.raumverwaltung;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RaumTest {


    @Test
    void createRaum() {
        Raum raum = new Raum(100, 10);
        assertEquals(100, raum.getRaumnummer());
        assertEquals(10, raum.getKapazitaet());
        assertEquals(Status.frei, raum.getStatus());
    }

    @Test
    void createRaumWithStatus() {
        Raum raum = new Raum(101, 20, Status.besetzt);
        assertEquals(101, raum.getRaumnummer());
        assertEquals(20, raum.getKapazitaet());
        assertEquals(Status.besetzt, raum.getStatus());
    }

    @Test
    void shouldThrowExceptionForInvalidRaumnummer() {
        assertThrows(InvalidDatasException.class, () -> new Raum(99, 10));
        assertThrows(InvalidDatasException.class, () -> new Raum(1000, 10));
    }

}