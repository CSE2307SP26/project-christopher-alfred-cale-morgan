package main.Menus.MenuOptions;

import main.Utils.InputUtils;
import main.AppContext;
import main.BankAccount;

public class ChangeAccountNicknameOption implements IMenuOption {
    public String getDisplayString() {
        return "Change Account Nickname";
    }

    public void execute() {
        BankAccount currentAccount = AppContext.getInstance().getSelectedAccount();
        if (currentAccount == null) {
            System.out.println("Please create or select an account to change the nickname.");
            return;
        }
        System.out.println("Current account nickname: " + currentAccount.getAccountNickname());
        String newAccountNickname = InputUtils.getString("Enter new nickname: ");
        currentAccount.setAccountNickname(newAccountNickname);
        System.out.println("Updated nickname for account #" + currentAccount.getId() + " to \"" + newAccountNickname + "\".");
    }
    
}
