package com.api.jwt.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.jwt.model.SumaRequest;

@RestController
@RequestMapping("api")
public class SumaController {
	
	@PostMapping(value = "suma")
	 public ResponseEntity<String> notificacionWhatsapp(@RequestBody SumaRequest req) {
		
		
		if(req.getNumero1() < 0 || req.getNumero2() < 0)
		{
			return new ResponseEntity<>("Error, solo puede ingresar números positivos", HttpStatus.NOT_ACCEPTABLE);
		}
		else {
			int total = req.getNumero1() + req.getNumero2();			
			return new ResponseEntity<>("El resultado es: " + Integer.toString(total), HttpStatus.OK);
		}
		
	}
	
}
