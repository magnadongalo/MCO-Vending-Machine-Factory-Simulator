public class Item {
    private final String NAME;
    private final float CALORIES;

    public Item(String name, float calories) {
        this.NAME = name;

        if (calories <= 0)
            this.CALORIES = 10.0f;
        else
            this.CALORIES = calories;
    }

    public String getName() {
        return this.NAME;
    }

    public float getCALORIES() {
        return CALORIES;
    }
}
