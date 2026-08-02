import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the JFrame that displays the options for the vending machine's
 * maintenance features.
 * @author Gutierrez, Jonathan Jr
 * @author Maullon, Edriel Lexine
 */
public class MaintenanceMenu extends JFrame implements ActionListener {
    /** The primary JPanel that stores all the GUI elements. Exists for the use of BoxLayout.*/
    private JPanel masterPanel;
    /** The auxiliary JPanel that contains the JLabel for the window. */
    private JPanel subPanel;
    /** The auxiliary JPanel that contains all the program's JButtons.*/
    private JPanel buttonPanel;
    /** The JLabel that shows the item and its price. */
    private JLabel label = new JLabel("<html><center>MAINTENANCE FEATURES</center></html>");
    /** The JButtons used for restocking items, replenishing change, printing the summary of transactions,
     * and returning to the main menu.*/
    private JButton restock, replenish, summary, returnToMenu;
    /** The Vending Machine that will be used by the program.*/
    private VendingMachine vendingMachine;

    /**
     * Initializes the Maintenance Features Menu.
     * @param vendingMachine is the vending machine passed from the previous GUI class.
     */
    public MaintenanceMenu(VendingMachine vendingMachine) {
        this.setTitle("Maintenance Features");
        this.vendingMachine = vendingMachine;

        this.setSize(600, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        masterPanel = new JPanel();
        masterPanel.setSize(600, 300);
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel.setBackground(Color.decode("#242424"));

        subPanel = new JPanel();
        label.setFont(new Font("Century Gothic", Font.BOLD, 45));
        label.setForeground(Color.WHITE);
        subPanel.add(label);
        subPanel.setBackground(Color.decode("#242424"));

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(Color.decode("#242424"));

        restock = new JButton("<html><center>RESTOCK ITEM</center></html>");
        restock.setPreferredSize(new Dimension(150, 50));
        restock.addActionListener(this);
        restock.setFont(new Font("Century Gothic", Font.BOLD, 12));

        replenish = new JButton("<html><center>REPLENISH CHANGE</center></html>");
        replenish.setPreferredSize(new Dimension(150, 50));
        replenish.addActionListener(this);
        replenish.setFont(new Font("Century Gothic", Font.BOLD, 12));

        summary = new JButton("<html><center>TRANSACTION<br>SUMMARY</center></html>");
        summary.setPreferredSize(new Dimension(150, 50));
        summary.addActionListener(this);
        summary.setFont(new Font("Century Gothic", Font.BOLD, 12));

        returnToMenu = new JButton("<html><center>RETURN<br>TO MENU</center></html>");
        returnToMenu.setPreferredSize(new Dimension(150, 50));
        returnToMenu.addActionListener(this);
        returnToMenu.setFont(new Font("Century Gothic", Font.BOLD, 12));

        buttonPanel.add(restock);
        buttonPanel.add(replenish);
        buttonPanel.add(summary);
        buttonPanel.add(returnToMenu);

        masterPanel.add(subPanel);
        masterPanel.add(buttonPanel);
        this.add(masterPanel);
        this.setVisible(true);
    }

    /**
     * Processes the event in which a button is pressed.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restock) {
            //invoke restock menu
            new RestockMenu(vendingMachine);
            this.dispose();
        } else if (e.getSource() == replenish) {
            //invoke replenish menu
            new ReplenishMenu(vendingMachine);
            this.dispose();
        } else if (e.getSource() == summary) {
            //invoke summary menu
            new SummaryMenu(vendingMachine);
            this.dispose();
        } else if (e.getSource() == returnToMenu) {
            new TestMenu(vendingMachine);
            this.dispose();
        }
    }
}
