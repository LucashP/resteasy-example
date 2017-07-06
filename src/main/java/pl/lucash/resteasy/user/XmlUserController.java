package pl.lucash.resteasy.user;

import pl.lucash.resteasy.user.dto.UserDTO;

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
    public UserDTO addUser(UserDTO userDTO) {
        return userResource.add(userDTO);
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<UserDTO> getUsers() {
        return userResource.all();
    }
}
