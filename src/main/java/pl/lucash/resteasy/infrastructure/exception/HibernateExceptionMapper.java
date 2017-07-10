package pl.lucash.resteasy.infrastructure.exception;

import org.hibernate.HibernateException;
import pl.lucash.resteasy.infrastructure.Error;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class HibernateExceptionMapper implements ExceptionMapper<HibernateException> {

    @Override
    public Response toResponse(HibernateException exception) {
        exception.printStackTrace();
        return Response
                .status(500)
                .entity(new Error(exception.getLocalizedMessage(), exception.getClass().getCanonicalName()))
                .build();
    }
}
