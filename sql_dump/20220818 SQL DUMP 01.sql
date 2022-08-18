CREATE DATABASE  IF NOT EXISTS `sduty` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sduty`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
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
  `admin_password` char(60) NOT NULL,
  `admin_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`admin_seq`),
  UNIQUE KEY `admin_id_UNIQUE` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (5,'admin','admin','관리자');
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm`
--

LOCK TABLES `alarm` WRITE;
/*!40000 ALTER TABLE `alarm` DISABLE KEYS */;
INSERT INTO `alarm` VALUES (32,'14:36:00',1,1,1,1,1,0,0,'00 36 14 ? * MON,TUE,WED,THU,FRI *'),(33,'09:00:00',0,0,0,0,0,1,0,'00 0 9 ? * SAT *'),(34,'09:00:00',0,0,0,0,0,1,0,'00 0 9 ? * SAT *'),(35,'09:00:00',1,1,1,1,1,0,0,'00 0 9 ? * MON,TUE,WED,THU,FRI *');
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
  CONSTRAINT `dislike_user_seq` FOREIGN KEY (`dislike_user_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
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
  KEY `followee_seq_idx` (`followee_seq`),
  KEY `follower_seq` (`follower_seq`),
  CONSTRAINT `followee_seq` FOREIGN KEY (`followee_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `follower_seq` FOREIGN KEY (`follower_seq`) REFERENCES `user` (`user_seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (55,54),(54,59),(54,58),(54,55),(54,68),(54,65),(54,64),(66,54),(66,59),(66,58),(66,55),(66,68),(66,65),(66,64),(54,63),(63,54);
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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interest_hashtag`
--

LOCK TABLES `interest_hashtag` WRITE;
/*!40000 ALTER TABLE `interest_hashtag` DISABLE KEYS */;
INSERT INTO `interest_hashtag` VALUES ('예비고1',42),('수능',43),('예체능',44),('전공',45),('편입',46),('어학',47),('자격증',48),('국가고시',49),('공무원',50),('면접',51),('IT',52),('독서',53),('기타',54);
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_hashtag`
--

LOCK TABLES `job_hashtag` WRITE;
/*!40000 ALTER TABLE `job_hashtag` DISABLE KEYS */;
INSERT INTO `job_hashtag` VALUES ('고등학생',24),('대학생',25),('취업준비생',26),('직장인',27),('기타',28);
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
INSERT INTO `likes` VALUES (54,60),(66,60),(54,61),(66,61),(54,62),(66,62),(54,63),(66,63),(54,64),(66,64),(54,65),(66,65),(54,67),(66,67),(54,68),(66,68),(54,69),(66,69),(54,72),(54,73),(54,75),(54,82);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (11,5,'공지사항 테스트','2022-08-17 19:56:39');
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
INSERT INTO `participation` VALUES (106,54),(107,54),(110,54),(107,58),(112,63),(107,64),(107,65),(107,66),(107,68),(110,68),(111,68);
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
INSERT INTO `profile` VALUES (54,'살려줘','1999-09-06',2,'??','profile/1660797337392.png','대학생',2,0,2,2,1,52,0,0,0,0),(55,'Sample','1999-01-01',2,'Test','profile/1660725407320.png','기타',2,0,2,0,0,52,0,0,0,0),(56,'Hello','1998-12-31',2,'hi','profile/1660736306187.png','대학생',2,0,2,0,0,49,0,0,0,0),(57,'Test','1999-10-09',2,'Test','profile/1660730569723.png','직장인',2,0,2,0,0,49,0,0,0,1),(58,'영희','1994-10-20',2,'국가직 7급 전산 지원중','profile/1660736747143.png','취업준비생',2,0,2,0,0,50,0,0,0,0),(59,'김재민','2005-11-15',2,'수능','profile/1660781620965.png','고등학생',2,0,2,0,0,43,0,0,0,0),(63,'deve10p_','1999-03-20',2,'네카라쿠배당토','profile/1660806306593.png','대학생',2,0,2,0,0,52,0,0,0,0),(64,'아르마딜로','1998-12-30',2,'게임제작기','profile/1660782609296.png','취업준비생',2,0,2,0,2,52,0,0,0,1),(65,'정윤','1997-03-02',2,'하이','profile/1660804351759.png','대학생',2,0,2,0,0,0,0,0,0,0),(66,'봉진','1999-09-08',2,'안녕하세요','profile/1660805877873.png','취업준비생',2,0,2,7,0,52,0,0,0,1),(68,'steady1117','2004-07-11',2,'아자!','profile/1660804768194.png','고등학생',2,0,2,0,0,43,0,0,0,0),(69,'치킨먹고싶다','1997-03-23',2,'하이','profile/1660805187748.png','취업준비생',2,0,2,0,0,47,0,0,0,0);
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
  `ques_user_seq` int DEFAULT NULL,
  `ques_title` varchar(50) DEFAULT NULL,
  `ques_content` varchar(50) DEFAULT NULL,
  `ques_writer` varchar(30) DEFAULT NULL,
  `ans_writer` varchar(30) DEFAULT NULL,
  `ans_content` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`qna_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qna`
--

LOCK TABLES `qna` WRITE;
/*!40000 ALTER TABLE `qna` DISABLE KEYS */;
INSERT INTO `qna` VALUES (1,54,'asdf','asdf','살려줘','관리자','TestAnswer'),(2,54,'test 문의','테스트 입니다','살려줘','관리자','네 알겠습니다'),(3,54,'ㅇㅇ','ㅇㅇ','살려줘','관리자','ㅇㅇㅇㅇ'),(4,54,'test','content','살려줘','관리자','answer'),(5,54,'title','content','살려줘','관리자','answer'),(6,54,'t1','plz','살려줘','관리자','ssss'),(7,54,'er','dd','살려줘','관리자','3333'),(8,54,'aaaa','ddd','살려줘','관리자','33334'),(9,54,'333','1¹1111','살려줘','관리자','47484848'),(10,54,'1111','1111','살려줘','관리자',''),(11,54,'333','3222','살려줘','관리자','g&g');
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
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (67,54,'??',82,'2022-08-18 16:54:47');
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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (38,54,'2022-08-18'),(40,65,'2022-08-18'),(41,68,'2022-08-18'),(42,58,'2022-08-18'),(43,63,'2022-08-18'),(44,64,'2022-08-18');
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
INSERT INTO `scrap` VALUES (54,60),(54,65),(54,82);
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
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `story`
--

LOCK TABLES `story` WRITE;
/*!40000 ALTER TABLE `story` DISABLE KEYS */;
INSERT INTO `story` VALUES (60,58,'story/1660736797107.png','story/thumbnail-1660736797107.png','2022-08-17 20:46:36',0,0,'DB론 3개년 클리어!!',26),(61,58,'story/1660736867978.png','story/thumbnail-1660736867978.png','2022-08-17 20:47:47',0,0,'정보보호론 두 개 다 품 굿',26),(62,58,'story/1660781324623.png','story/thumbnail-1660781324623.png','2022-08-18 09:08:44',0,0,'소프트웨어 공학 3년치',26),(63,58,'story/1660781362749.png','story/thumbnail-1660781362749.png','2022-08-18 09:09:23',0,0,'자료구조론 문제 풀이',26),(64,59,'story/1660781686641.png','story/thumbnail-1660781686641.png','2022-08-18 09:14:46',0,0,'토익 리딩 공부 중임 난이도 실환가',24),(65,58,'story/1660782006628.png','story/thumbnail-1660782006628.png','2022-08-18 09:20:06',0,0,'자료구조론 기출 더...',26),(67,54,'story/1660801694801.png','story/thumbnail-1660801694801.png','2022-08-18 14:48:16',0,0,'코틀린',28),(68,54,'story/1660801814367.png','story/thumbnail-1660801814367.png','2022-08-18 14:50:15',0,0,'봐도 모르겠다.. ?',28),(69,54,'story/1660802635875.png','story/thumbnail-1660802635875.png','2022-08-18 15:03:57',0,0,'어려워요,,',25),(70,54,'story/1660806773899.png','story/thumbnail-1660806773899.png','2022-08-18 16:12:54',0,0,'코틀린',25),(71,54,'story/1660806839192.png','story/thumbnail-1660806839192.png','2022-08-18 16:13:59',0,0,'람다',25),(72,54,'story/1660807029942.png','story/thumbnail-1660807029942.png','2022-08-18 16:17:10',0,0,'rangeTo',25),(73,54,'story/1660807312509.png','story/thumbnail-1660807312509.png','2022-08-18 16:21:53',0,0,'컬렉션',25),(74,63,'story/1660807334274.png','story/thumbnail-1660807334274.png','2022-08-18 16:22:15',0,0,'쉬운 문제라도 1일 1커밋하자',25),(75,54,'story/1660807350014.png','story/thumbnail-1660807350014.png','2022-08-18 16:22:31',0,0,'컬렉션',25),(76,54,'story/1660807450881.png','story/thumbnail-1660807450881.png','2022-08-18 16:24:11',0,0,'lazy',25),(77,64,'story/1660807663352.png','story/thumbnail-1660807663352.png','2022-08-18 16:27:42',0,0,'어렵다....ㅠ',26),(78,54,'story/1660807836623.png','story/thumbnail-1660807836623.png','2022-08-18 16:30:37',0,0,'제네릭',25),(79,54,'story/1660808040240.png','story/thumbnail-1660808040240.png','2022-08-18 16:34:00',0,0,'Coroutine async',25),(81,63,'story/1660808371455.png','story/thumbnail-1660808371455.png','2022-08-18 16:39:32',0,0,'JPA를 사용한 프로젝트.. 맞게쓰고있는지 모르겠다',25),(82,63,'story/1660808901744.png','story/thumbnail-1660808901744.png','2022-08-18 16:48:23',0,0,'구글링으로 부족해서 유튜브로 공부',25),(83,64,'story/1660809141854.png','story/thumbnail-1660809141854.png','2022-08-18 16:52:21',0,0,'게임제작일지 #1 : 컨셉 회의\n\n대회 준비를 위해서 컨셉 회의를 진행했다.\n원하는 컨셉이 달라서 애먹었지만\n그래도 합의돼서 다행이다',26);
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
INSERT INTO `story_interest_hashtag` VALUES (69,45),(70,45),(72,45),(73,45),(75,45),(76,45),(64,47),(60,49),(61,49),(60,50),(61,50),(62,50),(65,50),(62,52),(65,52),(67,52),(70,52),(71,52),(72,52),(73,52),(74,52),(75,52),(76,52),(81,52),(82,52),(68,53),(71,53);
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
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study`
--

LOCK TABLES `study` WRITE;
/*!40000 ALTER TABLE `study` DISABLE KEYS */;
INSERT INTO `study` VALUES (106,54,NULL,'일반 스터디','구미 D108','IT',1,6,NULL,NULL,'2022-08-18 05:34:21','공지사항'),(107,54,32,'8팀 입니다?','구미 D108','IT',6,10,NULL,'b72dc4ef-f959-456b-a6ab-c79d1f254800','2022-08-18 05:35:54','공지사항'),(110,68,NULL,'20221117 수능','주중 공부시간 10시간 채우기\n3회 이상 미완료시 강퇴됩니다.','수능',2,5,NULL,NULL,'2022-08-18 06:42:14','공지사항'),(111,68,34,'기출시험','실제 수능처럼 시간 정해서 기출 풀기','수능',1,2,'1117','d0575bd5-776d-4d1c-8de3-630163f7621f','2022-08-18 06:43:24','공지사항'),(112,63,35,'모각코ㄱㄱ','모여서 각자 코딩','IT',1,10,NULL,'8ceefd9f-a4d4-46fc-abac-537e7f74ecf4','2022-08-18 07:06:12','공지사항');
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
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (88,38,'kotlin','thread','13:46:29','14:28:57',2548,'',''),(95,38,'안드로이드','UI','14:38:54','15:04:03',1509,'Coroutine',''),(97,40,'제목','내용','15:33:00','15:36:56',236,'',''),(98,41,'국어','매3비 37일차 풀+오','09:00:00','11:30:00',9000,'고전소설 분석:홍계월전',NULL),(99,41,'영어','영어단어','11:40:00','12:08:52',1732,NULL,NULL),(100,41,'수학','[미적분] 수분감25강 적분법','13:12:00','16:51:11',13151,'[확통] 수분감 21강 통계1',NULL),(101,41,'영어','수능 완성 14강','17:02:00','18:32:01',5401,NULL,NULL),(102,41,'물리','수능완성10강 풀+오','19:14:00','21:00:30',6390,NULL,NULL),(103,41,'영어','영어단어정리','22:00:00','22:56:10',3370,NULL,NULL),(104,42,'정보보호론 오답 빠른 풀이','','15:42:06','15:49:37',451,'',''),(105,43,'정처기 기출 풀기','[필기] 2021 3회차','09:00:00','11:30:00',9000,NULL,NULL),(107,43,'알고리즘 문제','백준:1113번','13:10:00','15:12:33',7353,'프로그래머스:괄호변환',NULL),(108,43,'CS 학습','[Java] GC','17:00:00','18:26:51',5211,NULL,NULL),(110,43,'알고 스터디',NULL,'20:00:00','20:43:02',2582,NULL,NULL),(111,43,'JPA 학습','강의 2회차','15:30:00','16:37:12',4032,'블로그에 내용정리',NULL),(113,38,'Java','JPA','15:46:18','16:38:11',3113,'',''),(115,38,'알고리즘','백트래킹','10:00:00','12:44:00',9840,'',''),(116,44,'게임제작회의','','16:32:01','16:49:41',1060,'','');
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
  `user_tel` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_email` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_regtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_fcm_token` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_public` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_seq`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (54,'darkberry@naver.com','$2a$10$zqWGk8V01mlPlKz3X936duthUpXqSb2sfqKefb4yAL2LwqRmxC4uy','정봉진','OAubfR5jT27OTN3mwfq7yMtkSK+R8dJAJk8JsyCyhm8=','darkberry@naver.com','2022-08-17 17:35:20','fEUcajKtTmaWfdLjaM-B7o:APA91bHR1acBEOvc80FnRcofV46u3O46vXgeP3OgvUF0GdX5o3JjQhkxUrERVSkZ4FzGAW2tL_4wcpmniuS9p3LFfoO7DKjlDm50oqdB9Fg1ztgenYvdiqEOl4GiTIAj8_Po29duIxEM',2),(55,'ebjdev@naver.com','$2a$10$Qasp3/omNaPeF9ZW7VHi8OcpGGgIqju2rQDG5kv.jBgvVRlG/5tFu','정봉진','ORaABGifIy8wmWn9p8B36unmB82QFqTGU8HfJA2So10=','ebjdev@naver.com','2022-08-17 17:36:09','dB8fJXEGQIe4dEMaYSA9xK:APA91bEufwe0G6JOn1cxj7Uim4JqZ3qRiK-YiCUzHRyLcTss7eWToa7GglWiA5Uq64r1S37c-DSjplIvyjOu0t8jO19o9eL9JZSxYcG9X1YyBCBaKH8-50fd65cmWEiwEyHoUlZoiBj1',2),(56,'test','$2a$10$2U.WdNx/VOxN0iq.N6p3cen6oyDpknk2RNXFaW8MSXnP2nqRUAoU2','Test','rNBLe786aqFIGaRyvXnnfND7E7H4yBGe3qnNj2URElQ=','@gmail.com','2022-08-17 17:37:34','czZn6ESPQwGe2pkM6ma1Vr:APA91bEJYKiY40LXEa3XEzqtbcnbNvRtHzrEN_HoYm_FrwSLEbQXH9YXH1lRSy0Cd5M4Y9bLoUInpkkPBRYQM8zgfbbArcXas-e5sEDtmEVfhCVlnmCdp7V8eC1yZaWl4QuBZqC50noD',2),(57,'ebjdev@gmail.com','$2a$10$1chQwDq4zSK0YAhZK8BKM..U667at0E4qF11joZUGHCC111bj0DTK','Sample','4InpLTwBGA2Mze/0v2tkpZ7CTTkD3KAAEhOC1ppEdks=','ebjdev@gmail.com','2022-08-17 19:02:15','cl9cTL_CQzGzPa7SbsAzQ1:APA91bFC1hug0pxoghyRULFtb0liev_CXGApubU13QZtkCzR8xPoPr0yUaK81LTpJZOGNbxcpH3VWPq9ePhDehc5qOvwhOqgu_LioZ-tfJnreoq1M_eIRNv9EZUpuiNUN0LJneeoDaT5',2),(58,'chanatonya@naver.com','$2a$10$d5LGEFVyUDhQTc30.vvJCezSqKWgjyBXZK7PkkPpZV19SB.VdBRbW','최영희','dDiFnrMktmoGpgx72jDzJM0ORRuqF584br0PYOhaGsg=','chanatonya@naver.com','2022-08-17 20:44:27','eB_5W4D5TLarB9gCpV3OSY:APA91bFmWDvpyuvp0Y7Q6W4n7SsEtqF1xvjOwHYgFMUt-eLtGts7xiP1pKBf1wDLKEqmdO-Ctj_WwKb9lV3JaE2NnozosSjyNjNrHwaJ53akXEwSJouLPWAmN4TdFPJMxL086RuP6nNr',2),(59,'jaemin123','$2a$10$GCwKBvXSX2tYTfvBei6RYeW/I4gBCHNK.AXBM.eC1S4KDc5e..gRy','김재민','48csdwrGEGqUmP0llf33hBax1vbX2Fm7W4ByzjDBejI=','@naver.com','2022-08-17 20:51:18','eB_5W4D5TLarB9gCpV3OSY:APA91bFmWDvpyuvp0Y7Q6W4n7SsEtqF1xvjOwHYgFMUt-eLtGts7xiP1pKBf1wDLKEqmdO-Ctj_WwKb9lV3JaE2NnozosSjyNjNrHwaJ53akXEwSJouLPWAmN4TdFPJMxL086RuP6nNr',2),(62,'nhee0410@naver.com','$2a$10$sTE3Yw8hhe1LgNSUqo6cRuPVnvGaaqvmQbU2nGyDguXDE92rBLqKi','김남희','6UHwj2AM8vAVLkVmxr+FdGudONApOCkd1DfgUDLnQgM=','nhee0410@naver.com','2022-08-18 15:19:29','',1),(63,'1','$2a$10$29He86KaejB3KT45E4PmUuWSnE7N0WJj2.YRuStNxEcr24h8OdfEW','권용준','LZaAvwpByVcMevoWAbJ4RMux9yXmo4AFOgDPwUEhTu0=','test@test.com','2022-08-18 15:23:13','fjsxPsjlTXCXWZJ-bE9Pn8:APA91bEAKT8o66YV0kfi4BW1yEfVDVTpWnfeLw5VyRA0NYp1MsNmVxBx40iytm4uRr2EoZYCy9rpSh81dTIFLb_oBR7E7hJ42bamL7-LD_Mq6Fjtjvt_umZmv3viZcWQ3wDyocVzEL7A',2),(64,'2','$2a$10$64PP.l4jmzMp0n0RY5O2AuPxWQCEIPoU1QIqCRJA.CNHAemrFyAaK','김남희','beN6+x5dx0BH8w2o+wyLbF2b6SAhmxxg9IcsX5Ei9TE=','test@test.com','2022-08-18 15:23:14','cn14fGttRfePu6gp0LgXOY:APA91bHg_xYPQgp-e6Vwc10OE_05-3b6faH5veXo-3mFHTqDsNzCsQ9lJO1FERY3nW_PueHBICdQNtRd56uAWp1POJ78boSW3yLXs8W1N4ZJFZs1dcpGwuXYh3pOW-FbBhLr7De5qdL9',2),(65,'3','$2a$10$iUusv4B1ehHwYJKypsx4nOAKl/4OZnJVCpdz6bzOco9w.9bBjtpwG','김정윤','BJ3kewY8xJ6oPCMYR4vmn5JzyAPD3b/CfQjQu71U/8k=','test@test.com','2022-08-18 15:23:15','fqa69gMkR529JUFPCSDxXv:APA91bFJUl1XdLTh86R7VUZ5Regb5KPDGOp2F48SuA-ROpZi268a5Ld6bwfNMWseSbPm8QN7HzrCXCu2sx48J_EAVnGQMtqhYZGIZ9PtNrUpsRFow7hLg7cvTNhTy9rG_UkmOgK2PABO',2),(66,'4','$2a$10$hSJildHfrJfuAiIXUk2JXuew6U8e79CtCdch8vd7FZgiuJgw05/eO','정봉진','eH0W83E8+ThOLcImUfPJDupj65v2DU6oNqmCPo5bGFk=','test@test.com','2022-08-18 15:23:16','fEUcajKtTmaWfdLjaM-B7o:APA91bHR1acBEOvc80FnRcofV46u3O46vXgeP3OgvUF0GdX5o3JjQhkxUrERVSkZ4FzGAW2tL_4wcpmniuS9p3LFfoO7DKjlDm50oqdB9Fg1ztgenYvdiqEOl4GiTIAj8_Po29duIxEM',2),(67,'5','$2a$10$Xn8uqKXlOGU6t0Uzo5XLAOxU4clUnrxeIDNpxXCjbxEPuM3IsUtLe','최영희','JjAoq10BcKWSMrLcCvIwwo+LUmNQ5pd2iti+ISDAxsY=','test@test.com','2022-08-18 15:23:16',NULL,1),(68,'6','$2a$10$3yiZEg/rsnGRQUgTTge5RefUlzFnDre3UhAnUrXh25t/IoMqGw0qa','편예린','SeQ53HBizUdVffgLauhbgSHAnwfNDozT3KTDn+NsWnA=','test@test.com','2022-08-18 15:23:17','fjsxPsjlTXCXWZJ-bE9Pn8:APA91bEAKT8o66YV0kfi4BW1yEfVDVTpWnfeLw5VyRA0NYp1MsNmVxBx40iytm4uRr2EoZYCy9rpSh81dTIFLb_oBR7E7hJ42bamL7-LD_Mq6Fjtjvt_umZmv3viZcWQ3wDyocVzEL7A',2),(69,'yjisap3176@naver.com','$2a$10$tNUN48isk2axXULWNr0QtOtTIWtOPQDwe72ng88psY9lxNQmAo11O','권용준','RFqg5BXkhuKcn0Myau9YhWDHjuKsHOFxjLkAj929LUM=','yjisap3176@naver.com','2022-08-18 15:44:55','dQfy-aKTSG2QkL-lXRmIdi:APA91bFYrPJmnT4yNjR24AyERgp0cJuXvoDIcmQDrq8sDFBbJZZsVuLW-ZUInk_AXCesce4vB0-XIDfIiSSvXVXsJcvtkGa4IL-wsmgxykq2sWNx_SO8McPALrf2CvAOIzP8Qu0WkvBZ',2);
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
INSERT INTO `user_interest_hashtag` VALUES (54,52),(64,52),(66,52),(54,53),(66,53);
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-18 16:55:09
