package com.jumia.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider		// component classes
public class JumiaExceptionMapper implements ExceptionMapper<JumiaException> {

	@Override
	public Response toResponse(JumiaException ex) {
		return Response.status(Status.BAD_REQUEST)
				.entity(ex.getExceptionMessage()).build();
	}

}
