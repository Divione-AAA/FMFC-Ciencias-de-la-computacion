
CREATE TABLE Autores(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    direccion VARCHAR(150) NOT NULL,
    ciudad VARCHAR(50) NOT NULL,
    estado VARCHAR(20) NOT NULL
)

-- Inserciones
INSERT INTO Autores (nombre, apellidos, telefono, direccion, ciudad, estado)
VALUES ('Juan', 'Pérez', '555-1234', 'Calle Falsa 123', 'Ciudad de México', 'CDMX');

INSERT INTO Autores (nombre, apellidos, telefono, direccion, ciudad, estado)
VALUES ('Ana', 'García', '555-5678', 'Av. Reforma 456', 'Guadalajara', 'Jalisco');

INSERT INTO Autores (nombre, apellidos, telefono, direccion, ciudad, estado)
VALUES ('Luis', 'Martínez', '555-9012', 'Calle Real 789', 'Monterrey', 'Nuevo León');

-- Consultas
SELECT * FROM Autores;

SELECT nombre, apellidos FROM Autores WHERE ciudad = 'Guadalajara';

SELECT COUNT(*) AS total_autores FROM Autores;

SELECT ciudad, COUNT(*) AS autores_por_ciudad FROM Autores GROUP BY ciudad;