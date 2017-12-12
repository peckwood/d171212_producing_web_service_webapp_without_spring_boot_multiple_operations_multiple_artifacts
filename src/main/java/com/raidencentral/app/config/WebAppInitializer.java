package com.raidencentral.app.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

@Configuration
@EnableWs
@ComponentScan("com.raidencentral.app.ws")
public class WebAppInitializer implements WebApplicationInitializer {
	static {
		System.out.println("WebAppInitializer created");
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(WebServiceConfig.class);
		ctx.setServletContext(servletContext);
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(ctx);
		servlet.setTransformWsdlLocations(true);
		Dynamic dynamic = servletContext.addServlet("messageDispatcherServlet", servlet);
		dynamic.addMapping("/ws/*");
		dynamic.setLoadOnStartup(1);
	}
}