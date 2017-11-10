--drop constraints

ALTER TABLE Users DROP CONSTRAINT pk_user_userid CASCADE
/
ALTER TABLE Users DROP CONSTRAINT nn_user_username CASCADE
/
ALTER TABLE Users DROP CONSTRAINT nn_user_email CASCADE
/
ALTER TABLE Users DROP CONSTRAINT nn_user_password CASCADE
/
ALTER TABLE Users DROP CONSTRAINT nn_user_role CASCADE
/
ALTER TABLE Airport DROP CONSTRAINT pk_airport_airportid CASCADE
/
ALTER TABLE Airport DROP CONSTRAINT nn_airport_airportname CASCADE
/
ALTER TABLE Airport DROP CONSTRAINT nn_airport_abbreviation CASCADE
/
ALTER TABLE Airport DROP CONSTRAINT pk_airport_location CASCADE
/
ALTER TABLE FlightInformation DROP CONSTRAINT pk_flightinfo_flightno CASCADE
/
ALTER TABLE BookingInformation  DROP CONSTRAINT pk_bookinginfo_bookingid CASCADE
/
ALTER TABLE BookingInformation  DROP CONSTRAINT nn_bookinginfo_useremail CASCADE
/
ALTER TABLE BookingInformation  DROP CONSTRAINT fk_bookinginfo_flightno CASCADE
/

--drop tables

DROP TABLE Users
/
DROP TABLE Airport
/
DROP TABLE BookingInformation
/
DROP TABLE FlightInformation
/

--Create tables

Create table Users( 
Userid Number(11) constraint pk_user_userid Primary Key,
Username Varchar2(20) constraint nn_user_username Not Null,
User_email Varchar2(40) constraint nn_user_email Not Null, 
Password Varchar2(20) constraint nn_user_password Not Null, 
Role Varchar2(10) constraint nn_user_role Not Null,
Mobile_no Number(10)
)
/


Create table  Airport(
AirportId Number(11) constraint pk_airport_airportid Primary Key,
AirportName Varchar2(50) constraint nn_airport_airportname Not Null, 
Abbreviation Varchar2(6) constraint nn_airport_abbreviation Not Null,
Location Varchar2(40) constraint pk_airport_location Not Null
)
/

Create table FlightInformation(
Flightno Varchar2(10) constraint pk_flightinfo_flightno primary key, 
Airline Varchar2(20), 
Dep_city Varchar2(10), 
Arr_city Varchar2(10), 
Dep_date Date, 
Arr_date Date, 
Dep_time Varchar2(10), 
Arr_time Varchar2(10), 
FirstSeats Number(10), 
FirstSeatFare Number(10,2), 
BussSeats Number, 
BussSeatsFare Number(10,2)
)
/

Create table BookingInformation ( 
Booking_id Varchar2(10) constraint pk_bookinginfo_bookingid primary key, 
User_email Varchar2(40) constraint nn_bookinginfo_useremail not null, 
No_of_passengers Number, 
Class_type Varchar2(10),
Total_fare Number(10,2),
CreditCard_info Varchar2(10), 
Src_city Varchar2(10), 
Dest_city Varchar2(10),
FlightNo varchar2(10) constraint fk_bookinginfo_flightno references FlightInformation(Flightno),
Booking_date Date
)
/