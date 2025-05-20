package JsonHandler;

import Main.Libro;

import java.util.ArrayList;

public interface Handler {
    void writeGson(ArrayList<Libro> libri,String filepath);
    void updateBookJson(String filepath);
    void removeBookJson(String filepath);

}
