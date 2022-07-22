CREATE SCHEMA department_store;
set search_path = "department_store";

-----------------------------
create table department_store.payment_type
(
    id   bigserial
        constraint payment_pk
            primary key,
    name varchar(250) not null
);

-----------------------------

create table department_store.employer_position
(
    id   bigserial
        constraint employer_position_pk
            primary key,
    name varchar(250) not null
);

-----------------------------

create table department_store.product_type
(
    id   bigserial
        constraint product_type_pk
            primary key,
    name varchar(250) not null
);

-----------------------------

create table department_store.branch_type
(
    id   bigserial
        constraint branch_type_pk
            primary key,
    name varchar(250) not null
);

-----------------------------

create table department_store.schedule
(
    id     bigserial
        constraint schedule_pk
            primary key,
    code   char(10) not null,
    "from" time     not null,
    "to"   time     not null
);

create unique index schedule_code_uindex
    on department_store.schedule (code);

create unique index schedule_from_to_uindex
    on department_store.schedule ("from", "to");

-----------------------------

create table department_store.order_products
(
    order_id       bigint,
    product_id     bigint,
    product_amount int default 1 not null,
    constraint order_products_pk
        primary key (order_id, product_id)
);

-----------------------------

create table department_store.product
(
    id      bigserial
        constraint product_pk
            primary key,
    name    varchar(120) not null,
    price   numeric(10, 2) not null,
    barcode numeric(15) not null,
    type    bigint not null
        constraint product_product_type_id_fk
            references department_store.product_type
);

-----------------------------

create table department_store.branch
(
    id      bigserial
        constraint branch_pk
            primary key,
    name    varchar(250) not null,
    address varchar(250) not null,
    type    bigint       not null
        constraint branch_branch_type_id_fk
            references department_store.branch_type
);

-----------------------------

create table department_store.employer
(
    id      bigserial
        constraint employer_pk
            primary key,
    full_name varchar(100) not null ,
    branch_id bigint not null
        constraint employer_branch_id_fk
            references department_store.branch,
    schedule_id bigint not null
        constraint employer_schedule_id_fk
            references department_store.schedule,
    position bigint not null
        constraint employer_employer_position_fk
            references department_store.employer_position,
    salary numeric(5)
);

-----------------------------

create table department_store.order
(
    id      bigserial
        constraint order_pk
            primary key,
    branch_id bigint not null
        constraint order_branch_id_fk
            references department_store.branch,
    employer_id bigint not null
        constraint order_employer_fk
            references department_store.employer,
    card_number numeric(16),
    payment_type bigint not null
        constraint order_payment_fk
            references department_store.payment_type
);
-----------------------------

INSERT INTO branch_type(name) VALUES ('ჰიპერმარკეტი');

INSERT INTO branch (name, address, type) VALUES ('ისთ ფოინთი', 'თვალჭრელერის 2', 1);

INSERT INTO schedule (code, "from", "to") VALUES ('09-16', '09:00', '16:00');
INSERT INTO schedule (code, "from", "to") VALUES ('16-22', '16:00', '22:00');

INSERT INTO employer_position (name) VALUES ('ყველაფერჩიკი');

INSERT INTO employer (full_name, branch_id, schedule_id, position, salary)
VALUES ('ირაკლი ახალკაციშვილი', 1, 2, 1, 12345);

INSERT INTO product_type (name) VALUES ('სასმელები');
INSERT INTO product_type (name) VALUES ('მისაყოლებლები');

INSERT INTO product (name, price, barcode, type)
VALUES ('coca-cola', 1.50, 010203040506070, 1);

INSERT INTO product (name, price, barcode, type)
VALUES ('lays', 5.5, 010203040506071, 2);

INSERT INTO payment_type (name) VALUES ('ქეში');

INSERT INTO "order" (branch_id, employer_id, payment_type)
VALUES (1, 1, 1);
-----------------------------