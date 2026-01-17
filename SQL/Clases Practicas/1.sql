/* CREATE TABLE Publisher
(
    pub_id VARCHAR(10),
    pub_name VARCHAR(50),
    city VARCHAR(30),
    state VARCHAR(10),
    country VARCHAR(20)
);

INSERT INTO Publisher
VALUES
    ('1389', 'TechBooks', 'New York', 'NY', 'USA'),
    ('1622', 'CodePress', 'Boston', 'MA', 'USA'),
    ('9901', 'DevHouse', 'San Francisco', 'CA', 'USA');
 */

/* -- SALES
CREATE TABLE Sales (
    store_id VARCHAR(10),
    ord_num VARCHAR(10),
    title_id VARCHAR(10),
    ord_date DATE,
    qty INT,
    payterms VARCHAR(20)
);

INSERT INTO Sales VALUES
('S1','O1','BU1032','2000-05-10',50,'Net 30'),
('S2','O2','BU1032','2000-06-15',30,'Net 30'),
('S3','O3','T1','2001-02-20',40,'Net 60'),
('S4','O4','T2','2000-07-18',20,'Net 30'),
('S1','O5','T3','1999-03-12',60,'Net 60');

-- TITLES (agregamos BU1032)
INSERT INTO Titles VALUES
('BU1032','Business SQL','business','1389',25.00,8000,12,3000,'SQL for business','1999-09-10');

*/

/* -- AUTHORS
CREATE TABLE Authors (
    au_id VARCHAR(10),
    au_lname VARCHAR(30),
    au_fname VARCHAR(30),
    phone VARCHAR(15),
    address VARCHAR(50),
    city VARCHAR(30),
    state VARCHAR(10)
);

INSERT INTO Authors VALUES
('A1','Smith','John','415-1111','123 Main St','Seattle','WA'),
('A2','Brown','Lisa','206-2222','456 Oak St','Seattle','WA'),
('A3','Taylor','Mark','503-3333','789 Pine St','Portland','OR'),
('A4','Wilson','Anna','415-4444','321 Cedar St','San Jose','CA'),
('A5','Davis','Paul','213-5555','654 Maple St','Los Angeles','CA');

-- STORES
CREATE TABLE Stores (
    stor_id VARCHAR(10),
    stor_name VARCHAR(50),
    stor_address VARCHAR(50),
    city VARCHAR(30),
    state VARCHAR(10),
    zip VARCHAR(10)
);

INSERT INTO Stores VALUES
('S1','Book World','12 Market St','Seattle','WA','98101'),
('S2','Readers Hub','45 Sunset Blvd','Los Angeles','CA','90001'),
('S3','City Books','78 Ocean Ave','San Diego','CA','92101'),
('S4','Page Turners','90 River Rd','Portland','OR','97201');

-- TITLES
CREATE TABLE Titles (
    title_id VARCHAR(10),
    title VARCHAR(50),
    type VARCHAR(30),
    pub_id VARCHAR(10),
    price DECIMAL(6,2),
    advance DECIMAL(8,2),
    royalty INT,
    ytd_sales INT,
    notes VARCHAR(100),
    pubdate DATE
);

INSERT INTO Titles VALUES
('T1','SQL Basics','business','1389',19.99,5000,10,1200,'Intro to SQL','2000-05-10'),
('T2','Advanced SQL','business','1389',29.99,7000,12,800,'Deep SQL','2000-08-20'),
('T3','Web Design','tech','1622',24.99,6000,8,1500,'HTML & CSS','1999-04-15'),
('T4','Java Guide','tech','9901',34.99,9000,15,2000,'Java Dev','2001-03-12'),
('T5','Python 101','tech','1389',22.99,6500,9,1800,'Python Basics','2000-11-01');

SELECT * FROM Authors; */

-- Listar los autores ordenados por ciudad y estado
SELECT *
FROM Authors a
ORDER BY a.city, a.state;

-- Obtener tienda ordenadas por estado descendiente y ciudad ascendente
SELECT *
FROM Stores s
ORDER BY s.state DESC, s.city ASC;

-- Cuantos autores son de Seattle
SELECT COUNT(*)
FROM Authors a
WHERE a.city = 'Seattle';

-- Títulos del año 2000 publicados por la editorial 1389, ordenados por título
SELECT *
FROM Titles
WHERE pubdate BETWEEN '2000-01-01' AND '2000-12-31' AND pub_id = '1389'
ORDER BY title;

-- Títulos publicados por editoriales 1389, 1622, 9901, ordenados por precio
SELECT *
FROM Titles
WHERE pub_id IN ('1389', '1622', '9901')
ORDER BY price;

-- Obtener el rango de precios de los títulos (mínimo y máximo)
SELECT MIN(price), MAX(price)
FROM Titles;

-- Apellido y teléfono de autores con código de área 415, ordenados por apellido
SELECT au_lname, phone
FROM Authors
WHERE phone LIKE '415%'
ORDER BY au_lname;

-- Buscar titulos con notas de 50
SELECT *
FROM Titles
WHERE notes LIKE '%50% off%';

-- Nombres que contengan ry
SELECT title
FROM Titles
WHERE title LIKE '___ryl%';

-- Apellidos similares a Carson, Karson, Carsen, Karsen
SELECT au_lname
FROM Authors
WHERE au_lname LIKE '%Carson%';

-- Seleccionar cantidad de titulos vendidos de BUS1032
SELECT SUM(qty) AS Total
FROM Sales
WHERE title_id LIKE 'BU1032';

-- Obtener promedio de los titulos de tipo bussness
SELECT AVG(price) AS Promedio
FROM Titles
WHERE type = 'business';

-- Por cada tipo de titulo obtener total de precios y adelantos
SELECT type, SUM(price) AS Total, SUM(advance) AS Adelanto
FROM Titles
GROUP BY type;

-- Por cada tipo de titulo obtener cantidad de titulos y promedio de precios
SELECT type, COUNT(*) AS cantidad, AVG(price) AS promedio
FROM Titles
WHERE advance > 1000
GROUP BY type;

-- Cantidad de ventas por ano
SELECT YEAR(ord_date) AS anio, COUNT(*) AS ventas
FROM Sales
GROUP BY anio;