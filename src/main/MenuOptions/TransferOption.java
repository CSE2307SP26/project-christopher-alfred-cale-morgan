package main.MenuOptions;

import main.AppContext;
import main.BankAccount;
import main.Utils.InputUtils;

public class TransferOption implements IMenuOption {
    public String getDisplayString() {
        return "Make a transfer";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();

        double transferAmount = InputUtils.getDoubleUntil("How much would you like to transfer: ",
            "Please enter a positive amount that is less than or equal to your account balance.",
            d-> (d > 0 && ctx.getUserAccount().getBalance() >= d)
        );

        int otherId = InputUtils.getInt("Enter account ID to transfer to: ");
        BankAccount target = ctx.getBankAccounts().getAccount(otherId);

        if (target == null) {
            throw new IllegalArgumentException("Target account cannot be found.");
        }

        ctx.getUserAccount().transfer(transferAmount, target);
        System.out.println("Transfer of " + transferAmount + " to " + target.getId() + " successful!");
    }
}
