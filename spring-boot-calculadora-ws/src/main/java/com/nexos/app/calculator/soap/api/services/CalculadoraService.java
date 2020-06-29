package com.nexos.app.calculator.soap.api.services;

import org.springframework.stereotype.Service;

import com.nexos_test.spring.soap.api.calculator.OperacionRequest;
import com.nexos_test.spring.soap.api.calculator.OperacionResponse;

@Service
public class CalculadoraService {

	public OperacionResponse Operar(OperacionRequest request) {
		
		OperacionResponse response = new OperacionResponse();
		String operacion = request.getOperacion();
		switch(operacion) {
			case "suma" :
				response.setResultado(request.getA()+request.getB());
				break;
			case "resta" :
				response.setResultado(request.getA()-request.getB());
				break;
			case "multiplicacion" :
				response.setResultado(request.getA()*request.getB());
				break;
			case "division" :
				response.setResultado(request.getA()/request.getB());
				break;
			default:
				response.setResultado(100.0);
		}
		
		return response;
	}
}
