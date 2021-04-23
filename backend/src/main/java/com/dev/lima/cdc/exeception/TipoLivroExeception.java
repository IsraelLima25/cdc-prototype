package com.dev.lima.cdc.exeception;

public class TipoLivroExeception extends RuntimeException {

	private static final long serialVersionUID = 1L;
	

	public TipoLivroExeception(String msg) {
		super(msg);
	}

	public TipoLivroExeception(String msg, Throwable cause) {
		super(msg, cause);
	}
	

}
