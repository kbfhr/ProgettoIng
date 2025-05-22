package FiltroManager;

import Main.Libro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FiltroManager {
    private final Map<String, FiltroLibro> libros = new HashMap<>();

    public void aggiungiFiltro(String nome, FiltroLibro filtro) {
        libros.put(nome, filtro);
    }
    public Map<String, FiltroLibro> getLibros() {
        return libros;
    }
    public void removeFiltro(String nome) {
        libros.remove(nome);
    }
    public void removeAll() {
        libros.clear();
    }
    public ArrayList<Libro> applica(ArrayList<Libro> libri) {
        return new ArrayList<>(libri.stream()
                .filter(libro -> libros.values().stream().allMatch(f -> f.filtra(libro)))
                .toList());
    }
}
