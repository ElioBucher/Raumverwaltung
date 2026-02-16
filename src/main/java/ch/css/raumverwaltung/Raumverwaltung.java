package ch.css.raumverwaltung;
import java.util.HashMap;

public class Raumverwaltung {

    HashMap<Integer, Integer> Raum = new HashMap<Integer, Integer>();

    public void getRaum () {
        int gefragteRaumnummer = 100;

        if (gefragteRaumnummer >= 100 && gefragteRaumnummer <= 999) {
            System.out.println(Raum.get(gefragteRaumnummer));
        } else {
            throw new InvalidDatasException ("Ungültige Raumnummer");
        }
    }
}
