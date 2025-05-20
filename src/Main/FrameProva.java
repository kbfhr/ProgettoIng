package Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FrameProva extends JFrame {
    private JFrame frame;
    private JTable table;
    private JTextField searchTextField;


    public void initialize() {
        frame = new JFrame();
        frame.setLayout(new BorderLayout(10,10));
        frame.setTitle("Progetto ing software");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    private void initComp() {
        table = createbookTable();
        searchTextField = new JTextField(30);
    }

    private JTable createbookTable() {
        String[] Attributi = {"Titolo", "Autore", "ISBN", "Genere", "Valutazione", "Stato"};
        DefaultTableModel model = new DefaultTableModel(Attributi, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return String.class; // Titolo
                    case 1:
                        return String.class; // Autore
                    case 2:
                        return String.class; // ISBN
                    case 3:
                        return String.class; // Genere
                    case 4:
                        return Integer.class; // Valutazione
                    case 5:
                        return String.class; // Stato
                    default:
                        return Object.class;

                }
            }
        };
        /*for (Book b : books) {
            Object[] row = new Object[6];
            row[0] = b.getTitolo();
            row[1] = b.getAutore();
            row[2] = b.getIsbn();
            row[3] = b.getGenere();
            row[4] = b.getValutazione();
            row[5] = b.getStato();
            model.addRow(row);
        }*/
        JTable table = new JTable(model);

    return null;}
}
