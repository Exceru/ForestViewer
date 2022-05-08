package wald;

import javax.swing.*;
import java.awt.*;

public class PixelPanel extends JPanel {

    //private static final long serialVersionUID = 8465814529701152253L;
    private static final int PIXEL_SIZE = 50;
    private Color backgroundColor;

    public PixelPanel(Color color) {
        this.backgroundColor = color;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(PIXEL_SIZE, PIXEL_SIZE));
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(getBackgroundColor());
        g.fillRect(0, 0, getWidth(), getHeight());
    }


}