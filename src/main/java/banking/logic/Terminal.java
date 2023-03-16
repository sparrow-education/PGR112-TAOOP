package banking.logic;

import banking.model.dto.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Terminal {
    private static final Scanner scan = new Scanner(System.in);
    private static final ArrayList<String> login = new ArrayList<>();
    private static final ArrayList<String> menu = new ArrayList<>();
    private static final HashMap<String, User> currentUser = Bank.getMyUsers();
    private static int totalBalance;
    private static int deposit;
    private static int withdraw;
    private static int transfer;

    static {
        login.add("+-----------------+");
        login.add("|\t1. Login\t  |");
        login.add("|\t2. Exit\t\t  |");
        login.add("+-----------------+");
        menu.add("+-------------------+");
        menu.add("|\t1. Show balance\t|");
        menu.add("|\t2. Deposit\t\t|");
        menu.add("|\t3. Withdraw\t\t|");
        menu.add("|\t4. Transfer\t\t|");
        menu.add("|\t5. Logout\t\t|");
        menu.add("+-------------------+");
    }

    public static void mainMenu() {
        login.forEach(System.out::println);

        String choice = scan.nextLine();
        if (choice.equals("1")) {
            System.out.print("Enter username: ");
            String usr = scan.nextLine();
            System.out.print("Enter password: ");
            String pw = scan.nextLine();
            authenticateHandler(usr, pw);
        } else {
            System.out.println("Good-bye");
            System.exit(0);
        }
    }

    private static void authenticateHandler(String usr, String pw) {
        try {
            if (currentUser.containsKey(usr) && currentUser.get(usr).getPassword().equals(pw)) {
                System.out.println("=".repeat(25));
                System.out.printf("\rWelcome back %s \r\n", usr);
                System.out.println("=".repeat(25));

                totalBalance = Bank.getMyUsers().get(usr).getAccountBalance(); // get balance from user
                while (true) {
                    System.out.printf("%-8s %-10s", "", usr);
                    displayBankingMenu();
                    String choice = scan.nextLine();
                    if (choice.equals("5")) {
                        System.out.println("Logout");
                        break;
                    }
                    bankingMenuHandler(Bank.getMyUsers().get(usr), choice);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("=".repeat(25));
            System.out.println("User not found");
            System.out.println("=".repeat(25));
        } finally {
            mainMenu();
        }
    }

    private static void bankingMenuHandler(User usr, String choice) {
        switch (choice) {
            case "1" -> getBalance(usr);
            case "2" -> depositHandler(usr);
            case "3" -> withdrawHandler(usr);
            case "4" -> transferHandler(usr);
            case "5" -> {
                mainMenu();
                totalBalance = 0;
                withdraw = 0;
                deposit = 0;
                transfer = 0;
            }
        }
    }

    private static int getBalance(User usr) throws NullPointerException {
        assert usr != null;
        System.out.println("_".repeat(25));
        System.out.printf("Your balance is: %s \r\n", usr.getAccountBalance());
        System.out.println("_".repeat(25));
        return usr.getAccountBalance();
    }

    private static int depositHandler(User usr) throws NullPointerException {
        assert usr != null;
        System.out.print("Enter deposit amount: ");
        int amount = scan.nextInt();
        scan.nextLine(); // next line consumer
        if (amount < 0) {
            System.out.println("=".repeat(25));
            System.out.println("Invalid amount");
            System.out.println("=".repeat(25));
        } else {
            totalBalance = usr.getAccountBalance() + amount;
            deposit += amount;
            usr.setAccountBalance(totalBalance);
            printHandler(usr.getUserName(), usr.getAccountBalance(), deposit, withdraw, transfer);
            return totalBalance;
        }
        return 0;
    }

    private static int withdrawHandler(User usr) throws NullPointerException {
        assert usr != null;
        System.out.println("Enter amount to withdraw: ");
        int amount = scan.nextInt();
        scan.nextLine();
        if (amount > usr.getAccountBalance() || amount < 0) {
            System.out.println("Insufficient funds");
        } else {
            withdraw += amount;
            usr.setAccountBalance(usr.getAccountBalance() - amount);
            printHandler(usr.getUserName(), usr.getAccountBalance(), deposit, withdraw, transfer);
        }
        return 0;
    }

    private static int transferHandler(User usr) {
        assert usr != null;
        System.out.print("Enter receiver account: ");
        String targetAccount = scan.nextLine();
        User receiver = targetValidate(targetAccount);
        if (receiver == null) {
            System.out.println("Invalid account");
            return 0;
        }

        System.out.print("Enter transfer amount: ");
        int amount = scan.nextInt();
        scan.nextLine();

        if (getBalance(usr) < amount || amount <= 0) {
            System.out.println("=".repeat(25));
            System.out.println("Insufficient funds");
            System.out.println("=".repeat(25));
        } else {
            System.out.println(totalBalance);
            totalBalance -= amount;
            System.out.println(totalBalance);
            transfer += amount;
            usr.setAccountBalance(usr.getAccountBalance() - amount);
            receiver.setAccountBalance(receiver.getAccountBalance() + amount);
            printHandler(usr.getUserName(), usr.getAccountBalance(), deposit, withdraw, transfer);
        }
        return 0;
    }

    private static User targetValidate(String usr) {
        assert usr != null;
        return currentUser.get(usr);
    }

    private static void displayBankingMenu() {
        System.out.println();
        menu.forEach(System.out::println);
        System.out.println();
    }

    private static void printHandler(String usr, int balance, int deposit, int withdraw, int transfer) {
        String positive = (deposit > 0) ? "+" : "";
        String negative = (withdraw > 0) ? "-" : "";
        System.out.printf("\r\n%-20s %-15s %-15s %-15s %-15s %n", "Account:", "Balance", "Deposit", "Withdraw", "Transfer");
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("%-20s %-15d %s%-15d %s%-15d %-15d %n", usr, balance, positive, deposit, negative, withdraw, transfer);
        System.out.println("------------------------------------------------------------------------------");
    }
}
