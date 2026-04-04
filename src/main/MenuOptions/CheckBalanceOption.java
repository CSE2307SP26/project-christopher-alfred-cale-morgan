package main.MenuOptions;

import main.AppContext;

public class CheckBalanceOption implements IMenuOption {
    public String getDisplayString() {
        return "Check Account Balance";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();

        if (ctx.getSelectedAccount() == null) throw new IllegalStateException("No user logged in");
        System.out.println("Current balance: $" + ctx.getSelectedAccount().getBalance());
    }
}
