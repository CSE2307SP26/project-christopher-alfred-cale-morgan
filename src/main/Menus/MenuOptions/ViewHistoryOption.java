package main.Menus.MenuOptions;

import main.AppContext;

public class ViewHistoryOption implements IMenuOption {
    public String getDisplayString() {
        return "View account history";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();
        if(ctx.getCurrentUser().getAccountIds().isEmpty()) {
            System.out.println("No accounts yet, please make one first.");
            return;
        }
        else if(ctx.getSelectedAccount() == null) {
            System.out.println("Please select an account first.");
            return;
        }
        ctx.getSelectedAccount().getTransactions().displayTransactions();
        System.out.println();
    }
}
