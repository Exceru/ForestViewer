package wald;

import javax.swing.*;
import java.awt.*;


public class WaldDisplay implements Runnable {

    private JFrame frame;
    private Wald wald;

    public void setWald(Wald wald) {
        this.wald = wald;
    }

    @Override
    public void run() {
        initGUI();
    }

    public void initGUI() {
        frame = new JFrame("Pixel Art");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(createPixels());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createPixels() {
        int x = wald.getSizeX();
        int y = wald.getSizeY();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(y, x, 0, 0));

        for (int row = 0; row < y; row++) {
            for (int column = 0; column < x; column++) {
                WaldPixelPane waldPixelPane = switch (wald.getFeld(row, column).getTyp()) {
                    case BAUM -> new WaldPixelPane(Color.GREEN);
                    case VERDECKTERBAUM -> new WaldPixelPane(Color.BLACK);
                    case FOERSTER -> new WaldPixelPane(Color.RED);
                };

                panel.add(waldPixelPane);
            }
        }

        return panel;
    }

}
