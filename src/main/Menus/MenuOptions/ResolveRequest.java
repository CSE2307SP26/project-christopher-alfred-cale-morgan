package main.Menus.MenuOptions;

import java.util.Iterator;

import main.AppContext;
import main.BankAccountManager;
import main.UserRequest;
import main.UserRequestManager;
import main.Users.User;
import main.Users.UserRole;
import main.Users.UserService;
import main.Utils.InputUtils;

public class ResolveRequest implements IMenuOption {

    public String getDisplayString() {
        return "Resolve limit requests";
    }

    public void execute() {

        AppContext ctx = AppContext.getInstance();

        if (ctx.getCurrentUser().getRole() == UserRole.Administrator) {

            BankAccountManager allAccounts = ctx.getAllAccounts();

            if (allAccounts.getNumAccounts() == 0) {
                System.out.println("There are no active accounts right now.");
                return;
            }

            for (User user : UserService.getUsers().values()) {

                if (user.getRole() == UserRole.Administrator) {
                    continue;
                }

                System.out.println("Customer: " + user.getUsername());

                if (user.getAccountIds().size() == 0) {
                    System.out.println("No active accounts");
                }

                UserRequestManager listRequests = user.getUserRequests();

                if (listRequests.getAllRequests().isEmpty()) {
                    System.out.println("This user has no request");
                    continue;
                }

                Iterator<UserRequest> iterator = listRequests.getAllRequests().iterator();

                while (iterator.hasNext()) {
                    UserRequest request = iterator.next();

                    System.out.println(
                        request.getRequestType() + " request for account Id: "
                        + request.getAccountIdRequested()
                        + " of amount: "
                        + request.getRequestAmount()
                    );

                    String resolution = InputUtils.getStringUntil(
                        "Would you like to approve this request? (y/n)",
                        "please enter 'y' or 'n'",
                        s -> s.equals("y") || s.equals("n")
                    );

                    if (resolution.equals("y")) {
                        request.approve(allAccounts);
                        System.out.println(request.getRequestType() + " Approved!");
                    }

                    iterator.remove();
                }

                System.out.println();
            }

        } else {
            throw new UnsupportedOperationException("Account does not have admin status.");
        }
    }
}