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

    public BankAccounts bankAccounts;
    public BankAccount userAccount;
    public Scanner keyboardInput;
    public double currentInput = 0;
}
