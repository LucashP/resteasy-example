package pl.lucash.resteasy;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@Path("/xml/users")
public class XmlUserController {

    private static final Logger LOGGER = Logger.getLogger(XmlUserController.class.getName());
    private UserResource userResource;

    public XmlUserController() {
    }

    @Inject
    public XmlUserController(UserResource userResource) {
        this.userResource = userResource;
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public User addUser(User user) {
        return userResource.add(user);
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<User> getUsers() {
        return userResource.all();
    }
}
