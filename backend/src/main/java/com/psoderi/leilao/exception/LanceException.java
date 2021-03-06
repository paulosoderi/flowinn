package com.psoderi.leilao.exception;

import com.psoderi.leilao.enums.ErrorCode;

public class LanceException extends GenericException {

	private static final long serialVersionUID = 1149241039409861914L;

	public LanceException(ErrorCode code) {
		super(code);
	}

	public LanceException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	@Override
	public Throwable getCause(){
		return super.getCause();
	}
}
