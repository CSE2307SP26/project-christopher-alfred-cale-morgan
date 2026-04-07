package main.Menus.MenuOptions;

import main.AppContext;
import main.Users.User;
import main.Users.UserService;
import main.Utils.InputUtils;

public class LoginOption implements IMenuOption {

    @Override
    public String getDisplayString() {
        return "Login (Existing Users)";
    }

    @Override
    public void execute() {
        String username = InputUtils.getString("Username: ");
        String password = InputUtils.getPassword("Password: ");
        User authenticatedUser = UserService.getInstance().authenticate(username, password);
        if(authenticatedUser != null) 
            AppContext.getInstance().setCurrentUser(authenticatedUser);
        else
            System.out.println("Could not complete login, username or password is incorrect.");
    }
}
