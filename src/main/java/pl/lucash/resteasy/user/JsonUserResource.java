package pl.lucash.resteasy.user;

import org.apache.log4j.Logger;
import pl.lucash.resteasy.user.dto.UserDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/json/users")
public class JsonUserResource {

    private static final Logger LOGGER = Logger.getLogger(JsonUserResource.class);
    private UserService userService;

    public JsonUserResource() {
    }

    @Inject
    public JsonUserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO addUser(UserDTO user) {
        return userService.add(user);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getUsers() {
        return userService.all();
    }
}