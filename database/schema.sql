CREATE DATABASE IF NOT EXISTS train_reservation;
USE train_reservation;

DROP TABLE IF EXISTS `history`;
DROP TABLE IF EXISTS `train`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `mailid` varchar(50) NOT NULL,
  `pword` varchar(50) NOT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) DEFAULT NULL,
  `addr` varchar(100) DEFAULT NULL,
  `phno` bigint DEFAULT NULL,
  PRIMARY KEY (`mailid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `admin` VALUES ('admin@srijan.com','admin','Srijan','Admin','HQ Pune',9876543210);

CREATE TABLE `user` (
  `mailid` varchar(50) NOT NULL,
  `pword` varchar(50) NOT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) DEFAULT NULL,
  `addr` varchar(100) DEFAULT NULL,
  `phno` bigint DEFAULT NULL,
  PRIMARY KEY (`mailid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `train` (
  `trainno` bigint NOT NULL,
  `trainname` varchar(100) DEFAULT NULL,
  `fromstn` varchar(50) DEFAULT NULL,
  `tostn` varchar(50) DEFAULT NULL,
  `seats` bigint DEFAULT NULL,
  `fare` double DEFAULT NULL,
  PRIMARY KEY (`trainno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `history` (
  `transid` varchar(50) NOT NULL,
  `from_stn` varchar(50) DEFAULT NULL,
  `to_stn` varchar(50) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `mailid` varchar(50) DEFAULT NULL,
  `seats` bigint DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `tr_no` bigint DEFAULT NULL,
  PRIMARY KEY (`transid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
