/*CREATE DATABASE Pedidos;
*/
CREATE TABLE Clientes (
    ClienteID INT PRIMARY KEY,
    Nombre VARCHAR(100),
    Email VARCHAR(100)
);

CREATE TABLE Productos (
    ProductoID INT PRIMARY KEY,
    Nombre VARCHAR(100),
    Precio DECIMAL(10,2)
);

CREATE TABLE Pedidos (
    PedidoID INT PRIMARY KEY,
    ClienteID INT,
    ProductoID INT,
    Cantidad INT,
    FechaPedido DATE,
    FOREIGN KEY (ClienteID) REFERENCES Clientes(ClienteID),
    FOREIGN KEY (ProductoID) REFERENCES Productos(ProductoID)
);
*/
INSERT INTO Clientes (ClienteID, Nombre, Email) VALUES (1, 'David', 'david@email.com');

INSERT INTO Productos (ProductoID, Nombre, Precio) VALUES (1, 'Laptop', 800.00);

INSERT INTO Pedidos (PedidoID, ClienteID, ProductoID, Cantidad, FechaPedido) 
VALUES (1, 1, 1, 2, '2025-04-04');

SELECT * FROM Pedidos WHERE ClienteID = 1;

UPDATE Productos SET Precio = 850.00 WHERE ProductoID = 1;
