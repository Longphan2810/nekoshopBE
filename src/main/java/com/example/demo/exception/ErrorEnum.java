package com.example.demo.exception;


public enum ErrorEnum {
	// code error Category : 100* 
	NAME_CATE_NOTNULL(1001,"Name Category not null"),CATE_NOTFOUND(1002,"ID Category not found"),CATE_ALREADY_EXISTS(1003,"Name Category already exist"),
	CATE_CAN_NOT_DELETE(1104,"Category can not be delete"),
	
	// code error product : 110* 
	PRODUCT_NULL(1101,"Product not exists "),NAME_PRODUCT_NULL(1102,"Name Product not null"),PRICE_PRODUCT_NULL(1103,"Price Product not null"),
	PRICE_PRODUCT_NOT_NEGATIVE(1104,"Price Product not negative"),STATUS_PRODUCT_NOT_NULL(1105,"Status Product not null"),
	DISSCOUNT_PRODUCT_NULL(1106,"Price Product not null"),DISSCOUNT_PRODUCT_NOT_NEGATIVE(1107,"Price Product not negative"),
	ID_CATE_PRODUCT_NULL(1108,"Id category Product not null"),ID_CATE_PRODUCT_MIN(1109,"Id category Product must be bigger than 0"),NAME_PRODUCT_ALREADY_EXISTS(1110,"Name PRODUCT already exist")
	,
	
	// code error productDetail : 120*
	PRODUCT_DETAIL_NULL(1201,"Product Detail not exists "),
	
	//code  register : 130*
	USE_ALREADY_EXIST(1301,"User already exist"),PASS_INVALID(1302,"Password invalid(min 8 character)"),
	WRONG_PASS_CONFIRM(1303,"Wrong pass confirm "),MAIL_INVALID(1304,"Mail Invalid"),MAIL_NULL(1305,"Mail null"),
	EXPIRATION_TOKEN(1306,"EXPIRATION TOKEN"),OLD_TOKEN(1307,"Old TOKEN"),
	
	// code login : 140*
	USE_NOT_EXIST(1401,"User not exist"),BLOCKED_USER(1402,"Blocked user"),USER_NEED_VERIFY_ACCOUNT(1403,"User need verify account"),
	WRONG_PASSWORD(1404,"Wrong password"),
	
	// code order : 150*
	LARGER_STOCK(1501,"larger than the product in stock"),ADDRESS_DELIVERY_INVALID(1502,"need to set default  address")
	
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
