
import java.util.Scanner;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;



public class Driver {
    public static void main(String[] args) {
        VendingMachine vendingMachine = null;
        Scanner scanner = new Scanner(System.in);
        char choice='0', choice1='0';
        int vendingChoice = -1;
        Money payment;
        while (choice!='3') {
            printOpenMenu();
            choice = scanner.next().charAt(0);
            switch (choice) {
                case '1':
                    vendingMachine = new VendingMachine();
                    System.out.println("Vending Machine Created\n\n\n");
                    break;
                case '2':
                    while(choice!='3'){
                        printTestMenu();
                        choice1 = scanner.next().charAt(0);
                        switch (choice1) {
                            case '1':
                                if(vendingMachine!=null){
                                    printVendingMachine(vendingMachine);
                                    vendingChoice = scanner.nextInt();
                                    payment = new Money();
                                    if(payment(scanner, vendingMachine.getSlots().get(vendingChoice-1).getPrice(), payment)){
                                        if(vendingMachine.transact(payment, vendingMachine.getSlots().get(vendingChoice-1).getPrice()).getTotal()!=payment.getTotal()){
                                            System.out.printf("%s Dispensed\n", vendingMachine.dispenseItem(vendingMachine.getSlots().get(vendingChoice-1)).getName());
                                        }
                                    }
                                }
                                
                                break;
                            case '2':
                                break;
                            case '3':
                                break;
                        }
                    }
                case '3':
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }

    public static void printOpenMenu(){
        System.out.println("====================================");
        System.out.println("         VENDING MACHINE SIM");
        System.out.println("====================================");
        System.out.println("1 - Create Vending Machine");
        System.out.println("2 - Test Vending Machine");
        System.out.println("3 - Exit");
        System.out.println("------------------------------------");
        System.out.print("Your choice: ");
    }

    public static void printTestMenu(){
        System.out.println("====================================");
        System.out.println("         TEST VENDING MACHINE");
        System.out.println("====================================");
        System.out.println("1 - Vending Features");
        System.out.println("2 - Maintenance");
        System.out.println("3 - Back to Main Menu");
        System.out.println("------------------------------------");
        System.out.print("Your choice: ");
    }

    public static void printVendingMachine(VendingMachine vendingMachine){
        System.out.println("====================================");
        System.out.println("         YOUR VENDING MACHINE");
        System.out.println("====================================");
        for(int i=0; i<vendingMachine.getSlots().size();i++){
            System.out.println((i+1) + " - " +  vendingMachine.getSlots().get(i).getItemType().getName() + " (" + vendingMachine.getSlots().get(i).getItemType().getCALORIES()+ " kcal) " + " - " + vendingMachine.getSlots().get(i).getCount() + " left.");
        }
        System.out.println("------------------------------------");
        System.out.print("Your choice: ");
    }

    public static boolean payment(Scanner scanner, float price, Money payment){
        boolean canBuy = false;
        System.out.println("====================================");
        System.out.println("Enter Bills: ");
        System.out.print("1000: ");
        payment.setThousand(scanner.nextInt());
        System.out.print("500: ");
        payment.setFiveH(scanner.nextInt());
        System.out.print("100: ");
        payment.setHund(scanner.nextInt());
        System.out.print("50: ");
        payment.setFifty(scanner.nextInt());
        System.out.print("20: ");
        payment.setTwenty(scanner.nextInt());
        System.out.print("10: ");
        payment.setTen(scanner.nextInt());
        System.out.print("5: ");
        payment.setFive(scanner.nextInt());

        if(payment.getTotal()>=price){
            canBuy = true;
        }
        return canBuy;
    }
}
