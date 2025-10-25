import java.util.ArrayList;
import java.util.List;

class BankAccount{
    private int id, contractNumber;
    private String owner, password;
    private double balance;
    private List<String> transactions;
    
    public BankAccount(String owner, int id, int contractNumber, double balance, String password){
        this.owner = owner;
        this.id = id;
        this.contractNumber = contractNumber;
        this.balance = balance;
        this.password = password;
        this.transactions = new ArrayList<>();
    }

    public String getOwner() { return owner; }
    public int getId() { return id; }
    public int getContractNumber() { return contractNumber; }
    public double getBalance() { return balance; }
    public String getPassword() { return password; }
    public List<String> getTransactions() { return transactions; }

    public void showInformation(){
        System.out.println("Owner - "+this.owner+"; ID - "+this.id+"; contract number - "+contractNumber+";");
    }

    void depositMoney(double change){
        if(change<0){
            System.out.println("Error. You cannot deposit a negative amount.");
            return;
        }
        balance+=change;
        String operation = "Deposit by "+(int)change+" and "+(int)((change-(int)change)*100)+" kopecks";
        System.out.println(operation);
        transactions.add(operation);
    }

    void withdrawMoney(double change){
        if(change<0){
            System.out.println("Error. You cannot withdraw a negative amount.");
            return;
        }
        // проверка достаточно ли на счёте средств
        if(balance-change>=0){
            balance-=change;
            String operation = "Withdrawal of "+(int)change+" and "+(int)((change-(int)change)*100)+" kopecks";
            System.out.println(operation);
            transactions.add(operation);
        } else {
            System.out.println("Error, there are insufficient funds in the account.");
            String operation = "Error in withdrawal of "+(int)change+" roubles and "+(int)((change-(int)change)*100)+" kopecks from account";
            transactions.add(operation);
        }
    }

    void showBalance(){
        System.out.println("\nThere is "+(int)balance+" roubles and "+(int)((balance-(int)balance)*100)+" kopecks in the account.");
    }

    void showTransactions(){
        System.out.println("\nList of account transactions:");
        System.out.println(transactions);
    }

    boolean searchById(int id){
        return this.id == id;
    }

    boolean searchByOwner(String owner){
        return this.owner.equals(owner);
    }

    boolean searchByContractNumber(int contractNumber){
        return this.contractNumber == contractNumber;
    }
}