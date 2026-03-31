package main.MenuOptions;

import main.AppContext;

public class ViewHistoryOption implements IMenuOption {
    public String getDisplayString() {
        return "View account history";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();
        ctx.getUserAccount().getTransactions().displayTransactions();
        System.out.println();
    }
}
