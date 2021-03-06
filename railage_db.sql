-- MySQL Script generated by MySQL Workbench
-- Ср. 01 июля 2015 00:08:33
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema railage
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema railage
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `railage` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `railage` ;

-- -----------------------------------------------------
-- Table `railage`.`Stations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railage`.`Stations` ;

CREATE TABLE IF NOT EXISTS `railage`.`Stations` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(100) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `railage`.`Trains`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railage`.`Trains` ;

CREATE TABLE IF NOT EXISTS `railage`.`Trains` (
  `id` INT(11) NOT NULL COMMENT '',
  `maxSeats` INT(11) NULL COMMENT '',
  `number` VARCHAR(50) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `railage`.`TimeInfos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railage`.`TimeInfos` ;

CREATE TABLE IF NOT EXISTS `railage`.`TimeInfos` (
  `id` INT(11) NOT NULL COMMENT '',
  `period` VARCHAR(50) NULL COMMENT '',
  `periodic` TINYINT(1) NULL COMMENT '',
  `arrivalTime` TIME NULL COMMENT '',
  `departureTime` TIME NULL COMMENT '',
  `fixedDate` DATE NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `railage`.`Routes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railage`.`Routes` ;

CREATE TABLE IF NOT EXISTS `railage`.`Routes` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `trainId` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_trainId_idx` (`trainId` ASC)  COMMENT '',
  CONSTRAINT `fk_route_trainId`
    FOREIGN KEY (`trainId`)
    REFERENCES `railage`.`Trains` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `railage`.`TimeTableLines`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railage`.`TimeTableLines` ;

CREATE TABLE IF NOT EXISTS `railage`.`TimeTableLines` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `trainId` INT(11) NOT NULL COMMENT '',
  `stationId` INT(11) NOT NULL COMMENT '',
  `timeInfoId` INT(11) NOT NULL COMMENT '',
  `routeId` INT(11) NOT NULL COMMENT '',
  INDEX `fk_trainId_idx` (`trainId` ASC)  COMMENT '',
  INDEX `fk_TimeInfoId_idx` (`timeInfoId` ASC)  COMMENT '',
  INDEX `fk_stationId_idx` (`stationId` ASC)  COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_tt_routeId_idx` (`routeId` ASC)  COMMENT '',
  CONSTRAINT `fk_stationId`
    FOREIGN KEY (`stationId`)
    REFERENCES `railage`.`Stations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trainId`
    FOREIGN KEY (`trainId`)
    REFERENCES `railage`.`Trains` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TimeInfoId`
    FOREIGN KEY (`timeInfoId`)
    REFERENCES `railage`.`TimeInfos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tt_routeId`
    FOREIGN KEY (`routeId`)
    REFERENCES `railage`.`Routes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `railage`.`Passengers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railage`.`Passengers` ;

CREATE TABLE IF NOT EXISTS `railage`.`Passengers` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(100) NOT NULL COMMENT '',
  `lastname` VARCHAR(100) NOT NULL COMMENT '',
  `birthDate` DATE NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `railage`.`TrainRides`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railage`.`TrainRides` ;

CREATE TABLE IF NOT EXISTS `railage`.`TrainRides` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `trainId` INT(11) NOT NULL COMMENT '',
  `routeId` INT(11) NOT NULL COMMENT '',
  `rideDate` DATE NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_trainId_idx` (`trainId` ASC)  COMMENT '',
  INDEX `fk_routeId_idx` (`routeId` ASC)  COMMENT '',
  CONSTRAINT `fk_ride_trainId`
    FOREIGN KEY (`trainId`)
    REFERENCES `railage`.`Trains` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ride_routeId`
    FOREIGN KEY (`routeId`)
    REFERENCES `railage`.`Routes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `railage`.`Tickets`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railage`.`Tickets` ;

CREATE TABLE IF NOT EXISTS `railage`.`Tickets` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `trainRideId` INT(11) NOT NULL COMMENT '',
  `passengerId` INT(11) NOT NULL COMMENT '',
  `boardingStationId` INT(11) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_passengerId_idx` (`passengerId` ASC)  COMMENT '',
  INDEX `fk_boardingStation_idx` (`boardingStationId` ASC)  COMMENT '',
  INDEX `fk_trainRideId_idx` (`trainRideId` ASC)  COMMENT '',
  CONSTRAINT `fk_trainRideId`
    FOREIGN KEY (`trainRideId`)
    REFERENCES `railage`.`TrainRides` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_passengerId`
    FOREIGN KEY (`passengerId`)
    REFERENCES `railage`.`Passengers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_boardingStation`
    FOREIGN KEY (`boardingStationId`)
    REFERENCES `railage`.`Stations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `railage`.`RouteParts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railage`.`RouteParts` ;

