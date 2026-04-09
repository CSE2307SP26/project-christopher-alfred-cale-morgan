package main;

public class BankAccount {


    private double balance;
    private double accountFees;
    private int id;
    private Transactions transactions;
    private double interestRate;
    private boolean isFrozen;
    private double widthdrawlLimit;
    public static final double DEFAULT_INTEREST_RATE = 2.39;
    
    //Users need to share what account they want to transfer to, so needed ID to represent accounts. Starts at 1, increments for each new account

    public BankAccount(int id) {
        this.id = id;
        this.balance = 0;
        this.interestRate = DEFAULT_INTEREST_RATE;
        this.transactions = new Transactions();
        isFrozen= false;
        this.widthdrawlLimit = -1;
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
            percentage = (this.interestRate / 100) * (this.balance);
            balance += percentage;
            balance = Math.round(balance * 100.0) / 100.0; // Round to the nearest hundredth
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
        if(isFrozen)
            throw new IllegalArgumentException("Account is Frozen");
        if (amount > 0) {
            balance += amount;
            transactions.addTransaction(
                new Transaction(amount, "Deposit", "Depositing " + amount)
            );
            System.out.println("Successfully Deposited $" + amount);
        } 
        else {
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

    public void withdraw(double amount) {
        if(amount < 0.0f )
            throw new IllegalArgumentException("Withdrawal must be positive");
        if((this.balance - amount) < 0.0f)
            throw new IllegalArgumentException("Not enough funds");
        if(isFrozen)
            throw new IllegalArgumentException("Account is Frozen");

        this.balance -= amount;
        transactions.addTransaction(
                new Transaction(amount, "Withdrawal", "Withdrawing " + amount)
            );
    }

    public void transfer(double amount, BankAccount other) {
        if (other == null) {
            throw new IllegalArgumentException("Target account does not exist");
        }
        if(isFrozen || other.isFrozen)
            throw new IllegalArgumentException("Account is Frozen");
        if (this == other) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

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

    public void freeze() {
        isFrozen = !isFrozen;
    }

    public boolean getFrozen() {
        return isFrozen;
    }

    public double getLimit() {
        return widthdrawlLimit;
    }
}
