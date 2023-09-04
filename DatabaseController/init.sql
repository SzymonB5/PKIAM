CREATE DATABASE dbname;
USE DATABASE dbname;

CREATE TABLE registered (
    user_id serial PRIMARY KEY,
    firstname VARCHAR (50) NOT NULL,
    lastname VARCHAR (100) NOT NULL,
    password varchar (27) NOT NULL,
    created_at TIMESTAMP DEFAULT current_timestamp
);

INSERT INTO registered (firstname, lastname, password)
VALUES
    ('John', 'Doe', 'mypassword123'),
    ('Jane', 'Smith', 'securepass456'),
    ('Michael', 'Johnson', 'strong_pw789');


CREATE TABLE frauds (
    user_id serial PRIMARY KEY,
    firstname VARCHAR (50) NOT NULL,
    lastname VARCHAR (100) NOT NULL
);

INSERT INTO frauds (firstname, lastname)
VALUES
    ('John', 'Fraud');
