package main.MenuOptions;

import main.AppContext;
import main.BankAccount;

public class TransferOption implements IMenuOption {
    public String getDisplayString() {
        return "Make a transfer";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();
        double transferAmount = -1;
        int otherId = -1;

        while (transferAmount <= 0) {
            System.out.print("How much would you like to transfer: ");
            transferAmount = ctx.getKeyboardInput().nextDouble();
        }

        System.out.print("Enter account ID to transfer to: ");
        otherId = ctx.getKeyboardInput().nextInt();

        BankAccount target = ctx.getBankAccounts().getAccount(otherId);

        if (target == null) {
            throw new IllegalArgumentException("Target account cannot be found.");
        }

        ctx.getUserAccount().transfer(transferAmount, target);
        System.out.println("Transfer of " + transferAmount + " to " + target.getId() + "successful!");
    }
}
