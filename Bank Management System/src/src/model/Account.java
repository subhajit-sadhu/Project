package model;

/**
 * Abstract base class for all bank accounts.
 * Defines common fields and enforces deposit/withdraw contract on subclasses.
 */
public abstract class Account {

    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String pin;

    // Constructor initializes all core account fields
    public Account(String accountNumber, String accountHolderName, double initialBalance, String pin) {
        this.accountNumber      = accountNumber;
        this.accountHolderName  = accountHolderName;
        this.balance            = initialBalance;
        this.pin                = pin;
    }

    // ---- Getters ----

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getPin() {
        return pin;
    }

    // ---- Protected Setter ----
    // Only subclasses may update the balance (via deposit/withdraw logic)
    protected void setBalance(double balance) {
        this.balance = balance;
    }

    // ---- Abstract methods — every subclass MUST implement these ----
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);

    // ---- Display ----
    public void displayAccountInfo() {
        System.out.println("==============================");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.printf( "Balance        : Rs. %.2f%n", balance);
        System.out.println("==============================");
    }
}
