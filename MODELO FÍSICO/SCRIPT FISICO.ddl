-- Generado por Oracle SQL Developer Data Modeler 4.1.1.888
--   en:        2017-03-30 19:29:09 CEST
--   sitio:      Oracle Database 12c
--   tipo:      Oracle Database 12c

DROP TABLE AVISOS CASCADE CONSTRAINTS;
DROP TABLE CENTROS CASCADE CONSTRAINTS;
DROP TABLE PARTES CASCADE CONSTRAINTS;
DROP TABLE REPARTOS CASCADE CONSTRAINTS;
DROP TABLE TRABAJADORES CASCADE CONSTRAINTS;
DROP TABLE USUARIOS CASCADE CONSTRAINTS;
DROP TABLE VEHICULOS CASCADE CONSTRAINTS;

CREATE TABLE AVISOS
  (
    ID_AVISO           NUMBER (5) PRIMARY KEY,
    TEXTO              VARCHAR2 (500) NOT NULL ,
    ID_TRABAJ_EMISOR   NUMBER (5) NOT NULL ,
    ID_TRABAJ_RECEPTOR NUMBER (5) NOT NULL
  ) ;
CREATE INDEX AVISO__IDX ON AVISOS
  ( ID_TRABAJ_EMISOR ASC
  ) ;
CREATE INDEX AVISO__IDX2 ON AVISOS
  ( ID_TRABAJ_RECEPTOR ASC
  ) ;

CREATE TABLE CENTROS
  (
    ID_CENTRO NUMBER (5) PRIMARY KEY ,
    NOMBRE    VARCHAR2 (25) NOT NULL ,
    DIRECCION VARCHAR2 (50) NOT NULL ,
    CP        NUMBER (6) NOT NULL ,
    LOC       VARCHAR2 (25) NOT NULL ,
    PROVINCIA VARCHAR2 (25) NOT NULL ,
    TELF      VARCHAR2 (15) NOT NULL
  ) ;

CREATE TABLE PARTES
  (
    ID_PARTE      NUMBER (5) PRIMARY KEY,
    FECHA         DATE NOT NULL,
    KM_INICIO     NUMBER (7,2) NOT NULL CONSTRAINT P_KMI_CK CHECK (KM_INICIO>0),
    KM_FIN        NUMBER (7,2) NOT NULL CONSTRAINT P_KMF_CK CHECK (KM_FIN>0),
    GASOIL        NUMBER (3,2) NOT NULL CONSTRAINT P_GAS_CK CHECK (GASOIL>0),
    AUTOPISTA     NUMBER (3,2) NOT NULL CONSTRAINT P_AUTOP_CK CHECK (AUTOPISTA>0),
    DIETAS        NUMBER (3,2) NOT NULL CONSTRAINT P_DIET_CK CHECK (DIETAS>0),
    OTROS_GASTOS  NUMBER (3,2) NOT NULL CONSTRAINT P_OTG_CK CHECK (OTROS_GASTOS>0),
    INCIDENCIAS   VARCHAR2 (500) NOT NULL,
    ABIERTO       NUMBER NOT NULL,
    EXCESO_HORAS  NUMBER (2,2) NOT NULL CONSTRAINT P_EXH_CK CHECK (EXCESO_HORAS>0),
    ID_TRABAJADOR NUMBER (2) NOT NULL,---- FALTA UNA RESTRICCION DE LA CATEGORIA DEL TRABAJADOR
    CONSTRAINT P_KMF2_CK CHECK (KM_FIN>KM_INICIO)
  ) ;
  
CREATE INDEX PARTE__IDX ON PARTES
  ( ID_TRABAJADOR ASC
  ) ;

CREATE TABLE REPARTOS
  (
    ID_REPARTO  NUMBER (2) PRIMARY KEY ,
    ALBARAN     VARCHAR2 (5) NOT NULL ,
    HORA_INICIO DATE  NOT NULL,
    HORA_FIN    DATE  NOT NULL ,
    ID_PARTE    NUMBER (2) NOT NULL ,
    ID_VEHICULO NUMBER (2,2) NOT NULL,
    CONSTRAINT R_HF_CK CHECK (HORA_FIN>HORA_INICIO)
  ) ;
CREATE INDEX REPARTO__IDX ON REPARTOS
  ( ID_PARTE ASC
  ) ;
CREATE INDEX REPARTO__IDX2 ON REPARTOS
  ( ID_VEHICULO ASC
  ) ;

