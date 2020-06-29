package com.nexos.app.calculator.soap.api.controllers;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nexos.app.calculator.soap.api.client.SoapClient;
import com.nexos.app.calculator.soap.api.services.OperacionesService;
import com.nexos_test.spring.soap.api.calculator.OperacionRequest;
import com.nexos_test.spring.soap.api.calculator.OperacionResponse;

@RestController
public class ControladorRest {
	
	Logger logger = LoggerFactory.getLogger(ControladorRest.class);
	
	@Autowired
	private SoapClient client;
	
	@Autowired
	private OperacionesService operacionesService;
	
	@PostMapping("/sumar")
	public OperacionResponse sumar(@RequestBody OperacionRequest request) {
		request.setOperacion("suma");
		return client.operar(request);
	}
	
	@PostMapping("/restar")
	public OperacionResponse restar(@RequestBody OperacionRequest request) {
		request.setOperacion("resta");
		return client.operar(request);
	}
	
	@PostMapping("/multiplicar")
	public OperacionResponse multiplicar(@RequestBody OperacionRequest request) {
		request.setOperacion("multiplicacion");
		return client.operar(request);
	}
	
	@PostMapping("/dividir")
	public OperacionResponse dividir(@RequestBody OperacionRequest request) {
		request.setOperacion("division");
		return client.operar(request);
	}
	
	@PostMapping("/operaciones")
	public OperacionResponse operaciones(@RequestBody HashMap<String, Double> map) {
		OperacionResponse response;
		int operacion = map.get("operacionMatematica").intValue();
		switch(operacion) {
			case 0:
				response = operacionesService.getPromedio(map);
				break;
			case 1:
				response = operacionesService.getPendiente(map);
				break;
			case 2:
				response = operacionesService.getAreaTriangulo(map);
				break;
			case 3:
				response = operacionesService.getAreaCirculo(map);
				break;
			default:
				response = new OperacionResponse();
		}
		
		return response;
	}
}
