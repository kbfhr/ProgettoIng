package JsonHandler;

import LibreriaSingleton.InputHandler;
import Main.Libro;

import java.util.ArrayList;
import java.util.Scanner;

public class GestioneLibri {
    private Scanner scanner;
    protected ArrayList<Libro> aggiungiLibri() {
        scanner = new Scanner(System.in);
        ArrayList<Libro> libri = new ArrayList<>();
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
            System.out.println(libri);
            System.out.println("Vuoi continuare ad aggiungere libri? (si/no)");
            String risposta = scanner.nextLine();
            if (risposta.equalsIgnoreCase("no")) {
                continua = false;
            }
        }
        return libri;
    }
}
