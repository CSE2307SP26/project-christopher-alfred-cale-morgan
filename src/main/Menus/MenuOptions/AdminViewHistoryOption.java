package main.Menus.MenuOptions;

import main.AppContext;
import main.BankAccount;
import main.Users.UserRole;
import main.Utils.InputUtils;

public class AdminViewHistoryOption implements IMenuOption {
    public String getDisplayString() {
        return "View Transaction History (Admin)";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();

        if(ctx.getCurrentUser().getRole() == UserRole.Administrator) {
            int otherId = InputUtils.getInt("Enter account ID to view from: ");
            BankAccount target = ctx.getAllAccounts().getAccount(otherId);

            if (target == null) {
                throw new IllegalArgumentException("Target account cannot be found.");
            }

            target.getTransactions().displayTransactions();
            System.out.println();
        } else {
            throw new UnsupportedOperationException("Account does not have admin status.");
        }
    }
}
