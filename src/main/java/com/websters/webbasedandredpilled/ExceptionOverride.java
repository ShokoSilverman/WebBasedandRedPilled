package com.websters.webbasedandredpilled;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionOverride extends ResponseEntityExceptionHandler{
	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<ExceptionMessage> somethingWentWrong(IllegalArgumentException ex){
		System.out.println("Illegal Argument Exception()");

		ExceptionMessage myEx = new ExceptionMessage(ex.getMessage(), 4);

		return new ResponseEntity<ExceptionMessage>(myEx, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalCallerException.class)
	public final ResponseEntity<ExceptionMessage> somethingWentWrong(IllegalCallerException ex){
		System.out.println("Illegal Caller Exception()");

		ExceptionMessage myEx = new ExceptionMessage(ex.getMessage(), 4);

		return new ResponseEntity<ExceptionMessage>(myEx, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

//	@ExceptionHandler(RuntimeException.class)
//	public final ResponseEntity<ExceptionMessage> somethingWentWrong(RuntimeException ex){
//		System.out.println("Runtime Exception()");
//
//		ExceptionMessage myEx = new ExceptionMessage(ex.getMessage(), 4);
//
//		return new ResponseEntity<ExceptionMessage>(myEx, new HttpHeaders(), HttpStatus.BAD_REQUEST);
//	}
}


