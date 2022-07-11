--liquibase formatted sql
--changeset kruglov:createTables

CREATE TYPE role AS ENUM ('CLIENT', 'ADMIN');

CREATE TABLE roles (
    id serial PRIMARY KEY,
    name role
);

CREATE TABLE users (
    user_id serial PRIMARY KEY,
    first_name varchar(180),
    last_name varchar(180),
    middle_name varchar(180),
    login varchar(180),
    password varchar(180),
    role_id INT REFERENCES roles(id)
);

CREATE TABLE clients (
    user_id INT REFERENCES users (user_id),
    number_phone varchar(180)
);

CREATE TABLE administration (
    user_id INT REFERENCES users (user_id),
    position varchar(180)
);

CREATE TABLE authors (
    id serial PRIMARY KEY,
    first_name varchar(180),
    last_name varchar(180),
    middle_name varchar(180),
    short_biography varchar(1300)
);

CREATE TABLE books (
    id serial PRIMARY KEY, 
    description varchar(900),
    genre varchar(180),
    title varchar(180),
    year_of_production INT
);

CREATE TABLE books_authors (
    book_id INT REFERENCES books (id),
    author_id INT REFERENCES authors (id)
);

CREATE TABLE reviews (
    id serial PRIMARY KEY,
    mark INT,
    text varchar(180),
    book_id INT REFERENCES books(id),
    user_id INT REFERENCES users (user_id)
);