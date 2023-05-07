use springdata


CREATE TABLE users
(
    username VARCHAR(50) PRIMARY KEY ,
    password VARCHAR(500) NOT NULL ,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities
(
    id SERIAL PRIMARY KEY ,
    username VARCHAR(50) NOT NULL ,
    authority VARCHAR(500) NOT NULL ,
    constraint fk_username FOREIGN KEY (username) references users(username) ON DELETE CASCADE
);

CREATE TABLE customer
(
    id SERIAL PRIMARY KEY ,
    username VARCHAR(50) NOT NULL ,
    password VARCHAR(500) NOT NULL ,
    role varchar(50) not null

);
INSERT INTO `springdata`.`customer`
(`id`,
 `username`,
 `password`,
 `role`)
VALUES
    (1,
     'k1el',
     '123',
     'admin');

