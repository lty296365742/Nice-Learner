/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : learner

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-04-12 12:46:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_aspect
-- ----------------------------
DROP TABLE IF EXISTS `tb_aspect`;
CREATE TABLE `tb_aspect` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `aspect` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_aspect
-- ----------------------------
INSERT INTO `tb_aspect` VALUES ('00000000001', '前端开发');
INSERT INTO `tb_aspect` VALUES ('00000000002', '后端开发');
INSERT INTO `tb_aspect` VALUES ('00000000003', '移动开发');
INSERT INTO `tb_aspect` VALUES ('00000000004', '数据库');
INSERT INTO `tb_aspect` VALUES ('00000000005', '人工智能');
INSERT INTO `tb_aspect` VALUES ('00000000006', '云计算&大数据');
INSERT INTO `tb_aspect` VALUES ('00000000007', '运维&测试');
INSERT INTO `tb_aspect` VALUES ('00000000008', 'UI设计');
INSERT INTO `tb_aspect` VALUES ('00000000009', '测试');
INSERT INTO `tb_aspect` VALUES ('00000000011', '测试爱的');

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `category` varchar(255) COLLATE utf8_bin NOT NULL,
  `aspectId` int(11) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `aspectId` (`aspectId`),
  CONSTRAINT `aspectId` FOREIGN KEY (`aspectId`) REFERENCES `tb_aspect` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES ('00000000001', 'HTML/CSS', '00000000001');
INSERT INTO `tb_category` VALUES ('00000000002', 'JavaScript', '00000000001');
INSERT INTO `tb_category` VALUES ('00000000003', 'Html5', '00000000001');
INSERT INTO `tb_category` VALUES ('00000000004', 'CSS3', '00000000001');
INSERT INTO `tb_category` VALUES ('00000000005', 'jQuery', '00000000001');
INSERT INTO `tb_category` VALUES ('00000000006', 'Node.js', '00000000001');
INSERT INTO `tb_category` VALUES ('00000000007', 'BootStrap', '00000000001');
INSERT INTO `tb_category` VALUES ('00000000008', 'Angular', '00000000001');
INSERT INTO `tb_category` VALUES ('00000000009', 'React.JS', '00000000001');
INSERT INTO `tb_category` VALUES ('00000000010', 'Vue.JS', '00000000001');
INSERT INTO `tb_category` VALUES ('00000000011', 'Sass/Less', '00000000001');
INSERT INTO `tb_category` VALUES ('00000000012', 'WebApp', '00000000001');
INSERT INTO `tb_category` VALUES ('00000000013', '前端工具', '00000000001');
INSERT INTO `tb_category` VALUES ('00000000014', 'PHP', '00000000002');
INSERT INTO `tb_category` VALUES ('00000000015', 'Java', '00000000002');
INSERT INTO `tb_category` VALUES ('00000000016', 'SpringBoot', '00000000002');
INSERT INTO `tb_category` VALUES ('00000000017', 'Python', '00000000002');
INSERT INTO `tb_category` VALUES ('00000000018', 'C', '00000000002');
INSERT INTO `tb_category` VALUES ('00000000019', 'C++', '00000000002');
INSERT INTO `tb_category` VALUES ('00000000020', 'Go', '00000000002');
INSERT INTO `tb_category` VALUES ('00000000021', 'C#', '00000000002');
INSERT INTO `tb_category` VALUES ('00000000022', 'Ruby', '00000000002');
INSERT INTO `tb_category` VALUES ('00000000023', 'Android', '00000000003');
INSERT INTO `tb_category` VALUES ('00000000024', 'IOS', '00000000003');
INSERT INTO `tb_category` VALUES ('00000000025', 'Unity3D', '00000000003');
INSERT INTO `tb_category` VALUES ('00000000026', 'Cocos2d-x', '00000000003');
INSERT INTO `tb_category` VALUES ('00000000027', 'MySql', '00000000004');
INSERT INTO `tb_category` VALUES ('00000000028', 'MongoDB', '00000000004');
INSERT INTO `tb_category` VALUES ('00000000029', 'Oracle', '00000000004');
INSERT INTO `tb_category` VALUES ('00000000030', 'SQL Server', '00000000004');
INSERT INTO `tb_category` VALUES ('00000000031', '机器学习', '00000000005');
INSERT INTO `tb_category` VALUES ('00000000032', '深度学习', '00000000005');
INSERT INTO `tb_category` VALUES ('00000000033', '大数据', '00000000006');
INSERT INTO `tb_category` VALUES ('00000000034', '云计算', '00000000006');
INSERT INTO `tb_category` VALUES ('00000000035', '测试', '00000000007');
INSERT INTO `tb_category` VALUES ('00000000036', 'Linux', '00000000007');
INSERT INTO `tb_category` VALUES ('00000000037', '动效动画', '00000000008');
INSERT INTO `tb_category` VALUES ('00000000038', 'APPUI设计', '00000000008');
INSERT INTO `tb_category` VALUES ('00000000039', '设计工具', '00000000008');
INSERT INTO `tb_category` VALUES ('00000000040', '设计基础', '00000000008');
INSERT INTO `tb_category` VALUES ('00000000042', '测试update', '00000000007');

-- ----------------------------
-- Table structure for tb_chapter
-- ----------------------------
DROP TABLE IF EXISTS `tb_chapter`;
CREATE TABLE `tb_chapter` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_bin NOT NULL,
  `video` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `courseId` int(11) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`) USING BTREE,
  KEY `courseId` (`courseId`),
  CONSTRAINT `courseId` FOREIGN KEY (`courseId`) REFERENCES `tb_course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_chapter
-- ----------------------------

