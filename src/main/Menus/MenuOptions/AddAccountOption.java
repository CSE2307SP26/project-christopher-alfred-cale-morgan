package main.Menus.MenuOptions;

import main.AppContext;
import main.BankAccount;

public class AddAccountOption implements IMenuOption {
    public String getDisplayString() {
        return "Add a new account";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();
        
        BankAccount newAccount = ctx.getAllAccounts().createAccount();

        ctx.getCurrentUser().addAccountId(newAccount.getId());
        ctx.setSelectedAccount(newAccount);

        System.out.println("Successfully opened new account (# " + newAccount.getId() + ")");
    }
}
