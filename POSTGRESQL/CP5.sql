CREATE DATABASE CP5

---Se crea para poder utilizarla en algunos incisos
CREATE TABLE Empleado (
    nroc INTEGER PRIMARY KEY,
    nombre VARCHAR(50),
    salario NUMERIC(12,2)
);
--- crea un rol
CREATE ROLE alumno
LOGIN; --- inicia sesion

--- Altera el rol
ALTER ROLE alumno
PASSWORD 'abc123';

---CREATE DATABASE prueba;

---concede funciones al rol
ALTER ROLE alumno
LOGIN
CREATEDB
CREATEROLE;

---CREATE DATABASE prueba2;

---crea el rol
CREATE ROLE prueba 
LOGIN;
SELECT * ---denegado 
FROM empleado;

--- creando role
CREATE ROLE ADMIN
NOLOGIN ---no entra al server solo agrupa permisos
CREATEDB
CREATEROLE;

--- da los mismos permsisos
GRANT SELECT
ON empleado
TO ADMIN;
GRANT ADMIN ---se los otorga

---consulta de prueba, (esta vacia la tabla pos la hice nueva)
TO alumno;
SELECT *
FROM empleado;

---CREATE DATABASE restaurada;