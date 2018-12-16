-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema linkdata
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema linkdata
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `linkdata` DEFAULT CHARACTER SET utf8 ;
USE `linkdata` ;

-- -----------------------------------------------------
-- Table `linkdata`.`Empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linkdata`.`Empresa` (
  `codigo` INT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `cnpj` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `linkdata`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linkdata`.`Funcionario` (
  `codigo` INT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `Empresa_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_Funcionario_Empresa_idx` (`Empresa_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_Funcionario_Empresa`
    FOREIGN KEY (`Empresa_codigo`)
    REFERENCES `linkdata`.`Empresa` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
