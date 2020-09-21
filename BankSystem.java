import java.util.ArrayList;
import java.util.Scanner;

public class BankSystem {
    //we create a static Scanner object in to be accessed
    //by all methods in the Main class
    static Scanner in = new Scanner(System.in);
    //ArrayList to store objects of BankAccount also child classes
    static ArrayList<BankAccount> account = new ArrayList<>();


    //we create a method for a menu to be called in the main method
    public static int bankMenu() {
        System.out.println("------------Menu-------------");
        System.out.println("1. Add Account.");
        System.out.println("2. Update Interest Rate.");
        System.out.println("3. Add Interest to Balance. ");
        System.out.println("4. Check Number of Accounts.");
        System.out.println("5. Search an Account. ");
        System.out.println("6. Deposit.");
        System.out.println("7. Withdraw.");
        System.out.println("8. Transfer Money.");
        System.out.println("9. Exit the System.");
        System.out.println("------------------------------------------------");
        System.out.print("Enter your choice: ");
        return Integer.parseInt(in.nextLine());
    }


    //we create a method for creating account
    public static void createNewAccount() {
        //we can choose which type of account we can create
        //Current or Savings
        int type = 0;
        do {
            System.out.print("[Enter 1 for Savings] [2 for Current account]: ");
            type = Integer.parseInt(in.nextLine());
            if (type == 1 || type == 2) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        } while (true);
        System.out.print("Enter account number: ");
        String number = in.nextLine();
        System.out.print("Enter account name: ");
        String name = in.nextLine();
        double balance = 0;
        //balance must be greater than 100
        do {
            System.out.print("Enter balance: ");
            balance = Double.parseDouble(in.nextLine());
            if (balance > 100) {
                break;
            } else {
                System.out.println("Initial balance should be greater than 100.");
            }
        } while (true);
        //if type 1 then we create Savings otherwise we create Current
        //we also have to specify an interest rate
        //can be changed later with a method
        if (type == 1) {
            System.out.print("Enter interest rate: ");
            double interest = Double.parseDouble(in.nextLine());
            account.add(new SavingsAccount(interest, number, name, balance));
        } else {
            account.add(new CurrentAccount(number, name, balance));
        }
        System.out.println("New account created.");
    }


