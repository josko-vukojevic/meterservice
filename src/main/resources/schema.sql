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
