package com.qloudbiz.product.pojo;

import java.util.Date;

import lombok.Data;

import com.alibaba.fastjson.annotation.JSONField;
import com.qloudbiz.core.annotations.Pojo;

@Data
public class ProductLine {
	@Pojo(name = "lineId")
	private String lineId;
	
	@Pojo(name = "lineCode")
	private String lineCode;
	
	@Pojo(name = "lineName")
	private String lineName;
	
	@Pojo(name = "parentId")
	private String parentId;
	
	@Pojo(name = "sort")
	private Integer sort;
	
	@Pojo(name = "status")
	private String status;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Pojo(name ="createdTime")
	private Date createdTime;
	
	@Pojo(name = "creatorId")
	private String creatorId;
	
	@Pojo(name = "creatorName")
	private String creatorName;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Pojo(name = "modifyTime")
	private Date modifyTime;
	
	@Pojo(name = "modifierId")
	private String modifierId;
	
	@Pojo(name = "modifierName")
	private String modifierName;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@Pojo(name = "lastModifyTime")
	private Date lastModifyTime;
	
	@Pojo(name = "ownerCompanyCode")
	private String ownerCompanyCode;
	
	@Pojo(name = "tenantId")
	private String tenantId;
	
	
}
