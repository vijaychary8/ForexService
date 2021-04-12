package com.cg.forex.exceptions;

public class CurrencyAlreadyExist extends RuntimeException {
	public CurrencyAlreadyExist(String errorMsg){
		super(errorMsg);
		}

}
