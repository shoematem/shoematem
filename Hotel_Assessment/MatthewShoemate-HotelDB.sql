DROP DATABASE IF EXISTS Hotel;
CREATE DATABASE Hotel;
USE Hotel;

CREATE TABLE Amenities (
	AmenitiesID INT AUTO_INCREMENT,
    CONSTRAINT pk_amenities
		PRIMARY KEY (AmenitiesID),
	AmenitiesName VARCHAR(40) NOT NULL,
    AmenitiesCost DECIMAL(10,2) NOT NULL
);

CREATE TABLE RoomType (
	RoomTypeID INT AUTO_INCREMENT,
    CONSTRAINT pk_roomtype
		PRIMARY KEY (RoomTypeID),
	RoomTypeName VARCHAR(20) NOT NULL,
    StandardOccupancy INT NOT NULL,
    MaxOccupancy INT NOT NULL
);

CREATE TABLE Room (
	RoomID INT,
    CONSTRAINT pk_room
		PRIMARY KEY (RoomID),
	ADA_Accessible boolean NOT NULL DEFAULT 0,
    BasePrice DECIMAL(10,2) NOT NULL,
    ExtraPersonPrice DECIMAL(5,2) NOT NULL
);

CREATE TABLE Guests (
	GuestID INT AUTO_INCREMENT,
	CONSTRAINT pk_guests
		PRIMARY KEY (GuestID),
	FirstName VARCHAR(40) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Address VARCHAR(100) NOT NULL,
    City VARCHAR(40) NOT NULL,
    State CHAR(2) NOT NULL,
    ZIP INT(9) NOT NULL,
    PhoneNumber VARCHAR(20) NOT NULL
);

CREATE TABLE Reservations (
	ReservationID INT AUTO_INCREMENT,
    CONSTRAINT pk_reservations
		PRIMARY KEY (ReservationID),
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    TotalCost DECIMAL(10,2) NOT NULL,
    NumAdults INT(2) NOT NULL DEFAULT 1,
    NumChildren INT(2) NOT NULL DEFAULT 0,
    GuestID INT,
    CONSTRAINT fk_reservation_guest
		FOREIGN KEY (GuestID)
        REFERENCES guests(GuestID)
);

CREATE TABLE AmenitiesRoom (
	AmenitiesID INT,
    RoomID INT,
    RoomTypeID INT,
	CONSTRAINT pk_amenitiesroom
		PRIMARY KEY (AmenitiesID, RoomID, RoomTypeID),
	CONSTRAINT fk_amenitiesroom_amenitiesid
		FOREIGN KEY (AmenitiesID)
        REFERENCES Amenities(AmenitiesID),
	CONSTRAINT fk_amenitiesroom_roomid
		FOREIGN KEY (RoomID)
        REFERENCES Room(RoomID),
	CONSTRAINT fk_amenitiesroom_roomtypeid
		FOREIGN KEY (RoomTypeID)
        REFERENCES RoomType(RoomTypeID)
);

CREATE TABLE RoomReservations (
	RoomID INT,
    RoomTypeID INT,
    ReservationID INT,
    CONSTRAINT pk_roomreservations
		PRIMARY KEY (RoomID, RoomTypeID, ReservationID),
	CONSTRAINT fk_roomreservation_roomid
		FOREIGN KEY (RoomID)
        REFERENCES Room(RoomID),
	CONSTRAINT fk_roomreservation_roomtypeid
		FOREIGN KEY (RoomTypeID)
        REFERENCES RoomType(RoomTypeID),
	CONSTRAINT fk_roomreservation_reservationid
		FOREIGN KEY (ReservationID)
        REFERENCES Reservations(ReservationID)
);