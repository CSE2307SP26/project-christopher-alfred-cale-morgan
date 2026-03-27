package main.MenuOptions;

import main.AppContext;

public class WithdrawOption implements IMenuOption {
    public String getDisplayString() {
        return "Make a withdrawal";
    }

    public void execute(AppContext ctx) {
        double withdrawAmount = -1;
        while(withdrawAmount < 0) {
            System.out.print("How much would you like to withdraw: ");
            withdrawAmount = ctx.keyboardInput.nextInt();
        }
        ctx.userAccount.withdraw(withdrawAmount);
        System.out.println("Successfully Withdrew $" + withdrawAmount);
    }
}
