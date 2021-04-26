import entity.User;
import util.DatabaseUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = DatabaseUtil.getUsers();
        users.forEach(user -> System.out.println(user.getLogin()));
    }
}
