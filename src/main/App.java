package main;

import main.Menus.AbstractMenu;
import main.Menus.AdminMenu;
import main.Menus.CustomerMenu;
import main.Menus.LoginMenu;
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
                if(ctx.getCurrentUser().getRole() == UserRole.Administrator) {
                    currentMenu = new AdminMenu();
                } else {
                currentMenu = new CustomerMenu();
                }
            }
            currentMenu.run();
        }
    }
}
