/*
 Navicat Premium Data Transfer

 Source Server         : plc
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : plc

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 06/05/2025 15:49:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for plc
-- ----------------------------
DROP TABLE IF EXISTS `plc`;
CREATE TABLE `plc`  (
  `plc_id` int(11) NOT NULL AUTO_INCREMENT,
  `plc_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `plc_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `plc_port` int(11) NULL DEFAULT NULL,
  `connection_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '连接状态',
  `product_series` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产品系列',
  `product_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '产品类型',
  `standard_voltage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '额定电源电压',
  `discrete_input_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '离散输入数量',
  `analogue_input_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '模拟输入数量',
  `discrete_output_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '离散输出类型',
  `discrete_output_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '离散输出数量',
  `discrete_output_voltage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '离散输出电压',
  `discrete_output_current` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '离散输出电流',
  `plc_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`plc_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of plc
-- ----------------------------
INSERT INTO `plc` VALUES (1, 'SCHNEIDER TM221CE16R', '192.168.102.1', 502, '离线', '标准型', 'PLC主机', '100...240 V AC', '9', '2', '继电器常开', '8', '5...125 V DC, 5...250 V AC', '2 A', '/images/plc/1.png');
INSERT INTO `plc` VALUES (2, 'LOGO! 12/24RCE', '192.168.102.2', 503, '离线', '标准型', 'PLC主机', '10.8-28.8V DC', '8', '4', '继电器常开', '4', '5...125 V DC, 5...250 V AC', '2 A', '/images/plc/2.png');
INSERT INTO `plc` VALUES (3, 'SIMATIC S7-1200', '192.168.102.3', 503, '离线', '标准型', 'CPU', '24V DC', '14', '2', '继电器常开', '10', '5...125 V DC, 5...250 V AC', '2 A', '/images/plc/3.png');
INSERT INTO `plc` VALUES (4, 'SIMATIC S7-200 SMART', '192.168.102.4', 503, '离线', '标准型', '逻辑控制器', '220V AC 或 110V DC', '12', '2', '继电器常开', '8', '5...125 V DC, 5...250 V AC', '2 A', '/images/plc/4.png');
INSERT INTO `plc` VALUES (5, 'SIMATIC LE5118', '172.172.12.133', 503, '离线', '标准型', '逻辑控制器', '220V AC', '24', '2', '继电器常开', '16', '24VDC', '2 A', '/images/plc/5.png');
INSERT INTO `plc` VALUES (6, 'HOLLYSYS LM3109', '192.168.102.6', 503, '离线', '紧凑型', '逻辑控制器', '100-240V AC', '20', '2', '继电器常开', '16', '24VDC', '0.5 A', '/images/plc/6.png');
INSERT INTO `plc` VALUES (7, 'OMRON CP1E-N20DR-A', '192.168.102.7', 506, '离线', '紧凑型', 'PLC', '100-240V AC, 50/60Hz', '24', '2', '晶体管输出', '16', '5-24VDC', '0.5 A', '/images/plc/7.png');

SET FOREIGN_KEY_CHECKS = 1;
