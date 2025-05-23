package Command;

public class CaricaCommand implements Command {
    private final RepositoryLibri repositoryLibri;

    public CaricaCommand(RepositoryLibri repositoryLibri) {
        this.repositoryLibri = repositoryLibri;
    }
    @Override
    public void execute() {
        repositoryLibri.getAll();
    }
}
