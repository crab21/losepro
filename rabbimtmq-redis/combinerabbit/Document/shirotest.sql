/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : shirotest

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2018-11-10 23:35:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `mid` int(11) NOT NULL,
  `mname` varchar(255) NOT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('1', 'update');

-- ----------------------------
-- Table structure for module_role
-- ----------------------------
DROP TABLE IF EXISTS `module_role`;
CREATE TABLE `module_role` (
  `rid` int(11) NOT NULL,
  `mid` int(11) DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `mid_idx` (`mid`),
  CONSTRAINT `mid` FOREIGN KEY (`mid`) REFERENCES `module` (`mid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rid` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module_role
-- ----------------------------
INSERT INTO `module_role` VALUES ('1', '1');

-- ----------------------------
-- Table structure for rabb
-- ----------------------------
DROP TABLE IF EXISTS `rabb`;
CREATE TABLE `rabb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rabb
-- ----------------------------
INSERT INTO `rabb` VALUES ('1', 'name1');
INSERT INTO `rabb` VALUES ('2', 'name1');
INSERT INTO `rabb` VALUES ('3', 'name1');
INSERT INTO `rabb` VALUES ('4', 'name1');
INSERT INTO `rabb` VALUES ('5', 'name1');
INSERT INTO `rabb` VALUES ('6', 'name1');
INSERT INTO `rabb` VALUES ('7', 'name1');
INSERT INTO `rabb` VALUES ('8', 'name1');
INSERT INTO `rabb` VALUES ('9', 'name1');
INSERT INTO `rabb` VALUES ('10', 'name1');
INSERT INTO `rabb` VALUES ('11', 'name1');
INSERT INTO `rabb` VALUES ('12', 'name1');
INSERT INTO `rabb` VALUES ('13', 'name1');
INSERT INTO `rabb` VALUES ('14', 'name1');
INSERT INTO `rabb` VALUES ('15', 'name1');
INSERT INTO `rabb` VALUES ('16', 'name1');
INSERT INTO `rabb` VALUES ('17', 'name1');
INSERT INTO `rabb` VALUES ('18', 'name1');
INSERT INTO `rabb` VALUES ('19', 'name1');
INSERT INTO `rabb` VALUES ('20', 'name1');
INSERT INTO `rabb` VALUES ('21', 'name1');
INSERT INTO `rabb` VALUES ('22', 'name1');
INSERT INTO `rabb` VALUES ('23', 'name1');
INSERT INTO `rabb` VALUES ('24', 'name1');
INSERT INTO `rabb` VALUES ('25', 'name1');
INSERT INTO `rabb` VALUES ('26', 'name1');
INSERT INTO `rabb` VALUES ('27', 'name1');
INSERT INTO `rabb` VALUES ('28', 'name1');
INSERT INTO `rabb` VALUES ('29', 'name1');
INSERT INTO `rabb` VALUES ('30', 'name1');
INSERT INTO `rabb` VALUES ('31', 'name1');
INSERT INTO `rabb` VALUES ('32', 'name1');
INSERT INTO `rabb` VALUES ('33', 'name1');
INSERT INTO `rabb` VALUES ('34', 'name1');
INSERT INTO `rabb` VALUES ('35', 'name1');
INSERT INTO `rabb` VALUES ('36', 'name1');
INSERT INTO `rabb` VALUES ('37', 'name1');
INSERT INTO `rabb` VALUES ('38', 'name1');
INSERT INTO `rabb` VALUES ('39', 'name1');
INSERT INTO `rabb` VALUES ('40', 'name1');
INSERT INTO `rabb` VALUES ('41', 'name1');
INSERT INTO `rabb` VALUES ('42', 'name1');
INSERT INTO `rabb` VALUES ('43', 'name1');
INSERT INTO `rabb` VALUES ('44', 'name1');
INSERT INTO `rabb` VALUES ('45', 'name1');
INSERT INTO `rabb` VALUES ('46', 'name1');
INSERT INTO `rabb` VALUES ('47', 'name1');
INSERT INTO `rabb` VALUES ('48', 'name1');
INSERT INTO `rabb` VALUES ('49', 'name1');
INSERT INTO `rabb` VALUES ('50', 'name1');
INSERT INTO `rabb` VALUES ('51', 'name1');
INSERT INTO `rabb` VALUES ('52', 'name1');
INSERT INTO `rabb` VALUES ('53', 'name1');
INSERT INTO `rabb` VALUES ('54', 'name1');
INSERT INTO `rabb` VALUES ('55', 'name1');
INSERT INTO `rabb` VALUES ('56', 'name1');
INSERT INTO `rabb` VALUES ('57', 'name1');
INSERT INTO `rabb` VALUES ('58', 'name1');
INSERT INTO `rabb` VALUES ('59', 'name1');
INSERT INTO `rabb` VALUES ('60', 'name1');
INSERT INTO `rabb` VALUES ('61', 'name1');
INSERT INTO `rabb` VALUES ('62', 'name1');
INSERT INTO `rabb` VALUES ('63', 'name1');
INSERT INTO `rabb` VALUES ('64', 'name1');
INSERT INTO `rabb` VALUES ('65', 'name1');
INSERT INTO `rabb` VALUES ('66', 'name1');
INSERT INTO `rabb` VALUES ('67', 'name1');
INSERT INTO `rabb` VALUES ('68', 'name1');
INSERT INTO `rabb` VALUES ('69', 'name1');
INSERT INTO `rabb` VALUES ('70', 'name1');
INSERT INTO `rabb` VALUES ('71', 'name1');
INSERT INTO `rabb` VALUES ('72', 'name1');
INSERT INTO `rabb` VALUES ('73', 'name1');
INSERT INTO `rabb` VALUES ('74', 'name1');
INSERT INTO `rabb` VALUES ('75', 'name1');
INSERT INTO `rabb` VALUES ('76', 'name1');
INSERT INTO `rabb` VALUES ('77', 'name1');
INSERT INTO `rabb` VALUES ('78', 'name1');
INSERT INTO `rabb` VALUES ('79', 'name1');
INSERT INTO `rabb` VALUES ('80', 'name1');
INSERT INTO `rabb` VALUES ('81', 'name1');
INSERT INTO `rabb` VALUES ('82', 'name1');
INSERT INTO `rabb` VALUES ('83', 'name1');
INSERT INTO `rabb` VALUES ('84', 'name1');
INSERT INTO `rabb` VALUES ('85', 'name1');
INSERT INTO `rabb` VALUES ('86', 'name1');
INSERT INTO `rabb` VALUES ('87', 'name1');
INSERT INTO `rabb` VALUES ('88', 'name1');
INSERT INTO `rabb` VALUES ('89', 'name1');
INSERT INTO `rabb` VALUES ('90', 'name1');
INSERT INTO `rabb` VALUES ('91', 'name1');
INSERT INTO `rabb` VALUES ('92', 'name1');
INSERT INTO `rabb` VALUES ('93', 'name1');
INSERT INTO `rabb` VALUES ('94', 'name1');
INSERT INTO `rabb` VALUES ('95', 'name1');
INSERT INTO `rabb` VALUES ('96', 'name1');
INSERT INTO `rabb` VALUES ('97', 'name1');
INSERT INTO `rabb` VALUES ('98', 'name1');
INSERT INTO `rabb` VALUES ('99', 'name1');
INSERT INTO `rabb` VALUES ('100', 'name1');
INSERT INTO `rabb` VALUES ('101', 'name1');
INSERT INTO `rabb` VALUES ('102', 'name1');
INSERT INTO `rabb` VALUES ('103', 'name1');
INSERT INTO `rabb` VALUES ('104', 'name1');
INSERT INTO `rabb` VALUES ('105', 'name1');
INSERT INTO `rabb` VALUES ('106', 'name1');
INSERT INTO `rabb` VALUES ('107', 'name1');
INSERT INTO `rabb` VALUES ('108', 'name1');
INSERT INTO `rabb` VALUES ('109', 'name1');
INSERT INTO `rabb` VALUES ('110', 'name1');
INSERT INTO `rabb` VALUES ('111', 'name1');
INSERT INTO `rabb` VALUES ('112', 'name1');
INSERT INTO `rabb` VALUES ('113', 'name1');
INSERT INTO `rabb` VALUES ('114', 'name1');
INSERT INTO `rabb` VALUES ('115', 'name1');
INSERT INTO `rabb` VALUES ('116', 'name1');
INSERT INTO `rabb` VALUES ('117', 'name1');
INSERT INTO `rabb` VALUES ('118', 'name1');
INSERT INTO `rabb` VALUES ('119', 'name1');
INSERT INTO `rabb` VALUES ('120', 'name1');
INSERT INTO `rabb` VALUES ('121', 'name1');
INSERT INTO `rabb` VALUES ('122', 'name1');
INSERT INTO `rabb` VALUES ('123', 'name1');
INSERT INTO `rabb` VALUES ('124', 'name1');
INSERT INTO `rabb` VALUES ('125', 'name1');
INSERT INTO `rabb` VALUES ('126', 'name1');
INSERT INTO `rabb` VALUES ('127', 'name1');
INSERT INTO `rabb` VALUES ('128', 'name1');
INSERT INTO `rabb` VALUES ('129', 'name1');
INSERT INTO `rabb` VALUES ('130', 'name1');
INSERT INTO `rabb` VALUES ('131', 'name1');
INSERT INTO `rabb` VALUES ('132', 'name1');
INSERT INTO `rabb` VALUES ('133', 'name1');
INSERT INTO `rabb` VALUES ('134', 'name1');
INSERT INTO `rabb` VALUES ('135', 'name1');
INSERT INTO `rabb` VALUES ('136', 'name1');
INSERT INTO `rabb` VALUES ('137', 'name1');
INSERT INTO `rabb` VALUES ('138', 'name1');
INSERT INTO `rabb` VALUES ('139', 'name1');
INSERT INTO `rabb` VALUES ('140', 'name1');
INSERT INTO `rabb` VALUES ('141', 'name1');
INSERT INTO `rabb` VALUES ('142', 'name1');
INSERT INTO `rabb` VALUES ('143', 'name1');
INSERT INTO `rabb` VALUES ('144', 'name1');
INSERT INTO `rabb` VALUES ('145', 'name1');
INSERT INTO `rabb` VALUES ('146', 'name1');
INSERT INTO `rabb` VALUES ('147', 'name1');
INSERT INTO `rabb` VALUES ('148', 'name1');
INSERT INTO `rabb` VALUES ('149', 'name1');
INSERT INTO `rabb` VALUES ('150', 'name1');
INSERT INTO `rabb` VALUES ('151', 'name1');
INSERT INTO `rabb` VALUES ('152', 'name1');
INSERT INTO `rabb` VALUES ('153', 'name1');
INSERT INTO `rabb` VALUES ('154', 'name1');
INSERT INTO `rabb` VALUES ('155', 'name1');
INSERT INTO `rabb` VALUES ('156', 'name1');
INSERT INTO `rabb` VALUES ('157', 'name1');
INSERT INTO `rabb` VALUES ('158', 'name1');
INSERT INTO `rabb` VALUES ('159', 'name1');
INSERT INTO `rabb` VALUES ('160', 'name1');
INSERT INTO `rabb` VALUES ('161', 'name1');
INSERT INTO `rabb` VALUES ('162', 'name1');
INSERT INTO `rabb` VALUES ('163', 'name1');
INSERT INTO `rabb` VALUES ('164', 'name1');
INSERT INTO `rabb` VALUES ('165', 'name1');
INSERT INTO `rabb` VALUES ('166', 'name1');
INSERT INTO `rabb` VALUES ('167', 'name1');
INSERT INTO `rabb` VALUES ('168', 'name1');
INSERT INTO `rabb` VALUES ('169', 'name1');
INSERT INTO `rabb` VALUES ('170', 'name1');
INSERT INTO `rabb` VALUES ('171', 'name1');
INSERT INTO `rabb` VALUES ('172', 'name1');
INSERT INTO `rabb` VALUES ('173', 'name1');
INSERT INTO `rabb` VALUES ('174', 'name1');
INSERT INTO `rabb` VALUES ('175', 'name1');
INSERT INTO `rabb` VALUES ('176', 'name1');
INSERT INTO `rabb` VALUES ('177', 'name1');
INSERT INTO `rabb` VALUES ('178', 'name1');
INSERT INTO `rabb` VALUES ('179', 'name1');
INSERT INTO `rabb` VALUES ('180', 'name1');
INSERT INTO `rabb` VALUES ('181', 'name1');
INSERT INTO `rabb` VALUES ('182', 'name1');
INSERT INTO `rabb` VALUES ('183', 'name1');
INSERT INTO `rabb` VALUES ('184', 'name1');
INSERT INTO `rabb` VALUES ('185', 'name1');
INSERT INTO `rabb` VALUES ('186', 'name1');
INSERT INTO `rabb` VALUES ('187', 'name1');
INSERT INTO `rabb` VALUES ('188', 'name1');
INSERT INTO `rabb` VALUES ('189', 'name1');
INSERT INTO `rabb` VALUES ('190', 'name1');
INSERT INTO `rabb` VALUES ('191', 'name1');
INSERT INTO `rabb` VALUES ('192', 'name1');
INSERT INTO `rabb` VALUES ('193', 'name1');
INSERT INTO `rabb` VALUES ('194', 'name1');
INSERT INTO `rabb` VALUES ('195', 'name1');
INSERT INTO `rabb` VALUES ('196', 'name1');
INSERT INTO `rabb` VALUES ('197', 'name1');
INSERT INTO `rabb` VALUES ('198', 'name1');
INSERT INTO `rabb` VALUES ('199', 'name1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'guan');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123', '456');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `uid` int(11) NOT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `r_fk_idx` (`rid`),
  CONSTRAINT `r_fk` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `u_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
