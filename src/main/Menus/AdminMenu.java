package main.Menus;

import main.AppContext;
import main.Menus.MenuOptions.*;
import main.Users.UserRole;
import main.Users.UserService;

public class AdminMenu extends AbstractMenu {
    private AppContext ctx;

    public AdminMenu() {
        super("2307 Bank App - Menu (Admin)", "Log out");

        ctx = AppContext.getInstance();

        addMenuOption(new AddInterestPaymentOption());
        addMenuOption(new CreateFeesOption());
        addMenuOption(new CollectFeesOption());
        addMenuOption(new AdminViewHistoryOption());
        addMenuOption(new ViewAllBalancesOption());
        addMenuOption(new ResolveLimitRequest());
    }

    @Override
    protected boolean shouldKeepRunning() {
        return super.shouldKeepRunning() 
        && ctx.getCurrentUser() != null 
        && ctx.getCurrentUser().getRole() == UserRole.Administrator;
    }

    @Override
    protected void onClose() {
        System.out.println("Logging out user...");
        UserService.getInstance().logoutUser();
    }
}
