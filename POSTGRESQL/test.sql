--- Introduccion a PostgreSQL
--- Creacion de base de datos

CREATE DATABASE Nueva;

CREATE TABLE DatosPersona(
    ci_identidad VARCHAR(10),
    direccion direccionTipo,
    economia economiaType,
    familiares familiaresType,
    CONSTRAINT ci_identidad FOREIGN KEY(ci_identidad) REFERENCES Nombres(ci_identidad)
)

DROP TABLE Nombres;

CREATE TYPE direccionTipo AS(
    calle VARCHAR(20), 
    ciudad VARCHAR(20),
    estado VARCHAR(20),
    codigo_postal VARCHAR(20),
    numero_casa INTEGER
)

CREATE TYPE nivel_economico AS ENUM ('Alto', 'Bajo', 'Medio');
CREATE TYPE situacion AS ENUM ('Vulnerabilidad', 'Estable', 'Pobreza');
CREATE TYPE situacion_laboral AS ENUM ('Empleado', 'Desempleado', 'Emprendedor');

CREATE TYPE economiaType AS (
    IngresosEstimados INTEGER,
    Nivel_Economico nivel_economico,
    Situacion situacion,
    SituacionLaboral situacion_laboral
);

CREATE TYPE familiaresType AS (
    ci_familiares VARCHAR(10)[]
);

CREATE TABLE DatosPersona(
    ci_identidad FOREIGN KEY(ci_identidad) REFERENCES Nombres(ci_identidad),
    direccion direccionTipo,
    economia economiaType,
    familiares familiaresType
)