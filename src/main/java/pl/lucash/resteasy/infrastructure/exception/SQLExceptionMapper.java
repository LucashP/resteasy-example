package pl.lucash.resteasy.infrastructure.exception;

import pl.lucash.resteasy.infrastructure.Error;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.sql.SQLException;

@Provider
public class SQLExceptionMapper implements ExceptionMapper<SQLException> {

    @Override
    public Response toResponse(SQLException exception) {
        exception.printStackTrace();
        return Response
                .status(500)
                .entity(new Error(exception.getLocalizedMessage(), exception.getClass().getCanonicalName()))
                .build();
    }
}
