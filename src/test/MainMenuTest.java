package test;

import main.MainMenu;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class MainMenuTest {
    @Test
    public void testNewAccount() {
        MainMenu testApp = new MainMenu();
        testApp.addAccount();
        assertEquals(2, testApp.getNumAccounts());
    }

    @Test
    public void testCloseAccount() {
        MainMenu testApp = new MainMenu();
        testApp.addAccount();
        testApp.closeAccount(1);
        assertEquals(1, testApp.getNumAccounts());
    }

    @Test
    public void testIllegalCloseAccount() {
        MainMenu testApp = new MainMenu();
        try {
            testApp.closeAccount(1);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    @Test 
    public void testCloseCurrentAccountYes() {
        String data = "Y";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        MainMenu testApp = new MainMenu();
        testApp.addAccount();
        testApp.closeAccount(0);
        assertEquals(1, testApp.getNumAccounts());
    }

    @Test 
    public void testCloseCurrentAccountNo() {
        String data = "N";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        MainMenu testApp = new MainMenu();
        testApp.addAccount();
        testApp.closeAccount(0);
        assertEquals(2, testApp.getNumAccounts());
    }
}
