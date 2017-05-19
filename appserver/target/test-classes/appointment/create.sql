create table doctor(organizationID int, doctorID int primary key, doctorName text,specialist text, appointmentDate date, availableTimeFrom text, availableTimeTo text);

create table patient(organizationID int, patientID int primary key, patientName text, tokenNumber int,symptoms text, primaryReason text, allergy text);

create table slot(slotId varchar(10) primary key,slotType text, service text, startTime TimeStamp, endTime TimeStamp, overbooked Boolean);

create table auditEvent(organizationID int, eventID int, eventName text);

create table organization(organizationID int primary key,organizationName text, locationID int, locationName text,foreign key(locationID) REFERENCES location(locationID));

create table facility(organizationID int, facilityId int primary key, facilityName text, foreign key(organizationID) REFERENCES Organization(organizationID));

create table CalendarType(calendarType int,doctorId int, doctorName text, calendarName text, organizationName text, facilityName text, locationId int, locationName text,foreign key(doctorId) REFERENCES doctor(doctorID));

create table appointment(doctorId int,calendarType int,calendarName text,organizationName text,facilityName text,locationId int,locationName text,slotId varchar(10),ID int primary key,type text,reason text,priority text,description text,startTime TimeStamp,endTime TimeStamp,created TimeStamp,referral text,duration text,status text,alert text,foreign key(slotID) REFERENCES slot(slotID),foreign key(doctorId) REFERENCES doctor(doctorID));

create table location(locationId int primary key, locationName text, description text, telecom int, position text, status text);

insert into facility values (01,11,"Child Care");
insert into facility values (01,13,"Dentist");
insert into facility values (01,23,"ENT");
insert into facility values (03,27,"Child Care");

insert into organization values (01,"Global Hospital",121,"chennai");
insert into organization values (02,"Apollo",121,"chennai");
insert into organization values (03,"Global Hospital",122,"Coimbatore");
insert into organization values (04,"Apollo",122,"Coimbatore");

insert into location values (122,"Coimbatore","CBE",784561245,"Lat","Active");

insert into doctor values (01, 1012, "Smith", "Child Specialist", "2017-04-26", "03:00:00", "06:00:00");
insert into doctor values (01, 103, "Jack", "Dentist", "2017-04-26", "03:00:00", "06:00:00");
insert into doctor values (01, 102, "John", "Child Specialist", "2017-04-26", "03:00:00", "06:00:00");
insert into doctor values (01, 1043, "Rose", "Cardiologist", "2017-04-26", "03:00:00", "06:00:00");
insert into doctor values (01, 105, "Rani", "Dentist", "2017-04-26", "03:00:00", "06:00:00");
insert into doctor values (01, 205, "Mani", "Pediatrician ", "2017-04-26", "03:00:00", "06:00:00");
insert into doctor values (01, 2025, "Abi", "Child Specialist", "2017-04-26", "03:00:00", "06:00:00");

insert into patient values(01,2,"Jack" , 01, "head ache", "cold", "No");
insert into patient values(01,31,"James" , 02, "cold", "fever", "Yes");
insert into patient values(01,32,"Mark" , 03, "cough", "cold", "No");
insert into patient values(01,56,"Mary" , 04, "cold", "head ache", "Yes");
insert into patient values(01,42,"Rachel" , 05, "cough", "cold", "No");
insert into patient values(01,20,"Bradley" , 06, "fever", "head ache", "No");
insert into patient values(01,3,"Grace" , 07, "cough", "cold", "Yes");
insert into patient values(01,21,"Bruce" , 08, "cough", "cold", "No");
insert into patient values(01,12,"Rose" , 09, "head ache", "cold", "Yes");
insert into patient values(01,14,"Jill" , 10, "head ache", "cold", "Yes");
insert into patient values(01,17,"Ruth" , 11, "cough", "cold", "No");
insert into patient values(01,210,"George" , 21, "cough", "cold", "No");

insert into slot values("M01", "OPD", "Health Care Service", "2017-04-05 10:30:00", "2017-04-05 12:30:00", TRUE);
insert into slot values("M02", "In Patient", "Health Care Service", "2017-04-05 10:00:00", "2017-04-05 10:30:00", FALSE);
insert into slot values("E01", "OPD", "Health Care Service", "2017-04-05 17:00:00", "2017-04-05 19:30:00", FALSE);


insert into CalendarType values(1, 1012, "Smith", "Clinic 1", "Global", "Child Care",122, "Coimbatore");
insert into CalendarType values(2, 1012, "Smith", "Clinic 2", "Apollo", "Child Care",123, "Chennai");
insert into CalendarType values(3, 1012, "Smith", "Personal", null, "Child Care",122, "Coimbatore");
insert into CalendarType values(1, 1043, "Rose", "Clinic 1", "Apollo", "Child Care",123, "Chennai");


insert into appointment values(1012,1,"Clinic 1","Global",  "Child Care",122,"Coimbatore","M01", 111,"checkup","fever","medium","3 days fever","2017-04-05 11:00:00","2017-04-05 11:30:00","2017-04-05 10:00:00", "No reference","short","Confirmed","CONFIRMED");
insert into appointment values(1012,1,"Clinic 1","Global",  "Child Care",122,"Coimbatore","M01", 141,"Walkin","fever","medium","sudden fever","2017-05-05 11:00:00","2017-05-05 11:30:00","2017-05-05 10:00:00", "No reference","short","Confirmed","CONFIRMED");
insert into appointment values(1012,2,"Clinic 2","Apollo", "Child Care",122,"Coimbatore","E01",121,"followup","Cough","medium","2 days cough","2017-04-05 17:00:00","2017-04-05 17:30:00","2017-04-05 13:00:00","No reference","short","Confirmed","CONFIRMED");
insert into appointment values(1012,3,"Personal",null, "Child Care",122,"Coimbatore","E01",130,"walkin","allergy","high","sudden allergy","2017-04-05 19:00:00","2017-04-05 19:30:00","2017-04-05 17:00:00", "No reference","short","Confirmed","CONFIRMED");

insert into appointment values(1012,3,"Personal",null,"Child Care",122,"Coimbatore","E01",165,"Meeting","Medical Enhancement","Medium","Arrival of New Vaccination", "2017-05-06 14:00:00","2017-05-06 16:00:00","2017-05-06 10:00:00","Dr.Ruth","Medium","confirmed","CONFIRMED");
