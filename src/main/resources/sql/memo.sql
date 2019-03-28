/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : memo

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 07/03/2019 14:20:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for memo
-- ----------------------------
DROP TABLE IF EXISTS `memo`;
CREATE TABLE `memo`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备忘录内容',
  `place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地点',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `remind_time` datetime(0) NOT NULL COMMENT '提醒时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_content`(`content`) USING BTREE COMMENT '备忘录内容索引',
  INDEX `index_place`(`place`) USING BTREE COMMENT '地点索引',
  INDEX `index_start_time`(`start_time`) USING BTREE COMMENT '开始时间索引',
  INDEX `index_end_time`(`end_time`) USING BTREE COMMENT '结束时间索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '备忘录表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
