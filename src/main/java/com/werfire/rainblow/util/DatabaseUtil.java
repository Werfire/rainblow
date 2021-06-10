package com.werfire.rainblow.util;

import com.werfire.rainblow.models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class DatabaseUtil {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseUtil.class);

    public static List<User> getUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).getResultList();
        }
    }

    public static List<Client> getClients() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Client", Client.class).getResultList();
        }
    }

    public static User getUser(String login, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<User> result = session.createQuery("from User where login = :login and password = :password",
                    User.class).setParameter("login", login).setParameter("password", password)
                    .getResultList();
            if(!result.isEmpty())
                return result.get(0);
            else
                return null;
        }
    }

    public static Client getClient(UUID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Client> result = session.createQuery("from Client where id = :id",
                    Client.class).setParameter("id", id).getResultList();
            if(!result.isEmpty())
                return result.get(0);
            else
                return null;
        }
    }

    public static Orders getCart(UUID clientId) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Orders> result = session.createQuery("from Orders where client.id = :client_id and " +
                    "status = 'shopping_cart'", Orders.class).setParameter("client_id", clientId).getResultList();
            if(!result.isEmpty())
                return result.get(0);
            else
                return null;
        }
    }

    public static void addItem(Item item) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
        }
    }

    public static void deleteClient(UUID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.delete(session.get(Client.class, id));
                transaction.commit();
        }
    }

    public static List<Admin> getAdmins() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Admin", Admin.class).getResultList();
        }
    }

    public static List<Orders> getOrders() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Orders", Orders.class).getResultList();
        }
    }

    public static List<Item> getItems() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Item", Item.class).getResultList();
        }
    }
    public static List<Equipment> getEquipments() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Equipment", Equipment.class).getResultList();
        }
    }

    public static List<SiteReservation> getSiteReservations() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from SiteReservation", SiteReservation.class).getResultList();
        }
    }

    public static List<PlayingSite> getPlayingSites() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from PlayingSite", PlayingSite.class).getResultList();
        }
    }

    public static Set<Orders> getOrdersFromClients() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Client> clients = session.createQuery("from Client", Client.class).getResultList();
            Set<Orders> allOrders = new HashSet<>(Collections.emptySet());
            clients.forEach(client -> {
                Set<Orders> orders = client.getOrders();
                allOrders.addAll(orders);
            });
            return allOrders;
        }
    }
}
