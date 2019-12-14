/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50624
 Source Host           : localhost:3306
 Source Schema         : user

 Target Server Type    : MySQL
 Target Server Version : 50624
 File Encoding         : 65001

 Date: 29/11/2019 13:47:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_group_has_resources
-- ----------------------------
DROP TABLE IF EXISTS `tb_group_has_resources`;
CREATE TABLE `tb_group_has_resources`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime(0) DEFAULT NULL COMMENT '最近一次更新时间',
  `group_id` int(11) DEFAULT NULL COMMENT '分组id',
  `resources_id` int(11) DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员组拥有的资源' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_group_has_resources
-- ----------------------------
INSERT INTO `tb_group_has_resources` VALUES (14, '2019-11-29 12:06:43', NULL, 1, 14);
INSERT INTO `tb_group_has_resources` VALUES (15, '2019-11-29 12:06:46', NULL, 2, 15);
INSERT INTO `tb_group_has_resources` VALUES (16, NULL, NULL, 2, 16);

-- ----------------------------
-- Table structure for tb_mem_base
-- ----------------------------
DROP TABLE IF EXISTS `tb_mem_base`;
CREATE TABLE `tb_mem_base`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime(0) DEFAULT NULL COMMENT '最近一次更新时间',
  `deltag` int(2) DEFAULT NULL COMMENT '删除位',
  `state` int(2) DEFAULT NULL COMMENT '状态',
  `sort` decimal(18, 2) DEFAULT NULL COMMENT '排序',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '会员登录名',
  `group_id` int(11) DEFAULT NULL COMMENT '分组id',
  `type` tinyint(2) DEFAULT 0 COMMENT '0个人用户1企业用户',
  `nick_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'email',
  `reg_from` int(16) DEFAULT NULL COMMENT '来源',
  `qq` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'qq号',
  `description` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `head_portrait` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户头像',
  `salt` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'salt',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `token_key` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'tokenkey',
  `question` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '问题',
  `answer` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '问题答案',
  `open_creditfile` tinyint(1) DEFAULT 0 COMMENT '是否已开通信用信息：0未开通1已开通',
  `last_login_date` datetime(0) DEFAULT NULL COMMENT '最后一次登陆时间',
  `recommend_member_id` int(11) DEFAULT NULL COMMENT '推荐人id',
  `recommend_code` varchar(127) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '我的推荐码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8902 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员主表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_mem_base
-- ----------------------------
INSERT INTO `tb_mem_base` VALUES (8899, '2018-12-24 18:10:12', '2018-12-24 18:10:12', 0, 1, 0.00, '13724256429', NULL, 0, NULL, NULL, 1, NULL, NULL, NULL, '9a7b9b8dc681bfb95cf3175c4943ec19', '08411568b421db6a2fd7a90a63ff0ffa', '7b82fcdbe4cd7a22a45267d6f69582ba', NULL, NULL, 0, '2018-12-24 18:10:12', NULL, '910b7fc3c1af3eb999415c47a8455bc9');
INSERT INTO `tb_mem_base` VALUES (8900, '2018-12-24 18:12:28', '2019-11-24 11:35:23', 0, 1, 333.00, '13724256427', 1, 0, '', '', 1, '', '', 'http://localhost:9090/contents/images/con_logo.png', 'df0cd15c-0e6b-11ea-a677-e7efd46c7636', '309c23f8d851448a8369b991f7ea00e8', 'df0cd15d-0e6b-11ea-a677-e7efd46c7636', '', '', 0, '2019-11-29 05:10:09', NULL, '8c21b1cb89dc7299168760ec3336c06e');
INSERT INTO `tb_mem_base` VALUES (8901, '2019-06-20 15:23:12', '2019-11-25 13:17:19', 0, 1, 222.00, '13658745145', 2, 0, '', '', 1, '', '', '', 'e87d63b4-0f85-11ea-a43b-ad641098bc23', '5e28d07ac84d433c963f4844e8d9efe7', 'e87db1d5-0f85-11ea-a43b-ad641098bc23', '', '', 0, '2019-11-29 05:08:29', NULL, 'bafc41fbeeda713b6b24c33d110b42ba');

-- ----------------------------
-- Table structure for tb_mem_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_mem_group`;
CREATE TABLE `tb_mem_group`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime(0) DEFAULT NULL COMMENT '最近一次更新时间',
  `deltag` int(2) DEFAULT NULL COMMENT '删除位',
  `state` int(2) DEFAULT NULL COMMENT '使用状态',
  `sort` decimal(18, 2) DEFAULT NULL COMMENT '排序',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分组名称',
  `description` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `is_default` int(2) DEFAULT NULL COMMENT '默认状态',
  `percent` decimal(18, 2) DEFAULT NULL COMMENT '享受价格百分比',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员分组' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_mem_group
