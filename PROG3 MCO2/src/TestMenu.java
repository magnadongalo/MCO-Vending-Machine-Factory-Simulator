import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestMenu extends JFrame implements ActionListener {
    private JButton vendingFeatures, maintenance, returnToMenu;
    private JPanel masterPanel;
    private JPanel subPanel;
    private JPanel buttonPanel;
    private JLabel label = new JLabel("<html><center>TEST<br>VENDING MACHINE</center></html>");
    private VendingMachine vendingMachine;

    public TestMenu(VendingMachine vendingMachine) {
        this.setTitle("Test Vending Machine");
        this.vendingMachine = vendingMachine;

        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        masterPanel = new JPanel();
        masterPanel.setSize(600, 600);
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));

        subPanel = new JPanel();
        label.setFont(new Font("Bahnschrift", Font.PLAIN, 50));
        subPanel.add(label);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        vendingFeatures = new JButton("<html><center>VENDING<br>FEATURES</center></html>");
        vendingFeatures.setPreferredSize(new Dimension(150, 50));
        vendingFeatures.addActionListener(this);
        maintenance = new JButton("<html><center>MAINTENANCE</center></html>");
        maintenance.setPreferredSize(new Dimension(150, 50));
        maintenance.addActionListener(this);
        returnToMenu = new JButton("<html><center>RETURN<br>TO MENU</center></html>");
        returnToMenu.setPreferredSize(new Dimension(150, 50));
        returnToMenu.addActionListener(this);

        buttonPanel.add(vendingFeatures);
        buttonPanel.add(maintenance);
        buttonPanel.add(returnToMenu);

        masterPanel.add(subPanel);
        masterPanel.add(buttonPanel);
        this.add(masterPanel);
        this.setVisible(true);
    }

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
            new VMFrame(vendingMachine);
            this.dispose();
        }
    }
}
