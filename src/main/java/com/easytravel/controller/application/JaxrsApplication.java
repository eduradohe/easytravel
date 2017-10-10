package com.easytravel.controller.application;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/easytravel")
public class JaxrsApplication extends Application {
}
