import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VMFrame extends JFrame implements ActionListener{
    private JPanel masterPanel = new JPanel();
    private JPanel subpanel = new JPanel();
    private JPanel buttonPanel;
    private JLabel label = new JLabel("<html><center>VENDING<br>MACHINE<br>SIM</center></html>");
    private JButton create, test, exit;
    private boolean running = false;
    private VendingMachine vendingMachine;

    public VMFrame() {
        this.setTitle("Vending Machine Simulator");

        this.setSize(new Dimension(600, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        masterPanel.setSize(600, 600);
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));

        label.setFont(new Font("Bahnschrift", Font.PLAIN, 80));
        subpanel.add(label);

        //buttonPanel = new JPanel(new GridLayout(1, 3, 15, 10));
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        create = new JButton("<html><center>CREATE<br>MACHINE</center></html>");
        create.setPreferredSize(new Dimension(150, 50));
        create.addActionListener(this);
        test = new JButton("<html><center>TEST<br>MACHINE</center></html>");
        test.setPreferredSize(new Dimension(150, 50));
        test.addActionListener(this);
        exit = new JButton("<html><center>EXIT</center></html>");
        exit.setPreferredSize(new Dimension(150, 50));
        exit.addActionListener(this);

        buttonPanel.add(create);
        buttonPanel.add(test);
        buttonPanel.add(exit);

        masterPanel.add(subpanel);
        masterPanel.add(buttonPanel);
        this.add(masterPanel);
        this.setVisible(true);

        running = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            createVendingMachine();
            JOptionPane.showMessageDialog(
                    null,
                    "Vending Machine Created."
            );
            running = false;
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

            running = false;
        } else if (e.getSource() == exit) {
            running = false;
            this.dispose();
        }
    }

    public void createVendingMachine() {
        vendingMachine = new VendingMachine();
    }

    public VendingMachine getVendingMachine() {
        return vendingMachine;
    }

    public static void main(String[] args) {
        VMFrame test = new VMFrame();
    }
}
