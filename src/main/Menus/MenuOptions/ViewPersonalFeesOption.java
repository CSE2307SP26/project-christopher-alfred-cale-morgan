package main.Menus.MenuOptions;

import main.AppContext;

public class ViewPersonalFeesOption implements IMenuOption {
    public String getDisplayString() {
        return "View personal fees";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();
        if(ctx.getSelectedAccount() == null) {
            System.out.println("No accounts yet, please make one first.");
            return;
        }
        System.out.println("\n Fees for Account #" + ctx.getSelectedAccount().getId() + ": $" + ctx.getSelectedAccount().getFees());
        System.out.println();
    }
}
