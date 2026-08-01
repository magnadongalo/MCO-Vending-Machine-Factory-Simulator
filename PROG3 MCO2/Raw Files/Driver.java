
import java.util.Scanner;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/*
 *@author Gutierrez, Jonathan Jr
 *@author Maullon, Edriel Lexine
 */
public class Driver {
    public static void main(String[] args) {
        VendingMachine vendingMachine = null;
        SpecialVendingMachine specialVendingMachine = null;
        Scanner scanner = new Scanner(System.in);
        char choice='0', choice1, choice2;
        String input;
        String[] stringArr;
        int vendingChoice = -1, vendingChoice1 = -1;
        Money payment;
        boolean isValid = false;
        while (choice!='3') {
            printOpenMenu();
            choice = scanner.next().charAt(0);
            switch (choice) {
                case '0':
                    vendingMachine = new VendingMachine();;
                    System.out.println("Vending Machine Created");
                    break;
                case '1':
                    vendingMachine = new SpecialVendingMachine();
                    System.out.println("Special Vending Machine Created");
                    break;
                case '2':
                    if(vendingMachine!=null) {
                        choice1 = ' ';
                        //Set empty ulit in case someone reenters menu 2
                        while(choice1!='3'){
                            vendingMachine.printTestMenu();
                            choice1 = scanner.next().charAt(0);
                            switch (choice1) {
                                case '1':
                                    do{
                                        vendingMachine.printVendingMachine();
                                        if(scanner.hasNextInt()){
                                            vendingChoice = scanner.nextInt();
                                            isValid = vendingMachine.isValidSlot(vendingChoice);
                                            if (!isValid)
                                                System.out.println("Please enter a valid Option: ");
                                        } else {
                                            scanner.next();
                                            System.out.println("Please enter a valid Option: ");
                                        }
                                    }while(!isValid);
                                    if(vendingChoice==(vendingMachine.getSlots().size())+ 2 && vendingMachine instanceof SpecialVendingMachine){
                                        specialVendingMachine= (SpecialVendingMachine) vendingMachine;
                                        do{
                                            specialVendingMachine.printCustomOrder();
                                            if(scanner.hasNextInt()){
                                                vendingChoice = scanner.nextInt();
                                                isValid = specialVendingMachine.isValidOrder(vendingChoice);
                                                if (!isValid)
                                                    System.out.println("Please enter a valid Option: ");
                                            } else {
                                                scanner.next();
                                                System.out.println("Please enter a valid Option: ");
                                            }
                                            if (isValid && vendingChoice != specialVendingMachine.getSlots().size() && vendingChoice != specialVendingMachine.getSlots().size()+1) 
                                                specialVendingMachine.addOrder(specialVendingMachine.getSlots().get(vendingChoice));
                                        }while(!isValid || (vendingChoice != specialVendingMachine.getSlots().size() && vendingChoice != specialVendingMachine.getSlots().size()+1));
                                        if(vendingChoice==specialVendingMachine.getSlots().size()){
                                            payment = new Money();
                                            if(payment(scanner, specialVendingMachine.calculatePrice(), payment)){
                                                if(specialVendingMachine.transact(payment, specialVendingMachine.calculatePrice()).getTotal()!=payment.getTotal()){
                                                    System.out.printf("%s Dispensed\n", specialVendingMachine.createProduct().getName());
                                                }
                                            }    
                                        }else{
                                            specialVendingMachine.clearOrder();
                                        }
                                        vendingMachine = specialVendingMachine;
                                    } else if(vendingChoice!=(vendingMachine.getSlots().size())+1){
                                        payment = new Money();
                                        if(payment(scanner, vendingMachine.getSlots().get(vendingChoice-1).getPrice(), payment)){
                                            if(vendingMachine.transact(payment, vendingMachine.getSlots().get(vendingChoice-1).getPrice()).getTotal()!=payment.getTotal()){
                                                System.out.printf("%s Dispensed\n", vendingMachine.dispenseItem(vendingMachine.getSlots().get(vendingChoice-1)).getName());
                                            }
                                        }
                                    }
                                    break;
                                case '2':
                                    choice2 = '0';

                                    while (choice2!='4') {
                                        vendingMachine.printMaintenance();
                                        choice2 = scanner.next().charAt(0);

                                        switch (choice2) {
                                        case '1':
                                            System.out.println("RESTOCKING");
                                            System.out.print("Slot Number: ");
                                            do{
                                                if(scanner.hasNextInt()){
                                                    vendingChoice = scanner.nextInt();
                                                    if (!vendingMachine.isValidSlot(vendingChoice))
                                                        System.out.print("Please enter a valid Option: ");
                                                } else {
                                                    scanner.next();
                                                    System.out.print("Please enter a valid Option: ");
                                                }
                                            }while(!vendingMachine.isValidSlot(vendingChoice));
                                            System.out.print("Amount: ");
                                            do{
                                                vendingChoice1 = scanner.nextInt();
                                                if (vendingChoice1<=0)
                                                    System.out.println("Please enter a number above 0: ");
                                            }while (vendingChoice1<=0);

                                            vendingMachine.restock(vendingMachine.getSlots().get(vendingChoice-1), vendingChoice1);
                                            System.out.println(vendingMachine.getSlots().get(vendingChoice-1).getItemType().getName() + " restocked!");
                                            break;
                                        case '2':
                                            scanner.nextLine(); //send \n to oblivion!!!
                                            System.out.println("""
                                                    Select denominations to be replenished.
                                                    Separate inputs by space if you want to
                                                    replenish multiple denominations.
                                                    
                                                    Valid choices:
                                                    1000, 500, 100, 50, 20, 10, 5, 1, ALL
                                                    """);

                                            System.out.print("\nInput Here: ");
                                            input = scanner.nextLine();
                                            System.out.println("------------------------------------");

                                            if (input.equals("ALL") || input.equals("all")) {
                                                vendingMachine.replenish();
                                            } else {
                                                stringArr = input.split(" ");

                                                for (String s : stringArr) {
                                                    if (s.equals("1000") || s.equals("500") || s.equals("100")
                                                            || s.equals("50") || s.equals("20") || s.equals("10")
                                                            || s.equals("5") || s.equals("1"))
                                                        vendingMachine.replenish(Float.parseFloat(s));
                                                }
                                                //If they have invalid input, the function will not be called
                                            }
                                            break;
                                        case '3':
                                            vendingMachine.printSummary();
                                            break;
                                        case '4':
                                            System.out.println("Returning to Vending Machine menu...");
                                            break;
                                        default:
                                            System.out.println("Invalid input!");
                                            break;
                                        }
                                    }
                                    break;
                                case '3':
                                    System.out.println("Returning to Main Menu...");
                                    break;
                                default:
                                    System.out.println("Invalid input!");
                            }
                        }
                    } else
                        System.out.println("Please create a vending machine first!");
                    break;
                case '3':
                    System.out.println("Exiting program...");
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }

    public static void printOpenMenu(){
        System.out.println("\n\n====================================");
        System.out.println("         VENDING MACHINE SIM");
        System.out.println("====================================");
        System.out.println("0 - Create Vending Machine");
        System.out.println("1 - Create Special Vending Machine");
        System.out.println("2 - Test Vending Machine");
        System.out.println("3 - Exit");
        System.out.println("------------------------------------");
        System.out.print("Your choice: ");
    }



    public static boolean payment(Scanner scanner, float price, Money payment){
        boolean canBuy = false;
        System.out.println("====================================");
        System.out.println("Enter Bills: ");
        System.out.print("1000: ");
        payment.setThousand(scanner.nextInt());
        System.out.print("500 : ");
        payment.setFiveH(scanner.nextInt());
        System.out.print("100 : ");
        payment.setHund(scanner.nextInt());
        System.out.print("50  : ");
        payment.setFifty(scanner.nextInt());
        System.out.print("20  : ");
        payment.setTwenty(scanner.nextInt());
        System.out.print("10  : ");
        payment.setTen(scanner.nextInt());
        System.out.print("5   : ");
        payment.setFive(scanner.nextInt());

        if(payment.getTotal()>=price){
            canBuy = true;
        }
        return canBuy;
    }
}
