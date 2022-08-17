-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sduty
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sduty
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sduty` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `sduty` ;

-- -----------------------------------------------------
-- Table `sduty`.`achievement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`achievement` ;

CREATE TABLE IF NOT EXISTS `sduty`.`achievement` (
  `achievement_seq` INT NOT NULL AUTO_INCREMENT,
  `achievement_name` VARCHAR(45) NOT NULL,
  `achievement_content` VARCHAR(100) NOT NULL,
  `achievement_is_hidden` TINYINT NOT NULL DEFAULT '0',
  PRIMARY KEY (`achievement_seq`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sduty`.`admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`admin` ;

CREATE TABLE IF NOT EXISTS `sduty`.`admin` (
  `admin_seq` INT NOT NULL AUTO_INCREMENT,
  `admin_id` VARCHAR(45) NOT NULL,
  `admin_password` CHAR(60) NOT NULL,
  `admin_name` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`admin_seq`),
  UNIQUE INDEX `admin_id_UNIQUE` (`admin_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sduty`.`alarm`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`alarm` ;

CREATE TABLE IF NOT EXISTS `sduty`.`alarm` (
  `alarm_seq` INT NOT NULL AUTO_INCREMENT,
  `alarm_time` TIME NOT NULL,
  `mon` TINYINT NOT NULL DEFAULT '0',
  `tue` TINYINT NOT NULL DEFAULT '0',
  `wed` TINYINT NOT NULL DEFAULT '0',
  `thu` TINYINT NOT NULL DEFAULT '0',
  `fri` TINYINT NOT NULL DEFAULT '0',
  `sat` TINYINT NOT NULL DEFAULT '0',
  `sun` TINYINT NOT NULL DEFAULT '0',
  `alarm_cron` CHAR(50) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`alarm_seq`))
ENGINE = InnoDB
AUTO_INCREMENT = 32
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`user` ;

CREATE TABLE IF NOT EXISTS `sduty`.`user` (
  `user_seq` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(40) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `user_password` CHAR(60) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `user_name` CHAR(30) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `user_tel` VARCHAR(50) NOT NULL,
  `user_email` CHAR(50) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `user_regtime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_fcm_token` VARCHAR(250) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `user_public` TINYINT NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_seq`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 35
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`story`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`story` ;

CREATE TABLE IF NOT EXISTS `sduty`.`story` (
  `story_seq` INT NOT NULL AUTO_INCREMENT,
  `story_writer_seq` INT NOT NULL,
  `story_image_source` CHAR(100) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `story_thumbnail` CHAR(100) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `story_regtime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `story_public` INT NOT NULL DEFAULT '2',
  `story_warning` INT NOT NULL DEFAULT '0',
  `story_contents` VARCHAR(140) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `story_job_hashtag` INT NULL DEFAULT NULL,
  PRIMARY KEY (`story_seq`),
  UNIQUE INDEX `story_seq_UNIQUE` (`story_seq` ASC) VISIBLE,
  INDEX `story_writer_seq_idx` (`story_writer_seq` ASC) VISIBLE,
  INDEX `story_job_hashtag_idx` (`story_job_hashtag` ASC) VISIBLE,
  CONSTRAINT `story_writer_seq`
    FOREIGN KEY (`story_writer_seq`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 57
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`comment` ;

CREATE TABLE IF NOT EXISTS `sduty`.`comment` (
  `comment_seq` INT NOT NULL AUTO_INCREMENT,
  `comment_story_seq` INT NOT NULL,
  `comment_writer_seq` INT NOT NULL,
  `comment_content` VARCHAR(200) NOT NULL,
  `comment_regtime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comment_mentioned_seq` INT NULL DEFAULT NULL,
  PRIMARY KEY (`comment_seq`),
  INDEX `comment_mentioned_seq_idx` (`comment_mentioned_seq` ASC) VISIBLE,
  INDEX `comment_writer_seq_idx` (`comment_writer_seq` ASC) VISIBLE,
  INDEX `comment_story_seq_idx` (`comment_story_seq` ASC) VISIBLE,
  CONSTRAINT `comment_mentioned_seq`
    FOREIGN KEY (`comment_mentioned_seq`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `comment_story_seq`
    FOREIGN KEY (`comment_story_seq`)
    REFERENCES `sduty`.`story` (`story_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `comment_writer_seq`
    FOREIGN KEY (`comment_writer_seq`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sduty`.`daily_question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`daily_question` ;

CREATE TABLE IF NOT EXISTS `sduty`.`daily_question` (
  `dailyq_seq` INT NOT NULL AUTO_INCREMENT,
  `dailyq_admin_seq` INT NOT NULL,
  `dailyq_content` VARCHAR(200) NOT NULL,
  `dailyq_regtime` TIMESTAMP NOT NULL,
  PRIMARY KEY (`dailyq_seq`),
  INDEX `dailyq_admin_seq_idx` (`dailyq_admin_seq` ASC) VISIBLE,
  CONSTRAINT `dailyq_admin_seq`
    FOREIGN KEY (`dailyq_admin_seq`)
    REFERENCES `sduty`.`admin` (`admin_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sduty`.`dislike`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`dislike` ;

CREATE TABLE IF NOT EXISTS `sduty`.`dislike` (
  `dislike_user_seq` INT NOT NULL,
  `dislike_story_seq` INT NOT NULL,
  PRIMARY KEY (`dislike_user_seq`, `dislike_story_seq`),
  INDEX `dislike_story_seq_idx` (`dislike_story_seq` ASC) VISIBLE,
  CONSTRAINT `dislike_story_seq`
    FOREIGN KEY (`dislike_story_seq`)
    REFERENCES `sduty`.`story` (`story_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `dislike_user_seq`
    FOREIGN KEY (`dislike_user_seq`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sduty`.`follow`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`follow` ;

CREATE TABLE IF NOT EXISTS `sduty`.`follow` (
  `follower_seq` INT NOT NULL,
  `followee_seq` INT NOT NULL,
  INDEX `followee_seq_idx` (`followee_seq` ASC) VISIBLE,
  INDEX `follower_seq` (`follower_seq` ASC) VISIBLE,
  CONSTRAINT `followee_seq`
    FOREIGN KEY (`followee_seq`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `follower_seq`
    FOREIGN KEY (`follower_seq`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`hashtag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`hashtag` ;

CREATE TABLE IF NOT EXISTS `sduty`.`hashtag` (
  `hashtag_seq` INT NOT NULL,
  `hashtag_name` VARCHAR(50) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  PRIMARY KEY (`hashtag_seq`),
  UNIQUE INDEX `hashtag_name_UNIQUE` (`hashtag_name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`identification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`identification` ;

CREATE TABLE IF NOT EXISTS `sduty`.`identification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tel` VARCHAR(11) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `code` VARCHAR(6) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `expire` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 46
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`image` ;

CREATE TABLE IF NOT EXISTS `sduty`.`image` (
  `image_id` INT NOT NULL AUTO_INCREMENT,
  `image_name` VARCHAR(100) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `image_url` VARCHAR(200) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  PRIMARY KEY (`image_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`interest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`interest` ;

CREATE TABLE IF NOT EXISTS `sduty`.`interest` (
  `interest_user_seq` INT NOT NULL,
  `interest_hashtag_seq` INT NOT NULL,
  PRIMARY KEY (`interest_user_seq`, `interest_hashtag_seq`),
  INDEX `interest_hashtag_seq_idx` (`interest_hashtag_seq` ASC) VISIBLE,
  CONSTRAINT `interest_hashtag_seq`
    FOREIGN KEY (`interest_hashtag_seq`)
    REFERENCES `sduty`.`hashtag` (`hashtag_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `interest_user_seq`
    FOREIGN KEY (`interest_user_seq`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`interest_hashtag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`interest_hashtag` ;

CREATE TABLE IF NOT EXISTS `sduty`.`interest_hashtag` (
  `interest_hashtag_name` VARCHAR(10) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `interest_hashtag_seq` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`interest_hashtag_seq`))
ENGINE = InnoDB
AUTO_INCREMENT = 29
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`job`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`job` ;

CREATE TABLE IF NOT EXISTS `sduty`.`job` (
  `job_user_seq` INT NOT NULL,
  `job_hashtag_seq` INT NOT NULL,
  PRIMARY KEY (`job_user_seq`, `job_hashtag_seq`),
  CONSTRAINT `job_user_seq`
    FOREIGN KEY (`job_user_seq`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`job_hashtag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`job_hashtag` ;

CREATE TABLE IF NOT EXISTS `sduty`.`job_hashtag` (
  `job_hashtag_name` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `job_hashtag_seq` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`job_hashtag_seq`))
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`likes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`likes` ;

CREATE TABLE IF NOT EXISTS `sduty`.`likes` (
  `likes_user_seq` INT NOT NULL,
  `likes_story_seq` INT NOT NULL,
  PRIMARY KEY (`likes_user_seq`, `likes_story_seq`),
  INDEX `like_story_seq_idx` (`likes_story_seq` ASC) VISIBLE,
  CONSTRAINT `like_story_seq`
    FOREIGN KEY (`likes_story_seq`)
    REFERENCES `sduty`.`story` (`story_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `like_user_seq`
    FOREIGN KEY (`likes_user_seq`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`notice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`notice` ;

CREATE TABLE IF NOT EXISTS `sduty`.`notice` (
  `notice_seq` INT NOT NULL AUTO_INCREMENT,
  `notice_writer_seq` INT NOT NULL,
  `notice_content` VARCHAR(45) NOT NULL,
  `notice_regtime` DATETIME NOT NULL,
  PRIMARY KEY (`notice_seq`),
  INDEX `notice_writer_seq_idx` (`notice_writer_seq` ASC) VISIBLE,
  CONSTRAINT `notice_writer_seq`
    FOREIGN KEY (`notice_writer_seq`)
    REFERENCES `sduty`.`admin` (`admin_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sduty`.`study`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`study` ;

CREATE TABLE IF NOT EXISTS `sduty`.`study` (
  `study_seq` INT NOT NULL AUTO_INCREMENT,
  `study_master_seq` INT NOT NULL,
  `study_alarm_seq` INT NULL DEFAULT NULL,
  `study_name` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `study_introduce` VARCHAR(100) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `study_category` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `study_join_number` INT NOT NULL DEFAULT '1',
  `study_limit_number` INT NOT NULL DEFAULT '2',
  `study_password` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `study_room_id` CHAR(70) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `study_regtime` TIMESTAMP NOT NULL,
  `study_notice` CHAR(200) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`study_seq`),
  INDEX `study_master_seq` (`study_master_seq` ASC) VISIBLE,
  INDEX `study_alarm_seq` (`study_alarm_seq` ASC) VISIBLE,
  CONSTRAINT `study_alarm_seq`
    FOREIGN KEY (`study_alarm_seq`)
    REFERENCES `sduty`.`alarm` (`alarm_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `study_master_seq`
    FOREIGN KEY (`study_master_seq`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 106
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`participation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`participation` ;

CREATE TABLE IF NOT EXISTS `sduty`.`participation` (
  `participation_study_seq` INT NOT NULL,
  `participation_user_seq` INT NOT NULL,
  PRIMARY KEY (`participation_study_seq`, `participation_user_seq`),
  INDEX `participation_user_seq_idx` (`participation_user_seq` ASC) VISIBLE,
  CONSTRAINT `participation_study_seq`
    FOREIGN KEY (`participation_study_seq`)
    REFERENCES `sduty`.`study` (`study_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `participation_user_seq`
    FOREIGN KEY (`participation_user_seq`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sduty`.`profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`profile` ;

CREATE TABLE IF NOT EXISTS `sduty`.`profile` (
  `profile_user_seq` INT NOT NULL,
  `profile_nickname` VARCHAR(40) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `profile_birthday` DATE NOT NULL,
  `profile_public_birthday` TINYINT NOT NULL DEFAULT '1',
  `profile_short_introduce` VARCHAR(80) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `profile_image` CHAR(100) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `profile_job` CHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `profile_public_job` TINYINT NOT NULL DEFAULT '2',
  `profile_interest` INT NOT NULL,
  `profile_public_interest` INT NOT NULL DEFAULT '2',
  `profile_followers` INT NOT NULL DEFAULT '0',
  `profile_followees` INT NOT NULL DEFAULT '0',
  `profile_main_achievement_seq` INT NOT NULL DEFAULT '0',
  `profile_block_action` TINYINT NOT NULL DEFAULT '0',
  `profile_warning` INT NOT NULL DEFAULT '0',
  `is_prohibited_user` TINYINT NOT NULL DEFAULT '0',
  `is_studying` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`profile_user_seq`),
  UNIQUE INDEX `profile_user_seq_UNIQUE` (`profile_user_seq` ASC) VISIBLE,
  CONSTRAINT `user_seq`
    FOREIGN KEY (`profile_user_seq`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`qna`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`qna` ;

CREATE TABLE IF NOT EXISTS `sduty`.`qna` (
  `qna_seq` INT NOT NULL AUTO_INCREMENT,
  `ques_title` VARCHAR(50) NOT NULL,
  `ques_content` VARCHAR(100) NOT NULL,
  `ques_category` VARCHAR(50) NOT NULL,
  `ques_writer` INT NOT NULL,
  `ques_regtime` TIMESTAMP NOT NULL,
  `ans_content` VARCHAR(100) NULL DEFAULT NULL,
  `ans_writer` INT NULL DEFAULT NULL,
  `ans_regtime` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`qna_seq`),
  INDEX `ques_writer_idx` (`ques_writer` ASC) VISIBLE,
  INDEX `ans_writer_idx` (`ans_writer` ASC) VISIBLE,
  CONSTRAINT `ans_writer`
    FOREIGN KEY (`ans_writer`)
    REFERENCES `sduty`.`admin` (`admin_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `ques_writer`
    FOREIGN KEY (`ques_writer`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sduty`.`reply`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`reply` ;

CREATE TABLE IF NOT EXISTS `sduty`.`reply` (
  `reply_seq` INT NOT NULL AUTO_INCREMENT,
  `reply_user_seq` INT NOT NULL,
  `reply_content` VARCHAR(140) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `reply_story_seq` INT NOT NULL,
  `reply_regtime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`reply_seq`),
  INDEX `reply_user_seq_idx` (`reply_user_seq` ASC) VISIBLE,
  INDEX `reply_story_seq_idx` (`reply_story_seq` ASC) VISIBLE,
  CONSTRAINT `reply_story_seq`
    FOREIGN KEY (`reply_story_seq`)
    REFERENCES `sduty`.`story` (`story_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `reply_user_seq`
    FOREIGN KEY (`reply_user_seq`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 66
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`report` ;

CREATE TABLE IF NOT EXISTS `sduty`.`report` (
  `report_seq` INT NOT NULL AUTO_INCREMENT,
  `report_owner_seq` INT NOT NULL,
  `report_date` DATE NOT NULL,
  PRIMARY KEY (`report_seq`),
  INDEX `report_owner_seq_idx` (`report_owner_seq` ASC) VISIBLE,
  CONSTRAINT `report_owner_seq`
    FOREIGN KEY (`report_owner_seq`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 34
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`scrap`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`scrap` ;

CREATE TABLE IF NOT EXISTS `sduty`.`scrap` (
  `scrap_user_seq` INT NOT NULL,
  `scrap_story_seq` INT NOT NULL,
  PRIMARY KEY (`scrap_user_seq`, `scrap_story_seq`),
  INDEX `scrap_story_seq_idx` (`scrap_story_seq` ASC) VISIBLE,
  CONSTRAINT `scrap_story_seq`
    FOREIGN KEY (`scrap_story_seq`)
    REFERENCES `sduty`.`story` (`story_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `scrap_user_seq`
    FOREIGN KEY (`scrap_user_seq`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`story_interest_hashtag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`story_interest_hashtag` ;

CREATE TABLE IF NOT EXISTS `sduty`.`story_interest_hashtag` (
  `story_seq` INT NOT NULL,
  `story_interest_hashtag` INT NOT NULL,
  PRIMARY KEY (`story_seq`, `story_interest_hashtag`),
  INDEX `story_interest_seq_idx` (`story_interest_hashtag` ASC) VISIBLE,
  CONSTRAINT `story_interest_seq`
    FOREIGN KEY (`story_interest_hashtag`)
    REFERENCES `sduty`.`interest_hashtag` (`interest_hashtag_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `story_seq`
    FOREIGN KEY (`story_seq`)
    REFERENCES `sduty`.`story` (`story_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`tag` ;

CREATE TABLE IF NOT EXISTS `sduty`.`tag` (
  `tag_story_seq` INT NOT NULL,
  `tag_hashtag_seq` INT NOT NULL,
  PRIMARY KEY (`tag_story_seq`, `tag_hashtag_seq`),
  INDEX `tag_hashtag_seq_idx` (`tag_hashtag_seq` ASC) VISIBLE,
  CONSTRAINT `tag_hashtag_seq`
    FOREIGN KEY (`tag_hashtag_seq`)
    REFERENCES `sduty`.`hashtag` (`hashtag_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `tag_story_seq`
    FOREIGN KEY (`tag_story_seq`)
    REFERENCES `sduty`.`story` (`story_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_520_ci;


-- -----------------------------------------------------
-- Table `sduty`.`task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`task` ;

CREATE TABLE IF NOT EXISTS `sduty`.`task` (
  `task_seq` INT NOT NULL AUTO_INCREMENT,
  `task_report_seq` INT NOT NULL,
  `task_title` VARCHAR(45) NOT NULL,
  `task_content1` VARCHAR(100) NULL DEFAULT NULL,
  `task_start_time` TIME NOT NULL,
  `task_end_time` TIME NOT NULL,
  `task_duration_time` INT NOT NULL,
  `task_content2` VARCHAR(100) NULL DEFAULT NULL,
  `task_content3` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`task_seq`),
  INDEX `task_report_seq_idx` (`task_report_seq` ASC) VISIBLE,
  CONSTRAINT `task_report_seq`
    FOREIGN KEY (`task_report_seq`)
    REFERENCES `sduty`.`report` (`report_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 82
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sduty`.`user_interest_hashtag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`user_interest_hashtag` ;

CREATE TABLE IF NOT EXISTS `sduty`.`user_interest_hashtag` (
  `user_seq` INT NOT NULL,
  `user_interest_hashtag` INT NOT NULL,
  PRIMARY KEY (`user_seq`, `user_interest_hashtag`),
  INDEX `user_interest_seq_idx` (`user_interest_hashtag` ASC) VISIBLE,
  CONSTRAINT `fk_user_seq`
    FOREIGN KEY (`user_seq`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `kf_user_interest_seq`
    FOREIGN KEY (`user_interest_hashtag`)
    REFERENCES `sduty`.`interest_hashtag` (`interest_hashtag_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `sduty`.`userachieve`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sduty`.`userachieve` ;

CREATE TABLE IF NOT EXISTS `sduty`.`userachieve` (
  `userachieve_user_seq` INT NOT NULL,
  `userachieve_achievement_seq` INT NOT NULL,
  PRIMARY KEY (`userachieve_user_seq`, `userachieve_achievement_seq`),
  INDEX `userachieve_achievement_seq_idx` (`userachieve_achievement_seq` ASC) VISIBLE,
  CONSTRAINT `userachieve_achievement_seq`
    FOREIGN KEY (`userachieve_achievement_seq`)
    REFERENCES `sduty`.`achievement` (`achievement_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `userachieve_user_seq`
    FOREIGN KEY (`userachieve_user_seq`)
    REFERENCES `sduty`.`user` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `sduty`;

DELIMITER $$

USE `sduty`$$
DROP TRIGGER IF EXISTS `sduty`.`study_member_cntdown` $$
USE `sduty`$$
CREATE
DEFINER=`sduty108`@`%`
TRIGGER `sduty`.`study_member_cntdown`
AFTER DELETE ON `sduty`.`participation`
FOR EACH ROW
BEGIN
    UPDATE study SET study.study_join_number=study.study_join_number-1
    WHERE study.study_seq = old.participation_study_seq;
END$$


USE `sduty`$$
DROP TRIGGER IF EXISTS `sduty`.`study_member_cntup` $$
USE `sduty`$$
CREATE
DEFINER=`sduty108`@`%`
TRIGGER `sduty`.`study_member_cntup`
AFTER INSERT ON `sduty`.`participation`
FOR EACH ROW
BEGIN
    UPDATE study SET study.study_join_number=study.study_join_number+1
    WHERE study.study_seq = new.participation_study_seq;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
