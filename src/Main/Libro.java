package Main;

public class Libro {
    String titolo;
    String autore;
    int isbn;
    String genere;
    int valutazione;
    String stato;

    public Libro(String titolo, String autore, int isbn, String genere, int valutazione, String stato) {
        this.titolo = titolo;
        this.autore = autore;
        this.isbn = isbn;
        this.genere = genere;
        this.valutazione = valutazione;
        this.stato = stato;
    }
    public String getTitolo() {
        return titolo;
    }
    public String getAutore() {
        return autore;
    }
    public int getIsbn() {return isbn;}
    public String getGenere() {return genere;}
    public int getValutazione() {return valutazione;}
    public String getStato() {return stato;}

    public void setTitolo(String titolo) { this.titolo = titolo; }
    public void setAutore(String autore) { this.autore = autore; }
    public void setIsbn(int isbn) { this.isbn = isbn; }
    public void setGenere(String genere) { this.genere = genere; }
    public void setValutazione(int valutazione) { this.valutazione = valutazione; }
    public void setStato(String stato) { this.stato = stato; }


    public String toString() {
        return
                "titolo='" + titolo + '\'' +
                ", autore='" + autore + '\'' +
                ", isbn='" + isbn + '\'' +
                ", genere='" + genere + '\'' +
                ", valutazione=" + valutazione +
                ", stato='" + stato + '\'' +
                '}';
    }
}
