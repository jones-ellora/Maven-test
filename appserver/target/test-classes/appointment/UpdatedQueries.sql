create table calendar(calendarId int primary key AUTO_INCREMENT,calendarOrder int, calendarType text, name text, organizationId int, organizationName text, facilityId int, facilityName text, locationId int, locationName text, doctorId int, doctorName text,foreign key(doctorId) REFERENCES doctor(doctorID), foreign key(organizationID) REFERENCES organization (organizationId), foreign key(facilityId) REFERENCES facility(facilityId));

insert into facility values (04,274,"Diabetic center");

insert into calendar values(1,1, "Clinic", "A1", 01, "Global Hospital", 11, "Child Care", 122, "Coimbatore",1012, "Smith");
insert into calendar values(null,2, "Clinic", "B1", 04, "Apollo", 274, "Diabetic center", 122, "Coimbatore", 1012, "Smith");
insert into calendar values(null,3, "Personal", "Pers", null, null, null, "Child Care", 122, "Coimbatore", 1012, "Smith");

insert into calendar values(null,1, "Clinic", "Clinic 1", 02, "Apollo", 274, "Diabetic center", 121, "Chennai", 103, "Jack");
insert into calendar values(null,2, "Clinic", "Clinic 2", 01, "Global Hospital", 11, "Dentist", 121, "Chennai", 103, "Jack");
insert into calendar values(null,3, "Personal", "Personal", null, null, null, "Child Care", 121, "Chennai", 103, "Jack");

create table appointment(doctorId int, calendarOrder int, slotId varchar(10), appointmentId int primary key, type text, reason text, priority text, description text, startTime timeStamp, endTime timeStamp, created timeStamp, referral text, duration text, status text, alert text,foreign key(slotID) REFERENCES slot(slotID),foreign key(doctorId) REFERENCES doctor(doctorID));

create table participant(appointmentId int, participantId int primary key AUTO_INCREMENT, number int, name text,role text, purpose text, foreign key(appointmentId) REFERENCES appointment(appointmentId));

select cal.calendarOrder,cal.calendarType,cal.calendarName,cal.doctorId,cal.doctorName,cal.organizationId,cal.organizationName,cal.facilityId,cal.facilityName,cal.locationId,cal.locationName,
apt.slotId,apt.Id,apt.type,apt.reason,apt.priority,apt.description,apt.startTime,apt.endTime,apt.created,apt.referral,apt.duration,apt.status,apt.alert,
part.id,part.participantId,part.participantName, part.role,part.purpose from calendar cal LEFT OUTER JOIN appointment apt ON cal.doctorId = apt.doctorId AND cal.calendarOrder = apt.calendarOrder
LEFT OUTER JOIN participant part ON part.id = apt.id; 


insert into appointment values (1012, 1, "M01", 01,"checkup","cold","medium","Throat Infection","2017-04-05 10:30:00","2017-04-05 10:45:00","2017-04-05 09:00:00", "No reference","short","Confirmed","CONFIRMED");
insert into appointment values (1012, 1, "M01", 02,"routine","allergy","medium","sudden allergy","2017-05-06 10:40:00","2017-05-06 11:00:00","2017-05-06 09:00:00", "No reference","short","Confirmed","CONFIRMED");
insert into appointment values (1012, 1, "M01", 03,"routine","fever","medium","3 days fever","2017-05-07 11:00:00","2017-05-07 11:30:00","2017-05-07 09:30:00", "No reference","short","Confirmed","CONFIRMED");
insert into appointment values (1012, 1, "M01", 04,"meeting",null,"medium","Medical Rep","2017-05-08 11:30:00","2017-05-08 12:00:00","2017-05-08 10:00:00", "No reference","short","Confirmed","CONFIRMED");
insert into appointment values (1012, 1, "M01", 05,"routine","vaccination","medium","vaccination","2017-05-10 12:00:00","2017-05-10 12:30:00","2017-05-10 11:00:00", "No reference","short","Confirmed","CONFIRMED");

