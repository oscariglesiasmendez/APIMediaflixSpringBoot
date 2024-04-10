package com.castelao.mediaflix_v4.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;


	@ControllerAdvice
	public class ValidationExceptionHandler {

	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
	    List<String> errors = new ArrayList<>();

	    ex.getAllErrors().forEach(err -> {
	        // Realizar múltiples sentencias aquí
	        // Por ejemplo, agregar cada código de error a una lista
	        errors.add(err.getCode());
	        if (err.getArguments()!= null) {
	        	errors.add(err.getObjectName());
	        }
	        errors.add(err.getDefaultMessage());
	        // O realizar alguna otra lógica basada en el error
	        // ...
	    });

	    Map<String, List<String>> result = new HashMap<>();
	    result.put("errors", errors);

	    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	  }
	
}