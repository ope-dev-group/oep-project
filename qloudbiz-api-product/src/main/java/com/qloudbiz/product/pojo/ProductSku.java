package com.qloudbiz.product.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 产品Sku
 * @author Administrator
 *
 */
@Data
public class ProductSku {
	String skuId ;
    String productId;
    String skuCode;
    String skuName;
    String barCode;
    String status;
    BigDecimal stockQty;
    BigDecimal stockAmount;
    Timestamp createdTime;
    String creatorId;
    String creatorName;
    Timestamp modifyTime;
    String modifierId;
    String modifierName;
    Timestamp lastModifyTime;
    String tenantId ;
    List<ProductSkuAttribute> skuAttributes; 
}
