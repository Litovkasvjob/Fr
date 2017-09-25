package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Serg on 17.09.2017.
 */
public class HibernateUtils {
    private static final SessionFactory ourSessionFactory;
    //private static final ServiceRegistry serviceRegistry;

    static {
        try {
            //Configuration configuration = new Configuration();
           // configuration.configure();

            //serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            //ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
            //ourSessionFactory = new Configuration().configure().buildSessionFactory();


            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties());
            ourSessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        Session session = ourSessionFactory.openSession();
        System.out.println("session open");
        return session;
    }

}