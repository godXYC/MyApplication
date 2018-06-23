-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: tg_demo
-- ------------------------------------------------------
-- Server version	5.7.22

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
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `good_name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `sell_price` double DEFAULT NULL,
  `original_price` double DEFAULT NULL,
  `good_brand` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `specification` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `series` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `product_time` date DEFAULT NULL,
  `warranty_period` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `storage` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `goods_number` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,'芒果榴莲味道油炸矿泉水',3.5,99,'芒果榴莲味道油炸矿泉水','600ML','5*600ML',NULL,'一天','中国日本省','95度以上冷藏','DM38256'),(2,'拍拍就爆炸清蒸果冻',8.3,34,'拍拍就爆炸清蒸果冻','100g','1*100g',NULL,'一小时','中国日本省','1000度以下高温储藏','DM38257');
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `comSn` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `pictureUrl` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `specification` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `sellPrice` float DEFAULT NULL,
  `payPrice` float DEFAULT NULL,
  `payStatus` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `orderSn` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `orderId` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `ogn_number` varchar(45) COLLATE utf8_bin NOT NULL,
  `orn_number` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,'水','20185180001',NULL,'23',37,8,'PAID',1,'1233','1','DM38256',NULL),(2,'芒果榴莲汽水','20185180002',NULL,'32',56,5,'PAID',2,'4422','1','DM38257',NULL),(3,'百事可悲','20185180001',NULL,'44',55,2,'PAID',1,'222','2','DM38257',NULL);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_list`
--

DROP TABLE IF EXISTS `order_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) COLLATE utf8_bin NOT NULL,
  `usedCredits` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `totalPrice` float DEFAULT NULL,
  `totalPayPrice` float DEFAULT NULL,
  `userName` varchar(225) COLLATE utf8_bin DEFAULT NULL,
  `wxNumber` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `boxName` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `degaussing` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `payTime` varchar(225) COLLATE utf8_bin DEFAULT NULL,
  `endTime` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `scanTime` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `createTime` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `orderStatus` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `syncTime` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_list`
--

LOCK TABLES `order_list` WRITE;
/*!40000 ALTER TABLE `order_list` DISABLE KEYS */;
INSERT INTO `order_list` VALUES (1,'20185180001','0',9,10,'Alice','12334','芒果是你发的刷卡缴费',NULL,'1526611700000','1526618724000','1526597128000','1526607893000','PAID',NULL),(2,'20185180002','3',4,5,'Adsfd','135666688888','鲜榨榴莲配臭豆腐汁订单',NULL,'1526611854334','1526619724000','1526598128000','1526609893000','PAID',NULL),(3,'20185180003','2',4,3,'we','1453456','亲亲下调',NULL,'1526611854334','1526619724000','1526598128000','1529692853000','PAID',NULL);
/*!40000 ALTER TABLE `order_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `head_picture` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone_number` varchar(45) COLLATE utf8_bin NOT NULL,
  `credit_picture` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `order_message` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,'18676488674',NULL,'18676488674',NULL,'123',NULL);
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_grade`
--

DROP TABLE IF EXISTS `user_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `telephone` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `workNumber` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `enteringTime` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `wxNumber` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `remainingCredits` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `usedMoney` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `usedCredits` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `updateTime` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `prepaidUserStatus` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `deleted` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_grade`
--

LOCK TABLES `user_grade` WRITE;
/*!40000 ALTER TABLE `user_grade` DISABLE KEYS */;
INSERT INTO `user_grade` VALUES (1,'Alice','15884566140','12233','1526608096000','12334','4','8','8','1526611540000','NORMAL','null');
/*!40000 ALTER TABLE `user_grade` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-23  9:15:48
