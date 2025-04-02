-- Creando la base de datos

CREATE DATABASE ejemplo;

CREATE TABLE User (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'INACTIVE',
    username VARCHAR(50) UNIQUE NOT NULL,
    mail VARCHAR(100) UNIQUE NOT NULL,
    age INT UNSIGNED NOT NULL,
    bio TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Coments (
    tittle VARCHAR('50'),
    id INT AUTO_INCREMENT PRIMARY KEY
    user_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES User(id)
);