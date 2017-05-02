package pl.lucash.resteasy.controller;

import pl.lucash.resteasy.resource.User;
import pl.lucash.resteasy.resource.UserResource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Path("/xml/users")
public class XmlUserController {

    private UserResource userResource;

    public XmlUserController() {
        userResource = UserResource.getInstance();
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public User addUser(User user) {
        return userResource.add(user);
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Set<User> getUsers() {
        return userResource.all();
    }
}
