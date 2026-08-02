import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomBuilderMenu extends FeaturesMenu implements ActionListener {
    private JButton enter, clear;
    private JTextField status;
    private SpecialVendingMachine specialVM = new SpecialVendingMachine();
    private int[] counter;
    private boolean nothingPressed = true;

    public CustomBuilderMenu(SpecialVendingMachine vendingMachine) {
        super(vendingMachine);
        specialVM = vendingMachine;
        this.setTitle("Custom Drink Builder");
        this.setSize(600, 600);

        counter = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

        clear = new JButton("<html><center>CLEAR ORDER</center></html>");
        clear.setPreferredSize(new Dimension(150, 50));
        clear.addActionListener(e -> {
            specialVM.clearOrder();
            this.dispose();
            new CustomBuilderMenu(specialVM);
        });
        enter = new JButton("<html><center>ENTER</center></html>");
        enter.setPreferredSize(new Dimension(150, 50));
        enter.addActionListener(this);

        status = new JTextField(100);
        status.setText("");
        status.setFont(new Font("Consolas", Font.PLAIN, 20));
        status.setForeground(Color.WHITE);
        status.setBackground(Color.decode("#41578c"));
        status.setEditable(false);
        status.setHorizontalAlignment(SwingConstants.CENTER);
        status.setSize(550, 25);
        status.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        buttonPanel2.remove(customOrder);
        buttonPanel2.remove(returnToMenu);
        buttonPanel2.add(clear);
        buttonPanel2.add(enter);
        buttonPanel2.add(returnToMenu);
        buttonPanel2.add(status);
    }

    @Override
    protected JButton getJButton(VendingMachine vendingMachine, int i) {
        JButton button = new JButton("<html><center>" +
                vendingMachine.getSlots().get(i).getItemType().getNAME() + "<br>" +
                "Php " + String.format("%.2f", vendingMachine.getSlots().get(i).getPrice())
                + "<br>" + String.format("%.1f", vendingMachine.getSlots().get(i).getItemType().getCALORIES())
                + " kcal" + "<br>" + vendingMachine.getSlots().get(i).getCount() + " in stock" +
                "</html></center>");

        button.setHorizontalAlignment(JButton.CENTER);

        if (i == 0) {
            button.setText("<html><center>" +
                    vendingMachine.getSlots().get(i).getItemType().getNAME()
                    + "<br>" + "ALREADY<br>PLACED" + "<br>" +
                    "Php " + String.format("%.2f", vendingMachine.getSlots().get(i).getPrice())
                    + "<br>" + String.format("%.1f", vendingMachine.getSlots().get(i).getItemType().getCALORIES())
                    + " kcal" + "<br>" + vendingMachine.getSlots().get(i).getCount() + " in stock" +
                    "</html></center>");
            button.setEnabled(false);
        }

        if (vendingMachine.getSlots().get(i).getCount() == 0) {
            button.setEnabled(false);
        }

        button.addActionListener(e -> {
            if (vendingMachine.getSlots().get(i).getCount() > 0 && vendingMachine.getSlots().get(0).getCount() > 0) {
                specialVM.addOrder(specialVM.getSlots().get(i));
                counter[i]++;
                status.setText("Added " + vendingMachine.getSlots().get(i).getItemType().getNAME()
                        + " (" + counter[i] + ")");
                nothingPressed = false;

                if (counter[i] > vendingMachine.getSlots().get(i).getCount())
                    status.setText(vendingMachine.getSlots().get(i).getItemType().getNAME() + " exhausted of stock!");
            }
            else {
                if (vendingMachine.getSlots().get(0).getCount() <= 0)
                    status.setText("Ice Cups out of stock!");
                else if (vendingMachine.getSlots().get(i).getCount() <= 0)
                    status.setText(vendingMachine.getSlots().get(i).getItemType().getNAME() + " out of stock!");
            }
        });

        button.repaint();
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnToMenu) {
            this.dispose();
            specialVM.clearIngredientMessages();
            specialVM.clearOrder();
            new FeaturesMenu(specialVM);
        } else if (e.getSource() == enter) {
            if (!nothingPressed) {
                this.dispose();
                new TransactFrame(specialVM, String.format("%.2f", specialVM.calculatePrice()), true);
            }
            else
                status.setText("Please enter an option first!");
        }
    }
}
