package Command;

public class UpdateCommand implements Command {
    private final RepositoryLibri repositoryLibri;


    public UpdateCommand(RepositoryLibri repositoryLibri) {
        this.repositoryLibri = repositoryLibri;
    }

    @Override
    public void execute() {
        try {
            repositoryLibri.update();
        } catch (Exception e) {
            System.out.println("Errore durante l'aggiornamento del libro: " + e.getMessage());
        }
    }
}
