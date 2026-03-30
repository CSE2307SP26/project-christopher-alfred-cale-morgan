package main.MenuOptions;

import main.AppContext;

public class ViewPersonalFeesOption implements IMenuOption {
    public String getDisplayString() {
        return "View personal fees";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();
        System.out.println("\n Fees for User " + ctx.getUserAccount().getId() +": $" + ctx.getUserAccount().getFees());
        System.out.println();
    }
}
