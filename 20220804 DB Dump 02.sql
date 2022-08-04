-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: i7d108.p.ssafy.io    Database: sduty
-- ------------------------------------------------------
-- Server version	8.0.30-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `achievement`
--

DROP TABLE IF EXISTS `achievement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `achievement` (
  `achievement_seq` int NOT NULL AUTO_INCREMENT,
  `achievement_name` varchar(45) NOT NULL,
  `achievement_content` varchar(100) NOT NULL,
  `achievement_is_hidden` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`achievement_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achievement`
--

LOCK TABLES `achievement` WRITE;
/*!40000 ALTER TABLE `achievement` DISABLE KEYS */;
INSERT INTO `achievement` VALUES (1,'Starter','Study Starter',0),(2,'Junior','Study Junior',0);
/*!40000 ALTER TABLE `achievement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_seq` int NOT NULL AUTO_INCREMENT,
  `admin_id` varchar(45) NOT NULL,
  `admin_password` varchar(45) NOT NULL,
  `admin_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`admin_seq`),
  UNIQUE KEY `admin_id_UNIQUE` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'sduty','123','관리자');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alarm`
--

DROP TABLE IF EXISTS `alarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alarm` (
  `alarm_seq` int NOT NULL AUTO_INCREMENT,
  `alarm_time` time NOT NULL,
  `mon` tinyint NOT NULL DEFAULT '0',
  `tue` tinyint NOT NULL DEFAULT '0',
  `wed` tinyint NOT NULL DEFAULT '0',
  `thu` tinyint NOT NULL DEFAULT '0',
  `fri` tinyint NOT NULL DEFAULT '0',
  `sat` tinyint NOT NULL DEFAULT '0',
  `sun` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`alarm_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm`
--

LOCK TABLES `alarm` WRITE;
/*!40000 ALTER TABLE `alarm` DISABLE KEYS */;
INSERT INTO `alarm` VALUES (1,'09:00:00',1,1,1,1,1,0,0),(2,'09:00:00',1,1,1,1,1,0,0);
/*!40000 ALTER TABLE `alarm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_seq` int NOT NULL AUTO_INCREMENT,
  `comment_story_seq` int NOT NULL,
  `comment_writer_seq` int NOT NULL,
  `comment_content` varchar(200) NOT NULL,
  `comment_regtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comment_mentioned_seq` int DEFAULT NULL,
  PRIMARY KEY (`comment_seq`),
  KEY `comment_mentioned_seq_idx` (`comment_mentioned_seq`),
  KEY `comment_writer_seq_idx` (`comment_writer_seq`),
  KEY `comment_story_seq_idx` (`comment_story_seq`),
  CONSTRAINT `comment_mentioned_seq` FOREIGN KEY (`comment_mentioned_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_story_seq` FOREIGN KEY (`comment_story_seq`) REFERENCES `story` (`story_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_writer_seq` FOREIGN KEY (`comment_writer_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `daily_question`
--

