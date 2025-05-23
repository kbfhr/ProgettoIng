package Command;

import Main.Libro;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import LibreriaSingleton.InputHandler;
import GUI.InputLibriDialog;
import GUI.AggiornaLibroDialog;
import GUI.removeDialog;
import Observer.LibreriaSubject;

public class RepositoryLibri extends LibreriaSubject{
    private final Gson gson = new Gson();
    private final String filepath ;
    private final InputHandler inputHandler = new InputHandler();

    public RepositoryLibri(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Libro> getAll() {
        try {
            FileReader fileReader = new FileReader(filepath);
            Type arrayType = new TypeToken<ArrayList<Libro>>() {
            }.getType();
            ArrayList<Libro> libriJson = gson.fromJson(fileReader, arrayType);
        if (libriJson == null) {
            System.out.println("Nessun libro trovato, creo un nuovo file");
            return null;
        } else {
            System.out.println(getObservers());
            notifyObservers(libriJson);
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
            notifyObservers(libri);
            filewriter.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    public void save() {
        /*ArrayList<Libro> libri = new ArrayList<>();
        boolean continua = true;
        while(continua) {
            String titolo = inputHandler.leggiStringa("Inserisci titolo: ");
            String autore = inputHandler.leggiStringa("Inserisci autore: ");
            int isbn = inputHandler.leggiIntero("Inserisci isbn: ");
            String genereInput = inputHandler.leggiStringa("Inserisci genere: ").toUpperCase();
            Libro.Genere genere;

            try {
                genere = Libro.Genere.valueOf(genereInput.toLowerCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Genere non valido. Impostato su ALTRO.");
                genere = Libro.Genere.altro;
            }
            String statoInput = inputHandler.leggiStringa("Inserisci stato lettura: ").toUpperCase().replace(" ", "_");
            Libro.Stato stato;

            try {
                stato = Libro.Stato.valueOf(statoInput.toLowerCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Stato non valido. Impostato su DA_LEGGERE.");
                stato = Libro.Stato.da_leggere;
            }
            int valutazione = inputHandler.leggiInteroRange("Inserisci valutazione (1-5): ", 1, 5);
            Libro l = new Libro(titolo, autore, isbn, genere, valutazione, stato);
            libri.add(l);

            String risposta = inputHandler.leggiStringa("Vuoi continuare? (si/no)");
            if(risposta.equals("no")) {
                continua = false;
            }
        }

        if (libriJson == null) {
            libriJson = new ArrayList<>();
            override(libriJson);
        }*/
        ArrayList<Libro> libriJson = getAll();
        ArrayList<Libro> libri = InputLibriDialog.showBookInputDialog();
        libriJson.addAll(libri);
        override(libriJson);
    }

    public void update() throws IOException {
        /*ArrayList<Libro> libriJson = getAll();
        boolean modificato = false;
        int isbn = inputHandler.leggiIntero("Inserisci l'ISBN del libro da aggiornare: ");
        for (Libro l : libriJson) {
            if (l.getIsbn() == isbn) {
                String attributo = null;
                boolean continua = false;
                while(!continua){
                    String input = inputHandler.leggiStringa("Inserisci l'attributo da aggiornare (titolo, autore, genere, valutazione): ");
                    switch (input.toLowerCase()) {
                        case "titolo":
                            attributo = input;
                            continua = true;
                            break;
                        case "autore":
                            attributo = input;
                            continua = true;
                            break;
                        case "genere":
                            attributo = input;
                            continua = true;
                            break;
                        case "valutazione":
                            attributo = input;
                            continua = true;
                            break;
                        case "stato":
                            attributo = input;
                            continua = true;
                            break;
                        default:
                            System.out.println("Attributo non valido");
                    }
                }
                Object nuovoValore = null;
                if(attributo.equals("titolo") || attributo.equals("autore")) {
                    nuovoValore = inputHandler.leggiStringa("Inserisci il nuovo valore: ");
                }else if(attributo.equals("valutazione")) {
                    nuovoValore = inputHandler.leggiInteroRange("Inserisci la nuova valutazione: ", 1, 5);

                }
                else if(attributo.equals("genere")) {
                    String genereInput = inputHandler.leggiStringa("Inserisci il nuovo genere: ").toUpperCase();
                    Libro.Genere genere;

                    try {
                        genere = Libro.Genere.valueOf(genereInput.toLowerCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Genere non valido. Impostato su ALTRO.");
                        genere = Libro.Genere.altro;
                    }
                    nuovoValore = genere;
                }
                else if(attributo.equals("stato")) {
                    String statoInput = inputHandler.leggiStringa("Inserisci il nuovo stato: ").toUpperCase().replace(" ", "_");
                    Libro.Stato stato;

                    try {
                        stato = Libro.Stato.valueOf(statoInput.toLowerCase());
                        nuovoValore = stato;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Stato non valido. Impostato su DA_LEGGERE.");
                        stato = Libro.Stato.da_leggere;
                    }

                }
                else{
                    System.out.println("Attributo non valido");
                }
                System.out.println("attributo valido" );
                switch (attributo) {
                    case "titolo" : l.setTitolo((String) nuovoValore);
                        System.out.println("Titolo aggiornato");
                        modificato=true;
                        break;
                    case "autore" : l.setAutore((String) nuovoValore);
                        modificato=true;
                        break;
                    case "isbn" : l.setIsbn((int) nuovoValore);
                        modificato=true;
                        break;
                    case "genere" :l.setGenere((Libro.Genere) nuovoValore);
                        modificato=true;
                        break;
                    case "valutazione": l.setValutazione((int) nuovoValore);
                        modificato=true;
                        break;
                    case "stato" : l.setStato((Libro.Stato) nuovoValore);
                        modificato=true;
                        break;
                }

            }


        }*/
        ArrayList<Libro> libriJson = getAll();
        System.out.println(libriJson);
            if (AggiornaLibroDialog.updateLibroByTitle(libriJson)) {

                override(libriJson);

            } else {
                System.out.println("Libro non trovato");
            }


    }
    public void remove() {
        ArrayList<Libro> libriJson = getAll();
        /*boolean rimosso = false;
        for(Libro l : libriJson) {
            if(l.getIsbn() == isbn) {
                libriJson.remove(l);
                rimosso = true;
                break;
            }

        }*/
        if(removeDialog.removeLibroByTitle(libriJson)){
            override(libriJson);
            System.out.println("Libro rimosso con successo");
        }
        else    {
            System.out.println("Libro non trovato");
        }

    }
}
