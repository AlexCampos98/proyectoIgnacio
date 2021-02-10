--------------------------------------------------------
-- Archivo creado  - miércoles-febrero-10-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence SEQUENCE_ENTRENAMIENTO
--------------------------------------------------------

   CREATE SEQUENCE  SEQUENCE_ENTRENAMIENTO  MINVALUE 0 MAXVALUE 99999 INCREMENT BY 1 START WITH 7 CACHE 20 NOORDER  CYCLE ;
--------------------------------------------------------
--  DDL for Sequence SEQUENCE_USUARIO
--------------------------------------------------------

   CREATE SEQUENCE  SEQUENCE_USUARIO  MINVALUE 0 MAXVALUE 99999 INCREMENT BY 1 START WITH 6 CACHE 20 NOORDER  CYCLE ;
--------------------------------------------------------
--  DDL for Table ENTRENAMIENTO
--------------------------------------------------------

  CREATE TABLE ENTRENAMIENTO 
   (	ID_ENTRENAMIENTO NUMBER(5,0), 
	NOMBRE VARCHAR2(30), 
	FECHA DATE, 
	PLAZAS NUMBER(3,0), 
	ID_USUARIO_ENTRENADOR NUMBER(5,0), 
	ID_USUARIO_DEPORTISTA NUMBER(5,0)
   ) ;
--------------------------------------------------------
--  DDL for Table USUARIO
--------------------------------------------------------

  CREATE TABLE USUARIO 
   (	ID_USUARIO NUMBER(5,0), 
	CORREO VARCHAR2(320), 
	NOMBRE VARCHAR2(30), 
	APELLIDO1 VARCHAR2(50), 
	APELLIDO2 VARCHAR2(50), 
	TELEFONO CHAR(9), 
	TELEFONOEMERGENCIA CHAR(9), 
	NOMBREUSUARIO VARCHAR2(20)
   ) ;

Insert into ENTRENAMIENTO (ID_ENTRENAMIENTO,NOMBRE,FECHA,PLAZAS,ID_USUARIO_ENTRENADOR,ID_USUARIO_DEPORTISTA) values ('1','Calistenia',to_date('14/01/21','DD/MM/RR'),'1','2','2');
Insert into ENTRENAMIENTO (ID_ENTRENAMIENTO,NOMBRE,FECHA,PLAZAS,ID_USUARIO_ENTRENADOR,ID_USUARIO_DEPORTISTA) values ('2','Crossfit',to_date('14/01/21','DD/MM/RR'),'1','1','2');
Insert into ENTRENAMIENTO (ID_ENTRENAMIENTO,NOMBRE,FECHA,PLAZAS,ID_USUARIO_ENTRENADOR,ID_USUARIO_DEPORTISTA) values ('3','aaaa',to_date('14/01/21','DD/MM/RR'),'5','1','3');
Insert into ENTRENAMIENTO (ID_ENTRENAMIENTO,NOMBRE,FECHA,PLAZAS,ID_USUARIO_ENTRENADOR,ID_USUARIO_DEPORTISTA) values ('4','aaaa',to_date('14/01/21','DD/MM/RR'),'5','1','3');
Insert into ENTRENAMIENTO (ID_ENTRENAMIENTO,NOMBRE,FECHA,PLAZAS,ID_USUARIO_ENTRENADOR,ID_USUARIO_DEPORTISTA) values ('5','algo',to_date('25/01/21','DD/MM/RR'),'3','1','2');
Insert into ENTRENAMIENTO (ID_ENTRENAMIENTO,NOMBRE,FECHA,PLAZAS,ID_USUARIO_ENTRENADOR,ID_USUARIO_DEPORTISTA) values ('6','jkgkjg',to_date('05/02/21','DD/MM/RR'),'4','1','2');

