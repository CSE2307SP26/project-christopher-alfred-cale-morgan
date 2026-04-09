package main;

import java.util.HashMap;
import java.util.Map;

public class BankAccounts {

    private Map<Integer, BankAccount> accounts;
    private int nextId;

    public BankAccounts() {
        accounts = new HashMap<>();
        nextId = 1;
    }

    
    public BankAccount createCheckingAccount() {
        BankAccount account = new CheckingAccount(nextId);
        accounts.put(nextId, account);
        nextId++;
        return account;
    }

    public BankAccount createSavingsAccount() {
        BankAccount account = new SavingsAccount(nextId);
        accounts.put(nextId, account);
        nextId++;
        return account;
    }

    public BankAccount getAccount(int id) {
        return accounts.get(id);
    }
    public int getNumAccounts() { return accounts.size(); }
}