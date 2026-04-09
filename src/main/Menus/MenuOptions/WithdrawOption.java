package main.Menus.MenuOptions;

import main.AppContext;
import main.Utils.InputUtils;

public class WithdrawOption implements IMenuOption {
    public String getDisplayString() {
        return "Make a withdrawal";
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
        double amount = InputUtils.getDoubleUntil("How much would you like to withdraw: ",
            "Please enter a positive amount less than or equal to your account balance",
            d -> (d > 0 && ctx.getSelectedAccount().getBalance() >= d)
        );
        ctx.getSelectedAccount().withdraw(amount);
        System.out.println("Successfully Withdrew $" + amount);
    }
}
