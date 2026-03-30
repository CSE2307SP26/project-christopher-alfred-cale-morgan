package main.MenuOptions;

import main.AppContext;
import main.BankAccount;

public class AddInterestPaymentOption implements IMenuOption {
    public String getDisplayString() {
        return "Add interest payment (Admin)";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();

        if(ctx.userAccount.getAdminStatus()) {
            int otherId;
            System.out.print("Enter account ID to collect from: ");
            otherId = ctx.keyboardInput.nextInt();
            BankAccount target = ctx.bankAccounts.getAccount(otherId);

            if (target == null) {
                throw new IllegalArgumentException("Target account cannot be found.");
            }

            target.payInterest();
        } else {
            throw new UnsupportedOperationException("Account does not have admin status.");
        }
    }
}
