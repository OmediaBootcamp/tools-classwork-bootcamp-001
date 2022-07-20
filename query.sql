SELECT *
FROM categories;
SELECT distinct tbl.alc_name
FROM (SELECT c.customerid
           , c.customername alc_name
           , p.productname
           , ct.categoryname
      FROM customers c
               Join orders o
                    on c.customerid = o.customerid
               join order_details od
                    on od.orderid = o.orderid
               join products p
                    on od.productid = p.productid
               join categories ct
                    on ct.categoryid = p.categoryid
      where ct.categoryid = 1) tbl;


select *
from v_customer_order
where customer_city = 'Bern'
  and category_id = 1;
INSERT INTO w3schools.customers (customername, contactname, address, city, postalcode, country)
VALUES ('Beqa Archuadze', 'Beqa Archuadze', 'temqa', 'Tbilisi', '11101', 'Georgia');

CREATE VIEW v_customer_location AS
SELECT Distinct c.country
              , c.city
FROM customers c
order by c.country, c.city;


CREATE OR REPLACE PROCEDURE select_customers_by_country(p_country varchar(255))
    LANGUAGE SQL
    AS $$
DELETE
FROM customers
WHERE country=p_country;
$$;

CALL select_customers_by_country('Georgia');