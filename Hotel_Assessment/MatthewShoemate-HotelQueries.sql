USE hotel;

-- Query 1: Write a query that returns a list of reservations that end in July 2023, including the name of the guest,
-- the room number(s), and the reservation dates.
SELECT rr.RoomID, g.FirstName, g.LastName, r.StartDate, r.EndDate
FROM Reservations r
INNER JOIN Guests g ON r.GuestID = g.GuestID
INNER JOIN RoomReservations rr ON r.ReservationID = rr.ReservationID
WHERE r.EndDate BETWEEN '2023-07-01' AND '2023-07-31'
ORDER BY r.StartDate;

-- Query 2: Write a query that returns a list of all reservations for rooms with a jacuzzi, displaying the guest's
-- name, the room number, and the dates of the reservation.
SELECT rr.RoomID, g.FirstName, g.LastName, r.StartDate, r.EndDate
FROM Reservations r
INNER JOIN Guests g ON r.GuestID = g.GuestID
INNER JOIN RoomReservations rr ON r.ReservationID = rr.ReservationID
INNER JOIN AmenitiesRoom ar ON rr.RoomID = ar.RoomID
INNER JOIN Amenities a ON ar.AmenitiesID = a.AmenitiesID
WHERE a.AmenitiesID = 2
ORDER BY r.StartDate;

-- Query 3: Write a query that returns all the rooms reserved for a specific guest, including the guest's name, the
-- room(s) reserved, the starting date of the reservation, and how many people were included in the reservation.
-- (Choose a guest's name from the existing data.)

SELECT rr.RoomID, g.FirstName, g.LastName, r.StartDate, (r.NumAdults + r.NumChildren) NumPeople
FROM Reservations r
LEFT JOIN Guests g ON r.GuestID = g.GuestID
LEFT JOIN RoomReservations rr ON r.ReservationID = rr.ReservationID
WHERE g.GuestID = 10
ORDER BY r.StartDate;

-- Query 4: Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation. The
-- results should include all rooms, whether or not there is a reservation associated with the room.

SELECT r.RoomID, rr.ReservationID, r.BasePrice, r.ExtraPersonPrice
FROM Room r
LEFT JOIN RoomReservations rr ON r.RoomID = rr.RoomID;

-- Query 5: Write a query that returns all rooms with a capacity of three or more and that are reserved on any date in April 2023.

SELECT rr.RoomID, r.ReservationID, r.StartDate, r.EndDate
FROM Reservations r
INNER JOIN RoomReservations rr ON r.ReservationID = rr.ReservationID
INNER JOIN RoomType rt ON rr.RoomTypeID = rt.RoomTypeID
WHERE rt.MaxOccupancy >= 3 AND if((r.StartDate < '2023-03-31' AND r.EndDate > '2023-04-30'), 1, 0) = 1
	OR (r.StartDate BETWEEN '2023-04-01' AND '2023-04-30') OR (r.EndDate BETWEEN '2023-04-01' AND '2023-04-30');

-- Query 6: Write a query that returns a list of all guest names and the number of reservations per guest, sorted
-- starting with the guest with the most reservations and then by the guest's last name.

SELECT g.FirstName, g.LastName, COUNT(ReservationID) NumReservations
FROM Reservations r
INNER JOIN Guests g ON r.GuestID = g.GuestID
GROUP BY g.GuestID
ORDER BY COUNT(ReservationID) DESC, g.LastName;

-- Query 7: Write a query that displays the name, address, and phone number of a guest based on their phone number.
-- (Choose a phone number from the existing data.)

SELECT FirstName, LastName, Address, City, State, ZIP, PhoneNumber
FROM Guests
WHERE PhoneNumber = '989-863-0088'