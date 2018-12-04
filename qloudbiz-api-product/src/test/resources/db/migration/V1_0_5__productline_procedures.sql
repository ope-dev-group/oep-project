-- ----------------------------
-- Procedure structure for QLOUDFLOW_PRODUCTLINE_DELETE_PROCEDURE
-- ----------------------------
DROP PROCEDURE IF EXISTS `QLOUDFLOW_PRODUCTLINE_DELETE_PROCEDURE`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `QLOUDFLOW_PRODUCTLINE_DELETE_PROCEDURE`(
	 IN v_lineId varchar(50)
)
lable:BEGIN
   	DELETE FROM productLine WHERE lineId=v_lineId;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for QLOUDFLOW_PRODUCTLINE_INFO_PROCEDURE
-- ----------------------------
DROP PROCEDURE IF EXISTS `QLOUDFLOW_PRODUCTLINE_INFO_PROCEDURE`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `QLOUDFLOW_PRODUCTLINE_INFO_PROCEDURE`(
	IN v_lineId varchar(50)
)
lable:BEGIN
	SELECT * FROM productLine WHERE lineId = v_lineId;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for QLOUDFLOW_PRODUCTLINE_INSERT_PROCEDURE
-- ----------------------------
DROP PROCEDURE IF EXISTS `QLOUDFLOW_PRODUCTLINE_INSERT_PROCEDURE`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `QLOUDFLOW_PRODUCTLINE_INSERT_PROCEDURE`(
	 IN v_lineId varchar(50),
     IN v_lineCode varchar(50), 
     IN v_lineName varchar(100),
     IN v_parentId varchar(50),
     IN v_sort int(4),
     IN v_status varchar(16),
     IN v_createdTime timestamp,
     IN v_creatorId varchar(50),
     IN v_creatorName varchar(50),
     IN v_modifyTime timestamp,
     IN v_modifierId varchar(50),
     IN v_modifierName varchar(50),
     IN v_lastModifyTime timestamp,
     IN v_ownerCompanyCode varchar(50),
     IN v_tenantId varchar(50)
)
lable:BEGIN
    DECLARE tmpCont int default 0;
    DECLARE cont int default 0;
    
    INSERT INTO productLine 
    (
     lineId, 
     lineCode,
     lineName,
     parentId,
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
	 v_lineId, 
     v_lineCode,
     v_lineName,
     v_parentId,
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
-- Procedure structure for QLOUDFLOW_PRODUCTLINE_LISTALL_PROCEDURE
-- ----------------------------
DROP PROCEDURE IF EXISTS `QLOUDFLOW_PRODUCTLINE_LISTALL_PROCEDURE`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `QLOUDFLOW_PRODUCTLINE_LISTALL_PROCEDURE`(
	IN v_startrow int,
	IN v_pagesize int,
	IN v_lineCode varchar(50),
	IN v_lineName varchar(100),
	IN v_status varchar(16),
	IN v_tenantId varchar(50)
)
lable:BEGIN
	IF 
	v_startrow=-1  THEN SELECT COUNT(1) 
	FROM productLine where 
	(v_lineCode is null or lineCode like CONCAT('%',v_lineCode,'%') )
	and (v_lineName is null or lineName like CONCAT('%',v_lineName,'%'))
	and (v_status is null or status = v_status)
	and (v_tenantId is null or tenantId = v_tenantId);
	ELSE
	SELECT * FROM productLine where 
	(v_lineCode is null or lineCode like CONCAT('%',v_lineCode,'%') )
	and (v_lineName is null or lineName like CONCAT('%',v_lineName,'%'))
	and (v_status is null or status = v_status)
	and (v_tenantId is null or tenantId = v_tenantId)
	LIMIT v_startrow,v_pagesize;
	END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for QLOUDFLOW_PRODUCTLINE_UPDATE_PROCEDURE
-- ----------------------------
DROP PROCEDURE IF EXISTS `QLOUDFLOW_PRODUCTLINE_UPDATE_PROCEDURE`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `QLOUDFLOW_PRODUCTLINE_UPDATE_PROCEDURE`(
  IN v_lineId varchar(50),
	IN v_lineName varchar(100),
	IN v_parentId varchar(50),
	IN v_sort int(4),
	IN v_status varchar(16),
	IN v_modifierId varchar(50),
	IN v_modifierName varchar(50),
	IN v_modifyTime timestamp,
	IN v_lastModifyTime timestamp
)
label:BEGIN
	UPDATE productLine set 
	lineName=v_lineName,
	parentId=v_parentId,
	sort=v_sort,
	status=v_status,
	modifierId = v_modifierId,
	modifierName = v_modifierName,
	modifyTime = v_modifyTime,
	lastModifyTime = v_lastModifyTime
	where lineId=v_lineId;
END
;;
DELIMITER ;
