package LibreriaSingleton;

import JsonHandler.GestioneJson;
import JsonHandler.InputHandler;
import Main.Libro;

import java.util.ArrayList;
import java.util.Scanner;

public class GestioneLibreria {
    private static GestioneLibreria instance;
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
    public void aggiornaLibro() {
        json.updateBookJson(filepath);
    }
    public void aggiungiLibro() {
        json.writeGson("libri.json");
    }
    public void rimuoviLibro() {
        json.removeBookJson(filepath);
    }
    public ArrayList<Libro> getLibri() {
        ArrayList<Libro> libri = json.libriPresenti();
        System.out.println(libri);
        return libri;
    }
public static void main(String[] args) {
        GestioneLibreria gestioneLibreria = GestioneLibreria.getInstance();
        gestioneLibreria.rimuoviLibro();
    }

}
