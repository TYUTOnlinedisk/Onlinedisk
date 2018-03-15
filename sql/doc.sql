/*
Navicat MySQL Data Transfer

Source Server         : MYSQLKeShe
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : onlinedisk

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2016-06-30 22:22:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `doc`
-- ----------------------------
DROP TABLE IF EXISTS `doc`;
CREATE TABLE `doc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `owner` varchar(20) NOT NULL,
  `createtime` varchar(20) NOT NULL,
  `size` bigint(100) NOT NULL,
  `directory` varchar(256) NOT NULL,
  `pid` tinyint(11) NOT NULL,
  `isdir` tinyint(11) NOT NULL,
  `realdirectory` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of doc
-- ----------------------------
INSERT INTO `doc` VALUES ('1', '3', 'lllidan', '2016-06-30 17:27', '0', '3', '-1', '1', '3');
INSERT INTO `doc` VALUES ('2', 'DSC0 (2).jpg', 'lllidan', '2016-06-30 17:29', '992', 'DSC0 (2).jpg', '-1', '0', 'd:/onlienDisk/lllidan/lllidan20160630172951.jpg');
INSERT INTO `doc` VALUES ('3', 'DSC0 (2).jpg', '', '2016-06-30 18:06', '992', 'DSC0 (2).jpg', '-1', '0', 'd:/onlienDisk//20160630180634.jpg');
INSERT INTO `doc` VALUES ('4', '1', '', '2016-06-30 18:13', '0', '1', '-1', '1', '1');
