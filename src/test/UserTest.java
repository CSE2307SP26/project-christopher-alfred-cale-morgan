package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import main.Users.User;
import main.Users.UserRole;
import main.Users.UserService;

public class UserTest {
    private User user;
 
    @BeforeEach
    public void setUpUserTests() {
        UserService service = UserService.getInstance();
        service.registerUser("usersName", "password", UserRole.Customer);
        this.user = service.authenticate("usersName", "password");
    }

    @AfterEach
    public void resetUserTests() {
        UserService.getInstance().reset();
    }

    @Test
    public void testUserProperties() {
        assertNotNull(user);
        assertTrue(user.getUsername().startsWith("user"));
        assertEquals("password", user.getPassword());
        assertEquals(UserRole.Customer, user.getRole());
        assertNotNull(user.getAccountIds());
        assertEquals(0, user.getAccountIds().size());
    }

    @Test
    public void testAddAccountId() {
        final int testAccountId = 101;
        boolean result = user.addAccountId(testAccountId);
        assertTrue(result);
        assertEquals(1, user.getAccountIds().size());
        assertTrue(user.getAccountIds().contains(testAccountId));
    }

    @Test
    public void testAddMultipleAccountIds() {
        for(int i = 101; i < 111; i++) {
            user.addAccountId(i);
        }
        assertEquals(10, user.getAccountIds().size());
    }

    @Test
    public void testAddDuplicateAccountId() {
        boolean validResult = user.addAccountId(501);
        boolean invalidResult = user.addAccountId(501);

        assertTrue(validResult);
        assertFalse(invalidResult);
        assertEquals(1, user.getAccountIds().size());
    }
}
