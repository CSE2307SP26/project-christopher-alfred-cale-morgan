package main.Menus.MenuOptions;

import main.AppContext;

public class ViewHistoryOption implements IMenuOption {
    public String getDisplayString() {
        return "View account history";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();
        ctx.getSelectedAccount().getTransactions().displayTransactions();
        System.out.println();
    }
}
