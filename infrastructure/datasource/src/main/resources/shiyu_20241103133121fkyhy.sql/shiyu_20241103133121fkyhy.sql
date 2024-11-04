-- MySQL dump 10.13  Distrib 8.4.3, for Linux (x86_64)
--
-- Host: localhost    Database: shiyu
-- ------------------------------------------------------
-- Server version	8.4.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sy_menu`
--

DROP TABLE IF EXISTS `sy_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sy_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT (now()) ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '上级id，根节点：0',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名',
  `type` smallint NOT NULL DEFAULT '0' COMMENT 'MENU/BUTTON',
  `uri` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '路径',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT 'code',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '图标',
  `path` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由',
  `component` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '/src... 地址',
  `layout` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所属布局',
  `method` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `show` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `enable` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `order` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` smallint NOT NULL DEFAULT (0) COMMENT '0：正常  ',
  `del_status` smallint NOT NULL DEFAULT '1' COMMENT '0：删除  1：正常  ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sy_menu`
--

LOCK TABLES `sy_menu` WRITE;
/*!40000 ALTER TABLE `sy_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sy_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sy_role`
--

DROP TABLE IF EXISTS `sy_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sy_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT (now()) ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'code',
  `permissionIds` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` smallint DEFAULT '0' COMMENT '0：正常  ',
  `del_status` smallint DEFAULT '1' COMMENT '0：删除  1：正常  ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sy_role`
--

LOCK TABLES `sy_role` WRITE;
/*!40000 ALTER TABLE `sy_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sy_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sy_role_menu`
--

DROP TABLE IF EXISTS `sy_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sy_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色菜单关联ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT (now()) ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  `del_status` smallint DEFAULT '1' COMMENT '0：删除  1：正常  ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色菜单关联';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sy_role_menu`
--

LOCK TABLES `sy_role_menu` WRITE;
/*!40000 ALTER TABLE `sy_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sy_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sy_user`
--

DROP TABLE IF EXISTS `sy_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sy_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT (now()) ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` smallint DEFAULT '0' COMMENT '0:活跃',
  `del_status` smallint DEFAULT '1' COMMENT '0：删除 1：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sy_user`
--

LOCK TABLES `sy_user` WRITE;
/*!40000 ALTER TABLE `sy_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `sy_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sy_user_profile`
--

DROP TABLE IF EXISTS `sy_user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sy_user_profile` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT (now()) ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `birthday` timestamp NULL DEFAULT NULL COMMENT '生日',
  `addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
  `gender` smallint DEFAULT '0' COMMENT '性别 0：保密 1：男 2：女',
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '个性签名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `ext_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '其他信息(权限/角色)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sy_user_profile`
--

LOCK TABLES `sy_user_profile` WRITE;
/*!40000 ALTER TABLE `sy_user_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `sy_user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sy_user_role`
--

DROP TABLE IF EXISTS `sy_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sy_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户角色关联ID',
  `create_time` datetime NOT NULL DEFAULT (now()) ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT (now()) COMMENT '更新时间',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `del_status` smallint DEFAULT '1' COMMENT '0：删除  1：正常  ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户角色关联';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sy_user_role`
--

LOCK TABLES `sy_user_role` WRITE;
/*!40000 ALTER TABLE `sy_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sy_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-03 13:31:21
