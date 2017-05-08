package pl.lucash.resteasy;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class ResteasyApplication extends Application {

    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();

    public ResteasyApplication() {
        init();

        singletons.add(new JsonUserController());
        singletons.add(new XmlUserController());
    }

    private void init() {
        ResteasyDatasource.init();
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

