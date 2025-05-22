package LibreriaSingleton;

import Command.Command;
import Command.RepositoryLibri;
import Command.RemoveCommand;
import Command.AggiungiCommand;
import Command.UpdateCommand;
import Main.Libro;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionLibreria {
    private static GestionLibreria instance;
    private final RepositoryLibri repositoryLibri;
    private final InputHandler inputHandler;
    private final Scanner scanner;
    private GestionLibreria(String filepath) {
        this.repositoryLibri = new RepositoryLibri(filepath);
        this.inputHandler = new InputHandler();
        this.scanner = new Scanner(System.in);
    }
    public static GestionLibreria getInstance(String filepath) {
        if (instance == null) {
            instance = new GestionLibreria(filepath);
        }
        return instance;
    }
    public void rimuoviLibro() {
        int isbn = inputHandler.leggiInteroRange("Inserisci l'ISBN del libro da rimuovere: ",1,5);
        Command removeCommand = new RemoveCommand(repositoryLibri,isbn);
        removeCommand.execute();
        System.out.println("Libro rimosso con successo.");
    }

    public void aggiungiLibro() {
        ArrayList<Libro> libri = new ArrayList<>();
        boolean continua = true;
        while(continua) {
            String titolo = inputHandler.leggiStringa("Inserisci titolo: ");
            String autore = inputHandler.leggiStringa("Inserisci autore: ");
            int isbn = inputHandler.leggiIntero("Inserisci isbn: ");
            String genere = inputHandler.leggiStringa("Inserisci genere: ");
            int valutazione = inputHandler.leggiInteroRange("Inserisci valutazione: ", 1, 5);

            Libro l = BookFactory.createBook(titolo, autore, isbn, genere, valutazione);
            libri.add(l);

            String risposta = inputHandler.leggiStringa("Vuoi continuare? (si/no)");
            if(risposta.equals("no")) {
                continua = false;
            }
        }
        Command aggiungiCommand = new AggiungiCommand(repositoryLibri,libri);
        aggiungiCommand.execute();
        System.out.println("Libri aggiunti con successo.");
    }

    public void aggiornaLibro() {
        int isbn = inputHandler.leggiIntero("Inserisci l'ISBN del libro da aggiornare: ");
        Object attributo = null;
        boolean trovato = false;
        while(!trovato){
            String input = inputHandler.leggiStringa("Inserisci l'attributo da aggiornare (titolo, autore, genere, valutazione): ");
            switch (input.toLowerCase()) {
                case "titolo":
                    attributo = input;
                    trovato = true;
                    break;
                case "autore":
                    attributo = input;
                    trovato = true;
                    break;
                case "genere":
                    attributo = input;
                    trovato = true;
                    break;
                case "valutazione":
                    attributo = input;
                    trovato = true;
                    break;
                case "stato":
                    attributo = input;
                    trovato = true;
                    break;
                default:
                    System.out.println("Attributo non valido");
            }
        }
        Object nuovoValore = null;
        if(attributo.equals("titolo") || attributo.equals("autore") || attributo.equals("genere") || attributo.equals("stato")) {
            nuovoValore = inputHandler.leggiStringa("Inserisci il nuovo valore: ");
        }else if(attributo.equals("valutazione")) {
            nuovoValore = inputHandler.leggiInteroRange("Inserisci la nuova valutazione: ", 1, 5);

        }
        Command updateCommand = new UpdateCommand(repositoryLibri,isbn,attributo,nuovoValore);
        updateCommand.execute();
        System.out.println("Libro aggiornato con successo.");
    }


}
