package pl.lucash.resteasy;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class ResteasyApplication extends Application {

    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();

    private ResteasyDatasource resteasyDatasource;
    private UserResource userResource;

    public ResteasyApplication() {
        init();

        singletons.add(new JsonUserController(userResource));
        singletons.add(new XmlUserController(userResource));
    }

    private void init() {
        resteasyDatasource = ResteasyDatasource.init();
        userResource = UserResource.getInstance(resteasyDatasource);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}

