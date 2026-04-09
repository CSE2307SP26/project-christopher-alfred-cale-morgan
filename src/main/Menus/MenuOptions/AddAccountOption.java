package main.Menus.MenuOptions;

import main.AppContext;
import main.BankAccount;
import main.Utils.InputUtils;

public class AddAccountOption implements IMenuOption {
    public String getDisplayString() {
        return "Add a new account";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();
        BankAccount newAccount;
        
        System.out.println("Checkings or Savings? (C/S)");
        
        String input = InputUtils.getStringUntil("Enter account type: ", "Please enter 'C' for Checkings or 'S' for Savings.", s -> {
            String lower = s.toLowerCase();
            return lower.equals("c") || lower.equals("s");
        });

        if (input.equalsIgnoreCase("c")) {
            newAccount = ctx.getAllAccounts().createCheckingAccount();
        } else {
            newAccount = ctx.getAllAccounts().createSavingsAccount();
        }

        ctx.getCurrentUser().addAccountId(newAccount.getId());
        ctx.setSelectedAccount(newAccount);

        System.out.println("Successfully opened new account (# " + newAccount.getId() + ")");
    }
}
