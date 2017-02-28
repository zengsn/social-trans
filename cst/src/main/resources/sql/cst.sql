/*
Navicat MySQL Data Transfer

Source Server         : qingtian
Source Server Version : 50632
Source Host           : localhost:3306
Source Database       : cst

Target Server Type    : MYSQL
Target Server Version : 50632
File Encoding         : 65001

Date: 2017-02-28 22:13:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for p_file
-- ----------------------------
DROP TABLE IF EXISTS `p_file`;
CREATE TABLE `p_file` (
  `fileId` char(36) CHARACTER SET utf8 NOT NULL COMMENT '附件id',
  `fileName` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '文件名',
  `fileType` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '文件类型',
  `fileSize` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '文件大小',
  `filePath` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '文件存储路径',
  `createTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `fileCode` char(36) CHARACTER SET utf8 NOT NULL COMMENT '附件关联标识号',
  `childFile` char(1) CHARACTER SET utf8 DEFAULT '0' COMMENT '是否为分割的文件：0为否，1为是',
  PRIMARY KEY (`fileId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of p_file
-- ----------------------------
INSERT INTO `p_file` VALUES ('1057A444-21B5-6EB5-1F02-F1A12D0D7052', '1057A444-21B5-6EB5-1F02-F1A12D0D7052', 'txt', null, 'E:\\TestCst\\2017\\02\\28\\1057A444-21B5-6EB5-1F02-F1A12D0D70522.txt', '2017-02-28 11:40:53', '5530557D-3D5A-3EF5-C538-C8C8480239B7', '1');
INSERT INTO `p_file` VALUES ('29C596BD-AB74-93C6-2628-DA0154F53347', '29C596BD-AB74-93C6-2628-DA0154F53347', 'txt', null, 'E:\\TestCst\\2017\\02\\28\\29C596BD-AB74-93C6-2628-DA0154F5334713.txt', '2017-02-28 11:40:53', '5530557D-3D5A-3EF5-C538-C8C8480239B7', '1');
INSERT INTO `p_file` VALUES ('2C789BD5-F819-2726-A8DC-DB8A4CCE1DD6', '2C789BD5-F819-2726-A8DC-DB8A4CCE1DD6', 'txt', null, 'E:\\TestCst\\2017\\02\\28\\2C789BD5-F819-2726-A8DC-DB8A4CCE1DD69.txt', '2017-02-28 11:40:53', '5530557D-3D5A-3EF5-C538-C8C8480239B7', '1');
INSERT INTO `p_file` VALUES ('35C45077-4D71-3499-96DE-6F622FBBF1F8', '35C45077-4D71-3499-96DE-6F622FBBF1F8', 'txt', null, 'E:\\TestCst\\2017\\02\\28\\35C45077-4D71-3499-96DE-6F622FBBF1F84.txt', '2017-02-28 11:40:53', '5530557D-3D5A-3EF5-C538-C8C8480239B7', '1');
INSERT INTO `p_file` VALUES ('6AA6D140-36FA-DCB4-AE3A-58BB7437B906', '6AA6D140-36FA-DCB4-AE3A-58BB7437B906', 'txt', null, 'E:\\TestCst\\2017\\02\\28\\6AA6D140-36FA-DCB4-AE3A-58BB7437B90610.txt', '2017-02-28 11:40:53', '5530557D-3D5A-3EF5-C538-C8C8480239B7', '1');
INSERT INTO `p_file` VALUES ('6E466F5D-82AC-5A1C-C140-1DF702991284', '6E466F5D-82AC-5A1C-C140-1DF702991284', 'txt', null, 'E:\\TestCst\\2017\\02\\28\\6E466F5D-82AC-5A1C-C140-1DF7029912848.txt', '2017-02-28 11:40:53', '5530557D-3D5A-3EF5-C538-C8C8480239B7', '1');
INSERT INTO `p_file` VALUES ('779EDE2F-37E4-DB14-3BDD-E145D2F3B14F', '779EDE2F-37E4-DB14-3BDD-E145D2F3B14F', 'txt', null, 'E:\\TestCst\\2017\\02\\28\\779EDE2F-37E4-DB14-3BDD-E145D2F3B14F3.txt', '2017-02-28 11:40:53', '5530557D-3D5A-3EF5-C538-C8C8480239B7', '1');
INSERT INTO `p_file` VALUES ('80E67847-D68C-9176-249B-E1495F9FD59F', '80E67847-D68C-9176-249B-E1495F9FD59F', 'txt', null, 'E:\\TestCst\\2017\\02\\28\\80E67847-D68C-9176-249B-E1495F9FD59F14.txt', '2017-02-28 11:40:53', '5530557D-3D5A-3EF5-C538-C8C8480239B7', '1');
INSERT INTO `p_file` VALUES ('849C232E-1AB6-5905-4CA0-2862D4B75B6F', 'test1.txt', 'txt', '1400089', 'E:\\TestCst\\2017\\02\\28\\\\5530557D-3D5A-3EF5-C538-C8C8480239B7', '2017-02-28 11:40:50', '5530557D-3D5A-3EF5-C538-C8C8480239B7', '0');
INSERT INTO `p_file` VALUES ('8BD25856-64B4-47DF-BF53-5AD9813074EB', '8BD25856-64B4-47DF-BF53-5AD9813074EB', 'txt', null, 'E:\\TestCst\\2017\\02\\28\\8BD25856-64B4-47DF-BF53-5AD9813074EB5.txt', '2017-02-28 11:40:53', '5530557D-3D5A-3EF5-C538-C8C8480239B7', '1');
INSERT INTO `p_file` VALUES ('90969F8B-148B-526A-C2D0-A41B388B4093', 'test1.txt', 'txt', '1400089', 'E:\\TestCst\\2017\\02\\28\\\\F937927C-783A-1F9A-7164-948D1F83BF28', '2017-02-28 11:36:15', 'F937927C-783A-1F9A-7164-948D1F83BF28', '0');
INSERT INTO `p_file` VALUES ('924D7769-0A56-2E7D-9965-B81BA6006CE1', 'test1.txt', 'txt', '1400089', 'E:\\TestCst\\2017\\02\\27\\\\6D3ABF0B-5463-0B46-1BB3-9D002EB4A064', '2017-02-27 18:13:30', '6D3ABF0B-5463-0B46-1BB3-9D002EB4A064', '0');
INSERT INTO `p_file` VALUES ('A31D6830-77AE-3101-4054-AEA8E317CD1F', 'A31D6830-77AE-3101-4054-AEA8E317CD1F', 'txt', null, 'E:\\TestCst\\2017\\02\\28\\A31D6830-77AE-3101-4054-AEA8E317CD1F0.txt', '2017-02-28 11:40:53', '5530557D-3D5A-3EF5-C538-C8C8480239B7', '1');
INSERT INTO `p_file` VALUES ('B830BE7F-4FAD-57FD-FA59-7D72A8D94BE3', 'B830BE7F-4FAD-57FD-FA59-7D72A8D94BE3', 'txt', null, 'E:\\TestCst\\2017\\02\\28\\B830BE7F-4FAD-57FD-FA59-7D72A8D94BE311.txt', '2017-02-28 11:40:53', '5530557D-3D5A-3EF5-C538-C8C8480239B7', '1');
INSERT INTO `p_file` VALUES ('B9E2A910-505A-42B8-BA8D-EBF2CB442750', 'B9E2A910-505A-42B8-BA8D-EBF2CB442750', 'txt', null, 'E:\\TestCst\\2017\\02\\28\\B9E2A910-505A-42B8-BA8D-EBF2CB4427507.txt', '2017-02-28 11:40:53', '5530557D-3D5A-3EF5-C538-C8C8480239B7', '1');
INSERT INTO `p_file` VALUES ('BDE7116E-9A91-7BF4-B821-F73344307477', 'BDE7116E-9A91-7BF4-B821-F73344307477', 'txt', null, 'E:\\TestCst\\2017\\02\\28\\BDE7116E-9A91-7BF4-B821-F733443074776.txt', '2017-02-28 11:40:53', '5530557D-3D5A-3EF5-C538-C8C8480239B7', '1');
INSERT INTO `p_file` VALUES ('C7CEA6C4-75B1-1382-31D5-514E73E34197', 'test1.txt', 'txt', '1400089', 'E:\\TestCst\\2017\\02\\27\\\\B1AE8A37-D1BA-3E3C-C7F6-E45190788A1C', '2017-02-27 18:12:05', 'B1AE8A37-D1BA-3E3C-C7F6-E45190788A1C', '0');
INSERT INTO `p_file` VALUES ('DD084AD3-038D-F456-AAE2-5314C0DCF773', 'DD084AD3-038D-F456-AAE2-5314C0DCF773', 'txt', null, 'E:\\TestCst\\2017\\02\\28\\DD084AD3-038D-F456-AAE2-5314C0DCF77312.txt', '2017-02-28 11:40:53', '5530557D-3D5A-3EF5-C538-C8C8480239B7', '1');
INSERT INTO `p_file` VALUES ('E7036470-B1D5-5D85-596D-D0DE98C89196', 'test1.txt', 'txt', '1400089', 'E:\\TestCst\\2017\\02\\28\\\\AA641F15-3AE7-F6A8-D4AF-856D6AAD4ED9', '2017-02-28 11:39:09', 'AA641F15-3AE7-F6A8-D4AF-856D6AAD4ED9', '0');
INSERT INTO `p_file` VALUES ('EEB546D1-6594-4452-CB8A-D7643C00DEEA', 'test1.txt', 'txt', '1400089', 'E:\\TestCst\\2017\\02\\27\\\\B98A28A2-5894-2CD0-818A-D1BD20E04CAD', '2017-02-27 18:09:48', 'B98A28A2-5894-2CD0-818A-D1BD20E04CAD', '0');
INSERT INTO `p_file` VALUES ('F9CD2163-158D-8BE6-502C-C5387CEC2AC1', 'F9CD2163-158D-8BE6-502C-C5387CEC2AC1', 'txt', null, 'E:\\TestCst\\2017\\02\\28\\F9CD2163-158D-8BE6-502C-C5387CEC2AC11.txt', '2017-02-28 11:40:53', '5530557D-3D5A-3EF5-C538-C8C8480239B7', '1');

-- ----------------------------
-- Table structure for p_menu
-- ----------------------------
DROP TABLE IF EXISTS `p_menu`;
CREATE TABLE `p_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(30) NOT NULL DEFAULT '' COMMENT '菜单显示的名称',
  `url` varchar(200) DEFAULT '' COMMENT '对应的url ',
  `iconCls` varchar(50) DEFAULT '' COMMENT '图标',
  `parentId` int(10) NOT NULL DEFAULT '0' COMMENT '父节点id',
  `level` int(10) NOT NULL DEFAULT '0',
  `sort` int(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `level` (`level`),
  KEY `sort` (`sort`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_menu
-- ----------------------------
INSERT INTO `p_menu` VALUES ('1', '用户管理', '', 'fa fa-users', '0', '0', '1');
INSERT INTO `p_menu` VALUES ('2', '用户管理', 'cst/user.html', '', '1', '1', '1');
INSERT INTO `p_menu` VALUES ('3', '角色管理', '', 'fa fa-cubes', '0', '0', '1');
INSERT INTO `p_menu` VALUES ('4', '角色管理', 'cst/role.html', '', '3', '1', '1');
INSERT INTO `p_menu` VALUES ('5', '任务管理', '', 'fa fa-cubes', '0', '0', '1');
INSERT INTO `p_menu` VALUES ('6', '任务管理', 'cst/subbmitTask.html', '', '5', '1', '1');
INSERT INTO `p_menu` VALUES ('7', '任务列表', 'cst/receiveTask.html', '', '5', '1', '1');
INSERT INTO `p_menu` VALUES ('8', '我的任务', 'cst/myTask.html', '', '5', '1', '1');

-- ----------------------------
-- Table structure for p_permission
-- ----------------------------
DROP TABLE IF EXISTS `p_permission`;
CREATE TABLE `p_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '业务无关主键',
  `pcode` varchar(30) NOT NULL COMMENT '权限码',
  `pname` varchar(50) NOT NULL COMMENT '权限名称',
  `description` varchar(1000) DEFAULT '' COMMENT '说明',
  `tag` varchar(30) DEFAULT '' COMMENT '聚合标识',
  `menuCode` varchar(30) DEFAULT NULL COMMENT 'P_menu对应的menuCode',
  `disabled` char(1) DEFAULT '1' COMMENT '''禁用，1启用 0禁用'',',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_permission
-- ----------------------------
INSERT INTO `p_permission` VALUES ('1', 'basePer001', '用户管理', '用户管理', 'menu', '1', '1');
INSERT INTO `p_permission` VALUES ('2', 'basePer001', '角色管理', '角色管理', 'menu', '2', '1');

-- ----------------------------
-- Table structure for p_role
-- ----------------------------
DROP TABLE IF EXISTS `p_role`;
CREATE TABLE `p_role` (
  `id` char(36) NOT NULL COMMENT '业务无关主键',
  `rolename` varchar(100) NOT NULL COMMENT '角色名称',
  `code` varchar(30) NOT NULL COMMENT '角色代码',
  `description` varchar(1000) DEFAULT '' COMMENT '说明',
  `disabled` char(1) NOT NULL DEFAULT '1' COMMENT '数据的有效性，''1''有效，''0''无效 用于逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_role
-- ----------------------------
INSERT INTO `p_role` VALUES ('0E0A9E1D-8E7D-3791-ABA1-6BABB0B3AF0F', '超级用户', '002', '超级用户', '1');
INSERT INTO `p_role` VALUES ('C7F018E5-E0F6-476D-C92A-A54A577303B9', '普通用户', '001', '普通用户', '1');

-- ----------------------------
-- Table structure for p_rolepermission
-- ----------------------------
DROP TABLE IF EXISTS `p_rolepermission`;
CREATE TABLE `p_rolepermission` (
  `id` char(36) NOT NULL COMMENT '业务无关主键',
  `roleCode` varchar(30) NOT NULL COMMENT '角色代码 P_role 的code',
  `perCode` varchar(30) NOT NULL COMMENT '权限代码 P_permission 的 code',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_rolepermission
-- ----------------------------
INSERT INTO `p_rolepermission` VALUES ('1', '001', 'basePer001');
INSERT INTO `p_rolepermission` VALUES ('2', '002', 'basePer001');
INSERT INTO `p_rolepermission` VALUES ('3', '002', 'basePer002');

-- ----------------------------
-- Table structure for p_task_receive
-- ----------------------------
DROP TABLE IF EXISTS `p_task_receive`;
CREATE TABLE `p_task_receive` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taskId` char(36) CHARACTER SET utf8 NOT NULL COMMENT '与P_task_subbmit表的taskId关联',
  `receiver` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '任务领取人',
  `receiverId` char(36) CHARACTER SET utf8 DEFAULT NULL COMMENT '与P_user表的userId关联',
  `isReceive` char(1) CHARACTER SET utf8 DEFAULT '0' COMMENT '是否有人领取任务,0为无人领取',
  `submitter` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '任务提交者',
  `submitterId` char(36) CHARACTER SET utf8 NOT NULL COMMENT '与P_user表的userId关联',
  `fileId` char(36) CHARACTER SET utf8 NOT NULL COMMENT '附件id',
  `fileCode` char(36) CHARACTER SET utf8 NOT NULL COMMENT '附件关联标识号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of p_task_receive
-- ----------------------------
INSERT INTO `p_task_receive` VALUES ('1', '9351E63B-7DC9-AA53-5B0F-0D6366BAE2CA', null, 'E452F2E3-D4B7-4498-25B1-72E9B925F752', '1', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', 'A31D6830-77AE-3101-4054-AEA8E317CD1F', '5530557D-3D5A-3EF5-C538-C8C8480239B7');
INSERT INTO `p_task_receive` VALUES ('2', '9351E63B-7DC9-AA53-5B0F-0D6366BAE2CA', null, 'E452F2E3-D4B7-4498-25B1-72E9B925F752', '1', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', 'F9CD2163-158D-8BE6-502C-C5387CEC2AC1', '5530557D-3D5A-3EF5-C538-C8C8480239B7');
INSERT INTO `p_task_receive` VALUES ('3', '9351E63B-7DC9-AA53-5B0F-0D6366BAE2CA', null, 'E452F2E3-D4B7-4498-25B1-72E9B925F752', '1', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', '1057A444-21B5-6EB5-1F02-F1A12D0D7052', '5530557D-3D5A-3EF5-C538-C8C8480239B7');
INSERT INTO `p_task_receive` VALUES ('4', '9351E63B-7DC9-AA53-5B0F-0D6366BAE2CA', null, null, '0', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', '779EDE2F-37E4-DB14-3BDD-E145D2F3B14F', '5530557D-3D5A-3EF5-C538-C8C8480239B7');
INSERT INTO `p_task_receive` VALUES ('5', '9351E63B-7DC9-AA53-5B0F-0D6366BAE2CA', null, null, '0', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', '35C45077-4D71-3499-96DE-6F622FBBF1F8', '5530557D-3D5A-3EF5-C538-C8C8480239B7');
INSERT INTO `p_task_receive` VALUES ('6', '9351E63B-7DC9-AA53-5B0F-0D6366BAE2CA', null, null, '0', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', '8BD25856-64B4-47DF-BF53-5AD9813074EB', '5530557D-3D5A-3EF5-C538-C8C8480239B7');
INSERT INTO `p_task_receive` VALUES ('7', '9351E63B-7DC9-AA53-5B0F-0D6366BAE2CA', null, null, '0', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', 'BDE7116E-9A91-7BF4-B821-F73344307477', '5530557D-3D5A-3EF5-C538-C8C8480239B7');
INSERT INTO `p_task_receive` VALUES ('8', '9351E63B-7DC9-AA53-5B0F-0D6366BAE2CA', null, null, '0', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', 'B9E2A910-505A-42B8-BA8D-EBF2CB442750', '5530557D-3D5A-3EF5-C538-C8C8480239B7');
INSERT INTO `p_task_receive` VALUES ('9', '9351E63B-7DC9-AA53-5B0F-0D6366BAE2CA', null, null, '0', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', '6E466F5D-82AC-5A1C-C140-1DF702991284', '5530557D-3D5A-3EF5-C538-C8C8480239B7');
INSERT INTO `p_task_receive` VALUES ('10', '9351E63B-7DC9-AA53-5B0F-0D6366BAE2CA', null, null, '0', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', '2C789BD5-F819-2726-A8DC-DB8A4CCE1DD6', '5530557D-3D5A-3EF5-C538-C8C8480239B7');
INSERT INTO `p_task_receive` VALUES ('11', '9351E63B-7DC9-AA53-5B0F-0D6366BAE2CA', null, null, '0', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', '6AA6D140-36FA-DCB4-AE3A-58BB7437B906', '5530557D-3D5A-3EF5-C538-C8C8480239B7');
INSERT INTO `p_task_receive` VALUES ('12', '9351E63B-7DC9-AA53-5B0F-0D6366BAE2CA', null, null, '0', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', 'B830BE7F-4FAD-57FD-FA59-7D72A8D94BE3', '5530557D-3D5A-3EF5-C538-C8C8480239B7');
INSERT INTO `p_task_receive` VALUES ('13', '9351E63B-7DC9-AA53-5B0F-0D6366BAE2CA', null, null, '0', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', 'DD084AD3-038D-F456-AAE2-5314C0DCF773', '5530557D-3D5A-3EF5-C538-C8C8480239B7');
INSERT INTO `p_task_receive` VALUES ('14', '9351E63B-7DC9-AA53-5B0F-0D6366BAE2CA', null, null, '0', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', '29C596BD-AB74-93C6-2628-DA0154F53347', '5530557D-3D5A-3EF5-C538-C8C8480239B7');
INSERT INTO `p_task_receive` VALUES ('15', '9351E63B-7DC9-AA53-5B0F-0D6366BAE2CA', null, null, '0', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', '80E67847-D68C-9176-249B-E1495F9FD59F', '5530557D-3D5A-3EF5-C538-C8C8480239B7');

-- ----------------------------
-- Table structure for p_task_subbmit
-- ----------------------------
DROP TABLE IF EXISTS `p_task_subbmit`;
CREATE TABLE `p_task_subbmit` (
  `taskId` char(36) CHARACTER SET utf8 NOT NULL COMMENT '任务id',
  `comment` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '任务内容',
  `submitter` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '任务提交者',
  `submitterId` char(36) CHARACTER SET utf8 NOT NULL COMMENT '与P_user表的userId关联',
  `fileCode` char(36) CHARACTER SET utf8 NOT NULL COMMENT '附件关联标识号',
  `finishedTime` datetime DEFAULT NULL COMMENT '任务完成时间',
  `isReceive` char(1) CHARACTER SET utf8 DEFAULT '0' COMMENT '是否有人领取任务,0为无人领取',
  `fileId` char(36) CHARACTER SET utf8 NOT NULL COMMENT '附件id',
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of p_task_subbmit
-- ----------------------------
INSERT INTO `p_task_subbmit` VALUES ('1', '1', '1', '1', '1', '2017-02-07 17:41:20', '0', '1');
INSERT INTO `p_task_subbmit` VALUES ('9351E63B-7DC9-AA53-5B0F-0D6366BAE2CA', '111', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', '5530557D-3D5A-3EF5-C538-C8C8480239B7', null, '0', '849C232E-1AB6-5905-4CA0-2862D4B75B6F');

-- ----------------------------
-- Table structure for p_user
-- ----------------------------
DROP TABLE IF EXISTS `p_user`;
CREATE TABLE `p_user` (
  `userId` char(36) NOT NULL,
  `account` varchar(20) NOT NULL COMMENT '用户帐号',
  `password` varchar(20) NOT NULL COMMENT '用户密码',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `email` varchar(20) DEFAULT NULL COMMENT '用户邮箱',
  `phoneNumber` varchar(20) DEFAULT NULL COMMENT '用户手机号',
  `role` varchar(100) DEFAULT '普通用户' COMMENT '角色',
  `disabled` char(1) DEFAULT '1' COMMENT '数据的有效性，''1''有效，''0''无效 用于逻辑删除',
  `createTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_user
-- ----------------------------
INSERT INTO `p_user` VALUES ('362AF613-F441-0222-88CB-34D1BA33E43E', 'qingtian@163.com', '1', '晴天1993', null, '13613033671', '超级用户', '1', null);
INSERT INTO `p_user` VALUES ('7974A5E1-ADD2-7C72-A2E8-9204DD872180', '温四天', '1', null, null, null, '普通用户', '1', null);
INSERT INTO `p_user` VALUES ('B3347B03-0646-5B00-38D6-CFDDA11970D5', '11', '11', '1', null, '1', '1', '1', '2017-02-10 23:34:39');
INSERT INTO `p_user` VALUES ('CBB91C91-14AB-9BEE-F78E-15527DFB2E16', '123', '123', null, null, null, '普通用户', '0', '2017-02-07 23:59:10');
INSERT INTO `p_user` VALUES ('CD1B3074-17B8-20AF-3FED-DCBFA565617A', '方旅生', '123456', null, null, null, '普通用户', '0', '2017-02-10 23:48:08');
INSERT INTO `p_user` VALUES ('DC40A21C-A2E6-28E7-C2F8-0FC2BA6E7710', '晴天', '1', '晴天', null, '111', '普通用户', '1', null);
INSERT INTO `p_user` VALUES ('E452F2E3-D4B7-4498-25B1-72E9B925F752', '1', '1', '陈杰', null, '110', '普通用户', '1', '2017-02-10 23:44:52');

-- ----------------------------
-- Table structure for p_userrole
-- ----------------------------
DROP TABLE IF EXISTS `p_userrole`;
CREATE TABLE `p_userrole` (
  `id` char(36) NOT NULL COMMENT '业务无关主键',
  `roleCode` varchar(30) NOT NULL COMMENT '角色代码 P_role 的code',
  `userId` char(36) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_userrole
-- ----------------------------
INSERT INTO `p_userrole` VALUES ('1FEA0D6A-EBC6-0366-7B6A-7952CCEF67B2', '002', 'B90E8BF9-105F-5191-2579-4A5CA391755F');
INSERT INTO `p_userrole` VALUES ('A052F118-A615-9C39-CBB4-5A649A573AA3', '001', '74CA646D-0988-A039-F2FB-24351AE8DF0D');
