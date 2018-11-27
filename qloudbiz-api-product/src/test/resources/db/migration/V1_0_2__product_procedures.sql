-- insert a product
DROP PROCEDURE IF EXISTS `QLOUDFLOW_PRODUCT_INSERT_PROCEDURE`;
DELIMITER //
CREATE  PROCEDURE `QLOUDFLOW_PRODUCT_INSERT_PROCEDURE`(
	 IN v_productId varchar(255),
     IN v_code varchar(50), 
     IN v_name varchar(255)
)
lable:BEGIN
    DECLARE tmpCont int default 0;
    DECLARE cont int default 0;
    
    INSERT INTO product 
    (
     productId, 
     code,
     name
	) VALUES 
	(
	 v_productId, 
     v_code,
     v_name
	); 
	
	

END//
DELIMITER ;


-- query products
DROP PROCEDURE IF EXISTS QLOUDFLOW_PRODUCT_LISTALL_PROCEDURE;
DELIMITER //
CREATE PROCEDURE QLOUDFLOW_PRODUCT_LISTALL_PROCEDURE(
	IN v_startrow int,
	IN v_pagesize int
)
lable:BEGIN
	IF(v_startrow=-1)  THEN SELECT COUNT(1) FROM product;
	ELSE SELECT productId,code,name FROM product LIMIT v_startrow,v_pagesize;
	END IF;
END//
DELIMITER;


-- delete QLOUDFLOW_PRODUCT_DELETE_PROCEDURE
DROP PROCEDURE IF EXISTS QLOUDFLOW_PRODUCT_DELETE_PROCEDURE;
DELIMITER //
CREATE  PROCEDURE QLOUDFLOW_PRODUCT_DELETE_PROCEDURE(
	 IN v_productId varchar(255),
     OUT V_OUT int
)
lable:BEGIN
   	DELETE FROM product WHERE productId=v_productId;
   	
	SELECT  ROW_COUNT() into V_OUT;

END//
DELIMITER ;




-- delete QLOUDFLOW_PRODUCT_DELETE_PROCEDURE
DROP PROCEDURE IF EXISTS QLOUDFLOW_PRODUCT_UPDATE_PROCEDURE;
DELIMITER //
CREATE PROCEDURE  QLOUDFLOW_PRODUCT_UPDATE_PROCEDURE(
	IN v_productId varchar(64),
	IN v_code varchar(255),
    IN v_name varchar(255),
	OUT V_OUT int
)
label:BEGIN
	UPDATE product set code=v_code,name=v_name where productId=v_productId;

  SELECT  ROW_COUNT() into V_OUT;
END//

DELIMITER ;

