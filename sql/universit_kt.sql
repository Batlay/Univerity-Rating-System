-- MySQL Script generated by MySQL Workbench
-- Wed Nov 24 21:47:46 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema univers
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema univers
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `univers` DEFAULT CHARACTER SET utf8 COLLATE utf8_danish_ci ;
USE `univers` ;

-- -----------------------------------------------------
-- Table `univers`.`faculty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `univers`.`faculty` (
  `code_faculty` VARCHAR(45) NOT NULL,
  `faculty_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`code_faculty`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_danish_ci;


-- -----------------------------------------------------
-- Table `univers`.`spec`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `univers`.`spec` (
  `code_spec` VARCHAR(45) NOT NULL,
  `spec_name` VARCHAR(45) NOT NULL,
  `code_faculty` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`code_spec`),
  INDEX `faculty_spec_idx` (`code_faculty` ASC) VISIBLE,
  CONSTRAINT `faculty_spec`
    FOREIGN KEY (`code_faculty`)
    REFERENCES `univers`.`faculty` (`code_faculty`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_danish_ci;


-- -----------------------------------------------------
-- Table `univers`.`semester`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `univers`.`semester` (
  `id_semester` INT NOT NULL,
  PRIMARY KEY (`id_semester`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `univers`.`year`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `univers`.`year` (
  `code_year` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`code_year`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `univers`.`groupa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `univers`.`groupa` (
  `id_group` VARCHAR(45) NOT NULL,
  `code_spec` VARCHAR(45) NOT NULL,
  `id_semester` INT NOT NULL,
  `code_year` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_group`),
  INDEX `spec_idx` (`code_spec` ASC) VISIBLE,
  INDEX `sem_idx` (`id_semester` ASC) VISIBLE,
  INDEX `year_idx` (`code_year` ASC) VISIBLE,
  CONSTRAINT `spec`
    FOREIGN KEY (`code_spec`)
    REFERENCES `univers`.`spec` (`code_spec`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `sem`
    FOREIGN KEY (`id_semester`)
    REFERENCES `univers`.`semester` (`id_semester`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `year`
    FOREIGN KEY (`code_year`)
    REFERENCES `univers`.`year` (`code_year`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_danish_ci;


-- -----------------------------------------------------
-- Table `univers`.`Student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `univers`.`Student` (
  `id_student` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `middle_name` VARCHAR(45) NULL,
  `id_group` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id_student`),
  INDEX `group_idx` (`id_group` ASC) VISIBLE,
  UNIQUE INDEX `id_student_UNIQUE` (`id_student` ASC) VISIBLE,
  CONSTRAINT `group`
    FOREIGN KEY (`id_group`)
    REFERENCES `univers`.`groupa` (`id_group`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_danish_ci;


-- -----------------------------------------------------
-- Table `univers`.`subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `univers`.`subject` (
  `code_subject` VARCHAR(45) NOT NULL,
  `subject_name` VARCHAR(45) NOT NULL,
  `code_spec` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`code_subject`),
  INDEX `sub_spec_idx` (`code_spec` ASC) VISIBLE,
  CONSTRAINT `sub_spec`
    FOREIGN KEY (`code_spec`)
    REFERENCES `univers`.`spec` (`code_spec`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_danish_ci;


-- -----------------------------------------------------
-- Table `univers`.`Administator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `univers`.`Administator` (
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `univers`.`Teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `univers`.`Teacher` (
  `id_teacher` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id_teacher`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `univers`.`study_plan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `univers`.`study_plan` (
  `id_study_plan` VARCHAR(45) NOT NULL,
  `id_teacher` VARCHAR(45) NOT NULL,
  `code_subject` VARCHAR(45) NOT NULL,
  `id_semester` INT NOT NULL,
  `form` VARCHAR(45) NULL,
  `id_group` VARCHAR(45) NOT NULL,
  `hours` VARCHAR(45) NULL,
  PRIMARY KEY (`id_study_plan`),
  INDEX `teach_idx` (`id_teacher` ASC) VISIBLE,
  INDEX `subject_plan_idx` (`code_subject` ASC) VISIBLE,
  INDEX `group_idx` (`id_group` ASC) VISIBLE,
  CONSTRAINT `teach`
    FOREIGN KEY (`id_teacher`)
    REFERENCES `univers`.`Teacher` (`id_teacher`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `subject_plan`
    FOREIGN KEY (`code_subject`)
    REFERENCES `univers`.`subject` (`code_subject`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `gruppa`
    FOREIGN KEY (`id_group`)
    REFERENCES `univers`.`groupa` (`id_group`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `univers`.`kt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `univers`.`kt` (
  `kt_id` INT NOT NULL,
  `date` DATE NULL,
  PRIMARY KEY (`kt_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `univers`.`mark1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `univers`.`mark1` (
  `kt_id` INT NULL,
  `mark1` VARCHAR(45) NULL,
  `mark2` VARCHAR(45) NULL,
  `id_student` VARCHAR(45) NULL,
  `id_study_plan` VARCHAR(45) NULL,
  INDEX `stud_idx` (`id_student` ASC) VISIBLE,
  INDEX `sp_idx` (`id_study_plan` ASC) VISIBLE,
  INDEX `kt2_idx` (`kt_id` ASC) VISIBLE,
  CONSTRAINT `stud`
    FOREIGN KEY (`id_student`)
    REFERENCES `univers`.`Student` (`id_student`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `sp`
    FOREIGN KEY (`id_study_plan`)
    REFERENCES `univers`.`study_plan` (`id_study_plan`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `kt2`
    FOREIGN KEY (`kt_id`)
    REFERENCES `univers`.`kt` (`kt_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `univers`.`mark2`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `univers`.`mark2` (
  `kt_id` INT NULL,
  `mark3` VARCHAR(45) NULL,
  `mark4` VARCHAR(45) NULL,
  `id_student` VARCHAR(45) NULL,
  `id_study_plan` VARCHAR(45) NULL,
  INDEX `stud1_idx` (`id_student` ASC) VISIBLE,
  INDEX `sp1_idx` (`id_study_plan` ASC) VISIBLE,
  INDEX `kt1_idx` (`kt_id` ASC) VISIBLE,
  CONSTRAINT `stud1`
    FOREIGN KEY (`id_student`)
    REFERENCES `univers`.`Student` (`id_student`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `sp1`
    FOREIGN KEY (`id_study_plan`)
    REFERENCES `univers`.`study_plan` (`id_study_plan`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `kt1`
    FOREIGN KEY (`kt_id`)
    REFERENCES `univers`.`kt` (`kt_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `univers`.`mark3`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `univers`.`mark3` (
  `kt_id` INT NULL,
  `mark5` VARCHAR(45) NULL,
  `mark6` VARCHAR(45) NULL,
  `id_student` VARCHAR(45) NULL,
  `id_study_plan` VARCHAR(45) NULL,
  INDEX `stud2_idx` (`id_student` ASC) VISIBLE,
  INDEX `sp2_idx` (`id_study_plan` ASC) VISIBLE,
  INDEX `kt_idx` (`kt_id` ASC) VISIBLE,
  CONSTRAINT `stud2`
    FOREIGN KEY (`id_student`)
    REFERENCES `univers`.`Student` (`id_student`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `sp2`
    FOREIGN KEY (`id_study_plan`)
    REFERENCES `univers`.`study_plan` (`id_study_plan`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `kt`
    FOREIGN KEY (`kt_id`)
    REFERENCES `univers`.`kt` (`kt_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