insert into appointment values (1012, 2, "E01", 06,"checkup","fever","medium","1 day fever","2017-05-05 17:00:00","2017-04-05 11:30:00","2017-05-05 10:00:00", "No reference","short","Confirmed","CONFIRMED");
insert into appointment values (1012, 2, "E01", 07,"routine","diabetic checkup","medium","3 days fever","2017-05-05 17:30:00","2017-05-05 17:45:00","2017-05-05 11:00:00", "No reference","short","Confirmed","CONFIRMED");
insert into appointment values (1012, 2, "E01", 08,"checkup","diabetic","medium","Found symptoms for diabetic","2017-05-06 17:45:00","2017-05-06 18:00:00","2017-05-06 12:00:00", "No reference","short","Confirmed","CONFIRMED");
insert into appointment values (1012, 2, "E01", 09,"routine","diabetic checkup","medium","Rise in diabetic","2017-05-07 18:00:00","2017-05-07 18:30:00","2017-05-07 13:00:00", "No reference","short","Confirmed","CONFIRMED");
insert into appointment values (1012, 2, "E01", 10,"checkup","fever","medium","Recheckup","2017-05-08 18:30:00","2017-05-08 19:00:00","2017-05-08 14:00:00", "No reference","short","Confirmed","CONFIRMED");

insert into slot values("E03", "Personal", null, "2017-05-05 13:00:00", "2017-05-05 18:00:00", TRUE);

insert into appointment values (1012, 3, "E03", 11,"Team Lunch",null,"medium","Team lunch","2017-05-05 13:00:00","2017-05-05 14:00:00","2017-05-05 09:00:00", "No reference","short","Confirmed","CONFIRMED");
insert into appointment values (1012, 3, "E03", 12,"Meeting",null,"medium","New arrival of medicine","2017-05-05 14:00:00","2017-05-05 15:30:00","2017-05-05 10:00:00", "No reference","short","Confirmed","CONFIRMED");
insert into appointment values (1012, 3, "E03", 13,"Conference",null,"medium","Diabetic","2017-05-05 15:30:00","2017-05-05 18:00:00","2017-05-05 11:00:00", "No reference","Long","Confirmed","CONFIRMED");


insert into participant values(01,1,01,"Smith","Doctor","Serve Patient");
insert into participant values(01,null,02,"Jack","Patient","Get Treatment");
insert into participant values(02,null,01,"Smith","Doctor","Serve Patient");
insert into participant values(02,null,02,"James","Patient","Get Treatment");
insert into participant values(03,null,01,"Smith","Doctor","Serve Patient");
insert into participant values(03,null,02,"Mary","Patient","Get Treatment");
insert into participant values(04,null,01,"Smith","Doctor","Consulting with Rep");
insert into participant values(04,null,02,"Joseph","Medical Rep","Consulting with doctor");
insert into participant values(04,null,03,"Francis","Medical Rep","Consulting with doctor");
insert into participant values(05,null,01,"Smith","Doctor","Serve Patient");
insert into participant values(05,null,02,"Raja","Patient","Get Treatment");
insert into participant values(06,null,01,"Smith","Doctor","Serve Patient");
insert into participant values(06,null,02,"Bruce","Patient","Get Treatment");
insert into participant values(07,null,01,"Smith","Doctor","Serve Patient");
insert into participant values(07,null,02,"Grace","Patient","Get Treatment");
insert into participant values(08,null,01,"Smith","Patient","Serve Treatment");
insert into participant values(08,null,02,"Jill","Patient","Get Treatment");
insert into participant values(09,null,01,"Smith","Patient","Serve Treatment");
insert into participant values(09,null,02,"George","Patient","Get Treatment");
insert into participant values(10,null,01,"Smith","Patient","Serve Treatment");
insert into participant values(10,null,02,"Ruth","Patient","Get Treatment");
insert into participant values(11,null,01,"Smith","Doctor","Team Lunch");
insert into participant values(11,null,02,"Jack","Doctor","Team Lunch");
insert into participant values(11,null,03,"John","Doctor","Team Lunch");
insert into participant values(11,null,04,"Mani","Doctor","Team Lunch");
insert into participant values(11,null,05,"Abi","Doctor","Team Lunch");
insert into participant values(11,null,06,"Rose","Doctor","Team Lunch");
insert into participant values(12,null,01,"Smith","Doctor","Meeting");
insert into participant values(12,null,02,"John","Doctor","Meeting");
insert into participant values(12,null,03,"Mani","Doctor","Meeting");
insert into participant values(12,null,04,"Abi","Doctor","Meeting");
insert into participant values(13,null,01,"Smith","Doctor","Conference");
insert into participant values(13,null,02,"Raja","Doctor","Conference");
insert into participant values(13,null,03,"Rani","Doctor","Conference");
insert into participant values(13,null,04,"Rose","Doctor","Conference");
insert into participant values(13,null,05,"Abi","Doctor","Conference");
insert into participant values(13,null,06,"Jack","Doctor","Conference");
insert into participant values(13,null,07,"John","Doctor","Conference");



select c.calendarOrder, c.,
