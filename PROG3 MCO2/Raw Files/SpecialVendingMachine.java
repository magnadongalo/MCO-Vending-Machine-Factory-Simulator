import java.util.ArrayList;

public class SpecialVendingMachine extends VendingMachine {

     /** Array containing customer's order */
    private ArrayList<Slot> customerOrder;

     /** Creates a vending machine, initializes customer order */
    public SpecialVendingMachine(){
        super();
        customerOrder = new ArrayList<Slot>();
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
                System.out.println(slot.getItemType().getName() + " is out of stock.");
            }
        } else {
            System.out.println(slot.getItemType().getName() + "No more ice cups in stock");
        }
        
    }

     /**
     * Clears the order
     */
    public void clearOrder(){
        customerOrder.clear();
    }

    public void printIngredient(Item item){
        if(item.getName().equals("Ice Cup")) {
            System.err.println("Placing Ice Cup.");          
        } else if (item.getName().equals("Milk")){
            System.err.println("Pouring Milk.");  
        } else if (item.getName().equals("Assorted Jelly")){
            System.err.println("Adding Gulaman, Jelly, and Nata de Coco!");  
        } else if (item.getName().equals("Sweetened Banana")){
            System.err.println("Dispensing Sweetened Banana.");  
        } else if (item.getName().equals("Sweet Corn")){
            System.err.println("Dispensing Sweet Corn.");  
        } else if (item.getName().equals("Ube Halaya")){
            System.err.println("Pumping out the Ube Halaya!");  
        } else if (item.getName().equals("Pinipig")){
            System.err.println("Adding Pinipig toppings.");  
        } else if (item.getName().equals("Macapuno")){
            System.err.println("Adding Macapuno Jelly.");  
        }
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
        int totalCal = 0;
        ArrayList<Item> customRecipe = new ArrayList<Item>();
        if(super.getSlots().get(0).getCount()>0){
            customRecipe.add(super.getSlots().get(0).dispense());
            for(int i = 0; i<customerOrder.size();i++){
                customRecipe.add(customerOrder.get(i).dispense());
            }
            System.out.println();
            for(Item item : customRecipe){
                totalCal += item.getCALORIES();
                
                printIngredient(item);
            }
            product = new Item("Custom Order", totalCal);
        }
        customerOrder.clear();
        return product;
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
            System.out.printf("%d - (PHP %.2f) - %-32s - %d left\n", i+1, this.getSlots().get(i).getPrice(), this.getSlots().get(i).getItemType().getName() + " (" + this.getSlots().get(i).getItemType().getCALORIES()+ " kcal)", this.getSlots().get(i).getCount());
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
            System.out.printf("%d - (PHP %.2f) - %-32s - %d left\n", i, this.getSlots().get(i).getPrice(), this.getSlots().get(i).getItemType().getName() + " (" + this.getSlots().get(i).getItemType().getCALORIES()+ " kcal)", this.getSlots().get(i).getCount());
            num = i + 1;
        }
        System.out.println(num + " - Finish Order");
        System.out.println(num + 1 + " - Back to Vending Slots");
        System.out.println("------------------------------------"); 
        if (customerOrder.size()>0) {
            System.out.println("Your Order:");
            for(Slot slot : customerOrder){
                System.out.println(slot.getItemType().getName());
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