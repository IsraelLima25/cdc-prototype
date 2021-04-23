package com.dev.lima.cdc.exeception;

public class ImageCapaDuplicateDropBoxException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	

	public ImageCapaDuplicateDropBoxException(String msg) {
		super(msg);
	}

	public ImageCapaDuplicateDropBoxException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
