/*
Navicat MySQL Data Transfer

Source Server         : win
Source Server Version : 50733
Source Host           : 123.56.168.54:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50733
File Encoding         : 65001

Date: 2021-04-23 21:08:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `good_name` varchar(255) NOT NULL,
  `price` varchar(1000) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `sort` int(11) NOT NULL DEFAULT '1',
  `category` int(11) NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '0-未删除 1-删除',
  PRIMARY KEY (`gid`),
  KEY `category` (`category`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`category`) REFERENCES `good_category` (`gcid`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for good_category
-- ----------------------------
DROP TABLE IF EXISTS `good_category`;
CREATE TABLE `good_category` (
  `gcid` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  PRIMARY KEY (`gcid`),
  KEY `category_name` (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `oid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `gid` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `order_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `is_delete` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`oid`),
  KEY `uid` (`uid`),
  KEY `gid` (`gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `regist_time` varchar(20) NOT NULL DEFAULT '',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '0-未删除 1-删除',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
