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
    private listaLibriGUI libriGUI;

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
        libriGUI = new listaLibriGUI(libriCaricati);

        System.out.println(gestionLibreria.getLibri());

        this.booksTable = new JTable(libriGUI); // Usa this.booksTable invece di creare una variabile locale

        // Configurazioni tabella
        this.booksTable.setAutoCreateRowSorter(true);
        this.booksTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(this.booksTable);
        scrollPane.setPreferredSize(new Dimension(1100, 700));

        // Aggiungi al pannello principale
        mainPanel.add(scrollPane, BorderLayout.CENTER);



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
                    System.out.println(selectedFile.getAbsolutePath());
                    gestionLibreria.aggiungiObserver(libriGUI);
                    libriCaricati = gestionLibreria.getLibri();
                    System.out.println(libriCaricati);
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

        // Aggiungi le opzioni di modifica
        JMenuItem aggiorna = new JMenuItem("Modifica Libro");
        aggiorna.addActionListener(e -> gestionLibreria.aggiornaLibro());
        editMenu.add(aggiorna);
        JMenuItem rimuovi = new JMenuItem("Rimuovi Libro");
        rimuovi.addActionListener(e -> gestionLibreria.rimuoviLibro());
        editMenu.add(rimuovi);

        // Crea il menu "Filtri"
        JMenu filterMenu = new JMenu("Filtri");
        JMenu genereMenu = new JMenu("Per Genere");

        for (Libro.Genere g : Libro.Genere.values()) {
            JCheckBoxMenuItem item = new JCheckBoxMenuItem(g.name().toLowerCase());
            //JMenuItem item = new JMenuItem(g.name().toLowerCase());
            item.addActionListener(e -> {
                if (item.isSelected()) {
                    gestionLibreria.filtroPerGenere(g.name());
                    gestionLibreria.applicaFiltri();
                } else {
                    gestionLibreria.rimuoviFiltro(g.name());

                }

            });
            genereMenu.add(item);
        }

        // Filtri per Stato
        JMenu statoMenu = new JMenu("Per Stato");
        for (Libro.Stato s : Libro.Stato.values()) {
            String statoName = s.name().toLowerCase().replace("_", " ");
            JCheckBoxMenuItem item = new JCheckBoxMenuItem(statoName);
            //JMenuItem item = new JMenuItem(statoName);
            item.addActionListener(e -> {
                if( item.isSelected()) {

                    gestionLibreria.filtraPerStato(s.name());
                    gestionLibreria.applicaFiltri();
                } else {
                    gestionLibreria.rimuoviFiltro(s.name());

                }

            });
            statoMenu.add(item);
        }

        // Pulisci filtri
        JMenuItem clearItem = new JMenuItem("Pulisci filtri");
        clearItem.addActionListener(e -> {
            gestionLibreria.rimuoviTuttiFiltri();
            resetAllFilterIcons(genereMenu,statoMenu);
        });
        filterMenu.add(genereMenu);
        filterMenu.add(statoMenu);
        filterMenu.addSeparator();
        filterMenu.add(clearItem);
        menuBar.add(filterMenu);
        return menuBar;
    }

    private void resetAllFilterIcons(JMenu genereMenu, JMenu statoMenu) {
        // Resetta le icone del menu Generi
        for (Component comp : genereMenu.getMenuComponents()) {
            if (comp instanceof JCheckBoxMenuItem) {
                JCheckBoxMenuItem item = (JCheckBoxMenuItem) comp;
                item.setSelected(false);
            }
        }

        // Resetta le icone del menu Stati
        for (Component comp : statoMenu.getMenuComponents()) {
            if (comp instanceof JCheckBoxMenuItem) {
                JCheckBoxMenuItem item = (JCheckBoxMenuItem) comp;
                item.setSelected(false);
            }
        }
    }
}

