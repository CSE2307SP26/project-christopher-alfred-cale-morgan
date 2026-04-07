package main.Menus.MenuOptions;

import main.AppContext;

public class CheckBalanceOption implements IMenuOption {
    public String getDisplayString() {
        return "Check Account Balance";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();

        if (ctx.getSelectedAccount() == null) {
            System.out.println("No accounts yet, please make one first.");
        }
        else {
            System.out.println("Current balance: $" + ctx.getSelectedAccount().getBalance());
        }
    }
}
