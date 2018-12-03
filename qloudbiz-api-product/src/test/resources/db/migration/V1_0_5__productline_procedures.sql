-- insert a productLine
DROP PROCEDURE IF EXISTS `QLOUDFLOW_PRODUCTLINE_INSERT_PROCEDURE`;
DELIMITER //
CREATE  PROCEDURE `QLOUDFLOW_PRODUCTLINE_INSERT_PROCEDURE`(
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
	
END//
DELIMITER ;




-- query productLines
DROP PROCEDURE IF EXISTS QLOUDFLOW_PRODUCTLINE_LISTALL_PROCEDURE;
DELIMITER //
CREATE PROCEDURE QLOUDFLOW_PRODUCTLINE_LISTALL_PROCEDURE(
	IN v_currentNum int,
	IN v_pagePerNum int,
	IN v_lineCode varchar(50),
	IN v_lineName varchar(100),
	IN v_status varchar(16),
	IN v_tenantId varchar(50)
)
lable:BEGIN
	IF 
	v_pagePerNum=-1  THEN SELECT COUNT(1) FROM productLine where lineCode is null or lineCode like CONCAT('%',v_lineCode,'%') 
	and lineName is null or lineName like CONCAT('%',v_lineName,'%')
	and status is null or status like CONCAT('%',v_status, '%')
	and tenantId is null or tenantId like CONCAT('%',v_tenantId,'%');
	ELSE
	SELECT * FROM productLine 
	where lineCode like CONCAT('%',v_lineCode,'%') 
	and lineName like CONCAT('%',v_lineName,'%')
	and status like CONCAT('%',v_status,'%')
	and tenantId like CONCAT('%',v_tenantId,'%')
	LIMIT v_currentNum,v_pagePerNum;
	END IF;
END//
DELIMITER;





-- query info productLines
DROP PROCEDURE IF EXISTS QLOUDFLOW_PRODUCTLINE_INFO_PROCEDURE;
DELIMITER //
CREATE PROCEDURE QLOUDFLOW_PRODUCTLINE_INFO_PROCEDURE(
	IN v_lineId varchar(50)
)
lable:BEGIN
	SELECT * FROM productLine WHERE lineId = v_lineId;
END//
DELIMITER;




-- delete productLines
DROP PROCEDURE IF EXISTS QLOUDFLOW_PRODUCTLINE_DELETE_PROCEDURE;
DELIMITER //
CREATE  PROCEDURE QLOUDFLOW_PRODUCTLINE_DELETE_PROCEDURE(
	 IN v_lineId varchar(50)
)
lable:BEGIN
   	DELETE FROM productLine WHERE lineId=v_lineId;
   	
	SELECT  ROW_COUNT();

END//
DELIMITER ;





--update productLine
DROP PROCEDURE IF EXISTS QLOUDFLOW_PRODUCTLINE_UPDATE_PROCEDURE;
DELIMITER //
CREATE PROCEDURE  QLOUDFLOW_PRODUCTLINE_UPDATE_PROCEDURE(
    IN v_lineId varchar(50),
	IN v_lineName varchar(100),
	IN v_parentId varchar(50),
	IN v_sort int(4),
	IN v_status varchar(16)
)
label:BEGIN
	UPDATE productLine set 
	lineName=v_lineName,
	parentId=v_parentId,
	sort=v_sort,
	status=v_status
	where lineId=v_lineId;

  SELECT  ROW_COUNT();
END//

DELIMITER ;



