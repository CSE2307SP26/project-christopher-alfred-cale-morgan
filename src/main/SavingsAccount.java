package main;

public class SavingsAccount extends BankAccount {
    public static final double SAVINGS_DEFAULT_INTEREST_RATE = 4.0;

    public SavingsAccount(int id) {
        super(id);
        accountType = AccountType.SAVINGS;
        interestRate = SAVINGS_DEFAULT_INTEREST_RATE;
    }
    
}
