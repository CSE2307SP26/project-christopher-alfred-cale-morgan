package test;

import main.BankAccount;
import main.BankAccounts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    public void testCheckBalance() {
        BankAccounts allAccounts = new BankAccounts();
        allAccounts.createAccount();
        assertEquals(0.0, allAccounts.getAccount(1).getBalance(), 0.01);
        allAccounts.getAccount(1).deposit(50);
        assertEquals(50.0, allAccounts.getAccount(1).getBalance(), 0.01);
        allAccounts.getAccount(1).withdraw(25);
        assertEquals(25.0, allAccounts.getAccount(1).getBalance(), 0.01);
        allAccounts.getAccount(1).withdraw(25);
        assertEquals(0.0, allAccounts.getAccount(1).getBalance(), 0.01);
    }

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
        assertThrows(IllegalArgumentException.class, 
            () -> allAccounts.getAccount(1).deposit(-50)
        );
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
        
        assertThrows(IllegalArgumentException.class,
            () -> allAccounts.getAccount(1).withdraw(100)
        );
        
        // ensure balance was not changed after invalid withdrawal
        assertEquals(50, allAccounts.getAccount(1).getBalance(), 0.01);
    }

    @Test
    public void testNegativeWithdrawal() {
        BankAccounts allAccounts = new BankAccounts();
        allAccounts.createAccount();

        allAccounts.getAccount(1).deposit(50);
        assertThrows(IllegalArgumentException.class, 
            () -> allAccounts.getAccount(1).withdraw(-10)
        );
       
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
        assertThrows(IllegalArgumentException.class,
            () -> allAccounts.getAccount(1)
                    .transfer(100, 
                        allAccounts.getAccount(2))
        );
    }
 
    @Test
    public void takeFeesSuccess() {
        BankAccount testAccount = new BankAccount(1);
        testAccount.deposit(100);
        testAccount.addFees(50);
        testAccount.payFee();
        assertEquals(50, testAccount.getBalance(), 0.01);        
    }

    @Test
    public void addInterestPayment() {
        BankAccounts allAccounts = new BankAccounts();
        allAccounts.createAccount();
        allAccounts.getAccount(1).deposit(100);
        allAccounts.getAccount(1).payInterest();
        assertEquals(102.39, allAccounts.getAccount(1).getBalance(), 0.01);
    }

    @Test
    public void addInterestPaymentFail() {
        BankAccounts allAccounts = new BankAccounts();
        allAccounts.createAccount();
        assertThrows(IllegalArgumentException.class,
            () -> allAccounts.getAccount(1).payInterest()
        );
    }

    @Test
    public void testFrozenWithdrawl() {
        BankAccounts allAccounts = new BankAccounts();
        allAccounts.createAccount();

        allAccounts.getAccount(1).deposit(250);
        allAccounts.getAccount(1).freeze();
        
        assertThrows(IllegalArgumentException.class,
            () -> allAccounts.getAccount(1).withdraw(100)
        );
        
        // ensure balance was not changed after invalid withdrawal
        assertEquals(250, allAccounts.getAccount(1).getBalance(), 0.01);
    }

    @Test
    public void testFrozenDeposit() {
        BankAccounts allAccounts = new BankAccounts();
        allAccounts.createAccount();

        allAccounts.getAccount(1).deposit(250);
        allAccounts.getAccount(1).freeze();
        
        assertThrows(IllegalArgumentException.class,
            () -> allAccounts.getAccount(1).deposit(100)
        );
        
        // ensure balance was not changed after invalid withdrawal
        assertEquals(250, allAccounts.getAccount(1).getBalance(), 0.01);
    }

    @Test
    public void testFrozenTransferTo() {
        BankAccounts allAccounts = new BankAccounts();
        allAccounts.createAccount();
        allAccounts.createAccount();


        allAccounts.getAccount(1).deposit(250);
        allAccounts.getAccount(2).deposit(250);

        allAccounts.getAccount(1).freeze();
        
        assertThrows(IllegalArgumentException.class,
            () -> allAccounts.getAccount(1).transfer(100,allAccounts.getAccount(2))
        );
        
        // ensure balance was not changed after invalid withdrawal
        assertEquals(250, allAccounts.getAccount(1).getBalance(), 0.01);
    }

    @Test
    public void testFrozenTransferFrom() {
        BankAccounts allAccounts = new BankAccounts();
        allAccounts.createAccount();
        allAccounts.createAccount();


        allAccounts.getAccount(1).deposit(250);
        allAccounts.getAccount(2).deposit(250);
        allAccounts.getAccount(2).freeze();
        
        assertThrows(IllegalArgumentException.class,
            () -> allAccounts.getAccount(1).transfer(100,allAccounts.getAccount(2))
        );
        
        // ensure balance was not changed after invalid withdrawal
        assertEquals(250, allAccounts.getAccount(2).getBalance(), 0.01);
    }
}
