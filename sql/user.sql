/*
Navicat MySQL Data Transfer

Source Server         : MYSQLKeShe
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : onlinedisk

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2016-06-30 22:22:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  `password` varchar(16) NOT NULL,
  `email` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'lllidan', '123456', '773885456@qq.com');
INSERT INTO `user` VALUES ('2', 'xiaoming', '111', '123@qq.com');
INSERT INTO `user` VALUES ('3', 'xiaohong', '111', '456@qq.com');
INSERT INTO `user` VALUES ('4', '', '', '');
