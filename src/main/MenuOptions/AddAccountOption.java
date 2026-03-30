package main.MenuOptions;

import main.AppContext;

public class AddAccountOption implements IMenuOption {
    public String getDisplayString() {
        return "Add a new account";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();
        ctx.getBankAccounts().createAccount();
        //TODO We'll need a way to track which accounts are owned by who?
    }
}
