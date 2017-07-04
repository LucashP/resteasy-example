package pl.lucash.resteasy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Path("/hello")
public class HelloResource {

    private static final Logger LOGGER = Logger.getLogger(HelloResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting sayHello() {
        LOGGER.info("Say Hello");
        return new Greeting("Say Hello");
    }
}