    //we can change interest by searching for the account using the account number
    //if account nof found then we change no interest
    public static void updateInterest() {
        System.out.print("Enter account number: ");
        String number = in.nextLine();
        int flag = 0;
        for (int i = 0; i < account.size(); i++) {
            if (number.equals(account.get(i).getAcctNumber()) && account.get(i) instanceof SavingsAccount) {
                System.out.print("Enter a new interest rate: ");
                double interest = Double.parseDouble(in.nextLine());
                ((SavingsAccount) account.get(i)).setInterestRate(interest);
                System.out.println("Interest rate changed to: " + interest);
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            System.out.println("account not found.");
        }
    }


    //we add computed interest to our current balance here
    //we search for the account number
    //we only change the balance for savings account
    //that is why we compare an instance of Savingsaccount
    public static void addInterest() {
        System.out.print("Enter account number: ");
        String number = in.nextLine();
        int flag = 0;
        for (int i = 0; i < account.size(); i++) {
            if (number.equals(account.get(i).getAcctNumber()) && account.get(i) instanceof SavingsAccount) {
                ((SavingsAccount) account.get(i)).updateBalanceAfterInterest();
                System.out.println("Balance has been updated.");
                System.out.println("------------------------------------------------");
                System.out.println(account.get(i).toString());
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            System.out.println("account not found.");
        }
    }


    //we just get the size of arrayList to get the number of accounts created
    public static void numAccounts() {
        System.out.println("Current number of bank accounts: " + account.size());
    }


    //we can search and display account information here
    //by looking at account number
    public static void search() {
        System.out.print("Enter account number: ");
        String number = in.nextLine();
        int flag = 0;
        for (int i = 0; i < account.size(); i++) {
            if (number.equals(account.get(i).getAcctNumber())) {
                System.out.println("Account found: ");
                System.out.println("------------------------------------------------");
                System.out.println(account.get(i).toString());
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            System.out.println("account not found.");
        }
    }


    //we do deposit here
    //we check first for an existing account number
    //then we also check if the amount to be deposited is valid
    //before we call on the deposit method and make a deposit
    public static void goDeposit() {
        System.out.print("Enter account number: ");
        String number = in.nextLine();
        int flag = 0;
        for (int i = 0; i < account.size(); i++) {
            if (number.equals(account.get(i).getAcctNumber())) {
                do {
                    System.out.print("Enter amount to be deposited: ");
                    double amount = Double.parseDouble(in.nextLine());
                    if (amount > 0) {
                        account.get(i).deposit(amount);
                        break;
                    } else {
                        System.out.println("Invalid amount.");
                    }
                } while (true);
                System.out.println("Deposit successful.");
                System.out.println("------------------------------------------------");
                System.out.println(account.get(i).toString());
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            System.out.println("Account not found.");
        }
    }


    //we withdraw here
    //we check for an existing account number
    //implementation of withdraw for Savingsaccount and Currentaccount
    //is different. We also check for a valid amount greater than 0
    //we also check if our funds are sufficient by calling on the 
    //withdraw method. if it returns true then our fund is sufficient
    //if it returns false then we don't do any withdrawal
    public static void goWithdraw() {
        System.out.print("Enter account number: ");
        String number = in.nextLine();
        int flag = 0;
        for (int i = 0; i < account.size(); i++) {
            if (number.equals(account.get(i).getAcctNumber())) {
                do {
                    System.out.print("Enter amount to be withdrawn: ");
                    double amount = Double.parseDouble(in.nextLine());
                    if (amount > 0) {
                        if (account.get(i).withdraw(amount)) {
                            System.out.println("Withdrawal succcessful.");
                            break;
                        } else {
                            System.out.println("Insufficient funds.");
                            break;
                        }
                    } else {
                        System.out.println("Invalid amount.");
                    }
                } while (true);
                System.out.println("------------------------------------------------");
                System.out.println(account.get(i).toString());
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            System.out.println("account not found.");
        }
    }


    //we transfer money by specifying two accounts to transfer and receive
    //we also specify the amount the be transfered
    //if accounts are not found then no transfer is conducted
    //we also check if amount is sufficient to transfer in relation with balance
    //otherwise we don't transfer
    public static void transferMoney() {
        System.out.print("Enter account number transferring: ");
        String number1 = in.nextLine();
        System.out.print("Enter account number receiving:");
        String number2 = in.nextLine();
        int index1 = 0;
        int index2 = 0;
        int flag = 0;
        for (int i = 0; i < account.size(); i++) {
            if (number1.equals(account.get(i).getAcctNumber())) {
                index1 = i;
                flag++;
            }
            if (number2.equals(account.get(i).getAcctNumber())) {
                index2 = i;
                flag++;
            }
        }
        if (flag == 2) {
            System.out.print("Enter amount to transfer: ");
            double amount = Double.parseDouble(in.nextLine());
            if (account.get(index2).transferAmount(account.get(index1), amount)) {
                System.out.println("Money transfer succesful.");
                System.out.println("===============================================");
                System.out.println(account.get(index2).toString());
                System.out.println("===============================================");
                System.out.println(account.get(index1).toString());
            } else {
                System.out.println("Money transfer unsuccessful. Insuffiecient funds.");
            }
        } else {
            System.out.println("account not found.");
        }
    }


    //here is our main method
    public static void main(String[] args) {
        //we just call all methods that are created above in a do while loop and switch case
        System.out.println("===========(Welcome to Banking System)==========");
        do {
            switch (bankMenu()) {
                case 1:
                    createNewAccount();
                    break;
                case 2:
                    updateInterest();
                    break;
                case 3:
                    addInterest();
                    break;
                case 4:
                    numAccounts();
                    break;
                case 5:
                    search();
                    break;
                case 6:
                    goDeposit();
                    break;
                case 7:
                    goWithdraw();
                    break;
                case 8:
                    transferMoney();
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (true);
    }
}