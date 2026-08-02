import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The driver class that kickstarts the whole program
 * It is a GUI display that allows the user to create a vending
 * machine, test its features, or exit the program.
 * @author Gutierrez, Jonathan Jr
 * @author Maullon, Edriel Lexine
 */
public class Driver extends JFrame implements ActionListener{
    /** The primary JPanel that stores all the GUI elements. Exists for the use of BoxLayout.*/
    private JPanel masterPanel = new JPanel();
    /** The auxiliary JPanel that contains the JLabel for the window. */
    private JPanel subpanel = new JPanel();
    /** The auxiliary JPanel that contains all the program's JButtons.*/
    private JPanel buttonPanel;
    /** The JLabel that shows the program's title. */
    private JLabel label = new JLabel("<html><center>VENDING<br>MACHINE<br>SIM</center></html>");
    /** The JButtons used for creating a regular vending machine, creating a special vending machine,
     * testing the features of the vending machine, and exiting the program.*/
    private JButton create, createSpecial, test, exit;
    /** The Vending Machine that will be used by the program.*/
    private VendingMachine vendingMachine;

    /**
     * Initializes the Driver class. This is for when the program
     * first starts.
     */
    public Driver() {
        initialize();
    }

    /**
     * Initializes the Driver class with the previous Vending Machine.
     * @param vendingMachine the vending machine passed as a parameter to be preserved.
     */
    public Driver(VendingMachine vendingMachine) {
        initialize();
        this.vendingMachine = vendingMachine;
    }

    /**
     * These are the common lines used in both constructors for the Driver class,
     * collected into one method.
     */
    public void initialize() {
        this.setTitle("Vending Machine Simulator");

        this.setSize(new Dimension(600, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.decode("#161716"));

        masterPanel.setSize(600, 600);
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel.setBackground(Color.decode("#242424"));

        label.setFont(new Font("Century Gothic", Font.BOLD, 100));
        label.setForeground(Color.WHITE);
        subpanel.add(label);
        subpanel.setBackground(Color.decode("#242424"));

        //buttonPanel = new JPanel(new GridLayout(1, 3, 15, 10));
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        create = new JButton("<html><center>CREATE<br>MACHINE</center></html>");
        create.setPreferredSize(new Dimension(150, 50));
        create.addActionListener(this);
        createSpecial = new JButton("<html><center>CREATE SPECIAL<br>MACHINE</center></html>");
        createSpecial.setPreferredSize(new Dimension(150, 50));
        createSpecial.addActionListener(this);
        test = new JButton("<html><center>TEST<br>MACHINE</center></html>");
        test.setPreferredSize(new Dimension(150, 50));
        test.addActionListener(this);
        exit = new JButton("<html><center>EXIT</center></html>");
        exit.setPreferredSize(new Dimension(150, 50));
        exit.addActionListener(this);

        buttonPanel.add(create);
        buttonPanel.add(createSpecial);
        buttonPanel.add(test);
        buttonPanel.add(exit);
        buttonPanel.setBackground(Color.decode("#242424"));

        masterPanel.add(subpanel);
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
        if (e.getSource() == create) {
            createVendingMachine(false);
            JOptionPane.showMessageDialog(
                    null,
                    "Regular Vending Machine Created."
            );
        } else if (e.getSource() == createSpecial) {
            createVendingMachine(true);
            JOptionPane.showMessageDialog(
                    null,
                    "Special Vending Machine Created."
            );
        } else if (e.getSource() == test) {
            if (vendingMachine != null) {
                //insert test menu here
                this.dispose();
                new TestMenu(vendingMachine);
            }
            else
                JOptionPane.showMessageDialog(
                        null,
                        "Create a vending machine first.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
        } else if (e.getSource() == exit) {
            this.dispose();
        }
    }

    /**
     * Creates a vending machine.
     * @param isSpecial tells whether or not the vending machine created
     *                  will be regular or special.
     */
    public void createVendingMachine(boolean isSpecial) {
        if (isSpecial)
            vendingMachine = new SpecialVendingMachine();
        else
            vendingMachine = new VendingMachine();
    }

    /**
     * Returns the vending machine.
     * @return the vending machine.
     */
    public VendingMachine getVendingMachine() {
        return vendingMachine;
    }

    /**
     * The main method used to drive the program.
     * @param args the array of strings in the command line.
     */
    public static void main(String[] args) {
        new Driver();
    }
}
