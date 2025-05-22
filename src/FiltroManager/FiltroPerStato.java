package FiltroManager;

import Main.Libro;

public class FiltroPerStato implements FiltroLibro{
    private final String stato;

    public FiltroPerStato(String stato) {
        this.stato = stato;
    }

    public boolean filtra(Libro libro) {
        return libro.getStato().equalsIgnoreCase(stato);
    }
}
