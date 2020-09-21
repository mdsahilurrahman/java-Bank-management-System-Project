//we declare our class BankAccount to be abstract
//because the method withdraw will be created here but not implemented
public abstract class BankAccount {
    //our data members
    private String acctNumber;
    private String acctName;
    private double balance;
    //no param
    public BankAccount() {
    }
    //with param
    public BankAccount(String acctNumber, String acctName, double balance) {
        this.acctNumber = acctNumber;
        this.acctName = acctName;
        this.balance = balance;
    }
    //deposit method. amount should be greater than 0
    //to deposit
    public boolean deposit(double amount){
        if(amount > 0 ){
            this.balance += amount;
            return true;
        }
        return false;
    }
    //an abstract class withdraw
    //SavingsAccount and CurrentAccount will have their own implementation of it
    public abstract boolean withdraw(double amount);
    //we transfrer amount by getting an account and amount as a param
    public boolean transferAmount(BankAccount account, double amount){
        //we also check if balance - amount is sufficient
        if(account.balance - amount >=0){
            this.balance += amount;
            account.balance = account.balance - amount;
            return true;
        }
        return false;
    }
    //getters
    public String getAcctNumber() {
        return acctNumber;
    }
    public String getAcctName() {
        return acctName;
    }
    public double getBalance() {
        return balance;
    }
    //setters
    public void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber;
    }
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    @Override
    public String toString() {
        return "Account Number: " + acctNumber + "\nAccount Name: " + acctName + "\nBalance: " + balance;
    } 
}