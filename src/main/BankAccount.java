package main;

public class BankAccount {


    private double balance;
    private double accountFees;
    private Boolean isAdmin;
    private int id;
    private Transactions transactions;
    private double interestRate;
    public static final double DEFAULT_INTEREST_RATE = 2.39;
    
    //Users need to share what account they want to transfer to, so needed ID to represent accounts. Starts at 1, increments for each new account

    public BankAccount(int id) {
        this.id = id;
        this.balance = 0;
        this.isAdmin = false;
        this.interestRate = DEFAULT_INTEREST_RATE;
        this.transactions = new Transactions();
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(double newRate) {
        this.interestRate = newRate;
    }

    public void payInterest() {
        
        double percentage;

        if(this.balance != 0) {
            percentage = (this.interestRate/100)*(this.balance);
            balance += percentage;
            transactions.addTransaction(
                new Transaction(percentage, "Deposit of interest", "Depositing " + percentage)
            );
        } else {
            throw new IllegalArgumentException("Account must have a balance");
        }
    }

    public int getId() {
        return this.id;
    }

    public double getBalance() {
        return this.balance;
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

    public void payFee() {
        if (this.balance >= this.accountFees) {
            this.balance -= this.accountFees;
            this.transactions.addTransaction(
                new Transaction(accountFees, "Fees", "Withdrawing " + accountFees)
            );
            this.accountFees = 0;
        } else {
            throw new IllegalCallerException("Account has insufficient funds to pay fees");
        }
    }

    public void setAdminStatus() {
        this.isAdmin = !this.isAdmin;
        if(this.isAdmin) {
            System.out.print("Logged in with admin access!\n");
        } else {
            System.out.print("Logged out from admin access!\n");
        }
    }

    public boolean getAdminStatus() {
        return this.isAdmin;
    }

    public void withdraw(double amount) {
        if(amount < 0.0f)
            throw new IllegalArgumentException("Withdrawal must be positive");
        if((this.balance - amount) < 0.0f)
            throw new IllegalArgumentException("Not enough funds");

        this.balance -= amount;
        transactions.addTransaction(
                new Transaction(amount, "Withdrawal", "Withdrawing " + amount)
            );
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

    public void addFees(double addedFee) {
        this.accountFees += addedFee;
    }   

    public double getFees() {
        return this.accountFees;
    }
}
