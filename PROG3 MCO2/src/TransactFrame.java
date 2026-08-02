import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This is the JFrame that displays the options for the vending machine's
 * transactions.
 * @author Gutierrez, Jonathan Jr
 * @author Maullon, Edriel Lexine
 */
public class TransactFrame extends JFrame implements ActionListener {
    /** The primary JPanel that stores all the GUI elements. Exists for the use of BoxLayout.*/
    private JPanel masterPanel;
    /** The auxiliary JPanel that contains the JLabel for the window. */
    private JPanel subPanel;
    /** The auxiliary JPanel that contains all the program's JButtons.*/
    private JPanel buttonPanel;
    /** The JLabel that shows the item and its price. */
    private JLabel label;
    /** The JTextField that shows the total price of the inputted payment. */
    private JTextField total;
    /** The JTextField that shows how many denominations are currently to be paid. */
    private JTextField thousand, fiveH, hund, fifty, twenty, ten, five, one;
    /** The JTextField that shows if pressing the button adds or subtracts the denominations. */
    private JTextField reverse;
    /** A boolean that tells whether or not pressing the button adds or subtracts the denominations.*/
    private boolean add = true;
    /** The Money class that collects the input and adds it as payment for the item. */
    private Money payment = new Money();
    /** The index of the item slot in the vending machine.*/
    private int index = 0;
    /** Tells whether or not a custom order is being made. */
    private boolean isCustom = false;
    /** The Vending Machine that is used by the program.*/
    private VendingMachine vendingMachine;

    /**
     * Initializes the Transaction Menu.
     * @param vendingMachine is the vending machine passed from the previous GUI class.
     * @param index the slot at which the item is located.
     * @param price the price of the item, formatted as a string for convenience of display.
     */
    public TransactFrame(VendingMachine vendingMachine, int index, String price) {
        this.index = index;
        this.vendingMachine = vendingMachine;

        subPanel = new JPanel();
        label = new JLabel();
        label.setFont(new Font("Century Gothic", Font.BOLD, 25));
        label.setText("<html>" + this.vendingMachine.getSlots().get(index).getItemType().getNAME() +
                ": Php " + price + "</html>");
        subPanel.add(label);

        initialize();
    }

    /**
     * Initializes the Transaction Menu.
     * @param vendingMachine is the vending machine passed from the previous GUI class. It must be of the
     *                       SpecialVendingMachine class.
     * @param price the price of the item, formatted as a string for convenience of display.
     * @param isCustom tells whether or not the order made is custom.
     */
    public TransactFrame(SpecialVendingMachine vendingMachine, String price, boolean isCustom) {
        this.vendingMachine = vendingMachine;
        this.isCustom = isCustom;
        subPanel = new JPanel();
        label = new JLabel();
        label.setFont(new Font("Century Gothic", Font.BOLD, 25));
        label.setText("Custom Order: Php " + price);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        subPanel.add(label);

        initialize();
    }

