-- MySQL Script generated by MySQL Workbench
-- Пн. 27 июля 2015 19:12:46
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
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `login` VARCHAR(50) NULL COMMENT '',
  `password` VARCHAR(50) NULL COMMENT '',
  `role` VARCHAR(50) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
