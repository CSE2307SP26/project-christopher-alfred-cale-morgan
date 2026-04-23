package main.Menus.MenuOptions;

import main.AppContext;
import main.BankAccount;
import main.BankAccountManager;
import main.Users.User;
import main.Users.UserRole;
import main.Users.UserService;
import main.Utils.InputUtils;

public class AdminCloseAccount implements IMenuOption {
    public String getDisplayString() {
        return "Close Customer's Account (Admin)";
    }

    public void execute() {
        AppContext ctx = AppContext.getInstance();

        if (ctx.getCurrentUser().getRole() == UserRole.Administrator) {
            BankAccountManager allAccounts = ctx.getAllAccounts();
            if (allAccounts.getNumAccounts() == 0) {
                System.out.println("There are no active accounts right now.");
                return;
            }

            ViewAllBalancesOption displayAccounts = new ViewAllBalancesOption();
            displayAccounts.execute();
            int accountNumber = InputUtils.getInt("Select an account #: ");
            BankAccount closedAccount = ctx.getAllAccounts().deleteAccount(accountNumber);
            if (closedAccount == null) {
                System.out.println("Invalid Account ID");
            } else {
                for (User user : UserService.getUsers().values()) {
                    if (user.getRole() == UserRole.Administrator || user.getAccountIds().size() == 0) {
                        continue;
                    }
                    for (int id : user.getAccountIds()) {
                        if (id == accountNumber) {
                            user.removeAccount(accountNumber);
                            return;
                        }
                    }
                }
            }
        }else{
            throw new UnsupportedOperationException("Account does not have admin status.");
        }

    }
}