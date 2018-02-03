CREATE DATABASE  IF NOT EXISTS `wpy_blog` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `wpy_blog`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: wpy_blog
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `blog_articleinfo`
--

DROP TABLE IF EXISTS `blog_articleinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_articleinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `artId` varchar(450) NOT NULL,
  `artTitle` varchar(450) NOT NULL,
  `artDetaInfo` text NOT NULL,
  `artTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_articleinfo`
--

LOCK TABLES `blog_articleinfo` WRITE;
/*!40000 ALTER TABLE `blog_articleinfo` DISABLE KEYS */;
INSERT INTO `blog_articleinfo` VALUES (1,'37e7d219111345138b9ca91eb70098bd','wpy','fdfsdfa','2018-01-19 00:07:16'),(2,'e4d355ccd63b4b3bbc924903529e4a6d','wpy','fdfsdfa','2018-01-19 00:10:30'),(3,'12318274038242e4b5c6c856d372d4d0','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:48:48'),(4,'d3ab85082cc74378935b220863592544','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:48:50'),(5,'85bcc0d5711a4b659136ce0d3d5184a7','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:48:51'),(6,'c6e14ae0826e419f851b73cd0b395b05','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:48:54'),(7,'cf865f59c1df4af3b31ffbe69335fb76','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:48:56'),(8,'627f661bcb5340acbda0dba2caf84bc8','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:48:58'),(9,'d2545a888e9744c1abb824a4aa80e50a','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:48:59'),(10,'c6cefb30a6c54db6800f60a35b09526e','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:49:01'),(11,'62db82e81b1149faa250caef9a8c3f97','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:49:11'),(12,'ab9acb8a149d4347a80954ec25e8b3d1','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:49:14'),(13,'c84d32c184d444809b9902adc85ba902','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:49:15'),(14,'2ec7c33fd3bb403699edc160e91e560d','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:49:17'),(15,'3888d932f7a843138448bb8644963b42','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:49:18'),(16,'c7d69013d81b434c83cd6ea24de7639f','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:49:20'),(17,'520de41b369f44bc8560ad77eda56ee8','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:49:21'),(18,'afead8895f424f1b9575462800e5933c','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:49:23'),(19,'0d92901678d848a3af047fed2d653439','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:49:24'),(20,'ba9080ac22b04eab99400bb2896b4dfa','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:49:25'),(21,'ca268094be524d0d840943c10c75292f','wpy','fdafasdflksjfklsdjfsaklfjksdlfsjalfa;ds','2018-01-19 09:50:21');
/*!40000 ALTER TABLE `blog_articleinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `wpy_blog`.`blog_articleinfo_AFTER_INSERT` AFTER INSERT ON `blog_articleinfo` FOR EACH ROW
BEGIN
	insert into blog_evaluate values(0,new.artId,0,0,0,sysdate());
    if length(new.artTitle) < 20 then
		insert into blog_brief values(0,new.artId,new.artTitle,"暂无更多简介....",sysdate());
	else
		insert into blog_brief values(0,new.artId,new.artTitle,substring(new.artDetaInfo,0,20),sysdate());
    end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `blog_brief`
--

