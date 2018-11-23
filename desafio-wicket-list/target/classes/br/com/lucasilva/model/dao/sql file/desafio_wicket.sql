﻿CREATE DATABASE desafio_wicket;

CREATE TABLE Endereco(
  ID_ENDERECO INT,
  RUA VARCHAR(100) NOT NULL,
  COMPLEMENTO VARCHAR(100),
  NUMERO INT(10),
  UF CHAR(2) NOT NULL,
  CIDADE VARCHAR(100) NOT NULL,
  CEP INT(8) NOT NULL,
  CONSTRAINT PK_ENDERECO PRIMARY KEY(ID_ENDERECO)
);

CREATE TABLE User(
  ID_USER INT NOT NULL AUTO_INCREMENT,
  ID_ENDERECO INT NOT NULL,
  NOME VARCHAR(50) NOT NULL,
  SBNM VARCHAR(150) NOT NULL,
  CPF BIGINT(11) NOT NULL UNIQUE,
  RG INT(8),
  TELEFONE INT(11) NOT NULL,
  EMAIL VARCHAR(150) NOT NULL UNIQUE,
  SENHA VARCHAR(50) NOT NULL,
  CONSTRAINT PK_USER PRIMARY KEY(ID_USER),
  CONSTRAINT FK_END_USER FOREIGN KEY(ID_ENDERECO) REFERENCES Endereco(ID_ENDERECO)
);
