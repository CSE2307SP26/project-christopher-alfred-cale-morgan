package main.MenuOptions;

import main.AppContext;
import main.BankAccount;

public class CreateFeesOption implements IMenuOption {
    public String getDisplayString() {
        return "Create Fees (Admin)";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();
        if(ctx.getUserAccount().getAdminStatus()) {
            int otherId;
            System.out.print("Enter account ID to add fees to: ");
            otherId = ctx.getKeyboardInput().nextInt();
            BankAccount target = ctx.getBankAccounts().getAccount(otherId);

            if (target == null) {
                throw new IllegalArgumentException("Target account cannot be found.");
            }

            double addedFee = -1;
            while (addedFee <= 0) {
                System.out.print("How much would you like to add to their fees: ");
                addedFee = ctx.getKeyboardInput().nextDouble();
            }

            target.addFees(addedFee);
        } else {
            throw new UnsupportedOperationException("Account does not have admin status.");
        }
    }
}
