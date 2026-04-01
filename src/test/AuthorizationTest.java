package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.AppContext;
import main.BankAccount;
import main.MenuOptions.AddInterestPaymentOption;
import main.MenuOptions.CollectFeesOption;
import main.MenuOptions.CreateFeesOption;
import main.Users.User;
import main.Users.UserRole;
import main.Users.UserService;
import main.Utils.InputUtils;

public class AuthorizationTest {
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
    }

    @Test
    public void testCustomerCannotCreateFees() {
        ctx.setCurrentUser(customer);
        CreateFeesOption option = new CreateFeesOption();
        assertThrows(UnsupportedOperationException.class, () -> option.execute());
    }

    @Test
    public void testCustomerCannotCollectFees() {
        this.ctx.setCurrentUser(this.customer);
        CollectFeesOption option = new CollectFeesOption();
        assertThrows(UnsupportedOperationException.class, () -> option.execute());
    }

    @Test
    public void testCustomerCannotAddInterest() {
        this.ctx.setCurrentUser(this.customer);
        AddInterestPaymentOption option = new AddInterestPaymentOption();
        assertThrows(UnsupportedOperationException.class, () -> option.execute());
    }

    @Test
    public void testAdminCanCreateFees() {
        this.ctx.setCurrentUser(this.admin);
        CreateFeesOption option = new CreateFeesOption();
        BankAccount newAccount = ctx.getAllAccounts().createAccount();
        customer.addAccountId(newAccount.getId());
        String userInput = newAccount.getId() + "\n25.50\n"; // Account #, Fee of $25.50
        InputUtils.setInputStream(new ByteArrayInputStream(userInput.getBytes()));
        assertDoesNotThrow(()->option.execute());
    }

    @Test
    public void testAdminCanCollectFees() {
        this.ctx.setCurrentUser(this.admin);
        CollectFeesOption option = new CollectFeesOption();
        BankAccount newAccount = ctx.getAllAccounts().createAccount();
        customer.addAccountId(newAccount.getId());
        String userInput = newAccount.getId() + "\n"; // Account #
        ctx.getAllAccounts().getAccount(newAccount.getId()).deposit(100);
        ctx.getAllAccounts().getAccount(newAccount.getId()).addFees(10);
        
        InputUtils.setInputStream(new ByteArrayInputStream(userInput.getBytes()));
        
        assertDoesNotThrow(()->option.execute());
    }
    
    @Test
    public void testAdminCanAddInterestPayment() {
        this.ctx.setCurrentUser(this.admin);
        AddInterestPaymentOption option = new AddInterestPaymentOption();

        BankAccount newAccount = ctx.getAllAccounts().createAccount();
        customer.addAccountId(newAccount.getId());
        String userInput = newAccount.getId() + "\n"; // Account #

        ctx.getAllAccounts().getAccount(newAccount.getId()).deposit(100);
        InputUtils.setInputStream(new ByteArrayInputStream(userInput.getBytes()));
    
        assertDoesNotThrow(()->option.execute());
    }
}
