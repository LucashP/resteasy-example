package pl.lucash.resteasy.resource;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserResource {

    private Set<User> users = new HashSet<User>();
    private static UserResource instance;

    private UserResource() {
        User user = new User("testoweImie", "testoweNazwisko");
        users.add(user);
    }

    public Set<User> all() {
        return users;
    }

    public User add(User user) {
        user.setUuid(UUID.randomUUID().toString());
        users.add(user);
        return user;
    }

    public static UserResource getInstance() {
        if (instance == null) {
            instance = new UserResource();
        }
        return instance;
    }
}
