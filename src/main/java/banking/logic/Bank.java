package banking.logic;

import banking.jdbc.JDBC;
import banking.model.dao.UserRepo;
import banking.model.dto.User;

import java.sql.SQLException;
import java.util.HashMap;

public class Bank {
    private static final User user1 = new User("user1", "123");
    private static final User user2 = new User("user2", "123");
    private static final HashMap<String, User> myUsers = new HashMap<>();

    static {
        myUsers.put(user1.getUserName(), user1);
        myUsers.put(user2.getUserName(), user2);
        if (!checkUserExist()) {
            addDefaultUserToDB();
        }
    }

    public static HashMap<String, User> getMyUsers() {
        return myUsers;
    }

    private static boolean checkUserExist() {
        return UserRepo.read().stream().anyMatch(user ->
                user.getUserName().equals(user1.getUserName()) || user.getUserName().equals(user2.getUserName()));
    }

    private static void addDefaultUserToDB() {
        try (var con = JDBC.getConnection()) {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            var stmt = con.prepareStatement(sql);
            con.setAutoCommit(false);
            // Loop through HashMap to add to batch
            for (User user : myUsers.values()) {
                stmt.setString(1, user.getUserName());
                stmt.setString(2, user.getPassword());
                stmt.addBatch();
            }
            // Execute batch statements in one transaction
            var affectedRows = stmt.executeBatch();
            if (affectedRows.length > 0) {
                con.commit();
            } else {
                con.rollback();
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());

        }
    }

}
