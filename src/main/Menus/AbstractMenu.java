package main.Menus;

import java.util.ArrayList;
import java.util.List;

import main.Menus.MenuOptions.IMenuOption;
import main.Utils.InputUtils;

public abstract class AbstractMenu {
    private List<IMenuOption> options = new ArrayList<>();
    private boolean isRunning;
    private String menuTitle;

    public AbstractMenu(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public void displayOptions() {
        System.out.println("\n--- " + menuTitle + " ---");

        for(int i = 1; i <= options.size(); i++) {
            // options start at 0 but menu starts at 1
            System.out.println((i) + ". " + options.get(i - 1).getDisplayString());
        }
        // last option should always be the exit option
        System.out.println((options.size() + 1) + ". Exit the app");
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
            System.out.println("Exiting " + menuTitle + "...");
            this.isRunning = false;
        }
    }

    // subclasses can redefine this to signal when they should close
    protected boolean shouldKeepRunning() {
        return this.isRunning;
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
