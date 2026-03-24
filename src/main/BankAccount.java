package main;

public class BankAccount {


    private double balance;
    private double accountFees;
    private Boolean isAdmin;
    private int id;


    public BankAccount(int id) {
        this.balance = 0;
        this.accountFees = 0;
        this.isAdmin = false;
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void payFee() {

        if (this.balance >= this.accountFees) {
            this.balance -= this.accountFees;
            System.out.print("User " + this.id + " will pay: $" + this.accountFees);
            this.accountFees = 0;
        } else {
            throw new IllegalCallerException("Accout has insufficient funds to pay fees");
        }

    }

    public void setAdminStatus(){ //TODO: be able to set admin false
        this.isAdmin = true;
        System.out.print("Admin Access Gained!\n");
    }

    public boolean getAdminStatus() {
        if(!(this.isAdmin)) {
            return false;
        }
        return true;
    }

    public double getBalance() {
        return this.balance;
    }

    public void addFees(double addedFee) {
        this.accountFees += addedFee;
    }   

    public double getFees() {
        return this.accountFees;
    }
}
