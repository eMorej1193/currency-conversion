package com.martrust.currency.conversion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MartrustRuntimeExcpetion extends Exception{

	private static final long serialVersionUID = 1L;

	public MartrustRuntimeExcpetion(String message){
    	super(message);
    }
}
