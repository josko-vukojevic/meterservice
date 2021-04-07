DROP TABLE IF EXISTS meter_readings;
DROP TABLE IF EXISTS meters;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS addresses;
CREATE TABLE addresses (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    street_name VARCHAR(30) NOT NULL,
    house_number INT NOT NULL
);
CREATE TABLE clients (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    address_id INT,
    FOREIGN KEY (address_id) REFERENCES addresses(id)
);
CREATE TABLE meters (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    client_id INT,
    FOREIGN KEY (client_id) REFERENCES clients(id)
);
CREATE TABLE meter_readings (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    month_of_consuption VARCHAR(10) NOT NULL,
    year_of_consuption INT NOT NULL,
    kwh INT NOT NULL,
    meter_id INT,
    FOREIGN KEY (meter_id) REFERENCES meters(id)
);
INSERT INTO addresses(street_name, house_number) VALUES
    ('address1', 10),
    ('address2', 20),
    ('address3', 30),
    ('address4', 40);
INSERT INTO clients(name, address_id) VALUES
    ('name1', 1),
    ('name2', 2),
    ('name3', 3);
INSERT INTO meters(client_id) VALUES
    (1),
    (2);
INSERT INTO meter_readings(month_of_consuption, year_of_consuption, kwh, meter_id) VALUES
    ('January', 2021, 10, 1),
    ('March', 2020, 11, 1),
    ('February', 2020, 12, 1),
    ('May', 2020, 19, 1),
    ('April', 2021, 44, 1),
    ('January', 2021, 10, 2),
    ('March', 2021, 11, 2),
    ('February', 2021, 12, 2),
    ('May', 2021, 54, 2),
    ('April', 2020, 24, 2);

