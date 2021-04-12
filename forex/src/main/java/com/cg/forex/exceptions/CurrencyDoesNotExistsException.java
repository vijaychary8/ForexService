package com.cg.forex.exceptions;

@SuppressWarnings("serial")
public class CurrencyDoesNotExistsException extends  RuntimeException {
	public CurrencyDoesNotExistsException(String errorMsg){
		super(errorMsg);
		}

}
