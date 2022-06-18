CREATE TABLE USUARIO (
    ID INTEGER NOT NULL AUTO_INCREMENT,
    NOMBRE VARCHAR(50),
    APELLIDO VARCHAR(50),
    EMAIL VARCHAR(150),
	CLAVE_ENCRIPTADA VARCHAR(50),
    PRIMARY KEY (ID)
);

CREATE TABLE CATEGORIA (
    ID INTEGER NOT NULL AUTO_INCREMENT,
    NOMBRE VARCHAR(50),
    NOMBRE_INGLES VARCHAR(50),
    PRIMARY KEY (ID)
);

CREATE TABLE FRASE_USADA (
    ID INTEGER NOT NULL AUTO_INCREMENT,
    USUARIO_ID INTEGER,
    PRIMARY KEY (ID),
    CONSTRAINT FK_FRASE_USADA_USUARIO FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO(ID)
);

CREATE TABLE PICTOGRAMA (
    ID INTEGER NOT NULL AUTO_INCREMENT,
    IMAGEN VARCHAR(250),
    NOMBRE VARCHAR(50),
    FUNCION_SINTACTICA INTEGER,
    PRIMARY KEY (ID)
);

CREATE TABLE PICTOGRAMA_CATEGORIA (
    CATEGORIA_ID INTEGER NOT NULL AUTO_INCREMENT,
    PICTOGRAMA_ID INTEGER,
    PRIMARY KEY (CATEGORIA_ID, PICTOGRAMA_ID),
    CONSTRAINT FK_PICTOGRAMA_CATEGORIA_CATEGORIA FOREIGN KEY (CATEGORIA_ID) REFERENCES CATEGORIA(ID),
    CONSTRAINT FK_PICTOGRAMA_CATEGORIA_PICTOGRAMA FOREIGN KEY (PICTOGRAMA_ID) REFERENCES PICTOGRAMA(ID)
);

CREATE TABLE PREDICCION_PICTOGRAMA (
    ID INTEGER NOT NULL AUTO_INCREMENT,
    PICTOGRAMA_ID INTEGER,
    USUARIO_ID INTEGER,
    PESO INTEGER,
    PRIMARY KEY (ID),
    CONSTRAINT FK_PREDICCION_PICTOGRAMA_PICTOGRAMA FOREIGN KEY (PICTOGRAMA_ID) REFERENCES PICTOGRAMA(ID),
    CONSTRAINT FK_PREDICCION_PICTOGRAMA_USUARIO FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO(ID)
);

CREATE TABLE FRASE_INICIAL (
    ID INTEGER NOT NULL AUTO_INCREMENT,
    ORDEN INTEGER NOT NULL,
    PICTOGRAMA_ID INTEGER NOT NULL,
    PREDICCION_PICTOGRAMA_ID INTEGER NOT NULL,
    PRIMARY KEY (ID, ORDEN),
    CONSTRAINT FK_FRASE_INICIAL_PICTOGRAMA FOREIGN KEY (PICTOGRAMA_ID) REFERENCES PICTOGRAMA(ID),
    CONSTRAINT FK_FRASE_INICIAL_PREDICCION_PICTOGRAMA FOREIGN KEY (PREDICCION_PICTOGRAMA_ID) REFERENCES PREDICCION_PICTOGRAMA(ID)
);

COMMIT;


	
	