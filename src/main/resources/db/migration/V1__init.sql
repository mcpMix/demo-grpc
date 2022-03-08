CREATE TABLE PRODUCT (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    quantity_in_stock INTEGER NOT NULL,
   `created_at` DATETIME NOT NULL,
   `updated_at` DATETIME NOT NULL,
   `version` INT(11) NULL,
    CONSTRAINT id UNIQUE (id)
);

INSERT INTO product (id, name, price, quantity_in_stock,created_at,updated_at,version) VALUES (1, 'Product A', 10.99, 10,NOW(),NOW(),0);
INSERT INTO product (id, name, price, quantity_in_stock,created_at,updated_at,version) VALUES (2, 'Product B', 10.99, 10,NOW(),NOW(),0);