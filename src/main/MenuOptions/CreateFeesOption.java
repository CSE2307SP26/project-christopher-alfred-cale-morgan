package main.MenuOptions;

import main.AppContext;
import main.BankAccount;
import main.Utils.InputUtils;

public class CreateFeesOption implements IMenuOption {
    public String getDisplayString() {
        return "Create Fees (Admin)";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();
        if(ctx.getUserAccount().getAdminStatus()) {
            int otherId = InputUtils.getInt("Enter account ID to add fees to: ");
            BankAccount target = ctx.getBankAccounts().getAccount(otherId);

            if (target == null) {
                throw new IllegalArgumentException("Target account cannot be found.");
            }

            double amount = InputUtils.getDoubleUntil("How much would you like to add to their fees: ",
                  "Please enter an amount greater than zero.", 
                  d->d >0);

            target.addFees(amount);
        } else {
            throw new UnsupportedOperationException("Account does not have admin status.");
        }
    }
}
