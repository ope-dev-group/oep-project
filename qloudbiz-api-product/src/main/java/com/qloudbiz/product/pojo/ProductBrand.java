package com.qloudbiz.product.pojo;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

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
	private Timestamp createdTime;
	private String creatorId;
	private String creatorName;
	private Timestamp modifyTime;
	private String modifierId;
	private String modifierName;
	private Timestamp lastModifyTime;
	private String ownerCompanyCode;
	private String tenantId;
	
	
}
