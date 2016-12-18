/*
Navicat MySQL Data Transfer

Source Server         : qingtian
Source Server Version : 50632
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50632
File Encoding         : 65001

Date: 2016-12-16 17:57:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `gender` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `age` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `role` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of User
-- ----------------------------
INSERT INTO `User` VALUES ('1', '张三', '男', '18', '主管');
INSERT INTO `User` VALUES ('2', '李四', '男', '25', '主管');
INSERT INTO `User` VALUES ('3', '王妈', '女', '22', '家庭主妇');
INSERT INTO `User` VALUES ('6', '123', '女', '123', '123');
INSERT INTO `User` VALUES ('7', '123', '男', '123', '123');
