-- Running this script will DELETE the existing database and all data it contains.
-- Use with caution.

DROP DATABASE IF EXISTS vinylrecordshop;

CREATE DATABASE vinylrecordshop;

USE vinylrecordshop;

CREATE TABLE album (
	albumId INT AUTO_INCREMENT,
    albumTitle VARCHAR(100) NOT NULL,
    label VARCHAR(50),
    releaseDate DATE,
    price DECIMAL(5,2),
    CONSTRAINT pk_album 
        PRIMARY KEY (albumId)
);

CREATE TABLE artist (
    artistId INT NOT NULL AUTO_INCREMENT,
    fname VARCHAR(25) NOT NULL,
    lname varchar(50) NOT NULL,
    isHallOfFame TINYINT(1) NOT NULL,
    CONSTRAINT pk_artist 
        PRIMARY KEY (artistId)
);

CREATE TABLE band (
	bandId INT AUTO_INCREMENT,
	bandName VARCHAR(50) NOT NULL,
	CONSTRAINT pk_band 
        PRIMARY KEY (bandId)
);

CREATE TABLE song (
    songId INT NOT NULL AUTO_INCREMENT,
    songTitle VARCHAR(100) NOT NULL,
    videoUrl VARCHAR(100),
    bandId INT NOT NULL,
    CONSTRAINT pk_song 
    	PRIMARY KEY (songId),
    CONSTRAINT fk_song_band 
    	FOREIGN KEY (bandID)
    	REFERENCES band(bandId)
);

CREATE TABLE songAlbum (
    songId INT,
    albumId INT,
    CONSTRAINT pk_songAlbum 
    	PRIMARY KEY (songId, albumId),
    CONSTRAINT fk_songAlbum_song
    	FOREIGN KEY (songId)
    	REFERENCES song(songId),
    CONSTRAINT fk_songAlbum_album
    	FOREIGN KEY (albumId)
    	REFERENCES album(albumId)
);

CREATE TABLE bandArtist (
	bandId INT,
	artistId INT,
	CONSTRAINT pk_bandArtist 
        PRIMARY KEY (bandId, artistId),
	CONSTRAINT fk_bandArtist_band 
        FOREIGN KEY (bandId)
		REFERENCES band(bandId),
	CONSTRAINT fk_bandArtist_artist 
        FOREIGN KEY (artistId)
		REFERENCES artist (artistId)
);

INSERT INTO `artist` VALUES (1,'John','Lennon',1),(2,'Paul','McCartney',1),(3,'George','Harrison',1),(4,'Ringo','Starr',1),(5,'Denny','Zager',0),(6,'Rick','Evans',0),(10,'Van','Morrison',1),(11,'Judy','Collins',0),(12,'Paul','Simon',1),(13,'Art','Garfunkel',0),(14,'Brian','Wilson',1),(15,'Dennis','Wilson',1),(16,'Carl','Wilson',1),(17,'Ricky','Fataar',0),(18,'Blondie','Chaplin',0),(19,'Jimmy','Page',1),(20,'Robert','Plant',1),(21,'John Paul','Jones',1),(22,'John','Bonham',1),(23,'Mike ','Love',1),(24,'Al ','Jardine',1),(25,'David','Marks',0),(26,'Bruce ','Johnston',0);