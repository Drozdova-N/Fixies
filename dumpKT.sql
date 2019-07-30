-- MySQL dump 10.16  Distrib 10.1.26-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: databaseKT
-- ------------------------------------------------------
-- Server version	10.1.26-MariaDB-0+deb9u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cabinet`
--

DROP TABLE IF EXISTS `cabinet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cabinet` (
  `idcabinet` int(11) NOT NULL AUTO_INCREMENT,
  `num_cabinet` varchar(45) NOT NULL,
  PRIMARY KEY (`idcabinet`),
  UNIQUE KEY `idcabinet_UNIQUE` (`idcabinet`),
  UNIQUE KEY `num_cabinet_UNIQUE` (`num_cabinet`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cabinet`
--

LOCK TABLES `cabinet` WRITE;
/*!40000 ALTER TABLE `cabinet` DISABLE KEYS */;
INSERT INTO `cabinet` VALUES (1,'1'),(2,'2'),(3,'4'),(4,'5'),(5,'6'),(6,'88');
/*!40000 ALTER TABLE `cabinet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment` (
  `idequipment` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `inv_number` varchar(45) NOT NULL,
  `status_idstatus` int(11) NOT NULL,
  `type_of_equipment_idtype` int(11) NOT NULL,
  `cabinet_idcabinet` int(11) NOT NULL,
  `description` longtext,
  PRIMARY KEY (`idequipment`),
  UNIQUE KEY `inventory number_UNIQUE` (`inv_number`),
  UNIQUE KEY `idequipment_UNIQUE` (`idequipment`),
  KEY `fk_equipment_status_idx` (`status_idstatus`),
  KEY `fk_equipment_type_of_equipment1_idx` (`type_of_equipment_idtype`),
  KEY `fk_equipment_cabinet1_idx` (`cabinet_idcabinet`),
  CONSTRAINT `fk_equipment_cabinet1` FOREIGN KEY (`cabinet_idcabinet`) REFERENCES `cabinet` (`idcabinet`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_equipment_status` FOREIGN KEY (`status_idstatus`) REFERENCES `status` (`idstatus`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_equipment_type_of_equipment1` FOREIGN KEY (`type_of_equipment_idtype`) REFERENCES `type_of_equipment` (`idtype`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (8,'name1','111',1,1,1,''),(9,'NAME2','222',2,3,2,''),(10,'name','333',2,2,3,'описание');
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `idstatus` int(11) NOT NULL AUTO_INCREMENT,
  `name_status` varchar(45) NOT NULL,
  PRIMARY KEY (`idstatus`),
  UNIQUE KEY `idstatus_UNIQUE` (`idstatus`),
  UNIQUE KEY `name_status_UNIQUE` (`name_status`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'status1'),(2,'status2');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_of_equipment`
--

DROP TABLE IF EXISTS `type_of_equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_of_equipment` (
  `idtype` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) NOT NULL,
  PRIMARY KEY (`idtype`),
  UNIQUE KEY `idtype_of_equipment_UNIQUE` (`idtype`),
  UNIQUE KEY `type_name_UNIQUE` (`type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_of_equipment`
--

LOCK TABLES `type_of_equipment` WRITE;
/*!40000 ALTER TABLE `type_of_equipment` DISABLE KEYS */;
INSERT INTO `type_of_equipment` VALUES (6,'7'),(7,'88'),(1,'type1'),(2,'type2'),(3,'type3'),(4,'type5'),(5,'type6');
/*!40000 ALTER TABLE `type_of_equipment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-30 16:37:55
