public class SavingsAccount extends BankAccount{
    //our data member
    private double interestRate;
    //no param constructor
    public SavingsAccount() {
    }
    //with param constructor
    public SavingsAccount(double interestRate) {
        this.interestRate = interestRate;
    }
    public SavingsAccount(double interestRate, String acctNumber, String acctName, double balance) {
        super(acctNumber, acctName, balance);
        this.interestRate = interestRate;
    }
    //own implementation of withdraw method
    @Override
    public boolean withdraw(double amount) {
        if(getBalance()-amount >= 100){
            setBalance(getBalance()-amount);
            return true;
        }
        return false;
    }
    //we calculate interest here by the formula balance * (rate / 100)
    public double calcBalanceInterest(){
        return getBalance() * (this.interestRate/100);
    }
    //we update balance by using setBalance, adding the current balance with the calculated interest
    public void updateBalanceAfterInterest(){
        setBalance(getBalance()+calcBalanceInterest());
    }
    //getter
    public double getInterestRate() {
        return interestRate;
    }
    //setter
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    //toString method
    @Override
    public String toString() {
        return super.toString() + "\nInterest Rate: " + interestRate;
    }
}