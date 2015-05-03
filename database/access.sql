-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: access
-- ------------------------------------------------------
-- Server version	5.6.24-log

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
-- Table structure for table `actions`
--

DROP TABLE IF EXISTS `actions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actions` (
  `ActID` int(11) NOT NULL AUTO_INCREMENT,
  `AppID` int(11) DEFAULT NULL,
  `ActName` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `ActDes` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`ActID`),
  KEY `act_id_app_id_idx` (`AppID`),
  CONSTRAINT `act_app` FOREIGN KEY (`AppID`) REFERENCES `applications` (`AppID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actions`
--

LOCK TABLES `actions` WRITE;
/*!40000 ALTER TABLE `actions` DISABLE KEYS */;
/*!40000 ALTER TABLE `actions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applications`
--

DROP TABLE IF EXISTS `applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applications` (
  `AppID` int(11) NOT NULL AUTO_INCREMENT,
  `AppName` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `AppDes` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KeyApp` varchar(45) COLLATE utf8_unicode_ci DEFAULT 'null',
  PRIMARY KEY (`AppID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applications`
--

LOCK TABLES `applications` WRITE;
/*!40000 ALTER TABLE `applications` DISABLE KEYS */;
/*!40000 ALTER TABLE `applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `AppID` int(11) NOT NULL,
  `PasswordFormat` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PasswordValidate` varchar(45) COLLATE utf8_unicode_ci DEFAULT 'null',
  `UserNameValidate` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MaxFailedPassword` int(11) DEFAULT '5',
  PRIMARY KEY (`AppID`),
  CONSTRAINT `fasdf` FOREIGN KEY (`AppID`) REFERENCES `applications` (`AppID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_act`
--

DROP TABLE IF EXISTS `role_act`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_act` (
  `RoleID` int(11) NOT NULL,
  `ActID` int(11) NOT NULL,
  PRIMARY KEY (`RoleID`,`ActID`),
  KEY `fdgv_idx` (`ActID`),
  CONSTRAINT `dfg` FOREIGN KEY (`RoleID`) REFERENCES `roles` (`RoleID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fdgv` FOREIGN KEY (`ActID`) REFERENCES `actions` (`ActID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_act`
--

LOCK TABLES `role_act` WRITE;
/*!40000 ALTER TABLE `role_act` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_act` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `RoleID` int(11) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RoleDes` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `AppID` int(11) DEFAULT NULL,
  PRIMARY KEY (`RoleID`),
  KEY `role_appID_idx` (`AppID`),
  CONSTRAINT `role_app` FOREIGN KEY (`AppID`) REFERENCES `applications` (`AppID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_act`
--

DROP TABLE IF EXISTS `user_act`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_act` (
  `UserID` int(11) NOT NULL,
  `ActID` int(11) NOT NULL,
  PRIMARY KEY (`UserID`,`ActID`),
  KEY `acasdf_idx` (`ActID`),
  CONSTRAINT `acasdf` FOREIGN KEY (`ActID`) REFERENCES `actions` (`ActID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ewrt` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_act`
--

LOCK TABLES `user_act` WRITE;
/*!40000 ALTER TABLE `user_act` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_act` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `UserID` int(11) NOT NULL,
  `RoleID` int(11) NOT NULL,
  PRIMARY KEY (`UserID`,`RoleID`),
  KEY `fsdf_idx` (`RoleID`),
  CONSTRAINT `fsdf` FOREIGN KEY (`RoleID`) REFERENCES `roles` (`RoleID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sdafasdf` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PasswordUser` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IsApproved` int(11) DEFAULT NULL,
  `IsLockedOut` int(11) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `LastLoginDate` datetime DEFAULT NULL,
  `LastPasswordChangedDate` datetime DEFAULT NULL,
  `LastLockoutDate` datetime DEFAULT NULL,
  `FailedPasswordAttemptCount` int(11) DEFAULT NULL,
  `FailedPasswordAttemptWindowStart` datetime DEFAULT NULL,
  `LastActiveDate` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `AppID` int(11) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  KEY `UserID_idx` (`UserID`),
  KEY `mem_appid_idx` (`AppID`),
  CONSTRAINT `mem_app` FOREIGN KEY (`AppID`) REFERENCES `applications` (`AppID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'access'
--
/*!50003 DROP FUNCTION IF EXISTS `find_action` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `find_action`(idapp int,n varchar(45)) RETURNS int(11)
BEGIN
	declare tmp int default 0;
    SELECT ActID into tmp FROM access.actions
    where (AppID=idapp)and(ActName=n);
RETURN tmp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `find_role` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `find_role`(idapp int,r varchar(45)) RETURNS int(11)
BEGIN
	declare tmp int default 0;

	SELECT RoleID into tmp FROM access.roles
    where  (AppID=idapp)and(RoleName=r);
RETURN tmp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `find_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `find_user`(idapp int,uname varchar(45)) RETURNS int(11)
BEGIN
	declare tmp int default 0;
    
    select UserID into tmp from access.users
    where AppID=idapp and UserName=uname;
RETURN tmp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AddRoleAct` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddRoleAct`(in idapp int,in r varchar(45),in a varchar(45),
	out result int)
_main:BEGIN
	declare tmp int default 0;
    declare tmp1 int default 0;
    declare tmp2 int default 0;
     declare exit handler for sqlexception
		 set result=0;
	select find_action(idapp,a) into tmp;
    -- select ActID into tmp from access.actions where AppID=idapp and ActName=a;
    if tmp=0 then
		set result=2;
        leave _main;
	end if;
     select find_role(idapp,r) into tmp1;
     -- select RoleID into tmp1 from access.roles where AppID=idapp and RoleName=r;
     if tmp1=0 then
		set result=3;
        leave _main;
	end if;
    select count(*) into tmp2 from access.role_act
    where ActID=tmp and RoleID=tmp1;
    if tmp2 !=0 then
		set result=4;
        leave _main;
	else
		INSERT INTO `access`.`role_act` (`RoleID`, `ActID`) 
        VALUES (tmp1, tmp);
		set result=1;
        leave _main;
	end if;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AddUserAct` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddUserAct`(in idapp int,in u varchar(45),in a varchar(45),
	out result int)
_main:BEGIN
	declare tmp int default 0;
    declare tmp1 int default 0;
    declare tmp2 int default 0;
     declare exit handler for sqlexception
		 set result=0;
	select find_action(idapp,a) into tmp;
    -- select ActID into tmp from access.actions where AppID=idapp and ActName=a;
    if tmp=0 then
		set result=2;
        leave _main;
	end if;
     select find_user(idapp,u) into tmp1;
     -- select RoleID into tmp1 from access.roles where AppID=idapp and RoleName=r;
     if tmp1=0 then
		set result=3;
        leave _main;
	end if;
    select count(*) into tmp2 from access.user_act
    where ActID=tmp and UserID=tmp1;
    if tmp2 !=0 then
		set result=4;
        leave _main;
	else
		INSERT INTO `access`.`user_act` (`UserID`, `ActID`) 
        VALUES (tmp1, tmp);
		set result=1;
        leave _main;
	end if;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AddUserRole` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddUserRole`(in idapp int,in u varchar(45),in r varchar(45),
	out result int)
_main:BEGIN
	declare tmp int default 0;
    declare tmp1 int default 0;
    declare tmp2 int default 0;
     declare exit handler for sqlexception
		 set result=0;
	select find_user(idapp,u) into tmp;
    -- select ActID into tmp from access.actions where AppID=idapp and ActName=a;
    if tmp=0 then
		set result=2;
        leave _main;
	end if;
     select find_role(idapp,r) into tmp1;
     -- select RoleID into tmp1 from access.roles where AppID=idapp and RoleName=r;
     if tmp1=0 then
		set result=3;
        leave _main;
	end if;
    select count(*) into tmp2 from access.user_role
    where UserID=tmp and RoleID=tmp1;
    if tmp2 !=0 then
		set result=4;
        leave _main;
	else
		INSERT INTO `access`.`user_role` (`UserID`, `RoleID`) 
        VALUES (tmp, tmp1);
		set result=1;
        leave _main;
	end if;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `CheckUserAct` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `CheckUserAct`(in idapp int,in uname varchar(45),in aname varchar(45),
    out result int)
BEGIN
	declare tmp0 int default 0;
    declare tmp1 int default 0;
    declare tmp2 int default 0;
    declare exit handler for sqlexception
		set result=0;
        
	select find_user(idapp,uname) into tmp0;
    if tmp0=0 then
		set result=2;
	end if;
    select find_action(idapp,aname) into tmp1;
    if tmp1=0 then
		set result=3;
	end if;
    
    select count(*) into tmp2
    from user_act
    where UserID=tmp0 and ActID=tmp1;
    
    if tmp2=0 then
		set result=4;
	elseif tmp2=1 then
		set result=1;
	else
		set result=0;
	end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Close_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Close_user`(in idapp int,in uname varchar(45),out result int)
_main:BEGIN
	declare tmp0 int default 0;
    declare tmp1 int default 0;
	declare exit handler for sqlexception
		set result=0;
	
    select UserID,IsApproved into tmp0,tmp1
    from access.users
    where AppID=idapp and UserName=uname;
    
    if tmp0 is null then
		set result=2;
        leave _main;
	elseif tmp1=0 then
		set result=3;
        leave _main;
	else
		UPDATE `access`.`users` 
        SET `IsApproved`='0' 
        WHERE `UserID`=tmp0;
		set result=1;
        leave _main;
	end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `create_act` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_act`(in idapp int,in n varchar(45),in d varchar(45),
	out result int)
BEGIN
	declare tmp int default 0;
    declare exit handler for sqlexception
		set result=0;
	select find_action(idapp,n) into tmp;
    if tmp!=0 then
		set result=2;
	elseif tmp=0 then
		INSERT INTO `access`.`actions` (`AppID`, `ActName`, `ActDes`) 
        VALUES (idapp, n, d);
		set result=1;
	end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `create_app` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_app`(in appn varchar(45),in appde varchar(45),in keya varchar(45),
	out idapp int)
BEGIN
    declare exit handler for sqlexception
		set idapp=0;
	
    INSERT INTO `access`.`applications` (`AppName`, `AppDes`, `KeyApp`) 
    VALUES (appn, appde, keya);
    
    select AppID into idapp from access.applications
    where AppName=appn and AppDes=appde and KeyApp=keya;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `create_config` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_config`(in idapp int,
	in u varchar(45),in m int,
	in p varchar(45),out result int)
BEGIN
    declare exit handler for sqlexception
		set result=0;

	INSERT INTO `access`.`config` 
    (`AppID`, `PasswordFormat`, `UserNameValidate`, `MaxFailedPassword`, `PasswordValidate`)
    VALUES (idapp, 'MD5', u, m, p);

	set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `create_role` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_role`(in idapp int,in n varchar(45),in d varchar(45),
	out result int)
BEGIN
	declare tmp int default 0;
    declare exit handler for sqlexception
		set result=0;
	select find_role(idapp,n) into tmp;
	

    if tmp!=0 then
		set result=2;
	elseif tmp=0 then
		INSERT INTO `access`.`roles` (`AppID`, `RoleName`, `RoleDes`) 
        VALUES (idapp, n, d);
		set result=1;
	end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `create_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_user`(in idapp int,in uname varchar(45),
	in pass varchar(45),
	in mail varchar(45),
	out result int)
_main:BEGIN
	declare tmp int default 0;
	declare exit handler for sqlexception
		set result=0;
	
    select find_user(idapp,uname) into tmp;
    
    if tmp!=0 then
		set result=2;
        leave _main;
	elseif tmp=0 then
		INSERT INTO `access`.`users` 
        (`UserName`, `PasswordUser`, `Email`, `IsApproved`,
        `IsLockedOut`, `CreateDate`, `LastActiveDate`, `AppID`)
        VALUES (uname, pass, mail, '1', '0', now(), now(), idapp);

		set result=1;
        leave _main;
	end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `DeleteRoleAct` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteRoleAct`(in idapp int,in r varchar(45),in a varchar(45),
	out result int)
_main:BEGIN
	declare tmp int default 0;
    declare tmp1 int default 0;
    declare tmp2 int default 0;
     declare exit handler for sqlexception
		 set result=0;
	select find_action(idapp,a) into tmp;
    -- select ActID into tmp from access.actions where AppID=idapp and ActName=a;
    if tmp=0 then
		set result=2;
        leave _main;
	end if;
     select find_role(idapp,r) into tmp1;
     -- select RoleID into tmp1 from access.roles where AppID=idapp and RoleName=r;
     if tmp1=0 then
		set result=3;
        leave _main;
	end if;
    select count(*) into tmp2 from access.role_act
    where ActID=tmp and RoleID=tmp1;
    if tmp2 =0 then
		set result=4;
        leave _main;
	else
		DELETE FROM `access`.`role_act`
        WHERE `RoleID`=tmp1 and`ActID`=tmp;

		set result=1;
        leave _main;
	end if;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `DeleteUserAct` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteUserAct`(in idapp int,in u varchar(45),in a varchar(45),
	out result int)
_main:BEGIN
	declare tmp int default 0;
    declare tmp1 int default 0;
    declare tmp2 int default 0;
     declare exit handler for sqlexception
		 set result=0;
	select find_action(idapp,a) into tmp;
    -- select ActID into tmp from access.actions where AppID=idapp and ActName=a;
    if tmp=0 then
		set result=2;
        leave _main;
	end if;
     select find_user(idapp,u) into tmp1;
     -- select RoleID into tmp1 from access.roles where AppID=idapp and RoleName=r;
     if tmp1=0 then
		set result=3;
        leave _main;
	end if;
    select count(*) into tmp2 from access.user_act
    where ActID=tmp and UserID=tmp1;
    if tmp2 =0 then
		set result=4;
        leave _main;
	else
		DELETE FROM `access`.`user_act`
        WHERE `UserID`=tmp1 and`ActID`=tmp;

		set result=1;
        leave _main;
	end if;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `DeleteUserRole` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteUserRole`(in idapp int,in u varchar(45),in r varchar(45),
	out result int)
_main:BEGIN
	declare tmp int default 0;
    declare tmp1 int default 0;
    declare tmp2 int default 0;
     declare exit handler for sqlexception
		 set result=0;
	select find_role(idapp,r) into tmp;
    -- select ActID into tmp from access.actions where AppID=idapp and ActName=a;
    if tmp=0 then
		set result=2;
        leave _main;
	end if;
     select find_user(idapp,u) into tmp1;
     -- select RoleID into tmp1 from access.roles where AppID=idapp and RoleName=r;
     if tmp1=0 then
		set result=3;
        leave _main;
	end if;
    select count(*) into tmp2 from access.user_role
    where RoleID=tmp and UserID=tmp1;
    if tmp2 =0 then
		set result=4;
        leave _main;
	else
		DELETE FROM `access`.`user_role`
        WHERE `UserID`=tmp1 and`RoleID`=tmp;

		set result=1;
        leave _main;
	end if;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Delete_act` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Delete_act`(in idapp int ,in aname varchar(45),out result int)
_main:BEGIN
	declare tmp0 int default 0;
    declare exit handler for sqlexception
		 set result=0;
	select find_action(idapp,aname) into tmp0;
    if tmp0=0 then
		set result=2;
        leave _main;
	end if;
    DELETE FROM `access`.`role_act` WHERE `ActID`=tmp0;
	delete from access.user_act where ActID=tmp0;
    delete from access.actions where ActID=tmp0;
    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_app` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_app`(in id int,out result int)
_main:BEGIN
	declare tmp0 int default 0;
	declare exit handler for sqlexception
		 set result=0;
	SELECT AppID into tmp0 FROM access.applications where AppID=id;
    if tmp0=0 then
		set result=2;
        leave _main;
	else
		DELETE FROM `access`.`applications` WHERE `AppID`=id;
		set result=1;
        leave _main;
    end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_config` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_config`(in idapp int ,out result int)
_main:BEGIN
	declare tmp0 int default 0;
    declare exit handler for sqlexception
		 set result=0;
	select AppID into tmp0 from access.config where AppID=idapp;
    if tmp0=0 then
		set result=2;
        leave _main;
	end if;
    DELETE FROM `access`.`config` WHERE `AppID`=idapp;

    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Delete_role` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Delete_role`(in idapp int ,in rname varchar(45),out result int)
_main:BEGIN
	declare tmp0 int default 0;
    declare exit handler for sqlexception
		 set result=0;
	select find_role(idapp,rname) into tmp0;
    if tmp0=0 then
		set result=2;
        leave _main;
	end if;
    DELETE FROM `access`.`role_act` WHERE `RoleID`=tmp0;
	delete from access.user_Role where RoleID=tmp0;
    delete from access.roles where RoleID=tmp0;
    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Delete_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Delete_user`(in idapp int ,in uname varchar(45),out result int)
_main:BEGIN
	declare tmp0 int default 0;
    declare exit handler for sqlexception
		 set result=0;
	select find_user(idapp,uname) into tmp0;
    if tmp0=0 then
		set result=2;
        leave _main;
	end if;
    DELETE FROM `access`.`user_act` WHERE `UserID`=tmp0;
	delete from access.user_Role where UserID=tmp0;
    delete from access.users where UserID=tmp0;
    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `find_action` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_action`(in idapp int,in a varchar(45),out result int)
BEGIN
    declare exit handler for sqlexception
		set result=0;
	select * from access.actions where AppID=idapp and ActName=a;
    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `find_app` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_app`(in id int,out result int)
BEGIN
	declare exit handler for sqlexception
		set result=0;
	select *
    from access.applications
    where AppID=id;
    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `find_config` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_config`(in idapp int,out result int)
BEGIN
	declare exit handler for sqlexception
		set result=0;
	select * from access.config where AppID=idapp;
    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `find_role` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_role`(in idapp int,in r varchar(45),out result int)
BEGIN
    declare exit handler for sqlexception
		set result=0;
	select * from access.roles where AppID=idapp and RoleName=r;
    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `find_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_user`(in idapp int,in u varchar(45),out result int)
BEGIN
    declare exit handler for sqlexception
		set result=0;
	select * from access.users where AppID=idapp and UserName=u;
    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ListActionByApp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListActionByApp`(in id int,out result int)
BEGIN
	declare exit handler for sqlexception
		set result=0;
	select * from access.actions where AppID=id;
    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ListActionByRole` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListActionByRole`(in idapp int,in r varchar(45),out result int)
_main:BEGIN
	declare tmp int default 0;
    declare exit handler for sqlexception
		set result=0;
	select find_role(idapp,r) into tmp;
	
    if tmp=0 then
		set result=2;
        leave _main;
	end if;
    
    select access.actions.ActID,ActName,ActDes
    from access.actions,access.role_act
    where access.actions.ActID=access.role_act.ActID
    and access.role_act.RoleID=tmp;
    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ListActionByUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListActionByUser`(in idapp int,in u varchar(45),out result int)
_main:BEGIN
	declare tmp int default 0;
    declare exit handler for sqlexception
		set result=0;
	select find_user(idapp,u) into tmp;
	
    if tmp=0 then
		set result=2;
        leave _main;
	end if;
    
    select access.actions.ActID,ActName,ActDes
    from access.actions,access.user_act
    where access.actions.ActID=access.user_act.ActID
    and access.user_act.UserID=tmp;
    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ListApplication` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListApplication`(out result int)
BEGIN
	declare exit handler for sqlexception
		 set result=0;
	SELECT * FROM access.applications;
		set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ListRoleByAct` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListRoleByAct`(in idapp int,in a varchar(45),
	out result int)
_main:BEGIN
	declare tmp int default 0;
    declare exit handler for sqlexception
		set result=0;
	select find_action(idapp,a) into tmp;
	
    if tmp=0 then
		set result=2;
        leave _main;
	end if;
    
    select access.roles.RoleID,RoleName,RolesDes
    from access.roles,access.role_act
    where access.roles.RoleID=access.role_act.RoleID
    and access.role_act.ActID=tmp;
    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ListRoleByApp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListRoleByApp`(in id int,out result int)
BEGIN
	declare exit handler for sqlexception
		set result=0;
	select * from access.roles where AppID=id;
    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ListRoleByUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListRoleByUser`(in idapp int,in u varchar(45),
	out result int)
_main:BEGIN
	declare tmp int default 0;
    declare exit handler for sqlexception
		set result=0;
	select find_user(idapp,u) into tmp;
	
    if tmp=0 then
		set result=2;
        leave _main;
	end if;
    
    select access.roles.RoleID,RoleName,RolesDes
    from access.roles,access.user_role
    where access.roles.RoleID=access.user_role.RoleID
    and access.user_role.UserID=tmp;
    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ListUserByAct` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListUserByAct`(in idapp int,in a varchar(45),
	out result int)
_main:BEGIN
	declare tmp int default 0;
    declare exit handler for sqlexception
		set result=0;
	select find_action(idapp,a) into tmp;
	
    if tmp=0 then
		set result=2;
        leave _main;
	end if;
    
    select *
    from access.users,access.user_act
    where access.users.UserID=access.user_act.UserID
    and access.user_act.ActID=tmp;
    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `listUserByApp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `listUserByApp`(in idapp int,out result int)
BEGIN
	declare exit handler for sqlexception
		set result=0;
	select * from access.users where AppID=idapp;
    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ListUserByRole` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ListUserByRole`(in idapp int,in r varchar(45),out result int)
_main:BEGIN
	declare tmp int default 0;
    declare exit handler for sqlexception
		set result=0;
	select find_role(idapp,r) into tmp;
	
    if tmp=0 then
		set result=2;
        leave _main;
	end if;
    
    select *
    from access.users,access.user_role
    where access.users.UserID=access.user_role.UserID
    and access.user_role.RoleID=tmp;
    set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `locad_config` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `locad_config`(out result int)
BEGIN
	declare exit handler for sqlexception
		 set result=0;
	 select * from access.config;
		set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `logout` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `logout`(in idapp int,
	in uname varchar(45),out result int)
_main:BEGIN
	declare tmp0 int default 0;
    declare tmp1 int default 0;
    declare tmp2 int default 0;

    declare exit handler for sqlexception
		set result=0;
        
	select UserID,IsApproved,IsLockedOut
    into tmp0,tmp1,tmp2 from access.users
    where AppID=idapp and UserName=uname;
    
    if tmp0 is null then
		set result=2;
        leave _main;
	end if;
    if tmp1=0 then
		set result=3;
        leave _main;
	end if;
    
	if tmp2=1 then
		set result=4;
        leave _main;
    end if;

	UPDATE `access`.`users` 
    SET `IsLockedOut`='1', `LastLockoutDate`=now()
    WHERE `UserID`=tmp0;

	set result=1;
	leave _main;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `signin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `signin`(in idapp int,
	in uname varchar(45),in p varchar(45),out result int)
_main:BEGIN
	declare tmp0 int default 0;
    declare tmp1 int default 0;
    declare tmp2 int default 0;
	declare tmp3 varchar(45) default null;
    declare tmp5 int default 0;
    declare exit handler for sqlexception
		set result=0;
        
	select UserID,IsApproved,IsLockedOut,PasswordUser,FailedPasswordAttemptCount
    into tmp0,tmp1,tmp2,tmp3,tmp5 from access.users
    where AppID=idapp and UserName=uname;
    
    if tmp0 is null then
		set result=2;
        leave _main;
	end if;
    if tmp1=0 then
		set result=3;
        leave _main;
	end if;
    
	if tmp2=0 then
		set result=4;
        leave _main;
    end if;
    
    if tmp3 !=p then
		UPDATE `access`.`users` 
        SET `FailedPasswordAttemptCount`=tmp5+1, `FailedPasswordAttemptWindowStart`=now() 
        WHERE `UserID`=tmp0;

		set result=5;
        leave _main;
	end if;
    
	UPDATE `access`.`users` 
    SET `PasswordUser`=newp, 
    `LastLoginDate`=now(),
    `FailedPasswordAttemptCount`=0, `FailedPasswordAttemptWindowStart`=null
    WHERE `UserID`=tmp0;


	set result=1;
	leave _main;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_act` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_act`(in idapp int,in oldn varchar(45),
	in des varchar(45),out result int,in newn varchar(45))
_main:BEGIN
	declare tmp int default 0;
    declare tmp1 int default 0;
    declare exit handler for sqlexception
		set result=0;
        
	select find_action(idapp,oldn) into tmp;
    if tmp=0 then
		set result=2;
        leave _main;
	end if;
    
    select find_action(idapp,newn) into tmp1;
    if tmp1 !=0 and tmp1!= tmp then
		set result=3;
        leave _main;
	end if;

	UPDATE `access`.`actions` 
	SET `ActName`=newn, `ActDes`=des 
	WHERE `ActID`=tmp;
	set result=1;
	leave _main;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_app` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_app`(in idapp int,in newn varchar(45),
	in des varchar(45),out result int)
_main:BEGIN
    declare exit handler for sqlexception
		set result=0;

	UPDATE `access`.`applications`
    SET `AppName`=newn, `AppDes`=des
    WHERE `AppID`=idapp;

	set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_config` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_config`(in idapp int,
	in u varchar(45),in m int,
	in p varchar(45),out result int)
BEGIN

	UPDATE `access`.`config` 
    SET `UserNameValidate`=u, `MaxFailedPassword`=m, `PasswordValidate`=p
    WHERE `AppID`=idapp;

	set result=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_email` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_email`(in idapp int,in uname varchar(45),
	in m varchar(45),out result int)
_main:BEGIN
	declare tmp0 int default 0;
    declare tmp1 int default 0;
    declare tmp2 int default 0;
    declare tmp3 varchar(45) default null;

    declare exit handler for sqlexception
		set result=0;
        
	select UserID,IsApproved,IsLockedOut,Email
    into tmp0,tmp1,tmp2,tmp3 from access.users
    where AppID=idapp and UserName=uname;
    
    if tmp0 is null then
		set result=2;
        leave _main;
	end if;
    if tmp1=0 then
		set result=3;
        leave _main;
	end if;
	if tmp2=1 then
		set result=4;
        leave _main;
    end if;
    
    if tmp3=m then
		set result=5;
        leave _main;
	end if;
    
	UPDATE `access`.`users`
    SET `Email`=m
    WHERE `UserID`=tmp0;

	set result=1;
	leave _main;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_password_by_admin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_password_by_admin`(in idapp int,
	in uname varchar(45),
	in newp varchar(45),out result int)
_main:BEGIN
	declare tmp0 int default 0;
    declare tmp1 varchar(45) default null;
    declare exit handler for sqlexception
		set result=0;
        
	select UserID,PasswordUser
    into tmp0,tmp1 from access.users
    where AppID=idapp and UserName=uname;
    
    if tmp0 is null then
		set result=2;
        leave _main;
	end if;
    if tmp1 =newp then
		set result=3;
        leave _main;
	end if;
    
	UPDATE `access`.`users` 
    SET `PasswordUser`=newp, `LastPasswordChangedDate`=now(),
    `FailedPasswordAttemptCount`=0, `FailedPasswordAttemptWindowStart`=null
    WHERE `UserID`=tmp0;


	set result=1;
	leave _main;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_password_by_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_password_by_user`(in idapp int,
	in uname varchar(45),in oldp varchar(45),
	in newp varchar(45),out result int)
_main:BEGIN
	declare tmp0 int default 0;
    declare tmp1 int default 0;
    declare tmp2 int default 0;
	declare tmp3 varchar(45) default null;
    declare exit handler for sqlexception
		set result=0;
        
	select UserID,IsApproved,IsLockedOut,PasswordUser
    into tmp0,tmp1,tmp2,tmp3 from access.users
    where AppID=idapp and UserName=uname;
    
    if tmp0 is null then
		set result=2;
        leave _main;
	end if;
    if tmp1=0 then
		set result=3;
        leave _main;
	end if;
	if tmp2=1 then
		set result=4;
        leave _main;
    end if;
    
    if tmp3 !=oldp then
		set result=5;
        leave _main;
	end if;
    
	UPDATE `access`.`users` 
    SET `PasswordUser`=newp, `LastPasswordChangedDate`=now(),
    `FailedPasswordAttemptCount`=0, `FailedPasswordAttemptWindowStart`=null
    WHERE `UserID`=tmp0;


	set result=1;
	leave _main;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_role` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_role`(in idapp int,in oldn varchar(45),
	in newn varchar(45),in des varchar(45),out result int)
_main:BEGIN
	declare tmp int default 0;
    declare tmp1 int default 0;
    declare exit handler for sqlexception
		set result=0;
        
	select find_role(idapp,oldn) into tmp;
    if tmp=0 then
		set result=2;
        leave _main;
	end if;
    
    select find_role(idapp,newn) into tmp1;
    if tmp1 !=0 and tmp1!=tmp then
		set result=3;
        leave _main;
	end if;

	UPDATE `access`.`roles` 
	SET `RoleName`=newn, `RoleDes`=des 
	WHERE `RoleID`=tmp;
	set result=1;
	leave _main;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_username` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_username`(in idapp int,in oldn varchar(45),
	in newn varchar(45),out result int)
_main:BEGIN
	declare tmp0 int default 0;
    declare tmp1 int default 0;
    declare tmp2 int default 0;
    declare tmp3 int default 0;
    declare exit handler for sqlexception
		set result=0;
        
	select UserID,IsApproved,IsLockedOut
    into tmp0,tmp1,tmp2 from access.users
    where AppID=idapp and UserName=oldn;
    
    if tmp0 is null then
		set result=2;
        leave _main;
	end if;
    if tmp1=0 then
		set result=3;
        leave _main;
	end if;
	if tmp2=1 then
		set result=4;
        leave _main;
    end if;
    
    select find_user(idapp,newn) into tmp3;
    if tmp3 !=0 then
		set result=5;
        leave _main;
	end if;
    
	UPDATE `access`.`users`
    SET `UserName`=newn
    WHERE `UserID`=tmp0;

	set result=1;
	leave _main;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-02 15:21:09
