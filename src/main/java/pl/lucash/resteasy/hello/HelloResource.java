package pl.lucash.resteasy.hello;

import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.log4j.Logger;
import pl.lucash.resteasy.infrastructure.DataSource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/hello")
public class HelloResource {

    private static final Logger LOGGER = Logger.getLogger(HelloResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting sayHello() {
        List<DataSource> contextualReferences = BeanProvider.getContextualReferences(DataSource.class, true);
        contextualReferences.forEach(dataSource -> LOGGER.info(dataSource.toString()));
        LOGGER.info("Say Hello");
        return new Greeting("Say Hello");
    }
}
