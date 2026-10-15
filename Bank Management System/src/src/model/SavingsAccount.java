package model;

import exception.InsufficientBalanceException;
import exception.InvalidAmountException;

/**
 * Savings Account — extends Account.
 * Rule: Balance must never fall below MINIMUM_BALANCE after a withdrawal.
 */
public class SavingsAccount extends Account {

    private static final double MINIMUM_BALANCE = 500.0;

    public SavingsAccount(String accountNumber, String accountHolderName,
                          double initialBalance, String pin) {
        super(accountNumber, accountHolderName, initialBalance, pin);
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than 0.");
        }
        setBalance(getBalance() + amount);
        System.out.printf("Deposited Rs. %.2f successfully.%n", amount);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than 0.");
        }
        if (getBalance() - amount < MINIMUM_BALANCE) {
            throw new InsufficientBalanceException(
                    "Cannot withdraw. Minimum balance of Rs. " + MINIMUM_BALANCE + " must be maintained."
            );
        }
        setBalance(getBalance() - amount);
        System.out.printf("Withdrawn Rs. %.2f successfully.%n", amount);
    }

    @Override
    public void displayAccountInfo() {
        System.out.println("--- Savings Account ---");
        super.displayAccountInfo();
        System.out.println("Min Balance Required : Rs. " + MINIMUM_BALANCE);
    }
}
