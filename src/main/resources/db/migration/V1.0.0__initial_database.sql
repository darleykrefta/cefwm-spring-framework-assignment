CREATE TABLE IF NOT EXISTS `delivery`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uuid` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `phone` VARCHAR(20) NULL,
  `email` VARCHAR(100) NULL,
  PRIMARY KEY (`id`)) ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `delivery`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uuid` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `price` DOUBLE NULL,
  PRIMARY KEY (`id`)) ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `delivery`.`restaurant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uuid` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `taxa_frete` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)) ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `delivery`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uuid` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `username` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(100) NULL,
  `status` INT NULL,
  `restaurant` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_restaurant_user`
    FOREIGN KEY (`restaurant`)
    REFERENCES `delivery`.`restaurant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION) ENGINE = InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX `fk_restaurant_user_idx` ON `delivery`.`user` (`restaurant` ASC);

CREATE TABLE IF NOT EXISTS `delivery`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uuid` VARCHAR(45) NULL,
  `restaurant` INT NULL,
  `client` INT NULL,
  `user` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_restaurant_order`
    FOREIGN KEY (`restaurant`)
    REFERENCES `delivery`.`restaurant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_client_order`
    FOREIGN KEY (`client`)
    REFERENCES `delivery`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_order`
    FOREIGN KEY (`user`)
    REFERENCES `delivery`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION) ENGINE = InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX `fk_restaurant_order_idx` ON `delivery`.`orders` (`restaurant` ASC);
CREATE INDEX `fk_restaurant_client_idx` ON `delivery`.`orders` (`client` ASC);
CREATE INDEX `fk_user_order_idx` ON `delivery`.`orders` (`user` ASC);

CREATE TABLE IF NOT EXISTS `delivery`.`order_item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uuid` VARCHAR(45) NULL,
  `product` INT NULL,
  `quantity` INT NULL,
  `order_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_order_order_item`
    FOREIGN KEY (`order_id`)
    REFERENCES `delivery`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_order_item`
    FOREIGN KEY (`product`)
    REFERENCES `delivery`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION) ENGINE = InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX `fk_order_order_item_idx` ON `delivery`.`order_item` (`order_id` ASC);
CREATE INDEX `fk_product_order_item_idx` ON `delivery`.`order_item` (`product` ASC);



INSERT INTO `delivery`.`restaurant` (`id`, `name`, `taxa_frete`, `uuid`) VALUES ('1', 'Restaurante do User', '10', '00b8fd02-eba0-48ed-8806-8d88176f9c42');

INSERT INTO `delivery`.`user` (`id`, `email`, `name`, `password`, `status`, `username`, `uuid`, `restaurant`) VALUES ('1', 'user@user.com', 'User', '$2a$12$3GOp.wck4WC52Fuj9Pj8UOk/wkY53p2f5oOfL80y9a8dbUNY2v1sq', 1, 'user', '7e9779f6-15da-4457-b7ee-e026329cb605', 1);