Insert into USUARIO (ID_USUARIO,CORREO,NOMBRE,APELLIDO1,APELLIDO2,TELEFONO,TELEFONOEMERGENCIA,NOMBREUSUARIO) values ('2','bbb@bb.bb','Pol','Algo','Menos','222222222','333333333','BbBb');
Insert into USUARIO (ID_USUARIO,CORREO,NOMBRE,APELLIDO1,APELLIDO2,TELEFONO,TELEFONOEMERGENCIA,NOMBREUSUARIO) values ('3','alex@hotmail.com','Alexggggg1','ape1','ape2','456789456','216457984','Alex69');
Insert into USUARIO (ID_USUARIO,CORREO,NOMBRE,APELLIDO1,APELLIDO2,TELEFONO,TELEFONOEMERGENCIA,NOMBREUSUARIO) values ('4','azucar@azucar.aa','Jacobo','fgfdgf','ghfdhgf','987456321','123654789','POLLL');
Insert into USUARIO (ID_USUARIO,CORREO,NOMBRE,APELLIDO1,APELLIDO2,TELEFONO,TELEFONOEMERGENCIA,NOMBREUSUARIO) values ('1','aaa@aa.aa','Alejandro','Campos','Maestre','111111111','252525252','AaAa');
Insert into USUARIO (ID_USUARIO,CORREO,NOMBRE,APELLIDO1,APELLIDO2,TELEFONO,TELEFONOEMERGENCIA,NOMBREUSUARIO) values ('5','BbBbB@bb.bb','bb','bB','Bb','123456789','987654321','bb');
--------------------------------------------------------
--  DDL for Index ENTRENAMIENTO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX ENTRENAMIENTO_PK ON ENTRENAMIENTO (ID_ENTRENAMIENTO) 
  ;
--------------------------------------------------------
--  DDL for Index USUARIO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX USUARIO_PK ON USUARIO (ID_USUARIO) 
  ;
--------------------------------------------------------
--  DDL for Trigger AUDITORIA_ENTRENO_DEPORTISTA
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER AUDITORIA_ENTRENO_DEPORTISTA 
AFTER INSERT OR UPDATE ON entrenamiento
DECLARE
    nUsuario NUMBER(1);
BEGIN
    SELECT MAX(nombre1) INTO nusuario
    FROM (SELECT COUNT(nombre) as nombre1, fecha, id_usuario_deportista
    FROM entrenamiento 
    GROUP BY fecha, id_usuario_deportista);

    IF nusuario > 2 THEN
        RAISE_APPLICATION_ERROR(-20001,'El deportistas no puede tener mas de dos entrenamientos el mismo dia.');
    END IF;
END;
/
--------------------------------------------------------
--  DDL for Trigger AUDITORIA_MAX_ENTRENO
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER AUDITORIA_MAX_ENTRENO 
AFTER INSERT OR UPDATE ON entrenamiento
DECLARE
    nEntrenamientos NUMBER(1);
BEGIN
    SELECT MAX(nombre1) INTO nEntrenamientos
    FROM (SELECT COUNT(nombre) as nombre1, fecha
    FROM entrenamiento 
    GROUP BY fecha);

    IF nEntrenamientos > 8 THEN
        RAISE_APPLICATION_ERROR(-20002,'Se ha alcanzado el maximo de entrenamientos en el dia (Max 8).');
    END IF;
END;
/
--------------------------------------------------------
--  DDL for Trigger NOM_APE_VOCALES
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER NOM_APE_VOCALES 
AFTER INSERT OR UPDATE ON usuario
FOR EACH ROW
DECLARE
    concatenacion_carac VARCHAR2(255);
    vocales VARCHAR2(130);
    consonantes VARCHAR2(130);
BEGIN
    concatenacion_carac := CONCAT(:NEW.nombre, :NEW.apellido1);
    concatenacion_carac := CONCAT(concatenacion_carac, :NEW.apellido2);
    consonantes := REGEXP_REPLACE(concatenacion_carac,'[a,e,i,o,u,A,E,I,O,U]','');
    vocales := REGEXP_REPLACE(concatenacion_carac,'[q,Q,w,W,r,R,t,T,y,Y,p,P,s,S,d,D,f,F,g,G,h,H,j,J,k,K,l,L,ñ,Ñ,z,Z,x,X,c,C,v,V,b,B,n,N,m,M]','');

    IF LENGTH(vocales) > LENGTH(consonantes) THEN
          RAISE_APPLICATION_ERROR(-20003,'No puede haber mas vocales que consonantes en la union del nombre y los apellidos.');
    END IF;
END;
/
--------------------------------------------------------
--  DDL for Procedure MODIFICAR_ENTRENAMIENTO
--------------------------------------------------------

  CREATE OR REPLACE PROCEDURE MODIFICAR_ENTRENAMIENTO 
(
  R_ID_ENTRENAMIENTO IN NUMBER 
, R_NOMBRE IN VARCHAR2 
, R_FECHA IN DATE 
, R_PLAZAS IN VARCHAR2 
, R_ID_USUARIO_ENTRENADOR IN NUMBER 
, R_ID_USUARIO_DEPORTISTA IN NUMBER 
) AS 
BEGIN
  UPDATE entrenamiento SET  nombre = r_nombre, fecha = r_fecha, 
  plazas = r_plazas, id_usuario_entrenador = r_id_usuario_entrenador, id_usuario_deportista = r_id_usuario_deportista
  WHERE id_entrenamiento = r_id_entrenamiento;
