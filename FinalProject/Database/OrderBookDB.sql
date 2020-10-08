-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema OrderBook
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `OrderBook` ;

-- -----------------------------------------------------
-- Schema OrderBook
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `OrderBook` DEFAULT CHARACTER SET utf8 ;
USE `OrderBook` ;

-- -----------------------------------------------------
-- Table `OrderBook`.`Stock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OrderBook`.`Stock` ;

CREATE TABLE IF NOT EXISTS `OrderBook`.`Stock` (
  `stockId` INT NOT NULL AUTO_INCREMENT,
  `ticker` VARCHAR(5) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  `tickSize` DECIMAL(2,2) NOT NULL,
  PRIMARY KEY (`stockId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OrderBook`.`Buy_Order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OrderBook`.`Buy_Order` ;

CREATE TABLE IF NOT EXISTS `OrderBook`.`Buy_Order` (
  `orderId` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `number` INT NOT NULL,
  `price` DECIMAL(6,2) NULL,
  `MPID` VARCHAR(45) NOT NULL,
  `fulfulled` TINYINT NOT NULL,
  `Stock_stockId` INT NOT NULL,
  PRIMARY KEY (`orderId`),
  INDEX `fk_Order_Stock_idx` (`Stock_stockId` ASC) VISIBLE,
  CONSTRAINT `fk_Order_Stock`
    FOREIGN KEY (`Stock_stockId`)
    REFERENCES `OrderBook`.`Stock` (`stockId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OrderBook`.`Sell_Order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OrderBook`.`Sell_Order` ;

CREATE TABLE IF NOT EXISTS `OrderBook`.`Sell_Order` (
  `orderId` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `number` INT NOT NULL,
  `price` DECIMAL(6,2) NULL,
  `MPID` VARCHAR(45) NOT NULL,
  `fulfulled` TINYINT NOT NULL,
  `Stock_stockId` INT NOT NULL,
  PRIMARY KEY (`orderId`),
  INDEX `fk_Order_Stock_idx` (`Stock_stockId` ASC) VISIBLE,
  CONSTRAINT `fk_Order_Stock0`
    FOREIGN KEY (`Stock_stockId`)
    REFERENCES `OrderBook`.`Stock` (`stockId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OrderBook`.`Trade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OrderBook`.`Trade` ;

CREATE TABLE IF NOT EXISTS `OrderBook`.`Trade` (
  `tradeId` INT NOT NULL AUTO_INCREMENT,
  `time` DATETIME NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  `Buy_Order_orderId` INT NOT NULL,
  `Sell_Order_orderId` INT NOT NULL,
  `number` INT NOT NULL,
  PRIMARY KEY (`tradeId`),
  INDEX `fk_Trade_Order1_idx` (`Buy_Order_orderId` ASC) VISIBLE,
  INDEX `fk_Trade_Sell_Order1_idx` (`Sell_Order_orderId` ASC) VISIBLE,
  CONSTRAINT `fk_Trade_Order1`
    FOREIGN KEY (`Buy_Order_orderId`)
    REFERENCES `OrderBook`.`Buy_Order` (`orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Trade_Sell_Order1`
    FOREIGN KEY (`Sell_Order_orderId`)
    REFERENCES `OrderBook`.`Sell_Order` (`orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
