package main.Menus.MenuOptions;

import main.AppContext;
import main.Utils.InputUtils;

public class FreezeOption implements IMenuOption {
    public String getDisplayString() {
        return "Freeze/Unfreeze Account";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();
        if(ctx.getSelectedAccount() == null) {
            System.out.println("No accounts yet, please make one first.");
            return;
        }
        
        if(ctx.getSelectedAccount().getFrozen())
        System.out.println("Successfully Unfrozen");
        else
        System.out.println("Successfully Frozen");

        ctx.getSelectedAccount().freeze();
    }
}
