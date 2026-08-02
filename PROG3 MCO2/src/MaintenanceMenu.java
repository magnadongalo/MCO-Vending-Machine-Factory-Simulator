import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaintenanceMenu extends JFrame implements ActionListener {
    private JPanel masterPanel;
    private JPanel subPanel;
    private JPanel buttonPanel;
    private JLabel label = new JLabel("<html><center>MAINTENANCE FEATURES</center></html>");
    private JButton restock, replenish, summary, returnToMenu;
    private VendingMachine vendingMachine;
    private boolean running = false;

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
        replenish = new JButton("<html><center>REPLENISH CHANGE</center></html>");
        replenish.setPreferredSize(new Dimension(150, 50));
        replenish.addActionListener(this);

        summary = new JButton("<html><center>TRANSACTION<br>SUMMARY</center></html>");
        summary.setPreferredSize(new Dimension(150, 50));
        summary.addActionListener(this);

        returnToMenu = new JButton("<html><center>RETURN<br>TO MENU</center></html>");
        returnToMenu.setPreferredSize(new Dimension(150, 50));
        returnToMenu.addActionListener(this);

        buttonPanel.add(restock);
        buttonPanel.add(replenish);
        buttonPanel.add(summary);
        buttonPanel.add(returnToMenu);

        masterPanel.add(subPanel);
        masterPanel.add(buttonPanel);
        this.add(masterPanel);
        this.setVisible(true);

        running = true;
    }

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
