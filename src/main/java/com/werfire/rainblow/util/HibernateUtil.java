package com.werfire.rainblow.util;

import com.werfire.rainblow.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static final String URL = "jdbc:mysql://localhost:3306/rainblow?serverTimezone=Europe/Samara";
    private static final String USER = "root";
    private static final String PASS = "Vehfibx-00";

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USER);
                settings.put(Environment.PASS, PASS);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "validate");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(Admin.class);
                configuration.addAnnotatedClass(Orders.class);
                configuration.addAnnotatedClass(Item.class);
                configuration.addAnnotatedClass(Equipment.class);
                configuration.addAnnotatedClass(SiteReservation.class);
                configuration.addAnnotatedClass(PlayingSite.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
