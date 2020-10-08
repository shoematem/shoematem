USE Hotel;

-- Note: I could not get the command line INSERT to work, so I used the wizard --
-- Insert Amenities Table Data --
-- Insert Guests Table  Data --
-- Insert Rooms Table Data --
-- Insert RoomTypes Table Data --
-- Insert Reservations Table Data --
-- Insert AmenitiesRoom Table Data --
-- Insert RoomReservations Table Data --

-- Delete Jeremiah Pendergrass records (GuestID: 8) --

DELETE FROM RoomReservations
WHERE ReservationID = 8;

DELETE FROM Reservations
WHERE GuestID = 8;

DELETE FROM Guests
WHERE GuestID = 8;