package main.MenuOptions;

import main.AppContext;

public class CheckBalanceOption implements IMenuOption {
    public String getDisplayString() {
        return "Check Account Balance";
    }

    public void execute(AppContext ctx) {
        if (ctx.userAccount == null) throw new IllegalStateException("No user logged in");
        System.out.println("Current balance: $" + ctx.userAccount.getBalance());
    }
}
