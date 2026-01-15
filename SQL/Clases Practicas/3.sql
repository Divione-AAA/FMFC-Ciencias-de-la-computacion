/* DROP TABLE IF EXISTS Sales;
DROP TABLE IF EXISTS Stores;
DROP TABLE IF EXISTS Employees;
DROP TABLE TitleAuthor;
DROP TABLE Titles;
DROP TABLE IF EXISTS Vendors;

CREATE TABLE Stores (
    stor_id INT PRIMARY KEY,
    store_name VARCHAR(40),
    stor_address VARCHAR(60),
    city VARCHAR(30),
    state VARCHAR(5),
    zip VARCHAR(10)
);

CREATE TABLE Titles (
    title_id VARCHAR(10) PRIMARY KEY,
    title VARCHAR(80)
);

CREATE TABLE Sales (
    sale_id INT PRIMARY KEY,
    title_id VARCHAR(10),
    stor_id INT,
    order_qty INT,
    ord_date DATE
);

CREATE TABLE Employees (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(40),
    hire_date DATE,
    job_lvl INT,
    stor_id INT
);

CREATE TABLE Vendors (
    vendor_id INT PRIMARY KEY,
    vendor_name VARCHAR(40)
);

-- DATOS

INSERT INTO Stores VALUES
(7066,'Book Planet','Main St','LA','CA','90001'),
(8000,'Readers Hub','Central Ave','NY','NY','10001'),
(9000,'Old Books','5th Street','TX','TX','75001');

INSERT INTO Titles VALUES
('T1','SQL Guide'),
('T2','Database Design');

INSERT INTO Sales VALUES
(1,'T1',7066,5,'1998-05-10'),
(2,'T2',7066,8,'1997-03-15'),
(3,'T1',8000,2,'1999-02-20');

INSERT INTO Employees VALUES
(1,'Carlos Perez','1987-06-01',NULL,7066),
(2,'Ana Gomez','1990-01-10',2,8000),
(3,'Luis Torres','1986-04-20',NULL,7066);

INSERT INTO Vendors VALUES
(1,'Global Books'),
(2,'Distribuciones SA'); */

--- Formato de insercion para valores especificando columnas
INSERT INTO Stores (stor_id,store_name,city,state)
VALUES (1111,"VietNam","Stgo","CU")

--- Eliminar tiendas con ventas menores a 10
DELETE FROM Stores WHERE stor_id IN (
    SELECT stor_id FROM Sales WHERE qty < 10
)

DELETE FROM Employees WHERE emp_id IN(
    SELECT emp_id FROM Employees WHERE job_lvl IS NULL AND hire_date<='1978'
)

--- Actualizar nombres de tiendas de 7066 a VietNam
UPDATE Stores SET store_name='VietNam' where store_name='7066' 

--- Actualizar el nivel de empleados que no tienen un nivel de empleado a 3
UPDATE Employees SET job_lvl=3 WHERE job_lvl=NULL

ALTER TABLE Vendors ADD phone VARCHAR(10)