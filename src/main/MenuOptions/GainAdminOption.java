package main.MenuOptions;

import main.AppContext;

public class GainAdminOption implements IMenuOption {
    public String getDisplayString() {
        AppContext ctx = AppContext.getInstance();
        return ctx.getUserAccount().getAdminStatus() ? "Logout from admin access" : "Login with admin access";
    }

    public void execute() {
        //gain access to admin status for collecting fees...
        //TODO: Add password
        
        AppContext ctx = AppContext.getInstance();
        ctx.getUserAccount().setAdminStatus();
    }
}
