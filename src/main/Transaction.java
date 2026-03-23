package main;

public class Transaction {

    private double amount;
    private String type;
    private String description;

    public Transaction(double amount, String type, String description) {
        this.amount = amount;
        this.type = type;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
