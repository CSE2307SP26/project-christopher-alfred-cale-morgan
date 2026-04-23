package main.Users;

import java.util.ArrayList;
import java.util.List;

import main.UserLimitRequest;
import main.UserRequestManager;
import main.UserGrantRequest;

public class User {

    private String username; 
    private String password; // TODO: PW unsecure

    private UserRole role;

    private List<Integer> accountIds;

    private UserRequestManager requests;

    protected User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.requests = new UserRequestManager();
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

    public UserRequestManager getUserRequests() {
        return requests;
    }

    public void addRequestLimit(UserLimitRequest req) {
        this.requests.addRequest(req);
    }

    public void finishRequestLimit(UserLimitRequest req) {
        this.requests.removeRequest(req);
    }

    public void addRequestGrant(UserGrantRequest req) {
        this.requests.addRequest(req);
    }

    public void finishRequestGrant(UserGrantRequest req) {
        this.requests.removeRequest(req);
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
