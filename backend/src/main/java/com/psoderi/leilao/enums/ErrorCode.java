package com.psoderi.leilao.enums;

public enum ErrorCode  {
	
	LANCE_MENOR_VALOR_ATUAL(1, "O lance deve ser maior que o valor autal do item"),
	ITEM_NAO_EXISTE(2, "Item não existe");
	
	private final int code;
	private final String message;
	
	ErrorCode (int code, String message){
        this.code = code;
        this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}