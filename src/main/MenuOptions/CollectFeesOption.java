package main.MenuOptions;

import main.AppContext;
import main.BankAccount;

public class CollectFeesOption implements IMenuOption {
    public String getDisplayString() {
        return "Collect fees (Admin)";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();
        if (ctx.userAccount.getAdminStatus()) {
            int otherId;
            System.out.print("Enter account ID to collect from: ");
            otherId = ctx.keyboardInput.nextInt();
            BankAccount target = ctx.bankAccounts.getAccount(otherId);

            if (target == null) {
                System.out.println("Account not found.");
                return;
            }

            target.payFee();
        } else {
            throw new IllegalCallerException("Account does not have admin status.");
        }
    }
}
