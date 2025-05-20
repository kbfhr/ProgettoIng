package JsonHandler;

import Main.Libro;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GestioneJson implements Handler {
    private static final Gson gson = new Gson();
    GestioneLibri g = new GestioneLibri();

    @Override
    public void writeGson(String filepath) {
        try {
            ArrayList<Libro> libri = g.aggiungiLibri();
            ArrayList<Libro> libriPresenti = libriPresenti();
            libriPresenti.addAll(libri);
            System.out.println(libriPresenti);
            FileWriter filewriter = new FileWriter(filepath);
            gson.toJson(libriPresenti, filewriter);
            filewriter.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public ArrayList<Libro> libriPresenti() {
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

    @Override
    public void updateBookJson(String filepath) {
        try {
            FileReader fileReader = new FileReader("libri.json");
            Type arrayType = new TypeToken<ArrayList<Libro>>() {
            }.getType();
            ArrayList<Libro> libriJson = gson.fromJson(fileReader, arrayType);
            System.out.println(libriJson);
            InputHandler inputHandler = new InputHandler();
            int isbn = inputHandler.leggiIntero("Inserisci l'ISBN del libro da aggiornare: ");
            boolean trovato = false;
            for (Libro libro : libriJson) {
                if (libro.getIsbn() == isbn) {
                    while(!trovato){
                    String attributo = inputHandler.leggiStringa("Inserisci l'attributo da aggiornare (titolo, autore, genere, valutazione): ");
                    switch (attributo.toLowerCase()) {
                        case "titolo":
                            libro.setTitolo(inputHandler.leggiStringa("Insersci il nuovo titolo: "));
                            trovato = true;
                            break;
                        case "autore":
                            libro.setAutore(inputHandler.leggiStringa("Insersci il nuovo autore: "));
                            trovato = true;
                            break;
                        case "genere":
                            libro.setGenere((inputHandler.leggiStringa("Insersci il nuovo genere: ")));
                            trovato = true;
                            break;
                        case "valutazione":
                            libro.setValutazione(inputHandler.leggiInteroRange("Insersci la nuova valutazione: ", 1, 5));
                            trovato = true;
                            break;
                        case "stato":
                            libro.setStato(inputHandler.leggiStringa(("Insersci il nuovo stato: ")));
                            trovato = true;
                            break;
                        default:
                            System.out.println("Attributo non valido");
                        }
                    }
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
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


    @Override
    public void removeBookJson(String filepath) {
        try{
            FileReader fileReader = new FileReader("libri.json");
            Type arrayType = new TypeToken<ArrayList<Libro>>() {
            }.getType();
            ArrayList<Libro> libriJso = gson.fromJson(fileReader, arrayType);
            System.out.println(libriJso);
            InputHandler inputHandler = new InputHandler();
            int isbn = inputHandler.leggiIntero("Inserisci l'ISBN del libro da eliminare: ");
            boolean trovato = false;
            for (Libro libro : libriJso) {
                System.out.println(libro.getIsbn());
                if (libro.getIsbn() == isbn) {
                    libriJso.remove(libro);
                    trovato = true;
                System.out.println("Libro rimosso");
                    break;}
            }
            if (trovato) {
                FileWriter filewriter = new FileWriter(filepath);
                gson.toJson(libriJso, filewriter);
                System.out.println(libriJso);
                filewriter.close();
            } else {
                System.out.println("Libro non trovato");
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
        }




