package main.Menus.MenuOptions;

import main.AppContext;
import main.Utils.InputUtils;

public class DepositOption implements IMenuOption {
    public String getDisplayString() {
        return "Make a deposit";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();
        double amount = InputUtils.getDoubleUntil("How much would you like to deposit: ",
            "Please enter a positive deposit amount",
            d->d > 0);
        System.out.println("Successfully Deposited $" + amount);
        ctx.getSelectedAccount().deposit(amount);
    }
}
