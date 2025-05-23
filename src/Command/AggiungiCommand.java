package Command;

import Main.Libro;

import java.util.ArrayList;

public class AggiungiCommand implements Command {
    private final RepositoryLibri repositoryLibri;


    public AggiungiCommand(RepositoryLibri repositoryLibri) {
        this.repositoryLibri = repositoryLibri;
    }

    @Override
    public void execute() {
        repositoryLibri.save();
        System.out.println("Libro aggiunto con successo.");
    }
}
