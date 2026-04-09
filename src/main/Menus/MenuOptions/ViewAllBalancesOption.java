package main.Menus.MenuOptions;


import main.AppContext;
import main.BankAccount;
import main.BankAccounts;
import main.Users.User;
import main.Users.UserRole;
import main.Users.UserService;
import main.Utils.InputUtils;

public class ViewAllBalancesOption implements IMenuOption {
    public String getDisplayString() {
        return "View All Account Balances (Admin)";
    }

    public void execute(){
        AppContext ctx = AppContext.getInstance();

        if (ctx.getCurrentUser().getRole() == UserRole.Administrator) {
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
                    System.out.println("    " + account.getAccountType() + " | Account #" + account.getId() + ": $" + account.getBalance());
                }
                System.out.println();
            }
        } else {
            throw new UnsupportedOperationException("Account does not have admin status.");
        }
        
    }
}
