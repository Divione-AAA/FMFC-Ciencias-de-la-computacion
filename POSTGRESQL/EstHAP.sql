CREATE DATABASE Estudiante;

--- Se crea primero la restriccion

CREATE DOMAIN dom_cid AS CHAR(11)
CHECK (VALUE ~ '^[0-9]{11}$');

--- Creando las tablas

CREATE TABLE Estgral (
    cid dom_cid PRIMARY KEY,
    nombres VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    sexo CHAR(1),
    fechaingreso DATE DEFAULT CURRENT_DATE
);

CREATE TABLE EstAA (
    cid dom_cid PRIMARY KEY,
    nivel INTEGER,
    salario NUMERIC(10,2),
    fnombramiento DATE,
    CONSTRAINT fk_est FOREIGN KEY (cid) REFERENCES Estgral(cid)
);

--- Se crean las resticciones especificas

ALTER TABLE EstAA
ADD CONSTRAINT ck_nivel
CHECK (nivel IN (1,2,3,4));

ALTER TABLE Estgral
ADD CONSTRAINT ck_sexo
CHECK (sexo IN ('F','f','M','m'));

--- Segunda pregunta 
--- Se crea la estructura

CREATE TYPE procsocial AS ENUM ('Obrero','Campesino');

--- TAbla de nota

CREATE TABLE Evaluacion (
    cid dom_cid,
    asignatura VARCHAR(50),
    notas INTEGER[],
    CONSTRAINT fk_eval FOREIGN KEY (cid) REFERENCES Estgral(cid)
);

--- Consultas e insercciones

INSERT INTO Evaluacion
VALUES ('12345678901','Matematica',ARRAY[90,85,88]),
       ('12345678902','Fisica',ARRAY[70,75]);

SELECT cid, asignatura, notas[1] AS primera_prueba FROM Evaluacion;

--- Cuatro
--- Creamos el tipo de dato

CREATE TYPE direccion AS (
    calle VARCHAR(50),
    numero INTEGER,
    municipio VARCHAR(50),
    provincia VARCHAR(50)
);

--- Alteramos la tabla

ALTER TABLE Estgral ADD COLUMN dir direccion;