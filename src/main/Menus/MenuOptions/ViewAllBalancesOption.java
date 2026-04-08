package main.Menus.MenuOptions;


import main.AppContext;
import main.BankAccount;
import main.BankAccounts;
import main.Users.User;
import main.Users.UserService;

public class ViewAllBalancesOption implements IMenuOption {
    public String getDisplayString() {
        return "View All Account Balances (Admin)";
    }

    public void execute(){
        AppContext ctx = AppContext.getInstance();
        BankAccounts allAccounts = ctx.getAllAccounts();
        if(allAccounts.getNumAccounts() == 0){
            System.out.println("There are no active accounts right now.");
        }

        for(User user : UserService.getUsers().values()){
            System.out.println(user.getUsername());
            for(int id : user.getAccountIds()){
                BankAccount account = allAccounts.getAccount(id);
                System.out.println("Account #" + account.getId() + ": $" + account.getBalance());
                //TODO: Once we implement different account types, it may be useful to include that information in this string too
                System.out.println();
            }
        }
    }
}
