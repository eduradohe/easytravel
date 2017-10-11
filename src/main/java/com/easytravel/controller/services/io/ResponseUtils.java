package com.easytravel.controller.services.io;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ResponseUtils {

	private ResponseUtils() {
		super();
	}
	
	public static Response okResponse( final Object result ) {
		return Response.status(Status.OK).entity(result).build();
	}
	
	public static Response noContentResponse( final Object result ) {
		return Response.status(Status.NO_CONTENT).entity(result).build();
	}
	
	public static Response noContentResponse() {
		return Response.status(Status.NO_CONTENT).build();
	}
	
	public static Response badRequestResponse() {
		return Response.status(Status.BAD_REQUEST).build();
	}
}
