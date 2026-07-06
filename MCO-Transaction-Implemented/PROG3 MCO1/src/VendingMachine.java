import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private ArrayList<Slot> slots;
    private Money balance;
    private Money startingBalance;

    public VendingMachine(){
        ArrayList<Item> listOfItems = new ArrayList<>();
        slots = new ArrayList<>();
        //placeholder
        Item item1 = new Item("Item1", 1);
        Item item2 = new Item("Item2", 1);
        Item item3 = new Item("Item3", 1);
        Item item4 = new Item("Item4", 1);
        Item item5 = new Item("Item5", 1);
        Item item6 = new Item("Item6", 1);
        Item item7 = new Item("Item7", 1);
        Item item8 = new Item("Item8", 1);
        listOfItems.add(item1);
        listOfItems.add(item2);
        listOfItems.add(item3);
        listOfItems.add(item4);
        listOfItems.add(item5);
        listOfItems.add(item6);
        listOfItems.add(item7);
        listOfItems.add(item8);

        float prices[] = {10f,20f,30f,40f,50f,60f,70f,80f};

        this.balance = new Money();
        
        this.balance.setThousand(25);
        this.balance.setFiveH(50);
        this.balance.setHund(125);
        this.balance.setFifty(125);
        this.balance.setTwenty(50);
        this.balance.setTen(50);
        this.balance.setFive(50);
        this.balance.setOne(50);
        this.startingBalance = this.balance;
        //FOR TESTING PURPOSES
//        balance.setFiveH(0);
//        balance.setHund(0);
//        balance.setFifty(0);
//
//        balance.setTwenty(0);
//        balance.setTen(0);
//        balance.setFive(0);
//        balance.setOne(0);

        for(int i=0;i<8;i++){
                Slot slot = new Slot(10, i, prices[i], listOfItems.get(i));
                this.slots.add(slot);
        }

    }

    public Item dispenseItem(Slot slot) {
        Item item = slot.dispense();
        return item;
    }

    public Money transact(Money payment, float price) {
        float res = payment.calculateTotal() - price;
        float[] arr = {1000f, 500f, 100f, 50f, 20f, 10f, 5f, 1f};
        Money change = new Money();
        boolean canTransact = true, exit = false;

        System.out.printf("Received payment: Php %.2f\n", payment.calculateTotal());
        System.out.printf("Deducted price: Php %.2f\n", price);

        //if there is change
        if (res > 0) {
            for (float f : arr) {
                while (res >= f && !exit) {
                    switch ((int) f) {
                    case 1000:
                        if (balance.getThousand() <=0)
                            exit = true;
                        break;
                    case 500:
                        if (balance.getFiveH() <= 0)
                            exit = true;
                        break;
                    case 100:
                        if (balance.getHund() <= 0)
                            exit = true;
                        break;
                    case 50:
                        if (balance.getFifty() <= 0)
                            exit = true;
                        break;
                    case 20:
                        if (balance.getTwenty() <= 0)
                            exit = true;
                        break;
                    case 10:
                        if (balance.getTen() <= 0)
                            exit = true;
                        break;
                    case 5:
                        if (balance.getFive() <= 0)
                            exit = true;
                        break;
                    case 1:
                        if (balance.getOne() <= 0)
                            exit = true;
                        break;
                    }

                    if (!exit) {
                        res -= f;

                        switch ((int) f) {
                        case 1000:
                            change.setThousand(change.getThousand() + 1);
                            this.balance.setThousand(this.balance.getThousand() - 1);
                            break;
                        case 500: 
                            change.setFiveH(change.getFiveH() + 1);
                            this.balance.setFiveH(this.balance.getFiveH() - 1);
                            break;
                        case 100: 
                            change.setHund(change.getHund() + 1);
                            this.balance.setHund(this.balance.getHund() - 1);
                            break;
                        case 50:
                            change.setFifty(change.getFifty() + 1);
                            this.balance.setFifty(this.balance.getFifty() - 1);
                            break;
                        case 20: 
                            change.setTwenty(change.getTwenty() + 1);
                            this.balance.setTwenty(this.balance.getTwenty() - 1);
                            break;
                        case 10: 
                            change.setTen(change.getTen() + 1);
                            this.balance.setTen(this.balance.getTen() - 1);
                            break;
                        case 5: 
                            change.setFive(change.getFive() + 1);
                            this.balance.setFive(this.balance.getFive() - 1);
                            break;
                        case 1: 
                            change.setOne(change.getOne() + 1);
                            this.balance.setOne(this.balance.getOne() - 1);
                            break;
                        }
                    }
                }
            }

            if (res != 0f) {
                System.out.println("Cannot dispense change! Transaction cancelled!");
                canTransact = false;
            }
        }

        if (canTransact)
        {
            for(float f : arr){
                switch ((int) f) {
                case 1000:
                    if (payment.getThousand() > 0)
                        exit = true;
                case 500:
                    if (balance.getFiveH() <= 0)
                        exit = true;
                    break;
                case 100:
                    if (balance.getHund() <= 0)
                        exit = true;
                    break;
                case 50:
                    if (balance.getFifty() <= 0)
                        exit = true;
                    break;
                case 20:
                    if (balance.getTwenty() <= 0)
                        exit = true;
                    break;
                case 10:
                    if (balance.getTen() <= 0)
                        exit = true;
                    break;
                case 5:
                    if (balance.getFive() <= 0)
                        exit = true;
                    break;
                case 1:
                    if (balance.getOne() <= 0)
                        exit = true;
                    break;
                    }  
            }
            System.out.printf("Change Dispensed: Php %.2f\n", change.calculateTotal());
            if (change.calculateTotal() != 0)
            {
                for (float f : arr) {
                    switch ((int) f) {
                        case 1000:
                            if (change.getThousand() > 0)
                                System.out.println("1000: " + change.getThousand());
                            break;
                        case 500:
                            if (change.getFiveH() > 0)
                                System.out.println("500: " + change.getFiveH());
                            break;
                        case 100:
                            if (change.getHund() > 0)
                                System.out.println("100: " + change.getHund());
                            break;
                        case 50:
                            if (change.getFifty() > 0)
                                System.out.println("50 : " + change.getFifty());
                            break;
                        case 20:
                            if (change.getTwenty() > 0)
                                System.out.println("20 : " + change.getTwenty());
                            break;
                        case 10:
                            if (change.getTen() > 0)
                                System.out.println("10 : " + change.getTen());
                            break;
                        case 5:
                            if (change.getFive() > 0)
                                System.out.println("5  : " + change.getFive());
                            break;
                        case 1:
                            if (change.getOne() > 0)
                                System.out.println("1  : " + change.getOne());
                            break;
                    }
                }
            }
            return change;
        }
        else
            return payment; //Isukli mo yung buong bayad LMFAO
    }

    public void restock(Slot slot, int numItems) {
        int i;
        for(i=0;i<numItems;i++){
            slot.addItem();
        }
        this.startingBalance=this.balance;
    }

    public void replenish() {
        this.balance.setThousand(25);
        this.balance.setFiveH(50);
        this.balance.setHund(125);
        this.balance.setFifty(125);
        this.balance.setTwenty(50);
        this.balance.setTen(50);
        this.balance.setFive(50);
        this.balance.setOne(50);
    }

    public void printSummary() {
        System.out.println("TRANSACTION SUMMARY:");
        System.out.println("Total amount collected: " + (this.balance.calculateTotal()-this.startingBalance.calculateTotal()));
        System.out.println("ITEMS SOLD: ");
        for(int i = 0; i<this.slots.size();i++){
            System.out.println(this.slots.get(i).getItemType().getName() + ": " + (this.slots.get(i).getStartingCount()-this.slots.get(i).getCount()) + " Sold");
        }
    }

    public void setPrice(Slot slot, float price) {
        slot.setPrice(price);
    }

    public ArrayList<Slot> getSlots(){
        return slots;
    }
}
