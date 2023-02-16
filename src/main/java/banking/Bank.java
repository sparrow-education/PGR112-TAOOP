package banking;

import java.util.HashMap;

public class Bank {
    private static final User user1 = new User("user1", "123");
    private static final User user2 = new User("user2", "123");
    private static final HashMap<String, String> myUsers = new HashMap<>();
    static
    {
        myUsers.put(user1.getUserName(), user1.getPassword());
        myUsers.put(user2.getUserName(), user2.getPassword());
    }

    public static HashMap<String, String> getMyUsers() {
        return myUsers;
    }
}
