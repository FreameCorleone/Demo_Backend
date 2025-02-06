CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP NULL
);

CREATE TABLE genero (
    id_genero INT AUTO_INCREMENT PRIMARY KEY,
    nome_genero VARCHAR(45) NOT NULL,
    status BIT DEFAULT 1
);

CREATE TABLE livro (
    id_livro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    editora VARCHAR(255) NOT NULL,
    ano_publicacao YEAR NOT NULL,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    num_paginas INT(11) NOT NULL,
    sinopse TEXT,
    idioma VARCHAR(50) NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    preco DECIMAL(10,2) NOT NULL,
    genero_id_genero INT,
    FOREIGN KEY (genero_id_genero) REFERENCES genero(id_genero) ON DELETE SET NULL ON UPDATE CASCADE
);
