package main;

import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private static final int EXIT_SELECTION = 9; //These should likely remain equal but I kept them seperate
	private static final int MAX_SELECTION = 9; //Set to the final number. Increase if you modify displayOptions().

	
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
        System.out.println("2. Make a transfer");
        System.out.println("3. View your history");
        System.out.println("4. View your fees");
        System.out.println("5. Gain Admin Access");
        System.out.println("6. Collect Fees (Admin)");
        System.out.println("7. Create Fees (Admin)");
        System.out.println("8. View All Fees (Admin)"); //TODO:
        System.out.println("9. Exit the app");

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
                performTransfer();
                break;
            case 3:
                performViewHistory();
                break;
            case 4:
                viewPersonalFees();
                break;
            case 5:
                gainAdmin();
                break;
            case 6:
                collectFees();
                break;
            case 7:
                createFees();
                break;

            case 9: //Leave exit at the bottom
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

    public void addAccount() {
        BankAccount newAccount = bankAccounts.createAccount();
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
        userAccount.getTransactions().displayTransactions();
        System.out.println();
    }




    /*
    TODO
    Add a method to switch user accounts
    Update Main Menu options to include changing accounts 
    */



    /*
    TODO
    Add a method to switch user accounts
    Update Main Menu options to include changing accounts 
    */


    public void gainAdmin() { //gain access to admin status for collecting fees...TODO:Add password
        this.userAccount.setAdminStatus();
    }

    public void collectFees() {

        if(userAccount.getAdminStatus()) {
            int otherId;
            System.out.print("Enter account ID to collect from: ");
            otherId = keyboardInput.nextInt();
            BankAccount target = bankAccounts.getAccount(otherId);
            
            if (target == null) {
                System.out.println("Account not found.");
                return;
            }

            target.payFee();
        } else {
            throw new IllegalCallerException("Account does not have admin status.");
        }
    }

    public void createFees() {

        if(userAccount.getAdminStatus()) {
            int otherId;
            System.out.print("Enter account ID to add fees to: ");
            otherId = keyboardInput.nextInt();
            BankAccount target = bankAccounts.getAccount(otherId);
            
            if (target == null) {
                System.out.println("Account not found.");
                return;
            }

            double addedFee = -1;
            while (addedFee <= 0) {
                System.out.print("How much would you like to add to their fees: ");
                addedFee = keyboardInput.nextDouble();
            }

            target.addFees(addedFee);
            
        } else {
            throw new IllegalCallerException("Account does not have admin status.");
        }
    }

    public void viewPersonalFees() {
        System.out.println("\n Fees for User: " + userAccount.getId() +", -->" + userAccount.getFees());
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
