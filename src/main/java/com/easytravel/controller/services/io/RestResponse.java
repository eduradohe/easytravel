package com.easytravel.controller.services.io;

public class RestResponse<T> {
	
	private Integer returnCode;
	private String returnMessage;
	private T returnObject;
	
	private RestResponse() {
		super();
	}
	
	public RestResponse( final Integer returnCode, final String returnMessage ) {
		this();
		this.returnCode = returnCode;
		this.returnMessage = returnMessage;
	}
	
	public RestResponse( final Integer returnCode, final String returnMessage, final T returnObject ) {
		this(returnCode, returnMessage);
		this.returnObject = returnObject;
	}

	public Integer getReturnCode() {
		return returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public T getReturnObject() {
		return returnObject;
	}
}
