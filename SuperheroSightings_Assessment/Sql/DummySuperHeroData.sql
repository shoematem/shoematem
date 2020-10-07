USE superherosightingsdb;

INSERT INTO location(locationName, locationDescription, address, latitude, longitude)
	VALUES ('Ford Field', 'Ford Field Football Stadium Parking Lot', '123 Court Street Detroit, MI 12345', '121.223735', '152.331447');
    
INSERT INTO location(locationName, locationDescription, address, latitude, longitude)
	VALUES ('UM-Flint', 'Murchie Science Building', '321 Michigan Street Flint, MI 48532', 532.164472, 965.432331);
    
INSERT INTO superPower(superPowerName)
	VALUES('Fly');
    
INSERT INTO superPower(superPowerName)
	VALUES('Super strength');
    
INSERT INTO superPerson(superPersonName, superPersonDescription, isSuperHero, superPowerID)
	VALUES('The Hulk', 'Giant Green Man', 1, 3);
    
INSERT INTO superPerson(superPersonName, superPersonDescription, isSuperHero, superPowerID)
	VALUES('The Thing', 'Giant scaley-type man', 1, 3);
    
INSERT INTO superPerson(superPersonName, superPersonDescription, isSuperHero, superPowerID)
	VALUES('Superman', 'Looks like Clark Kent', 1, 2);
    
INSERT INTO superPerson(superPersonName, superPersonDescription, isSuperHero, superPowerID)
	VALUES('Thanos', 'Evil Titan', 0, 3);
    
INSERT INTO sightings(sightingDate, locationID)
	VALUES('01/01/2020 10:15 AM', 2);

INSERT INTO sightings(sightingDate, locationID)
	VALUES('02/02/2020 11:16 PM', 3);

INSERT INTO sightings(sightingDate, locationID)
	VALUES('03/03/2020 12:17 PM', 2);
    
INSERT INTO sightings(sightingDate, locationID)
	VALUES('04/04/2020 3:18 AM', 3);
    
INSERT INTO sightings(sightingDate, locationID)
	VALUES('04/04/2020 4:19 AM', 2);
    
INSERT INTO sightings(sightingDate, locationID)
	VALUES('05/05/2020 5:20 PM', 3);
    
INSERT INTO sightings(sightingDate, locationID)
	VALUES('06/06/2020 6:21 AM', 2);
    
INSERT INTO sightings(sightingDate, locationID)
	VALUES('07/07/2020 7:22 PM', 3);
    
INSERT INTO organizations(organizationName, organizationDescription, contactInfo, locationID)
	VALUES('Justice League', 'DC Comics', 'Do not try to find us', 1);
    
INSERT INTO organizations(organizationName, organizationDescription, contactInfo, locationID)
	VALUES('Avengers', 'Marvel Comics', 'Come to our location', 2);
    
INSERT INTO organizations(organizationName, organizationDescription, contactInfo, locationID)
	VALUES('Black Order', 'Marvel Comics', 'Come to our location.. If you dare', 3);
    
INSERT INTO organizations_superPerson(superPersonID, organizationID)
	VALUES(1, 3);

INSERT INTO organizations_superPerson(superPersonID, organizationID)
	VALUES(2, 3);
    
INSERT INTO organizations_superPerson(superPersonID, organizationID)
	VALUES(3, 2);
    
INSERT INTO organizations_superPerson(superPersonID, organizationID)
	VALUES(4, 4);
    
INSERT INTO superPerson_sightings(superPersonID, sightingID)
	VALUES(1, 1);
    
INSERT INTO superPerson_sightings(superPersonID, sightingID)
	VALUES(1, 2);
    
INSERT INTO superPerson_sightings(superPersonID, sightingID)
	VALUES(1, 3);
    
INSERT INTO superPerson_sightings(superPersonID, sightingID)
	VALUES(2, 2);

INSERT INTO superPerson_sightings(superPersonID, sightingID)
	VALUES(2, 3);
    
INSERT INTO superPerson_sightings(superPersonID, sightingID)
	VALUES(4, 1);
    
INSERT INTO superPerson_sightings(superPersonID, sightingID)
	VALUES(4, 2);
    
INSERT INTO superPerson_sightings(superPersonID, sightingID)
	VALUES(4, 3);
    
INSERT INTO superPerson_sightings(superPersonID, sightingID)
	VALUES(3, 4);
    
INSERT INTO superPerson_sightings(superPersonID, sightingID)
	VALUES(3, 5);
    
INSERT INTO superPerson_sightings(superPersonID, sightingID)
	VALUES(3, 6);
    
INSERT INTO superPerson_sightings(superPersonID, sightingID)
	VALUES(3, 7);
    
INSERT INTO superPerson_sightings(superPersonID, sightingID)
	VALUES(1, 5);
    
INSERT INTO superPerson_sightings(superPersonID, sightingID)
	VALUES(1, 6);
    
INSERT INTO superPerson_sightings(superPersonID, sightingID)
	VALUES(2, 5);