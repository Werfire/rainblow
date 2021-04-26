import entity.*;
import util.DatabaseUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = DatabaseUtil.getUsers();
        users.forEach(user -> System.out.println(user.getLogin()));

        List<Admin> admins = DatabaseUtil.getAdmins();
        admins.forEach(admin -> System.out.println(admin.getName()));

        List<Client> clients = DatabaseUtil.getClients();
        clients.forEach(client -> System.out.println(client.getEmail()));
    }
}
