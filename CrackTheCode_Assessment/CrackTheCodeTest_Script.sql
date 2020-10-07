DROP DATABASE IF EXISTS crackthecodetest;
CREATE DATABASE crackthecodetest;

USE crackthecodetest;

CREATE TABLE Game (
	gameID INT AUTO_INCREMENT,
    CONSTRAINT pk_game
		PRIMARY KEY (gameID),
	gameStatus VARCHAR(15) NOT NULL DEFAULT 'In Progress',
    answer VARCHAR(4) NOT NULL
);

CREATE TABLE Round (
	roundID INT AUTO_INCREMENT,
    guess INT NOT NULL,
    timeOfGuess DATETIME NOT NULL,
    result VARCHAR(10) NOT NULL,
    CONSTRAINT pk_round
		PRIMARY KEY (roundID),
	gameID INT,
    CONSTRAINT fk_round_game
		FOREIGN KEY (gameID)
        REFERENCES game(gameID)
);