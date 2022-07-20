

create table w3schools.categories
(
    categoryid   serial
        constraint category_id_pk
            primary key,
    categoryname varchar(255) default NULL::character varying,
    description  varchar(255) default NULL::character varying
);

alter table w3schools.categories
    owner to postgres;

INSERT INTO w3schools.categories (categoryid, categoryname, description) VALUES (1, 'Beverages', 'Soft drinks, coffees, teas, beers, and ales');
INSERT INTO w3schools.categories (categoryid, categoryname, description) VALUES (2, 'Condiments', 'Sweet and savory sauces, relishes, spreads, and seasonings');
INSERT INTO w3schools.categories (categoryid, categoryname, description) VALUES (3, 'Confections', 'Desserts, candies, and sweet breads');
INSERT INTO w3schools.categories (categoryid, categoryname, description) VALUES (4, 'Dairy Products', 'Cheeses');
INSERT INTO w3schools.categories (categoryid, categoryname, description) VALUES (5, 'Grains/Cereals', 'Breads, crackers, pasta, and cereal');
INSERT INTO w3schools.categories (categoryid, categoryname, description) VALUES (6, 'Meat/Poultry', 'Prepared meats');
INSERT INTO w3schools.categories (categoryid, categoryname, description) VALUES (7, 'Produce', 'Dried fruit and bean curd');
INSERT INTO w3schools.categories (categoryid, categoryname, description) VALUES (8, 'Seafood', 'Seaweed and fish');
