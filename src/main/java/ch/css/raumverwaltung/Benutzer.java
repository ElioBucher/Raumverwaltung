package ch.css.raumverwaltung;

public class Benutzer {
    private int id;
    private String name;
    private Rolle rolle;

    public Benutzer(int id, String name, Rolle rolle) {
        if (id < 0) {
            throw new InvalidDatasException("Ungültige ID!");
        }
        if (name == null) {
            throw new InvalidDatasException("Name darf nicht leer sein!");
        }
        if (rolle == null) {
            throw new InvalidDatasException("Rolle darf nicht null sein!");
        }

        this.id = id;
        this.name = name;
        this.rolle = rolle;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Rolle getRolle() {
        return rolle;
    }

    @Override
    public String toString() {
        return "Benutzer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rolle=" + rolle +
                '}';
    }
}