package pl.lucash.resteasy.resource

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.boot.registry.StandardServiceRegistry
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration
import pl.lucash.resteasy.Event
import spock.lang.Shared
import spock.lang.Specification;

class AnnotationsIllustrationSpec extends Specification {
    @Shared SessionFactory sessionFactory;

    def setupSpec() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory(registry)
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry)
        }
    }

    def cleanupSpec() {
        if (sessionFactory != null) {
            sessionFactory.close()
        }
    }

    def "should add two events" () {
        given:
        Session session = sessionFactory.openSession()

        when:
        session.beginTransaction()
        session.save(new Event("Our very first event!", new Date()))
        session.save(new Event("A follow up event", new Date()))
        session.getTransaction().commit()
        session.close()

        // now lets pull events from the database and list them
        session = sessionFactory.openSession()
        session.beginTransaction()
        def result = session.createQuery("from Event").list()
        session.getTransaction().commit()
        session.close()

        then:
        result.size() == 2
    }
}
