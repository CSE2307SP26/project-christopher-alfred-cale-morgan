package test;

import main.BankAccount;
import main.BankAccounts;
import main.MainMenu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BankAccountTest {



    @Test
    public void testDeposit() {
        BankAccounts allAccounts = new BankAccounts();
        allAccounts.createAccount();
        allAccounts.getAccount(1).deposit(50);
        assertEquals(50, allAccounts.getAccount(1).getBalance(), 0.01);
    }

    @Test
    public void testInvalidDeposit() {
        BankAccounts allAccounts = new BankAccounts();
        allAccounts.createAccount();
        try {
        allAccounts.getAccount(1).deposit(-50);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    
    @Test
    public void testTransfer() {
        BankAccounts allAccounts = new BankAccounts();

        allAccounts.createAccount();
        allAccounts.createAccount();


        allAccounts.getAccount(1).deposit(50);
        allAccounts.getAccount(1).transfer(50,allAccounts.getAccount(2));
        assertEquals(50, allAccounts.getAccount(2).getBalance(), 0.01);
    }

    @Test
    public void testInvalidTransfer() {
        BankAccounts allAccounts = new BankAccounts();

        allAccounts.createAccount();
        allAccounts.createAccount();

        allAccounts.getAccount(1).deposit(50);
        try {
            allAccounts.getAccount(1).transfer(100,allAccounts.getAccount(2));
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
