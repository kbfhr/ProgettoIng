package JsonHandler;

import Main.Libro;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GestioneJson implements Handler{
    private static final Gson gson = new Gson();
    private FileWriter filewriter;

    @Override
    public void writeGson(ArrayList<Libro> libri, String filepath) {
        try{
            filewriter = new FileWriter(filepath);
            gson.toJson(libri, filewriter);
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public void updateBookJson(String filepath) {
        try{
            FileReader fileReader = new FileReader("libri.json");
            Type arrayType = new TypeToken<ArrayList<Libro>>(){}.getType();
            ArrayList<Libro> libriJson = gson.fromJson(fileReader, arrayType);
            for(Libro libro : libriJson){
                System.out.println(libro);
            }
        }catch(IOException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void removeBookJson(String filepath) {

    }


}
