package main;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import main.MenuOptions.*;

public class MainMenu {
    private static AppContext appContext;
    private static List<IMenuOption> options;

    public MainMenu() {
        appContext = new AppContext();

        appContext.bankAccounts = new BankAccounts();
        appContext.userAccount = appContext.bankAccounts.createAccount(); // create user's account
        appContext.keyboardInput = new Scanner(System.in);

        options = new ArrayList<>();
        options.add(new CheckBalanceOption());
        options.add(new DepositOption());
        options.add(new WithdrawOption());
        options.add(new TransferOption());
        options.add(new ViewPersonalFeesOption());
        options.add(new ViewHistoryOption());
        options.add(new GainAdminOption());
        options.add(new AddInterestPaymentOption());
        options.add(new CreateFeesOption());
        options.add(new CollectFeesOption());
    }

    public void displayOptions() {
        System.out.println("Welcome to the 237 Bank App!");

        for(int i = 1; i <= options.size(); i++) {
            // options start at 0 but menu starts at 1
            System.out.println((i) + ". " + options.get(i - 1).getDisplayString());
        }
        // last option should always be the exit option
        System.out.println((options.size() + 1) + ". Exit the app");
    }

    public int getUserSelection() {
        int selection = -1;
        while(selection < 1 || selection > options.size() + 1) {
            System.out.print("Please make a selection: ");
            selection = appContext.keyboardInput.nextInt();
        }
        appContext.currentInput = selection;
        return selection;
    }

    public void processInput(int selection) {
        //Basically if statement, add cases for each new thing we add
        if (selection > 0 && selection < options.size())
            options.get(selection - 1).execute(appContext);
        if(selection == options.size() + 1) //Leave exit at the end
                System.out.println("Exiting!");
        }

    public int getNumAccounts() { //TODO: Fix this one

        return appContext.bankAccounts.getNumAccounts(); //this.userAccounts.size();
    }
    /*TODO
    Add a method to switch user accounts
    Update Main Menu options to include changing accounts */

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

    public void run() {
        int selection = -1;
        while(selection != options.size() + 1) {
            displayOptions();
            selection = getUserSelection();
            processInput(selection);
        }
    }

    public static void main(String[] args) {
        MainMenu bankApp = new MainMenu();
        bankApp.run();
    }
}
