package main;

import main.Users.User;

public class AppContext {
    private static AppContext instance;
    private AppContext(){}
    public static AppContext getInstance() {
        if(instance == null) {
            instance = new AppContext();
        }

        return instance;
    }

    private User currentUser;
    private BankAccount selectedAccount;
    private BankAccounts allAccounts = new BankAccounts();

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;

        // select their first account if they have one
        if(!user.getAccountIds().isEmpty()) {
            selectedAccount = allAccounts.getAccount(user.getAccountIds().get(0));
        } else {
            selectedAccount = null;
        }
    }

    public BankAccount getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(BankAccount userAccount) {
        this.selectedAccount = userAccount;
    }

    public BankAccounts getAllAccounts() {
        return allAccounts;
    }
}
