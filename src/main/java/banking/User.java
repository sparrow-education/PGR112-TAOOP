package banking;

public class User {
    private final String userName;
    private final String password;
    private int accountBalance;


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.accountBalance = 0;
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

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
