-- Generado por Oracle SQL Developer Data Modeler 4.1.5.907
--   en:        2017-04-05 20:00:10 CEST
--   sitio:      Oracle Database 12c
--   tipo:      Oracle Database 12c




DROP TABLE AVISO CASCADE CONSTRAINTS ;

DROP TABLE CENTRO CASCADE CONSTRAINTS ;

DROP TABLE PARTE CASCADE CONSTRAINTS ;

DROP TABLE REPARTO CASCADE CONSTRAINTS ;

DROP TABLE TRABAJADOR CASCADE CONSTRAINTS ;

DROP TABLE USUARIO CASCADE CONSTRAINTS ;

DROP TABLE VEHICULO CASCADE CONSTRAINTS ;

CREATE TABLE AVISO
  (
    ID_AVISO        NUMBER GENERATED ALWAYS AS IDENTITY NOT NULL,
    TEXTO           VARCHAR2 (500) NOT NULL ,
    ID_TRABAJADOR_E NUMBER (5) NOT NULL ,
    ID_TRABAJADOR_R NUMBER (5) NOT NULL ,
    PARTE_FECHA     DATE NOT NULL ,
    ID_TRABAJADOR   NUMBER (5) NOT NULL
  ) ;
CREATE INDEX AVISO__IDX ON AVISO
  ( ID_TRABAJADOR_E ASC
  ) ;
CREATE INDEX AVISO__IDX2 ON AVISO
  ( ID_TRABAJADOR_R ASC
  ) ;
CREATE UNIQUE INDEX AVISOv1__IDX ON AVISO
  (
    PARTE_FECHA ASC , ID_TRABAJADOR ASC
  )
  ;
ALTER TABLE AVISO ADD CONSTRAINT AVISOS_PK PRIMARY KEY ( ID_AVISO ) ;


CREATE TABLE CENTRO
  (

    ID_CENTRO NUMBER (5) GENERATED ALWAYS AS IDENTITY NOT NULL,
    NOMBRE    VARCHAR2 (25) NOT NULL ,
    DIRECCION VARCHAR2 (50) NOT NULL ,
    CP        VARCHAR2 (6) NOT NULL ,
    LOC       VARCHAR2 (25) NOT NULL ,
    PROVINCIA VARCHAR2 (25) NOT NULL ,
    TELF      VARCHAR2 (15) NOT NULL
  ) ;
ALTER TABLE CENTRO ADD CONSTRAINT CENTROS_PK PRIMARY KEY ( ID_CENTRO ) ;


CREATE TABLE PARTE
  (
    FECHA         DATE NOT NULL ,
    ID_TRABAJADOR NUMBER NOT NULL ,
    KM_INICIO     NUMBER (9,2) NOT NULL CHECK(KM_INICIO>=0),
    KM_FIN        NUMBER (9,2) NOT NULL CHECK(KM_FIN>=0),
    GASOIL        NUMBER (5,2) CHECK(GASOIL>=0),
    AUTOPISTA     NUMBER (5,2) CHECK(AUTOPISTA>=0),
    DIETAS        NUMBER (5,2) CHECK(DIETAS>=0),
    OTROS_GASTOS  NUMBER (5,2) CHECK(OTROS_GASTOS>=0),
    INCIDENCIAS   VARCHAR2 (500) ,
    ABIERTO       NUMBER NOT NULL ,
    EXCESO_HORAS  NUMBER (4,2) ,
    VALIDADO      NUMBER NOT NULL ,
    ID_VEHICULO   NUMBER NOT NULL,
    CONSTRAINT PAR_KMS_CK CHECK(KM_INICIO<KM_FIN)
  ) ;
CREATE INDEX PARTE__IDX ON PARTE
  ( ID_TRABAJADOR ASC
  ) ;
ALTER TABLE PARTE ADD CONSTRAINT PARTES_PK PRIMARY KEY ( FECHA, ID_TRABAJADOR ) ;


CREATE TABLE REPARTO
  (
    FECHA         DATE NOT NULL ,
    ID_TRABAJADOR NUMBER NOT NULL ,
    ALBARAN       VARCHAR2 (5) NOT NULL ,
    HORA_INICIO   TIMESTAMP NOT NULL ,
    HORA_FIN      TIMESTAMP NOT NULL ,
    CONSTRAINT REP_HOR_CK CHECK(HORA_FIN>HORA_INICIO)
  ) ;

-- Error - Index REPARTO__IDX2 has no columns
ALTER TABLE REPARTO ADD CONSTRAINT REPARTOS_PK PRIMARY KEY ( FECHA, ID_TRABAJADOR,ALBARAN ) ;


