package banking.model.dto;

public class User {
    private int id;
    private String userName;
    private String password;
    private int accountBalance;


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.accountBalance = 0;
    }

    public User(int id, String userName, String password, int accountBalance) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.accountBalance = accountBalance;
    }

    public User() {

    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("User: %s%n Password: %s%n Account balance: %d%n", userName, password, accountBalance);
    }
}
