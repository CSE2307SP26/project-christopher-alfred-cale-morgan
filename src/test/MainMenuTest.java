package test;

import main.AppContext;
import main.MainMenu;
import main.MenuOptions.IMenuOption;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainMenuTest {
    
    private static MainMenu menu;
    private static AppContext ctx;

    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final PrintStream sysOut = System.out;
    private final InputStream sysIn = System.in;

    @BeforeEach
    public void setUpMainMenuTests() {
        System.setOut(new PrintStream(outStream));
        menu = new MainMenu();
        ctx = menu.getAppContext();
    }

    @AfterEach
    public void resetMainMenuTests() {
        System.setOut(sysOut);
        System.setIn(sysIn);

        if(ctx.keyboardInput != null) {
            ctx.keyboardInput.close();
        }
    }

    // Test Menu Options
    @Test
    public void testInitialization()
    {
        AppContext ctx = menu.getAppContext();
        assertNotNull(ctx);
        assertNotNull(ctx.bankAccounts);
        assertNotNull(ctx.userAccount);
        assertNotNull(ctx.keyboardInput);

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
        String testInput = "3\n";
        ctx.keyboardInput = new Scanner(new ByteArrayInputStream(testInput.getBytes()));

        int selection = menu.getUserSelection();
        assertEquals(3, selection);
        assertEquals(3.0, ctx.currentInput, 0.001);
    }

    @Test
    public void testGetUserSelectionOutOfBoundsThenValid() {
        // below, above, in bounds
        String testInput = "-1\n99\n5\n";
        ctx.keyboardInput = new Scanner(new ByteArrayInputStream(testInput.getBytes()));

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
