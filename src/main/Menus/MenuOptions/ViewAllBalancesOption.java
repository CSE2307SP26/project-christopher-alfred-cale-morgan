package main.Menus.MenuOptions;


import main.AppContext;
import main.BankAccount;
import main.BankAccounts;
import main.Users.User;
import main.Users.UserRole;
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
            return;
        }

        for(User user : UserService.getUsers().values()){
            if(user.getRole() == UserRole.Administrator){
                continue;
            }
            System.out.println("Customer: " + user.getUsername());
            if(user.getAccountIds().size() == 0){
                System.out.println("    No active accounts");
            }
            for(int id : user.getAccountIds()){
                BankAccount account = allAccounts.getAccount(id);
                System.out.println("    Account #" + account.getId() + ": $" + account.getBalance());
                //TODO: Once we implement different account types, it may be useful to include that information in this string too
            }
            System.out.println();
        }
    }
}
