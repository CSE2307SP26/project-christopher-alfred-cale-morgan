package main.Users;

import java.util.HashMap;
import java.util.Map;

import main.AppContext;

public class UserService {
    private UserService(){
        users = new HashMap<>();
    }
 
    private static UserService instance;
    public static UserService getInstance() {
        if(instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private static Map<String, User> users;

    /***
     * Creates a new user in the system
     * @param Username
     * @param Password (plaintext)
     * @param UserRole (Customer or Administrator)
     * @return false if the username is taken, else returns true
     */
    public boolean registerUser(String username, String password, UserRole role) {
        if (users.containsKey(username)) {
            return false;
        }

        User newUser = new User(username, password, role);
        users.put(username, newUser);
        return true;
    }

    /***
     * Takes user credentials and returns the User with the matching username/password
     * @param username
     * @param password (plaintext)
     * @return The User object with the given credentials, null if username does not exist or password does not match
     */
    public User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password))
            return user;
        return null;
    }

    /***
     * When logging out the user, menus should use this method instead of doing it manually.
     */
    public void logoutUser() {
        AppContext ctx = AppContext.getInstance();
        ctx.setCurrentUser(null);
    }

    // Allows for easier testing, should not actually be called. 
    public void reset() {
        users.clear();
    }

    // Should only be used by admin functions
    public static Map<String, User> getUsers(){
        return users;
    }
}
