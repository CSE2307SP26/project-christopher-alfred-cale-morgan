package main;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import main.MenuOptions.*;
import main.Utils.InputUtils;

public class MainMenu {
    private AppContext appContext;
    private List<IMenuOption> options;

    public MainMenu() {
        appContext = AppContext.getInstance();

        appContext.setBankAccounts(new BankAccounts());
        appContext.setUserAccount(appContext.getBankAccounts().createAccount()); // create user's account

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

        // TODO: Add a MenuOption implementation to switch user accounts
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
        int selection = InputUtils.getIntUntil("Please make a selection: ",
        "Please make a selection between 1 and " + (options.size() + 1),
        i -> (i >= 1 && i <= options.size() + 1)
        );
        return selection;
    }

    public void processInput(int selection) {
        //Basically if statement, add cases for each new thing we add
        if (selection > 0 && selection <= options.size()) {
            try {
                options.get(selection - 1).execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if(selection == options.size() + 1) //Leave exit at the end
                System.out.println("Exiting!");
        }

    public int getNumAccounts() {
        return appContext.getBankAccounts().getNumAccounts();
    }

    public void run() {
        int selection = -1;
        while(selection != options.size() + 1) {
            displayOptions();
            selection = getUserSelection();
            processInput(selection);
        }
    }

    public AppContext getAppContext() {
        return appContext;
    }

    public List<IMenuOption> getMenuOptions() {
        return options;
    }

    public static void main(String[] args) {
        MainMenu bankApp = new MainMenu();
        bankApp.run();
    }
}
