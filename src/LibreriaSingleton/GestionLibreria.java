package LibreriaSingleton;

import Command.Command;
import Command.RepositoryLibri;
import Command.RemoveCommand;
import Command.AggiungiCommand;
import Command.UpdateCommand;
import Main.Libro;
import Strategy.Ordina;

import java.util.ArrayList;
import java.util.Scanner;
import FiltroManager.*;

public class GestionLibreria {
    private static GestionLibreria instance;
    private final RepositoryLibri repositoryLibri;
    private final Ordina ordina;
    public final InputHandler inputHandler ;
    private FiltroManager filtroManager;
    private final Scanner scanner;
    private GestionLibreria(String filepath) {
        this.repositoryLibri = new RepositoryLibri(filepath);
        this.ordina = new Ordina();
        inputHandler = new InputHandler();
        this.scanner = new Scanner(System.in);
        this.filtroManager = new FiltroManager();
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

            Libro l = new Libro(titolo, autore, isbn, genere, valutazione, "disponibile");
            libri.add(l);

            String risposta = inputHandler.leggiStringa("Vuoi continuare? (si/no)");
            if(risposta.equals("no")) {
                continua = false;
            }
        }
        Command aggiungiCommand = new AggiungiCommand(repositoryLibri,libri);
        aggiungiCommand.execute();

    }
    public voi

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


    /*public static void main(String[] args) {
        System.out.println("Benvenuto nella libreria");
        GestionLibreria g = getInstance("libri.json");
        boolean continua = true;
        while(continua) {
            System.out.println("1. Aggiungi libro");
            System.out.println("2. Rimuovi libro");
            System.out.println("3. Aggiorna libro");
            System.out.println("4. Esci");
            int scelta = inputHandler.leggiInteroRange("Inserisci la tua scelta: ", 1, 4);
            switch (scelta) {
                case 1:
                    g.aggiungiLibro();
                    break;
                case 2:
                    g.rimuoviLibro();
                    break;
                case 3:
                    g.aggiornaLibro();
                    break;
                case 4:
                    continua = false;
                    break;
            }
        }
    }*/
    public void ordinaLibri() {
        String criterio = inputHandler.leggiStringa("Inserisci il criterio di ordinamento (titolo, autore, isbn, genere, valutazione, stato): ");
        ordina.ordina(repositoryLibri.getAll(), criterio);
        repositoryLibri.override(repositoryLibri.getAll());
        System.out.println("Libri ordinati con successo.");
        System.out.println(repositoryLibri.getAll());
    }
    public void filtra() {
        boolean fine = false;
        while (!fine){
            String tipoFiltro = inputHandler.leggiStringa("Aggiungi un filtro (genere, stato): ");
            switch (tipoFiltro.toLowerCase()) {
                case "genere":
                    String genere = inputHandler.leggiStringa("Inserisci il genere: ");
                    filtroManager.aggiungiFiltro("genere", new FiltroPerGenere( genere));
                    break;
                case "stato":
                    String stato = inputHandler.leggiStringa("Inserisci lo stato (letto, da leggere, in lettura): ");
                    filtroManager.aggiungiFiltro("stato", new FiltroPerStato(stato));
                    break;
                default:
                    System.out.println("Filtro non valido.");
            }
            String risposta = inputHandler.leggiStringa("Vuoi aggiungere un altro filtro? (si/no): ");
            if (risposta.equalsIgnoreCase("no")) {
                fine = true;
            }
        }
        ArrayList<Libro> libriFiltrati = filtroManager.applica(repositoryLibri.getAll());
        if  (libriFiltrati.isEmpty()) {
            System.out.println("Nessun libro trovato con i filtri selezionati.");
        } else {
            libriFiltrati.forEach(System.out::println);
        }

        /*Scanner scanner = new Scanner(System.in);

        System.out.print("Vuoi filtrare per GENERE? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Inserisci il genere: ");
            String genere = scanner.nextLine();
            filtroManager.aggiungiFiltro("genere", new FiltroPerGenere(genere));
        }

        System.out.print("Vuoi filtrare per STATO di lettura? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Inserisci lo stato (letto, da leggere, in lettura): ");
            String stato = scanner.nextLine();
            filtroManager.aggiungiFiltro("stato", new FiltroPerStato(stato));
        }

        System.out.println("\n=== Risultati dei filtri attivi ===");

        ArrayList<Libro> libriFiltrati = filtroManager.applica(repositoryLibri.getAll());
        if (libriFiltrati.isEmpty()) {
            System.out.println("Nessun libro trovato con i filtri selezionati.");
        } else {
            libriFiltrati.forEach(System.out::println);
        }*/
    }


    public static void main (String[] args) {
        GestionLibreria gestionLibreria = getInstance("libri.json");
        gestionLibreria.filtra();
    }
}
