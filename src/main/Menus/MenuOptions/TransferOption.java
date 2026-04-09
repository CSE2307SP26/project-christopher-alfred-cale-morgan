package main.Menus.MenuOptions;

import main.AppContext;
import main.BankAccount;
import main.Utils.InputUtils;

public class TransferOption implements IMenuOption {
    public String getDisplayString() {
        return "Make a transfer";
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
        double transferAmount = InputUtils.getDoubleUntil("How much would you like to transfer: ",
            "Please enter a positive amount that is less than or equal to your account balance.",
            d-> (d > 0 && ctx.getSelectedAccount().getBalance() >= d)
        );

        int otherId = InputUtils.getInt("Enter account ID to transfer to: ");
        BankAccount target = ctx.getAllAccounts().getAccount(otherId);

        if (target == null) {
            throw new IllegalArgumentException("Target account cannot be found.");
        }

        ctx.getSelectedAccount().transfer(transferAmount, target);
        System.out.println("Transfer of $" + transferAmount + " to account #" + target.getId() + " successful!");
    }
}
