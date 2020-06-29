package com.nexos.app.calculator.soap.api.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.nexos.app.calculator.soap.api.services.CalculadoraService;
import com.nexos_test.spring.soap.api.calculator.OperacionRequest;
import com.nexos_test.spring.soap.api.calculator.OperacionResponse;

@Endpoint
public class CalculadoraEndpoint {

	@Autowired
	CalculadoraService calculadoraService;
	
	@PayloadRoot(namespace = "http://www.nexos-test.com/spring/soap/api/calculator",
			localPart = "OperacionRequest")
	@ResponsePayload
	public OperacionResponse sumaResponse(@RequestPayload OperacionRequest request) {
		return calculadoraService.Operar(request);		
	}
}
