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
        Item item1 = new Item("Ice Cup", 0);
        Item item2 = new Item("Milk", 100);
        Item item3 = new Item("Assorted Jelly", 150);
        Item item4 = new Item("Sweetened Banana", 150);
        Item item5 = new Item("Sweet Corn", 85);
        Item item6 = new Item("Ube Halaya", 95);
        Item item7 = new Item("Pinipig", 90);
        Item item8 = new Item("Macapuno", 130);
        listOfItems.add(item1);
        listOfItems.add(item2);
        listOfItems.add(item3);
        listOfItems.add(item4);
        listOfItems.add(item5);
        listOfItems.add(item6);
        listOfItems.add(item7);
        listOfItems.add(item8);

        float prices[] = {10f,25f, 15f, 30f,30f, 20f, 25f, 40f};

        balance = new Money();
        
        balance.setThousand(25);
        balance.setFiveH(50);
        balance.setHund(125);
        balance.setFifty(125);
        balance.setTwenty(50);
        balance.setTen(50);
        balance.setFive(50);
        balance.setOne(50);
        startingBalance = new Money();
        startingBalance.setThousand(balance.getThousand());
        startingBalance.setFiveH(balance.getFiveH());
        startingBalance.setHund(balance.getHund());
        startingBalance.setFifty(balance.getFifty());
        startingBalance.setTwenty(balance.getTwenty());
        startingBalance.setTen(balance.getTen());
        startingBalance.setFive(balance.getFive());
        startingBalance.setOne(balance.getOne());
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
                Slot slot = new Slot(10, prices[i], listOfItems.get(i));
                slots.add(slot);
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
                exit = false;
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
                            break;
                        case 500: 
                            change.setFiveH(change.getFiveH() + 1);
                            break;
                        case 100: 
                            change.setHund(change.getHund() + 1);
                            break;
                        case 50:
                            change.setFifty(change.getFifty() + 1);
                            break;
                        case 20: 
                            change.setTwenty(change.getTwenty() + 1);
                            break;
                        case 10: 
                            change.setTen(change.getTen() + 1);
                            break;
                        case 5: 
                            change.setFive(change.getFive() + 1);
                            break;
                        case 1: 
                            change.setOne(change.getOne() + 1);
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
                    if (balance.getThousand() <= 0)
                        exit = true;
                    balance.setThousand(balance.getThousand() + payment.getThousand());
                    break;
                case 500:
                    if (balance.getFiveH() <= 0)
                        exit = true;
                    balance.setFiveH(balance.getFiveH() + payment.getFiveH());
                    break;
                case 100:
                    if (balance.getHund() <= 0)
                        exit = true;
                    balance.setHund(balance.getHund() + payment.getHund());
                    break;
                case 50:
                    if (balance.getFifty() <= 0)
                        exit = true; 
                    balance.setFifty(balance.getFifty() + payment.getFifty());
                    break;
                case 20:
                    if (balance.getTwenty() <= 0)
                        exit = true;
                    balance.setTwenty(balance.getTwenty() + payment.getTwenty());
                    break;
                case 10:
                    if (balance.getTen() <= 0)
                        exit = true;
                    balance.setTen(balance.getTen() + payment.getTen());
                    break;
                case 5:
                    if (balance.getFive() <= 0)
                        exit = true;
                    balance.setFive(balance.getFive() + payment.getFive());
                    break;
                case 1:
                    if (balance.getOne() <= 0)
                        exit = true;
                    balance.setOne(balance.getOne() + payment.getOne());
                    break;
                    }  
            }
            System.out.printf("Change Dispensed: Php %.2f\n", change.calculateTotal());
            if (change.calculateTotal() != 0)
            {
                for (float f : arr) {
                    switch ((int) f) {
                        case 1000:
                            if (change.getThousand() > 0){
                                balance.setThousand(balance.getThousand() - change.getThousand());
                                System.out.println("1000: " + change.getThousand());
                            }
                            break;
                        case 500:
                            if (change.getFiveH() > 0){
                                balance.setFiveH(balance.getFiveH() - change.getFiveH());
                                System.out.println("500: " + change.getFiveH());
                            }
                            break;
                        case 100:
                            if (change.getHund() > 0){
                                balance.setHund(balance.getHund() - change.getHund());
                                System.out.println("100: " + change.getHund());
                            }
                                
                            break;
                        case 50:
                            if (change.getFifty() > 0){
                                balance.setFifty(balance.getFifty() - change.getFifty());
                                System.out.println("50 : " + change.getFifty());
                            }
                            break;
                        case 20:
                            if (change.getTwenty() > 0){
                                balance.setTwenty(balance.getTwenty() - change.getTwenty());
                                System.out.println("20 : " + change.getTwenty());
                            }
                            break;
                        case 10:
                            if (change.getTen() > 0){
                                balance.setTen(balance.getTen() - change.getTen());
                                System.out.println("10 : " + change.getTen());
                            }
                            break;
                        case 5:
                            if (change.getFive() > 0){
                                balance.setFive(balance.getFive() - change.getFive());
                                System.out.println("5  : " + change.getFive());
                            }
                            break;
                        case 1:
                            if (change.getOne() > 0){
                                balance.setOne(balance.getOne() - change.getOne());
                                System.out.println("1  : " + change.getOne());
                            }
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
        startingBalance.setThousand(balance.getThousand());
        startingBalance.setFiveH(balance.getFiveH());
        startingBalance.setHund(balance.getHund());
        startingBalance.setFifty(balance.getFifty());
        startingBalance.setTwenty(balance.getTwenty());
        startingBalance.setTen(balance.getTen());
        startingBalance.setFive(balance.getFive());
        startingBalance.setOne(balance.getOne());
        slot.setStartingCount(slot.getCount());
    }

    public void replenish() {
        balance.setThousand(25);
        balance.setFiveH(50);
        balance.setHund(125);
        balance.setFifty(125);
        balance.setTwenty(50);
        balance.setTen(50);
        balance.setFive(50);
        balance.setOne(50);
    }

    public void printSummary() {
        System.out.println("====================================");
        System.out.println("TRANSACTION SUMMARY:");
        System.out.println("Total amount collected: " + (balance.calculateTotal()-startingBalance.calculateTotal()));
        System.out.println("ITEMS SOLD: ");
        for(int i = 0; i<slots.size();i++){
            System.out.printf("%-20s %d sold\n", slots.get(i).getItemType().getName() + ":", slots.get(i).getStartingCount()-slots.get(i).getCount());
        }
        System.out.println("====================================");
    }

    public void setPrice(Slot slot, float price) {
        slot.setPrice(price);
    }

    public ArrayList<Slot> getSlots(){
        return slots;
    }
}
