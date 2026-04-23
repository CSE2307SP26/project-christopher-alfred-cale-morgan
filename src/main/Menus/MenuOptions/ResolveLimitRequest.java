package main.Menus.MenuOptions;

import main.AppContext;
import main.BankAccountManager;
import main.UserLimitRequest;
import main.UserRequestManager;
import main.Users.User;
import main.Users.UserRole;
import main.Users.UserService;
import main.Utils.InputUtils;

public class ResolveLimitRequest implements IMenuOption {

    public String getDisplayString() {
        return "Resolve limit requests";
    }



    public void execute() {

        AppContext ctx = AppContext.getInstance();

        if (ctx.getCurrentUser().getRole() == UserRole.Administrator) {
            BankAccountManager allAccounts = ctx.getAllAccounts();
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
                    System.out.println("No active accounts");
                }
                
                UserRequestManager listRequests = user.getUserLimitsRequested();

                if(listRequests.getAllRequests().isEmpty()) {
                    System.out.println("This user has no request");
                    continue;
                }
                
                for(UserLimitRequest request : listRequests.getAllRequests()){
                
                    System.out.println("Request for account Id: " + request.getAccountIdRequested() + " of amount: " + request.getRequestedLimit());

                    String resolution = InputUtils.getStringUntil("Would you like to approve this limit? (y/n)", 
                    "please enter 'y' or 'n'", 
                    s->s.equals("y") || s.equals("n")); //TODO: add option to stop adn return

                    if(resolution == "n") {
                        listRequests.removeRequest(request);
                    } else {
                        System.out.println("Limit Approved!");
                        allAccounts.getAccount(request.getAccountIdRequested()).setLimit(request.getRequestedLimit());
                        listRequests.removeRequest(request);
                    }
                }
            }     
            System.out.println();
        
        } else {
            throw new UnsupportedOperationException("Account does not have admin status.");
        }
        
        
    }
    
}
