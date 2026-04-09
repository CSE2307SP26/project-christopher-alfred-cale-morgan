package main.Users;

import java.util.ArrayList;
import java.util.List;

import main.UserLimitRequest;
import main.UserRequests;

public class User {

    private String username; 
    private String password; // TODO: PW unsecure

    private UserRole role;

    private List<Integer> accountIds;

    private UserRequests limitsRequested;

    protected User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.limitsRequested = new UserRequests();
        this.accountIds = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Integer> getAccountIds() {
        return accountIds;
    }

    public UserRequests getUserLimitsRequested() {
        return limitsRequested;
    }

    public void addRequestLimit(UserLimitRequest req) {
        this.limitsRequested.addRequest(req);
    }



    /***
     * Add an accountId to the user
     * @param id
     * @return true if the account id was added, false if the account was already in the accountId list
     */
    public boolean addAccountId(int id) { 
        if(!this.accountIds.contains(id)) {
            this.accountIds.add(id); 
            return true;
        } else {
            return false;
        }
    }
}
