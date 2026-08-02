import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This the JFrame that displays the options for testing the
 * vending machine.
 * @author Gutierrez, Jonathan Jr
 * @author Maullon, Edriel Lexine
 */
public class TestMenu extends JFrame implements ActionListener {
    /** The buttons used for opening the GUI for vending features, for vending maintenance,
     * and for returning to the previous menu.*/
    private JButton vendingFeatures, maintenance, returnToMenu;
    /** The primary JPanel that stores all the GUI elements. Exists for the use of BoxLayout.*/
    private JPanel masterPanel;
    /** The auxiliary JPanel that contains the JLabel for the window. */
    private JPanel subPanel;
    /** The auxiliary JPanel that contains all the program's JButtons.*/
    private JPanel buttonPanel;
    /** The JLabel that shows which section of the menu the user is in. */
    private JLabel label = new JLabel("<html><center>TEST<br>VENDING MACHINE</center></html>");
    /** The Vending Machine that is used by the program.*/
    private VendingMachine vendingMachine;

    /**
     * Initializes the Test Menu.
     * @param vendingMachine is the vending machine passed from the previous
     *                       GUI class.
     */
    public TestMenu(VendingMachine vendingMachine) {
        this.setTitle("Test Vending Machine");
        this.vendingMachine = vendingMachine;

        this.setSize(600, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        masterPanel = new JPanel();
        masterPanel.setSize(600, 300);
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel.setBackground(Color.decode("#242424"));

        subPanel = new JPanel();
        label.setFont(new Font("Century Gothic", Font.BOLD, 50));
        label.setForeground(Color.WHITE);
        subPanel.add(label);
        subPanel.setBackground(Color.decode("#242424"));

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        vendingFeatures = new JButton("<html><center>VENDING<br>FEATURES</center></html>");
        vendingFeatures.setPreferredSize(new Dimension(150, 50));
        vendingFeatures.addActionListener(this);
        vendingFeatures.setFont(new Font("Century Gothic", Font.BOLD, 12));
        maintenance = new JButton("<html><center>MAINTENANCE</center></html>");
        maintenance.setPreferredSize(new Dimension(150, 50));
        maintenance.addActionListener(this);
        maintenance.setFont(new Font("Century Gothic", Font.BOLD, 12));
        returnToMenu = new JButton("<html><center>RETURN<br>TO MENU</center></html>");
        returnToMenu.setPreferredSize(new Dimension(150, 50));
        returnToMenu.addActionListener(this);
        returnToMenu.setFont(new Font("Century Gothic", Font.BOLD, 12));

        buttonPanel.add(vendingFeatures);
        buttonPanel.add(maintenance);
        buttonPanel.add(returnToMenu);
        buttonPanel.setBackground(Color.decode("#242424"));

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
        if (e.getSource() == vendingFeatures) {
            //invoke vendingFeatures menu
            new FeaturesMenu(vendingMachine);
            this.dispose();
        } else if (e.getSource() == maintenance) {
            //invoke maintenance menu
            new MaintenanceMenu(vendingMachine);
            this.dispose();
        } else if (e.getSource() == returnToMenu) {
            new Driver(vendingMachine);
            this.dispose();
        }
    }
}
