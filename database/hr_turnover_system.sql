-- MySQL dump 10.13  Distrib 9.0.1, for Win64 (x86_64)
--
-- Host: localhost    Database: hr_turnover_system
-- ------------------------------------------------------
-- Server version	9.0.1

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `DeptID` int NOT NULL AUTO_INCREMENT,
  `DeptName` varchar(100) NOT NULL,
  `DeptPhone` varchar(25) DEFAULT NULL,
  `ManagerEmployeeID` int DEFAULT NULL,
  PRIMARY KEY (`DeptID`),
  KEY `fk_department_manager` (`ManagerEmployeeID`),
  CONSTRAINT `fk_department_manager` FOREIGN KEY (`ManagerEmployeeID`) REFERENCES `manager` (`EmployeeID`)
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
  `EmployeeID` int NOT NULL AUTO_INCREMENT,
  `DeptID` int DEFAULT NULL,
  `PositionID` int DEFAULT NULL,
  `FirstName` varchar(55) NOT NULL,
  `LastName` varchar(55) NOT NULL,
  `DOB` date DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `PhoneNum` varchar(25) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `HireDate` date NOT NULL,
  `Salary` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`),
  KEY `DeptID` (`DeptID`),
  KEY `PositionID` (`PositionID`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`DeptID`) REFERENCES `department` (`DeptID`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`PositionID`) REFERENCES `jobposition` (`PositionID`)
) ENGINE=InnoDB AUTO_INCREMENT=1235 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1234,NULL,NULL,'Sam','Smith',NULL,NULL,NULL,'Smith@gmail.com','2026-03-22',NULL);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exitinterview`
--

