package main.Menus.MenuOptions;

import main.AppContext;
import main.Utils.InputUtils;

public class ChangePasswordOption implements IMenuOption {

    @Override
    public String getDisplayString() {
        return "Change Password";
    }

    @Override
    public void execute() {
        AppContext ctx = AppContext.getInstance();
        String currentPassword = InputUtils.getPassword("Current Password: ");
        
        if(!currentPassword.equals(ctx.getCurrentUser().getPassword())){
            System.out.println("Incorrect Password");
            return;
        }else{
            String newPassword = InputUtils.getPassword("Set New Password: ");
            ctx.getCurrentUser().setPassword(newPassword);
            System.out.println("Password Successfully Changed");
            return;
        }
    }
}
