-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.4.3 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 shiyu 的数据库结构
CREATE DATABASE IF NOT EXISTS `shiyu` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `shiyu`;

-- 导出  表 shiyu.sy_menu 结构
CREATE TABLE IF NOT EXISTS `sy_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT (now()) ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '上级id，根节点：0',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'MENU/BUTTON',
  `uri` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '路径',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT 'code',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '图标',
  `route_path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由',
  `component` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '/src... 地址',
  `layout` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所属布局',
  `method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `redirect` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `keep_alive` tinyint DEFAULT NULL COMMENT '0:false 1:true',
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `sort` int DEFAULT NULL COMMENT '排序',
  `show` smallint NOT NULL DEFAULT '0' COMMENT '0：显示 1:隐藏',
  `status` smallint NOT NULL DEFAULT '0' COMMENT '0：显示 1:隐藏',
  `del_status` tinyint NOT NULL DEFAULT (1) COMMENT '0：删除  1：正常  ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单';

-- 正在导出表  shiyu.sy_menu 的数据：~15 rows (大约)
INSERT INTO `sy_menu` (`id`, `create_time`, `update_time`, `parent_id`, `name`, `type`, `uri`, `code`, `icon`, `route_path`, `component`, `layout`, `method`, `redirect`, `keep_alive`, `description`, `sort`, `show`, `status`, `del_status`) VALUES
	(1, '2024-11-16 20:23:06', '2024-11-16 20:29:46', 0, '系统管理', 'MENU', '', 'SysMgt', 'i-fe:grid', '', '', '', '', NULL, NULL, '', 2, 0, 0, 1),
	(2, '2024-11-16 20:27:19', '2024-11-16 20:27:19', 0, '个人资料', 'MENU', '', 'UserProfile', 'i-fe:user', '/profile', '/src/views/profile/index.vue', '', '', NULL, NULL, '', 99, 0, 0, 1),
	(3, '2024-11-16 20:28:43', '2024-11-16 20:28:43', 0, '基础功能', 'MENU', '', 'Base', 'i-fe:grid', NULL, NULL, '', '', NULL, NULL, '', 0, 0, 0, 1),
	(4, '2024-11-16 20:29:28', '2024-11-16 20:29:28', 0, '业务示例', 'MENU', '', 'Demo', 'i-fe:grid', NULL, NULL, '', '', NULL, NULL, '', 1, 0, 0, 1),
	(5, '2024-11-16 20:31:52', '2024-11-16 20:31:52', 1, '资源管理', 'MENU', '', 'Resource_Mgt', 'i-fe:list', '/pms/resource', '/src/views/pms/resource/index.vue', '', '', NULL, NULL, '', 1, 0, 0, 1),
	(6, '2024-11-16 20:32:37', '2024-11-16 20:54:14', 1, '角色管理', 'MENU', '', 'RoleMgt', 'i-fe:user-check', '/pms/role', '/src/views/pms/role/index.vue', '', '', NULL, 0, '', 2, 0, 0, 1),
	(7, '2024-11-16 20:33:24', '2024-11-16 20:48:40', 1, '用户管理', 'MENU', '', 'UserMgt', 'i-fe:user', '/pms/user', '/src/views/pms/user/index.vue', '', '', NULL, 1, '', 3, 0, 0, 1),
	(8, '2024-11-16 20:35:35', '2024-12-28 16:50:56', 6, '分配用户', 'MENU', '', 'RoleUser', 'i-fe:user-plus', '/pms/role/user/:roleId', '/src/views/pms/role/role-user.vue', 'full', '', NULL, 0, '', 1, 1, 0, 1),
	(9, '2024-11-16 20:36:14', '2024-11-16 20:52:14', 7, '创建新用户', 'BUTTON', '', 'AddUser', 'i-fe:user-plus', '/pms/role/user/:roleId', '/src/views/pms/role/role-user.vue', '', '', NULL, 0, '', 1, 0, 0, 1),
	(10, '2024-11-16 20:39:15', '2024-11-16 20:48:23', 4, '图片上传', 'MENU', '', 'ImgUpload', 'i-fe:image', '/demo/upload', '/src/views/demo/upload/index.vue', 'simple', '', NULL, 1, '', 1, 0, 0, 1),
	(11, '2024-11-16 20:40:20', '2024-11-16 20:40:20', 3, '基础组件', 'MENU', '', 'BaseComponents', 'i-me:awesome', '/base/components', '/src/views/base/index.vue', 'simple', '', NULL, NULL, '', 1, 0, 0, 1),
	(12, '2024-11-16 20:40:56', '2024-11-16 20:40:56', 3, 'Unocss', 'MENU', '', 'Unocss', 'i-me:awesome', '/base/unocss', '/src/views/base/unocss.vue', '', '', NULL, NULL, '', 2, 0, 0, 1),
	(13, '2024-11-16 20:41:30', '2024-11-16 20:47:32', 3, 'KeepAlive', 'MENU', '', 'KeepAlive', 'i-me:awesome', '/base/keep-alive', '/src/views/base/keep-alive.vue', '', '', NULL, 1, '', 3, 0, 0, 1),
	(14, '2024-11-16 20:42:14', '2024-11-16 20:47:05', 3, 'MeModal', 'MENU', '', 'TestModal', 'i-me:dialog', '/testModal', '/src/views/base/test-modal.vue', '', '', NULL, NULL, '', 4, 0, 0, 1),
	(15, '2024-11-16 20:43:13', '2024-11-16 20:43:13', 3, '图标 Icon', 'MENU', '', 'Icon', 'i-fe:feather', '/base/icon', '/src/views/base/unocss-icon.vue', '', '', NULL, NULL, '', 0, 0, 0, 1);

-- 导出  表 shiyu.sy_role 结构
CREATE TABLE IF NOT EXISTS `sy_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT (now()) ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'code',
  `permission_ids` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` smallint DEFAULT '0' COMMENT '0：正常  ',
  `del_status` smallint DEFAULT '1' COMMENT '0：删除  1：正常  ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色';

-- 正在导出表  shiyu.sy_role 的数据：~2 rows (大约)
INSERT INTO `sy_role` (`id`, `create_time`, `update_time`, `name`, `code`, `permission_ids`, `status`, `del_status`) VALUES
	(1, '2024-11-16 19:33:15', '2024-12-27 21:14:49', '超级管理员', 'SUPER_ADMIN', NULL, 0, 1),
	(2, '2024-11-16 19:33:47', '2024-12-27 21:14:58', '系统管理员', 'SYS_ADMIN', NULL, 0, 1);

-- 导出  表 shiyu.sy_role_menu 结构
CREATE TABLE IF NOT EXISTS `sy_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色菜单关联ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT (now()) ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  `del_status` smallint DEFAULT '1' COMMENT '0：删除  1：正常  ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色菜单关联';

