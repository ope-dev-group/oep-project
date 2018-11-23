package com.qloudbiz.core.result;

public enum ResultStatusEnum {
	
	SUCCESS("200"),
	ERROR("500");
	
	private String code; //状态编码

	
	private ResultStatusEnum(String code) {
		this.code = code;
		
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	
}
