package main.Menus.MenuOptions;

import main.Utils.InputUtils;
import main.AppContext;
import main.Users.User;

public class SelectAccountOption implements IMenuOption {
    public String getDisplayString() {
        return "Select an account";
    }

    public void execute() {
        User currentUser = AppContext.getInstance().getCurrentUser();
        if(currentUser.getAccountIds().isEmpty()) {
            System.out.println("No accounts yet, please make one first.");
            return;
        }
        for(int accountId : currentUser.getAccountIds()) {
            System.out.println(AppContext.getInstance().getAllAccounts().getAccount(accountId).getAccountType() + " | Account ID: " + accountId);
        }
        System.out.println();

        int accountId = InputUtils.getIntUntil("Enter the account ID you want to select: ",
            "Please enter a valid account ID",
            id -> AppContext.getInstance().getCurrentUser().getAccountIds().contains(id));
        AppContext.getInstance().setSelectedAccount(AppContext.getInstance().getAllAccounts().getAccount(accountId));

        System.out.println("Selected account #" + accountId);

    }
    
}
