package FiltroManager;

import Main.Libro;

public class FiltroPerGenere implements FiltroLibro{
    private final String genere;

    public FiltroPerGenere(String genere) {
        this.genere = genere;
    }
    public boolean filtra(Libro libro) {
        return libro.getGenere().equalsIgnoreCase(genere);
    }
}
