import java.util.ArrayList;

public class Slot {
    private ArrayList<Item> items;
    private int count;
    private final int slotID;
    private float price;

    public Slot(int count, int slotID, float price, Item item) {
        int i;
        items = new ArrayList<>();

        if (count < 10)
            this.count = 10;
        else
            this.count = count;

        this.slotID = slotID;

        if (price <= 0.00f)
            this.price = 100.00f;
        else
            this.price = price;

        for(i=0;i<count;i++){
           this.items.add(item);
        }
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Item dispense() {
        Item item = null;
        if (count>0) {
            item = this.items.get(this.items.size()-1);
            this.items.remove(this.items.size()-1);
        }
        return item;
    }

    public int getID(){
        return this.slotID;
    }

    public float getPrice(){
        return this.price;
    }
}
