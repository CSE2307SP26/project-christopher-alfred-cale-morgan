package main;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MainMenu {

    private static final int EXIT_SELECTION = 5; //These should likely remain equal but I kept them seperate
	private static final int MAX_SELECTION = 5; //Set to the final number. Increase if you modify displayOptions().

    private BankAccounts bankAccounts;
	private BankAccount userAccount;
    private Scanner keyboardInput;

    public MainMenu() {
        this.bankAccounts = new BankAccounts();
        this.userAccount = bankAccounts.createAccount(); // create user's account
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() { //TODO: We need to add the Create Account here
        System.out.println("Welcome to the 237 Bank App!");
        
        System.out.println("1. Make a deposit");
        System.out.println("2. Make a withdrawal");
        System.out.println("3. Make a transfer");
        System.out.println("4. View your history");
        System.out.println("5. Exit the app");
    }

    public int getUserSelection(int max) {
        int selection = -1;
        while(selection < 1 || selection > max) {
            System.out.print("Please make a selection: ");
            selection = keyboardInput.nextInt();
        }
        return selection;
    }

    public void processInput(int selection) { //Basically if statement, add cases for each new thing we add
        switch (selection) {
            case 1:
                performDeposit();
                break;
            case 2:
                performWithdrawal();
                break;
            case 3:
                performTransfer();
                break;
            case 4:
                performViewHistory();
                break;
            case 5: //Leave exit at the bottom
                System.out.println("Exiting!");
                break;
        }
    }

    public void performDeposit() {
        double depositAmount = -1;
        while(depositAmount < 0) {
            System.out.print("How much would you like to deposit: ");
            depositAmount = keyboardInput.nextInt();
        }
        System.out.println("Successfully Deposited" + depositAmount);
        userAccount.deposit(depositAmount);
    }

    public void performWithdrawal() {
        double withdrawAmount = -1;
        while(withdrawAmount < 0) {
            System.out.print("How much would you like to withdraw: ");
            withdrawAmount = keyboardInput.nextInt();
        }
        userAccount.withdraw(withdrawAmount);
        System.out.println("Successfully Withdrew " + withdrawAmount);
    }

    public void addAccount() {
        BankAccount newAccount = new BankAccount(1);
    //TODO this.userAccounts.add(newAccount); We'll need a way to track which accounts are owned by who?    }
    }
    public int getNumAccounts() { //TODO: Fix this one
        return 1; //this.userAccounts.size();
    }

    /*TODO
    Add a method to switch user accounts
    Update Main Menu options to include changing accounts */


    public void performTransfer() {
        double transferAmount = -1;
        int otherId = -1;

        while (transferAmount <= 0) {
            System.out.print("How much would you like to transfer: ");
            transferAmount = keyboardInput.nextDouble();
        }

        System.out.print("Enter account ID to transfer to: ");
        otherId = keyboardInput.nextInt();

        BankAccount target = bankAccounts.getAccount(otherId);

        if (target == null) {
            System.out.println("Account not found.");
            return;
        }

        userAccount.transfer(transferAmount, target);
        System.out.println("Transfer of " + transferAmount + " to " + target.getId() + "successful!");
    }


    public void performViewHistory() {
        System.out.println("\n=== Transaction History ===");
        userAccount.getTransactions().displayTransactions();
        System.out.println();
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
