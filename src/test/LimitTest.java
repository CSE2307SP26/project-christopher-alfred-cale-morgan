package test;

import main.UserLimitRequest;
import main.UserRequestManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class LimitTest {


    private UserRequestManager requests;

    @BeforeEach
    void setUp() {
        // Arrange: Start each test with a fresh manager
        requests = new UserRequestManager();
    }

    @AfterEach
    public void resetRequests() {
        requests.getAllRequests().clear();
       
    }


    @Test
    void testAddRequest() {
        UserLimitRequest request = new UserLimitRequest(101, 5000.0);
        requests.addRequest(request);
        assertEquals(1, requests.getAllRequests().size(), "List size should be 1 after adding a request");
        assertEquals(101, request.getAccountIdRequested());
        assertEquals(5000.0, request.getRequestedLimit());
    }

    @Test
    void testMultipleRequests() {
        // Act
        requests.addRequest(new UserLimitRequest(101, 1000.0));
        requests.addRequest(new UserLimitRequest(102, 2000.0));

        // Assert
        assertEquals(2, requests.getAllRequests().size());
    }
   
}
