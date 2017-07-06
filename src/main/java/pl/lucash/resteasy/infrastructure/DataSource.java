package pl.lucash.resteasy.infrastructure;

import org.apache.log4j.Logger;
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

@Singleton
@Startup
@ApplicationScoped
public class DataSource {

    private static final Logger LOGGER = Logger.getLogger(DataSource.class);

    private SessionFactory sessionFactory;

    @PostConstruct
    public void postConstruct() {
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void preDestroy() {
        try {
            tearDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUp() throws Exception {
        LOGGER.debug("setUp method()");
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory(registry);
            LOGGER.debug(sessionFactory);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private void tearDown() throws Exception {
        LOGGER.debug("tearDown method()");
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public Session beginTransaction() {
        LOGGER.debug("beginTransaction method()");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    public void endTransaction(Session session) {
        LOGGER.debug("endTransaction method()");
        session.getTransaction().commit();
        session.close();
    }
}
