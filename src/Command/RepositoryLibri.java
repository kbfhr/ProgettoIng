package Command;

import Main.Libro;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class RepositoryLibri {
    private final Gson gson = new Gson();
    private final String filepath ;

    public RepositoryLibri(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Libro> getAll() {
        try {
            FileReader fileReader = new FileReader("libri.json");
            Type arrayType = new TypeToken<ArrayList<Libro>>() {
            }.getType();
            ArrayList<Libro> libriJson = gson.fromJson(fileReader, arrayType);
            return libriJson;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void save(ArrayList<Libro> libri) {
        try {
            ArrayList<Libro> libriJson = getAll();
            libriJson.addAll(libri);
            FileWriter filewriter = new FileWriter(filepath);
            gson.toJson(libriJson, filewriter);
            filewriter.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void update(int isbn,Object attributo,Object valore) throws IOException {
        ArrayList<Libro> libriJson = getAll();
        boolean trovato = false;
        for (Libro l : libriJson) {
            if (l.getIsbn() == isbn) {
                switch (attributo.toString()) {
                    case "titolo" -> l.setTitolo((String) valore);
                    case "autore" -> l.setAutore((String) valore);
                    case "isbn" -> l.setIsbn((int) valore);
                    case "genere" -> l.setGenere((String) valore);
                    case "valutazione" -> l.setValutazione((int) valore);
                    case "stato" -> l.setStato((String) valore);
                }
            }
            if (trovato) {
                FileWriter filewriter = new FileWriter(filepath);
                gson.toJson(libriJson, filewriter);
                System.out.println(libriJson);
                filewriter.close();
            } else {
                System.out.println("Libro non trovato");
            }

        }
    }
    public void remove(int isbn) {

    }
}
