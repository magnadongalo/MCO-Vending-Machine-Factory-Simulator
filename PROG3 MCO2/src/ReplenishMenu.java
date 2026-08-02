import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the JFrame that displays the options for the vending machine's
 * replenishing features.
 * @author Gutierrez, Jonathan Jr
 * @author Maullon, Edriel Lexine
 */
public class ReplenishMenu extends JFrame implements ActionListener {
    /** The primary JPanel that stores all the GUI elements. Exists for the use of BoxLayout.*/
    private JPanel masterPanel;
    /** The auxiliary JPanel that contains the JLabel for the window. */
    private JPanel subPanel;
    /** The auxiliary JPanel that contains all the program's JButtons.*/
    private JPanel buttonPanel;
    /** The JLabel that shows the user what to do. */
    private JLabel label;
    /** The Vending Machine that will be used by the program.*/
    private VendingMachine vendingMachine;

    /**
     * Initializes the Replenish Change menu.
     * @param vendingMachine is the vending machine passed from the previous GUI class.
     */
    public ReplenishMenu(VendingMachine vendingMachine) {
        int i;

        this.vendingMachine = vendingMachine;

        this.setTitle("Replenish Change Denominations");

        this.setSize(new Dimension(600, 300));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        masterPanel = new JPanel();
        masterPanel.setPreferredSize(new Dimension(600, 400));
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel.setBackground(Color.decode("#242424"));

        subPanel = new JPanel();
        label = new JLabel();
        label.setFont(new Font("Century Gothic", Font.BOLD, 40));
        label.setText("<html><center>Replenish which<br>denomination?</center></html>");
        label.setForeground(Color.WHITE);
        subPanel.add(label);
        subPanel.setBackground(Color.decode("#242424"));
        masterPanel.add(subPanel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 3, 15, 15));
        buttonPanel.setBackground(Color.decode("#242424"));

        for (i=0; i<10; i++) {
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
    public JButton getJButton(VendingMachine vendingMachine, int i) {
        String[] denoms = {"1000.00", "500.00", "100.00", "50.00", "20.00", "10.00", "5.00", "1.00",
                "ALL", "EXIT"};

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

        if (command.equals("ALL")) {
            vendingMachine.replenish();
            JOptionPane.showMessageDialog(
                    null,
                    "All denominations replenished to full!"
            );
        } else if (!command.equals("EXIT")) {
            switch ((int)Float.parseFloat(command)) {
            case 1000:
                vendingMachine.replenish(1000f);
                JOptionPane.showMessageDialog(null, "Php 1000.00 bills replenished to full!");
                break;
            case 500:
                vendingMachine.replenish(500f);
                JOptionPane.showMessageDialog(null, "Php 500.00 bills replenished to full!");
                break;
            case 100:
                vendingMachine.replenish(100f);
                JOptionPane.showMessageDialog(null, "Php 100.00 bills replenished to full!");
                break;
            case 50:
                vendingMachine.replenish(50f);
                JOptionPane.showMessageDialog(null, "Php 50.00 bills replenished to full!");
                break;
            case 20:
                vendingMachine.replenish(20f);
                JOptionPane.showMessageDialog(null, "Php 20.00 coins replenished to full!");
                break;
            case 10:
                vendingMachine.replenish(10f);
                JOptionPane.showMessageDialog(null, "Php 10.00 coins replenished to full!");
                break;
            case 5:
                vendingMachine.replenish(5f);
                JOptionPane.showMessageDialog(null, "Php 5.00 coins replenished to full!");
                break;
            case 1:
                vendingMachine.replenish(1f);
                JOptionPane.showMessageDialog(null, "Php 1.00 coins replenished to full!");
                break;
            }
        }

        this.dispose();
        new MaintenanceMenu(vendingMachine);
    }
}
