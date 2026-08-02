import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * This is the JFrame that displays the options for the items the
 * vending machine can be dispensed.
 * @author Gutierrez, Jonathan Jr
 * @author Maullon, Edriel Lexine
 */
public class FeaturesMenu extends JFrame implements ActionListener {
    /** The buttons used for opening the GUI for custom menu (for special vending machines),
     * and for returning to the previous menu.*/
    protected JButton customOrder, returnToMenu;
    /** The primary JPanel that stores all the GUI elements. Exists for the use of BoxLayout.*/
    protected JPanel masterPanel;
    /** The auxiliary JPanel that contains the JLabel for the window. */
    protected JPanel subPanel;
    /** The auxiliary JPanels that contains all the program's JButtons.
     * buttonPanel1 contains the items of the vending machine, whilst buttonPanel2
     * contains the JButtons customOrder and returnToMenu.*/
    protected JPanel buttonPanel1, buttonPanel2;
    /** The JLabel that shows the user what to do; in this case, select an item to dispense. */
    protected JLabel label = new JLabel("<html><center>Select an option:</center></html>");
    /** The Vending Machine that is used by the program.*/
    private VendingMachine vendingMachine;

    /**
     * Initializes the Features Menu.
     * @param vendingMachine is the vending machine passed from the previous
     *                       GUI class.
     */
    public FeaturesMenu(VendingMachine vendingMachine) {
        int i;

        this.setTitle("Vending Features");
        this.vendingMachine = vendingMachine;

        this.setSize(new Dimension(600, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        masterPanel = new JPanel();
        masterPanel.setSize(600, 600);
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel.setBackground(Color.decode("#242424"));

        subPanel = new JPanel();
        label.setFont(new Font("Century Gothic", Font.BOLD, 70));
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        subPanel.add(label);
        subPanel.setBackground(Color.decode("#41578c"));
        subPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new GridLayout(2, 4, 15, 15));
        buttonPanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel1.setBackground(Color.decode("#242424"));

        for (i=0; i<8; i++) {
            JButton button = getJButton(vendingMachine, i);
            buttonPanel1.add(button);
        }

        buttonPanel2 = new JPanel(new FlowLayout());
        buttonPanel2.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        customOrder = new JButton("<html><center>CUSTOM DRINK<br>BUILDER</center></html>");
        customOrder.setPreferredSize(new Dimension(150, 50));
        customOrder.addActionListener(this);
        customOrder.setEnabled(false);
        customOrder.setFont(new Font("Century Gothic", Font.BOLD, 12));

        buttonPanel2.add(customOrder);

        if (vendingMachine instanceof SpecialVendingMachine)
            customOrder.setEnabled(true);

        returnToMenu = new JButton("<html><center>RETURN<br>TO MENU</center></html>");
        returnToMenu.setPreferredSize(new Dimension(150, 50));
        returnToMenu.addActionListener(this);
        returnToMenu.setFont(new Font("Century Gothic", Font.BOLD, 12));
        buttonPanel2.add(returnToMenu);
        buttonPanel2.setBackground(Color.decode("#242424"));

        masterPanel.add(subPanel);
        masterPanel.add(buttonPanel1);
        masterPanel.add(buttonPanel2);
        this.add(masterPanel);
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
    protected JButton getJButton(VendingMachine vendingMachine, int i) {
        JButton button = new JButton("<html><center>" +
                vendingMachine.getSlots().get(i).getItemType().getNAME() + "<br>" +
                "Php " + String.format("%.2f", vendingMachine.getSlots().get(i).getPrice())
                + "<br>" + String.format("%.1f", vendingMachine.getSlots().get(i).getItemType().getCALORIES())
                + " kcal" + "<br>" + vendingMachine.getSlots().get(i).getCount() + " in stock" +
                "</html></center>");

        button.setHorizontalAlignment(JButton.CENTER);
        button.setFont(new Font("Century Gothic", Font.BOLD, 12));

        button.addActionListener(e -> {
                    new TransactFrame(vendingMachine, i,
                            String.format("%.2f", vendingMachine.getSlots().get(i).getPrice()));
                    this.dispose();
        });

        if (!(vendingMachine.getSlots().get(i).getCount() > 0))
            button.setEnabled(false);

        button.repaint();
        return button;
    }

    /**
     * Processes the event in which a button is pressed.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnToMenu) {
            this.dispose();
            new TestMenu(vendingMachine);
        } else if (e.getSource() == customOrder) {
            this.dispose();
            if (vendingMachine instanceof SpecialVendingMachine)
                new CustomBuilderMenu((SpecialVendingMachine) vendingMachine);
            //invoke custom builder menu
        }
    }
}
