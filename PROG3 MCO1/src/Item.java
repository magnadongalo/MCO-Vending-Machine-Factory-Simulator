public class Item {
    private final String name;
    private final float calories;

    public Item(String name, float calories) {
        this.name = name;

        if (calories <= 0)
            this.calories = 10.0f;
        else
            this.calories = calories;
    }

    public String getName() {
        return this.name;
    }

    public float getCalories() {
        return calories;
    }
}
