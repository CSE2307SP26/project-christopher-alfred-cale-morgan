package main.Menus.MenuOptions;


import main.AppContext;
import main.BankAccount;
import main.BankAccountManager;
import main.Users.User;
import main.Users.UserRole;
import main.Users.UserService;
import main.Utils.InputUtils;

public class UserViewAllBalancesOption implements IMenuOption {
    public String getDisplayString() {
        return "View All Account Balances";
    }

    public void execute() {
        User currentUser = AppContext.getInstance().getCurrentUser();
        if(currentUser.getAccountIds().isEmpty()) {
            System.out.println("No accounts yet, please make one first.");
            return;
        }
        double sumBalance = 0.0;
        for(int accountId : currentUser.getAccountIds()) {
            System.out.println(AppContext.getInstance().getAllAccounts().getAccount(accountId).getAccountType() + " | Account ID: " + accountId + " | Balance: " + AppContext.getInstance().getAllAccounts().getAccount(accountId).getBalance());
            sumBalance += AppContext.getInstance().getAllAccounts().getAccount(accountId).getBalance();
        }
        System.out.println();

        System.out.println("Total Balance Across Accounts | " + sumBalance);

    }
}
