package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import main.Users.User;
import main.Users.UserRole;
import main.Users.UserService;

public class UserServiceTest {
    
    @Test
    public void testSingletonInstance() {
        UserService first = UserService.getInstance();
        UserService second = UserService.getInstance();
        assertSame(first, second);
    }

    @Test
    public void testAdminExists() {
        UserService service = UserService.getInstance();
        User admin = service.authenticate("admin", "password");
        
        assertNotNull(admin);
        assertEquals("admin", admin.getUsername());
        assertEquals(UserRole.Administrator, admin.getRole());
    }

    @Test
    public void testRegisterNewUser() {
        UserService service = UserService.getInstance();

        boolean result = service.registerUser("newUser", "123456789", UserRole.Customer);
        assertTrue(result);

        User authenticateResult = service.authenticate("newUser", "123456789");
        assertNotNull(authenticateResult);
        assertEquals("newUser", authenticateResult.getUsername());
        assertEquals(UserRole.Customer, authenticateResult.getRole());
    }

    @Test
    public void testDuplicateRegisterUser() {
        UserService service = UserService.getInstance();
        assertTrue(service.registerUser("duplicateUser", "firstPW", UserRole.Customer));
        boolean result = service.registerUser("duplicateUser", "secondPW", UserRole.Customer);
        assertFalse(result);
        User authenticateResult = service.authenticate("duplicateUser", "firstPW");
        assertNotNull(authenticateResult);
        assertEquals("firstPW", authenticateResult.getPassword());
        assertNull(service.authenticate("duplicateUser", "secondPW"));
    }

    @Test
    public void testAuthWrongPassword() {
        UserService service = UserService.getInstance();
        service.registerUser("authUser", "correctPW", UserRole.Customer);
        User authResult = service.authenticate("authUser", "wrong");
        assertNull(authResult);
    }

    @Test
    public void testAuthUnknownUsername() {
        UserService service = UserService.getInstance();

        User authResult = service.authenticate("not_a_user", "fortnite");
        assertNull(authResult);
    }
}
