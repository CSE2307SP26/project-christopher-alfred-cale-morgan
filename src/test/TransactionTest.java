package test;

import main.AppContext;
import main.BankAccount;
import main.Transaction;
import main.Menus.MenuOptions.AdminViewHistoryOption;
import main.Users.User;
import main.Users.UserRole;
import main.Users.UserService;
import main.Utils.InputUtils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest {
    private AppContext ctx;
    private UserService service;
    private User customer;
    private User admin;

    @BeforeEach
    public void setUpAuthorizationTests() {
        ctx = AppContext.getInstance();
        service = UserService.getInstance();
        service.registerUser("customer", "password", UserRole.Customer);
        service.registerUser("admin", "password", UserRole.Administrator);
        customer = service.authenticate("customer", "password");
        admin = service.authenticate("admin", "password");
    }

    @AfterEach
    public void resetInput() {
        InputUtils.setInputStream(System.in);
        ctx.reset();
        UserService.getInstance().reset();
    }

    @Test
    public void testTransactionAddedOnDeposit() {
        BankAccount account = new BankAccount(1);
    
        account.deposit(100);
    
        assertEquals(1, account.getTransactions().getAllTransactions().size());
    }

    @Test
    public void testInvalidDeposit() {
        BankAccount testAccount = new BankAccount(1);
        assertThrows(IllegalArgumentException.class, 
            () -> testAccount.deposit(-50)
        );
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

        assertThrows(IllegalArgumentException.class,
            () -> account.deposit(-10)
        );

        assertEquals(0, account.getTransactions().getAllTransactions().size());
    }

    @Test
    public void testAdminViewEmptyTransactions() {
        this.ctx.setCurrentUser(this.admin);
        BankAccount account = ctx.getAllAccounts().createAccount();
        customer.addAccountId(account.getId());

        String userInput = ""+account.getId();
        InputUtils.setInputStream(new ByteArrayInputStream(userInput.getBytes()));
        assertEquals(0, ctx.getAllAccounts().getAccount(account.getId()).getTransactions().getAllTransactions().size());
    }


    @Test
    public void testAdminViewSingleTransaction() {
        this.ctx.setCurrentUser(this.admin);
        BankAccount account = ctx.getAllAccounts().createAccount();
        customer.addAccountId(account.getId());

        String userInput = ""+account.getId();
        InputUtils.setInputStream(new ByteArrayInputStream(userInput.getBytes()));


        ctx.getAllAccounts().getAccount(account.getId()).deposit(100);

        assertEquals(1, ctx.getAllAccounts().getAccount(account.getId()).getTransactions().getAllTransactions().size());
    }


    @Test
    public void testAdminViewMultipleTransactions() {
        this.ctx.setCurrentUser(this.admin);
        BankAccount account = ctx.getAllAccounts().createAccount();
        customer.addAccountId(account.getId());

        String userInput = ""+account.getId();
        InputUtils.setInputStream(new ByteArrayInputStream(userInput.getBytes()));

        ctx.getAllAccounts().getAccount(account.getId()).deposit(100);
        ctx.getAllAccounts().getAccount(account.getId()).deposit(100);

        assertEquals(2, ctx.getAllAccounts().getAccount(account.getId()).getTransactions().getAllTransactions().size());
    }
}
