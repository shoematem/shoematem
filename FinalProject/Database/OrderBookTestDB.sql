-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema OrderBook
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `OrderBookTest` ;

-- -----------------------------------------------------
-- Schema OrderBook
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `OrderBookTest` DEFAULT CHARACTER SET utf8 ;
USE `OrderBookTest` ;

-- -----------------------------------------------------
-- Table `OrderBook`.`Stock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OrderBookTest`.`Stock` ;

CREATE TABLE IF NOT EXISTS `OrderBookTest`.`Stock` (
  `stockId` INT NOT NULL AUTO_INCREMENT,
  `ticker` VARCHAR(5) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  `tickSize` DECIMAL(2,2) NOT NULL,
  PRIMARY KEY (`stockId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OrderBook`.`Buy_Order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OrderBookTest`.`Buy_Order` ;

CREATE TABLE IF NOT EXISTS `OrderBookTest`.`Buy_Order` (
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
    REFERENCES `OrderBookTest`.`Stock` (`stockId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OrderBook`.`Sell_Order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OrderBookTest`.`Sell_Order` ;

CREATE TABLE IF NOT EXISTS `OrderBookTest`.`Sell_Order` (
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
    REFERENCES `OrderBookTest`.`Stock` (`stockId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OrderBook`.`Trade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OrderBookTest`.`Trade` ;

CREATE TABLE IF NOT EXISTS `OrderBookTest`.`Trade` (
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
    REFERENCES `OrderBookTest`.`Buy_Order` (`orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Trade_Sell_Order1`
    FOREIGN KEY (`Sell_Order_orderId`)
    REFERENCES `OrderBookTest`.`Sell_Order` (`orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
