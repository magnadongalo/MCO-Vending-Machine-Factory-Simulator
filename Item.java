/**
 * Represents an item that can be sold by the vending machine.
 * <p>
 * An Item has a constant name and calorie count. Once created,
 * neither value can be changed.
 * @author Gutierrez, Jonathan Jr
 * @author Maullon, Edriel Lexine
 */
public class Item {
    /** The name of the item */
    private final String NAME;
    /** The calorie count of the item (kcal) */
    private final float CALORIES;

    /**
     * Constructs a new Item with the given name and calorie count.
     * The default calorie count is 10.0f if the input value is 
     * negative.
     * 
     * @param name the name of the item
     * @param calories the calorie count of the item
     */
    public Item(String name, float calories) {
        NAME = name;

        if (calories <= 0)
            CALORIES = 10.0f;
        else
            CALORIES = calories;
    }

    /**
     * Returns the name of this item.
     *
     * @return the item's name
     */
    public String getNAME() {
        return NAME;
    }

    /**
     * Returns the calorie count of this item.
     *
     * @return the item's calorie count
     */
    public float getCALORIES() {
        return CALORIES;
    }
}