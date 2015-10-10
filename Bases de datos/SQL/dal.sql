DROP DATABASE IF EXISTS DALBD;
CREATE DATABASE DALBD;

USE DALBD;

DROP TABLE IF EXISTS colegio;
CREATE TABLE colegio(
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(100) NULL,
	PRIMARY KEY (id) 
);

DROP TABLE IF EXISTS autor;
CREATE TABLE autor(
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(100) NOT NULL, 
	apellido1 VARCHAR(100) NOT NULL, 
	apellido2 VARCHAR(100) NULL, 
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS multimedia;
CREATE TABLE multimedia(
	id INT NOT NULL AUTO_INCREMENT, 
	idGrupo INT NOT NULL, 
	nombre VARCHAR(100) NOT NULL, 
	descrip VARCHAR(250) NULL, 
	tipo VARCHAR(10) NOT NULL, 
	archivo BLOB NOT NULL, 
	numTelefono VARCHAR(20) NOT NULL, 
	idAutor INT NOT NULL,
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS grupo;
CREATE TABLE grupo(
	id INT NOT NULL AUTO_INCREMENT,
	idProfesor VARCHAR(20) NOT NULL, 
	idColegio INT NOT NULL, 
	nombre VARCHAR(100) NOT NULL, 
	nombreMateria VARCHAR(100) NOT NULL, 
	tema VARCHAR(50) NOT NULL, 
	clave VARCHAR(50) NOT NULL, 
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS profesor;
CREATE TABLE profesor(
	nombreUsuario VARCHAR(20) NOT NULL,
	clave VARCHAR(50) NOT NULL,
	idDetalle INT NOT NULL, 
	PRIMARY KEY (nombreUsuario)
);

DROP TABLE IF EXISTS detalleProfesor;
CREATE TABLE detalleProfesor(
	id INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(100) NOT NULL, 
	apellido1 VARCHAR(100) NOT NULL, 
	apellido2 VARCHAR(100) NULL, 
	correo VARCHAR(250) NULL,
	PRIMARY KEY (id)
);

ALTER TABLE grupo ADD FOREIGN KEY (idColegio) REFERENCES colegio(id);
ALTER TABLE grupo ADD FOREIGN KEY (idProfesor) REFERENCES profesor(nombreUsuario);
ALTER TABLE profesor ADD FOREIGN KEY (idDetalle) REFERENCES detalleProfesor(id);
ALTER TABLE multimedia ADD FOREIGN KEY (idAutor) REFERENCES autor(id);
ALTER TABLE multimedia ADD FOREIGN KEY (idGrupo) REFERENCES grupo(id);