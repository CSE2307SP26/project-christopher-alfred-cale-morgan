package main;

import java.util.Scanner;

public class AppContext {
    private static AppContext instance;
    private AppContext(){}
    public static AppContext getInstance() {
        if(instance == null) {
            instance = new AppContext();
        }

        return instance;
    }

    private BankAccounts bankAccounts;
    private BankAccount userAccount;
    private Scanner keyboardInput;
      
    public Scanner getKeyboardInput() {
        return keyboardInput;
    }

    public void setKeyboardInput(Scanner keyboardInput) {
        this.keyboardInput = keyboardInput;
    }

    public BankAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(BankAccount userAccount) {
        this.userAccount = userAccount;
    }

    public BankAccounts getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(BankAccounts bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
