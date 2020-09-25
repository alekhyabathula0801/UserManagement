-- create user management schema 
CREATE SCHEMA `user_management` ;

-- create user details table
CREATE TABLE `user_management`.`user_details` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(20) NOT NULL,
  `middle_name` VARCHAR(25) NULL,
  `last_name` VARCHAR(25) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `country` VARCHAR(20) NOT NULL,
  `country_code` VARCHAR(7) NOT NULL,
  `phone` BIGINT NOT NULL,
  `address` VARCHAR(70) NULL,
  `password` VARCHAR(45) NOT NULL,
  `user_role` VARCHAR(10) NOT NULL,
  `creator_stamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `creator_user` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC) VISIBLE);
  
ALTER TABLE `user_management`.`user_details` 
ADD COLUMN `user_profile_image` MEDIUMBLOB NOT NULL AFTER `user_role`;

ALTER TABLE `user_management`.`user_details` 
ADD COLUMN `updated_user` VARCHAR(25) NULL AFTER `creator_user`,
ADD COLUMN `updated_time` TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP AFTER `updated_user`;
  
ALTER TABLE `user_management`.`user_details` 
ADD COLUMN `status` VARCHAR(10) NOT NULL DEFAULT 'Active' AFTER `user_profile_image`;

-- create pages table
  CREATE TABLE `user_management`.`pages` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `page_name` VARCHAR(25) NOT NULL,
  `creator_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));
  
-- create permissions table
CREATE TABLE `user_management`.`user_permissions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `page_id` INT NOT NULL,
  `add` TINYINT NOT NULL DEFAULT 0,
  `delete` TINYINT NOT NULL DEFAULT 0,
  `modify` TINYINT NOT NULL DEFAULT 0,
  `read` TINYINT NOT NULL DEFAULT 0,
  `creator_stamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `creator_user` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_user_permissions_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_page_id_user_permissions_idx` (`page_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_id_user_permissions`
    FOREIGN KEY (`user_id`)
    REFERENCES `user_management`.`user_details` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_page_id_user_permissions`
    FOREIGN KEY (`page_id`)
    REFERENCES `user_management`.`pages` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `user_management`.`user_permissions` 
ADD COLUMN `updated_user` VARCHAR(25) NULL AFTER `creator_user`,
ADD COLUMN `updated_stamp` TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP AFTER `updated_user`;

-- create user login details table
CREATE TABLE `user_management`.`user_login_details` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `is_login` TINYINT NOT NULL DEFAULT 0,
  `last_login_date_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `cretor_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_user_login_details_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_id_user_login_details`
    FOREIGN KEY (`user_id`)
    REFERENCES `user_management`.`user_details` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
ALTER TABLE `user_management`.`user_login_details` 
	ADD COLUMN `number_of_login_attempts` INT NOT NULL DEFAULT 0 AFTER `last_login_date_time`;

-- create stored procedure to update user status
USE `user_management`;
DROP procedure IF EXISTS `updateUserStatus`;

DELIMITER $$
USE `user_management`$$
CREATE PROCEDURE `updateUserStatus`()
BEGIN
	UPDATE user_login_details SET number_of_login_attempts = 0 WHERE number_of_login_attempts>0;
	UPDATE user_details SET status = "Active" WHERE status = "Inactive";
END$$
DELIMITER ;

-- create event to update user status at 1 AM daily
CREATE EVENT IF NOT EXISTS update_user_status
ON SCHEDULE EVERY 1 DAY_HOUR
ON COMPLETION PRESERVE
DO
CALL updateUserStatus();
