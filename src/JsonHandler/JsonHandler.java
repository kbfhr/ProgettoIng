package JsonHandler;

import LibreriaSingleton.GestioneLibreria;
import LibreriaSingleton.InputHandler;
import com.google.gson.Gson;
import Main.Libro;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonHandler {
    private static final Gson gson = new Gson();
    Scanner scanner ;
    static GestioneLibreria gestioneLibreria = GestioneLibreria.getInstance();
    private FileWriter w;

    public void writeGson (String filepath){
        boolean continua = true;
        try{
            w = new FileWriter(filepath);
            scanner = new Scanner(System.in);
            InputHandler inputHandler = new InputHandler();
            while(continua) {
                String titolo = inputHandler.leggiStringa("Inserisci titolo: ");
                String autore = inputHandler.leggiStringa("Inserisci autore: ");
                int isbn = inputHandler.leggiIntero( "Inserisci isbn: ");
                String genere = inputHandler.leggiStringa( "Inserisci genere: ");
                int valutazione = inputHandler.leggiInteroRange( "Inserisci valutazione: ",1,5);
                Libro l = new Libro(titolo, autore, isbn, genere, valutazione, "disponibile");
                gestioneLibreria.getLibri().add(l);
                System.out.println("Vuoi continuare ad aggiungere libri? (si/no)");
                String risposta = scanner.nextLine();
                if (risposta.equalsIgnoreCase("no")) {
                    continua = false;
                }
            }
            gson.toJson(gestioneLibreria.getLibri(), w);
            w.close();
        }
        catch (IOException e){
            throw new RuntimeException();
        }
    }

    public void updateBooks() {
        try{
            FileReader fileReader = new FileReader("libri.json");
            Type arrayType = new TypeToken<ArrayList<Libro>>(){}.getType();
            ArrayList<Libro> libriJson = gson.fromJson(fileReader, arrayType);
            fileReader.close();

        }
        catch (FileNotFoundException e){
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        JsonHandler jsonHandler = new JsonHandler();
        jsonHandler.writeGson("libri.json");

    }
}
