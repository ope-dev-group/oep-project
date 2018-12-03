package com.qloudbiz.product.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import com.qloudbiz.core.vo.PageVO;

@Data
public class ProductLineVO extends PageVO implements Serializable{
	
	/**
	 * productLine的属性，因为查询列表可能会有分页，因此继承PageVO
	 */
	private String lineId;
	private String lineCode;
	private String lineName;
	private String parentId;
	private Integer sort;
	private String status;
	private Date createdTime;
	private String creatorId;
	private String creatorName;
	private Date modifyTime;
	private String modifierId;
	private String modifierName;
	private Date lastModifyTime;
	private String ownerCompanyCode;
	private String tenantId;
	
	

}
