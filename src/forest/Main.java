package forest;

import vector.Vector2i;
import vector.VectorMath;

/**
 * Main class of the application.
 */
public class Main {

    /**
     * Main method of the application. Used to execute the forester Interface.
     * @param args Program arguments.
     */
    public static void main(String[] args) {
        Vector2i fieldSize = new Vector2i(20,20);
        Vector2i randomForester = VectorMath.getRandomVector2i(new Vector2i(0,0),
                VectorMath.sub(fieldSize, new Vector2i(1,1))); // We have to subtract 1 because (20, 20) would exceed the Forest

        ForesterInterface foresterInterface = new ForesterInterface(randomForester, fieldSize);

        foresterInterface.showForestersView();
        foresterInterface.showBestSpotsInForest();
    }
}