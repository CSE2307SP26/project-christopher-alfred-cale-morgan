package main.MenuOptions;

import main.AppContext;
import main.BankAccount;

public class AddInterestPaymentOption implements IMenuOption {
    public String getDisplayString() {
        return "Add interest payment (Admin)";
    }

    public void execute(AppContext ctx) {
        if(ctx.userAccount.getAdminStatus()) {
            int otherId;
            System.out.print("Enter account ID to collect from: ");
            otherId = ctx.keyboardInput.nextInt();
            BankAccount target = ctx.bankAccounts.getAccount(otherId);

            if (target == null) {
                System.out.println("Account not found.");
                return;
            }

            target.payInterest();
        } else {
            throw new IllegalCallerException("Account does not have admin status.");
        }
    }
}
