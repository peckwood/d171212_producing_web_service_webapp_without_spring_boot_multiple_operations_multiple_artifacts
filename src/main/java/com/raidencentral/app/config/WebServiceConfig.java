package com.raidencentral.app.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.validation.XmlValidator;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;

@EnableWs
@Configuration
@ComponentScan("com.raidencentral.app")
public class WebServiceConfig extends WsConfigurerAdapter {

	@Bean(name = "contract-status-update")
	public DefaultWsdl11Definition defaultWsdl11Definition() {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("contract-status-update-port");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://www.ouhaogroup.com/contract-status");
		//This is how you load multiple schemas
		//to load only one, use
		//wsdl11Definition.setSchema(schema);
		XsdSchemaCollection schemaCollection = new XsdSchemaCollection() {
			@Override
			public XsdSchema[] getXsdSchemas() {
				List<XsdSchema> schemas = new ArrayList<>();
				schemas.add(contractStatusSchema());
				schemas.add(responseSchema());
				return schemas.toArray(new XsdSchema[schemas.size()]);
			}
			@Override
			public XmlValidator createValidator() {
				return null;
			}
		};
		wsdl11Definition.setSchemaCollection(schemaCollection);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema contractStatusSchema() {
		return new SimpleXsdSchema(new ClassPathResource("web_service/contract-status.xsd"));
	}

	@Bean
	public XsdSchema responseSchema() {
		return new SimpleXsdSchema(new ClassPathResource("web_service/response.xsd"));
	}

}
