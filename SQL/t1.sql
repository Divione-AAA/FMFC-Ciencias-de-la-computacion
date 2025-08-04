CREATE DATABASE t1;

CREATE TABLE Autores(
    id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    direccion VARCHAR(150) NOT NULL,
    ciudad VARCHAR(50) NOT NULL,
    estado VARCHAR(20) NOT NULL
)

--CREATE TABLE Empleados(
--    id INT NOT NULL AUTO_INCREMENT
--    nombre
--   apellidos
--    minit
--)