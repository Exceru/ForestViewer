package forest;

import javax.swing.*;
import java.awt.*;

/**
 * A class to display single Field tiles in a ForestDisplay.
 */
public class ForestPixelPanel extends JPanel {
    /**
     * The color of the Field to be displayed in the ForestDisplay.
     */
    private final Color color;

    /**
     * Constructor for the ForestPixelPanel which sets the color and size of the pixel.
     * @param color Color of the Panel.
     * @param pixelSize Size of the Panel in absolute pixels.
     */
    public ForestPixelPanel(Color color, int pixelSize) {
        this.color = color;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(pixelSize, pixelSize));
    }

    /**
     * Gets the color of the panel.
     * @return Color of the panel.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Used to set the appearance of the panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(getColor());
        g.fillRect(0, 0, getWidth(), getHeight());
    }


}