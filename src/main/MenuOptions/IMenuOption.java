package main.MenuOptions;

import main.AppContext;

public interface IMenuOption {
    public String getDisplayString();
    public void execute(AppContext ctx);
}