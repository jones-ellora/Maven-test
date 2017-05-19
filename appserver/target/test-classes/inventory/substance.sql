create table Location (locationID int primary key, locationName text,description text,telecom int,position text,status text);
create table Organization(organizationID int primary key,locationID int,service text, foreign key(locationID) references Location(locationID));
create table Substance(organizationID int,substanceID int not null primary key,substanceName text,category text,expiryDate date,quantity int, rol int, foreign key (organizationID) REFERENCES organization(OrganizationID));

alter table Substance add size text;
update Substance set size = "medium" where substanceID=101;
update Substance set size = "large" where substanceID=102;
update Substance set size = "medium" where substanceID=103;
update Substance set size = "large" where substanceID=104;

insert into Substance values (01,105,"Stethoscopes","Material","2045-03-07",20,10,"medium");
insert into Substance values (01,106,"Bandages","Material","2045-05-14",20,10,"small");
insert into Substance values (01,107,"syringe","Material","2035-08-04",20,10,"small");
insert into Substance values (01,108,"Hospital bed","Material","2019-04-04",20,10,"small");
insert into Substance values (01,109,"Weight scale","Material","2045-03-07",20,10);


