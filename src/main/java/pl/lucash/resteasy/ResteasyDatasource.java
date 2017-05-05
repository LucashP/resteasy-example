package pl.lucash.resteasy;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class ResteasyDatasource {
    private SessionFactory sessionFactory;
    private static ResteasyDatasource instance;

    private ResteasyDatasource() {
    }

    private void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory(registry);

        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    protected void tearDown() throws Exception {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public static ResteasyDatasource getInstance() {
        if (instance == null) {
            instance = new ResteasyDatasource();
        }

        try {
            instance.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
