package pl.lucash.resteasy.resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.lucash.resteasy.ResteasyDatasource;
import pl.lucash.resteasy.domain.User;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class UserResource {

    private static UserResource instance;
    private SessionFactory sessionFactory;

    private UserResource() {
        sessionFactory = ResteasyDatasource.getInstance().getSessionFactory();
    }

    public static UserResource getInstance() {
        if (instance == null) {
            instance = new UserResource();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public List<User> all() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<User> result = (List<User>) session.createQuery("from User").list();

        session.getTransaction().commit();
        session.close();
        return result;
    }

    public User add(User user) {
        user.setUuid(UUID.randomUUID().toString());
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Serializable serializable = session.save(user);
        user = (User) session.get(User.class, serializable);

        session.getTransaction().commit();
        session.close();
        return user;
    }
}
