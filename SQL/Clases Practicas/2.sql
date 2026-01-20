/* DROP TABLE IF EXISTS TitleAuthor;
DROP TABLE IF EXISTS Sales;
DROP TABLE IF EXISTS Titles;
DROP TABLE IF EXISTS Authors;
DROP TABLE IF EXISTS Publishers;

CREATE TABLE Authors (
    au_id VARCHAR(10) PRIMARY KEY,
    au_fname VARCHAR(30),
    au_lname VARCHAR(30),
    phone VARCHAR(15)
);

CREATE TABLE Publishers (
    pub_id VARCHAR(10) PRIMARY KEY,
    pub_name VARCHAR(50)
);

CREATE TABLE Titles (
    title_id VARCHAR(10) PRIMARY KEY,
    title VARCHAR(80),
    type VARCHAR(30),
    pub_id VARCHAR(10),
    price DECIMAL(6,2),
    advance DECIMAL(8,2),
    ytd_sales INT,
    FOREIGN KEY (pub_id) REFERENCES Publishers(pub_id)
);

CREATE TABLE TitleAuthor (
    au_id VARCHAR(10),
    title_id VARCHAR(10),
    au_ord INT,
    royaltyper INT,
    PRIMARY KEY (au_id, title_id),
    FOREIGN KEY (au_id) REFERENCES Authors(au_id),
    FOREIGN KEY (title_id) REFERENCES Titles(title_id)
);

CREATE TABLE Sales (
    sale_id INT PRIMARY KEY,
    title_id VARCHAR(10),
    qty INT,
    FOREIGN KEY (title_id) REFERENCES Titles(title_id)
);

-- AUTORES
INSERT INTO Authors VALUES
('A1','John','White','415-1111'),
('A2','Marjorie','Green','212-2222'),
('A3','Carlos','Diaz','305-3333'),
('A4','Ana','Lopez','305-4444'),
('A5','Robert','King','718-5555');

-- EDITORIALES
INSERT INTO Publishers VALUES
('P1','TechBooks'),
('P2','BusinessPress'),
('P3','EduWorld');

-- TITULOS
INSERT INTO Titles VALUES
('T1','SQL Fundamentals','business','P2',40,5000,12000),
('T2','Advanced Databases','tech','P1',55,8000,15000),
('T3','Intro to Programming','edu','P3',35,3000,9000),
('T4','Business Guide','business','P2',45,7000,20000),
('T5','Web Development','tech','P1',50,6000,11000);

-- RELACION AUTOR-TITULO
INSERT INTO TitleAuthor VALUES
('A1','T1',1,100),
('A2','T1',2,50),
('A2','T2',1,80),
('A3','T3',1,70),
('A4','T4',1,100),
('A5','T5',1,60);

-- VENTAS
INSERT INTO Sales VALUES
(1,'T1',500),
(2,'T2',600),
(3,'T3',300),
(4,'T4',800),
(5,'T5',400);
 */

-- 1
SELECT a.au_fname, a.au_lname, tl.title
FROM authors a JOIN titleauthor t ON a.au_id = t.au_id
    JOIN titles tl ON tl.title_id = t.title_id
WHERE au_ord = 1

-- 2
SELECT a.au_fname, p.pub_name
FROM authors a, Publisher p

-- 3
SELECT  a.ytd_sales, ((a.ytd_sales*a.royalty)/100 ) AS debitoAutor,
        (a.ytd_sales-((a.ytd_sales*a.royalty)/100)) AS debitoEditorial
FROM Authors a
    JOIN TitleAuthor ta ON a.au_id = ta.au_id
    JOIN Titles t ON ta.title_id = t.title_id
    JOIN Publisher p ON t.pub_id = p.pub_id
ORDER BY t.ytd_sales DESC, a.au_fname ASC;

-- 4
SELECT a.au_fname, a.au_lname
FROM Authors a
WHERE a.au_id NOT IN (SELECT t.au_id
FROM TitleAuthor t);
-- Se leeria como selecciona nombre de autores que no esten sus id en y hace una subconsulta pidiendo las id de los titulos por autor

-- 5
SELECT p.pub_name
FROM Publisher p
JOIN Titles t ON t.pub_id = p.pub_id
WHERE t.title_id IS NULL;

-- 8
SELECT t.type
FROM Titles t
WHERE MAX(advance) > 2 * AVG(advance);

-- 9
SELECT ta.au_id
FROM TitleAuthor ta
JOIN Titles t ON ta.title_id = t.title_id
JOIN Publisher p ON t.pub_id = p.pub_id
GROUP BY ta.au_id
HAVING COUNT(DISTINCT p.pub_id) = (SELECT COUNT(*) FROM Publisher);