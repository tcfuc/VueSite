-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: demo
-- ------------------------------------------------------
-- Server version	5.7.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `site_sys_area`
--

DROP TABLE IF EXISTS `site_sys_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site_sys_area` (
  `area_code` int(11) NOT NULL AUTO_INCREMENT COMMENT '区域编码',
  `parent_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '父级编号',
  `tree_leaf` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '1' COMMENT '是否最末级',
  `area_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '区域名称',
  `area_type` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区域类型',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`area_code`),
  KEY `idx_sys_area_pc` (`parent_code`),
  KEY `idx_sys_area_status` (`status`)
) ENGINE=MyISAM AUTO_INCREMENT=3700009 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='行政区划';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_sys_area`
--

LOCK TABLES `site_sys_area` WRITE;
/*!40000 ALTER TABLE `site_sys_area` DISABLE KEYS */;
INSERT INTO `site_sys_area` VALUES (370000,'0','0','山东省','1','0','system','2019-01-04 18:35:36','system','2019-01-04 18:35:36',NULL),(370100,'370000','0','济南市','2','0','system','2019-01-04 18:35:37','system','2019-01-04 18:35:37',NULL),(370200,'370000','0','青岛市','2','0','system','2019-01-04 18:35:37','system','2019-01-04 18:35:37',NULL),(370102,'370100','1','历下区','3','0','system','2019-01-04 18:35:37','system','2019-01-04 18:35:37',NULL),(370103,'370100','1','市中区','3','0','system','2019-01-04 18:35:37','system','2019-01-04 18:35:37',NULL),(370104,'370100','1','槐荫区','3','0','system','2019-01-04 18:35:37','system','2019-01-04 18:35:37',NULL),(370105,'370100','1','天桥区','3','0','system','2019-01-04 18:35:37','system','2019-01-04 18:35:37',NULL),(370112,'370100','1','历城区','3','0','system','2019-01-04 18:35:37','system','2019-01-04 18:35:37',NULL),(370113,'370100','1','长清区','3','0','system','2019-01-04 18:35:37','system','2019-01-04 18:35:37',NULL),(370114,'370100','1','章丘区','3','0','system','2019-01-04 18:35:37','system','2019-01-04 18:35:37',NULL),(370124,'370100','1','平阴县','3','0','system','2019-01-04 18:35:37','system','2019-01-04 18:35:37',NULL),(370125,'370100','1','济阳县','3','0','system','2019-01-04 18:35:37','system','2019-01-04 18:35:37',NULL),(370126,'370100','1','商河县','3','0','system','2019-01-04 18:35:37','system','2019-01-04 18:35:37',NULL),(370202,'370200','1','市南区','3','0','system','2019-01-04 18:35:37','system','2019-01-04 18:35:38',NULL),(370203,'370200','1','市北区','3','0','system','2019-01-04 18:35:38','system','2019-01-04 18:35:38',NULL),(370211,'370200','1','黄岛区','3','0','system','2019-01-04 18:35:38','system','2019-01-04 18:35:38',NULL),(370212,'370200','1','崂山区','3','0','system','2019-01-04 18:35:38','system','2019-01-04 18:35:38',NULL),(370213,'370200','1','李沧区','3','0','system','2019-01-04 18:35:38','system','2019-01-04 18:35:38',NULL),(370214,'370200','1','城阳区','3','0','system','2019-01-04 18:35:38','system','2019-01-04 18:35:38',NULL),(370281,'370200','1','胶州市','3','0','system','2019-01-04 18:35:38','system','2019-01-04 18:35:38',NULL),(370282,'370200','1','即墨区','3','0','system','2019-01-04 18:35:38','system','2019-01-04 18:35:38',NULL),(370283,'370200','1','平度市','3','0','system','2019-01-04 18:35:38','system','2019-01-04 18:35:38',NULL),(370285,'370200','1','莱西市','3','0','system','2019-01-04 18:35:38','system','2019-01-04 18:35:38',NULL),(3700001,'370000','0','枣庄','2','0',NULL,NULL,NULL,NULL,NULL),(3700002,'370000','1','烟台市','2','0',NULL,NULL,NULL,NULL,NULL),(3700003,'3700001','1','1','3','1',NULL,NULL,NULL,NULL,NULL),(3700004,'3700001','1','2','2','1',NULL,NULL,NULL,NULL,NULL),(3700005,'3700001','1','3','3','1',NULL,NULL,NULL,NULL,NULL),(3700006,'0','1','湖南省','1','0',NULL,NULL,NULL,NULL,NULL),(3700007,'0','1','湖北省','1','0',NULL,NULL,NULL,NULL,NULL),(3700008,'0','1','1','2','0',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `site_sys_area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_sys_area_company`
--

DROP TABLE IF EXISTS `site_sys_area_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site_sys_area_company` (
  `area_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '区域编码',
  `company_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公司编码',
  PRIMARY KEY (`area_code`,`company_code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='区域公司关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_sys_area_company`
