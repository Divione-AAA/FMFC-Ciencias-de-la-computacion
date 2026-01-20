-- Tabla de Autores
CREATE TABLE Authors (
    au_id       VARCHAR(20) PRIMARY KEY,
    au_lname    VARCHAR(40) NOT NULL,
    au_fname    VARCHAR(40) NOT NULL,
    phone       VARCHAR(20),
    address     VARCHAR(100),
    city        VARCHAR(40),
    state       VARCHAR(20)
);

-- Tabla de Empleados
CREATE TABLE Employee (
    emp_id      INT PRIMARY KEY,
    fname       VARCHAR(40) NOT NULL,
    minit       CHAR(1),
    lname       VARCHAR(40) NOT NULL,
    job_id      INT NOT NULL,
    job_lvl     INT,
    pb_id       INT NOT NULL
);

-- Tabla de Puestos de Trabajo
CREATE TABLE Jobs (
    job_id      INT PRIMARY KEY,
    job_desc    VARCHAR(50) NOT NULL,
    min_lvl     INT,
    max_lvl     INT
);

-- Tabla de Editoriales
CREATE TABLE Publisher (
    pub_id      INT PRIMARY KEY,
    pub_name    VARCHAR(100) NOT NULL,
    city        VARCHAR(40),
    state       VARCHAR(20),
    country     VARCHAR(40)
);

-- Tabla de Ventas
CREATE TABLE Sales (
    store_id    INT NOT NULL,
    ord_num     INT NOT NULL,
    id_title    VARCHAR(20) NOT NULL,
    ord_date    DATE,
    qty         INT,
    payterms    VARCHAR(40),
    PRIMARY KEY (store_id, ord_num, id_title)
);

-- Tabla de Almacenes
CREATE TABLE Stores (
    stor_id     INT PRIMARY KEY,
    stor_name   VARCHAR(100) NOT NULL,
    stor_address VARCHAR(100),
    city        VARCHAR(40),
    state       VARCHAR(20),
    zip         VARCHAR(10)
);

-- Tabla de Autores de Títulos
CREATE TABLE TitleAuthor (
    au_id       VARCHAR(20) NOT NULL,
    title_id    VARCHAR(20) NOT NULL,
    au_ord      INT,
    royaltyper  DECIMAL(5,2),
    PRIMARY KEY (au_id, title_id),
    FOREIGN KEY (au_id) REFERENCES Authors(au_id),
    FOREIGN KEY (title_id) REFERENCES Titles(title_id)
);

-- Tabla de Títulos
CREATE TABLE Titles (
    title_id    VARCHAR(20) PRIMARY KEY,
    title       VARCHAR(200) NOT NULL,
    type        VARCHAR(40),
    pub_id      INT NOT NULL,
    price       DECIMAL(10,2),
    advance     DECIMAL(10,2),
    royalty     INT,
    ytd_sales   INT,
    notes       VARCHAR(255),
    pubdate     DATE,
    FOREIGN KEY (pub_id) REFERENCES Publisher(pub_id)
);

ALTER TABLE Employee
    ADD CONSTRAINT fk_employee_job FOREIGN KEY (job_id) REFERENCES Jobs(job_id);

ALTER TABLE Employee
    ADD CONSTRAINT fk_employee_pub FOREIGN KEY (pb_id) REFERENCES Publisher(pub_id);

ALTER TABLE Sales
    ADD CONSTRAINT fk_sales_store FOREIGN KEY (store_id) REFERENCES Stores(stor_id);

ALTER TABLE Sales
    ADD CONSTRAINT fk_sales_title FOREIGN KEY (id_title) REFERENCES Titles(title_id);
