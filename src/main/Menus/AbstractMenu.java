package main.Menus;

import java.util.ArrayList;
import java.util.List;

import main.AppContext;
import main.BankAccount;
import main.Menus.MenuOptions.IMenuOption;
import main.Utils.InputUtils;

public abstract class AbstractMenu {
    private List<IMenuOption> options = new ArrayList<>();
    private boolean isRunning;
    private String menuTitle;
    private String exitOptionText;
    private String nicknameString = "";

    public AbstractMenu(String menuTitle, String exitOptionText) {
        this.menuTitle = menuTitle;
        this.exitOptionText = exitOptionText;
    }

    public void displayOptions() {
        AppContext ctx = AppContext.getInstance();
        BankAccount selectedAccount = ctx.getSelectedAccount();
        if(selectedAccount != null) {
            if(!selectedAccount.getAccountNickname().equals(""))
            this.nicknameString = " (" + ctx.getSelectedAccount().getAccountNickname() + ")";
        } else {
            this.nicknameString = "";
        }
        System.out.println("\n--- " + menuTitle + " ---");
        System.out.println(
            ctx.getSelectedAccount() == null ? "No account selected" 
            : "Account #" + selectedAccount.getId() + this.nicknameString);
        for(int i = 1; i <= options.size(); i++) {

            // options start at 0 but menu starts at 1
            System.out.println((i) + ". " + options.get(i - 1).getDisplayString());
        }
        // last option should always be the exit option
        System.out.println((options.size() + 1) + ". " + exitOptionText);
    }

    public int getUserSelection() {
        return InputUtils.getIntUntil("Please make a selection: ",
        "Please make a selection between 1 and " + (options.size() + 1),
        i -> (i >= 1 && i <= options.size() + 1)
        );
    }

    public void processInput(int selection) {
        //Basically if statement, add cases for each new thing we add
        if (selection > 0 && selection <= options.size()) {
            try {
                options.get(selection - 1).execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else if(selection == options.size() + 1) {
            onClose();
        }
    }

    // for signaling when to close  
    protected boolean shouldKeepRunning() {
        return this.isRunning;
    }

    // executed when exit option is selected (different for login/main menus)
    protected void onClose() {
        System.out.println("Exiting " + menuTitle + "...");
        this.isRunning = false;
    }
    
    public void run() {
        this.isRunning = true;
        while(shouldKeepRunning()) {
            displayOptions();
            int selection = getUserSelection();
            processInput(selection);
        }
    }

    public List<IMenuOption> getMenuOptions() {
        return options;
    }

    public void addMenuOption(IMenuOption option) {
        this.options.add(option);
    }
}
