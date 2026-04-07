package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.AppContext;
import main.Menus.LoginMenu;
import main.Users.UserService;
import main.Utils.InputUtils;

public class LoginMenuTest {
    private LoginMenu menu;
    private AppContext ctx;

    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final PrintStream sysOut = System.out;
    private final InputStream sysIn = System.in;

    @BeforeEach
    public void setUpLoginMenuTests() {
        System.setOut(new PrintStream(outStream));
        this.ctx = AppContext.getInstance();
        this.menu = new LoginMenu();
    }

    @AfterEach
    public void tearDownLoginMenuTests() throws Exception {
        System.setOut(sysOut);
        System.setIn(sysIn);
        InputUtils.setInputStream(sysIn);

        ctx.setCurrentUser(null);
        ctx.setExitCondition(false);
        UserService.getInstance().reset();
    }

    private void simulateInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        InputUtils.setInputStream(testIn);
    }

    @Test
    public void testInitialization() {
        assertNotNull(menu.getMenuOptions());
        assertEquals(2, menu.getMenuOptions().size());
        assertEquals("Login (Existing Users)", menu.getMenuOptions().get(0).getDisplayString());
        assertEquals("Register (New Users)", menu.getMenuOptions().get(1).getDisplayString());
    }

    @Test
    public void testDisplayOptions() {
        menu.displayOptions();
        String output = outStream.toString();

        assertTrue(output.contains("CSE2307 Bank App - Login"));
        assertTrue(output.contains("1. Login (Existing Users)"));
        assertTrue(output.contains("2. Register (New Users)"));
        assertTrue(output.contains("3. Exit the app"));
    }

    @Test
    public void testExitSelection() {
        int exitSelection = menu.getMenuOptions().size() + 1;
        menu.processInput(exitSelection);

        assertTrue(outStream.toString().contains("Exiting the app..."));
        assertTrue(ctx.getExitCondition());
    }

    @Test
    public void testGetUsersSelectionValid() {
        simulateInput("2\n");
        int selection = menu.getUserSelection();
        assertEquals(2, selection);
    }
}

