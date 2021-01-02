/*
 Navicat Premium Data Transfer

 Source Server         : ConvenientOfficeSystem
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 114.215.186.52:3306
 Source Schema         : db_officesystem

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 02/01/2021 17:09:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Archive
-- ----------------------------
DROP TABLE IF EXISTS `Archive`;
CREATE TABLE `Archive`  (
  `archiveNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `archiveName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `storagePeriod` int(11) NOT NULL,
  `copies` int(11) NOT NULL DEFAULT 0,
  `closeOrNot` int(11) NOT NULL,
  `capacity` int(11) NOT NULL DEFAULT 30,
  PRIMARY KEY (`archiveNo`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Archive
-- ----------------------------
INSERT INTO `Archive` VALUES ('202011', '发文33', 120, 0, 2, 30);
INSERT INTO `Archive` VALUES ('202073', '会议记录', 120, 1, 1, 10);
INSERT INTO `Archive` VALUES ('202091', '发文1', 120, 1, 1, 30);
INSERT INTO `Archive` VALUES ('202092', '收文', 120, 0, 2, 30);

-- ----------------------------
-- Table structure for Attendee
-- ----------------------------
DROP TABLE IF EXISTS `Attendee`;
CREATE TABLE `Attendee`  (
  `meetingSerialNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `department` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `occupation` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `account` int(11) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `informationOrNot` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_1`(`meetingSerialNo`) USING BTREE,
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`meetingSerialNo`) REFERENCES `Meting` (`meetingSerialNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Attendee
-- ----------------------------
INSERT INTO `Attendee` VALUES ('a67e49c56e92708d', '秘书处（办公室）', '主任', 202001001, '苏浩东', 1, 14);
INSERT INTO `Attendee` VALUES ('a67e49c56e92708d', '秘书处（办公室）', '副主任', 202001002, '周义伟', 1, 15);
INSERT INTO `Attendee` VALUES ('a67e49c56e92708d', '秘书处（办公室）', '主任', 202001001, '苏浩东', 1, 16);
INSERT INTO `Attendee` VALUES ('a67e49c56e92708d', '秘书处（办公室）', '副主任', 202001002, '周义伟', 1, 17);
INSERT INTO `Attendee` VALUES ('ae61e8a2a45e648a', '秘书处（办公室）', '主任', 202001001, '苏浩东', 2, 28);
INSERT INTO `Attendee` VALUES ('ae61e8a2a45e648a', '秘书处（办公室）', '副主任', 202001002, '周义伟', 2, 29);
INSERT INTO `Attendee` VALUES ('a52a6f1b33936e09', '秘书处（办公室）', '主任', 202001001, '苏浩东', 2, 30);
INSERT INTO `Attendee` VALUES ('93d114ffb31ab296', '秘书处（办公室）', '主任', 202001001, '苏浩东', 1, 31);

-- ----------------------------
-- Table structure for Borrowing
-- ----------------------------
DROP TABLE IF EXISTS `Borrowing`;
CREATE TABLE `Borrowing`  (
  `borrowingNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `archiveNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `borrower` int(11) NOT NULL,
  `ileNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `borrowingPeriod` int(11) NOT NULL,
  `applicationTime` timestamp(0) NOT NULL,
  `returnTime` timestamp(0) NULL DEFAULT NULL,
  `state` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `approvedTime` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`borrowingNo`) USING BTREE,
  INDEX `FK_Reference_4`(`archiveNo`) USING BTREE,
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`archiveNo`) REFERENCES `Archive` (`archiveNo`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Borrowing
-- ----------------------------
INSERT INTO `Borrowing` VALUES ('1608903854365', '202091', 202001001, 'FF_20201223_89f2f365c152cd99', 60, '2020-12-25 21:44:14', '2021-01-04 21:52:43', '4', '2020-12-25 21:52:43');
INSERT INTO `Borrowing` VALUES ('1608905432615', '202091', 202001001, 'FF_20201223_89f2f365c152cd99', 60, '2020-12-25 22:10:33', NULL, '2', '2020-12-25 22:10:47');
INSERT INTO `Borrowing` VALUES ('1608906746163', '202091', 202001001, 'FF_20201223_89f2f365c152cd99', 5, '2020-12-25 22:32:26', '2020-12-30 22:32:44', '4', '2020-12-25 22:32:44');
INSERT INTO `Borrowing` VALUES ('1609059535503', '202091', 202001001, 'FF_20201223_89f2f365c152cd99', 10, '2020-12-27 16:58:56', '2021-01-06 16:59:32', '4', '2020-12-27 16:59:32');
INSERT INTO `Borrowing` VALUES ('1609071350084', '202073', 202001001, 'a3045a3bdacad5aa', 60, '2020-12-27 20:15:50', NULL, '2', '2020-12-27 20:33:36');
INSERT INTO `Borrowing` VALUES ('1609221867860', '202073', 202001001, 'a3045a3bdacad5aa', 10, '2020-12-29 14:04:28', '2021-01-08 14:05:05', '4', '2020-12-29 14:05:05');
INSERT INTO `Borrowing` VALUES ('1609291916204', '202073', 202001001, 'a3045a3bdacad5aa', 10, '2020-12-30 09:31:56', '2021-01-09 09:38:57', '4', '2020-12-30 09:38:57');

-- ----------------------------
-- Table structure for EnreRFile
-- ----------------------------
DROP TABLE IF EXISTS `EnreRFile`;
CREATE TABLE `EnreRFile`  (
  `fileNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `receiveDate` timestamp(0) NOT NULL,
  `senderOrgan` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fileCaption` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `keyWord` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `haveAffix` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `confidential` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `express` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `copies` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `recorder` int(11) NULL DEFAULT NULL,
  `auditor` int(11) NULL DEFAULT NULL,
  `auditing` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `draftsman` int(11) NULL DEFAULT NULL,
  `drafting` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorizeMan` int(11) NULL DEFAULT NULL,
  `authorizing` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `tranPerson` int(11) NULL DEFAULT NULL,
  `transacting` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `receiver` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of EnreRFile
-- ----------------------------
INSERT INTO `EnreRFile` VALUES ('FF_20201223_89f2f365c152cd99', '2020-12-23 02:57:38', '秘书处（办公室）', '文件3', '主题3', '有', '绝对机密', '限时送', 2, 2, 202001001, 202001001, '', NULL, '111', NULL, '', NULL, '', '', '13370dc7d9b9479b8da2e6b051e5ff7c', 202001001);

-- ----------------------------
-- Table structure for EnreSFile
-- ----------------------------
DROP TABLE IF EXISTS `EnreSFile`;
CREATE TABLE `EnreSFile`  (
  `fileNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fileCaption` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `keyWord` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sendDate` timestamp(0) NOT NULL,
  `receiverOrgan` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `receiver` int(11) NOT NULL,
  `haveAffix` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `confidential` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `express` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of EnreSFile
-- ----------------------------
INSERT INTO `EnreSFile` VALUES ('FF_20201223_89f2f365c152cd99', '文件3', '主题3', '2020-12-23 14:51:11', '秘书处（办公室）', 202001001, '有', '绝对机密', '限时送', 'FF_20201223_a6b1e767366f4a74');

-- ----------------------------
-- Table structure for Meting
-- ----------------------------
DROP TABLE IF EXISTS `Meting`;
CREATE TABLE `Meting`  (
  `meetingSerialNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `meetingName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `topic` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `organizer` int(11) NOT NULL,
  `chiefOrganizer` int(11) NOT NULL,
  `spokesman` int(11) NOT NULL,
  `meetingLocus` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `startTime` timestamp(0) NOT NULL,
  `endTime` timestamp(0) NOT NULL,
  `applicator` int(11) NOT NULL,
  `auditor` int(11) NOT NULL,
  `auditing` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `informer` int(11) NOT NULL,
  `minuteRecorder` int(11) NOT NULL,
  `state` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`meetingSerialNo`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Meting
-- ----------------------------
INSERT INTO `Meting` VALUES ('93d114ffb31ab296', 'dddd', '通知', 202001001, 202001001, 202001001, '武汉', '2020-12-30 09:28:32', '2020-12-30 09:28:36', 202001001, 202001001, '不通过', 202001001, 202001001, '未开始');
INSERT INTO `Meting` VALUES ('9ddfaed145937485', '8984', '7845', 202001001, 202001001, 202001001, '6543', '2020-12-21 19:47:38', '2020-12-21 19:47:38', 233, 202001001, '通过', 202001001, 202001001, '会议已举行');
INSERT INTO `Meting` VALUES ('a52a6f1b33936e09', '大家好', '通知', 202001001, 202001001, 202001001, '武汉', '2020-12-27 19:18:01', '2020-12-27 19:18:06', 202001001, 202001001, '通过', 202001001, 202001001, '会议已举行');
INSERT INTO `Meting` VALUES ('a67e49c56e92708d', '巨蟹座', '收到', 202001002, 202001002, 202001002, '十堰', '2020-12-19 20:48:04', '2020-12-12 20:47:57', 202001002, 202001002, '', 202001002, 202001002, '未开始');
INSERT INTO `Meting` VALUES ('ae61e8a2a45e648a', '奖学金', '通知', 202001001, 202001001, 202001001, '6503', '2020-12-26 16:08:12', '2020-12-26 18:08:16', 202001001, 202001001, '通过', 202001001, 202001001, '会议已举行');

-- ----------------------------
-- Table structure for Minute
-- ----------------------------
DROP TABLE IF EXISTS `Minute`;
CREATE TABLE `Minute`  (
  `meetingSerialNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `meetingName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fileNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fileCaption` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `keyWord` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `chiefOrganizer` int(11) NOT NULL,
  `meetingLocus` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `startTime` timestamp(0) NOT NULL,
  `endTime` timestamp(0) NOT NULL,
  `attendee` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `topicAndArgumentation` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `id` int(14) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_2`(`meetingSerialNo`) USING BTREE,
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`meetingSerialNo`) REFERENCES `Meting` (`meetingSerialNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Minute
-- ----------------------------

-- ----------------------------
-- Table structure for MinuteArchive
-- ----------------------------
DROP TABLE IF EXISTS `MinuteArchive`;
CREATE TABLE `MinuteArchive`  (
  `meetingSerialNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `meetingName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fileNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fileCaption` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `keyWord` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `chiefOrganizer` int(11) NOT NULL,
  `meetingLocus` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `startTime` timestamp(0) NOT NULL,
  `endTime` timestamp(0) NOT NULL,
  `attendee` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `topicAndArgumentation` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `archiveNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_3`(`meetingSerialNo`) USING BTREE,
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`meetingSerialNo`) REFERENCES `Meting` (`meetingSerialNo`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of MinuteArchive
-- ----------------------------
INSERT INTO `MinuteArchive` VALUES ('a52a6f1b33936e09', '大家好', 'a3045a3bdacad5aa', 'bluesky', 'bluesky', 202001001, '武汉', '2020-12-27 19:19:34', '2020-12-27 19:19:37', '苏浩东', '很好', '20122720', '202073');

-- ----------------------------
-- Table structure for Notice
-- ----------------------------
DROP TABLE IF EXISTS `Notice`;
CREATE TABLE `Notice`  (
  `noticeNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告编号;',
  `publishngMaker` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发布人;',
  `deaprtment` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发布人部门;',
  `topic` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主题;',
  `content` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '正文;',
  `publishingTime` timestamp(0) NOT NULL COMMENT '发布时间;',
  `expirationTime` timestamp(0) NOT NULL COMMENT '过期时间;',
  `state` int(1) NOT NULL COMMENT '0-已删除; 1-已发布; 2-已保存; 3-定时发布;',
  `auditStatus` int(1) NOT NULL COMMENT '0-未审核; 1-已审核;',
  PRIMARY KEY (`noticeNo`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Notice
-- ----------------------------
INSERT INTO `Notice` VALUES ('GG_20201224_82c15bfddd6fb83e', '苏浩东', '秘书处（办公室）', '发布通知', '<p>通知</p>', '2020-12-24 17:53:26', '2020-12-27 17:53:26', 0, 1);
INSERT INTO `Notice` VALUES ('GG_20201224_8db1b0a2c887c9eb', '苏浩东', '秘书处（办公室）', '定时发布通知', '<p>通知</p>', '2020-12-24 17:51:50', '2020-12-27 17:51:50', 0, 1);
INSERT INTO `Notice` VALUES ('GG_20201224_8f46aceddc5974ee', '苏浩东', '秘书处（办公室）', '发布通知', '<p>通知</p>', '2020-12-24 17:52:31', '2020-12-27 17:52:31', 0, 1);
INSERT INTO `Notice` VALUES ('GG_20201224_90b9901d94ca595a', '苏浩东', '秘书处（办公室）', '通知', '<p>通知</p>', '2020-12-27 19:49:32', '2020-12-27 19:23:32', 1, 0);
INSERT INTO `Notice` VALUES ('GG_20201224_9edc2d12e8e1ff88', '刘清晨', '省政府总值班处', '通知', '<p>通知</p>', '2020-12-24 19:11:34', '2020-12-27 19:11:34', 0, 1);
INSERT INTO `Notice` VALUES ('GG_20201224_9f4b13713bc47a16', '王五', '秘书处（办公室）', '通知', '<p>通知</p>', '2020-12-24 19:05:43', '2020-12-27 19:05:43', 2, 0);
INSERT INTO `Notice` VALUES ('GG_20201224_9f4fe09a391d75a7', '万秋阳', '保卫处', '通知', '<p>通知</p>', '2020-12-24 19:10:58', '2020-12-27 19:10:58', 0, 1);
INSERT INTO `Notice` VALUES ('GG_20201224_a11a1cdb867ffc0c', '张三', '秘书处（办公室）', '通知', '<p>通知</p>', '2020-12-24 19:07:59', '2020-12-27 19:07:58', 2, 0);
INSERT INTO `Notice` VALUES ('GG_20201224_a5d3982f91ce6f31', '张三', '秘书处（办公室）', '通知', '<p>通知</p>', '2020-12-24 19:07:40', '2020-12-27 19:07:40', 0, 1);
INSERT INTO `Notice` VALUES ('GG_20201224_a79c2952444dedb0', '陈庚宇', '督察处', '通知', '<p>通知</p>', '2020-12-24 19:09:59', '2020-12-27 19:09:59', 0, 1);
INSERT INTO `Notice` VALUES ('GG_20201224_a97ffe15ae7d8ecb', '苏浩东', '秘书处（办公室）', '保存通知', '<h1 style=\"text-align: center;\">通知</h1>', '2020-12-27 19:48:18', '2020-12-30 19:48:17', 2, 0);
INSERT INTO `Notice` VALUES ('GG_20201224_aadf96fc79e37e79', '王五', '秘书处（办公室）', '通知', '<p>通知</p>', '2020-12-24 19:04:30', '2020-12-27 19:04:30', 0, 1);
INSERT INTO `Notice` VALUES ('GG_20201224_ba267cef9e3ed27f', '苏浩东', '秘书处（办公室）', '通知', '<p>通知</p>', '2020-12-24 19:23:52', '2020-12-27 19:23:52', 1, 1);
INSERT INTO `Notice` VALUES ('GG_20201224_bb3613e71922fd99', '张三', '秘书处（办公室）', '通知', '<p>通知</p>', '2020-12-24 19:08:07', '2020-12-27 19:07:50', 0, 0);
INSERT INTO `Notice` VALUES ('GG_20201224_bc9f0d2b3d120263', '王五', '秘书处（办公室）', '通知', '<p>通知</p>', '2020-12-24 19:06:12', '2020-12-27 19:05:36', 0, 0);
INSERT INTO `Notice` VALUES ('GG_20201227_bfb25b759bfbc073', '苏浩东', '秘书处（办公室）', '新通知', '<h1 style=\"text-align: center;\"><span style=\"color: #e03e2d;\"><strong>通知</strong></span></h1>', '2020-12-27 19:47:31', '2021-01-27 19:47:30', 1, 1);
INSERT INTO `Notice` VALUES ('GG_20201230_80c2b784fc515e61', '王五', '秘书处（办公室）', '通知', '<p>通知</p>', '2020-12-30 08:57:35', '2021-01-02 08:57:35', 1, 0);
INSERT INTO `Notice` VALUES ('GG_20201230_963fbe197221d484', '张三', '秘书处（办公室）', '通知', '<p>通知</p>\n<p><audio style=\"display: none;\" controls=\"controls\"></audio></p>', '2020-12-30 08:56:28', '2021-01-02 08:56:28', 1, 0);
INSERT INTO `Notice` VALUES ('GG_20210101_8e6b599cd0e0bfb7', '苏浩东', '秘书处（办公室）', '关于放假的通知', '<h1 style=\"text-align: center;\"><span style=\"color: #e03e2d;\"><strong>通知</strong></span></h1>\n<ol>\n<li>放假时间</li>\n<li>注意安全</li>\n</ol>', '2021-01-01 19:31:52', '2021-01-04 19:31:51', 1, 1);

-- ----------------------------
-- Table structure for Pending
-- ----------------------------
DROP TABLE IF EXISTS `Pending`;
CREATE TABLE `Pending`  (
  `user` int(11) NOT NULL,
  `submitTime` timestamp(0) NOT NULL,
  `topic` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `submitter` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Pending
-- ----------------------------

-- ----------------------------
-- Table structure for RFile
-- ----------------------------
DROP TABLE IF EXISTS `RFile`;
CREATE TABLE `RFile`  (
  `fileNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fileCaption` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `keyWord` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `affixCaption` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mainTo` int(11) NOT NULL,
  `CC` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `confidential` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `express` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fileContent` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `affixContent` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of RFile
-- ----------------------------
INSERT INTO `RFile` VALUES ('FF_20201223_89f2f365c152cd99', '文件3', '主题3', '有', 1, '省政府总值班处', '绝对机密', '限时送', '<p><strong>正文2</strong></p>', '', '7d1aba774a07466db8cefd1198501545');

-- ----------------------------
-- Table structure for RFileArchive
-- ----------------------------
DROP TABLE IF EXISTS `RFileArchive`;
CREATE TABLE `RFileArchive`  (
  `fileNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fileCaption` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `keyWord` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `affixCaption` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mainTo` int(11) NOT NULL,
  `CC` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `confidential` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `express` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fileContent` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `affixContent` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `archiveNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of RFileArchive
-- ----------------------------

-- ----------------------------
-- Table structure for SFile
-- ----------------------------
DROP TABLE IF EXISTS `SFile`;
CREATE TABLE `SFile`  (
  `draftNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `draftDate` timestamp(0) NOT NULL,
  `fileCaption` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `affixCaption` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mainTo` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `closeingDate` timestamp(0) NOT NULL,
  `keyWord` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `draftsMan` int(11) NOT NULL,
  `confidential` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `express` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fileNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `copies` int(11) NOT NULL,
  `CC` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `auditor` int(11) NOT NULL,
  `auditing` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `checkingMan` int(11) NOT NULL,
  `checking` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `signator` int(11) NOT NULL,
  `signing` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `distributor` int(11) NOT NULL,
  `distributing` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fileContent` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `affixContent` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `send` int(11) NOT NULL,
  `state` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`draftNo`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of SFile
-- ----------------------------
INSERT INTO `SFile` VALUES ('CN_20201222_a7a0f0aca27a7906', '2020-12-22 22:04:47', 'aaa', 'aaaaa', '202001001', '2020-12-22 22:04:47', 'fhasufhasiudfhai', 202001001, '绝对机密', '限时送', 'FF_20201222_9353052af7d7fc1f', 1, '202001001', 202001002, NULL, 202001002, NULL, 202001001, NULL, 202001005, NULL, '', '', 2, '审核中');
INSERT INTO `SFile` VALUES ('CN_20201223_8ec67ec31db4ef86', '2020-12-23 12:48:01', '', '', '202001001', '2020-12-23 12:48:01', '', 202001001, '绝对机密', '限时送', 'FF_20201223_9971ae94a7d36f7a', 666, '202001001', 202001002, NULL, 202001002, NULL, 202001001, NULL, 202001005, NULL, '', '', 2, '审核中');
INSERT INTO `SFile` VALUES ('CN_20201223_a6a779dd70bd8f70', '2020-12-24 16:51:40', '88888', '88', '202001001', '2020-12-24 16:51:40', '7', 202001001, '绝对机密', '限时送', 'FF_20201223_9e9d9a3b3d988efd', 666, '202001001', 202001002, '', 202001003, '', 202003001, '', 202001007, NULL, '<p>744474</p>', '', 4, '签发中');
INSERT INTO `SFile` VALUES ('CN_20201223_a6e691b2aaa3180a', '2020-12-25 08:25:29', '文件3', '标题3', '202001001', '2020-12-25 08:25:29', '主题3', 202001001, '绝对机密', '限时送', 'FF_20201223_89f2f365c152cd99', 2, '202001001', 202001002, '通过', 202001002, '通过', 202001001, '通过', 202001005, NULL, '<p><strong>正文2</strong></p>', '', 5, '已存档');
INSERT INTO `SFile` VALUES ('CN_20201223_babd3b656621704d', '2020-12-23 12:55:29', 'ggggggggggggg', '444444', '202001001', '2020-12-23 12:55:29', '8888888888', 202001001, '绝对机密', '限时送', 'FF_20201223_bfa6e92171c6ca27', 444, '202001001', 202001002, NULL, 202001002, NULL, 202001001, NULL, 202001005, NULL, '<p>88888888888888888</p>', '', 2, '审核中');

-- ----------------------------
-- Table structure for SFileArchive
-- ----------------------------
DROP TABLE IF EXISTS `SFileArchive`;
CREATE TABLE `SFileArchive`  (
  `fileNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fileCaption` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `keyWord` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `mainTo` int(11) NOT NULL,
  `CC` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `confidential` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `express` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `closeingDate` timestamp(0) NULL DEFAULT NULL,
  `fileContent` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `affixContent` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `draftsMan` int(11) NOT NULL,
  `affixCaption` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `archiveNo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of SFileArchive
-- ----------------------------
INSERT INTO `SFileArchive` VALUES ('FF_20201223_89f2f365c152cd99', '文件3', '主题3', 202001001, '202001001', '绝对机密', '限时送', '2020-12-25 08:25:29', '<p><strong>正文2</strong></p>', '', 202001001, '标题3', 'GG_20201227_b8e36f96883206a8', '202091');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `departmentNo` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `departmentName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`departmentNo`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('01', '秘书处（办公室）');
INSERT INTO `department` VALUES ('02', '会议处');
INSERT INTO `department` VALUES ('03', '综合处');
INSERT INTO `department` VALUES ('04', '调研处');
INSERT INTO `department` VALUES ('05', '督察处');
INSERT INTO `department` VALUES ('06', '政策法规处');
INSERT INTO `department` VALUES ('07', '秘书一处');
INSERT INTO `department` VALUES ('08', '秘书二处');
INSERT INTO `department` VALUES ('09', '秘书三处');
INSERT INTO `department` VALUES ('10', '秘书四处');
INSERT INTO `department` VALUES ('11', '秘书五处');
INSERT INTO `department` VALUES ('12', '秘书六处');
INSERT INTO `department` VALUES ('13', '政务公开与信息化处');
INSERT INTO `department` VALUES ('14', '人事处');
INSERT INTO `department` VALUES ('15', '行政处');
INSERT INTO `department` VALUES ('16', '参事处');
INSERT INTO `department` VALUES ('17', '文史处');
INSERT INTO `department` VALUES ('18', '省政府总值班处');

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `series` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `last_used` timestamp(0) NOT NULL,
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `account` int(11) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `department` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `occupation` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `accessAuthority` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (202001001, '苏浩东', '秘书处（办公室）', '主任', 'administrator', '123', NULL);
INSERT INTO `tb_user` VALUES (202001002, '周义伟', '秘书处（办公室）', '副主任', 'subAdministrator', '123', NULL);
INSERT INTO `tb_user` VALUES (202001003, '张三', '秘书处（办公室）', '副主任', 'subAdministrator', '123', NULL);
INSERT INTO `tb_user` VALUES (202001004, '李四', '秘书处（办公室）', '副主任', 'subAdministrator', '123', NULL);
INSERT INTO `tb_user` VALUES (202001005, '王五', '秘书处（办公室）', '助理', 'normal', '123', NULL);
INSERT INTO `tb_user` VALUES (202001006, '赵六', '秘书处（办公室）', '助理', 'normal', '123', NULL);
INSERT INTO `tb_user` VALUES (202001007, '郑秀', '秘书处（办公室）', '助理', 'normal', '123', NULL);
INSERT INTO `tb_user` VALUES (202002001, '陈庚宇', '督察处', '保安队长', 'administrator', '123', NULL);
INSERT INTO `tb_user` VALUES (202002002, '周义伟', '保卫处', '保安', 'normal', '123', NULL);
INSERT INTO `tb_user` VALUES (202002003, '潘胜伟', '保卫处', '保安', 'normal', '123', NULL);
INSERT INTO `tb_user` VALUES (202002004, '万秋阳', '保卫处', '保安', 'normal', '123', NULL);
INSERT INTO `tb_user` VALUES (202003001, '刘清晨', '省政府总值班处', '主任', 'administrator', '123', NULL);

SET FOREIGN_KEY_CHECKS = 1;
