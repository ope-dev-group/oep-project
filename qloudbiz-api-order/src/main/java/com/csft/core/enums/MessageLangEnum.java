package com.csft.core.enums;

/**
 * 消息语言枚举类
 * @author Administrator
 *
 */
public enum MessageLangEnum {
	MSG_LAGN_ZH("ZH","中文"),
	Msg_LANGE_EN("EN","英文");
	
	
	
	private MessageLangEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	private String code;
	private String name;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