DROP TABLE IF EXISTS `blog_brief`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_brief` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `artId` varchar(450) NOT NULL,
  `artTitle` varchar(45) NOT NULL,
  `artBrief` varchar(450) NOT NULL,
  `artTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_brief`
--

LOCK TABLES `blog_brief` WRITE;
/*!40000 ALTER TABLE `blog_brief` DISABLE KEYS */;
INSERT INTO `blog_brief` VALUES (1,'37e7d219111345138b9ca91eb70098bd','wpy','暂无更多简介....','2018-01-19 00:07:16'),(2,'e4d355ccd63b4b3bbc924903529e4a6d','wpy','暂无更多简介....','2018-01-19 00:10:30'),(3,'12318274038242e4b5c6c856d372d4d0','wpy','暂无更多简介....','2018-01-19 09:48:48'),(4,'d3ab85082cc74378935b220863592544','wpy','暂无更多简介....','2018-01-19 09:48:50'),(5,'85bcc0d5711a4b659136ce0d3d5184a7','wpy','暂无更多简介....','2018-01-19 09:48:51'),(6,'c6e14ae0826e419f851b73cd0b395b05','wpy','暂无更多简介....','2018-01-19 09:48:54'),(7,'cf865f59c1df4af3b31ffbe69335fb76','wpy','暂无更多简介....','2018-01-19 09:48:56'),(8,'627f661bcb5340acbda0dba2caf84bc8','wpy','暂无更多简介....','2018-01-19 09:48:58'),(9,'d2545a888e9744c1abb824a4aa80e50a','wpy','暂无更多简介....','2018-01-19 09:48:59'),(10,'c6cefb30a6c54db6800f60a35b09526e','wpy','暂无更多简介....','2018-01-19 09:49:01'),(11,'62db82e81b1149faa250caef9a8c3f97','wpy','暂无更多简介....','2018-01-19 09:49:11'),(12,'ab9acb8a149d4347a80954ec25e8b3d1','wpy','暂无更多简介....','2018-01-19 09:49:14'),(13,'c84d32c184d444809b9902adc85ba902','wpy','暂无更多简介....','2018-01-19 09:49:15'),(14,'2ec7c33fd3bb403699edc160e91e560d','wpy','暂无更多简介....','2018-01-19 09:49:17'),(15,'3888d932f7a843138448bb8644963b42','wpy','暂无更多简介....','2018-01-19 09:49:18'),(16,'c7d69013d81b434c83cd6ea24de7639f','wpy','暂无更多简介....','2018-01-19 09:49:20'),(17,'520de41b369f44bc8560ad77eda56ee8','wpy','暂无更多简介....','2018-01-19 09:49:21'),(18,'afead8895f424f1b9575462800e5933c','wpy','暂无更多简介....','2018-01-19 09:49:23'),(19,'0d92901678d848a3af047fed2d653439','wpy','暂无更多简介....','2018-01-19 09:49:24'),(20,'ba9080ac22b04eab99400bb2896b4dfa','wpy','暂无更多简介....','2018-01-19 09:49:25'),(21,'ca268094be524d0d840943c10c75292f','wpy','暂无更多简介....','2018-01-19 09:50:21');
/*!40000 ALTER TABLE `blog_brief` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_evaluate`
--

DROP TABLE IF EXISTS `blog_evaluate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_evaluate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `artId` varchar(450) NOT NULL,
  `evalThumbsUp` int(11) NOT NULL,
  `evalReadNumber` int(11) NOT NULL,
  `evalShareNumber` int(11) NOT NULL,
  `evalTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_evaluate`
--

LOCK TABLES `blog_evaluate` WRITE;
/*!40000 ALTER TABLE `blog_evaluate` DISABLE KEYS */;
INSERT INTO `blog_evaluate` VALUES (1,'37e7d219111345138b9ca91eb70098bd',0,0,0,'2018-01-19 00:07:16'),(2,'e4d355ccd63b4b3bbc924903529e4a6d',0,0,0,'2018-01-19 00:10:30'),(3,'12318274038242e4b5c6c856d372d4d0',0,0,0,'2018-01-19 09:48:48'),(4,'d3ab85082cc74378935b220863592544',0,0,0,'2018-01-19 09:48:50'),(5,'85bcc0d5711a4b659136ce0d3d5184a7',0,0,0,'2018-01-19 09:48:51'),(6,'c6e14ae0826e419f851b73cd0b395b05',0,0,0,'2018-01-19 09:48:54'),(7,'cf865f59c1df4af3b31ffbe69335fb76',0,0,0,'2018-01-19 09:48:56'),(8,'627f661bcb5340acbda0dba2caf84bc8',0,0,0,'2018-01-19 09:48:58'),(9,'d2545a888e9744c1abb824a4aa80e50a',0,0,0,'2018-01-19 09:48:59'),(10,'c6cefb30a6c54db6800f60a35b09526e',0,0,0,'2018-01-19 09:49:01'),(11,'62db82e81b1149faa250caef9a8c3f97',0,0,0,'2018-01-19 09:49:11'),(12,'ab9acb8a149d4347a80954ec25e8b3d1',0,0,0,'2018-01-19 09:49:14'),(13,'c84d32c184d444809b9902adc85ba902',0,0,0,'2018-01-19 09:49:15'),(14,'2ec7c33fd3bb403699edc160e91e560d',0,0,0,'2018-01-19 09:49:17'),(15,'3888d932f7a843138448bb8644963b42',0,0,0,'2018-01-19 09:49:18'),(16,'c7d69013d81b434c83cd6ea24de7639f',0,0,0,'2018-01-19 09:49:20'),(17,'520de41b369f44bc8560ad77eda56ee8',0,0,0,'2018-01-19 09:49:21'),(18,'afead8895f424f1b9575462800e5933c',0,0,0,'2018-01-19 09:49:23'),(19,'0d92901678d848a3af047fed2d653439',0,0,0,'2018-01-19 09:49:24'),(20,'ba9080ac22b04eab99400bb2896b4dfa',0,0,0,'2018-01-19 09:49:25'),(21,'ca268094be524d0d840943c10c75292f',0,0,0,'2018-01-19 09:50:21');
/*!40000 ALTER TABLE `blog_evaluate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_type`
--

DROP TABLE IF EXISTS `blog_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `artType` varchar(100) NOT NULL,
  `creTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_type`
