-- ----------------------------
-- Procedure structure for QLOUDFLOW_PRODUCTTYPE_INSERT_PROCEDURE
-- ----------------------------
DROP PROCEDURE IF EXISTS `QLOUDFLOW_PRODUCTTYPE_INSERT_PROCEDURE`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `QLOUDFLOW_PRODUCTTYPE_INSERT_PROCEDURE`(
	 IN v_typeId varchar(50),
     IN v_typeCode varchar(50), 
     IN v_typeName varchar(100),
     IN v_parentId varchar(50),
     IN v_isPhysical tinyint(1),
     IN v_toTransport tinyint(1),
     IN v_prodLineId varchar(50),
     IN v_sort int(4),
     IN v_status varchar(16),
     IN v_remark varchar(50),
     IN v_createdTime timestamp,
     IN v_creatorId varchar(50),
     IN v_creatorName varchar(50),
     IN v_modifyTime timestamp,
     IN v_modifierId varchar(50),
     IN v_modifierName varchar(50),
     IN v_lastModifyTime timestamp,
     IN v_tenantId varchar(50)
)
lable:BEGIN
    DECLARE tmpCont int default 0;
    DECLARE cont int default 0;
    
    INSERT INTO productType 
    (
     typeId, 
     typeCode,
     typeName,
     parentId,
     isPhysical,
     toTransport,
     prodLineId,
     sort,
     status,
     remark,
     createdTime,
     creatorId,
     creatorName,
     modifyTime,
     modifierId,
     modifierName,
     lastModifyTime,
     tenantId
	) VALUES 
	(
	 v_typeId, 
     v_typeCode,
     v_typeName,
     v_parentId,
     v_isPhysical,
     v_toTransport,
     v_prodLineId,
     v_sort,
     v_status,
     v_remark,
     v_createdTime,
     v_creatorId,
     v_creatorName,
     v_modifyTime,
     v_modifierId,
     v_modifierName,
     v_lastModifyTime,
     v_tenantId
	); 
	
	

END
;;
DELIMITER ;




-- ----------------------------
-- Procedure structure for QLOUDFLOW_PRODUCTTYPE_UPDATE_PROCEDURE
-- ----------------------------
DROP PROCEDURE IF EXISTS `QLOUDFLOW_PRODUCTTYPE_UPDATE_PROCEDURE`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `QLOUDFLOW_PRODUCTTYPE_UPDATE_PROCEDURE`(
    IN v_typeId varchar(50),
	IN v_typeCode varchar(50),
	IN v_typeName varchar(100),
	IN v_isPhysical tinyint(1),
	IN v_toTransport tinyint(1),
	IN v_parentId varchar(50),
	IN v_sort int(4),
	IN v_status varchar(16),
	IN v_prodLineId varchar(50),
	IN v_tenantId varchar(50),
	IN v_modifierId varchar(50),
	IN v_modifierName varchar(50),
	IN v_modifyTime timestamp,
	IN v_lastModifyTime timestamp
)
label:BEGIN
	UPDATE productType set 
	typeCode=v_typeCode,
	typeName=v_typeName,
	isPhysical=v_isPhysical,
	toTransport=v_toTransport,
	parentId=v_parentId,
	sort=v_sort,
	status=v_status,
	prodLineId=v_prodLineId,
	tenantId=v_tenantId,
	modifierId = v_modifierId,
	modifierName = v_modifierName,
	modifyTime = v_modifyTime,
	lastModifyTime = v_lastModifyTime
	where typeId=v_typeId;
END
;;
DELIMITER ;




-- ----------------------------
-- Procedure structure for QLOUDFLOW_PRODUCTTYPE_DELETE_PROCEDURE
-- ----------------------------
DROP PROCEDURE IF EXISTS `QLOUDFLOW_PRODUCTTYPE_DELETE_PROCEDURE`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `QLOUDFLOW_PRODUCTTYPE_DELETE_PROCEDURE`(
	 IN v_typeId varchar(50)
)
lable:BEGIN
   	DELETE FROM productType WHERE typeId=v_typeId;
END
;;
DELIMITER ;




-- ----------------------------
-- Procedure structure for QLOUDFLOW_PRODUCTTYPE_INFO_PROCEDURE
-- ----------------------------
DROP PROCEDURE IF EXISTS `QLOUDFLOW_PRODUCTTYPE_INFO_PROCEDURE`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `QLOUDFLOW_PRODUCTTYPE_INFO_PROCEDURE`(
	IN v_typeId varchar(50)
)
lable:BEGIN
	SELECT * FROM productType WHERE typeId = v_typeId;
END
;;
DELIMITER ;





-- ----------------------------
-- Procedure structure for QLOUDFLOW_PRODUCTTYPE_TREE_PROCEDURE
-- ----------------------------
DROP PROCEDURE IF EXISTS `QLOUDFLOW_PRODUCTTYPE_TREE_PROCEDURE`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `QLOUDFLOW_PRODUCTTYPE_TREE_PROCEDURE`(
	IN v_typeName varchar(100)
)
lable:BEGIN
	SELECT * FROM productType
	WHERE (v_typeName is null or typeName like CONCAT('%',v_typeName,'%') );
END
;;
DELIMITER ;