-- ----------------------------
-- Table structure for tb_collect_history
-- ----------------------------
DROP TABLE IF EXISTS `tb_collect_history`;
CREATE TABLE `tb_collect_history` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `courseId` int(11) unsigned zerofill NOT NULL,
  `lastTime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `userId` int(11) unsigned zerofill NOT NULL,
  `ishistory` bit(1) NOT NULL,
  `iscollect` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `courseId_his` (`courseId`),
  KEY `userId_his` (`userId`),
  CONSTRAINT `courseId_his` FOREIGN KEY (`courseId`) REFERENCES `tb_course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userId_his` FOREIGN KEY (`userId`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_collect_history
-- ----------------------------

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `chapterId` int(11) unsigned zerofill NOT NULL,
  `createTime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `userId` int(11) unsigned zerofill NOT NULL,
  `content` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `chapterId` (`chapterId`),
  KEY `userId_com` (`userId`),
  CONSTRAINT `chapterId` FOREIGN KEY (`chapterId`) REFERENCES `tb_chapter` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userId_com` FOREIGN KEY (`userId`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_course
-- ----------------------------
DROP TABLE IF EXISTS `tb_course`;
CREATE TABLE `tb_course` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_bin NOT NULL,
  `degreeId` int(11) unsigned zerofill NOT NULL,
  `categoryId` int(11) unsigned zerofill NOT NULL,
  `createTime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `authorId` int(11) unsigned zerofill NOT NULL,
  `description` varchar(255) COLLATE utf8_bin NOT NULL,
  `lengthTime` varchar(255) COLLATE utf8_bin NOT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `degreeId` (`degreeId`),
  KEY `categoryId` (`categoryId`),
  KEY `authorId` (`authorId`),
  CONSTRAINT `authorId` FOREIGN KEY (`authorId`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `categoryId` FOREIGN KEY (`categoryId`) REFERENCES `tb_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `degreeId` FOREIGN KEY (`degreeId`) REFERENCES `tb_degree` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_course
-- ----------------------------

-- ----------------------------
-- Table structure for tb_degree
-- ----------------------------
DROP TABLE IF EXISTS `tb_degree`;
CREATE TABLE `tb_degree` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `degree` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_degree
-- ----------------------------
INSERT INTO `tb_degree` VALUES ('00000000001', '入门');
INSERT INTO `tb_degree` VALUES ('00000000002', '初级');
INSERT INTO `tb_degree` VALUES ('00000000003', '中级');
INSERT INTO `tb_degree` VALUES ('00000000004', '高级');

-- ----------------------------
-- Table structure for tb_hobby
-- ----------------------------
DROP TABLE IF EXISTS `tb_hobby`;
CREATE TABLE `tb_hobby` (
  `userId` int(11) unsigned zerofill NOT NULL,
  `categoryId` int(11) unsigned zerofill NOT NULL,
  UNIQUE KEY `categoryId_hob` (`categoryId`) USING BTREE,
  KEY `userId` (`userId`),
  CONSTRAINT `categoryId_hob` FOREIGN KEY (`categoryId`) REFERENCES `tb_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_hobby
-- ----------------------------

-- ----------------------------
-- Table structure for tb_notes
-- ----------------------------
DROP TABLE IF EXISTS `tb_notes`;
CREATE TABLE `tb_notes` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `userId` int(11) unsigned zerofill NOT NULL,
  `content` varchar(255) COLLATE utf8_bin NOT NULL,
  `createDate` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `userId_not` (`userId`),
  CONSTRAINT `userId_not` FOREIGN KEY (`userId`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_notes
-- ----------------------------

-- ----------------------------
-- Table structure for tb_reply
-- ----------------------------
DROP TABLE IF EXISTS `tb_reply`;
CREATE TABLE `tb_reply` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `chapterId` int(255) unsigned zerofill NOT NULL,
  `createTime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `userId` int(11) unsigned zerofill NOT NULL,
  `content` varchar(255) COLLATE utf8_bin NOT NULL,
  `commentId` int(11) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`),
  KEY `chapterId_rep` (`chapterId`),
  KEY `userId_rep` (`userId`),
  KEY `commentId` (`commentId`),
  CONSTRAINT `chapterId_rep` FOREIGN KEY (`chapterId`) REFERENCES `tb_chapter` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `commentId` FOREIGN KEY (`commentId`) REFERENCES `tb_comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userId_rep` FOREIGN KEY (`userId`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_reply
-- ----------------------------

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `role` varchar(255) COLLATE utf8_bin NOT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `state` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('00000000001', 'admin', '管理员', '');
INSERT INTO `tb_role` VALUES ('00000000002', 'assessor', '审核员', '');
INSERT INTO `tb_role` VALUES ('00000000003', 'teacher', '教师', '');
INSERT INTO `tb_role` VALUES ('00000000004', 'student', '学生', '');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `account` varchar(255) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `salt` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `createTime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `logo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `organization` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `roleId` int(11) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `roleId` FOREIGN KEY (`roleId`) REFERENCES `tb_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('00000000004', '123450', '1234560', '511039423@qq.com', '22d6e58b-b2a8-4a4a-a3d9-5922576dc0de', 'cf57fea94b800fcf5e937bceb9b97717', '2018-04-11 09:45:52', '123456', null, '\0', null, '00000000001');
INSERT INTO `tb_user` VALUES ('00000000005', '123451', '1234561', '51103942@qq.com', '60d28b67-f0ad-4820-bfd3-0685d04aee49', '290abc8916386bc4589acf66819f41bb', '2018-04-11 22:41:29', null, null, '\0', null, '00000000002');
SET FOREIGN_KEY_CHECKS=1;
