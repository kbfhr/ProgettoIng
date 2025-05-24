package FiltroStrategy;

import FiltroManager.FiltroLibro;
import Main.Libro;

public class FiltroPerGenere implements FiltroStrategy {
    private final String genere;

    public FiltroPerGenere(String genere) {
        this.genere = genere;
    }
    public boolean filtra(Libro libro) {
        return libro.getGenere().equalsIgnoreCase(genere);
    }
}
