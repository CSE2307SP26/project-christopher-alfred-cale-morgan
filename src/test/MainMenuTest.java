package test;

import main.MainMenu;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.Test;

public class MainMenuTest {
    @Test
    public void testNewAccount() {
        MainMenu testApp = new MainMenu();
        testApp.AddAccount();
        assertEquals(2, testApp.getNumAccounts());
    }
}
