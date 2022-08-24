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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm`
--

LOCK TABLES `alarm` WRITE;
/*!40000 ALTER TABLE `alarm` DISABLE KEYS */;
INSERT INTO `alarm` VALUES (32,'14:36:00',1,1,1,1,1,0,0,'00 36 14 ? * MON,TUE,WED,THU,FRI *'),(34,'09:00:00',0,0,0,0,0,1,0,'00 0 9 ? * SAT *'),(35,'09:00:00',1,1,1,1,1,0,0,'00 0 9 ? * MON,TUE,WED,THU,FRI *'),(36,'18:15:00',0,0,0,1,1,0,0,'00 15 18 ? * THU,FRI *'),(39,'20:00:00',1,1,1,1,1,0,0,'00 0 20 ? * MON,TUE,WED,THU,FRI *'),(40,'08:10:00',0,0,0,0,0,0,0,NULL);
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
INSERT INTO `dislike` VALUES (77,78),(77,84),(77,86),(58,99),(58,101);
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
INSERT INTO `follow` VALUES (55,54),(54,59),(54,58),(54,55),(54,68),(54,65),(54,64),(66,59),(66,58),(66,55),(66,68),(66,65),(66,64),(63,54),(68,54),(68,66),(66,54),(65,54),(74,54),(79,54),(54,79),(54,74),(56,54),(65,64),(65,68),(65,74),(65,58),(54,77),(58,64),(58,63);
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
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
INSERT INTO `likes` VALUES (54,60),(66,60),(71,60),(54,61),(66,61),(68,61),(71,61),(54,62),(66,62),(68,62),(71,62),(54,63),(66,63),(68,63),(71,63),(54,64),(66,64),(68,64),(71,64),(54,65),(66,65),(68,65),(71,65),(54,67),(56,67),(66,67),(68,67),(71,67),(54,68),(66,68),(68,68),(71,68),(54,69),(66,69),(68,69),(71,69),(68,70),(71,70),(68,71),(71,71),(54,72),(68,72),(71,72),(54,73),(68,73),(71,73),(54,74),(56,74),(66,74),(68,74),(71,74),(54,75),(68,75),(71,75),(68,76),(71,76),(68,78),(71,78),(68,79),(71,79),(54,81),(65,81),(66,81),(68,81),(71,81),(79,81),(54,82),(56,82),(66,82),(68,82),(71,82),(54,83),(56,83),(66,83),(68,83),(71,83),(54,84),(56,84),(66,84),(68,84),(71,84),(77,84),(54,85),(56,85),(64,85),(66,85),(71,85),(77,85),(54,86),(56,86),(58,86),(66,86),(71,86),(54,87),(56,87),(58,87),(66,87),(71,87),(54,88),(56,88),(66,88),(71,88),(74,88),(77,88),(79,88),(71,92),(71,93),(71,94),(71,95),(71,96),(58,98),(65,99),(80,99),(65,101),(77,103);
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (14,5,'[업데이트] Sduty 1.2 버전 업데이트 안내','2022-08-19 06:08:22'),(15,5,'[점검] 8/19 13:00 ~ 16:00 이용이 중지될 예정입니다','2022-08-19 06:09:54'),(17,5,'[공지] 공통 프로젝트가 8월 19일 종료됩니다!','2022-08-19 06:10:42');
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
INSERT INTO `participation` VALUES (106,54),(107,54),(110,54),(113,54),(107,58),(112,58),(119,58),(112,63),(107,64),(106,66),(107,66),(113,66),(107,68),(110,68),(111,68),(107,74),(114,74),(117,74),(106,77),(107,77),(112,77),(106,79),(112,79),(118,79);
/*!40000 ALTER TABLE `participation` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `profile` VALUES (54,'살려줘','1999-09-05',2,'??','profile/1660797337392.png','대학생',2,0,2,6,4,52,0,0,0,0),(55,'Sample','1999-01-01',2,'Test','profile/1660725407320.png','기타',2,0,2,0,0,52,0,0,0,0),(56,'Hello','1998-12-31',2,'hi','profile/1660736306187.png','대학생',2,0,2,0,0,49,0,0,0,0),(58,'영희','1994-10-18',2,'정보처리기사 공부','profile/1660736747143.png','취업준비생',2,0,2,2,3,52,0,0,0,0),(59,'김재민','2005-11-15',2,'수능','profile/1660781620965.png','고등학생',2,0,2,0,0,43,0,0,0,0),(63,'deve10p_','1999-03-20',2,'네카라쿠배당토','profile/1660806306593.png','대학생',2,0,2,0,0,52,0,0,0,0),(64,'아르마딜로','1998-12-30',2,'게임제작기','profile/1660782609296.png','취업준비생',2,0,2,0,2,52,0,0,0,0),(65,'정윤','1997-03-02',2,'하이','profile/1660804351759.png','대학생',2,0,2,0,0,0,0,0,0,0),(66,'봉진','1999-09-08',2,'안녕하세요','profile/1660805877873.png','취업준비생',2,0,2,7,0,52,0,0,0,0),(68,'steady1117','2004-07-11',2,'아자!','profile/1660804768194.png','고등학생',2,0,2,0,0,43,0,0,0,0),(71,'Test','1998-12-31',2,'Test','profile/1660846082837.png','대학생',2,0,2,0,0,51,0,0,0,0),(74,'치킨먹고싶다','1997-06-21',2,'안녕하세요','profile/1660850008312.png','취업준비생',2,0,2,1,0,52,0,0,0,1),(77,'치킨','1997-03-22',2,'구미 1반 8팀입니다!','profile/1660857960188.png','취업준비생',2,0,2,0,0,52,0,0,0,1),(79,'용준','1997-03-23',2,'안녕하세요','profile/1660860156409.png','직장인',2,0,2,0,0,0,0,0,0,1),(80,'t','1999-12-12',2,'hi','profile/1660989546897.png','취업준비생',2,0,2,0,0,52,0,0,0,0),(82,'요주','1997-03-23',2,'하이','profile/1660996018172.png','고등학생',2,0,2,0,0,45,0,0,0,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qna`
--

