package com.easytravel.controller.application;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.easytravel.controller.services.SeatService;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Component
public class JerseyConfig extends ResourceConfig {
	
	@Value("${spring.jersey.application-path:/}")
	private String apiPath;
	
	public JerseyConfig() {
		this.registerEndpoints();
	}
	
	@PostConstruct
	public void init() {
		this.configureSwagger();
	}
	
	private void configureSwagger() {
		this.register(ApiListingResource.class);
		this.register(SwaggerSerializers.class);
		
		final BeanConfig config = new BeanConfig();
	     config.setConfigId("easytravel");
	     config.setTitle("EasyTravel Agency REST API");
	     config.setVersion("v1");
	     config.setContact("Eduardo Turella");
	     config.setSchemes(new String[] { "http", "https" });
	     config.setBasePath(this.apiPath);
	     config.setResourcePackage("com.easytravel.controller.services");
	     config.setPrettyPrint(true);
	     config.setScan(true);
	}
	
	private void registerEndpoints() {
		this.register(SeatService.class);
		this.register(WadlResource.class);
	}
}