DROP TABLE IF EXISTS `exitinterview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exitinterview` (
  `InterviewID` int NOT NULL AUTO_INCREMENT,
  `EventID` int NOT NULL,
  `InterviewDate` date DEFAULT NULL,
  `Feedback` text,
  `StayFactors` text,
  `LeaveFactors` text,
  PRIMARY KEY (`InterviewID`),
  KEY `EventID` (`EventID`),
  CONSTRAINT `exitinterview_ibfk_1` FOREIGN KEY (`EventID`) REFERENCES `turnoverevent` (`EventID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exitinterview`
--

LOCK TABLES `exitinterview` WRITE;
/*!40000 ALTER TABLE `exitinterview` DISABLE KEYS */;
/*!40000 ALTER TABLE `exitinterview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goal`
--

DROP TABLE IF EXISTS `goal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goal` (
  `GoalID` int NOT NULL AUTO_INCREMENT,
  `EmployeeID` int NOT NULL,
  `Description` text,
  `DueDate` date DEFAULT NULL,
  PRIMARY KEY (`GoalID`),
  KEY `EmployeeID` (`EmployeeID`),
  CONSTRAINT `goal_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goal`
--

LOCK TABLES `goal` WRITE;
/*!40000 ALTER TABLE `goal` DISABLE KEYS */;
/*!40000 ALTER TABLE `goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobposition`
--

DROP TABLE IF EXISTS `jobposition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobposition` (
  `PositionID` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(100) NOT NULL,
  `JobDescription` text,
  `JobLevel` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PositionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobposition`
--

LOCK TABLES `jobposition` WRITE;
/*!40000 ALTER TABLE `jobposition` DISABLE KEYS */;
/*!40000 ALTER TABLE `jobposition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `EmployeeID` int NOT NULL,
  `StartDate` date DEFAULT NULL,
  `ManagerLevel` varchar(100) DEFAULT NULL,
  `ManagerQualifications` varchar(100) DEFAULT NULL,
  `ManagementStyle` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`),
  CONSTRAINT `manager_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offboardingrecord`
--

DROP TABLE IF EXISTS `offboardingrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offboardingrecord` (
  `OffboardingID` int NOT NULL AUTO_INCREMENT,
  `EventID` int NOT NULL,
  `ChecklistComplete` tinyint(1) DEFAULT NULL,
  `FinalizeDate` date DEFAULT NULL,
  PRIMARY KEY (`OffboardingID`),
  KEY `EventID` (`EventID`),
  CONSTRAINT `offboardingrecord_ibfk_1` FOREIGN KEY (`EventID`) REFERENCES `turnoverevent` (`EventID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offboardingrecord`
--

LOCK TABLES `offboardingrecord` WRITE;
/*!40000 ALTER TABLE `offboardingrecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `offboardingrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `onboardingrecord`
--

DROP TABLE IF EXISTS `onboardingrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `onboardingrecord` (
  `OnboardingID` int NOT NULL AUTO_INCREMENT,
  `EmployeeID` int NOT NULL,
  `StartDate` date DEFAULT NULL,
  PRIMARY KEY (`OnboardingID`),
  KEY `EmployeeID` (`EmployeeID`),
  CONSTRAINT `onboardingrecord_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `onboardingrecord`
--

LOCK TABLES `onboardingrecord` WRITE;
/*!40000 ALTER TABLE `onboardingrecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `onboardingrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performanceeval`
--

DROP TABLE IF EXISTS `performanceeval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `performanceeval` (
  `EvalID` int NOT NULL AUTO_INCREMENT,
  `EmployeeID` int NOT NULL,
  `EvalDate` date DEFAULT NULL,
  `Score` decimal(10,2) DEFAULT NULL,
  `Notes` text,
  PRIMARY KEY (`EvalID`),
  KEY `EmployeeID` (`EmployeeID`),
  CONSTRAINT `performanceeval_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performanceeval`
--

LOCK TABLES `performanceeval` WRITE;
/*!40000 ALTER TABLE `performanceeval` DISABLE KEYS */;
/*!40000 ALTER TABLE `performanceeval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey`
--

DROP TABLE IF EXISTS `survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey` (
  `SurveyID` int NOT NULL AUTO_INCREMENT,
  `SurveyTitle` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`SurveyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey`
--

LOCK TABLES `survey` WRITE;
/*!40000 ALTER TABLE `survey` DISABLE KEYS */;
/*!40000 ALTER TABLE `survey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `surveyresponse`
--

DROP TABLE IF EXISTS `surveyresponse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `surveyresponse` (
  `ResponseID` int NOT NULL AUTO_INCREMENT,
  `SurveyID` int NOT NULL,
  `EmployeeID` int NOT NULL,
  `SatisfactionLevel` int DEFAULT NULL,
  `Comments` text,
  PRIMARY KEY (`ResponseID`),
  KEY `SurveyID` (`SurveyID`),
  KEY `EmployeeID` (`EmployeeID`),
  CONSTRAINT `surveyresponse_ibfk_1` FOREIGN KEY (`SurveyID`) REFERENCES `survey` (`SurveyID`) ON DELETE CASCADE,
  CONSTRAINT `surveyresponse_ibfk_2` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `surveyresponse`
--

LOCK TABLES `surveyresponse` WRITE;
/*!40000 ALTER TABLE `surveyresponse` DISABLE KEYS */;
/*!40000 ALTER TABLE `surveyresponse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turnoverevent`
--

DROP TABLE IF EXISTS `turnoverevent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turnoverevent` (
  `EventID` int NOT NULL AUTO_INCREMENT,
  `EmployeeID` int NOT NULL,
  `ReasonID` int DEFAULT NULL,
  `DepartureDate` date DEFAULT NULL,
  `DepartureType` varchar(55) DEFAULT NULL,
  `EmployeeLength` int DEFAULT NULL,
  PRIMARY KEY (`EventID`),
  KEY `EmployeeID` (`EmployeeID`),
  KEY `ReasonID` (`ReasonID`),
  CONSTRAINT `turnoverevent_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`) ON DELETE CASCADE,
  CONSTRAINT `turnoverevent_ibfk_2` FOREIGN KEY (`ReasonID`) REFERENCES `turnoverreason` (`ReasonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turnoverevent`
--

LOCK TABLES `turnoverevent` WRITE;
/*!40000 ALTER TABLE `turnoverevent` DISABLE KEYS */;
/*!40000 ALTER TABLE `turnoverevent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turnoverreason`
--

DROP TABLE IF EXISTS `turnoverreason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turnoverreason` (
  `ReasonID` int NOT NULL AUTO_INCREMENT,
  `Description` text NOT NULL,
  PRIMARY KEY (`ReasonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turnoverreason`
--

LOCK TABLES `turnoverreason` WRITE;
/*!40000 ALTER TABLE `turnoverreason` DISABLE KEYS */;
/*!40000 ALTER TABLE `turnoverreason` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-11 23:06:39
