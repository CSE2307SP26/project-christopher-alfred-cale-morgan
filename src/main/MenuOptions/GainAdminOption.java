package main.MenuOptions;

import main.AppContext;

public class GainAdminOption implements IMenuOption {
    public String getDisplayString() {
        return "Gain admin access";
    }

    public void execute(AppContext ctx) {
        //gain access to admin status for collecting fees...
        //TODO:Add password
        ctx.userAccount.setAdminStatus();
    }
}
