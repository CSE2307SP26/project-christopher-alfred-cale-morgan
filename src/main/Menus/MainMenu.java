package main.Menus;

import main.AppContext;
import main.Menus.MenuOptions.*;
import main.Users.UserService;

public class MainMenu extends AbstractMenu {
    private AppContext ctx;

    public MainMenu() {
        super("2307 Bank App - Main Menu", "Log out");

        ctx = AppContext.getInstance();

        addMenuOption(new CheckBalanceOption());
        addMenuOption(new DepositOption());
        addMenuOption(new WithdrawOption());
        addMenuOption(new TransferOption());
        addMenuOption(new AddAccountOption());
        addMenuOption(new ViewPersonalFeesOption());
        addMenuOption(new ViewHistoryOption());
        addMenuOption(new AddInterestPaymentOption());
        addMenuOption(new CreateFeesOption());
        addMenuOption(new CollectFeesOption());
        // TODO: Add a MenuOption implementation to switch user accounts
    }

    @Override
    protected boolean shouldKeepRunning() {
        return super.shouldKeepRunning() && ctx.getCurrentUser() != null;
    }

    @Override
    protected void onClose() {
        System.out.println("Logging out user...");
        UserService.getInstance().logoutUser();
    }

    public int getNumAccounts() {
        return ctx.getAllAccounts().getNumAccounts();
    }

    public AppContext getAppContext() {
        return ctx;
    }
}
