package main;

public class UserLimitRequest extends UserRequest {
    private double requestedLimit;

    public UserLimitRequest(int accountIdRequested, double requestedLimit) {
        super(accountIdRequested);
        this.requestedLimit = requestedLimit;
    }

    @Override
    public double getRequestAmount() {
        return requestedLimit;
    }

    @Override
    public String getRequestType() {
        return "Limit";
    }

    @Override
    public void approve(BankAccountManager allAccounts) {
        allAccounts.getAccount(accountIdRequested).setLimit(requestedLimit);
    }
}