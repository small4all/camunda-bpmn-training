package com.small.exception;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Throwable>  {

	@Override
	public Response toResponse(Throwable th) {
		Map<String, String> error = new HashMap<>();
		error.put("message", th.toString());
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
	}

}
