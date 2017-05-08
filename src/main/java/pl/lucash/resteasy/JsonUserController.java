package pl.lucash.resteasy;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/json/users")
public class JsonUserController {

    private UserResource userResource;

    public JsonUserController() {
        ResteasyDatasource instance = ResteasyDatasource.getInstance();
        userResource = UserResource.getInstance(instance);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(User user) {
        return userResource.add(user);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return userResource.all();
    }
}