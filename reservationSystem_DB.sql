-- MySQL dump 10.13  Distrib 5.1.48, for apple-darwin10.3.0 (i386)
--
-- Host: localhost    Database: res_system
-- ------------------------------------------------------
-- Server version	5.1.48

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `card_id` varchar(255) NOT NULL,
  `flag_for_deletion` bit(1) NOT NULL,
  `owner_id` bigint(20) NOT NULL,
  `reservation_list_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB9D38A2D24558260` (`reservation_list_id`),
  KEY `FKB9D38A2DF04B9DEF` (`owner_id`),
  CONSTRAINT `FKB9D38A2DF04B9DEF` FOREIGN KEY (`owner_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKB9D38A2D24558260` FOREIGN KEY (`reservation_list_id`) REFERENCES `reservation_list` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,0,'1 1111 11111 1111','\0',1,NULL),(2,0,'1 4444 55555 4444','\0',2,NULL),(3,0,'1 5555 44444 5555','\0',3,NULL),(4,0,'1 4321 54321 4321','\0',4,NULL),(5,0,'1 1234 12345 1234','\0',5,NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_fine`
--

DROP TABLE IF EXISTS `account_fine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_fine` (
  `account_fines_id` bigint(20) DEFAULT NULL,
  `fine_id` bigint(20) DEFAULT NULL,
  KEY `FK4107788C2CFBF0D` (`account_fines_id`),
  KEY `FK4107788CC4C0C4AD` (`fine_id`),
  CONSTRAINT `FK4107788CC4C0C4AD` FOREIGN KEY (`fine_id`) REFERENCES `fine` (`id`),
  CONSTRAINT `FK4107788C2CFBF0D` FOREIGN KEY (`account_fines_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_fine`
--

LOCK TABLES `account_fine` WRITE;
/*!40000 ALTER TABLE `account_fine` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_fine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fine`
--

DROP TABLE IF EXISTS `fine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `amount_owed` double NOT NULL,
  `description` varchar(255) NOT NULL,
  `offense` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fine`
--

LOCK TABLES `fine` WRITE;
/*!40000 ALTER TABLE `fine` DISABLE KEYS */;
/*!40000 ALTER TABLE `fine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `artist` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `format` varchar(255) NOT NULL,
  `is_available` bit(1) NOT NULL,
  `title` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `class` varchar(255) NOT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `pulisher` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `producer` varchar(255) DEFAULT NULL,
  `run_time` bigint(20) DEFAULT NULL,
  `length` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
INSERT INTO `media` VALUES (1,0,'Laura Hillenbrand',NULL,'EPUB','','Unbroken: A World War II Story of Survival, Resilience, and Redemption','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(2,0,'Laura Hillenbrand',NULL,'PAPER','','Unbroken: A World War II Story of Survival, Resilience, and Redemption','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(3,0,'Todd Burpo, Sonja Burpo, Colton Burpo, Lynn Vincent',NULL,'EPUB','','Heaven is for Real: A Little Boy\'s Astounding Story of His Trip to Heaven and Back','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(4,0,'Todd Burpo, Sonja Burpo, Colton Burpo, Lynn Vincent',NULL,'PAPER','','Heaven is for Real: A Little Boy\'s Astounding Story of His Trip to Heaven and Back','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(5,0,'Timothy Ferriss',NULL,'EPUB','','The 4-Hour Body: An Uncommon Guide to Rapid Fat-Loss, Incredible Sex, and Becoming Superhuman','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(6,0,'Timothy Ferriss',NULL,'PAPER','','The 4-Hour Body: An Uncommon Guide to Rapid Fat-Loss, Incredible Sex, and Becoming Superhuman','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(7,0,'Deborah E. Harkness',NULL,'EPUB','','A Discovery of Witches: A Novel','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(8,0,'Deborah E. Harkness',NULL,'PAPER','','A Discovery of Witches: A Novel','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(9,0,'The Editors at America\'s Test Kitchen, America\'s Test Kitchen',NULL,'EPUB','','Slow Cooker Revolution','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(10,0,'The Editors at America\'s Test Kitchen, America\'s Test Kitchen',NULL,'PAPER','','Slow Cooker Revolution','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(11,0,'Abraham Verghese',NULL,'EPUB','','Cutting for Stone','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(12,0,'Abraham Verghese',NULL,'PAPER','','Cutting for Stone','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(13,0,'Stieg Larsson, Reg Keeland',NULL,'EPUB','','The Girl Who Kicked the Hornet\'s Nest','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(14,0,'Stieg Larsson, Reg Keeland',NULL,'PAPER','','The Girl Who Kicked the Hornet\'s Nest','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(15,0,'Iyanla Vanzant',NULL,'EPUB','','Peace from Broken Pieces: How to Get Through What You\'re Going Through','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(16,0,'Iyanla Vanzant',NULL,'PAPER','','Peace from Broken Pieces: How to Get Through What You\'re Going Through','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(17,0,'Patrick Rothfuss',NULL,'EPUB','','The Wise Man\'s Fear (Kingkiller Chronicles, Day 2)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(18,0,'Patrick Rothfuss',NULL,'PAPER','','The Wise Man\'s Fear (Kingkiller Chronicles, Day 2)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(19,0,'The Fitness Elite, Dax Moy',NULL,'EPUB','','Total Body Breakthroughs: The World\'s Leading Experts Reveal Proven Health, Fitness & Nutrition Secrets To Help You Achieve The Body You\'ve Always Wanted But Couldn\'t Until Now!','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(20,0,'The Fitness Elite, Dax Moy',NULL,'PAPER','','Total Body Breakthroughs: The World\'s Leading Experts Reveal Proven Health, Fitness & Nutrition Secrets To Help You Achieve The Body You\'ve Always Wanted But Couldn\'t Until Now!','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(21,0,'James Patterson',NULL,'EPUB','','Angel: A Maximum Ride Novel (Maximum Ride: The Protectors)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(22,0,'James Patterson',NULL,'PAPER','','Angel: A Maximum Ride Novel (Maximum Ride: The Protectors)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(23,0,'Gary Chapman',NULL,'EPUB','','The 5 Love Languages: The Secret to Love That Lasts','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(24,0,'Gary Chapman',NULL,'PAPER','','The 5 Love Languages: The Secret to Love That Lasts','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(25,0,'Tom Rath',NULL,'EPUB','','StrengthsFinder 2.0','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(26,0,'Tom Rath',NULL,'PAPER','','StrengthsFinder 2.0','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(27,0,'Charlaine Harris',NULL,'EPUB','','Dead Reckoning (Sookie Stackhouse, Book 11)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(28,0,'Charlaine Harris',NULL,'PAPER','','Dead Reckoning (Sookie Stackhouse, Book 11)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(29,0,'Donald Rumsfeld',NULL,'EPUB','','Known and Unknown: A Memoir','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(30,0,'Donald Rumsfeld',NULL,'PAPER','','Known and Unknown: A Memoir','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(31,0,'Sara Gruen',NULL,'EPUB','','Water for Elephants: A Novel','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(32,0,'Sara Gruen',NULL,'PAPER','','Water for Elephants: A Novel','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(33,0,'Dambisa Moyo',NULL,'EPUB','','How the West Was Lost: Fifty Years of Economic Folly--and the Stark Choices Ahead','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(34,0,'Dambisa Moyo',NULL,'PAPER','','How the West Was Lost: Fifty Years of Economic Folly--and the Stark Choices Ahead','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(35,0,'Stieg Larsson, Reg Keeland',NULL,'EPUB','','The Girl with the Dragon Tattoo','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(36,0,'Stieg Larsson, Reg Keeland',NULL,'PAPER','','The Girl with the Dragon Tattoo','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(37,0,'Kathryn Stockett',NULL,'EPUB','','The Help','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(38,0,'Kathryn Stockett',NULL,'PAPER','','The Help','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(39,0,'Amy Chua',NULL,'EPUB','','Battle Hymn of the Tiger Mother','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(40,0,'Amy Chua',NULL,'PAPER','','Battle Hymn of the Tiger Mother','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(41,0,'American Psychological Association',NULL,'EPUB','','Publication Manual of the American Psychological Association, Sixth Edition','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(42,0,'American Psychological Association',NULL,'PAPER','','Publication Manual of the American Psychological Association, Sixth Edition','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(43,0,'Michael Waltrip, Ellis Henican',NULL,'EPUB','','In the Blink of an Eye: Dale, Daytona, and the Day that Changed Everything','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(44,0,'Michael Waltrip, Ellis Henican',NULL,'PAPER','','In the Blink of an Eye: Dale, Daytona, and the Day that Changed Everything','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(45,0,'The Invisible Committee',NULL,'EPUB','','The Coming Insurrection (Semiotext(e) / Intervention)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(46,0,'The Invisible Committee',NULL,'PAPER','','The Coming Insurrection (Semiotext(e) / Intervention)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(47,0,'Suzanne Collins',NULL,'EPUB','','Mockingjay (The Final Book of The Hunger Games)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(48,0,'Suzanne Collins',NULL,'PAPER','','Mockingjay (The Final Book of The Hunger Games)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(49,0,'Ann Voskamp',NULL,'EPUB','','One Thousand Gifts: A Dare to Live Fully Right Where You Are','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(50,0,'Ann Voskamp',NULL,'PAPER','','One Thousand Gifts: A Dare to Live Fully Right Where You Are','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(51,0,'Kathy Freston',NULL,'EPUB','','Veganist: Lose Weight, Get Healthy, Change the World','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(52,0,'Kathy Freston',NULL,'PAPER','','Veganist: Lose Weight, Get Healthy, Change the World','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(53,0,'Emma Donoghue',NULL,'EPUB','','Room: A Novel','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(54,0,'Emma Donoghue',NULL,'PAPER','','Room: A Novel','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(55,0,'Stieg Larsson, Reg Keeland',NULL,'EPUB','','The Girl Who Played with Fire (Vintage)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(56,0,'Stieg Larsson, Reg Keeland',NULL,'PAPER','','The Girl Who Played with Fire (Vintage)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(57,0,'Michael Lewis',NULL,'EPUB','','The Big Short: Inside the Doomsday Machine','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(58,0,'Michael Lewis',NULL,'PAPER','','The Big Short: Inside the Doomsday Machine','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(59,0,'Suzanne Collins',NULL,'EPUB','','Catching Fire (The Second Book of the Hunger Games)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(60,0,'Suzanne Collins',NULL,'PAPER','','Catching Fire (The Second Book of the Hunger Games)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(61,0,'Victoria Kann',NULL,'EPUB','','Silverlicious (Pinkalicious)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(62,0,'Victoria Kann',NULL,'PAPER','','Silverlicious (Pinkalicious)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(63,0,'Stacy Schiff',NULL,'EPUB','','Cleopatra: A Life','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(64,0,'Stacy Schiff',NULL,'PAPER','','Cleopatra: A Life','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(65,0,'Timothy Ferriss',NULL,'EPUB','','The 4-Hour Workweek, Expanded and Updated: Expanded and Updated, With Over 100 New Pages of Cutting-Edge Content.','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(66,0,'Timothy Ferriss',NULL,'PAPER','','The 4-Hour Workweek, Expanded and Updated: Expanded and Updated, With Over 100 New Pages of Cutting-Edge Content.','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(67,0,'James Patterson, Michael Ledwidge',NULL,'EPUB','','Tick Tock (Michael Bennett)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(68,0,'James Patterson, Michael Ledwidge',NULL,'PAPER','','Tick Tock (Michael Bennett)','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(69,0,'Jamie Ford',NULL,'EPUB','','Hotel on the Corner of Bitter and Sweet','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(70,0,'Jamie Ford',NULL,'PAPER','','Hotel on the Corner of Bitter and Sweet','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(71,0,'Francis Chan',NULL,'EPUB','','Crazy Love: Overwhelmed by a Relentless God','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(72,0,'Francis Chan',NULL,'PAPER','','Crazy Love: Overwhelmed by a Relentless God','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(73,0,'Janet Jackson, David Ritz',NULL,'EPUB','','True You: A Journey to Finding and Loving Yourself','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(74,0,'Janet Jackson, David Ritz',NULL,'PAPER','','True You: A Journey to Finding and Loving Yourself','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(75,0,'Joel Richardson',NULL,'EPUB','','The Islamic Antichrist: The Shocking Truth about the Real Nature of the Beast','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(76,0,'Joel Richardson',NULL,'PAPER','','The Islamic Antichrist: The Shocking Truth about the Real Nature of the Beast','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(77,0,'Daniel C. Goldie, Gordon S. Murray',NULL,'EPUB','','The Investment Answer','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(78,0,'Daniel C. Goldie, Gordon S. Murray',NULL,'PAPER','','The Investment Answer','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(79,0,'Karen Russell',NULL,'EPUB','','Swamplandia!','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(80,0,'Karen Russell',NULL,'PAPER','','Swamplandia!','PRINT','reservationsystem.Print',NULL,NULL,NULL,NULL,NULL,NULL),(81,0,'Leonardo DiCaprio (Actor), Ken Watanabe (Actor),',NULL,'AVI','','Inception','VIDEO','reservationsystem.Video',NULL,NULL,'Christopher Nolan (Director)',NULL,NULL,NULL),(82,0,'Leonardo DiCaprio (Actor), Ken Watanabe (Actor),',NULL,'DVD','','Inception','VIDEO','reservationsystem.Video',NULL,NULL,'Christopher Nolan (Director)',NULL,NULL,NULL),(83,0,'Tom Hanks (Actor), Tim Allen (Actor),',NULL,'AVI','','Toy Story 3','VIDEO','reservationsystem.Video',NULL,NULL,'Lee Unkrich (Director)',NULL,NULL,NULL),(84,0,'Tom Hanks (Actor), Tim Allen (Actor),',NULL,'DVD','','Toy Story 3','VIDEO','reservationsystem.Video',NULL,NULL,'Lee Unkrich (Director)',NULL,NULL,NULL),(85,0,'Daniel Radcliffe (Actor), Rupert Grint (Actor),',NULL,'AVI','','Harry Potter and the Deathly Hallows, Part 1','VIDEO','reservationsystem.Video',NULL,NULL,'David Yates (Director)',NULL,NULL,NULL),(86,0,'Daniel Radcliffe (Actor), Rupert Grint (Actor),',NULL,'DVD','','Harry Potter and the Deathly Hallows, Part 1','VIDEO','reservationsystem.Video',NULL,NULL,'David Yates (Director)',NULL,NULL,NULL),(87,0,'Robert Jr. Downey (Actor), Don Cheadle (Actor)',NULL,'AVI','','Iron Man 2','VIDEO','reservationsystem.Video',NULL,NULL,NULL,NULL,NULL,NULL),(88,0,'Robert Jr. Downey (Actor), Don Cheadle (Actor)',NULL,'DVD','','Iron Man 2','VIDEO','reservationsystem.Video',NULL,NULL,NULL,NULL,NULL,NULL),(89,0,'Diane Lane (Actor), John Malkovich (Actor)',NULL,'AVI','','Secretariat','VIDEO','reservationsystem.Video',NULL,NULL,NULL,NULL,NULL,NULL),(90,0,'Diane Lane (Actor), John Malkovich (Actor)',NULL,'DVD','','Secretariat','VIDEO','reservationsystem.Video',NULL,NULL,NULL,NULL,NULL,NULL),(91,0,'Chris Pine (Actor), Denzel Washington (Actor),',NULL,'AVI','','Unstoppable','VIDEO','reservationsystem.Video',NULL,NULL,'Tony Scott (Director)',NULL,NULL,NULL),(92,0,'Chris Pine (Actor), Denzel Washington (Actor),',NULL,'DVD','','Unstoppable','VIDEO','reservationsystem.Video',NULL,NULL,'Tony Scott (Director)',NULL,NULL,NULL),(93,0,'Mandy Moore (Actor), Zachary Levi (Actor)',NULL,'AVI','','Tangled','VIDEO','reservationsystem.Video',NULL,NULL,NULL,NULL,NULL,NULL),(94,0,'Mandy Moore (Actor), Zachary Levi (Actor)',NULL,'DVD','','Tangled','VIDEO','reservationsystem.Video',NULL,NULL,NULL,NULL,NULL,NULL),(95,0,'Jillian Michaels (Actor),',NULL,'AVI','','Jillian Michaels - 30 Day Shred','VIDEO','reservationsystem.Video',NULL,NULL,'Andrea Ambandos (Director)',NULL,NULL,NULL),(96,0,'Jillian Michaels (Actor),',NULL,'DVD','','Jillian Michaels - 30 Day Shred','VIDEO','reservationsystem.Video',NULL,NULL,'Andrea Ambandos (Director)',NULL,NULL,NULL),(97,0,'Bruce Willis (Actor), Morgan Freeman (Actor),',NULL,'AVI','','Red','VIDEO','reservationsystem.Video',NULL,NULL,'Robert Schwentke (Director)',NULL,NULL,NULL),(98,0,'Bruce Willis (Actor), Morgan Freeman (Actor),',NULL,'DVD','','Red','VIDEO','reservationsystem.Video',NULL,NULL,'Robert Schwentke (Director)',NULL,NULL,NULL),(99,0,NULL,NULL,'AVI','','Masterpiece Classic: Downton Abbey','VIDEO','reservationsystem.Video',NULL,NULL,NULL,NULL,NULL,NULL),(100,0,NULL,NULL,'DVD','','Masterpiece Classic: Downton Abbey','VIDEO','reservationsystem.Video',NULL,NULL,NULL,NULL,NULL,NULL),(101,0,'Jay Baruchel (Actor), Gerard Butler (Actor),',NULL,'AVI','','How to Train Your Dragon','VIDEO','reservationsystem.Video',NULL,NULL,'Dean DeBlois and Chris Sanders (Director)',NULL,NULL,NULL),(102,0,'Jay Baruchel (Actor), Gerard Butler (Actor),',NULL,'DVD','','How to Train Your Dragon','VIDEO','reservationsystem.Video',NULL,NULL,'Dean DeBlois and Chris Sanders (Director)',NULL,NULL,NULL),(103,0,'Colin Firth (Actor), Helena Bonham Carter (Actor),',NULL,'AVI','','The King\'s Speech','VIDEO','reservationsystem.Video',NULL,NULL,'Tom Hooper (Director)',NULL,NULL,NULL),(104,0,'Colin Firth (Actor), Helena Bonham Carter (Actor),',NULL,'DVD','','The King\'s Speech','VIDEO','reservationsystem.Video',NULL,NULL,'Tom Hooper (Director)',NULL,NULL,NULL),(105,0,'Geoffrey Canada (Actor), Michelle Rhee (Actor),',NULL,'AVI','','Waiting for \"Superman\"','VIDEO','reservationsystem.Video',NULL,NULL,'Davis Guggenheim (Director)',NULL,NULL,NULL),(106,0,'Geoffrey Canada (Actor), Michelle Rhee (Actor),',NULL,'DVD','','Waiting for \"Superman\"','VIDEO','reservationsystem.Video',NULL,NULL,'Davis Guggenheim (Director)',NULL,NULL,NULL),(107,0,'Ben Affleck (Actor), Rebecca Hall (Actor),',NULL,'AVI','','The Town','VIDEO','reservationsystem.Video',NULL,NULL,'Ben Affleck (Director)',NULL,NULL,NULL),(108,0,'Ben Affleck (Actor), Rebecca Hall (Actor),',NULL,'DVD','','The Town','VIDEO','reservationsystem.Video',NULL,NULL,'Ben Affleck (Director)',NULL,NULL,NULL),(109,0,'Jesse Eisenberg (Actor), Andrew Garfield (Actor),',NULL,'AVI','','The Social Network','VIDEO','reservationsystem.Video',NULL,NULL,'David Fincher (Director)',NULL,NULL,NULL),(110,0,'Jesse Eisenberg (Actor), Andrew Garfield (Actor),',NULL,'DVD','','The Social Network','VIDEO','reservationsystem.Video',NULL,NULL,'David Fincher (Director)',NULL,NULL,NULL),(111,0,NULL,NULL,'AVI','','Beauty and the Beast','VIDEO','reservationsystem.Video',NULL,NULL,NULL,NULL,NULL,NULL),(112,0,NULL,NULL,'DVD','','Beauty and the Beast','VIDEO','reservationsystem.Video',NULL,NULL,NULL,NULL,NULL,NULL),(113,0,'Mumford & Sons',NULL,'MP3','','Sigh No More','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(114,0,'Mumford & Sons',NULL,'CD','','Sigh No More','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(115,0,'Adele',NULL,'MP3','','21','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(116,0,'Adele',NULL,'CD','','21','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(117,0,'Lady Antebellum',NULL,'MP3','','Need You Now','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(118,0,'Lady Antebellum',NULL,'CD','','Need You Now','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(119,0,'Various Artists (Artist)',NULL,'MP3','','2011 GRAMMY Nominees','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(120,0,'Various Artists (Artist)',NULL,'CD','','2011 GRAMMY Nominees','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(121,0,'Arcade Fire',NULL,'MP3','','The Suburbs','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(122,0,'Arcade Fire',NULL,'CD','','The Suburbs','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(123,0,'Esperanza Spalding',NULL,'MP3','','Chamber Music Society','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(124,0,'Esperanza Spalding',NULL,'CD','','Chamber Music Society','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(125,0,'The Decemberists',NULL,'MP3','','The King Is Dead','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(126,0,'The Decemberists',NULL,'CD','','The King Is Dead','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(127,0,'PJ Harvey',NULL,'MP3','','Let England Shake','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(128,0,'PJ Harvey',NULL,'CD','','Let England Shake','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(129,0,'The Black Keys',NULL,'MP3','','Brothers','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(130,0,'The Black Keys',NULL,'CD','','Brothers','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(131,0,'Bruno Mars',NULL,'MP3','','Doo - Wops & Hooligans','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(132,0,'Bruno Mars',NULL,'CD','','Doo - Wops & Hooligans','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(133,0,'Gregg Allman',NULL,'MP3','','Low Country Blues','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(134,0,'Gregg Allman',NULL,'CD','','Low Country Blues','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(135,0,'Amos Lee',NULL,'MP3','','Mission Bell','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(136,0,'Amos Lee',NULL,'CD','','Mission Bell','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(137,0,'Esperanza Spalding',NULL,'MP3','','Esperanza','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(138,0,'Esperanza Spalding',NULL,'CD','','Esperanza','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(139,0,'Florence + The Machine',NULL,'MP3','','Lungs','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(140,0,'Florence + The Machine',NULL,'CD','','Lungs','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(141,0,'Lucinda Williams',NULL,'MP3','','Blessed','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(142,0,'Lucinda Williams',NULL,'CD','','Blessed','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(143,0,'Eminem',NULL,'MP3','','Recovery','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(144,0,'Eminem',NULL,'CD','','Recovery','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(145,0,'Cee-Lo Green',NULL,'MP3','','The Lady Killer','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(146,0,'Cee-Lo Green',NULL,'CD','','The Lady Killer','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(147,0,'The Avett Brothers',NULL,'MP3','','I and Love and You','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(148,0,'The Avett Brothers',NULL,'CD','','I and Love and You','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(149,0,'Drive-By Truckers',NULL,'MP3','','Go-Go Boots','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(150,0,'Drive-By Truckers',NULL,'CD','','Go-Go Boots','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(151,0,'Muse',NULL,'MP3','','The Resistance','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(152,0,'Muse',NULL,'CD','','The Resistance','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(153,0,'Bright Eyes',NULL,'MP3','','The People\'s Key','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(154,0,'Bright Eyes',NULL,'CD','','The People\'s Key','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(155,0,'Adele',NULL,'MP3','','19','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(156,0,'Adele',NULL,'CD','','19','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(157,0,'Celtic Thunder',NULL,'MP3','','Heritage','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(158,0,'Celtic Thunder',NULL,'CD','','Heritage','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(159,0,'Katy Perry',NULL,'MP3','','Teenage Dream','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(160,0,'Katy Perry',NULL,'CD','','Teenage Dream','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(161,0,'Celtic Woman',NULL,'MP3','','Lullaby','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(162,0,'Celtic Woman',NULL,'CD','','Lullaby','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(163,0,'Ray LaMontagne',NULL,'MP3','','God Willin\' & The Creek Don\'t Rise','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(164,0,'Ray LaMontagne',NULL,'CD','','God Willin\' & The Creek Don\'t Rise','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(165,0,'Pink',NULL,'MP3','','Greatest Hits... So Far!!!','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(166,0,'Pink',NULL,'CD','','Greatest Hits... So Far!!!','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(167,0,'Iron & Wine',NULL,'MP3','','Kiss Each Other Clean','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(168,0,'Iron & Wine',NULL,'CD','','Kiss Each Other Clean','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(169,0,'Marsha Ambrosius',NULL,'MP3','','Late Nights, Early Mornings','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(170,0,'Marsha Ambrosius',NULL,'CD','','Late Nights, Early Mornings','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(171,0,'Taylor Swift',NULL,'MP3','','Speak Now','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(172,0,'Taylor Swift',NULL,'CD','','Speak Now','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(173,0,'Zac Brown Band',NULL,'MP3','','You Get What You Give','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(174,0,'Zac Brown Band',NULL,'CD','','You Get What You Give','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(175,0,'Hayes Carll',NULL,'MP3','','KMAG YOYO (& other American stories)','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(176,0,'Hayes Carll',NULL,'CD','','KMAG YOYO (& other American stories)','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(177,0,'R.E.M.',NULL,'MP3','','Collapse Into Now','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(178,0,'R.E.M.',NULL,'CD','','Collapse Into Now','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(179,0,'Glee Cast',NULL,'MP3','','Glee: The Music, Volume 4','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(180,0,'Glee Cast',NULL,'CD','','Glee: The Music, Volume 4','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(181,0,'Train',NULL,'MP3','','Save Me, San Francisco (Golden Gate Edition)','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(182,0,'Train',NULL,'CD','','Save Me, San Francisco (Golden Gate Edition)','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(183,0,'Josh Groban',NULL,'MP3','','Illuminations','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(184,0,'Josh Groban',NULL,'CD','','Illuminations','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(185,0,'Justin Bieber',NULL,'MP3','','My World 2.0','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(186,0,'Justin Bieber',NULL,'CD','','My World 2.0','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(187,0,'Stryper',NULL,'MP3','','The Covering','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(188,0,'Stryper',NULL,'CD','','The Covering','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(189,0,'Mumford & Sons',NULL,'MP3','','Sigh No More','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL),(190,0,'Mumford & Sons',NULL,'CD','','Sigh No More','AUDIO','reservationsystem.Audio',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `user_login_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC4E39B55C66654B0` (`user_login_id`),
  CONSTRAINT `FKC4E39B55C66654B0` FOREIGN KEY (`user_login_id`) REFERENCES `user_login` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,0,'foo@bar.com','admin','admin','1',1),(2,0,'foo@bar.com','David','Bauer','1',1),(3,0,'foo@bar.com','Erik','Knutson','1',1),(4,0,'foo@bar.com','Laura','Sweet','1',4),(5,0,'foo@bar.com','Steve','Vlaminck','1',5);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `media_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA2D543CCC6707867` (`media_id`),
  CONSTRAINT `FKA2D543CCC6707867` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_list`
--

DROP TABLE IF EXISTS `reservation_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_list`
--

LOCK TABLES `reservation_list` WRITE;
/*!40000 ALTER TABLE `reservation_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_list_reservation`
--

DROP TABLE IF EXISTS `reservation_list_reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation_list_reservation` (
  `reservation_list_reservations_id` bigint(20) DEFAULT NULL,
  `reservation_id` bigint(20) DEFAULT NULL,
  KEY `FK8F8D05BE8F08B9A7` (`reservation_id`),
  KEY `FK8F8D05BED1B03E9C` (`reservation_list_reservations_id`),
  CONSTRAINT `FK8F8D05BED1B03E9C` FOREIGN KEY (`reservation_list_reservations_id`) REFERENCES `reservation_list` (`id`),
  CONSTRAINT `FK8F8D05BE8F08B9A7` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_list_reservation`
--

LOCK TABLES `reservation_list_reservation` WRITE;
/*!40000 ALTER TABLE `reservation_list_reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation_list_reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sec_role`
--

DROP TABLE IF EXISTS `sec_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sec_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `authority` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `authority` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sec_role`
--

LOCK TABLES `sec_role` WRITE;
/*!40000 ALTER TABLE `sec_role` DISABLE KEYS */;
INSERT INTO `sec_role` VALUES (1,0,'ROLE_USER'),(2,0,'ROLE_ADMIN');
/*!40000 ALTER TABLE `sec_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sec_user_sec_role`
--

DROP TABLE IF EXISTS `sec_user_sec_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sec_user_sec_role` (
  `sec_role_id` bigint(20) NOT NULL,
  `sec_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`sec_role_id`,`sec_user_id`),
  KEY `FK6630E2A68C98D2C` (`sec_user_id`),
  KEY `FK6630E2A2EB461EA` (`sec_role_id`),
  CONSTRAINT `FK6630E2A2EB461EA` FOREIGN KEY (`sec_role_id`) REFERENCES `sec_role` (`id`),
  CONSTRAINT `FK6630E2A68C98D2C` FOREIGN KEY (`sec_user_id`) REFERENCES `user_login` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sec_user_sec_role`
--

LOCK TABLES `sec_user_sec_role` WRITE;
/*!40000 ALTER TABLE `sec_user_sec_role` DISABLE KEYS */;
INSERT INTO `sec_user_sec_role` VALUES (2,1),(1,2),(1,3),(1,4),(1,5);
/*!40000 ALTER TABLE `sec_user_sec_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_login`
--

DROP TABLE IF EXISTS `user_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `password_expired` bit(1) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_login`
--

LOCK TABLES `user_login` WRITE;
/*!40000 ALTER TABLE `user_login` DISABLE KEYS */;
INSERT INTO `user_login` VALUES (1,0,'\0','\0','','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','\0','admin'),(2,0,'\0','\0','','d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1','\0','david'),(3,0,'\0','\0','','d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1','\0','erik'),(4,0,'\0','\0','','d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1','\0','laura'),(5,0,'\0','\0','','d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1','\0','steve');
/*!40000 ALTER TABLE `user_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wait_list`
--

DROP TABLE IF EXISTS `wait_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wait_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  `media_id` bigint(20) NOT NULL,
  `position` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC5EF7928C6707867` (`media_id`),
  KEY `FKC5EF7928CA77D087` (`account_id`),
  CONSTRAINT `FKC5EF7928CA77D087` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKC5EF7928C6707867` FOREIGN KEY (`media_id`) REFERENCES `media` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wait_list`
--

LOCK TABLES `wait_list` WRITE;
/*!40000 ALTER TABLE `wait_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `wait_list` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-03-25 20:53:18
