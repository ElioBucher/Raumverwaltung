package ch.css.raumverwaltung;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BenutzerTest {

    @Test
    void BenutzerErstellen() {
        Benutzer benutzer = new Benutzer(1, "Max Mustermann", Rolle.ADMIN);
        assertEquals(1, benutzer.getId());
        assertEquals("Max Mustermann", benutzer.getName());
        assertEquals(Rolle.ADMIN, benutzer.getRolle());
    }

    @Test
    void shouldThrowExceptionForInvalidId() {
        assertThrows(InvalidDatasException.class, () -> {
            new Benutzer(-1, "Max Mustermann", Rolle.ADMIN);
        });
    }

    @Test
    void shouldThrowExceptionForNullName() {
        assertThrows(InvalidDatasException.class, () -> {
            new Benutzer(1, null, Rolle.ADMIN);
        });
    }

    @Test
    void shouldThrowExceptionForNullRolle() {
        assertThrows(InvalidDatasException.class, () -> {
            new Benutzer(1, "Max Mustermann", null);
        });
    }

}