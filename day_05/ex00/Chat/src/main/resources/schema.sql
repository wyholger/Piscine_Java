DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA IF NOT EXISTS chat;

CREATE TABLE IF NOT EXISTS chat.users (
    id SERIAL PRIMARY KEY,
    login text UNIQUE NOT NULL,
    password text NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.rooms (
    id SERIAL PRIMARY KEY,
    name text UNIQUE NOT NULL,
    owner INTEGER REFERENCES chat.users(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.massages (
    id SERIAL PRIMARY KEY,
    author INTEGER REFERENCES chat.users(id) NOT NULL,
    room INTEGER REFERENCES chat.rooms(id) NOT NULL,
    text text NOT NULL,
    timestamp timestamp NOT NULL
);

