import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main{

    private static final int FIRST_CONTRACT_NUMBER = 202500001;
    private static final int STARTING_ID = 1;
    private static final double INITIAL_BALANCE = 0.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<BankAccount> accounts = new ArrayList<>();
        int input = -1;

        System.out.println("Welcome to our bank.");
        System.out.println("We currently do not have any accounts. You need to create your own. Please enter your name:");
        String owner = scanner.nextLine();
        System.out.println("Nice to meet you, "+owner+"! You have been assigned ID 1. Your contract number is 202500001. Your balance is 0 roubles and 0 kopecks.");
        System.out.println("Create a password:");
        String password = scanner.nextLine();
        BankAccount account = new BankAccount(owner, STARTING_ID, FIRST_CONTRACT_NUMBER, INITIAL_BALANCE, password);
        accounts.add(account);

        while(input != 0){
            System.out.println("\nSelect an action: \n1 - open an account; \n2 - deposit funds into account; \n3 - withdraw funds from account; \n4 - view account balance; \n5 - view transaction history; \n6 - search for account by attributes; \n7 - switch to another account; \n0 - leave the bank;");
            try {
                String inputStr = scanner.nextLine();
                input = Integer.parseInt(inputStr);
            } catch (NumberFormatException e) {
                System.out.println("Error. Please enter a number from the menu.");
                input = -1;
                continue;
            }

            if(input == 1){
                System.out.println("\nPlease enter your name:");
                String owner1 = scanner.nextLine();
                System.out.println("Nice to meet you, "+owner1+"! You have been assigned ID "+(accounts.size()+1)+". Your contract number is "+(FIRST_CONTRACT_NUMBER+accounts.size())+". Your balance is 0 roubles and 0 kopecks.");
                System.out.println("Create a password:");
                String password1 = scanner.nextLine();
                BankAccount account1 = new BankAccount(owner1, accounts.size()+1, FIRST_CONTRACT_NUMBER+accounts.size(), INITIAL_BALANCE, password1);
                accounts.add(account1);
                // смена аккаунта
                account = account1;

            } else if(input == 2){
                System.out.println("\nEnter the amount you wish to deposit into your account:");
                String str = scanner.nextLine();
                // проверка корректности введённого значения
                try{
                    // преобразование введённой строки в число
                    double change = Double.parseDouble(str);
                    account.depositMoney(change);
                } catch (NumberFormatException e) {
                    System.out.println("Error. Please enter a number.");
                }

            } else if(input == 3){
                System.out.println("\nEnter the amount you wish to withdraw from your balance:");
                String str = scanner.nextLine();
                // проверка корректности введённого значения
                try{
                    // преобразование введённой строки в число
                    double change = Double.parseDouble(str);
                    account.withdrawMoney(change);
                } catch (NumberFormatException e) {
                    System.out.println("Error. Please enter a number.");
                }

            } else if(input == 4){
                account.showBalance();

            } else if(input == 5){
                account.showTransactions();

            } else if(input == 6){
                System.out.println("\nSelect the attribute to search by: \n1 - owner; \n2 - id; \n3 - contract number;");
                int choice = scanner.nextInt();
                int c = 0;
                System.out.println("Enter attribute:");
                scanner.nextLine();
                String search_attribute = scanner.nextLine();
                // проверка корректности введённого значения
                try{
                    if(choice==1){
                        for(int i=0; i<accounts.size(); i++){
                            if(accounts.get(i).searchByOwner(search_attribute)){
                                accounts.get(i).showInformation();
                                c++;
                            }
                        }   
                    } else if(choice==2){
                        // преобразование введённой строки в число
                        int int_search_attribute = Integer.parseInt(search_attribute);
                        for(int i=0; i<accounts.size(); i++){
                            if(accounts.get(i).searchById(int_search_attribute)){
                                accounts.get(i).showInformation();
                                c++;
                            }
                        }
                    } else if(choice==3){
                        // преобразование введённой строки в число
                        int int_search_attribute = Integer.parseInt(search_attribute);
                        for(int i=0; i<accounts.size(); i++){
                            if(accounts.get(i).searchByContractNumber(int_search_attribute)){
                                accounts.get(i).showInformation();
                                c++;
                            }
                        }
                    }
                    if(c==0){
                        System.out.println("There are no accounts with this attribute.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error. Incorrect attribute entry.");
                }

            } else if(input == 7){
                System.out.println("\nEnter the ID of the account you wish to log into:");
                String new_id_str = scanner.nextLine();
                // проверка корректности введённого значения
                try{
                    // преобразование введённой строки в число
                    int new_id = Integer.parseInt(new_id_str);
                    // проверка существования счёта с индексом
                    if(new_id >= 1 && new_id <= accounts.size()){ 
                        System.out.println("Enter the password:");
                        String new_password = scanner.nextLine();
                        if(accounts.get(new_id-1).getPassword().equals(new_password)){
                            // смена аккаунта
                            account = accounts.get(new_id-1);
                            System.out.println("The account change was successful.");
                        } else {
                            System.out.println("Error. Problem with password.");
                        }
                    } else {
                        System.out.println("This ID does not exist.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error. Please enter a number.");
                }

            } else if(input == 0){
                System.out.println("\nThank you for using our services. Goodbye!");
                break;

            } else {
                System.out.println("\nError. There is no such action.");

            }
        }
    }
}