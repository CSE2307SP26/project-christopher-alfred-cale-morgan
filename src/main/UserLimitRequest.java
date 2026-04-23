package main;


public class UserLimitRequest {
    
    //TODO: Wont implement just yet!

    // public enum RequestStatus {
    //     PENDING,
    //     APPROVED,
    //     DENIED
    // }


    private double requestedLimit;
    private int accountIdRequested;
    


    public UserLimitRequest(int accountIdRequested, double requestedLimit) {

        this.requestedLimit = requestedLimit;
        this.accountIdRequested = accountIdRequested;


    }


    public int getAccountIdRequested() {
        return this.accountIdRequested;
    }
    

    public double getRequestedLimit() {
        return this.requestedLimit;
    }
    





}
