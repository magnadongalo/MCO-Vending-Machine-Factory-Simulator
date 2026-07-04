import java.util.ArrayList;

public class Slot {
    private ArrayList<Item> itemType;
    private int count;
    private final int slotID;
    private float price;

    public Slot(int count, int slotID, float price) {
        itemType = new ArrayList<>();

        if (count < 10)
            this.count = 10;
        else
            this.count = count;

        this.slotID = slotID;

        if (price <= 0.00f)
            this.price = 100.00f;
        else
            this.price = price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Item dispense() {

    }
}