CREATE TABLE TRABAJADORES
  (
    ID_TRABAJADOR NUMBER (5) PRIMARY KEY ,
    DNI           VARCHAR2 (15) NOT NULL ,
    NOMBRE        VARCHAR2 (25) NOT NULL ,
    AP1           VARCHAR2 (25) NOT NULL ,
    AP2           VARCHAR2 (25) NOT NULL ,
    DIRECCION     VARCHAR2 (50) NOT NULL ,
    TELF_EMPRESA  VARCHAR2 (15) NOT NULL ,
    TELF_PERSONAL VARCHAR2 (15) ,
    CATEGORIA     VARCHAR2 (25) NOT NULL CONSTRAINT T_CAT_CK CHECK (UPPER(CATEGORIA)IN ('ADMINISTRATIVO','TRANSPORTISTA')),
    SALARIO       NUMBER (5,2) ,
    FCHANAC       DATE NOT NULL ,
    ID_CENTRO     NUMBER (2) NOT NULL
  ) ;
CREATE UNIQUE INDEX TRABAJADOR__IDX ON TRABAJADORES
  (
    ID_CENTRO ASC
  );

CREATE TABLE USUARIOS
  (
    ID_USUARIO    NUMBER (2,2) PRIMARY KEY ,
    CONTRASEŅA    VARCHAR2 (100) NOT NULL ,
    ID_TRABAJADOR NUMBER (2) NOT NULL
  ) ;
CREATE UNIQUE INDEX USUARIO__IDX ON USUARIOS
  (
    ID_TRABAJADOR ASC
  )
  ;

CREATE TABLE VEHICULOS
  (
    ID_VEHICULO NUMBER (2,2) PRIMARY KEY ,
    MATRICULA   VARCHAR2 (12) NOT NULL ,
    MODELO      VARCHAR2 (50),
    MARCA       VARCHAR2 (50)
  ) ;
  
ALTER TABLE AVISOS ADD CONSTRAINT AVISO_EMISOR_FK FOREIGN KEY ( ID_TRABAJ_EMISOR ) REFERENCES TRABAJADORES ( ID_TRABAJADOR ) ;

ALTER TABLE AVISOS ADD CONSTRAINT AVISO_RECEPTOR_FK FOREIGN KEY ( ID_TRABAJ_RECEPTOR ) REFERENCES TRABAJADORES ( ID_TRABAJADOR ) ;

ALTER TABLE PARTES ADD CONSTRAINT PARTE_TRABAJADOR_FK FOREIGN KEY ( ID_TRABAJADOR ) REFERENCES TRABAJADORES ( ID_TRABAJADOR ) ;

ALTER TABLE REPARTOS ADD CONSTRAINT REPARTO_PARTE_FK FOREIGN KEY ( ID_PARTE ) REFERENCES PARTES ( ID_PARTE ) ;

ALTER TABLE REPARTOS ADD CONSTRAINT REPARTO_VEHICULO_FK FOREIGN KEY ( ID_VEHICULO ) REFERENCES VEHICULOS ( ID_VEHICULO ) ;

ALTER TABLE TRABAJADORES ADD CONSTRAINT TRABAJADOR_CENTRO_FK FOREIGN KEY ( ID_CENTRO ) REFERENCES CENTROS ( ID_CENTRO ) ;

ALTER TABLE USUARIOS ADD CONSTRAINT USUARIO_TRABAJADOR_FK FOREIGN KEY ( ID_TRABAJADOR ) REFERENCES TRABAJADORES ( ID_TRABAJADOR ) ;

CREATE SEQUENCE ID_TRABAJADOR_SQ 
MAXVALUE 99999
MINVALUE 1
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE ID_CENTRO_SQ 
MAXVALUE 99999
MINVALUE 1
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE ID_PARTE_SQ 
MAXVALUE 99999
MINVALUE 1
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE ID_REPARTO_SQ 
MAXVALUE 99999
MINVALUE 1
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE ID_VEHICULO_SQ 
MAXVALUE 99999
MINVALUE 1
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE ID_AVISO_SQ 
MAXVALUE 99999
MINVALUE 1
START WITH 1
INCREMENT BY 1;

SELECT * FROM TRABAJADORES;

CREATE OR REPLACE PROCEDURE P_MOSTRAR_TRABAJADOR ( VP_ID_TRABAJADOR TRABAJADORES.ID_TRABAJADOR%TYPE)AS
BEGIN
SELECT ID_TRABAJADOR,DNI,NOMBRE,AP1,AP2,DIRECCION,TELF_EMPRESA,TELF_PERSONAL,CATEGORIA,SALARIO,FCHANAC,ID_CENTRO FROM TRABAJADORES WHERE ID_TRABAJADOR = VP_ID_TRABAJADOR;










