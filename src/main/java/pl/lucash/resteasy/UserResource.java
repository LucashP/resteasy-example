package pl.lucash.resteasy;

import org.hibernate.Session;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Named
@ApplicationScoped
public class UserResource {

    private static final Logger LOGGER = Logger.getLogger(UserResource.class.getName());
    private ResteasyDatasource datasource;

    public UserResource() {
    }

    @Inject
    public UserResource(ResteasyDatasource resteasyDatasource) {
        datasource = resteasyDatasource;
    }

    @SuppressWarnings("unchecked")
    public List<User> all() {
        LOGGER.info(this.toString());
        Session session = datasource.beginTransaction();
        List<User> result = (List<User>) session.createQuery("from User").list();
        datasource.endTransaction(session);
        return result;
    }

    public User add(User user) {
        LOGGER.info(this.toString());
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
