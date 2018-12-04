/*
Navicat MySQL Data Transfer

Source Server         : mariadb
Source Server Version : 50505
Source Host           : 192.168.11.130:3306
Source Database       : productdb

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-12-04 14:23:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for productBrand
-- ----------------------------
DROP TABLE IF EXISTS `productBrand`;
CREATE TABLE `productBrand` (
  `brandId` varchar(50) NOT NULL,
  `brandCode` varchar(50) NOT NULL,
  `brandName` varchar(100) NOT NULL,
  `brandType` varchar(32) DEFAULT NULL,
  `remark` varchar(1024) DEFAULT NULL,
  `sort` int(4) NOT NULL,
  `status` varchar(16) NOT NULL,
  `createdTime` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `creatorId` varchar(50) NOT NULL DEFAULT '1',
  `creatorName` varchar(50) NOT NULL DEFAULT 'admin',
  `modifyTime` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `modifierId` varchar(50) DEFAULT NULL,
  `modifierName` varchar(50) DEFAULT NULL,
  `lastModifyTime` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `ownerCompanyCode` varchar(50) DEFAULT NULL,
  `tenantId` varchar(50) NOT NULL DEFAULT '1',
  PRIMARY KEY (`brandId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
