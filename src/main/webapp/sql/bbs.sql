/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : bbs

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 24/06/2021 16:53:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for replytiezi
-- ----------------------------
DROP TABLE IF EXISTS `replytiezi`;
CREATE TABLE `replytiezi`  (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tcontent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL,
  `tdate` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `pid` int(11) NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of replytiezi
-- ----------------------------
INSERT INTO `replytiezi` VALUES (1, '测试信息', '2021-06-21 17:11:09', 1, 1);
INSERT INTO `replytiezi` VALUES (17, '标个眼', '2021-06-24 14:28:19', 3, 1);
INSERT INTO `replytiezi` VALUES (18, 'dsfsadfsadfsd', '2021-06-24 11:38:13', 10, 0);
INSERT INTO `replytiezi` VALUES (19, '不错额，挺好的建议！', '2021-06-24 14:32:50', 3, 0);
INSERT INTO `replytiezi` VALUES (20, '多发点哈打扫哈', '2021-06-24 15:01:21', 17, 0);
INSERT INTO `replytiezi` VALUES (21, '华东师大', '2021-06-24 15:02:30', 7, 0);
INSERT INTO `replytiezi` VALUES (22, '的很大很大', '2021-06-24 15:14:37', 3, 0);
INSERT INTO `replytiezi` VALUES (23, '38你好哇啊大多数', '2021-06-24 15:43:54', 22, 2);
INSERT INTO `replytiezi` VALUES (24, '不快乐啊啊啊', '2021-06-24 15:45:28', 23, 2);

-- ----------------------------
-- Table structure for tiezi
-- ----------------------------
DROP TABLE IF EXISTS `tiezi`;
CREATE TABLE `tiezi`  (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL,
  `tcontent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL,
  `tdate` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `tnum1` int(20) NULL DEFAULT NULL,
  `tnum2` int(25) NULL DEFAULT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `status` int(6) NULL DEFAULT NULL,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tiezi
-- ----------------------------
INSERT INTO `tiezi` VALUES (3, '多喝热水', '专家说多喝热水对身体好', '2021-06-24 15:14:38', 231, 1, 1, 2, 0);
INSERT INTO `tiezi` VALUES (4, '特斯拉销量', '网传特斯拉 5 月销量破 2 万辆 环比 4 月暴涨近 100%', '2021-06-24 14:24:46', 14, 1, 1, 2, 1);
INSERT INTO `tiezi` VALUES (5, '低智短视频像猪食 ', '腾讯副总裁批低智短视频像猪食 编剧：又是谁在生产长视频猪食', '2021-06-24 14:20:08', 8, 0, 0, 2, 1);
INSERT INTO `tiezi` VALUES (6, '曝微软内部会议', '曝微软内部会议敲定Windows 11：严禁截图泄露', '2021-06-24 14:21:52', 160, 1, 1, 2, 1);
INSERT INTO `tiezi` VALUES (7, '乐视网被强制执行', '乐视网被强制执行超3.7亿元 贾跃亭是大股东', '2021-06-24 15:02:31', 162, 1, 1, 2, 0);
INSERT INTO `tiezi` VALUES (8, '云南北迁野象群最新动向：向西南迁移6.6公里、人象平安', '云南北迁野象群最新动向：向西南迁移6.6公里、人象平安', '2021-06-24 15:02:20', 160, 1, 1, 2, 0);
INSERT INTO `tiezi` VALUES (9, '“祝融号”火星车开将进行约92个地球日的巡视探测任务', '“祝融号”火星车开将进行约92个地球日的巡视探测任务', '2021-06-24 14:21:53', 159, 1, 1, 2, 1);
INSERT INTO `tiezi` VALUES (10, '比尔·盖茨遭“亲密关系”调查', '比尔·盖茨遭“亲密关系”调查', '2021-06-24 14:21:08', 161, 1, 1, 2, 0);
INSERT INTO `tiezi` VALUES (11, '\r\n最新日本游戏群体调查 Switch游戏依然是小学生们的最爱', '\r\n最新日本游戏群体调查 Switch游戏依然是小学生们的最爱', '2021-06-24 14:21:13', 159, 1, 1, 2, 0);
INSERT INTO `tiezi` VALUES (12, '英国正在调查一种新的变异新冠病毒 已有49人感染', '英国正在调查一种新的变异新冠病毒 已有49人感染', '2021-06-24 14:21:19', 159, 1, 1, 2, 0);
INSERT INTO `tiezi` VALUES (13, '“杂交水稻之父”袁隆平院士逝世 享年91岁', '“杂交水稻之父”袁隆平院士逝世 享年91岁', '2021-06-24 14:54:41', 160, 1, 1, 2, 0);
INSERT INTO `tiezi` VALUES (14, '微软将在2022年6月15日彻底结束Internet Explorer浏览器的使命', '微软将在2022年6月15日彻底结束Internet Explorer浏览器的使命', '2021-06-24 14:23:06', 162, 1, 1, 2, 0);
INSERT INTO `tiezi` VALUES (15, '女子被骗22万后 假装“网恋”8个月将骗子骗回国', '女子被骗22万后 假装“网恋”8个月将骗子骗回国', '2021-06-24 14:21:36', 159, 1, 1, 2, 0);
INSERT INTO `tiezi` VALUES (16, '联合国发文缅怀袁隆平', '联合国发文缅怀袁隆平：国士无双 消除贫困造福民生', '2021-06-24 14:23:46', 160, 1, 1, 2, 1);
INSERT INTO `tiezi` VALUES (17, '是真的吗？', '孔雀会飞是真的吗？', '2021-06-24 15:03:06', 4, 0, 0, 2, 0);
INSERT INTO `tiezi` VALUES (18, '多发点', '的发送到发送到', '2021-06-24 15:00:06', 0, 0, 0, 2, 0);
INSERT INTO `tiezi` VALUES (19, '我的爱多发点', '阿萨德大法师的', '2021-06-24 15:00:59', 0, 0, 0, 0, 0);
INSERT INTO `tiezi` VALUES (20, '测试111', '测试2222', '2021-06-24 15:14:52', 0, 0, 0, 0, 0);
INSERT INTO `tiezi` VALUES (21, '狗子', '狗子你变了额', '2021-06-24 15:37:55', 0, 0, 0, 0, 1);
INSERT INTO `tiezi` VALUES (22, '38你好', 'jiajingjiajing', '2021-06-24 15:44:49', 5, 0, 0, 2, 0);
INSERT INTO `tiezi` VALUES (23, '狗子你快乐吗？', 'd狗子你快乐吗？', '2021-06-24 15:46:08', 4, 0, 0, 2, 1);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL,
  `upwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL,
  `uquestion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL,
  `uanswer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL,
  `uemail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL,
  `utype` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_german2_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'bbb', 'bbb', '0', '1', 'bbbb', '1');
INSERT INTO `users` VALUES (2, 'demo', 'demo', '0', '1', '1', '0');
INSERT INTO `users` VALUES (3, 'cyg', 'cyg', NULL, NULL, '111', '1');
INSERT INTO `users` VALUES (4, 'aaa', 'aaa', '0', '1', 'aaa', '1');
INSERT INTO `users` VALUES (5, 'bbb', 'bbb', '0', '1', 'bbbb', '1');

SET FOREIGN_KEY_CHECKS = 1;
