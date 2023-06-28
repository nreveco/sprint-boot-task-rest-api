/*
Navicat MySQL Data Transfer

Source Server         : wamp
Source Server Version : 80031
Source Host           : localhost:3306
Source Database       : tasks

Target Server Type    : MYSQL
Target Server Version : 80031
File Encoding         : 65001

Date: 2023-06-27 22:43:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tasks
-- ----------------------------
DROP TABLE IF EXISTS `tasks`;
CREATE TABLE `tasks` (
  `identifier` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `active` int DEFAULT NULL,
  PRIMARY KEY (`identifier`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tasks
-- ----------------------------
INSERT INTO `tasks` VALUES ('4', 'tarea 1', '2023-06-27 20:00:00.000000', '1');
INSERT INTO `tasks` VALUES ('5', 'tarea 5', '2023-06-27 20:00:00.000000', '0');
INSERT INTO `tasks` VALUES ('6', 'tarea 6', '2023-06-27 20:00:00.000000', '1');
INSERT INTO `tasks` VALUES ('7', 'tarea 7 update', '2023-06-30 20:00:00.000000', '1');
INSERT INTO `tasks` VALUES ('15', 'tarea 1 modificada', '2023-08-02 20:00:00.000000', '1');
INSERT INTO `tasks` VALUES ('16', 'test tarea', '2023-06-29 20:00:00.000000', '1');
