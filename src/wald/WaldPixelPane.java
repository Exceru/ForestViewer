package wald;

import javax.swing.*;
import java.awt.*;

public class WaldPixelPane extends JPanel {

    //private static final long serialVersionUID = 8465814529701152253L;
    private static final int pixelSize = 50;
    private Color farbe;

    public WaldPixelPane(Color farbe) {
        this.farbe = farbe;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(pixelSize, pixelSize));
    }

    public Color getFarbe() {
        return farbe;
    }

    public void setFarbe(Color farbe) {
        this.farbe = farbe;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(getFarbe());
        g.fillRect(0, 0, getWidth(), getHeight());
    }


}