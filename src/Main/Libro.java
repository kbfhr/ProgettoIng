package Main;

public class Libro {
    String titolo;
    String autore;
    int isbn;
    Genere genere;
    int valutazione;
    Stato stato;

    public Libro(String titolo, String autore, int isbn, Genere genere, int valutazione, Stato stato) {
        this.titolo = titolo;
        this.autore = autore;
        this.isbn = isbn;
        this.genere = genere;
        this.valutazione = valutazione;
        this.stato = stato;
    }
    public static enum Genere{
        fantasy,
        horror,
        avventura,
        romanzo,
        giallo,
        storico,
        fantascienza,
        autobiografia,
        altro
    }
    public static enum Stato{
        letto,
        da_leggere,
        in_lettura
    }

    public String getTitolo() {
        return titolo;
    }
    public String getAutore() {
        return autore;
    }
    public int getIsbn() {return isbn;}
    public String getGenere() {return genere.toString();}
    public int getValutazione() {return valutazione;}
    public String getStato() {return stato.toString();}

    public void setTitolo(String titolo) { this.titolo = titolo; }
    public void setAutore(String autore) { this.autore = autore; }
    public void setIsbn(int isbn) { this.isbn = isbn; }
    public void setGenere(Genere genere) { this.genere = genere; }
    public void setValutazione(int valutazione) { this.valutazione = valutazione; }
    public void setStato(Stato stato) { this.stato = stato; }


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
