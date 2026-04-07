package main.Menus;

import main.AppContext;
import main.Menus.MenuOptions.LoginOption;
import main.Menus.MenuOptions.RegisterOption;

public class LoginMenu extends AbstractMenu {

    public LoginMenu() {
        super("CSE2307 Bank App - Login", "Exit the app");
        addMenuOption(new LoginOption());
        addMenuOption(new RegisterOption());
    }

    @Override
    protected boolean shouldKeepRunning() {
    AppContext ctx = AppContext.getInstance();
       return !ctx.getExitCondition() && ctx.getCurrentUser() == null;
    }

    @Override
    protected void onClose() {
        System.out.println("Exiting the app...");
        AppContext.getInstance().setExitCondition(true);
    }
}