DROP TABLE IF EXISTS `daily_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `daily_question` (
  `dailyq_seq` int NOT NULL AUTO_INCREMENT,
  `dailyq_admin_seq` int NOT NULL,
  `dailyq_content` varchar(200) NOT NULL,
  `dailyq_regtime` timestamp NOT NULL,
  PRIMARY KEY (`dailyq_seq`),
  KEY `dailyq_admin_seq_idx` (`dailyq_admin_seq`),
  CONSTRAINT `dailyq_admin_seq` FOREIGN KEY (`dailyq_admin_seq`) REFERENCES `admin` (`admin_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_question`
--

LOCK TABLES `daily_question` WRITE;
/*!40000 ALTER TABLE `daily_question` DISABLE KEYS */;
INSERT INTO `daily_question` VALUES (1,1,'공부를 하면서 힘들었던 때 언제인가요?','2022-08-02 05:46:56'),(2,1,'체력 관리 비법을 알려주세요','2022-08-02 05:48:53');
/*!40000 ALTER TABLE `daily_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `follower_seq` int NOT NULL,
  `followee_seq` int NOT NULL,
  PRIMARY KEY (`follower_seq`,`followee_seq`),
  KEY `followee_seq_idx` (`followee_seq`),
  CONSTRAINT `followee_seq` FOREIGN KEY (`followee_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `follower_seq` FOREIGN KEY (`follower_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (11,10),(10,11),(10,49),(10,65);
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hashtag`
--

DROP TABLE IF EXISTS `hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hashtag` (
  `hashtag_seq` int NOT NULL,
  `hashtag_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`hashtag_seq`),
  UNIQUE KEY `hashtag_name_UNIQUE` (`hashtag_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtag`
--

LOCK TABLES `hashtag` WRITE;
/*!40000 ALTER TABLE `hashtag` DISABLE KEYS */;
INSERT INTO `hashtag` VALUES (0,'test');
/*!40000 ALTER TABLE `hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `identification`
--

DROP TABLE IF EXISTS `identification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `identification` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tel` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL,
  `code` varchar(6) COLLATE utf8mb4_unicode_ci NOT NULL,
  `expire` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `identification`
--

LOCK TABLES `identification` WRITE;
/*!40000 ALTER TABLE `identification` DISABLE KEYS */;
/*!40000 ALTER TABLE `identification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `image_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image_url` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,'story/1659579346960.png','/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/story/'),(2,'story/1659586318482.png','/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/story/'),(3,'story/1659587725154.png','/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/story/'),(4,'story/1659587779304.png','/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/story/'),(5,'story/1659587879690.png','/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/story/'),(6,'story/1659588887438.png','/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/story/'),(7,'story/1659588995540.png','/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/story/'),(8,'story/1659589096612.png','/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/story/'),(9,'story/1659589111814.png','/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/story/'),(10,'story/1659589318607.png','/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/story/'),(11,'story/1659589432037.png','/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/story/'),(12,'story/1659589465700.png','/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/story/'),(13,'story/1659589612802.png','/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/story/'),(14,'story/1659589669234.png','/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/story/'),(15,'story/1659589681889.png','/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/story/');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interest`
--

DROP TABLE IF EXISTS `interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interest` (
  `interest_user_seq` int NOT NULL,
  `interest_hashtag_seq` int NOT NULL,
  PRIMARY KEY (`interest_user_seq`,`interest_hashtag_seq`),
  KEY `interest_hashtag_seq_idx` (`interest_hashtag_seq`),
  CONSTRAINT `interest_hashtag_seq` FOREIGN KEY (`interest_hashtag_seq`) REFERENCES `hashtag` (`hashtag_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `interest_user_seq` FOREIGN KEY (`interest_user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interest`
--

LOCK TABLES `interest` WRITE;
/*!40000 ALTER TABLE `interest` DISABLE KEYS */;
/*!40000 ALTER TABLE `interest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interest_hashtag`
--

DROP TABLE IF EXISTS `interest_hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interest_hashtag` (
  `interest_hashtag_name` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `interest_hashtag_seq` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`interest_hashtag_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interest_hashtag`
--

LOCK TABLES `interest_hashtag` WRITE;
/*!40000 ALTER TABLE `interest_hashtag` DISABLE KEYS */;
INSERT INTO `interest_hashtag` VALUES ('테스트',1);
/*!40000 ALTER TABLE `interest_hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `job_user_seq` int NOT NULL,
  `job_hashtag_seq` int NOT NULL,
  PRIMARY KEY (`job_user_seq`,`job_hashtag_seq`),
  CONSTRAINT `job_user_seq` FOREIGN KEY (`job_user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_hashtag`
--

DROP TABLE IF EXISTS `job_hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_hashtag` (
  `job_hashtag_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `job_hashtag_seq` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`job_hashtag_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_hashtag`
--

LOCK TABLES `job_hashtag` WRITE;
/*!40000 ALTER TABLE `job_hashtag` DISABLE KEYS */;
INSERT INTO `job_hashtag` VALUES ('교수',1),('학생',2);
/*!40000 ALTER TABLE `job_hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `likes_user_seq` int NOT NULL,
  `likes_story_seq` int NOT NULL,
  PRIMARY KEY (`likes_user_seq`,`likes_story_seq`),
  KEY `like_story_seq_idx` (`likes_story_seq`),
  CONSTRAINT `like_story_seq` FOREIGN KEY (`likes_story_seq`) REFERENCES `story` (`story_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `like_user_seq` FOREIGN KEY (`likes_user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `notice_seq` int NOT NULL AUTO_INCREMENT,
  `notice_writer_seq` int NOT NULL,
  `notice_content` varchar(45) NOT NULL,
  `notice_regtime` datetime NOT NULL,
  PRIMARY KEY (`notice_seq`),
  KEY `notice_writer_seq_idx` (`notice_writer_seq`),
  CONSTRAINT `notice_writer_seq` FOREIGN KEY (`notice_writer_seq`) REFERENCES `admin` (`admin_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (2,1,'마이페이지 피드 오류가 수정되었습니다.','2022-08-02 14:08:10'),(3,1,'공지사항등록은 content랑 writerSeq만 사용합니다.','2022-08-02 14:29:51');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participation`
--

DROP TABLE IF EXISTS `participation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participation` (
  `participation_study_seq` int NOT NULL,
  `participation_user_seq` int NOT NULL,
  PRIMARY KEY (`participation_study_seq`,`participation_user_seq`),
  KEY `participation_user_seq_idx` (`participation_user_seq`),
  CONSTRAINT `participation_study_seq` FOREIGN KEY (`participation_study_seq`) REFERENCES `study` (`study_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `participation_user_seq` FOREIGN KEY (`participation_user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participation`
--

LOCK TABLES `participation` WRITE;
/*!40000 ALTER TABLE `participation` DISABLE KEYS */;
INSERT INTO `participation` VALUES (5,51),(6,51),(7,51),(8,51),(10,51),(11,51),(12,51),(13,51),(14,51),(15,51),(16,51),(17,51),(18,51),(19,51),(20,51),(11,52),(3,61),(4,61);
/*!40000 ALTER TABLE `participation` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`sduty108`@`%`*/ /*!50003 TRIGGER `study_member_cntup` AFTER INSERT ON `participation` FOR EACH ROW BEGIN
    UPDATE study SET study.study_join_number=study.study_join_number+1
    WHERE study.study_seq = new.participation_study_seq;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`sduty108`@`%`*/ /*!50003 TRIGGER `study_member_cntdown` AFTER DELETE ON `participation` FOR EACH ROW BEGIN
    UPDATE study SET study.study_join_number=study.study_join_number-1
    WHERE study.study_seq = old.participation_study_seq;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile` (
  `profile_user_seq` int NOT NULL,
  `profile_nickname` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `profile_birthday` date NOT NULL,
  `profile_public_birthday` tinyint NOT NULL DEFAULT '1',
  `profile_short_introduce` varchar(80) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `profile_image` char(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `profile_job` char(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `profile_public_job` tinyint NOT NULL DEFAULT '2',
  `profile_interest` int NOT NULL,
  `profile_public_interest` int NOT NULL DEFAULT '2',
  `profile_followers` int NOT NULL DEFAULT '0',
  `profile_followees` int NOT NULL DEFAULT '0',
  `profile_main_achievement_seq` int NOT NULL DEFAULT '0',
  `profile_block_action` tinyint NOT NULL DEFAULT '0',
  `profile_warning` int NOT NULL DEFAULT '0',
  `is_prohibited_user` tinyint NOT NULL DEFAULT '0',
  `is_studying` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`profile_user_seq`),
  UNIQUE KEY `profile_user_seq_UNIQUE` (`profile_user_seq`),
  CONSTRAINT `user_seq` FOREIGN KEY (`profile_user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (2,'123','1990-12-22',2,'5','profile/1659529824683.png','2',1,123,2,0,0,1,0,0,0,0),(49,'younghei','2022-07-30',1,'hello','123','학생',2,3,2,0,0,0,0,2,0,0),(50,'tt','1999-01-01',2,'hi','profile/1659269834364.png','test',2,2,2,0,0,1,0,0,0,0),(51,'정윤','1997-03-02',2,'정윤','profile/1659269588907.png','대학생',2,1,2,0,0,1,0,0,0,0),(52,'test3','1999-01-01',2,'hi','profile/1659269930461.png','t',2,1,2,0,0,1,0,0,0,0),(53,'aaa','1999-10-10',2,'dd','profile/1659515372610.png','saa',2,11,2,0,0,1,0,0,0,0),(55,'hh','1999-01-01',2,'dd','profile/1659499190229.png','dd',2,1,2,0,0,1,0,0,0,0),(56,'d','1999-01-01',2,'ddd','profile/1659499408736.png','ddd',2,1,2,0,0,1,0,0,0,0),(61,'test','1999-01-01',2,'test','profile/1659244516816.png','test',2,1,2,0,0,1,0,0,0,0),(62,'test1','1999-10-10',2,'hi','profile/1659252326272.png','test',2,1,2,0,0,1,0,0,0,0),(65,'test12','1999-01-01',2,'hi','profile/1659260831492.png','test',2,1,2,0,0,1,0,0,0,0),(76,'용준','1997-03-23',0,'안녕하세요','profile/1659499450441.png','1',2,1,2,0,0,1,0,0,0,0);
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qna`
--

DROP TABLE IF EXISTS `qna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qna` (
  `qna_seq` int NOT NULL AUTO_INCREMENT,
  `ques_title` varchar(50) NOT NULL,
  `ques_content` varchar(100) NOT NULL,
  `ques_category` varchar(50) NOT NULL,
  `ques_writer` int NOT NULL,
  `ques_regtime` timestamp NOT NULL,
  `ans_content` varchar(100) DEFAULT NULL,
  `ans_writer` int DEFAULT NULL,
  `ans_regtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`qna_seq`),
  KEY `ques_writer_idx` (`ques_writer`),
  KEY `ans_writer_idx` (`ans_writer`),
  CONSTRAINT `ans_writer` FOREIGN KEY (`ans_writer`) REFERENCES `admin` (`admin_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ques_writer` FOREIGN KEY (`ques_writer`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qna`
--

LOCK TABLES `qna` WRITE;
/*!40000 ALTER TABLE `qna` DISABLE KEYS */;
INSERT INTO `qna` VALUES (3,'로딩 문의','타임라인 로딩이 안됩니다ㅠ','기능',61,'2022-08-01 08:19:43',NULL,NULL,NULL);
/*!40000 ALTER TABLE `qna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reply` (
  `reply_seq` int NOT NULL AUTO_INCREMENT,
  `reply_user_seq` int NOT NULL,
  `reply_content` varchar(140) COLLATE utf8mb4_unicode_ci NOT NULL,
  `reply_story_seq` int NOT NULL,
  `reply_regtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`reply_seq`),
  KEY `reply_user_seq_idx` (`reply_user_seq`),
  KEY `reply_story_seq_idx` (`reply_story_seq`),
  CONSTRAINT `reply_story_seq` FOREIGN KEY (`reply_story_seq`) REFERENCES `story` (`story_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reply_user_seq` FOREIGN KEY (`reply_user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (1,49,'hehe',3,'2022-08-04 15:48:24');
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `report_seq` int NOT NULL AUTO_INCREMENT,
  `report_owner_seq` int NOT NULL,
  `report_date` date NOT NULL,
  PRIMARY KEY (`report_seq`),
  KEY `report_owner_seq_idx` (`report_owner_seq`),
  CONSTRAINT `report_owner_seq` FOREIGN KEY (`report_owner_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (2,2,'2022-07-26'),(3,10,'2022-07-31'),(4,61,'2022-07-31'),(5,61,'2022-08-01'),(8,47,'2022-08-01'),(9,62,'2022-08-01'),(10,72,'2022-08-03'),(11,47,'2022-08-03');
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scrap`
--

DROP TABLE IF EXISTS `scrap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scrap` (
  `scrap_user_seq` int NOT NULL,
  `scrap_story_seq` int NOT NULL,
  PRIMARY KEY (`scrap_user_seq`,`scrap_story_seq`),
  KEY `scrap_story_seq_idx` (`scrap_story_seq`),
  CONSTRAINT `scrap_story_seq` FOREIGN KEY (`scrap_story_seq`) REFERENCES `story` (`story_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `scrap_user_seq` FOREIGN KEY (`scrap_user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scrap`
--

LOCK TABLES `scrap` WRITE;
/*!40000 ALTER TABLE `scrap` DISABLE KEYS */;
INSERT INTO `scrap` VALUES (10,2),(10,3);
/*!40000 ALTER TABLE `scrap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `story`
--

DROP TABLE IF EXISTS `story`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `story` (
  `story_seq` int NOT NULL AUTO_INCREMENT,
  `story_writer_seq` int NOT NULL,
  `story_image_source` char(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `story_thumbnail` char(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `story_regtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `story_public` int NOT NULL DEFAULT '2',
  `story_warning` int NOT NULL DEFAULT '0',
  `story_contents` varchar(140) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `story_interest_hashtag` int DEFAULT NULL,
  `story_job_hashtag` int DEFAULT NULL,
  PRIMARY KEY (`story_seq`),
  UNIQUE KEY `story_seq_UNIQUE` (`story_seq`),
  KEY `story_writer_seq_idx` (`story_writer_seq`),
  KEY `story_interest_hashtag_idx` (`story_interest_hashtag`),
  KEY `story_job_hashtag_idx` (`story_job_hashtag`),
  CONSTRAINT `story_interest_hashtag` FOREIGN KEY (`story_interest_hashtag`) REFERENCES `interest_hashtag` (`interest_hashtag_seq`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `story_job_hashtag` FOREIGN KEY (`story_job_hashtag`) REFERENCES `job_hashtag` (`job_hashtag_seq`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `story_writer_seq` FOREIGN KEY (`story_writer_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `story`
--

LOCK TABLES `story` WRITE;
/*!40000 ALTER TABLE `story` DISABLE KEYS */;
INSERT INTO `story` VALUES (2,2,'123','123','2022-08-04 12:19:22',2,0,'chamjjeol',1,2),(3,2,'555555555','555555555555555123','2022-08-04 12:19:51',2,0,'mwajjeol',1,1),(4,2,'12323131','412412244','2022-08-04 12:20:32',2,0,'bbibjjeol',1,1),(5,65,'story/1659586318482.png','','2022-08-04 13:11:59',0,0,NULL,1,2),(6,65,'story/1659588995540.png','-story/1659588995540.png','2022-08-04 13:56:36',0,0,'asdfasdf',1,1),(7,65,'story/1659589096612.png','-story/1659589096612.png','2022-08-04 13:58:17',0,0,'asdfasdf',1,1),(8,65,'story/1659589111814.png','-story/1659589111814.png','2022-08-04 13:58:32',0,0,'asdfasdf',1,1),(9,65,'story/1659589318607.png','story/thumbnail-1659589318607.png','2022-08-04 14:01:59',0,0,'asdfasdf',1,1),(10,65,'story/1659589669234.png','story/thumbnail-1659589669234.png','2022-08-04 14:07:50',0,0,'asdfasdf',1,1),(11,65,'story/1659589681889.png','story/thumbnail-1659589681889.png','2022-08-04 14:08:02',0,0,'asdfasdf',1,1),(12,65,'story/1659590753882.png','story/thumbnail-1659590753882.png','2022-08-04 14:25:55',2,0,NULL,1,1),(13,65,'story/1659596695666.png','story/thumbnail-1659596695666.png','2022-08-04 16:04:56',0,0,'fffffffffffffffffffffffffffff',1,1);
/*!40000 ALTER TABLE `story` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `study`
--

DROP TABLE IF EXISTS `study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study` (
  `study_seq` int NOT NULL AUTO_INCREMENT,
  `study_master_seq` int NOT NULL,
  `study_alarm_seq` int DEFAULT NULL,
  `study_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `study_introduce` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `study_category` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `study_join_number` int NOT NULL DEFAULT '1',
  `study_limit_number` int NOT NULL DEFAULT '2',
  `study_password` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `study_room_id` char(70) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `study_regtime` timestamp NOT NULL,
  `study_notice` char(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`study_seq`),
  KEY `study_master_seq` (`study_master_seq`),
  KEY `study_alarm_seq` (`study_alarm_seq`),
  CONSTRAINT `study_alarm_seq` FOREIGN KEY (`study_alarm_seq`) REFERENCES `alarm` (`alarm_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `study_master_seq` FOREIGN KEY (`study_master_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study`
--

LOCK TABLES `study` WRITE;
/*!40000 ALTER TABLE `study` DISABLE KEYS */;
INSERT INTO `study` VALUES (3,61,NULL,'캠스터디아님','열심히 하실분만','대학생',1,10,NULL,NULL,'2022-08-01 03:31:13','공지사항 등록'),(4,61,2,'서울가자!!','열심히 하실분만','대학생',1,10,'a123','12345','2022-08-01 03:35:30',NULL),(5,51,NULL,'a','a','대학생',1,10,NULL,NULL,'2022-08-01 08:09:53',NULL),(6,51,NULL,'b','열심히 하실분만','대학생',1,10,NULL,NULL,'2022-08-01 08:13:15',NULL),(7,51,NULL,'c','열심히 하실분만','대학생',1,10,NULL,NULL,'2022-08-02 02:15:50',NULL),(8,51,NULL,'d','dd','대학생',1,10,NULL,NULL,'2022-08-02 04:08:43',NULL),(10,51,NULL,'qq','열심히 하실분만','대학생',1,10,NULL,NULL,'2022-08-02 12:20:48',NULL),(11,51,NULL,'e','e','대학생',3,6,'e',NULL,'2022-08-02 12:40:03',''),(12,51,NULL,'aaa','aaa','대학생',2,2,'aaa',NULL,'2022-08-02 12:49:23',''),(13,51,NULL,'aa','aa','대학생',2,2,'aa',NULL,'2022-08-02 12:59:19',''),(14,51,NULL,'bbb','bbb','수능',1,9,'bbb',NULL,'2022-08-02 13:01:17',''),(15,51,NULL,'bbbb','bbbb','예비 고1',1,7,'bbb',NULL,'2022-08-02 13:09:44',''),(16,51,NULL,'zxc','zxc','수능',1,8,'',NULL,'2022-08-03 00:24:26','공지사항'),(17,51,NULL,'aaaaa','aa','어학',1,2,'',NULL,'2022-08-03 00:35:35','공지사항'),(18,51,NULL,'aaaa','dsa','예비 고1',1,9,'',NULL,'2022-08-03 00:39:25','공지사항'),(19,51,NULL,'qwe','qwert','예체능',1,2,'',NULL,'2022-08-03 00:43:00','공지사항'),(20,51,NULL,'qqq','ewq','전공',1,2,'',NULL,'2022-08-03 00:49:04','공지사항');
/*!40000 ALTER TABLE `study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `tag_story_seq` int NOT NULL,
  `tag_hashtag_seq` int NOT NULL,
  PRIMARY KEY (`tag_story_seq`,`tag_hashtag_seq`),
  KEY `tag_hashtag_seq_idx` (`tag_hashtag_seq`),
  CONSTRAINT `tag_hashtag_seq` FOREIGN KEY (`tag_hashtag_seq`) REFERENCES `hashtag` (`hashtag_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tag_story_seq` FOREIGN KEY (`tag_story_seq`) REFERENCES `story` (`story_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `task_seq` int NOT NULL AUTO_INCREMENT,
  `task_report_seq` int NOT NULL,
  `task_title` varchar(45) NOT NULL,
  `task_content1` varchar(100) DEFAULT NULL,
  `task_start_time` time NOT NULL,
  `task_end_time` time NOT NULL,
  `task_duration_time` int NOT NULL,
  `task_content2` varchar(100) DEFAULT NULL,
  `task_content3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`task_seq`),
  KEY `task_report_seq_idx` (`task_report_seq`),
  CONSTRAINT `task_report_seq` FOREIGN KEY (`task_report_seq`) REFERENCES `report` (`report_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (2,2,'수학','2단원풀기','19:00:00','20:00:00',3600,NULL,NULL),(3,3,'수정한 테스크!!','내용1','19:00:00','21:00:00',7200,'내용2',NULL),(4,4,'수학','2단원풀기','19:00:00','20:00:00',3600,NULL,NULL),(5,5,'수학','2단원풀기','19:00:00','21:00:00',7200,NULL,NULL),(6,5,'수학','2단원풀기','19:00:00','21:00:00',7200,'시험 준비',NULL),(7,5,'수학','2단원풀기','10:00:00','13:30:00',12600,'시험 준비',NULL),(8,8,'수학','2단원풀기','10:00:00','13:30:00',12600,'시험 준비',NULL),(9,9,'수학','2단원풀기','10:00:00','13:30:00',12600,'시험 준비',NULL),(10,5,'수학','2단원풀기','10:00:00','13:30:00',12600,'시험 준비',NULL),(11,8,'수학','2단원풀기','10:00:00','13:30:00',12600,'시험 준비',NULL),(12,8,'수학','2단원풀기','10:00:00','13:30:00',12600,'시험 준비',NULL),(13,10,'수학','2단원풀기','10:00:00','13:30:00',12600,'시험 준비',NULL),(14,11,'1','1','12:04:53','12:04:56',3,'',''),(15,11,'테스트','1','01:04:48','01:05:10',22,'2','3');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_seq` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_password` char(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_name` char(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_tel` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_email` char(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_regtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_fcm_token` varchar(250) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_public` tinyint NOT NULL DEFAULT '1',
  `user_password2` char(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_seq`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'tester','12344','테스트','01030000000','qweqwe@','2022-07-29 16:03:02','0',0,NULL),(10,'123','12341234','12','01012341234','123@gmail.com','2022-07-29 20:54:30','',1,NULL),(11,'asdf','asdfasdf','asdf','01012341234','asdf@gmail.com','2022-07-29 20:58:04','',1,NULL),(12,'2315123','12341234','1235','01012341234','1235@gmail.com','2022-07-29 23:26:20','',1,NULL),(13,'23','12341234','1255325','01012341234','235@gmail.com','2022-07-29 23:28:19','',1,NULL),(14,'1234','12341234','61236','01012341234','61@gmail.com','2022-07-29 23:28:57','',1,NULL),(15,'234','12341234','54','01012341234','7345@gmail.com','2022-07-29 23:29:26','',1,NULL),(16,'6789','67896789','7689','01067896789','6789@gmail.com','2022-07-29 23:30:53','',1,NULL),(17,'3535','35353535','535','01035353535','35@gmail.com','2022-07-29 23:33:23','',1,NULL),(18,'57','12341234','757','01012341234','575@gmail.com','2022-07-29 23:36:01','',1,NULL),(19,'1236','12341234','353','01012341234','32234@gmail.com','2022-07-29 23:39:51','',1,NULL),(20,'4646','12341234','6464','01012341234','46@gmail.com','2022-07-29 23:41:24','',1,NULL),(47,'1','1','권용준','1','1','2022-07-30 00:58:59',NULL,1,NULL),(48,'6','6','편예린','1','1','2022-07-30 00:59:32',NULL,1,NULL),(49,'5','5','최영희','1','1','2022-07-30 00:59:33',NULL,1,NULL),(50,'4','4','정봉진','1','1','2022-07-30 00:59:33',NULL,1,NULL),(51,'3','3','김정윤','1','1','2022-07-30 00:59:34',NULL,1,NULL),(52,'2','2','김남희','1','1','2022-07-30 00:59:34',NULL,1,NULL),(53,'121','15151515','e','01013131313','e@gmail.com','2022-07-30 03:59:18','',1,NULL),(54,'2525','14141414','377','010133466','52@gmail.com','2022-07-30 04:04:53','',1,NULL),(55,'r','15151515','e','01012121212','e@gmail.com','2022-07-30 22:33:56','',1,NULL),(56,'rr','10101010','1','01012121212','1@gmail.com','2022-07-30 22:40:33','',1,NULL),(57,'333','19191919','333','01012121212','333@gmail.com','2022-07-31 00:13:43','',1,NULL),(58,'521','12341234','251','01012341234','15215@gmail.com','2022-07-31 02:51:57','',1,NULL),(61,'test','test1234','test','01049177914','tst@gmail.com','2022-07-31 14:14:12','',1,NULL),(62,'test1','123456789','test','01049177914','test@gmail.com','2022-07-31 16:24:56','',1,NULL),(65,'darkberry@naver.com','darkberry@naver.com','정봉진','01049177914','darkberry@naver.com','2022-07-31 18:45:54','',1,NULL),(70,'yerini','a1234','암호테스트','01011112222','test12@gmail.com','2022-08-02 09:00:00','',1,'$2a$10$t1rTPSXGET0S6PfYoudbWOdEhKcyeyLOH/vBwGaf0fQVLXdaaOXn.'),(72,'yr12','yr12345','편예린','01022223333','test11@gmail.com','2022-08-03 08:59:00',NULL,1,'$2a$10$w9ciHAEfUKXguDDAJZ2G8ehSiKQ6PHRr4bbqhlmlSkIvvWzKAIDZ6'),(73,'yr123','test12@naver.com','수정한 이름','01022223333','yerin3433@gmail.com','2022-08-03 08:59:00',NULL,1,'$2a$10$J492toplzxIuO5OhKtrUwu85mytgjih/xjTrlNhNSiwubJLBfC1hG'),(76,'yjisap3176@naver.com','yjisap3176@naver.com','권용준','01050452811','yjisap3176@naver.com','2022-08-03 13:01:01','',1,'$2a$10$uiSvQabiGEgLNXIFs6FiWOZRCqTvrzeniTVr0/j81W.Pipa4hG952');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userachieve`
--

DROP TABLE IF EXISTS `userachieve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userachieve` (
  `userachieve_user_seq` int NOT NULL,
  `userachieve_achievement_seq` int NOT NULL,
  PRIMARY KEY (`userachieve_user_seq`,`userachieve_achievement_seq`),
  KEY `userachieve_achievement_seq_idx` (`userachieve_achievement_seq`),
  CONSTRAINT `userachieve_achievement_seq` FOREIGN KEY (`userachieve_achievement_seq`) REFERENCES `achievement` (`achievement_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userachieve_user_seq` FOREIGN KEY (`userachieve_user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userachieve`
--

LOCK TABLES `userachieve` WRITE;
/*!40000 ALTER TABLE `userachieve` DISABLE KEYS */;
INSERT INTO `userachieve` VALUES (10,1),(49,1),(2,2),(10,2);
/*!40000 ALTER TABLE `userachieve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sduty'
--

--
-- Dumping routines for database 'sduty'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-04 21:44:34
