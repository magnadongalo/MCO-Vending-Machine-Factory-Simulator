public class Driver {
    public static void main(String[] args) {
        VendingMachine test = new VendingMachine();
        Money payment = new Money();
        payment.setFiveH(1);

        test.transact(payment, 125.00f);

    }
}
