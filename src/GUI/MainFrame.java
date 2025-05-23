package GUI;

import Main.Libro;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import LibreriaSingleton.GestionLibreria;

public class MainFrame extends JFrame {
    private JTable booksTable;
    private JTextField searchField;
    private GestionLibreria gestionLibreria;
    private ArrayList<Libro> libriCaricati;
    private File selectedFile;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new MainFrame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MainFrame() {
        this.gestionLibreria = GestionLibreria.getInstance();
        creaGUI();
    }

    private void creaGUI() {
        JFrame frame = new JFrame("Gestione Libri - Java");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        libriCaricati = new ArrayList<>();

        // Crea il pannello principale
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Aggiunge la barra dei menu
        mainPanel.add(createMenuBar(frame), BorderLayout.NORTH);
/*
        // Aggiunge la barra degli strumenti
        mainPanel.add(createToolBar(), BorderLayout.NORTH);

        // Crea il pannello centrale con tabella e sidebar
        JSplitPane centerPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        centerPanel.setLeftComponent(createSidebar());
        centerPanel.setRightComponent(createBookTable());
        centerPanel.setDividerLocation(200);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Aggiunge il pannello inferiore
        mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);*/

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JMenuBar createMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        // Crea il menu "File"
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Carica da JSON");
        saveItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            // Filtro per file JSON
            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.getName().toLowerCase().endsWith(".json") || f.isDirectory();
                }

                @Override
                public String getDescription() {
                    return "File JSON (*.json)";
                }
            });

            int returnValue = fileChooser.showOpenDialog(frame);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(frame, "File selezionato: " + selectedFile.getAbsolutePath());

            }
            if (selectedFile != null) {
                try{
                    gestionLibreria.caricaLibri(selectedFile.getAbsolutePath());
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(frame, "Errore durante il caricamento del file: " + ex.getMessage());
                }
            }
        });

        fileMenu.add(saveItem);
        menuBar.add(fileMenu);

        // Crea il menu "Modifica"
        JMenu editMenu = new JMenu("Modifica");
        JMenuItem addBookItem = new JMenuItem("Aggiungi Libro");
        addBookItem.addActionListener(e -> gestionLibreria.aggiungiLibro());
        editMenu.add(addBookItem);
        menuBar.add(editMenu);

        return menuBar;
    }
}

