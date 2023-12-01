-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: qschool
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `CourseID` int NOT NULL AUTO_INCREMENT,
  `CourseName` varchar(100) DEFAULT NULL,
  `CourseDescription` varchar(500) DEFAULT NULL,
  `CourseStartTime` datetime DEFAULT NULL,
  `CourseEndTime` datetime DEFAULT NULL,
  `TeacherID` int NOT NULL,
  PRIMARY KEY (`CourseID`),
  KEY `TeacherID` (`TeacherID`),
  CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`TeacherID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (5,'离散数学','离散数学（英语：Discrete mathematics）是数学的几个分支的总称，研究基于离散空间而不是连续的数学结构。 与连续变化的实数不同，离散数学的研究对象——例如整数、图和数学逻辑中的命题——不是连续变化的，而是拥有不等、分立的值。 因此离散数学不包含微积分和分析等“连续数学”的内容。','2023-12-12 08:00:00','2023-12-14 08:00:00',5),(6,'课程13','高等数学描述','2023-02-28 08:00:00','2023-06-22 08:00:00',5),(7,'课程14','线性代数描述','2023-12-05 08:00:00','2023-12-18 08:00:00',5),(8,'课程15','大学英语描述','2023-12-05 08:00:00','2023-12-19 08:00:00',5),(9,'课程16','离散数学描述','2023-12-12 08:00:00','2023-12-14 08:00:00',5),(10,'课程1','课程1描述','2023-12-15 08:00:00','2023-12-17 08:00:00',5),(11,'课程2','课程2描述','2023-12-18 08:00:00','2023-12-20 08:00:00',5),(12,'课程3','课程3描述','2023-12-21 08:00:00','2023-12-23 08:00:00',5),(13,'课程4','课程4描述','2023-12-24 08:00:00','2023-12-26 08:00:00',5),(14,'课程5','课程5描述','2023-12-27 08:00:00','2023-12-29 08:00:00',5),(15,'课程6','课程6描述','2023-12-30 08:00:00','2024-01-01 08:00:00',5),(16,'课程7','课程7描述','2024-01-02 08:00:00','2024-01-04 08:00:00',5),(17,'课程8','课程8描述','2024-01-05 08:00:00','2024-01-07 08:00:00',5),(18,'课程9','课程9描述','2024-01-08 08:00:00','2024-01-10 08:00:00',5),(19,'课程10','课程10描述','2024-01-11 08:00:00','2024-01-13 08:00:00',5),(20,'课程11','课程11描述','2024-01-14 08:00:00','2024-01-16 08:00:00',5),(21,'课程12','课程12描述','2024-01-17 08:00:00','2024-01-19 08:00:00',5);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `deptno` int NOT NULL,
  `dname` varchar(255) DEFAULT NULL,
  `loc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `EMPNO` int NOT NULL AUTO_INCREMENT,
  `ENAME` varchar(10) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `JOB` varchar(9) DEFAULT NULL,
  `MGR` int DEFAULT NULL,
  `HIREDATE` date DEFAULT NULL,
  `SAL` double DEFAULT NULL,
  `COMM` double DEFAULT NULL,
  `DEPTNO` int DEFAULT NULL,
  PRIMARY KEY (`EMPNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentcourses`
--

DROP TABLE IF EXISTS `studentcourses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studentcourses` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `StudentID` int DEFAULT NULL,
  `CourseID` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `studentcourses_user_id_fk` (`StudentID`),
  KEY `studentcourses_courses_CourseID_fk` (`CourseID`),
  CONSTRAINT `studentcourses_courses_CourseID_fk` FOREIGN KEY (`CourseID`) REFERENCES `courses` (`CourseID`),
  CONSTRAINT `studentcourses_user_id_fk` FOREIGN KEY (`StudentID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentcourses`
--

LOCK TABLES `studentcourses` WRITE;
/*!40000 ALTER TABLE `studentcourses` DISABLE KEYS */;
/*!40000 ALTER TABLE `studentcourses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `sex` int DEFAULT NULL,
  `age` int DEFAULT NULL,
  `avator` varchar(500) DEFAULT NULL,
  `isAdmin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'roger','$2a$10$tm.r.vosvVzh8dQ.t/Z45OCeMV4mpojgb5Osq0VGNc/Jy4v8ZMDGi',NULL,NULL,'https://s1.ax1x.com/2023/03/06/ppZmZ36.jpg',1),(6,'jack','$2a$10$zch4d0qCu3EdsJCM5yfxXu1Q8G2fWfEXLRoZRZSr4FUFW96fR5eS2',NULL,NULL,'https://s1.ax1x.com/2023/03/06/ppZmZ36.jpg',0),(7,'lucy','$2a$10$mI0mqJK2zn533C6jKS.obupeDarrVeEXfmsW2cua1qZMDWqSqzC0W',NULL,NULL,'https://s1.ax1x.com/2023/03/06/ppZmZ36.jpg',0),(8,'zhangsan','$2a$10$skXIKU/ynLs/8Lrkkgp/3uq5cOUHREkut92STWKQywEaEyQdTercO',NULL,NULL,'https://s1.ax1x.com/2023/03/06/ppZmZ36.jpg',0),(9,'spike','$2a$10$u/uLlL786aszvhRD2.bXueTyyWQO2fZ8oNe0UVzl0p1OJ2MEJFTx2',NULL,NULL,'https://s1.ax1x.com/2023/03/06/ppZmZ36.jpg',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-01 18:26:44
