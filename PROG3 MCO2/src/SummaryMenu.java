import javax.swing.*;
import java.awt.*;

public class SummaryMenu extends JFrame{
    /** The primary JPanel that stores all the GUI elements. */
    private JPanel masterPanel;
    /** The auxiliary JPanel that contains the JLabel for the window. */
    private JPanel subPanel;
    /** The button allowing to user to confirm what is displayed on the usmmary and exit.*/
    private JButton confirmExit;
    /** The text area where the summary of transactions is displayed.*/
    private JTextArea summaryText;

    /**
     * Initializes the Transaction Summary menu.
     * @param vendingMachine is the vending machine passed from the previous GUI class.
     */
    public SummaryMenu(VendingMachine vendingMachine) {
        this.setTitle("Summary of Transactions");

        this.setSize(new Dimension(600,300));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        masterPanel = new JPanel();
        masterPanel.setPreferredSize(new Dimension(600, 300));
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel.setBackground(Color.decode("#242424"));

        //summaryText = new JTextArea(vendingMachine.printSummary());
        summaryText = new JTextArea(300, 300);
        summaryText.setText(vendingMachine.printSummary());
        summaryText.setEditable(false);
        summaryText.setLineWrap(true);
        summaryText.setFont(new Font("Consolas", Font.BOLD, 13));
        summaryText.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryText.setBackground(Color.decode("#242424"));
        summaryText.setForeground(Color.WHITE);

        confirmExit = new JButton("<html><center>Exit and Close</html></center>");
        confirmExit.addActionListener(e ->  {
            this.dispose();
            new MaintenanceMenu(vendingMachine);
        });
        confirmExit.setFont(new Font("Century Gothic", Font.BOLD, 12));
        subPanel = new JPanel();
        subPanel.add(confirmExit);
        subPanel.setBackground(Color.decode("#242424"));

        masterPanel.add(summaryText);
        masterPanel.add(subPanel);
        this.add(masterPanel);
        this.setVisible(true);
    }
}
