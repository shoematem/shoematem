-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema OrderBook2Test
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `OrderBook2Test` ;

-- -----------------------------------------------------
-- Schema OrderBook2Test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `OrderBook2Test` DEFAULT CHARACTER SET utf8 ;
USE `OrderBook2Test` ;

-- -----------------------------------------------------
-- Table `OrderBook2Test`.`Stock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OrderBook2Test`.`Stock` ;

CREATE TABLE IF NOT EXISTS `OrderBook2Test`.`Stock` (
  `stockId` INT NOT NULL AUTO_INCREMENT,
  `ticker` VARCHAR(5) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  `tickSize` DECIMAL(2,2) NOT NULL,
  PRIMARY KEY (`stockId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OrderBook2Test`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OrderBook2Test`.`User` ;

CREATE TABLE IF NOT EXISTS `OrderBook2Test`.`User` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `MPID` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `Admin` TINYINT NOT NULL,
  `Active` TINYINT NOT NULL,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OrderBook2Test`.`TradeType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OrderBook2Test`.`TradeType` ;

CREATE TABLE IF NOT EXISTS `OrderBook2Test`.`TradeType` (
  `tradetypeid` INT NOT NULL AUTO_INCREMENT,
  `typeName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tradetypeid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OrderBook2Test`.`Buy_Order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OrderBook2Test`.`Buy_Order` ;

CREATE TABLE IF NOT EXISTS `OrderBook2Test`.`Buy_Order` (
  `orderId` INT NOT NULL AUTO_INCREMENT,
  `number` INT NOT NULL,
  `price` DECIMAL(6,2) NULL,
  `Stock_stockId` INT NOT NULL,
  `User_userId` INT NOT NULL,
  `TradeType_tradetypeid` INT NOT NULL,
  `outstanding` INT NOT NULL,
  PRIMARY KEY (`orderId`),
  INDEX `fk_Order_Stock_idx` (`Stock_stockId` ASC) VISIBLE,
  INDEX `fk_Buy_Order_User1_idx` (`User_userId` ASC) VISIBLE,
  INDEX `fk_Buy_Order_TradeType1_idx` (`TradeType_tradetypeid` ASC) VISIBLE,
  CONSTRAINT `fk_Order_Stock`
    FOREIGN KEY (`Stock_stockId`)
    REFERENCES `OrderBook2Test`.`Stock` (`stockId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Buy_Order_User1`
    FOREIGN KEY (`User_userId`)
    REFERENCES `OrderBook2Test`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Buy_Order_TradeType1`
    FOREIGN KEY (`TradeType_tradetypeid`)
    REFERENCES `OrderBook2Test`.`TradeType` (`tradetypeid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OrderBook2Test`.`Sell_Order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OrderBook2Test`.`Sell_Order` ;

CREATE TABLE IF NOT EXISTS `OrderBook2Test`.`Sell_Order` (
  `orderId` INT NOT NULL AUTO_INCREMENT,
  `number` INT NOT NULL,
  `price` DECIMAL(6,2) NULL,
  `Stock_stockId` INT NOT NULL,
  `User_userId` INT NOT NULL,
  `TradeType_tradetypeid` INT NOT NULL,
  `outstanding` INT NOT NULL,
  PRIMARY KEY (`orderId`),
  INDEX `fk_Order_Stock_idx` (`Stock_stockId` ASC) VISIBLE,
  INDEX `fk_Sell_Order_User1_idx` (`User_userId` ASC) VISIBLE,
  INDEX `fk_Sell_Order_TradeType1_idx` (`TradeType_tradetypeid` ASC) VISIBLE,
  CONSTRAINT `fk_Order_Stock0`
    FOREIGN KEY (`Stock_stockId`)
    REFERENCES `OrderBook2Test`.`Stock` (`stockId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sell_Order_User1`
    FOREIGN KEY (`User_userId`)
    REFERENCES `OrderBook2Test`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sell_Order_TradeType1`
    FOREIGN KEY (`TradeType_tradetypeid`)
    REFERENCES `OrderBook2Test`.`TradeType` (`tradetypeid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OrderBook2Test`.`Trade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OrderBook2Test`.`Trade` ;

CREATE TABLE IF NOT EXISTS `OrderBook2Test`.`Trade` (
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
    REFERENCES `OrderBook2Test`.`Buy_Order` (`orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Trade_Sell_Order1`
    FOREIGN KEY (`Sell_Order_orderId`)
    REFERENCES `OrderBook2Test`.`Sell_Order` (`orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO tradetype (typename) values
('Market');

INSERT INTO tradetype (typename) values
('Limit');

INSERT INTO tradetype (typename) values
('Stop');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