--

LOCK TABLES `site_sys_area_company` WRITE;
/*!40000 ALTER TABLE `site_sys_area_company` DISABLE KEYS */;
/*!40000 ALTER TABLE `site_sys_area_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_sys_company`
--

DROP TABLE IF EXISTS `site_sys_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site_sys_company` (
  `company_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公司编码',
  `parent_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '父级编号',
  `tree_leaf` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '是否最末级',
  `company_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公司名称',
  `full_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公司全称',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`company_code`),
  KEY `idx_sys_company_pc` (`parent_code`),
  KEY `idx_sys_company_status` (`status`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公司表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_sys_company`
--

LOCK TABLES `site_sys_company` WRITE;
/*!40000 ALTER TABLE `site_sys_company` DISABLE KEYS */;
INSERT INTO `site_sys_company` VALUES ('SD','0','0','山东公司','山东公司','0','system','2019-01-04 18:37:16','system','2019-01-04 18:37:16',NULL),('SDJN','SD','1','济南公司','山东济南公司','0','system','2019-01-04 18:37:17','system','2019-01-04 18:37:17',NULL),('SDQD','SD','1','青岛公司','山东青岛公司','0','system','2019-01-04 18:37:18','system','2019-01-04 18:37:18',NULL);
/*!40000 ALTER TABLE `site_sys_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_sys_company_office`
--

DROP TABLE IF EXISTS `site_sys_company_office`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site_sys_company_office` (
  `company_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公司编码',
  `office_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机构编码',
  PRIMARY KEY (`company_code`,`office_code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公司部门关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_sys_company_office`
--

LOCK TABLES `site_sys_company_office` WRITE;
/*!40000 ALTER TABLE `site_sys_company_office` DISABLE KEYS */;
/*!40000 ALTER TABLE `site_sys_company_office` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_sys_office`
--

DROP TABLE IF EXISTS `site_sys_office`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site_sys_office` (
  `office_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机构编码',
  `parent_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '父级编号',
  `tree_leaf` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '是否最末级',
  `office_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机构名称',
  `full_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机构全称',
  `office_type` char(1) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机构类型',
  `leader` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '负责人',
  `phone` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '办公电话',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系地址',
  `zip_code` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮政编码',
  `email` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电子邮箱',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`office_code`),
  KEY `idx_sys_office_pc` (`parent_code`),
  KEY `idx_sys_office_status` (`status`),
  KEY `idx_sys_office_ot` (`office_type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='组织机构表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_sys_office`
--

LOCK TABLES `site_sys_office` WRITE;
/*!40000 ALTER TABLE `site_sys_office` DISABLE KEYS */;
INSERT INTO `site_sys_office` VALUES ('SD','0','0','山东公司','山东公司','1',NULL,NULL,NULL,NULL,NULL,'0','system','2019-01-04 18:37:10','system','2019-01-04 18:37:10',NULL),('SDJN','SD','0','济南公司','山东济南公司','2',NULL,NULL,NULL,NULL,NULL,'1','system','2019-01-04 18:37:11','system','2019-01-04 18:37:11',NULL),('SDJN01','SDJN','1','企管部','山东济南企管部','3',NULL,NULL,NULL,NULL,NULL,'0','system','2019-01-04 18:37:12','system','2019-01-04 18:37:12',NULL),('SDJN02','SDJN','1','财务部','山东济南财务部','3',NULL,NULL,NULL,NULL,NULL,'0','system','2019-01-04 18:37:12','system','2019-01-04 18:37:12',NULL),('SDJN03','SDJN','1','研发部','山东济南研发部','3',NULL,NULL,NULL,NULL,NULL,'0','system','2019-01-04 18:37:13','system','2019-01-04 18:37:13',NULL),('SDQD','SD','0','青岛公司','山东青岛公司','2',NULL,NULL,NULL,NULL,NULL,'0','system','2019-01-04 18:37:14','system','2019-01-04 18:37:14',NULL),('SDQD01','SDQD','1','企管部','山东青岛企管部','3',NULL,NULL,NULL,NULL,NULL,'0','system','2019-01-04 18:37:14','system','2019-01-04 18:37:14',NULL),('SDQD02','SDQD','1','财务部','山东青岛财务部','3',NULL,NULL,NULL,NULL,NULL,'0','system','2019-01-04 18:37:15','system','2019-01-04 18:37:15',NULL),('SDQD03','SDQD','1','研发部','山东青岛研发部','3',NULL,NULL,NULL,NULL,NULL,'0','system','2019-01-04 18:37:16','system','2019-01-04 18:37:16',NULL);
/*!40000 ALTER TABLE `site_sys_office` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_sys_office_post`
--

DROP TABLE IF EXISTS `site_sys_office_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site_sys_office_post` (
  `office_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机构编码',
  `post_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位编码',
  PRIMARY KEY (`office_code`,`post_code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='机构岗位关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_sys_office_post`
--

LOCK TABLES `site_sys_office_post` WRITE;
/*!40000 ALTER TABLE `site_sys_office_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `site_sys_office_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_sys_post`
--

DROP TABLE IF EXISTS `site_sys_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site_sys_post` (
  `post_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位名称',
  `post_type` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '岗位分类（高管、中层、基层）',
  `post_sort` decimal(10,0) DEFAULT NULL COMMENT '岗位排序（升序）',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`post_code`),
  KEY `idx_sys_post_status` (`status`),
  KEY `idx_sys_post_ps` (`post_sort`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='员工岗位表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_sys_post`
--

LOCK TABLES `site_sys_post` WRITE;
/*!40000 ALTER TABLE `site_sys_post` DISABLE KEYS */;
INSERT INTO `site_sys_post` VALUES ('ceo','总经理',NULL,1,'0','system','2019-01-04 18:37:18','system','2019-01-04 18:37:18',NULL),('cfo','财务经理',NULL,2,'0','system','2019-01-04 18:37:18','system','2019-01-04 18:37:18',NULL),('hrm','人力经理',NULL,2,'0','system','2019-01-04 18:37:18','system','2019-01-04 18:37:18',NULL),('dept','部门经理',NULL,2,'0','system','2019-01-04 18:37:18','system','2019-01-04 18:37:18',NULL),('user','普通员工',NULL,3,'0','system','2019-01-04 18:37:18','system','2019-01-04 18:37:18',NULL);
/*!40000 ALTER TABLE `site_sys_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_sys_post_user`
--

DROP TABLE IF EXISTS `site_sys_post_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site_sys_post_user` (
  `post_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位编码',
  `user_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户编码',
  PRIMARY KEY (`post_code`,`user_code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='岗位用户关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_sys_post_user`
--

LOCK TABLES `site_sys_post_user` WRITE;
/*!40000 ALTER TABLE `site_sys_post_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `site_sys_post_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_sys_user`
--

DROP TABLE IF EXISTS `site_sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site_sys_user` (
  `user_code` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户编码',
  `login_code` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登录账号',
  `user_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户昵称',
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '123456' COMMENT '登录密码',
  `email` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电子邮箱',
  `mobile` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号码',
  `phone` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '办公电话',
  `mgr_type` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '管理员类型（0非管理员 1系统管理员  2二级管理员）',
  `last_login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `status` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用 3冻结）',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`user_code`),
  KEY `idx_sys_user_lc` (`login_code`),
  KEY `idx_sys_user_mobile` (`mobile`),
  KEY `idx_sys_user_mt` (`mgr_type`),
  KEY `idx_sys_user_ud` (`update_date`),
  KEY `idx_sys_user_status` (`status`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_sys_user`
--

LOCK TABLES `site_sys_user` WRITE;
/*!40000 ALTER TABLE `site_sys_user` DISABLE KEYS */;
INSERT INTO `site_sys_user` VALUES ('admin','admin','admin','123','1141614570@qq.com','15084845632','15084845632','1',NULL,'0',NULL,NULL,NULL,NULL,NULL),('system','system','超级管理员','123','','','','1','2019-01-04 18:48:34','0','system','2019-01-04 18:37:07','system','2019-01-04 18:37:10','开发者使用的最高级别管理员，主要用于开发和调试。'),('admin1','admin1','系统管理员','123',NULL,NULL,NULL,'1',NULL,'0','system','2019-01-04 18:37:10','system','2019-01-04 18:37:10','客户方使用的系统管理员，用于一些常用的基础数据配置。'),('user1','user1','用户01','123','user@test.com','18555555555','053188888888','0',NULL,'0','system','2019-01-04 18:37:19','system','2019-01-04 18:37:19',NULL),('user2','user2','用户02','123','user@test.com','18555555555','053188888888','0',NULL,'0','system','2019-01-04 18:37:20','system','2019-01-04 18:37:20',NULL),('user3','user3','用户03','123','user@test.com','18555555555','053188888888','0',NULL,'0','system','2019-01-04 18:37:22','system','2019-01-04 18:37:22',NULL),('user4','user4','用户04','123','user@test.com','18555555555','053188888888','0',NULL,'0','system','2019-01-04 18:37:23','system','2019-01-04 18:37:23',NULL),('user5','user5','用户05','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:24','system','2019-01-04 18:37:24',NULL),('user6','user6','用户06','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:25','system','2019-01-04 18:37:25',NULL),('user7','user7','用户07','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:26','system','2019-01-04 18:37:26',NULL),('user8','user8','用户08','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:28','system','2019-01-04 18:37:28',NULL),('user9','user9','用户09','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:29','system','2019-01-04 18:37:29',NULL),('user10','user10','用户10','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:30','system','2019-01-04 18:37:30',NULL),('user11','user11','用户11','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:31','system','2019-01-04 18:37:31',NULL),('user12','user12','用户12','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:32','system','2019-01-04 18:37:32',NULL),('user13','user13','用户13','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:33','system','2019-01-04 18:37:33',NULL),('user14','user14','用户14','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:34','system','2019-01-04 18:37:34',NULL),('user15','user15','用户15','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:36','system','2019-01-04 18:37:36',NULL),('user16','user16','用户16','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:37','system','2019-01-04 18:37:37',NULL),('user17','user17','用户17','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:38','system','2019-01-04 18:37:38',NULL),('user18','user18','用户18','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:39','system','2019-01-04 18:37:39',NULL),('user19','user19','用户19','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:40','system','2019-01-04 18:37:40',NULL),('user20','user20','用户20','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:42','system','2019-01-04 18:37:42',NULL),('user21','user21','用户21','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:43','system','2019-01-04 18:37:43',NULL),('user22','user22','用户22','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:44','system','2019-01-04 18:37:44',NULL),('user23','user23','用户23','123','user@test.com','18555555555','053188888888','2',NULL,'0','system','2019-01-04 18:37:45','system','2019-01-04 18:37:45',NULL);
/*!40000 ALTER TABLE `site_sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'demo'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-18 22:00:04
