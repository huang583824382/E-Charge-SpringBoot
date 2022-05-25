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

 Date: 24/05/2022 15:48:18
*/

CREATE USER IF NOT EXISTS 'echarge'@'localhost ' IDENTIFIED BY '123456';

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS echarge;

USE echarge;

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
  `time_excepted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`item_id`) USING BTREE,
  INDEX `item_puber`(`pub_id`) USING BTREE,
  CONSTRAINT `item_puber` FOREIGN KEY (`pub_id`) REFERENCES `user` (`uid`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES (1, '白色短袖连衣裙荷叶边裙摆宽松韩版休闲纯白清爽优雅连衣裙', 0, 1, 199.00, '裙子;休闲', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09a.png;https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09b.png', '超值裙裙！', '2022-02-15 01:02:03', 0, NULL, NULL);
INSERT INTO `commodity` VALUES (2, '纯色纯棉休闲圆领短袖T恤纯白亲肤厚柔软细腻面料纯白短袖套头T恤', 0, 2, 299.00, 'T恤', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-08b.png', '爆款T恤', '2022-03-15 04:05:06', 1, NULL, NULL);
INSERT INTO `commodity` VALUES (3, '运动连帽拉链卫衣休闲开衫长袖多色运动细绒面料运动上衣', 0, 3, 399.00, '卫衣', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-17a.png;https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-17a1.png', '帅气卫衣~', '2022-02-01 07:08:09', 2, NULL, NULL);
INSERT INTO `commodity` VALUES (4, '腾讯极光盒子4智能网络电视机顶盒6K千兆网络机顶盒4K高分辨率', 0, 4, 599.00, '机顶盒', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/dz-3a.png', '极致高清', '2022-04-15 01:01:01', 3, NULL, NULL);
INSERT INTO `commodity` VALUES (5, '带帽午休毯虎年款多功能加厚加大加绒简约多功能午休毯连帽披肩', 0, 5, 99.00, '披肩', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/muy-3a.png', '休闲百搭', '2022-01-15 02:02:02', 0, NULL, NULL);
INSERT INTO `commodity` VALUES (6, '玉泉求软工课本', 1, 6, 50.00, '教材', '', '最好有笔记！', '2022-04-25 11:12:13', 0, '玉泉', NULL);
INSERT INTO `commodity` VALUES (7, '紫金港蓝田求取外卖', 1, 7, 3.00, '外卖', '', '18点前送至蓝田，细节私聊', '2022-05-01 17:02:06', 1, '紫金港', NULL);
INSERT INTO `commodity` VALUES (8, '华家池帮领快递', 1, 8, 5.00, '快递', '', '明天前送至×宿舍', '2022-04-29 13:12:28', 2, '华家池', NULL);
INSERT INTO `commodity` VALUES (9, '西溪求借书', 1, 9, 10.00, '借书', '', '西溪图书馆借一本《三体》，明天前送至×宿舍', '2022-03-07 19:52:43', 3, '西溪', NULL);
INSERT INTO `commodity` VALUES (10, '之江求篮球', 1, 10, 60.00, '篮球', '', '有意者私聊', '2022-04-08 15:22:13', 0, '之江', NULL);

insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('白色短袖连衣裙荷叶边裙摆宽松韩版休闲纯白清爽优雅连衣裙', 0, 1, 299, '裙子;休闲', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09b.png', '超值裙裙！', '2022-02-15 01:02:50', 0);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('白色短袖连衣裙荷叶边裙摆宽松韩版休闲纯白清爽优雅连衣裙', 0, 1, 399, '裙子;休闲', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09b.png', '超值裙裙！', '2022-02-15 02:02:50', 0);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('白色短袖连衣裙荷叶边裙摆宽松韩版休闲纯白清爽优雅连衣裙', 0, 1, 499, '裙子;休闲', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09b.png', '超值裙裙！', '2022-02-15 03:02:50', 0);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('白色短袖连衣裙荷叶边裙摆宽松韩版休闲纯白清爽优雅连衣裙', 0, 1, 599, '裙子;休闲', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09b.png', '超值裙裙！', '2022-02-15 04:02:50', 0);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('白色短袖连衣裙荷叶边裙摆宽松韩版休闲纯白清爽优雅连衣裙', 0, 1, 699, '裙子;休闲', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09b.png', '超值裙裙！', '2022-02-15 05:02:50', 0);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('白色短袖连衣裙荷叶边裙摆宽松韩版休闲纯白清爽优雅连衣裙', 0, 1, 799, '裙子;休闲', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09b.png', '超值裙裙！', '2022-02-15 06:02:50', 0);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('白色短袖连衣裙荷叶边裙摆宽松韩版休闲纯白清爽优雅连衣裙', 0, 1, 899, '裙子;休闲', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09b.png', '超值裙裙！', '2022-02-15 07:02:50', 0);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('白色短袖连衣裙荷叶边裙摆宽松韩版休闲纯白清爽优雅连衣裙', 0, 1, 999, '裙子;休闲', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09b.png', '超值裙裙！', '2022-02-15 08:02:50', 0);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('白色短袖连衣裙荷叶边裙摆宽松韩版休闲纯白清爽优雅连衣裙', 0, 1, 1099, '裙子;休闲', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09b.png', '超值裙裙！', '2022-02-15 09:02:50', 0);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('白色短袖连衣裙荷叶边裙摆宽松韩版休闲纯白清爽优雅连衣裙', 0, 1, 1199, '裙子;休闲', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09b.png', '超值裙裙！', '2022-02-15 10:02:50', 0);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('白色短袖连衣裙荷叶边裙摆宽松韩版休闲纯白清爽优雅连衣裙', 0, 1, 1299, '裙子;休闲', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09b.png', '超值裙裙！', '2022-02-15 11:02:50', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of transaction
-- ----------------------------
INSERT INTO `transaction` VALUES (1, 1, 1, 1, 3, '2022-05-23 18:28:31', 2);
INSERT INTO `transaction` VALUES (2, 20, 1, 1, 5, '2022-05-23 18:38:04', 2);
INSERT INTO `transaction` VALUES (3, 20, 1, 1, 1, '2022-05-23 18:38:16', 1);
INSERT INTO `transaction` VALUES (4, 20, 0, 20, 4, '2022-05-23 18:38:16', 1);
INSERT INTO `transaction` VALUES (5, 20, 0, 3, 1, '2022-05-23 18:38:16', 1);
INSERT INTO `transaction` VALUES (6, 20, 1, 20, 2, '2022-05-23 18:38:16', 1);
INSERT INTO `transaction` VALUES (7, 20, 1, 1, 1, '2022-05-23 18:38:16', 1);
INSERT INTO `transaction` VALUES (8, 20, 1, 20, 7, '2022-05-23 18:38:16', 2);
INSERT INTO `transaction` VALUES (9, 20, 1, 1, 1, '2022-05-23 18:38:16', 1);
INSERT INTO `transaction` VALUES (10, 20, 1, 1, 1, '2022-05-23 18:38:16', 1);
INSERT INTO `transaction` VALUES (11, 20, 1, 20, 6, '2022-05-23 18:38:16', 1);
INSERT INTO `transaction` VALUES (12, 20, 1, 2, 1, '2022-05-23 18:38:16', 1);

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
  `is_login` int(0) NULL DEFAULT NULL,
  `token` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone_number` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `credit` int(0) NULL DEFAULT NULL,
  `balance` double(255, 0) NULL DEFAULT NULL,
  `icon_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_admin` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`wx_id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (4, '000', 'jkl', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (5, '111', 'mno', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (1, '123', 'abc', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (6, '222', 'pqr', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (7, '333', 'stu', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (8, '444', 'vwx', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (2, '456', 'def', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (9, '555', 'aaa', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (10, '666', 'bbb', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (3, '789', 'ghi', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;

GRANT all ON echarge.* TO 'echarge'@'localhost';
