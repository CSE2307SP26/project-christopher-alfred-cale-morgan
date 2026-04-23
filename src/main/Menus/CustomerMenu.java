package main.Menus;

import main.AppContext;
import main.Menus.MenuOptions.*;
import main.Users.UserRole;
import main.Users.UserService;

public class CustomerMenu extends AbstractMenu {
    private AppContext ctx;

    public CustomerMenu() {
        super("2307 Bank App - Menu (Customer)", "Log out");

        ctx = AppContext.getInstance();

        addMenuOption(new CheckBalanceOption());
        addMenuOption(new DepositOption());
        addMenuOption(new WithdrawOption());
        addMenuOption(new TransferOption());
        addMenuOption(new AddAccountOption());
        addMenuOption(new ViewPersonalFeesOption());
        addMenuOption(new ViewHistoryOption());
        addMenuOption(new FreezeOption());
        addMenuOption(new RequestWithdrawlLimitOption());
        addMenuOption(new SelectAccountOption());
        addMenuOption(new UserViewAllBalancesOption());
        addMenuOption(new ChangePasswordOption());
        addMenuOption(new ChangeAccountNicknameOption());
        addMenuOption(new RequestWithdrawlLimitOption());
    }

    @Override
    protected boolean shouldKeepRunning() {
        return super.shouldKeepRunning() 
        && ctx.getCurrentUser() != null 
        && ctx.getCurrentUser().getRole() == UserRole.Customer;
    }

    @Override
    protected void onClose() {
        System.out.println("Logging out user...");
        UserService.getInstance().logoutUser();
    }
}
