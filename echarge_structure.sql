/*
 Navicat Premium Data Transfer

 Source Server         : echarge
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : echarge

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 25/05/2022 16:54:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` int(0) NOT NULL AUTO_INCREMENT,
  `transaction_id` int(0) NOT NULL,
  `uid` int(0) NULL DEFAULT NULL,
  `rate` float NULL DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`  (
  `item_id` int(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `pub_id` int(0) NULL DEFAULT NULL,
  `price` float(10, 2) NULL DEFAULT NULL,
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `figure_urls` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `release_time` datetime(0) NULL DEFAULT NULL,
  `state` int(0) NULL DEFAULT NULL,
  `place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time_expected` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`item_id`) USING BTREE,
  INDEX `item_puber`(`pub_id`) USING BTREE,
  CONSTRAINT `item_puber` FOREIGN KEY (`pub_id`) REFERENCES `user` (`uid`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `message_id` int(0) NOT NULL AUTO_INCREMENT,
  `type` int(0) NULL DEFAULT NULL,
  `sender_id` int(0) NULL DEFAULT NULL,
  `receiver_id` int(0) NULL DEFAULT NULL,
  `send_time` datetime(0) NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`message_id`) USING BTREE,
  INDEX `msg_sender`(`sender_id`) USING BTREE,
  INDEX `msg_recver`(`receiver_id`) USING BTREE,
  CONSTRAINT `msg_recver` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`uid`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `msg_sender` FOREIGN KEY (`sender_id`) REFERENCES `user` (`uid`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report`  (
  `report_id` int(0) NOT NULL AUTO_INCREMENT,
  `type` int(0) NULL DEFAULT NULL,
  `target_user_id` int(0) NULL DEFAULT NULL,
  `target_item_id` int(0) NULL DEFAULT NULL,
  `whistleblower_id` int(0) NULL DEFAULT NULL,
  `report_time` datetime(0) NULL DEFAULT NULL,
  `state` int(0) NULL DEFAULT NULL,
  `option_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`report_id`) USING BTREE,
  INDEX `pub_er`(`whistleblower_id`) USING BTREE,
  INDEX `target_item`(`target_item_id`) USING BTREE,
  INDEX `target_user`(`target_user_id`) USING BTREE,
  INDEX `admin`(`option_id`) USING BTREE,
  CONSTRAINT `admin` FOREIGN KEY (`option_id`) REFERENCES `user` (`uid`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `pub_er` FOREIGN KEY (`whistleblower_id`) REFERENCES `user` (`uid`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `target_item` FOREIGN KEY (`target_item_id`) REFERENCES `commodity` (`item_id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `target_user` FOREIGN KEY (`target_user_id`) REFERENCES `user` (`uid`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction`  (
  `transaction_id` int(0) NOT NULL AUTO_INCREMENT,
  `seller_id` int(0) NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `customer_id` int(0) NULL DEFAULT NULL,
  `item_id` int(0) NULL DEFAULT NULL,
  `deal_time` datetime(0) NULL DEFAULT NULL,
  `state` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`transaction_id`) USING BTREE,
  INDEX `tran_seller`(`seller_id`) USING BTREE,
  INDEX `tran_customer`(`customer_id`) USING BTREE,
  INDEX `tran_item`(`item_id`) USING BTREE,
  CONSTRAINT `tran_customer` FOREIGN KEY (`customer_id`) REFERENCES `user` (`uid`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tran_item` FOREIGN KEY (`item_id`) REFERENCES `commodity` (`item_id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tran_seller` FOREIGN KEY (`seller_id`) REFERENCES `user` (`uid`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(0) NOT NULL AUTO_INCREMENT,
  `wx_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `gender` int(0) NULL DEFAULT NULL,
  `token` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone_number` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `credit` int(0) NULL DEFAULT NULL,
  `balance` double(255, 0) NULL DEFAULT NULL,
  `icon_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_admin` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
