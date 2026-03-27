package main.MenuOptions;

import main.AppContext;
import main.BankAccount;

public class AddAccountOption implements IMenuOption {
    public String getDisplayString() {
        return "Add a new account";
    }

    public void execute(AppContext ctx) {
        BankAccount newAccount = ctx.bankAccounts.createAccount();
        //TODO We'll need a way to track which accounts are owned by who?
    }
}
