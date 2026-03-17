package main;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MainMenu {

    private static final int EXIT_SELECTION = 2;
	private static final int MAX_SELECTION = 2;

	private List<BankAccount> userAccounts;
    private BankAccount userAccount;
    private Scanner keyboardInput;

    public MainMenu() {
        this.userAccounts = new ArrayList<>();
        this.userAccount = new BankAccount();
        userAccounts.add(this.userAccount);
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("Welcome to the 237 Bank App!");
        
        System.out.println("1. Make a deposit");
        System.out.println("2. Exit the app");

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

    /*TODO
    Add a method to switch user accounts
    Update Main Menu options to include changing accounts */

    public void closeAccount(int index){
        int numAccounts = this.userAccounts.size();
        BankAccount accountToDelete;
        if(index < numAccounts && numAccounts > 1){
            accountToDelete = this.userAccounts.get(index);
        } else{
            throw new IllegalArgumentException();
        }
        
        if(this.userAccount.equals(accountToDelete)){
            System.out.println("This is the current account. Type 'Y' to confirm");
            String userInput = this.keyboardInput.nextLine();
            if(userInput.equals("Y")){
                this.userAccounts.remove(accountToDelete);
                this.userAccount = this.userAccounts.get(0);
            }else{
                return;
            }
        }
        this.userAccounts.remove(accountToDelete);

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
