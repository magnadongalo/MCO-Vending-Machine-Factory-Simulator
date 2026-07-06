import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private ArrayList<Slot> slots;
    private Money balance;

    public VendingMachine() {
        balance = new Money();

        //Vending machine itself will not store 1k bills
        balance.setFiveH(50);
        balance.setHund(125);
        balance.setFifty(125);

        balance.setTwenty(50);
        balance.setTen(50);
        balance.setFive(50);
        balance.setOne(50);

        //FOR TESTING PURPOSES
//        balance.setFiveH(0);
//        balance.setHund(0);
//        balance.setFifty(0);
//
//        balance.setTwenty(0);
//        balance.setTen(0);
//        balance.setFive(0);
//        balance.setOne(0);
    }

    public void dispenseItem() {

    }

    public Money transact(Money payment, float price) {
        float res = payment.calculateTotal() - price;
        float[] arr = {500f, 100f, 50f, 20f, 10f, 5f, 1f};
        Money change = new Money();
        boolean canTransact = true, exit = false;

        System.out.printf("Received payment: Php %.2f\n", payment.calculateTotal());
        System.out.printf("Deducted price: Php %.2f\n", price);

        //if there is change
        if (res > 0) {
            for (float f : arr) {
                while (res >= f && !exit) {
                    switch ((int) f) {
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
                        case 500: change.setFiveH(change.getFiveH() + 1);
                            break;
                        case 100: change.setHund(change.getHund() + 1);
                            break;
                        case 50: change.setFifty(change.getFifty() + 1);
                            break;
                        case 20: change.setTwenty(change.getTwenty() + 1);
                            break;
                        case 10: change.setTen(change.getTen() + 1);
                            break;
                        case 5: change.setFive(change.getFive() + 1);
                            break;
                        case 1: change.setOne(change.getOne() + 1);
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
            System.out.printf("Change Dispensed: Php %.2f\n", change.calculateTotal());
            if (change.calculateTotal() != 0)
            {
                for (float f : arr) {
                    switch ((int) f) {
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

    public void restock() {

    }

    public void replenish() {

    }

    public void printSummary() {

    }

    public void setPrice() {

    }
}
