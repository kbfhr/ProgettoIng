package LibreriaSingleton;

import JsonHandler.GestioneJson;
import Main.Libro;

import java.util.ArrayList;
import java.util.Scanner;

public class GestioneLibreria {
    private static GestioneLibreria instance;
    ArrayList<Libro> libri = new ArrayList<>();
    private GestioneJson json = new GestioneJson();
    private final String filepath = "libri.json";
    private Scanner scanner;

    private GestioneLibreria() {
    }
    public static GestioneLibreria getInstance() {
        if (instance == null) {
            instance = new GestioneLibreria();
        }
        return instance;
    }
    public void aggiungiLibro() {
        scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler();
        boolean continua = true;
        while(continua) {
            String titolo = inputHandler.leggiStringa("Inserisci titolo: ");
            String autore = inputHandler.leggiStringa("Inserisci autore: ");
            int isbn = inputHandler.leggiIntero( "Inserisci isbn: ");
            String genere = inputHandler.leggiStringa( "Inserisci genere: ");
            int valutazione = inputHandler.leggiInteroRange( "Inserisci valutazione: ",1,5);
            Libro l = new Libro(titolo, autore, isbn, genere, valutazione, "disponibile");
            libri.add(l);
            System.out.println("Vuoi continuare ad aggiungere libri? (si/no)");
            String risposta = scanner.nextLine();
            if (risposta.equalsIgnoreCase("no")) {
                continua = false;
            }
        }
        json.writeGson(libri,filepath);
    }
    public void rimuoviLibro(Libro libro) {
        libri.remove(libro);
    }
    public ArrayList<Libro> getLibri() {
        return new ArrayList<>(libri);
    }
public static void main(String[] args) {
        GestioneLibreria gestioneLibreria = GestioneLibreria.getInstance();
        gestioneLibreria.aggiungiLibro();
    }

}
