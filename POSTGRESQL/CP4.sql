--- Creacion de las base de datos
CREATE Database CP4

CREATE TABLE Dpto (
    dno INTEGER PRIMARY KEY,
    nombred VARCHAR(50),
    salariot NUMERIC(12,2)
);

CREATE TABLE Empleado (
    nroc INTEGER PRIMARY KEY,
    nombre VARCHAR(50),
    salario NUMERIC(12,2),
    dno INTEGER REFERENCES Dpto(dno)
);

