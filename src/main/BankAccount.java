package main;

public class BankAccount {

    private double balance;
    private Transactions transactions;

    public BankAccount() {
        this.balance = 0;
        this.transactions = new Transactions();
    }

    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
            transactions.addTransaction(new Transaction(amount, "Deposit", "Depositing " + amount));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public Transactions getTransactions() {
        return this.transactions;
    }

    public void transfer(double amount, BankAccount other) {
        if(amount > 0 && this.balance - amount >=0.0) {
            this.balance -= amount;
            other.balance += amount;
            this.transactions.addTransaction(new Transaction(amount, "Transfer Out", "Sending " + amount));
            other.transactions.addTransaction(new Transaction(amount, "Transfer To", "Recieving " + amount));

        } else {
            throw new IllegalArgumentException();
        }
    }
}

