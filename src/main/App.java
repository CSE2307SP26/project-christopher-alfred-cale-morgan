package main;

import main.Menus.AbstractMenu;
import main.Menus.LoginMenu;
import main.Menus.MainMenu;
import main.Users.UserRole;
import main.Users.UserService;

public class App {
    public static void main(String[] args) {
        AppContext ctx = AppContext.getInstance();
        AbstractMenu currentMenu;

        // Seed an admin account for testing
        UserService.getInstance().registerUser("admin", "password", UserRole.Administrator);

        while(!ctx.getExitCondition()) {
            if (ctx.getCurrentUser() == null) {
                currentMenu = new LoginMenu();
            } else {
                currentMenu = new MainMenu();
            }
            currentMenu.run();
        }
    }
}
