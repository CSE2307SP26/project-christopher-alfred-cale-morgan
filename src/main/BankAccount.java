package main;

public class BankAccount {

    private double balance;
    private Transactions transactions;
    private int id; 
    //Users need to share what account they want to transfer to, so needed ID to represent accounts. Starts at 1, increments for each new account

    public BankAccount(int id) {
        this.id = id;
        this.balance = 0;
        this.transactions = new Transactions();
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.addTransaction(
                new Transaction(amount, "Deposit", "Depositing " + amount)
            );
        } else {
            throw new IllegalArgumentException("Deposit must be positive");
        }
    }

    public void transfer(double amount, BankAccount other) {
        if (other == null) {
            throw new IllegalArgumentException("Target account does not exist");
        }

        //Is this something we want? I removed it for now cus testing w/1 account
        // if (this == other) {
        //     throw new IllegalArgumentException("Cannot transfer to the same account");
        // }

        if (amount <= 0 || balance < amount) {
            throw new IllegalArgumentException("Invalid transfer amount");
        }

        //Do the transfer if avoids all bad arguments
        this.balance -= amount;
        other.balance += amount;

        this.transactions.addTransaction(
            new Transaction(amount, "Transfer Out",
                "Sent $" + amount + " to account " + other.getId())
        );

        other.transactions.addTransaction(
            new Transaction(amount, "Transfer In",
                "Received $" + amount + " from account " + this.getId())
        );
    }
}