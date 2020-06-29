package com.nexos.app.calculator.soap.api.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.nexos_test.spring.soap.api.calculator.OperacionRequest;
import com.nexos_test.spring.soap.api.calculator.OperacionResponse;

@Service
public class SoapClient {

	@Autowired
	private Jaxb2Marshaller marshaller;
	
	private WebServiceTemplate template;
	
	public OperacionResponse operar(OperacionRequest request) {
		template = new WebServiceTemplate(marshaller);
		OperacionResponse response = (OperacionResponse) template.marshalSendAndReceive("http://localhost:8091/WS",request);
		return response;
	}
}
