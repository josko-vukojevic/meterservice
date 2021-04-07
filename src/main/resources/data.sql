INSERT INTO addresses(street_name, house_number) VALUES
    ('aaa', 10),
    ('bbb', 20),
    ('ccc', 30),
    ('ddd', 40);
INSERT INTO clients(name, address_id) VALUES
    ('name1', 1),
    ('name2', 2),
    ('name3', 3),
    ('name4', 4);
INSERT INTO meters(client_id) VALUES
    (1),
    (2),
    (3),
    (4);
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
    ('April', 2020, 24, 2),
    ('January', 2020, 20, 3),
    ('March', 2020, 31, 3),
    ('February', 2021, 12, 3),
    ('May', 2021, 13, 3),
    ('April', 2020, 43, 3),
    ('January', 2020, 2, 4),
    ('March', 2021, 18, 4),
    ('February', 2020, 17, 4),
    ('May', 2021, 67, 4);