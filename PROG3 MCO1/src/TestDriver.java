public class TestDriver {
    public static void main(String[] args) {
        Item test = new Item("Ube Halaya", 95);
        Slot test1 = new Slot(10, 20.00f, test);

        System.out.println(test1.dispense() == null);
    }
}
