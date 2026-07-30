import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeaturesMenu extends JFrame implements ActionListener {
    private JButton returnToMenu;
    private JPanel masterPanel;
    private JPanel subPanel;
    private JPanel buttonPanel;
    private JLabel label = new JLabel("<html><center>Select an option:</center></html>");
    private VendingMachine vendingMachine;

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

        subPanel = new JPanel();
        label.setFont(new Font("Bahnschrift", Font.PLAIN, 50));
        subPanel.add(label);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3, 15, 15));

        for (i=0; i<8; i++) {
            JButton button = getJButton(vendingMachine, i);
            buttonPanel.add(button);
        }

        returnToMenu = new JButton("<html><center>RETURN<br>TO MENU</center></html>");
        returnToMenu.addActionListener(this);
        buttonPanel.add(returnToMenu);

        masterPanel.add(subPanel);
        masterPanel.add(buttonPanel);
        this.add(masterPanel);
        this.setVisible(true);
    }

    private JButton getJButton(VendingMachine vendingMachine, int i) {
        String[] prices = {"10.00", "25.00", "15.00", "30.00",
                "30.00", "20.00", "25.00", "40.00"};
        String[] calories ={"10.0", "100.0", "150.0", "150.0",
                "85.0", "95.0", "90.0", "130.0"};

        JButton button = new JButton("<html><center>" +
                vendingMachine.getSlots().get(i).getItemType().getNAME() + "<br>" +
                "Php " + prices[i] + "<br>" + calories[i] + " kcal" + "<br>" +
                vendingMachine.getSlots().get(i).getCount() + " in stock" +
                "</html></center>");

        int finalI = i;
        button.addActionListener(e -> {
                    new TransactFrame(vendingMachine, finalI, prices[i]);
                    this.dispose();
        });
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnToMenu) {
            this.dispose();
            new TestMenu(vendingMachine);
        }
    }
}
