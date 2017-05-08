package pl.lucash.resteasy;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class UserResource {

    private static UserResource instance;
    private final ResteasyDatasource datasource;

    private UserResource(ResteasyDatasource resteasyDatasource) {
        datasource = resteasyDatasource;
    }

    public static UserResource getInstance(ResteasyDatasource resteasyDatasource) {
        if (instance == null) {
            instance = new UserResource(resteasyDatasource);
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public List<User> all() {
        Session session = datasource.beginTransaction();
        List<User> result = (List<User>) session.createQuery("from User").list();
        datasource.endTransaction(session);
        return result;
    }

    public User add(User user) {
        user.setUuid(UUID.randomUUID().toString());
        Session session = datasource.beginTransaction();
        Serializable serializable = session.save(user);
        user = (User) session.get(User.class, serializable);
        datasource.endTransaction(session);
        return user;
    }

    public int add(int i, int j) {
        return i + j;
    }
}
