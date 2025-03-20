/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : plc

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 01/03/2025 21:49:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for crane
-- ----------------------------
DROP TABLE IF EXISTS `crane`;
CREATE TABLE `crane`  (
  `num` int(0) NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `weight` float NULL DEFAULT NULL,
  `sit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crane
-- ----------------------------
INSERT INTO `crane` VALUES (1, '起升制动器', 953.5, '0');
INSERT INTO `crane` VALUES (2, '起升制动器', 953.5, '0');
INSERT INTO `crane` VALUES (3, '起升制动器', 953.5, '1');
INSERT INTO `crane` VALUES (4, '起升制动器', 953.5, '1');

-- ----------------------------
-- Table structure for data
-- ----------------------------
DROP TABLE IF EXISTS `data`;
CREATE TABLE `data`  (
  `Num` int(0) NOT NULL COMMENT '记录序号',
  `Time` datetime(0) NULL DEFAULT NULL COMMENT '记录时间',
  `Speed` float NULL DEFAULT NULL COMMENT '速度信息',
  `Tem` float NULL DEFAULT NULL COMMENT '温度信息',
  `Yingli` float NULL DEFAULT NULL COMMENT '应力信息',
  `Dir_x` float NULL DEFAULT NULL COMMENT 'x轴应力信息',
  `Dir_y` float NULL DEFAULT NULL COMMENT 'y轴应力信息',
  PRIMARY KEY (`Num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of data
-- ----------------------------
INSERT INTO `data` VALUES (1, '2023-02-02 12:23:36', 0.036, 20.803, 5.465, 0.8, 0.683);
INSERT INTO `data` VALUES (2, '2023-02-02 12:23:37', 0.046, 20.781, 5.273, 0.799, 0.682);
INSERT INTO `data` VALUES (3, '2023-02-02 12:23:38', 0.003, 20.788, 4.132, 0.801, 0.684);
INSERT INTO `data` VALUES (4, '2023-02-02 12:23:39', 0.036, 20.801, 2.811, 0.8, 0.684);
INSERT INTO `data` VALUES (5, '2023-02-02 12:23:40', 0.045, 20.807, 2.346, 0.801, 0.683);
INSERT INTO `data` VALUES (6, '2023-02-02 12:23:41', 0.059, 20.81, 2.625, 0.801, 0.683);
INSERT INTO `data` VALUES (7, '2023-02-02 12:23:42', 0.014, 20.803, 2.154, 0.801, 0.684);
INSERT INTO `data` VALUES (8, '2023-02-02 12:23:43', 0.291, 20.824, 2.03, 0.801, 0.684);
INSERT INTO `data` VALUES (9, '2023-02-02 12:23:44', 0.447, 20.824, 3.394, 0.801, 0.683);
INSERT INTO `data` VALUES (10, '2023-02-02 12:23:45', 0.552, 20.84, 2.755, 0.8, 0.683);
INSERT INTO `data` VALUES (16, '2023-04-15 21:41:08', 566, 7575, 775, 6, 565);
INSERT INTO `data` VALUES (17, '2023-04-15 21:41:24', 65, 65, 65, 6, 65);
INSERT INTO `data` VALUES (18, '2023-04-12 21:41:35', 665, 65, 65, 65, 65);
INSERT INTO `data` VALUES (10021, '2024-02-01 16:04:15', 0.089, 26.3, 22044.3, 25213, 24524.1);
INSERT INTO `data` VALUES (10022, '2024-02-01 16:04:23', 0.095, 22.4, 22041.7, 25212.8, 24520.4);
INSERT INTO `data` VALUES (10023, '2024-02-01 16:04:31', 0.061, 20.9, 22040.5, 25212.4, 24519);
INSERT INTO `data` VALUES (10024, '2024-02-01 16:04:39', 0.054, 21.7, 22039.8, 25212.8, 24520.1);
INSERT INTO `data` VALUES (10025, '2024-02-01 16:04:47', 0.065, 23.6, 22041.7, 25212.6, 24521.5);

-- ----------------------------
-- Table structure for data1
-- ----------------------------
DROP TABLE IF EXISTS `data1`;
CREATE TABLE `data1`  (
  `time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `yingbian1` decimal(10, 5) NULL DEFAULT NULL,
  `yingbian2` decimal(10, 5) NULL DEFAULT NULL,
  `yingbian3` decimal(10, 5) NULL DEFAULT NULL,
  `yingbian4` decimal(10, 5) NULL DEFAULT NULL,
  `speed1` decimal(10, 5) NULL DEFAULT NULL,
  `speed2` decimal(10, 5) NULL DEFAULT NULL,
  `tem` float(10, 0) NULL DEFAULT NULL,
  `dir_x` decimal(8, 5) NULL DEFAULT NULL,
  `dir_y` decimal(8, 5) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for data2
-- ----------------------------
DROP TABLE IF EXISTS `data2`;
CREATE TABLE `data2`  (
  `time` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `yingbian1` decimal(10, 5) NULL DEFAULT NULL,
  `yingbian2` decimal(10, 5) NULL DEFAULT NULL,
  `yingbian3` decimal(10, 5) NULL DEFAULT NULL,
  `yingbian4` decimal(10, 5) NULL DEFAULT NULL,
  `speed1` decimal(10, 5) NULL DEFAULT NULL,
  `speed2` decimal(10, 5) NULL DEFAULT NULL,
  `tem` float(10, 0) NULL DEFAULT NULL,
  `dir_x` decimal(8, 5) NULL DEFAULT NULL,
  `dir_y` decimal(8, 5) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of data2
-- ----------------------------
INSERT INTO `data2` VALUES ('16837737725680000', 2.75500, 2.75500, 2.75500, 2.75500, 0.55200, 0.55200, 21, 0.80000, 0.68300);
INSERT INTO `data2` VALUES ('16837737645680000', 3.39400, 3.39400, 3.39400, 3.39400, 0.44700, 0.44700, 21, 0.80100, 0.68300);
INSERT INTO `data2` VALUES ('16837737565680000', 2.03000, 2.03000, 2.03000, 2.03000, 0.29100, 0.29100, 21, 0.80100, 0.68400);
INSERT INTO `data2` VALUES ('16837737485680000', 2.15400, 2.15400, 2.15400, 2.15400, 0.01400, 0.01400, 21, 0.80100, 0.68400);
INSERT INTO `data2` VALUES ('16837737405680000', 2.62500, 2.62500, 2.62500, 2.62500, 0.05900, 0.05900, 21, 0.80100, 0.68300);
INSERT INTO `data2` VALUES ('16837737325680000', 2.34600, 2.34600, 2.34600, 2.34600, 0.04500, 0.04500, 21, 0.80100, 0.68300);
INSERT INTO `data2` VALUES ('16837737245680000', 2.81100, 2.81100, 2.81100, 2.81100, 0.03600, 0.03600, 21, 0.80000, 0.68400);
INSERT INTO `data2` VALUES ('16837737165680000', 4.13200, 4.13200, 4.13200, 4.13200, 0.00300, 0.00300, 21, 0.80100, 0.68400);
INSERT INTO `data2` VALUES ('16837737085680000', 5.27300, 5.27300, 5.27300, 5.27300, 0.04600, 0.04600, 21, 0.79900, 0.68200);
INSERT INTO `data2` VALUES ('16837737005680000', 5.46500, 5.46500, 5.46500, 5.46500, 0.03600, 0.03600, 21, 0.80000, 0.68300);

-- ----------------------------
-- Table structure for error
-- ----------------------------
DROP TABLE IF EXISTS `error`;
CREATE TABLE `error`  (
  `num` int(0) NOT NULL,
  `temup` float NULL DEFAULT NULL,
  `temdown` float NULL DEFAULT NULL,
  PRIMARY KEY (`num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of error
-- ----------------------------
INSERT INTO `error` VALUES (1, 20, -20);

-- ----------------------------
-- Table structure for information
-- ----------------------------
DROP TABLE IF EXISTS `information`;
CREATE TABLE `information`  (
  `sno` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `Sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `birth` date NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`sno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10000 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of information
-- ----------------------------
INSERT INTO `information` VALUES (10000, '张三', '女', NULL, '12345678998', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `Sno` int(0) NOT NULL,
  `Sname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `Pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `Kind` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (10001, 'zs', '123456', '待审核');
INSERT INTO `user` VALUES (200001, 'ls', '12345', '管理员');

SET FOREIGN_KEY_CHECKS = 1;
