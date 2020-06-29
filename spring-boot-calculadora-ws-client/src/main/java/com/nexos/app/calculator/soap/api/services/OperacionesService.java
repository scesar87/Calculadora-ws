package com.nexos.app.calculator.soap.api.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexos.app.calculator.soap.api.client.SoapClient;
import com.nexos_test.spring.soap.api.calculator.OperacionRequest;
import com.nexos_test.spring.soap.api.calculator.OperacionResponse;

@Service
public class OperacionesService {
	
	Logger logger = LoggerFactory.getLogger(OperacionesService.class);
	
	@Autowired
	private SoapClient client;
	
	OperacionRequest request;
	OperacionResponse response;
	
	public OperacionResponse getPendiente(HashMap<String, Double> map) {
		Double x0, x1, y0, y1, x, y;
		request = new OperacionRequest();
		response = new OperacionResponse();
		
		x0 = map.get("x0");
		x1 = map.get("x1");
		y0 = map.get("y0");
		y1 = map.get("y1");
		
		request.setA(x0);
		request.setB(x1);
		request.setOperacion("resta");		
		response = client.operar(request);	
		x = response.getResultado();
		logger.info(x.toString());
		
		request.setA(y0);
		request.setB(y1);
		request.setOperacion("resta");		
		response = client.operar(request);		
		y = response.getResultado();
		logger.info(y.toString());
		
		request.setA(x);
		request.setB(y);
		request.setOperacion("division");		
		response = client.operar(request);
		
		return response;
	} 
	
	public OperacionResponse getPromedio(Map<String, Double> map) {
		int i = 0;
		Integer cantidadDatos = map.size()-1;
		request = new OperacionRequest();
		response = new OperacionResponse();
		
		for(Double entry : map.values()) {
			if(i == 0) {
				request.setA(entry);
			}else{
				request.setB(entry);
				request.setOperacion("suma");
				response = client.operar(request);	
				request.setA(response.getResultado());
			}
			i++;
		}
		
		request.setB(cantidadDatos);
		request.setOperacion("division");
		return client.operar(request);
		
	}
	
	public OperacionResponse getAreaTriangulo(Map<String, Double> map) {
		
		Double base, altura;
		request = new OperacionRequest();
		response = new OperacionResponse();
		
		base = map.get("base");
		altura = map.get("altura");
		
		request.setA(base);
		request.setB(altura);
		request.setOperacion("multiplicacion");
		response = client.operar(request);
		
		request.setA(response.getResultado());
		request.setB(2);
		request.setOperacion("division");
		
		return client.operar(request);
	}
	
	public OperacionResponse getAreaCirculo(Map<String, Double> map) {
		
		Double pi = Math.PI, radio;
		request = new OperacionRequest();
		response = new OperacionResponse();
		
		radio = map.get("radio");
		
		request.setA(radio);
		request.setB(radio);
		request.setOperacion("multiplicacion");
		response = client.operar(request);
		
		request.setA(pi);
		request.setB(response.getResultado());
		return client.operar(request);
	}
}
