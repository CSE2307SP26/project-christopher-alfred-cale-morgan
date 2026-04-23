package main;

public abstract class UserRequest {
    protected int accountIdRequested;

    public UserRequest(int accountIdRequested) {
        this.accountIdRequested = accountIdRequested;
    }

    public int getAccountIdRequested() {
        return accountIdRequested;
    }

    public abstract double getRequestAmount();

    public abstract String getRequestType();

    public abstract void approve(BankAccountManager allAccounts);
}