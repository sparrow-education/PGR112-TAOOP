package banking;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;


public class Terminal {
    private static final Scanner scan = new Scanner(System.in);
    private static final ArrayList<String> login = new ArrayList<>();
    private static final ArrayList<String> menu = new ArrayList<>();
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

    public static void main(String[] args) {
        mainMenu();
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
            User authUser;
            if (Bank.getMyUsers().get(usr).equals(pw)) {
                authUser = new User(usr, pw);
                System.out.println("=".repeat(25));
                System.out.printf("\rWelcome back %s \r\n", authUser.getUserName());
                System.out.println("=".repeat(25));

                while (true) {
                    System.out.printf("%-8s %-10s", "", usr);
                    displayBankingMenu();
                    String choice = scan.nextLine();
                    if (choice.equals("5")) {
                        System.out.println("Logout");
                        totalBalance = 0;
                        withdraw = 0;
                        deposit = 0;
                        transfer = 0;
                        break;
                    }
                    bankingMenuHandler(authUser, choice);
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
            case "5" -> mainMenu();
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
            totalBalance += amount;
            deposit = amount;
            usr.setAccountBalance(totalBalance);
//            System.out.println("_".repeat(25));
//            System.out.printf("You deposited: %s \r\n", amount);
//            System.out.println("_".repeat(25));
            printHandler(usr.getUserName(), usr.getAccountBalance(), deposit, withdraw, transfer);
            deposit = 0;
            return totalBalance;
        }
        return 0;
    }

    private static int withdrawHandler(User usr) throws NullPointerException {
        assert usr != null;
        System.out.print("Enter amount to withdraw: ");
        int amount = scan.nextInt();
        scan.nextLine();
        if (amount > getBalance(usr) || amount < 0) {
            System.out.println("=".repeat(25));
            System.out.println("Insufficient funds");
            System.out.println("=".repeat(25));
        } else {
            totalBalance -= amount;
            withdraw = amount;
            usr.setAccountBalance(totalBalance);
            printHandler(usr.getUserName(), usr.getAccountBalance(), deposit, withdraw, transfer);
            withdraw = 0;
            return totalBalance;
        }
        return 0;
    }

    private static int transferHandler(User usr) {
        try {
            System.out.print("Receiver account: ");
            String target = scan.nextLine();
            //TODO Fix validation
            User valid = targetValidate(target);
            System.out.print("Transfer amount: ");
            int amount = scan.nextInt();
            scan.nextLine();
            totalBalance = usr.getAccountBalance();
            if (amount > totalBalance || amount < 0) {
                System.out.println("=".repeat(25));
                System.out.println("Insufficient funds");
                System.out.println("=".repeat(25));
            } else {
                totalBalance += amount;
                transfer = amount;
                usr.setAccountBalance(totalBalance);
                printHandler(usr.getUserName(), usr.getAccountBalance(), deposit, withdraw, transfer);
            }

        } catch (NullPointerException e) {
            System.out.println("Invalid account");
        } finally {
            mainMenu();
        }
        return 0;
    }

    private static User targetValidate(String usr) {
        String key;
        String value;
        if (Bank.getMyUsers().containsKey(usr)) {
            for (Map.Entry<String, String> user : Bank.getMyUsers().entrySet()) {
                if (!user.getKey().equals(usr)) {
                    key = user.getKey();
                    value = user.getValue();
                    return new User(key, value);
                }
                break;
            }
            //System.out.println(key + " " + value); should contain null can't transfer to yourself!
        }
        return null;
    }

    private static void displayBankingMenu() {
        System.out.println();
        menu.forEach(System.out::println);
        System.out.println();
    }

    private static void printHandler(String usr, int balance, int deposit, int withdraw, int transfer) {
        System.out.printf("\r\n%-20s %-15s %-15s %-15s %-15s %n", "Account:", "Balance", "Deposit", "Withdraw", "Transfer");
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("%-20s %-15d %-15d %-15d %-15d %n", usr, balance, deposit, withdraw, transfer);
        System.out.println("------------------------------------------------------------------------------");
    }
}
