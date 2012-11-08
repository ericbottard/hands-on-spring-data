CREATE TABLE address
(
   address_id smallint PRIMARY KEY NOT NULL,
   address varchar(50) NOT NULL,
   address2 varchar(50),
   district varchar(20) NOT NULL,
   city_id smallint NOT NULL,
   postal_code varchar(10),
   phone varchar(20) NOT NULL,
   last_update timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL
)
;
CREATE TABLE city
(
   city_id smallint PRIMARY KEY NOT NULL,
   city varchar(50) NOT NULL,
   country_id smallint NOT NULL,
   last_update timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL
)
;
CREATE TABLE country
(
   country_id smallint PRIMARY KEY NOT NULL,
   country varchar(50) NOT NULL,
   last_update timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL
)
;
CREATE TABLE customer
(
   customer_id smallint PRIMARY KEY NOT NULL,
   store_id tinyint NOT NULL,
   first_name varchar(45) NOT NULL,
   last_name varchar(45) NOT NULL,
   email varchar(50),
   address_id smallint NOT NULL,
   active bit DEFAULT 1 NOT NULL,
   create_date timestamp NOT NULL,
   last_update timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL
)
;
ALTER TABLE address ADD CONSTRAINT fk_address_city FOREIGN KEY
(
   city_id
)
REFERENCES city(city_id) ON
UPDATE CASCADE
;
CREATE UNIQUE INDEX ON address(address_id)
;
CREATE INDEX idx_fk_city_id ON address(city_id)
;
ALTER TABLE city ADD CONSTRAINT fk_city_country FOREIGN KEY
(
   country_id
)
REFERENCES country(country_id) ON
UPDATE CASCADE
;
CREATE UNIQUE INDEX ON city(city_id)
;
CREATE INDEX idx_fk_country_id ON city(country_id)
;
CREATE UNIQUE INDEX ON country(country_id)
;
ALTER TABLE customer ADD CONSTRAINT fk_customer_address FOREIGN KEY
(
   address_id
)
REFERENCES address(address_id) ON
UPDATE CASCADE
;
CREATE UNIQUE INDEX ON customer(customer_id)
;
CREATE INDEX idx_last_name ON customer(last_name)
;
CREATE INDEX idx_fk_store_id ON customer(store_id)
;
CREATE INDEX idx_fk_address_id ON customer(address_id)
;

