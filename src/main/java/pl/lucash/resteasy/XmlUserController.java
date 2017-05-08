package pl.lucash.resteasy;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/xml/users")
public class XmlUserController {

    private UserResource userResource;

    public XmlUserController(UserResource userResource) {
        this.userResource = userResource;
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    User addUser(User user) {
        return userResource.add(user);
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    List<User> getUsers() {
        return userResource.all();
    }
}
