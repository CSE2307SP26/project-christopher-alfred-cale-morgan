package test;

import main.BankAccount;
import main.BankAccountManager;
import main.CheckingAccount;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    public void testCheckBalance() {
        BankAccountManager allAccounts = new BankAccountManager();
        allAccounts.createCheckingAccount();
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
        BankAccountManager allAccounts = new BankAccountManager();
        allAccounts.createCheckingAccount();
        allAccounts.getAccount(1).deposit(50);
        assertEquals(50, allAccounts.getAccount(1).getBalance(), 0.01);
    }

    @Test
    public void testInvalidDeposit() {
        BankAccountManager allAccounts = new BankAccountManager();
        allAccounts.createCheckingAccount();
        assertThrows(IllegalArgumentException.class, 
            () -> allAccounts.getAccount(1).deposit(-50)
        );
    }

    @Test
    public void testWithdrawal() {
        BankAccountManager allAccounts = new BankAccountManager();
        allAccounts.createCheckingAccount();

        allAccounts.getAccount(1).deposit(100);
        allAccounts.getAccount(1).withdraw(50);
        assertEquals(50, allAccounts.getAccount(1).getBalance(), 0.01);
    }

    @Test
    public void testTooBigWithdrawal() {
        BankAccountManager allAccounts = new BankAccountManager();
        allAccounts.createCheckingAccount();

        allAccounts.getAccount(1).deposit(50);
        
        assertThrows(IllegalArgumentException.class,
            () -> allAccounts.getAccount(1).withdraw(100)
        );
        
        // ensure balance was not changed after invalid withdrawal
        assertEquals(50, allAccounts.getAccount(1).getBalance(), 0.01);
    }

    @Test
    public void testNegativeWithdrawal() {
        BankAccountManager allAccounts = new BankAccountManager();
        allAccounts.createCheckingAccount();

        allAccounts.getAccount(1).deposit(50);
        assertThrows(IllegalArgumentException.class, 
            () -> allAccounts.getAccount(1).withdraw(-10)
        );
       
        // ensure balance was not changed after invalid withdrawal
        assertEquals(50, allAccounts.getAccount(1).getBalance(), 0.01);
    }

    @Test
    public void testTransfer() {
        BankAccountManager allAccounts = new BankAccountManager();

        allAccounts.createCheckingAccount();
        allAccounts.createCheckingAccount();

        allAccounts.getAccount(1).deposit(50);
        allAccounts.getAccount(1).transfer(50,allAccounts.getAccount(2));
        assertEquals(50, allAccounts.getAccount(2).getBalance(), 0.01);
    }

    @Test
    public void testInvalidTransfer() {
        BankAccountManager allAccounts = new BankAccountManager();

        allAccounts.createCheckingAccount();
        allAccounts.createCheckingAccount();

        allAccounts.getAccount(1).deposit(50);
        assertThrows(IllegalArgumentException.class,
            () -> allAccounts.getAccount(1)
                    .transfer(100, 
                        allAccounts.getAccount(2))
        );
    }
 
    @Test
    public void takeFeesSuccess() {
        BankAccount testAccount = new CheckingAccount(1);
        testAccount.deposit(100);
        testAccount.addFees(50);
        testAccount.payFee();
        assertEquals(50, testAccount.getBalance(), 0.01);        
    }

    @Test
    public void addInterestPaymentChecking() {
        BankAccountManager allAccounts = new BankAccountManager();
        allAccounts.createCheckingAccount();
        allAccounts.getAccount(1).deposit(100);
        allAccounts.getAccount(1).payInterest();
        assertEquals(102.39, allAccounts.getAccount(1).getBalance(), 0.01);
    }

    @Test
    public void addInterestPaymentSavings() {
        BankAccountManager allAccounts = new BankAccountManager();
        allAccounts.createSavingsAccount();
        allAccounts.getAccount(1).deposit(100);
        allAccounts.getAccount(1).payInterest();
        assertEquals(104, allAccounts.getAccount(1).getBalance(), 0.01);
    }

    @Test
    public void addInterestPaymentFail() {
        BankAccountManager allAccounts = new BankAccountManager();
        allAccounts.createCheckingAccount();
        assertThrows(IllegalArgumentException.class,
            () -> allAccounts.getAccount(1).payInterest()
        );
    }

    @Test
    public void testFrozenWithdrawl() {
        BankAccountManager allAccounts = new BankAccountManager();
        allAccounts.createCheckingAccount();

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
        BankAccountManager allAccounts = new BankAccountManager();
        allAccounts.createCheckingAccount();

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
        BankAccountManager allAccounts = new BankAccountManager();
        allAccounts.createCheckingAccount();
        allAccounts.createCheckingAccount();


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
        BankAccountManager allAccounts = new BankAccountManager();
        allAccounts.createCheckingAccount();
        allAccounts.createCheckingAccount();


        allAccounts.getAccount(1).deposit(250);
        allAccounts.getAccount(2).deposit(250);
        allAccounts.getAccount(2).freeze();
        
        assertThrows(IllegalArgumentException.class,
            () -> allAccounts.getAccount(1).transfer(100,allAccounts.getAccount(2))
        );
        
        // ensure balance was not changed after invalid withdrawal
        assertEquals(250, allAccounts.getAccount(2).getBalance(), 0.01);
    }

    @Test
    public void testSetAndGetAccountNickname() {
        BankAccountManager allAccounts = new BankAccountManager();
        allAccounts.createCheckingAccount();
        BankAccount account = allAccounts.getAccount(1);
        assertEquals("", account.getAccountNickname());

        account.setAccountNickname("Emergency Savings");
        assertEquals("Emergency Savings", account.getAccountNickname());

        account.setAccountNickname("Vacation Fund");
        assertEquals("Vacation Fund", account.getAccountNickname());
    }
}
