package FiltroStrategy;

import FiltroManager.FiltroLibro;
import Main.Libro;

public class FiltroPerStato implements FiltroStrategy {
    private final String stato;

    public FiltroPerStato(String stato) {
        this.stato = stato;
    }

    public boolean filtra(Libro libro) {
        return libro.getStato().equalsIgnoreCase(stato);
    }
}
