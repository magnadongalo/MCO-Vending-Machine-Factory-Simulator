import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Simulates a vending machine that stocks items in Slots, accepts
 * payments, dispenses items and change, and tracks its cash balance.
 * It has an additional feature in which the user can combine and
 * dispense a combination of items.
 *
 * @author Gutierrez, Jonathan Jr
 * @author Maullon, Edriel Lexine
 */
public class SpecialVendingMachine extends VendingMachine {

     /** Array containing customer's order */
    private ArrayList<Slot> customerOrder;

    /** Array containing the messages when ingredients are added.
     * Utilized in the program's GUI. */
    private ArrayList<String> ingredientMessages;

     /** Creates a vending machine, initializes customer order */
    public SpecialVendingMachine(){
        super();
        customerOrder = new ArrayList<Slot>();
        ingredientMessages = new ArrayList<String>();
    }

    /**
     * Adds an order to the customerOrder ArrayList
     * 
     * @param slot the slot that will dispense the item ordered
     */
    public void addOrder(Slot slot){
        int count = 0;
        int currStock;
        for(Slot s : customerOrder){
            if (s.getItemType().equals(slot.getItemType()))
                count++;
        }
        currStock = slot.getCount() - count;
        if (super.getSlots().get(0).getCount()>0) {
            if (currStock>0) {
                customerOrder.add(slot);
            } else {
                System.out.println(slot.getItemType().getNAME() + " is out of stock.");
            }
        } else {
            System.out.println(slot.getItemType().getNAME() + "No more ice cups in stock");
        }
        
    }

     /**
     * Clears the order
     */
    public void clearOrder(){
        customerOrder.clear();
    }

    public String printIngredient(Item item, boolean printOnGui){
        String message = "";
        if (printOnGui) {
            if (item.getNAME().equals("Ice Cup")) {
                message = "Placing Ice Cup.";
            } else if (item.getNAME().equals("Milk")){
                message = "Pouring Milk.";
            } else if (item.getNAME().equals("Assorted Jelly")){
                message = "Adding Gulaman, Jelly, and Nata de Coco!";
            } else if (item.getNAME().equals("Sweetened Banana")){
                message = "Dispensing Sweetened Banana.";
            } else if (item.getNAME().equals("Sweet Corn")){
                message = "Dispensing Sweet Corn.";
            } else if (item.getNAME().equals("Ube Halaya")){
                message = "Pumping out the Ube Halaya!";
            } else if (item.getNAME().equals("Pinipig")){
                message = "Adding Pinipig toppings.";
            } else if (item.getNAME().equals("Macapuno")){
                message = "Adding Macapuno Jelly.";
            }
        }
        else {
            if(item.getNAME().equals("Ice Cup")) {
                System.err.println("Placing Ice Cup.");
            } else if (item.getNAME().equals("Milk")){
                System.err.println("Pouring Milk.");
            } else if (item.getNAME().equals("Assorted Jelly")){
                System.err.println("Adding Gulaman, Jelly, and Nata de Coco!");
            } else if (item.getNAME().equals("Sweetened Banana")){
                System.err.println("Dispensing Sweetened Banana.");
            } else if (item.getNAME().equals("Sweet Corn")){
                System.err.println("Dispensing Sweet Corn.");
            } else if (item.getNAME().equals("Ube Halaya")){
                System.err.println("Pumping out the Ube Halaya!");
            } else if (item.getNAME().equals("Pinipig")){
                System.err.println("Adding Pinipig toppings.");
            } else if (item.getNAME().equals("Macapuno")){
                System.err.println("Adding Macapuno Jelly.");
            }
        }
        return message;
    }

     /**
     * Sums up the total price of the customer's order
     * 
     * @return the price of the customer's order
     */
    public float calculatePrice(){
        float f = 0.00f;
        for(Slot slot : customerOrder){
            f += slot.getPrice();
        }
        return f;
    }

    /**
     * Creates an item that is the customized product
     * based on the customer's order.
     * 
     * @return the item created
     */
    public Item createProduct(){
        Item product = null;
        float totalCal = 0;
        ArrayList<Item> customRecipe = new ArrayList<Item>();
        if(super.getSlots().get(0).getCount()>0){
            customRecipe.add(super.getSlots().get(0).dispense());
            for(int i = 0; i<customerOrder.size();i++){
                customRecipe.add(customerOrder.get(i).dispense());
            }
            System.out.println();
            for(Item item : customRecipe){
                totalCal += item.getCALORIES();
                
                printIngredient(item, false);
                ingredientMessages.add(printIngredient(item, true));
            }
            product = new Item("Custom Order", totalCal);
        }
        customerOrder.clear();
        return product;
    }

    /**
     * Gets the string of ingredient messages to be printed.
     * @return an ArrayList containing the string of messages.
     */
    public ArrayList<String> getIngredientMessages() {
        return ingredientMessages;
    }

    /**
     * Clears the arraylist of ingredient messages.
     */
    public void clearIngredientMessages() {
        ingredientMessages.clear();
    }

    /**
     * Is the Vending Machine interface.
     */
    public void printVendingMachine(){
        int num = 0;
        System.out.println("\n\n====================================");
        System.out.println("         YOUR VENDING MACHINE");
        System.out.println("====================================");
        for(int i=0; i<this.getSlots().size();i++){
            System.out.printf("%d - (PHP %.2f) - %-32s - %d left\n", i+1, this.getSlots().get(i).getPrice(), this.getSlots().get(i).getItemType().getNAME() + " (" + this.getSlots().get(i).getItemType().getCALORIES()+ " kcal)", this.getSlots().get(i).getCount());
            num = i + 2;
        }
        System.out.println(num + " - Back to Main Menu");
        System.out.println(num+1 + " - Custom Drink Builder");
        System.out.println("------------------------------------");
        System.out.print("Your choice: ");
    }

    /**
     * Is the Custom Order interface.
     */
    public void printCustomOrder(){
        int num = 0;
        System.out.println("\n\n====================================");
        System.out.println("Add Ingredients to Your Custom Order");
        System.out.println("====================================");
        for(int i=1; i<this.getSlots().size();i++){
            System.out.printf("%d - (PHP %.2f) - %-32s - %d left\n", i, this.getSlots().get(i).getPrice(), this.getSlots().get(i).getItemType().getNAME() + " (" + this.getSlots().get(i).getItemType().getCALORIES()+ " kcal)", this.getSlots().get(i).getCount());
            num = i + 1;
        }
        System.out.println(num + " - Finish Order");
        System.out.println(num + 1 + " - Back to Vending Slots");
        System.out.println("------------------------------------"); 
        if (customerOrder.size()>0) {
            System.out.println("Your Order:");
            for(Slot slot : customerOrder){
                System.out.println(slot.getItemType().getNAME());
            }
            System.out.println("------------------------------------");
        }   
        System.out.print("Your choice: ");
    }

    /**
     * Tests if the user input is a valid option for customizing orders
     *
     * @return true if it is valid, false otherwise.
     */
    public boolean isValidOrder(int choice){
        boolean isValid = false;
        for(int i=1;i<(this.getSlots().size()-1);i++){
            if (i==choice)
                isValid = true;
        }
    if(choice==(super.getSlots().size())||choice==(super.getSlots().size())+1)
        isValid = true;
    return isValid;
    }

    /**
     * Returns the ArrayList containing the customer's order in this
     * Vending Machine.
     *
     * @return the slot's price
     */
    public ArrayList<Slot> getCustomerOrder(){
        return this.customerOrder;
    }
}