LOCK TABLES `qna` WRITE;
/*!40000 ALTER TABLE `qna` DISABLE KEYS */;
INSERT INTO `qna` VALUES (1,54,'ㅎㅎ','ㅎㅎ','살려줘','관리자',''),(2,76,'기능 개선 문의','타임라인 이용시간을 제한 할 수 있었으면 좋겠어요','배고프다','',''),(3,76,'악성 유저 신고','싫어요','배고프다','',''),(4,76,'앱 잠금 기능에서 궁금한 것이 있어요','앱을 지웠다 설치하면 어떻게 되나요?','배고프다','',''),(5,54,'ㅇㅇㅇ','ㅇㅇㅇㅇㅇ','살려줘','관리자','');
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
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (71,54,'????',81,'2022-08-18 17:27:43'),(76,79,'고생하셨어요!!',88,'2022-08-19 07:07:59'),(79,79,'어려워요..',84,'2022-08-19 12:00:11'),(83,54,'그러게요..',84,'2022-08-19 13:33:22'),(85,58,'화이팅!',98,'2022-08-22 03:19:01'),(86,58,'화이팅!',87,'2022-08-22 03:25:44'),(87,58,'굳',86,'2022-08-22 03:35:38');
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
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (38,54,'2022-08-17'),(40,65,'2022-08-18'),(41,68,'2022-08-18'),(42,58,'2022-08-18'),(43,63,'2022-08-18'),(44,64,'2022-08-18'),(45,54,'2022-08-18'),(47,65,'2022-08-19'),(48,54,'2022-08-19'),(49,74,'2022-08-19'),(51,79,'2022-08-19'),(52,77,'2022-08-19'),(53,64,'2022-08-19'),(54,66,'2022-08-19'),(55,58,'2022-08-19'),(56,79,'2022-08-20'),(57,58,'2022-08-22');
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
INSERT INTO `scrap` VALUES (54,60),(54,65),(54,81),(79,81),(77,84),(58,86),(58,87),(74,88),(58,98);
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
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `story`
--

