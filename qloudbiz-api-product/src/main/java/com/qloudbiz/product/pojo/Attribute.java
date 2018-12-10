package com.qloudbiz.product.pojo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class Attribute {
	
	private String attributeId;	
	private String attributeName;
	private String attributeCode;
	private String prodTypeId;
	private String attributeType;	
	private String isRequired;
	private String attributeContraint;
	private String maxStringLength;
	private String dateTimeFormat;	
	private String numberFormat;
	private String numberUnit;	
	private Integer sort;
	private String status;
	private String isSearchForMiddlePlatform;
	private String isSearchForShoppingMall;
	private String description;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createdTime;	
	private String creatorId;
	private String creatorName;	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date modifyTime;
	private String modifierId;	
	private String modifierName;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date lastModifyTime;
	private String tenantId;
	private String attributeGroupId;
	//字节
	private String attrributeValues;
	//这是枚举值对象
	private EnumAttribute enumAttribute;

}
