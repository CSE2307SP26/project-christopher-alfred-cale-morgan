package test;

import main.BankAccount;
import main.BankAccounts;
import main.MainMenu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
    public void testWithdrawal() {
        BankAccounts allAccounts = new BankAccounts();
        allAccounts.createAccount();

        allAccounts.getAccount(1).deposit(100);
        allAccounts.getAccount(1).withdraw(50);
        assertEquals(50, allAccounts.getAccount(1).getBalance(), 0.01);
    }

    @Test
    public void testTooBigWithdrawal() {
        BankAccounts allAccounts = new BankAccounts();
        allAccounts.createAccount();

        allAccounts.getAccount(1).deposit(50);
        try {
            allAccounts.getAccount(1).withdraw(100);
            fail();
        } catch(Exception e) {
            // do nothing, test passes so far
        }

        // ensure balance was not changed after invalid withdrawal
        assertEquals(100, allAccounts.getAccount(1).getBalance(), 0.01);
    }

    @Test
    public void testNegativeWithdrawal() {
        BankAccounts allAccounts = new BankAccounts();
        allAccounts.createAccount();

        allAccounts.getAccount(1).deposit(50);
        try {
            allAccounts.getAccount(1).withdraw(-10);
            fail();
        } catch(Exception e) {
            // do nothing, test passes so far
        }

        // ensure balance was not changed after invalid withdrawal
        assertEquals(50, allAccounts.getAccount(1).getBalance(), 0.01);
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
}
