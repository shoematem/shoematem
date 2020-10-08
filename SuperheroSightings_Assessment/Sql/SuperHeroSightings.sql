DROP DATABASE IF EXISTS superheroSightingsDB;
CREATE DATABASE superheroSightingsDB;

USE superheroSightingsDB;

CREATE TABLE location (
	locationID INT PRIMARY KEY AUTO_INCREMENT,
    locationName VARCHAR(60) NOT NULL,
    locationDescription VARCHAR(200) NOT NULL,
    address VARCHAR(100) NOT NULL,
    latitude DECIMAL(9,6) NOT NULL,
    longitude DECIMAL(9,6) NOT NULL
);

CREATE TABLE superPower (
	superPowerID INT PRIMARY KEY AUTO_INCREMENT,
    superPowerName VARCHAR(100) NOT NULL
);

CREATE TABLE superPerson (
	superPersonID INT PRIMARY KEY AUTO_INCREMENT,
    superPersonName VARCHAR(60) NOT NULL,
    superPersonDescription VARCHAR(200) NOT NULL,
    isSuperHero BOOLEAN NOT NULL,
    superPowerID INT NOT NULL,
    FOREIGN KEY(superPowerID) REFERENCES superPower(superPowerID)
);

CREATE TABLE sightings (
	sightingID INT PRIMARY KEY AUTO_INCREMENT,
    sightingDate VARCHAR(50) NOT NULL,
    locationID INT NOT NULL DEFAULT 1,
    FOREIGN KEY (locationID) REFERENCES location(locationID)
);

CREATE TABLE organizations (
	organizationID INT PRIMARY KEY AUTO_INCREMENT,
    organizationName VARCHAR(100) NOT NULL,
    organizationDescription VARCHAR(200) NOT NULL,
    contactInfo VARCHAR(200) NOT NULL,
    locationID INT NOT NULL DEFAULT 1,
    FOREIGN KEY(locationID) REFERENCES location(locationID)
);

CREATE TABLE organizations_superPerson (
	superPersonID INT NOT NULL,
    organizationID INT NOT NULL,
    PRIMARY KEY(superPersonID, organizationID),
    FOREIGN KEY(superPersonID) REFERENCES superPerson(superPersonID),
    FOREIGN KEY(organizationID) REFERENCES organizations(organizationID)
);

CREATE TABLE superPerson_sightings (
	superPersonID INT NOT NULL,
    sightingID INT NOT NULL,
    PRIMARY KEY(superPersonID, sightingID),
    FOREIGN KEY(superPersonID) REFERENCES superPerson(superPersonID),
    FOREIGN KEY(sightingID) REFERENCES sightings(sightingID)
);

INSERT INTO superPower(superPowerName) VALUES ('none');
INSERT INTO location(locationName, locationDescription, address, latitude, longitude) VALUES ('unspecified', 'N/A', 'N/A', 0, 0);
INSERT INTO organizations(organizationName, organizationDescription, contactInfo) VALUES ('unspecified', 'N/A', 'N/A');