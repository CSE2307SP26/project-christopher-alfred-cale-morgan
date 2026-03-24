package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainMenu {

    private static final int EXIT_SELECTION = 3;
	private static final int MAX_SELECTION = 3;

    private List<BankAccount> userAccounts;
    private BankAccount userAccount;
    private Scanner keyboardInput;

    public MainMenu() {
        this.userAccounts = new ArrayList<>();
        this.userAccount = new BankAccount();
        userAccounts.add(userAccount);
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("Welcome to the 237 Bank App!");
        
        System.out.println("1. Make a deposit");
        System.out.println("2. Gain Admin");
        System.out.println("3. Exit the app");

    }

    public int getUserSelection(int max) {
        int selection = -1;
        while(selection < 1 || selection > max) {
            System.out.print("Please make a selection: ");
            selection = keyboardInput.nextInt();
        }
        return selection;
    }

    public void processInput(int selection) {
        switch (selection) {
            case 1:
                performDeposit();
            case 2:
                gainAdmin();

        }
    }

    public void performDeposit() {
        double depositAmount = -1;
        while(depositAmount < 0) {
            System.out.print("How much would you like to deposit: ");
            depositAmount = keyboardInput.nextInt();
        }
        userAccount.deposit(depositAmount);
    }


    public void addAccount() {
        BankAccount newAccount = new BankAccount();
        this.userAccounts.add(newAccount);
    }

    public int getNumAccounts() {
        return this.userAccounts.size();
    }

    /*
    TODO
    Add a method to switch user accounts
    Update Main Menu options to include changing accounts 
    */


    public int gainAdmin() { //gain access to admin status for collecting fees...TODO:Add password
        this.userAccount.setAdminStatus();
        return 0;
    }

    public void run() {
        int selection = -1;
        while(selection != EXIT_SELECTION) {
            displayOptions();
            selection = getUserSelection(MAX_SELECTION);
            processInput(selection);
        }
    }

    public static void main(String[] args) {
        MainMenu bankApp = new MainMenu();
        bankApp.run();
    }

}
