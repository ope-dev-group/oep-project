DROP TABLE IF EXISTS `productType`;
CREATE TABLE `productType` (
  `typeId` varchar(50) NOT NULL COMMENT '产品分类Id',
  `typeCode` varchar(50) NOT NULL COMMENT '产品分类编号',
  `typeName` varchar(100) NOT NULL COMMENT '产品分类中文名称',
  `parentId` varchar(50) DEFAULT NULL COMMENT '产品分类父节点id',
  `isPhysical` tinyint(1) DEFAULT '1' COMMENT '是否物理分类',
  `toTransport` tinyint(1) DEFAULT '1' COMMENT '是否需要运输',
  `prodLineId` varchar(50) DEFAULT NULL COMMENT '所属产品线Id',
  `sort` int(4) NOT NULL COMMENT '排序号',
  `status` varchar(16) NOT NULL COMMENT '状态',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `creatorId` varchar(50) NOT NULL COMMENT '创建人ID',
  `creatorName` varchar(50) NOT NULL COMMENT '创建人姓名',
  `modifyTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `modifierId` varchar(50) DEFAULT NULL COMMENT '更新人ID',
  `modifierName` varchar(50) DEFAULT NULL COMMENT '更新人姓名',
  `lastModifyTime` timestamp NULL DEFAULT NULL COMMENT '最后更新时间',
  `tenantId` varchar(50) NOT NULL COMMENT '租户ID',
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;