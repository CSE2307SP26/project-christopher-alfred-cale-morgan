package main.Menus;

import main.AppContext;
import main.Menus.MenuOptions.*;

public class MainMenu extends AbstractMenu {
    private AppContext appContext;

    public MainMenu() {
        super("2307 Bank App - Main Menu");

        appContext = AppContext.getInstance();

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
        return super.shouldKeepRunning() && appContext.getCurrentUser() != null;
    }

    public int getNumAccounts() {
        return appContext.getAllAccounts().getNumAccounts();
    }

    public AppContext getAppContext() {
        return appContext;
    }
}
