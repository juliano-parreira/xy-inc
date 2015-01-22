-- MySQL Script
-- Tue Jan 20 15:57:42 2015
-- Model: ZUP XY-INC DB    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema xyincdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `xyincdb` ;

-- -----------------------------------------------------
-- Schema xyincdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `xyincdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
SHOW WARNINGS;
USE `xyincdb` ;

-- -----------------------------------------------------
-- Table `xyincdb`.`point_of_interest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `xyincdb`.`point_of_interest` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `xyincdb`.`point_of_interest` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `position_x` INT NOT NULL,
  `position_y` INT NOT NULL,
  `create_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
