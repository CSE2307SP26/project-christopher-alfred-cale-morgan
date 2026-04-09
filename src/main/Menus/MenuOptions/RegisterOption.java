package main.Menus.MenuOptions;

import main.Users.UserRole;
import main.Users.UserService;
import main.Utils.InputUtils;

public class RegisterOption implements IMenuOption {

    @Override
    public String getDisplayString() {
        return "Register (New Users)";
    }

    @Override
    public void execute() {
        String username;
        String password;
        boolean result = false;
        do {
            username = InputUtils.getString("Username: ");
            password = InputUtils.getPassword("Password: ");
            result = UserService.getInstance().registerUser(username, password, UserRole.Customer);
            if (!result) {
                System.out.println("Username \"" + username + "\" is already taken, please try a different username.");
                if(!InputUtils.getConfirmation("Try again?")) break;
            }
        }
        while(!result);
        if(result)
            System.out.println("User \"" + username + "\" was sucessfully registered, you may now log in.");
    }
}