CREATE TABLE IF NOT EXISTS `railage`.`RouteParts` (
  `id` INT(11) NOT NULL COMMENT '',
  `routeId` INT(11) NOT NULL COMMENT '',
  `stationId` INT(11) NOT NULL COMMENT '',
  `position` INT(11) NULL COMMENT '',
  `status` VARCHAR(50) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_routeId_idx` (`routeId` ASC)  COMMENT '',
  INDEX `fk_stationId_idx` (`stationId` ASC)  COMMENT '',
  CONSTRAINT `fk_route_routeId`
    FOREIGN KEY (`routeId`)
    REFERENCES `railage`.`Routes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_route_stationId`
    FOREIGN KEY (`stationId`)
    REFERENCES `railage`.`Stations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `railage`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `railage`.`Users` ;

CREATE TABLE IF NOT EXISTS `railage`.`Users` (
  `id` INT(11) NOT NULL COMMENT '',
  `login` VARCHAR(50) NULL COMMENT '',
  `password` VARCHAR(50) NULL COMMENT '',
  `role` VARCHAR(50) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `railage`.`Stations`
-- -----------------------------------------------------
START TRANSACTION;
USE `railage`;
INSERT INTO `railage`.`Stations` (`id`, `name`) VALUES (1, 'SpbFinRailwayStation');
INSERT INTO `railage`.`Stations` (`id`, `name`) VALUES (2, 'Kushelevka');
INSERT INTO `railage`.`Stations` (`id`, `name`) VALUES (3, 'Piskarevka');
INSERT INTO `railage`.`Stations` (`id`, `name`) VALUES (4, 'Ruchii');
INSERT INTO `railage`.`Stations` (`id`, `name`) VALUES (5, 'Devyatkino');
INSERT INTO `railage`.`Stations` (`id`, `name`) VALUES (6, 'Lavriki');
INSERT INTO `railage`.`Stations` (`id`, `name`) VALUES (7, 'Kuzmolovo');
INSERT INTO `railage`.`Stations` (`id`, `name`) VALUES (8, 'Kapitolovo');
INSERT INTO `railage`.`Stations` (`id`, `name`) VALUES (9, 'Toksovo');
INSERT INTO `railage`.`Stations` (`id`, `name`) VALUES (10, 'Kavgolovo');
INSERT INTO `railage`.`Stations` (`id`, `name`) VALUES (11, 'Peri');
INSERT INTO `railage`.`Stations` (`id`, `name`) VALUES (12, 'Gruzino');
INSERT INTO `railage`.`Stations` (`id`, `name`) VALUES (13, 'Vaskelovo');
INSERT INTO `railage`.`Stations` (`id`, `name`) VALUES (14, 'Lembolovo');
INSERT INTO `railage`.`Stations` (`id`, `name`) VALUES (15, 'Orehovo');
INSERT INTO `railage`.`Stations` (`id`, `name`) VALUES (16, 'Sosnovo');

COMMIT;


-- -----------------------------------------------------
-- Data for table `railage`.`Trains`
-- -----------------------------------------------------
START TRANSACTION;
USE `railage`;
INSERT INTO `railage`.`Trains` (`id`, `maxSeats`, `number`) VALUES (1, 100, 'AA22BB77');
INSERT INTO `railage`.`Trains` (`id`, `maxSeats`, `number`) VALUES (2, 120, 'BB78CC90');
INSERT INTO `railage`.`Trains` (`id`, `maxSeats`, `number`) VALUES (3, 40, 'ZZ88FF67');
INSERT INTO `railage`.`Trains` (`id`, `maxSeats`, `number`) VALUES (4, 60, 'KK54OP91');

COMMIT;


-- -----------------------------------------------------
-- Data for table `railage`.`TimeInfos`
-- -----------------------------------------------------
START TRANSACTION;
USE `railage`;
INSERT INTO `railage`.`TimeInfos` (`id`, `period`, `periodic`, `arrivalTime`, `departureTime`, `fixedDate`) VALUES (1, 'EVERYDAY', TRUE, NULL, '10:00:00', NULL);
INSERT INTO `railage`.`TimeInfos` (`id`, `period`, `periodic`, `arrivalTime`, `departureTime`, `fixedDate`) VALUES (2, 'EVERYDAY', TRUE, NULL, '11:00:00', NULL);
INSERT INTO `railage`.`TimeInfos` (`id`, `period`, `periodic`, `arrivalTime`, `departureTime`, `fixedDate`) VALUES (3, 'EVERYDAY', TRUE, NULL, '12:00:00', NULL);
INSERT INTO `railage`.`TimeInfos` (`id`, `period`, `periodic`, `arrivalTime`, `departureTime`, `fixedDate`) VALUES (4, 'EVERYDAY', TRUE, NULL, '13:00:00', NULL);
INSERT INTO `railage`.`TimeInfos` (`id`, `period`, `periodic`, `arrivalTime`, `departureTime`, `fixedDate`) VALUES (5, 'EVERYDAY', TRUE, NULL, '14:00:00', NULL);
INSERT INTO `railage`.`TimeInfos` (`id`, `period`, `periodic`, `arrivalTime`, `departureTime`, `fixedDate`) VALUES (6, 'EVERYDAY', TRUE, NULL, '15:00:00', NULL);
INSERT INTO `railage`.`TimeInfos` (`id`, `period`, `periodic`, `arrivalTime`, `departureTime`, `fixedDate`) VALUES (7, 'EVERYDAY', TRUE, NULL, '16:00:00', NULL);
INSERT INTO `railage`.`TimeInfos` (`id`, `period`, `periodic`, `arrivalTime`, `departureTime`, `fixedDate`) VALUES (8, 'EVERYDAY', TRUE, NULL, '17:00:00', NULL);
INSERT INTO `railage`.`TimeInfos` (`id`, `period`, `periodic`, `arrivalTime`, `departureTime`, `fixedDate`) VALUES (9, 'EVERYDAY', TRUE, NULL, '18:00:00', NULL);
INSERT INTO `railage`.`TimeInfos` (`id`, `period`, `periodic`, `arrivalTime`, `departureTime`, `fixedDate`) VALUES (10, 'EVERYDAY', TRUE, NULL, '19:00:00', NULL);
INSERT INTO `railage`.`TimeInfos` (`id`, `period`, `periodic`, `arrivalTime`, `departureTime`, `fixedDate`) VALUES (11, 'EVERYDAY', TRUE, NULL, '20:00:00', NULL);
INSERT INTO `railage`.`TimeInfos` (`id`, `period`, `periodic`, `arrivalTime`, `departureTime`, `fixedDate`) VALUES (12, 'EVERYDAY', TRUE, NULL, '21:00:00', NULL);
INSERT INTO `railage`.`TimeInfos` (`id`, `period`, `periodic`, `arrivalTime`, `departureTime`, `fixedDate`) VALUES (13, 'EVERYDAY', TRUE, NULL, '22:00:00', NULL);
INSERT INTO `railage`.`TimeInfos` (`id`, `period`, `periodic`, `arrivalTime`, `departureTime`, `fixedDate`) VALUES (14, 'EVERYDAY', TRUE, NULL, '23:00:00', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `railage`.`Routes`
-- -----------------------------------------------------
START TRANSACTION;
USE `railage`;
INSERT INTO `railage`.`Routes` (`id`, `trainId`) VALUES (1, 1);
INSERT INTO `railage`.`Routes` (`id`, `trainId`) VALUES (2, 1);
INSERT INTO `railage`.`Routes` (`id`, `trainId`) VALUES (3, 2);
INSERT INTO `railage`.`Routes` (`id`, `trainId`) VALUES (4, 2);
INSERT INTO `railage`.`Routes` (`id`, `trainId`) VALUES (5, 3);
INSERT INTO `railage`.`Routes` (`id`, `trainId`) VALUES (6, 3);
INSERT INTO `railage`.`Routes` (`id`, `trainId`) VALUES (7, 4);
INSERT INTO `railage`.`Routes` (`id`, `trainId`) VALUES (8, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `railage`.`TimeTableLines`
-- -----------------------------------------------------
START TRANSACTION;
USE `railage`;
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (1, 1, 1, 1, 1);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (2, 1, 2, 2, 1);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (3, 1, 3, 3, 1);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (4, 1, 4, 4, 1);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (5, 1, 1, 10, 2);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (6, 1, 2, 11, 2);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (7, 1, 3, 12, 2);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (8, 1, 4, 13, 2);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (9, 1, 5, 14, 2);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (10, 2, 1, 2, 3);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (11, 2, 4, 3, 3);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (12, 2, 6, 4, 3);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (13, 2, 1, 3, 4);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (14, 2, 6, 5, 4);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (15, 2, 7, 6, 4);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (16, 2, 8, 7, 4);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (17, 2, 9, 8, 4);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (18, 3, 5, 9, 5);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (19, 3, 6, 10, 5);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (20, 3, 7, 11, 5);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (21, 3, 9, 12, 5);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (22, 3, 10, 13, 5);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (23, 3, 1, 1, 6);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (24, 3, 6, 3, 6);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (25, 3, 8, 4, 6);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (26, 3, 10, 5, 6);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (27, 3, 12, 6, 6);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (28, 3, 14, 7, 6);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (29, 3, 16, 8, 6);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (30, 4, 13, 1, 7);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (31, 4, 14, 3, 7);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (32, 4, 15, 4, 7);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (33, 4, 16, 5, 7);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (34, 4, 1, 8, 8);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (35, 4, 5, 9, 8);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (36, 4, 15, 11, 8);
INSERT INTO `railage`.`TimeTableLines` (`id`, `trainId`, `stationId`, `timeInfoId`, `routeId`) VALUES (37, 4, 16, 12, 8);

COMMIT;


-- -----------------------------------------------------
-- Data for table `railage`.`Passengers`
-- -----------------------------------------------------
START TRANSACTION;
USE `railage`;
INSERT INTO `railage`.`Passengers` (`id`, `name`, `lastname`, `birthDate`) VALUES (1, 'Ivan', 'Ivanov', '1999-01-01');
INSERT INTO `railage`.`Passengers` (`id`, `name`, `lastname`, `birthDate`) VALUES (2, 'Petr', 'Petrov', '1991-02-02');
INSERT INTO `railage`.`Passengers` (`id`, `name`, `lastname`, `birthDate`) VALUES (3, 'Alex', 'Bystrov', '1990-03-03');
INSERT INTO `railage`.`Passengers` (`id`, `name`, `lastname`, `birthDate`) VALUES (4, 'Vadim', 'Sokolov', '1989-04-04');
INSERT INTO `railage`.`Passengers` (`id`, `name`, `lastname`, `birthDate`) VALUES (5, 'Victor', 'Berezkin', '1988-05-05');
INSERT INTO `railage`.`Passengers` (`id`, `name`, `lastname`, `birthDate`) VALUES (6, 'Sergey', 'Fedorov', '1987-06-06');
INSERT INTO `railage`.`Passengers` (`id`, `name`, `lastname`, `birthDate`) VALUES (7, 'Pavel', 'Karpov', '1986-07-07');
INSERT INTO `railage`.`Passengers` (`id`, `name`, `lastname`, `birthDate`) VALUES (8, 'Vladimir', 'Sergeev', '1985-08-08');

COMMIT;


-- -----------------------------------------------------
-- Data for table `railage`.`TrainRides`
-- -----------------------------------------------------
START TRANSACTION;
USE `railage`;
INSERT INTO `railage`.`TrainRides` (`id`, `trainId`, `routeId`, `rideDate`) VALUES (1, 1, 1, '2016-01-01');
INSERT INTO `railage`.`TrainRides` (`id`, `trainId`, `routeId`, `rideDate`) VALUES (2, 1, 1, '2016-02-01');
INSERT INTO `railage`.`TrainRides` (`id`, `trainId`, `routeId`, `rideDate`) VALUES (3, 1, 2, '2016-01-10');
INSERT INTO `railage`.`TrainRides` (`id`, `trainId`, `routeId`, `rideDate`) VALUES (4, 1, 2, '2016-01-11');
INSERT INTO `railage`.`TrainRides` (`id`, `trainId`, `routeId`, `rideDate`) VALUES (5, 2, 3, '2016-01-11');
INSERT INTO `railage`.`TrainRides` (`id`, `trainId`, `routeId`, `rideDate`) VALUES (6, 2, 4, '2016-01-13');
INSERT INTO `railage`.`TrainRides` (`id`, `trainId`, `routeId`, `rideDate`) VALUES (7, 3, 5, '2016-01-14');
INSERT INTO `railage`.`TrainRides` (`id`, `trainId`, `routeId`, `rideDate`) VALUES (8, 3, 6, '2016-01-15');
INSERT INTO `railage`.`TrainRides` (`id`, `trainId`, `routeId`, `rideDate`) VALUES (9, 3, 6, '2016-01-16');
INSERT INTO `railage`.`TrainRides` (`id`, `trainId`, `routeId`, `rideDate`) VALUES (10, 4, 7, '2016-01-20');
INSERT INTO `railage`.`TrainRides` (`id`, `trainId`, `routeId`, `rideDate`) VALUES (11, 4, 7, '2016-01-21');
INSERT INTO `railage`.`TrainRides` (`id`, `trainId`, `routeId`, `rideDate`) VALUES (12, 4, 8, '2016-01-22');

COMMIT;


-- -----------------------------------------------------
-- Data for table `railage`.`Tickets`
-- -----------------------------------------------------
START TRANSACTION;
USE `railage`;
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (1, 1, 1, 1);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (2, 1, 2, 2);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (3, 1, 3, 1);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (4, 2, 1, 1);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (5, 2, 2, 3);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (6, 3, 4, 2);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (7, 3, 5, 3);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (8, 4, 6, 4);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (9, 5, 1, 1);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (10, 5, 2, 1);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (11, 6, 7, 7);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (12, 6, 8, 6);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (13, 7, 5, 9);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (14, 8, 6, 8);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (15, 9, 3, 10);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (16, 10, 1, 13);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (17, 11, 5, 13);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (18, 11, 6, 13);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (19, 12, 5, 5);
INSERT INTO `railage`.`Tickets` (`id`, `trainRideId`, `passengerId`, `boardingStationId`) VALUES (20, 12, 7, 15);

COMMIT;


-- -----------------------------------------------------
-- Data for table `railage`.`RouteParts`
-- -----------------------------------------------------
START TRANSACTION;
USE `railage`;
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (1, 1, 1, 1, 'start');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (2, 1, 2, 2, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (3, 1, 3, 3, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (4, 1, 4, 4, 'end');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (5, 2, 1, 1, 'start');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (6, 2, 2, 2, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (7, 2, 3, 3, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (8, 2, 4, 4, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (9, 2, 9, 5, 'end');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (10, 3, 1, 1, 'start');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (11, 3, 4, 2, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (12, 3, 6, 3, 'end');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (13, 4, 1, 1, 'start');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (14, 4, 6, 2, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (15, 4, 7, 3, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (16, 4, 8, 4, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (17, 4, 9, 5, 'end');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (18, 5, 5, 1, 'start');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (19, 5, 6, 2, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (20, 5, 7, 3, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (21, 5, 9, 4, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (22, 5, 10, 5, 'end');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (23, 6, 1, 1, 'start');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (24, 6, 6, 2, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (25, 6, 8, 3, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (26, 6, 10, 4, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (27, 6, 12, 5, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (28, 6, 14, 6, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (29, 6, 16, 7, 'end');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (30, 7, 13, 1, 'start');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (31, 7, 14, 2, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (32, 7, 15, 3, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (33, 7, 16, 4, 'end');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (34, 8, 1, 1, 'start');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (35, 8, 5, 2, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (36, 8, 15, 3, 'stand');
INSERT INTO `railage`.`RouteParts` (`id`, `routeId`, `stationId`, `position`, `status`) VALUES (37, 8, 16, 4, 'end');

COMMIT;


-- -----------------------------------------------------
-- Data for table `railage`.`Users`
-- -----------------------------------------------------
START TRANSACTION;
USE `railage`;
INSERT INTO `railage`.`Users` (`id`, `login`, `password`, `role`) VALUES (1, 'admin', 'admin', 'EMPLOYEE');
INSERT INTO `railage`.`Users` (`id`, `login`, `password`, `role`) VALUES (2, 'client', 'client', 'CLIENT');

COMMIT;

