package pl.lucash.resteasy.infrastructure;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@Singleton
@Startup
@ApplicationScoped
public class DataSource {

    private static final Logger LOGGER = Logger.getLogger(DataSource.class.getName());
    private SessionFactory sessionFactory;

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("postConstruct() method " + this.toString());
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void preDestroy() {
        LOGGER.info("preDestroy() method " + this.toString());
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
