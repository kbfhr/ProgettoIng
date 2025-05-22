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
        if (libriJson == null) {
            System.out.println("Nessun libro trovato, creo un nuovo file");
            return null;
        } else {
            return libriJson;
        }
        } catch (IOException e) {
            throw new RuntimeException();

        }

    }
    public void override (ArrayList<Libro> libri) {
        try {
            FileWriter filewriter = new FileWriter(filepath);
            gson.toJson(libri, filewriter);
            filewriter.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    public void save(ArrayList<Libro> libri) {
        ArrayList<Libro> libriJson = getAll();
        if (libriJson == null) {
            libriJson = new ArrayList<>();
            override(libriJson);
        }
        libriJson.addAll(libri);
        override(libriJson);
    }

    public void update(int isbn,Object attributo,Object valore) throws IOException {
        ArrayList<Libro> libriJson = getAll();
        boolean trovato = false;
        for (Libro l : libriJson) {
            if (l.getIsbn() == isbn) {
                switch (attributo.toString()) {
                    case "titolo" : l.setTitolo((String) valore);
                            trovato=true;
                    case "autore" : l.setAutore((String) valore);
                            trovato=true;
                    case "isbn" : l.setIsbn((int) valore);
                        trovato=true;
                    case "genere" :l.setGenere((Libro.Genere) valore);
                        trovato=true;
                    case "valutazione": l.setValutazione((int) valore);
                        trovato=true;
                    case "stato" : l.setStato((Libro.Stato) valore);
                        trovato=true;
                }
            }
            if (trovato) {
                override(libriJson);
                System.out.println(libriJson);

            } else {
                System.out.println("Libro non trovato");
            }

        }
    }
    public void remove(int isbn) {
        ArrayList<Libro> libriJson = getAll();
        boolean rimosso = false;
        for(Libro l : libriJson) {
            if(l.getIsbn() == isbn) {
                libriJson.remove(l);
                rimosso = true;
                break;
            }

        }
        if(rimosso){
            override(libriJson);
            System.out.println("Libro rimosso con successo");
        }
        else    {
            System.out.println("Libro non trovato");
        }

    }
}
