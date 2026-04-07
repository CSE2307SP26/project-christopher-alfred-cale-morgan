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
        if(user == null) { 
            selectedAccount = null;
            return;
        } 
        else if(!user.getAccountIds().isEmpty()) {
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

    public boolean getExitCondition() {
         return this.exitCondition; 
    }
    
    public void setExitCondition(boolean newExitCondition) {
        this.exitCondition = newExitCondition;
    }

    public void reset() {
        this.currentUser = null;
        this.selectedAccount = null;
        this.allAccounts = new BankAccounts();

        this.exitCondition = false;
    }
}
