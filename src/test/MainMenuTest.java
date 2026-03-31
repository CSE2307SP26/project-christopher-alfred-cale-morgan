package test;

import main.AppContext;
import main.MainMenu;
import main.MenuOptions.IMenuOption;
import main.Utils.InputUtils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainMenuTest {
    
    private static MainMenu menu;
    private static AppContext ctx = AppContext.getInstance();

    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final PrintStream sysOut = System.out;
    private final InputStream sysIn = System.in;

    @BeforeEach
    public void setUpMainMenuTests() {
        System.setOut(new PrintStream(outStream));
        menu = new MainMenu();
        ctx = AppContext.getInstance();
    }

    @AfterEach
    public void resetMainMenuTests() {
        System.setOut(sysOut);
        System.setIn(sysIn);

        InputUtils.setInputStream(System.in);
    }

    /***
     * Helper method to make input collection/testing easier
     * (Added during migration to InputUtils)
     * @param data string to simulate the user typing
     */
    private void simulateInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        InputUtils.setInputStream(testIn);
    }

    // Test Menu Options
    @Test
    public void testInitialization()
    {
        assertNotNull(ctx);
        assertNotNull(ctx.getBankAccounts());
        assertNotNull(ctx.getUserAccount());

        List<IMenuOption> options = menu.getMenuOptions();
        assertNotNull(options);
    }

    @Test 
    public void testGetNumAccounts() {
        assertEquals(1, menu.getNumAccounts());
    }

    @Test
    public void testDisplayOptions() {
        menu.displayOptions();
        String output = outStream.toString();

        assertTrue(output.contains("Welcome to the 237 Bank App!"));
        assertTrue(output.contains("1. Check Account Balance"));
        assertTrue(output.contains("2. Make a deposit"));
    
        int exitNumber = menu.getMenuOptions().size() + 1;
        assertTrue(output.contains(exitNumber + ". Exit the app"));
    }

    @Test
    public void testGetUserSelectionNormal() {
        simulateInput("3\n");
        int selection = menu.getUserSelection();
        assertEquals(3, selection);
    }

    @Test
    public void testGetUserSelectionOutOfBoundsThenValid() {
        // below, above, in bounds
        simulateInput("-1\n99\n5\n");
        int selection = menu.getUserSelection();
        assertEquals(5, selection);
    }

    @Test
    public void testExitSelection() {
        int exitSelection = menu.getMenuOptions().size() + 1;
        menu.processInput(exitSelection);
        assertTrue(outStream.toString().contains("Exiting!"));
    }
}
