import java.util.ArrayList;

public class Slot {
    private Item itemType;
    private ArrayList<Item> items;
    private int count;
    private int startingCount;
    private float price;

    public Slot(int count, float price, Item item) {
        int i;
        itemType = item;
        items = new ArrayList<Item>();

        if (count < 10)
            this.count = 10;
        else
            this.count = count;

        startingCount = this.count;

        if (price <= 0.00f)
            this.price = 100.00f;
        else
            this.price = price;

        for(i=0;i<count;i++){
           items.add(itemType);
        }
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Item dispense() {
        Item item = null;
        if (count>0) {
            item = items.get(this.items.size()-1);
            items.remove(this.items.size()-1);
            count--;
        }
        return item;
    }

    public float getPrice(){
        return price;
    }

    public Item getItemType(){
        return itemType;
    }

    public boolean setItemType(Item item){
        boolean success = false;
        if(item.getCALORIES()!=this.itemType.getCALORIES() && !(item.getName().equals(this.itemType.getName()))){
            if(items.size()==0){
                itemType = item;
                success = true;
            }
        }
        return success;
    }

    public void addItem(){
        items.add(itemType);
        count++;
    }

    public int getStartingCount(){
        return startingCount;
    }

    public int getCount(){
        return count;
    }

    public void setStartingCount(int startingCount){
        this.startingCount=startingCount;
    }
}
