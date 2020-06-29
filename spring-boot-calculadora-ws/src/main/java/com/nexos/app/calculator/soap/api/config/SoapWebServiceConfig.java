package com.nexos.app.calculator.soap.api.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapWebServiceConfig extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(context);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(servlet, "/WS/*");
	}
	
	@Bean 
	public XsdSchema calculatorSchema() {
		return new SimpleXsdSchema(new ClassPathResource("Calculadora.xsd"));
	}
	
	@Bean(name = "calculadora")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema calculatorSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setSchema(calculatorSchema);
		definition.setLocationUri("/WS");
		definition.setPortTypeName("CalculatorServicePort");
		definition.setTargetNamespace("http://www.nexos-test.com/spring/soap/api/calculator");
		return definition;
	}
}
