package com.example.demo.exception;


public enum ErrorEnum {
	// code error Category : 100* 
	NAME_CATE_NOTNULL(1001,"Name Category not null"),CATE_NOTFOUND(1002,"ID Category not found"),CATE_ALREADY_EXISTS(1003,"Name Category already exist"),
	
	// code error product : 110* 
	PRODUCT_NULL(1101,"Product already exists "),NAME_PRODUCT_NULL(1102,"Name Product not null"),PRICE_PRODUCT_NULL(1103,"Price Product not null"),
	PRICE_PRODUCT_NOT_NEGATIVE(1104,"Price Product not negative"),STATUS_PRODUCT_NOT_NULL(1105,"Status Product not null"),
	DISSCOUNT_PRODUCT_NULL(1106,"Price Product not null"),DISSCOUNT_PRODUCT_NOT_NEGATIVE(1107,"Price Product not negative"),
	ID_CATE_PRODUCT_NULL(1108,"Id category Product not null"),ID_CATE_PRODUCT_MIN(1109,"Id category Product must be bigger than 0")
	;
	private int code;
	private String message;
	private ErrorEnum(int code, String message) { 
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
	

}
