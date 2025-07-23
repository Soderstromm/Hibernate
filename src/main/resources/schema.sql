DROP TABLE IF EXISTS persons;

CREATE TABLE persons (
                         name VARCHAR(100),
                         surname VARCHAR(100),
                         age INT,
                         phone_number VARCHAR(20),
                         city_of_living VARCHAR(100),
                         PRIMARY KEY (name, surname, age)
);