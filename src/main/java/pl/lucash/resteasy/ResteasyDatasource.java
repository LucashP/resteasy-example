package pl.lucash.resteasy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class ResteasyDatasource {
    private static ResteasyDatasource instance;

    private SessionFactory sessionFactory;

    private ResteasyDatasource() {
    }

    public static ResteasyDatasource getInstance() {
        if (instance == null) {
            instance = new ResteasyDatasource();
        }
        return instance;
    }

    public static void init() {
        getInstance();

        try {
            instance.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finalize() {
        try {
            tearDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory(registry);
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private void tearDown() throws Exception {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public Session beginTransaction() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    public void endTransaction(Session session) {
        session.getTransaction().commit();
        session.close();
    }
}
