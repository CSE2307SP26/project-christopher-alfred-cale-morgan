package test;

import main.BankAccount;
import main.Transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

public class TransactionTest {

    @Test
    public void testTransactionAddedOnDeposit() {
        BankAccount account = new BankAccount(1);
    
        account.deposit(100);
    
        assertEquals(1, account.getTransactions().getAllTransactions().size());
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
    public void testTransactionDetails() {
        BankAccount account = new BankAccount(1);

        account.deposit(75);

        Transaction t = account.getTransactions().getAllTransactions().get(0);

        assertEquals(75, t.getAmount(), 0.01);
        assertEquals("Deposit", t.getType());
        assertEquals("Depositing 75.0", t.getDescription());
    }

    @Test
    public void testMultipleTransactions() {
        BankAccount account = new BankAccount(1);

        account.deposit(50);
        account.deposit(25);

        assertEquals(2, account.getTransactions().getAllTransactions().size());
    }

    @Test
    public void testNoTransactionOnInvalidDeposit() {
        BankAccount account = new BankAccount(1);

        try {
            account.deposit(-10);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }

        assertEquals(0, account.getTransactions().getAllTransactions().size());
    }
}
