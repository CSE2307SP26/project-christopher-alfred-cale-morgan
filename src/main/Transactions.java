package main;

import java.util.ArrayList;
import java.util.List;

public class Transactions {

    private List<Transaction> transactions;

    public Transactions() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    // Display nicely
    public void displayTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("---- Transaction History ----");

        for (int i = 0; i < transactions.size(); i++) {
            Transaction t = transactions.get(i);

            System.out.println(
                (i + 1) + ". " +
                t.getType() +
                " | $" + t.getAmount() +
                " | " + t.getDescription()
            );
        }
    }
}