package ch.css.raumverwaltung;

public class InvalidDatasException extends IllegalArgumentException {
    public InvalidDatasException(String message) {
        super(message);
    }
}