--

LOCK TABLES `blog_type` WRITE;
/*!40000 ALTER TABLE `blog_type` DISABLE KEYS */;
INSERT INTO `blog_type` VALUES (1,'java','2018-01-19 00:07:16');
/*!40000 ALTER TABLE `blog_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_typearticle`
--

DROP TABLE IF EXISTS `blog_typearticle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blog_typearticle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeId` int(11) NOT NULL,
  `artId` varchar(450) NOT NULL,
  `creTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_typearticle`
--

LOCK TABLES `blog_typearticle` WRITE;
/*!40000 ALTER TABLE `blog_typearticle` DISABLE KEYS */;
INSERT INTO `blog_typearticle` VALUES (1,1,'37e7d219111345138b9ca91eb70098bd','2018-01-19 00:07:16'),(2,1,'e4d355ccd63b4b3bbc924903529e4a6d','2018-01-19 00:10:30'),(3,1,'12318274038242e4b5c6c856d372d4d0','2018-01-19 09:48:48'),(4,1,'d3ab85082cc74378935b220863592544','2018-01-19 09:48:50'),(5,1,'85bcc0d5711a4b659136ce0d3d5184a7','2018-01-19 09:48:51'),(6,1,'c6e14ae0826e419f851b73cd0b395b05','2018-01-19 09:48:54'),(7,1,'cf865f59c1df4af3b31ffbe69335fb76','2018-01-19 09:48:56'),(8,1,'627f661bcb5340acbda0dba2caf84bc8','2018-01-19 09:48:58'),(9,1,'d2545a888e9744c1abb824a4aa80e50a','2018-01-19 09:48:59'),(10,1,'c6cefb30a6c54db6800f60a35b09526e','2018-01-19 09:49:01'),(11,1,'62db82e81b1149faa250caef9a8c3f97','2018-01-19 09:49:11'),(12,1,'ab9acb8a149d4347a80954ec25e8b3d1','2018-01-19 09:49:14'),(13,1,'c84d32c184d444809b9902adc85ba902','2018-01-19 09:49:15'),(14,1,'2ec7c33fd3bb403699edc160e91e560d','2018-01-19 09:49:17'),(15,1,'3888d932f7a843138448bb8644963b42','2018-01-19 09:49:18'),(16,1,'c7d69013d81b434c83cd6ea24de7639f','2018-01-19 09:49:20'),(17,1,'520de41b369f44bc8560ad77eda56ee8','2018-01-19 09:49:21'),(18,1,'afead8895f424f1b9575462800e5933c','2018-01-19 09:49:23'),(19,1,'0d92901678d848a3af047fed2d653439','2018-01-19 09:49:24'),(20,1,'ba9080ac22b04eab99400bb2896b4dfa','2018-01-19 09:49:25'),(21,1,'ca268094be524d0d840943c10c75292f','2018-01-19 09:50:21');
/*!40000 ALTER TABLE `blog_typearticle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'wpy_blog'
--

--
-- Dumping routines for database 'wpy_blog'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-03 10:04:08
