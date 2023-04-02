package com.lcwd.hotel.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lcwd.hotel.payload.ApiResponce;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
//	@ExceptionHandler(ResourceNotfoundException.class)
//	public ResponseEntity<ApiResponce> handlerResourceNotFoundException(ResourceNotfoundException ex) {
//		
//		String message = ex.getMessage();
//		
//		ApiResponce responce = ApiResponce.builder().message(message).succes(true).status(HttpStatus.NOT_FOUND).build();
//		
//		return new ResponseEntity<ApiResponce>(responce,HttpStatus.NOT_FOUND);
//	}

	@ExceptionHandler(ResourceNotfoundException.class)
	public ResponseEntity<Map<String, Object>> notFoundHandler(ResourceNotfoundException ex){
		
		Map<String, Object> map=new HashMap<>();
		map.put("message", ex.getMessage());
		map.put("sucess", false);
		map.put("status", HttpStatus.NOT_FOUND);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
}
