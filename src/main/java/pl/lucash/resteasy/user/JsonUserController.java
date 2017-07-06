package pl.lucash.resteasy.user;

import pl.lucash.resteasy.user.dto.UserDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@Path("/json/users")
public class JsonUserController {

    private static final Logger LOGGER = Logger.getLogger(JsonUserController.class.getName());
    private UserResource userResource;

    public JsonUserController() {
    }

    @Inject
    public JsonUserController(UserResource userResource) {
        this.userResource = userResource;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO addUser(UserDTO user) {
        return userResource.add(user);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getUsers() {
        return userResource.all();
    }
}