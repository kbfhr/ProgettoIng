package Command;

public class RemoveCommand implements Command {
    private final RepositoryLibri repositoryLibri;


    public RemoveCommand(RepositoryLibri l) {
        this.repositoryLibri = l;

    }

    @Override
    public void execute() {
        repositoryLibri.remove();

    }
}
