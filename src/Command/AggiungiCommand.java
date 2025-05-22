package Command;

import Main.Libro;

import java.util.ArrayList;

public class AggiungiCommand implements Command {
    private final RepositoryLibri repositoryLibri;
    private final ArrayList<Libro> libri;

    public AggiungiCommand(RepositoryLibri repositoryLibri, ArrayList<Libro> libri) {
        this.repositoryLibri = repositoryLibri;
        this.libri = libri;
    }

    @Override
    public void execute() {
        repositoryLibri.save(libri);
        System.out.println("Libro aggiunto con successo.");
    }
}
