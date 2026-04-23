package main.Menus.MenuOptions;

import main.AppContext;
import main.BankAccount;
import main.UserLimitRequest;
import main.Utils.InputUtils;

public class RequestWithdrawlLimitOption implements IMenuOption {
    
    public String getDisplayString() {
        return "Request Withdrawl Limit to Account";
    }


    //this does not implement chosing a different account than the one that the user is currently on...TODO

    public void execute() {
        AppContext ctx = AppContext.getInstance();
        BankAccount curr = ctx.getSelectedAccount();
        if(curr == null) {
            System.out.println("No accounts yet, please make one first.");
            return;
        }
        

        double amount = InputUtils.getDoubleUntil("Enter new limit to be: ",
            "Please enter a positive amount",
            d->d > 0);

        UserLimitRequest req = new UserLimitRequest(curr.getId(), amount);
        ctx.getCurrentUser().addRequestLimit(req);

        System.out.println("Request Made");
        
    }


}