-- ----------------------------
INSERT INTO `tb_mem_group` VALUES (1, '2018-01-03 15:37:52', '2018-05-17 18:06:25', 0, 1, 1.00, '尊贵会员', '呵呵', 0, 20.00);
INSERT INTO `tb_mem_group` VALUES (2, '2018-01-23 10:10:42', '2018-11-03 17:15:54', 0, 1, 2.00, '黄金会员', '啊啊', 0, 100.00);
INSERT INTO `tb_mem_group` VALUES (3, '2018-01-26 13:39:40', '2018-08-23 15:56:30', 0, 1, 3.00, '钻石会员', '1', 0, 81.00);
INSERT INTO `tb_mem_group` VALUES (4, '2018-02-02 22:01:04', '2018-11-03 17:15:54', 0, 1, 4.00, '八百炮兵', '', 0, 95.00);
INSERT INTO `tb_mem_group` VALUES (5, '2018-02-04 10:59:15', '2018-08-01 16:04:03', 1, 0, 5.00, '八百标兵', '', 0, 85.34);
INSERT INTO `tb_mem_group` VALUES (6, '2018-04-25 15:28:45', '2018-11-03 17:15:54', 0, 1, 6.00, '新手入门', '', 0, 100.00);
INSERT INTO `tb_mem_group` VALUES (7, '2018-04-25 15:29:08', '2018-11-03 17:15:54', 0, 1, 7.00, '青铜三', '', 0, 100.00);
INSERT INTO `tb_mem_group` VALUES (8, '2018-04-25 15:29:19', '2018-11-03 17:15:54', 0, 1, 8.00, '白银1', '', 0, 90.00);
INSERT INTO `tb_mem_group` VALUES (9, '2018-04-25 15:29:29', '2018-11-03 17:15:54', 0, 1, 9.00, '黄金2', '', 0, 100.00);
INSERT INTO `tb_mem_group` VALUES (10, '2018-04-25 15:29:45', '2018-11-03 17:15:54', 0, 1, 10.00, '铂金1', '', 0, 99.50);
INSERT INTO `tb_mem_group` VALUES (11, '2018-04-25 15:30:01', '2019-08-15 11:59:09', 0, 1, 332.00, '星耀3', '111', 0, 86.52);
INSERT INTO `tb_mem_group` VALUES (12, '2019-08-15 11:53:17', NULL, 0, NULL, NULL, '王者', '', 1, 80.00);
INSERT INTO `tb_mem_group` VALUES (13, '2019-08-15 11:59:23', '2019-08-16 11:39:55', 0, 1, 333.00, '责任', '229', 0, 33.00);

-- ----------------------------
-- Table structure for tb_mem_resources
-- ----------------------------
DROP TABLE IF EXISTS `tb_mem_resources`;
CREATE TABLE `tb_mem_resources`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime(0) DEFAULT NULL COMMENT '最近一次更新时间',
  `deltag` int(2) DEFAULT NULL COMMENT '删除位',
  `state` int(2) DEFAULT NULL COMMENT '使用状态',
  `sort` decimal(18, 2) DEFAULT NULL COMMENT '排序',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源名称',
  `url_pattern` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员资源' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_mem_resources
-- ----------------------------
INSERT INTO `tb_mem_resources` VALUES (14, '2019-11-29 12:05:30', NULL, 0, 1, 0.00, '订单模块', '/order/**');
INSERT INTO `tb_mem_resources` VALUES (15, '2019-11-29 12:05:35', NULL, 0, 1, 1.00, '会员模块', '/mem/**');
INSERT INTO `tb_mem_resources` VALUES (16, '2019-11-29 12:55:26', NULL, 0, 1, 0.00, '积分模块', '/score/query');
INSERT INTO `tb_mem_resources` VALUES (17, '2019-11-29 12:55:26', NULL, 0, 1, 0.00, '商城模块', '/product/q/**');

SET FOREIGN_KEY_CHECKS = 1;
