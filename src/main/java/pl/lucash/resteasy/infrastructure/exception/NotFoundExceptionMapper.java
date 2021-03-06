package pl.lucash.resteasy.infrastructure.exception;

import pl.lucash.resteasy.infrastructure.Error;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(new Error(exception.getMessage(), exception.getClass().getCanonicalName()))
                .build();
    }

}
