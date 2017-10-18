-- auto-generated definition
CREATE TABLE accounts
(
  id        INT AUTO_INCREMENT
    PRIMARY KEY,
  full_name VARCHAR(50)            NOT NULL,
  email     VARCHAR(30)            NOT NULL,
  is_admin  TINYINT(1) DEFAULT '0' NOT NULL,
  password  VARCHAR(64)            NOT NULL,
  CONSTRAINT accounts_id_uindex
  UNIQUE (id),
  CONSTRAINT accounts_email_uindex
  UNIQUE (email)
);
