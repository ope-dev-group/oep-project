-- ----------------------------
-- Procedure structure for PRODUCTBRAND_DELETE_PROCEDURE
-- ----------------------------
DROP PROCEDURE IF EXISTS `PRODUCTBRAND_DELETE_PROCEDURE`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `PRODUCTBRAND_DELETE_PROCEDURE`(IN v_brandId varchar(50))
lable:BEGIN
   	DELETE FROM productBrand WHERE brandId=v_brandId;
  
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for PRODUCTBRAND_INFO_PROCEDURE
-- ----------------------------
DROP PROCEDURE IF EXISTS `PRODUCTBRAND_INFO_PROCEDURE`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `PRODUCTBRAND_INFO_PROCEDURE`(IN v_brandId varchar(50))
lable:BEGIN
	SELECT * FROM productBrand WHERE brandId = v_brandId;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for PRODUCTBRAND_INSERT_PROCEDURE
-- ----------------------------
DROP PROCEDURE IF EXISTS `PRODUCTBRAND_INSERT_PROCEDURE`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `PRODUCTBRAND_INSERT_PROCEDURE`(IN v_brandId varchar(50),
     IN v_brandCode varchar(50), 
     IN v_brandName varchar(100),
     IN v_brandType varchar(32),
 IN  v_remark varchar(1024) , IN v_sort int(4),
     IN v_status varchar(16),
     IN v_createdTime timestamp,
     IN v_creatorId varchar(50),
     IN v_creatorName varchar(50),
     IN v_modifyTime timestamp,
     IN v_modifierId varchar(50),
     IN v_modifierName varchar(50),
     IN v_lastModifyTime timestamp,
     IN v_ownerCompanyCode varchar(50),
     IN v_tenantId varchar(50))
lable:BEGIN
    DECLARE tmpCont int default 0;
    DECLARE cont int default 0;
    
    INSERT INTO productBrand 
    (
     brandId, 
     brandCode,
     brandName,
     brandType,
     remark,
     sort,
     status,
     createdTime,
     creatorId,
     creatorName,
     modifyTime,
     modifierId,
     modifierName,
     lastModifyTime,
     ownerCompanyCode,
     tenantId
	) VALUES 
	(
	   v_brandId, 
     v_brandCode,
     v_brandName,
     v_brandType,
     v_remark,
     v_sort,
     v_status,
     v_createdTime,
     v_creatorId,
     v_creatorName,
     v_modifyTime,
     v_modifierId,
     v_modifierName,
     v_lastModifyTime,
     v_ownerCompanyCode,
     v_tenantId
	); 
	
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for PRODUCTBRAND_LISTALL_PROCEDURE
-- ----------------------------
DROP PROCEDURE IF EXISTS `PRODUCTBRAND_LISTALL_PROCEDURE`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `PRODUCTBRAND_LISTALL_PROCEDURE`(IN v_startrow int,
	IN v_pagesize int,
	IN v_brandCode varchar(50),
  IN v_brandType varchar(32),
	IN v_brandName varchar(100),
	IN v_status varchar(16),
	IN v_tenantId varchar(50))
lable:BEGIN
	IF 
	v_startrow=-1  THEN SELECT COUNT(1) FROM 
  productBrand where 
  (v_brandCode is null or brandCode like CONCAT('%',v_brandCode,'%') )
	and (v_brandName is null or brandName like CONCAT('%',v_brandName,'%'))
	and (v_status is null or status=v_status)
  and (v_brandType is null or brandType=v_brandType)
	and (v_tenantId is null or tenantId=v_tenantId);
	ELSE
	SELECT * FROM productBrand 
	where 
   (v_brandCode is null or brandCode like CONCAT('%',v_brandCode,'%') )
	and (v_brandName is null or brandName like CONCAT('%',v_brandName,'%'))
	and (v_status is null or status =v_status)
  and (v_brandType is null or brandType=v_brandType)
	and (v_tenantId is null or tenantId=v_tenantId)
	LIMIT v_startrow,v_pagesize;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for PRODUCTBRAND_UPDATE_PROCEDURE
-- ----------------------------
DROP PROCEDURE IF EXISTS `PRODUCTBRAND_UPDATE_PROCEDURE`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `PRODUCTBRAND_UPDATE_PROCEDURE`(IN v_brandId varchar(50),
  IN v_brandCode varchar(50),
	IN v_brandName varchar(100),
	IN v_brandType varchar(32),
  IN v_remark varchar(1024),
	IN v_sort int(4),
IN v_status varchar(16),IN v_modifierId VARCHAR(50),IN v_modifierName VARCHAR(50),IN v_modifyTime timestamp,IN v_lastModifyTime timestamp)
label:BEGIN
	UPDATE productBrand set 
  brandCode=v_brandCode,
	brandName=v_brandName,
  brandType=v_brandType,
	remark=v_remark,
	sort=v_sort,
  modifierId=v_modifierId,
  modifierName=v_modifierName,
  modifyTime=v_modifyTime,
  lastModifyTime=v_lastModifyTime,
	status=v_status
	where brandId =v_brandId;
END
;;
DELIMITER ;