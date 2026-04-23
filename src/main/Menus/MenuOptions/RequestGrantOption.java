package main.Menus.MenuOptions;

import main.AppContext;
import main.BankAccount;
import main.UserGrantRequest;
import main.UserLimitRequest;
import main.Utils.InputUtils;

public class RequestGrantOption implements IMenuOption {
    
    public String getDisplayString() {
        return "Request grant to account";
    }


    //this does not implement chosing a different account than the one that the user is currently on...TODO

    public void execute() {
        AppContext ctx = AppContext.getInstance();
        BankAccount curr = ctx.getSelectedAccount();
        if(curr == null) {
            System.out.println("No accounts yet, please make one first.");
            return;
        }
        

        double amount = InputUtils.getDoubleUntil("Enter request amount: ",
            "Please enter a positive amount",
            d->d > 0);

        UserGrantRequest req = new UserGrantRequest(curr.getId(), amount);
        ctx.getCurrentUser().addRequestGrant(req);

        System.out.println("Request Made");
        
    }
