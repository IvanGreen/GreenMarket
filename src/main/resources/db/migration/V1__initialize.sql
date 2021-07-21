SET
FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS users;

CREATE TABLE `users`
(
    `id`         int         NOT NULL AUTO_INCREMENT,
    `username`   varchar(50) NOT NULL,
    `password`   char(80)    NOT NULL,
    `first_name` varchar(50) NOT NULL,
    `last_name`  varchar(50) NOT NULL,
    `email`      varchar(50) NOT NULL,
    `phone`      varchar(15) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS roles;

CREATE TABLE `roles`
(
    `id`   int NOT NULL AUTO_INCREMENT,
    `name` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS users_roles;

CREATE TABLE `users_roles`
(
    `user_id` int NOT NULL,
    `role_id` int NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    KEY       `FK_ROLE_ID` (`role_id`),
    CONSTRAINT `FK_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
    CONSTRAINT `FK_USER_ID_01` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS products;

CREATE TABLE `products`
(
    `id`    int         NOT NULL AUTO_INCREMENT,
    `title` varchar(45) NOT NULL,
    `price` double      NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS products_images;

CREATE TABLE `products_images`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `product_id` int          NOT NULL,
    `path`       varchar(250) NOT NULL,
    PRIMARY KEY (`id`),
    KEY          `FK_PRODUCT_ID_IMG` (`product_id`),
    CONSTRAINT `FK_PRODUCT_ID_IMG` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS delivery_addresses;

CREATE TABLE `delivery_addresses`
(
    `id`      int          NOT NULL AUTO_INCREMENT,
    `user_id` int          NOT NULL,
    `address` varchar(500) NOT NULL,
    PRIMARY KEY (`id`),
    KEY       `FK_USER_ID_DEL_ADR` (`user_id`),
    CONSTRAINT `FK_USER_ID_DEL_ADR` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS orders_statuses;

CREATE TABLE `orders_statuses`
(
    `id`    int NOT NULL AUTO_INCREMENT,
    `title` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS orders_products;

CREATE TABLE `orders_products`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `quantity`      int DEFAULT NULL,
    `product_price` blob,
    `total_price`   blob,
    `product_id`    int DEFAULT NULL,
    `order_id`      int DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY             `product_id_idx` (`product_id`),
    KEY             `order_id_idx` (`id`,`order_id`),
    KEY             `order_id_idx1` (`order_id`),
    CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
    CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS orders;

CREATE TABLE `orders`
(
    `id`                  int           NOT NULL AUTO_INCREMENT,
    `user_id`             int           NOT NULL,
    `price`               decimal(8, 2) NOT NULL,
    `delivery_price`      decimal(8, 2) NOT NULL,
    `delivery_address_id` int           NOT NULL,
    `phone_number`        varchar(20)   NOT NULL,
    `status_id`           int           NOT NULL,
    `delivery_date`       timestamp     NOT NULL,
    `create_at`           timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_at`           timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY                   `FK_USER_ID` (`user_id`),
    KEY                   `FK_STATUS_ID` (`status_id`),
    KEY                   `FK_DELIVERY_ADDRESS_ID` (`delivery_address_id`),
    CONSTRAINT `FK_DELIVERY_ADDRESS_ID` FOREIGN KEY (`delivery_address_id`) REFERENCES `delivery_addresses` (`id`),
    CONSTRAINT `FK_STATUS_ID` FOREIGN KEY (`status_id`) REFERENCES `orders_statuses` (`id`),
    CONSTRAINT `FK_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb3;

SET
FOREIGN_KEY_CHECKS = 1;

INSERT INTO roles (name)
VALUES ('ROLE_EMPLOYEE'),
       ('ROLE_MANAGER'),
       ('ROLE_ADMIN');

INSERT INTO users (username, password, first_name, last_name, email, phone)
VALUES ('admin', '$2a$10$V9o4Jw1JG1iycqSMgu5cPuvUXsu9ROyQkFAm490.M8yQGzb23ScjS', 'Admin', 'Adminovich',
        'admin@green.ru', '+79997777777'),
       ('manager', '$2a$10$V9o4Jw1JG1iycqSMgu5cPuvUXsu9ROyQkFAm490.M8yQGzb23ScjS', 'Manager', 'Managerovich',
        'manager@green.ru','+79997777777'),
       ('user', '$2a$10$V9o4Jw1JG1iycqSMgu5cPuvUXsu9ROyQkFAm490.M8yQGzb23ScjS', 'User', 'Userovich',
        'user@green.ru','+79997777777');

INSERT INTO users_roles (user_id, role_id)
VALUES (3, 1),
       (2, 2),
       (1, 3);

INSERT INTO orders_statuses (title)
VALUES ('Formed'),
       ('Submitted');

INSERT INTO delivery_addresses (`user_id`, `address`) VALUES ('3', 'Saint-P');
INSERT INTO delivery_addresses (`user_id`, `address`) VALUES ('3', 'Moscow');
