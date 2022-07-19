create table shippers
(
    shipperid   serial
        constraint shipper_id_pk
            primary key,
    shippername varchar(255) default NULL::character varying,
    phone       varchar(255) default NULL::character varying
);

alter table shippers
    owner to postgres;

INSERT INTO w3schools.shippers (shipperid, shippername, phone) VALUES (1, 'Speedy Express', '(503) 555-9831');
INSERT INTO w3schools.shippers (shipperid, shippername, phone) VALUES (2, 'United Package', '(503) 555-3199');
INSERT INTO w3schools.shippers (shipperid, shippername, phone) VALUES (3, 'Federal Shipping', '(503) 555-9931');
