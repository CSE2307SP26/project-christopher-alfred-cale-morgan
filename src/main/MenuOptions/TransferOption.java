package main.MenuOptions;

import main.AppContext;
import main.BankAccount;

public class TransferOption implements IMenuOption {
    public String getDisplayString() {
        return "Make a transfer";
    }

    public void execute(AppContext ctx) {
        double transferAmount = -1;
        int otherId = -1;

        while (transferAmount <= 0) {
            System.out.print("How much would you like to transfer: ");
            transferAmount = ctx.keyboardInput.nextDouble();
        }

        System.out.print("Enter account ID to transfer to: ");
        otherId = ctx.keyboardInput.nextInt();

        BankAccount target = ctx.bankAccounts.getAccount(otherId);

        if (target == null) {
            System.out.println("Account not found.");
            return;
        }

        ctx.userAccount.transfer(transferAmount, target);
        System.out.println("Transfer of " + transferAmount + " to " + target.getId() + "successful!");
    }
}