END MODIFICAR_ENTRENAMIENTO;
/
--------------------------------------------------------
--  DDL for Procedure MODIFICAR_USUARIO
--------------------------------------------------------

  CREATE OR REPLACE PROCEDURE MODIFICAR_USUARIO 
(
  r_ID_USUARIO NUMBER 
, r_CORREO VARCHAR2 
, r_NOMBRE VARCHAR2 
, r_APELLIDO1 VARCHAR2 
, r_APELLIDO2 VARCHAR2 
, r_TELEFONO VARCHAR2 
, r_TELEFONOEMERGENCIA VARCHAR2 
, r_NOMBREUSUARIO VARCHAR2 
) AS 
BEGIN
  UPDATE usuario SET correo = r_correo, nombre = r_nombre, apellido1 = r_apellido1, apellido2 = r_apellido2,
  telefono = r_telefono, telefonoemergencia = r_telefonoemergencia, nombreusuario = r_nombreusuario
  WHERE id_usuario = r_id_usuario;
END MODIFICAR_USUARIO;
/
--------------------------------------------------------
--  Constraints for Table USUARIO
--------------------------------------------------------

  ALTER TABLE USUARIO ADD CONSTRAINT CORREO_CHK CHECK (correo LIKE '%@%.%') ENABLE;
 
  ALTER TABLE USUARIO ADD CONSTRAINT CORREO_UQ UNIQUE (CORREO) ENABLE;
 
  ALTER TABLE USUARIO ADD CONSTRAINT NOMBREUSUARIO_UQ UNIQUE (NOMBREUSUARIO) ENABLE;
 
  ALTER TABLE USUARIO MODIFY (CORREO NOT NULL ENABLE);
 
  ALTER TABLE USUARIO MODIFY (NOMBRE NOT NULL ENABLE);
 
  ALTER TABLE USUARIO MODIFY (APELLIDO1 NOT NULL ENABLE);
 
  ALTER TABLE USUARIO MODIFY (APELLIDO2 NOT NULL ENABLE);
 
  ALTER TABLE USUARIO MODIFY (TELEFONO NOT NULL ENABLE);
 
  ALTER TABLE USUARIO MODIFY (TELEFONOEMERGENCIA NOT NULL ENABLE);
 
  ALTER TABLE USUARIO MODIFY (NOMBREUSUARIO NOT NULL ENABLE);
 
  ALTER TABLE USUARIO ADD CONSTRAINT TELEFONO_CHK CHECK (telefono <> telefonoEmergencia) ENABLE;
 
  ALTER TABLE USUARIO ADD CONSTRAINT TELEFONO_UQ UNIQUE (TELEFONO) ENABLE;
 
  ALTER TABLE USUARIO ADD CONSTRAINT USUARIO_PK PRIMARY KEY (ID_USUARIO) ENABLE;
--------------------------------------------------------
--  Constraints for Table ENTRENAMIENTO
--------------------------------------------------------

  ALTER TABLE ENTRENAMIENTO ADD CONSTRAINT ENTRENAMIENTO_PK PRIMARY KEY (ID_ENTRENAMIENTO) ENABLE;
 
  ALTER TABLE ENTRENAMIENTO ADD CONSTRAINT PLAZAS_CHK CHECK (plazas > 0) ENABLE;
 
  ALTER TABLE ENTRENAMIENTO MODIFY (NOMBRE NOT NULL ENABLE);
 
  ALTER TABLE ENTRENAMIENTO MODIFY (FECHA NOT NULL ENABLE);
 
  ALTER TABLE ENTRENAMIENTO MODIFY (PLAZAS NOT NULL ENABLE);
 
  ALTER TABLE ENTRENAMIENTO MODIFY (ID_USUARIO_ENTRENADOR NOT NULL ENABLE);
 
  ALTER TABLE ENTRENAMIENTO MODIFY (ID_USUARIO_DEPORTISTA NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table ENTRENAMIENTO
--------------------------------------------------------

  ALTER TABLE ENTRENAMIENTO ADD CONSTRAINT DEPORTISTA_FK FOREIGN KEY (ID_USUARIO_DEPORTISTA)
	  REFERENCES USUARIO (ID_USUARIO) ENABLE;
 
  ALTER TABLE ENTRENAMIENTO ADD CONSTRAINT ENTRENADOR_FK FOREIGN KEY (ID_USUARIO_ENTRENADOR)
	  REFERENCES USUARIO (ID_USUARIO) ENABLE;