import java.util.ArrayList;

/**
 * Represents a single slot in a vending machine that holds stock of a
 * particular Item, along with its price and remaining count.
 * @author Gutierrez, Jonathan Jr
 * @author Maullon, Edriel Lexine
 */
public class Slot {
    /** The type of item currently stocked in this slot. */
    private Item itemType;
    /** The list of items currently available in this slot. */
    private ArrayList<Item> items;
    /** The current number of items remaining in this slot. */
    private int count;
    /** The number of items the slot held at the start of the current stocking period. */
    private int startingCount;
    /** The price of the item stocked in this slot. */
    private float price;
    /** The number of times the item was sold. */
    private int sold = 0;

    /**
     * Constructs a new Slot with the given initial count, price,
     * and item type. If the count is less than 10, the slot is initialized with a
     * default count of 10. If the price is zero or negative, a default
     * price of 100.00 is used instead.
     *
     * @param count the initial number of items to stock in the slot
     * @param price the price of the item; must be greater than 0, otherwise 
     * a default of 100.00 is assigned
     * @param item the type of item to stock in this slot
     */
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

        for(i=0;i<this.count;i++){
           items.add(itemType);
        }
    }

    /**
     * Sets the price of the item stocked in this slot.
     *
     * @param price the new price for this slot
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Dispenses one item from this slot, removing it from the internal
     * stock and decrementing the item count.
     *
     * @return the dispensed item, or @code null if the slot is empty
     */
    public Item dispense() {
        Item item = null;
        if (count>0) {
            item = items.get(this.items.size()-1);
            items.remove(this.items.size()-1);
            count--;
            sold++;
        }
        return item;
    }

    /**
     * Returns the price of the item stocked in this slot.
     *
     * @return the slot's price
     */
    public float getPrice(){
        return price;
    }

    /**
     * Returns the type of item currently stocked in this slot.
     *
     * @return the slot's item type
     */
    public Item getItemType(){
        return itemType;
    }

    /**
     * Attempts to change the type of item stocked in this slot. The 
     * change only succeeds if the new item differs from the current
     * item type (by both calorie count and name) and the slot is 
     * currently empty.
     *
     * @param item the new item type to assign to this slot
     * @return true if the item type was successfully changed,
     * false otherwise
     */
    public boolean setItemType(Item item){
        boolean success = false;
        if(item.getCALORIES()!=this.itemType.getCALORIES() && !(item.getNAME().equals(this.itemType.getNAME()))){
            if(items.size()==0){
                itemType = item;
                success = true;
            }
        }
        return success;
    }
    
    /**
     * Adds one item of this slot's current item type to the stock and
     * increments the item count.
     */
    public void addItem(){
        items.add(itemType);
        count++;
    }

    /**
     * Returns the number of items this slot held at the start of the
     * current stocking period.
     *
     * @return the starting item count
     */
    public int getStartingCount(){
        return startingCount;
    }

    /**
     * Returns the current number of items remaining in this slot.
     *
     * @return the current item count
     */
    public int getCount(){
        return count;
    }

    /**
     * Returns the current amount of times the item was sold.
     *
     * @return the times the item was sold
     */
    public int getSold (){
        return sold;
    }

    /**
     * Sets the starting item count for this slot.
     *
     * @param startingCount the new starting count value
     */
    public void setStartingCount(int startingCount){
        this.startingCount=startingCount;
    }
}
