import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public   class BankAccountGUI extends JFrame {
    private JLabel balanceLabel;
    private JTextField depositField, withdrawField;
    private JButton depositButton, withdrawButton;
    private double balance;

    public BankAccountGUI() {
        // Initialize the balance to 0
        balance = 0.0;

        // Create GUI components
        balanceLabel = new JLabel("Balance: $0.0");
        depositField = new JTextField(10);
        withdrawField = new JTextField(10);
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");

        // Add action listeners to buttons
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(depositField.getText());
                    deposit(amount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BankAccountGUI.this, "Invalid input. Please enter a valid number.");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(withdrawField.getText());
                    withdraw(amount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BankAccountGUI.this, "Invalid input. Please enter a valid number.");
                }
            }
        });

        // Create and set up the GUI layout
        setLayout(new FlowLayout());
        add(balanceLabel);
        add(new JLabel("Deposit:"));
        add(depositField);
        add(depositButton);
        add(new JLabel("Withdraw:"));
        add(withdrawField);
        add(withdrawButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bank Account Application");
        pack();
        setLocationRelativeTo(null);
    }

    // Method to deposit money
    private void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            updateBalanceLabel();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive number.");
        }
    }

    // Method to withdraw money
    private void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                updateBalanceLabel();
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance. Cannot withdraw more than the current balance.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive number.");
        }
    }

    // Method to update the balance label
    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + balance);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BankAccountGUI().setVisible(true);
            }
        });
    }
}
