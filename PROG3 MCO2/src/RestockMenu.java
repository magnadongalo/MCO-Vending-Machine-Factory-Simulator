import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the JFrame that displays the options for the vending machine's
 * restocking features.
 * @author Gutierrez, Jonathan Jr
 * @author Maullon, Edriel Lexine
 */
public class RestockMenu extends JFrame {
    /** The primary JPanel that stores all the GUI elements. Exists for the use of BoxLayout.*/
    private JPanel masterPanel;
    /** The auxiliary JPanel that contains the JLabel for the window. */
    private JPanel subPanel;
    /** The auxiliary JPanel that contains all the program's JButtons.*/
    private JPanel buttonPanel;
    /** The JLabel that shows the user what to do. */
    private JLabel label;
    /** The button used to exit and return to the Maintenance Features menu.*/
    private JButton exit;
    /** The Vending Machine that will be used by the program.*/
    private VendingMachine vendingMachine;

    /**
     * Initializes the Restock Item menu.
     * @param vendingMachine is the vending machine passed from the previous GUI class.
     */
    public RestockMenu(VendingMachine vendingMachine) {
        int i;

        this.vendingMachine = vendingMachine;

        this.setTitle("Restock Items");

        this.setSize(new Dimension(600, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        masterPanel = new JPanel();
        masterPanel.setPreferredSize(new Dimension(600, 600));
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel.setBackground(Color.decode("#242424"));

        subPanel = new JPanel();
        label = new JLabel();
        label.setFont(new Font("Century Gothic", Font.BOLD, 60));
        label.setText("<html><center>Restock<br>which items?</center></html>");
        label.setForeground(Color.WHITE);
        subPanel.add(label);
        subPanel.setBackground(Color.decode("#242424"));
        masterPanel.add(subPanel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3, 15, 15));
        buttonPanel.setBackground(Color.decode("#242424"));

        for (i=0; i<8; i++) {
            JButton button = getJButton(vendingMachine, i);
            buttonPanel.add(button);
        }

        exit = new JButton("<html><center>EXIT</center></html>");
        exit.addActionListener(e -> {
            this.dispose();
            new MaintenanceMenu(vendingMachine);
        });
        exit.setFont(new Font("Century Gothic", Font.BOLD, 12));
        buttonPanel.add(exit);

        masterPanel.add(subPanel);
        masterPanel.add(buttonPanel);
        this.add(masterPanel);
        //this.pack();
        this.setVisible(true);
    }

    /**
     * Returns a JButton that contains the information of one of the items in each slot
     * of the vending machine.
     * @param vendingMachine the vending machine used by the program.
     * @param i the index at which slot the item of interest is in.
     * @return a JButton that contains the name, price, calories, and stock count of
     *         an item.
     */
    private JButton getJButton(VendingMachine vendingMachine, int i) {
        JButton button = new JButton("<html><center>" +
                vendingMachine.getSlots().get(i).getItemType().getNAME() + "<br>" +
                "Php " + String.format("%.2f", vendingMachine.getSlots().get(i).getPrice())
                + "<br>" + String.format("%.1f", vendingMachine.getSlots().get(i).getItemType().getCALORIES())
                + " kcal" + "<br>" + vendingMachine.getSlots().get(i).getCount() + " in stock" +
                "</html></center>");

        button.setFont(new Font("Century Gothic", Font.BOLD, 12));

        button.addActionListener(e ->  {
            askInput(vendingMachine, i);
            this.setEnabled(true);
        });

        return button;
    }

    /**
     * Asks the user to input an integer on how many items to add to the stock.
     * @param vendingMachine the vending machine used by the program.
     * @param i the index at which slot the item of interest is in.
     */
    private void askInput(VendingMachine vendingMachine, int i) {
        this.dispose();

        JFrame subWindow = new JFrame("Replenish Item Stock");
        subWindow.setSize(400, 200);
        subWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        subWindow.setLayout(new FlowLayout());
        subWindow.setLocationRelativeTo(null);
        subWindow.setBackground(Color.decode("#242424"));

        JLabel label = new JLabel("How much?: ");
        JTextField textField = new JTextField(20);
        textField.setBackground(Color.decode("#242424"));
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Century Gothic", Font.BOLD, 12));
        JButton button = new JButton("Submit");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean exceptionThrown = false;

                String userInput = textField.getText();

                try {
                    vendingMachine.restock(vendingMachine.getSlots().get(i),
                            Integer.parseInt(userInput));

                    JOptionPane.showMessageDialog(null,
                            vendingMachine.getSlots().get(i).getItemType().getNAME() + " restocked!\n" +
                            "Current count: " + vendingMachine.getSlots().get(i).getCount(),
                            "NumberFormatException", JOptionPane.PLAIN_MESSAGE);
                } catch (NumberFormatException exc) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter a valid integer.",
                            "NumberFormatException", JOptionPane.ERROR_MESSAGE);

                    exceptionThrown = true;
                } finally {
                    if (!exceptionThrown) {
                        subWindow.dispose();
                        new RestockMenu(vendingMachine);
                    }
                }
            }
        });

        JButton exitButton = new JButton("Back");
        exitButton.addActionListener(e -> {
            subWindow.dispose();
            new RestockMenu(vendingMachine);
        });

        subWindow.add(label);
        subWindow.add(textField);
        subWindow.add(button);
        subWindow.add(exitButton);
        subWindow.setVisible(true);
    }
}
