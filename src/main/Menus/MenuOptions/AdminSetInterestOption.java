package main.Menus.MenuOptions;

import main.AppContext;
import main.BankAccount;
import main.Users.UserRole;
import main.Utils.InputUtils;

public class AdminSetInterestOption implements IMenuOption {
    public String getDisplayString() {
        return "Change Account Interest Rate (Admin)";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();

        if(ctx.getCurrentUser().getRole() == UserRole.Administrator) {
            int otherId = InputUtils.getInt("Enter account ID to adjust interest rate for: ");
            BankAccount target = ctx.getAllAccounts().getAccount(otherId);

            if (target == null) {
                throw new IllegalArgumentException("Target account cannot be found.");
            }

            double newInterestRate = InputUtils.getDoubleUntil(
                "Enter new interest rate (x.xx): ",
                "Interest rate should be between 0.00% and 100.0%",
                    rate -> rate >= 0 && rate < 100
            );

            target.setInterestRate(newInterestRate);
            System.out.println("Successfully updated account #" + target.getId() + " interest rate to " + newInterestRate + "%");

        } else {
            throw new UnsupportedOperationException("Account does not have admin status.");
        }
    }
}
