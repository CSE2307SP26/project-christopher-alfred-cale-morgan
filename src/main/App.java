package main;

import main.Menus.AbstractMenu;
import main.Menus.MainMenu;
import main.Users.User;
import main.Users.UserRole;
import main.Users.UserService;

public class App {
    public static void main(String[] args) {
        /*
            TODO: Login menu should go here
        */

        AppContext appContext = AppContext.getInstance();

        // Seed with new user until login method implemented
        UserService.getInstance().registerUser("customer", "password", UserRole.Customer);
        User defaultUser = UserService.getInstance().authenticate("customer", "password");
        
        BankAccount defaultAccount = appContext.getAllAccounts().createAccount();
        defaultUser.addAccountId(defaultAccount.getId());
        
        appContext.setCurrentUser(defaultUser);
        
        AbstractMenu currentMenu = new MainMenu();
        currentMenu.run();
    }
}
