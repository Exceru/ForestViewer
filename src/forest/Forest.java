package forest;

import vector.Vector2i;

import java.util.ArrayList;
import java.util.List;

/**
 * A class which stores a 2d List of fields, which is useful to perform visibility operations on.
 */
public class Forest {
    private final List<List<Field>> fields;
    private final Vector2i size;

    /**
     * Constructor for the forest. Will create a forest by a given size.
     * @param size Determines the size of the forest.
     */
    public Forest(Vector2i size) {
        this.fields = new ArrayList<>();

        this.size = size;

        /* Creates a 2d List with the dimensions given by the size vector parameter. */
        for(int y = 0; y < this.size.getY(); y++){
            ArrayList<Field> horizontaleListe = new ArrayList<>();
            for (int x = 0; x < this.size.getX(); x++) {
                horizontaleListe.add(new Field(Fieldtype.TREE));
            }
            this.fields.add(horizontaleListe);
        }
    }

    /**
     * Returns the field at a given location in the forest.
     * @param location A vector which provides the location in the forest.
     * @return The field which is at that location.
     */
    public Field getFeld(Vector2i location) {
        return this.fields.get(location.getY()).get(location.getX());
    }

    /**
     * Will change the field at the given location to the given field.
     * @param location A vector which provides the location in the forest.
     * @param field The field to change the field at the given location to.
     */
    public void setFeld(Vector2i location, Field field) {
        List<Field> row = this.fields.get(location.getY());
        row.set(location.getX(), field);

        this.fields.set(location.getY(), row);
    }

    /**
     * Will return the size of the forest.
     * @return Vector of type integer with the size of the forest.
     */
    public Vector2i getSize(){
        return this.size;
    }
}
