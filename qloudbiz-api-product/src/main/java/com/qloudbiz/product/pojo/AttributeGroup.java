package com.qloudbiz.product.pojo;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

import com.qloudbiz.core.annotations.Pojo;

/**
 * 属性组
 * @author Administrator
 *
 */
@Data
public class AttributeGroup {
	private String groupId;
	private String groupName;
	private String productTypeId;
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
