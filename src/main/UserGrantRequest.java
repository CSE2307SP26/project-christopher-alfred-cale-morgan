package main;

public class UserGrantRequest extends UserRequest {
    private double requestedGrant;

    public UserGrantRequest(int accountIdRequested, double requestedGrant) {
        super(accountIdRequested);
        this.requestedGrant = requestedGrant;
    }

    @Override
    public double getRequestAmount() {
        return requestedGrant;
    }

    @Override
    public String getRequestType() {
        return "Grant";
    }

    @Override
    public void approve(BankAccountManager allAccounts) {
        allAccounts.getAccount(accountIdRequested).deposit(requestedGrant);
    }
}