package main.Menus.MenuOptions;

import main.AppContext;
import main.Utils.InputUtils;

public class DepositOption implements IMenuOption {
    public String getDisplayString() {
        return "Make a deposit";
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
        
        double amount = InputUtils.getDoubleUntil("How much would you like to deposit: ",
            "Please enter a positive deposit amount",
            d->d > 0);
        ctx.getSelectedAccount().deposit(amount);
    }
}
