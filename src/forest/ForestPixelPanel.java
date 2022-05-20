package forest;

import javax.swing.*;
import java.awt.*;

public class ForestPixelPanel extends JPanel {
    //private static final int pixelSize = 35;
    private Color color;

    public ForestPixelPanel(Color color, int pixelSize) {
        this.color = color;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(pixelSize, pixelSize));
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(getColor());
        g.fillRect(0, 0, getWidth(), getHeight());
    }


}