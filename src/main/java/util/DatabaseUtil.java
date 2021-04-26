package util;

import entity.*;
import org.hibernate.Session;

import java.util.List;

public class DatabaseUtil {
    public static List<User> getUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    public static List<Client> getClients() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Client", Client.class).list();
        }
    }

    public static List<Admin> getAdmins() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Admin", Admin.class).list();
        }
    }

    public static List<Order> getOrders() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Order", Order.class).list();
        }
    }

    public static List<Item> getItems() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Item", Item.class).list();
        }
    }
    public static List<Equipment> getEquipments() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Equipment", Equipment.class).list();
        }
    }

    public static List<SiteReservation> getSiteReservations() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from SiteReservation", SiteReservation.class).list();
        }
    }

    public static List<PlayingSite> getPlayingSites() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from PlayingSite", PlayingSite.class).list();
        }
    }
}
