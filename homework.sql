/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : homework

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 12/03/2023 22:49:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class_room
-- ----------------------------
DROP TABLE IF EXISTS `class_room`;
CREATE TABLE `class_room`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class_room
-- ----------------------------
INSERT INTO `class_room` VALUES (1, '一班', 1);
INSERT INTO `class_room` VALUES (2, '二班', 2);
INSERT INTO `class_room` VALUES (3, '三班', 3);

-- ----------------------------
-- Table structure for home_work_detail
-- ----------------------------
DROP TABLE IF EXISTS `home_work_detail`;
CREATE TABLE `home_work_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `home_work_id` int(11) NULL DEFAULT NULL,
  `p1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `p2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `p3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `p4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of home_work_detail
-- ----------------------------
INSERT INTO `home_work_detail` VALUES (1, '2', 1, '1', '2', '3', '4', 2, '1+1', 1);
INSERT INTO `home_work_detail` VALUES (2, '一种编程语言', 1, NULL, NULL, NULL, NULL, 2, 'java是什么', 2);

-- ----------------------------
-- Table structure for home_work_result
-- ----------------------------
DROP TABLE IF EXISTS `home_work_result`;
CREATE TABLE `home_work_result`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `homework_detail_id` int(11) NULL DEFAULT NULL,
  `homework_id` int(11) NULL DEFAULT NULL,
  `is_true` bit(1) NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of home_work_result
-- ----------------------------
INSERT INTO `home_work_result` VALUES (1, '2', '2', NULL, NULL, NULL, 2, NULL);
INSERT INTO `home_work_result` VALUES (2, '一种编程语言', '2', NULL, NULL, NULL, 2, NULL);

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_room_id` int(11) NULL DEFAULT NULL,
  `create_time` date NULL DEFAULT NULL,
  `homework_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `subject_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES (1, 1, '2023-03-12', 'day1', 1, 2);

-- ----------------------------
-- Table structure for homework_result_detail
-- ----------------------------
DROP TABLE IF EXISTS `homework_result_detail`;
CREATE TABLE `homework_result_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `homework_result_id` int(11) NULL DEFAULT NULL,
  `is_true` bit(1) NULL DEFAULT NULL,
  `my_answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `my_score` int(11) NULL DEFAULT NULL,
  `p1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `p2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `p3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `p4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of homework_result_detail
-- ----------------------------
INSERT INTO `homework_result_detail` VALUES (9, '2', NULL, 5, NULL, '2', 2, '1', '2', '3', '4', 2, '1+1', 1);
INSERT INTO `homework_result_detail` VALUES (10, '一种编程语言', '好好看看', 5, NULL, '1', 0, NULL, NULL, NULL, NULL, 2, 'java是什么', 2);

-- ----------------------------
-- Table structure for homework_result_end
-- ----------------------------
DROP TABLE IF EXISTS `homework_result_end`;
CREATE TABLE `homework_result_end`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `homework_id` int(11) NULL DEFAULT NULL,
  `is_pass` bit(1) NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  `teacher_is_look` bit(1) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of homework_result_end
-- ----------------------------
INSERT INTO `homework_result_end` VALUES (5, 1, b'0', 2, b'1', 1);
INSERT INTO `homework_result_end` VALUES (6, 1, b'0', 4, b'1', 2);

-- ----------------------------
-- Table structure for native
-- ----------------------------
DROP TABLE IF EXISTS `native`;
CREATE TABLE `native`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of native
-- ----------------------------
INSERT INTO `native` VALUES (1, '7月21', '4', '开学');

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES (1, '语文');
INSERT INTO `subject` VALUES (2, '数学');

-- ----------------------------
-- Table structure for teacher_look_work
-- ----------------------------
DROP TABLE IF EXISTS `teacher_look_work`;
CREATE TABLE `teacher_look_work`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class_room_id` int(11) NULL DEFAULT NULL,
  `homework_detail_id` int(11) NULL DEFAULT NULL,
  `homework_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher_look_work
-- ----------------------------
INSERT INTO `teacher_look_work` VALUES (1, '2', 1, 1, 1, 1);
INSERT INTO `teacher_look_work` VALUES (2, '编程语言', 1, 2, 1, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class_room` int(11) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_type` int(11) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '湖南长沙', 1, '333', '15173999999', 1, 'zzy');
INSERT INTO `user` VALUES (2, '湖南长沙', NULL, 'zz', '15137888974', 2, 'zz');
INSERT INTO `user` VALUES (3, '湖南长沙', NULL, 'admin', NULL, 3, 'admin');

SET FOREIGN_KEY_CHECKS = 1;
