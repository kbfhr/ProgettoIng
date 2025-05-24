package LibreriaSingleton;

import Command.Command;
import Command.RepositoryLibri;
import Command.RemoveCommand;
import Command.AggiungiCommand;
import Command.UpdateCommand;
import FiltroStrategy.FiltroPerGenere;
import FiltroStrategy.FiltroPerStato;
import Main.Libro;
import Observer.InterfacciaObserver;
import Strategy.Ordina;

import java.util.ArrayList;
import java.util.Scanner;
import FiltroStrategy.*;

public class GestionLibreria {
    private static GestionLibreria instance;
    private RepositoryLibri repositoryLibri;
    private final Ordina ordina;
    public final InputHandler inputHandler ;
    private FiltroStrategyConcreto filtroManager;
    private final Scanner scanner;
    private GestionLibreria() {
        this.ordina = new Ordina();
        inputHandler = new InputHandler();
        this.scanner = new Scanner(System.in);
        this.filtroManager = new FiltroStrategyConcreto();
    }
    public static GestionLibreria getInstance() {
        if (instance == null) {
            instance = new GestionLibreria();
        }
        return instance;
    }
    public void caricaLibri(String filepath) {
        repositoryLibri = new RepositoryLibri(filepath);

    }
    public void aggiungiObserver(InterfacciaObserver observer) {
        repositoryLibri.addObserver(observer);
    }
    public ArrayList<Libro> getLibri() {
        if (repositoryLibri == null) {
            System.out.println("Aggiungi prima un libro.");
            return null;
        }
        return repositoryLibri.getAll();
    }
    public void rimuoviLibro() {

        Command removeCommand = new RemoveCommand(repositoryLibri);
        removeCommand.execute();

    }

    public void aggiungiLibro() {
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
        }*/
        Command aggiungiCommand = new AggiungiCommand(repositoryLibri);
        aggiungiCommand.execute();

    }


    public void aggiornaLibro() {
        /*int isbn = inputHandler.leggiIntero("Inserisci l'ISBN del libro da aggiornare: ");
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
        }*/
        Command updateCommand = new UpdateCommand(repositoryLibri);
        updateCommand.execute();
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
    public void filtroPerGenere(String genere) {
        /*if (repositoryLibri.getAll() == null) {
            System.out.println("Aggiungi prima un libro.");
            return;
        }
        boolean fine = false;
        boolean applicato = false;
        while (!fine){
            String tipoFiltro = inputHandler.leggiStringa("Aggiungi un filtro (genere, stato): ");
            switch (tipoFiltro.toLowerCase()) {
                case "genere":
                    System.out.println("Generi disponibili:");
                    for (Libro.Genere g : Libro.Genere.values()) {
                        System.out.println("- " + g.name().toLowerCase());
                    }
                    String inputGenere = inputHandler.leggiStringa("Inserisci il genere: ").toLowerCase();

                    try {
                        Libro.Genere genereEnum = Libro.Genere.valueOf(inputGenere);
                        filtroManager.aggiungiFiltro("genere", new FiltroPerGenere(inputGenere));
                        applicato = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Genere non valido, filtro ignorato.");
                    }
                    break;
                case "stato":
                    System.out.println("Stati di lettura disponibili:");
                    for (Libro.Stato stato : Libro.Stato.values()) {
                        System.out.println("- " + stato.name().toLowerCase().replace("_", " "));
                    }
                    String inputStato = inputHandler.leggiStringa("Inserisci lo stato (letto, da leggere, in lettura): ")
                            .toLowerCase().replace(" ", "_");

                    try {
                        Libro.Stato statoEnum = Libro.Stato.valueOf(inputStato);
                        filtroManager.aggiungiFiltro("stato", new FiltroPerStato(inputStato));
                        applicato = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Stato non valido, filtro ignorato.");
                    }
                    break;
                default:
                    System.out.println("Filtro non valido.");
            }
            String risposta = inputHandler.leggiStringa("Vuoi aggiungere un altro filtro? (si/no): ");
            if (risposta.equalsIgnoreCase("no")) {
                fine = true;
            }
        }
        if (filtroManager.getLibros().isEmpty()) {
            System.out.println("Nessun filtro selezionato.");
        }
        else{
            ArrayList<Libro> libriFiltrati = filtroManager.applica(repositoryLibri.getAll());
            if  (libriFiltrati.isEmpty()) {
                System.out.println("Nessun libro trovato con i filtri selezionati.");
            } else {
                libriFiltrati.forEach(System.out::println);
            }
        }*/
        filtroManager.aggiungiFiltro(genere,new FiltroPerGenere(genere));
    }
    public void filtraPerStato(String stato) {
        filtroManager.aggiungiFiltro(stato,new FiltroPerStato(stato));
    }
    public void applicaFiltri(){
        ArrayList<Libro> libriFiltrati=filtroManager.applicaFiltri(repositoryLibri.getAll());
        repositoryLibri.notifyObservers(libriFiltrati);
    }
    public void rimuoviFiltro(String valore) {
        filtroManager.rimuoviFiltro(valore);
        System.out.println(filtroManager.filtriAttivi());
        if(filtroManager.filtriAttivi()){

            ArrayList<Libro> libriFiltrati = filtroManager.applicaFiltri(repositoryLibri.getAll());
            repositoryLibri.notifyObservers(libriFiltrati);
        } else {
            repositoryLibri.notifyObservers(repositoryLibri.getAll());
        }
        //repositoryLibri.notifyObservers(repositoryLibri.getAll());
    }
    /*public void rimuoviFiltroPerStato(String stato) {
        filtroManager.rimuoviFiltro(stato);
        if(filtroManager.filtriAttivi()){
            ArrayList<Libro> libriFiltrati = filtroManager.applicaFiltri(repositoryLibri.getAll());
            repositoryLibri.notifyObservers(libriFiltrati);
        } else {
            repositoryLibri.notifyObservers(repositoryLibri.getAll());
        }
        //repositoryLibri.notifyObservers(repositoryLibri.getAll());
    }*/
    public void rimuoviTuttiFiltri() {
        filtroManager.pulisciFiltri();
        repositoryLibri.notifyObservers(repositoryLibri.getAll());
    }



    /*public static void main (String[] args) {
        GestionLibreria gestionLibreria = getInstance();
        gestionLibreria.aggiornaLibro();
    }*/
}
