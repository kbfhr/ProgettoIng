package Command;

public class RemoveCommand implements Command {
    private final RepositoryLibri repositoryLibri;
    private final int isbn;

    public RemoveCommand(RepositoryLibri l,int isbn) {
        this.repositoryLibri = l;
        this.isbn = isbn;
    }

    @Override
    public void execute() {
        repositoryLibri.remove(isbn);

    }
}
