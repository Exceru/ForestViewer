package forest;

/**
 * Class for the contents of a single field.
 */
public class Field {
    /**
     * The type is stored as an enum and is later used to determine the visibility.
     */
    private Fieldtype type;

    /**
     * @param type Sets the type of the field.
     */
    public Field(Fieldtype type) {
        this.type = type;
    }

    /**
     * @return the type of the field.
     */
    public Fieldtype getType() {
        return type;
    }

    /**
     * @param type type of the field.
     */
    public void setType(Fieldtype type) {
        this.type = type;
    }
}
