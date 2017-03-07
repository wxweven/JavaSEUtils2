/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : wxwevenmvc

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2016-06-15 20:04:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sec_permission
-- ----------------------------
DROP TABLE IF EXISTS `sec_permission`;
CREATE TABLE `sec_permission` (
  `permission_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sec_permission
-- ----------------------------
INSERT INTO `sec_permission` VALUES ('1', 'user:create', '2016-03-09 15:42:07', '2016-03-09 15:42:10');
INSERT INTO `sec_permission` VALUES ('2', 'user:view', '2016-03-09 15:43:35', '2016-03-09 15:43:39');

-- ----------------------------
-- Table structure for sec_role
-- ----------------------------
DROP TABLE IF EXISTS `sec_role`;
CREATE TABLE `sec_role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sec_role
-- ----------------------------
INSERT INTO `sec_role` VALUES ('1', 'admin', '2016-03-09 11:58:12', '2016-03-09 11:58:16');
INSERT INTO `sec_role` VALUES ('2', 'user', '2016-03-09 15:09:04', '2016-03-09 15:09:08');

-- ----------------------------
-- Table structure for sec_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sec_role_permission`;
CREATE TABLE `sec_role_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `permission_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `permission_id外键` (`permission_id`),
  KEY `role_id外键1` (`role_id`),
  CONSTRAINT `permission_id??????` FOREIGN KEY (`permission_id`) REFERENCES `sec_permission` (`permission_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_id??????1` FOREIGN KEY (`role_id`) REFERENCES `sec_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sec_role_permission
-- ----------------------------
INSERT INTO `sec_role_permission` VALUES ('1', '1', '1');
INSERT INTO `sec_role_permission` VALUES ('2', '2', '1');
INSERT INTO `sec_role_permission` VALUES ('3', '2', '2');

-- ----------------------------
-- Table structure for sec_user
-- ----------------------------
DROP TABLE IF EXISTS `sec_user`;
CREATE TABLE `sec_user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sec_user
-- ----------------------------
INSERT INTO `sec_user` VALUES ('1', 'jacky', '9661FD65249B026EBEA0F49927E82F0E', '2016-03-08 16:37:59', '2016-03-08 16:38:06');
INSERT INTO `sec_user` VALUES ('2', 'cheng', '89975C5E5D407916E8080D137C48DDD7', '2016-03-09 15:09:35', '2016-03-09 15:10:16');

-- ----------------------------
-- Table structure for sec_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sec_user_role`;
CREATE TABLE `sec_user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned DEFAULT NULL,
  `role_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id外键` (`user_id`),
  KEY `role_id外键` (`role_id`),
  CONSTRAINT `role_id??????` FOREIGN KEY (`role_id`) REFERENCES `sec_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id??????` FOREIGN KEY (`user_id`) REFERENCES `sec_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sec_user_role
-- ----------------------------
INSERT INTO `sec_user_role` VALUES ('1', '1', '1');
INSERT INTO `sec_user_role` VALUES ('2', '2', '2');

-- ----------------------------
-- Table structure for wxw_dept
-- ----------------------------
DROP TABLE IF EXISTS `wxw_dept`;
CREATE TABLE `wxw_dept` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wxw_dept
-- ----------------------------

-- ----------------------------
-- Table structure for wxw_menu
-- ----------------------------
DROP TABLE IF EXISTS `wxw_menu`;
CREATE TABLE `wxw_menu` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `level` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` longtext COLLATE utf8_unicode_ci,
  `orderNum` int(255) DEFAULT NULL,
  `parentId` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `isLeftMenuTree` bit(1) DEFAULT b'1',
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC98A0423949C56B2` (`parentId`) USING BTREE,
  CONSTRAINT `wxw_menu_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `wxw_menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of wxw_menu
-- ----------------------------
INSERT INTO `wxw_menu` VALUES ('00', '根目录', null, null, '0', '根目录', null, null, '', null);
INSERT INTO `wxw_menu` VALUES ('01', '系统管理', 'new-unlock', '#', '1', null, '1', '00', '', null);
INSERT INTO `wxw_menu` VALUES ('0101', '用户管理', 'new-sysmanage', 'user/manage', '2', null, '1', '01', '', null);
INSERT INTO `wxw_menu` VALUES ('0102', '菜单管理', 'new-structure', 'home/menuEdit', '2', null, '2', '01', '', null);
INSERT INTO `wxw_menu` VALUES ('02', '测试', 'new-show', '#', '1', null, '2', '00', '', null);
INSERT INTO `wxw_menu` VALUES ('0201', '测试一', 'new-user_m', null, '2', null, '1', '02', '', null);
INSERT INTO `wxw_menu` VALUES ('0202', '测试二', 'new-sale_user', null, '2', null, '2', '02', '', null);

-- ----------------------------
-- Table structure for wxw_user
-- ----------------------------
DROP TABLE IF EXISTS `wxw_user`;
CREATE TABLE `wxw_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wxw_user
-- ----------------------------
INSERT INTO `wxw_user` VALUES ('1', 'admin', 'afdbaa3ee63a8b4e97196dcfd24b03fc', '24');
INSERT INTO `wxw_user` VALUES ('3', '123123', 'b3ddbc502e307665f346cbd6e52cc10d', '3123');
INSERT INTO `wxw_user` VALUES ('4', '123', '202cb962ac59075b964b07152d234b70', '123');
