package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import main.AppContext;
import main.BankAccount;
import main.Menus.AbstractMenu;
import main.Menus.CustomerMenu;
import main.Menus.MenuOptions.IMenuOption;
import main.Users.User;
import main.Users.UserRole;
import main.Users.UserService;
import main.Utils.InputUtils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerMenuTest {
    private static AbstractMenu menu;
    private static AppContext ctx;

    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final PrintStream sysOut = System.out;
    private final InputStream sysIn = System.in;
    
    @BeforeEach
    public void setUpCustomerMenuTests() {
        System.setOut(new PrintStream(outStream));
        ctx = AppContext.getInstance();
        UserService.getInstance().registerUser("testCustomer", "testPassword", UserRole.Customer);
        User testUser = UserService.getInstance().authenticate("testCustomer", "testPassword");
        ctx.setCurrentUser(testUser);

        BankAccount testAccount = ctx.getAllAccounts().createCheckingAccount();
        testUser.addAccountId(testAccount.getId());
        ctx.setSelectedAccount(testAccount);
        
        menu = new CustomerMenu();
    }

    @AfterEach
    public void resetCustomerMenuTests() {
        System.setOut(sysOut);
        System.setIn(sysIn);
        InputUtils.setInputStream(System.in);

        ctx.reset();
        UserService.getInstance().reset();
    }

    private void simulateInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        InputUtils.setInputStream(testIn);
    }

    @Test
    public void testInitialization() {
        assertNotNull(ctx.getCurrentUser());
        assertEquals(UserRole.Customer, ctx.getCurrentUser().getRole());
        List<IMenuOption> options = menu.getMenuOptions();
        assertNotNull(options);
        assertEquals(9, options.size());
    }

    @Test
    public void testDisplayOptions() {
        menu.displayOptions();
        String output = outStream.toString();

        assertTrue(output.contains("2307 Bank App - Menu (Customer)"));
        assertTrue(output.contains("1. Check Account Balance"));
        assertTrue(output.contains("2. Make a deposit"));

        int exitNumber = menu.getMenuOptions().size() + 1;
        assertTrue(output.contains(exitNumber + ". Log out"));
    }

    @Test
    public void testGetUserSelectionValid() {
        simulateInput("3\n");
        int selection = menu.getUserSelection();
        assertEquals(3, selection);
    }

    @Test
    public void testLogoutSelection() {
        int exitSelection = menu.getMenuOptions().size() + 1;
        menu.processInput(exitSelection);
        assertTrue(outStream.toString().contains("Logging out user..."));
        assertNull(AppContext.getInstance().getCurrentUser());
    }
}

