import javax.swing.*;
import java.awt.*;

public class SummaryMenu extends JFrame{
    private JPanel masterPanel;
    private JPanel subPanel;
    private JButton confirmExit;
    private JTextArea summaryText;

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
        subPanel = new JPanel();
        subPanel.add(confirmExit);
        subPanel.setBackground(Color.decode("#242424"));

        masterPanel.add(summaryText);
        masterPanel.add(subPanel);
        this.add(masterPanel);
        //this.pack();
        this.setVisible(true);
    }
}
