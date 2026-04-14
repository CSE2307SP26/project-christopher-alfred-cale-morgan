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
    private BankAccountManager allAccounts = new BankAccountManager();
    private boolean exitCondition = false; // signals when to close the application

    public User getCurrentUser() {
        return currentUser;
    }

    /***
     * Should only be used by UserService
     * @param user
     */

    public void setCurrentUser(User user) {
        currentUser = user;
        selectedAccount = null; // reset selected account whenever we switch users
    }

    public BankAccount getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(BankAccount userAccount) {
        this.selectedAccount = userAccount;
    }

    public BankAccountManager getAllAccounts() {
        return allAccounts;
    }

    public boolean getExitCondition() {
         return this.exitCondition; 
    }
    
    public void setExitCondition(boolean newExitCondition) {
        this.exitCondition = newExitCondition;
    }

    public void reset() {
        this.currentUser = null;
        this.selectedAccount = null;
        this.allAccounts = new BankAccountManager();

        this.exitCondition = false;
    }
}
