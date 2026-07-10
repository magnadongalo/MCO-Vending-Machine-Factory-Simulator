public class Item {
    private final String NAME;
    private final float CALORIES;

    public Item(String name, float calories) {
        NAME = name;

        if (calories <= 0)
            CALORIES = 10.0f;
        else
            CALORIES = calories;
    }

    public String getName() {
        return NAME;
    }

    public float getCALORIES() {
        return CALORIES;
    }
}
