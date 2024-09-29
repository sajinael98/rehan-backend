CREATE Table IF NOT EXISTS `sysusers` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_Name VARCHAR(25),
    last_Name VARCHAR(25),
    email VARCHAR(255) UNIQUE,
    password TEXT,
    username TEXT
);

CREATE Table IF NOT EXISTS `roles` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    `enabled` TINYINT(1),
    role VARCHAR(25)
);

CREATE Table IF NOT EXISTS `role_assignments` (
    user_id BIGINT,
    role_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES `sysusers` (id),
    FOREIGN KEY (role_id) REFERENCES `roles` (id)
);

CREATE Table IF NOT EXISTS `permissions` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    entity VARCHAR(25),
    `createR` TINYINT(1),
    `readR` TINYINT(1),
    `editR` TINYINT(1),
    `deleteR` TINYINT(1),
    role_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES `roles` (id)
);

CREATE Table IF NOT EXISTS `tokens` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    token TEXT,
    expired TINYINT(1),
    revoked TINYINT(1),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES `sysusers` (id)
);

CREATE TABLE files (
    id SERIAL PRIMARY KEY,
    file_name VARCHAR(255) NOT NULL UNIQUE,
    file_type VARCHAR(50) NOT NULL,
    file_size BIGINT NOT NULL,
    upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `categories` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(25),
    `enabled` TINYINT(1)
);

CREATE TABLE `items` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(25) NOT NULL,
    description TEXT,
    image VARCHAR(50) NOT NULL,
    category_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES `categories` (id)
);

CREATE TABLE `item_modifiers` (
    item_id BIGINT NOT NULL,
    modifier VARCHAR(25) NOT NULL,
    PRIMARY KEY (item_id, modifier),
    FOREIGN KEY (item_id) REFERENCES `items` (id)
);

CREATE TABLE `item_sizes`(
    item_id BIGINT NOT NULL,
    `size` VARCHAR(25) NOT NULL,
    price FLOAT NOT NULL,
    PRIMARY KEY (item_id, `size`),
    FOREIGN KEY (item_id) REFERENCES `items` (id)
);