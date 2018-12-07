package com.qloudbiz.product.pojo;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;
import com.qloudbiz.core.annotations.Pojo;

@Data
public class ProductBrand {
	
	private String brandId;
	private String brandCode;
	private String brandName;
	private String brandType;
	private String remark;
	private Integer sort;
	private String status;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp createdTime;
	private String creatorId;
	private String creatorName;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp modifyTime;
	private String modifierId;
	private String modifierName;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Timestamp lastModifyTime;
	private String ownerCompanyCode;
	private String tenantId;
	
	
}
