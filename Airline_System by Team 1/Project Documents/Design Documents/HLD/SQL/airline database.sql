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
BussSeats Number(10), 
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

--Insert Data

insert into flightinformation 
values('9W-617','JET AIRWAYS','PNQ','BOM',to_date('2017-10-23','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'17:35','18:20',30,4567,40,5432)
/

insert into flightinformation 
values('AI-617','AIR INDIA','BOM','PNQ',to_date('2017-10-23','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'13:55','14:05',30,4567,40,5432)
/

insert into flightinformation 
values('SG-981','SPICE JET','MAA','PNQ',to_date('2017-11-22','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'23:50','1:20',30,4567,40,5432)
/

insert into flightinformation 
values('6E-6406','INDIGO','PNQ','MAA',to_date('2017-11-22','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'0:40','2:25',30,4567,40,5432)
/

insert into flightinformation 
values('G8-176','GO AIR','PNQ','DEL',to_date('2017-11-15','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'1:20','3:30',30,4567,40,5432)
/

insert into flightinformation 
values('I5-731','AIR ASIA','DEL','PNQ',to_date('2017-11-05','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'15:20','17:20',30,4567,40,5432)
/

insert into flightinformation 
values('UK-985','VISTARA','CCU','PNQ',to_date('2017-11-10','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'2:00','4:40',30,4567,40,5432)
/

insert into flightinformation 
values('G8-393','GO AIR','PNQ','CCU',to_date('2017-11-10','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'22:30','1:00',30,4567,40,5432)
/

insert into flightinformation 
values('SG-519','SPICE JET','PNQ','BLR',to_date('2017-11-15','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'5:55','7:20',30,4567,40,5432)
/

insert into flightinformation 
values('I5-1424','AIR ASIA','BLR','PNQ',to_date('2017-12-01','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'0:25','2:00',30,4567,40,5432)
/

insert into flightinformation 
values('G8-173','GO AIR','DEL','BOM',to_date('2017-12-10','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'8:50','11:00',30,4567,40,5432)
/

insert into flightinformation 
values('AI-635','AIR INDIA','BOM','DEL',to_date('2017-12-10','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'7:05','10:35',30,4567,40,5432)
/

insert into flightinformation 
values('SG-105','SPICE JET','DEL','MAA',to_date('2017-12-06','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'20:40','23:20',30,4567,40,5432)
/

insert into flightinformation 
values('AI-43','AIR INDIA','MAA','DEL',to_date('2017-12-05','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'21:10','23:55',30,4567,40,5432)
/

insert into flightinformation 
values('I5-546','AIR ASIA','DEL','CCU',to_date('2017-12-03','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'19:35','22:50',30,4567,40,5432)
/

insert into flightinformation 
values('UK-720','VISTARA','CCU','DEL',to_date('2017-11-26','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'7:10','9:25',30,4567,40,5432)
/

insert into flightinformation 
values('6E-4376','INDIGO','DEL','BLR',to_date('2017-12-06','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'23:45','2:25',30,4567,40,5432)
/

insert into flightinformation 
values('9W-816','JET AIRWAYS','BLR','DEL',to_date('2017-11-26','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'17:45','20:30',30,4567,40,5432)
/

insert into flightinformation 
values('AI-50','AIR INDIA','BOM','MAA',to_date('2017-08-23','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'20:50','9:00',30,4567,40,5432)
/

insert into flightinformation 
values('G8-302','GO AIR','MAA','BOM',to_date('2017-08-23','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'5:20','7:10',30,4567,40,5432)
/

insert into flightinformation 
values('SG-415','SPICE JET','BOM','BLR',to_date('2017-08-23','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'18:05','19:55',30,4567,40,5432)
/

insert into flightinformation 
values('6E-5449','INDIGO','BLR','BOM',to_date('2017-08-23','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'18:10','19:45',30,4567,40,5432)
/

insert into flightinformation 
values('9W-355','JET AIRWAYS','BOM','CCU',to_date('2017-08-23','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'8:30','4:40',30,4567,40,5432)
/

insert into flightinformation 
values('AI-765','AIR INDIA','CCU','BOM',to_date('2017-08-23','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'14:45','23:15',30,4567,40,5432)
/

insert into flightinformation 
values('SG-517','SPICE JET','BLR','CCU',to_date('2017-08-23','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'18:10','20:35',30,4567,40,5432)
/

insert into flightinformation 
values('I5-535','AIR ASIA','CCU','BLR',to_date('2017-08-23','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'8:10','11:55',30,4567,40,5432)
/

insert into flightinformation 
values('6E-389','INDIGO','BLR','MAA',to_date('2017-08-23','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'12:25','13:25',30,4567,40,5432)
/

insert into flightinformation 
values('SG-3309','SPICE JET','MAA','BLR',to_date('2017-08-23','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'16:55','17:50',30,4567,40,5432)
/

insert into flightinformation 
values('AI-764','AIR INDIA','CCU','MAA',to_date('2017-08-23','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'14:45','17:05',30,4567,40,5432)
/

insert into flightinformation 
values('6E-797','INDIGO','MAA','CCU',to_date('2017-08-23','yyyy-mm-dd'),to_date('2012-05-06','yyyy-mm-dd'),'7:50','10:05',30,4567,40,5432)
/