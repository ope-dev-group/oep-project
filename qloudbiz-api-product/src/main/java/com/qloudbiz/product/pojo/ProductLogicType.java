package com.qloudbiz.product.pojo;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

@Data
public class ProductLogicType {
	
	private String typeId;	
	private String typeCode;	
	private String typeName;	
	private String parentId;		
	private boolean isPhysical = false;	
	private String prodLineId;	
	private Integer sort;	
	private String status;	
	private String remark;	
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
	//子节点
	private List<ProductLogicType> children;

}
