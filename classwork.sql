--------------------------------------------------------------
-------------DDL
--------------------------------------------------------------
CREATE SCHEMA ob;

create table ob.owner
(
    id            bigserial
        constraint owner_pk
            primary key,
    code          varchar(11)  not null,
    owner_type_id int          not null,
    full_name     varchar(128) not null,
    creation_time date,
    address       varchar(512)
);

create unique index owner_code_uindex
    on ob.owner (code);

----------------------------------------------------
create table ob.vehicle
(
    id               bigserial
        constraint vehicle_pk
            primary key,
    manufacture_year numeric(4)  not null,
    mark             varchar(64) not null,
    model            varchar(64) not null,
    vin_code         char(17)    not null,
    millage          numeric(7)  not null,
    vehicle_type_id  numeric(19) not null,
    color            varchar(10),
    owner_id         bigint
);

create unique index vehicle_vin_uindex
    on ob.vehicle (vin_code);

-------------------------------------------------

create table ob.library
(
    id   numeric(19)
        constraint library_pk
            primary key,
    name varchar(16) not null,
    type varchar(16) not null
);

-------------------------------------------------

alter table ob.owner
    add constraint owner_library_id_fk
        foreign key (owner_type_id) references ob.library;

--------------------------------------------------

alter table ob.vehicle
    add constraint vehicle_library_id_fk
        foreign key (vehicle_type_id) references ob.library;

alter table ob.vehicle
    add constraint vehicle_owner_id_fk
        foreign key (owner_id) references ob.owner;

--------------------------------------------------------------
-------------DML
--------------------------------------------------------------

INSERT INTO ob.library (id, name, type)
VALUES (1, 'LEGAL ENTITY', 'OWNER TYPE');
INSERT INTO ob.library (id, name, type)
VALUES (2, 'PERSON', 'OWNER TYPE');
INSERT INTO ob.library (id, name, type)
VALUES (3, 'SUV', 'VEHICLE TYPE');
INSERT INTO ob.library (id, name, type)
VALUES (4, 'SEDAN', 'VEHICLE TYPE');
INSERT INTO ob.library (id, name, type)
VALUES (5, 'HECHBACK', 'VEHICLE TYPE');
INSERT INTO ob.library (id, name, type)
VALUES (6, 'COUPE', 'VEHICLE TYPE');

INSERT INTO ob.owner (code, owner_type_id, full_name, creation_time, address)
VALUES ('12345678901', 2, 'Nikoloz Chapidze', '2002-10-20', 'dolidze');
INSERT INTO ob.owner (code, owner_type_id, full_name, creation_time, address)
VALUES ('12345678902', 2, 'Nodar Aleksashvili', '2002-10-20', 'dolidze');
INSERT INTO ob.owner (code, owner_type_id, full_name, creation_time, address)
VALUES ('123456789', 1, 'Omedia', '2006-01-01', '6 Gegechkori st.');

INSERT INTO ob.vehicle (manufacture_year, mark, model, vin_code, millage, vehicle_type_id, color, owner_id)
VALUES (2022, 'Hyundai', 'Santa claus', '01234567890134567', 22, 3, 'Silver', 1);
INSERT INTO ob.vehicle (manufacture_year, mark, model, vin_code, millage, vehicle_type_id, color, owner_id)
VALUES (2000, 'BMW', '525', '01234567890134568', 110000, 4, 'Black', 2);
INSERT INTO ob.vehicle (manufacture_year, mark, model, vin_code, millage, vehicle_type_id, color, owner_id)
VALUES (2022, 'Cadilac', 'Escalade', '01234567890134569', 20000, 3, 'Pink', 3);
INSERT INTO ob.vehicle (manufacture_year, mark, model, vin_code, millage, vehicle_type_id, color, owner_id)
VALUES (2020, 'Lincoln', 'Navigator', '01234567890134566', 72000, 3, 'Red', 3);
