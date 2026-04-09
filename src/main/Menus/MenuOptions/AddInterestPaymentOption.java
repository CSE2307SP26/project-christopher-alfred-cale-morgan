package main.Menus.MenuOptions;

import main.AppContext;
import main.BankAccount;
import main.Users.UserRole;
import main.Utils.InputUtils;

public class AddInterestPaymentOption implements IMenuOption {
    public String getDisplayString() {
        return "Add interest payment (Admin)";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();

        if(ctx.getCurrentUser().getRole() == UserRole.Administrator) {
            int otherId = InputUtils.getInt("Enter account ID to collect from: ");
            BankAccount target = ctx.getAllAccounts().getAccount(otherId);

            if (target == null) {
                throw new IllegalArgumentException("Target account cannot be found.");
            }

            target.payInterest();
        } else {
            throw new UnsupportedOperationException("Account does not have admin status.");
        }
    }
}
