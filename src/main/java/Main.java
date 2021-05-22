import entity.*;
import util.DatabaseUtil;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<User> users = DatabaseUtil.getUsers();
        users.forEach(user -> System.out.println(user.getLogin()));

        List<Admin> admins = DatabaseUtil.getAdmins();
        admins.forEach(admin -> System.out.println(admin.getName()));

        List<Client> clients = DatabaseUtil.getClients();
        clients.forEach(client -> System.out.println(client.getAddress()));

        Set<Order> orders = DatabaseUtil.getOrdersFromClients();
        orders.forEach(order -> System.out.println(order.getDeliveryAddress()));

        List<Order> ordersList = DatabaseUtil.getOrders();
        orders.forEach(order -> System.out.println(order.getStatus()));

        DatabaseUtil.deleteClient(clients.get(0).getId());

        clients = DatabaseUtil.getClients();
        clients.forEach(client -> System.out.println(client.getAddress()));

        ordersList = DatabaseUtil.getOrders();
        orders.forEach(order -> System.out.println(order.getStatus()));
    }
}
