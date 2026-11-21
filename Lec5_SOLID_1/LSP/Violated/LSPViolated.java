import java.util.ArrayList;
import java.util.List;

public class LSPViolated {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new SavingAccount());
        accounts.add(new CurrentAccount());
        accounts.add(new FixedAccount());

        BankClient client = new BankClient(accounts);
        client.processTransactions(); // Throws exception when withdrawing from FixedTermAccount
 
    }
}

class BankClient{
    private List<Account> accounts;

    public BankClient(List<Account> a){
        this.accounts = a;
    }

    void processTransactions(){
        for(Account a: accounts){
            a.deposit(1000); // all accounts allow deposits

            // assuming all accounts support withdrawl (LSP violation)
            try{
                a.withdraw(500);
            }catch(UnsupportedOperationException e){
                System.out.println("Exception : "+e.getMessage());
            }
        }
    }



}

interface Account{
    void deposit(double amount);
    void withdraw(double amount);
}

class SavingAccount implements Account{
    private double balance;

    public SavingAccount(){
        balance =0;
    }

    @Override
    public void deposit(double amount){
        balance += amount;
        System.out.println("Deposited: "+ amount+" in Saving Account. New Balance: "+ balance);
    }

    @Override
    public void withdraw(double amount){
        if(balance >= amount){
            balance -= amount;
            System.out.println("Withdraw: "+ amount+" from Saving Account. New Balance: "+balance);
        }else{
            System.out.println("Insufficient funds in Savings Account! ");
        }
    }
    
}

class CurrentAccount implements Account{
    private double balance;

    public CurrentAccount(){
        balance =0;
    }

    @Override
    public void deposit(double amount){
        balance += amount;
        System.out.println("Deposited: "+ amount+" in Current Account. New Balance: "+ balance);
    }

    @Override
    public void withdraw(double amount){
        if(balance >= amount){
            balance -= amount;
            System.out.println("Withdraw: "+ amount+" from Current Account. New Balance: "+balance);
        }else{
            System.out.println("Insufficient funds in Current Account! ");
        }
    }
}

// breaking liskov substitution principle
// bcz can't withdraw from FD account
class FixedAccount implements Account{
    private double balance;

    public FixedAccount(){
        balance =0;
    }

    @Override
    public void deposit(double amount){
        balance += amount;
        System.out.println("Deposited: "+ amount+" in Saving Account. New Balance: "+ balance);
    }

     @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawal not allowed in Fixed Term Account!");
    }
}
