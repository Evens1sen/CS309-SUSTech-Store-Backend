-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mmall
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart`
(
    `id`          int      NOT NULL AUTO_INCREMENT,
    `product_id`  int DEFAULT NULL,
    `quantity`    int DEFAULT NULL,
    `cost`        int DEFAULT NULL,
    `user_id`     int DEFAULT NULL,
    `create_time` datetime NOT NULL,
    `update_time` datetime NOT NULL,
    PRIMARY KEY (`id`),
    KEY `productId` (`product_id`),
    KEY `userId` (`user_id`),
    CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
    CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`uid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 162
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart`
    DISABLE KEYS */;
INSERT INTO `cart`
VALUES (161, NULL, NULL, NULL, 10, '2020-05-15 16:07:05', '2020-05-15 16:08:37');
/*!40000 ALTER TABLE `cart`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders`
(
    `id`           int      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `buyer_id`     int               DEFAULT NULL COMMENT '买家id',
    `owner_id`     int               DEFAULT NULL COMMENT '卖家id',
    `user_address` varchar(255)      DEFAULT NULL COMMENT '用户地址',
    `product_id`   int      NOT NULL COMMENT '商品主键' REFERENCES product (id),
    `cost`         float             DEFAULT NULL COMMENT '总金额',
    `serialnumber` varchar(255)      DEFAULT NULL COMMENT '订单号',
    `status`       int      NOT NULL DEFAULT 0 COMMENT '订单状态',
    `create_time`  datetime          DEFAULT NULL COMMENT '创建时间',
    `update_time`  datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    CONSTRAINT `buyer_idfk` FOREIGN KEY (buyer_id) REFERENCES user (uid),
    CONSTRAINT `owner_idfk` FOREIGN KEY (owner_id) REFERENCES user (uid)
#     CONSTRAINT `addressfk` FOREIGN KEY (user_address) REFERENCES user_address (address)
) ENGINE = InnoDB
  AUTO_INCREMENT = 118
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders`
    DISABLE KEYS */;
INSERT INTO `orders`
VALUES (109, 11911001, 11912919, '软件园', 733, 1482, 'E96F8AF1E5E0D5BF1646F9344E31DA14', 0, '2019-10-22 21:20:24',
        '2019-10-22 21:20:24'),
       (110, 11911001, 11912919, '软件园', 734, 152, '6E9A8991CCCBDC6B9840BA0613B3C4E4', 0, '2019-10-22 21:21:49',
        '2019-10-22 21:21:49'),
       (111, 11911001, 11912919, '软件园', 735, 152, '18C4B6CCAE6F6CF9232DD97529B82E23', 0, '2019-10-23 01:15:25',
        '2019-10-23 01:15:25'),
       (112, 11911001, 11912919, 'newAddress', 736, 152, '69FF8CEBCC5F8DF67EA2BB56C45FA11E', 0, '2019-10-23 01:16:57',
        '2019-10-23 01:16:57');
/*!40000 ALTER TABLE `orders`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

# DROP TABLE IF EXISTS `order_detail`;
# /*!40101 SET @saved_cs_client = @@character_set_client */;
# /*!50503 SET character_set_client = utf8mb4 */;
# CREATE TABLE `order_detail`
# (
#     `id`         int   NOT NULL AUTO_INCREMENT COMMENT '主键',
#     `order_id`   int   NOT NULL COMMENT '订单主键',
#     `product_id` int   NOT NULL COMMENT '商品主键',
#     `quantity`   int   NOT NULL COMMENT '数量',
#     `cost`       float NOT NULL COMMENT '消费',
#     PRIMARY KEY (`id`),
#     UNIQUE KEY `PK__EASYBUY___66E1BD8E2F10007B` (`id`)
# ) ENGINE = InnoDB
#   AUTO_INCREMENT = 98
#   DEFAULT CHARSET = utf8;
# /*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

# LOCK TABLES `order_detail` WRITE;
# /*!40000 ALTER TABLE `order_detail`
#     DISABLE KEYS */;
# INSERT INTO `order_detail`
# VALUES (86, 109, 733, 2, 304),
#        (87, 109, 751, 2, 1178),
#        (88, 110, 733, 1, 152),
#        (89, 111, 733, 1, 152),
#        (90, 112, 733, 1, 152),
#        (91, 113, 733, 1, 152),
#        (92, 114, 733, 1, 152),
#        (93, 115, 733, 1, 152),
#        (94, 116, 734, 1, 152),
#        (95, 116, 745, 3, 1767),
#        (96, 116, 768, 1, 5896),
#        (97, 117, 742, 4, 632);
# /*!40000 ALTER TABLE `order_detail`
#     ENABLE KEYS */;
# UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product`
(
    `id`                    int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`                  varchar(200) NOT NULL COMMENT '名称',
    `description`           varchar(1024) DEFAULT NULL COMMENT '描述',
    `price`                 float        NOT NULL COMMENT '价格',
    `owner_id`              int           DEFAULT NULL COMMENT '卖家id' REFERENCES user (uid),
#     `stock`                 int          NOT NULL COMMENT '库存',
    `categorylevelone_id`   int           DEFAULT NULL COMMENT '分类1' REFERENCES product_category (id),
    `categoryleveltwo_id`   int           DEFAULT NULL COMMENT '分类2' REFERENCES product_category (id),
    `categorylevelthree_id` int           DEFAULT NULL COMMENT '分类3' REFERENCES product_category (id),
    `image`                 varchar(200)  DEFAULT NULL COMMENT '图片',
    `create_time`           datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time`           datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `PK__EASYBUY___94F6E55132E0915F` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 777
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--
LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product`
    DISABLE KEYS */;
INSERT INTO `product`
VALUES (733, '香奈儿', '', 152, 11912919, 548, 654, 655, 'baby_1.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (734, '洗面奶', '', 152, 11912919, 548, 654, 655, 'baby_2.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (735, '水啫喱水', '', 152, 11912919, 548, 654, 655, 'baby_3.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (736, '香水', '', 152, 11912919, 548, 654, 655, 'baby_4.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (737, '香水', '', 152, 11912919, 548, 654, 655, 'baby_5.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (738, '润肤露', '', 45, 11912919, 548, 654, 655, 'baby_6.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (739, '洁面装', '', 156, 11912919, 548, 654, 655, 'bk_2.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (740, '电饭锅', '', 158, 11912919, 628, 656, 659, 'bk_1.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (741, '婴儿喂奶装', '', 569, 11912919, 632, 637, 653, 'bk_3.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (742, '坚果套餐', '', 158, 11912919, 660, 661, 662, 'bk_4.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (743, '超甜蜜崭', '', 589, 11912919, 660, 661, 663, 'bk_5.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (744, '华为2566', '', 589, 11912919, 670, 671, 672, 'de1.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (745, '荣耀3C', '', 589, 11912919, 670, 671, 672, 'de2.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (746, '小米手环', '', 963, 11912919, 670, 674, 675, 'de3.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (747, '华为2265', '', 896, 11912919, 670, 671, 673, 'de4.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (748, '越南坚果', '', 520, 11912919, 660, 661, 662, 'de5.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (749, '日本进口马桶', '', 5866, 11912919, 628, 657, 0, 'food_1.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (750, '联想Y系列', '', 569, 11912919, 670, 690, 691, 'food_2.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (751, '脑白金1号', '', 589, 11912919, 676, 677, 680, 'food_3.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (752, '莫里斯按', '', 589, 11912919, 676, 678, 0, 'food_4.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (753, '三鹿好奶粉', '', 859, 11912919, 676, 679, 0, 'food_5.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (754, '儿童牛奶', '', 5896, 11912919, 676, 679, 0, 'food_6.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (755, '软沙发', '', 8596, 11912919, 628, 696, 0, 'food_b1.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (756, '收纳盒', '', 5966, 11912919, 628, 696, 0, 'food_b2.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (757, '洗衣液', '', 58, 11912919, 628, 696, 0, 'food_r.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (758, '红短沙发', '', 596, 11912919, 628, 696, 0, 'fre_1.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (759, '新西兰奶粉', '', 5896, 11912919, 676, 679, 0, 'fre_2.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (760, '婴儿车', '', 11000, 11912919, 681, 682, 687, 'fre_3.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (761, '夏款婴儿车', '', 963, 11912919, 681, 682, 688, 'fre_4.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (762, '抗压旅行箱', '', 569, 11912919, 681, 683, 685, 'fre_5.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (763, '透明手提箱', '', 8596, 11912919, 681, 683, 684, 'fre_6.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (764, '婴儿果粉', '', 5896, 11912919, 660, 661, 662, 'milk_1.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (765, '椰子粉', '', 5963, 11912919, 660, 661, 662, 'milk_2.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (766, '坚果蛋糕', '', 200, 11912919, 660, 661, 663, 'milk_3.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (767, '编制手提箱', '', 5896, 11912919, 681, 682, 688, 'milk_4.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (768, '纸箱', '', 5896, 11912919, 681, 682, 687, 'milk_5.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (769, '健胃液', '', 152, 11912919, 676, 679, 0, 'milk_6.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (770, '联想NTC', '', 8596, 11912919, 670, 671, 673, 'milk_7.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (771, '香水1', '', 100, 11912919, 548, 654, 655, 'milk_8.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (772, '香水2', '', 100, 11912919, 548, 654, 655, 'pro1.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (773, '香水3', '', 100, 11912919, 548, 654, 655, 'pro2.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (774, '香水4', '', 100, 11912919, 548, 654, 655, 'pro3.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (775, '香水5', '', 100, 11912919, 548, 654, 655, 'pro4.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00'),
       (776, '香水6', '', 1, 11912919, 548, 654, 655, 'pro5.jpg', '2021-11-22 21:00:00', '2021-11-22 21:00:00');
update product
set categorylevelone_id = 1;
update product
set categorylevelone_id = 2
where id > 760;
update product
set categorylevelone_id = 3
where id between 750 and 760;
update product
set categorylevelone_id = 4
where id between 740 and 750;
update product
set categoryleveltwo_id = 0;
update product
set categorylevelthree_id = 0;
/*!40000 ALTER TABLE `product`
    ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category`
(
    `id`        int         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`      varchar(20) NOT NULL COMMENT '名称',
    `parent_id` int         NOT NULL COMMENT '父级目录id',
    `type`      int DEFAULT NULL COMMENT '级别(1:一级 2：二级 3：三级)',
    PRIMARY KEY (`id`),
    UNIQUE KEY `PK__EASYBUY___9EC2A4E236B12243` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 697
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--
LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category`
    DISABLE KEYS */;
INSERT INTO `product_category`
VALUES (1, 'Study', 0, 1),
       (2, 'Food', 0, 1),
       (3, 'Electronic Device', 0, 1),
       (4, 'Living', 0, 1);
#        (628, '家用商品', 0, 1),
#        (654, '面部护理', 548, 2),
#        (655, '少女派', 654, 3),
#        (656, '餐具', 628, 2),
#        (657, '卫具', 628, 2),
#        (658, '叉子', 656, 3),
#        (659, '锅', 656, 3),
#        (660, '进口食品', 0, 1),
#        (661, '零食/糖果/巧克力', 660, 2),
#        (662, '坚果', 661, 3),
#        (663, '蜜饯', 661, 3),
#        (669, '孕期教育', 546, 3),
#        (670, '电子商品', 0, 1),
#        (671, '手机', 670, 2),
#        (672, '华为手机', 671, 3),
#        (673, '联想手机', 671, 3),
#        (674, '手环', 670, 2),
#        (675, '小米手环', 674, 3),
#        (676, '保健食品', 0, 1),
#        (677, '老年保健品', 676, 2),
#        (678, '中年营养品', 676, 2),
#        (679, '儿童保健品', 676, 2),
#        (680, '脑白金', 677, 3),
#        (681, '箱包', 0, 1),
#        (682, '旅行箱', 681, 2),
#        (683, '手提箱', 681, 2),
#        (684, '大型', 683, 3),
#        (685, '小型', 683, 3),
#        (686, '中型', 683, 3),
#        (687, '大型', 682, 3),
#        (688, '中型', 682, 3),
#        (689, '小型', 682, 3),
#        (690, '电脑', 670, 2),
#        (691, '联想电脑', 690, 3),
#        (692, '刀叉', 656, 3),
#        (693, '碗筷', 656, 3),
#        (696, '客厅专用', 628, 2);
/*!40000 ALTER TABLE `product_category`
    ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image`
(
    `id`          int      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `product_id`  int      NOT NULL COMMENT '商品id',
    `image`       varchar(200) DEFAULT NULL COMMENT '图片',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`product_id`) REFERENCES product (`id`),
    UNIQUE KEY `PK__EASYBUY___94F6E55132E0915F` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 777
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image`
    DISABLE KEYS */;
INSERT INTO `product_image`
VALUES (1, 733, 'test1.jpg', '2019-10-22 21:20:24', '2019-10-22 21:20:24'),
       (2, 734, 'test2.jpg', '2019-10-22 21:20:24', '2019-10-22 21:20:24'),
       (3, 734, 'test3.jpg', '2019-10-22 21:20:24', '2019-10-22 21:20:24');
/*!40000 ALTER TABLE `product_image`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
    `uid`         int          NOT NULL COMMENT '主键',
#     `user_name`   varchar(255) NOT NULL COMMENT '用户名',
    `nick_name`   varchar(255) NOT NULL COMMENT '昵称',
    `password`    varchar(255) NOT NULL COMMENT '密码',
    `gender`      int          NOT NULL DEFAULT '1' COMMENT '性别(1:男 0：女)',
    `email`       varchar(80)           DEFAULT NULL COMMENT '邮箱',
    `mobile`      varchar(11)           DEFAULT NULL COMMENT '手机',
    `icon`        varchar(255)          DEFAULT NULL COMMENT '头像',
    `balance`     float                 DEFAULT '0' COMMENT '余额',
    `credit`      int                   DEFAULT '100' COMMENT '信誉值',
    `create_time` datetime     NOT NULL,
    `update_time` datetime     NOT NULL,
    PRIMARY KEY (`uid`),
    UNIQUE KEY `PK__EASYBUY___C96109CC3A81B327` (`nick_name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 27
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
update user set credit = 100;
update user set balance = 10000;
update user set password = MD5(password);
--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user`
    DISABLE KEYS */;
INSERT INTO `user`
VALUES (11911001, 'admin', '123123', 0, 'hello11@bdqn.com', '1583233515', '7.jpg', 0, 100,
        '2020-05-18 06:22:27', '2020-05-18 06:22:32'),
       (11911002, 'cgn', '123', 1, '1044732267@qq.com', '13366055011', '2.jpg', 0, 100,
        '2020-05-18 06:22:34', '2020-05-18 06:22:37'),
       (11911003, 'hyl', '1231', 1, '1044732267@qq.com', '13366055010', '3.jpg', 0, 100,
        '2020-05-18 06:22:35', '2020-05-18 06:22:37'),
       (11911004, 'ck', '123', 1, '1044732267@qq.com', '13366055010', '4.jpg', 0, 100,
        '2020-05-18 06:22:36', '2020-05-18 06:22:38');
INSERT INTO `user`
    VALUE (11912919, 'mhy', '123', 1, '11912919@mail.sustech.edu.cn', '13366055010', '0.jpg', 648, 100,
           '2021-11-23 06:22:36', '2021-11-23 06:22:36');
/*!40000 ALTER TABLE `user`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_address`
--

DROP TABLE IF EXISTS `user_address`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_address`
(
    `id`          int      NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id`     int          DEFAULT NULL COMMENT '用户主键' REFERENCES user (uid),
    `address`     varchar(255) DEFAULT NULL COMMENT '地址',
    `remark`      varchar(255) DEFAULT NULL COMMENT '备注',
    `isdefault`   int          DEFAULT '0' COMMENT '是否是默认地址（1:是 0否）',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 48
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_address`
--

LOCK TABLES `user_address` WRITE;
/*!40000 ALTER TABLE `user_address`
    DISABLE KEYS */;
INSERT INTO `user_address`
VALUES (11, 10, '北京市海淀区大有庄', '朋友家', 0, '2019-06-03 02:32:39', '2019-06-03 02:32:39'),
       (12, 2, '北京市海淀区大有庄', '女朋友公司', 0, '2019-06-03 02:32:38', '2019-06-03 02:32:38'),
       (13, 23, '北京市西直门大桥芬兰国际大厦', '女朋友地址', 1, '2019-06-03 02:32:37', '2019-06-03 02:32:37'),
       (14, 23, '北京市花园路小区', '家里', 0, '2019-06-03 02:32:33', '2019-06-03 02:32:37'),
       (15, 10, '小寨西路', '学校', 0, '2019-05-30 20:21:32', '2019-06-03 02:32:37'),
       (35, 10, '软件园', '公司', 1, '2019-06-02 20:06:41', '2019-06-03 02:32:37'),
       (37, 23, '小寨西路', '学校', 0, '2019-06-20 23:26:46', '2019-06-03 02:32:37'),
       (41, 21, '软件园', '公司', 0, '2019-06-21 03:03:37', '2019-06-03 02:32:37'),
       (45, 10, '你好', '学校', 0, '2019-07-03 20:50:29', '2019-06-03 02:32:37'),
       (46, 10, '新三北', '公司', 0, '2019-07-03 20:52:33', '2019-06-03 02:32:37'),
       (47, 10, 'ddd', '123', 0, '2019-10-23 01:24:05', '2019-06-03 02:32:37');
/*!40000 ALTER TABLE `user_address`
    ENABLE KEYS */;
UNLOCK TABLES;

drop table if exists user_chat;

create table user_chat
(
    id        bigint auto_increment,
    buy_id    int          not null,
    sell_id   int          not null,
    line_text varchar(255) not null,
    create_at datetime     not null comment '时间',
    constraint user_chat_pk
        primary key (id),
    foreign key (buy_id) references user (uid),
    foreign key (sell_id) references user (uid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into user_chat value (1, 11911001, 11911002, 'sdasdafd', CURRENT_TIMESTAMP);
insert into user_chat value (2, 11911001, 11911003, 'sdasdafd', CURRENT_TIMESTAMP);


DROP TABLE IF EXISTS `errand`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `errand`
(
    `id`          int          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(200) NOT NULL COMMENT '名称',
    `description` varchar(1024) DEFAULT NULL COMMENT '描述',
    `price`       float        NOT NULL COMMENT '价格',
    `origin`      varchar(200) NOT NULL COMMENT '起始地点',
    `destination` varchar(200) NOT NULL COMMENT '结束地点',
    `owner_id`    int           DEFAULT NULL COMMENT '卖家id',
    `type`    int           DEFAULT NULL COMMENT '分类',
    `buyer_id`    int           DEFAULT NULL COMMENT '买家id' COMMENT '买家id',
    `status`      int           DEFAULT 0 COMMENT '跑腿状态',
    `image`       varchar(200)  DEFAULT NULL COMMENT '图片',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `PK__EASYBUY___94F6E55132E0915F` (`id`),
    FOREIGN KEY (owner_id) REFERENCES user (uid),
    FOREIGN KEY (buyer_id) REFERENCES user (uid)
) ENGINE = InnoDB
  AUTO_INCREMENT = 777
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `errand` WRITE;
/*!40000 ALTER TABLE `errand`
    DISABLE KEYS */;
INSERT INTO `errand`
VALUES (1, '给zmt送东西', '给zmt送100个麦麦脆汁鸡', 100, '15栋213', '荔园', 11912919, 1, 11912614, 0, '', '2019-06-03 02:32:39',
        '2019-06-03 02:32:39'),
       (2, '给mihoyo取钱', '冲648', 648, '璃月港', '15栋213', 11912919, 2, 11111111, 0, '', '2019-06-03 02:32:39',
        '2019-06-03 02:32:39');
/*!40000 ALTER TABLE `errand`
    ENABLE KEYS */;
UNLOCK TABLES;


/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2020-05-18 16:51:58
