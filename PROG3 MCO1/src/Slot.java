import java.util.ArrayList;

public class Slot {
    private Item itemType;
    private ArrayList<Item> items;
    private int count;
    private int startingCount;
    private final int slotID;
    private float price;

    public Slot(int count, int slotID, float price, Item item) {
        int i;
        this.itemType = item;
        items = new ArrayList<Item>();

        if (count < 10)
            this.count = 10;
        else
            this.count = count;

        this.startingCount = this.count;

        this.slotID = slotID;

        if (price <= 0.00f)
            this.price = 100.00f;
        else
            this.price = price;

        for(i=0;i<count;i++){
           this.items.add(itemType);
        }
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Item dispense() {
        Item item = null;
        if (this.count>0) {
            item = this.items.get(this.items.size()-1);
            this.items.remove(this.items.size()-1);
            this.count--;
        }
        return item;
    }

    public int getID(){
        return this.slotID;
    }

    public float getPrice(){
        return this.price;
    }

    public Item getItemType(){
        return this.itemType;
    }

    public boolean setItemType(Item item){
        boolean success = false;
        if(item.getCALORIES()!=this.itemType.getCALORIES() && !(item.getName().equals(this.itemType.getName()))){
            if(this.items.isEmpty()) {
                this.itemType = item;
                success = true;
            }
        }
        return success;
    }

    public void addItem(){
        this.items.add(itemType);
        this.count++;
    }

    public int getStartingCount(){
        return this.startingCount;
    }

    public int getCount(){
        return this.count;
    }

    public void setStartingCount(int startingCount){
        this.startingCount=startingCount;
    }
}