    /**
     * These are the common lines used in both constructors for the TransactFrame class,
     * collected into one method.
     */
    public void initialize() {
        int i;

        this.setTitle("Transaction");

        this.setSize(new Dimension(600, 800));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        label.setBackground(Color.decode("#41578c"));
        label.setForeground(Color.WHITE);
        subPanel.setBackground(Color.decode("#242424"));

        masterPanel = new JPanel();
        masterPanel.setPreferredSize(new Dimension(600, 600));
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel.setBackground(Color.decode("#242424"));

        total = new JTextField("Total: 0.0");
        total.setFont(new Font("Consolas", Font.BOLD, 20));
        total.setEditable(false);
        total.setForeground(Color.WHITE);
        total.setBackground(Color.decode("#41578c"));
        total.setHorizontalAlignment(JTextField.RIGHT);
        total.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));

        thousand = new JTextField("1000: 0");
        thousand.setFont(new Font("Consolas", Font.BOLD, 20));
        thousand.setEditable(false);
        thousand.setForeground(Color.WHITE);
        thousand.setBackground(Color.decode("#41578c"));
        thousand.setHorizontalAlignment(JTextField.RIGHT);
        thousand.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        fiveH = new JTextField("500: 0");
        fiveH.setFont(new Font("Consolas", Font.BOLD, 20));
        fiveH.setEditable(false);
        fiveH.setForeground(Color.WHITE);
        fiveH.setBackground(Color.decode("#41578c"));
        fiveH.setHorizontalAlignment(JTextField.RIGHT);
        fiveH.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        hund = new JTextField("100: 0");
        hund.setFont(new Font("Consolas", Font.BOLD, 20));
        hund.setEditable(false);
        hund.setForeground(Color.WHITE);
        hund.setBackground(Color.decode("#41578c"));
        hund.setHorizontalAlignment(JTextField.RIGHT);
        hund.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        fifty = new JTextField("50: 0");
        fifty.setFont(new Font("Consolas", Font.BOLD, 20));
        fifty.setEditable(false);
        fifty.setForeground(Color.WHITE);
        fifty.setBackground(Color.decode("#41578c"));
        fifty.setHorizontalAlignment(JTextField.RIGHT);
        fifty.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        twenty = new JTextField("20: 0");
        twenty.setFont(new Font("Consolas", Font.BOLD, 20));
        twenty.setEditable(false);
        twenty.setForeground(Color.WHITE);
        twenty.setBackground(Color.decode("#41578c"));
        twenty.setHorizontalAlignment(JTextField.RIGHT);
        twenty.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        ten = new JTextField("10: 0");
        ten.setFont(new Font("Consolas", Font.BOLD, 20));
        ten.setEditable(false);
        ten.setForeground(Color.WHITE);
        ten.setBackground(Color.decode("#41578c"));
        ten.setHorizontalAlignment(JTextField.RIGHT);
        ten.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        five = new JTextField("5: 0");
        five.setFont(new Font("Consolas", Font.BOLD, 20));
        five.setEditable(false);
        five.setForeground(Color.WHITE);
        five.setBackground(Color.decode("#41578c"));
        five.setHorizontalAlignment(JTextField.RIGHT);
        five.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        one = new JTextField("1: 0");
        one.setFont(new Font("Consolas", Font.BOLD, 20));
        one.setEditable(false);
        one.setForeground(Color.WHITE);
        one.setBackground(Color.decode("#41578c"));
        one.setHorizontalAlignment(JTextField.RIGHT);
        one.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        reverse = new JTextField("Adding Denominations");
        reverse.setFont(new Font("Consolas", Font.BOLD, 20));
        reverse.setEditable(false);
        reverse.setForeground(Color.WHITE);
        reverse.setBackground(Color.decode("#41578c"));
        reverse.setHorizontalAlignment(JTextField.RIGHT);
        reverse.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        masterPanel.add(total);
        masterPanel.add(thousand);
        masterPanel.add(fiveH);
        masterPanel.add(hund);
        masterPanel.add(fifty);
        masterPanel.add(twenty);
        masterPanel.add(ten);
        masterPanel.add(five);
        masterPanel.add(one);
        masterPanel.add(reverse);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 3, 15, 15));
        buttonPanel.setBackground(Color.decode("#242424"));

        for (i=0; i<11; i++) {
            JButton button = getJButton(vendingMachine, i);
            buttonPanel.add(button);
        }

        masterPanel.add(subPanel);
        masterPanel.add(buttonPanel);
        this.add(masterPanel);
        //this.pack();
        this.setVisible(true);
    }

    /**
     * Returns a JButton that allows the input of the options that could be done on the GUI.
     * @param vendingMachine the vending machine used by the program.
     * @param i the index that tells what string of text the button will display.
     * @return a JButton that allows the input of the denominations.
     */
    private JButton getJButton(VendingMachine vendingMachine, int i) {
        String[] denoms = {"1000.00", "500.00", "100.00", "50.00", "20.00", "10.00", "5.00", "1.00",
                "REVERSE", "ENTER", "EXIT"};

        JButton button = new JButton(denoms[i]);
        button.setFont(new Font("Century Gothic", Font.BOLD, 12));

        button.addActionListener(this);

        return button;
    }

    /**
     * Processes the event in which a button is pressed.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("REVERSE")) {
            if (!add) {
                add = true;
                reverse.setText("Adding Denominations");
            }
            else {
                add = false;
                reverse.setText("Subtracting Denominations");
            }
        }
        else if (command.equals("ENTER")) {
            float[] arr = {1000f, 500f, 100f, 50f, 20f, 10f, 5f, 1f};
            // payment and stuff
            if (vendingMachine instanceof SpecialVendingMachine && isCustom) {
                SpecialVendingMachine specialVM = new SpecialVendingMachine();
                if (payment.getTotal() >= ((SpecialVendingMachine) vendingMachine).calculatePrice()) {
                    specialVM = (SpecialVendingMachine) vendingMachine;

                    JOptionPane.showMessageDialog(
                            null,
                            "Custom Order dispensed.\n" +
                            vendingMachine.changeDispensedMessage(specialVM.transact(payment, specialVM.calculatePrice()), arr));

                    specialVM.createProduct();

                    if (specialVM.getIngredientMessages().size() > 1) {
                        showMessages(specialVM.getIngredientMessages(), specialVM);
                    }
                    else
                        new FeaturesMenu(specialVM);
                }
                else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Not enough money inserted! Your money is returned.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    ((SpecialVendingMachine) vendingMachine).clearIngredientMessages();
                    ((SpecialVendingMachine) vendingMachine).clearOrder();
                    new FeaturesMenu(vendingMachine);
                }
            } else {
                if (payment.getTotal() >= vendingMachine.getSlots().get(index).getPrice()) {
                    if (!(vendingMachine.getSlots().get(index).getCount() == 0)) {
                        JOptionPane.showMessageDialog(
                                null,
                                vendingMachine.dispenseItem(vendingMachine.getSlots().get(index)).getNAME()
                                        + " dispensed.\n" +
                                        vendingMachine.changeDispensedMessage(vendingMachine.transact(payment, vendingMachine.getSlots().get(index).getPrice()), arr)
                        );
                    } else
                        JOptionPane.showMessageDialog(
                                null,
                                "Item is out of stock!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                } else
                    JOptionPane.showMessageDialog(
                            null,
                            "Not enough money inserted! Your money is returned.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                new FeaturesMenu(vendingMachine);
            }

            this.dispose();
        }
        else if (command.equals("EXIT")) {
            this.dispose();
            if (vendingMachine instanceof SpecialVendingMachine) {
                ((SpecialVendingMachine) vendingMachine).clearIngredientMessages();
                ((SpecialVendingMachine) vendingMachine).clearOrder();
            }
            new FeaturesMenu(vendingMachine);
        }
        else {
            switch ((int)Float.parseFloat(command)) {
            case 1000:
                if (add)
                    payment.setThousand(payment.getThousand()+1);
                else if (payment.getThousand() > 0)
                    payment.setThousand(payment.getThousand()-1);

                thousand.setText("1000: " + payment.getThousand());
                break;
            case 500:
                if (add)
                    payment.setFiveH(payment.getFiveH()+1);
                else if (payment.getFiveH() > 0)
                    payment.setFiveH(payment.getFiveH()-1);

                fiveH.setText("500: " + payment.getFiveH());
                break;
            case 100:
                if (add)
                    payment.setHund(payment.getHund()+1);
                else if (payment.getHund() > 0)
                    payment.setHund(payment.getHund()-1);

                hund.setText("100: " + payment.getHund());
                break;
            case 50:
                if (add)
                    payment.setFifty(payment.getFifty()+1);
                else if (payment.getFifty() > 0)
                    payment.setFifty(payment.getFifty()-1);

                fifty.setText("50: " + payment.getFifty());
                break;
            case 20:
                if (add)
                    payment.setTwenty(payment.getTwenty()+1);
                else if (payment.getTwenty() > 0)
                    payment.setTwenty(payment.getTwenty()-1);

                twenty.setText("20: " + payment.getTwenty());
                break;
            case 10:
                if (add)
                    payment.setTen(payment.getTen()+1);
                else if (payment.getTen() > 0)
                    payment.setTen(payment.getTen()-1);

                ten.setText("10: " + payment.getTen());
                break;
            case 5:
                if (add)
                    payment.setFive(payment.getFive()+1);
                else if (payment.getFive() > 0)
                    payment.setFive(payment.getFive()-1);

                five.setText("5: " + payment.getFive());
                break;
            case 1:
                if (add)
                    payment.setOne(payment.getOne()+1);
                else if (payment.getOne() > 0)
                    payment.setOne(payment.getOne()-1);

                one.setText("1: " + payment.getOne());
                break;
            }

            total.setText("Total: " + payment.getTotal());
        }
    }

    /**
     * Shows the messages that tells the status of the custom order.
     * @param messages the ArrayList of messages that will be displayed, with an interval of 1.5 seconds
     *                 for each string.
     * @param vendingMachine The SpecialVendingMachine used by the program.
     */
    public void showMessages(ArrayList<String> messages, SpecialVendingMachine vendingMachine) {
        final int[] counter = {0};
        JFrame subFrame = new JFrame("Status");
        subFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        subFrame.setSize(800, 150);
        subFrame.setLocationRelativeTo(null);
        subFrame.setBackground(Color.decode("#242424"));

        //The first ingredient anyways
        JTextField textField = new JTextField("Placing Ice Cup.", 80);
        textField.setFont(new Font("Consolas", Font.PLAIN, 25));
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setBackground(Color.decode("#242424"));
        textField.setForeground(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        subFrame.add(textField);
        subFrame.setVisible(true);

        Timer timer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter[0]++;
                if (counter[0] <= messages.size()) {
                    if (counter[0] < messages.size())
                        textField.setText(messages.get(counter[0]));
                    else
                        textField.setText("Your order is ready!");
                }
                else {
                    ((Timer)e.getSource()).stop();
                    subFrame.dispose();
                    vendingMachine.clearIngredientMessages();
                    new FeaturesMenu(vendingMachine);
                }

            }
        });
        timer.start();
    }
}
