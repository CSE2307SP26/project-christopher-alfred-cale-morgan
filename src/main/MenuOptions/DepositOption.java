package main.MenuOptions;

import main.AppContext;

public class DepositOption implements IMenuOption {
    public String getDisplayString() {
        return "Make a deposit";
    }

    public void execute(AppContext ctx) {
        double depositAmount = -1;
        while(depositAmount < 0) {
            System.out.print("How much would you like to deposit: ");
            depositAmount = ctx.keyboardInput.nextInt();
        }
        System.out.println("Successfully Deposited $" + depositAmount);
        ctx.userAccount.deposit(depositAmount);
    }
}
