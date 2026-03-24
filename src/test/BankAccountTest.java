package test;

import main.BankAccount;
import main.MainMenu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    public void testDeposit() {
        BankAccount testAccount = new BankAccount(1);
        testAccount.deposit(50);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidDeposit() {
        BankAccount testAccount = new BankAccount(1);
        try {
            testAccount.deposit(-50);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    @Test
    public void testNewAccount() {
        MainMenu testApp = new MainMenu();
        testApp.addAccount();
        assertEquals(2, testApp.getNumAccounts());
    }

    @Test
    public void testNewAdmin() { //Test the method to gain admin access
        BankAccount testAccount = new BankAccount(1);
        testAccount.setAdminStatus();
        assertTrue(testAccount.getAdminStatus());
    }
 

    @Test
    public void takeFeesSuccess() {

        BankAccount testAccount = new BankAccount(1);
        testAccount.setAdminStatus();
        testAccount.deposit(100);
        testAccount.addFees(50);
        testAccount.payFee();
        assertEquals(50, testAccount.getBalance(), 0.01);        
    }

    

}
