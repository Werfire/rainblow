package util;

import entity.User;
import org.hibernate.Session;

import java.util.List;

public class DatabaseUtil {
    public static List<User> getUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }
}
