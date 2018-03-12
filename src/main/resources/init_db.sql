/*
Navicat MySQL Data Transfer

Source Server         : 172.16.0.15
Source Server Version : 50624
Source Host           : 172.16.0.15:3306
Source Database       : dev_center

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-05-22 20:59:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for D_AGENT_APPLY
-- ----------------------------
DROP TABLE IF EXISTS `D_AGENT_APPLY`;
CREATE TABLE `D_AGENT_APPLY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `COMPANY_NAME` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `CONTACT` varchar(255) DEFAULT NULL COMMENT '联系人',
  `CONTEXT` varchar(255) DEFAULT NULL COMMENT '内容',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `PHONE_NUMBER` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `STATUS` tinyint(4) DEFAULT '0' COMMENT '审核状态（0-审核中， 1-通过， 2-拒绝）',
  `EMAIL` varchar(255) DEFAULT NULL COMMENT '电子邮件',
  `COMMENT` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `USER_NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of D_AGENT_APPLY
-- ----------------------------
INSERT INTO `D_AGENT_APPLY` VALUES ('1', '微软公司', '吴先生', '<div><h1>dfdfdsf<h1></div>', '2017-05-15 14:50:15', '2017-05-22 15:29:26', '18859789999', '2', '704373316@qq.com', null, null, null);
INSERT INTO `D_AGENT_APPLY` VALUES ('2', '微软公司1', '周先生', '<div><h1>dfdfdsf<h1></div>', '2017-05-15 14:52:29', null, '1885971119', '0', 'fyd1988@gmail.com', null, null, null);
INSERT INTO `D_AGENT_APPLY` VALUES ('3', '微软公司1', '答先生', '<div><h1>dfdfdsf<h1></div>', '2017-05-15 14:53:31', null, '1885971119', '0', null, null, null, null);
INSERT INTO `D_AGENT_APPLY` VALUES ('4', '微软公司2', '无先生', '<div><h1>dfdfdsf<h1></div>', '2017-05-15 14:56:52', null, '1885971119', '0', null, null, null, null);
INSERT INTO `D_AGENT_APPLY` VALUES ('5', '微软公司2', '无先生', '<div><h1>dfdfdsf<h1></div>', '2017-05-15 14:57:27', null, '1885971119', '0', null, null, null, null);
INSERT INTO `D_AGENT_APPLY` VALUES ('6', '微软公司2', '杰克先生', '<div><h1>dfdfdsf<h1></div>', '2017-05-15 14:59:25', null, '1885971119', '0', null, null, null, null);
INSERT INTO `D_AGENT_APPLY` VALUES ('7', '微软公司2', '卡罗尔先生', '<div><h1>dfdfdsf<h1></div>', '2017-05-15 15:03:56', null, '1885971119', '0', null, null, null, null);
INSERT INTO `D_AGENT_APPLY` VALUES ('8', '微软公司2', '詹姆斯先生', '<div><h1>dfdfdsf<h1></div>', '2017-05-15 15:04:10', null, '1885971119', '0', null, null, null, null);
INSERT INTO `D_AGENT_APPLY` VALUES ('9', '微软公司2', '库里先生', '<div><h1>dfdfdsf<h1></div>', '2017-05-15 15:04:18', null, '1885971119', '0', null, null, null, null);
INSERT INTO `D_AGENT_APPLY` VALUES ('10', '微软公司2', '二货先生', '<div><h1>dfdfdsf<h1></div>', '2017-05-15 15:04:26', null, '1885971119', '0', null, null, null, null);
INSERT INTO `D_AGENT_APPLY` VALUES ('11', '微软公司2', '驴得水先生', '<div><h1>dfdfdsf<h1></div>', '2017-05-15 15:04:33', null, '1885971119', '0', null, null, null, null);
INSERT INTO `D_AGENT_APPLY` VALUES ('12', '星辉公司', '周星驰先生', '<div><h1>dfdfdsf<h1></div>', '2017-05-17 20:19:15', '2017-05-22 15:16:26', '18859789999', '2', null, null, null, null);
INSERT INTO `D_AGENT_APPLY` VALUES ('13', '暴雪', '法里奥斯', 'testAgent', '2017-05-18 09:24:41', '2017-05-18 09:25:33', '589977747', '2', null, null, null, null);
INSERT INTO `D_AGENT_APPLY` VALUES ('14', '暴雪1', '法里奥斯1', 'testAgent', '2017-05-18 09:25:17', '2017-05-22 15:15:59', '589977747', '1', '704373316@qq.com', null, null, null);
INSERT INTO `D_AGENT_APPLY` VALUES ('15', 'string', 'string', 'string', '2017-05-19 17:41:28', null, 'string', '0', 'string', null, 'string', 'string');
INSERT INTO `D_AGENT_APPLY` VALUES ('16', 'string', 'string', 'string', '2017-05-19 17:41:59', null, 'string', '0', '704373316@qq.com', null, 'fuyuda', '123456');
INSERT INTO `D_AGENT_APPLY` VALUES ('17', 'string', 'string', 'string', '2017-05-19 17:42:42', null, 'string', '0', '704373316@qq.com', null, 'fuyuda', '123456');
INSERT INTO `D_AGENT_APPLY` VALUES ('18', 'string', 'string', 'string', '2017-05-19 17:43:01', '2017-05-19 17:46:07', 'string', '2', '704373316@qq.com', null, 'fuyuda', '123456');
INSERT INTO `D_AGENT_APPLY` VALUES ('19', 'string', 'string', 'string', '2017-05-19 17:43:42', null, 'string', '0', '704373316@qq.com', null, 'fuyuda', '123456');
INSERT INTO `D_AGENT_APPLY` VALUES ('20', 'string', 'string', 'string', '2017-05-19 17:44:10', null, 'string', '0', '704373316@qq.com', null, 'fuyuda', '123456');
INSERT INTO `D_AGENT_APPLY` VALUES ('21', 'string', 'string', 'string', '2017-05-22 11:24:42', null, 'string', '0', 'string', null, 'string', 'string');
INSERT INTO `D_AGENT_APPLY` VALUES ('22', '特斯拉', 'Mr.Fu', '<dfdsfds>', '2017-05-22 15:06:19', '2017-05-22 15:10:46', '15866787', '1', '704373316@qq.com', null, 'fuyuda2017', '123456');
INSERT INTO `D_AGENT_APPLY` VALUES ('23', '中铁公司1', '中铁', 'aaaaaa', '2017-05-22 15:46:50', '2017-05-22 15:49:16', '1111111', '1', '704373316@qq.com', null, 'zhongtie', '123456');
INSERT INTO `D_AGENT_APPLY` VALUES ('24', '中铁公司2', '中铁2', 'aaaaaa', '2017-05-22 15:50:08', '2017-05-22 15:50:34', '1111111', '2', '704373316@qq.com', null, 'zhongtie2', '123456');
INSERT INTO `D_AGENT_APPLY` VALUES ('25', 'string', 'string', 'string', '2017-05-22 17:14:15', null, 'string', '0', 'string', null, 'string', 'string');
INSERT INTO `D_AGENT_APPLY` VALUES ('26', 'string', 'string', 'string', '2017-05-22 20:44:55', null, 'string', '0', 'string', null, 'string', 'string');
INSERT INTO `D_AGENT_APPLY` VALUES ('27', 'string', 'string', 'string', '2017-05-22 20:50:53', null, null, '0', 'string', null, 'a123456', '123456');

-- ----------------------------
-- Table structure for D_ISV_APPLY
-- ----------------------------
DROP TABLE IF EXISTS `D_ISV_APPLY`;
CREATE TABLE `D_ISV_APPLY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `COMPANY_NAME` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `CONTACT` varchar(255) DEFAULT NULL COMMENT '联系人',
  `PHONE_NUMBER` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `CONTEXT` text COMMENT '内容',
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `STATUS` tinyint(1) DEFAULT '0' COMMENT '审核状态（0-审核中， 1-通过， 2-拒绝）',
  `EMAIL` varchar(255) DEFAULT NULL COMMENT '电子邮件',
  `COMMENT` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `USER_NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of D_ISV_APPLY
-- ----------------------------
INSERT INTO `D_ISV_APPLY` VALUES ('1', '微软公司', '吴先生', '18859789999', '<div>aaaa</div>', '2017-05-09 14:41:38', null, '1', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('4', '微软公司1', '刘先生', '18859789999', '<div>aaaa</div>', '2017-05-09 14:45:26', null, '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('5', '微软公司2', '王先生', '18859789999', '<div>aaaa</div>', '2017-05-09 14:47:13', null, '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('6', '微软公司3', '李先生', '18859789999', '<div>aaaa</div>', '2017-05-09 14:41:38', '2017-05-09 14:51:41', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('7', '微软公司4', null, '18859789999', '<div>aaaa</div>', '2017-05-09 14:45:26', '2017-05-09 14:51:55', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('8', '微软公司5', null, '18859789999', '<div>aaaa</div>', '2017-05-09 14:47:13', '2017-05-09 14:51:58', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('9', '微软公司6', null, '18859789999', '<div>aaaa</div>', '2017-05-09 14:41:38', '2017-05-09 14:53:00', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('10', '微软公司7', null, '18859789999', '<div>aaaa</div>', '2017-05-09 14:45:26', '2017-05-09 14:52:46', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('11', '微软公司8', null, '18859789999', '<div>aaaa</div>', '2017-05-09 14:47:13', '2017-05-09 14:52:48', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('12', '微软公司9', null, '18859789999', '<div>aaaa</div>', '2017-05-09 14:41:38', '2017-05-09 14:52:50', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('13', '微软公司10', null, '18859789999', '<div>aaaa</div>', '2017-05-09 14:45:26', '2017-05-09 14:52:54', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('14', '微软公司11', null, '18859789999', '<div>aaaa</div>', '2017-05-09 14:47:13', '2017-05-09 14:52:57', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('19', '微软公司22', '周先生', '18610331757', '<div>请问您的公司总员工数是多少？</div>', '2017-05-15 14:34:43', '2017-05-15 14:34:43', '3', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('20', '微软公司6', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:41:38', '2017-05-09 14:53:00', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('21', '微软公司7', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:45:26', '2017-05-09 14:52:46', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('22', '微软公司8', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:47:13', '2017-05-09 14:52:48', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('23', '微软公司9', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:41:38', '2017-05-09 14:52:50', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('24', '微软公司10', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:45:26', '2017-05-09 14:52:54', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('25', '微软公司11', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:47:13', '2017-05-09 14:52:57', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('26', '微软公司22', '周先生', '18610331757', '<div>请问您的公司总员工数是多少？</div>', '2017-05-15 14:34:43', '2017-05-15 14:34:43', '3', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('27', '微软公司', '吴先生', '18859789999', '<div>aaaa</div>', '2017-05-09 14:41:38', '2017-05-15 14:36:54', '1', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('28', '微软公司1', '刘先生', '18859789999', '<div>aaaa</div>', '2017-05-09 14:45:26', '2017-05-15 14:36:57', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('29', '微软公司2', '王先生', '18859789999', '<div>aaaa</div>', '2017-05-09 14:47:13', '2017-05-15 14:36:59', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('30', '微软公司3', '李先生', '18859789999', '<div>aaaa</div>', '2017-05-09 14:41:38', '2017-05-09 14:51:41', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('31', '微软公司4', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:45:26', '2017-05-09 14:51:55', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('32', '微软公司5', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:47:13', '2017-05-09 14:51:58', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('33', '微软公司6', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:41:38', '2017-05-09 14:53:00', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('34', '微软公司7', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:45:26', '2017-05-09 14:52:46', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('35', '微软公司8', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:47:13', '2017-05-09 14:52:48', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('36', '微软公司9', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:41:38', '2017-05-09 14:52:50', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('37', '微软公司10', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:45:26', '2017-05-09 14:52:54', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('38', '微软公司11', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:47:13', '2017-05-09 14:52:57', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('39', '微软公司22', '周先生', '18610331757', '<div>请问您的公司总员工数是多少？</div>', '2017-05-15 14:34:43', '2017-05-15 14:34:43', '3', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('40', '微软公司6', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:41:38', '2017-05-09 14:53:00', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('41', '微软公司7', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:45:26', '2017-05-09 14:52:46', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('42', '微软公司8', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:47:13', '2017-05-09 14:52:48', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('43', '微软公司9', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:41:38', '2017-05-09 14:52:50', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('44', '微软公司10', '', '18859789999', '<div>aaaa</div>', '2017-05-09 14:45:26', '2017-05-09 14:52:54', '0', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('45', '微软公司11', '泥煤', '18859789999', '<div>aaaa</div>', '2017-05-09 14:47:13', '2017-05-18 11:41:58', '1', '704373316@qq.com', null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('46', '微软公司22', '周先生', '18610331757', '<div>请问您的公司总员工数是多少？</div>', '2017-05-15 14:34:43', '2017-05-15 14:34:43', '3', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('47', '傻愣愣', '人类', '999999', '的人日日日日日日钱钱钱', '2017-05-18 09:28:03', '2017-05-18 09:33:02', '2', null, null, null, null);
INSERT INTO `D_ISV_APPLY` VALUES ('48', 'string', 'string', 'string', 'string', '2017-05-19 17:51:37', '2017-05-19 17:52:15', '1', 'string', 'string', 'string', 'string');
INSERT INTO `D_ISV_APPLY` VALUES ('49', 'string', 'string', 'string', 'string', '2017-05-22 11:24:49', null, '0', 'string', 'string', 'string', 'string');
INSERT INTO `D_ISV_APPLY` VALUES ('50', '家乐福', 'james', '1111111', 'aaaaaa', '2017-05-22 15:32:16', '2017-05-22 15:34:02', '2', '704373316@qq.com', '', 'james', '123456');
INSERT INTO `D_ISV_APPLY` VALUES ('51', '家乐福', 'james123', '1111111', 'aaaaaa', '2017-05-22 15:34:46', '2017-05-22 15:35:20', '1', '704373316@qq.com', '', 'james_bill', '123456');
INSERT INTO `D_ISV_APPLY` VALUES ('52', '家乐福', 'james123', '1111111', 'aaaaaa', '2017-05-22 15:37:13', '2017-05-22 15:37:35', '1', '704373316@qq.com', '', 'james_123', '123456');
INSERT INTO `D_ISV_APPLY` VALUES ('53', '戴尔公司', '戴尔', '1111111', 'aaaaaa', '2017-05-22 15:41:07', '2017-05-22 15:43:05', '1', '704373316@qq.com', null, 'dell', '123456');
INSERT INTO `D_ISV_APPLY` VALUES ('54', '戴尔公司1', '戴尔', '1111111', 'aaaaaa', '2017-05-22 15:44:12', '2017-05-22 15:44:49', '2', '704373316@qq.com', null, 'dell1', '123456');
INSERT INTO `D_ISV_APPLY` VALUES ('55', 'string', 'string', 'string', 'string', '2017-05-22 16:38:54', null, '0', 'string', 'string', 'fasdfdsf', 'string');
INSERT INTO `D_ISV_APPLY` VALUES ('56', 'string', 'string', 'string', 'string', '2017-05-22 16:39:35', null, '0', 'string', 'string', '测试普通权限', 'string');
INSERT INTO `D_ISV_APPLY` VALUES ('57', 'string', 'string', 'string', 'string', '2017-05-22 17:14:07', null, '0', 'string', 'string', 'string', 'string');

-- ----------------------------
-- Table structure for D_MAIL
-- ----------------------------
DROP TABLE IF EXISTS `D_MAIL`;
CREATE TABLE `D_MAIL` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FROM_MAIL` varchar(255) DEFAULT NULL COMMENT '发件人',
  `TO_MAIL` varchar(255) DEFAULT NULL COMMENT '收件人',
  `CONTEXT` text COMMENT '内容',
  `SUBJECT` varchar(255) DEFAULT NULL COMMENT '主题',
  `SEND_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of D_MAIL
-- ----------------------------
INSERT INTO `D_MAIL` VALUES ('2', 'cloudmanager@mrobot.cn', '704373316@qq.com', '吴先生,你好，恭喜微软公司获得木爷代理商资格', '申请成功', '2017-05-18 10:15:22');
INSERT INTO `D_MAIL` VALUES ('3', 'cloudmanager@mrobot.cn', '704373316@qq.com', '吴先生,你好，恭喜微软公司获得木爷ISV资格', '申请成功', '2017-05-18 10:28:18');
INSERT INTO `D_MAIL` VALUES ('4', 'cloudmanager@mrobot.cn', '704373316@qq.com', '吴先生,你好，很抱歉微软公司未通过木爷代理商资格审核，原因:没晕阿银', '申请失败', '2017-05-18 11:21:16');
INSERT INTO `D_MAIL` VALUES ('5', 'cloudmanager@mrobot.cn', '704373316@qq.com', '泥煤,你好，恭喜微软公司11获得木爷ISV资格', '申请成功', '2017-05-18 11:39:55');
INSERT INTO `D_MAIL` VALUES ('6', 'cloudmanager@mrobot.cn', '704373316@qq.com', '泥煤,你好，很抱歉微软公司11未通过木爷ISV资格审核，原因:null', '申请失败', '2017-05-18 11:40:18');
INSERT INTO `D_MAIL` VALUES ('7', 'cloudmanager@mrobot.cn', '704373316@qq.com', '泥煤,你好，恭喜微软公司11获得木爷ISV资格', 'ISV资格申请成功', '2017-05-18 11:42:00');
INSERT INTO `D_MAIL` VALUES ('8', 'cloudmanager@mrobot.cn', '704373316@qq.com', '法里奥斯1,你好，恭喜暴雪1获得木爷代理商资格', '代理商资格申请成功', '2017-05-18 11:45:31');
INSERT INTO `D_MAIL` VALUES ('9', 'cloudmanager@mrobot.cn', '704373316@qq.com', '法里奥斯1,你好，很抱歉暴雪1未通过木爷代理商资格审核，原因:null', '代理商资格申请失败', '2017-05-18 11:46:27');
INSERT INTO `D_MAIL` VALUES ('10', 'cloudmanager@mrobot.cn', '704373316@qq.com', '法里奥斯1,你好，很抱歉暴雪1未通过木爷代理商资格审核，原因:不符合条件', '代理商资格申请失败', '2017-05-18 11:47:52');
INSERT INTO `D_MAIL` VALUES ('11', 'cloudmanager@mrobot.cn', '704373316@qq.com', '用户名: test2017/n密码:123456', '您的账号已创建', '2017-05-18 14:51:40');
INSERT INTO `D_MAIL` VALUES ('12', 'cloudmanager@mrobot.cn', '704373316@qq.com', '用户名: test2017/n密码:555', '您的账号已创建', '2017-05-18 14:54:45');
INSERT INTO `D_MAIL` VALUES ('13', 'cloudmanager@mrobot.cn', '704373316@qq.com', '用户名: fuyuda密码:123456', '您的账号已创建', '2017-05-19 17:06:11');
INSERT INTO `D_MAIL` VALUES ('14', 'cloudmanager@mrobot.cn', '704373316@qq.com', '用户名: abcdefg密码:123456', '您的账号已创建', '2017-05-19 17:11:31');
INSERT INTO `D_MAIL` VALUES ('15', 'cloudmanager@mrobot.cn', '704373316@qq.com', '用户名: abcdefg111密码:123456', '您的账号已创建', '2017-05-19 17:12:43');
INSERT INTO `D_MAIL` VALUES ('16', 'cloudmanager@mrobot.cn', '704373316@qq.com', '用户名: abcdefg11122密码:123456', '您的账号已创建', '2017-05-19 17:15:34');
INSERT INTO `D_MAIL` VALUES ('17', 'cloudmanager@mrobot.cn', '704373316@qq.com', 'string,你好，很抱歉string未通过木爷代理商资格审核，原因:错误', '代理商资格申请失败', '2017-05-19 17:46:09');
INSERT INTO `D_MAIL` VALUES ('18', 'cloudmanager@mrobot.cn', '704373316@qq.com', 'Mr.Fu,你好，恭喜特斯拉获得木爷代理商资格<br> 用户名:fuyuda2017密码:123456', '代理商资格申请成功', '2017-05-22 15:10:49');
INSERT INTO `D_MAIL` VALUES ('19', 'cloudmanager@mrobot.cn', '704373316@qq.com', '法里奥斯1,你好，恭喜暴雪1获得木爷代理商资格<br> 用户名:null密码:null', '代理商资格申请成功', '2017-05-22 15:16:01');
INSERT INTO `D_MAIL` VALUES ('20', 'cloudmanager@mrobot.cn', '704373316@qq.com', '吴先生,你好，很抱歉微软公司未通过木爷代理商资格审核，原因:不通过', '代理商资格申请失败', '2017-05-22 15:29:27');
INSERT INTO `D_MAIL` VALUES ('21', 'cloudmanager@mrobot.cn', '704373316@qq.com', 'james,你好，很抱歉家乐福未通过木爷ISV资格审核，原因:not passed', 'ISV资格申请失败', '2017-05-22 15:34:04');
INSERT INTO `D_MAIL` VALUES ('22', 'cloudmanager@mrobot.cn', '704373316@qq.com', 'james123,你好，恭喜家乐福获得木爷ISV资格', 'ISV资格申请成功', '2017-05-22 15:35:22');
INSERT INTO `D_MAIL` VALUES ('23', 'cloudmanager@mrobot.cn', '704373316@qq.com', 'james123,你好，恭喜家乐福获得木爷ISV资格', 'ISV资格申请成功', '2017-05-22 15:38:49');
INSERT INTO `D_MAIL` VALUES ('24', 'cloudmanager@mrobot.cn', '704373316@qq.com', '戴尔,你好，恭喜戴尔公司获得木爷ISV资格', 'ISV资格申请成功', '2017-05-22 15:43:12');
INSERT INTO `D_MAIL` VALUES ('25', 'cloudmanager@mrobot.cn', '704373316@qq.com', '戴尔,你好，很抱歉戴尔公司1未通过木爷ISV资格审核，原因:不通过', 'ISV资格申请失败', '2017-05-22 15:44:54');
INSERT INTO `D_MAIL` VALUES ('26', 'cloudmanager@mrobot.cn', '704373316@qq.com', '中铁,你好，恭喜中铁公司1获得木爷代理商资格<br> 用户名:zhongtie密码:123456', '代理商资格申请成功', '2017-05-22 15:49:18');
INSERT INTO `D_MAIL` VALUES ('27', 'cloudmanager@mrobot.cn', '704373316@qq.com', '中铁2,你好，很抱歉中铁公司2未通过木爷代理商资格审核，原因:不通过', '代理商资格申请失败', '2017-05-22 15:50:35');

-- ----------------------------
-- Table structure for D_MENU
-- ----------------------------
DROP TABLE IF EXISTS `D_MENU`;
CREATE TABLE `D_MENU` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` bigint(20) DEFAULT NULL COMMENT '父菜单ID',
  `NAME` varchar(255) DEFAULT NULL COMMENT '菜单名',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `IS_VALID` bit(1) DEFAULT b'1' COMMENT '是否有效',
  `VERSION_ID` bigint(20) NOT NULL COMMENT '版本ID',
  `LEVEL` int(11) DEFAULT NULL COMMENT '菜单等级',
  `ORIGIN_ID` bigint(20) DEFAULT NULL COMMENT '本ID（同ID）',
  `CONTENT` text COMMENT '内容',
  `URL` varchar(255) DEFAULT NULL COMMENT '链接地址',
  PRIMARY KEY (`ID`),
  KEY `VERSION_ID` (`VERSION_ID`),
  KEY `PARENT_ID` (`PARENT_ID`),
  CONSTRAINT `D_MENU_ibfk_1` FOREIGN KEY (`VERSION_ID`) REFERENCES `D_VERSION` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=630 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of D_MENU
-- ----------------------------
INSERT INTO `D_MENU` VALUES ('26', '33', '配置环境及发布', null, null, '', '1', '2', '26', 'adaafasdfsadf', null);
INSERT INTO `D_MENU` VALUES ('27', '33', '申请密钥', null, null, '', '1', '2', '27', 'sdffsdfsadfasdf', null);
INSERT INTO `D_MENU` VALUES ('28', '33', '简介', null, null, '', '1', '2', '28', null, null);
INSERT INTO `D_MENU` VALUES ('29', null, '相关下载', null, null, '', '1', '1', '29', null, null);
INSERT INTO `D_MENU` VALUES ('30', null, '常见问题', null, null, '', '1', '1', '30', null, null);
INSERT INTO `D_MENU` VALUES ('31', null, '更新日志', null, null, '', '1', '1', '31', null, null);
INSERT INTO `D_MENU` VALUES ('32', null, '类参考', null, null, '', '1', '1', '32', null, 'http://wiki.lbsyun.baidu.com/cms/androidsdk/doc/v4_3_0/index.html');
INSERT INTO `D_MENU` VALUES ('33', null, '开发指南', null, null, '', '1', '1', '33', null, null);
INSERT INTO `D_MENU` VALUES ('34', null, '获取密钥', null, null, '', '1', '1', '34', null, null);
INSERT INTO `D_MENU` VALUES ('35', null, '概述', '2017-04-28 00:00:00', null, '', '1', '1', '35', null, null);
INSERT INTO `D_MENU` VALUES ('265', '35', 'haha1', '2017-05-02 00:00:00', null, '', '25', null, '265', null, null);
INSERT INTO `D_MENU` VALUES ('266', '35', '第二层菜单AAA', '2017-05-02 00:00:00', null, '', '25', null, '266', null, null);
INSERT INTO `D_MENU` VALUES ('267', '35', '第二层菜单BBB', '2017-05-02 00:00:00', null, '', '25', null, '267', null, null);
INSERT INTO `D_MENU` VALUES ('268', '36', '范德萨', '2017-05-02 00:00:00', null, '', '25', null, '268', null, null);
INSERT INTO `D_MENU` VALUES ('269', '36', '嘎嘎', '2017-05-02 00:00:00', null, '', '25', null, '269', null, null);
INSERT INTO `D_MENU` VALUES ('270', '22', '嘎1嘎', '2017-05-02 00:00:00', null, '', '25', null, '270', null, null);
INSERT INTO `D_MENU` VALUES ('271', '21', '撒大大', '2017-05-02 00:00:00', null, '', '25', null, '271', null, null);
INSERT INTO `D_MENU` VALUES ('272', '35', '柔柔弱弱', '2017-05-02 00:00:00', null, '', '25', null, '272', null, null);
INSERT INTO `D_MENU` VALUES ('273', '35', '啊啊啊', '2017-05-02 00:00:00', null, '', '25', null, '273', null, null);
INSERT INTO `D_MENU` VALUES ('274', '35', '摩羯', '2017-05-02 00:00:00', null, '', '25', null, '274', null, null);
INSERT INTO `D_MENU` VALUES ('275', '35', '天平座', '2017-05-02 00:00:00', null, '', '25', null, '275', null, null);
INSERT INTO `D_MENU` VALUES ('276', '0', 'aaaaaa', '2017-05-02 00:00:00', '2017-05-04 00:00:00', '', '25', null, '276', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('277', '35', '水瓶座', '2017-05-02 00:00:00', null, '', '25', null, '277', null, null);
INSERT INTO `D_MENU` VALUES ('278', '35', '天蝎座', '2017-05-02 00:00:00', null, '', '25', null, '278', null, null);
INSERT INTO `D_MENU` VALUES ('279', null, '第一层菜单1', '2017-05-02 00:00:00', null, '', '25', null, '279', null, null);
INSERT INTO `D_MENU` VALUES ('280', '35', '第二层菜单2', '2017-05-02 00:00:00', null, '', '25', null, '280', null, null);
INSERT INTO `D_MENU` VALUES ('281', '36', '第三层菜单2', '2017-05-02 00:00:00', null, '', '25', null, '281', null, null);
INSERT INTO `D_MENU` VALUES ('282', '94', 'uululul', '2017-05-02 00:00:00', '2017-05-03 00:00:00', '', '25', null, '282', null, 'ululrulrul');
INSERT INTO `D_MENU` VALUES ('283', '94', '第四层菜单2', '2017-05-02 00:00:00', null, '\0', '25', null, '283', 'aaaaaaaaa', '');
INSERT INTO `D_MENU` VALUES ('303', '281', 'fdsafasd', '2017-05-03 00:00:00', '2017-05-03 00:00:00', '', '25', null, '303', '', 'www.qq.com');
INSERT INTO `D_MENU` VALUES ('304', '35', 'haha1', '2017-05-03 00:00:00', null, '', '30', null, '265', null, null);
INSERT INTO `D_MENU` VALUES ('305', '35', '第二层菜单AAA', '2017-05-03 00:00:00', null, '', '30', null, '266', null, null);
INSERT INTO `D_MENU` VALUES ('306', '35', '第二层菜单BBB', '2017-05-03 00:00:00', null, '', '30', null, '267', null, null);
INSERT INTO `D_MENU` VALUES ('307', '36', '范德萨', '2017-05-03 00:00:00', null, '', '30', null, '268', null, null);
INSERT INTO `D_MENU` VALUES ('308', '36', '嘎嘎', '2017-05-03 00:00:00', null, '', '30', null, '269', null, null);
INSERT INTO `D_MENU` VALUES ('309', '22', '嘎1嘎', '2017-05-03 00:00:00', null, '', '30', null, '270', null, null);
INSERT INTO `D_MENU` VALUES ('310', '21', '撒大大', '2017-05-03 00:00:00', null, '', '30', null, '271', null, null);
INSERT INTO `D_MENU` VALUES ('311', '35', '柔柔弱弱', '2017-05-03 00:00:00', null, '', '30', null, '272', null, null);
INSERT INTO `D_MENU` VALUES ('312', '35', '啊啊啊', '2017-05-03 00:00:00', null, '', '30', null, '273', null, null);
INSERT INTO `D_MENU` VALUES ('313', '35', '摩羯', '2017-05-03 00:00:00', null, '', '30', null, '274', null, null);
INSERT INTO `D_MENU` VALUES ('314', '35', '天平座', '2017-05-03 00:00:00', null, '', '30', null, '275', null, null);
INSERT INTO `D_MENU` VALUES ('315', '35', '白羊座', '2017-05-03 00:00:00', null, '', '30', null, '276', null, null);
INSERT INTO `D_MENU` VALUES ('316', '35', '水瓶座', '2017-05-03 00:00:00', null, '', '30', null, '277', null, null);
INSERT INTO `D_MENU` VALUES ('317', '35', '天蝎座', '2017-05-03 00:00:00', null, '', '30', null, '278', null, null);
INSERT INTO `D_MENU` VALUES ('318', null, '第一层菜单1', '2017-05-03 00:00:00', null, '', '30', null, '279', null, null);
INSERT INTO `D_MENU` VALUES ('319', '35', '第二层菜单2', '2017-05-03 00:00:00', null, '', '30', null, '280', null, null);
INSERT INTO `D_MENU` VALUES ('320', '36', '第三层菜单2', '2017-05-03 00:00:00', null, '', '30', null, '281', null, null);
INSERT INTO `D_MENU` VALUES ('321', '94', 'uululul', '2017-05-03 00:00:00', null, '', '30', null, '282', null, null);
INSERT INTO `D_MENU` VALUES ('322', '281', 'fdsafasd', '2017-05-03 00:00:00', null, '', '30', null, '303', null, null);
INSERT INTO `D_MENU` VALUES ('342', '35', 'haha1', '2017-05-05 00:00:00', null, '', '39', null, '20', null, null);
INSERT INTO `D_MENU` VALUES ('343', '35', '第二层菜单AAA', '2017-05-05 00:00:00', null, '', '39', null, '21', null, null);
INSERT INTO `D_MENU` VALUES ('344', '35', '第二层菜单BBB', '2017-05-05 00:00:00', null, '', '39', null, '22', null, null);
INSERT INTO `D_MENU` VALUES ('345', '36', '范德萨', '2017-05-05 00:00:00', null, '', '39', null, '24', null, null);
INSERT INTO `D_MENU` VALUES ('346', '36', '嘎嘎', '2017-05-05 00:00:00', null, '', '39', null, '25', null, null);
INSERT INTO `D_MENU` VALUES ('347', '22', '嘎1嘎', '2017-05-05 00:00:00', null, '', '39', null, '26', null, null);
INSERT INTO `D_MENU` VALUES ('348', '21', '撒大大', '2017-05-05 00:00:00', null, '', '39', null, '27', null, null);
INSERT INTO `D_MENU` VALUES ('349', '35', '柔柔弱弱', '2017-05-05 00:00:00', null, '', '39', null, '28', null, null);
INSERT INTO `D_MENU` VALUES ('350', '35', '啊啊啊', '2017-05-05 00:00:00', null, '', '39', null, '29', null, null);
INSERT INTO `D_MENU` VALUES ('351', '35', '摩羯', '2017-05-05 00:00:00', null, '', '39', null, '30', null, null);
INSERT INTO `D_MENU` VALUES ('352', '35', '天平座', '2017-05-05 00:00:00', null, '', '39', null, '31', null, null);
INSERT INTO `D_MENU` VALUES ('353', '35', '白羊座', '2017-05-05 00:00:00', null, '', '39', null, '32', null, null);
INSERT INTO `D_MENU` VALUES ('354', '35', '水瓶座', '2017-05-05 00:00:00', null, '', '39', null, '33', null, null);
INSERT INTO `D_MENU` VALUES ('355', '35', '天蝎座', '2017-05-05 00:00:00', null, '', '39', null, '34', null, null);
INSERT INTO `D_MENU` VALUES ('356', null, '第一层菜单1', '2017-05-05 00:00:00', null, '', '39', null, '35', null, null);
INSERT INTO `D_MENU` VALUES ('357', '35', '第二层菜单2', '2017-05-05 00:00:00', null, '', '39', null, '36', null, null);
INSERT INTO `D_MENU` VALUES ('358', '36', '第三层菜单2', '2017-05-05 00:00:00', null, '', '39', null, '94', '', 'www.baidu.com');
INSERT INTO `D_MENU` VALUES ('359', '35', 'haha1', '2017-05-05 00:00:00', null, '', '46', null, '20', null, null);
INSERT INTO `D_MENU` VALUES ('360', '35', '第二层菜单AAA', '2017-05-05 00:00:00', null, '', '46', null, '21', null, null);
INSERT INTO `D_MENU` VALUES ('361', '35', '第二层菜单BBB', '2017-05-05 00:00:00', null, '', '46', null, '22', null, null);
INSERT INTO `D_MENU` VALUES ('362', '36', '范德萨', '2017-05-05 00:00:00', null, '', '46', null, '24', null, null);
INSERT INTO `D_MENU` VALUES ('363', '36', '嘎嘎', '2017-05-05 00:00:00', null, '', '46', null, '25', null, null);
INSERT INTO `D_MENU` VALUES ('364', '22', '嘎1嘎', '2017-05-05 00:00:00', null, '', '46', null, '26', null, null);
INSERT INTO `D_MENU` VALUES ('365', '21', '撒大大', '2017-05-05 00:00:00', null, '', '46', null, '27', null, null);
INSERT INTO `D_MENU` VALUES ('366', '35', '柔柔弱弱', '2017-05-05 00:00:00', null, '', '46', null, '28', null, null);
INSERT INTO `D_MENU` VALUES ('367', '35', '啊啊啊', '2017-05-05 00:00:00', null, '', '46', null, '29', null, null);
INSERT INTO `D_MENU` VALUES ('368', '35', '摩羯', '2017-05-05 00:00:00', null, '', '46', null, '30', null, null);
INSERT INTO `D_MENU` VALUES ('369', '35', '天平座', '2017-05-05 00:00:00', null, '', '46', null, '31', null, null);
INSERT INTO `D_MENU` VALUES ('370', '35', '白羊座', '2017-05-05 00:00:00', null, '', '46', null, '32', null, null);
INSERT INTO `D_MENU` VALUES ('371', '35', '水瓶座', '2017-05-05 00:00:00', null, '', '46', null, '33', null, null);
INSERT INTO `D_MENU` VALUES ('372', '35', '天蝎座', '2017-05-05 00:00:00', null, '', '46', null, '34', null, null);
INSERT INTO `D_MENU` VALUES ('373', null, '第一层菜单1', '2017-05-05 00:00:00', null, '', '46', null, '35', null, null);
INSERT INTO `D_MENU` VALUES ('374', '35', '第二层菜单2', '2017-05-05 00:00:00', null, '', '46', null, '36', null, null);
INSERT INTO `D_MENU` VALUES ('375', '36', '第三层菜单2', '2017-05-05 00:00:00', null, '', '46', null, '94', '', 'www.baidu.com');
INSERT INTO `D_MENU` VALUES ('376', '35', 'haha1', '2017-05-05 00:00:00', null, '', '52', null, '20', null, null);
INSERT INTO `D_MENU` VALUES ('377', '35', '第二层菜单AAA', '2017-05-05 00:00:00', null, '', '52', null, '21', null, null);
INSERT INTO `D_MENU` VALUES ('378', '35', '第二层菜单BBB', '2017-05-05 00:00:00', null, '', '52', null, '22', null, null);
INSERT INTO `D_MENU` VALUES ('379', '36', '范德萨', '2017-05-05 00:00:00', null, '', '52', null, '24', null, null);
INSERT INTO `D_MENU` VALUES ('380', '36', '嘎嘎', '2017-05-05 00:00:00', null, '', '52', null, '25', null, null);
INSERT INTO `D_MENU` VALUES ('381', '22', '嘎1嘎', '2017-05-05 00:00:00', null, '', '52', null, '26', null, null);
INSERT INTO `D_MENU` VALUES ('382', '21', '撒大大', '2017-05-05 00:00:00', null, '', '52', null, '27', null, null);
INSERT INTO `D_MENU` VALUES ('383', '35', '柔柔弱弱', '2017-05-05 00:00:00', null, '', '52', null, '28', null, null);
INSERT INTO `D_MENU` VALUES ('384', '35', '啊啊啊', '2017-05-05 00:00:00', null, '', '52', null, '29', null, null);
INSERT INTO `D_MENU` VALUES ('385', '35', '摩羯', '2017-05-05 00:00:00', null, '', '52', null, '30', null, null);
INSERT INTO `D_MENU` VALUES ('386', '35', '天平座', '2017-05-05 00:00:00', null, '', '52', null, '31', null, null);
INSERT INTO `D_MENU` VALUES ('387', '35', '白羊座', '2017-05-05 00:00:00', null, '', '52', null, '32', null, null);
INSERT INTO `D_MENU` VALUES ('388', '35', '水瓶座', '2017-05-05 00:00:00', null, '', '52', null, '33', null, null);
INSERT INTO `D_MENU` VALUES ('389', '35', '天蝎座', '2017-05-05 00:00:00', null, '', '52', null, '34', null, null);
INSERT INTO `D_MENU` VALUES ('390', null, '第一层菜单1', '2017-05-05 00:00:00', null, '', '52', null, '35', null, null);
INSERT INTO `D_MENU` VALUES ('391', '35', '第二层菜单2', '2017-05-05 00:00:00', null, '', '52', null, '36', null, null);
INSERT INTO `D_MENU` VALUES ('392', '36', '第三层菜单2', '2017-05-05 00:00:00', null, '', '52', null, '94', '', 'www.baidu.com');
INSERT INTO `D_MENU` VALUES ('399', '0', 'string', '2017-05-08 16:56:16', null, '', '65', null, '393', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('400', '0', 'string', '2017-05-08 16:56:16', null, '', '65', null, '394', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('401', '0', 'string', '2017-05-08 16:56:16', null, '', '65', null, '395', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('402', '0', 'string', '2017-05-08 16:56:16', null, '', '65', null, '396', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('403', '0', 'string', '2017-05-08 16:56:16', null, '', '65', null, '397', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('404', '36', '第三层菜单2', '2017-05-08 16:56:16', null, '', '65', null, '94', '', 'www.baidu.com');
INSERT INTO `D_MENU` VALUES ('405', null, '第一层菜单1', '2017-05-08 16:56:16', null, '', '65', null, '35', null, null);
INSERT INTO `D_MENU` VALUES ('406', '35', '第二层菜单2', '2017-05-08 16:56:16', null, '', '65', null, '36', null, null);
INSERT INTO `D_MENU` VALUES ('407', '35', 'haha1', '2017-05-08 16:56:16', null, '', '65', null, '20', null, null);
INSERT INTO `D_MENU` VALUES ('408', '35', '第二层菜单AAA', '2017-05-08 16:56:16', null, '', '65', null, '21', null, null);
INSERT INTO `D_MENU` VALUES ('409', '35', '第二层菜单BBB', '2017-05-08 16:56:16', null, '', '65', null, '22', null, null);
INSERT INTO `D_MENU` VALUES ('410', '36', '范德萨', '2017-05-08 16:56:16', null, '', '65', null, '24', null, null);
INSERT INTO `D_MENU` VALUES ('411', '36', '嘎嘎', '2017-05-08 16:56:16', null, '', '65', null, '25', null, null);
INSERT INTO `D_MENU` VALUES ('412', '22', '嘎1嘎', '2017-05-08 16:56:16', null, '', '65', null, '26', null, null);
INSERT INTO `D_MENU` VALUES ('413', '21', '撒大大', '2017-05-08 16:56:16', null, '', '65', null, '27', null, null);
INSERT INTO `D_MENU` VALUES ('414', '35', '柔柔弱弱', '2017-05-08 16:56:17', null, '', '65', null, '28', null, null);
INSERT INTO `D_MENU` VALUES ('415', '35', '啊啊啊', '2017-05-08 16:56:17', null, '', '65', null, '29', null, null);
INSERT INTO `D_MENU` VALUES ('416', '35', '摩羯', '2017-05-08 16:56:17', null, '', '65', null, '30', null, null);
INSERT INTO `D_MENU` VALUES ('417', '35', '天平座', '2017-05-08 16:56:17', null, '', '65', null, '31', null, null);
INSERT INTO `D_MENU` VALUES ('418', '35', '白羊座', '2017-05-08 16:56:17', null, '', '65', null, '32', null, null);
INSERT INTO `D_MENU` VALUES ('419', '35', '水瓶座', '2017-05-08 16:56:17', null, '', '65', null, '33', null, null);
INSERT INTO `D_MENU` VALUES ('420', '35', '天蝎座', '2017-05-08 16:56:17', null, '', '65', null, '34', null, null);
INSERT INTO `D_MENU` VALUES ('421', '0', 'string', '2017-05-08 16:56:52', null, '', '66', null, '393', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('422', '0', 'string', '2017-05-08 16:56:52', null, '', '66', null, '394', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('423', '0', 'string', '2017-05-08 16:56:52', null, '', '66', null, '395', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('424', '0', 'string', '2017-05-08 16:56:52', null, '', '66', null, '396', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('425', '0', 'string', '2017-05-08 16:56:52', null, '', '66', null, '397', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('426', '36', '第三层菜单2', '2017-05-08 16:56:52', null, '', '66', null, '94', '', 'www.baidu.com');
INSERT INTO `D_MENU` VALUES ('427', null, '第一层菜单1', '2017-05-08 16:56:52', null, '', '66', null, '35', null, null);
INSERT INTO `D_MENU` VALUES ('428', '35', '第二层菜单2', '2017-05-08 16:56:52', null, '', '66', null, '36', null, null);
INSERT INTO `D_MENU` VALUES ('429', '35', 'haha1', '2017-05-08 16:56:52', null, '', '66', null, '20', null, null);
INSERT INTO `D_MENU` VALUES ('430', '35', '第二层菜单AAA', '2017-05-08 16:56:52', null, '', '66', null, '21', null, null);
INSERT INTO `D_MENU` VALUES ('431', '35', '第二层菜单BBB', '2017-05-08 16:56:52', null, '', '66', null, '22', null, null);
INSERT INTO `D_MENU` VALUES ('432', '36', '范德萨', '2017-05-08 16:56:52', null, '', '66', null, '24', null, null);
INSERT INTO `D_MENU` VALUES ('433', '36', '嘎嘎', '2017-05-08 16:56:52', null, '', '66', null, '25', null, null);
INSERT INTO `D_MENU` VALUES ('434', '22', '嘎1嘎', '2017-05-08 16:56:52', null, '', '66', null, '26', null, null);
INSERT INTO `D_MENU` VALUES ('435', '21', '撒大大', '2017-05-08 16:56:52', null, '', '66', null, '27', null, null);
INSERT INTO `D_MENU` VALUES ('436', '35', '柔柔弱弱', '2017-05-08 16:56:52', null, '', '66', null, '28', null, null);
INSERT INTO `D_MENU` VALUES ('437', '35', '啊啊啊', '2017-05-08 16:56:52', null, '', '66', null, '29', null, null);
INSERT INTO `D_MENU` VALUES ('438', '35', '摩羯', '2017-05-08 16:56:52', null, '', '66', null, '30', null, null);
INSERT INTO `D_MENU` VALUES ('439', '35', '天平座', '2017-05-08 16:56:52', null, '', '66', null, '31', null, null);
INSERT INTO `D_MENU` VALUES ('440', '35', '白羊座', '2017-05-08 16:56:52', null, '', '66', null, '32', null, null);
INSERT INTO `D_MENU` VALUES ('441', '35', '水瓶座', '2017-05-08 16:56:52', null, '', '66', null, '33', null, null);
INSERT INTO `D_MENU` VALUES ('442', '35', '天蝎座', '2017-05-08 16:56:52', null, '', '66', null, '34', null, null);
INSERT INTO `D_MENU` VALUES ('443', '0', 'string', '2017-05-08 16:58:28', null, '', '67', null, '393', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('444', '0', 'string', '2017-05-08 16:58:28', null, '', '67', null, '394', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('445', '0', 'string', '2017-05-08 16:58:28', null, '', '67', null, '395', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('446', '0', 'string', '2017-05-08 16:58:28', null, '', '67', null, '396', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('447', '0', 'string', '2017-05-08 16:58:28', null, '', '67', null, '397', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('448', '36', '第三层菜单2', '2017-05-08 16:58:29', null, '', '67', null, '94', '', 'www.baidu.com');
INSERT INTO `D_MENU` VALUES ('449', null, '第一层菜单1', '2017-05-08 16:58:29', null, '', '67', null, '35', null, null);
INSERT INTO `D_MENU` VALUES ('450', '35', '第二层菜单2', '2017-05-08 16:58:29', null, '', '67', null, '36', null, null);
INSERT INTO `D_MENU` VALUES ('451', '35', 'haha1', '2017-05-08 16:58:29', null, '', '67', null, '20', null, null);
INSERT INTO `D_MENU` VALUES ('452', '35', '第二层菜单AAA', '2017-05-08 16:58:29', null, '', '67', null, '21', null, null);
INSERT INTO `D_MENU` VALUES ('453', '35', '第二层菜单BBB', '2017-05-08 16:58:29', null, '', '67', null, '22', null, null);
INSERT INTO `D_MENU` VALUES ('454', '36', '范德萨', '2017-05-08 16:58:29', null, '', '67', null, '24', null, null);
INSERT INTO `D_MENU` VALUES ('455', '36', '嘎嘎', '2017-05-08 16:58:29', null, '', '67', null, '25', null, null);
INSERT INTO `D_MENU` VALUES ('456', '22', '嘎1嘎', '2017-05-08 16:58:29', null, '', '67', null, '26', null, null);
INSERT INTO `D_MENU` VALUES ('457', '21', '撒大大', '2017-05-08 16:58:29', null, '', '67', null, '27', null, null);
INSERT INTO `D_MENU` VALUES ('458', '35', '柔柔弱弱', '2017-05-08 16:58:29', null, '', '67', null, '28', null, null);
INSERT INTO `D_MENU` VALUES ('459', '35', '啊啊啊', '2017-05-08 16:58:29', null, '', '67', null, '29', null, null);
INSERT INTO `D_MENU` VALUES ('460', '35', '摩羯', '2017-05-08 16:58:29', null, '', '67', null, '30', null, null);
INSERT INTO `D_MENU` VALUES ('461', '35', '天平座', '2017-05-08 16:58:29', null, '', '67', null, '31', null, null);
INSERT INTO `D_MENU` VALUES ('462', '35', '白羊座', '2017-05-08 16:58:29', null, '', '67', null, '32', null, null);
INSERT INTO `D_MENU` VALUES ('463', '35', '水瓶座', '2017-05-08 16:58:29', null, '', '67', null, '33', null, null);
INSERT INTO `D_MENU` VALUES ('464', '35', '天蝎座', '2017-05-08 16:58:29', null, '', '67', null, '34', null, null);
INSERT INTO `D_MENU` VALUES ('465', '0', 'string', '2017-05-08 17:03:43', null, '', '69', null, '393', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('466', '0', 'string', '2017-05-08 17:03:43', null, '', '69', null, '394', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('467', '0', 'string', '2017-05-08 17:03:43', null, '', '69', null, '395', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('468', '0', 'string', '2017-05-08 17:03:43', null, '', '69', null, '396', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('469', '0', 'string', '2017-05-08 17:03:43', null, '', '69', null, '397', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('470', '36', '第三层菜单2', '2017-05-08 17:03:43', null, '', '69', null, '94', '', 'www.baidu.com');
INSERT INTO `D_MENU` VALUES ('471', null, '第一层菜单1', '2017-05-08 17:03:43', null, '', '69', null, '35', null, null);
INSERT INTO `D_MENU` VALUES ('472', '35', '第二层菜单2', '2017-05-08 17:03:43', null, '', '69', null, '36', null, null);
INSERT INTO `D_MENU` VALUES ('473', '35', 'haha1', '2017-05-08 17:03:43', null, '', '69', null, '20', null, null);
INSERT INTO `D_MENU` VALUES ('474', '35', '第二层菜单AAA', '2017-05-08 17:03:43', null, '', '69', null, '21', null, null);
INSERT INTO `D_MENU` VALUES ('475', '35', '第二层菜单BBB', '2017-05-08 17:03:43', null, '', '69', null, '22', null, null);
INSERT INTO `D_MENU` VALUES ('476', '36', '范德萨', '2017-05-08 17:03:43', null, '', '69', null, '24', null, null);
INSERT INTO `D_MENU` VALUES ('477', '36', '嘎嘎', '2017-05-08 17:03:43', null, '', '69', null, '25', null, null);
INSERT INTO `D_MENU` VALUES ('478', '22', '嘎1嘎', '2017-05-08 17:03:43', null, '', '69', null, '26', null, null);
INSERT INTO `D_MENU` VALUES ('479', '21', '撒大大', '2017-05-08 17:03:43', null, '', '69', null, '27', null, null);
INSERT INTO `D_MENU` VALUES ('480', '35', '柔柔弱弱', '2017-05-08 17:03:43', null, '', '69', null, '28', null, null);
INSERT INTO `D_MENU` VALUES ('481', '35', '啊啊啊', '2017-05-08 17:03:43', null, '', '69', null, '29', null, null);
INSERT INTO `D_MENU` VALUES ('482', '35', '摩羯', '2017-05-08 17:03:43', null, '', '69', null, '30', null, null);
INSERT INTO `D_MENU` VALUES ('483', '35', '天平座', '2017-05-08 17:03:43', null, '', '69', null, '31', null, null);
INSERT INTO `D_MENU` VALUES ('484', '35', '白羊座', '2017-05-08 17:03:43', null, '', '69', null, '32', null, null);
INSERT INTO `D_MENU` VALUES ('485', '35', '水瓶座', '2017-05-08 17:03:43', null, '', '69', null, '33', null, null);
INSERT INTO `D_MENU` VALUES ('486', '35', '天蝎座', '2017-05-08 17:03:43', null, '', '69', null, '34', null, null);
INSERT INTO `D_MENU` VALUES ('487', '35', '柔柔弱弱', '2017-05-08 17:38:18', null, '', '75', null, '28', null, null);
INSERT INTO `D_MENU` VALUES ('488', '35', '啊啊啊', '2017-05-08 17:38:18', null, '', '75', null, '29', null, null);
INSERT INTO `D_MENU` VALUES ('489', '35', '摩羯', '2017-05-08 17:38:18', null, '', '75', null, '30', null, null);
INSERT INTO `D_MENU` VALUES ('490', '35', '天平座', '2017-05-08 17:38:18', null, '', '75', null, '31', null, null);
INSERT INTO `D_MENU` VALUES ('491', '35', '白羊座', '2017-05-08 17:38:18', null, '', '75', null, '32', null, null);
INSERT INTO `D_MENU` VALUES ('492', '35', '水瓶座', '2017-05-08 17:38:18', null, '', '75', null, '33', null, null);
INSERT INTO `D_MENU` VALUES ('493', '35', '天蝎座', '2017-05-08 17:38:18', null, '', '75', null, '34', null, null);
INSERT INTO `D_MENU` VALUES ('494', '0', 'string', '2017-05-08 17:38:18', null, '', '75', null, '393', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('495', '0', 'string', '2017-05-08 17:38:18', null, '', '75', null, '394', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('496', '0', 'string', '2017-05-08 17:38:18', null, '', '75', null, '395', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('497', '0', 'string', '2017-05-08 17:38:18', null, '', '75', null, '396', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('498', '0', 'string', '2017-05-08 17:38:18', null, '', '75', null, '397', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('499', '36', '第三层菜单2', '2017-05-08 17:38:18', null, '', '75', null, '94', '', 'www.baidu.com');
INSERT INTO `D_MENU` VALUES ('500', null, '第一层菜单1', '2017-05-08 17:38:18', null, '', '75', null, '35', null, null);
INSERT INTO `D_MENU` VALUES ('501', '35', '第二层菜单2', '2017-05-08 17:38:18', null, '', '75', null, '36', null, null);
INSERT INTO `D_MENU` VALUES ('502', '35', 'haha1', '2017-05-08 17:38:18', null, '', '75', null, '20', null, null);
INSERT INTO `D_MENU` VALUES ('503', '35', '第二层菜单AAA', '2017-05-08 17:38:18', null, '', '75', null, '21', null, null);
INSERT INTO `D_MENU` VALUES ('504', '35', '第二层菜单BBB', '2017-05-08 17:38:18', null, '', '75', null, '22', null, null);
INSERT INTO `D_MENU` VALUES ('505', '36', '范德萨', '2017-05-08 17:38:18', null, '', '75', null, '24', null, null);
INSERT INTO `D_MENU` VALUES ('506', '36', '嘎嘎', '2017-05-08 17:38:18', null, '', '75', null, '25', null, null);
INSERT INTO `D_MENU` VALUES ('507', '22', '嘎1嘎', '2017-05-08 17:38:18', null, '', '75', null, '26', null, null);
INSERT INTO `D_MENU` VALUES ('508', '21', '撒大大', '2017-05-08 17:38:18', null, '', '75', null, '27', null, null);
INSERT INTO `D_MENU` VALUES ('509', '35', '柔柔弱弱', '2017-05-08 17:40:19', null, '', '76', null, '28', null, null);
INSERT INTO `D_MENU` VALUES ('510', '35', '啊啊啊', '2017-05-08 17:40:19', null, '', '76', null, '29', null, null);
INSERT INTO `D_MENU` VALUES ('511', '35', '摩羯', '2017-05-08 17:40:19', null, '', '76', null, '30', null, null);
INSERT INTO `D_MENU` VALUES ('512', '35', '天平座', '2017-05-08 17:40:19', null, '', '76', null, '31', null, null);
INSERT INTO `D_MENU` VALUES ('513', '35', '白羊座', '2017-05-08 17:40:19', null, '', '76', null, '32', null, null);
INSERT INTO `D_MENU` VALUES ('514', '35', '水瓶座', '2017-05-08 17:40:19', null, '', '76', null, '33', null, null);
INSERT INTO `D_MENU` VALUES ('515', '35', '天蝎座', '2017-05-08 17:40:19', null, '', '76', null, '34', null, null);
INSERT INTO `D_MENU` VALUES ('516', '0', 'string', '2017-05-08 17:40:19', null, '', '76', null, '393', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('517', '0', 'string', '2017-05-08 17:40:19', null, '', '76', null, '394', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('518', '0', 'string', '2017-05-08 17:40:19', null, '', '76', null, '395', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('519', '0', 'string', '2017-05-08 17:40:19', null, '', '76', null, '396', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('520', '0', 'string', '2017-05-08 17:40:19', null, '', '76', null, '397', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('521', '36', '第三层菜单2', '2017-05-08 17:40:19', null, '', '76', null, '94', '', 'www.baidu.com');
INSERT INTO `D_MENU` VALUES ('522', null, '第一层菜单1', '2017-05-08 17:40:19', null, '', '76', null, '35', null, null);
INSERT INTO `D_MENU` VALUES ('523', '35', '第二层菜单2', '2017-05-08 17:40:19', null, '', '76', null, '36', null, null);
INSERT INTO `D_MENU` VALUES ('524', '35', 'haha1', '2017-05-08 17:40:19', null, '', '76', null, '20', null, null);
INSERT INTO `D_MENU` VALUES ('525', '35', '第二层菜单AAA', '2017-05-08 17:40:19', null, '', '76', null, '21', null, null);
INSERT INTO `D_MENU` VALUES ('526', '35', '第二层菜单BBB', '2017-05-08 17:40:19', null, '', '76', null, '22', null, null);
INSERT INTO `D_MENU` VALUES ('527', '36', '范德萨', '2017-05-08 17:40:19', null, '', '76', null, '24', null, null);
INSERT INTO `D_MENU` VALUES ('528', '36', '嘎嘎', '2017-05-08 17:40:19', null, '', '76', null, '25', null, null);
INSERT INTO `D_MENU` VALUES ('529', '22', '嘎1嘎', '2017-05-08 17:40:19', null, '', '76', null, '26', null, null);
INSERT INTO `D_MENU` VALUES ('530', '21', '撒大大', '2017-05-08 17:40:19', null, '', '76', null, '27', null, null);
INSERT INTO `D_MENU` VALUES ('531', '35', '天平座', '2017-05-08 17:43:38', null, '', '77', null, '31', null, null);
INSERT INTO `D_MENU` VALUES ('532', '35', '白羊座', '2017-05-08 17:43:39', null, '', '77', null, '32', null, null);
INSERT INTO `D_MENU` VALUES ('533', '35', '水瓶座', '2017-05-08 17:43:39', null, '', '77', null, '33', null, null);
INSERT INTO `D_MENU` VALUES ('534', '35', '天蝎座', '2017-05-08 17:43:39', null, '', '77', null, '34', null, null);
INSERT INTO `D_MENU` VALUES ('535', '0', 'string', '2017-05-08 17:43:39', null, '', '77', null, '393', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('536', '0', 'string', '2017-05-08 17:43:39', null, '', '77', null, '394', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('537', '0', 'string', '2017-05-08 17:43:39', null, '', '77', null, '395', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('538', '0', 'string', '2017-05-08 17:43:39', null, '', '77', null, '396', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('539', '0', 'string', '2017-05-08 17:43:39', null, '', '77', null, '397', 'string', 'string');
INSERT INTO `D_MENU` VALUES ('540', '36', '第三层菜单2', '2017-05-08 17:43:39', null, '', '77', null, '94', '', 'www.baidu.com');
INSERT INTO `D_MENU` VALUES ('541', null, '第一层菜单1', '2017-05-08 17:43:39', null, '', '77', null, '35', null, null);
INSERT INTO `D_MENU` VALUES ('542', '35', '第二层菜单2', '2017-05-08 17:43:39', null, '', '77', null, '36', null, null);
INSERT INTO `D_MENU` VALUES ('543', '35', 'haha1', '2017-05-08 17:43:39', null, '', '77', null, '20', null, null);
INSERT INTO `D_MENU` VALUES ('544', '35', '第二层菜单AAA', '2017-05-08 17:43:39', null, '', '77', null, '21', null, null);
INSERT INTO `D_MENU` VALUES ('545', '35', '第二层菜单BBB', '2017-05-08 17:43:39', null, '', '77', null, '22', null, null);
INSERT INTO `D_MENU` VALUES ('546', '36', '范德萨', '2017-05-08 17:43:39', null, '', '77', null, '24', null, null);
INSERT INTO `D_MENU` VALUES ('547', '36', '嘎嘎', '2017-05-08 17:43:39', null, '', '77', null, '25', null, null);
INSERT INTO `D_MENU` VALUES ('548', '22', '嘎1嘎', '2017-05-08 17:43:39', null, '', '77', null, '26', null, null);
INSERT INTO `D_MENU` VALUES ('549', '21', '撒大大', '2017-05-08 17:43:39', null, '', '77', null, '27', null, null);
INSERT INTO `D_MENU` VALUES ('550', '35', '柔柔弱弱', '2017-05-08 17:43:39', null, '', '77', null, '28', null, null);
INSERT INTO `D_MENU` VALUES ('551', '35', '啊啊啊', '2017-05-08 17:43:39', null, '', '77', null, '29', null, null);
INSERT INTO `D_MENU` VALUES ('552', '35', '摩羯', '2017-05-08 17:43:39', null, '', '77', null, '30', null, null);
INSERT INTO `D_MENU` VALUES ('553', null, '测试', '2017-05-09 13:54:02', null, null, '1', null, '553', '<div>今年发票是低价批发集散地发生的评价哦分拍摄佛菩萨就</div>', null);
INSERT INTO `D_MENU` VALUES ('554', null, '测试1', '2017-05-09 13:56:31', null, '\0', '1', null, '554', '<div>今年发票是低价批发集散地发生的评价哦分拍摄佛菩萨就</div>', null);
INSERT INTO `D_MENU` VALUES ('555', null, '测试', '2017-05-09 14:19:24', null, null, '1', null, '555', '<div>今年发票是低价批发集散地发生的评价哦分拍摄佛菩萨就</div>', null);
INSERT INTO `D_MENU` VALUES ('556', null, 'afds', '2017-05-09 14:21:41', null, null, '1', null, '556', '<div>今年发票是低价批发集散地发生的评价哦分拍摄佛菩萨就</div>', null);
INSERT INTO `D_MENU` VALUES ('557', '33', '这是测是个55555', '2017-05-09 14:23:55', '2017-05-09 14:26:28', '', '1', null, '557', '<div>今年发票是低价批发集散地发生的评价哦分拍摄佛菩萨就</div>', null);
INSERT INTO `D_MENU` VALUES ('558', null, '测试3', '2017-05-09 14:53:24', null, '', '1', null, '558', '<div>1111554今年发票是低价批发集散地发生的评价哦分拍摄佛菩萨就</div>', null);
INSERT INTO `D_MENU` VALUES ('559', null, '测试4', '2017-05-09 14:54:13', null, '', '1', null, '559', '<div>1111554今年发票是低价批发集散地发生的评价哦分拍摄佛菩萨就</div>', null);
INSERT INTO `D_MENU` VALUES ('573', '559', '测试14', '2017-05-09 16:17:01', null, '', '1', null, '573', '<div>1111554今年发票是低价批发集散地发生的评价哦分拍摄佛菩萨就</div>', null);
INSERT INTO `D_MENU` VALUES ('574', null, '测试15', '2017-05-09 16:17:19', null, '', '1', null, '574', '<div>1111554今年发票是低价批发集散地发生的评价哦分拍摄佛菩萨就</div>', null);
INSERT INTO `D_MENU` VALUES ('575', '574', '测试16', '2017-05-09 16:17:31', '2017-05-15 18:58:33', '', '1', null, '575', null, null);
INSERT INTO `D_MENU` VALUES ('577', null, '测试199', '2017-05-15 19:02:47', null, '\0', '1', null, '577', null, null);
INSERT INTO `D_MENU` VALUES ('578', null, 'string', '2017-05-17 15:21:47', null, '', '83', null, '578', '测试2', 'string');
INSERT INTO `D_MENU` VALUES ('579', null, 'string', '2017-05-17 15:40:33', null, '', '83', null, '579', '测试2', 'string');
INSERT INTO `D_MENU` VALUES ('581', null, '测试菜单89+', '2017-05-17 15:46:10', null, '', '83', null, '581', '测试呢人', 'string');
INSERT INTO `D_MENU` VALUES ('582', '581', '7657', '2017-05-17 16:17:10', null, '', '83', null, '582', null, '6');
INSERT INTO `D_MENU` VALUES ('583', '581', '657', '2017-05-17 16:22:41', null, '', '83', null, '583', null, '76575');
INSERT INTO `D_MENU` VALUES ('584', '581', '8', '2017-05-17 16:24:22', null, '', '83', null, '584', null, '543');
INSERT INTO `D_MENU` VALUES ('585', '581', '测试分类', '2017-05-17 16:26:35', null, '', '83', null, '585', '#评价哦放松放松\n_发射点发_\n**生发射点发生**fs\n> dfsdfsd\n', null);
INSERT INTO `D_MENU` VALUES ('586', null, '发', '2017-05-17 16:27:32', null, '', '83', null, '586', '#发', null);
INSERT INTO `D_MENU` VALUES ('587', null, '7757', '2017-05-17 16:29:20', null, '', '83', null, '587', '#发射点', null);
INSERT INTO `D_MENU` VALUES ('588', '587', '测试5', '2017-05-17 16:32:51', null, '', '83', null, '588', '发射点发生', null);
INSERT INTO `D_MENU` VALUES ('589', '587', '测试6', '2017-05-17 16:33:07', null, '', '83', null, '589', null, 'www.baidu.com');
INSERT INTO `D_MENU` VALUES ('590', null, '测试7', '2017-05-17 16:33:26', null, '\0', '83', null, '590', null, '水水');
INSERT INTO `D_MENU` VALUES ('591', null, '测试8', '2017-05-17 16:35:42', null, '', '83', null, '591', null, 'a');
INSERT INTO `D_MENU` VALUES ('592', '591', 'ces9', '2017-05-17 16:35:56', null, '', '83', null, '592', null, 's');
INSERT INTO `D_MENU` VALUES ('593', null, '测试10', '2017-05-17 16:36:09', null, '', '83', null, '593', null, null);
INSERT INTO `D_MENU` VALUES ('594', '593', '测试11', '2017-05-17 16:36:30', null, '', '83', null, '594', null, '1');
INSERT INTO `D_MENU` VALUES ('595', null, '测试12', '2017-05-17 16:36:39', null, '', '83', null, '595', null, '1');
INSERT INTO `D_MENU` VALUES ('596', '593', '111111', '2017-05-17 16:36:55', null, '', '83', null, '596', null, null);
INSERT INTO `D_MENU` VALUES ('597', null, '1111111', '2017-05-17 16:36:59', '2017-05-17 16:40:34', '\0', '83', null, '597', null, 'www.baidu.com');
INSERT INTO `D_MENU` VALUES ('598', null, 'a11', '2017-05-17 16:46:24', null, '', '82', null, '598', null, 'aaaa');
INSERT INTO `D_MENU` VALUES ('599', null, '64', '2017-05-17 16:51:19', null, '', '82', null, '599', null, '5');
INSERT INTO `D_MENU` VALUES ('600', null, 'dddd', '2017-05-17 16:51:40', null, '', '82', null, '600', null, 'd');
INSERT INTO `D_MENU` VALUES ('601', null, 'a1', '2017-05-17 16:51:55', null, '', '81', null, '601', '@dffa', null);
INSERT INTO `D_MENU` VALUES ('602', null, '2222222', '2017-05-17 16:56:55', '2017-05-17 19:17:53', '\0', '83', null, '602', '# qw', null);
INSERT INTO `D_MENU` VALUES ('603', null, 'ffasfasf', '2017-05-17 17:05:41', '2017-05-17 17:10:09', '\0', '83', null, '603', '#sdfasfasfafasfasf', null);
INSERT INTO `D_MENU` VALUES ('607', null, '测试菜单89+', '2017-05-17 17:08:43', null, '', '80', null, '607', '测试呢人', 'string');
INSERT INTO `D_MENU` VALUES ('608', null, '测试菜单89+', '2017-05-17 17:09:15', null, '', '59', null, '608', '测试呢人', 'string');
INSERT INTO `D_MENU` VALUES ('609', null, 'vsdfsf', '2017-05-17 19:19:00', null, '', '83', null, '609', 'dfsfsfs', null);
INSERT INTO `D_MENU` VALUES ('610', null, 'gsdgsg', '2017-05-17 19:19:08', null, '', '82', null, '610', null, null);
INSERT INTO `D_MENU` VALUES ('611', null, '菜单菜单', '2017-05-17 21:21:10', null, '', '83', null, null, 'ulsflsdfsdfsdfdsfds', '');
INSERT INTO `D_MENU` VALUES ('612', '574', '测试16', '2017-05-19 17:28:31', null, '', '85', null, '575', null, null);
INSERT INTO `D_MENU` VALUES ('613', null, '测试15', '2017-05-19 17:28:31', null, '', '85', null, '574', '<div>1111554今年发票是低价批发集散地发生的评价哦分拍摄佛菩萨就</div>', null);
INSERT INTO `D_MENU` VALUES ('614', '559', '测试14', '2017-05-19 17:28:31', null, '', '85', null, '573', '<div>1111554今年发票是低价批发集散地发生的评价哦分拍摄佛菩萨就</div>', null);
INSERT INTO `D_MENU` VALUES ('615', null, '测试4', '2017-05-19 17:28:31', null, '', '85', null, '559', '<div>1111554今年发票是低价批发集散地发生的评价哦分拍摄佛菩萨就</div>', null);
INSERT INTO `D_MENU` VALUES ('616', null, '测试3', '2017-05-19 17:28:31', null, '', '85', null, '558', '<div>1111554今年发票是低价批发集散地发生的评价哦分拍摄佛菩萨就</div>', null);
INSERT INTO `D_MENU` VALUES ('617', '33', '这是测是个55555', '2017-05-19 17:28:31', null, '', '85', null, '557', '<div>今年发票是低价批发集散地发生的评价哦分拍摄佛菩萨就</div>', null);
INSERT INTO `D_MENU` VALUES ('618', null, '概述', '2017-05-19 17:28:31', null, '', '85', null, '35', null, null);
INSERT INTO `D_MENU` VALUES ('619', null, '获取密钥', '2017-05-19 17:28:31', null, '', '85', null, '34', null, null);
INSERT INTO `D_MENU` VALUES ('620', null, '开发指南', '2017-05-19 17:28:31', null, '', '85', null, '33', null, null);
INSERT INTO `D_MENU` VALUES ('621', null, '类参考', '2017-05-19 17:28:31', null, '', '85', null, '32', null, 'http://wiki.lbsyun.baidu.com/cms/androidsdk/doc/v4_3_0/index.html');
INSERT INTO `D_MENU` VALUES ('622', null, '更新日志', '2017-05-19 17:28:31', null, '', '85', null, '31', null, null);
INSERT INTO `D_MENU` VALUES ('623', null, '常见问题', '2017-05-19 17:28:31', null, '', '85', null, '30', null, null);
INSERT INTO `D_MENU` VALUES ('624', null, '相关下载', '2017-05-19 17:28:31', null, '', '85', null, '29', null, null);
INSERT INTO `D_MENU` VALUES ('625', '33', '简介', '2017-05-19 17:28:31', null, '', '85', null, '28', null, null);
INSERT INTO `D_MENU` VALUES ('626', '33', '申请密钥', '2017-05-19 17:28:31', null, '', '85', null, '27', 'sdffsdfsadfasdf', null);
INSERT INTO `D_MENU` VALUES ('627', '33', '配置环境及发布', '2017-05-19 17:28:31', null, '', '85', null, '26', 'adaafasdfsadf', null);
INSERT INTO `D_MENU` VALUES ('628', null, '星期五下午', '2017-05-19 17:37:13', null, '\0', '85', null, null, '内容啊啊', 'dadsfsdafds');
INSERT INTO `D_MENU` VALUES ('629', null, '星期五下午', '2017-05-19 17:37:38', null, '', '85', null, null, '内容啊啊', 'dadsfsdafds');

-- ----------------------------
-- Table structure for D_PERMISSION
-- ----------------------------
DROP TABLE IF EXISTS `D_PERMISSION`;
CREATE TABLE `D_PERMISSION` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `URL` varchar(256) DEFAULT NULL COMMENT '接口地址',
  `DESCRIPTION` varchar(64) DEFAULT NULL COMMENT 'url描述',
  `PATTERN` varchar(255) DEFAULT NULL COMMENT '匹配规则',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of D_PERMISSION
-- ----------------------------
INSERT INTO `D_PERMISSION` VALUES ('21', 'admin/user', '添加/更新用户', 'user:upsert');
INSERT INTO `D_PERMISSION` VALUES ('22', 'admin/user', '列出全部用户', 'user:query');
INSERT INTO `D_PERMISSION` VALUES ('23', 'admin/user/validate', '校验用户名', 'user:validate');
INSERT INTO `D_PERMISSION` VALUES ('25', 'admin/user/**', '用户删除', 'user:delete');
INSERT INTO `D_PERMISSION` VALUES ('26', 'admin/menu', '菜单查询', 'menu:query');
INSERT INTO `D_PERMISSION` VALUES ('27', 'admin/menu', '新增/修改菜单', 'menu:upsert');
INSERT INTO `D_PERMISSION` VALUES ('28', 'admin/menu/**', '获取单个菜单详情', 'menu:query');
INSERT INTO `D_PERMISSION` VALUES ('29', 'admin/menu/**', '菜单删除', 'menu:delete');
INSERT INTO `D_PERMISSION` VALUES ('30', 'admin/version/**', '版本删除', 'version:delete');
INSERT INTO `D_PERMISSION` VALUES ('31', 'admin/version/**', '版本详情', 'version:detail');
INSERT INTO `D_PERMISSION` VALUES ('32', 'admin/version', '新增或修改版本', 'version:upsert');
INSERT INTO `D_PERMISSION` VALUES ('33', 'admin/version', '查询版本列表', 'version:query');
INSERT INTO `D_PERMISSION` VALUES ('34', 'admin/role', '查询角色列表', 'role:query');
INSERT INTO `D_PERMISSION` VALUES ('35', 'admin/role', '新增/修改角色', 'role:upsert');
INSERT INTO `D_PERMISSION` VALUES ('36', 'admin/role/permission', '角色绑定权限', 'role:upsert');
INSERT INTO `D_PERMISSION` VALUES ('37', 'admin/permission', '查询权限列表', 'permission:query');
INSERT INTO `D_PERMISSION` VALUES ('38', 'admin/permission/**', '查询角色已绑定权限', 'permission:query');
INSERT INTO `D_PERMISSION` VALUES ('39', 'admin/user/role', '绑定用户角色', 'user:upsert');
INSERT INTO `D_PERMISSION` VALUES ('40', 'agentApply', '代理商申请新增', 'agentApply:add');
INSERT INTO `D_PERMISSION` VALUES ('41', 'agentApply/audit', '审核代理商申请', 'agentApply:audit');
INSERT INTO `D_PERMISSION` VALUES ('42', 'agentApply', '查询代理商申请列表', 'agentApply:query');
INSERT INTO `D_PERMISSION` VALUES ('43', 'isvApply', '查询ISV申请列表', 'isvApply:query');
INSERT INTO `D_PERMISSION` VALUES ('44', 'agentApply/**', '查询代理商申请详情', 'agentApply:detail');
INSERT INTO `D_PERMISSION` VALUES ('45', 'isvApply/**', '查询ISV申请详情', 'isvApply:detail');
INSERT INTO `D_PERMISSION` VALUES ('46', 'admin/mail/send', '发送邮件', 'mail:send');
INSERT INTO `D_PERMISSION` VALUES ('47', 'admin/mail/**', '查看邮件详情', 'mail:detail');
INSERT INTO `D_PERMISSION` VALUES ('48', 'admin/resource', '上传文件', 'resource:upload');
INSERT INTO `D_PERMISSION` VALUES ('49', 'admin/resource', '获取所有文件', 'resource:query');
INSERT INTO `D_PERMISSION` VALUES ('50', 'admin/login', '后台登录', 'admin:login');
INSERT INTO `D_PERMISSION` VALUES ('51', 'isvApply', 'ISV申请', 'isvApply:add');
INSERT INTO `D_PERMISSION` VALUES ('52', 'isvApply/audit', 'ISV审核', 'isvApply:audit');

-- ----------------------------
-- Table structure for D_RESOURCE
-- ----------------------------
DROP TABLE IF EXISTS `D_RESOURCE`;
CREATE TABLE `D_RESOURCE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(250) DEFAULT NULL COMMENT '新资源名',
  `DESCRIPTION` varchar(500) DEFAULT NULL COMMENT '资源描述',
  `SOURCE_NAME` varchar(250) DEFAULT NULL COMMENT '源资源名',
  `EXTENSION_NAME` varchar(10) DEFAULT NULL COMMENT '扩展名',
  `SIZE` int(8) DEFAULT NULL COMMENT '资源大小',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `PATH` varchar(255) DEFAULT NULL COMMENT '资源存放路径',
  `VERSION_ID` bigint(20) DEFAULT NULL COMMENT '版本id',
  PRIMARY KEY (`ID`),
  KEY `VERSION_ID` (`VERSION_ID`),
  CONSTRAINT `D_RESOURCE_ibfk_1` FOREIGN KEY (`VERSION_ID`) REFERENCES `D_VERSION` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of D_RESOURCE
-- ----------------------------
INSERT INTO `D_RESOURCE` VALUES ('12', null, 'aaaaaa', 'portrait.jpg', 'jpg', null, '2017-05-03 00:00:00', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('13', null, 'aaaaaa', 'portrait.jpg', 'jpg', null, '2017-05-03 00:00:00', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('14', null, 'aaaaaa', '64卦卦序.jpg', 'jpg', null, '2017-05-03 00:00:00', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('15', null, 'aaaaaa', 'aaa2.png', 'png', null, '2017-05-03 00:00:00', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('16', null, 'aaaaaa', 'question1.jpg', 'jpg', null, '2017-05-03 00:00:00', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('17', null, null, 'rat.png', '.png', '797587', '2017-05-03 00:00:00', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('18', null, null, 'rat.png', 'png', '797587', '2017-05-03 00:00:00', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('19', null, null, 'portrait.jpg', 'jpg', '13652', '2017-05-04 00:00:00', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('20', null, null, 'sn.txt', 'txt', '512', '2017-05-08 00:00:00', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('21', null, null, 'angular.min.js', 'min.js', '154641', '2017-05-08 00:00:00', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('22', null, null, 'angular.min.js', 'js', '154641', '2017-05-08 00:00:00', null, 'http://172.16.0.145:18080/devResource/angular.min.js', null);
INSERT INTO `D_RESOURCE` VALUES ('23', null, null, 'angular.min.js', 'js', '154641', '2017-05-08 00:00:00', null, 'http://172.16.0.145:18080/devResource/angular.min.js', null);
INSERT INTO `D_RESOURCE` VALUES ('24', null, null, 'portrait.jpg', 'jpg', '13652', '2017-05-08 00:00:00', null, 'portrait.jpg', null);
INSERT INTO `D_RESOURCE` VALUES ('25', null, null, 'portrait.jpg', 'jpg', '13652', '2017-05-08 00:00:00', null, 'portrait.jpg', null);
INSERT INTO `D_RESOURCE` VALUES ('26', null, null, 'angular.min.js', 'js', '154641', '2017-05-08 00:00:00', null, 'http://172.16.0.145:18080/devResource/angular.min.js', null);
INSERT INTO `D_RESOURCE` VALUES ('27', null, null, 'angular.min.js', 'js', '154641', '2017-05-08 00:00:00', null, 'http://172.16.0.145:18080/devResource/angular.min.js', null);
INSERT INTO `D_RESOURCE` VALUES ('28', null, null, 'aa.html', 'html', '2874', '2017-05-08 00:00:00', null, 'http://172.16.0.145:18080/devResource/aa.html', null);
INSERT INTO `D_RESOURCE` VALUES ('29', null, null, 'angular.min.js', 'js', '154641', '2017-05-08 00:00:00', null, 'http://172.16.0.145:18080/devResource/angular.min.js', null);
INSERT INTO `D_RESOURCE` VALUES ('30', null, null, 'angular.min.js', 'js', '154641', '2017-05-08 00:00:00', null, 'http://172.16.0.145:18080/devResource/angular.min.js', null);
INSERT INTO `D_RESOURCE` VALUES ('31', '20170508105900.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 00:00:00', null, 'angular.min.js', null);
INSERT INTO `D_RESOURCE` VALUES ('32', '20170508105904.html', null, 'aa.html', 'html', '2874', '2017-05-08 00:00:00', null, 'aa.html', null);
INSERT INTO `D_RESOURCE` VALUES ('33', '20170508110229.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 00:00:00', null, 'angular.min.js', null);
INSERT INTO `D_RESOURCE` VALUES ('34', '20170508110234.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 00:00:00', null, 'angular.min.js', null);
INSERT INTO `D_RESOURCE` VALUES ('35', '20170508110239.js', null, 'app.js', 'js', '151894', '2017-05-08 00:00:00', null, 'app.js', null);
INSERT INTO `D_RESOURCE` VALUES ('36', '20170508110716.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 00:00:00', null, 'angular.min.js', null);
INSERT INTO `D_RESOURCE` VALUES ('37', '20170508110722.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 00:00:00', null, 'angular.min.js', null);
INSERT INTO `D_RESOURCE` VALUES ('38', '2017050811103321912.jpg', null, '64卦卦序.jpg', 'jpg', '63726', '2017-05-08 00:00:00', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('39', '2017050811105212159.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 00:00:00', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('40', '2017050811591369995.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 11:59:14', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('41', '2017050811592096422.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 11:59:21', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('42', '2017050813015977474.png', null, 'rat.png', 'png', '797587', '2017-05-08 13:02:00', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('43', '2017050813390244345.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 13:39:03', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('44', '2017050813403435826.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 13:40:34', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('45', '2017050813483569411.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 13:48:36', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('46', '2017050814220130760.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 14:22:01', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('47', '2017050814233626989.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 14:23:36', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('48', '2017050814251279003.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 14:25:12', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('49', '2017050814253339836.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 14:25:34', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('50', '2017050814291558944.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 14:29:15', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('51', '2017050814301913879.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 14:30:19', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('52', '2017050814302474440.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 14:30:25', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('53', '2017050814302734180.js', null, 'angular.min.js', 'js', '154641', '2017-05-08 14:30:28', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('54', '2017050814304410335.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 14:30:44', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('55', '2017050814422051891.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 14:42:21', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('56', '2017050814433787525.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 14:43:38', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('57', '2017050815005267816.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:00:52', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('58', '2017050815013837552.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:01:38', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('59', '2017050815015510145.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:01:56', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('60', '2017050815023673495.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:02:36', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('61', '2017050815025919521.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:03:00', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('62', '2017050815032251597.jpg', null, 'a11.jpg', 'jpg', '435487', '2017-05-08 15:03:23', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('63', '2017050815034228470.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:03:42', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('64', '2017050815085074798.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:08:51', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('65', '2017050815091839646.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:09:18', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('66', '2017050815141635428.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:14:17', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('67', '2017050815205240017.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:20:52', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('68', '2017050815222548696.jpg', null, 'a11.jpg', 'jpg', '435487', '2017-05-08 15:22:25', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('69', '2017050815222717433.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:22:27', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('70', '2017050815222928009.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 15:22:29', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('71', '2017050815234585915.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:23:46', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('72', '2017050815325175436.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:32:52', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('73', '2017050815330821223.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:33:09', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('74', '2017050815331099900.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:33:11', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('75', '2017050815331260415.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:33:13', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('76', '2017050815331423886.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:33:15', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('77', '2017050815331653349.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 15:33:17', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('78', '2017050815331987693.jpg', null, 'a11.jpg', 'jpg', '435487', '2017-05-08 15:33:19', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('79', '2017050815335111751.jpg', null, 'a11.jpg', 'jpg', '435487', '2017-05-08 15:33:51', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('80', '2017050815335437367.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:33:54', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('81', '2017050815335795523.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:33:57', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('82', '2017050815341752227.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:34:18', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('83', '2017050815342262488.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 15:34:22', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('84', '2017050815342599549.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 15:34:26', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('85', '2017050815345838155.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:34:58', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('86', '2017050815350770043.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 15:35:07', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('87', '2017050815442069791.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:44:20', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('88', '2017050815443782779.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:44:37', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('89', '2017050815444056295.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:44:41', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('90', '2017050815444343399.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:44:44', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('91', '2017050815444735632.jpg', null, 'picc.jpg', 'jpg', '24009', '2017-05-08 15:44:48', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('92', '2017050815445143896.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 15:44:51', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('93', '2017050815455581464.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:45:56', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('94', '2017050815472243096.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:47:22', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('95', '2017050815473953729.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 15:47:40', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('96', '2017050815480131937.jpg', null, 'picc.jpg', 'jpg', '24009', '2017-05-08 15:48:01', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('97', '2017050815483178623.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 15:48:32', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('98', '2017050815491895341.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:49:19', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('99', '2017050815492873685.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:49:28', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('100', '2017050815494639807.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:49:46', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('101', '2017050815503372584.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 15:50:33', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('102', '2017050815510052689.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:51:00', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('103', '2017050815512860883.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:51:29', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('104', '2017050815514519903.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:51:45', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('105', '2017050815523011895.jpg', null, 'a11.jpg', 'jpg', '435487', '2017-05-08 15:52:30', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('106', '2017050815542969514.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:54:30', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('107', '2017050815544598758.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:54:45', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('108', '2017050815555944030.jpg', null, 'picc.jpg', 'jpg', '24009', '2017-05-08 15:56:00', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('109', '2017050815561592805.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:56:15', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('110', '2017050815562725274.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:56:27', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('111', '2017050815570737689.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 15:57:08', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('112', '2017050816003394218.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 16:00:34', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('113', '2017050816012276918.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:01:22', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('114', '2017050816013516960.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 16:01:36', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('115', '2017050816071378666.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:07:14', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('116', '2017050816071621677.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 16:07:16', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('117', '2017050816072050047.jpG', null, '140036019671839799.jpG', 'jpG', '49231', '2017-05-08 16:07:20', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('118', '2017050816072250035.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:07:22', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('119', '2017050816150987601.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 16:15:09', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('120', '2017050816162469334.jpg', null, 'picc.jpg', 'jpg', '24009', '2017-05-08 16:16:25', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('121', '2017050816163440973.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:16:34', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('122', '2017050816164234707.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:16:42', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('123', '2017050816191930459.jpg', null, 'picc.jpg', 'jpg', '24009', '2017-05-08 16:19:19', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('124', '2017050816200769747.jpg', null, 'picc.jpg', 'jpg', '24009', '2017-05-08 16:20:08', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('125', '2017050816214388156.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:21:43', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('126', '2017050816224326306.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:22:43', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('127', '2017050816232625773.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:23:26', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('128', '2017050816233814819.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:23:39', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('129', '2017050816240777326.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:24:07', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('130', '2017050816243376179.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 16:24:34', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('131', '2017050816252282249.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 16:25:23', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('132', '2017050816254471885.jpg', null, 'picc.jpg', 'jpg', '24009', '2017-05-08 16:25:44', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('133', '2017050816271380576.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 16:27:14', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('134', '2017050816274370123.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 16:27:43', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('135', '2017050816294278851.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:29:43', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('136', '2017050816305540759.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:30:56', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('137', '2017050816311775880.jpg', null, 'a11.jpg', 'jpg', '435487', '2017-05-08 16:31:17', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('138', '2017050816323574868.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:32:36', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('139', '2017050816335153882.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:33:51', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('140', '2017050816343977049.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:34:39', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('141', '2017050816354782991.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:35:47', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('142', '2017050816371334184.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:37:14', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('143', '2017050816380929679.jpg', null, 'picc.jpg', 'jpg', '24009', '2017-05-08 16:38:09', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('144', '2017050816381918581.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:38:19', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('145', '2017050816382490899.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 16:38:24', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('146', '2017050816390068621.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:39:01', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('147', '2017050816400832833.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:40:08', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('148', '2017050816402677372.jpg', null, 'picc.jpg', 'jpg', '24009', '2017-05-08 16:40:27', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('149', '2017050816411030992.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 16:41:10', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('150', '2017050816412322163.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 16:41:23', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('151', '2017050816460568303.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:46:06', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('152', '2017050816475673584.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:47:56', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('153', '2017050816541459124.pdf', null, '(第二版).pdf', 'pdf', '32978724', '2017-05-08 16:54:15', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('154', '2017050816550876828.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:55:09', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('155', '2017050816553451282.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:55:35', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('156', '2017050816554726309.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 16:55:48', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('157', '2017050816555361172.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 16:55:53', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('158', '2017050816555826586.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 16:55:59', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('159', '2017050816561487760.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 16:56:15', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('160', '2017050816563630040.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:56:37', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('161', '2017050816564517015.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:56:45', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('162', '2017050816571381574.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 16:57:14', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('163', '2017050816582228569.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:58:22', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('164', '2017050816585623840.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 16:58:57', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('165', '2017050817033675911.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 17:03:36', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('166', '2017050817042618368.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 17:04:26', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('167', '2017050817060893952.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 17:06:09', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('168', '2017050817364557833.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 17:36:45', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('169', '2017050817371528752.jpg', null, 'picc.jpg', 'jpg', '24009', '2017-05-08 17:37:16', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('170', '2017050817372621889.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 17:37:26', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('171', '2017050817381679136.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 17:38:17', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('172', '2017050817401176912.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 17:40:12', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('173', '2017050817432857554.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 17:43:29', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('174', '2017050817440788311.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 17:44:07', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('175', '2017050817452673684.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 17:45:27', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('176', '2017050817490772021.jpg', null, 'picc.jpg', 'jpg', '24009', '2017-05-08 17:49:08', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('177', '2017050817494115779.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 17:49:41', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('178', '2017050817494916746.png', null, 'weixinlogo.png', 'png', '4753', '2017-05-08 17:49:49', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('179', '2017050817523746039.jpg', null, 'picc.jpg', 'jpg', '24009', '2017-05-08 17:52:38', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('180', '2017050817540466335.zip', null, 'LayaAirIDE_1.6.1.zip', 'zip', '91376215', '2017-05-08 17:54:05', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('181', '2017050818080812060.zip', null, 'genymotion.zip', 'zip', '26388620', '2017-05-08 18:08:09', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('182', '2017050819453983791.rar', null, 'CuteFTP9.rar', 'rar', '10176625', '2017-05-08 19:45:39', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('183', '2017050819455419530.jpg', null, 'a11.jpg', 'jpg', '435487', '2017-05-08 19:45:55', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('184', '2017050819464134580.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-08 19:46:42', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('185', '2017050916310417584.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-09 16:31:04', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('186', '2017050916315343596.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-09 16:31:53', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('187', '2017051016162951238.jpg', null, 't.jpg', 'jpg', '18161', '2017-05-10 16:16:29', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('188', '2017051917350155313.xml', null, 'ehcache-shiro.xml', 'xml', '403', '2017-05-19 17:35:02', null, null, null);
INSERT INTO `D_RESOURCE` VALUES ('189', '2017051917355486899.xml', null, 'pom.xml', 'xml', '8703', '2017-05-19 17:35:54', null, null, null);

-- ----------------------------
-- Table structure for D_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `D_ROLE`;
CREATE TABLE `D_ROLE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EN_NAME` varchar(32) DEFAULT NULL COMMENT '角色英文名称',
  `CN_NAME` varchar(10) DEFAULT NULL COMMENT '角色中文名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of D_ROLE
-- ----------------------------
INSERT INTO `D_ROLE` VALUES ('5', 'superAdmin', '超级管理员');
INSERT INTO `D_ROLE` VALUES ('6', 'bizAdmin', '商务管理员');
INSERT INTO `D_ROLE` VALUES ('7', 'sdkAdmin', 'SDK管理员');
INSERT INTO `D_ROLE` VALUES ('8', 'customer', '普通代理商');
INSERT INTO `D_ROLE` VALUES ('9', 'admin', '普通管理员');
INSERT INTO `D_ROLE` VALUES ('10', 'isvCustomer', 'ISV代理商');
INSERT INTO `D_ROLE` VALUES ('11', 'superAdmin6', '超级管理员6');
INSERT INTO `D_ROLE` VALUES ('12', 'superAdmin7', '超级管理员7');
INSERT INTO `D_ROLE` VALUES ('13', '的发生edd的', 'adsfasd');

-- ----------------------------
-- Table structure for D_ROLE_PERMISSION
-- ----------------------------
DROP TABLE IF EXISTS `D_ROLE_PERMISSION`;
CREATE TABLE `D_ROLE_PERMISSION` (
  `RID` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `PID` bigint(20) DEFAULT NULL COMMENT '权限ID',
  KEY `RID` (`RID`),
  KEY `PID` (`PID`),
  CONSTRAINT `D_ROLE_PERMISSION_ibfk_1` FOREIGN KEY (`RID`) REFERENCES `D_ROLE` (`ID`),
  CONSTRAINT `D_ROLE_PERMISSION_ibfk_2` FOREIGN KEY (`PID`) REFERENCES `D_PERMISSION` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of D_ROLE_PERMISSION
-- ----------------------------
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '22');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '23');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '21');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('6', '21');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('6', '22');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('6', '23');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('8', '23');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('8', '40');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('8', '51');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '25');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '26');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '27');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '28');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '29');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '30');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '31');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '32');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '33');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '34');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '35');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '36');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '37');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '38');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '39');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('7', '25');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('7', '26');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('7', '27');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('13', '48');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '50');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('9', '51');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('5', '52');
INSERT INTO `D_ROLE_PERMISSION` VALUES ('8', '26');

-- ----------------------------
-- Table structure for D_USER
-- ----------------------------
DROP TABLE IF EXISTS `D_USER`;
CREATE TABLE `D_USER` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(30) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(30) DEFAULT NULL COMMENT '密码',
  `EMAIL_ADDRESS` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `SEX` tinyint(1) DEFAULT NULL COMMENT '性别',
  `PASSWORD_CHANGE_REQUIRED` bit(1) DEFAULT NULL COMMENT '是否需要修改密码',
  `RECEIVE_EMAIL` bit(1) DEFAULT NULL COMMENT '是否接收到邮件',
  `REGISTERED` bit(1) DEFAULT NULL COMMENT '注册',
  `DEACTIVATED` bit(1) DEFAULT NULL COMMENT '是否有效',
  `LAST_VISIT` datetime DEFAULT NULL COMMENT '最近登录时间',
  `USER_ROLE_IDS` varchar(20) DEFAULT NULL,
  `APP_KEY` varchar(50) DEFAULT NULL,
  `APP_SECRET` varchar(50) DEFAULT NULL,
  `PHONE` varchar(20) DEFAULT NULL,
  `COMPANY` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of D_USER
-- ----------------------------
INSERT INTO `D_USER` VALUES ('1', 'jelynn', '123456', null, null, '\0', '', null, '\0', '2017-05-19 16:16:41', '1', null, null, null, null);
INSERT INTO `D_USER` VALUES ('2', 'jelynn1', '123456', null, null, '\0', '', null, '\0', '2017-05-16 16:03:21', '8,9,10', null, null, null, null);
INSERT INTO `D_USER` VALUES ('32', '撒大放送', '123123123', 'asdas', null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER` VALUES ('33', 'asdasd', '123123123', 'asdas', null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER` VALUES ('34', 'asdasddsfasdf', '123123123', 'asdas', null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER` VALUES ('35', 'asdasddsfasdf', '123123123', 'asdas', null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER` VALUES ('36', 'asdasddsfasdf', '123123123', 'asdas', null, '\0', '', null, '\0', null, '2', null, null, null, null);
INSERT INTO `D_USER` VALUES ('37', 'asdasddsfasdf', '123123123', 'asdas', null, '\0', '', null, '\0', null, '2', null, null, null, null);
INSERT INTO `D_USER` VALUES ('38', 'asdasddsfasdf', '123123123', 'asdas', null, '\0', '', null, '\0', null, '2', null, null, null, null);
INSERT INTO `D_USER` VALUES ('39', 'ray', '123456', null, null, '\0', '', null, '\0', '2017-05-16 18:18:28', '3', null, null, null, null);
INSERT INTO `D_USER` VALUES ('40', 'ray1', '123456', null, null, '\0', '', null, '\0', null, '2', null, null, null, null);
INSERT INTO `D_USER` VALUES ('41', 'ray2', '123456', null, null, '\0', '', null, '\0', null, '2', null, null, null, null);
INSERT INTO `D_USER` VALUES ('42', 'ray3', '123456', null, null, '\0', '', null, '\0', null, '2', null, null, null, null);
INSERT INTO `D_USER` VALUES ('43', 'ray4', '123456', null, null, '\0', '', null, '\0', null, '2', null, null, null, null);
INSERT INTO `D_USER` VALUES ('44', 'ray5', '123456', null, null, '\0', '', null, '\0', null, '2', null, null, null, null);
INSERT INTO `D_USER` VALUES ('49', 'ray11', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER` VALUES ('53', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER` VALUES ('54', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER` VALUES ('55', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER` VALUES ('56', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER` VALUES ('57', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER` VALUES ('58', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER` VALUES ('59', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER` VALUES ('60', 'ray321', '123456', null, null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER` VALUES ('61', 'ray321', '123456', null, null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER` VALUES ('62', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER` VALUES ('63', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER` VALUES ('64', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER` VALUES ('65', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER` VALUES ('66', 'ray321', '123456', null, null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER` VALUES ('75', 'ray3121', '123456', null, null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER` VALUES ('76', 'ray999', '1234561111', null, null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER` VALUES ('80', 'string', 'string', 'string', '0', '', '', null, '', '2017-05-17 21:10:53', '0', null, null, null, null);
INSERT INTO `D_USER` VALUES ('81', 'string', 'string', 'string', '0', '', '', null, '', '2017-05-17 21:10:53', '0', null, null, null, null);
INSERT INTO `D_USER` VALUES ('82', 'test2017', '123456', '704373316@qq.com', '1', null, null, null, '', null, '0', null, null, null, null);
INSERT INTO `D_USER` VALUES ('83', 'amanda', '123456', 'fyd1988gmail.com', '1', null, null, null, '', null, '0', null, null, '123544488', '隐隐约约');
INSERT INTO `D_USER` VALUES ('84', 'test2017', '555', '704373316@qq.com', '2', null, null, null, '', null, '0', null, null, '99999', '金刚公司');
INSERT INTO `D_USER` VALUES ('85', 'fuyuda', '123456', '704373316@qq.com', '0', null, null, null, '\0', null, '', null, null, '123311111', 'aaatest');
INSERT INTO `D_USER` VALUES ('86', 'abcdefg', '123456', '704373316@qq.com', '0', null, null, null, '', null, '0', null, null, '123456789', 'string');
INSERT INTO `D_USER` VALUES ('87', 'abcdefg111', '123456', '704373316@qq.com', '0', null, null, null, '', null, '0', null, null, '123456789', 'string');
INSERT INTO `D_USER` VALUES ('88', 'abcdefg99922', '123456', '704373316@qq.com', '1', null, null, null, '', null, '0', null, null, '12345678911', 'string');
INSERT INTO `D_USER` VALUES ('89', null, null, null, null, null, null, null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER` VALUES ('90', null, null, null, null, null, null, null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER` VALUES ('91', 'string', 'string', 'string', '0', null, null, null, '', '2017-05-21 16:50:33', '0', null, null, 'string', 'string');
INSERT INTO `D_USER` VALUES ('92', 'string', 'string', 'string', '0', null, null, null, '', '2017-05-21 16:50:33', '0', null, null, 'string', 'string');
INSERT INTO `D_USER` VALUES ('93', 'fuyuda2017', '123456', null, null, null, null, null, '\0', null, '10', null, null, null, null);
INSERT INTO `D_USER` VALUES ('94', null, null, null, null, null, null, null, '\0', null, '10', null, null, null, null);
INSERT INTO `D_USER` VALUES ('95', 'dell', '123456', null, null, null, null, null, '\0', null, '10', null, null, null, null);
INSERT INTO `D_USER` VALUES ('96', 'zhongtie', '123456', null, null, null, null, null, '\0', null, '10', null, null, null, null);
INSERT INTO `D_USER` VALUES ('97', 'mckay', '123456', null, null, null, null, null, '\0', null, '10', null, null, null, null);
INSERT INTO `D_USER` VALUES ('98', 'fuyuda', '123456', null, null, null, null, null, '\0', null, '10', null, null, null, null);
INSERT INTO `D_USER` VALUES ('99', 'fuyuda', '123456', null, null, null, null, null, '\0', null, '10', null, null, null, null);
INSERT INTO `D_USER` VALUES ('100', 'fuyuda', '123456', null, null, null, null, null, '', null, '10', null, null, null, null);
INSERT INTO `D_USER` VALUES ('101', 'fuyuda', '123456', null, null, null, null, null, '\0', null, '10', null, null, null, null);

-- ----------------------------
-- Table structure for D_USER_BAK
-- ----------------------------
DROP TABLE IF EXISTS `D_USER_BAK`;
CREATE TABLE `D_USER_BAK` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(30) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(30) DEFAULT NULL COMMENT '密码',
  `EMAIL_ADDRESS` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `SEX` bit(1) DEFAULT NULL COMMENT '性别',
  `PASSWORD_CHANGE_REQUIRED` bit(1) DEFAULT NULL COMMENT '是否需要修改密码',
  `RECEIVE_EMAIL` bit(1) DEFAULT NULL COMMENT '是否接收到邮件',
  `REGISTERED` bit(1) DEFAULT NULL COMMENT '注册',
  `DEACTIVATED` bit(1) DEFAULT NULL COMMENT '是否有效',
  `LAST_VISIT` datetime DEFAULT NULL COMMENT '最近登录时间',
  `USER_TYPE` int(1) DEFAULT NULL,
  `APP_KEY` varchar(50) DEFAULT NULL,
  `APP_SECRET` varchar(50) DEFAULT NULL,
  `PHONE` varchar(20) DEFAULT NULL,
  `COMPANY` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of D_USER_BAK
-- ----------------------------
INSERT INTO `D_USER_BAK` VALUES ('1', 'jelynn', '123456', null, null, '\0', '', null, '\0', '2017-05-17 15:54:09', '1', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('2', 'jelynn1', '123456', null, null, '\0', '', null, '\0', '2017-05-16 16:03:21', '3', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('32', '撒大放送', '123123123', 'asdas', null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('33', 'asdasd', '123123123', 'asdas', null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('34', 'asdasddsfasdf', '123123123', 'asdas', null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('35', 'asdasddsfasdf', '123123123', 'asdas', null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('36', 'asdasddsfasdf', '123123123', 'asdas', null, '\0', '', null, '\0', null, '2', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('37', 'asdasddsfasdf', '123123123', 'asdas', null, '\0', '', null, '\0', null, '2', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('38', 'asdasddsfasdf', '123123123', 'asdas', null, '\0', '', null, '\0', null, '2', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('39', 'ray', '123456', null, null, '\0', '', null, '\0', '2017-05-16 18:18:28', '3', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('40', 'ray1', '123456', null, null, '\0', '', null, '\0', null, '2', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('41', 'ray2', '123456', null, null, '\0', '', null, '\0', null, '2', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('42', 'ray3', '123456', null, null, '\0', '', null, '\0', null, '2', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('43', 'ray4', '123456', null, null, '\0', '', null, '\0', null, '2', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('44', 'ray5', '123456', null, null, '\0', '', null, '\0', null, '2', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('49', 'ray11', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('53', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('54', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('55', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('56', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('57', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('58', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('59', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('60', 'ray321', '123456', null, null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('61', 'ray321', '123456', null, null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('62', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('63', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('64', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('65', 'ray191', '123456', null, null, '\0', '', null, '\0', null, '3', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('66', 'ray321', '123456', null, null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('75', 'ray3121', '123456', null, null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('76', 'ray999', '1234561111', null, null, '\0', '', null, '\0', null, '0', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('80', 'string', 'string', 'string', '\0', '', '', null, '', '2017-05-17 21:10:53', '0', null, null, null, null);
INSERT INTO `D_USER_BAK` VALUES ('81', 'string', 'string', 'string', '\0', '', '', null, '', '2017-05-17 21:10:53', '0', null, null, null, null);

-- ----------------------------
-- Table structure for D_USER_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `D_USER_ROLE`;
CREATE TABLE `D_USER_ROLE` (
  `UID` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `RID` bigint(20) DEFAULT NULL COMMENT '角色ID',
  KEY `UID` (`UID`),
  KEY `RID` (`RID`),
  CONSTRAINT `D_USER_ROLE_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `D_USER` (`ID`),
  CONSTRAINT `D_USER_ROLE_ibfk_2` FOREIGN KEY (`RID`) REFERENCES `D_ROLE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of D_USER_ROLE
-- ----------------------------
INSERT INTO `D_USER_ROLE` VALUES ('1', '5');
INSERT INTO `D_USER_ROLE` VALUES ('44', '6');
INSERT INTO `D_USER_ROLE` VALUES ('39', '8');
INSERT INTO `D_USER_ROLE` VALUES ('76', '7');
INSERT INTO `D_USER_ROLE` VALUES ('76', '9');
INSERT INTO `D_USER_ROLE` VALUES ('88', '6');
INSERT INTO `D_USER_ROLE` VALUES ('93', '10');
INSERT INTO `D_USER_ROLE` VALUES ('94', '10');
INSERT INTO `D_USER_ROLE` VALUES ('95', '10');
INSERT INTO `D_USER_ROLE` VALUES ('96', '10');
INSERT INTO `D_USER_ROLE` VALUES ('2', '8');
INSERT INTO `D_USER_ROLE` VALUES ('97', '10');
INSERT INTO `D_USER_ROLE` VALUES ('98', '10');
INSERT INTO `D_USER_ROLE` VALUES ('99', '10');
INSERT INTO `D_USER_ROLE` VALUES ('100', '10');
INSERT INTO `D_USER_ROLE` VALUES ('101', '10');

-- ----------------------------
-- Table structure for D_VERSION
-- ----------------------------
DROP TABLE IF EXISTS `D_VERSION`;
CREATE TABLE `D_VERSION` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION_CODE` varchar(30) DEFAULT NULL COMMENT '版本号',
  `DESCRIPTION` text COMMENT '版本描述',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `URL` varchar(255) DEFAULT NULL COMMENT 'SDK下载地址',
  `EXTENDED_VERSION_CODE` varchar(255) DEFAULT NULL COMMENT '继承版本号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of D_VERSION
-- ----------------------------
INSERT INTO `D_VERSION` VALUES ('1', 'v0.175675', 'v0.18fsdfs', '2017-05-02 00:00:00', '2017-05-08 16:15:13', 'C:\\fakepath\\weixinlogo.png', '0');
INSERT INTO `D_VERSION` VALUES ('2', 'v0.2', 'v0.2', '2017-05-02 00:00:00', '2017-05-08 16:23:28', 'http://172.16.0.145:18080/devResource/2017050816232625773.jpg', '');
INSERT INTO `D_VERSION` VALUES ('3', 'v0.3', 'v0.3', '2017-05-02 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('5', 'v0.5.1', 'v0.5.1', '2017-05-02 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('25', 'v0.6.1', 'v0.6.1', '2017-05-02 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('26', 'v0.6.1', 'v0.6.1', '2017-05-02 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('30', 'v3.3', 'addfafdfdsafdsf', '2017-05-03 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('33', '版本33', '版本33', '2017-05-05 00:00:00', '2017-05-05 00:00:00', null, null);
INSERT INTO `D_VERSION` VALUES ('34', 'string', 'string', '2017-05-05 00:00:00', '2017-05-05 00:00:00', '', '');
INSERT INTO `D_VERSION` VALUES ('35', 'string', 'string', '2017-05-05 00:00:00', '2017-05-05 00:00:00', '', '');
INSERT INTO `D_VERSION` VALUES ('36', 'v31', 'string', '2017-05-05 00:00:00', '2017-05-05 00:00:00', '', '');
INSERT INTO `D_VERSION` VALUES ('37', null, null, '2017-05-05 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('38', 'string', 'string', '2017-05-05 00:00:00', null, null, 'v0.2');
INSERT INTO `D_VERSION` VALUES ('39', 'string', 'string', '2017-05-05 00:00:00', null, null, 'v0.1');
INSERT INTO `D_VERSION` VALUES ('40', 'string', 'string', '2017-05-05 00:00:00', '2017-05-05 00:00:00', null, null);
INSERT INTO `D_VERSION` VALUES ('41', 'string', 'string', '2017-05-05 00:00:00', '2017-05-05 00:00:00', null, null);
INSERT INTO `D_VERSION` VALUES ('42', 'string', 'string', '2017-05-05 00:00:00', null, null, 'v0.2');
INSERT INTO `D_VERSION` VALUES ('43', 'string', 'string', '2017-05-05 00:00:00', null, null, 'v0.2');
INSERT INTO `D_VERSION` VALUES ('44', 'string', 'string', '2017-05-05 00:00:00', null, null, 'v0.2');
INSERT INTO `D_VERSION` VALUES ('45', 'string', 'string', '2017-05-05 00:00:00', null, null, 'v0.2');
INSERT INTO `D_VERSION` VALUES ('46', '7', '', '2017-05-05 00:00:00', null, null, 'v0.1');
INSERT INTO `D_VERSION` VALUES ('47', 'v0.1', 'v0.1', '2017-05-05 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('48', 'v0.1', '767567', '2017-05-05 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('49', 'v0.1', 'v0.1', '2017-05-05 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('50', 'v0.1', 'v0.1更新', '2017-05-05 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('51', '123', '测试添加', '2017-05-05 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('52', '234', '测试添加2', '2017-05-05 00:00:00', null, null, 'v0.1');
INSERT INTO `D_VERSION` VALUES ('53', 'v0.1', 'v0.1', '2017-05-05 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('54', 'v0.1', 'v0.175676575', '2017-05-05 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('55', 'v0.1', 'v0.17657567567567', '2017-05-05 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('56', 'v0.1', 'v0.17657567', '2017-05-05 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('57', 'v0.1', 'v0.17657567', '2017-05-05 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('58', 'v0.1', 'v0.17657567', '2017-05-05 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('59', '44', '', '2017-05-08 00:00:00', null, null, null);
INSERT INTO `D_VERSION` VALUES ('61', 'ceshi', '', '2017-05-08 15:07:19', '2017-05-08 16:38:12', 'http://172.16.0.145:18080/devResource/2017050816305540759.jpg', null);
INSERT INTO `D_VERSION` VALUES ('62', '756756756', '', '2017-05-08 16:41:27', '2017-05-08 16:56:00', 'http://172.16.0.145:18080/devResource/2017050816555826586.png', null);
INSERT INTO `D_VERSION` VALUES ('63', '75', '67567', '2017-05-08 16:47:58', '2017-05-08 16:55:54', 'http://172.16.0.145:18080/devResource/2017050816555361172.png', null);
INSERT INTO `D_VERSION` VALUES ('64', '756', '7567', '2017-05-08 16:55:10', '2017-05-08 16:55:48', 'http://172.16.0.145:18080/devResource/2017050816554726309.png', null);
INSERT INTO `D_VERSION` VALUES ('65', '测试4', '', '2017-05-08 16:56:16', null, null, 'v0.175675');
INSERT INTO `D_VERSION` VALUES ('66', '测试5', '555555555', '2017-05-08 16:56:52', '2017-05-08 16:57:15', 'http://172.16.0.145:18080/devResource/2017050816571381574.png', 'v0.175675');
INSERT INTO `D_VERSION` VALUES ('67', '测试6', '', '2017-05-08 16:58:28', null, null, 'v0.175675');
INSERT INTO `D_VERSION` VALUES ('68', '测试7', '', '2017-05-08 16:59:11', null, null, null);
INSERT INTO `D_VERSION` VALUES ('69', '来了', '', '2017-05-08 17:03:43', null, null, 'v0.175675');
INSERT INTO `D_VERSION` VALUES ('70', 'ionjjhoi', '', '2017-05-08 17:04:29', null, null, null);
INSERT INTO `D_VERSION` VALUES ('71', 'ionjjhoi', '', '2017-05-08 17:04:43', null, null, null);
INSERT INTO `D_VERSION` VALUES ('72', '7567567', '756757', '2017-05-08 17:06:10', null, 'http://172.16.0.145:18080/devResource/2017050817060893952.jpg', null);
INSERT INTO `D_VERSION` VALUES ('73', '解决了', '发士大夫十分', '2017-05-08 17:36:54', '2017-05-08 17:37:17', 'http://172.16.0.145:18080/devResource/2017050817371528752.jpg', 'ionjjhoi');
INSERT INTO `D_VERSION` VALUES ('74', '测试url丢失', '房贷首付', '2017-05-08 17:37:36', null, null, '解决了');
INSERT INTO `D_VERSION` VALUES ('75', '756', '7567', '2017-05-08 17:38:18', '2017-05-09 16:31:54', 'http://172.16.0.145:18080/devResource/2017050916315343596.jpg', '测试4');
INSERT INTO `D_VERSION` VALUES ('76', '测试url丢失2', '', '2017-05-08 17:40:19', '2017-05-08 17:49:42', 'http://172.16.0.145:18080/devResource/2017050817494115779.jpg', '756');
INSERT INTO `D_VERSION` VALUES ('77', '测试丢失3', '是个大帅哥', '2017-05-08 17:43:38', '2017-05-08 17:44:08', 'http://172.16.0.145:18080/devResource/2017050817440788311.jpg', '测试url丢失2');
INSERT INTO `D_VERSION` VALUES ('78', '5345', '353', '2017-05-08 17:45:28', '2017-05-08 17:49:50', 'http://172.16.0.145:18080/devResource/2017050817494916746.png', null);
INSERT INTO `D_VERSION` VALUES ('79', '756', '', '2017-05-08 17:53:01', null, 'http://172.16.0.145:18080/devResource/2017050817523746039.jpg', null);
INSERT INTO `D_VERSION` VALUES ('80', '654', '', '2017-05-08 17:54:08', null, 'http://172.16.0.145:18080/devResource/2017050817540466335.zip', null);
INSERT INTO `D_VERSION` VALUES ('81', 'v66.1', '测试丢失111', '2017-05-08 18:04:12', null, 'http://172.16.0.145:18080/devResource/2017050816232625773.jpg', null);
INSERT INTO `D_VERSION` VALUES ('82', '234567', '', '2017-05-08 18:08:11', '2017-05-11 15:37:00', 'C:\\fakepath\\t.jpg', null);
INSERT INTO `D_VERSION` VALUES ('83', 'V83', '这是个83的版本', '2017-05-16 20:29:04', '2017-05-17 17:26:31', 'C:\\fakepath\\t.jpg', '234567');
INSERT INTO `D_VERSION` VALUES ('85', 'v1.0', 'fsdafdsfds', '2017-05-19 17:28:31', null, 'http://www.baidu.com', 'v0.175675');

<!-- 新增员工表 -->
CREATE TABLE `D_EMPLOYEE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL COMMENT '姓名',
  `USER_BIZ_ID` varchar(255) DEFAULT NULL COMMENT '用户表业务ID',
  `COMPANY_NAME` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `PHONE` varchar(20) DEFAULT NULL COMMENT '手机号',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `POSITION` varchar(50) DEFAULT NULL COMMENT '职位',
  `TYPE` tinyint(4) DEFAULT NULL COMMENT '类别(1-诺亚，2-人形)',
  `IS_VALID` tinyint(4) DEFAULT NULL COMMENT '是否有效(1-有效，0-无效)',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

<!-- 新增课程表 -->
CREATE TABLE `D_TRAIN` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `TOPIC` varchar(255) DEFAULT NULL COMMENT '议题',
  `TIME` varchar(255) DEFAULT NULL COMMENT '时间',
  `TEACHER` varchar(255) DEFAULT NULL COMMENT '培训讲师',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '课程描述',
  `LOCATION` varchar(255) DEFAULT NULL COMMENT '地点',
  `WEBEX_URL` varchar(255) DEFAULT NULL COMMENT 'webex的url地址',
  `STATUS` tinyint(4) DEFAULT NULL COMMENT '1-未发送，2-已发送',
  `EMPLOYEE_IDS` text COMMENT '员工ID拼接，用于发送邮件',
  `MAIL_SUBJECT` varchar(255) DEFAULT NULL COMMENT '邮件主题',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

<!-- 新增API包表 -->
CREATE TABLE `D_API_PACKAGE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL COMMENT '文件别名',
  `TYPE` tinyint(4) DEFAULT NULL COMMENT '1-人机交互平台 2-业务应用平台 3-SDK开放平台',
  `URL` varchar(255) DEFAULT NULL COMMENT '文件下载地址',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '描述',
  `CREATE_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `UPDATE_TIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

