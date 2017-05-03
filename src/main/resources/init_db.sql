/*
Navicat MySQL Data Transfer

Source Server         : 172.16.0.15
Source Server Version : 50624
Source Host           : 172.16.0.15:3306
Source Database       : dev_center

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-05-03 16:28:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for D_DOCUMENT
-- ----------------------------
DROP TABLE IF EXISTS `D_DOCUMENT`;
CREATE TABLE `D_DOCUMENT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(250) DEFAULT NULL COMMENT '标题',
  `CONTENT` text COMMENT '内容',
  `MENU_ID` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `IS_VALID` bit(1) DEFAULT NULL COMMENT '是否有效',
  `VERSION_ID` int(11) DEFAULT NULL COMMENT '版本ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for D_MENU
-- ----------------------------
DROP TABLE IF EXISTS `D_MENU`;
CREATE TABLE `D_MENU` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` bigint(20) DEFAULT NULL COMMENT '父菜单ID',
  `NAME` varchar(255) DEFAULT NULL COMMENT '菜单名',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `IS_VALID` bit(1) DEFAULT b'1' COMMENT '是否有效',
  `VERSION_ID` bigint(20) DEFAULT NULL COMMENT '版本ID',
  `LEVEL` int(11) DEFAULT NULL COMMENT '菜单等级',
  `IS_LEAF` bit(1) DEFAULT NULL COMMENT '是否叶节点',
  `ORIGIN_ID` bigint(20) DEFAULT NULL COMMENT '本ID（同ID）',
  `CONTENT` text COMMENT '内容',
  `URL` varchar(255) DEFAULT NULL COMMENT '链接地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=342 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for D_RESOURCE
-- ----------------------------
DROP TABLE IF EXISTS `D_RESOURCE`;
CREATE TABLE `D_RESOURCE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(250) DEFAULT NULL COMMENT '新资源名',
  `DESCRIPTION` varchar(500) DEFAULT NULL COMMENT '资源描述',
  `SOURCE_NAME` varchar(250) DEFAULT NULL COMMENT '源资源名',
  `EXTENSION_NAME` varchar(10) DEFAULT NULL COMMENT '扩展名',
  `SIZE` int(8) DEFAULT NULL COMMENT '资源大小',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `PATH` varchar(255) DEFAULT NULL COMMENT '资源存放路径',
  `VERSION_ID` bigint(20) DEFAULT NULL COMMENT '版本id',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for D_VERSION
-- ----------------------------
DROP TABLE IF EXISTS `D_VERSION`;
CREATE TABLE `D_VERSION` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION_CODE` varchar(30) DEFAULT NULL COMMENT '版本号',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '版本描述',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `URL` varchar(255) DEFAULT NULL COMMENT 'SDK下载地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
