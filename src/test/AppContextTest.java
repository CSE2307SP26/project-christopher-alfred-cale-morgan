package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.AppContext;
import main.BankAccount;
import main.Users.User;
import main.Users.UserRole;
import main.Users.UserService;

public class AppContextTest {
    private AppContext ctx;
    private UserService service;

    @BeforeEach
    public void setUpAppContextTests() {
        this.ctx = AppContext.getInstance();
        this.service = UserService.getInstance();
    }

    @Test
    public void testSetCurrentUserWithNoAccounts() {
        this.service.registerUser("newUser", "password", UserRole.Customer);
        User user = this.service.authenticate("newUser", "password");
        
        this.ctx.setCurrentUser(user);
        
        assertEquals(user, this.ctx.getCurrentUser());
        assertNull(this.ctx.getSelectedAccount()); // No accounts created yet for new user
    }

    @Test
    public void testSetCurrentUserWithAccounts() {
        this.service.registerUser("activeUser", "password", UserRole.Customer);
        User user = this.service.authenticate("activeUser", "password");
        
        BankAccount newAccount = this.ctx.getAllAccounts().createAccount();
        user.addAccountId(newAccount.getId());

        this.ctx.setCurrentUser(user);
        
        assertEquals(user, this.ctx.getCurrentUser());
        assertNotNull(this.ctx.getSelectedAccount());
        assertEquals(newAccount.getId(), this.ctx.getSelectedAccount().getId()); // selected account should automatically be updated
    }
}
