package main;

public class CheckingAccount extends BankAccount {



    public CheckingAccount(int id) {
        super(id);
        accountType = AccountType.CHECKING;
    }
    
}
