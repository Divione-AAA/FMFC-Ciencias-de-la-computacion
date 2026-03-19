--- Se crean las tablas (pregunta 1)

CREATE TABLE Producto (
    id_fabricante CHAR(3),
    id_producto VARCHAR(10),
    descripcion VARCHAR(50),
    precio NUMERIC(10,2),
    existencias INTEGER,
    
    CONSTRAINT pk_producto
    PRIMARY KEY (id_fabricante, id_producto)
);

CREATE TABLE Pedido (
    num_pedido INTEGER PRIMARY KEY,
    fechapedido DATE,
    cliente INTEGER,
    fabricante CHAR(3),
    producto VARCHAR(10),
    cant INTEGER,
    importe NUMERIC(10,2),
    
    CONSTRAINT fk_producto
    FOREIGN KEY (fabricante, producto)
    REFERENCES Producto(id_fabricante, id_producto)
);

---Insercciones

INSERT INTO Producto VALUES
('REI','2A44L','BISAGRA IZQ.',4500.00,12),
('ACI','41003','ARTICULO TIPO 3',107.00,207),
('FEA','114','BANCADA MOTOR',243.00,15),
('QSA','XK44','REDUCTOR',355.00,38),
('ACI','41002','ARTICULO TIPO 2',76.00,167),
('ACI','41004','ARTICULO TIPO 4',117.00,207);

INSERT INTO Pedido VALUES
(112961,'2013-12-17',2117,'REI','2A44L',7,31500.00),
(113012,'2014-01-11',2111,'ACI','41003',35,3745.00),
(112989,'2014-01-03',2101,'FEA','114',6,1458.00),
(113051,'2014-02-10',2117,'QSA','XK44',4,1420.00),
(112968,'2013-10-12',2102,'ACI','41004',34,3978.00),
(110036,'2014-12-30',2117,'ACI','41002',9,22500.00);

--- Consultas y mAnipulaciones

DELETE FROM Pedido
WHERE num_pedido = 112968;

UPDATE Producto
SET existencias = existencias * 2;

UPDATE Producto
SET precio = precio * 0.90
WHERE id_fabricante = 'ACI'
AND precio > 100;

--- Creacion de las vistas y muestra de ellas

CREATE VIEW pedidos_2117 AS
SELECT *
FROM Pedido
WHERE cliente = 2117;

SELECT * FROM pedidos_2117;

CREATE VIEW total_pedidos_cliente AS
SELECT cliente,
       SUM(importe) AS total
FROM Pedido
GROUP BY cliente;

SELECT * FROM total_pedidos_cliente;

--- Regla

CREATE RULE actualizar_existencias AS
ON DELETE TO Pedido
DO ALSO
UPDATE Producto
SET existencias = existencias - OLD.cant
WHERE id_fabricante = OLD.fabricante
AND id_producto = OLD.producto;

DELETE FROM Pedido
WHERE num_pedido = 112961;

SELECT * FROM Producto;