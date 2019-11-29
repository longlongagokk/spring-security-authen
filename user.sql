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
INSERT INTO `tb_mem_base` VALUES (1, '2018-05-24 14:17:27', '2018-01-28 21:31:59', 0, 1, 1.00, 'wasfew', 0, 0, 'aaa', 'asfdf@qq.ac ', 0, '22123', '呵呵', 'https://gss0.bdstatic.com/5bVWsj_p_tVS5dKfpU_Y_D3/res/r/image/2018-01-03/e94facb7bd41729782c0c2148c5fa621.gif', '5441380bc94fe0f839ea40bc8be73909', '26dddc3e962f31a6fc108d038bcfc551', '9cf87c9af9ae0d09aae18b4849c4e188', '明天你是否还想起', '我的昨日', 0, NULL, NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (2, '2018-05-24 14:17:27', '2018-02-02 09:27:13', 0, 1, 2.00, 'sdfff', 1, 0, '', '', NULL, '', '', '', '73532875f291015fc3bf19d83e93ba9b', 'a49f1710d2f00ee043b20f8a03e3122f', '5edb67d2b9e2634ec3de38ca11e223f4', '', '', 0, NULL, NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (3, '2018-05-24 14:17:27', '2018-03-01 10:43:02', 0, 1, 3.00, 'laoleis', 2, 0, '', '', NULL, '', '', '', '54d936a870e2aded09ec578b0ccb212c', '4b35b547c5cfc39a162d3710c0852954', NULL, '', '', 0, NULL, NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (4, '2018-05-24 14:17:27', '2018-03-01 10:43:02', 0, 1, 4.00, 'laoyanga', 0, 0, '', '', NULL, '', '', '', '293470747a4e0c8113fef2c312d91aeb', '04c6b4207b0e37e12187716261ec6eff', NULL, '', '', 0, NULL, NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (5, '2018-05-24 14:17:27', '2018-03-20 01:00:05', 0, 1, 5.00, 'searl445', 0, 0, '', '', NULL, '', '', '', 'd0b21f111f5e114a5fea184b88929d11', '7215231adbd0daf959ee226329049d91', NULL, '', '', 0, NULL, NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (6, '2018-05-24 14:17:27', '2018-08-01 16:04:39', 0, 1, 6.00, 'yeracwangshang', 0, 0, '', '', NULL, '', '', '', '80c8829fa0a46f2682d8dda64ebed17a', 'd7626da33ff686ec336ed638245bbba0', NULL, '', '', 1, NULL, NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (7, '2018-05-24 14:17:27', '2018-05-23 14:47:15', 0, 1, 7.00, '404045664@qq.com', 2, 0, '', '404045664@qq.com', NULL, '', '', '', 'f54596341262c02c279ae79640c24169', 'c3cc0a14801644dbcd2cfd386e72f68c', 'fe8e3deca7a1529e6d258c83cc201940', '', '', 0, '2018-05-14 10:54:33', NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (8, '2018-05-24 14:17:27', '2018-05-23 14:47:15', 0, 1, 8.00, '13434343322', 0, 0, '', '', NULL, '', '', '', '28425cb2c9584ca4d2fb327930092861', 'bdd16203396a3cdbcd00148f8594aaed', '90113f4a21cd2c7d3c8b05297ac966e0', '', '', 0, '2018-03-18 17:04:58', NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (9, '2018-05-24 14:17:27', '2018-12-24 18:06:07', 0, 1, 9.00, '13956653544', 3, 0, 'laolongwan', '', NULL, '', '', '', 'b5f20c6fc769a5e63b9c69008fd3b914', 'f64624563f6480dd3f9d9ff1ff086542', '8306a84deadc14a74a90f63816589a20', '', '', 1, '2018-04-05 10:28:34', NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (10, '2018-05-24 14:17:27', '2018-12-24 18:06:07', 0, 1, 10.00, '13222112222', 2, 0, '', '', 1, '', '', '', '35aae7fc18066e0600c972ac2a30d34e', '6a5d5519fa4ced2a29fb1a85b7985f7a', 'ae803507896a2a65bef833e0907b0ce7', '', '', 1, NULL, NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (11, '2018-05-24 14:17:27', '2019-08-09 18:46:40', 0, 1, 11.00, '13677667865', 0, 0, '', '', 0, '', '', '', '87396c19b323a9b88ef62c9ad6a2c14a', 'a11fdeaf9bdd0aa3b3ef70a60812174d', '1767c0dbba47f5962a794318a44eb563', '', '', 1, '2018-04-05 10:30:11', NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (12, '2018-05-24 14:17:27', '2019-08-09 15:55:40', 0, 1, 12.00, '13677667862', 4, 0, '', '', 0, '', '', '', 'b02b6ae2587184707f60f5b011f81979', 'e467baa7a5236dccc45ceaf3d345cfe0', NULL, '', '', 1, NULL, NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (13, '2018-05-24 14:17:27', '2019-08-09 15:55:40', 0, 1, 13.00, 'aeraf@qq.com', NULL, 1, '', 'aeraf@qq.com', 0, '', '', '', '5597b7158457c7e7f289f7a6747509e1', 'b74634830122ebbfefed40edf8c0f7a7', NULL, '', '', 1, NULL, NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (14, '2018-05-24 14:17:27', '2019-08-09 15:55:40', 0, 0, 14.00, 'szgkzdf', 4, 1, '深圳高科公司', 'szgk@com.cn', 0, '3333', '4343', 'http://localhost:9090/fs/uf/product/image/20180322/20180322222640KX56GM92.jpg', '21de6b46af42a3c0bc407294a8fe359e', '537036e6bf8839577bdd93b7d9d962b6', '7aad1c192ffbbd84761aea61a11854cb', '', '', 1, NULL, NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (8887, '2018-05-24 14:17:27', '2019-08-09 15:55:40', 0, 1, 15.00, 'test', 11, 0, '平台', 'longlongagokk@126.com', 1, '', '', '', 'aa8b33c6597c5d7a52f2809a0e2c48b4', '45755869a52dcd213b28350182bba91b', NULL, '', '', 0, NULL, NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (8888, '2018-05-24 14:17:27', '2019-08-09 15:55:40', 0, 1, 16.00, '平台用户P2PMERCHID', 1, 1, '', '13724256432@qq.com', 0, '', '', '', 'aa52e013d72bb71c08663205c0213869', 'a859bdb2adcb37530359ce1ecec7187b', NULL, '', '', 0, NULL, NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (8889, '2018-08-02 15:38:59', '2019-08-09 15:55:40', 0, 1, 17.00, 'yangser', 7, 0, 'fover', 'f11111@qq.com', 0, '2321111111', '', 'http://back.boozilin.com/fs/uf/product/image/20180802/20180802153839I2W9TK2E.jpg', 'bb8ec19c76654eb783ca4edb8d27b14a', 'b20067493db8d5e3379a9f124441409f', NULL, '', '', 1, NULL, NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (8890, '2018-12-18 18:18:00', '2019-08-09 15:55:40', 0, 1, 18.58, '13724256432', NULL, 0, '', '', 0, '', '', '', 'c48e8b0190ff35e9100b4680abcc7e64', '34e905031ba64482a981e4b317d9e0dc', '45fba8188f4517496514ae6b06ac44d1', '', '', 0, '2019-07-17 13:59:19', NULL, NULL);
INSERT INTO `tb_mem_base` VALUES (8891, '2018-12-24 17:55:35', '2018-12-24 18:35:52', 0, 1, 0.00, '13458784554', NULL, 0, '', '', 1, '', '', '', 'd0046bbc621c98d5c6356647b9e51947', 'fa10e72b5dcd6aaddc690e35471a4f51', 'ff5a683cefeb951b91b06f24ebeeab29', '', '', 0, '2018-12-24 17:55:35', NULL, 'ed916859e2252ee518fa815d424afcd2');
INSERT INTO `tb_mem_base` VALUES (8892, '2018-12-24 17:56:10', '2018-12-24 18:36:00', 0, 1, 0.00, '13256989985', NULL, 0, '', '', 1, '', '', '', '5b7f9dfc40002523ae3135eb0de71b7b', '6aa6a051a2b276cd17dce23ff421e602', '6c32116b2e3f259414cc0be86a1f0c11', '', '', 0, '2018-12-24 17:56:10', NULL, '19cd813eddbfdf8bfc81d0204511818c');
INSERT INTO `tb_mem_base` VALUES (8893, '2018-12-24 17:56:14', '2018-12-24 18:36:09', 0, 1, 0.00, '13658987452', NULL, 0, '', '', 1, '', '', '', '6477e4ef1d2efc705db04b175691e4d7', '0771157eb35906072bd435a9f52ab5bb', 'e26b4fad13323d3c8e1d51a0df925d33', '', '', 0, '2018-12-24 17:56:14', NULL, '7b573f0558b305c1d38a1dcd8fcd98fd');
INSERT INTO `tb_mem_base` VALUES (8894, '2018-12-24 17:56:21', '2018-12-24 18:36:17', 0, 1, 0.00, '13254145785', NULL, 0, '', '', 1, '', '', '', 'acc8a8a3b14fb91bbb58fc1d65e95f72', 'fdb299c917bb8bb7ff7ffb28e0dfb5c6', '92fa7816447f57a4aee3701dfad7adc8', '', '', 0, '2018-12-24 17:56:21', NULL, '37a9f417887e8b81d2a5c6940f72599a');
INSERT INTO `tb_mem_base` VALUES (8895, '2018-12-24 17:56:23', '2018-12-24 18:35:33', 0, 1, 0.00, '13569856526', NULL, 0, '', '', 1, '', '', '', '1f3acada37e3e9e7fc0f191546bfb335', '89fa975aef887459bc61eb1a71ee093d', 'f9553cf91be0f222db77f9586ef7b77b', '', '', 0, '2018-12-24 17:56:23', NULL, '1342a3cae4fd28cacd8526d6ea048fd9');
INSERT INTO `tb_mem_base` VALUES (8896, '2018-12-24 18:00:50', '2018-12-24 18:00:50', 0, 1, 0.00, '13724256431', NULL, 0, NULL, NULL, 1, NULL, NULL, NULL, '50797380df068042ce90cc4b6a5c1379', 'e2e6e0fb772ac168becc1610a6364424', '61f20833ea2fc71808e694663d9e4f72', NULL, NULL, 0, '2018-12-24 18:00:50', NULL, 'bb4bbfafbd5aa089a4438f331f61cfb3');
INSERT INTO `tb_mem_base` VALUES (8897, '2018-12-24 18:07:21', '2018-12-24 18:07:21', 0, 1, 0.00, '13724256430', NULL, 0, NULL, NULL, 1, NULL, NULL, NULL, '405002fe0f2d4a85d91a48973746f099', 'f2c74c01e29410a8abd4c486a3d39ec5', '46b13b1e5d719f495be5a256d11be08d', NULL, NULL, 0, '2018-12-24 18:07:21', NULL, '3425ee3d4cef4bbef525bfe781663ddc');
INSERT INTO `tb_mem_base` VALUES (8898, '2018-12-24 18:08:46', '2018-12-24 18:08:46', 0, 1, 0.00, '13724256439', NULL, 0, NULL, NULL, 1, NULL, NULL, NULL, 'b807087a54962a88ad6173b71ab4cbe5', '594a30dc184f1e62bc40ff7ebd40be8e', '2b2ad1a863614b0772862a8fc201c833', NULL, NULL, 0, '2018-12-24 18:08:46', NULL, 'ccfd8ceada83337053795141d869cd35');
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