-- 正在导出表  shiyu.sy_role_menu 的数据：~30 rows (大约)
INSERT INTO `sy_role_menu` (`id`, `create_time`, `update_time`, `role_id`, `menu_id`, `del_status`) VALUES
	(1, '2024-12-15 21:33:29', '2024-12-15 21:33:29', 1, 1, 1),
	(2, '2024-12-25 22:24:31', '2024-12-25 22:24:31', 1, 2, 1),
	(3, '2024-12-25 22:24:40', '2024-12-25 22:24:40', 1, 3, 1),
	(4, '2024-12-25 22:24:44', '2024-12-25 22:24:44', 1, 4, 1),
	(5, '2024-12-25 22:24:49', '2024-12-25 22:24:49', 1, 5, 1),
	(6, '2024-12-25 22:24:56', '2024-12-25 22:24:56', 1, 6, 1),
	(7, '2024-12-25 22:25:01', '2024-12-25 22:25:01', 1, 7, 1),
	(8, '2024-12-25 22:25:05', '2024-12-25 22:25:05', 1, 8, 1),
	(9, '2024-12-25 22:25:11', '2024-12-25 22:25:11', 1, 9, 1),
	(10, '2024-12-25 22:25:15', '2024-12-25 22:25:27', 1, 10, 1),
	(11, '2024-12-25 22:25:24', '2024-12-25 22:25:29', 1, 11, 1),
	(12, '2024-12-25 22:25:34', '2024-12-25 22:25:34', 1, 12, 1),
	(13, '2024-12-25 22:25:38', '2024-12-25 22:25:38', 1, 13, 1),
	(14, '2024-12-25 22:25:43', '2024-12-25 22:25:43', 1, 14, 1),
	(15, '2024-12-25 22:25:48', '2024-12-25 22:25:48', 1, 15, 1),
	(17, '2024-12-28 21:37:20', '2024-12-28 21:38:20', 2, 3, 1),
	(18, '2024-12-28 21:37:22', '2024-12-28 21:38:20', 2, 15, 1),
	(19, '2024-12-28 21:37:24', '2024-12-28 21:38:20', 2, 11, 1),
	(20, '2024-12-28 21:37:24', '2024-12-28 21:38:20', 2, 12, 1),
	(21, '2024-12-28 21:37:24', '2024-12-28 21:38:20', 2, 13, 1),
	(22, '2024-12-28 21:37:24', '2024-12-28 21:38:20', 2, 14, 1),
	(23, '2024-12-28 21:37:24', '2024-12-28 21:38:20', 2, 4, 1),
	(24, '2024-12-28 21:37:24', '2024-12-28 21:38:20', 2, 10, 1),
	(25, '2024-12-28 21:37:24', '2024-12-28 21:38:20', 2, 1, 1),
	(26, '2024-12-28 21:37:24', '2024-12-28 21:38:20', 2, 5, 1),
	(27, '2024-12-28 21:37:24', '2024-12-28 21:38:20', 2, 6, 1),
	(28, '2024-12-28 21:37:24', '2024-12-28 21:38:20', 2, 8, 1),
	(29, '2024-12-28 21:37:24', '2024-12-28 21:38:20', 2, 7, 1),
	(30, '2024-12-28 21:37:24', '2024-12-28 21:38:20', 2, 9, 1),
	(31, '2024-12-28 21:37:24', '2024-12-28 21:38:20', 2, 2, 1);

