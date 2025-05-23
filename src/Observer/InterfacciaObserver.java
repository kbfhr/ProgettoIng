package Observer;

import Main.Libro;

import java.util.ArrayList;

public interface InterfacciaObserver {
    void update(ArrayList<Libro> listaLibri);
}
