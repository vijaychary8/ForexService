package com.cg.forex.exceptions;

@SuppressWarnings("serial")
public class AdminNotFoundException extends RuntimeException{
	public AdminNotFoundException(String errorMsg){
		super(errorMsg);
		}
		public AdminNotFoundException(String msg,Throwable e){
		super(msg,e);
		}

}
