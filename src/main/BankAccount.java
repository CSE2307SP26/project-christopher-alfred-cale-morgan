package main;

public class BankAccount {


    private double balance;
    private Boolean isAdmin;
    //need to ID implementation...

    public BankAccount() {
        this.balance = 0;
        this.isAdmin = false;

    }

    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException();
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
}