LOCK TABLES `story` WRITE;
/*!40000 ALTER TABLE `story` DISABLE KEYS */;
INSERT INTO `story` VALUES (60,58,'story/1660736797107.png','story/thumbnail-1660736797107.png','2022-08-17 20:46:36',0,0,'DB론 3개년 클리어!!',26),(61,58,'story/1660736867978.png','story/thumbnail-1660736867978.png','2022-08-17 20:47:47',0,0,'정보보호론 두 개 다 품 굿',26),(62,58,'story/1660781324623.png','story/thumbnail-1660781324623.png','2022-08-18 09:08:44',0,0,'소프트웨어 공학 3년치',26),(63,58,'story/1660781362749.png','story/thumbnail-1660781362749.png','2022-08-18 09:09:23',0,0,'자료구조론 문제 풀이',26),(64,59,'story/1660781686641.png','story/thumbnail-1660781686641.png','2022-08-18 09:14:46',0,0,'토익 리딩 공부 중임 난이도 실환가',24),(65,58,'story/1660782006628.png','story/thumbnail-1660782006628.png','2022-08-18 09:20:06',0,0,'자료구조론 기출 더...',26),(67,54,'story/1660801694801.png','story/thumbnail-1660801694801.png','2022-08-18 14:48:16',0,0,'코틀린',28),(68,54,'story/1660801814367.png','story/thumbnail-1660801814367.png','2022-08-06 20:00:00',0,0,'봐도 모르겠다.. ?',28),(69,54,'story/1660802635875.png','story/thumbnail-1660802635875.png','2022-08-07 20:00:00',0,0,'어려워요,,',25),(70,54,'story/1660806773899.png','story/thumbnail-1660806773899.png','2022-08-08 20:00:00',0,0,'코틀린',25),(71,54,'story/1660806839192.png','story/thumbnail-1660806839192.png','2022-08-09 20:00:00',0,0,'람다',25),(72,54,'story/1660807029942.png','story/thumbnail-1660807029942.png','2022-08-10 20:00:00',0,0,'rangeTo',25),(73,54,'story/1660807312509.png','story/thumbnail-1660807312509.png','2022-08-11 20:00:00',0,0,'컬렉션',25),(74,63,'story/1660807334274.png','story/thumbnail-1660807334274.png','2022-08-18 16:22:15',0,0,'쉬운 문제라도 1일 1커밋하자',25),(75,54,'story/1660807350014.png','story/thumbnail-1660807350014.png','2022-08-12 20:00:00',0,0,'컬렉션',25),(76,54,'story/1660807450881.png','story/thumbnail-1660807450881.png','2022-08-13 20:00:00',0,0,'lazy',25),(78,54,'story/1660807836623.png','story/thumbnail-1660807836623.png','2022-08-15 20:00:00',0,0,'제네릭',25),(79,54,'story/1660808040240.png','story/thumbnail-1660808040240.png','2022-08-16 20:00:00',0,0,'Coroutine async',25),(81,63,'story/1660808371455.png','story/thumbnail-1660808371455.png','2022-08-18 16:39:32',0,0,'JPA를 사용한 프로젝트.. 맞게쓰고있는지 모르겠다',25),(82,63,'story/1660808901744.png','story/thumbnail-1660808901744.png','2022-08-18 16:48:23',0,0,'구글링으로 부족해서 유튜브로 공부',25),(83,64,'story/1660809141854.png','story/thumbnail-1660809141854.png','2022-08-18 16:52:21',0,0,'게임제작일지 #1 : 컨셉 회의\n\n대회 준비를 위해서 컨셉 회의를 진행했다.\n원하는 컨셉이 달라서 애먹었지만\n그래도 합의돼서 다행이다',26),(84,54,'story/1660811460472.png','story/thumbnail-1660811460472.png','2022-08-19 13:00:00',0,0,'코틀린 ???',25),(85,68,'story/1660812824288.png','story/thumbnail-1660812824288.png','2022-08-18 17:53:45',0,0,'D-91',24),(86,64,'story/1660814222200.png','story/thumbnail-1660814222200.png','2022-08-18 18:17:01',0,0,'게임 제작 일지 #2 : 맵 제작',26),(87,64,'story/1660821169504.png','story/thumbnail-1660821169504.png','2022-08-18 20:12:48',0,0,'게임 제작 일지 #3 : Unity 시작\n\n물리엔진 다루기 왜 이리 어렵냐\n너 어디 가 진짜 ㅠㅠ',26),(88,64,'story/1660821386249.png','story/thumbnail-1660821386249.png','2022-08-18 20:16:24',0,0,'게임 제작 일지 #4 : 버그 fix\n\n좀 되는 듯?',26),(92,77,'story/1660874242092.png','story/thumbnail-1660874242092.png','2022-08-10 16:00:00',0,0,'데이터베이스',26),(93,77,'story/1660874267264.png','story/thumbnail-1660874267264.png','2022-08-10 15:00:00',0,0,'b tree',26),(94,77,'story/1660874302114.png','story/thumbnail-1660874302114.png','2022-08-10 14:00:00',0,0,'아두이노',26),(95,77,'story/1660874326066.png','story/thumbnail-1660874326066.png','2022-08-10 13:00:00',0,0,'아두이노',26),(96,77,'story/1660874360613.png','story/thumbnail-1660874360613.png','2022-08-10 12:00:00',0,0,'더미',26),(98,77,'story/1660885613027.png','story/thumbnail-1660885613027.png','2022-08-19 14:06:50',0,0,'[2022/08/19]\n오늘의 코틀린 컬렉션 공부\n어려웠다... 오늘도 화이팅 ? ?',26),(99,64,'story/1660906225130.png','story/thumbnail-1660906225130.png','2022-08-19 19:50:27',0,0,'도비 이즈 프리이이ㅣ이이ㅣ이이',26),(101,79,'story/1660995202317.png','story/thumbnail-1660995202317.png','2022-08-20 20:33:23',0,0,'양다리',27),(103,58,'story/1661106994196.png','story/thumbnail-1661106994196.png','2022-08-22 03:36:34',0,0,'토익 공부',26);
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
INSERT INTO `story_interest_hashtag` VALUES (85,43),(69,45),(70,45),(72,45),(73,45),(75,45),(76,45),(84,45),(86,45),(87,45),(101,46),(64,47),(60,49),(61,49),(101,49),(60,50),(61,50),(62,50),(65,50),(62,52),(65,52),(67,52),(70,52),(71,52),(72,52),(73,52),(74,52),(75,52),(76,52),(81,52),(82,52),(84,52),(86,52),(87,52),(88,52),(92,52),(93,52),(95,52),(96,52),(98,52),(68,53),(71,53),(101,53),(99,54);
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
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study`
--

LOCK TABLES `study` WRITE;
/*!40000 ALTER TABLE `study` DISABLE KEYS */;
INSERT INTO `study` VALUES (106,54,NULL,'일반 스터디','구미 D108','IT',4,6,NULL,NULL,'2022-08-17 20:34:21','공지사항'),(107,54,32,'8팀 입니다?','구미 D108','IT',7,10,NULL,'b72dc4ef-f959-456b-a6ab-c79d1f254800','2022-08-17 20:35:54','공지사항'),(110,68,NULL,'20221117 수능','주중 공부시간 10시간 채우기\n3회 이상 미완료시 강퇴됩니다.','수능',2,2,NULL,NULL,'2022-08-17 21:42:14','공지사항'),(111,68,34,'기출시험','실제 수능처럼 시간 정해서 기출 풀기','수능',1,2,'1117','d0575bd5-776d-4d1c-8de3-630163f7621f','2022-08-17 21:43:24','공지사항'),(112,63,35,'모각코ㄱㄱ','모여서 각자 코딩','IT',4,10,NULL,'8ceefd9f-a4d4-46fc-abac-537e7f74ecf4','2022-08-17 22:06:12','공지사항'),(113,66,36,'토스','ㅎ','어학',2,2,'1414','f4a4d502-9093-4acc-a43b-1b14298e1824','2022-08-18 09:09:58','공지사항'),(114,74,NULL,'한사랑 독서회','하루 두 시간, 한 달 한 권 이상 책읽기!! ?','독서',1,8,NULL,NULL,'2022-08-18 19:53:24','잠수 회원 강퇴하겠습니다'),(117,74,39,'SSAFY 모각코','캠켜서 모각코 하실 분~\n모니터 화면만 나와도 되며,\n매일 7시에 시작합니다','독서',3,10,NULL,'59441bbb-1399-4dda-b3ba-5c24d276881a','2022-08-18 20:27:18','주말은 쉽니다!!'),(118,79,40,'테스트','ㅇㅇ','예비고1',1,2,NULL,'20ae4837-e91b-4c91-a179-b790d7c98d8c','2022-08-18 23:10:25','공지사항'),(119,58,NULL,'심야 공부팀','밤에 공부하실래요?','IT',1,6,NULL,NULL,'2022-08-21 18:38:39','공지사항');
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
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (88,38,'kotlin','thread','13:46:29','14:28:57',2548,'',''),(95,38,'안드로이드','UI','14:38:54','15:04:03',1509,'Coroutine',''),(97,40,'제목','내용','15:33:00','15:36:56',236,'',''),(98,41,'국어','매3비 37일차 풀+오','09:00:00','11:30:00',9000,'고전소설 분석:홍계월전',NULL),(99,41,'영어','영어단어','11:40:00','12:08:52',1732,NULL,NULL),(100,41,'수학','[미적분] 수분감25강 적분법','13:12:00','16:51:11',13151,'[확통] 수분감 21강 통계1',NULL),(101,41,'영어','수능 완성 14강','17:02:00','18:32:01',5401,NULL,NULL),(102,41,'물리','수능완성10강 풀+오','19:14:00','21:00:30',6390,NULL,NULL),(103,41,'영어','영어단어정리','22:00:00','22:56:10',3370,NULL,NULL),(104,42,'정보보호론 오답 빠른 풀이','','15:42:06','15:49:37',451,'',''),(105,43,'정처기 기출 풀기','[필기] 2021 3회차','09:00:00','11:30:00',9000,NULL,NULL),(107,43,'알고리즘 문제','백준:1113번','13:10:00','15:12:33',7353,'프로그래머스:괄호변환',NULL),(108,43,'CS 학습','[Java] GC','17:00:00','18:26:51',5211,NULL,NULL),(110,43,'알고 스터디',NULL,'20:00:00','20:43:02',2582,NULL,NULL),(111,43,'JPA 학습','강의 2회차','15:30:00','16:37:12',4032,'블로그에 내용정리',NULL),(113,38,'Java','JPA','15:46:18','16:38:11',3113,'',''),(115,38,'알고리즘','백트래킹','10:00:00','12:44:00',9840,'',''),(116,44,'게임제작회의','','16:32:01','16:49:41',1060,'',''),(117,38,'안드로이드','LiveData','10:10:00','12:23:00',7980,'',''),(119,45,'안드로이드','Coroutine','10:30:03','12:16:00',11760,'',''),(120,45,'Java','JPA','12:20:00','15:40:00',12000,'',''),(123,45,'알고리즘','DP','08:33:00','10:41:00',7680,'',''),(125,45,'프로젝트','8팀','17:44:23','17:44:29',6,'',''),(126,44,'게임 맵 제작','','16:53:09','18:13:02',4793,'',''),(127,44,'Unity','','18:17:31','18:22:14',283,'',''),(129,44,'Unity 버그 고치기','','20:15:03','20:15:06',3,'',''),(130,47,'1','2','01:59:26','02:03:34',248,'',''),(131,48,'프로젝트','힘들다','01:21:00','03:41:00',8400,'',''),(132,48,'안드로이드','애니메이션','03:52:39','03:54:09',90,'',''),(136,49,'안드로이드 개발','Sduty 개발','10:00:00','13:07:00',11220,'',''),(137,49,'알고리즘','SWEA 무선 충전','17:00:00','18:23:00',4980,'',''),(141,51,'알고리즘','SWEA 무선 충전','07:23:50','07:25:18',88,'',''),(142,51,'문제 리뷰','','08:11:00','09:05:00',3240,'',''),(143,51,'오픽','2단원 문제 풀이','09:00:00','11:17:00',8220,'',''),(144,52,'자기소개서 작성','','07:49:56','09:34:34',6278,'',''),(145,53,'자료 제작','','03:53:09','10:20:08',23219,'',''),(146,54,'알고리즘 스터디','','10:23:51','10:23:53',2,'',''),(148,48,'프로젝트','살려줘','03:54:34','10:39:40',24306,'',''),(152,55,'오','','03:54:07','11:01:26',25639,'',''),(153,48,'코틀린','제네릭','10:39:56','11:24:27',2671,'',''),(156,52,'시스템','','06:11:00','07:20:00',4140,'',''),(157,52,'데이터베이스','B tree','10:00:00','11:00:00',3600,'스키마',''),(160,52,'안드로이드','코틀린','11:30:00','12:49:00',4740,'',''),(161,47,'1','2','11:36:36','13:54:04',8248,'',''),(162,48,'프로젝트','힘들었다','13:10:40','19:49:56',23956,'',''),(163,56,'dd','','20:23:19','20:28:07',288,'',''),(164,57,'공부 시간 측정','','03:26:55','03:27:06',11,'',''),(165,57,'심야 공부','','03:36:50','03:37:00',10,'','');
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
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (54,'darkberry@naver.com','$2a$10$Sbo9.qQYj359btskaStZGeTZOozjMJU1lXi5DIunRXXlTP5bgRoru','정봉진','+gHB4aw2p+ef1wKAQQD3VlSsCKCBw8hFuUrMLDLcEOw=','darkberry@naver.com','2022-08-17 17:35:20','csdbvTzyShaSEMf-irDlB0:APA91bG2CZ0KbRp6ghqcHRtz-EQp_oeOzoTuvazoqN_oC5aaZh_fGrERcK3tIIGT1I5kOSuAvLFX8W7Dl8ocxtsL4yQ33oNUR-EkfGQHaqvmX7rzES0lZ6aI1oimd27MIN9ZBaZwxoU8',0),(55,'ebjdev@naver.com','$2a$10$Qasp3/omNaPeF9ZW7VHi8OcpGGgIqju2rQDG5kv.jBgvVRlG/5tFu','정봉진','ORaABGifIy8wmWn9p8B36unmB82QFqTGU8HfJA2So10=','ebjdev@naver.com','2022-08-17 17:36:09','dB8fJXEGQIe4dEMaYSA9xK:APA91bEufwe0G6JOn1cxj7Uim4JqZ3qRiK-YiCUzHRyLcTss7eWToa7GglWiA5Uq64r1S37c-DSjplIvyjOu0t8jO19o9eL9JZSxYcG9X1YyBCBaKH8-50fd65cmWEiwEyHoUlZoiBj1',2),(56,'test','$2a$10$baB/NpcCTKRa5ep0jE5UUuNPUmj6Tq.Ko7.8Iy0eiXrVors1lPoN2','Test','XYXFP10xXYROeXP+dvq/dVJttxu8oWoOGP+xvsL3bFY=','@gmail.com','2022-08-17 17:37:34','f1UsQmaCSDyqindqJYStf2:APA91bFuMKUsVq5O7oHsZyQP6Ed_qmFgxMymMH8xZP4L_p4QfbsmwOkvX90x2LppfsJ0qLG57bbnqIHyxpBTK_A96ZLgYJ60hqrHIm_sf1KoqMfT19_TA6cfMaVMNfmZjgW5gXhuke4x',2),(58,'chanatonya@naver.com','$2a$10$URKxjcp8WSyti7zlMPITvezITuN5KLX83Z7GZOad91QRUSL.Nyr8.','최영희','Hn/v0ZFDOMY3ulKsLZrX0xzwE9FKwhvYb75s9+gxDZ0=','chanatonya@naver.com','2022-08-17 20:44:27','dm8VYc3QSBOevik1aLXDn0:APA91bGM97L6xbw8UOYCtbO88wn_2aBhg3Qjt0CGvqjHLPQE5CDoW0NFbQt5AmVr0vufzxDyHXLuwAOIepUxUAuSawp21KWVuM_Dfvn13stPeSPPD1VMZdmkHb8W4OSmMj-pZCPy1CS8',2),(59,'jaemin123','$2a$10$GCwKBvXSX2tYTfvBei6RYeW/I4gBCHNK.AXBM.eC1S4KDc5e..gRy','김재민','48csdwrGEGqUmP0llf33hBax1vbX2Fm7W4ByzjDBejI=','@naver.com','2022-08-17 20:51:18','eB_5W4D5TLarB9gCpV3OSY:APA91bFmWDvpyuvp0Y7Q6W4n7SsEtqF1xvjOwHYgFMUt-eLtGts7xiP1pKBf1wDLKEqmdO-Ctj_WwKb9lV3JaE2NnozosSjyNjNrHwaJ53akXEwSJouLPWAmN4TdFPJMxL086RuP6nNr',2),(62,'nhee0410@naver.com','$2a$10$sTE3Yw8hhe1LgNSUqo6cRuPVnvGaaqvmQbU2nGyDguXDE92rBLqKi','김남희','6UHwj2AM8vAVLkVmxr+FdGudONApOCkd1DfgUDLnQgM=','nhee0410@naver.com','2022-08-18 15:19:29','',1),(63,'1','$2a$10$8/Cf6TRjZxHW3H5GtdG5NedPOKv2fSO5GJTqZjfDRElSQwRDVVVf6','권용준','Q2Ipejyg26CQldGd6S6/RC53KPUd0DTtGEml8hVSS9U=','test@test.com','2022-08-18 15:23:13','f1UsQmaCSDyqindqJYStf2:APA91bFuMKUsVq5O7oHsZyQP6Ed_qmFgxMymMH8xZP4L_p4QfbsmwOkvX90x2LppfsJ0qLG57bbnqIHyxpBTK_A96ZLgYJ60hqrHIm_sf1KoqMfT19_TA6cfMaVMNfmZjgW5gXhuke4x',2),(64,'2','$2a$10$2BUKCGurw3NwGp56d7gPxelu3tcst1vYvlf01QBqAiPngrafBuhkq','김남희','00zalOQKS4L4gVpllZ74sBKymTPZg+HDXO/ZE6NtE3I=','test@test.com','2022-08-18 15:23:14','cn14fGttRfePu6gp0LgXOY:APA91bHg_xYPQgp-e6Vwc10OE_05-3b6faH5veXo-3mFHTqDsNzCsQ9lJO1FERY3nW_PueHBICdQNtRd56uAWp1POJ78boSW3yLXs8W1N4ZJFZs1dcpGwuXYh3pOW-FbBhLr7De5qdL9',2),(65,'3','$2a$10$MM.0q8tfAE8VV5cB62bvHeuZDPsbVI5YiXKMYBbuD2OOM7D1g4A2W','김정윤','CmPEXbWkdW5Uk+gONDOQFsicuvU3Q5WzIUKqvFxfBnE=','test@test.com','2022-08-18 15:23:15','fqa69gMkR529JUFPCSDxXv:APA91bFJUl1XdLTh86R7VUZ5Regb5KPDGOp2F48SuA-ROpZi268a5Ld6bwfNMWseSbPm8QN7HzrCXCu2sx48J_EAVnGQMtqhYZGIZ9PtNrUpsRFow7hLg7cvTNhTy9rG_UkmOgK2PABO',2),(66,'4','$2a$10$JTjZw5e1riNl0xG9BXSjk.CAd0Sc8cRvExQ.b9hX.1Q4dRlKQyHi6','정봉진','95V+Wdn19twn8OEniJYVCcBEhmDiSn3k1A4KGO7SdnI=','test@test.com','2022-08-18 15:23:16','cn14fGttRfePu6gp0LgXOY:APA91bHg_xYPQgp-e6Vwc10OE_05-3b6faH5veXo-3mFHTqDsNzCsQ9lJO1FERY3nW_PueHBICdQNtRd56uAWp1POJ78boSW3yLXs8W1N4ZJFZs1dcpGwuXYh3pOW-FbBhLr7De5qdL9',2),(67,'5','$2a$10$Xn8uqKXlOGU6t0Uzo5XLAOxU4clUnrxeIDNpxXCjbxEPuM3IsUtLe','최영희','JjAoq10BcKWSMrLcCvIwwo+LUmNQ5pd2iti+ISDAxsY=','test@test.com','2022-08-18 15:23:16',NULL,1),(68,'6','$2a$10$nlbxnL3VweXJBWxoM6GuZuJdwTJ8RCN.R7kSz4UYNOYZg7bn8Zp2G','편예린','NBSTn4e9vdIzwtdRQ++FPo0Fw7WweybymbzpAMN5Q+Q=','test@test.com','2022-08-18 15:23:17','fjsxPsjlTXCXWZJ-bE9Pn8:APA91bEAKT8o66YV0kfi4BW1yEfVDVTpWnfeLw5VyRA0NYp1MsNmVxBx40iytm4uRr2EoZYCy9rpSh81dTIFLb_oBR7E7hJ42bamL7-LD_Mq6Fjtjvt_umZmv3viZcWQ3wDyocVzEL7A',2),(71,'ebjdev@gmail.com','$2a$10$gkHzd8x6IdyNAUAI/vAekOWJXtHLF2mVXJ8UqNMu0BLsCHDJkj.ry','Sample','yMRN16Ym/q+M5vnD/RH3VZ74kJxjLnzlTtkFpw3V4v0=','ebjdev@gmail.com','2022-08-19 03:06:48','dutnoOn3RyiNPS7iRmJzJ1:APA91bGLOvl9-iLZA1HCrSzW_a11Do3Cza_O3q6ezjQw2eU14iERKLf-25r_0MWPtP6-7Um3KDFzZ9iE-eTYaYIQ-8WCqNi0CLGnUyEG9AaMVxtrxUX0I2Tj3TPjgcyVnfk3DuG__ycp',2),(74,'taxfdi6371','$2a$10$y9Za6Ku1FDc4AqOxkGiYsez985te6wJhbw352MA0KtEoxVpARazby','권용준','DcSUu1p064c7sD20uXQRAcb69UPWM11IoEeQdOX6oRs=','@gmail.com','2022-08-19 03:55:35','cYYoB1d-TQGd-9yxk-3eoj:APA91bFf___-TD_Wsm2J2bYmsOcZpo1EIPBcLI8DtDywxtJ74ACgYcZzaVodSklbS20AlMv-zyCq03RWv3gXhaXttZOhCnWD4rnwTcB2UmVSVz6uYdm422IPkelGQQ6xdXCbWjVdZTvz',2),(77,'yjisap3176@naver.com','$2a$10$eANK5P./M/yMhHZ4CTiIbegumllJ7gY5J7AK50JUlMFf2B1OMjJda','권용준','eMV35pP0yzIxlyTO2oW8t/vRsNjVlf2nQfDJG3JvwFA=','yjisap3176@naver.com','2022-08-19 06:25:02','eyXPXTSsSP2pTFQp70kFoj:APA91bHhT-kbPQ8TuKvHa59Jqa4kTrdq1DfXEtUMKDAZgWsFJqPjoxxu-EibQbou8evEW55YQn8UtNeni8BJ00o-TTqazxtRcje2hgBNuSEqjTMki8egwa7JOGko5dqAoqBwBEnBgR6C',2),(78,'ssafy108','$2a$10$KEi3.qq.PwS3GRc1EvxEAeSzB5OFQUgLJwKq5/Mm7BjnLz6QVB1QS','권용준','ru/e7haJ0MOFG2nBQPD41BMOV8688pmrVWaiF3yHqvY=','@gmail.com','2022-08-19 06:51:23','',1),(79,'taxfdi6371@gmail.com','$2a$10$Gz3CL0TxzHW1mTQCvXaDOeHhFsQEC5FI8C.6KOJAvKEqW6NSP1ZrO','권용준','ADvFYaiVSZXNB4BACDrisvhsMAE29CBEsB0W1b80rvY=','taxfdi6371@gmail.com','2022-08-19 06:57:28','dogLJCfORiKu9XzqyPszAT:APA91bHFrChks7jvbAJ6Zd5j47qwAL0K0IV7zcVGPiGfTeBjKhhMjov_pmi2ZRebmWZNQGym0ZrpbzcEyAns9exvx7bShNonlGR_BPhHpzTxUYLmNQ83JVfc0TjlyT7nIFV1tWNg33Gd',2),(80,'t','$2a$10$7TOT13gccEycoEHCcs3EY.T5aQm6Lj6HWjCm2GdyEcGW4aa5cUAIO','테스트','KNNG6qk6Cs6RYguoEMf4DRg4kZ+wFAACLPhzpltUvVg=','@gmail.com','2022-08-20 18:58:27','fgb5Gdu0SQy03p3P9xWUGS:APA91bHUAWR_CGD2aaNBqVSE-5cRoSI4yXmJKxiHf8ZQkwbLh-z7RJh-XWZuKFkLs6XKQ-DsY4hntJzaRHmYyitGuTTcq3S9MNi3W0tOG2UTkDnFyCFu3jt31-_6CPV9aZeflzpIsrm_',2),(81,'ss9ogbmm0r@gmail.com','$2a$10$8EfFZCaAGZbWRq1aR.b8juQzcxsRnQEBAuTbClb5tLSWVQuM8o/We','김경표','LM2yWxtMUQ4pgy1dy4QlTiHOQSPwwByhjsXuUB5hr1w=','ss9ogbmm0r@gmail.com','2022-08-20 20:42:46','',1),(82,'ssafy','$2a$10$t4tcxzIVYlRzjI2auLOS7.bV1C3NRvn.EdDf13S5P19w99eCXf1VW','요주','WMa9f6yhrN61Bz6nn2qE8igE8hYZMKp+UBnC7J6nu3U=','@gmail.com','2022-08-20 20:46:32','dogLJCfORiKu9XzqyPszAT:APA91bHFrChks7jvbAJ6Zd5j47qwAL0K0IV7zcVGPiGfTeBjKhhMjov_pmi2ZRebmWZNQGym0ZrpbzcEyAns9exvx7bShNonlGR_BPhHpzTxUYLmNQ83JVfc0TjlyT7nIFV1tWNg33Gd',2);
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
INSERT INTO `user_interest_hashtag` VALUES (54,45),(74,47),(77,47),(77,51),(54,52),(58,52),(64,52),(66,52),(74,52),(77,52),(66,53);
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

-- Dump completed on 2022-08-24 13:11:02
