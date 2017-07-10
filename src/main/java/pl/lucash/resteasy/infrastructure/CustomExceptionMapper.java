package pl.lucash.resteasy.infrastructure;

import org.hibernate.HibernateException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<HibernateException> {

    @Override
    public Response toResponse(HibernateException exception) {
        exception.printStackTrace();
        return Response
                .status(500)
                .entity(new Error(exception.getMessage(), exception.getClass().getCanonicalName()))
                .build();
    }

//    @Override
//    public Response toResponse(NotFoundException exception) {
//        exception.printStackTrace();
//        return Response
//                .status()
//                .entity(new Error(exception.getMessage(), exception.getClass().getCanonicalName()))
//                .build();
//    }

}
