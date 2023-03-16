package banking.model.dao;

import banking.jdbc.JDBC;
import banking.model.dto.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class UserRepo {
    private static final ArrayList<User> userRepoList = new ArrayList<>();
    private static final JDBC jdbc = new JDBC();
    private static final Connection con = JDBC.getConnection();

    public static User create(Scanner scan) {
        try {
            String insert = "INSERT INTO users (username, password) VALUES (?, ?)";
            System.out.print("Enter username: ");
            String username = scan.nextLine();
            System.out.print("Enter password: ");
            String password = scan.nextLine();

            var stmt = con.prepareStatement(insert);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.execute();
            return new User(username, password);
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public static ArrayList<User> read() {
        try {
            String select = "SELECT * FROM users";
            var stmt = con.createStatement();
            stmt.execute(select);
            var rs = stmt.getResultSet();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setAccountBalance(rs.getInt(4));
                userRepoList.add(user);
            }
            return userRepoList;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public static boolean update(Scanner scan) {
        String sql = "UPDATE users SET accountBalance = ? WHERE id = ?";
        try (var stmt = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            System.out.print("Enter receiver: ");
            String receiver = scan.nextLine();
            Optional<User> stream = retrieveUser(receiver);
            if (stream.isPresent()) {
                User user = stream.get();
                System.out.println("Enter amount to deposit: ");
                int amount = Integer.parseInt(scan.nextLine());
                var newBalance = user.getAccountBalance() + amount;
                stmt.setInt(1, newBalance);
                stmt.setInt(2, user.getId());
                try {
                    stmt.executeUpdate();
                    System.out.println("Successfully updated: " + newBalance);
                    con.commit();
                    return true;
                } catch (SQLException e) {
                    con.rollback();
                    System.out.println("Transaction failed: " + e.getMessage());
                }
            } else {
                System.out.println("User not found");
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Query failed: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static Optional<User> retrieveUser(String receiver) {
        return userRepoList.stream().filter(u -> u.getUserName().equalsIgnoreCase(receiver)).findFirst();
    }

    public static boolean delete(Scanner scan) {
        String sql = "DELETE FROM users WHERE id = ?";
        String reset = "ALTER TABLE users AUTO_INCREMENT = 1";
        try (var stmt = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            System.out.print("Enter username to delete: ");
            String input = scan.nextLine();
            var target = retrieveUser(input);
            if (target.isPresent()) {
                var user = target.get();
                stmt.setInt(1, user.getId());
                System.out.println("Are you sure you want to delete this user? (y/n)");
                String commit = scan.nextLine();
                if (commit.equalsIgnoreCase("y")) {
                    stmt.executeUpdate();
                    stmt.execute(reset);
                    con.commit();
                } else {
                    con.rollback();
                }
            } else {
                System.out.println("User not found");
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Query failed: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


}
