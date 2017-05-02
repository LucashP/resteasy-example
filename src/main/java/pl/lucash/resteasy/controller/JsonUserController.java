package pl.lucash.resteasy.controller;

import pl.lucash.resteasy.resource.User;
import pl.lucash.resteasy.resource.UserResource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/json/users")
public class JsonUserController {

    private UserResource userResource;

    public JsonUserController() {
        userResource = UserResource.getInstance();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(User user) {
        return userResource.add(user);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<User> getUsers() {
        return userResource.all();
    }

    @GET
    @Path("/test")
    public Response test() {
        return Response.ok(new User("a", "b"), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/test")
    public Response test1() {
        return Response.ok(new User("a", "b"), MediaType.APPLICATION_JSON).build();
    }
}
