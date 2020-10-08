DROP DATABASE IF EXISTS toDoDB;
CREATE DATABASE toDoDB;

USE toDoDB;

CREATE TABLE toDo (
ID INT PRIMARY KEY AUTO_INCREMENT,
toDo VARCHAR(40) NOT NULL,
note VARCHAR(255),
finished BOOLEAN DEFAULT false
);


INSERT INTO toDo(ID, toDo, note, finished)
VALUES(1, "Wash Car", "", false),
(2, "Laundry", "Jeans", false),
(3, "Wash dishes", "", true);