/*
Navicat MySQL Data Transfer

Source Server         : mariadb
Source Server Version : 50505
Source Host           : 192.168.11.130:3306
Source Database       : productdb

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-12-04 16:05:02
*/



-- ----------------------------
-- Table structure for productLine
-- ----------------------------
DROP TABLE IF EXISTS `productLine`;
CREATE TABLE `productLine` (
  `lineId` varchar(50) NOT NULL,
  `lineCode` varchar(50) NOT NULL,
  `lineName` varchar(100) NOT NULL,
  `parentId` varchar(50) DEFAULT NULL,
  `sort` int(4) NOT NULL,
  `status` varchar(16) NOT NULL,
  `createdTime` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `creatorId` varchar(50) NOT NULL,
  `creatorName` varchar(50) NOT NULL,
  `modifyTime` timestamp NULL DEFAULT NULL,
  `modifierId` varchar(50) DEFAULT NULL,
  `modifierName` varchar(50) DEFAULT NULL,
  `lastModifyTime` timestamp NULL DEFAULT NULL,
  `ownerCompanyCode` varchar(50) DEFAULT NULL,
  `tenantId` varchar(50) NOT NULL DEFAULT '1',
  PRIMARY KEY (`lineId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
