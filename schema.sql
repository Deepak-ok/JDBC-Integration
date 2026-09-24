-- Run this in MySQL Workbench or the mysql CLI before running the app.

CREATE DATABASE IF NOT EXISTS weintern_db;

USE weintern_db;

CREATE TABLE IF NOT EXISTS students (
    id     INT PRIMARY KEY,
    name   VARCHAR(100) NOT NULL,
    age    INT,
    grade  DOUBLE
);