CREATE TABLE TRABAJADOR
  (
    ID_TRABAJADOR NUMBER GENERATED ALWAYS AS IDENTITY NOT NULL,
    DNI           VARCHAR2 (15) NOT NULL ,
    NOMBRE        VARCHAR2 (25) NOT NULL ,
    AP1           VARCHAR2 (25) NOT NULL ,
    AP2           VARCHAR2 (25) ,
    DIRECCION     VARCHAR2 (50) NOT NULL ,
    TELF_EMPRESA  VARCHAR2 (15) NOT NULL ,
    TELF_PERSONAL VARCHAR2 (15) ,
    CATEGORIA     VARCHAR2 (25) NOT NULL , --TRIGGER CONTROLAR NOMBRE CATEGORIA
    SALARIO       NUMBER (7,2) ,
    FECHANAC      DATE NOT NULL ,
    ID_CENTRO     NUMBER (5) NOT NULL,
    ID_USUARIO    VARCHAR2 (15) 
  ) ;
CREATE UNIQUE INDEX TRABAJADOR__IDX ON TRABAJADOR
  (
    ID_TRABAJADOR ASC
  )
  ;
ALTER TABLE TRABAJADOR ADD CONSTRAINT TRABAJADORES_PK PRIMARY KEY ( ID_TRABAJADOR ) ;


CREATE TABLE USUARIO
  (
    ID_USUARIO VARCHAR2 (15) NOT NULL ,
    CONTRASE�A VARCHAR2 (100) NOT NULL
  ) ;
CREATE UNIQUE INDEX USUARIO__IDX ON USUARIO
  (
    ID_USUARIO ASC
  )
  ;
ALTER TABLE USUARIO ADD CONSTRAINT USUARIOS_PK PRIMARY KEY ( ID_USUARIO ) ;


CREATE TABLE VEHICULO
  (
    ID_VEHICULO NUMBER GENERATED ALWAYS AS IDENTITY NOT NULL,
    MATRICULA   VARCHAR2 (12) NOT NULL ,
    MODELO      VARCHAR2 (50) ,
    MARCA       VARCHAR2 (50)
  ) ;
ALTER TABLE VEHICULO ADD CONSTRAINT VEHICULOS_PK PRIMARY KEY ( ID_VEHICULO ) ;


ALTER TABLE AVISO ADD CONSTRAINT AVISOS_TRABAJADORES_FK FOREIGN KEY ( ID_TRABAJADOR_E ) REFERENCES TRABAJADOR ( ID_TRABAJADOR ) ;

ALTER TABLE AVISO ADD CONSTRAINT AVISOS_TRABAJADORES_FKv1 FOREIGN KEY ( ID_TRABAJADOR_R ) REFERENCES TRABAJADOR ( ID_TRABAJADOR ) ;

ALTER TABLE AVISO ADD CONSTRAINT AVISOv1_PARTE_FK FOREIGN KEY ( PARTE_FECHA, ID_TRABAJADOR ) REFERENCES PARTE ( FECHA, ID_TRABAJADOR ) ;

ALTER TABLE PARTE ADD CONSTRAINT PARTES_TRABAJADORES_FK FOREIGN KEY ( ID_TRABAJADOR ) REFERENCES TRABAJADOR ( ID_TRABAJADOR ) ;

ALTER TABLE PARTE ADD CONSTRAINT PARTE_VEHICULO_FK FOREIGN KEY ( ID_VEHICULO ) REFERENCES VEHICULO ( ID_VEHICULO ) ;

ALTER TABLE REPARTO ADD CONSTRAINT REPARTOS_PARTES_FK FOREIGN KEY ( FECHA, ID_TRABAJADOR ) REFERENCES PARTE ( FECHA, ID_TRABAJADOR ) ;

ALTER TABLE TRABAJADOR ADD CONSTRAINT TRABAJADORES_CENTROS_FK FOREIGN KEY ( ID_CENTRO ) REFERENCES CENTRO ( ID_CENTRO ) ;

ALTER TABLE TRABAJADOR ADD CONSTRAINT TRABAJADOR_USUARIO_FK FOREIGN KEY ( ID_USUARIO ) REFERENCES USUARIO ( ID_USUARIO ) ;

--CREACI�N DE USUARIO DE LA APLICACI�N

-- USER SQL
CREATE USER Aplicacion IDENTIFIED BY a12345Abcde ;

-- QUOTAS

-- ROLES
GRANT "DBA" TO Aplicacion ;
GRANT "CONNECT" TO Aplicacion ;
GRANT "RESOURCE" TO Aplicacion ;

-- SYSTEM PRIVILEGES




-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             7
-- CREATE INDEX                             8
-- ALTER TABLE                             15
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- TSDP POLICY                              0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   1
-- WARNINGS                                 0


