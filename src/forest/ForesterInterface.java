package forest;

import vector.Vector2d;
import vector.Vector2i;
import vector.VectorMath;

import java.util.*;

/**
 * A class to perform various actions on a Forest regarding visibility.
 * Can be used to find the best spots for visibility.
 */
public class ForesterInterface {

    /**
     * Constructor for the ForesterInterface. Will perform all relevant actions.
     * @param foresterPosition Position of the forester.
     * @param forestSize The size of the forest.
     */
    public ForesterInterface(Vector2i foresterPosition, Vector2i forestSize) {
        Forest forest = new Forest(forestSize);
        ForestDisplay forestDisplay = new ForestDisplay(forest);

        /* Calculate the maximum visible trees from the foresters position:
        * - Outputs the number of visible trees to the console
        * - Shows a windows of the visibility matrix */
        forest.setFeld(foresterPosition, new Field(Fieldtype.FORESTER));
        showMaxVisibleTreesForPosition(foresterPosition, forest);
        forestDisplay.setTitle("Visible trees from " + foresterPosition);
        forestDisplay.run();

        /* Get the number of hidden trees and subtract that from the number of fields in the forest.
        Also subtract minus because of the forester. */
        int numberOfVisibleTrees = getNumberVisibleTrees(forest);
        System.out.println("The number of visible trees from the foresters point of " + foresterPosition + " is: "
                + numberOfVisibleTrees + "\n");


        /* Now we will calculate the best possible spots given the forest size. */
        List<Map.Entry<Vector2i, Integer>> spots = getBestLocations(forestSize);
        List<Vector2i> topSpots = new ArrayList<>();

        for(Map.Entry<Vector2i, Integer> entry : spots) {
            /* See if the current entry has the same value as the best spot.*/
            if(entry.getValue().intValue() == spots.get(0).getValue().intValue()) {
                // Add to best-list if it is one of the best spots
                topSpots.add(entry.getKey());
            }
        }


        /* Display all top spots in a Forest. */
        System.out.println("There are " + topSpots.size() + " top spots.");
        System.out.println("A maximum of " + spots.get(0).getValue() + " trees can be seen at the best locations.");
        System.out.println("Only " + spots.get(spots.size() - 1).getValue() + " can be seen at the worst locations.\n");

        System.out.println("The best spots are at:              (x,y) → (0,0) being top-left & (max, max) being bottom-right");

        /* Visualize the top spots in a new window and color them differently. */
        Forest topForest = new Forest(forestSize);
        for(Vector2i location : topSpots) {
            topForest.setFeld(location, new Field(Fieldtype.MISCELLANEOUS));
            /* Print out their locations as well. */
            System.out.println(location);
        }

        forestDisplay.setForest(topForest);
        forestDisplay.setTitle("Best Possible Spots in the Forest");
        forestDisplay.run();
    }

    /**
     * Will calculate every hidden and visible tree of the forest.
     * @param position Position of the forester.
     */
    private void showMaxVisibleTreesForPosition(Vector2i position, Forest forest) {
        for (int row = 0; row < forest.getSize().getY(); row++) {
            for (int column = 0; column < forest.getSize().getX(); column++) {
                calculateHiddenTrees(forest, position, new Vector2i(column, row));
            }
        }
    }

    /**
     * Will count all the visible trees inside the forest.
     * @return The number of visible trees inside the forest.
     */
    private int getNumberVisibleTrees(Forest forest){
        int counter = 0;

        for(int y = 0; y < forest.getSize().getY(); y++) {
            for(int x = 0; x < forest.getSize().getX(); x++) {
                if(forest.getFeld(new Vector2i(x,y)).getType() == Fieldtype.TREE) {
                    counter++;
                }
            }
        }

        return counter;
    }

    /**
     * Will output a sorted list of entries in the form of (Location, number of visible trees).
     * @param forestSize The forest size to perform the action with.
     * @return Sorted List of descending Entries in the format of (Location,number of visible trees)
     */
    private List<Map.Entry<Vector2i, Integer>> getBestLocations(Vector2i forestSize) {
        List<Map.Entry<Vector2i, Integer>> locations = new ArrayList<>();

        for(int row = 0; row < forestSize.getY(); row++) {
            for(int column = 0; column < forestSize.getX(); column++) {
                Forest newForest = new Forest(forestSize);
                Vector2i foresterPos = new Vector2i(column, row);

                // Set the location of the forester to the forester field type.
                newForest.setFeld(foresterPos, new Field(Fieldtype.FORESTER));

                // Calculate all trees that are visible or hidden for that location.
                showMaxVisibleTreesForPosition(foresterPos, newForest);

                // Count all the visible trees.
                int numberOfVisibleTrees = getNumberVisibleTrees(newForest);

                // Add the location of the forester with the amount of visible trees to the list of entries.
                Map.Entry<Vector2i, Integer> location = new AbstractMap.SimpleEntry<>(foresterPos, numberOfVisibleTrees);
                locations.add(location);
            }
        }

        /* Sort the list by the highest number of visible trees. */
        locations.sort(Map.Entry.comparingByValue());
        Collections.reverse(locations);

        return locations;
    }


    /**
     * Calculates the hidden trees of a Forest given a forester position. Every tree after the first one that is hit directly
     * in the middle of the pixel by the line of sight will become hidden.
     * @param grid The forest to perform the operation on.
     * @param start The forester's location to view from.
     * @param end The location the forester is looking at.
     */
    private void calculateHiddenTrees(Forest grid, Vector2i start, Vector2i end){
        Vector2d newPos;
        Vector2d interpPos;

        // Calculate difference between end and start vector
        Vector2i delta = VectorMath.sub(end, start);

        // Find the longest line. We will step along the maximum distance
        int diagonal = Math.max(Math.abs(delta.getX()), Math.abs(delta.getY()));

        // Used to count when to start marking the Field as hidden trees
        int marked = 0;

        /*
         We will step along the direct line of the forester to the given field using linear interpolation.
         If the interpolated position is exactly in the middle of a tree, the tree will be regarded as seen by the forester.
        */
        for (int i = 1; i <= diagonal; i++) {

            /* Calculate the delta of the interpolation given the distance/steps along the line. */
            double t = (double) i / (double) diagonal;
            interpPos = VectorMath.lerp(VectorMath.convertTo2d(start), VectorMath.convertTo2d(end), t);

            /* We will round the interpolated Position and consider it as a potential new position. */
            newPos = VectorMath.round(interpPos);

            /* If the interpolated position is the exact same as the rounded position,
            then we are directly in the middle of the pixel. */
            if (interpPos.equals(newPos)) {

                /* If we haven't encountered any tree yet, we will ignore the first as it is visible to us. */
                if(marked == 0) {
                    marked = 1;
                } else {
                    /* If we have already encountered at least one tree,
                    the rest of the encountered tree will be hidden behind the first tree. */
                    marked += 1;

                    /* Mark the Field as HIDDENTREE. */
                    grid.setFeld(new Vector2i((int) newPos.getX(), (int) newPos.getY()), new Field(Fieldtype.HIDDENTREE));
                }
            }


        }
    }

}