-- 导出  表 shiyu.sy_user 结构
CREATE TABLE IF NOT EXISTS `sy_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT (now()) ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
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
  `status` smallint DEFAULT '0' COMMENT '0:活跃',
  `del_status` smallint DEFAULT '1' COMMENT '0：删除 1：正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户';

-- 正在导出表  shiyu.sy_user 的数据：~2 rows (大约)
INSERT INTO `sy_user` (`id`, `create_time`, `update_time`, `username`, `nick_name`, `avatar`, `phone`, `email`, `birthday`, `addr`, `gender`, `signature`, `password`, `ext_info`, `status`, `del_status`) VALUES
	(1, '2024-11-16 19:32:12', '2024-12-28 16:34:12', 'shiyu', 'shiyu', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80', '5513126', 'ewaf@qq.com', '2024-11-16 11:32:13', NULL, 1, '', '$2a$10$SgYZiWLPRIvU9N/uiSkSFu0V5x7oM.jgtrQb3iKZJC2s4hYYRFEiy', '', 0, 1),
	(2, '2024-11-16 19:32:12', '2024-12-28 21:56:27', 'admin', 'admin', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80', '5513126', 'ewaf@qq.com', '2024-11-16 03:32:13', NULL, 1, '', '$2a$10$SgYZiWLPRIvU9N/uiSkSFu0V5x7oM.jgtrQb3iKZJC2s4hYYRFEiy', '', 0, 1);

-- 导出  表 shiyu.sy_user_role 结构
CREATE TABLE IF NOT EXISTS `sy_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户角色关联ID',
  `create_time` datetime NOT NULL DEFAULT (now()) ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT (now()) COMMENT '更新时间',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `del_status` smallint DEFAULT '1' COMMENT '0：删除  1：正常  ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户角色关联';

-- 正在导出表  shiyu.sy_user_role 的数据：~3 rows (大约)
INSERT INTO `sy_user_role` (`id`, `create_time`, `update_time`, `user_id`, `role_id`, `del_status`) VALUES
	(1, '2024-12-15 21:33:16', '2024-12-15 21:33:16', 1, 1, 1),
	(2, '2024-12-28 17:12:58', '2024-12-28 17:12:58', 2, 1, 1),
	(3, '2024-12-28 17:13:14', '2024-12-28 17:13:14', 1, 2, 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
