package Strategy;

import Command.RepositoryLibri;
import Main.Libro;

import java.util.ArrayList;

public interface OrdinamentoStrategy {


    void ordina(ArrayList<Libro> libri, String criterio);
}
