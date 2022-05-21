package forest;

import vector.Vector2i;

import javax.swing.*;
import java.awt.*;


/**
 * A class to visualize a forest given a forest.
 */
public class ForestDisplay implements Runnable {

    /**
     * The title can be changed by the {@link ForestDisplay#setTitle(String)}
     * method before executing {@link ForestDisplay#run()}
     */
    private String title;
    private Forest forest;

    /**
     * Constructor of ForestDisplay which sets the forest to visualize.
     * @param forest The forest to visualize.
     */
    public ForestDisplay(Forest forest){
        this.forest = forest;
    }

    /**
     * Sets the forest which will be visualized.
     * @param forest The forest to visualize.
     */
    public void setForest(Forest forest) {
        this.forest = forest;
    }

    /**
     * Used to start the visualization.
     * Overrides the run() method of the Runnable interface.
     */
    @Override
    public void run() {
        initGUI();
    }

    /**
     * Initializes the Graphical User Interface by creating the pixels, setting the window title and window behavior.
     */
    private void initGUI() {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(createPixels());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Produces a JPanel which contains a number of ForestPixelPanes given by the {@link ForestDisplay#forest}.
     * Will create a ForestPixelPanel for every Field in the Forest. This is later used by {@link ForestDisplay#initGUI()}.
     * @return A panel containing ForestPixelPanes
     */
    private JPanel createPixels() {
        int x = forest.getSize().getX();
        int y = forest.getSize().getY();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(y, x, 0, 0));

        /*
         For each Field in the forest, we will create a new ForestPixelPane and determine the color
         of the ForestPixelPane by the Fieldtype inside the Field.
        */
        for (int row = 0; row < y; row++) {
            for (int column = 0; column < x; column++) {
                int pixelSize = 550 / x; /* Size of window divided by the amount of pixels equals pixel size. */

                /* We will differentiate based on the FieldType enum inside the Field. */
                ForestPixelPanel forestPixelPane = switch (forest.getFeld(new Vector2i(column, row)).getType()) {
                    case TREE -> new ForestPixelPanel(new Color(52, 255, 52), pixelSize);
                    case HIDDENTREE -> new ForestPixelPanel(new Color(0, 86, 0), pixelSize);
                    case FORESTER -> new ForestPixelPanel(Color.RED, pixelSize);
                    case MISCELLANEOUS -> new ForestPixelPanel(new Color(117, 117, 117), pixelSize);
                };

                panel.add(forestPixelPane);
            }
        }

        return panel;
    }

    /**
     * Changes the title of the window. Must be set before executing {@link ForestDisplay#run()}.
     * @param title The text to be displayed in the window bar.
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
