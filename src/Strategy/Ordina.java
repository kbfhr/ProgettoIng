package Strategy;

import Main.Libro;

import java.util.ArrayList;
import java.util.Comparator;

public class Ordina implements OrdinamentoStrategy {


    @Override
    public void ordina(ArrayList<Libro> libri, String criterio) {
        switch (criterio) {
            case "titolo" -> libri.sort(Comparator.comparing(Libro::getTitolo));
            case "autore" -> libri.sort(Comparator.comparing(Libro::getAutore));
            case "isbn" -> libri.sort(Comparator.comparing(Libro::getIsbn));
            case "genere" -> libri.sort(Comparator.comparing(Libro::getGenere));
            case "valutazione" -> libri.sort(Comparator.comparing(Libro::getValutazione));
            case "stato" -> libri.sort(Comparator.comparing(Libro::getStato));
        }
    }
}
