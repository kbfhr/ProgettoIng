import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JFrame frame;

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.initialize();
    }

    public void initialize() {
        frame = new JFrame();
        frame.setLayout(new BorderLayout(10,10));
        frame.setTitle("Progetto ing software");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(Color.LIGHT_GRAY);
        Button b = new Button("pollo");
        b.setPreferredSize(new Dimension(50, 25));
        panel.add(b);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

}