package GUI;

import javax.swing.*;
import java.awt.*;

public class IconaPuntino implements Icon {
    private final Color color;
    private final int size;
    public IconaPuntino(Color color, int size) {
        this.color = color;
        this.size = size;
    }
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    @Override
    public int getIconHeight() {
        return size;
    }
}
