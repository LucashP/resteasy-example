package pl.lucash.resteasy.user;

import org.apache.log4j.Logger;
import pl.lucash.resteasy.user.dto.UserDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/xml/users")
public class XmlUserResource {

    private static final Logger LOGGER = Logger.getLogger(XmlUserResource.class);
    private UserService userService;

    public XmlUserResource() {
    }

    @Inject
    public XmlUserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public UserDTO addUser(UserDTO userDTO) {
        return userService.add(userDTO);
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<UserDTO> getUsers() {
        return userService.all();
    }
}
