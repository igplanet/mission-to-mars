create table Colonist (id bigint generated by default as identity, colonistId varchar(255), dateOfRegistration timestamp, password varchar(255), primary key (id))
create table Colonist_roles (Colonist_id bigint not null, roles integer)
create table Review (id bigint generated by default as identity, comment varchar(1000), entryDate timestamp, score integer, colonist_id bigint, unit_id bigint, primary key (id))
create table Unit (id bigint generated by default as identity, cancellationPolicy varchar(1000), description varchar(1000), image varchar(400), price varchar(255), region varchar(255), score decimal(19,2), title varchar(255), primary key (id))
alter table Colonist add constraint UK_322f9vavs5w5wpgrlk47uq1mv unique (colonistId)
alter table Colonist_roles add constraint FKjb3ske98l5a3q527hjgysfean foreign key (Colonist_id) references Colonist
alter table Review add constraint FKlq89km658628hpfxe9xdcdtqk foreign key (colonist_id) references Colonist
alter table Review add constraint FKhq7wchpnuk7shhvcoe508fi7a foreign key (unit_id) references Unit
