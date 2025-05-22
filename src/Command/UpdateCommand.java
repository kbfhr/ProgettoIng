package Command;

public class UpdateCommand implements Command {
    private final RepositoryLibri repositoryLibri;
    private final int isbn;
    private final Object attributo;
    private final Object valore;

    public UpdateCommand(RepositoryLibri repositoryLibri, int isbn, Object attributo, Object valore) {
        this.repositoryLibri = repositoryLibri;
        this.isbn = isbn;
        this.attributo = attributo;
        this.valore = valore;
    }

    @Override
    public void execute() {
        try {
            repositoryLibri.update(isbn, attributo,valore);
            System.out.println("Libro aggiornato con successo.");
        } catch (Exception e) {
            System.out.println("Errore durante l'aggiornamento del libro: " + e.getMessage());
        }
    }
}
