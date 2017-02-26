/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50632
Source Host           : localhost:3306
Source Database       : cst

Target Server Type    : MYSQL
Target Server Version : 50632
File Encoding         : 65001

Date: 2017-02-27 00:36:32
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
INSERT INTO `p_file` VALUES ('0AF222F4-335A-EE9A-C006-60F9DFC4B015', '20140104235918_uNikH.jpeg', 'jpeg', '745987', 'E:\\UpAndDown\\2017\\02\\18\\\\20140104235918_uNikH.jpeg', null, '', null);
INSERT INTO `p_file` VALUES ('0CBD4903-BF92-62FE-B8CF-E1275DD50522', '222.txt', 'txt', '2464', 'E:\\UpAndDown\\2017\\02\\18\\\\0CBD4903-BF92-62FE-B8CF-E1275DD50522', '2017-02-18 17:24:44', '', null);
INSERT INTO `p_file` VALUES ('1007A284-A1A8-9DA0-3B41-CF2516F53BB6', '222.txt', 'txt', '2464', 'E:\\UpAndDown\\2017\\02\\18\\\\222.txt', null, '', null);
INSERT INTO `p_file` VALUES ('10617610-E9F0-C439-4357-614A59084582', '222.txt', 'txt', '2464', 'E:\\UpAndDown\\2017\\02\\18\\\\222.txt', null, '', null);
INSERT INTO `p_file` VALUES ('1A827BDF-D007-0E6D-9D18-26429224CCB1', '222.txt', 'txt', '2464', 'E:\\UpAndDown\\2017\\02\\18\\\\222.txt', null, '', null);
INSERT INTO `p_file` VALUES ('2A1D8B4E-1C2B-6254-EA97-E6A0298FC247', '222.txt', 'txt', '2464', 'E:\\UpAndDown\\2017\\02\\18\\\\222.txt', null, '', null);
INSERT INTO `p_file` VALUES ('3BDD9432-38B3-3A5E-6B0E-890CDDE0DC66', '222.txt', 'txt', '2464', 'E:\\UpAndDown\\2017\\02\\18\\\\222.txt', null, '', null);
INSERT INTO `p_file` VALUES ('58C869D1-42E6-694E-0873-5A4E4803B63E', '222.txt', 'txt', '2464', 'E:\\UpAndDown\\2017\\02\\18\\\\58C869D1-42E6-694E-0873-5A4E4803B63E', null, '', null);
INSERT INTO `p_file` VALUES ('C5282D29-AE26-F45E-E2BB-7CEC6677CF01', '20140104235918_uNikH.jpeg', 'jpeg', '745987', 'E:\\UpAndDown\\2017\\02\\18\\\\20140104235918_uNikH.jpeg', null, '', null);
INSERT INTO `p_file` VALUES ('F3B572B7-9AE9-6F34-0054-E7BFBA37DBA5', '222.txt', 'txt', '2464', 'E:\\UpAndDown\\2017\\02\\18\\\\222.txt', null, '', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_menu
-- ----------------------------
INSERT INTO `p_menu` VALUES ('1', '用户管理', '', 'fa fa-users', '0', '0', '1');
INSERT INTO `p_menu` VALUES ('2', '用户管理', 'cst/user.html', '', '1', '1', '1');
INSERT INTO `p_menu` VALUES ('3', '角色管理', '', 'fa fa-cubes', '0', '0', '1');
INSERT INTO `p_menu` VALUES ('4', '角色管理', 'cst/role.html', '', '3', '1', '1');
INSERT INTO `p_menu` VALUES ('5', '任务管理', '', 'fa fa-cubes', '0', '0', '1');
INSERT INTO `p_menu` VALUES ('6', '任务管理', 'cst/task.html', '', '5', '1', '1');
INSERT INTO `p_menu` VALUES ('7', '任务列表', 'cst/task.html', '', '5', '1', '1');

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
  `id` int(11) NOT NULL,
  `taskId` char(36) NOT NULL COMMENT '与P_task_subbmit表的taskId关联',
  `receiver` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '任务领取人',
  `receiverId` char(36) CHARACTER SET utf8 NOT NULL COMMENT '与P_user表的userId关联',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of p_task_receive
-- ----------------------------

-- ----------------------------
-- Table structure for p_task_subbmit
-- ----------------------------
DROP TABLE IF EXISTS `p_task_subbmit`;
CREATE TABLE `p_task_subbmit` (
  `taskId` char(36) NOT NULL COMMENT '任务id',
  `comment` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '任务内容',
  `submitter` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '任务提交者',
  `submitterId` char(36) CHARACTER SET utf8 NOT NULL COMMENT '与P_user表的userId关联',
  `fileCode` char(36) CHARACTER SET utf8 NOT NULL COMMENT '附件关联标识号',
  `finishedTime` datetime DEFAULT NULL COMMENT '任务完成时间',
  `isReceive` char(1) CHARACTER SET utf8 DEFAULT '0' COMMENT '是否有人领取任务,0为无人领取',
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of p_task_subbmit
-- ----------------------------
INSERT INTO `p_task_subbmit` VALUES ('1', '任务1', '小明', '4BD85B34-1D05-4931-AEB9-6B38415220D2', '4BD85B34-1D05-4931-AEB9-6B38415220D2', null, '0');
INSERT INTO `p_task_subbmit` VALUES ('524BA84F-6F1F-C81B-BC8F-3D4E8C90E73B', '222', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', '0CBD4903-BF92-62FE-B8CF-E1275DD50522', null, '0');
INSERT INTO `p_task_subbmit` VALUES ('F95DCDA5-8299-EA3B-D209-CDA8EA24469B', '1111', '陈杰', 'E452F2E3-D4B7-4498-25B1-72E9B925F752', 'C5282D29-AE26-F45E-E2BB-7CEC6677CF01', null, '0');

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
