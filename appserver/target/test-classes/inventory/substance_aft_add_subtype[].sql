create table Location (locationID int primary key, locationName text,description text,telecom int,position text,status text);
create table Organization(organizationID int primary key,locationID int,service text, foreign key(locationID) references Location(locationID));
create table Substance (organizationID int not null,substanceID int not null primary key,substanceName text,category text,foreign key(organizationID) REFERENCES organization(organizationID));


insert into Location values (121,"Chennai","Chennai",986547123,"Lat","Active");
insert into Organization values(01,121,"HealthCareService");
insert into Substance values(01,101,"Blanket","Material");
insert into Substance values(01,102,"Gloves","Material");
insert into Substance values(01,103,"Pillow","Material");
insert into Substance values(01,104,"Torch","Material");
insert into Substance values(01,105,"Stethoscopes","Material");
insert into Substance values(01,106,"Bandages","Material");
insert into Substance values(01,107,"Syringe","Material");
insert into Substance values(01,108,"Hospital Bed","Material");
insert into Substance values(01,109,"Weight Scale","Material");
insert into Substance values(01,110,"Thermometer","Material");


create table substancesize (OrganizationID int not null, substanceID int not null,size int,type text,sizekey varchar(20), color text, quantity int, rol int, expiryDate date,foreign key(substanceID) references substance(substanceID),foreign key(organizationID) REFERENCES organization(organizationID));


insert into substancesize values(1,101,2,"Medium","101-2","White",45,10,"2025-05-12");
insert into substancesize values(1,101,3,"Large","101-3","White",25,15,"2034-07-12");
insert into substancesize values(1,102,1,"Small","102-1","White",15,10,"2044-01-29");
insert into substancesize values(1,103,2,"Medium","103-2","White",35,8,"2078-01-01");
insert into substancesize values(1,103,1,"Small","103-1","White",25,15,"2058-01-05");
insert into substancesize values(1,104,2,"Medium","104-2","Black",55,20,"2058-01-05");
insert into substancesize values(1,105,1,"Small","105-1","Navy blue",55,20,"2058-01-05");
insert into substancesize values(1,105,2,"Medium","105-2","Navy blue",55,20,"2058-01-05");
insert into substancesize values(1,105,3,"Large","105-3","Navy blue",55,20,"2058-01-05");
insert into substancesize values(1,106,1,"Small","106-1","Brown",55,20,"2058-01-05");
insert into substancesize values(1,106,2,"Medium","106-2","Brown",55,20,"2058-01-05");
insert into substancesize values(1,106,3,"Large","106-3","Brown",55,20,"2058-01-05");
insert into substancesize values(1,107,2,"Medium","107-2","White",55,20,"2058-01-05");
insert into substancesize values(1,107,3,"Large","107-3","White",55,20,"2058-01-05");
insert into substancesize values(1,108,1,"Small","108-1","Green",55,20,"2058-01-05");
insert into substancesize values(1,108,2,"Medium","108-2","Green",55,20,"2058-01-05");
insert into substancesize values(1,108,3,"Large","108-3","Green",55,20,"2058-01-05");
insert into substancesize values(1,109,2,"Medium","109-2","Grey",55,20,"2058-01-05");
insert into substancesize values(1,109,3,"Large","109-3","Grey",55,20,"2058-01-05");
insert into substancesize values(1,110,2,"Medium","110-2","White",55,20,"2058-01-05");
insert into substancesize values(1,110,3,"Large","110-3","White",55,20,"2058-01-05");


