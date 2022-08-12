CREATE DATABASE  IF NOT EXISTS `sduty` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sduty`;
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
  `alarm_cron` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`alarm_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm`
--

LOCK TABLES `alarm` WRITE;
/*!40000 ALTER TABLE `alarm` DISABLE KEYS */;
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
/*!40000 ALTER TABLE `daily_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dislike`
--

DROP TABLE IF EXISTS `dislike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dislike` (
  `dislike_user_seq` int NOT NULL,
  `dislike_story_seq` int NOT NULL,
  PRIMARY KEY (`dislike_user_seq`,`dislike_story_seq`),
  KEY `dislike_story_seq_idx` (`dislike_story_seq`),
  CONSTRAINT `dislike_story_seq` FOREIGN KEY (`dislike_story_seq`) REFERENCES `story` (`story_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `dislike_user_seq` FOREIGN KEY (`dislike_user_seq`) REFERENCES `user` (`user_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dislike`
--

LOCK TABLES `dislike` WRITE;
/*!40000 ALTER TABLE `dislike` DISABLE KEYS */;
/*!40000 ALTER TABLE `dislike` ENABLE KEYS */;
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
  PRIMARY KEY (`follower_seq`),
  KEY `followee_seq_idx` (`followee_seq`),
  CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`followee_seq`) REFERENCES `profile` (`profile_user_seq`),
  CONSTRAINT `follower_seq` FOREIGN KEY (`follower_seq`) REFERENCES `profile` (`profile_user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
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
  `hashtag_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`hashtag_seq`),
  UNIQUE KEY `hashtag_name_UNIQUE` (`hashtag_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtag`
--

LOCK TABLES `hashtag` WRITE;
/*!40000 ALTER TABLE `hashtag` DISABLE KEYS */;
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
  `tel` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `expire` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `identification`
--

LOCK TABLES `identification` WRITE;
/*!40000 ALTER TABLE `identification` DISABLE KEYS */;
INSERT INTO `identification` VALUES (24,'01049177914','947539','2022-08-12 08:53:25');
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
  `image_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `image_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
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
  `interest_hashtag_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `interest_hashtag_seq` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`interest_hashtag_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interest_hashtag`
--

LOCK TABLES `interest_hashtag` WRITE;
/*!40000 ALTER TABLE `interest_hashtag` DISABLE KEYS */;
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
  `job_hashtag_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `job_hashtag_seq` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`job_hashtag_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_hashtag`
--

LOCK TABLES `job_hashtag` WRITE;
/*!40000 ALTER TABLE `job_hashtag` DISABLE KEYS */;
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
  `profile_nickname` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `profile_birthday` date NOT NULL,
  `profile_public_birthday` tinyint NOT NULL DEFAULT '1',
  `profile_short_introduce` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `profile_image` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `profile_job` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
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
  `reply_content` varchar(140) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `reply_story_seq` int NOT NULL,
  `reply_regtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`reply_seq`),
  KEY `reply_user_seq_idx` (`reply_user_seq`),
  KEY `reply_story_seq_idx` (`reply_story_seq`),
  CONSTRAINT `reply_story_seq` FOREIGN KEY (`reply_story_seq`) REFERENCES `story` (`story_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reply_user_seq` FOREIGN KEY (`reply_user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
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
  `story_image_source` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `story_thumbnail` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `story_regtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `story_public` int NOT NULL DEFAULT '2',
  `story_warning` int NOT NULL DEFAULT '0',
  `story_contents` varchar(140) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `story_job_hashtag` int DEFAULT NULL,
  PRIMARY KEY (`story_seq`),
  UNIQUE KEY `story_seq_UNIQUE` (`story_seq`),
  KEY `story_writer_seq_idx` (`story_writer_seq`),
  KEY `story_job_hashtag_idx` (`story_job_hashtag`),
  CONSTRAINT `story_writer_seq` FOREIGN KEY (`story_writer_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `story`
--

LOCK TABLES `story` WRITE;
/*!40000 ALTER TABLE `story` DISABLE KEYS */;
/*!40000 ALTER TABLE `story` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `story_interest_hashtag`
--

DROP TABLE IF EXISTS `story_interest_hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `story_interest_hashtag` (
  `story_seq` int NOT NULL,
  `story_interest_hashtag` int NOT NULL,
  PRIMARY KEY (`story_seq`,`story_interest_hashtag`),
  KEY `story_interest_seq_idx` (`story_interest_hashtag`),
  CONSTRAINT `story_interest_seq` FOREIGN KEY (`story_interest_hashtag`) REFERENCES `interest_hashtag` (`interest_hashtag_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `story_seq` FOREIGN KEY (`story_seq`) REFERENCES `story` (`story_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `story_interest_hashtag`
--

LOCK TABLES `story_interest_hashtag` WRITE;
/*!40000 ALTER TABLE `story_interest_hashtag` DISABLE KEYS */;
/*!40000 ALTER TABLE `story_interest_hashtag` ENABLE KEYS */;
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
  `study_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `study_introduce` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `study_category` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `study_join_number` int NOT NULL DEFAULT '1',
  `study_limit_number` int NOT NULL DEFAULT '2',
  `study_password` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `study_room_id` char(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `study_regtime` timestamp NOT NULL,
  `study_notice` char(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`study_seq`),
  KEY `study_master_seq` (`study_master_seq`),
  KEY `study_alarm_seq` (`study_alarm_seq`),
  CONSTRAINT `study_alarm_seq` FOREIGN KEY (`study_alarm_seq`) REFERENCES `alarm` (`alarm_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `study_master_seq` FOREIGN KEY (`study_master_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study`
--

LOCK TABLES `study` WRITE;
/*!40000 ALTER TABLE `study` DISABLE KEYS */;
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
  `user_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_password` char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_name` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_tel` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_email` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_regtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_fcm_token` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_public` tinyint NOT NULL DEFAULT '1',
  `user_password2` char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_seq`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_interest_hashtag`
--

DROP TABLE IF EXISTS `user_interest_hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_interest_hashtag` (
  `user_seq` int NOT NULL,
  `user_interest_hashtag` int NOT NULL,
  PRIMARY KEY (`user_seq`,`user_interest_hashtag`),
  KEY `user_interest_seq_idx` (`user_interest_hashtag`),
  CONSTRAINT `fk_user_seq` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kf_user_interest_seq` FOREIGN KEY (`user_interest_hashtag`) REFERENCES `interest_hashtag` (`interest_hashtag_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_interest_hashtag`
--

LOCK TABLES `user_interest_hashtag` WRITE;
/*!40000 ALTER TABLE `user_interest_hashtag` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_interest_hashtag` ENABLE KEYS */;
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

-- Dump completed on 2022-08-12 17:51:16
