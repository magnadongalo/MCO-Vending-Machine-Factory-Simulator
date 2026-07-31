import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestockMenu extends JFrame {
    private JPanel masterPanel;
    private JPanel subPanel;
    private JPanel buttonPanel;
    private JLabel label;
    private VendingMachine vendingMachine;
    private JButton exit;

    public RestockMenu(VendingMachine vendingMachine) {
        int i;

        this.vendingMachine = vendingMachine;

        this.setTitle("Replenish Change Denominations");

        this.setSize(new Dimension(600, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        masterPanel = new JPanel();
        masterPanel.setPreferredSize(new Dimension(600, 600));
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));

        subPanel = new JPanel();
        label = new JLabel();
        label.setFont(new Font("Bahnschrift", Font.PLAIN, 25));
        label.setText("<html><center>Replenish which denomination?</center></html>");
        subPanel.add(label);
        masterPanel.add(subPanel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3, 15, 15));

        for (i=0; i<8; i++) {
            JButton button = getJButton(vendingMachine, i);
            buttonPanel.add(button);
        }

        exit = new JButton("<html><center>EXIT</center></html>");
        exit.addActionListener(e -> {
            this.dispose();
            new MaintenanceMenu(vendingMachine);
        });
        buttonPanel.add(exit);

        masterPanel.add(subPanel);
        masterPanel.add(buttonPanel);
        this.add(masterPanel);
        //this.pack();
        this.setVisible(true);
    }

    private JButton getJButton(VendingMachine vendingMachine, int i) {
        JButton button = new JButton("<html><center>" +
                vendingMachine.getSlots().get(i).getItemType().getNAME() + "<br>" +
                "Php " + String.format("%.2f", vendingMachine.getSlots().get(i).getPrice())
                + "<br>" + String.format("%.1f", vendingMachine.getSlots().get(i).getItemType().getCALORIES())
                + " kcal" + "<br>" + vendingMachine.getSlots().get(i).getCount() + " in stock" +
                "</html></center>");

        button.addActionListener(e ->  {
            replenishMenu(vendingMachine, i);
            this.setEnabled(true);
        });

        return button;
    }

    private void replenishMenu(VendingMachine vendingMachine, int i) {
        this.dispose();

        JFrame subWindow = new JFrame("Replenish Item Stock");
        subWindow.setSize(400, 200);
        subWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        subWindow.setLayout(new FlowLayout());
        subWindow.setLocationRelativeTo(null);

        JLabel label = new JLabel("How much?: ");
        JTextField textField = new JTextField(20);
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
