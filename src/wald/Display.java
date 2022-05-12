package wald;

import javax.swing.*;
import java.awt.*;


public class Display implements Runnable {

    private JFrame frame;
    private int[][] grid;

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
        int width = grid.length;
        int height = grid[0].length;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(height, width, 0, 0));

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                PixelPanel pixelPanel;

                switch(grid[row][column]) {
                    case 0:
                        pixelPanel = new PixelPanel(Color.GREEN);
                        break;
                    case 1:
                        pixelPanel = new PixelPanel(Color.BLACK);
                        break;
                    case 2:
                        pixelPanel = new PixelPanel(Color.RED);
                        break;
                    default:
                        pixelPanel = new PixelPanel(Color.WHITE);
                        break;

                }

                //pixelPanel.addMouseListener(new ColorListener(pixelPanel));
                panel.add(pixelPanel);
            }
        }

        return panel;
    }
    public void setGrid(int[][] grid) {
        this.grid = grid;
    }


}
