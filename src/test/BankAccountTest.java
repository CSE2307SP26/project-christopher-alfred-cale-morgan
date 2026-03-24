package test;

import main.BankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    public void testDeposit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidDeposit() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.deposit(-50);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    @Test
    public void testTransfer() {
        BankAccount testAccount = new BankAccount();
        BankAccount testAccount2 = new BankAccount();
        testAccount.deposit(50);
        testAccount.transfer(50,testAccount2);
        assertEquals(50, testAccount2.getBalance(), 0.01);
    }

    @Test
    public void testInvalidTransfer() {
        BankAccount testAccount = new BankAccount();
        BankAccount testAccount2 = new BankAccount();
        testAccount.deposit(50);
        try {
            testAccount.transfer(100,testAccount2);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }
}
