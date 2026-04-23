package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import main.AppContext;
import main.Menus.AbstractMenu;
import main.Menus.AdminMenu;
import main.Menus.MenuOptions.AddAccountOption;
import main.Menus.MenuOptions.DepositOption;
import main.Menus.MenuOptions.IMenuOption;
import main.Menus.MenuOptions.SelectAccountOption;
import main.Users.User;
import main.Users.UserRole;
import main.Users.UserService;
import main.Utils.InputUtils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminMenuTest {
    private static AbstractMenu menu;
    private static AppContext ctx;

    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final PrintStream sysOut = System.out;
    private final InputStream sysIn = System.in;
    
    @BeforeEach
    public void setUpAdminMenuTests() {
        System.setOut(new PrintStream(outStream));
        ctx = AppContext.getInstance();

        UserService.getInstance().registerUser("testAdmin", "testPassword", UserRole.Administrator);
        User testUser = UserService.getInstance().authenticate("testAdmin", "testPassword");
        ctx.setCurrentUser(testUser);
        
        menu = new AdminMenu();
    }

    @AfterEach
    public void resetAdminMenuTests() {
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
        assertEquals(UserRole.Administrator, ctx.getCurrentUser().getRole());
        List<IMenuOption> options = menu.getMenuOptions();
        assertNotNull(options);
        assertEquals(7, options.size());
    }

    @Test
    public void testDisplayOptions() {
        menu.displayOptions();
        String output = outStream.toString();

        assertTrue(output.contains("2307 Bank App - Menu (Admin)"));
        assertTrue(output.contains("Add interest payment (Admin)"));
        assertTrue(output.contains("Create Fees (Admin)"));
        assertTrue(output.contains("Collect fees (Admin)"));
        assertTrue(output.contains("View Transaction History (Admin)"));
        assertTrue(output.contains("View All Account Balances (Admin)"));

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

    @Test
    public void testNoBalance() {
        int getBalancesSelection = menu.getMenuOptions().size();
        menu.processInput(getBalancesSelection);
        assertTrue(outStream.toString().contains("There are no active accounts right now."));  
    }

    @Test
    public void testViewBalances() {
        IMenuOption add = new AddAccountOption();
        IMenuOption deposit = new DepositOption();
        IMenuOption select = new SelectAccountOption();
       
        UserService.getInstance().registerUser("customerOne", "passwordOne", UserRole.Customer);
        User customerOne = UserService.getInstance().authenticate("customerOne", "passwordOne");
        
        UserService.getInstance().registerUser("customerTwo", "passwordTwo", UserRole.Customer);
        User customerTwo = UserService.getInstance().authenticate("customerTwo", "passwordTwo");

        ctx.setCurrentUser(customerOne);
        simulateInput("C\n");
        add.execute();
        simulateInput("1\n");
        select.execute();
        simulateInput("25\n");
        deposit.execute();

        ctx.setCurrentUser(customerTwo);
        simulateInput("C\n");
        add.execute();
        simulateInput("2\n");
        select.execute();
        simulateInput("35\n");
        deposit.execute();

        User testUser = UserService.getInstance().authenticate("testAdmin", "testPassword");
        ctx.setCurrentUser(testUser);
        int getBalancesSelection = menu.getMenuOptions().size();
        menu.processInput(getBalancesSelection);
        assertTrue(outStream.toString().contains("25")); 
        assertTrue(outStream.toString().contains("35"));  
    }
}
    

