/**
  Mysql script for db creation
 */

CREATE TABLE IF NOT EXISTS accounts
(
  id       INT AUTO_INCREMENT
    PRIMARY KEY,
  username VARCHAR(30)            NOT NULL,
  email    VARCHAR(30)            NOT NULL,
  is_admin TINYINT(1) DEFAULT '0' NOT NULL,
  password VARCHAR(64)            NOT NULL,
  CONSTRAINT accounts_id_uindex
  UNIQUE (id),
  CONSTRAINT accounts_username_uindex
  UNIQUE (username),
  CONSTRAINT accounts_email_uindex
  UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS cars
(
  id    INT AUTO_INCREMENT
    PRIMARY KEY,
  model VARCHAR(30) NOT NULL,
  img   VARCHAR(32) NULL,
  price DECIMAL     NOT NULL,
  CONSTRAINT cars_id_uindex
  UNIQUE (id)
);

CREATE TABLE IF NOT EXISTS terms
(
  id       INT AUTO_INCREMENT
    PRIMARY KEY,
  pick_up  DATE NOT NULL,
  drop_off DATE NOT NULL,
  car_fk   INT  NOT NULL,
  CONSTRAINT terms_id_uindex
  UNIQUE (id),
  CONSTRAINT car_fk
  FOREIGN KEY (car_fk) REFERENCES cars (id)
    ON DELETE CASCADE
);

CREATE INDEX car_fk
  ON terms (car_fk);

CREATE TABLE IF NOT EXISTS clients
(
  id       INT AUTO_INCREMENT
    PRIMARY KEY,
  name     VARCHAR(30) NOT NULL,
  email    VARCHAR(30) NOT NULL,
  passport VARCHAR(10) NOT NULL,
  CONSTRAINT clients_id_uindex
  UNIQUE (id),
  CONSTRAINT clients_passport_uindex
  UNIQUE (passport)
);

CREATE TABLE IF NOT EXISTS credit_cards
(
  id          INT AUTO_INCREMENT
    PRIMARY KEY,
  card_number VARCHAR(18) NOT NULL,
  expires     DATE        NOT NULL,
  client_id   INT         NOT NULL,
  CONSTRAINT credit_cards_id_uindex
  UNIQUE (id),
  CONSTRAINT credit_cards_card_number_uindex
  UNIQUE (card_number),
  CONSTRAINT client_fk
  FOREIGN KEY (client_id) REFERENCES clients (id)
);
CREATE INDEX client_fk
  ON credit_cards (client_id);

CREATE TABLE orders
(
  id             INT AUTO_INCREMENT
    PRIMARY KEY,
  credit_card_id INT                                                                       NOT NULL,
  car_id         INT                                                                       NOT NULL,
  term_id        INT                                                                       NULL,
  status         ENUM ('NEW', 'CLOSED', 'REFUNDED', 'REJECTED', 'ACCEPTED') DEFAULT 'NEW' NULL,
  CONSTRAINT orders_id_uindex
  UNIQUE (id),
  CONSTRAINT credit_card_fk
  FOREIGN KEY (credit_card_id) REFERENCES credit_cards (id),
  CONSTRAINT order_car_fk
  FOREIGN KEY (car_id) REFERENCES cars (id)
    ON DELETE CASCADE,
  CONSTRAINT order_term_fk
  FOREIGN KEY (term_id) REFERENCES terms (id)
);
CREATE INDEX credit_card_fk
  ON orders (credit_card_id);
CREATE INDEX order_car_fk
  ON orders (car_id);
CREATE INDEX order_term_fk
  ON orders (term_id);