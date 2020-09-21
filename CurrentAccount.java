public class CurrentAccount extends BankAccount{
    //no param constructor
    public CurrentAccount() {
    }
    //with param constructor
    public CurrentAccount(String acctNumber, String acctName, double balance) {
        super(acctNumber, acctName, balance);
    }
    //here we implement our own withdraw method
    @Override
    public boolean withdraw(double amount) {
        if(amount==getBalance()){
            return false;
        }
        setBalance(getBalance()-amount);
        return true;
    }
}