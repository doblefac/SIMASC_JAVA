/* Fecha inicio 31/10/2017 */

/*
Casos de uso CON-F-101 CON-F-104 CON-F-061   pronunciamiento del conciliador -------------------------------------------------------------
*/

/*motivos rechazo conciliador*/
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('NODISPON','MOTIVO_DE_RECHAZO_CONCILIADOR','No puede atender el caso en la fecha, hora y sede seleccionada por el cliente','Motivo por el que un conciliador no puede aceptar la asignación a un caso',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('INHABILI','MOTIVO_DE_RECHAZO_CONCILIADOR','Inhabilidad','Motivo por el que un conciliador no puede aceptar la asignación a un caso',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('INCAPAC','MOTIVO_DE_RECHAZO_CONCILIADOR','Incapacidad médica','Motivo por el que un conciliador no puede aceptar la asignación a un caso',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('VACAS','MOTIVO_DE_RECHAZO_CONCILIADOR','Vacaciones','Motivo por el que un conciliador no puede aceptar la asignación a un caso',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('CALAMID','MOTIVO_DE_RECHAZO_CONCILIADOR','Calamidad doméstica','Motivo por el que un conciliador no puede aceptar la asignación a un caso',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('FALTCOMP','MOTIVO_DE_RECHAZO_CONCILIADOR','Falta de competencia','Motivo por el que un conciliador no puede aceptar la asignación a un caso',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('OTRO','MOTIVO_DE_RECHAZO_CONCILIADOR','Otro','Motivo por el que un conciliador no puede aceptar la asignación a un caso',null,null);


/*Estado caso conciliacion*/
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('PENDESIG','ESTADO_CASO','Pendiente por designación','Estado para los casos de conciliación. EL caso está pendiente de asignación del prestador de servicio.',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('CONDESIG','ESTADO_CASO','Conciliador designado','Estado para los casos de conciliación. Se está a la espera de que el prestador de servicio acepte la designación.',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('FALTCOMP','ESTADO_CASO','Falta de competencia','Estado para los casos de conciliación. El prestador de servicio identifico que el caso no es de competencia para la CCB.',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('ENCONCIL','ESTADO_CASO','En conciliación','Estado para los casos de conciliación. El caso se encuentra en proceso de conciliación con las partes.',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('DEVCOLEG','ESTADO_CASO','Devuelto en control de legalidad','Estado para los casos de conciliación. El caso fue devuelto por un analista de conciliación en la etapa de control de legalidad.',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('CONLEGAL','ESTADO_CASO','Asignado para control de legalidad','Estado para los casos de conciliación. El caso ha sido asignado para control de legalidad.',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('PENDOCRE','ESTADO_CASO','Pendiente entrega documento de resultado','Estado para los casos de conciliación. El prestador de servicio está pendiente de  entregar el documento de resultado del caso para poder pasar el caso a control de legalidad.',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('REABIERT','ESTADO_CASO','Reabierto','Estado para los casos de conciliación. El caso ha sido reabierto posterior al registro en el ministerio de justicia.',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('REGISTRA','ESTADO_CASO','Registrado','Estado para los casos de conciliación. El caso ha finalizado y se ha registrado en el ministerio de justicia.',null,null);

-- ADM-F-032 Antes del 28/09/2017
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('ESPECIAL','FORMACION_PERSONA','Especialización','Especialización',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('MAESTRIA','FORMACION_PERSONA','Maestría','Maestría',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('OTROS','FORMACION_PERSONA','Otro','Otro',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('PREGRADO','FORMACION_PERSONA','Pregrado','Pregrado',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('DTP','TIPO_DOCUMENTO_DIG','Tarjeta profesional','Tarjeta profesional',null,null);


/*tipo evento*/
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('RESPPETI','TIPO_EVENTO','Respuesta petición','Se dió respuesta a una de las peticiones especiales solicitadas por el conciliador.',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('RELIQUID','TIPO_EVENTO','Reliquidacion','Se realizó una reliquidación del caso.',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('REGISTRO','TIPO_EVENTO','Registro de caso','El caso ha sido reportado al ministerio de justicia.',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('SOLIPROR','TIPO_EVENTO','Solicitud de prorroga','El usuario ha realizado una solicitud de prorroga',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('REAPERTU','TIPO_EVENTO','Reapertura de caso','Se reabre el caso por solicitud de las partes o por el conciliador.',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('PENDESIG','TIPO_EVENTO','Pendiente por designación','El caso ha sido radicado pero queda pendiente por designación del prestador de servicio.',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('REPARTO','TIPO_EVENTO','Reparto','Se ha realizado un reparto para asignar un funcionario u operador al caso.',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('DESIGNA','TIPO_EVENTO','Designación','Designación de un prestador de servicio al caso',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('FALTCOMP','TIPO_EVENTO','Falta de competencia','Indica que el conciliador asignado al caso no pudo tomarlo por falta de competencia',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('MONOREP','TIPO_EVENTO','Reparto no exitoso','Se intento realizar un reparto de alguno de los roles pero no fue posible.',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('MODACA','TIPO_EVENTO','Modificación datos Caso','Modificacion datos basico del Caso',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('PRONCONC','TIPO_EVENTO','Pronunciamiento del conciliador','Se utiliza para registrar cuando un conciliador confirma o rechaza la asignación al caso.',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('DEVDIN','TIPO_EVENTO','Devolución de dinero','Se utiliza cuando se realiza la devolucion de dinero de un caso',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('MOPARECA','TIPO_EVENTO','Modificación de parámetros para reparto de un caso','Modificación de parámetros para reparto de un caso',null,null);

/*resultado caso*/
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('FALTCOMP','RESULTADO_CASO','Falta de competencia','El caso fue cerrado por falta de competencia',null,null);

/* parametros generales del devolucion de dinero */

INSERT INTO PARAMETROS_GENERALES (codigo,tipo,nombre,valor_numerico,valor_texto,valor_fecha,valor_booleano,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
	VALUES ('DEV_NCOM','PORCENTAJE DEVOLUCION','PORCENTAJE_DEVOLUCION_NO_COMPETENCIA',100,null,null,null,'Define el porcentaje del valor pagado por el usuario a devolverle debido al cierre del caso por motivo de no competencia.','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');

INSERT INTO PARAMETROS_GENERALES (codigo,tipo,nombre,valor_numerico,valor_texto,valor_fecha,valor_booleano,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
	VALUES ('DEV_NCIT','PORCENTAJE DEVOLUCION','PORCENTAJE_DEVOLUCION_NO_HAY_CITACION',100,null,null,null,'Define el porcentaje del valor pagado por el usuario a devolverle debido al cierre del caso por motivo de arreglo directo o cancelación en un momento en el que todavía no se ha generado la citación del caso.','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');

INSERT INTO PARAMETROS_GENERALES (codigo,tipo,nombre,valor_numerico,valor_texto,valor_fecha,valor_booleano,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
	VALUES ('DEV_CITA','PORCENTAJE DEVOLUCION','PORCENTAJE_DEVOLUCION_HUBO_CITACION',30,null,null,null,'Define el porcentaje del valor pagado por el usuario a devolverle debido al cierre del caso por motivo de arreglo directo o cancelación en un momento en el que se generó citación a la audiencia pero esta todavía no se ha realizado y no quedan menos de 24 horas para su ejecución.','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');

INSERT INTO parametros_generales (codigo,tipo,nombre,valor_numerico,valor_texto,valor_fecha,valor_booleano,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
	VALUES ('DHABDEV', 'DEVOLUCION', 'Días habiles para devolución', 3, 'Tres', null, null, 'Días que se tienen para reclamar la suma devuelta', 'USUARIO_SIMASC', GETDATE(), 'ACT')
	
/*motivo reliquidacion del caso */
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('MOTDEVO','CLASIFICADOR_RESULTADO_CASO','Motivo devolución','Motivos de devolución de dinero',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('CAMBCUAN','MOTIVO_DE_RELIQUIDACION','Cambio de cuantía','Valor adicional cobrado al cliente',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('COBRADIC','MOTIVO_DE_RELIQUIDACION','Cobro adicional por audiencia','Valor adicional cobrado al cliente',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('FALTCOMP','MOTIVO_DE_RELIQUIDACION','Falta de competencia','Valor devuelto al cliente ',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('ARREGDIR','MOTIVO_DE_RELIQUIDACION','Arreglo directo ','Valor devuelto al cliente ',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('CANCELA','MOTIVO_DE_RELIQUIDACION','Cancelación','Valor devuelto al cliente ',null,null);

/*tipo reliquidacion*/
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('DEVOLUCI','TIPO_RELIQUIDACION','Devolución','Devolución de dinero que realiza la CCB al cliente',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('RELIQUID','TIPO_RELIQUIDACION','Reliquidación','Dinero adicional que debe pagar el cliente por audiencias adicionales o cambios en la cuantía del caso.',null,null);

/* parametros de tiempo maximo aceptacion conciliador*/
INSERT INTO PARAMETROS_GENERALES (codigo,tipo,nombre,valor_numerico,valor_texto,valor_fecha,valor_booleano,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
	VALUES ('ACE_PAR','PLAZO ACEPTACION CONCILIADOR','PLAZO_ACEPTACION_CONCILIADOR_NOMBRADO_POR_PARTES',17,null,null,null,'Valor numérico que indica el tiempo en horas que tiene el conciliador para pronunciarse respecto a su asignación realizada por las partes','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');

INSERT INTO PARAMETROS_GENERALES (codigo,tipo,nombre,valor_numerico,valor_texto,valor_fecha,valor_booleano,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
	VALUES ('ACE_CCB','PLAZO ACEPTACION CONCILIADOR','PLAZO_ACEPTACION_CONCILIADOR_NOMBRADO_POR_CCB',24,null,null,null,'Valor numérico que indica el tiempo en horas que tiene el conciliador para pronunciarse respecto a su asignación realizada por la CCB','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');

/* parametro general url ccb */


INSERT INTO PARAMETROS_GENERALES (codigo,tipo,nombre,valor_numerico,valor_texto,valor_fecha,valor_booleano,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
	VALUES ('URL_SIMA','URL ACCESO SIMASC','URL_ACCESO_SIMASC',null,'http://www.centroarbitrajeconciliacion.com',null,null,'Url que redirige a la página de login de simasc.','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');

/*etapas conciliacion*/
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('CONCILIA','ETAPAS_CONCILIACION','Conciliación','',null,null);

/* Permisos  CON-F-101 y CON-F-061 SIMASC-CU-Lista-de-casos-pendientes-de-aceptacion */

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF101CasosPendientes';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF101CasosPendientesList';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF061PronunciamientoOrdConciliador';


DELETE FUNCIONALIDAD WHERE nombre ='CONF101CasosPendientesList';
DELETE FUNCIONALIDAD WHERE nombre ='CONF061PronunciamientoOrdConciliador';
DELETE FUNCIONALIDAD WHERE nombre ='CONF101CasosPendientes';

insert into FUNCIONALIDAD values ('CONF101CasosPendientes', 'Pronunciamiento del conciliador', 'app/Conciliacion/CONF101','PDL','ANGULAR',NULL,'SIMASC_USER',SYSDATETIME(),'ACT');
insert into FUNCIONALIDAD values ('CONF101CasosPendientesList', 'Lista Casos pendientes de pronunciamiento conciliador', 'app/Conciliacion/CONF101','PDL','ANGULAR','CONF101CasosPendientes','SIMASC_USER',SYSDATETIME(),'ACT');
insert into FUNCIONALIDAD values ('CONF061PronunciamientoOrdConciliador', 'Pronunciamiento de tramite ordinario del conciliador', 'app/Conciliacion/CONF061','PDL','ANGULAR','CONF101CasosPendientes','SIMASC_USER',SYSDATETIME(),'ACT');


insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF101CasosPendientes',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA','ROL_DE_PARTE_ARBITRAJE') and  d.codigo  
		NOT IN ('CON');

insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF101CasosPendientesList',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA','ROL_DE_PARTE_ARBITRAJE') and  d.codigo  
		NOT IN ('CON');

insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF061PronunciamientoOrdConciliador',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA','ROL_DE_PARTE_ARBITRAJE') and  d.codigo  
		NOT IN ('CON');

/*
Casos de uso CON-F-101 y CON-F-061 SIMASC-CU-Lista-de-casos-pendientes-de-aceptacion ----------------------------------------
*/
		
/* Permisos  CON-F-101 y CON-F-061 SIMASC-CU-Lista-de-casos-pendientes-de-aceptacion */

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='reportesConc';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONC037ReporteNoAceptados';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONC041ReporteNoCompetencia';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONC039ReporteCasosPagados';

DELETE FUNCIONALIDAD WHERE nombre ='CONC037ReporteNoAceptados';
DELETE FUNCIONALIDAD WHERE nombre ='CONC041ReporteNoCompetencia';
DELETE FUNCIONALIDAD WHERE nombre ='CONC039ReporteCasosPagados';
DELETE FUNCIONALIDAD WHERE nombre ='reportesConc';

insert into FUNCIONALIDAD values ('reportesConc', 'Reportes conciliación', 'app/Conciliacion/CONC037','PDL','ANGULAR',NULL,'SIMASC_USER',SYSDATETIME(),'ACT');
insert into FUNCIONALIDAD values ('CONC037ReporteNoAceptados', 'Reporte casos no aceptados conciliador', 'app/Conciliacion/CONC037','PDL','ANGULAR','reportesConc','SIMASC_USER',SYSDATETIME(),'ACT');
insert into FUNCIONALIDAD values ('CONC041ReporteNoCompetencia', 'Reporte casos rechazados por competencia', 'app/Conciliacion/CONC041','PDL','ANGULAR','reportesConc','SIMASC_USER',SYSDATETIME(),'ACT');
insert into FUNCIONALIDAD values ('CONC039ReporteCasosPagados', 'Reporte de casos pagados por sede', 'app/Conciliacion/CONC039','PDL','ANGULAR','reportesConc','SIMASC_USER',SYSDATETIME(),'ACT');


insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','reportesConc',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA','ROL_DE_PARTE_ARBITRAJE') and  d.codigo  
		NOT IN ('ACO','SECCON','JEFECON');

insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONC037ReporteNoAceptados',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA','ROL_DE_PARTE_ARBITRAJE') and  d.codigo  
		NOT IN ('ACO','SECCON','JEFECON');

insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONC041ReporteNoCompetencia',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA','ROL_DE_PARTE_ARBITRAJE') and  d.codigo  
		NOT IN ('ACO','SECCON','JEFECON');
		
insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONC039ReporteCasosPagados',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA','ROL_DE_PARTE_ARBITRAJE') and  d.codigo  
		NOT IN ('ACO','SECCON','JEFECON');
		
/* Permisos CON-F-100 Lista de casos pendientes para reparto*/
		
/* TABLE FUNCIONALIDAD_ROL*/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF100CasosPendientesAsignacion';
/* TABLE FUNCIONALIDAD*/
DELETE FUNCIONALIDAD WHERE nombre ='CONF100CasosPendientesAsignacion';
/* INSERT INTO TABLE FUNCIONALIDAD */		
INSERT INTO FUNCIONALIDAD VALUES ('CONF100CasosPendientesAsignacion', 'Lista de casos pendientes para reparto', 'app/Conciliacion/CONF100','PDL','ANGULAR',null,'SIMASC_USER',SYSDATETIME(),'ACT');	
/* INSERT INTO TABLE FUNCIONALIDAD_ROL */
INSERT INTO FUNCIONALIDAD_ROL
SELECT DISTINCT  'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF100CasosPendientesAsignacion',r.id_rol
FROM ROL r JOIN dominio d ON d.codigo = r.nombre
WHERE d.dominio IN ('ROL_PERSONA', 'ROL_DE_PARTE_ARBITRAJE', 'ROL_DE_PARTE_CONCILIACION') AND  d.codigo <> 'ACO' 

/*Permisos CONF103 Modificacion de parametros para reparto de un caso*/
/* TABLE FUNCIONALIDAD_ROL*/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF103ModificacionParametrosRepartoCaso';
/* TABLE FUNCIONALIDAD*/
DELETE FUNCIONALIDAD WHERE nombre ='CONF103ModificacionParametrosRepartoCaso';
/* INSERT INTO TABLE FUNCIONALIDAD */		
INSERT INTO FUNCIONALIDAD VALUES ('CONF103ModificacionParametrosRepartoCaso', 'Modificacion de parametros para reparto de un caso', 'app/Conciliacion/CONF103','PDL','ANGULAR','CONF100CasosPendientesAsignacion','SIMASC_USER',SYSDATETIME(),'ACT');	
/* INSERT INTO TABLE FUNCIONALIDAD_ROL */
INSERT INTO FUNCIONALIDAD_ROL
SELECT DISTINCT  'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF103ModificacionParametrosRepartoCaso',r.id_rol
FROM ROL r JOIN dominio d ON d.codigo = r.nombre
WHERE d.dominio IN ('ROL_PERSONA', 'ROL_DE_PARTE_ARBITRAJE', 'ROL_DE_PARTE_CONCILIACION') AND  d.codigo <> 'ACO' 

/*Permisos CONF060 Acceder a ficha tecnica de conciliacion*/
/* TABLE FUNCIONALIDAD_ROL*/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF060AccederFichaTecnicaConciliacion';
/* TABLE FUNCIONALIDAD*/
DELETE FUNCIONALIDAD WHERE nombre ='CONF060AccederFichaTecnicaConciliacion';
/* INSERT INTO TABLE FUNCIONALIDAD */		
INSERT INTO FUNCIONALIDAD VALUES ('CONF060AccederFichaTecnicaConciliacion', 'Ficha tecnica de conciliacion', 'app/Arbitraje/TRANSF009','PDL','ANGULAR','TRANSF009divFichaTecnica','SIMASC_USER',SYSDATETIME(),'ACT');	
/* INSERT INTO TABLE FUNCIONALIDAD_ROL */
INSERT INTO FUNCIONALIDAD_ROL
SELECT DISTINCT  'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF060AccederFichaTecnicaConciliacion',r.id_rol
FROM ROL r JOIN dominio d ON d.codigo = r.nombre
WHERE d.dominio IN ('ROL_PERSONA', 'ROL_DE_PARTE_ARBITRAJE', 'ROL_DE_PARTE_CONCILIACION') AND  d.codigo NOT IN ('ACO', 'CON', 'SECCON', 'JEFECON') 

/*Permisos CONF092 pestaña porroga cierre caso del CONF060*/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF092tabProrrogaCierreCaso';
/* TABLE FUNCIONALIDAD*/
DELETE FUNCIONALIDAD WHERE nombre ='CONF092tabProrrogaCierreCaso';
/* INSERT INTO TABLE FUNCIONALIDAD */		
INSERT INTO FUNCIONALIDAD VALUES ('CONF092tabProrrogaCierreCaso', 'Solicitud prorroga cierre caso', 'app/Conciliacion/CONF060','PDL','ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER',SYSDATETIME(),'ACT');	
/* INSERT INTO TABLE FUNCIONALIDAD_ROL */
INSERT INTO FUNCIONALIDAD_ROL
SELECT DISTINCT  'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF092tabProrrogaCierreCaso',r.id_rol
FROM ROL r JOIN dominio d ON d.codigo = r.nombre
WHERE d.dominio IN ('ROL_PERSONA', 'ROL_DE_PARTE_ARBITRAJE', 'ROL_DE_PARTE_CONCILIACION') AND  d.codigo <> 'CON'

/*Permisos CONF094 Seleccion manual del tipo de carta a generar*/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF094tabCartas';
DELETE FUNCIONALIDAD WHERE nombre ='CONF094tabCartas';
/* INSERT INTO TABLE FUNCIONALIDAD */		
INSERT INTO FUNCIONALIDAD VALUES ('CONF094tabCartas', 'Seleccion manual del tipo de carta a generar', 'app/Conciliacion/CONF094','PDL','ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER',SYSDATETIME(),'ACT');	
/* INSERT INTO TABLE FUNCIONALIDAD_ROL */
INSERT INTO FUNCIONALIDAD_ROL
SELECT DISTINCT  'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF094tabCartas',r.id_rol
FROM ROL r JOIN dominio d ON d.codigo = r.nombre
WHERE d.dominio IN ('ROL_PERSONA', 'ROL_DE_PARTE_CONCILIACION') AND  d.codigo NOT IN ('CON', 'ACO', 'SECCON')

/* actualizacion de titulo*/
update funcionalidad set titulo = 'Digitalizacion de documentos' where nombre = 'TRANSF009divDigitalizacionDocumentos'

/* Permisos CONF-080 Consultar estado de correspondencia */
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF080tabEstadoCorrespondencia'
DELETE FUNCIONALIDAD WHERE nombre ='CONF080tabEstadoCorrespondencia';
/* INSERT INTO TABLE FUNCIONALIDAD */		
INSERT INTO FUNCIONALIDAD VALUES ('CONF080tabEstadoCorrespondencia', 'Consultar estado de correspondencia', 'app/Conciliacion/CONF080','PDL','ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER',SYSDATETIME(),'ACT');	
/* INSERT INTO TABLE FUNCIONALIDAD_ROL */
INSERT INTO FUNCIONALIDAD_ROL
SELECT DISTINCT  'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF080tabEstadoCorrespondencia',r.id_rol
FROM ROL r JOIN dominio d ON d.codigo = r.nombre
WHERE d.dominio IN ('ROL_PERSONA', 'ROL_DE_PARTE_CONCILIACION') AND  d.codigo NOT IN ('CON', 'ACO', 'SECCON', 'JEFECON')


/* CON-F-107 CON-F-113 CON-F-096 */
insert into dominio values ('APOCON', 'ROL_PERSONA', 'Apoderado de convenio', 'Apoderado de convenio', null, null)
insert into rol (fecha_creacion, nombre, preseleccion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) values (GETDATE(), 'APOCON', 0, 'USUARIO_SIMASC', GETDATE(), 'ACT')

/* Para la funcionalidad ARBF033   */
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF033buttonEnviarCorreoCertificado';
DELETE FUNCIONALIDAD WHERE nombre = 'ARBF033buttonEnviarCorreoCertificado';
insert into FUNCIONALIDAD values ('ARBF033buttonEnviarCorreoCertificado', 'Enviar correo certificado', 'app/Arbitraje/ARBF033','PJT','ANGULAR','TRANSF097tabNotCorreo','SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','ARBF033buttonEnviarCorreoCertificado',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ( 'JEFEARB', 'ABO', 'ASA', 'SEC', 'ARB', 'AUX' );

/* para la pestaña de envio de correo certificado TRANSF097tabNotCorreo   */
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF097tabNotCorreo';
insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','TRANSF097tabNotCorreo',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('JEFEARB', 'ABO', 'ASA', 'SEC', 'ARB', 'AUX' );

/* para la pestaña: Consultar notificaciones enviadas por Certimail */
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF056tabNotiCertiMail';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF056tabNotiCertiMail';
insert into FUNCIONALIDAD values ('CONF056tabNotiCertiMail', 'Consultar notificaciones enviadas por Certimail', 'app/Conciliacion/CONF056','PDL','ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER',SYSDATETIME(),'ACT');
insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF056tabNotiCertiMail',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ( 'CON', 'ACO', 'SECCON', 'JEFECON' );

/* Para la funcionalidad del boton CONF056buttonEnviarCitacion  */
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF056buttonEnviarCitacion';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF056buttonEnviarCitacion';
insert into FUNCIONALIDAD values ('CONF056buttonEnviarCitacion', 'Enviar citación por correo certificado', 'app/Conciliacion/CONF056','PDL','ANGULAR','CONF056tabNotiCertiMail','SIMASC_USER',SYSDATETIME(),'ACT');
insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF056buttonEnviarCitacion',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ( 'CON', 'ACO', 'SECCON', 'JEFECON' );
			
			
----%%%% Para la funcionalidad del div de CONF055SeleccionarPartesCitacion CON-F-055 %%%%
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF055SeleccionarPartesCitacion';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF055SeleccionarPartesCitacion';
insert into FUNCIONALIDAD values ('CONF055SeleccionarPartesCitacion', 'Seleccionar partes para citación por correo certificado', 'app/Conciliacion/CONF055','PDL','ANGULAR','CONF056buttonEnviarCitacion','SIMASC_USER',SYSDATETIME(),'ACT');
insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF055SeleccionarPartesCitacion',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ( 'CON', 'ACO', 'SECCON', 'JEFECON' );
			
/* Para la pestaña consultar informacion de llamadas CONF-082*/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF082tabInfoLlamadas';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF082tabInfoLlamadas';
INSERT INTO FUNCIONALIDAD VALUES ('CONF082tabInfoLlamadas', 'Consultar información de llamadas realizadas a las partes', 'app/Conciliacion/CONF082','PDL','ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF082tabInfoLlamadas',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ( 'CON','SECCON', 'AUX' );
			
/* Para el boton adicionar informacion llamada CONF-081*/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF081AdiccionInfoLlamada';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF081AdiccionInfoLlamada';
INSERT INTO FUNCIONALIDAD VALUES ('CONF081AdiccionInfoLlamada', 'Adicionar informacion de llamadas', 'app/Conciliacion/CONF081','PDL','ANGULAR','CONF082tabInfoLlamadas','SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF081AdiccionInfoLlamada',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ( 'CON','SECCON', 'AUX', 'ACO');
			
/* caso de uso  CONF-090 Consultar Audiencias */
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF090tabAudiencias';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF090tabAudiencias';
INSERT INTO FUNCIONALIDAD VALUES ('CONF090tabAudiencias', 'Consultar Audiencias', 'app/Conciliacion/CONF090','PDL','ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF090tabAudiencias',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ( 'CON','SECCON', 'ACO', 'JEFECON' );


/*rago inicio de conflicto*/
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('1','RANGO_INICIO_DE_CONFLICTO','De 1 a 30 días (Hasta 1 mes).','Valor provisto por el ministerio de justicia',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('2','RANGO_INICIO_DE_CONFLICTO','De 31 días a 180 días (Entre 2 y 6 meses).','Valor provisto por el ministerio de justicia',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('3','RANGO_INICIO_DE_CONFLICTO','Superior a  180 días (Entre 7 y 12 meses).','Valor provisto por el ministerio de justicia',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('4','RANGO_INICIO_DE_CONFLICTO','Superior a 365 días (Superior a un año).','Valor provisto por el ministerio de justicia',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('5','RANGO_INICIO_DE_CONFLICTO','No informa','Valor provisto por el ministerio de justicia',null,null);

/*lugar audiencia*/
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('VIRTUAL','TIPO_LUGAR_AUDIENCIA','Virtual','Audiencia virtual',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('PRESENC','TIPO_LUGAR_AUDIENCIA','Presencial','Audiencia presencial',null,null);

/*Tipo cuantia conciliacion*/
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('DETERMIN','TIPO_CUANTIA_CONCILIACION','Determinada','',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('INDETERM','TIPO_CUANTIA_CONCILIACION','Indeterminada','',null,null);

/*tipo convenio*/
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('JORNADA','TIPO_CONVENIO','Jornada','Define el tipo de convenio como Jornada',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('CONVENIO','TIPO_CONVENIO','Convenio','Define el tipo de convenio como Convenio',null,null);

/*duracion audiencia*/
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('DOSHORAS','DURACION_AUDIENCIA','Dos horas','Define el tiempo que se puede demorar las audiencias de un servicio',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('UNAHORA','DURACION_AUDIENCIA','Una hora','Define el tiempo que se puede demorar las audiencias de un servicio',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('MEDIAHOR','DURACION_AUDIENCIA','Media hora','Define el tiempo que se puede demorar las audiencias de un servicio',null,null);

/*parte solicita servicio*/
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('DOSPARTE','PARTE_SOLICITA_SERVICIO','Las dos partes','La parte o partes que solicitan el servicio de conciliación cuando hay convenio',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('UNAPARTE','PARTE_SOLICITA_SERVICIO','Solo una de las partes','La parte o partes que solicitan el servicio de conciliación cuando hay convenio',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('ABOGPART','PARTE_SOLICITA_SERVICIO','El abogado de una de las partes','La parte o partes que solicitan el servicio de conciliación cuando hay convenio',null,null);

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF107RadicarCasoConvenio';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF107RadicarCasoConvenio';

INSERT INTO [dbo].[FUNCIONALIDAD]
     VALUES
     ('CONF107RadicarCasoConvenio', 'Radicar caso convenio', 'app/Conciliacion/CONF107','PDL','ANGULAR',NULL,'SIMASC_USER',GETDATE(),'ACT')
GO

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF107RadicarCasoConvenio',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('SECCON', 'RCO', 'APOCON');

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF069CargueCasos';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF069CargueCasos';

INSERT INTO [dbo].[FUNCIONALIDAD]
     VALUES
     ('CONF069CargueCasos', 'Cargar casos a partir de archivos', 'app/Conciliacion/CONF069','PDL','ANGULAR',NULL,'SIMASC_USER',GETDATE(),'ACT')
GO

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF069CargueCasos',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('SECCON', 'JEFECON', 'APOCON', 'ABO');

/* insert de servicio */

insert into servicio (nombre, tipo, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('CONVENIO CONCILIACIÓN', 'PDL', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into servicio (nombre, tipo, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('JORNADA CONCILIACIÓN', 'PDL', 'USUARIO_SIMASC', GETDATE(), 'ACT')
update servicio set nombre = 'CONCILIACIÓN TRAMITE ORDINARIO' where id_servicio = 1 
			
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('PERSONA_REMITENTE_DEL_SERVICIO','PERSREMI','pruebasimasc@gmail.com','correo del sistema por servicio.','USUARIO_SIMASC',GETDATE(),'ACT',1);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('PERSONA_REMITENTE_DEL_SERVICIO','PERSREMI','pruebasimasc@gmail.com','correo del sistema por servicio.','USUARIO_SIMASC',GETDATE(),'ACT',8);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('PERSONA_REMITENTE_DEL_SERVICIO','PERSREMI','pruebasimasc@gmail.com','correo del sistema por servicio.','USUARIO_SIMASC',GETDATE(),'ACT',9);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('TIEMPO_MAX_ANALISIS_CASO','TMAX_ANC','8','Es el tiempo máximo en horas que tienen los abogados para analizar un caso que ha pasado para control de legalidad.','USUARIO_SIMASC',GETDATE(),'ACT',1);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('TIEMPO_MAX_ANALISIS_CASO','TMAX_ANC','8','Es el tiempo máximo en horas que tienen los abogados para analizar un caso que ha pasado para control de legalidad.','USUARIO_SIMASC',GETDATE(),'ACT',8);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('TIEMPO_MAX_ANALISIS_CASO','TMAX_ANC','8','Es el tiempo máximo en horas que tienen los abogados para analizar un caso que ha pasado para control de legalidad.','USUARIO_SIMASC',GETDATE(),'ACT',9);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('MINIMO_DE_DIAS_PARA_PROGRAMAR_AUDIENCIA','PROGAUDI','6','Mínimo de días hábiles que se debe esperar desde la radicación del caso de conciliación de trámite ordinario para programar la audiencia de conciliación.','USUARIO_SIMASC',GETDATE(),'ACT',1);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('MAXIMO_DE_DIAS_PARA_PROGRAMAR_AUDIENCIA','PROGAUDI','10','Máximo de días hábiles que pueden pasar desde la fecha mínima de programación de audiencia para el caso de conciliación trámite ordinario','USUARIO_SIMASC',GETDATE(),'ACT',1);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('HORA_FIN_AGENDA','PROGAUDI','17:00','Valor que indica la hora del día inicial en la que se puede agendar al conciliador o al auxiliar administrativo. ','USUARIO_SIMASC',GETDATE(),'ACT',1);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('HORA_FIN_AGENDA','PROGAUDI','17:00','Valor que indica la hora del día inicial en la que se puede agendar al conciliador o al auxiliar administrativo. ','USUARIO_SIMASC',GETDATE(),'ACT',8);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('HORA_FIN_AGENDA','PROGAUDI','17:00','Valor que indica la hora del día inicial en la que se puede agendar al conciliador o al auxiliar administrativo. ','USUARIO_SIMASC',GETDATE(),'ACT',9);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('HORA_INICIO_AGENDA','PROGAUDI','8:00','Valor que indica la hora del día final en la que se puede agendar al conciliador o al auxiliar administrativo.','USUARIO_SIMASC',GETDATE(),'ACT',1);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('HORA_INICIO_AGENDA','PROGAUDI','8:00','Valor que indica la hora del día final en la que se puede agendar al conciliador o al auxiliar administrativo.','USUARIO_SIMASC',GETDATE(),'ACT',8);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('HORA_INICIO_AGENDA','PROGAUDI','8:00','Valor que indica la hora del día final en la que se puede agendar al conciliador o al auxiliar administrativo. ','USUARIO_SIMASC',GETDATE(),'ACT',9);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('DURACION_DE_AUDIENCIA','PROGAUDI','DOSHORA','Duracion en horas de una audiencia.','USUARIO_SIMASC',GETDATE(),'ACT',1);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('DURACION_DE_AUDIENCIA','PROGAUDI','DOSHORA','Duracion en horas de una audiencia.','USUARIO_SIMASC',GETDATE(),'ACT',8);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('DURACION_DE_AUDIENCIA','PROGAUDI','DOSHORA','Duracion en horas de una audiencia.','USUARIO_SIMASC',GETDATE(),'ACT',9);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('TIEMPO_MAXIMO_PARA_CORRECCION_DOCUMENTO','PROGAUDI','5','Tiempo máximo que tiene el conciliador para corregir el acta o la constancia por control de legalidad.','USUARIO_SIMASC',GETDATE(),'ACT',1);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('TIEMPO_MAXIMO_PARA_CORRECCION_DOCUMENTO','PROGAUDI','5','Tiempo máximo que tiene el conciliador para corregir el acta o la constancia por control de legalidad.','USUARIO_SIMASC',GETDATE(),'ACT',8);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('TIEMPO_MAXIMO_PARA_CORRECCION_DOCUMENTO','PROGAUDI','5','Tiempo máximo que tiene el conciliador para corregir el acta o la constancia por control de legalidad.','USUARIO_SIMASC',GETDATE(),'ACT',9);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('LIMITE_PARA_PAGO_ESCALONADO','PAGESCA','118','Si la cuantía del caso excede este límite el pago del caso es escalonado, lo que quiere decir que el cliente paga un porcentaje inicial del total a pagar por el servicio, y dependiendo del resultado de la conciliación paga un excedente. (SMMLV);','USUARIO_SIMASC',GETDATE(),'ACT',1);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('LIMITE_PARA_PAGO_ESCALONADO','PAGESCA','118','Si la cuantía del caso excede este límite el pago del caso es escalonado, lo que quiere decir que el cliente paga un porcentaje inicial del total a pagar por el servicio, y dependiendo del resultado de la conciliación paga un excedente. (SMMLV);','USUARIO_SIMASC',GETDATE(),'ACT',8);
INSERT INTO PARAMETRO_DE_SERVICIO (nombre,tipo_parametro,valor,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,id_servicio) VALUES
('LIMITE_PARA_PAGO_ESCALONADO','PAGESCA','118','Si la cuantía del caso excede este límite el pago del caso es escalonado, lo que quiere decir que el cliente paga un porcentaje inicial del total a pagar por el servicio, y dependiendo del resultado de la conciliación paga un excedente. (SMMLV);','USUARIO_SIMASC',GETDATE(),'ACT',9);



/*tipo servicio rol*/
 INSERT INTO TIPO_DE_SERVICIO_ROL(id_rol, tipo_servicio, id_usuario_modificaicon,fecha_ultima_modificacion,estado_registro)
 VALUES( (SELECT id_rol FROM ROL where nombre = 'CONCOM'), 'PDL','USUARIO_SIMASC',GETDATE(),'ACT')

 INSERT INTO TIPO_DE_SERVICIO_ROL(id_rol, tipo_servicio, id_usuario_modificaicon,fecha_ultima_modificacion,estado_registro)
 VALUES( (SELECT id_rol FROM ROL where nombre = 'CON'), 'PDL','USUARIO_SIMASC',GETDATE(),'ACT')
 
  
/*tipo actividad agenda*/
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('CAPACITA','TIPO_ACTIVIDAD_AGENDA','Capacitaciones','Capacitaciones',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('VACAS','TIPO_ACTIVIDAD_AGENDA','Vacaciones','Vacaciones',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('INCAMEDI','TIPO_ACTIVIDAD_AGENDA','Incapacidad médica','Incapacidad médica',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('AUDICON','TIPO_ACTIVIDAD_AGENDA','Audiencia conciliación ','Audiencia de conciliación',null,null);

 
 /*ADM-F-125 ADM-F-124*/
 
IF NOT EXISTS (Select * FROM DBO.DOMINIO WHERE codigo = 'ROLPARTE' and dominio = 'AGRUPADOR_ROL_PARTE')
BEGIN
	INSERT INTO DBO.DOMINIO (codigo, dominio, nombre,descripcion)
	values('ROLPARTE','AGRUPADOR_ROL_PARTE','Agrupador rol parte','Agrupador rol parte');
END

--INSERT OBLIGATORIOS
IF NOT EXISTS (Select * FROM DBO.SERVICIO_MATERIA WHERE id_servicio = 1 and id_materia = 2)
BEGIN
	INSERT INTO DBO.SERVICIO_MATERIA (id_servicio, id_materia, id_usuario_modificacion,
	fecha_ultima_modificacion, estado_registro)
	VALUES(1,2,'SIMASC_USUARIO',GETDATE(),'ACT');
END

IF NOT EXISTS (Select * FROM DBO.ROL WHERE nombre = 'SOLTAN')
BEGIN
	INSERT INTO DBO.ROL (nombre, preseleccion,
	id_usuario_modificacion,fecha_creacion,estado_registro,fecha_ultima_modificacion)
	VALUES('SOLTAN',0,'SIMASC_USUARIO',GETDATE(),'ACT',GETDATE());
END

IF NOT EXISTS (Select * FROM DBO.DOMINIO WHERE codigo = 'SOLTAN' and dominio = 'ROL_DE_PARTE_CONCILIACION')
BEGIN
	INSERT INTO dbo.DOMINIO (codigo, dominio, nombre, descripcion)
	VALUES('SOLTAN', 'ROL_DE_PARTE_CONCILIACION', 'Solicitante', 'Solicitante');
END

IF NOT EXISTS (Select * FROM DBO.PARAMETROS_GENERALES WHERE codigo = 'MINDPA' and nombre = 'PROGRAMACION_AUDIENCIA')
BEGIN
	INSERT INTO dbo.PARAMETROS_GENERALES(codigo,tipo,nombre,valor_numerico,id_usuario_modificacion,
	fecha_ultima_modificacion,estado_registro)
	VALUES('MINDPA','PROGRAMACION_AUDIENCIA','MIN_DIAS_PROGRAMACION_AUDIENCIA',6,'SIMASC',GETDATE(),'ACT');
END

IF NOT EXISTS (Select * FROM DBO.PARAMETROS_GENERALES WHERE codigo = 'MAXDPA' and nombre = 'PROGRAMACION_AUDIENCIA')
BEGIN
	INSERT INTO
	dbo.PARAMETROS_GENERALES(codigo,tipo,nombre,valor_numerico,id_usuario_modificacion,
	fecha_ultima_modificacion,estado_registro)
	VALUES('MAXDPA','PROGRAMACION_AUDIENCIA','MAX_DIAS_PROGRAMACION_AUDIENCIA',10,'SIMASC',GETDATE(),'ACT')
	;
	
END

--ADM-F-036, ADM-F-043
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='ADM036agenda';
DELETE FUNCIONALIDAD WHERE nombre = 'ADM036agenda';

INSERT INTO [dbo].[FUNCIONALIDAD]
     VALUES
     ('ADM036agenda', 'Consultar agenda', 'app/Conciliacion/ADM036','PDL','ANGULAR',NULL,'SIMASC_USER',GETDATE(),'ACT')
GO

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','ADM036agenda',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('SECCON', 'ACO', 'JEFECON','CON');

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='ADM036agendaModificar';
DELETE FUNCIONALIDAD WHERE nombre = 'ADM036agendaModificar';

INSERT INTO [dbo].[FUNCIONALIDAD]
     VALUES
     ('ADM036agendaModificar', 'Modificar agenda', 'app/Conciliacion/ADM043','PDL','ANGULAR','ADM036agenda','SIMASC_USER',GETDATE(),'ACT')
GO

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','ADM036agendaModificar',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('SECCON', 'ACO', 'JEFECON','ASA');

-- TIPO_FECHA_CASO CON-C-035, CON-C-038

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONC035ReporteCasosCobrados';
DELETE FUNCIONALIDAD WHERE nombre = 'CONC035ReporteCasosCobrados';

INSERT INTO [dbo].[FUNCIONALIDAD]
     VALUES
     ('CONC035ReporteCasosCobrados', 'Casos cobrados', 'app/Conciliacion/CONC035','PDL','ANGULAR','reportesConc','SIMASC_USER',GETDATE(),'ACT')
GO

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONC035ReporteCasosCobrados',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'JEFECON', 'SECCON');

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONC038ReporteCasosNoCobrados';
DELETE FUNCIONALIDAD WHERE nombre = 'CONC038ReporteCasosNoCobrados';

INSERT INTO [dbo].[FUNCIONALIDAD]
     VALUES
     ('CONC038ReporteCasosNoCobrados', 'Casos no cobrados', 'app/Conciliacion/CONC038','PDL','ANGULAR','reportesConc','SIMASC_USER',GETDATE(),'ACT')
GO

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONC038ReporteCasosNoCobrados',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'JEFECON', 'SECCON');


-- CON-F-107
insert into dominio values ('1','DIAS_DISPONIBILIDAD_PERSONA','Domingo','Dia de dísponibilidad','NULL','NULL')
insert into dominio values ('2','DIAS_DISPONIBILIDAD_PERSONA','Lunes','Dia de dísponibilidad','NULL','NULL')
insert into dominio values ('3','DIAS_DISPONIBILIDAD_PERSONA','Martes','Dia de dísponibilidad','NULL','NULL')
insert into dominio values ('4','DIAS_DISPONIBILIDAD_PERSONA','Miércoles','Dia de dísponibilidad','NULL','NULL')
insert into dominio values ('5','DIAS_DISPONIBILIDAD_PERSONA','Jueves','Dia de dísponibilidad','NULL','NULL')
insert into dominio values ('6','DIAS_DISPONIBILIDAD_PERSONA','Sábado','Dia de dísponibilidad','NULL','NULL')


-- ADM-F-043
INSERT INTO DOMINIO
SELECT 1,'TIPO_DE_EVENTO_DE_AGENDA','Conciliación','Conciliación',NULL,NULL
INSERT INTO DOMINIO
SELECT 2,'TIPO_DE_EVENTO_DE_AGENDA','Arbitraje','Arbitraje',NULL,NULL
INSERT INTO DOMINIO					
SELECT 3,'TIPO_DE_EVENTO_DE_AGENDA','Capacitación','Capacitación',NULL,NULL
INSERT INTO DOMINIO					
SELECT 4,'TIPO_DE_EVENTO_DE_AGENDA','Extracurricular','Extracurricular',NULL,NULL
INSERT INTO DOMINIO					
SELECT 5,'TIPO_DE_EVENTO_DE_AGENDA','Personal','Personal',NULL,NULL
INSERT INTO DOMINIO					
SELECT 6,'TIPO_DE_EVENTO_DE_AGENDA','Programada por el Centro','Programada por el Centro',NULL,NULL



UPDATE DOMINIO set dominio = 'RESULTADO_CASO_CONCILIACION', nombre = 'Acuerdo' WHERE DOMINIO ='RESULTADO_CASO' AND codigo = 'CAC'; 
UPDATE DOMINIO set dominio = 'RESULTADO_CASO_CONCILIACION', nombre = 'Imposibilidad' WHERE DOMINIO ='RESULTADO_CASO' AND codigo = 'CIM'; 
UPDATE DOMINIO set dominio = 'RESULTADO_CASO_CONCILIACION', nombre = 'Inasistencia' WHERE DOMINIO ='RESULTADO_CASO' AND codigo = 'CIN';
UPDATE DOMINIO set dominio = 'RESULTADO_CASO_CONCILIACION', nombre = 'Falta de competencia' WHERE DOMINIO ='RESULTADO_CASO' AND codigo = 'FALTCOMP' 
UPDATE DOMINIO set dominio = 'RESULTADO_CASO_CONCILIACION', nombre = 'Arreglo directo' WHERE DOMINIO ='RESULTADO_CASO' AND codigo = 'CDA';
UPDATE DOMINIO set dominio = 'RESULTADO_CASO_CONCILIACION', nombre = 'Cancelación' WHERE DOMINIO ='RESULTADO_CASO' AND codigo = 'CCN'; 

INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('RESAUDI','CLASIFICADOR_RESULTADO_CASO','Resultado audiencia','',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('FALTCOMP','CLASIFICADOR_RESULTADO_CASO','Falta de competencia','',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('SINTRAM','CLASIFICADOR_RESULTADO_CASO','No se realizo el tramite','No se envian al ministerio por que no se realiza el tramite',null,null);

INSERT INTO CLASIFICADOR_DOMINIO (codigo_clasificado, dominio_clasificado,codigo_clasificador,dominio_clasificador,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)
VALUES ('CAC','RESULTADO_CASO_CONCILIACION','RESAUDI','CLASIFICADOR_RESULTADO_CASO','USER_SIMASC',GETDATE(),'ACT');
INSERT INTO CLASIFICADOR_DOMINIO (codigo_clasificado, dominio_clasificado,codigo_clasificador,dominio_clasificador,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)
VALUES ('CIM','RESULTADO_CASO_CONCILIACION','RESAUDI','CLASIFICADOR_RESULTADO_CASO','USER_SIMASC',GETDATE(),'ACT');
INSERT INTO CLASIFICADOR_DOMINIO (codigo_clasificado, dominio_clasificado,codigo_clasificador,dominio_clasificador,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)
VALUES ('CIN','RESULTADO_CASO_CONCILIACION','RESAUDI','CLASIFICADOR_RESULTADO_CASO','USER_SIMASC',GETDATE(),'ACT');

INSERT INTO CLASIFICADOR_DOMINIO (codigo_clasificado, dominio_clasificado,codigo_clasificador,dominio_clasificador,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)
VALUES ('FALTCOMP','RESULTADO_CASO_CONCILIACION','FALTCOMP','CLASIFICADOR_RESULTADO_CASO','USER_SIMASC',GETDATE(),'ACT');

INSERT INTO CLASIFICADOR_DOMINIO (codigo_clasificado, dominio_clasificado,codigo_clasificador,dominio_clasificador,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)
VALUES ('CDA','RESULTADO_CASO_CONCILIACION','SINTRAM','CLASIFICADOR_RESULTADO_CASO','USER_SIMASC',GETDATE(),'ACT');
INSERT INTO CLASIFICADOR_DOMINIO (codigo_clasificado, dominio_clasificado,codigo_clasificador,dominio_clasificador,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)
VALUES ('CCN','RESULTADO_CASO_CONCILIACION','SINTRAM','CLASIFICADOR_RESULTADO_CASO','USER_SIMASC',GETDATE(),'ACT');

/*
cargue de archivos con excel 
*/
--eliminacion de data no valida

DELETE DOMINIO WHERE dominio = 'TIPO_CONVENIO' AND codigo IN   ('01','02');

/*
Integracion del ministerio
*/

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO (CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('ABOGPART','PARTE_SOLICITA_SERVICIO','3','MINS','USER_SIMASC',getdate(),'ACT');
INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO (CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('DOSPARTE','PARTE_SOLICITA_SERVICIO','1','MINS','USER_SIMASC',getdate(),'ACT');
INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO (CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('UNAPARTE','PARTE_SOLICITA_SERVICIO','2','MINS','USER_SIMASC',getdate(),'ACT');

--parametros generales
INSERT INTO PARAMETROS_GENERALES(codigo,tipo,nombre,valor_texto,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
VALUES ('USUARIO','USUARIO_MINISTERIO','USUARIO_NOMBRE','CC99999999990001','usuario ministerio de justica','USUARIO_SIMASC',GETDATE(),'ACT');
INSERT INTO PARAMETROS_GENERALES(codigo,tipo,nombre,valor_texto,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
VALUES ('CLAVE','USUARIO_MINISTERIO','USUARIO_CONTRASENA','Servicio.2016','clave ministerio de justica','USUARIO_SIMASC',GETDATE(),'ACT');

--Url servicios de integracion
INSERT INTO PARAMETROS_GENERALES(codigo,tipo,nombre,valor_texto,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
VALUES ('URLMIN','URL_SERVICIOS','URL_MINISTERIO','http://201.217.213.208:86/SICAACWebService.svc?wsdl','Url servicio ministerio de justicia','USUARIO_SIMASC',GETDATE(),'ACT');
INSERT INTO PARAMETROS_GENERALES(codigo,tipo,nombre,valor_texto,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
VALUES ('URLSIR_C','URL_SERVICIOS','URL_SIREP_CREAR','http://ihsd:80/WSRegistros/services/AutoridadCompetenteWS?wsdl','Url servicio SIREP crear','USUARIO_SIMASC',GETDATE(),'ACT');
INSERT INTO PARAMETROS_GENERALES(codigo,tipo,nombre,valor_texto,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
VALUES ('URLSIR_B','URL_SERVICIOS','URL_SIREP_CONSULTAR','http://ihsd:80/WSClientes/services/ServiciosClientesWS?wsdl','Url servicio SIREP consultar','USUARIO_SIMASC',GETDATE(),'ACT');
INSERT INTO PARAMETROS_GENERALES(codigo,tipo,nombre,valor_texto,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
VALUES ('URLSIR_A','URL_SERVICIOS','URL_SIREP_ACTUALIZAR','http://ihsd:80/WSClientes/services/ServiciosClientesWS?wsdl','Url servicio SIREP actualizar','USUARIO_SIMASC',GETDATE(),'ACT');
INSERT INTO PARAMETROS_GENERALES(codigo,tipo,nombre,valor_texto,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
VALUES ('URLPUP','URL_SERVICIOS','URL_PUP','http://172.16.1.220/WCFPUPXrecaudos/Servicio.svc?wsdl','Url servicio PUP','USUARIO_SIMASC',GETDATE(),'ACT');
INSERT INTO PARAMETROS_GENERALES(codigo,tipo,nombre,valor_texto,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
VALUES ('URLPUP_M','URL_SERVICIOS','URL_PUP_MOCK','http://172.16.3.58:8088/mockPagosBaisc?WSDL','Url servicio PUP MOCK','USUARIO_SIMASC',GETDATE(),'ACT');

/* permisos */
DELETE FUNCIONALIDAD_ROL WHERE NOMBRE_FUNCIONALIDAD LIKE 'TRANSF012divRadicacionDocumentos' AND 
id_rol = (SELECT id_rol FROM ROL WHERE nombre = 'RCO')


/* estado agenda persona */ 

INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('CANCELA','ESTADO_AGENDA_PERSONA','Agenda cancelada',null,null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('PROGRAMA','ESTADO_AGENDA_PERSONA','Agenda programada',null,null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('VALIDADA','ESTADO_AGENDA_PERSONA','Agenda validada',null,null,null);


/*HOMOLOGACION_SISMTEMA_EXTERNO RESULTADO CASO*/
INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO (CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('1','RESULTADOS_AUDIENCIA','5','MINS','USER_SIMASC',getdate(),'ACT');
INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO (CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('2','RESULTADOS_AUDIENCIA','17','MINS','USER_SIMASC',getdate(),'ACT');
INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO (CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('3','RESULTADOS_AUDIENCIA','4','MINS','USER_SIMASC',getdate(),'ACT');
INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO (CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('4','RESULTADOS_AUDIENCIA','12','MINS','USER_SIMASC',getdate(),'ACT');
INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO (CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('5','RESULTADOS_AUDIENCIA','12','MINS','USER_SIMASC',getdate(),'ACT');
INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO (CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('6','RESULTADOS_AUDIENCIA','12','MINS','USER_SIMASC',getdate(),'ACT');
INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO (CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('7','RESULTADOS_AUDIENCIA','12','MINS','USER_SIMASC',getdate(),'ACT');
INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO (CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('8','RESULTADOS_AUDIENCIA','10','MINS','USER_SIMASC',getdate(),'ACT');
INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO (CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('9','RESULTADOS_AUDIENCIA','12','MINS','USER_SIMASC',getdate(),'ACT');
INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO (CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('10','RESULTADOS_AUDIENCIA','8','MINS','USER_SIMASC',getdate(),'ACT');

/*
parametros de la audiencia
*/

INSERT INTO PARAMETROS_GENERALES (codigo,tipo,nombre,valor_numerico,valor_texto,valor_fecha,valor_booleano,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
	VALUES ('AUDI_REC','NUMERO AUDIENCIAS PARA RECOBRO','NUMERO_AUDIENCIAS_PARA_RECOBRO',4,null,null,null,'Este número menos 1 corresponde al número de audiencias que se pueden programar en un caso de conciliación de trámite ordinario sin hacer un recobro al cliente.','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');

INSERT INTO PARAMETROS_GENERALES (codigo,tipo,nombre,valor_numerico,valor_texto,valor_fecha,valor_booleano,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
	VALUES ('ADI_AUDI','COBRO ADICIONAL POR AUDIENCIA','PORCENTAJE_COBRO_ADICIONAL_X_AUDIENCIA',10,null,null,null,'Define el porcentaje adicional a cobrar al cliente cuando se excede el número de audiencias especificado en el parámetro NUMERO_AUDIENCIAS_PARA_RECOBRO','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');

/*
RESULTADO CIERRE DEL CASO 
*/

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO (CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('CCN','RESULTADO_CASO_CONCILIACION','9','MINS','USER_SIMASC',getdate(),'ACT');
INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO (CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('CDA','RESULTADO_CASO_CONCILIACION','10','MINS','USER_SIMASC',getdate(),'ACT')


/*
TIPO_EVENTO_MINISTERIO
*/

INSERT DOMINIO VALUES('MINJUS','TIPO_EVENTO','Creacion del caso en el ministerio de justicia','Creacion del caso en el ministerio de justicia',null,null);
**
 * Parametros generales para firma digita pdf CON-F-096
 */
 */
 
 Insert into PARAMETROS_GENERALES (codigo,tipo,nombre,valor_texto,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)
values ('WSPDF','FIRMA_DIGITAL_PDF','Web Service firma digital','http://ihsd/WSeSignWEB/EsignService/WEB-INF/wsdl/EsignService.wsdl','Web service que firmara  de manera digital el pdf','USUARIO_SIMASC',getdate(),'ACT'),
	   ('CLIENPDF','FIRMA_DIGITAL_PDF','Id cliente firma digital','devolucionesvir','Id que se enviara al Web service de firma digital pdf','USUARIO_SIMASC',getdate(),'ACT'),
	   ('PASSPDF','FIRMA_DIGITAL_PDF','Password firma digital','ZT8HXCRWdmfT9jHFJvZ/lw==','password que se enviara al Web service de firma digital pdf','USUARIO_SIMASC',getdate(),'ACT'),
	   ('POLPDF','FIRMA_DIGITAL_PDF','Politica firma digital','7','Politica que se enviara al Web service de firma digital pdf','USUARIO_SIMASC',getdate(),'ACT')

-- fin correccion de defectos



-- control de cambios gestor documental

INSERT INTO [dbo].[PARAMETROS_GENERALES]
           ([codigo]
           ,[tipo]
           ,[nombre]
           ,[valor_numerico]
           ,[valor_texto]
           ,[valor_fecha]
           ,[valor_booleano]
           ,[id_usuario_modificacion]
           ,[fecha_ultima_modificacion]
           ,[estado_registro])
     VALUES
           ('RUTA_DOC', 'GES_DOCU', 'PREFIJO_ON_BASE', null, 'https://onbaseqas.ccb.org.co', null, null, 'SIMASC_USER', GETDATE(), 'ACT')

           
 -- Agrupamientos ROLPARTE          
           
        INSERT INTO AGRUPAMIENTO_ROL 
		SELECT 85,'1','ROLPARTE','USUARIO_SIMASC',GETDATE(),'ACT'
		UNION ALL
		SELECT 86,'1','ROLPARTE','USUARIO_SIMASC',GETDATE(),'ACT'
		UNION ALL
		SELECT 87,'1','ROLPARTE','USUARIO_SIMASC',GETDATE(),'ACT'
		UNION ALL
		SELECT 88,'1','ROLPARTE','USUARIO_SIMASC',GETDATE(),'ACT'

/**
------------CON-F-089--------------------------------------------------------------------
**/

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF089buttonAdicionarParte';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF089ModificarParte';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF089VisualizarNombreParte';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF089EliminarParte';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF089botonGenerarClave';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF089divListarPartes';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF089ConsultaPartesCaso';

DELETE FUNCIONALIDAD WHERE nombre = 'CONF089buttonAdicionarParte';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF089ModificarParte';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF089VisualizarNombreParte';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF089EliminarParte';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF089botonGenerarClave';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF089divListarPartes';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF089ConsultaPartesCaso';

INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('CONF089ConsultaPartesCaso', 'Lista partes del caso', 'app/Conciliacion/CONF089','PDL','ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER',GETDATE(),'ACT')
INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('CONF089divListarPartes', 'Consulta de partes', 'app/Conciliacion/CONF089','PDL','ANGULAR','CONF089ConsultaPartesCaso','SIMASC_USER',GETDATE(),'ACT')
INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('CONF089botonGenerarClave', 'Modificar Parte', 'app/Conciliacion/CONF089','PDL','ANGULAR','CONF089ConsultaPartesCaso','SIMASC_USER',GETDATE(),'ACT')
INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('CONF089EliminarParte', 'Visualizar nombre parte', 'app/Conciliacion/CONF089','PDL','ANGULAR','CONF089ConsultaPartesCaso','SIMASC_USER',GETDATE(),'ACT')
INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('CONF089VisualizarNombreParte', 'Eliminar parte', 'app/Conciliacion/CONF089','PDL','ANGULAR','CONF089ConsultaPartesCaso','SIMASC_USER',GETDATE(),'ACT')
INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('CONF089ModificarParte', 'Generar clave', 'app/Conciliacion/CONF089','PDL','ANGULAR','CONF089ConsultaPartesCaso','SIMASC_USER',GETDATE(),'ACT')
INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('CONF089buttonAdicionarParte', 'Adicionar parte', 'app/Conciliacion/CONF089','PDL','ANGULAR','CONF089ConsultaPartesCaso','SIMASC_USER',GETDATE(),'ACT')

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF089ConsultaPartesCaso',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'JEFECON', 'SECCON', 'CON');

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF089divListarPartes',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'JEFECON', 'SECCON', 'CON');

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF089botonGenerarClave',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'JEFECON', 'SECCON');


insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF089EliminarParte',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'JEFECON', 'SECCON', 'CON');

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF089VisualizarNombreParte',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo IN ('ACO', 'JEFECON', 'SECCON', 'CON');

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF089ModificarParte',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'JEFECON', 'SECCON', 'CON');

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF089buttonAdicionarParte',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'JEFECON', 'SECCON', 'CON');
/**
--------------------------------------------------------------------------------
**/
		
-- creacion de dominio partes de conciliacion CON-F-089

insert into dominio values ('CONVODO', 'ROL_DE_PARTE_CONCILIACION', 'Convocado', 'Convocado', null, null)
insert into dominio values ('CONVOTE', 'ROL_DE_PARTE_CONCILIACION', 'Convocante', 'Convocante', null, null)
insert into dominio values ('APODCDO', 'ROL_DE_PARTE_CONCILIACION', 'Apoderado convocado', 'Apoderado convocado', null, null)
insert into dominio values ('APODCTE', 'ROL_DE_PARTE_CONCILIACION', 'Apoderado convocante', 'Apoderado convocante', null, null) 

--------ROLES ENVIO DE CARTA

INSERT INTO AGRUPAMIENTO_ROL(id_rol,id_servicio,tipo_agrupamiento,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)VALUES(85,1,'R_ENVCTA','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');
INSERT INTO AGRUPAMIENTO_ROL(id_rol,id_servicio,tipo_agrupamiento,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)VALUES(86,1,'R_ENVCTA','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');
INSERT INTO AGRUPAMIENTO_ROL(id_rol,id_servicio,tipo_agrupamiento,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)VALUES(87,1,'R_ENVCTA','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');
INSERT INTO AGRUPAMIENTO_ROL(id_rol,id_servicio,tipo_agrupamiento,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)VALUES(88,1,'R_ENVCTA','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');

INSERT INTO AGRUPAMIENTO_ROL(id_rol,id_servicio,tipo_agrupamiento,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)VALUES(85,8,'R_ENVCTA','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');
INSERT INTO AGRUPAMIENTO_ROL(id_rol,id_servicio,tipo_agrupamiento,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)VALUES(86,8,'R_ENVCTA','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');
INSERT INTO AGRUPAMIENTO_ROL(id_rol,id_servicio,tipo_agrupamiento,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)VALUES(87,8,'R_ENVCTA','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');
INSERT INTO AGRUPAMIENTO_ROL(id_rol,id_servicio,tipo_agrupamiento,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)VALUES(88,8,'R_ENVCTA','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');

INSERT INTO AGRUPAMIENTO_ROL(id_rol,id_servicio,tipo_agrupamiento,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)VALUES(85,9,'R_ENVCTA','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');
INSERT INTO AGRUPAMIENTO_ROL(id_rol,id_servicio,tipo_agrupamiento,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)VALUES(86,9,'R_ENVCTA','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');
INSERT INTO AGRUPAMIENTO_ROL(id_rol,id_servicio,tipo_agrupamiento,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)VALUES(87,9,'R_ENVCTA','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');
INSERT INTO AGRUPAMIENTO_ROL(id_rol,id_servicio,tipo_agrupamiento,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)VALUES(88,9,'R_ENVCTA','USUARIO_SIMASC','2017-03-03 00:00:00.000','ACT');


-- creacion de dominio para etapa de caso conciliacion

insert into dominio values ('CONCILIA', 'ETAPA_CASO', 'CONCILIACIÓN', 'En conciliación', null, null)

-- CON-C-053

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONC053btnGenerarPlantilla';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONC053PlanillaCorrespondencia';
DELETE FUNCIONALIDAD WHERE nombre = 'CONC053btnGenerarPlantilla';
DELETE FUNCIONALIDAD WHERE nombre = 'CONC053PlanillaCorrespondencia';

INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('CONC053PlanillaCorrespondencia', 'Generar planilla de correspondencia', 'app/Administracion/CONC053','PDL','ANGULAR',null,'SIMASC_USER',GETDATE(),'ACT')

INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('CONC053btnGenerarPlantilla', 'Generar planilla', 'app/Administracion/CONC053','PDL','ANGULAR','CONC053PlanillaCorrespondencia','SIMASC_USER',GETDATE(),'ACT')

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONC053PlanillaCorrespondencia',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'SECCON');

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONC053btnGenerarPlantilla',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'SECCON');

           
-- ADM-C-021 Roles de partes que no deben ser tenidos en cuenta en administración
INSERT INTO DOMINIO VALUES ('PARNOADM', 'AGRUPADOR_ROL_PERSONA', 'Roles de partes que no deben tenerse en cuenta en Administración', 'Roles arbitraje y conciliacion de las partes', NULL, NULL);

INSERT INTO CLASIFICADOR_DOMINIO 
SELECT codigo_clasificado, dominio_clasificado, 'PARNOADM', dominio_clasificador, id_usuario_modificacion, GETDATE(), 'ACT' FROM CLASIFICADOR_DOMINIO WHERE codigo_clasificador = 'PARTAPP' AND dominio_clasificador = 'AGRUPADOR_ROL_PERSONA'

INSERT INTO CLASIFICADOR_DOMINIO
SELECT 'PRJ', dominio_clasificado, 'PARNOADM', dominio_clasificador, id_usuario_modificacion, GETDATE(), 'ACT' FROM CLASIFICADOR_DOMINIO WHERE codigo_clasificador = 'PARTAPP' AND dominio_clasificador = 'AGRUPADOR_ROL_PERSONA'

INSERT INTO CLASIFICADOR_DOMINIO
SELECT 'CAL', dominio_clasificado, 'PARNOADM', dominio_clasificador, id_usuario_modificacion, GETDATE(), 'ACT' FROM CLASIFICADOR_DOMINIO WHERE codigo_clasificador = 'PARTAPP' AND dominio_clasificador = 'AGRUPADOR_ROL_PERSONA'

INSERT INTO CLASIFICADOR_DOMINIO
SELECT 'AGE', dominio_clasificado, 'PARNOADM', dominio_clasificador, id_usuario_modificacion, GETDATE(), 'ACT' FROM CLASIFICADOR_DOMINIO WHERE codigo_clasificador = 'PARTAPP' AND dominio_clasificador = 'AGRUPADOR_ROL_PERSONA'

INSERT INTO CLASIFICADOR_DOMINIO
SELECT 'PRO', dominio_clasificado, 'PARNOADM', dominio_clasificador, id_usuario_modificacion, GETDATE(), 'ACT' FROM CLASIFICADOR_DOMINIO WHERE codigo_clasificador = 'PARTAPP' AND dominio_clasificador = 'AGRUPADOR_ROL_PERSONA'

-- actualizacion de tipo de servicio para roles
update rol set tipo_servicio = 'PJT' where nombre in ('ARB', 'SEC', 'ABO', 'DIG', 'TER', 'AUJ', 'ARI', 'AMC', 'PER', 'ARE', 'DTE', 'DDA', 'ASA', 'PRJ', 'CAL', 'AGE', 'DCA', 'PRO', 'APO', 'APD', 'ARBS', 'RADCA', 'ESTARB', 'JEFEARB', 'SECA', 'SECCAC', 'SUBDICAC', 'AUX')
update rol set tipo_servicio = 'PDL' where nombre in ('ACO', 'CON', 'RCO', 'CONINS', 'CONCOM', 'ESTCON', 'JEFECON', 'JEFECCOM', 'SECCON', 'CONVOTE', 'CONVODO', 'APODCTE', 'APODCDO', 'SOLTAN', 'APOCON')

-- Ajuste de permisos al rol Abogado
INSERT INTO FUNCIONALIDAD_ROL
SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'TRANSF044VisualizarNombreParte', r.id_rol
FROM ROL r JOIN dominio d ON d.codigo = r.nombre
WHERE d.dominio in ('ROL_PERSONA', 'ROL_DE_PARTE_ARBITRAJE') and d.codigo = 'ABO';

-- Permisos de acceso a la ficha técnica arbitral
DELETE FROM FUNCIONALIDAD WHERE nombre = 'ARBF097CodigoCasoArbitral'
DELETE FROM FUNCIONALIDAD WHERE nombre = 'ARBF097LinkAccesoArbitral'

INSERT INTO FUNCIONALIDAD VALUES ('ARBF097CodigoCasoArbitral', 'Visualización codigo caso arbitral', 'app/Arbitraje/ARBF097', 'PJT', 'ANGULAR', 'TRANSF009divFichaTecnicaPrearbitral', 'SIMASC_USER', GETDATE(), 'ACT')
INSERT INTO FUNCIONALIDAD VALUES ('ARBF097LinkAccesoArbitral', 'Acceso ficha técnica arbitral', 'app/Arbitraje/ARBF097', 'PJT', 'ANGULAR', 'TRANSF009divFichaTecnicaPrearbitral', 'SIMASC_USER', GETDATE(), 'ACT')

INSERT INTO FUNCIONALIDAD_ROL
SELECT 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF097CodigoCasoArbitral', r.id_rol
FROM ROL r join DOMINIO d on d.codigo = r.nombre
WHERE d.dominio in ('ROL_PERSONA') and  d.codigo  
		IN ('ARB', 'SEC', 'ABO', 'APO', 'APD', 'DIG', 'AUX', 'ARI', 'AMC', 'PER', 'ARE', 'DTE', 'DDA', 'ASA', 'PRJ', 'CAL', 'AGE', 'DCA', 'PRO', 'ARBS', 'RADCA', 'ESTARB', 'JEFEARB', 'SECA', 'JUD', 'SUBDICAC');

INSERT INTO FUNCIONALIDAD_ROL
SELECT 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF097LinkAccesoArbitral', r.id_rol
FROM ROL r join DOMINIO d on d.codigo = r.nombre
WHERE d.dominio in ('ROL_PERSONA') and  d.codigo  
		NOT IN ('ARB', 'SEC', 'ABO', 'APO', 'APD', 'DIG', 'AUX', 'ARI', 'AMC', 'PER', 'ARE', 'DTE', 'DDA', 'ASA', 'PRJ', 'CAL', 'AGE', 'DCA', 'PRO', 'ARBS', 'RADCA', 'ESTARB', 'JEFEARB', 'SECA', 'JUD', 'SUBDICAC');

-- Nueva funcionalidad parametrización reparto Abogado
insert into funcionalidad values ('ARBParamRepartoAbogado', 'Parametrización reparto Abogado', 'app/Arbitraje/ARBParamRepartoAbogado', 'PJT', 'ANGULAR', null, 'SIMASC_USER', GETDATE(), 'ACT')

INSERT INTO FUNCIONALIDAD_ROL (SELECT 'USUARIO_SIMASC', GETDATE(), 'INA', 'ARBParamRepartoAbogado', r.id_rol FROM ROL r JOIN DOMINIO d ON r.nombre = d.codigo AND d.dominio = 'rol_persona' WHERE r.nombre NOT IN ('JEFEARB'))

--ARB-F-082 FECHA RADICACION
DELETE FROM [dbo].[FUNCIONALIDAD_ROL] WHERE [nombre_funcionalidad] = 'ARBF068DatosBasicosFechaRadicacion';
DELETE FROM [dbo].[FUNCIONALIDAD_ROL] WHERE [nombre_funcionalidad] = 'ARBF068DatosBasicosFechaRadicacionConsulta';

DELETE FROM [dbo].[FUNCIONALIDAD] WHERE [nombre] = 'ARBF068DatosBasicosFechaRadicacionConsulta';

INSERT INTO [dbo].[FUNCIONALIDAD] ([nombre],[titulo],[url],[nombre_tipo_funcionalidad],[aplicacion_tipo_funcionalidad],[nombre_funcionalidad_padre],[id_usuario_modificacion],[fecha_ultima_modificacion],[estado_registro]) VALUES ('ARBF068DatosBasicosFechaRadicacionConsulta','Definir fecha de radicación','app/Arbitraje/ARBF068','PJT','ANGULAR','TRANSF097divDatosBasicos','SIMASC_USER',SYSDATETIME(),'ACT');

INSERT INTO FUNCIONALIDAD_ROL
SELECT DISTINCT 'SIMASC_USER', SYSDATETIME(), 'INA', 'ARBF068DatosBasicosFechaRadicacionConsulta', r.id_rol
FROM ROL r JOIN DOMINIO d ON d.codigo = r.nombre
WHERE d.dominio in ('ROL_PERSONA') and d.codigo IN ('ASA','JEFEARB');

INSERT INTO FUNCIONALIDAD_ROL
SELECT DISTINCT 'SIMASC_USER', SYSDATETIME(), 'INA', 'ARBF068DatosBasicosFechaRadicacion', r.id_rol
FROM ROL r JOIN DOMINIO d ON d.codigo = r.nombre
WHERE d.dominio in ('ROL_PERSONA') and d.codigo NOT IN ('ASA','JEFEARB');

--ABR-F-082 EVENTO ASIGNACION MANUAL PRESIDENTE
INSERT INTO [dbo].[DOMINIO] ([codigo],[dominio],[nombre],[descripcion],[codigo_dom_padre],[dominio_padre]) VALUES ('ASGPRS','TIPO_EVENTO','TIPO_EVENTO','Asignación manual',NULL,NULL);

--ARB Continuar Radicacion
DELETE FROM [dbo].[FUNCIONALIDAD_ROL] WHERE [nombre_funcionalidad] = 'ARBContinuarRadicacion';
DELETE FROM [dbo].[FUNCIONALIDAD] WHERE [nombre] = 'ARBContinuarRadicacion';

INSERT INTO [dbo].[FUNCIONALIDAD] ([nombre],[titulo],[url],[nombre_tipo_funcionalidad],[aplicacion_tipo_funcionalidad],[nombre_funcionalidad_padre],[id_usuario_modificacion],[fecha_ultima_modificacion],[estado_registro]) VALUES ('ARBContinuarRadicacion','Continuar Radicación','app/Arbitraje/ARBContinuarRadicacion','PJT','ANGULAR',NULL,'SIMASC_USER',SYSDATETIME(),'ACT');

INSERT INTO FUNCIONALIDAD_ROL
SELECT DISTINCT 'SIMASC_USER', SYSDATETIME(), 'INA', 'ARBContinuarRadicacion', r.id_rol
FROM ROL r JOIN DOMINIO d ON d.codigo = r.nombre
WHERE d.dominio in ('ROL_PERSONA') and d.codigo <> 'SECA';
--

--Adicionar documento formato Excel en tabla dominio
-- XLS
BEGIN
   IF NOT EXISTS (SELECT * FROM DOMINIO WHERE codigo = 'XLS' AND dominio = 'TIPO_ARCHIVO' AND nombre = 'XLS')
   BEGIN
       INSERT INTO DOMINIO VALUES ('XLS', 'TIPO_ARCHIVO', 'XLS', 'Documento en formato excel XLS', null, null)
   END
END;
-- XLSX
BEGIN
   IF NOT EXISTS (SELECT * FROM DOMINIO WHERE codigo = 'XLSX' AND dominio = 'TIPO_ARCHIVO' AND nombre = 'XLSX')
   BEGIN
       INSERT INTO DOMINIO VALUES ('XLSX', 'TIPO_ARCHIVO', 'XLSX', 'Documento en formato excel XLSX', null, null)
   END
END;

-- Creación del nuevo rol Representante de Parte.
INSERT INTO DOMINIO VALUES ('RPD', 'ROL_PERSONA', 'Representante de Parte', 'Representante de Parte', NULL, NULL);
INSERT INTO DOMINIO VALUES ('RDP', 'TIPO_PARTE_CONTRAPARTE', 'Representante de Parte', 'Representante de Parte', NULL, NULL);
INSERT INTO DOMINIO VALUES ('RDP', 'ROL_DE_PARTE_ARBITRAJE', 'Representante de Parte', 'Representante de Parte', NULL, NULL);
INSERT INTO ROL VALUES (getdate(), 'RDP', 0, 'USUARIO_SIMASC', getdate(), 'ACT', 'PJT');

-- Permisos para el nuevo rol Representante de Parte.

INSERT INTO FUNCIONALIDAD_ROL (id_usuario_modificacion, fecha_ultima_modificacion, estado_registro, nombre_funcionalidad, id_rol) SELECT id_usuario_modificacion, fecha_ultima_modificacion, estado_registro, nombre_funcionalidad, 101 FROM FUNCIONALIDAD_ROL WHERE id_rol = 24;
INSERT INTO CLASIFICADOR_DOMINIO (codigo_clasificado, dominio_clasificado, codigo_clasificador, dominio_clasificador, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) SELECT 'RDP', dominio_clasificado, codigo_clasificador, dominio_clasificador, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro FROM CLASIFICADOR_DOMINIO WHERE codigo_clasificado = 'APD';
UPDATE DOMINIO SET codigo_dom_padre = 'APOD', dominio_padre = 'AGRUPADOR_ROL_PERSONA' WHERE codigo = 'RDP' AND dominio = 'ROL_PERSONA';

--Otorgar permisos sobre registrar resultado de la audiencia desde ficha técnica a los roles Abogado, Jefe de arbitraje y Judicante
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF079RegistrarResultado';
DELETE FUNCIONALIDAD where nombre='ARBF079RegistrarResultado';
insert into FUNCIONALIDAD values ('ARBF079RegistrarResultado', 'Registrar Resultado Ficha Tecnica','app/Arbitraje/ARBF079','PJT', 'ANGULAR','ARBF079Audiencia','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
    select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF079RegistrarResultado', r.id_rol
        from ROL r
        join dominio d on d.codigo = r.nombre
        where d.dominio
            in('ROL_PERSONA')
            and d.codigo not in ('JUD','ABO','JEFEARB');
            
-- Adicionar dominio para el evento de selección de secretario de tribunal
INSERT INTO DOMINIO VALUES ('ASGSET', 'TIPO_EVENTO', 'Asignación de secretario de tribunal', 'Asignación manual', null, null);

-- días límite para cierre de audiencias de acuerdo al servicio

INSERT INTO PARAMETRO_DE_SERVICIO VALUES('DIAS_LIMITE_PARA_CIERRE_DE_CASO', 'DLCC', 1, '180', 'Días límite para cerrar un caso', 'USUARIO_SIMASC', GETDATE(), 'ACT')
INSERT INTO PARAMETRO_DE_SERVICIO VALUES('DIAS_LIMITE_PARA_CIERRE_DE_CASO', 'DLCC', 2, '180', 'Días límite para cerrar un caso', 'USUARIO_SIMASC', GETDATE(), 'ACT')
INSERT INTO PARAMETRO_DE_SERVICIO VALUES('DIAS_LIMITE_PARA_CIERRE_DE_CASO', 'DLCC', 3, '180', 'Días límite para cerrar un caso', 'USUARIO_SIMASC', GETDATE(), 'ACT')
INSERT INTO PARAMETRO_DE_SERVICIO VALUES('DIAS_LIMITE_PARA_CIERRE_DE_CASO', 'DLCC', 4, '180', 'Días límite para cerrar un caso', 'USUARIO_SIMASC', GETDATE(), 'ACT')
INSERT INTO PARAMETRO_DE_SERVICIO VALUES('DIAS_LIMITE_PARA_CIERRE_DE_CASO', 'DLCC', 5, '180', 'Días límite para cerrar un caso', 'USUARIO_SIMASC', GETDATE(), 'ACT')
INSERT INTO PARAMETRO_DE_SERVICIO VALUES('DIAS_LIMITE_PARA_CIERRE_DE_CASO', 'DLCC', 6, '180', 'Días límite para cerrar un caso', 'USUARIO_SIMASC', GETDATE(), 'ACT')
INSERT INTO PARAMETRO_DE_SERVICIO VALUES('DIAS_LIMITE_PARA_CIERRE_DE_CASO', 'DLCC', 7, '180', 'Días límite para cerrar un caso', 'USUARIO_SIMASC', GETDATE(), 'ACT')
INSERT INTO PARAMETRO_DE_SERVICIO VALUES('DIAS_LIMITE_PARA_CIERRE_DE_CASO', 'DLCC', 8, '180', 'Días límite para cerrar un caso', 'USUARIO_SIMASC', GETDATE(), 'ACT')
INSERT INTO PARAMETRO_DE_SERVICIO VALUES('DIAS_LIMITE_PARA_CIERRE_DE_CASO', 'DLCC', 9, '180', 'Días límite para cerrar un caso', 'USUARIO_SIMASC', GETDATE(), 'ACT')

-- Inserción de nuevo dominio en tabla de dominios
insert into dominio values('DLCC', 'DIAS_LIMITE_PARA_CIERRE_DE_CASO', 'Días límite para cerrar un caso', 'Días límite para cierre de caso', null, null)

------------------------------------------------------------------------------------------------------------------------------

/*
-- Script asociado a la creación del tipo de documento que será asociado al adjunto
-- de la plantilla de Notificaciones de Arbitros para el caso de arbitros no internacionales
-- 
-- v1.0 Emisión: FTOVAR (22-02-18)
*/

-- *** 1. Plantilla ***
DECLARE @PlantillaId numeric(9);
DECLARE @HtmlCarta varchar(MAX);

-- Carta HTML
SET @HtmlCarta = '<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"/>
		
		<style type="text/css">
			body {
				 font-family: "Calibri Light";
			}
		
			ul {
			  list-style-type: none;
			  padding-left: 5px;
			  margin: 1px 0px;
			}
			
			.table-carta {
				width:100%;
				border: 2px solid #000;
				border-spacing: 0;
			}
			
			.table-carta td:first-child {
				width:30%;
				border-right: 2px solid #000;				
			}
			
			.table-carta td:last-child {
				width:70%;				
			}
			
			.table-carta tr:nth-child(n+2) td {
				border-top: 2px solid #000;
			}
		</style>
	</head>
	<body>
		<p>ciudadP, dia_cartaP de mes_cartaP de ano_cartaP</p>
		<br/>
		<p>
			Doctor(a)<br/> 
			dirigido_aP<br/> 
			Ciudad<br/>
		</p>
		<br/>
		<p>
			<b>Asunto: ARBITRAJE INTERNACIONAL - casoP/nombreCasoP</b>
		</p>
		<br/>
		<p>Respetado(a) Doctor(a) apellidoP,</p>
		
		<p align="justify">Me complace comunicarle que ha sido designado como árbitro, mediante sorteo público, para que junto con arbitrosCasoP, integre el tribunal de arbitramento internacional que decidirá las diferencias surgidas entre convocantesP, como parte convocante y convocadosP, como parte convocada.</p>
				
		<p align="justify">Dentro del proceso arbitral internacional de la referencia, se invoca el pacto arbitral incluido en la cláusula xxxx del “documento_pactoP” de fecha XX de XX de XX, que expresamente establece:</p>
				
		<p align="justify">
			<font style="font-style: italic"> “pacto_arbitralP” </font>.
		</p>
		
		<p align="justify">Para efectos de la revisión del caso, le informamos que el abogado a cargo de este trámite es el(la) doctor(a) abogadoP quien pondrá el expediente a su entera disposición. De requerirlo puede contactarlo previamente en el telefonoAbogadoP o en el correo abajo indicado.</p>
		<p align="justify">Atentamente le solicito confirmar, dentro de los cinco (5) días siguientes a la fecha de esta comunicación, si acepta o no su designación, enviando su respuesta al correo electrónico correoAbogadoP. Así mismo, le ruego tener en cuenta lo dispuesto por los artículos 75 y 76 de la Ley 1563 de 2012, en lo concerniente al deber de información y el deber de revelación, entre otros.</p>
		<p align="justify">Envío, para su conocimiento, la solicitud de inicio de arbitraje internacional presentada por la parte convocante; y, el formato de declaración de independencia, que deberá ser diligenciado para ser puesto en conocimiento de las partes. Los primeros anexos a la aludida solicitud, por su extensión, serán enviados por medio de comunicación separada. Para facilitar su diligenciamiento y decisión, le informamos que las partes y los apoderados hasta el momento acreditados son:</p>
		<p>
			<table class="table-carta">
				<tbody>
					<tr>
						<td>
							<b>Convocantes</b>
						</td>
						<td>
							convocantesP
						</td>
					</tr>
					<tr>
						<td>
							<b>Convocadas</b>
						</td>
						<td>							
							convocadosP
						</td>
					</tr>
					<tr>
						<td>
							<b>Representantes de la Parte Convocante</b>
						</td>
						<td>
							representanteConvocantesP
						</td>
					</tr>
					<tr>
						<td>
							<b>Representantes de la Parte Convocado</b>
						</td>
						<td>
							representanteConvocadosP
						</td>
					</tr>
				</tbody>
			</table>
		</p>
		<br/>
		<p>Cordialmente,</p>
		<br/><br/><br/>
		<p>
			<b>SANTIAGO DÍAZ CEDIEL</b>
			<br/>
			<b>Abogado</b>
			<br/>
			<font size="1">casoP</font>
		</p>
	</body>
</html>';

-- Crear Plantilla u obtener el id de la plantilla registrada
IF(NOT EXISTS(SELECT 1 FROM PLANTILLA_CARTA WHERE nombre = 'PCNARI'))
BEGIN
	-- Obtener Nuevo Id Plantilla
	SELECT @PlantillaId = MAX(id_plantilla_carta) + 1 FROM PLANTILLA_CARTA;

	-- Crear Plantilla :: Arbitraje Internacional
	INSERT INTO PLANTILLA_CARTA(id_plantilla_carta,nombre,tipo_servicio,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,plantilla_html,id_servicio)
	VALUES(@PlantillaId, 'PCNARI', 'JUS', 'SIMASC', SYSDATETIME(), 'ACT', @HtmlCarta,4);

	SELECT CONCAT('Plantilla CREADA: ',@PlantillaId)
END
ELSE
BEGIN
	-- Consultar id de plantilla registrada
	SELECT @PlantillaId = id_plantilla_carta FROM PLANTILLA_CARTA WHERE nombre = 'PCNARI'

	SELECT CONCAT('Plantilla Existente: ', @PlantillaId)

	-- Actualizar HTML
	UPDATE PLANTILLA_CARTA SET plantilla_html = @HtmlCarta WHERE id_plantilla_carta = @PlantillaId;

	SELECT 'Contenido Plantilla HTML Actualizado'
END

-- 2. Crear Valores Plantillas
DECLARE @ValorPlantillaId numeric(9);

-- 2.1 Nombre
IF(NOT EXISTS(SELECT 1 FROM VALOR_PLANTILLA_CARTA WHERE id_plantilla_carta = @PlantillaId AND nombre_valor_dinamico = 'dirigido_aP'))
BEGIN
	-- Obtener Nuevo Id valor plantilla
	SELECT @ValorPlantillaId = MAX(id_valor_plantilla_carta) + 1 FROM VALOR_PLANTILLA_CARTA

	-- Crear Valor
	INSERT INTO VALOR_PLANTILLA_CARTA(id_valor_plantilla_carta,nombre_valor_dinamico,id_plantilla_carta,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,consulta)
	VALUES(@ValorPlantillaId, 'dirigido_aP', @PlantillaId, 'USER_SIMASC', SYSDATETIME(), 'ACT',
		'NATIVE:SELECT concat(p.primer_nombre_o_razon_social,'' '', CONCAT(p.segundo_nombre, '' '',concat(p.primer_apellido,'' '', concat(p.segundo_apellido,'' '')))) FROM Persona p INNER JOIN ROL_PERSONA_CASO prpc ON prpc.id_persona = p.id_persona WHERE prpc.id_caso=? AND prpc.id_rol IN (SELECT DISTINCT id_rol FROM PARAMETRO_SERVICIO_SORTEO) and prpc.tipo_nombramiento=''PRI'' and prpc.estado=''ACE'' and p.id_persona=?2')

	SELECT 'Valor Creado: dirigido_aP'
END

-- 2.2 Caso
IF(NOT EXISTS(SELECT 1 FROM VALOR_PLANTILLA_CARTA WHERE id_plantilla_carta = @PlantillaId AND nombre_valor_dinamico = 'casoP'))
BEGIN
	-- Obtener Nuevo Id valor plantilla
	SELECT @ValorPlantillaId = MAX(id_valor_plantilla_carta) + 1 FROM VALOR_PLANTILLA_CARTA

	-- Crear Valor
	INSERT INTO VALOR_PLANTILLA_CARTA(id_valor_plantilla_carta,nombre_valor_dinamico,id_plantilla_carta,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,consulta)
	VALUES(@ValorPlantillaId, 'casoP', @PlantillaId, 'USER_SIMASC', SYSDATETIME(), 'ACT',
		'SELECT c.idCaso from Caso c where c.idCaso=:idCaso')

	SELECT 'Valor Creado: casoP'
END

-- 2.3 Caso Nombre
IF(NOT EXISTS(SELECT 1 FROM VALOR_PLANTILLA_CARTA WHERE id_plantilla_carta = @PlantillaId AND nombre_valor_dinamico = 'nombreCasoP'))
BEGIN
	-- Obtener Nuevo Id valor plantilla
	SELECT @ValorPlantillaId = MAX(id_valor_plantilla_carta) + 1 FROM VALOR_PLANTILLA_CARTA

	-- Crear Valor
	INSERT INTO VALOR_PLANTILLA_CARTA(id_valor_plantilla_carta,nombre_valor_dinamico,id_plantilla_carta,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,consulta)
	VALUES(@ValorPlantillaId, 'nombreCasoP', @PlantillaId, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'SELECT c.nombre from Caso c where c.idCaso=:idCaso')

	SELECT 'Valor Creado: nombreCasoP'
END

-- 2.4 Arbitros
IF(NOT EXISTS(SELECT 1 FROM VALOR_PLANTILLA_CARTA WHERE id_plantilla_carta = @PlantillaId AND nombre_valor_dinamico = 'arbitrosCasoP'))
BEGIN
	-- Obtener Nuevo Id valor plantilla
	SELECT @ValorPlantillaId = MAX(id_valor_plantilla_carta) + 1 FROM VALOR_PLANTILLA_CARTA

	-- Crear Valor
	INSERT INTO VALOR_PLANTILLA_CARTA(id_valor_plantilla_carta,nombre_valor_dinamico,id_plantilla_carta,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,consulta)
	VALUES(@ValorPlantillaId, 'arbitrosCasoP', @PlantillaId, 'USER_SIMASC', SYSDATETIME(), 'ACT',
		'NATIVE: LST_Y: SELECT CONCAT(p.primer_nombre_o_razon_social,'' '', CONCAT(p.segundo_nombre, '' '',CONCAT(p.primer_apellido,'' '', CONCAT(p.segundo_apellido,'' '')))) FROM PERSONA p INNER JOIN ROL_PERSONA_CASO prpc on prpc.id_persona = p.id_persona WHERE prpc.id_caso=? AND prpc.id_rol IN (SELECT DISTINCT id_rol FROM PARAMETRO_SERVICIO_SORTEO) and prpc.tipo_nombramiento=''PRI'' and prpc.estado=''ACE'' and p.id_persona<>?2')

	SELECT 'Valor Creado: arbitrosCasoP'
END

-- 2.5 Nombres Convocantes
IF(NOT EXISTS(SELECT 1 FROM VALOR_PLANTILLA_CARTA WHERE id_plantilla_carta = @PlantillaId AND nombre_valor_dinamico = 'convocantesP'))
BEGIN
	-- Obtener Nuevo Id valor plantilla
	SELECT @ValorPlantillaId = MAX(id_valor_plantilla_carta) + 1 FROM VALOR_PLANTILLA_CARTA

	-- Crear Valor
	INSERT INTO VALOR_PLANTILLA_CARTA(id_valor_plantilla_carta,nombre_valor_dinamico,id_plantilla_carta,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,consulta)
	VALUES(@ValorPlantillaId, 'convocantesP', @PlantillaId, 'USER_SIMASC', SYSDATETIME(), 'ACT',
		'NATIVE: LST_Y: SELECT CONCAT(ISNULL(p.primer_nombre_o_razon_social,''''), '' '', ISNULL(p.segundo_Nombre, ''''), '' '', ISNULL(p.primer_Apellido,''''), '' '', ISNULL(p.segundo_Apellido,'''')) FROM CASO c INNER JOIN ROL_PERSONA_CASO rp ON c.id_caso = rp.id_caso INNER JOIN PERSONA p ON rp.id_persona = p.id_persona INNER JOIN ROL r ON rp.id_rol = r.id_rol WHERE r.nombre = ''DTE'' AND p.estado_persona = ''ACT'' and rp.estado != ''INA''  AND c.id_caso=?1')

	SELECT 'Valor Creado: convocantesP'
END

-- 2.6 Nombres Convocados
IF(NOT EXISTS(SELECT 1 FROM VALOR_PLANTILLA_CARTA WHERE id_plantilla_carta = @PlantillaId AND nombre_valor_dinamico = 'convocadosP'))
BEGIN
	-- Obtener Nuevo Id valor plantilla
	SELECT @ValorPlantillaId = MAX(id_valor_plantilla_carta) + 1 FROM VALOR_PLANTILLA_CARTA

	-- Crear Valor
	INSERT INTO VALOR_PLANTILLA_CARTA(id_valor_plantilla_carta,nombre_valor_dinamico,id_plantilla_carta,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,consulta)
	VALUES(@ValorPlantillaId, 'convocadosP', @PlantillaId, 'USER_SIMASC', SYSDATETIME(), 'ACT',
		'NATIVE: LST_Y: SELECT CONCAT(ISNULL(p.primer_nombre_o_razon_social,''''), '' '', ISNULL(p.segundo_Nombre, ''''), '' '', ISNULL(p.primer_Apellido,''''), '' '', ISNULL(p.segundo_Apellido,'''')) FROM CASO as c INNER JOIN ROL_PERSONA_CASO rp on c.id_caso = rp.id_caso INNER JOIN PERSONA as p on rp.id_persona = p.id_persona INNER JOIN ROL r ON rp.id_rol = r.id_rol WHERE r.nombre = ''DDA'' AND p.estado_persona = ''ACT'' and rp.estado != ''INA''  AND c.id_caso=?1')

	SELECT 'Valor Creado: convocadosP'
END

-- 2.7 Documento Pacto
IF(NOT EXISTS(SELECT 1 FROM VALOR_PLANTILLA_CARTA WHERE id_plantilla_carta = @PlantillaId AND nombre_valor_dinamico = 'documento_pactoP'))
BEGIN
	-- Obtener Nuevo Id valor plantilla
	SELECT @ValorPlantillaId = MAX(id_valor_plantilla_carta) + 1 FROM VALOR_PLANTILLA_CARTA

	-- Crear Valor
	INSERT INTO VALOR_PLANTILLA_CARTA(id_valor_plantilla_carta,nombre_valor_dinamico,id_plantilla_carta,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,consulta)
	VALUES(@ValorPlantillaId, 'documento_pactoP', @PlantillaId, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'select c.documentoPacto from Caso c where c.idCaso=:idCaso')

	SELECT 'Valor Creado: documento_pactoP'
END

-- 2.8 Descripción Pacto
IF(NOT EXISTS(SELECT 1 FROM VALOR_PLANTILLA_CARTA WHERE id_plantilla_carta = @PlantillaId AND nombre_valor_dinamico = 'pacto_arbitralP'))
BEGIN
	-- Obtener Nuevo Id valor plantilla
	SELECT @ValorPlantillaId = MAX(id_valor_plantilla_carta) + 1 FROM VALOR_PLANTILLA_CARTA

	-- Crear Valor
	INSERT INTO VALOR_PLANTILLA_CARTA(id_valor_plantilla_carta,nombre_valor_dinamico,id_plantilla_carta,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,consulta)
	VALUES(@ValorPlantillaId, 'pacto_arbitralP', @PlantillaId, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'select c.descripcionPacto from Caso c where c.idCaso=:idCaso')

	SELECT 'Valor Creado: pacto_arbitralP'
END

-- 2.9 Abogado
IF(NOT EXISTS(SELECT 1 FROM VALOR_PLANTILLA_CARTA WHERE id_plantilla_carta = @PlantillaId AND nombre_valor_dinamico = 'abogadoP'))
BEGIN
	-- Obtener Nuevo Id valor plantilla
	SELECT @ValorPlantillaId = MAX(id_valor_plantilla_carta) + 1 FROM VALOR_PLANTILLA_CARTA

	-- Crear Valor
	INSERT INTO VALOR_PLANTILLA_CARTA(id_valor_plantilla_carta,nombre_valor_dinamico,id_plantilla_carta,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,consulta)
	VALUES(@ValorPlantillaId, 'abogadoP', @PlantillaId, 'USER_SIMASC', SYSDATETIME(), 'ACT', 
		'NATIVE: SELECT CONCAT(ISNULL(p.primer_nombre_o_razon_social,''''), '' '', ISNULL(p.segundo_nombre, ''''), '' '', ISNULL(p.primer_apellido, ''''), '' '', ISNULL(p.segundo_apellido, '''')) FROM CASO c INNER JOIN PARAMETRIZAR_SERVICIO_ROL psr ON c.id_servicio = psr.id_servicio INNER JOIN ROL_PERSONA_CASO rp ON c.id_caso = rp.id_caso AND rp.id_rol = psr.id_rol INNER JOIN PERSONA p ON rp.id_persona = p.id_persona WHERE c.id_caso=?1')

	SELECT 'Valor Creado: abogadoP'
END

-- 2.10 Telefono Abogado
IF(NOT EXISTS(SELECT 1 FROM VALOR_PLANTILLA_CARTA WHERE id_plantilla_carta = @PlantillaId AND nombre_valor_dinamico = 'telefonoAbogadoP'))
BEGIN
	-- Obtener Nuevo Id valor plantilla
	SELECT @ValorPlantillaId = MAX(id_valor_plantilla_carta) + 1 FROM VALOR_PLANTILLA_CARTA

	-- Crear Valor
	INSERT INTO VALOR_PLANTILLA_CARTA(id_valor_plantilla_carta,nombre_valor_dinamico,id_plantilla_carta,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,consulta)
	VALUES(@ValorPlantillaId, 'telefonoAbogadoP', @PlantillaId, 'USER_SIMASC', SYSDATETIME(), 'ACT', 
		'NATIVE: SELECT TOP 1 ISNULL(t.numero, ''NO REGISTRA'') FROM CASO c INNER JOIN PARAMETRIZAR_SERVICIO_ROL psr ON c.id_servicio = psr.id_servicio INNER JOIN ROL_PERSONA_CASO rp ON c.id_caso = rp.id_caso AND rp.id_rol = psr.id_rol INNER JOIN PERSONA p ON rp.id_persona = p.id_persona LEFT JOIN TELEFONO t ON p.id_persona = t.id_persona AND t.tipo_telefono = ''FIJ'' WHERE c.id_caso=?1 ORDER BY t.id_telefono DESC')

	SELECT 'Valor Creado: telefonoAbogadoP'
END

-- 2.11 Email Abogado
IF(NOT EXISTS(SELECT 1 FROM VALOR_PLANTILLA_CARTA WHERE id_plantilla_carta = @PlantillaId AND nombre_valor_dinamico = 'correoAbogadoP'))
BEGIN
	-- Obtener Nuevo Id valor plantilla
	SELECT @ValorPlantillaId = MAX(id_valor_plantilla_carta) + 1 FROM VALOR_PLANTILLA_CARTA

	-- Crear Valor
	INSERT INTO VALOR_PLANTILLA_CARTA(id_valor_plantilla_carta,nombre_valor_dinamico,id_plantilla_carta,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,consulta)
	VALUES(@ValorPlantillaId, 'correoAbogadoP', @PlantillaId, 'USER_SIMASC', SYSDATETIME(), 'ACT', 
		'NATIVE: SELECT TOP 1 ISNULL(ce.direccion, ''NO REGISTRA'') FROM CASO c INNER JOIN PARAMETRIZAR_SERVICIO_ROL psr ON c.id_servicio = psr.id_servicio INNER JOIN ROL_PERSONA_CASO rp ON c.id_caso = rp.id_caso AND rp.id_rol = psr.id_rol INNER JOIN PERSONA p ON rp.id_persona = p.id_persona LEFT JOIN CORREO_ELECTRONICO ce ON p.id_persona = ce.id_persona AND ce.tipo = ''PRI'' WHERE c.id_caso=?1 ORDER BY ce.id_correo DESC')

	SELECT 'Valor Creado: correoAbogadoP'
END

-- 2.12 Representantes o Apoderados del Demandante
IF(NOT EXISTS(SELECT 1 FROM VALOR_PLANTILLA_CARTA WHERE id_plantilla_carta = @PlantillaId AND nombre_valor_dinamico = 'representanteConvocantesP'))
BEGIN
	-- Obtener Nuevo Id valor plantilla
	SELECT @ValorPlantillaId = MAX(id_valor_plantilla_carta) + 1 FROM VALOR_PLANTILLA_CARTA

	-- Crear Valor
	INSERT INTO VALOR_PLANTILLA_CARTA(id_valor_plantilla_carta,nombre_valor_dinamico,id_plantilla_carta,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,consulta)
	VALUES(@ValorPlantillaId, 'representanteConvocantesP', @PlantillaId, 'USER_SIMASC', SYSDATETIME(), 'ACT', 
		'NATIVE: LST_U: SELECT CONCAT(CONCAT(ISNULL(pa.primer_nombre_o_razon_social,''''), '' '', ISNULL(pa.segundo_Nombre, ''''), '' '', ISNULL(pa.primer_Apellido,''''), '' '', ISNULL(pa.segundo_Apellido,'''')), '' '', ''( Representa a: '', CONCAT(ISNULL(pd.primer_nombre_o_razon_social,''''), '' '', ISNULL(pd.segundo_Nombre, ''''), '' '', ISNULL(pd.primer_Apellido,''''), '' '', ISNULL(pd.segundo_Apellido,'''')),'')'') FROM CASO c INNER JOIN ROL_PERSONA_CASO rpd ON c.id_caso = rpd.id_caso INNER JOIN PERSONA pd ON rpd.id_persona = pd.id_persona INNER JOIN ROL rd ON rpd.id_rol = rd.id_rol INNER JOIN APODERADOS apo ON apo.id_rol_representado = rpd.id_rol AND apo.id_persona_representado = rpd.id_persona AND apo.id_caso_apoderado = rpd.id_caso INNER JOIN PERSONA pa ON apo.id_persona_apoderado = pa.id_persona INNER JOIN ROL ra ON apo.id_rol_apoderado = ra.id_rol WHERE rd.nombre = ''DTE'' AND pd.estado_persona = ''ACT'' and rpd.estado != ''INA''  AND c.id_caso=?1 ORDER BY (CASE ra.nombre WHEN ''RDP'' THEN 1 WHEN ''APO'' THEN 2 ELSE 3 END)')

	SELECT 'Valor Creado: representanteConvocantesP'
END

-- 2.13 Representantes o Apoderados del Demandado
IF(NOT EXISTS(SELECT 1 FROM VALOR_PLANTILLA_CARTA WHERE id_plantilla_carta = @PlantillaId AND nombre_valor_dinamico = 'representanteConvocadosP'))
BEGIN
	-- Obtener Nuevo Id valor plantilla
	SELECT @ValorPlantillaId = MAX(id_valor_plantilla_carta) + 1 FROM VALOR_PLANTILLA_CARTA

	-- Crear Valor
	INSERT INTO VALOR_PLANTILLA_CARTA(id_valor_plantilla_carta,nombre_valor_dinamico,id_plantilla_carta,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,consulta)
	VALUES(@ValorPlantillaId, 'representanteConvocadosP', @PlantillaId, 'USER_SIMASC', SYSDATETIME(), 'ACT', 
		'NATIVE: LST_U: SELECT CONCAT(CONCAT(ISNULL(pa.primer_nombre_o_razon_social,''''), '' '', ISNULL(pa.segundo_Nombre, ''''), '' '', ISNULL(pa.primer_Apellido,''''), '' '', ISNULL(pa.segundo_Apellido,'''')), '' '', ''( Representa a: '', CONCAT(ISNULL(pd.primer_nombre_o_razon_social,''''), '' '', ISNULL(pd.segundo_Nombre, ''''), '' '', ISNULL(pd.primer_Apellido,''''), '' '', ISNULL(pd.segundo_Apellido,'''')),'')'') FROM CASO c INNER JOIN ROL_PERSONA_CASO rpd ON c.id_caso = rpd.id_caso INNER JOIN PERSONA pd ON rpd.id_persona = pd.id_persona INNER JOIN ROL rd ON rpd.id_rol = rd.id_rol INNER JOIN APODERADOS apo ON apo.id_rol_representado = rpd.id_rol AND apo.id_persona_representado = rpd.id_persona AND apo.id_caso_apoderado = rpd.id_caso INNER JOIN PERSONA pa ON apo.id_persona_apoderado = pa.id_persona INNER JOIN ROL ra ON apo.id_rol_apoderado = ra.id_rol WHERE rd.nombre = ''DDA'' AND pd.estado_persona = ''ACT'' and rpd.estado != ''INA''  AND c.id_caso=?1')

	SELECT 'Valor Creado: representanteConvocadosP'
END

-- 2.14 Apellido
IF(NOT EXISTS(SELECT 1 FROM VALOR_PLANTILLA_CARTA WHERE id_plantilla_carta = @PlantillaId AND nombre_valor_dinamico = 'apellidoP'))
BEGIN
	-- Obtener Nuevo Id valor plantilla
	SELECT @ValorPlantillaId = MAX(id_valor_plantilla_carta) + 1 FROM VALOR_PLANTILLA_CARTA

	-- Crear Valor
	INSERT INTO VALOR_PLANTILLA_CARTA(id_valor_plantilla_carta,nombre_valor_dinamico,id_plantilla_carta,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,consulta)
	VALUES(@ValorPlantillaId, 'apellidoP', @PlantillaId, 'USER_SIMASC', SYSDATETIME(), 'ACT',
		'NATIVE:SELECT concat(p.primer_apellido,'''') FROM Persona p INNER JOIN ROL_PERSONA_CASO prpc ON prpc.id_persona = p.id_persona WHERE prpc.id_caso=? AND prpc.id_rol IN (SELECT DISTINCT id_rol FROM PARAMETRO_SERVICIO_SORTEO) and prpc.tipo_nombramiento=''PRI'' and prpc.estado=''ACE'' and p.id_persona=?2')

	SELECT 'Valor Creado: apellidoP'
END

-- * Dirigido a :: Tipo Parte
IF(NOT EXISTS(SELECT 1 FROM VALOR_PLANTILLA_CARTA WHERE id_plantilla_carta = @PlantillaId AND nombre_valor_dinamico = 'IPP'))
BEGIN
	-- Obtener Nuevo Id valor plantilla
	SELECT @ValorPlantillaId = MAX(id_valor_plantilla_carta) + 1 FROM VALOR_PLANTILLA_CARTA
	
	-- Crear Valor
	INSERT INTO VALOR_PLANTILLA_CARTA(id_valor_plantilla_carta,nombre_valor_dinamico,id_plantilla_carta,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)
	VALUES(@ValorPlantillaId, 'IPP', @PlantillaId, 'USER_SIMASC', SYSDATETIME(), 'ACT')

	SELECT 'Valor Creado: IPP'
END

-- * Dominio :: Registro de Nombre Carta como Dominio
IF(NOT EXISTS(SELECT 1 FROM DOMINIO WHERE CODIGO = 'PCNARI' AND DOMINIO = 'NOMBRE_PLANTILLA_CARTA'))
BEGIN
	INSERT INTO DOMINIO(codigo,dominio,nombre,descripcion)
	VALUES('PCNARI','NOMBRE_PLANTILLA_CARTA','Notificación al árbitro internacional','Notificación al árbitro internacional')

	SELECT 'Dominio Registrado :: Nombre Plantilla'
END

-- * Adjuntos
IF(NOT EXISTS(SELECT 1 FROM VALOR_PLANTILLA_CARTA WHERE id_plantilla_carta = @PlantillaId AND nombre_valor_dinamico = 'adjuntosP'))
BEGIN
	-- Crear Documento
	DECLARE @DocumentoId numeric(9);

	INSERT INTO DOCUMENTO(nombre,tipo_documento,publicado,tipo_archivo,estado_digitalizacion,fecha_digitalizacion,numero_folios,radicado,url,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)
	VALUES('Formato deber de información Arbitro Internacional', 'DD18',0,'DOCX','DDO',SYSDATETIME(),1,0,'/opt/IBM/WebSphere/AppServer/simasc/GestorDocumental/Formato deber de información (arb internacional).docx','USUARIO_SIMASC',SYSDATETIME(),'ACT')

	SET @DocumentoId = SCOPE_IDENTITY();
	
	-- Obtener Nuevo Id valor plantilla
	SELECT @ValorPlantillaId = MAX(id_valor_plantilla_carta) + 1 FROM VALOR_PLANTILLA_CARTA
	
	-- Crear Valor
	INSERT INTO VALOR_PLANTILLA_CARTA(id_valor_plantilla_carta,nombre_valor_dinamico,id_plantilla_carta,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro,descripcion)
	VALUES(@ValorPlantillaId, 'adjuntosP', @PlantillaId, 'USER_SIMASC', SYSDATETIME(), 'ACT', CONVERT(varchar(250), @DocumentoId))

	SELECT 'Valor Creado: adjuntosP'
END
-------------------------------------------------------------------------------------------------------------------------------------------

/*
-- Script asociado a la creación de la plantilla de Notificación de aceptación de Arbitros
-- 
-- v1.0 Emisión: grangel 
*/

INSERT INTO DOMINIO ( CODIGO, DOMINIO, NOMBRE, DESCRIPCION)
VALUES ('PCCAA', 'NOMBRE_PLANTILLA_CARTA', 'Comunicación de aceptación de árbitros', 'Comunicación de aceptación de árbitros dirigida a las partes')

INSERT INTO PLANTILLA_CARTA ( NOMBRE, TIPO_SERVICIO, PLANTILLA_HTML, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, id_servicio)
VALUES ('PCCAA', 'PJT', 
'<!DOCTYPE html>  
<html>
   <head>
      <meta charset="UTF-8"/>
      <style type="text/css">body {
     font-family: "Calibri Light";
}
 </style>
   </head>
   <body>
      <p>Bogotá D.C, dia_cartaP de mes_cartaP de ano_cartaP</p>
      <br/>    
      <p>     Doctor<br/>      dirigido_aP<br/>      Representante de la Parte representanteP      representadoP<br/>      ciudadP<br/>    </p>
      <br/>    
      <p>     <b>Asunto:           Arbitraje Internacional - Caso casoP/nombreCasoP</b>    </p>
      <br/>    
      <p>Respetado Doctor apellidoDirigido_aP,</p>
      <p align="justify">Reciba un cordial saludo. El Centro de Arbitraje y Conciliación
	  de la Cámara de Comercio de Bogotá se permite comunicar que, una vez transcurrido
	  el término previsto en el artículo 3.13 del Reglamento del Centro de Arbitraje y 
	  Conciliación de la Cámara de Comercio de Bogotá (Parte III: Reglamento de 
	  Arbitraje Comercial Internacional), en consonancia con lo dispuesto en la Ley 
	  1563 de 2012, ha confirmado a los doctores arbitrosCasoP como árbitros del 
	  Tribunal Arbitral Internacional que dirimirá las diferencias entre demandanteP y 
	  demandadoP. A su turno y de conformidad con lo previsto en el aludido reglamento, ha sido 
	  nombrado Presidente del Tribunal el doctor XXXXXXXXXX. En consecuencia, el 
	  Tribunal se entiende constituido el XX de XX de 
	  XX, día en el que este Centro recibió la aceptación del doctor
	  XXXXXXXXXX.</p>
      <p align="justify">Le informo que el próximo dia_audiencia_pendienteP de 
	  mes_audiencia_pendienteP de ano_audiencia_pendienteP se llevará a efecto la 
	  primera reunión procesal entre el Tribunal y las Partes, en las instalaciones 
	  del Centro de Arbitraje y Conciliación de la Cámara de Comercio de Bogotá, 
	  ubicadas en la Calle 76 # 11-52, a las hora_audiencia_pendienteP.</p>
      <p align="justify">En caso de que surja cualquier duda sobre el particular, 
	  no dude en comunicarse al correo electrónico: santiago.diaz@ccb.org.co o al 
	  teléfono: 5941000, extensión 2326.</p>
      <br/>    
      <p>Cordial saludo,</p>
      <br/><br/><br/>    
      <p>     <b>MAURICIO GONZÁLEZ CUERVO</b>     <br/>     <b>Director</b>     <br/></p>
   </body>
</html>', 
'USER_SIMASC', SYSDATETIME(), 'ACT', (SELECT id_servicio FROM SERVICIO WHERE NOMBRE = 'ARBITRAJE INTERNACIONAL'))

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('dirigido_aP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae el nombre de la persona a quien va dirigida la carta', 
'NATIVE: SELECT concat(p.primer_nombre_o_razon_social,'' '', CONCAT(p.segundo_nombre, '' '',concat(p.primer_apellido,'' '', concat(p.segundo_apellido,'' ''))))   
FROM Persona p INNER JOIN ROL_PERSONA_CASO rpc ON rpc.id_persona = p.id_persona WHERE rpc.id_caso=?1 and p.id_persona=?2');

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('representanteP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae el nombre de la persona a quien va dirigida la carta', 
'NATIVE: select case  when r.nombre = ''APO'' then ''Demandante<br/>'' when r.nombre = ''APD'' then ''Demandado<br/>'' else ''<br/>'' end 
from dominio d inner join rol r on r.nombre = d.codigo inner join ROL_PERSONA_CASO rpc on r.id_rol = rpc.id_rol
where d.dominio = ''ROL_PERSONA'' and rpc.id_caso = ?1 and rpc.id_persona = ?2')

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('representadoP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae el nombre de las personas a las que representa', 
'NATIVE: LST_Y: SELECT concat(p.primer_nombre_o_razon_social,'' '', CONCAT(p.segundo_nombre, '' '',concat(p.primer_apellido,'' '', concat(p.segundo_apellido,'' ''))))   
FROM Persona p INNER JOIN APODERADOS a on p.id_persona = a.id_persona_representado where a.id_caso_apoderado = ?1 and a.id_persona_apoderado = ?2')

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('ciudadP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae la ciudad asociada a la dirección dada por la persona a quien va dirigida la carta',
'NATIVE: select zg.nombre from UBICACION u inner join zona_geografica zg on u.id_zona_geografica = zg.id_zona_geografica
where u.id_persona = (SELECT p.id_persona FROM Persona p INNER JOIN ROL_PERSONA_CASO rpc ON rpc.id_persona = p.id_persona WHERE rpc.id_caso=?1 and rpc.id_persona_apoderado = ?2)')

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('casoP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae el id de un caso', 'SELECT c.idCaso from Caso c where c.idCaso=:idCaso')

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES('nombreCasoP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae el nombre de un caso', 'SELECT c.nombre from Caso c where c.idCaso=:idCaso')

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES('apellidoDirigido_aP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae el apellido de la persona a quien va dirigida la carta', 
'NATIVE: SELECT p.primer_apellido FROM Persona p INNER JOIN ROL_PERSONA_CASO prpc ON prpc.id_persona = p.id_persona WHERE prpc.id_caso=?1 and p.id_persona=?2 ')

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES('arbitrosCasoP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae los árbitros asociados al caso que hayan aceptado', 
'NATIVE: LST_Y: SELECT concat(p.primer_nombre_o_razon_social,'' '', concat(p.segundo_nombre, '' '',concat(p.primer_apellido,'' '', concat(p.segundo_apellido,'' ''))))   
FROM Persona p   INNER JOIN ROL_PERSONA_CASO rpc ON rpc.id_persona = p.id_persona   
where rpc.id_caso = ?1  and rpc.id_rol IN(select id_rol from rol where nombre IN (''ARB'', ''ARI''))  and rpc.estado = ''ACE''')

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES('demandanteP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae el nombre del demandante', 
'NATIVE: LST_Y: SELECT concat(p.primer_nombre_o_razon_social,'' '', CONCAT(p.segundo_nombre, '' '',concat(p.primer_apellido,'' '', concat(p.segundo_apellido,'' ''))))   
FROM Persona p   INNER JOIN ROL_PERSONA_CASO rpc ON rpc.id_persona = p.id_persona   where rpc.id_caso = ?1  and rpc.id_rol = (select id_rol from rol where nombre = ''DTE'')')

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES('demandadoP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae el nombre del demandado', 
'NATIVE: LST_Y: SELECT concat(p.primer_nombre_o_razon_social,'' '', CONCAT(p.segundo_nombre, '' '',concat(p.primer_apellido,'' '', concat(p.segundo_apellido,'' ''))))   
FROM Persona p   INNER JOIN ROL_PERSONA_CASO rpc ON rpc.id_persona = p.id_persona   where rpc.id_caso = ?1  and rpc.id_rol = (select id_rol from rol where nombre = ''DDA'')')

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES('presidenteP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae el nombre del presidente', 
'NATIVE: SELECT concat(p.primer_nombre_o_razon_social,'' '', CONCAT(p.segundo_nombre, '' '',concat(p.primer_apellido,'' '', concat(p.segundo_apellido,'' ''))))   
FROM Persona p   INNER JOIN ROL_PERSONA_CASO rpc ON rpc.id_persona = p.id_persona   where rpc.id_caso = ?  
and rpc.id_rol IN(select id_rol from rol where nombre IN (''ARB'', ''ARI''))  and rpc.estado = ''ACE''  AND RPC.es_presidente = ''true''') 

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES('dia_aceptacionP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae el dia que el presidente aceptó la asignación', 
'NATIVE: SELECT DAY(rpc.fecha_ultima_modificacion) from ROL_PERSONA_CASO rpc where rpc.id_caso = ?1 and rpc.id_rol IN(select id_rol from rol where nombre IN (''ARB'', ''ARI''))
and rpc.estado = ''ACE'' AND RPC.es_presidente = 1')

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES('mes_aceptacionP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae el mes que el presidente aceptó la asignación', 
'NATIVE: SET LANGUAGE Spanish SELECT  DATENAME(MONTH,rpc.fecha_ultima_modificacion) from ROL_PERSONA_CASO rpc where rpc.id_caso = ?1 
and rpc.id_rol IN(select id_rol from rol where nombre IN (''ARB'', ''ARI'')) and rpc.estado = ''ACE'' AND RPC.es_presidente = 1')

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES('ano_aceptacionP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae el año que el presidente aceptó la asignación', 
'NATIVE: SELECT YEAR(rpc.fecha_ultima_modificacion) from ROL_PERSONA_CASO rpc where rpc.id_caso = ?1 and rpc.id_rol IN(select id_rol from rol where nombre IN (''ARB'', ''ARI''))
and rpc.estado = ''ACE'' AND RPC.es_presidente = 1')

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES('apellidoPresidenteP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae el apellido del arbitro designado como presidente', 
'select rpc.persona.primerApellido from RolPersonaCaso rpc where rpc.caso.idCaso=:idCaso and rpc.rol.idRol IN(select r.idRol from Rol r where r.nombre IN (''ARB'', ''ARI''))
and rpc.estado = ''ACE'' AND rpc.esPresidente = 1')

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES('dia_audiencia_pendienteP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae el día de la audiencia que está pendiente', 
'NATIVE: SELECT DAY(hora_inicio) from audiencia where id_caso = ?1 and estado = ''PEN''')

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES('mes_audiencia_pendienteP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae el mes de la audiencia que está pendiente', 
'NATIVE: SET LANGUAGE Spanish SELECT  DATENAME(MONTH,hora_inicio) from audiencia where id_caso = ?1 and estado = ''PEN''')

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES('ano_audiencia_pendienteP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae el año de la audiencia que está pendiente', 
'NATIVE: SELECT YEAR(hora_inicio) from audiencia where id_caso = ?1 and estado = ''PEN''')

INSERT INTO VALOR_PLANTILLA_CARTA (NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES('hora_audiencia_pendienteP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Trae la hora de la audiencia que está pendiente', 
'NATIVE: select convert( char(5), hora_inicio, 108 ) from audiencia where id_caso = ?1 and estado = ''PEN''')

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION)
VALUES ('IPP', (select id_plantilla_carta from plantilla_carta where nombre = 'PCCAA'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Identifica si la plantilla lleva un tag de <dirigido>' );

-------------------------------------------------------------------------------------------------------------------------------------------

/*
-- Script asociado a la actualización de las plantillas "Citación a audiencias diferentes a la primera",
-- "Formato genérico de carta dirigida a los árbitros" y "Formato genérico de carta dirigida a las partes"
-- 
-- v1.0 Emisión: grangel (06/04/2018)
*/

update PLANTILLA_CARTA set plantilla_html = '<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8"/>
      <title>Excel To HTML using codebeautify.org</title>
   </head>
   <body>
      <html>
         <head>
            <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>
         </head>
         <body>
            <p>ciudadP, dia_cartaP de mes_cartaP de ano_cartaP</p>
            <p>Señores</p>
            <p>existeRepresentanteLegalP</p>
            <p><b>personasP</b></p>
            <p>Doctores</p>
            <p>apoderadosP</p>
            <p>Ciudad</p>
            <p>REFERENCIA:  TRAMITE ARBITRAL DE convocanteP PARA SOLUCIONAR LAS DIFERENCIAS SURGIDAS CON convocadoP</p>
            <p>Respetados señores y doctores: </p>
            <p>-----------</p>
            <p>Codialmente,</p>
            <p><img src="firmaP" style=\"width:50%;\" /></p>
            <p><b>directorP</b><br/><b></b></p>
            <p>Director</p>
         </body>
      </html>
   </body>
</html>'
where id_plantilla_carta = 22

update PLANTILLA_CARTA set plantilla_html = '<html>
   <head>
      <META http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
   </head>
   <body>
      <p>ciudadP, dia_cartaP de mes_cartaP de ano_cartaP</p>
      <p>parteP<br/>direccionP<br/>Ciudad<br/></p>
      <p>
      <table>
         <tr>
            <td valign="top" width="15%">REFERENCIA:</td>
            <td width="75%">servicioP DE casoP - Caso codigoP</td>
         </tr>
      </table>
      </p>
      <p><span>Respetado(a) respetadoP</span></p>
      chooseTipoAudienciaP chooseTipoAudienciaArbitrajeP
      <p align="justify"> Cualquier información sobre el particular, no dude en comunicarse con nosotros al correo electrónico correoP o al teléfono 5941000 Ext. 2319/2323. </p>
      <p>Esperamos contar con su activa y valiosa participación.</p>
      <p>Cordialmente,</p>
      firmaAudienciaP
   </body>
</html>'
where id_plantilla_carta = 15

update PLANTILLA_CARTA set plantilla_html = '<html>
   <head>
      <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>
   </head>
   <body>
      <p>ciudadP, dia_cartaP de mes_cartaP de ano_cartaP</p>
      <p>Doctor(a)</p>
      personasP    
      <p>Ciudad</p>
      <p>REFERENCIA:  TRAMITE ARBITRAL DE convocanteP PARA SOLUCIONAR LAS DIFERENCIAS SURGIDAS CON convocadoP</p>
      <p>Respetado(a)s doctor(a)es </p>
      <p>arbitrosP </p>
      <p>Codialmente,</p>
      <p><img src="firmaP" style=\"width:50%;\" /></p>
      <p><b>directorP</b><br/><b></b></p>
      <p>Director</p> 
   </body>
</html>'
where id_plantilla_carta = 20

------------------------------------------------------------------------------------------------------------------------------

/*
-- Script asociado a la creación del parámetro general asociado a los roles con permiso para generar la carta "Citación a audiencias diferentes a la primera"
-- 
-- v1.0 Emisión: FTOVAR (18-04-18)
*/

INSERT INTO PARAMETROS_GENERALES(codigo,tipo,nombre,valor_texto,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)
VALUES('PCADPABO','ROLES_PCADP', 'ROL ABOGADO','ABO','USUARIO_SIMASC',SYSDATETIME(),'ACT');

INSERT INTO PARAMETROS_GENERALES(codigo,tipo,nombre,valor_texto,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro)
VALUES('PCADPJAR','ROLES_PCADP', 'ROL JEFE ARBITRAJE','JEFEARB','USUARIO_SIMASC',SYSDATETIME(),'ACT');


/*
 * Bugs de aceptacion
 */

DECLARE @PlantillaId numeric(9);

-- Crear Plantilla u obtener el id de la plantilla registrada
IF(EXISTS(SELECT 1 FROM PLANTILLA_CARTA WHERE nombre = 'PCNARI'))
BEGIN
	-- Consultar id de plantilla registrada
	SELECT @PlantillaId = id_plantilla_carta FROM PLANTILLA_CARTA WHERE nombre = 'PCNARI'
END

BEGIN 
	UPDATE VALOR_PLANTILLA_CARTA SET
	consulta = 'NATIVE: SELECT CONCAT(ISNULL(p.primer_nombre_o_razon_social,''''), '' '', ISNULL(p.segundo_nombre, ''''), '' '', ISNULL(p.primer_apellido, ''''), '' '', ISNULL(p.segundo_apellido, '''')) FROM CASO c INNER JOIN PARAMETRIZAR_SERVICIO_ROL psr ON c.id_servicio = psr.id_servicio INNER JOIN ROL_PERSONA_CASO rp ON c.id_caso = rp.id_caso AND rp.id_rol = psr.id_rol AND rp.estado_registro = ''ACT'' INNER JOIN PERSONA p ON rp.id_persona = p.id_persona WHERE c.id_caso=?1'
	WHERE nombre_valor_dinamico = 'abogadoP' and id_plantilla_carta = @PlantillaId
END 


DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF097divFichaTecnica';
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'TRANSF097divFichaTecnica', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SUBDICAC','ARB','JEFEARB','SEC','ABO','JUD','AUX','ARI','AMC','ARE','ARBS','SECA','ASA','DCA');
/*permisos ficha tecnica arbitral*/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF081divFichaTecnica';

INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'TRANSF081divFichaTecnica', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SUBDICAC','ARB','JEFEARB','SEC','ABO','JUD','AUX','ARI','AMC','ARE','ARBS','SECA','ASA','DCA');

			 
/* permisos boton redireccionar a ficha técnica pre arbitral*/

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF081divIrPreArbitral';
DELETE FUNCIONALIDAD where nombre='TRANSF081divIrPreArbitral';

insert into FUNCIONALIDAD values ('TRANSF081divIrPreArbitral', 'Botón redirección a ficha técnica pre arbitral','app/Arbitraje/TRANSF081','PJT', 'ANGULAR','TRANSF081divFichaTecnica','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'TRANSF081divIrPreArbitral', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SUBDICAC','ARB','JEFEARB','SEC','ABO','JUD','AUX','ARI','AMC','ARE','ARBS','SECA','ASA','DCA');


/* permisos boton redireccionar Botón redirección a ficha técnica arbitral. */
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF097divIrArbitral';
DELETE FUNCIONALIDAD where nombre='TRANSF097divIrArbitral';

insert into FUNCIONALIDAD values ('TRANSF097divIrArbitral', 'Botón redirección a ficha técnica arbitral. ','app/Arbitraje/TRANSF097','PJT', 'ANGULAR','TRANSF097divFichaTecnica','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'TRANSF097divIrArbitral', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SUBDICAC','ARB','JEFEARB','SEC','ABO','JUD','AUX','ARI','AMC','ARE','ARBS','SECA','ASA','DCA');

-- ARB-C-032 Corrección bug 6587 reportado en TFS 
UPDATE PARAMETROS_GENERALES SET codigo = 'REP_ABO' WHERE codigo = 'REP_ABOG' AND tipo = 'REPARTO_EQUITATIVO'

-- Ajuste permisos cambio de secretario de tribunal
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF068CambioSecretarioTribunal';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF068ConsultaSecretarioTribunal';

DELETE FUNCIONALIDAD where nombre='ARBF068CambioSecretarioTribunal';
DELETE FUNCIONALIDAD where nombre='ARBF068ConsultaSecretarioTribunal';

INSERT INTO FUNCIONALIDAD VALUES ('ARBF068CambioSecretarioTribunal', 'Secretario asignado', 'app/Arbitraje/ARBF068', 'PJT', 'ANGULAR', 'TRANSF081divDatosBasicos', 'SIMASC_USER', GETDATE(), 'ACT')
INSERT INTO FUNCIONALIDAD VALUES ('ARBF068ConsultaSecretarioTribunal', 'Consultar secretario asignado', 'app/Arbitraje/ARBF068', 'PJT', 'ANGULAR', 'TRANSF081divDatosBasicos', 'SIMASC_USER', GETDATE(), 'ACT')

-- Ajuste en los roles que pueden visualizar la plantilla de citaciones a audiencias diferentes a la primera
INSERT INTO PARAMETROS_GENERALES VALUES ('PCADPSECA', 'ROLES_PCADP', 'ROL SECRETARIO ARBITRAJE', NULL, 'SECA', NULL, NULL, 'USUARIO_SIMASC', GETDATE(), 'ACT')

-- Ajuste de la consulta del abogado del caso, para la plantilla de notificación al árbitro internacional
UPDATE VALOR_PLANTILLA_CARTA SET consulta = 'NATIVE: SELECT CONCAT(ISNULL(p.primer_nombre_o_razon_social,''''), '' '', ISNULL(p.segundo_nombre, ''''), '' '', ISNULL(p.primer_apellido, ''''), '' '', ISNULL(p.segundo_apellido, '''')) FROM CASO c INNER JOIN ROL_PERSONA_CASO rp ON c.id_caso = rp.id_caso AND rp.estado_registro = ''ACT'' INNER JOIN ROL r ON rp.id_rol = r.id_rol AND r.nombre = ''ABO'' AND r.estado_registro = ''ACT'' INNER JOIN PERSONA p ON rp.id_persona = p.id_persona WHERE c.id_caso = ?1' WHERE id_plantilla_carta = 24 AND nombre_valor_dinamico = 'abogadoP'


-- ajuste plantillas multiparte
update PLANTILLA_CARTA set plantilla_html = '<html>     <head>        <META http-equiv="Content-Type" content="text/html; charset=UTF-8"/>     </head>     
  <body>       
    <p>ciudadP, dia_cartaP de mes_cartaP de ano_cartaP</p>        
    <p>listaPartesSeleccionadasP<br/>
    </p>       
    <p>        
    <table>          
      <tr>              
        <td valign="top" width="15%">REFERENCIA:</td>              
        <td width="75%">servicioP DE casoP - Caso codigoP</td>           
      </tr>        
    </table>        
    </p>
	<p>Respetados señores y doctores:</p>
  chooseTipoAudienciaP chooseTipoAudienciaArbitrajeP        
  <p align="justify"> Cualquier información sobre el particular, no dude en comunicarse con nosotros al correo electrónico correoP o al teléfono 5941000 Ext. 2319/2323. </p>        
  <p>Esperamos contar con su activa y valiosa participación.</p>        
  <p>Cordialmente,</p>        
  firmaAudienciaP     
  </body>  
</html>' where id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCADP')

insert into VALOR_PLANTILLA_CARTA 
select nombre_valor_dinamico, (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCADP'), 'USER_SIMASC', GETDATE(), 'ACT'
, null, consulta, null from valor_plantilla_carta where id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCCIC') and nombre_valor_dinamico = 'listaPartesSeleccionadasP'

update PLANTILLA_CARTA set plantilla_html = '<!DOCTYPE html><html><head><meta charset="UTF-8"/></head><body><p>ciudadP, dia_cartaP de mes_cartaP de ano_cartaP</p><p>dirigidoP</p><p>direccionP (ciudad_cartaP)</p><table><tr><td valign="top" width="15%">REFERENCIA:</td><td width="75%">servicioP DE convocanteP CON convocadoP - Caso casoP</td></tr></table></p><p>Respetado(a) respetadoP</p><p align="justify">De la manera más atenta, acusamos recibo del documento radicado en las instalaciones de este Centro el pasado _____, por medio del cual se retiró la demanda del trámite arbitral de la referencia.</p><p align="justify">En consecuencia, nos permitimos informarle que este Centro procedió con el cierre del caso y al archivo correspondiente. En ese sentido, los documentos se encuentran a su entera disposición para su retiro.</p><p>Agradecemos su amable colaboración y esperamos volver a prestarles nuestros servicios</p><p>Cordialmente,</p><p><img src="firmaP"></img></p><p><b>directorP</b><br/><b>cargoP</b></p><p><font size="1">inicialesP</font></p><p align="right"><font size="1">casoP</font></p></body></html> ' where id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCCIC')

update VALOR_PLANTILLA_CARTA set consulta = 'select concat((case  
when r.tipo_persona = ''NAT'' and p.nombre = ''APO'' or p.nombre = ''APD'' then ''Doctor(a)</br>''
when r.tipo_persona = ''JUR'' then ''Señor(a)</br>Representante Legal de</br>''
else ''Señor(a)</br>''
end), concat(r.primer_nombre_o_razon_social, rtrim('' ''+r.segundo_nombre), rtrim('' ''+r.primer_apellido), rtrim('' ''+r.segundo_apellido)))
from Persona r  left join ROL_PERSONA_CASO prpc on prpc.id_persona = r.id_persona                                   
left join ROL p on p.id_rol = prpc.id_rol                  
where prpc.id_caso=?1 
and prpc.estado_registro=''ACT'' 
and p.estado_registro = ''ACT'' 
and r.estado_registro = ''ACT''
and r.id_persona = ?2', nombre_valor_dinamico = 'dirigidoP' where nombre_valor_dinamico = 'listaPartesSeleccionadasP' and id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCCIC')

update VALOR_PLANTILLA_CARTA set consulta = 'select case  
when r.tipo_persona = ''NAT'' and p.nombre = ''APO'' or p.nombre = ''APD'' then ''Doctor(a)</br>''                        
when r.tipo_persona = ''JUR'' then ''Señor(a)</br>Representante Legal de</br>''
else ''Señor(a)</br>''
end
from Persona r  left join ROL_PERSONA_CASO prpc on prpc.id_persona = r.id_persona                                   
left join ROL p on p.id_rol = prpc.id_rol                  
where prpc.id_caso=?1                   
and prpc.estado_registro=''ACT''                  
and p.estado_registro = ''ACT''                  
and r.estado_registro = ''ACT''
and r.id_persona = ?2' where id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCCIC') and nombre_valor_dinamico = 'respetadoP'

insert into VALOR_PLANTILLA_CARTA 
select nombre_valor_dinamico, (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCFGDP'), 'USER_SIMASC', GETDATE(), 'ACT'
, null, consulta, null from valor_plantilla_carta where id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCADP') and nombre_valor_dinamico = 'listaPartesSeleccionadasP'

insert into VALOR_PLANTILLA_CARTA 
select nombre_valor_dinamico, (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCPAR'), 'USER_SIMASC', GETDATE(), 'ACT'
, null, consulta, null from valor_plantilla_carta where id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCADP') and nombre_valor_dinamico = 'listaPartesSeleccionadasP'

insert into VALOR_PLANTILLA_CARTA 
select nombre_valor_dinamico, (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCFGDRA'), 'USER_SIMASC', GETDATE(), 'ACT'
, null, consulta, null from valor_plantilla_carta where id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCADP') and nombre_valor_dinamico = 'listaPartesSeleccionadasP'

update VALOR_PLANTILLA_CARTA set consulta = 'NATIVE: select replace(stuff((
select  concat((case when r.tipo_persona = ''NAT'' and p.nombre = ''APO'' or p.nombre = ''APD'' then ''Doctor(a)#'' when r.tipo_persona = ''JUR'' then ''Señor(a)#Representante Legal de#''                        
else ''Señor(a)#'' end), concat(r.primer_nombre_o_razon_social, rtrim('' ''+r.segundo_nombre), rtrim('' ''+r.primer_apellido), rtrim('' ''+r.segundo_apellido)))
from Persona r  left join ROL_PERSONA_CASO prpc on prpc.id_persona = r.id_persona left join ROL p on p.id_rol = prpc.id_rol                  
where prpc.id_caso=?1                   
and prpc.estado_registro=''ACT''                  
and p.estado_registro = ''ACT''                  
and r.estado_registro = ''ACT''  
AND p.nombre IN(''DTE'', ''DDA'', ''APO'', ''APD'') 
FOR XML PATH ('''')), 1, 0, ''''), ''#'', ''<br/>'')' where nombre_valor_dinamico = 'listaPartesSeleccionadasP'

update PLANTILLA_CARTA set plantilla_html = '<html>  <body>      <p>ciudadP, dia_cartaP de mes_cartaP de ano_cartaP</p>      <p>          listaPartesSeleccionadasP      </p>      <p>          <table>              <tr>                  <td valign="top" width="15%">REFERENCIA:</td><td width="75%">servicioP de convocanteP y convocadoP - Caso casoP.              </td>          </tr>      </table>  </p>  <p>      Respetado(a) señores y doctores            </p>        <p align="justify">          Dando cumplimiento al artículo 15 de la ley 1563 de 2012, de la manera más atenta, le remito escrito de aceptación arbitrosP          para lo de su competencia.      </p>      <p>Cualquier información sobre el particular, no dude en comunicarse con nosotros al correo electrónico correoP, o al teléfono telefonoP</p>      <p>Anexo lo enunciado en (cnt_arbP) folios.</p>      <p>Cordialmente,</p>      <p>          <img src="firmaP"></img></p>          <p>              <b>directorP</b>              <br/>              <b>cargoP</b>          </p>          <p>              <font size="1">inicialesP</font>          </p>          <p align="right">              <font size="1">casoP</font>          </p>                </body>      </html>' where id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCPAR')

update PLANTILLA_CARTA set plantilla_html = '<html>     <head>        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>     </head>     <body>    <p>ciudadP, dia_cartaP de mes_cartaP de ano_cartaP</p>    <p>  listaPartesSeleccionadasP  </p>  <p>Ciudad</p>    <p>REFERENCIA:  TRAMITE ARBITRAL DE convocanteP PARA SOLUCIONAR LAS DIFERENCIAS SURGIDAS CON convocadoP</p>    <p>Respetado(a)s doctor(a)es </p>    <p>arbitrosP </p>    <p>Codialmente,</p>    <p><img src="firmaP" style=\"width:50%;\"></p>    <p><b>directorP</b><br/><b></b></p>    <p>Director</p>        </p>     </body>  </html>' where id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCFGDRA')

/* ajuste plantilla notificacion de arbitro internacional */

update valor_plantilla_carta set consulta = 'NATIVE: SELECT concat(p.primer_nombre_o_razon_social, rtrim('' ''+p.segundo_nombre), rtrim('' ''+p.primer_apellido), rtrim('' ''+p.segundo_apellido))
FROM Persona p INNER JOIN ROL_PERSONA_CASO prpc ON prpc.id_persona = p.id_persona 
WHERE prpc.id_caso=?1 AND prpc.id_rol IN (SELECT DISTINCT id_rol FROM PARAMETRO_SERVICIO_SORTEO) 
and prpc.tipo_nombramiento=''PRI'' and prpc.estado=''POR'' and p.id_persona = ?2' where nombre_valor_dinamico = 'dirigido_aP' and id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCNARI')

update valor_plantilla_carta set consulta = 'NATIVE:SELECT isnull(p.primer_apellido,'''') FROM Persona p INNER JOIN ROL_PERSONA_CASO prpc ON prpc.id_persona = p.id_persona 
WHERE prpc.id_caso=?1 AND prpc.id_rol IN (SELECT DISTINCT id_rol FROM PARAMETRO_SERVICIO_SORTEO) and prpc.tipo_nombramiento=''PRI'' and prpc.estado=''POR'' and p.id_persona=?2' where nombre_valor_dinamico = 'apellidoP' 
and id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCNARI')


-- Correccion defecto 9486
update VALOR_PLANTILLA_CARTA set consulta = 'NATIVE: select replace(stuff((
select  concat((case when r.tipo_persona = ''NAT'' and p.nombre = ''APO'' or p.nombre = ''APD'' then ''Doctor(a)#'' when r.tipo_persona = ''JUR'' then ''Señor(a)#Representante Legal de#''                        
else ''Señor(a)#'' end), concat(r.primer_nombre_o_razon_social, rtrim('' ''+r.segundo_nombre), rtrim('' ''+r.primer_apellido), rtrim('' ''+r.segundo_apellido), ''#''))
from Persona r  left join ROL_PERSONA_CASO prpc on prpc.id_persona = r.id_persona left join ROL p on p.id_rol = prpc.id_rol                  
where prpc.id_caso=?1                   
and prpc.estado_registro=''ACT''                  
and p.estado_registro = ''ACT''                  
and r.estado_registro = ''ACT''  
AND p.nombre IN(''DTE'', ''DDA'', ''APO'', ''APD'') 
FOR XML PATH ('''')), 1, 0, ''''), ''#'', ''<br/>'')' where nombre_valor_dinamico = 'listaPartesSeleccionadasP'

/* Ajuste bug 7143 reportado en TFS */ 
UPDATE VALOR_PLANTILLA_CARTA SET consulta = 'NATIVE: select ''<p><img src="firmaP"/></p><p><b>directorP</b><br/><b>cargoP</b></p><p><font size="1">inicialesP</font></p><p align="right"><font size="1">casoP</font></p>''' WHERE nombre_valor_dinamico = 'firmaAudienciaEtapaPrearbitralP' AND id_plantilla_carta = (SELECT id_plantilla_carta FROM PLANTILLA_CARTA WHERE nombre = 'PCADP')

/* Ajuste del valor dinámico 'apellidoPresidenteP' de la plantilla 'Comunicación de aceptación de árbitros' */
UPDATE VALOR_PLANTILLA_CARTA SET consulta = 'select rpc.persona.primerApellido from RolPersonaCaso rpc where rpc.caso.idCaso=:idCaso and rpc.rol.idRol IN(select r.idRol from Rol r where r.nombre IN (''ARB'', ''ARI''))  and rpc.estado = ''ACE'' AND rpc.esPresidente = ''true''' where nombre_valor_dinamico = 'apellidoPresidenteP' and id_plantilla_carta = (SELECT id_plantilla_carta FROM PLANTILLA_CARTA WHERE nombre = 'PCCAA')

/*---------------------- CONCILIACIÓN ----------------------*/

-- CON-C-028 Script seguiridad

DELETE FROM FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC028MarcarCasosCobrados'
DELETE FROM FUNCIONALIDAD WHERE nombre = 'CONC028MarcarCasosCobrados'

INSERT INTO FUNCIONALIDAD VALUES ('CONC028MarcarCasosCobrados', 'Marcar casos cobrados', 'app/Conciliacion/CONC028', 'PDL', 'ANGULAR', NULL, 'SIMASC_USER', GETDATE(), 'ACT')

INSERT INTO FUNCIONALIDAD_ROL
SELECT DISTINCT 'USUARIO_SIMASC', GETDATE(), 'INA', 'CONC028MarcarCasosCobrados', r.id_rol
FROM ROL r JOIN DOMINIO d ON r.nombre = d.codigo
WHERE d.dominio IN ('ROL_PERSONA', 'ROL_DE_PARTE_ARBITRAJE', 'ROL_DE_PARTE_CONCILIACION')
AND d.codigo NOT IN ('ACO')


-- CON-C-029
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONC029ModificarAudiencia';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONC029EnviarNotificacion';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONC029ConsultarTurnosJornada';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='jornadas';
DELETE FUNCIONALIDAD WHERE nombre = 'CONC029ModificarAudiencia';
DELETE FUNCIONALIDAD WHERE nombre = 'CONC029EnviarNotificacion';
DELETE FUNCIONALIDAD WHERE nombre = 'CONC029ConsultarTurnosJornada';
DELETE FUNCIONALIDAD WHERE nombre = 'jornadas';

INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('jornadas', 'Jornadas', 'app/Conciliacion/','PDL','ANGULAR',null,'SIMASC_USER',GETDATE(),'ACT')

INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('CONC029ConsultarTurnosJornada', 'Consultar turnos jornadas', 'app/Conciliacion/CONC029','PDL','ANGULAR','jornadas','SIMASC_USER',GETDATE(),'ACT')

INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('CONC029ModificarAudiencia', 'Modificar audiencia', 'app/Conciliacion/CONC029','PDL','ANGULAR','CONC029ConsultarTurnosJornada','SIMASC_USER',GETDATE(),'ACT')

INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('CONC029EnviarNotificacion', 'Notificación', 'app/Conciliacion/CONC029','PDL','ANGULAR','CONC029ConsultarTurnosJornada','SIMASC_USER',GETDATE(),'ACT')

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','jornadas',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'SECCON', 'JEFECON');

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONC029ConsultarTurnosJornada',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'SECCON', 'JEFECON');

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONC029ModificarAudiencia',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'SECCON', 'JEFECON');

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONC029EnviarNotificacion',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'SECCON', 'JEFECON');
	
			
-- CON-C-030
update parametro_de_servicio set valor = 'MEDIAHOR' where nombre = 'DURACION_DE_AUDIENCIA' and id_servicio = 9

insert into parametro_de_servicio values ('HORA_INICIO_AUDIENCIA', 'PROGAUDI', 9, '7:00', 'Valor que indica la hora inicial en la que se puede programar una audiencia', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into parametro_de_servicio values ('HORA_FIN_AUDIENCIA', 'PROGAUDI', 9, '17:00', 'Valor que indica la hora final en la que se puede programar una audiencia', 'USUARIO_SIMASC', GETDATE(), 'ACT')


DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONC030CrearJornada';
DELETE FUNCIONALIDAD WHERE nombre = 'CONC030CrearJornada';

INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('CONC030CrearJornada', 'Crear jornada', 'app/Conciliacion/CONC030','PDL','ANGULAR','jornadas','SIMASC_USER',GETDATE(),'ACT')

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONC030CrearJornada',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'SECCON', 'JEFECON');	
		

insert into DOMINIO (codigo,dominio,nombre,descripcion)
values('NCEC','TIPO_EVENTO','Notificación por correo electrónico certificado','notificación por correo electrónico certificado')

-- ADM-F-041
insert into TAG_PLANTILLA (nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values('Nombre demandante', 'demandanteP','NATIVE: Select ISNULL(c.primer_nombre_o_razon_social,'''') +'' ''+ ISNULL(c.segundo_Nombre, '''') +'' ''+ ISNULL(c.primer_Apellido,'') +'' ''+ isnull(c.segundo_Apellido,'''') from CASO as a LEFT JOIN ROL_PERSONA_CASO b on a.id_caso = b.id_caso LEFT JOIN PERSONA as c on b.id_persona = c.id_persona INNER JOIN ROL d ON b.id_rol = d.id_rol WHERE d.nombre = ''DTE'' AND c.estado_persona = ''ACT'' and b.estado != ''INA''  AND a.id_caso=?1'
, 'PJT', 'Trae los demandantes del caso', 'SIMASC_USER', GETDATE(), 'ACT')

insert into TAG_PLANTILLA (nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values('Nombre demandado', 'demandadoP', 'NATIVE: Select ISNULL(c.primer_nombre_o_razon_social,'''') +'' ''+ ISNULL(c.segundo_Nombre, '''') +'' ''+ ISNULL(c.primer_Apellido,'') +'' ''+ isnull(c.segundo_Apellido,'''') from CASO as a LEFT JOIN ROL_PERSONA_CASO b on a.id_caso = b.id_caso LEFT JOIN PERSONA as c on b.id_persona = c.id_persona INNER JOIN ROL d ON b.id_rol = d.id_rol WHERE d.nombre = ''DDA'' AND c.estado_persona = ''ACT'' and b.estado != ''INA''  AND a.id_caso=?1'
, 'PJT', 'Trae los demandados del caso', 'SIMASC_USER', GETDATE(), 'ACT')

insert into TAG_PLANTILLA (nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
VALUES('Nombre de árbitros principales', 'arbitrosPrincipales', 'NATIVE: select concat ((select case when (  select count(*) from PERSONA cp  left join ROL_PERSONA_CASO rpc on rpc.id_persona=cp.id_persona where rpc.id_caso= ?1 and rpc.tipo_nombramiento=''PRI'' and rpc.estado=''ACE'' and rpc.estado_registro=''ACT'')>1 then '' los doctores '' else '' doctor '' end),(  select stuff ((select concat('', '', cp.primer_nombre_o_razon_social,'' '', cp.segundo_nombre, '' '', cp.primer_apellido,'' '', cp.segundo_apellido) from PERSONA cp left join ROL_PERSONA_CASO rpc on rpc.id_persona=cp.id_persona where rpc.id_caso=?1 and rpc.tipo_nombramiento=''PRI'' and rpc.estado=''ACE'' and rpc.estado_registro=''ACT'' and rpc.id_rol in (select distinct id_rol from parametro_servicio_sorteo) for xml path('''')),1,1,'''')))'
, 'PJT', 'Trae todos los arbitros principales que se encuentren en estado aceptado', 'SIMASC_USER', GETDATE(), 'ACT') -- ARBITROS PRINCIPALES

insert into TAG_PLANTILLA (nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
VALUES('Nombre de arbitros suplentes', 'arbitrosSuplentes', 'NATIVE: select concat ((select case when (  select count(*) from PERSONA cp  left join ROL_PERSONA_CASO rpc on rpc.id_persona=cp.id_persona where rpc.id_caso= ?1 and rpc.tipo_nombramiento=''PRI'' and rpc.estado=''ACE'' and rpc.estado_registro=''ACT'')>1 then '' los doctores '' else '' doctor '' end),(  select stuff ((select concat('', '', cp.primer_nombre_o_razon_social,'' '', cp.segundo_nombre, '' '', cp.primer_apellido,'' '', cp.segundo_apellido) from PERSONA cp left join ROL_PERSONA_CASO rpc on rpc.id_persona=cp.id_persona where rpc.id_caso=?1 and rpc.tipo_nombramiento=''PRI'' and rpc.estado=''DESG'' and rpc.estado_registro=''ACT'' and rpc.id_rol in (select distinct id_rol from parametro_servicio_sorteo) for xml path('''')),1,1,'''')))'
, 'PJT', 'Trae todos los arbitros suplentes que se encuentren en estado designado', 'SIMASC_USER', GETDATE(), 'ACT') -- ARBITROS SUPLENTES

insert into TAG_PLANTILLA (nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
VALUES('Abogado', 'abogadoP', 'NATIVE: select CONCAT(p.primer_nombre_o_razon_social,'' '', p.segundo_nombre,'' '', p.primer_apellido,'' '',  p.segundo_apellido) from Caso c LEFT JOIN ROL_PERSONA_CASO as cr ON c.id_caso = CR.id_caso left join ROL r on r.id_rol = cr.id_rol LEFT JOIN PERSONA p on p.id_persona = cr.id_persona where r.nombre=''ABO'' and c.id_caso =?1'
, 'PJT', 'Trae el abogado del caso', 'SIMASC_USER', GETDATE(), 'ACT') -- ABOGADO

insert into TAG_PLANTILLA (nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
VALUES('Fecha de audiencia', 'fechaAudiencia', 'NATIVE: select format(hora_inicio, ''dd/MM/yyyy'') from audiencia where id_caso = ?1 and estado_registro = ''ACT'' and estado = ''PEN'''
, 'PJT', 'Trae la fecha de la audiencia que se encuentre en estado pendiente', 'SIMASC_USER', GETDATE(), 'ACT') -- FECHA AUDIENCIA

insert into TAG_PLANTILLA (nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
VALUES('Fecha de audiencia', 'fechaAudiencia', 'NATIVE: select format(hora_inicio, ''dd/MM/yyyy'') from audiencia where id_caso = ?1 and estado_registro = ''ACT'' and estado = ''PEN'''
, 'PDL', 'Trae la fecha de la audiencia que se encuentre en estado pendiente', 'SIMASC_USER', GETDATE(), 'ACT') -- FECHA AUDIENCIA

insert into TAG_PLANTILLA (nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
VALUES('Dirección de audiencia', 'direccionAudiencia', 'NATIVE: select se.direccion from audiencia a left outer join agendamiento ag on a.id_audiencia = ag.id_audiencia left outer join sala s on s.id_sala = ag.id_sala left outer join sede se on se.id_sede = s.id_sede where a.id_caso = ?1 and a.estado_registro = ''ACT'' and ag.estado_registro = ''ACT'' and s.estado_registro = ''ACT'' and se.estado_registro = ''ACT'' and a.estado = ''PEN'''
, 'PJT', 'Trae la direccion de la audiencia que se encuentre en estado pendiente', 'SIMASC_USER', GETDATE(), 'ACT') -- DIRECCION AUDIENCIA

insert into TAG_PLANTILLA (nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
VALUES('Dirección de audiencia', 'direccionAudiencia', 'NATIVE: select se.direccion from audiencia a left outer join agendamiento ag on a.id_audiencia = ag.id_audiencia left outer join sala s on s.id_sala = ag.id_sala left outer join sede se on se.id_sede = s.id_sede where a.id_caso = ?1 and a.estado_registro = ''ACT'' and ag.estado_registro = ''ACT'' and s.estado_registro = ''ACT'' and se.estado_registro = ''ACT'' and a.estado = ''PEN'''
, 'PDL', 'Trae la direccion de la audiencia que se encuentre en estado pendiente', 'SIMASC_USER', GETDATE(), 'ACT') -- DIRECCION AUDIENCIA

insert into TAG_PLANTILLA (nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
VALUES('Hora de audiencia', 'horaAudiencia', 'NATIVE: select format(hora_inicio, ''HH:mm'') from audiencia where id_caso = ?1 and estado_registro = ''ACT'' and estado = ''PEN'''
, 'PJT', 'Trae la hora de la audiencia que se encuentre en estado pendiente', 'SIMASC_USER', GETDATE(), 'ACT') -- FECHA AUDIENCIA

insert into TAG_PLANTILLA (nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
VALUES('Hora de audiencia', 'horaAudiencia', 'NATIVE: select format(hora_inicio, ''HH:mm'') from audiencia where id_caso = ?1 and estado_registro = ''ACT'' and estado = ''PEN'''
, 'PDL', 'Trae la hora de la audiencia que se encuentre en estado pendiente', 'SIMASC_USER', GETDATE(), 'ACT') -- FECHA AUDIENCIA

insert into TAG_PLANTILLA (nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
VALUES('Pacto arbitral', 'pactoArbitral', 'select c.descripcionPacto from Caso c where c.idCaso=:idCaso and c.estadoRegistro = ''ACT'''
, 'PJT', 'Obtiene el pacto arbitral del caso', 'SIMASC_USER', GETDATE(), 'ACT') -- PACTO ARBITRAL

insert into TAG_PLANTILLA (nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
VALUES('Nombre del centro', 'nombreCentro', 'NATIVE: select ce.nombre from caso c left outer join sede s on s.id_sede = c.id_sede left outer join centro ce on ce.id_centro = s.id_centro where c.id_caso = ?1 and c.estado_registro = ''ACT'' and s.estado_registro = ''ACT'' and ce.estado_registro = ''ACT'''
, 'PJT', 'Trae el nombre del centro en el que se encuentra radicado el caso', 'SIMASC_USER', GETDATE(), 'ACT') -- NOMBRE DEL CENTRO

insert into TAG_PLANTILLA (nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
VALUES('Nombre del centro', 'nombreCentro', 'NATIVE: select ce.nombre from caso c left outer join sede s on s.id_sede = c.id_sede left outer join centro ce on ce.id_centro = s.id_centro where c.id_caso = ?1 and c.estado_registro = ''ACT'' and s.estado_registro = ''ACT'' and ce.estado_registro = ''ACT'''
, 'PDL', 'Trae el nombre del centro en el que se encuentra radicado el caso', 'SIMASC_USER', GETDATE(), 'ACT') -- NOMBRE DEL CENTRO

insert into TAG_PLANTILLA (nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values('Nombre convocante', 'convocanteP','NATIVE: Select ISNULL(c.primer_nombre_o_razon_social,'''') +'' ''+ ISNULL(c.segundo_Nombre, '''') +'' ''+ ISNULL(c.primer_Apellido,'''') +'' ''+ isnull(c.segundo_Apellido,'''') from CASO as a LEFT JOIN ROL_PERSONA_CASO b on a.id_caso = b.id_caso LEFT JOIN PERSONA as c on b.id_persona = c.id_persona INNER JOIN ROL d ON b.id_rol = d.id_rol WHERE d.nombre = ''CONVOTE'' AND c.estado_persona = ''ACT'' and b.estado != ''INA''  AND a.id_caso=?1'
, 'PDL', 'Trae los convocantes del caso', 'SIMASC_USER', GETDATE(), 'ACT')

insert into TAG_PLANTILLA (nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values('Nombre convocado', 'convocadoP','NATIVE: Select ISNULL(c.primer_nombre_o_razon_social,'''') +'' ''+ ISNULL(c.segundo_Nombre, '''') +'' ''+ ISNULL(c.primer_Apellido,'''') +'' ''+ isnull(c.segundo_Apellido,'''') from CASO as a LEFT JOIN ROL_PERSONA_CASO b on a.id_caso = b.id_caso LEFT JOIN PERSONA as c on b.id_persona = c.id_persona INNER JOIN ROL d ON b.id_rol = d.id_rol WHERE d.nombre = ''CONVODO'' AND c.estado_persona = ''ACT'' and b.estado != ''INA''  AND a.id_caso=?1'
, 'PDL', 'Trae los convocados del caso', 'SIMASC_USER', GETDATE(), 'ACT')

insert into TAG_PLANTILLA (tipo_consulta, nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values('IPP', 'Dirección envío', 'direccionP','NATIVE: SELECT DISTINCT ISNULL(ubi.direccion,'''') FROM PERSONA per INNER JOIN UBICACION ubi on per.id_persona = ubi.id_persona and per.id_persona =?2 and per.estado_registro = ''ACT'' and ubi.estado_registro = ''ACT'''
, 'PJT', 'Obtiene la dirección del destinatario', 'SIMASC_USER', GETDATE(), 'ACT')

insert into TAG_PLANTILLA (nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values('IPP', 'Ciudad envío', 'ciudadEnvioP','NATIVE: SELECT DISTINCT ISNULL(z.nombre,'''') FROM PERSONA per INNER JOIN UBICACION ubi on per.id_persona = ubi.id_persona and per.id_persona = ?2 LEFT JOIN ZONA_GEOGRAFICA z on z.id_zona_geografica = ubi.id_zona_geografica where per.estado_registro = ''ACT'' and ubi.estado_registro = ''ACT'' and z.estado_registro = ''ACT'''
, 'PJT', 'Obtiene la ciudad de envio del destinatario', 'SIMASC_USER', GETDATE(), 'ACT')

insert into TAG_PLANTILLA (tipo_consulta, nombre, codigo, consulta, tipo_servicio, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values('IPP', 'Teléfono envío', 'telefonoP', 'NATIVE: select distinct numero from telefono where id_persona = ?2 and estado_registro = ''ACT'' order by tipo_telefono desc'
, 'PJT', 'Obtiene el teléfono del destinatario', 'SIMASC_USER', GETDATE(), 'ACT')

INSERT INTO TAG_PLANTILLA ( CODIGO, NOMBRE, CONSULTA, TIPO_SERVICIO, DESCRIPCION, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO)
VALUES ('nombreCasoP', 'Nombre del caso', 'NATIVE: select nombre from caso where id_caso = ?1', 'PDL', 'trae el nombre de un caso a partir del id del caso', 'USER_SIMASC', SYSDATETIME(), 'ACT');

INSERT INTO TAG_PLANTILLA ( CODIGO, NOMBRE, CONSULTA, TIPO_SERVICIO, DESCRIPCION, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO)
VALUES ('nombreCasoP', 'Nombre del caso', 'NATIVE: select nombre from caso where id_caso = ?1', 'PJT', 'trae el nombre de un caso a partir del id del caso', 'USER_SIMASC', SYSDATETIME(), 'ACT');

INSERT INTO TAG_PLANTILLA (tipo_consulta, CODIGO, NOMBRE, CONSULTA, TIPO_SERVICIO, DESCRIPCION, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO)
VALUES ('IPP', 'dirigidoP', 'Nombre del destinatario', 'NATIVE: select concat(p.primer_nombre_o_razon_social,'' '', p.segundo_nombre, '' '', p.primer_apellido,'' '', p.segundo_apellido,'' '') from Persona p left join ROL_PERSONA_CASO prpc on prpc.id_persona = p.id_persona where prpc.id_caso=?1 AND prpc.estado_registro=''ACT'' and p.id_persona=?2', 'PDL', 'nombre de la persona a la que va dirigida la carta', 'USER_SIMASC', SYSDATETIME(), 'ACT');

INSERT INTO TAG_PLANTILLA (tipo_consulta, CODIGO, NOMBRE, CONSULTA, TIPO_SERVICIO, DESCRIPCION, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO)
VALUES ('IPP', 'dirigidoP', 'Nombre del destinatario', 'NATIVE: select concat(p.primer_nombre_o_razon_social,'' '', p.segundo_nombre, '' '', p.primer_apellido,'' '', p.segundo_apellido,'' '') from Persona p left join ROL_PERSONA_CASO prpc on prpc.id_persona = p.id_persona where prpc.id_caso=?1 AND prpc.estado_registro=''ACT'' and p.id_persona=?2', 'PJT', 'nombre de la persona a la que va dirigida la carta', 'USER_SIMASC', SYSDATETIME(), 'ACT');

INSERT INTO TAG_PLANTILLA ( CODIGO, NOMBRE, CONSULTA, TIPO_SERVICIO, DESCRIPCION, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO)
VALUES ('conciliadorP', 'Nombre del conciliador', 'NATIVE: select ISNULL(p.primer_nombre_o_razon_social,'''') +'' ''+ ISNULL(p.segundo_Nombre, '''') 
    +'' ''+ ISNULL(p.primer_Apellido,'''') +'' ''+ isnull(p.segundo_Apellido,'''')  
from rol_persona_caso rpc 
    inner join persona p on p.id_persona = rpc.id_persona
where id_rol in (select id_rol from tipo_de_servicio_rol where tipo_servicio = ''PDL'')
and rpc.estado not in (''REC'', ''INA'')
and id_caso = ?1
and rpc.estado_registro = ''ACT''
and p.estado_registro = ''ACT''', 'PDL', 'trae el nombre completo del conciliador asignador al caso', 'USER_SIMASC', SYSDATETIME(), 'ACT');

INSERT INTO TAG_PLANTILLA ( CODIGO, NOMBRE, CONSULTA, TIPO_SERVICIO, DESCRIPCION, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO)
VALUES ('direccionCentroP', 'Dirección del centro', 'NATIVE: SELECT c.direccion
FROM CENTRO c
INNER JOIN SEDE sede
ON sede.id_centro = c.id_centro
INNER JOIN CASO caso
ON caso.id_sede = sede.id_sede
WHERE caso.id_caso = ?1
AND c.estado_registro = ''ACT''
AND sede.estado_registro = ''ACT''
AND caso.estado_registro = ''ACT''', 'PDL', 'Trae la direccion del centro', 'USER_SIMASC', SYSDATETIME(), 'ACT');

INSERT INTO TAG_PLANTILLA ( CODIGO, NOMBRE, CONSULTA, TIPO_SERVICIO, DESCRIPCION, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO)
VALUES ('directorCentroP', 'Nombre del director del centro', 'NATIVE: SELECT c.director
FROM CENTRO c
INNER JOIN SEDE sede
ON sede.id_centro = c.id_centro
INNER JOIN CASO caso
ON caso.id_sede = sede.id_sede
WHERE caso.id_caso = ?1
AND c.estado_registro = ''ACT''
AND sede.estado_registro = ''ACT''
AND caso.estado_registro = ''ACT''', 'PDL', 'Trae el nombre director del centro', 'USER_SIMASC', SYSDATETIME(), 'ACT');

INSERT INTO TAG_PLANTILLA ( CODIGO, NOMBRE, CONSULTA, TIPO_SERVICIO, DESCRIPCION, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO)
VALUES ('ciudadP', 'Dirección del centro', NULL, 'PJT', 'Trae la direccion del centro', 'USER_SIMASC', SYSDATETIME(), 'ACT');

INSERT INTO TAG_PLANTILLA ( CODIGO, NOMBRE, CONSULTA, TIPO_SERVICIO, DESCRIPCION, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO)
VALUES ('directorP', 'Nombre del director del centro', null, 'PJT', 'Trae el nombre director del centro', 'USER_SIMASC', SYSDATETIME(), 'ACT');

INSERT INTO TAG_PLANTILLA ( CODIGO, NOMBRE, CONSULTA, TIPO_SERVICIO, DESCRIPCION, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO)
VALUES ('cargoP', 'Cargo del director del centro', null, 'PJT', 'Trae el nombre director del centro', 'USER_SIMASC', SYSDATETIME(), 'ACT');

INSERT INTO TAG_PLANTILLA ( CODIGO, NOMBRE, CONSULTA, TIPO_SERVICIO, DESCRIPCION, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO)
VALUES ('cargoP', 'Cargo del director del centro', null, 'PDL', 'Trae el nombre director del centro', 'USER_SIMASC', SYSDATETIME(), 'ACT');

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='ADM038plantillas';
DELETE FUNCIONALIDAD WHERE nombre = 'ADM038plantillas';

INSERT INTO [dbo].[FUNCIONALIDAD]
     VALUES
     ('ADM038plantillas', 'Consultar plantillas', 'app/Conciliacion/ADM041','ADM','ANGULAR',NULL,'SIMASC_USER',GETDATE(),'ACT')
GO

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','ADM038plantillas',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ADMINU');
			

/*INSERTS REPARTO*/

--Este insert ya lo había enviado

  INSERT INTO PARAMETROS_GENERALES(codigo, tipo, nombre, valor_numerico, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
  VALUES('VLRPRET', 'TOPE_PRETENTECIONES_LISTA', 'Valor Pretenciones', 30000000, 'Valor de las pretenciones para determinar si se toman conciliadores de la lista A o de la lista B', 'USUARIO_SIMASC', SYSDATETIME(), 'ACT');  

--estos insert son utilizados para la asignación cíclica de casos
  INSERT INTO PARAMETROS_GENERALES(codigo, tipo, nombre, valor_numerico, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
  VALUES('ACCR_ABG', 'ASIGNACION_CICLICA_CASOS_REP', 'Abogado de arbitraje', null, 'Identificador (rol_persona) del último abogado de arbitraje al que se le asignó un caso', 'USUARIO_SIMASC', SYSDATETIME(), 'ACT');  
  INSERT INTO PARAMETROS_GENERALES(codigo, tipo, nombre, valor_numerico, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
  VALUES('ACCR_ANC', 'ASIGNACION_CICLICA_CASOS_REP', 'Analista de conciliación', null, 'Identificador (rol_persona) del último analista de conciliación al que se le asignó un caso', 'USUARIO_SIMASC', SYSDATETIME(), 'ACT');  
  INSERT INTO PARAMETROS_GENERALES(codigo, tipo, nombre, valor_numerico, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
  VALUES('ACCR_AUX', 'ASIGNACION_CICLICA_CASOS_REP', 'Auxiliar adiministrativo', null, 'Identificador (rol_persona) del último auxiliar administrativo al que se le asignó un caso', 'USUARIO_SIMASC', SYSDATETIME(), 'ACT');  

--Yo ejecuté los siguientes scripts, pero más que todo fue para realizar pruebas, pienso que no deben ir en la entrega:

insert into AGRUPAMIENTO_ROL (id_rol, id_servicio, tipo_agrupamiento, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
  values(52, 1, 'PSERVICI', 'USUARIO_SIMASC', SYSDATETIME(), 'ACT');
  insert into AGRUPAMIENTO_ROL (id_rol, id_servicio, tipo_agrupamiento, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
  values(52, 8, 'PSERVICI', 'USUARIO_SIMASC', SYSDATETIME(), 'ACT');
  
  
 /* permisos funcionalidad CON-F-074  */
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF074GeneracionLotesCartas';
DELETE FUNCIONALIDAD WHERE nombre ='CONF074GeneracionLotesCartas';

insert into FUNCIONALIDAD 
values ('CONF074GeneracionLotesCartas', 'Generación de lotes de cartas', 'app/Conciliacion/CONF074','PDL','ANGULAR',NULL,'SIMASC_USER',SYSDATETIME(),'ACT');

insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF074GeneracionLotesCartas',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA','ROL_DE_PARTE_ARBITRAJE') and  d.codigo  
		NOT IN ('ACO','SECCON');
		
/* permisos CONF055*/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF055SeleccionarPartesCitacion';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF055SeleccionarPartesCitacion';
insert into FUNCIONALIDAD values ('CONF055SeleccionarPartesCitacion', 'Seleccionar partes para citación por correo certificado', 'app/Conciliacion/CONF055','PDL','ANGULAR','CONF056buttonEnviarCitacion','SIMASC_USER',SYSDATETIME(),'ACT');
insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF055SeleccionarPartesCitacion',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ( 'CON', 'ACO', 'SECCON', 'JEFECON' );
			
/* creación de plantilla: Citacion a audiencia diferente a la primera: PCADP tipo_servicio: PDL */
INSERT INTO PLANTILLA_CARTA ( NOMBRE, TIPO_SERVICIO, PLANTILLA_HTML, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, ID_SERVICIO)
VALUES ('PCADP', 'PDL', '<html>
<head>
<title>Citación audiencia</title>
</head>
<body>
  <font face="Arial" size="2">
    <p align="left">
     ciudadP, dia_cartaP de mes_cartaP de ano_cartaP 
    </p>
    <p align="left">
      senorDoctorRLegalP 
      dirigidoP
      direccionEnvio
      telefonoEnvio
      ciudadEnvio
    </p>
    <p align="right">
      radicadoP
    </p>
    <p align="justify">
      REFERENCIA: SOLICITUD DE CONCILIACION DE convocanteP PARA SOLUCIONAR LAS DIFERENCIAS SURGIDAS CON convocadoP.
    </p>
    <p align="justify">Apreciado(a) señor(a):</p>

		<p align="justify">Nos complace invitarle a una audiencia de conciliación, el fechaAudienciaP, a las horaAudienciaP en nuestras oficinas ubicadas en direccionSedeP.</p>

		<p align="justify">
		leyAsistenciaP
		</p>

    <p align="justify">
      Para nosotros es importante saber si recibieron esta comunicación, por lo cual les solicitamos que confirmen su asistencia al siguiente correo electrónico: emailConciliadorP. Por favor indique el número de caso.
    </p>
    <p align="justify">
      Para información adicional puede comunicarse al teléfono <!--número de teléfono-->
    </p>
    <p align="justify">Cordialmente,</p>
    <p align="justify">&#160;</p>
    <p align="left">
      conciliadorP
    </p>
    <p align="left">Conciliador</p>
    <p align="left">
      Registro registroConciliadorP
    </p>
    <p align="right">
      Caso codigoCaso
    </p>
  </font>
</body>
</html>', 'USER_SIMASC', SYSDATETIME(), 'ACT', 1);
--dominio: ya existe es el mismo de arbitraje, solo cambia el tipo de servicio (tipo_servicio)

--tags para la tabla valor_tag_plantilla

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('senorDoctorRLegalP', 42, 'USER_SIMASC', SYSDATETIME(), 'ACT', '(Señor, Doctor o Representante legal), trae el titulo que se le debe dar a la persona dependiendo el rol y el tipo de persona', 
'NATIVE: select  case  when p.tipo_persona = ''NAT'' and r.nombre = ''APODCTE'' or r.nombre = ''APODCDO'' then ''Doctor(a) <br/>''
					   when p.tipo_persona = ''JUR'' then ''Señor(a)<br/>Representante Legal de<br/>''
					   else ''Señor(a)<br/>''    
				 end  
				 from Persona p  left join ROL_PERSONA_CASO prpc on prpc.id_persona = p.id_persona 
								 left join ROL r on r.id_rol = prpc.id_rol
				 where prpc.id_caso=?1  
				 and prpc.estado_registro=''ACT''
				 and p.estado_registro = ''ACT''
				 and r.estado_registro = ''ACT''
				 and p.id_persona=?2 ');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('fechaAudienciaP', 42, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'fecha en que se hará la audiencia, fecha colsultada de la tabla audiencia del campo hora_inicio', 'NATIVE: SELECT FORMAT(au.hora_inicio,''dd/MM/yyyy'') from AUDIENCIA AU where AU.id_caso = ?1 and au.id_audiencia = ?3');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('horaAudienciaP', 42, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'hora en que se hará la audiencia, hora colsultada de la tabla audiencia del campo hora_inicio', 'NATIVE: SELECT FORMAT(au.hora_inicio,''HH:mm'') from AUDIENCIA AU where AU.id_caso = ?1 and au.id_audiencia = ?3');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('direccionSedeP', 42, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'direccion de la sede donde se hará la audiencia', 
'NATIVE: select se.direccion
from AGENDAMIENTO ag  
inner join audiencia au on ag.id_audiencia= au.id_audiencia  
inner join sala s on s.id_sala = ag.id_sala 
inner join sede se on se.id_sede=s.id_sede 
where au.id_caso= ?1
and ag.id_audiencia = ?3 		
and au.estado_registro = ''ACT'' 
and ag.estado_registro = ''ACT''
and se.estado_registro= ''ACT''
and s.estado_registro= ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('leyAsistenciaP', 42, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'parrafo donde se enuncia la Ley 640, mostrada solo cuando la citacion va dirigida al convocado o convocante', '
NATIVE: select   case when p.tipo_persona = ''NAT'' and (r.nombre = ''CONVOTE'' or r.nombre = ''CONVODO'') then ''Así mismo le recordamos que de conformidad con lo dispuesto en el parágrafo 2º del artículo 1º de la Ley 640 de 2001, deberá asistir  a la audiencia de conciliación y podrá hacerlo junto con su apoderado, de lo contrario su inasistencia puede ser considerada como indicio grave en contra de sus pretensiones o de sus excepciones de mérito en un eventual proceso judicial que verse sobre los mismos hechos, de acuerdo a lo establecido en el artículo 22 de la misma ley.''  
					  else ''''
				 end  
				 from Persona p  left join ROL_PERSONA_CASO prpc on prpc.id_persona = p.id_persona 
								 left join ROL r on r.id_rol = prpc.id_rol
				 where prpc.id_caso=?1  
				 and prpc.estado_registro=''ACT''
				 and p.estado_registro = ''ACT''
				 and r.estado_registro = ''ACT''
				 and p.id_persona=?2');
				 
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocanteP', 42, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocantes en una sola linea separados por comas', 'NATIVE: select stuff((select '','', concat(pe.primer_nombre_o_razon_social, '' '', pe.segundo_nombre, '' '', pe.primer_apellido, '' '', pe.segundo_apellido) 
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre = ''CONVOTE'' and estado <> ''INA'')
        FOR xml PATH ('''')), 1, 1, '''') AS arbitros
from caso c
where id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocadoP', 42, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocados en una sola linea separados por comas', 'NATIVE: select stuff((select '','', concat(pe.primer_nombre_o_razon_social, '' '', pe.segundo_nombre, '' '', pe.primer_apellido, '' '', pe.segundo_apellido) 
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre = ''CONVODO'' and estado <> ''INA'')
        FOR xml PATH ('''')), 1, 1, '''') AS arbitros
from caso c
where id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('conciliadorP', 42, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae el nombre completo del conciliador asignador al caso', 'NATIVE: select ISNULL(p.primer_nombre_o_razon_social,'''') +'' ''+ ISNULL(p.segundo_Nombre, '''') 
    +'' ''+ ISNULL(p.primer_Apellido,'''') +'' ''+ isnull(p.segundo_Apellido,'''')  
from rol_persona_caso rpc 
    inner join persona p on p.id_persona = rpc.id_persona
where id_rol in (select id_rol from tipo_de_servicio_rol where tipo_servicio = ''PDL'')
and rpc.estado not in (''REC'', ''INA'')
and id_caso = ?1
and rpc.estado_registro = ''ACT''
and p.estado_registro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION)
VALUES ('IPP', 42, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Identica si la plantilla lleva un tag de <dirigido>' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION)
VALUES ('IDA', 42, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Identica si la plantilla lleva un tag de <Audiencia>' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('dirigidoP', 42, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'nombre de la persona a la que se envia la carta', 'NATIVE: select concat(p.primer_nombre_o_razon_social,'' '', p.segundo_nombre, '' '', p.primer_apellido,'' '', p.segundo_apellido, ''<br/>'') from Persona p left join ROL_PERSONA_CASO prpc on prpc.id_persona = p.id_persona where prpc.id_caso=?1 AND prpc.estado_registro=''ACT'' and p.id_persona=?2' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('emailConciliadorP', 42, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'correo electronico del conciliador asignado a un caso', '
NATIVE: select TOP 1 ce.direccion
from rol_persona_caso rpc 
    inner join CORREO_ELECTRONICO ce on ce.id_persona = rpc.id_persona
where id_rol in (select id_rol from tipo_de_servicio_rol where tipo_servicio = ''PDL'')
and rpc.estado not in (''REC'', ''INA'')
and ce.tipo = ''PRI''
and id_caso = ?1
and rpc.estado_registro = ''ACT''
and ce.estado_registro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('registroConciliadorP', 42, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'registro del conciliador, se encuentra en la tabla persona', '
NATIVE: SELECT p.registro_conciliador FROM persona p 
INNER JOIN rol_persona_caso rpc
ON rpc.id_persona = p.id_persona
AND rpc.estado in ( ''ACE'', ''POR'' )
AND rpc.tipo_nombramiento = ''PRI''
INNER JOIN rol r 
ON rpc.id_rol = r.id_rol
AND r.id_rol in (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = ''PDL'' and estado_registro = ''ACT'')
WHERE rpc.id_caso = ?1
AND p.estado_registro = ''ACT''
AND rpc.estado_registro = ''ACT''
AND r.estado_registro = ''ACT'' ');

INSERT INTO PLANTILLA_CARTA ( NOMBRE, TIPO_SERVICIO, PLANTILLA_HTML, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO)
VALUES ('DEVODIN', 'PDL', '<html>

<head>
    <meta charset=utf-8 />
</head>

<body>
    <p align="left">
        ciudadP, dia_cartaP de mes_cartaP de ano_cartaP
    </p>
    <p align="left">
        Señor(a)
        <br/> dirigidoP
        <br/> direccionEnvio
        <br/> telefonoEnvio
        <br/> ciudadEnvio </p>
    <p> Reciba un cordial saludo del Centro de Arbitraje y Conciliación. </p>
    <p align="justify"> Le informamos que le será devuelta la suma de $valorDevolverP suma equivalente al porcentajeDevolucionP% del valor consignado
        por usted en razón del trámite de conciliación entre nombreCasoP, según decreto 1069 de 2015. </p>
    <p align="justify">
    Podrá reclamar la suma mencionada textoDiasHabilesP (numDiasHabilesP) días hábiles después de recibida esta comunicación,
        en cualquiera de la cajas de las Sedes Comerciales de la Cámara de Comercio de Bogotá. </p>
    <p align="justify"> La devolución se realizará a nombrePagadorP, debe presentar el formato original de devolución (que ustedes ya tienen),
        y el memorando que se adjunta al correo. </p>
    <p align="justify"> En caso de que no pueda asistir, puede enviar autorización con poder autentico ante notaría, fotocopia de la cedula y
        documento original de la persona que retira el dinero. </p>
    <p> Cordialmente </p>
    <p> personaQueGeneraCartaP </p>
</body>

</html>', 'USER_SIMASC', SYSDATETIME(), 'ACT');

INSERT INTO DOMINIO ( CODIGO, DOMINIO, NOMBRE, DESCRIPCION)
VALUES ( 'DEVODIN', 'NOMBRE_PLANTILLA_CARTA', 'Reliquidación', 'Plantilla de cartas para la notificación de devolucion de dinero por motivo de reliquidación')

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('nombreCasoP', 39, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae el nombre de un caso a partir del id del caso', 'NATIVE: select nombre from caso where id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('valorDevolverP', 39, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'cantidad de dinero que se debe regresar cuando se hace la reliquidacion de un caso', 'NATIVE: select TOP 1 isnull(valor, 0) 
from RELIQUIDACION 
where tipo = ''DEVOLUCI'' 
and id_caso = ?1
and motivo = (select estado_caso from caso where id_caso = ?1)
and estado_registro = ''ACT''
order by fecha_ultima_modificacion desc');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('numDiasHabilesP', 39, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'valor numerico de los dias habiles en los que puede ir a retirar el dinero a partir de la fecha de la carta', 'NATIVE: select valor_numerico from PARAMETROS_GENERALES where codigo = ''DHABDEV'' and tipo = ''DEVOLUCION'' and estado_registro = ''ACT''' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('textoDiasHabilesP', 39, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'valor en texto de los dias habiles en los que puede ir a retirar el dinero a partir de la fecha de la carta', 'NATIVE: select valor_texto from PARAMETROS_GENERALES where codigo = ''DHABDEV'' and tipo = ''DEVOLUCION'' and estado_registro = ''ACT''' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('nombrePagadorP', 39, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'nombre de la persona que pago el caso', 'NATIVE: select nombre_persona from pago_caso where id_caso = ?1' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('dirigidoP', 39, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'nombre de la persona a la que se envia la carta', 'NATIVE: select concat(p.primer_nombre_o_razon_social,'' '', p.segundo_nombre, '' '', p.primer_apellido,'' '', p.segundo_apellido,'' '') from Persona p left join ROL_PERSONA_CASO prpc on prpc.id_persona = p.id_persona where prpc.id_caso=?1 AND prpc.estado_registro=''ACT'' and p.id_persona=?2' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('porcentajeDevolucionP', 39, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Valor numérico del porcentaje de devolución de la reliquidación', 'NATIVE: select TOP 1 isnull(porcentaje_devolucion, 0) 
from RELIQUIDACION 
where tipo = ''DEVOLUCI'' 
and id_caso = ?1
and motivo = (select estado_caso from caso where id_caso = ?1)
and estado_registro = ''ACT''
order by fecha_ultima_modificacion desc' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION)
VALUES ('IPP', 39, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Identica si la plantilla lleva un tag de <dirigido>' );

-- %%%% INSERT DE LA PLANTILLA: CIERRE POR NO COMPETENCIA %%%%%%

INSERT INTO PLANTILLA_CARTA ( NOMBRE, TIPO_SERVICIO, PLANTILLA_HTML, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO)
VALUES ('CIENOCO', 'PDL', '<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title> cierre por no competencia</title>
</head>
<body>
  <font face="Arial" size="1">
	<p>ciudadP, dia_cartaP de mes_cartaP de ano_cartaP</p>
    <p align="left">       
		Señor(a)<br/>
		dirigidoP 
      <br/>
      direccionEnvio 
      <br/>
      telefonoEnvio
      <br/>
      ciudadEnvio
    </p>
    <p align="right">
      radicadoCarta 
    </p>
    <p align="justify">
      REFERENCIA: SOLICITUD DE CONCILIACION DE convocanteP PARA SOLUCIONAR LAS DIFERENCIAS SURGIDAS CON convocadoP
    </p>
    <p align="justify">Apreciado(a) señor(a):</p>
    <p align="justify">
		textoNoCompetenciaP
    </p>   
    <p align="justify">Cordialmente,</p>
    <p align="justify">&#160;</p>
    <p align="left">
      conciliadorP
    </p>
    <p align="left">Conciliador</p>
    <p align="left">
      Registro registroConciliadorP
    </p>
    <p align="right">
      Caso codigoCasoP
    </p>
  </font>
</body>
</html>', 'USER_SIMASC', SYSDATETIME(), 'ACT');

INSERT INTO DOMINIO ( CODIGO, DOMINIO, NOMBRE, DESCRIPCION)
VALUES ( 'CIENOCO', 'NOMBRE_PLANTILLA_CARTA', 'Cierre de caso', 'Plantilla de cartas para la notificar el cierre de un caso')

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocanteP', 41, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocantes en una sola linea separados por comas', 'NATIVE: select stuff((select '','', concat(pe.primer_nombre_o_razon_social, '' '', pe.segundo_nombre, '' '', pe.primer_apellido, '' '', pe.segundo_apellido) 
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre = ''CONVOTE'' and estado <> ''INA'')
        FOR xml PATH ('''')), 1, 1, '''') AS arbitros
from caso c
where id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocadoP', 41, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocados en una sola linea separados por comas', 'NATIVE: select stuff((select '','', concat(pe.primer_nombre_o_razon_social, '' '', pe.segundo_nombre, '' '', pe.primer_apellido, '' '', pe.segundo_apellido) 
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre = ''CONVODO'' and estado <> ''INA'')
        FOR xml PATH ('''')), 1, 1, '''') AS arbitros
from caso c
where id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('textoNoCompetenciaP', 41, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'las observaciones que se pusieron cuando se cerro el caso', 'NATIVE: select observaciones from EVENTO where tipo_evento = ''FALTCOMP'' and id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('conciliadorP', 41, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae el nombre completo del conciliador asignador al caso', 'NATIVE: select ISNULL(p.primer_nombre_o_razon_social,'''') +'' ''+ ISNULL(p.segundo_Nombre, '''') 
    +'' ''+ ISNULL(p.primer_Apellido,'''') +'' ''+ isnull(p.segundo_Apellido,'''')  
from rol_persona_caso rpc 
    inner join persona p on p.id_persona = rpc.id_persona
where id_rol in (select id_rol from tipo_de_servicio_rol where tipo_servicio = ''PDL'')
and rpc.estado not in (''REC'', ''INA'')
and id_caso = ?1
and rpc.estado_registro = ''ACT''
and p.estado_registro = ''ACT''');


INSERT INTO DOMINIO ( CODIGO, DOMINIO, NOMBRE, DESCRIPCION) VALUES ( 'TERC', 'TIPO_CORREO_ELECTRONICO', 'Correo terciario', 'Correo terciario')

---ojo falta registroP (del conciliador)
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('registroConciliadorP', 41, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'registro del conciliador, se encuentra en la tabla persona', '
NATIVE: SELECT p.registro_conciliador FROM persona p 
INNER JOIN rol_persona_caso rpc
ON rpc.id_persona = p.id_persona
AND rpc.estado in ( ''ACE'', ''POR'' )
AND rpc.tipo_nombramiento = ''PRI''
INNER JOIN rol r 
ON rpc.id_rol = r.id_rol
AND r.id_rol in (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = ''PDL'' and estado_registro = ''ACT'')
WHERE rpc.id_caso = ?1
AND p.estado_registro = ''ACT''
AND rpc.estado_registro = ''ACT''
AND r.estado_registro = ''ACT'' ');

-- CON-F-065 seguridad
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF065ProgramarAudiencia';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF065AsignacionTurnosJornada';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF065ProgramarAudiencia';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF065AsignacionTurnosJornada';

INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('CONF065AsignacionTurnosJornada', 'Asignar turnos para casos', 'app/Conciliacion/CONF065','PDL','ANGULAR','jornadas','SIMASC_USER',GETDATE(),'ACT')
INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('CONF065ProgramarAudiencia', 'Programar audiencias', 'app/Conciliacion/CONF065','PDL','ANGULAR','CONF065AsignacionTurnosJornada','SIMASC_USER',GETDATE(),'ACT')

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF065AsignacionTurnosJornada',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'SECCON', 'JEFECON');	
			
insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF065ProgramarAudiencia',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'SECCON', 'JEFECON');

/** Funcionalidad para el CON-C-040 */			
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC040ReporteCasosPorApoderado';
DELETE FUNCIONALIDAD WHERE nombre = 'CONC040ReporteCasosPorApoderado';
insert into FUNCIONALIDAD values ('CONC040ReporteCasosPorApoderado', 'Reporte de casos por apoderado', 'app/Conciliacion/CONC040','PDL','ANGULAR','reportesConc','SIMASC_USER',SYSDATETIME(),'ACT');
insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONC040ReporteCasosPorApoderado',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ( 'ACO', 'SECCON', 'JEFECON' );


--Inserts de la plantilla PCPAU de TIPO_SERVICIO = 'PDL'

INSERT INTO PLANTILLA_CARTA ( NOMBRE, TIPO_SERVICIO, PLANTILLA_HTML, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, ID_SERVICIO)
VALUES ('PCPAU', 'PDL', '<html>
<head>
<title>Citación audiencia</title>
</head>
<body>
  <font face="Arial" size="1">
    <p align="left">
     ciudadP, dia_cartaP de mes_cartaP de ano_cartaP 
    </p>
    <p align="left">
	
      senorDoctorRLegalP 
      dirigidoP
      direccionEnvio
      telefonoEnvio
      ciudadEnvio
    </p>
    <p align="right">
      radicadoP
    </p>
    <p align="justify">
      REFERENCIA: SOLICITUD DE CONCILIACION DE convocanteP PARA SOLUCIONAR LAS DIFERENCIAS SURGIDAS CON convocadoP.
    </p>
    <p align="justify">Apreciado(a) señor(a):</p>
			
		<p align="justify">
		  PCPAUparrafo1P
		</p>

		<p align="justify">
		  El nombreCentroP, se creó para ayudar a que las personas resuelvan LEGALMENTE sus problemas sin necesidad de ir a un juzgado. Las personas que vienen al Centro de Conciliación arreglan rápidamente sus conflictos, los solucionan pacíficamente y no se ocasionan más problemas de los que ya tienen.
		</p>
		<p align="justify">
		  Queremos comentarle que para este Centro, es un honor prestarle nuestros servicios. Por lo tanto, atentamente lo invitamos a una audiencia de conciliación en nuestras oficinas ubicada en la direccionSedeP, nombreSedeP el fechaAudienciaP a las horaAudienciaP.
		</p>
		
		<p align="justify">leyAsistenciaP</p>
	
		<p align="justify">PCPAUparrafo4P</p>

    <p align="justify">
      Para nosotros es importante saber si recibieron esta comunicación, por lo cual les solicitamos que confirmen su asistencia al siguiente correo electrónico: emailConciliadorP. Por favor indique el número de caso.
    </p>
    <p align="justify">
      Para información adicional puede comunicarse al teléfono: <!--número de teléfono-->
    </p>
    <p align="justify">Cordialmente,</p>
    <p align="justify">&#160;</p>
    <p align="left">
      conciliadorP
    </p>
    <p align="left">Conciliador</p>
    <p align="left">
      Registro registroConciliadorP
    </p>
    <p align="right">
      Caso codigoCaso
    </p>
  </font>
</body>
</html>','USER_SIMASC', SYSDATETIME(), 'ACT', 1);

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('senorDoctorRLegalP', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', '(Señor, Doctor o Representante legal), trae el titulo que se le debe dar a la persona dependiendo el rol y el tipo de persona', 
'NATIVE: select  case  when p.tipo_persona = ''NAT'' and r.nombre = ''APODCTE'' or r.nombre = ''APODCDO'' then ''Doctor(a)<br/>''
					   when p.tipo_persona = ''JUR'' then ''Señor(a)<br/>Representante Legal de<br/>''
					   else ''Señor(a)<br/>''    
				 end  
				 from Persona p  left join ROL_PERSONA_CASO prpc on prpc.id_persona = p.id_persona 
								 left join ROL r on r.id_rol = prpc.id_rol
				 where prpc.id_caso=?1  
				 and prpc.estado_registro=''ACT''
				 and p.estado_registro = ''ACT''
				 and r.estado_registro = ''ACT''
				 and p.id_persona=?2 ');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocanteP', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocantes en una sola linea separados por comas', 'NATIVE: select stuff((select '','', concat(pe.primer_nombre_o_razon_social, '' '', pe.segundo_nombre, '' '', pe.primer_apellido, '' '', pe.segundo_apellido) 
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre = ''CONVOTE'' and estado <> ''INA'')
        FOR xml PATH ('''')), 1, 1, '''') AS arbitros
from caso c
where id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocadoP', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocados en una sola linea separados por comas', 'NATIVE: select stuff((select '','', concat(pe.primer_nombre_o_razon_social, '' '', pe.segundo_nombre, '' '', pe.primer_apellido, '' '', pe.segundo_apellido) 
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre = ''CONVODO'' and estado <> ''INA'')
        FOR xml PATH ('''')), 1, 1, '''') AS arbitros
from caso c
where id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('conciliadorP', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae el nombre completo del conciliador asignador al caso', 'NATIVE: select ISNULL(p.primer_nombre_o_razon_social,'''') +'' ''+ ISNULL(p.segundo_Nombre, '''') 
    +'' ''+ ISNULL(p.primer_Apellido,'''') +'' ''+ isnull(p.segundo_Apellido,'''')  
from rol_persona_caso rpc 
    inner join persona p on p.id_persona = rpc.id_persona
where id_rol in (select id_rol from tipo_de_servicio_rol where tipo_servicio = ''PDL'')
and rpc.estado not in (''REC'', ''INA'')
and id_caso = ?1
and rpc.estado_registro = ''ACT''
and p.estado_registro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION)
VALUES ('IPP', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Identica si la plantilla necesita el id_persona con ?2 ' );


INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION)
VALUES ('IDA', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Identica si la plantilla necesita el id_audiencia con ?3' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('dirigidoP', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'nombre de la persona a la que se envia la carta', 'NATIVE: select concat(p.primer_nombre_o_razon_social,'' '', p.segundo_nombre, '' '', p.primer_apellido,'' '', p.segundo_apellido,''<br/>'') from Persona p left join ROL_PERSONA_CASO prpc on prpc.id_persona = p.id_persona where prpc.id_caso=?1 AND prpc.estado_registro=''ACT'' and p.id_persona=?2' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('fechaAudienciaP', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'fecha en que se hará la audiencia, fecha colsultada de la tabla audiencia del campo hora_inicio', 'NATIVE: SELECT FORMAT(au.hora_inicio,''dd/MM/yyyy'') from AUDIENCIA AU where AU.id_caso = ?1 and au.id_audiencia = ?3');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('horaAudienciaP', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'hora en que se hará la audiencia, hora colsultada de la tabla audiencia del campo hora_inicio', 'NATIVE: SELECT FORMAT(au.hora_inicio,''HH:mm'') from AUDIENCIA AU where AU.id_caso = ?1 and au.id_audiencia = ?3');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('direccionSedeP', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'direccion de la sede donde se hará la audiencia', 
'NATIVE: select se.direccion
from AGENDAMIENTO ag  
inner join audiencia au on ag.id_audiencia= au.id_audiencia  
inner join sala s on s.id_sala = ag.id_sala 
inner join sede se on se.id_sede=s.id_sede 
where au.id_caso= ?1
and ag.id_audiencia = ?3 		
and au.estado_registro = ''ACT'' 
and ag.estado_registro = ''ACT''
and se.estado_registro= ''ACT''
and s.estado_registro= ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('leyAsistenciaP', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'parrafo donde se enuncia la Ley 640, mostrada solo cuando la citacion va dirigida al convocado o convocante', '
NATIVE: select   case when p.tipo_persona = ''NAT'' and (r.nombre = ''CONVOTE'' or r.nombre = ''CONVODO'') then ''Así mismo le recordamos que de conformidad con lo dispuesto en el parágrafo 2º del artículo 1º de la Ley 640 de 2001, deberá asistir  a la audiencia de conciliación y podrá hacerlo junto con su apoderado, de lo contrario su inasistencia puede ser considerada como indicio grave en contra de sus pretensiones o de sus excepciones de mérito en un eventual proceso judicial que verse sobre los mismos hechos, de acuerdo a lo establecido en el artículo 22 de la misma ley.''  
					  else ''''
				 end  
				 from Persona p  left join ROL_PERSONA_CASO prpc on prpc.id_persona = p.id_persona 
								 left join ROL r on r.id_rol = prpc.id_rol
				 where prpc.id_caso=?1  
				 and prpc.estado_registro=''ACT''
				 and p.estado_registro = ''ACT''
				 and r.estado_registro = ''ACT''
				 and p.id_persona=?2');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('emailConciliadorP', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'correo electronico del conciliador asignado a un caso', '
NATIVE: select TOP 1 ce.direccion
from rol_persona_caso rpc 
    inner join CORREO_ELECTRONICO ce on ce.id_persona = rpc.id_persona
where id_rol in (select id_rol from tipo_de_servicio_rol where tipo_servicio = ''PDL'')
and rpc.estado not in (''REC'', ''INA'')
and ce.tipo = ''PRI''
and id_caso = ?1
and rpc.estado_registro = ''ACT''
and ce.estado_registro = ''ACT''');

--inserts propios de la plantilla 

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('PCPAUparrafo1P', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Primer parrafo de la carta de primera audiencia, cambia de acuerdo a los roles', '
NATIVE: select  case	when r.nombre = ''CONVOTE'' or r.nombre = ''APODCTE'' then ''Hemos recibido su comunicación en la cual nos solicita la citación de convocadoP a una Audiencia de Conciliación a fin de solucionar las diferencias presentadas con ocasión de convocanteP.''  
						when r.nombre = ''CONVODO'' or r.nombre = ''APODCDO'' then ''Hemos recibido una solicitud de conciliación, en la que convocanteP nos pide la citación de convocadoP a una Audiencia de Conciliación a fin de solucionar las diferencias presentadas con ocasión de casoHechosP.''
						else ''''   
				end  
		from ROL r INNER JOIN ROL_PERSONA_CASO rpc on rpc.id_rol = r.id_rol 
				   INNER JOIN PERSONA P on p.id_persona = rpc.id_persona
		where rpc.id_caso= ?1 
		and rpc.estado_registro=''ACT''
		and p.estado_registro = ''ACT''
		and r.estado_registro = ''ACT''
		and p.id_persona = ?2');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('casoHechosP', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Hechos del caso, consultada de la tabla ''caso'' el campo ''hechos'' ', 'select c.hechos from Caso c where c.idCaso = :idCaso and c.estadoRegistro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('nombreCentroP', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Nombre del centro donde se encuentra el caso ', 'NATIVE: select ce.nombre from caso c left outer join sede s on s.id_sede = c.id_sede left outer join centro ce on ce.id_centro = s.id_centro where c.id_caso = ?1 and c.estado_registro = ''ACT'' and s.estado_registro = ''ACT'' and ce.estado_registro = ''ACT'' ');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('PCPAUparrafo4P', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Cuarto parrafo de la carta de primera audiencia, cambia de acuerdo a los roles', '
NATIVE: select  case when r.nombre = ''CONVOTE'' or r.nombre = ''APODCTE'' then ''Le informamos que habrá lugar a la reliquidación del servicio, por concepto de gastos administrativos u honorarios del conciliador, cuando del estudio de la solicitud, en el desarrollo del trámite conciliatorio o en el acuerdo, se determine que el valor de las diferencias objeto de conflicto, es superior al establecido inicialmente como valor de la solicitud.''  
					 else ''''   
				end  
				from ROL r INNER JOIN ROL_PERSONA_CASO rpc on rpc.id_rol = r.id_rol 
					INNER JOIN PERSONA P on p.id_persona = rpc.id_persona
				where rpc.id_caso= ?1
				and rpc.estado_registro=''ACT''
				and p.estado_registro = ''ACT''
				and r.estado_registro = ''ACT''
				and p.id_persona = ?2 ');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('registroConciliadorP', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'registro del conciliador, se encuentra en la tabla persona', '
NATIVE: SELECT p.registro_conciliador FROM persona p 
INNER JOIN rol_persona_caso rpc
ON rpc.id_persona = p.id_persona
AND rpc.estado in ( ''ACE'', ''POR'' )
AND rpc.tipo_nombramiento = ''PRI''
INNER JOIN rol r 
ON rpc.id_rol = r.id_rol
AND r.id_rol in (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = ''PDL'' and estado_registro = ''ACT'')
WHERE rpc.id_caso = ?1
AND p.estado_registro = ''ACT''
AND rpc.estado_registro = ''ACT''
AND r.estado_registro = ''ACT'' ');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('nombreSedeP', 53, 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Nombre de la sede donde se realizara la audiencia', 
'NATIVE: select se.nombre
from AGENDAMIENTO ag  
inner join audiencia au on ag.id_audiencia= au.id_audiencia  
inner join sala s on s.id_sala = ag.id_sala 
inner join sede se on se.id_sede=s.id_sede 
where au.id_caso= ?1
and ag.id_audiencia = ?3 		
and au.estado_registro = ''ACT'' 
and ag.estado_registro = ''ACT''
and se.estado_registro= ''ACT''
and s.estado_registro= ''ACT'' ');

-- CON-F_067
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF067CapturaResultado';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF067CapturaResultado';

INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('CONF067CapturaResultado', 'Capturar resultado caso', 'app/Conciliacion/CONF067','PDL','ANGULAR','jornadas','SIMASC_USER',GETDATE(),'ACT')

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF067CapturaResultado',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'SECCON');
			
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('DD37','TIPO_DOCUMENTO_DIG','Respuesta ministerio','Documento devuelto por el servicio del ministerio',null,null);
		
		
-- CON-F-066

INSERT DOMINIO VALUES('CCPM','TIPO_EVENTO','Cambio de conciliador principal forma manual','Cambio de conciliador principal forma manual',null,null);

INSERT DOMINIO VALUES('CCPA','TIPO_EVENTO','Cambio de conciliador principal forma automática','Cambio de conciliador principal forma automática',null,null);


 update parametros_generales set valor_numerico=781242 where codigo='SMLMV'


 update PARAMETROS_GENERALES set nombre='Tope Pretenciones en Salarios Minimos Vigentes' where codigo='VLRPRET'

 update PARAMETROS_GENERALES set valor_numerico=100 where codigo='VLRPRET'
 

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF066CambiarConciliador';
DELETE FUNCIONALIDAD where nombre='CONF066CambiarConciliador';
insert into FUNCIONALIDAD values ('CONF066CambiarConciliador', 'Cambiar Conciliador','app/Conciliacion/CONF066','PDL', 'ANGULAR','CONF086FichaTecnicaDatosBasicosCaso','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF066CambiarConciliador', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON');
			
			

-- CON-F-086

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF086ButtonCambiarConciliador';
DELETE FUNCIONALIDAD where nombre='CONF086ButtonCambiarConciliador';
insert into FUNCIONALIDAD values ('CONF086ButtonCambiarConciliador', 'Cambiar Conciliador','app/Conciliacion/CONF086','PDL', 'ANGULAR','CONF086FichaTecnicaDatosBasicosCaso','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF086ButtonCambiarConciliador', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO', 'JEFECON');


DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF086FichaTecnicaDatosBasicosCaso';
DELETE FUNCIONALIDAD where nombre='CONF086FichaTecnicaDatosBasicosCaso';
insert into FUNCIONALIDAD values ('CONF086FichaTecnicaDatosBasicosCaso', 'Cambiar Conciliador','app/Conciliacion/CONF086','PDL', 'ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF086FichaTecnicaDatosBasicosCaso', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('CON', 'ACO', 'SECCON', 'JEFECON');


			
-- CON-F-092

			
insert into dominio values('DD20','TIPO_DOCUMENTO_DIG','Constancia','Documento Constancia Solicitud Prorroga Cierre de Caso',NULL,NULL);

			
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF092SolicitudProrrogaCierreCaso';
DELETE FUNCIONALIDAD where nombre='CONF092SolicitudProrrogaCierreCaso';
insert into FUNCIONALIDAD values ('CONF092SolicitudProrrogaCierreCaso', 'Solicitud Prorroga Cierre de Caso','app/Conciliacion/CONF092','PDL', 'ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF092SolicitudProrrogaCierreCaso', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('CON');
			
--CON-C-051
insert into DOMINIO (codigo, dominio, nombre,descripcion)
values ('SEGCASO','TIPO_LLAMADA','Seguimiento del caso','Seguimiento del caso en estado Acuerdo');

-- CON-F-116
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF116ReversarResultadoCasoJornada';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF116ReversarResultadoCasoJornada';

INSERT INTO [dbo].[FUNCIONALIDAD] VALUES
     ('CONF116ReversarResultadoCasoJornada', 'Reversar resultado caso', 'app/Conciliacion/CONF067','PDL','ANGULAR','jornadas','SIMASC_USER',GETDATE(),'ACT')

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF116ReversarResultadoCasoJornada',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('ACO', 'SECCON');
-- CON-F-117




DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF117ReversarResultadoAudiencia';
DELETE FUNCIONALIDAD where nombre='CONF117ReversarResultadoAudiencia';
insert into FUNCIONALIDAD values ('CONF117ReversarResultadoAudiencia', 'Reversar Resultado Audiencia','app/Conciliacion/CONF117','PDL', 'ANGULAR','CONF086FichaTecnicaDatosBasicosCaso','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF117ReversarResultadoAudiencia', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON','AUX','SECCON');
			
INSERT DOMINIO VALUES('RRA','TIPO_EVENTO','Reversar resultado','Reversar resultado de una Audiencia',null,null);
INSERT DOMINIO VALUES('RRC','TIPO_EVENTO','Reversar resultado de Caso','Reversar resultado de un Caso',null,null);
 insert into dominio values('DD39','TIPO_DOCUMENTO_DIG','Constancia de Resultado Audiencia','Documento constancia del resultado de la audiencia',NULL,NULL);

 -- Cambio código Constancia 
 update dominio set codigo='DD38' WHERE codigo='DD20'
 
 -- CON-C-044
delete from CLASIFICADOR_DOMINIO where codigo_clasificador='CLADEDIN'
delete from DOMINIO where codigo='CLADEDIN'

insert into DOMINIO (codigo,dominio,nombre,descripcion)
values ('CLADEDIN','CLASIFICADOR_RESULTADO_CASO','Devolucion de dinero','Clasificador para la devolucion de dinero');

insert into CLASIFICADOR_DOMINIO
values('CCN','RESULTADO_CASO_CONCILIACION','CLADEDIN','CLASIFICADOR_RESULTADO_CASO','USER_SIMASC',GETDATE(),'ACT'),
	  ('CDA','RESULTADO_CASO_CONCILIACION','CLADEDIN','CLASIFICADOR_RESULTADO_CASO','USER_SIMASC',GETDATE(),'ACT'),
	  ('FALTCOMP','RESULTADO_CASO_CONCILIACION','CLADEDIN','CLASIFICADOR_RESULTADO_CASO','USER_SIMASC',GETDATE(),'ACT')
 
 
 -- CONF-102 
-- permisos

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF102ListaCasosSinCerrar';
DELETE FUNCIONALIDAD where nombre='CONF102ListaCasosSinCerrar';
insert into FUNCIONALIDAD values ('CONF102ListaCasosSinCerrar', 'Casos Sin Cerrar','app/Conciliacion/CONF102','PDL', 'ANGULAR',null,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF102ListaCasosSinCerrar', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('CON');
			
			
-- CON-F-075
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF075ControlAsistencia';
DELETE FUNCIONALIDAD where nombre='CONF075ControlAsistencia';
insert into FUNCIONALIDAD values ('CONF075ControlAsistencia', 'Control asistencia audiencias','app/Conciliacion/CONF075','PDL', 'ANGULAR',null,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF075ControlAsistencia', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON', 'JEFECON');

			
			
-- CON-C-048
			
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC048ReportePartesDeCaso';
DELETE FUNCIONALIDAD where nombre='CONC048ReportePartesDeCaso';
insert into FUNCIONALIDAD values ('CONC048ReportePartesDeCaso', 'Reporte Partes de Caso','app/Conciliacion/CONF102','PDL', 'ANGULAR','reportesConc','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC048ReportePartesDeCaso', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON','SECCON');

-- CON-F-091

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF091FichaTecnicaConsultarRutaCasoConciliacion';
DELETE FUNCIONALIDAD where nombre='CONF091FichaTecnicaConsultarRutaCasoConciliacion';
insert into FUNCIONALIDAD values ('CONF091FichaTecnicaConsultarRutaCasoConciliacion', 'Ficha Tecnica Ruta Caso Conciliacion','app/Conciliacion/CONF091','PDL', 'ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF091FichaTecnicaConsultarRutaCasoConciliacion', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','CON','SECCON','DCA','SUBDICAC');

/**
 * Parametros de servicio de numero de dias de cierre de caso y numero de audiencias para recobro
 */			
insert parametro_de_servicio (nombre, tipo_parametro, id_servicio, valor, descripcion,
							 id_usuario_modificacion, fecha_ultima_modificacion, estado_registro )
values ( 'NUMERO_DIAS_CIERRE_CASO', 'CIERRECA', 1, 90, 'Numero de dias de vigencia de un caso de conciliacion a partir de la fecha de radicacion',
		'USER_SIMASC', getdate(), 'ACT' )
insert parametro_de_servicio (nombre, tipo_parametro, id_servicio, valor, descripcion,
							 id_usuario_modificacion, fecha_ultima_modificacion, estado_registro )
values ( 'NUMERO_DIAS_CIERRE_CASO', 'CIERRECA', 8, 90, 'Numero de dias de vigencia de un caso de conciliacion a partir de la fecha de radicacion',
		'USER_SIMASC', getdate(), 'ACT' )
insert parametro_de_servicio (nombre, tipo_parametro, id_servicio, valor, descripcion,
							 id_usuario_modificacion, fecha_ultima_modificacion, estado_registro )
values ( 'NUMERO_AUDIENCIAS_PARA_RECOBRO', 'AUDI_REC', 1, 4, 'Este número menos 1 corresponde al número de audiencias que se pueden programar en un caso de conciliación de trámite ordinario sin hacer un recobro al cliente',
		'USER_SIMASC', getdate(), 'ACT' )
insert parametro_de_servicio (nombre, tipo_parametro, id_servicio, valor, descripcion,
							 id_usuario_modificacion, fecha_ultima_modificacion, estado_registro )
values ( 'NUMERO_AUDIENCIAS_PARA_RECOBRO', 'AUDI_REC', 8, 4, 'Este número menos 1 corresponde al número de audiencias que se pueden programar en un caso de conciliación de trámite ordinario sin hacer un recobro al cliente',
		'USER_SIMASC', getdate(), 'ACT' )

/* caso de uso  CONF-090 Consultar Audiencias */
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF106ProgramarAudiencia';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF106ProgramarAudiencia';
INSERT INTO FUNCIONALIDAD VALUES ('CONF106ProgramarAudiencia', 'Programar audiencia', 'app/Conciliacion/CONF106','PDL','ANGULAR','CONF090tabAudiencias','SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF106ProgramarAudiencia',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ( 'CON','SECCON', 'ACO', 'JEFECON' );
			
-- Permiso Submenú Convenios

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'convenios';
DELETE FUNCIONALIDAD where nombre='convenios';
insert into FUNCIONALIDAD values ('convenios', 'Convenios','app/Conciliacion/','PDL', 'ANGULAR',null,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'convenios', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON','RCO');

			

-- Permisos CON-C-011

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC011ConsultaDeConvenios';
DELETE FUNCIONALIDAD where nombre='CONC011ConsultaDeConvenios';
insert into FUNCIONALIDAD values ('CONC011ConsultaDeConvenios', 'Consulta de Convenio','app/Conciliacion/CONC011','PDL', 'ANGULAR','convenios','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC011ConsultaDeConvenios', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON');
			
			
-- CON-F-072
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('CAPACITA','TIPO_EVENTO_CCB','Capacitación','Capacitación',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('INCAMEDI','TIPO_EVENTO_CCB','Incapacidad médica','Incapacidad médica',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('VACAS','TIPO_EVENTO_CCB','Vacaciones','Vacaciones',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('OTROS','TIPO_EVENTO_CCB','Otros','Otros',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('COMITE','TIPO_EVENTO_CCB','Comité','Comité',null,null);

INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('PROGRAMA','ESTADO_EVENTO_CCB','Programado','Estado de un evento programado',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('REALIZA','ESTADO_EVENTO_CCB','Realizado','Estado de un evento realizado',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('CANCELA','ESTADO_EVENTO_CCB','Cancelado','Estado de un evento cancelado',null,null);

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF072CrearEvento';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF072ModificarEvento';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF072CancelarEvento';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF072RegistrarResultado';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF072ConsultarEventosCcb';

DELETE FUNCIONALIDAD where nombre='CONF072CrearEvento';
DELETE FUNCIONALIDAD where nombre='CONF072ModificarEvento';
DELETE FUNCIONALIDAD where nombre='CONF072CancelarEvento';
DELETE FUNCIONALIDAD where nombre='CONF072RegistrarResultado';
DELETE FUNCIONALIDAD where nombre='CONF072ConsultarEventosCcb';

insert into FUNCIONALIDAD values ('CONF072ConsultarEventosCcb', 'Programación de eventos','app/Conciliacion/CONF072','PDL', 'ANGULAR',null,'SIMASC_USER', SYSDATETIME(), 'ACT');
insert into FUNCIONALIDAD values ('CONF072CrearEvento', 'Programación de eventos','app/Conciliacion/CONF072','PDL', 'ANGULAR','CONF072ConsultarEventosCcb','SIMASC_USER', SYSDATETIME(), 'ACT');
insert into FUNCIONALIDAD values ('CONF072ModificarEvento', 'Programación de eventos','app/Conciliacion/CONF072','PDL', 'ANGULAR','CONF072ConsultarEventosCcb','SIMASC_USER', SYSDATETIME(), 'ACT');
insert into FUNCIONALIDAD values ('CONF072CancelarEvento', 'Programación de eventos','app/Conciliacion/CONF072','PDL', 'ANGULAR','CONF072ConsultarEventosCcb','SIMASC_USER', SYSDATETIME(), 'ACT');
insert into FUNCIONALIDAD values ('CONF072RegistrarResultado', 'Programación de eventos','app/Conciliacion/CONF072','PDL', 'ANGULAR','CONF072ConsultarEventosCcb','SIMASC_USER', SYSDATETIME(), 'ACT');

INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF072ConsultarEventosCcb', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON');

INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF072CrearEvento', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO');

INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF072ModificarEvento', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF072CancelarEvento', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF072RegistrarResultado', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO');			
			
-- Permisos CON-C-016

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC016NuevoContratoConvenio';
DELETE FUNCIONALIDAD where nombre='CONC016NuevoContratoConvenio';
insert into FUNCIONALIDAD values ('CONC016NuevoContratoConvenio', 'Nuevo Contrato Convenio','app/Conciliacion/CONC016','PDL', 'ANGULAR','convenios','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC016NuevoContratoConvenio', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON');

-- Documento Contrato de un Convenio CON-C-016

 insert into dominio values('DD40','TIPO_DOCUMENTO_DIG','Contrato','Contrato de un convenio',NULL,NULL);
 
-- correccion de CON-C-069
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF069CargueCasos';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF069CargueCasos';

INSERT INTO [dbo].[FUNCIONALIDAD]
     VALUES
     ('CONF069CargueCasos', 'Cargar casos a partir de archivos', 'app/Conciliacion/CONF069','PDL','ANGULAR',NULL,'SIMASC_USER',GETDATE(),'ACT')
GO

insert into FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF069CargueCasos',r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio 
			in ('ROL_PERSONA') 
			and d.codigo not IN ('SECCON', 'JEFECON', 'APOCON', 'ACO');
			
			
INSERT INTO PARAMETROS_GENERALES (codigo,tipo,nombre,valor_numerico,valor_texto,valor_fecha,valor_booleano,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
VALUES ('MCON_REP','MANEJO_CONCURRENCIA_REPARTO','Manejo de concurrencia en reparto',0,null,null,null,'Registro utilizado para manejar la concurrencia en el proceso de reparto','USUARIO_SIMASC','2018-03-06 00:00:00.000','ACT');

	--permisos CON-C-1003	
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC1003ConsultaAsesorias';
DELETE FUNCIONALIDAD where nombre='CONC1003ConsultaAsesorias';
insert into FUNCIONALIDAD values ('CONC1003ConsultaAsesorias', 'Asesorías','app/Conciliacion/CONC1003','PDL', 'ANGULAR',null,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC1003ConsultaAsesorias', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','SECCON','JEFECON');
			
--subpermiso link asesoria.
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC1003LinkdetalleAsesoria';
DELETE FUNCIONALIDAD where nombre='CONC1003LinkdetalleAsesoria';
insert into FUNCIONALIDAD values ('CONC1003LinkdetalleAsesoria', 'link detalle Asesorías','app/Conciliacion/CONC1003','PDL', 'ANGULAR',null,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC1003LinkdetalleAsesoria', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO');

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC1003noLinkdetalleAsesoria';
DELETE FUNCIONALIDAD where nombre='CONC1003noLinkdetalleAsesoria';
insert into FUNCIONALIDAD values ('CONC1003noLinkdetalleAsesoria', 'link detalle Asesorías','app/Conciliacion/CONC1003','PDL', 'ANGULAR',null,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC1003noLinkdetalleAsesoria', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','JEFECON');

--Permisos CON-C-006
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC006nuevaAsesoria';
DELETE FUNCIONALIDAD where nombre='CONC006nuevaAsesoria';
insert into FUNCIONALIDAD values ('CONC006nuevaAsesoria', 'link detalle Asesorías','app/Conciliacion/CONC006','PDL', 'ANGULAR',null,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC006nuevaAsesoria', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO');
	
-- CON-C-025
insert into dominio values ('ASISTE', 'ESTADO_ASISTENCIA_EVENTO', 'Asistió al evento', 'Asistió al evento', null, null)
insert into dominio values ('NOASISTE', 'ESTADO_ASISTENCIA_EVENTO', 'No asistió al evento', 'No asistió al evento', null, null)

-- CON-C-013 Permisos

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC013CrearConveniosConciliacion';
DELETE FUNCIONALIDAD where nombre='CONC013CrearConveniosConciliacion';
insert into FUNCIONALIDAD values ('CONC013CrearConveniosConciliacion', 'Crear Convenios de Conciliacion','app/Conciliacion/CONC013','PDL', 'ANGULAR','convenios','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC013CrearConveniosConciliacion', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON');
			
			
-- CON-F-110 Adicionar Resultado Audiencia
insert into dominio values ('INASIS', 'TIPO_INASISTENCIA', 'Inasistencia', 'Inasistencia de audiencia', null, null)
insert into dominio values ('IMPOSI', 'TIPO_INASISTENCIA', 'Imposibilidad', 'Imposibilidad de audiencia', null, null)

--CON-F-110 Excusa Resultado Inasistencia
insert into dominio values ('DD42', 'TIPO_DOCUMENTO_DIG', 'Excusa', 'Excusa de un resultado tipo Inasistencia', null, null)

--PERMISOS CON-F-110
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF110RealizarAudiencia';
DELETE FUNCIONALIDAD where nombre='CONF110RealizarAudiencia';
insert into FUNCIONALIDAD values ('CONF110RealizarAudiencia', 'link Realización de audiencia','/app/Conciliacion/CONF110','PDL', 'ANGULAR','CONF090tabAudiencias','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF110RealizarAudiencia', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('CON');
			
--PERMISOS (Adicionar resultado a la Audiencia)
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF110AdicionResultado';
DELETE FUNCIONALIDAD where nombre='CONF110AdicionResultado';
insert into FUNCIONALIDAD values ('CONF110AdicionResultado', 'Adicionar resultado a la audiencia','/app/Conciliacion/CONF110','PDL', 'ANGULAR','CONF110RealizarAudiencia','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF110AdicionResultado', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('CON');
			
-- CON-F-110 DOMINIO RESULTADOS_AUDIENCIA Cancelada
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('11','RESULTADOS_AUDIENCIA','Cancelada','Cancelada',null,null);

-- CON-F-110 DOMINIO TIPO_EVENTO Caso
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('CACON','TIPO_EVENTO','Caso en conciliación','Caso en conciliación',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('PEDR','TIPO_EVENTO','Caso pendiente entrega documento de resultado','Caso pendiente entrega documento de resultado',null,null);
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('CLE','TIPO_EVENTO','Caso para control de legalidad','Caso asignado a control de legalidad',null,null);		

-------------------CASOS USO ALERTAS
/*
 tipo periodicidad
*/
INSERT DOMINIO VALUES('CAL','TIPO_PERIODICIDAD','Calendario','Horas o dias calendario',null,null);
INSERT DOMINIO VALUES('HAB','TIPO_PERIODICIDAD','Hábiles','Horas o dias hábiles',null,null);


/*
periodicidad
*/
INSERT DOMINIO VALUES('DIA','PERIODICIDAD','Día','Dias trascurridos para ejecutar una alerta.',null,null);
INSERT DOMINIO VALUES('HORA','PERIODICIDAD','Horas','Horas trascurridas para ejecutar una alerta.',null,null);
INSERT DOMINIO VALUES('SEG','PERIODICIDAD','Segundos','Segundos trascurridos para ejecutar una alerta.',null,null);


/* Permisos  AMD-F-037 y AMD-F-046 SIMASC-CU-ALERTAS SEGURIDAD */

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='ADMF037ListaAlertasPag';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='ADMF046ParametrizacionAlertas';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='ADMF037listaAlertas';

DELETE FUNCIONALIDAD WHERE nombre ='ADMF037ListaAlertasPag';
DELETE FUNCIONALIDAD WHERE nombre ='ADMF046ParametrizacionAlertas';
DELETE FUNCIONALIDAD WHERE nombre ='ADMF037listaAlertas';

insert into FUNCIONALIDAD values ('ADMF046ParametrizacionAlertas', 'Parametrización de alertas', 'app/Administracion/ADMF046','ADM','ANGULAR',NULL,'SIMASC_USER',SYSDATETIME(),'ACT');
insert into FUNCIONALIDAD values ('ADMF037listaAlertas', 'Menú lista de alertas', 'app/Administracion/ADMF037','ADM','ANGULAR',NULL,'SIMASC_USER',SYSDATETIME(),'ACT');
insert into FUNCIONALIDAD values ('ADMF037ListaAlertasPag', 'Página lista de alertas', 'app/Administracion/ADMF037','ADM','ANGULAR','ADMF037listaAlertas','SIMASC_USER',SYSDATETIME(),'ACT');

insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','ADMF046ParametrizacionAlertas',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA','ROL_DE_PARTE_ARBITRAJE') and  d.codigo  
		NOT IN ('JEFEARB','JEFECON');

insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','ADMF037listaAlertas',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA','ROL_DE_PARTE_ARBITRAJE') and  d.codigo  
		NOT IN ('JEFEARB','JEFECON');

insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','ADMF037ListaAlertasPag',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA','ROL_DE_PARTE_ARBITRAJE') and  d.codigo  
		NOT IN ('JEFEARB','JEFECON');

/*
 Estado de la alerta
*/
INSERT DOMINIO VALUES('ACT','ESTADO_ALERTA','Activo','Alerta activa',null,null);
INSERT DOMINIO VALUES('INA','ESTADO_ALERTA','Inactivo','Alerat inactiva',null,null);


/*DOMINIO TIPO PETICION */

INSERT DOMINIO VALUES('CAMBCON','TIPO_PETICION','Cambio de conciliador','Petición cambio de conciliador',null,null);
INSERT DOMINIO VALUES('CAMBFECH','TIPO_PETICION','Cambio de fecha','Petición cambio de fecha',null,null);
INSERT DOMINIO VALUES('DEVDINER','TIPO_PETICION','Devolución de dinero','Petición devolución de dinero',null,null);
INSERT DOMINIO VALUES('INCUMPLI','TIPO_PETICION','Incumplimiento','Petición incumplimiento',null,null);

INSERT DOMINIO VALUES('RESPUEST','TIPO_PARTE_PETICION','Recibe respuesta','Parte que recibe respuesta',null,null);
INSERT DOMINIO VALUES('SOLICITA','TIPO_PARTE_PETICION','Solicita respuesta','Parte que solicita respuesta',null,null);

---REVISAR
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('RESPPETI','TIPO_EVENTO','Respuesta petición','Se dió respuesta a una de las peticiones especiales solicitadas por el conciliador.',null,null);


/* EDICION DE CORREO ELECTRONIO 079  */

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF079EdicionCorreoE';
DELETE FUNCIONALIDAD WHERE nombre ='CONF079EdicionCorreoE';
insert into FUNCIONALIDAD values ('CONF079EdicionCorreoE', 'Parametrización de alertas', 'app/Conciliacion/CONF079','PDL','ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER',SYSDATETIME(),'ACT');

insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF079EdicionCorreoE',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA','ROL_DE_PARTE_ARBITRAJE') and  d.codigo  
		NOT IN ('ACO', 'CON', 'CONCOM', 'CONINS',  'SECCON', 'JEFECON');

/* peticiones especiales*/

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='CONF083tabPeticionesEsp';
DELETE FUNCIONALIDAD WHERE nombre ='CONF083tabPeticionesEsp';
insert into FUNCIONALIDAD values ('CONF083tabPeticionesEsp', 'Peticiones especiales', 'app/Conciliacion/CONF083','PDL','ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER',SYSDATETIME(),'ACT');
insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF083tabPeticionesEsp',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA','ROL_DE_PARTE_ARBITRAJE') and  d.codigo  
		NOT IN ('ACO', 'CON', 'CONCOM', 'CONINS', 'JEFECON');
		
/*Reabrir caso */

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF086ButtonReabrirCaso';
DELETE FUNCIONALIDAD where nombre='CONF086ButtonReabrirCaso';

insert into FUNCIONALIDAD values ('CONF086ButtonReabrirCaso', 'Reversar Resultado Audiencia','app/Conciliacion/CONF109','PDL', 'ANGULAR','CONF086ButtonReabrirCaso','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF086ButtonReabrirCaso', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON');

/**
 * DOMINIOS DE LA TABLA SOLICITUD PRESTADOR
 */			
			
INSERT DOMINIO VALUES ( 'PEND', 'ESTADO_SOLICITUD_PRESTADOR', 'Pendiente', 'Estado de la solicitud pendiente', null,null )
INSERT DOMINIO VALUES ( 'APRO', 'ESTADO_SOLICITUD_PRESTADOR', 'Aprobada', 'Estado de la solicitud aprobada', null,null )
INSERT DOMINIO VALUES ( 'RECH', 'ESTADO_SOLICITUD_PRESTADOR', 'Rechazada', 'Estado de la solicitud rechazada', null,null )

insert DOMINIO ( codigo, dominio, nombre, descripcion )values ( 'CAMLISTA', 'TIPO_SOLICITUD_PRESTADOR', 'Tipo de solicitud de prestador cambio de lista', 'Valor que puede tomar el campo "tipo" de la tabla SOLICITUD_PRESTADOR' )
insert DOMINIO ( codigo, dominio, nombre, descripcion )values ( 'CAMESPEC', 'TIPO_SOLICITUD_PRESTADOR', 'Tipo de solicitud de prestador cambio de especialidad', 'Valor que puede tomar el campo "tipo" de la tabla SOLICITUD_PRESTADOR' )
insert DOMINIO ( codigo, dominio, nombre, descripcion )values ( 'ACTIVACI', 'TIPO_SOLICITUD_PRESTADOR', 'Tipo de solicitud de prestador activacion', 'Valor que puede tomar el campo "tipo" de la tabla SOLICITUD_PRESTADOR' )
insert DOMINIO ( codigo, dominio, nombre, descripcion )values ( 'SUSPENCI', 'TIPO_SOLICITUD_PRESTADOR', 'Tipo de solicitud de prestador suspencion', 'Valor que puede tomar el campo "tipo" de la tabla SOLICITUD_PRESTADOR' )

/**
 * PERMISOS CON-F-118
 */

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF118SolicitarCambioLista';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF118SolicitarCambioLista';
INSERT INTO FUNCIONALIDAD VALUES ('CONF118SolicitarCambioLista', 'Solicitar cambio de lista', 'app/Administracion/CONF118','PDL','ANGULAR',null,'SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF118SolicitarCambioLista',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ( select nombre from rol 
								  where (id_rol in ( SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = 'PDL' and estado_registro = 'ACT' )
								  OR id_rol in ( select distinct(id_rol) from PARAMETRO_SERVICIO_SORTEO where estado_registro = 'ACT' )) );

/**
 * PERMISOS CON-C-002
 */								  
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC002ConsultarSolicitudesLista';
DELETE FUNCIONALIDAD WHERE nombre = 'CONC002ConsultarSolicitudesLista';
INSERT INTO FUNCIONALIDAD VALUES ('CONC002ConsultarSolicitudesLista', 'Consultar solicitudes de cambio de lista', 'app/Administracion/CONC002','PDL','ANGULAR',null,'SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONC002ConsultarSolicitudesLista',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ( 'ASA', 'JEFEARB', 'ACO', 'JEFECON');

insert DOMINIO ( codigo, dominio, nombre, descripcion )values ( 'DD41', 'TIPO_DOCUMENTO_DIG', 'Solicitud de cambio de lista', 'Documento de solicitud de cambio de lista' )

/**
 * DOMINIOS DE EVALUACION PRESTADOR
 */
/** codigo del criterio **/
INSERT DOMINIO VALUES ( 'CRIPARTI', 'EVALUACION_CONCILIADOR', 'Criterio de participación',
						  'Criterio de participación de la evaluación de conciliador', null, null )
INSERT DOMINIO VALUES ( 'CRIPROCE', 'EVALUACION_CONCILIADOR', 'Criterio de procedimientos',
						  'Criterio de procedimientos de la evaluación de conciliador', null, null )
INSERT DOMINIO VALUES ( 'CRIEDUCO', 'EVALUACION_CONCILIADOR', 'Criterio de educación continua',
						  'Criterio de educación continua de la evaluación de conciliador', null, null )
INSERT DOMINIO VALUES ( 'CRICALID', 'EVALUACION_CONCILIADOR', 'Criterio de calidad',
						  'Criterio de calidad de la evaluación de conciliador', null, null )

/**** codigo total ***/ 

INSERT DOMINIO VALUES ( 'PORTOTAL', 'CRITERIO_CALIDAD', 'Porcentaje total',
						  'Criterio de calidad: Porcentaje total', 'CRICALID', 'EVALUACION_CONCILIADOR' )
INSERT DOMINIO VALUES ( 'PORACUER', 'CRITERIO_CALIDAD', 'Porcentaje de acuerdos',
						  'Criterio de calidad: Porcentaje  de acuerdos', 'CRICALID', 'EVALUACION_CONCILIADOR' )

INSERT DOMINIO VALUES ( 'PORTOTAL', 'CRITERIO_EDUCACION_CONTINUA', 'Porcentaje total',
						  'Criterio de educación continua: Porcentaje total', 'CRIEDUCO', 'EVALUACION_CONCILIADOR' )

INSERT DOMINIO VALUES ( 'PORCUAAU', 'CRITERIO_PROCEDIMIENTOS', 'Porcentaje de cumplimiento de asistencia a audiencias',
						  'Criterio de procedimientos: Porcentaje de cumplimiento de asistencia a audiencias', 'CRIPROCE', 'EVALUACION_CONCILIADOR' )

INSERT DOMINIO VALUES ( 'PORACOTA', 'CRITERIO_PARTICIPACION', 'Porcentaje asistencia a comités y talleres',
						  'Criterio de participación: Porcentaje asistencia a comités y talleres', 'CRIPARTI', 'EVALUACION_CONCILIADOR' )
INSERT DOMINIO VALUES ( 'PORAJORN', 'CRITERIO_PARTICIPACION', 'Porcentaje de asistencia a las jornadas',
						  'Criterio de participación: Porcentaje de asistencia a las jornadas', 'CRIPARTI', 'EVALUACION_CONCILIADOR' )

insert criterio_total values ( 'CRICALID', 'PORTOTAL', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRICALID', 'PORACUER', 'USER_SIMASC', getdate(), 'ACT' )

insert criterio_total values ( 'CRIEDUCO', 'PORTOTAL', 'USER_SIMASC', getdate(), 'ACT' )

insert criterio_total values ( 'CRIPROCE', 'PORCUAAU', 'USER_SIMASC', getdate(), 'ACT' )

insert criterio_total values ( 'CRIPARTI', 'PORACOTA', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRIPARTI', 'PORAJORN', 'USER_SIMASC', getdate(), 'ACT' )

INSERT dominio values ( 'EVALUCON', 'RESULTADOS_EVALUACION_CONCILIADOR', 'Resultados de la evaluacion de conciliador',
						'Dominios que corresponden a los resultados de la evaluacion de conciliador', null, null )

INSERT CLASIFICADOR_DOMINIO VALUES ( 'PORTOTAL', 'CRITERIO_CALIDAD', 'EVALUCON', 'RESULTADOS_EVALUACION_CONCILIADOR', 'USER_SIMASC', getdate(), 'ACT' )
INSERT CLASIFICADOR_DOMINIO VALUES ( 'PORTOTAL', 'CRITERIO_EDUCACION_CONTINUA', 'EVALUCON', 'RESULTADOS_EVALUACION_CONCILIADOR', 'USER_SIMASC', getdate(), 'ACT' )
INSERT CLASIFICADOR_DOMINIO VALUES ( 'PORCUAAU', 'CRITERIO_PROCEDIMIENTOS', 'EVALUCON', 'RESULTADOS_EVALUACION_CONCILIADOR', 'USER_SIMASC', getdate(), 'ACT' )
INSERT CLASIFICADOR_DOMINIO VALUES ( 'PORACOTA', 'CRITERIO_PARTICIPACION', 'EVALUCON', 'RESULTADOS_EVALUACION_CONCILIADOR', 'USER_SIMASC', getdate(), 'ACT' )
INSERT CLASIFICADOR_DOMINIO VALUES ( 'PORAJORN', 'CRITERIO_PARTICIPACION', 'EVALUCON', 'RESULTADOS_EVALUACION_CONCILIADOR', 'USER_SIMASC', getdate(), 'ACT' )

/**
*SUFIJO DE CORREO CERTIFICADO
*/

insert PARAMETROS_GENERALES ( codigo, tipo, nombre, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values ( 'SUFIJO', 'CORREO', 'SUFIJO_CORREO_ELECTRONICO_CERTIFICADO', '.rpost.org', 'Sufijo que se le ponen a los correos electronicos certificados', 'USER_SIMASC', getdate(), 'ACT' )

/**
* DOMINIOS DE TIPO_ARCHIVO_INVALIDO 
*/



insert dominio values ( 'SH', 'TIPO_ARCHIVO_INVALIDO', 'SH','Archivos invalidos del CON-F-055', null, null )
insert dominio values ( 'TAR', 'TIPO_ARCHIVO_INVALIDO', 'TAR', 'Archivos invalidos del CON-F-055', null, null )
insert dominio values ( 'GZ', 'TIPO_ARCHIVO_INVALIDO', 'GZ', 'Archivos invalidos del CON-F-055', null, null )
insert dominio values ( 'KO', 'TIPO_ARCHIVO_INVALIDO', 'KO', 'Archivos invalidos del CON-F-055', null, null )
insert dominio values ( 'SYS', 'TIPO_ARCHIVO_INVALIDO', 'SYS', 'Archivos invalidos del CON-F-055', null, null )
insert dominio values ( 'SO', 'TIPO_ARCHIVO_INVALIDO', 'SO', 'Archivos invalidos del CON-F-055', null, null )
insert dominio values ( 'O', 'TIPO_ARCHIVO_INVALIDO', 'O', 'Archivos invalidos del CON-F-055', null, null )			

-- Ajuste permisos para acceso a reportes de conciliacion
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad ='reportesConc';

insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','reportesConc',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA','ROL_DE_PARTE_ARBITRAJE') and  d.codigo  
        NOT IN ('ACO','SECCON','JEFECON');
        
 -- correccion consulta de tags para convocantes

update TAG_PLANTILLA set consulta = 'NATIVE: Select ISNULL(c.primer_nombre_o_razon_social,'''') +'' ''+ ISNULL(c.segundo_Nombre, '''') +'' ''+ ISNULL(c.primer_Apellido,'''') +'' ''+ isnull(c.segundo_Apellido,'''') from CASO as a LEFT JOIN ROL_PERSONA_CASO b on a.id_caso = b.id_caso LEFT JOIN PERSONA as c on b.id_persona = c.id_persona INNER JOIN ROL d ON b.id_rol = d.id_rol WHERE d.nombre = ''CONVOTE'' AND c.estado_persona = ''ACT'' and b.estado != ''INA''  AND a.id_caso=?1'
, fecha_ultima_modificacion = GETDATE()
, id_usuario_modificacion = 'USER_SIMASC'
where codigo = 'convocanteP'

update TAG_PLANTILLA set consulta = 'NATIVE: Select ISNULL(c.primer_nombre_o_razon_social,'''') +'' ''+ ISNULL(c.segundo_Nombre, '''') +'' ''+ ISNULL(c.primer_Apellido,'''') +'' ''+ isnull(c.segundo_Apellido,'''') from CASO as a LEFT JOIN ROL_PERSONA_CASO b on a.id_caso = b.id_caso LEFT JOIN PERSONA as c on b.id_persona = c.id_persona INNER JOIN ROL d ON b.id_rol = d.id_rol WHERE d.nombre = ''CONVODO'' AND c.estado_persona = ''ACT'' and b.estado != ''INA''  AND a.id_caso=?1'
, fecha_ultima_modificacion = GETDATE()
, id_usuario_modificacion = 'USER_SIMASC'
where codigo = 'convocadoP'

update TAG_PLANTILLA set consulta = 'NATIVE: Select ISNULL(c.primer_nombre_o_razon_social,'''') +'' ''+ ISNULL(c.segundo_Nombre, '''') +'' ''+ ISNULL(c.primer_Apellido,'''') +'' ''+ isnull(c.segundo_Apellido,'''') from CASO as a LEFT JOIN ROL_PERSONA_CASO b on a.id_caso = b.id_caso LEFT JOIN PERSONA as c on b.id_persona = c.id_persona INNER JOIN ROL d ON b.id_rol = d.id_rol WHERE d.nombre = ''DTE'' AND c.estado_persona = ''ACT'' and b.estado != ''INA''  AND a.id_caso=?1'
, fecha_ultima_modificacion = GETDATE()
, id_usuario_modificacion = 'USER_SIMASC'
where codigo = 'demandanteP'

update TAG_PLANTILLA set consulta = 'NATIVE: Select ISNULL(c.primer_nombre_o_razon_social,'''') +'' ''+ ISNULL(c.segundo_Nombre, '''') +'' ''+ ISNULL(c.primer_Apellido,'''') +'' ''+ isnull(c.segundo_Apellido,'''') from CASO as a LEFT JOIN ROL_PERSONA_CASO b on a.id_caso = b.id_caso LEFT JOIN PERSONA as c on b.id_persona = c.id_persona INNER JOIN ROL d ON b.id_rol = d.id_rol WHERE d.nombre = ''DDA'' AND c.estado_persona = ''ACT'' and b.estado != ''INA''  AND a.id_caso=?1'
, fecha_ultima_modificacion = GETDATE()
, id_usuario_modificacion = 'USER_SIMASC'
where codigo = 'demandadoP'

delete tag_plantilla where codigo = 'demandante'
delete tag_plantilla where codigo = 'demandado'

-- Casos entregados 21/03/2018

-- No se incluyo en la entrega CON-C-028 Script seguiridad

DELETE FROM FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC028MarcarCasosCobrados'
DELETE FROM FUNCIONALIDAD WHERE nombre = 'CONC028MarcarCasosCobrados'

INSERT INTO FUNCIONALIDAD VALUES ('CONC028MarcarCasosCobrados', 'Marcar casos cobrados', 'app/Conciliacion/CONC028', 'PDL', 'ANGULAR', NULL, 'SIMASC_USER', GETDATE(), 'ACT')

INSERT INTO FUNCIONALIDAD_ROL
SELECT DISTINCT 'USUARIO_SIMASC', GETDATE(), 'INA', 'CONC028MarcarCasosCobrados', r.id_rol
FROM ROL r JOIN DOMINIO d ON r.nombre = d.codigo
WHERE d.dominio IN ('ROL_PERSONA', 'ROL_DE_PARTE_ARBITRAJE', 'ROL_DE_PARTE_CONCILIACION')
AND d.codigo NOT IN ('ACO')


-- Actualización de permisos 

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF086ButtonReabrirCaso';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF117ReversarResultadoAudiencia';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF066CambiarConciliador';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF086ButtonCambiarConciliador';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF086ButtonDeshacerUltimoResultado';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF086FichaTecnicaDatosBasicosCaso';

DELETE FUNCIONALIDAD where nombre='CONF086ButtonReabrirCaso';
DELETE FUNCIONALIDAD where nombre='CONF117ReversarResultadoAudiencia';
DELETE FUNCIONALIDAD where nombre='CONF066CambiarConciliador';
DELETE FUNCIONALIDAD where nombre='CONF086ButtonDeshacerUltimoResultado';
DELETE FUNCIONALIDAD where nombre='CONF086ButtonCambiarConciliador';
DELETE FUNCIONALIDAD where nombre='CONF086FichaTecnicaDatosBasicosCaso';


insert into FUNCIONALIDAD values ('CONF086FichaTecnicaDatosBasicosCaso', 'Cambiar Conciliador','app/Conciliacion/CONF086','PDL', 'ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF086FichaTecnicaDatosBasicosCaso', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ( 'CON','ACO', 'SECCON', 'JEFECON' , 'CONINS','CONCOM');

insert into FUNCIONALIDAD values ('CONF086ButtonCambiarConciliador', 'Cambiar Conciliador','app/Conciliacion/CONF086','PDL', 'ANGULAR','CONF086FichaTecnicaDatosBasicosCaso','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF086ButtonCambiarConciliador', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO', 'JEFECON');

insert into FUNCIONALIDAD values ('CONF086ButtonDeshacerUltimoResultado', 'Cambiar Conciliador','app/Conciliacion/CONF086','PDL', 'ANGULAR','CONF086FichaTecnicaDatosBasicosCaso','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF086ButtonDeshacerUltimoResultado', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ( 'ACO', 'SECCON', 'JEFECON');


insert into FUNCIONALIDAD values ('CONF066CambiarConciliador', 'Cambiar Conciliador','app/Conciliacion/CONF066','PDL', 'ANGULAR','CONF086FichaTecnicaDatosBasicosCaso','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF066CambiarConciliador', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON');

insert into FUNCIONALIDAD values ('CONF117ReversarResultadoAudiencia', 'Reversar Resultado Audiencia','app/Conciliacion/CONF117','PDL', 'ANGULAR','CONF086FichaTecnicaDatosBasicosCaso','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF117ReversarResultadoAudiencia', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON','AUX','SECCON');

insert into FUNCIONALIDAD values ('CONF086ButtonReabrirCaso', 'Reversar Resultado Audiencia','app/Conciliacion/CONF109','PDL', 'ANGULAR','CONF086ButtonReabrirCaso','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF086ButtonReabrirCaso', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON');



-- Permisos CON-C-015

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC015ModificarConveniosConciliacion';
DELETE FUNCIONALIDAD where nombre='CONC015ModificarConveniosConciliacion';
insert into FUNCIONALIDAD values ('CONC015ModificarConveniosConciliacion', 'Modificar Convenio de Conciliacion','app/Conciliacion/CONC015','PDL', 'ANGULAR','convenios','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC015ModificarConveniosConciliacion', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON');


/**
 * PERMISOS CON-C-008
 */								  
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC008ModificarApoderado';
DELETE FUNCIONALIDAD WHERE nombre = 'CONC008ModificarApoderado';
INSERT INTO FUNCIONALIDAD VALUES ('CONC008ModificarApoderado', 'Adicionar o eliminar apoderado de convenio', 'app/Conciliacion/CONC008','PDL','ANGULAR','CONC015ModificarConveniosConciliacion','SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONC008ModificarApoderado',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ( 'SECCON','ACO', 'JEFECON');


/**
 * Actualizacion permisos CON-F-072
 */
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF072ModificarEvento';
DELETE FUNCIONALIDAD where nombre='CONF072ModificarEvento';


-- CORRECCION DOMINIO DURACION AUDIENCIAS CON-C-015 

update dominio set codigo='DOSHORA' WHERE CODIGO='DOSHORAS' AND DOMINIO='DURACION_AUDIENCIA'

/** Permisos del CON-C-004 **/

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC004VerificarSolicitudCambioLista';
DELETE FUNCIONALIDAD WHERE nombre = 'CONC004VerificarSolicitudCambioLista';
INSERT INTO FUNCIONALIDAD VALUES ('CONC004VerificarSolicitudCambioLista', 'Verificar solicitudes de cambio de lista', 'app/Administracion/CONC004','PDL','ANGULAR', 'CONC002ConsultarSolicitudesLista', 'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONC004VerificarSolicitudCambioLista',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ( 'ASA', 'JEFEARB', 'ACO', 'JEFECON');


/**
 * CON-C-026
 */
insert into dominio values ('DD61', 'TIPO_DOCUMENTO_DIG', 'Acta resultado evento', 'Acta resultado evento', null, null)


/**
 * Dominios Tipo Tarifas
 */

insert DOMINIO values ('TTFIJ','TIPO_TARIFA_CONTRATO','Fija','Fija',null,null)
insert DOMINIO values ('TTDIN','TIPO_TARIFA_CONTRATO','Dinámica','Dinámica',null,null)


/**
 * CON-C-034
 */
 
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC034ReporteCasosCerradosConciliacion';
DELETE FUNCIONALIDAD where nombre='CONC034ReporteCasosCerradosConciliacion';
insert into FUNCIONALIDAD values ('CONC034ReporteCasosCerradosConciliacion', 'Casos Cerrados Conciliación','app/Conciliacion/CONC034','PDL', 'ANGULAR','reportesConc','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC034ReporteCasosCerradosConciliacion', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON');

-- Correccion bug 6234
insert into dominio values ('COMITE', 'TIPO_ACTIVIDAD_AGENDA', 'Comité', 'Comité', null, null)
insert into dominio values ('OTROS', 'TIPO_ACTIVIDAD_AGENDA', 'Otros', 'Otros', null, null)

/**
 * CON-C-018
*/

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC018ConciliadoresEvaluar';
DELETE FUNCIONALIDAD where nombre='CONC018ConciliadoresEvaluar';
insert into FUNCIONALIDAD values ('CONC018ConciliadoresEvaluar', 'Evaluación de conciliadores','app/Administracion/CONC018','ADM', 'ANGULAR',null,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC018ConciliadoresEvaluar', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON');/**
* CON-C-012
*/
			
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC012ConsultaModificacionContratosConvenio';
DELETE FUNCIONALIDAD where nombre='CONC012ConsultaModificacionContratosConvenio';
insert into FUNCIONALIDAD values ('CONC012ConsultaModificacionContratosConvenio', 'Consulta y modificacion de contratos de convenio','app/Conciliacion/CONC012','PDL', 'ANGULAR','convenios','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC012ConsultaModificacionContratosConvenio', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON');
			
-- CON-F-111

INSERT INTO PARAMETROS_GENERALES VALUES ('TEC_IJL', NULL, 'TIEMPO_ESTUDIO_CASOS_CON', 'HORA INICIO JORNADA LABORAL', NULL, '8:00', 'Hora de inicio de la jornada laboral', 'USUARIO_SIMASC', GETDATE(), 'ACT')
INSERT INTO PARAMETROS_GENERALES VALUES ('TEC_IR', NULL, 'TIEMPO_ESTUDIO_CASOS_CON', 'HORA INICIO RECESO', NULL, '12:00', 'Hora de inicio de receso en jornada laboral', 'USUARIO_SIMASC', GETDATE(), 'ACT')
INSERT INTO PARAMETROS_GENERALES VALUES ('TEC_FR', NULL, 'TIEMPO_ESTUDIO_CASOS_CON', 'HORA FIN RECESO', NULL, '13:00', 'Hora de fin de receso en jornada laboral', 'USUARIO_SIMASC', GETDATE(), 'ACT')
INSERT INTO PARAMETROS_GENERALES VALUES ('TEC_FJL', NULL, 'TIEMPO_ESTUDIO_CASOS_CON', 'HORA FIN JORNADA LABORAL', NULL, '17:00', 'Hora de fin de la jornada laboral', 'USUARIO_SIMASC', GETDATE(), 'ACT')
INSERT INTO PARAMETROS_GENERALES VALUES ('TEC_TME', NULL, 'TIEMPO_ESTUDIO_CASOS_CON', 'TIEMPO MAXIMO ESTUDIO CASO', 8, NULL, 'Tiempo máximo de estudio de caso (horas)', 'USUARIO_SIMASC', GETDATE(), 'ACT')

/* CON-F-109 Reabrir caso */
			
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF109reabrirCaso';
DELETE FUNCIONALIDAD where nombre='CONF109reabrirCaso';

insert into FUNCIONALIDAD values ('CONF109reabrirCaso', 'Reapertura del caso','app/Conciliacion/CONF109','PDL', 'ANGULAR','CONF086ButtonReabrirCaso','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF109reabrirCaso', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON');
			
			
/*---Permisos CONC046---*/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC046ReporteCasosFacturadosConvenio';
DELETE FUNCIONALIDAD where nombre='CONC046ReporteCasosFacturadosConvenio';

insert into FUNCIONALIDAD values ('CONC046ReporteCasosFacturadosConvenio', 'Reporte casos facturados de convenio','app/Conciliacion/CONF046','PDL', 'ANGULAR','reportesConc','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC046ReporteCasosFacturadosConvenio', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON','SECCON','RCO','APOCON');


/*------Permisos CONC043------*/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC043ReporteConvenios';
DELETE FUNCIONALIDAD where nombre='CONC043ReporteConvenios';

insert into FUNCIONALIDAD values ('CONC043ReporteConvenios', 'Reporte de convenios','app/Conciliacion/CONF043','PDL', 'ANGULAR','reportesConc','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC043ReporteConvenios', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON','SECCON');

			
/*--- Permisos ADM-C-006 ---*/
			
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ADMC006ConsultaSolicitudPrestador';
DELETE FUNCIONALIDAD where nombre='ADMC006ConsultaSolicitudPrestador';
insert into FUNCIONALIDAD values ('ADMC006ConsultaSolicitudPrestador', 'Consultar Solicitudes Realizadas por prestadores de servicio','app/Administracion/ADMC006','ADM', 'ANGULAR',NULL,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ADMC006ConsultaSolicitudPrestador', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('JEFEARB','JEFECON');
			
			
-- CON-C-020
insert into dominio values ('TOTCALCO', 'CRITERIO_CALIDAD', 'Total casos llevados por el conciliador', 'Total casos llevados por el conciliador', 'CRICALID', 'EVALUACION_CONCILIADOR')
insert into dominio values ('TOTACU', 'CRITERIO_CALIDAD', 'Total de acuerdos', 'Total de casos cerrados por acuerdo', 'CRICALID', 'EVALUACION_CONCILIADOR')
insert into dominio values ('TOTARRDI', 'CRITERIO_CALIDAD', 'Total arreglo directo', 'Total de casos cerrados por arreglo directo',  'CRICALID', 'EVALUACION_CONCILIADOR')
insert into dominio values ('TOTIMPO', 'CRITERIO_CALIDAD', 'Total imposibilidades', 'Total de casos cerrados por imposibilidad', 'CRICALID', 'EVALUACION_CONCILIADOR')
insert into dominio values ('TOTACCON', 'CRITERIO_CALIDAD', 'Total actas y constancias', 'Total de actas y constancias generadas por el conciliador', 'CRICALID', 'EVALUACION_CONCILIADOR')
insert into dominio values ('TOTDEVAC', 'CRITERIO_CALIDAD', 'Total devoluciones de actas y constancias', 'Total devoluciones de actas y constancias', 'CRICALID', 'EVALUACION_CONCILIADOR')
insert into dominio values ('PORACNOD', 'CRITERIO_CALIDAD', 'Porcentaje de actas y constancias no devueltos', 'Porcentaje de actas y constancias no devueltos', 'CRICALID', 'EVALUACION_CONCILIADOR')

insert into dominio values ('AGRACYCO', 'AGRUPADOR_ACTA_CON', 'Agrupador de actas y constancias', 'Los tipos de documentos a tener en cuenta par criterio de calidad', null, null)
insert into CLASIFICADOR_DOMINIO values('DD42', 'TIPO_DOCUMENTO_DIG', 'AGRACYCO', 'AGRUPADOR_ACTA_CON', 'USER_SIMASC', GETDATE(), 'ACT')
insert into CLASIFICADOR_DOMINIO values('DD22', 'TIPO_DOCUMENTO_DIG', 'AGRACYCO', 'AGRUPADOR_ACTA_CON', 'USER_SIMASC', GETDATE(), 'ACT')
insert into CLASIFICADOR_DOMINIO values('DD39', 'TIPO_DOCUMENTO_DIG', 'AGRACYCO', 'AGRUPADOR_ACTA_CON', 'USER_SIMASC', GETDATE(), 'ACT')

insert criterio_total values ( 'CRICALID', 'PORACNOD', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRICALID', 'TOTACCON', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRICALID', 'TOTACU', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRICALID', 'TOTARRDI', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRICALID', 'TOTCALCO', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRICALID', 'TOTDEVAC', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRICALID', 'TOTIMPO', 'USER_SIMASC', getdate(), 'ACT' )

/*--- Permisos CON-F-111 ---*/
			
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF111reasignarCasoControlLegalidad';
DELETE FUNCIONALIDAD where nombre='CONF111reasignarCasoControlLegalidad';
insert into FUNCIONALIDAD values ('CONF111reasignarCasoControlLegalidad', 'Reasignar casos para control de legalidad','app/Conciliacion/CONF111','PDL', 'ANGULAR',NULL,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF111reasignarCasoControlLegalidad', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('JEFECON');
			
/*--- Permisos ADM-C-019 ---*/
			
 DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ADMC019AdministracionMembresia';
DELETE FUNCIONALIDAD where nombre='ADMC019AdministracionMembresia';
insert into FUNCIONALIDAD values ('ADMC019AdministracionMembresia', 'Administración, Adición y consulta de membresías','app/Administracion/ADMC019','ADM', 'ANGULAR','ADM024rolprestador','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ADMC019AdministracionMembresia', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','SECCON','JEFECON');

/**
 * CON-C-021
 */
insert into dominio values ('PORTOTAL', 'CRITERIO_PARTICIPACION', 'Porcentaje total', 'Criterio participacion: porcentaje total', 'CRIPARTI', 'EVALUACION_CONCILIADOR')
insert into dominio values ('TOTALACU', 'CRITERIO_PARTICIPACION', 'Total comités y talles programados', 'Total comités y talles programados', 'CRIPARTI', 'EVALUACION_CONCILIADOR')
insert into dominio values ('TOTALAC', 'CRITERIO_PARTICIPACION', 'Total comités y talles programados a los que asistio el conciliador', 'Total comités y talles programados a los que asistio el conciliador', 'CRIPARTI', 'EVALUACION_CONCILIADOR')
insert into dominio values ('TOTJORPR', 'CRITERIO_PARTICIPACION', 'Total jornadas programadas', 'Total jornadas programadas', 'CRIPARTI', 'EVALUACION_CONCILIADOR')
insert into dominio values ('TOTJORAS', 'CRITERIO_PARTICIPACION', 'total jornadas asistió', 'Total jornadas asistió conciliador', 'CRIPARTI', 'EVALUACION_CONCILIADOR')

insert criterio_total values ( 'CRIPARTI', 'TOTALACU', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRIPARTI', 'TOTALAC', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRIPARTI', 'TOTJORPR', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRIPARTI', 'TOTJORAS', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRIPARTI', 'PORTOTAL', 'USER_SIMASC', getdate(), 'ACT' )

insert into dominio values ('PORTOTAL', 'CRITERIO_PROCEDIMIENTOS', 'Porcentaje total', 'Criterio procedimientos: porcentaje total', 'CRIPROCE', 'EVALUACION_CONCILIADOR')


			
-- Permisos Digitar Informacion para Constancia de no competencia

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'informacionConstanciaNoCompetencia';
DELETE FUNCIONALIDAD where nombre='informacionConstanciaNoCompetencia';
insert into FUNCIONALIDAD values ('informacionConstanciaNoCompetencia', 'Digitar información para constancia de no competencia','app/Conciliacion/DigitarInformacion','PDL', 'ANGULAR',NULL,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'informacionConstanciaNoCompetencia', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('CON');

/*** Permisos reporte CON-C-033 casos cerrados de convenios ***/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC033ReporteCasosCerradosConvenio';
DELETE FUNCIONALIDAD where nombre='CONC033ReporteCasosCerradosConvenio';
insert into FUNCIONALIDAD values ('CONC033ReporteCasosCerradosConvenio', 'Casos cerrados de convenios','app/Conciliacion/CONC033','PDL', 'ANGULAR','reportesConc','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC033ReporteCasosCerradosConvenio', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			AND d.codigo NOT IN ( 'ACO', 'SECCON', 'JEFECON', 'RCO'/*,aca el auxiliar administrativo*/);

			
/** 
 * Permisos Control de Cambios CON-F-091
 **/
			
delete funcionalidad_rol where nombre_funcionalidad='ARBF063divDocumentosAportados'
	and id_rol in (select id_rol from rol where tipo_servicio='PDL')

INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063divDocumentosAportados', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where r.tipo_servicio='PDL' 
			and d.dominio in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON','CON');

delete funcionalidad_rol where nombre_funcionalidad='ARBF063buttonAdjuntarDocumento'
	and id_rol in (select id_rol from rol where tipo_servicio='PDL')

INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063buttonAdjuntarDocumento', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where r.tipo_servicio='PDL' 
			and d.dominio in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON','CON');

delete funcionalidad_rol where nombre_funcionalidad='ARBF063divRespuestaCargaDocumento'
	and id_rol in (select id_rol from rol where tipo_servicio='PDL')

INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063divRespuestaCargaDocumento', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where r.tipo_servicio='PDL' 
			and d.dominio in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON','CON');

delete funcionalidad_rol where nombre_funcionalidad='ARBF063tableDocumentosAportados'
	and id_rol in (select id_rol from rol where tipo_servicio='PDL')

INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063tableDocumentosAportados', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where r.tipo_servicio='PDL' 
			and d.dominio in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON','CON');

delete funcionalidad_rol where nombre_funcionalidad='ARBF063tabDocAportados'
	and id_rol in (select id_rol from rol where tipo_servicio='PDL')

INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063tabDocAportados', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where r.tipo_servicio='PDL' 
			and d.dominio in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON','CON');

delete funcionalidad_rol where nombre_funcionalidad='TRANSF012divRadicacionDocumentos'
	and id_rol in (select id_rol from rol where tipo_servicio='PDL')

INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'TRANSF012divRadicacionDocumentos', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where r.tipo_servicio='PDL' 
			and d.dominio in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON','CON','SECCAC','APOCON','RCO');

delete funcionalidad_rol where nombre_funcionalidad='TRANSF012buttonGuardar'
	and id_rol in (select id_rol from rol where tipo_servicio='PDL')

INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'TRANSF012buttonGuardar', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where r.tipo_servicio='PDL' 
			and d.dominio in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON','CON','SECCAC','APOCON','RCO');
			

delete funcionalidad_rol where nombre_funcionalidad='CONF094tabCartas'
	and id_rol in (select id_rol from rol where tipo_servicio='PDL')

INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF094tabCartas', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where r.tipo_servicio='PDL' 
			and d.dominio in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON','CON');

/* DOMINIOS RESULTADO_AUDIENCIA */			
UPDATE DOMINIO SET nombre = 'Acta' WHERE codigo = 'DD22'
UPDATE DOMINIO SET nombre = 'Constancia', descripcion = 'Constancia de conciliación' WHERE codigo = 'DD39'

/*** Permisos CON-C-036 ***/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC036ReporteCasosDevueltos';
DELETE FUNCIONALIDAD where nombre='CONC036ReporteCasosDevueltos';
insert into FUNCIONALIDAD values ('CONC036ReporteCasosDevueltos', 'Casos devueltos en control de legalidad','app/Conciliacion/CONC036','PDL', 'ANGULAR','reportesConc','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC036ReporteCasosDevueltos', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			AND d.codigo NOT IN ( 'ACO', 'SECCON', 'JEFECON'/*,aca el auxiliar administrativo*/);

/*** bug: 6089, 6693 **/ 
update PLANTILLA_CARTA set plantilla_html = '<html> 
<head>  <title>Citación audiencia</title>  </head> 
<body>    <font face="Arial">      <p align="left">       ciudadP, dia_cartaP de mes_cartaP de ano_cartaP       </p>   
<p align="left">           senorDoctorRLegalP      <br/>     
dirigidoP        <br/>        direccionEnvio        <br/>      
telefonoEnvio        <br/>        ciudadEnvio      </p>   
<p align="right">        radicadoP      </p>    
<p align="justify">        REFERENCIA: SOLICITUD DE CONCILIACION DE convocanteP PARA SOLUCIONAR LAS DIFERENCIAS SURGIDAS CON convocadoP.      </p> 
<p align="justify">Apreciado(a) señor(a):</p>      
<p align="justify">      PCPAUparrafo1P    </p>    
<p align="justify">      El nombreCentroP, se creó para ayudar a que las personas resuelvan LEGALMENTE sus problemas sin necesidad de ir a un juzgado. Las personas que vienen al Centro de Conciliación arreglan rápidamente sus conflictos, los solucionan pacíficamente y no se ocasionan más problemas de los que ya tienen.    </p>   
<p align="justify">      Queremos comentarle que para este Centro, es un honor prestarle nuestros servicios. Por lo tanto, atentamente lo invitamos a una audiencia de conciliación en nuestras oficinas ubicada en la direccionSedeP, nombreSedeP el fechaAudienciaP a las horaAudienciaP.    </p>  
<p align="justify">leyAsistenciaP</p>       
<p align="justify">PCPAUparrafo4P</p>     
<p align="justify">        Para nosotros es importante saber si recibieron esta comunicación, por lo cual les solicitamos que confirmen su asistencia al siguiente correo electrónico: emailConciliadorP. Por favor indique el número de caso.      </p>  
<p align="justify">        Para información adicional puede comunicarse al teléfono: <!--número de teléfono-->      </p>      
<p align="justify">Cordialmente,</p>      <p align="justify">&#160;</p>     <p align="left">        conciliadorP      </p>   
<p align="left">Conciliador</p>    
<p align="left">        Registro registroConciliadorP      </p>    
<p align="right">        Caso codigoCaso      </p>   
</font>  
</body> 
</html>', id_usuario_modificacion = 'USER_SIMASC', fecha_ultima_modificacion = getdate() 
where nombre = 'PCPAU' and tipo_servicio = 'PDL'

update PLANTILLA_CARTA set plantilla_html = '<html> 
<head> <title>Citación audiencia</title> </head>
 <body>  
<font face="Arial">  
<p align="left">     ciudadP, dia_cartaP de mes_cartaP de ano_cartaP    </p>   
<p align="left">           senorDoctorRLegalP        <br/>  
dirigidoP      <br/>      direccionEnvio      <br/>  
telefonoEnvio      <br/>    
ciudadEnvio    </p>  
<p align="right">      radicadoP    </p>  
<p align="justify">      REFERENCIA: SOLICITUD DE CONCILIACION DE convocanteP PARA SOLUCIONAR LAS DIFERENCIAS SURGIDAS CON convocadoP.    </p> 
<p align="justify">Apreciado(a) señor(a):</p>
<p align="justify">Nos complace invitarle a una audiencia de conciliación, el fechaAudienciaP, a las horaAudienciaP en nuestras oficinas ubicadas en direccionSedeP.</p>    
<p align="justify">         leyAsistenciaP         </p>  
<p align="justify">      Para nosotros es importante saber si recibieron esta comunicación, por lo cual les solicitamos que confirmen su asistencia al siguiente correo electrónico: emailConciliadorP. Por favor indique el número de caso.    </p> 
<p align="justify">      Para información adicional puede comunicarse al teléfono <!--número de teléfono-->    </p>
<p align="justify">Cordialmente,</p>    
<p align="justify">&#160;</p>  
<p align="left">      conciliadorP    </p>   
<p align="left">Conciliador</p>    <p align="left">      Registro registroConciliadorP    </p> 
<p align="right">      Caso codigoCaso    </p>
</font>
</body>
</html>', id_usuario_modificacion = 'USER_SIMASC', fecha_ultima_modificacion = getdate() 
where nombre = 'PCADP' and tipo_servicio = 'PDL'	
			
-- CON-C-022
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) values ('INCUMPLI', 1, 'PLAZO_RESPUESTA_PETICION', 'Solicitud de incumplimiento', 8, null, 'Valor dado en días hábiles', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) values ('CAMBFECH', 1, 'PLAZO_RESPUESTA_PETICION', 'Solicitud cambio de fecha', 15, null, 'Valor dado en días hábiles', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) values ('CAMBCON', 1, 'PLAZO_RESPUESTA_PETICION', 'Solicitud cambio de conciliador', 1, null, 'Valor dado en días hábiles', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) values ('DEVDINER', 1, 'PLAZO_RESPUESTA_PETICION', 'Solicitud devolución de dinero', 10, null, 'Valor dado en días hábiles', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) values ('DEREPETI', 1, 'PLAZO_RESPUESTA_PETICION', 'Solicitud derecho de petición', 15, null, 'Valor dado en días hábiles', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) values ('FOTOCOPI', 1, 'PLAZO_RESPUESTA_PETICION', 'Solicitud de fotocopias', 10, null, 'Aplica para solicitud de información, Valor dado en días hábiles', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) values ('PETIOTRO', 1, 'PLAZO_RESPUESTA_PETICION', 'Otros', 10, null, 'Aplica para tipo de peticion diferentes, Valor dado en días hábiles', 'USUARIO_SIMASC', GETDATE(), 'ACT')
			
insert into dominio values ('TOTAUPRO', 'CRITERIO_PROCEDIMIENTOS', 'Total de audiencias programadas', 'Total de audiencias programadas', 'CRIPROCE', 'EVALUACION_CONCILIADOR')
insert into dominio values ('TOTAINCU', 'CRITERIO_PROCEDIMIENTOS', 'Total audiencias incumplio horario', 'Numero de audiencias en las que incumplio el horario', 'CRIPROCE', 'EVALUACION_CONCILIADOR')
insert into dominio values ('TOTAJUST', 'CRITERIO_PROCEDIMIENTOS', 'Total audiencias con justificacion valida', 'Numero de audiencias en las que incumplio el horario pero presento una justificacion valida', 'CRIPROCE', 'EVALUACION_CONCILIADOR')
insert into dominio values ('TOTPETES', 'CRITERIO_PROCEDIMIENTOS', 'Total de peticiones especiales asignadas', 'Total de peticiones especiales asignadas', 'CRIPROCE', 'EVALUACION_CONCILIADOR')
insert into dominio values ('TOTARESE', 'CRITERIO_PROCEDIMIENTOS', 'Total de respuestas emitidas oportunamente', 'Numero de respuestas emitidas en el tiempo definido', 'CRIPROCE', 'EVALUACION_CONCILIADOR')
insert into dominio values ('PORCRESE', 'CRITERIO_PROCEDIMIENTOS', 'Porcentaje de respuestas emitidas oportunamente', 'Porcentaje de respuestas emitidas oportunamente', 'CRIPROCE', 'EVALUACION_CONCILIADOR')
insert into dominio values ('TOTCASAC', 'CRITERIO_PROCEDIMIENTOS', 'Total de casos aceptados por el conciliador', 'Numero de casos que fueron aceptados por el conciliador', 'CRIPROCE', 'EVALUACION_CONCILIADOR')
insert into dominio values ('TOTCASCE', 'CRITERIO_PROCEDIMIENTOS', 'Total de casos cerrados antes de 90 dias', 'Numero de casos cerrados antes de 90 dias', 'CRIPROCE', 'EVALUACION_CONCILIADOR')
insert into dominio values ('TOTCASOL', 'CRITERIO_PROCEDIMIENTOS', 'Total casos con solicitud de prorroga', 'Numero de casos que dispongan de prorroga', 'CRIPROCE', 'EVALUACION_CONCILIADOR')
insert into dominio values ('PORCUMCI', 'CRITERIO_PROCEDIMIENTOS', 'Porcentaje cumplimiento cierre de casos', 'Porcentaje cumplimiento cierre de casos', 'CRIPROCE', 'EVALUACION_CONCILIADOR')

insert criterio_total values ( 'CRIPROCE', 'PORTOTAL', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRIPROCE', 'TOTAUPRO', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRIPROCE', 'TOTAINCU', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRIPROCE', 'TOTAJUST', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRIPROCE', 'TOTPETES', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRIPROCE', 'TOTARESE', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRIPROCE', 'PORCRESE', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRIPROCE', 'TOTCASAC', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRIPROCE', 'TOTCASCE', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRIPROCE', 'TOTCASOL', 'USER_SIMASC', getdate(), 'ACT' )
insert criterio_total values ( 'CRIPROCE', 'PORCUMCI', 'USER_SIMASC', getdate(), 'ACT' )

-- CON-C-019
delete from CLASIFICADOR_DOMINIO where codigo_clasificador = 'EVALUCON'

insert into CLASIFICADOR_DOMINIO values ('PORTOTAL', 'CRITERIO_EDUCACION_CONTINUA', 'EVALUCON', 'RESULTADOS_EVALUACION_CONCILIADOR', 'USER_SIMASC', GETDATE(), 'ACT')
insert into CLASIFICADOR_DOMINIO values ('PORACUER', 'CRITERIO_CALIDAD', 'EVALUCON', 'RESULTADOS_EVALUACION_CONCILIADOR', 'USER_SIMASC', GETDATE(), 'ACT')
insert into CLASIFICADOR_DOMINIO values ('PORACNOD', 'CRITERIO_CALIDAD', 'EVALUCON', 'RESULTADOS_EVALUACION_CONCILIADOR', 'USER_SIMASC', GETDATE(), 'ACT')
insert into CLASIFICADOR_DOMINIO values ('PORACOTA', 'CRITERIO_PARTICIPACION', 'EVALUCON', 'RESULTADOS_EVALUACION_CONCILIADOR', 'USER_SIMASC', GETDATE(), 'ACT')
insert into CLASIFICADOR_DOMINIO values ('PORAJORN', 'CRITERIO_PARTICIPACION', 'EVALUCON', 'RESULTADOS_EVALUACION_CONCILIADOR', 'USER_SIMASC', GETDATE(), 'ACT')
insert into CLASIFICADOR_DOMINIO values ('PORCUAAU', 'CRITERIO_PROCEDIMIENTOS', 'EVALUCON', 'RESULTADOS_EVALUACION_CONCILIADOR', 'USER_SIMASC', GETDATE(), 'ACT')
insert into CLASIFICADOR_DOMINIO values ('PORCRESE', 'CRITERIO_PROCEDIMIENTOS', 'EVALUCON', 'RESULTADOS_EVALUACION_CONCILIADOR', 'USER_SIMASC', GETDATE(), 'ACT')
insert into CLASIFICADOR_DOMINIO values ('PORCUMCI', 'CRITERIO_PROCEDIMIENTOS', 'EVALUCON', 'RESULTADOS_EVALUACION_CONCILIADOR', 'USER_SIMASC', GETDATE(), 'ACT')

insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('PORTOTAL', 1, 'PESO_CRITERIOS_EVALUACION', 'Peso criterio educacion continua', 15, null, 'Peso criterio educacion continua', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('PORACOTA', 1, 'PESO_CRITERIOS_EVALUACION', 'Peso porcentaje asistencias comites y audiencias', 8, null, 'Peso criterio participacion', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('PORAJORN', 1, 'PESO_CRITERIOS_EVALUACION', 'Peso porcentaje asistencia jornadas', 7, null, 'Peso criterio educacion continua', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('PORCUAAU', 1, 'PESO_CRITERIOS_EVALUACION', 'Peso porcentaje de asistencia a audiencias', 8, null, 'Peso criterio procedimientos', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('PORCRESE', 1, 'PESO_CRITERIOS_EVALUACION', 'Peso porcentaje respuestas emitidas oportunamente', 6, null, 'Peso criterio procedimientos', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('PORCUMCI', 1, 'PESO_CRITERIOS_EVALUACION', 'Peso porcentaje cumplimiento cierre de casos', 6, null, 'Peso criterio procedimientos', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('PORACUER', 1, 'PESO_CRITERIOS_EVALUACION', 'Peso porcentaje acuerdos', 30, null, 'Peso criterio calidad', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('PORACNOD', 1, 'PESO_CRITERIOS_EVALUACION', 'Peso porcentaje actas y constancias no devueltas', 20, null, 'Peso criterio calidad', 'USUARIO_SIMASC', GETDATE(), 'ACT')


-- Permisos Control de cambios ADM-C-009

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ADMC009AdministracionParametros';
DELETE FUNCIONALIDAD where nombre='ADMC009AdministracionParametros';
insert into FUNCIONALIDAD values ('ADMC009AdministracionParametros', 'Administración, consulta y actualización de parámetros','app/Administracion/ADMC009','ADM', 'ANGULAR',NULL,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ADMC009AdministracionParametros', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ADMINU','JEFECON','JEFEARB');
			
-- Dominios control de cambios ADM-C-009

insert dominio values('TPG','TIPO_PARAMETRO','Parámetros Generales','Parámetros Generales',NULL,NULL)
insert dominio values('TPS','TIPO_PARAMETRO','Parámetros de Servicio','Parámetros de Servicio',NULL,NULL)
insert dominio values('TPDC','TIPO_PARAMETRO','Parámetros de Centro','Parámetros de Centro',NULL,NULL)

-- Actualizacion del rol Analista de conciliacion para el caso de uso  CON-F-075
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF075ControlAsistencia';
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF075ControlAsistencia', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON', 'JEFECON', 'ACO');
			
/*** Permisos CON-F-126 Lista de casos pendientes por control de legalidad ***/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF126CasosControlLegalidad';
DELETE FUNCIONALIDAD where nombre='CONF126CasosControlLegalidad';
INSERT INTO FUNCIONALIDAD values ('CONF126CasosControlLegalidad', 'Casos pendientes por control de legalidad','app/Conciliacion/CONF126','PDL', 'ANGULAR', null,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF126CasosControlLegalidad', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			AND d.codigo NOT IN ('ACO');
			
--Permisos Boton crear asesoria CONC1003
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC1003botonCrear';
DELETE FUNCIONALIDAD where nombre='CONC1003botonCrear';
insert into FUNCIONALIDAD values ('CONC1003botonCrear', 'Botón crear asesorías','app/Conciliacion/CONC1003','PDL', 'ANGULAR','CONC1003ConsultaAsesorias','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC1003botonCrear', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO');

/* DOMINIOS MOTIVO_DEVOLUCION_CASO */	
INSERT DOMINIO VALUES ( 'ADRLP', 'MOTIVO_DEVOLUCION_CASO', 'Acreditación y debida representación legal de las partes', 'Motivo de devolución: Acreditación y debida representación legal de las partes', null,null )
INSERT DOMINIO VALUES ( 'ERRDERE', 'MOTIVO_DEVOLUCION_CASO', 'Error de redacción', 'Motivo de devolución: Error de redacción', null,null )
INSERT DOMINIO VALUES ( 'ERRCRC', 'MOTIVO_DEVOLUCION_CASO', 'Error en el cierre del resultado del caso', 'Motivo de devolución: Error en el cierre del resultado del caso', null,null )
INSERT DOMINIO VALUES ( 'ERRFEEXP', 'MOTIVO_DEVOLUCION_CASO', 'Error en la fecha de expedición', 'Motivo de devolución: Error en la fecha de expedición', null,null )
INSERT DOMINIO VALUES ( 'ERRFESOL', 'MOTIVO_DEVOLUCION_CASO', 'Error en la fecha de solicitud', 'Motivo de devolución: Error en la fecha de solicitud', null,null )
INSERT DOMINIO VALUES ( 'ERRMECA', 'MOTIVO_DEVOLUCION_CASO', 'Error mecanográfico', 'Motivo de devolución: Error mecanográfico', null,null )
INSERT DOMINIO VALUES ( 'EXIOBLI', 'MOTIVO_DEVOLUCION_CASO', 'Exigibilidad de las obligaciones', 'Motivo de devolución: Exigibilidad de las obligaciones', null,null )
INSERT DOMINIO VALUES ( 'OMISFIRM', 'MOTIVO_DEVOLUCION_CASO', 'Omisión de las firmas', 'Motivo de devolución: Omisión de las firmas', null,null )
INSERT DOMINIO VALUES ( 'OMISREAC', 'MOTIVO_DEVOLUCION_CASO', 'Omisión de requisitos de las actas', 'Motivo de devolución: Omisión de requisitos de las actas', null,null )
INSERT DOMINIO VALUES ( 'OMIREQCO', 'MOTIVO_DEVOLUCION_CASO', 'Omisión de requisitos de las constancias', 'Motivo de devolución: Omisión de requisitos de las constancias', null,null )
INSERT DOMINIO VALUES ( 'ERRNOMBP', 'MOTIVO_DEVOLUCION_CASO', 'Error en los nombres de las partes', 'Motivo de devolución: Error en los nombres de las partes', null,null )

/* DOMINIOS RESULTADO_AUDIENCIA */
INSERT INTO DOMINIO VALUES ('REPVAL', 'ESTADO_RESULTADO_AUDIENCIA', 'Por validar', 'Estado del documento del resultado de la audiencia: Por validar', null, null)
INSERT INTO DOMINIO VALUES ('REAUDEVU', 'ESTADO_RESULTADO_AUDIENCIA', 'Devuelto', 'Estado del documento del resultado de la audiencia: Devuelto', null, null)
INSERT INTO DOMINIO VALUES ('REAUVALI', 'ESTADO_RESULTADO_AUDIENCIA', 'Validado', 'Estado del documento del resultado de la audiencia: Validado', null, null)
INSERT INTO DOMINIO VALUES ('REAUINSI', 'ESTADO_RESULTADO_AUDIENCIA', 'Insiste', 'Estado del documento del resultado de la audiencia: Insiste', null, null)
INSERT INTO DOMINIO VALUES ('REAUCORR', 'ESTADO_RESULTADO_AUDIENCIA', 'Corrige', 'Estado del documento del resultado de la audiencia: Corregido', null, null)
INSERT INTO DOMINIO VALUES ('REAUREAU', 'ESTADO_RESULTADO_AUDIENCIA', 'Reprogramar audiencia', 'Estado del documento del resultado de la audiencia: Re-programar audiencia', null, null)
INSERT INTO DOMINIO VALUES ('SINDOC','ESTADO_RESULTADO_AUDIENCIA','Sin documento','Estado del documento del resultado de la audiencia: Sin documento', null, null)

/*** Permisos CON-F-084 ***/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF084ModificarActasConstanciasDevueltas';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF084ModificarActasConstanciasDevueltas';
INSERT INTO FUNCIONALIDAD VALUES ('CONF084ModificarActasConstanciasDevueltas', 'Modificación de actas o constancias', 'app/Conciliacion/CONF084','PDL','ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF084ModificarActasConstanciasDevueltas',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ( select nombre from rol where id_rol in (select id_rol from tipo_de_servicio_rol where tipo_servicio = 'PDL') );

-- CON-F-095
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('CAC1', 1, 'PARAMETROS_PUP', 'Data adicional conciliación Bogotá', null, 'CAC1-1', 'Valor para campo data adicional conciliación Bogotá', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('CAC2', 1, 'PARAMETROS_PUP', 'Data adicional conciliación Fusagasugá', null, 'CAC1-2', 'Valor para campo data adicional conciliación Fusagasugá', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('CAC3', 1, 'PARAMETROS_PUP', 'Data adicional conciliación Zipaquira', null, 'CAC1-2', 'Valor para campo data adicional conciliación Fusagasugá', 'USUARIO_SIMASC', GETDATE(), 'ACT')

insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('SERV', 1, 'PARAMETROS_PUP', 'ServicioId', null, '15', 'Valor para el campo servicioId utilizado en sirep', 'USUARIO_SIMASC', GETDATE(), 'ACT')

update parametros_generales set tipo = 'PORCENTAJE_PAGO_ESCALONADO', editable  = 1, nombre = 'Cobro adicional por audiencia' where codigo = 'ADI_AUDI' and tipo = 'COBRO ADICIONAL POR AUDIENCIA'

insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('ADI_ACU', 1, 'PORCENTAJE_PAGO_ESCALONADO', 'Acuerdo', 30, null, 'Define el porcentaje a cobrar cuando el motivo de reliquidacion es acuerdo', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('ADI_IMP', 1, 'PORCENTAJE_PAGO_ESCALONADO', 'Imposibilidad', 5, null, 'Define el porcentaje a cobrar cuando el motivo de reliquidacion es imposibilidad', 'USUARIO_SIMASC', GETDATE(), 'ACT')

insert into parametros_generales (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('LIM_PAG', 1, 'LIMITE_PAGO_ESCALONADO', 'Cantidad Salarios minimos para reliquidacion', 118, null, 'Define la cantidad de salarios para realizar reliquidacion por acuerdo o imposibilidad', 'USUARIO_SIMASC', GETDATE(), 'ACT')

insert into dominio values ('IMPOSIB', 'MOTIVO_DE_RELIQUIDACION', 'Imposibilidad', 'Valor devuelto al cliente', null, null)
insert into dominio values ('ACUREL', 'MOTIVO_DE_RELIQUIDACION', 'Acuerdo', 'Valor devuelto al cliente', null, null)

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF095AprobarReliquidacion';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF095PagoCasosList';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF095AprobarReliquidacion';
DELETE FUNCIONALIDAD WHERE nombre = 'CONF095PagoCasosList';
INSERT INTO FUNCIONALIDAD VALUES ('CONF095PagoCasosList', 'Proceso de reliquidación', 'app/Conciliacion/CONF095','PDL','ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD VALUES ('CONF095AprobarReliquidacion', 'Aprobar reliquidación', 'app/Conciliacion/CONF095','PDL','ANGULAR','CONF095PagoCasosList','SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF095PagoCasosList',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ('ACO', 'SECCON', 'JEFECON')
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','CONF095AprobarReliquidacion',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ('ACO', 'SECCON', 'JEFECON')
			
-- parametro general dias para cierre de caso
insert into parametros_generales values ('NDCC', null, 'CIERRE_CASO', 'Número de días para cierre de caso', 90, null, 'Numero de dias para cerrar un caso de conciliacion a partir de la fecha de radicacion', 'USER_SIMASC', GETDATE(), 'ACT')			
			
-- TRANS-F-025

-- Permisos TRANSF025PeticionesEspecialesPartes

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF025PeticionesEspecialesPartes';
DELETE FUNCIONALIDAD where nombre='TRANSF025PeticionesEspecialesPartes';
insert into FUNCIONALIDAD values ('TRANSF025PeticionesEspecialesPartes', 'Petición Especial Partes','app/Conciliacion/TRANSF025','PDL', 'ANGULAR',null,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'TRANSF025PeticionesEspecialesPartes', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('APODCDO','APODCTE','CONVODO','CONVOTE');


DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF040Peticiones';
DELETE FUNCIONALIDAD where nombre='TRANSF040Peticiones';
insert into FUNCIONALIDAD values ('TRANSF040Peticiones', 'Petición Especial Partes','app/Conciliacion/TRANSF025','PDL', 'ANGULAR',null,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'TRANSF040Peticiones', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('APODCDO','APODCTE','CONVODO','CONVOTE');


-- Insert dominios tipos de petición -- TRANS-F-025

insert dominio values('DEREPETI','TIPO_PETICION','Derecho de petición', 'Petición derecho de petición',null,null)
insert dominio values('FOTOCOPI','TIPO_PETICION','Solicitud de fotocopias', 'Petición Solicitud de fotocopias',null,null)
insert dominio values('PETIOTRO','TIPO_PETICION','Otros', 'Otros',null,null)

-- Insert tipo documento petición -- TRANS-F-025

insert DOMINIO values ('DD43','TIPO_DOCUMENTO_DIG','Documento de Petición Especial','Documento de Petición Especial',null,null)
		
-- Insert rol_tipo_peticion -- TRANS-F-025

insert PARAMETROS_GENERALES values('RTPDD',1,'ROL_TIPO_PETICION','Roles petición devolución de dinero',null,'SECCON','Roles a enviar correo para petición devolución de dinero','USUARIO_SIMASC','2017-08-09 00:00:00.000','ACT')
insert PARAMETROS_GENERALES values('RTPCC',1,'ROL_TIPO_PETICION','Roles petición cambio de conciliador',null,'JEFECON,ACO','Roles a enviar correo para petición cambio de conciliador','USUARIO_SIMASC','2017-08-09 00:00:00.000','ACT')
insert PARAMETROS_GENERALES values('RTPI',1,'ROL_TIPO_PETICION','Roles petición incumplimiento',null,'SECCON','Roles a enviar correo para petición incumplimiento','USUARIO_SIMASC','2017-08-09 00:00:00.000','ACT')
insert PARAMETROS_GENERALES values('RTPDP',1,'ROL_TIPO_PETICION','Roles petición derecho de peticion',null,'ACO','Roles a enviar correo para petición derecho de peticion','USUARIO_SIMASC','2017-08-09 00:00:00.000','ACT')
insert PARAMETROS_GENERALES values('RTPF',1,'ROL_TIPO_PETICION','Roles petición fotocopias',null,'SECCON','Roles a enviar correo para petición fotocopias','USUARIO_SIMASC','2017-08-09 00:00:00.000','ACT')
insert PARAMETROS_GENERALES values('RTPO',1,'ROL_TIPO_PETICION','Roles petición otros',null,'SECCON','Roles a enviar correo para petición otros','USUARIO_SIMASC','2017-08-09 00:00:00.000','ACT')
insert PARAMETROS_GENERALES values('RTPCF',1,'ROL_TIPO_PETICION','Roles petición cambio de fecha',null,'SECCON','Roles a enviar correo para petición cambio de fecha','USUARIO_SIMASC','2017-08-09 00:00:00.000','ACT')

-- Update solicitud cambio de fecha -- TRANS-F-025
update PARAMETROS_GENERALES set valor_numerico=2 where codigo='CAMBFECH'
			

/*----------------Actualizacion de permisos ----------------------*/
			
/*Facturacion del conciliador */
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF086FichaTecnicaDatosPagoConciliador';
DELETE FUNCIONALIDAD where nombre='CONF086FichaTecnicaDatosPagoConciliador';

insert into FUNCIONALIDAD values ('CONF086FichaTecnicaDatosPagoConciliador', 'Datos de pago del conciliador','app/Conciliacion/CONF086','PDL', 'ANGULAR','CONF086FichaTecnicaDatosBasicosCaso','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF086FichaTecnicaDatosPagoConciliador', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio2
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON');

UPDATE FUNCIONALIDAD SET nombre_funcionalidad_padre = 'CONF086FichaTecnicaDatosBasicosCaso' WHERE  nombre = 'CONF086ButtonReabrirCaso';

/* Reportes concialiacion permisos responsable de convenio*/

DELETE FUNCIONALIDAD_ROL WHERE id_rol  IN (SELECT r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo  in ('RCO')) AND nombre_funcionalidad =  'reportesConc'


/*permisos ficha tecnica del caso*/

UPDATE	FUNCIONALIDAD SET nombre_funcionalidad_padre = 'CONF086FichaTecnicaDatosBasicosCaso' WHERE nombre IN  ( 'CONF086ButtonReabrirCaso')
UPDATE	FUNCIONALIDAD SET nombre_funcionalidad_padre = 'CONF086FichaTecnicaDatosBasicosCaso' WHERE nombre IN  ( 'CONF109reabrirCaso')


DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF086ButtonReabrirCaso';
DELETE FUNCIONALIDAD where nombre='CONF086ButtonReabrirCaso';

insert into FUNCIONALIDAD values ('CONF086ButtonReabrirCaso', 'Reversar Resultado Audiencia','app/Conciliacion/CONF109','PDL', 'ANGULAR','CONF086FichaTecnicaDatosBasicosCaso','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF086ButtonReabrirCaso', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON');

-- CON-C-052
			
--Permisos

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC052ActualizarEstadoCorrespondencia';
DELETE FUNCIONALIDAD where nombre='CONC052ActualizarEstadoCorrespondencia';
insert into FUNCIONALIDAD values ('CONC052ActualizarEstadoCorrespondencia', 'Actualizar Estado Correspondencia','app/Administracion/CONC052','ADM', 'ANGULAR',NULL,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC052ActualizarEstadoCorrespondencia', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO');

-- Insert dominio 

insert dominio values ('CARDEV','ESTADO_CARTA','Devuelta','Devuelta',null,null)
insert dominio values ('CARNOEN', 'TIPO_LLAMADA', 'Carta no entregada', 'Carta no entregada',null,null)

-- Actualizacion parametro wsdl servicio pup

update parametros_generales set valor_texto = 'http://appqas.ccb.org.co/WCFPUPXrecaudos/Servicio.svc?wsdl' where codigo = 'URLPUP' and tipo = 'URL_SERVICIOS'
-- Update Parametros Generales TRANS-F-041

update parametros_generales set valor_texto = 'http://appqas.ccb.org.co/WCFPUPXrecaudos/Servicio.svc?wsdl' where codigo = 'URLPUP' and tipo = 'URL_SERVICIOS'

-- CON-F-098

-- Permisos CON-F-098

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF098ImpresionPaginasLibrosRegistro';
DELETE FUNCIONALIDAD where nombre='CONF098ImpresionPaginasLibrosRegistro';
insert into FUNCIONALIDAD values ('CONF098ImpresionPaginasLibrosRegistro', 'Impresión Páginas Libros Registro','app/Conciliacion/CONF098','PDL', 'ANGULAR','reportesConc','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF098ImpresionPaginasLibrosRegistro', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO');

-- insert dominio CON-F-098
insert dominio values ('LIBACTA','TIPO_LIBRO','Actas','Acta',null,null)
insert dominio values ('LIBCONS','TIPO_LIBRO','Constancias','Acta',null,null)
insert dominio values ('LIBOTRO','TIPO_LIBRO','Otros Resultados','Acta',null,null)

--Permisos seguridad CON-C-045
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC045ReporteEvaluacionConciliadores';
DELETE FUNCIONALIDAD where nombre='CONC045ReporteEvaluacionConciliadores';
INSERT INTO FUNCIONALIDAD values ('CONC045ReporteEvaluacionConciliadores', 'Reporte evaluación de conciliadores ','app/Conciliacion/CONC045','PDL', 'ANGULAR','reportesConc','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC045ReporteEvaluacionConciliadores', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO', 'JEFECON');
			
											/*** CONTROL DE CAMBIOS 17: Parametrización de plantillas ***/
INSERT INTO DOMINIO ( CODIGO, DOMINIO, NOMBRE, DESCRIPCION)
VALUES ( 'PERESIMC', 'NOMBRE_PLANTILLA_CARTA', 'Respuesta de incumplimiento', 'Plantilla de la respuesta de la petición especial de incumplimiento')

INSERT INTO PLANTILLA_CARTA ( NOMBRE, TIPO_SERVICIO, PLANTILLA_HTML, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, ID_SERVICIO)
VALUES ('PERESIMC', 'PDL', '<html>
<head>
<title>Peticion especial: Expedición de incumplimiento</title>
</head>
<body>
  <font face="Arial" size="3">
    <p align="left">
     ciudadCentroP, dia_cartaP de mes_cartaP de ano_cartaP 
    </p>
    <p align="justify">
      Asunto: Respuesta solicitud de expedición de incumplimiento, caso No. codigoCaso
    </p>
    <p align="justify">Respetado señor(a): nombreSolicitanteP</p>	
	<p align="justify">
		Se ha respondido su solicitud de expedición de incumplimiento radicada el fechaSolicitudP.
	</p>
	<p align="justify">
		Esperamos haber contestado su solicitud. Para información adicional pueden comunicarse al correo electrónico correoConciliadorP
	</p>
    <p align="justify">Cordialmente,</p>
    <p align="justify">&#160;</p>
    <p align="left">conciliadorP.</p>
  </font>
</body>
</html>','USER_SIMASC', SYSDATETIME(), 'ACT', 1);

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('conciliadorP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PERESIMC' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae el nombre completo del conciliador asignador al caso',
'NATIVE: select concat(p.primer_nombre_o_razon_social, rtrim('' ''+p.segundo_nombre), rtrim('' ''+p.primer_apellido), rtrim('' ''+p.segundo_apellido))
from rol_persona_caso rpc 
    inner join persona p on p.id_persona = rpc.id_persona
where id_rol in (select id_rol from tipo_de_servicio_rol where tipo_servicio = ''PDL'')
and rpc.estado not in (''REC'', ''INA'')
and rpc.tipo_nombramiento = ''PRI''
and id_caso = ?1
and rpc.estado_registro = ''ACT''
and p.estado_registro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('nombreSolicitanteP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PERESIMC' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'trae el nombre completo del conciliador asignador al caso',
'NATIVE: select top 1 concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
from peticion p
inner join PARTE_PETICION pp
on pp.id_peticion = p.id_peticion
inner join PERSONA pe
on pe.id_persona = pp.id_persona
where pp.id_persona = ?2
and p.id_caso = ?1
and p.tipo = ''INCUMPLI''
and p.respuesta is not null
and p.estado_registro = ''ACT''
and pp.estado_registro = ''ACT''
and pe.estado_registro = ''ACT''
order by fecha_radicacion desc');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('fechaSolicitudP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PERESIMC' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'fecha de solicitud de la peticion',
'NATIVE: select top 1 FORMAT(p.fecha_radicacion,''dd/MM/yyyy'')
from peticion p
inner join PARTE_PETICION pp
on pp.id_peticion = p.id_peticion
where p.id_caso = ?1
and p.tipo = ''INCUMPLI''
and p.respuesta is not null
order by fecha_radicacion desc');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION)
VALUES ('IPP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PERESIMC' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Identica si la plantilla lleva un tag de <dirigido> con id_persona = ?2' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('correoConciliadorP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PERESIMC' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae el nombre completo del conciliador asignador al caso',
'select ce.direccion
from CorreoElectronico ce
join ce.persona p 
left join p.rolPersonaCasoList rpc
where rpc.rol.idRol in (select tdsr.rol.idRol from TipoDeServicioRol tdsr where tdsr.tipoDeServicioRolPK.tipoServicio = ''PDL'')
and rpc.estado not in (''REC'', ''INA'')
and rpc.tipoNombramiento = ''PRI''
and ce.tipo = ''PRI''
and ce.estadoRegistro = ''ACT''
and rpc.estadoRegistro = ''ACT''
and rpc.caso.idCaso =: idCaso');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('ciudadCentroP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PERESIMC' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'Ciudad donde se encuentra el centro del caso',
'NATIVE: select zg.nombre from zona_geografica zg
INNER JOIN centro ce
ON zg.id_zona_geografica = ce.id_ciudad
INNER JOIN sede se
ON se.id_centro = ce.id_centro 
INNER JOIN caso ca
ON se.id_sede = ca.id_sede
WHERE ca.id_caso = ?1
AND ce.estado_registro = ''ACT''
AND zg.estado_registro = ''ACT''
AND se.estado_registro = ''ACT''
AND ca.estado_registro = ''ACT''');

/** fin de la plantilla incumplimiento **/

INSERT INTO DOMINIO ( CODIGO, DOMINIO, NOMBRE, DESCRIPCION)
VALUES ( 'PEREPRAU', 'NOMBRE_PLANTILLA_CARTA', 'Respuesta de reprogramación de audiencia', 'Plantilla de la respuesta de la petición especial de reprogramación de audiencia')

INSERT INTO PLANTILLA_CARTA ( NOMBRE, TIPO_SERVICIO, PLANTILLA_HTML, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, ID_SERVICIO)
VALUES ('PEREPRAU', 'PDL', '<html>
<head>
<title>Peticion especial: Reprogramación de audiencia</title>
</head>
<body>
  <font face="Arial" size="3">
    <p align="left">
     ciudadCentroP, dia_cartaP de mes_cartaP de ano_cartaP 
    </p>
    <p align="justify">
      Asunto: Respuesta solicitud de reprogramación de audiencia, caso No. codigoCaso
    </p>
    <p align="justify">Respetado señor(a): nombreSolicitanteP</p>	
	<p align="justify">
		En respuesta a su solicitud de reprogramación radicada el fechaSolicitudP se informa que con la anuencia de las partes se procede a reprogramar la audiencia para el próximo fechaAudienciaP a las horaAudienciaP para lo cual se remitirán las comunicaciones respectivas.
	</p>
	<p align="justify">
		Esperamos haber contestado su solicitud. Para información adicional pueden comunicarse al correo electrónico correoConciliadorP
	</p>
    <p align="justify">Cordialmente,</p>
    <p align="justify">&#160;</p>
    <p align="left">conciliadorP</p>
  </font>
</body>
</html>','USER_SIMASC', SYSDATETIME(), 'ACT', 1);

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('conciliadorP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PEREPRAU' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae el nombre completo del conciliador asignador al caso',
'NATIVE: select concat(p.primer_nombre_o_razon_social, rtrim('' ''+p.segundo_nombre), rtrim('' ''+p.primer_apellido), rtrim('' ''+p.segundo_apellido))
from rol_persona_caso rpc 
    inner join persona p on p.id_persona = rpc.id_persona
where id_rol in (select id_rol from tipo_de_servicio_rol where tipo_servicio = ''PDL'')
and rpc.estado not in (''REC'', ''INA'')
and rpc.tipo_nombramiento = ''PRI''
and id_caso = ?1
and rpc.estado_registro = ''ACT''
and p.estado_registro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('nombreSolicitanteP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PEREPRAU' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'trae el nombre completo del conciliador asignador al caso',
'NATIVE: select top 1 concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
from peticion p
inner join PARTE_PETICION pp
on pp.id_peticion = p.id_peticion
inner join PERSONA pe
on pe.id_persona = pp.id_persona
where pp.id_persona = ?2
and p.id_caso = ?1
and p.tipo = ''CAMBFECH''
and p.respuesta is not null
and p.estado_registro = ''ACT''
and pp.estado_registro = ''ACT''
and pe.estado_registro = ''ACT''
order by fecha_radicacion desc');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('fechaSolicitudP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PEREPRAU' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'fecha de solicitud de la peticion',
'NATIVE: select top 1 FORMAT(p.fecha_radicacion,''dd/MM/yyyy'')
from peticion p
inner join PARTE_PETICION pp
on pp.id_peticion = p.id_peticion
where p.id_caso = ?1
and p.tipo = ''CAMBFECH''
and p.respuesta is not null
order by fecha_radicacion desc');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('fechaAudienciaP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PEREPRAU' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'fecha en que se programó la audiencia',
'NATIVE: select FORMAT(au.hora_inicio,''dd/MM/yyyy'')
from AUDIENCIA AU 
where AU.id_caso = ?1
and au.estado = ''PEN''
and au.estado_registro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('horaAudienciaP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PEREPRAU' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'hora en que se programó la audiencia',
'NATIVE: select FORMAT(au.hora_inicio,''HH:mm'')
from AUDIENCIA AU 
where AU.id_caso = ?1
and au.estado = ''PEN''
and au.estado_registro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION)
VALUES ('IPP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PEREPRAU' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Identica si la plantilla lleva un tag de <dirigido> con id_persona = ?2' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('correoConciliadorP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PEREPRAU' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae el nombre completo del conciliador asignador al caso',
'select ce.direccion
from CorreoElectronico ce
join ce.persona p 
left join p.rolPersonaCasoList rpc
where rpc.rol.idRol in (select tdsr.rol.idRol from TipoDeServicioRol tdsr where tdsr.tipoDeServicioRolPK.tipoServicio = ''PDL'')
and rpc.estado not in (''REC'', ''INA'')
and rpc.tipoNombramiento = ''PRI''
and ce.tipo = ''PRI''
and ce.estadoRegistro = ''ACT''
and rpc.estadoRegistro = ''ACT''
and rpc.caso.idCaso =: idCaso');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('ciudadCentroP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PEREPRAU' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'Ciudad donde se encuentra el centro del caso',
'NATIVE: select zg.nombre from zona_geografica zg
INNER JOIN centro ce
ON zg.id_zona_geografica = ce.id_ciudad
INNER JOIN sede se
ON se.id_centro = ce.id_centro 
INNER JOIN caso ca
ON se.id_sede = ca.id_sede
WHERE ca.id_caso = ?1
AND ce.estado_registro = ''ACT''
AND zg.estado_registro = ''ACT''
AND se.estado_registro = ''ACT''
AND ca.estado_registro = ''ACT''');

/*** fin de los inserts de la plantilla de re-programacion ***/

INSERT INTO DOMINIO ( CODIGO, DOMINIO, NOMBRE, DESCRIPCION)
VALUES ( 'PERECACO', 'NOMBRE_PLANTILLA_CARTA', 'Respuesta de cambio de conciliador', 'Plantilla de la respuesta de la petición especial de cambio de conciliador')

INSERT INTO PLANTILLA_CARTA ( NOMBRE, TIPO_SERVICIO, PLANTILLA_HTML, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, ID_SERVICIO)
VALUES ('PERECACO', 'PDL', '<html>
<head>
<title>Peticion especial: Respuesta a solicitud de cambio de conciliador</title>
</head>
<body>
  <font face="Arial" size="3">
    <p align="left">
     ciudadCentroP, dia_cartaP de mes_cartaP de ano_cartaP 
    </p>
    <p align="justify">
      Asunto: Respuesta a solicitud de cambio de conciliador, caso No. codigoCaso
    </p>
    <p align="justify">Respetado señor(a): nombreSolicitanteP</p>	
	<p align="justify">
	  En respuesta a su solicitud de cambio de conciliador radicada el fechaSolicitudP se informa que el centro procedió a efectuarlo y se designó al doctor conciliadorP.
	</p>
	<p align="left">
		Esperamos haber contestado su solicitud. Para información adicional pueden comunicarse a los correos electrónicos: correosInformacionP.
	</p>
    <p align="justify">Cordialmente,</p>
    <p align="justify">&#160;</p>
    <p align="left">nombreCentroP</p>
  </font>
</body>
</html>','USER_SIMASC', SYSDATETIME(), 'ACT', 1);

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('ciudadCentroP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PERECACO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'Ciudad donde se encuentra el centro del caso',
'NATIVE: select zg.nombre from zona_geografica zg
INNER JOIN centro ce
ON zg.id_zona_geografica = ce.id_ciudad
INNER JOIN sede se
ON se.id_centro = ce.id_centro 
INNER JOIN caso ca
ON se.id_sede = ca.id_sede
WHERE ca.id_caso = ?1
AND ce.estado_registro = ''ACT''
AND zg.estado_registro = ''ACT''
AND se.estado_registro = ''ACT''
AND ca.estado_registro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('nombreSolicitanteP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PERECACO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'trae el nombre completo del conciliador asignador al caso',
'NATIVE: select top 1 concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
from peticion p
inner join PARTE_PETICION pp
on pp.id_peticion = p.id_peticion
inner join PERSONA pe
on pe.id_persona = pp.id_persona
where pp.id_persona = ?2
and p.id_caso = ?1
and p.tipo = ''CAMBCON''
and p.respuesta is not null
and p.estado_registro = ''ACT''
and pp.estado_registro = ''ACT''
and pe.estado_registro = ''ACT''
order by fecha_radicacion desc');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('fechaSolicitudP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PERECACO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'fecha de solicitud de la peticion',
'NATIVE: select top 1 FORMAT(p.fecha_radicacion,''dd/MM/yyyy'')
from peticion p
inner join PARTE_PETICION pp
on pp.id_peticion = p.id_peticion
where p.id_caso = ?1
and p.tipo = ''CAMBCON''
and p.respuesta is not null
order by fecha_radicacion desc');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION)
VALUES ('IPP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PERECACO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Identica si la plantilla lleva un tag de <dirigido> con id_persona = ?2' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('conciliadorP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PERECACO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae el nombre completo del conciliador asignador al caso',
'NATIVE: select concat(p.primer_nombre_o_razon_social, rtrim('' ''+p.segundo_nombre), rtrim('' ''+p.primer_apellido), rtrim('' ''+p.segundo_apellido))
from rol_persona_caso rpc 
    inner join persona p on p.id_persona = rpc.id_persona
where id_rol in (select id_rol from tipo_de_servicio_rol where tipo_servicio = ''PDL'')
and rpc.estado not in (''REC'', ''INA'')
and rpc.tipo_nombramiento = ''PRI''
and id_caso = ?1
and rpc.estado_registro = ''ACT''
and p.estado_registro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('nombreCentroP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PERECACO' and tipo_servicio = 'PDL'),
'USER_SIMASC', SYSDATETIME(), 'ACT', 'Nombre del centro donde se encuentra el caso ',
'NATIVE: select ce.nombre from caso c left outer join sede s on s.id_sede = c.id_sede left outer join centro ce on ce.id_centro = s.id_centro where c.id_caso = ?1 and c.estado_registro = ''ACT'' and s.estado_registro = ''ACT'' and ce.estado_registro = ''ACT'' ');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('correosInformacionP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PERECACO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'trae la dirección de correo electrónico de las personas que daran mas información por el cambio de conciliador',
'NATIVE: select ce.direccion from Rol_Persona rp
inner join persona p
on p.id_persona = rp.id_persona
inner join centro centro
on centro.id_centro = rp.id_centro
inner join sede se
on se.id_centro = centro.id_centro
inner join caso ca
on ca.id_sede = se.id_sede
inner join correo_electronico ce
on ce.id_persona = p.id_persona
where ca.id_caso = ?1
and rp.id_rol in (select id_rol from rol 
					where nombre in (select value from string_split((select valor_texto 
										from PARAMETROS_GENERALES 
										where codigo = ''RTPCCCD'' 
										and tipo = ''ROL_TIPO_PETICION'' 
										and estado_registro = ''ACT''), '','') ))
and rp.fecha_inicio_vigencia <= GETDATE()
and rp.fecha_fin_vigencia is null
and ce.tipo = ''PRI''
and rp.estado_registro = ''ACT'' 
and p.estado_registro = ''ACT''
and centro.estado_registro = ''ACT''
and se.estado_registro = ''ACT''
and ca.estado_registro = ''ACT''
and ce.estado_registro = ''ACT''');

INSERT PARAMETROS_GENERALES (codigo, editable, tipo, nombre, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
VALUES ('RTPCCCD', 1, 'ROL_TIPO_PETICION', 'Roles de la carta de respuesta cambio conciliador', 'JEFECON,SECCON',
		'Los roles que estén en este parámetro les aparecerá su dirección de correo electrónico en la carta de respuesta de la petición de cambio de conciliador, los códigos de los roles que se pongan en este parámetro deben estar separados por coma (,)',
		'USER_SIMASC', GETDATE(), 'ACT');

/*** fin de los inserts de la plantilla de cambio de conciliador ***/
		
INSERT INTO DOMINIO ( CODIGO, DOMINIO, NOMBRE, DESCRIPCION)
VALUES ( 'CITAJORN', 'NOMBRE_PLANTILLA_CARTA', 'Citación a jornada', 'Plantilla de la citación a jornada de conciliación');

INSERT INTO PLANTILLA_CARTA ( NOMBRE, TIPO_SERVICIO, PLANTILLA_HTML, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, ID_SERVICIO)
VALUES ('CITAJORN', 'PDL', '<html>
<head>
<title>Citación a jornada</title>
</head>
<body>
<font FACE="Arial">
<p align="left">ciudadCentroP, dia_cartaP de mes_cartaP de ano_cartaP</p>
<p align="justify">&#160;</p>
<p align="left">Señor(a): <br/>
				dirigidoP
				direccionEnvio
				telefonoEnvio
				correoElectronicoP
</p>

<p align="justify">&#160;</p>
<p align="left">REFERENCIA: SOLICITUD DE CONCILIACION DE convocanteP PARA SOLUCIONAR LAS DIFERENCIAS SURGIDAS CON convocadoP.</p>
<p align="justify">Apreciado(a) Señor(a):</p>
<p align="justify">Hemos recibido comunicación en la cual se solicita una audiencia de Conciliación con relación a: casoHechosP</p>
<p align="justify">El nombreCentroConvenioP está llevando a cabo la nombreJornadaP, que constituye el más importante servicio social que la comunidad conciliatoria le aporta a la ciudadanía, toda vez que les brinda la posibilidad de acceder de manera gratuita a la justicia alternativa y obtener con la ayuda de sus mejores operadores decisiones justas.</p>
<p align="justify">Queremos comentar que, para este Centro de Arbitraje y Conciliación, es un honor prestarle nuestros servicios de manera gratuita.  Por lo tanto, atentamente lo invitamos a una audiencia de Conciliación el próximo fechaAudienciaJornadaP, en el nombreCentroConvenioP, ubicado en la direccionConvenioP.</p>
<p align="justify">Así mismo le informamos que de conformidad con lo dispuesto en el parágrafo 2º del artículo 1º de la Ley 640 de 2001, deberá asistir a la audiencia de Conciliación, de lo contrario su inasistencia puede ser considerada como indicio grave en contra de sus pretensiones o de sus excepciones de mérito en un eventual proceso judicial que verse sobre los mismos hechos, de acuerdo a lo establecido en el artículo 22 de la misma ley.</p>
<p align="justify">Le recomendamos por favor, que <b>no olvide su documento de identificación</b>, que disponga de tiempo para atender la audiencia, que esté 10 minutos antes de la hora señalada y que allegue los documentos que resulten pertinentes con la controversia como registros civiles de nacimiento, de matrimonio, certificados de tradición y libertad, contratos y los demás que sean necesarios para atender adecuadamente su conflicto.</p>
<p align="left">Cordialmente,</p>
<p align="justify">&#160;</p>
<p align="justify">&#160;</p>
<p align="left">nombreCentroConvenioP</p>
  <p align="right">
    CODIGO CASO: codigoCaso
  </p>
 </font></body>
</html>', 'USER_SIMASC', SYSDATETIME(), 'ACT', 9);

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('ciudadCentroP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CITAJORN' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'Ciudad donde se encuentra el centro del caso',
'select zg.nombre from Caso ca
join ca.convenio co
join co.centro ce
join ce.zonaGeografica zg
where ca.idCaso =: idCaso
and ca.estadoRegistro = ''ACT''
and co.estadoRegistro = ''ACT''
and ce.estadoRegistro = ''ACT''
and zg.estadoRegistro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('dirigidoP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CITAJORN' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'nombre de la persona a la que se envia la carta',
'NATIVE: select concat(p.primer_nombre_o_razon_social, rtrim('' ''+p.segundo_nombre), rtrim('' ''+p.primer_apellido), rtrim('' ''+p.segundo_apellido), ''<br/>'')
from Persona p left join ROL_PERSONA_CASO prpc 
on prpc.id_persona = p.id_persona where prpc.id_caso=?1
AND prpc.estado_registro=''ACT'' and p.id_persona=?2' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('correoElectronicoP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CITAJORN' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'dirección de correo electronico de la persona a la que se envia la carta',
'select case when ce.direccion is not null then concat(ce.direccion, ''<br/>'') else '''' end
from CorreoElectronico ce
join ce.persona p 
left join p.rolPersonaCasoList rpc
where ce.idPersona =: idPersona
and ce.tipo = ''PRI''
and ce.estadoRegistro = ''ACT''
and rpc.estadoRegistro = ''ACT''
and rpc.estado <> ''INA''
and rpc.caso.idCaso =: idCaso ' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('casoHechosP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CITAJORN' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Hechos del caso, consultada de la tabla ''caso'' el campo ''hechos'' ', 'select c.hechos from Caso c where c.idCaso = :idCaso and c.estadoRegistro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('nombreConvenioP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CITAJORN' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'nombre del convenio del caso',
'select co.nombre from Caso ca
join ca.convenio co
where ca.idCaso =: idCaso
and ca.estadoRegistro = ''ACT''
and co.estadoRegistro = ''ACT''' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('fechaAudienciaJornadaP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CITAJORN' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'fecha audiencia de una jornada',
'NATIVE: SET LANGUAGE Spanish SELECT CONCAT( DATEPART(DAY, au.hora_inicio ), '' de '',
									DATENAME(MONTH,au.hora_inicio), '' de '',
									DATENAME(YEAR,au.hora_inicio), '', a las '',
									FORMAT(au.hora_inicio,''HH:mm'') )
FROM Audiencia au
WHERE au.id_caso = ?1
AND au.id_audiencia = ?3' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('nombreCentroConvenioP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CITAJORN' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'nombre del centro del convenio del caso',
'select ce.nombre from Caso ca
join ca.convenio co
join co.centro ce
where ca.idCaso =: idCaso
and co.estadoRegistro = ''ACT''
and ce.estadoRegistro = ''ACT''' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION)
VALUES ('IDA', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CITAJORN' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Identica si la plantilla necesita el id_audiencia con ?3' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION)
VALUES ('IPP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CITAJORN' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Identica si la plantilla lleva un tag de <dirigido> con id_persona = ?2' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('direccionConvenioP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CITAJORN' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'direccion del convenio del caso',
'select co.direccion from Caso ca
join ca.convenio co
where ca.idCaso =: idCaso
and co.estadoRegistro = ''ACT''' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocanteP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CITAJORN' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocantes en una sola linea separados por comas',
'NATIVE: select stuff((select '', '', UPPER( concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido)) )
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre in (''CONVOTE'') and estado_registro = ''ACT'')
		AND rp.estado <> ''INA''
        FOR xml PATH ('''')), 1, 2, '''') AS convocantes
from caso c
where id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocadoP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CITAJORN' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocados en una sola linea separados por comas',
'NATIVE: select stuff((select '', '', UPPER( concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido)) )
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre in (''CONVODO'') and estado_registro = ''ACT'')
		AND rp.estado <> ''INA''
        FOR xml PATH ('''')), 1, 2, '''') AS convocados
from caso c
where id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('nombreJornadaP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CITAJORN' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'nombre de la jornada del caso',
'select co.nombre from Caso ca
join ca.convenio co
where ca.idCaso =: idCaso
and co.estadoRegistro = ''ACT''' );

/*** Fin de los inserts de la plantilla de citacion a jornadas ***/

/*** plantilla de ACTA DE AUDIENCIA DE CONCILIACIÓN **/
INSERT INTO DOMINIO ( CODIGO, DOMINIO, NOMBRE, DESCRIPCION)
VALUES ( 'ACTACON', 'NOMBRE_PLANTILLA_CARTA', 'Acta de audiencia de conciliación', 'Plantilla del acta de audiencia de conciliación');

INSERT INTO PLANTILLA_CARTA ( NOMBRE, TIPO_SERVICIO, PLANTILLA_HTML, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, ID_SERVICIO)
VALUES ('ACTACON', 'PDL', '<html>
<head>
<title>Acta de Conciliación</title>
</head>
<body>
<b><font face="Arial"><p align="center">ACTA DE AUDIENCIA DE CONCILIACIÓN</p></font></b>
<p align="justify">&#160;</p>
<p align="justify">En la ciudad de ciudadCentroP a los dia_cartaP días del mes de mes_cartaP ante conciliadorP, quien fue designado(a) por el Director del Centro como Conciliador(a), para que actuara de conformidad con lo dispuesto en el art. 116 de la Constitución Política, Ley 23 de 1991, Ley 446 de 1998, Ley 640 de 2001 y demás normas complementarias, comparecieron las siguientes personas: </p>
<b><p align="left">Parte Convocante:</p></b>
convocantesAsistentesP
<b><p align="left">Parte Convocada:</p></b>
convocadosAsistentesP
<p align="justify">Los comparecientes han manifestado que su asistencia a la diligencia ha sido voluntaria y libre de presiones y que sus decisiones tienen el mismo carácter.</p>
<b><p align="center">HECHOS:</p></b>
<p align="justify">PRIMERO. Que el Señor(a) convocanteP le solicitó el día fechaCasoP a este Centro citar a convocadoP, con el efecto de efectuar una audiencia de conciliación para solucionar diferencias en materia materiaCasoP surgidas con relación a casoHechosP.</p>
<p align="justify">SEGUNDO. </p>
<p align="justify">TERCERO. </p>
<p align="justify">CUARTO. Los comparecientes de manera voluntaria y sin presiones llegan a un acuerdo el cual se plasma a continuación de los considerandos bajo estas condiciones y bajo su responsabilidad, una vez el conciliador explica claramente los efectos del presente documento, quienes manifestaron no tener duda alguna para firmar la presente acta.</p> 
<b><p align="center">CONSIDERANDO</p></b>
<p align="justify">Que el nombreCentroP fue autorizado para funcionar por la Resolución resolucionP del Ministerio de Justicia y del Derecho.</p>
<p align="justify">Que el nombreCentroP, en ejercicio de las funciones atribuidas por la Ley 23 de 1991, Ley 446 de 1998, Ley 640 de 2001, ha designado un conciliador que colabore con las facultades otorgadas por el artículo 116 de la Constitución Política de Colombia.</p>
<p align="justify">Una vez escuchadas las manifestaciones de las partes y analizadas y discutidas las propuestas presentadas por ellas, y para que se surtan los efectos previstos en los artículos 2469 y concordantes del Código Civil, Ley 23 de 1991, el Art. 66 de la Ley 446 de 1998, Ley 640 de 2001, y demás disposiciones complementarias, se ha llegado a un acuerdo conciliatorio que hace tránsito a <b>COSA JUZGADA Y PRESTA MERITO EJECUTIVO</b>, en los siguientes términos:</p>
<b><p align="center">ACUERDO</p></b>
<p align="justify">PRIMERO. </p>

obligacionesP

<p align="justify">SEGUNDO. </p>
<p align="justify">TERCERO. </p>
<b><p align="center">PARAGRAFO FINAL</p></b>

<p align="justify">Las partes se declaran a paz y salvo mutuamente y por lo tanto renuncian a iniciar cualquier reclamación judicial y extrajudicial, por la vía civil, comercial, laboral o penal, relativa al diferendo relacionado en este acuerdo siempre y cuando se cumplan por las partes, las obligaciones en este documento consignadas.</p>
<p align="justify">Para constancia se forma por quienes en ella intervinieron en la ciudad de ciudadCentroP a los dia_cartaP días del mes de mes_cartaP de ano_cartaP.</p>
<p align="justify">&#160;</p>
<p align="justify">&#160;</p>
<font face="Arial">
	<p align="left">firmaConvocantesP</p>		

	<p align="left">firmaConvocadosP</p>		

	<p align="left"><img src=""></img></p>
	<p align="justify">conciliadorP<br/>Conciliador<br/>Registro: registroConciliadorP</p>
	  <p align="justify">
		Caso: codigoCaso
	  </p>
</font>
</body>
</html>','USER_SIMASC', SYSDATETIME(), 'ACT', 1);

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('ciudadCentroP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'Ciudad donde se encuentra el centro del caso',
'NATIVE: select zg.nombre from zona_geografica zg
INNER JOIN centro ce
ON zg.id_zona_geografica = ce.id_ciudad
INNER JOIN sede se
ON se.id_centro = ce.id_centro 
INNER JOIN caso ca
ON se.id_sede = ca.id_sede
WHERE ca.id_caso = ?1
AND ce.estado_registro = ''ACT''
AND zg.estado_registro = ''ACT''
AND se.estado_registro = ''ACT''
AND ca.estado_registro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('conciliadorP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae el nombre completo del conciliador asignador al caso',
'NATIVE: select concat(p.primer_nombre_o_razon_social, rtrim('' ''+p.segundo_nombre), rtrim('' ''+p.primer_apellido), rtrim('' ''+p.segundo_apellido))
from rol_persona_caso rpc 
    inner join persona p on p.id_persona = rpc.id_persona
where id_rol in (select id_rol from tipo_de_servicio_rol where tipo_servicio = ''PDL'')
and rpc.estado not in (''REC'', ''INA'')
and rpc.tipo_nombramiento = ''PRI''
and id_caso = ?1
and rpc.estado_registro = ''ACT''
and p.estado_registro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocantesAsistentesP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae informacion de la parte que asistió a la audiencia',
'NATIVE: LIST: select concat( ''<td>'', pe.primer_nombre_o_razon_social,
								  rtrim('' ''+pe.segundo_nombre),
								  rtrim('' ''+pe.primer_apellido),
								  rtrim('' ''+pe.segundo_apellido),
								  '', quien obra (EN SU PROPIO NOMBRE O EN NOMBRE Y REPRESENTACIÓN O COMO APODERADO DE), mayor de edad,  identificado(a) como aparece al pie de su firma'',
								  '' y domiciliado(a) en la ciudad de ''
								  +(select top 1 zg.nombre from ubicacion u
									INNER JOIN ZONA_GEOGRAFICA zg ON u.id_zona_geografica = zg.id_zona_geografica
									where u.id_persona = pe.id_persona and u.estado_registro = ''ACT'' order by u.fecha_ultima_modificacion desc),''.</td>'') 
        FROM ROL_PERSONA_CASO rpc
        LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona
		INNER JOIN AUDIENCIA au ON rpc.id_caso = au.id_caso
		WHERE au.id_caso = ?1
		AND au.id_audiencia = ?2
		AND rpc.estado_registro = ''ACT''
		AND rpc.id_persona not in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and estado_registro = ''ACT'' )
        AND rpc.id_rol IN (select r.id_rol from rol r where r.nombre in (''CONVOTE'', ''APODCTE'') and r.estado_registro = ''ACT'')
		AND rpc.estado <> ''INA''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocadosAsistentesP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae informacion de la parte que asistió a la audiencia',
'NATIVE: LIST: select concat( ''<td>'', pe.primer_nombre_o_razon_social,
								  rtrim('' ''+pe.segundo_nombre),
								  rtrim('' ''+pe.primer_apellido),
								  rtrim('' ''+pe.segundo_apellido),
								  '', quien obra (EN SU PROPIO NOMBRE O EN NOMBRE Y REPRESENTACIÓN O COMO APODERADO DE), mayor de edad,  identificado(a) como aparece al pie de su firma'',
								  '' y domiciliado(a) en la ciudad de ''
								  +(select top 1 zg.nombre from ubicacion u
									INNER JOIN ZONA_GEOGRAFICA zg ON u.id_zona_geografica = zg.id_zona_geografica
									where u.id_persona = pe.id_persona and u.estado_registro = ''ACT'' order by u.fecha_ultima_modificacion desc),''.</td>'') 
        FROM ROL_PERSONA_CASO rpc
        LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona
		INNER JOIN AUDIENCIA au ON rpc.id_caso = au.id_caso
		WHERE au.id_caso = ?1
		AND au.id_audiencia = ?2
		AND rpc.estado_registro = ''ACT''
		AND rpc.id_persona not in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and estado_registro = ''ACT'' )
        AND rpc.id_rol IN (select r.id_rol from rol r where r.nombre in (''CONVODO'',''APODCDO'') and r.estado_registro = ''ACT'')
		AND rpc.estado <> ''INA''');
		
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocanteP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocantes en una sola linea separados por comas',
'NATIVE: select stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre in (''CONVOTE'') and estado_registro = ''ACT'' )
		AND rp.estado <> ''INA''
        FOR xml PATH ('''')), 1, 2, '''') AS convocantes
from caso c
where id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocadoP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocados en una sola linea separados por comas',
'NATIVE: select stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre in (''CONVODO'') and estado_registro = ''ACT'')
		AND rp.estado <> ''INA''
        FOR xml PATH ('''')), 1, 2, '''') AS convocados
from caso c
where id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('fechaCasoP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'Fecha de radicación del caso, consultada de la tabla ''caso'' el campo ''fecha_radicacion''',
'NATIVE: SELECT FORMAT(c.fecha_radicacion,''dd/MM/yyyy'') from CASO C where C.id_caso = ?1 and c.estado_registro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('materiaCasoP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'Nombre de la materia del caso',
'select m.nombre from Materia m, Caso c where c.idMateria = m.idMateria and m.estadoRegistro = ''ACT'' and c.idCaso = : idCaso');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('casoHechosP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Hechos del caso, consultada de la tabla ''caso'' el campo ''hechos'' ', 'select c.hechos from Caso c where c.idCaso = :idCaso and c.estadoRegistro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('nombreCentroP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'),
'USER_SIMASC', SYSDATETIME(), 'ACT', 'Nombre del centro donde se encuentra el caso ',
'NATIVE: select ce.nombre from caso c left outer join sede s on s.id_sede = c.id_sede left outer join centro ce on ce.id_centro = s.id_centro where c.id_caso = ?1 and c.estado_registro = ''ACT'' and s.estado_registro = ''ACT'' and ce.estado_registro = ''ACT'' ');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('resolucionP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'),
'USER_SIMASC', SYSDATETIME(), 'ACT', 'Resolucion del centro donde se encuentra el caso ',
'select ce.resolucion from Caso ca
join ca.sede se
join se.centro ce
where ca.idCaso =: idCaso');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('obligacionesP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca las obligaciones de los resultados de la audiencia',
'NATIVE: LIST: SELECT case when o.tipo_obligacion = ''OBLDAR''  then concat( ''<td>'', stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
													FROM OBLIGACION_PARTE op
													INNER JOIN PERSONA pe
													ON op.id_persona = pe.id_persona
													WHERE op.tipo_parte_resultado = ''OBLPPAGA''
													AND op.estado_registro = ''ACT''
													AND op.id_resultado_audiencia = ra.id_resultado_audiencia
													FOR xml PATH ('''')), 1, 2, ''''), 

													'' se compromete a pagar a '',

													stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
													FROM OBLIGACION_PARTE op
													INNER JOIN PERSONA pe
													ON op.id_persona = pe.id_persona
													WHERE op.tipo_parte_resultado = ''OBLPRECI''
													AND op.estado_registro = ''ACT''
													AND op.id_resultado_audiencia = ra.id_resultado_audiencia
													FOR xml PATH ('''')), 1, 2, ''''),

													'' la suma de '', 
													o.valor_total_acuerdo, '' en '',
													(select COUNT(*) from CUOTA where id_resultado_audiencia = ra.id_resultado_audiencia), 
													'' cuotas en '', 
													(select nombre from DOMINIO where codigo = o.forma_de_pago and dominio = ''FORMA_PAGO_OBLIGACION''), 
													'' las cuales serán canceladas en '', o.direccion, 
													''  en las siguientes fechas: '',
													replace(stuff((select ''.<br/>'' ,concat( ''Cuota No. '', numero_cuota, '': El '', FORMAT(fecha_prevista,''dd/MM/yyyy'') ) 
													FROM CUOTA 
													WHERE id_resultado_audiencia = ra.id_resultado_audiencia
													FOR xml PATH ('''')), 1, 1, ''''), ''&lt;br/&gt;'', ''<br/>''),
													''.<br/>'', o.observaciones, ''</td>'')
			when o.tipo_obligacion = ''OBLHACER'' then concat( ''<td>'', stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
														FROM OBLIGACION_PARTE op
														INNER JOIN PERSONA pe
														ON op.id_persona = pe.id_persona
														WHERE op.tipo_parte_resultado = ''OBLPHACE''
														AND op.estado_registro = ''ACT''
														AND op.id_resultado_audiencia = ra.id_resultado_audiencia
														FOR xml PATH ('''')), 1, 2, ''''),
														'' se compromete a '', o.compromiso,
														''. La fecha de cumplimiento del acuerdo es el '',
														FORMAT(o.fecha_compromiso,''dd/MM/yyyy''), '' en '',
														o.direccion, ''.<br/> '', o.observaciones, ''</td>'')
		end
FROM AUDIENCIA au
INNER JOIN RESULTADO_AUDIENCIA ra
ON ra.id_audiencia = au.id_audiencia
INNER JOIN OBLIGACION o
ON ra.id_resultado_audiencia = o.id_resultado_audiencia
WHERE au.id_caso = ?1
AND au.id_audiencia = ?2
AND au.estado_registro = ''ACT''
AND ra.estado_registro = ''ACT''
AND o.estado_registro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('firmaConvocantesP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocados en una sola linea separados por comas',
'NATIVE: LIST: SELECT concat( ''<br/><td>'', pe.primer_nombre_o_razon_social,
								  rtrim('' ''+pe.segundo_nombre),
								  rtrim('' ''+pe.primer_apellido),
								  rtrim('' ''+pe.segundo_apellido), ''<br/>'',
								  pe.tipo_documento+'': ''+pe.numero_documento+''<br/>'',
								  case when rpc.id_rol = ( select r.id_rol from rol r where r.nombre = ''APODCTE'' and r.estado_registro = ''ACT'' )
										then ''T.P: ''+pe.numero_tarjeta_profesional+''<br/>'' 
								  end, ''</td>'' ) 
FROM ROL_PERSONA_CASO rpc
LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona
INNER JOIN AUDIENCIA au ON rpc.id_caso = au.id_caso
WHERE au.id_caso = ?1
AND au.id_audiencia = ?2
AND rpc.estado_registro = ''ACT''
AND rpc.id_persona not in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and estado_registro = ''ACT'' )
AND rpc.id_rol IN (select r.id_rol from rol r where r.nombre in (''CONVOTE'', ''APODCTE'') and r.estado_registro = ''ACT'')
AND rpc.estado <> ''INA''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('firmaConvocadosP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocados en una sola linea separados por comas',
'LIST: NATIVE: SELECT concat( ''<br/><td>'', pe.primer_nombre_o_razon_social,
								  rtrim('' ''+pe.segundo_nombre),
								  rtrim('' ''+pe.primer_apellido),
								  rtrim('' ''+pe.segundo_apellido), ''<br/>'',
								  pe.tipo_documento+'': ''+pe.numero_documento+''<br/>'',
								  case when rpc.id_rol = ( select r.id_rol from rol r where r.nombre = ''APODCDO'' and r.estado_registro = ''ACT'' )
										then ''T.P: ''+pe.numero_tarjeta_profesional+''<br/>'' 
								  end, ''</td>'' ) 
FROM ROL_PERSONA_CASO rpc
LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona
INNER JOIN AUDIENCIA au ON rpc.id_caso = au.id_caso
WHERE au.id_caso = ?1
AND au.id_audiencia = ?2
AND rpc.estado_registro = ''ACT''
AND rpc.id_persona not in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and estado_registro = ''ACT'' )
AND rpc.id_rol IN (select r.id_rol from rol r where r.nombre in (''CONVODO'',''APODCDO'') and r.estado_registro = ''ACT'')
AND rpc.estado <> ''INA''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('registroConciliadorP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'registro del conciliador, se encuentra en la tabla persona', '
NATIVE: SELECT p.registro_conciliador FROM persona p 
INNER JOIN rol_persona_caso rpc
ON rpc.id_persona = p.id_persona
AND rpc.estado in ( ''ACE'', ''POR'' )
AND rpc.tipo_nombramiento = ''PRI''
INNER JOIN rol r 
ON rpc.id_rol = r.id_rol
AND r.id_rol in (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = ''PDL'' and estado_registro = ''ACT'')
WHERE rpc.id_caso = ?1
AND p.estado_registro = ''ACT''
AND rpc.estado_registro = ''ACT''
AND r.estado_registro = ''ACT'' ');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION)
VALUES ('IDA', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Identica si la plantilla necesita el id_audiencia con ?2' );

/**** fin de los inserts del acta de conciliacion ***/

/*** Inserts de la plantilla de inasistencia a audiencia con excusa **/ 
INSERT INTO DOMINIO ( CODIGO, DOMINIO, NOMBRE, DESCRIPCION)
VALUES ( 'CONINACE', 'NOMBRE_PLANTILLA_CARTA', 'Constancia de inasistencia con excusa', 'Plantilla de la constancia de inasistencia con excusa a una audiencia')

INSERT INTO PLANTILLA_CARTA ( NOMBRE, TIPO_SERVICIO, PLANTILLA_HTML, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO)
VALUES ('CONINACE', 'PDL', '<html>
<head>
<title>Constancia de Inasistencia con Excusa</title>
</head>
<body>
<font FACE="Arial"><b>
<p align="center">CONSTANCIA DE INASISTENCIA</p>
<p align="justify">&#160;</p>
<p align="center">EL (LA) SUSCRITO (A) CONCILIADOR(A) INSCRITO(A) EN LA LISTA OFICIAL DE CONCILIADORES DEL nombreCentroP</p>
<p align="center">&#160;</p>
<p align="center">HACE CONSTAR:</p>
<p align="justify">&#160;</p>
</b><p align="justify">Que de conformidad con lo dispuesto en el artículo 2º de la Ley 640  de 2001, se procede a suscribir la presente constancia en los siguientes términos:</p>
<p align="justify">convocanteP, le solicitó el pasado fechaCasoP, a este Centro, la citación de convocadoP, a una Audiencia de Conciliación, a fin de solucionar diferencias surgidas con ocasión de casoHechosP</p>
<p align="justify">El (La) suscrito (a) conciliador (a) tras haber sido designado (a) por el Director del Centro, procedió a citar a las partes el fechaAudienciaP a las horaAudienciaP reunión a la que asistieron parteAsistenciaP</p>
<p align="justify">Dentro del término legal establecido para el efecto, mediante comunicación (escrita o vía fax, o por email)  (FECHA EXCUSA) de los corrientes, el(la) (PARTE QUE SE EXCUSA), justificó su inasistencia, aduciendo (CAUSA) , que le impidieron asistir a la audiencia.</p>
<p align="justify">En consecuencia y de conformidad con el artículo 22 de la Ley 640 de 2001, se procede a suscribir la presente constancia.</p>
<p align="justify">Dada en ciudadCentroP, dia_cartaP de mes_cartaP de ano_cartaP.</p>
<p align="justify">&#160;</p>
<p align="left"><img src=""></img></p>
<p align="justify">conciliadorP<br/>Conciliador<br/>Registro: registroConciliadorP</p>
  <p align="justify">
    Caso: codigoCaso
  </p>
  </font></body>
</html>','USER_SIMASC', SYSDATETIME(), 'ACT');

--nombreCentroP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('nombreCentroP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINACE' and tipo_servicio = 'PDL'),
'USER_SIMASC', SYSDATETIME(), 'ACT', 'Nombre del centro donde se encuentra el caso ',
'NATIVE: select UPPER(ce.nombre) from caso c left outer join sede s on s.id_sede = c.id_sede left outer join centro ce on ce.id_centro = s.id_centro where c.id_caso = ?1 and c.estado_registro = ''ACT'' and s.estado_registro = ''ACT'' and ce.estado_registro = ''ACT'' ');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocanteP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINACE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocantes en una sola linea separados por comas',
'NATIVE: select stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre in (''CONVOTE'') and estado_registro = ''ACT'')
		AND rp.estado <> ''INA''
        FOR xml PATH ('''')), 1, 2, '''') AS convocantes
from caso c
where id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocadoP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINACE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocados en una sola linea separados por comas',
'NATIVE: select stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre in (''CONVODO'') and estado_registro = ''ACT'')
		AND rp.estado <> ''INA''
        FOR xml PATH ('''')), 1, 2, '''') AS convocados
from caso c
where id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('conciliadorP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINACE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae el nombre completo del conciliador asignador al caso',
'NATIVE: select concat(p.primer_nombre_o_razon_social, rtrim('' ''+p.segundo_nombre), rtrim('' ''+p.primer_apellido), rtrim('' ''+p.segundo_apellido))
from rol_persona_caso rpc 
    inner join persona p on p.id_persona = rpc.id_persona
where id_rol in (select id_rol from tipo_de_servicio_rol where tipo_servicio = ''PDL'')
and rpc.estado not in (''REC'', ''INA'')
and rpc.tipo_nombramiento = ''PRI''
and id_caso = ?1
and rpc.estado_registro = ''ACT''
and p.estado_registro = ''ACT''');

--fechaCasoP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('fechaCasoP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINACE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'Fecha de radicación del caso, consultada de la tabla ''caso'' el campo ''fecha_radicacion''',
'NATIVE: SELECT FORMAT(c.fecha_radicacion,''dd/MM/yyyy'') from CASO C where C.id_caso = ?1 and c.estado_registro = ''ACT''');

--casoHechosP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('casoHechosP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINACE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Hechos del caso, consultada de la tabla ''caso'' el campo ''hechos'' ', 'select c.hechos from Caso c where c.idCaso = :idCaso and c.estadoRegistro = ''ACT''');

--fechaAudienciaP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('fechaAudienciaP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINACE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'fecha en que se hará la audiencia, fecha colsultada de la tabla audiencia del campo hora_inicio', 'NATIVE: SELECT FORMAT(au.hora_inicio,''dd/MM/yyyy'') from AUDIENCIA AU where AU.id_caso = ?1 and au.id_audiencia = ?2');

--horaAudienciaP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('horaAudienciaP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINACE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'hora en que se hará la audiencia, hora colsultada de la tabla audiencia del campo hora_inicio', 'NATIVE: SELECT FORMAT(au.hora_inicio,''HH:mm'') from AUDIENCIA AU where AU.id_caso = ?1 and au.id_audiencia = ?2');

--parteAsistenciaP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('parteAsistenciaP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINACE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'las partes que asistieron a la audiencia en una sola linea y separados por comas',
'NATIVE: select stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
        FROM ROL_PERSONA_CASO rpc
        LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona 
        WHERE rpc.id_caso = au.id_caso 
        AND rpc.estado_registro = ''ACT''
		AND rpc.id_persona not in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and estado_registro = ''ACT'' )
        AND rpc.id_rol IN (select id_rol from rol where nombre in (SELECT codigo_clasificado FROM CLASIFICADOR_DOMINIO  WHERE CODIGO_CLASIFICADOR = ''PARTAPP'')
							and estado <> ''INA'' and estado_registro = ''ACT'')
        FOR xml PATH ('''')), 1, 2, '''') AS asistentes
from AUDIENCIA au
where au.id_caso = ?1
and au.id_audiencia = ?2');

--ciudadCentroP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('ciudadCentroP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINACE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'Ciudad donde se encuentra el centro del caso',
'NATIVE: select zg.nombre from zona_geografica zg
INNER JOIN centro ce
ON zg.id_zona_geografica = ce.id_ciudad
INNER JOIN sede se
ON se.id_centro = ce.id_centro 
INNER JOIN caso ca
ON se.id_sede = ca.id_sede
WHERE ca.id_caso = ?1
AND ce.estado_registro = ''ACT''
AND zg.estado_registro = ''ACT''
AND se.estado_registro = ''ACT''
AND ca.estado_registro = ''ACT''');

--registroConciliadorP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('registroConciliadorP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINACE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'registro del conciliador, se encuentra en la tabla persona', '
NATIVE: SELECT p.registro_conciliador FROM persona p 
INNER JOIN rol_persona_caso rpc
ON rpc.id_persona = p.id_persona
AND rpc.estado in ( ''ACE'', ''POR'' )
AND rpc.tipo_nombramiento = ''PRI''
INNER JOIN rol r 
ON rpc.id_rol = r.id_rol
AND r.id_rol in (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = ''PDL'' and estado_registro = ''ACT'')
WHERE rpc.id_caso = ?1
AND p.estado_registro = ''ACT''
AND rpc.estado_registro = ''ACT''
AND r.estado_registro = ''ACT'' ');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION)
VALUES ('IDA', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINACE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Identica si la plantilla necesita el id_audiencia con ?2' );

/**** fin de los inserts de la plantilla de inasistencia con excusa ***/

/*** plantilla de constancia inasistencia sin excusa **/
INSERT INTO DOMINIO ( CODIGO, DOMINIO, NOMBRE, DESCRIPCION)
VALUES ( 'CONINASE', 'NOMBRE_PLANTILLA_CARTA', 'Constancia de inasistencia sin excusa', 'Plantilla de la constancia de inasistencia sin excusa a una audiencia')

INSERT INTO PLANTILLA_CARTA ( NOMBRE, TIPO_SERVICIO, PLANTILLA_HTML, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO)
VALUES ('CONINASE', 'PDL', '<html>
<head>
<title>Constancia de Inasistencia sin Excusa</title>
</head>
<body>
<font face="Arial">
<b><p align="center">CONSTANCIA DE INASISTENCIA</p>
<p align="justify">&#160;</p>
<p align="center">EL (LA) SUSCRITO (A) CONCILIADOR(A) INSCRITO(A) EN LA LISTA OFICIAL DE CONCILIADORES DEL nombreCentroP</p>
<p align="center">&#160;</p>
<p align="center">HACE CONSTAR:</p>
<p align="justify">&#160;</p>
</b><p align="justify">Que de conformidad con lo dispuesto en el artículo 2º de la Ley 640  de 2001, se procede a suscribir la presente constancia en los siguientes términos:</p>
<p align="justify">convocanteP, le solicitó el pasado fechaCasoP, a este Centro, la citación de convocadoP, a una Audiencia de Conciliación, a fin de solucionar diferencias surgidas con ocasión de casoHechosP</p>
<p align="justify">El (La) suscrito(a) conciliador(a) tras haber sido designado(a) por el Director del Centro, procedió a citar a las partes el fechaAudienciaP a las horaAudienciaP reunión a la que asistieron parteAsistenciaP</p>
<p align="justify">En consecuencia, vencido el término legal establecido para el efecto y no habiéndose presentado excusa alguna por su inasistencia, de parte de (PARTE INASISTENTE) y de conformidad con el artículo 22 de la Ley 640 de 2001, se procede a suscribir la presente constancia.</p>
<p align="justify">Dada en ciudadCentroP, dia_cartaP de mes_cartaP de ano_cartaP.</p>
<p align="justify">&#160;</p>
<p align="left"><img src=""></img></p>
<p align="justify">conciliadorP<br/>Conciliador<br/>Registro: registroConciliadorP</p>
  <p align="justify">
    Caso: codigoCaso
  </p>
  </font></body>
</html>', 'USER_SIMASC', SYSDATETIME(), 'ACT');
--nombre Plantilla: CONINASE 

--nombreCentroP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('nombreCentroP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINASE' and tipo_servicio = 'PDL'),
'USER_SIMASC', SYSDATETIME(), 'ACT', 'Nombre del centro donde se encuentra el caso ',
'NATIVE: select UPPER(ce.nombre) from caso c left outer join sede s on s.id_sede = c.id_sede left outer join centro ce on ce.id_centro = s.id_centro where c.id_caso = ?1 and c.estado_registro = ''ACT'' and s.estado_registro = ''ACT'' and ce.estado_registro = ''ACT'' ');

-- convocanteP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocanteP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINASE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocantes en una sola linea separados por comas',
'NATIVE: select stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre in (''CONVOTE'') and estado_registro = ''ACT'')
		AND rp.estado <> ''INA''
        FOR xml PATH ('''')), 1, 2, '''') AS convocantes
from caso c
where id_caso = ?1');

--convocado
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocadoP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINASE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocados en una sola linea separados por comas',
'NATIVE: select stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre in (''CONVODO'') and estado_registro = ''ACT'')
		AND rp.estado <> ''INA''
        FOR xml PATH ('''')), 1, 2, '''') AS convocados
from caso c
where id_caso = ?1');

--conciliador
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('conciliadorP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINASE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae el nombre completo del conciliador asignador al caso',
'NATIVE: select concat(p.primer_nombre_o_razon_social, rtrim('' ''+p.segundo_nombre), rtrim('' ''+p.primer_apellido), rtrim('' ''+p.segundo_apellido))
from rol_persona_caso rpc 
    inner join persona p on p.id_persona = rpc.id_persona
where id_rol in (select id_rol from tipo_de_servicio_rol where tipo_servicio = ''PDL'')
and rpc.estado not in (''REC'', ''INA'')
and rpc.tipo_nombramiento = ''PRI''
and id_caso = ?1
and rpc.estado_registro = ''ACT''
and p.estado_registro = ''ACT''');

--fechaCasoP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('fechaCasoP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINASE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'Fecha de radicación del caso, consultada de la tabla ''caso'' el campo ''fecha_radicacion''',
'NATIVE: SELECT FORMAT(c.fecha_radicacion,''dd/MM/yyyy'') from CASO C where C.id_caso = ?1 and c.estado_registro = ''ACT''');

--casoHechosP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('casoHechosP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINASE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Hechos del caso, consultada de la tabla ''caso'' el campo ''hechos'' ', 'select c.hechos from Caso c where c.idCaso = :idCaso and c.estadoRegistro = ''ACT''');

--fechaAudienciaP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('fechaAudienciaP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINASE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'fecha en que se hará la audiencia, fecha colsultada de la tabla audiencia del campo hora_inicio', 'NATIVE: SELECT FORMAT(au.hora_inicio,''dd/MM/yyyy'') from AUDIENCIA AU where AU.id_caso = ?1 and au.id_audiencia = ?2');

--horaAudienciaP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('horaAudienciaP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINASE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'hora en que se hará la audiencia, hora colsultada de la tabla audiencia del campo hora_inicio', 'NATIVE: SELECT FORMAT(au.hora_inicio,''HH:mm'') from AUDIENCIA AU where AU.id_caso = ?1 and au.id_audiencia = ?2');

--parteAsistenciaP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('parteAsistenciaP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINASE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'las partes que asistieron a la audiencia en una sola linea y separados por comas',
'NATIVE: select stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
        FROM ROL_PERSONA_CASO rpc
        LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona 
        WHERE rpc.id_caso = au.id_caso 
        AND rpc.estado_registro = ''ACT''
		AND rpc.id_persona not in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and i.estado_registro = ''ACT'' )
        AND rpc.id_rol IN (select id_rol from rol where nombre in (SELECT codigo_clasificado FROM CLASIFICADOR_DOMINIO  WHERE CODIGO_CLASIFICADOR = ''PARTAPP'')
							and estado <> ''INA'' and estado_registro = ''ACT'')
        FOR xml PATH ('''')), 1, 2, '''') AS asistentes
from AUDIENCIA au
where au.id_caso = ?1
and au.id_audiencia = ?2');

--ciudadCentroP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('ciudadCentroP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINASE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'Ciudad donde se encuentra el centro del caso',
'NATIVE: select zg.nombre from zona_geografica zg
INNER JOIN centro ce
ON zg.id_zona_geografica = ce.id_ciudad
INNER JOIN sede se
ON se.id_centro = ce.id_centro 
INNER JOIN caso ca
ON se.id_sede = ca.id_sede
WHERE ca.id_caso = ?1
AND ce.estado_registro = ''ACT''
AND zg.estado_registro = ''ACT''
AND se.estado_registro = ''ACT''
AND ca.estado_registro = ''ACT''');

--registroConciliadorP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('registroConciliadorP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINASE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'registro del conciliador, se encuentra en la tabla persona', '
NATIVE: SELECT p.registro_conciliador FROM persona p 
INNER JOIN rol_persona_caso rpc
ON rpc.id_persona = p.id_persona
AND rpc.estado in ( ''ACE'', ''POR'' )
AND rpc.tipo_nombramiento = ''PRI''
INNER JOIN rol r 
ON rpc.id_rol = r.id_rol
AND r.id_rol in (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = ''PDL'' and estado_registro = ''ACT'')
WHERE rpc.id_caso = ?1
AND p.estado_registro = ''ACT''
AND rpc.estado_registro = ''ACT''
AND r.estado_registro = ''ACT'' ');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION)
VALUES ('IDA', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINASE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Identica si la plantilla necesita el id_audiencia con ?2' );

/**** fin de los inserts de la constancia de inasistencia sin excusa ***/

INSERT INTO DOMINIO ( CODIGO, DOMINIO, NOMBRE, DESCRIPCION)
VALUES ( 'CONSIMPO', 'NOMBRE_PLANTILLA_CARTA', 'Constancia de imposibilidad de acuerdo', 'Plantilla de la constancia de imposibilidad de acuerdo');

INSERT INTO PLANTILLA_CARTA ( NOMBRE, TIPO_SERVICIO, PLANTILLA_HTML, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, ID_SERVICIO)
VALUES ('CONSIMPO', 'PDL', '<html>
<head>
<title>Constancia de Imposibilidad</title>
</head>
<body>
<b><font face="Arial">
<p align="center">CONSTANCIA DE IMPOSIBILIDAD</p>
<p align="center">&#160;</p>
<p align="center">EL(LA) SUSCRITO(A) CONCILIADOR(A) INSCRITO(A) EN LA LISTA OFICIAL DE CONCILIADORES DEL nombreCentroP</p>
<p align="center">&#160;</p>
<p align="center">HACE CONSTAR:</p>
</font></b>
<font face="Arial">
<p align="justify">&#160;</p>
<p align="justify">Que de conformidad con lo dispuesto en el artículo 2º de la Ley 640 de 2001 se procede a suscribir la presente constancia en los siguientes términos.</p>
<p align="justify">convocanteP,  le solicitó el fechaCasoP a este Centro, la citación a una audiencia de conciliación de convocadoP, a fin de resolver las diferencias presentadas, con ocasión de casoHechosP</p>
<p align="justify">El(La) suscrito(a) conciliador(a) tras haber sido designado(a) por el Director del Centro, procedió a citar a las partes el fechaAudienciaP, reunión a la que asistieron:</p>
<p align="justify">Parte Convocante:</p>
<table>convocantesAsistentesP</table>
<p align="justify">Parte Convocada:</p>
<table>convocadosAsistentesP</table>
</font>
<font face="Arial">
<p align="justify">&#160;</p>
<p align="justify">A quienes en el curso de la audiencia se les dio el uso de la palabra y después de un intercambio de opiniones, quedó clara la imposibilidad de llegar a un acuerdo conciliatorio en relación con los hechos que motivaron la solicitud de conciliación.</p>
<p align="justify">Para constancia se firma el fechaAudienciaP, por quienes asistieron:</p>
</font>

<p align="justify">&#160;</p>
<p align="justify">&#160;</p>
<font face="Arial">
	<table>firmaConvocantesP</table>

	<table>firmaConvocadosP</table>

	<p align="justify">conciliadorP<br/>Conciliador<br/>Registro: registroConciliadorP</p>
	  <p align="justify">
		Caso: codigoCaso
	  </p>
</font>
</body>
</html>','USER_SIMASC', SYSDATETIME(), 'ACT', 1);

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('nombreCentroP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'),
'USER_SIMASC', SYSDATETIME(), 'ACT', 'Nombre del centro donde se encuentra el caso ',
'NATIVE: select UPPER(ce.nombre) from caso c left outer join sede s on s.id_sede = c.id_sede left outer join centro ce on ce.id_centro = s.id_centro where c.id_caso = ?1 and c.estado_registro = ''ACT'' and s.estado_registro = ''ACT'' and ce.estado_registro = ''ACT'' ');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocanteP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocantes en una sola linea separados por comas',
'NATIVE: select stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre in (''CONVOTE'') and estado_registro = ''ACT'' )
		AND rp.estado <> ''INA''
        FOR xml PATH ('''')), 1, 1, '''') AS convocantes
from caso c
where id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('fechaCasoP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'Fecha de radicación del caso, consultada de la tabla ''caso'' el campo ''fecha_radicacion''',
'NATIVE: SELECT FORMAT(c.fecha_radicacion,''dd/MM/yyyy'') from CASO C where C.id_caso = ?1 and c.estado_registro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocadoP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocados en una sola linea separados por comas',
'NATIVE: select stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre in (''CONVODO'') and estado_registro = ''ACT'')
		AND rp.estado <> ''INA''
        FOR xml PATH ('''')), 1, 2, '''') AS convocados
from caso c
where id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('casoHechosP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Hechos del caso, consultada de la tabla ''caso'' el campo ''hechos'' ', 'select c.hechos from Caso c where c.idCaso = :idCaso and c.estadoRegistro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('fechaCasoP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'Fecha de radicación del caso, consultada de la tabla ''caso'' el campo ''fecha_radicacion''',
'NATIVE: SELECT FORMAT(c.fecha_radicacion,''dd/MM/yyyy'') from CASO C where C.id_caso = ?1 and c.estado_registro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocantesAsistentesP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae informacion de la parte que asistió a la audiencia',
'NATIVE: LIST: select concat( ''<td align="justify">'', pe.primer_nombre_o_razon_social,
								  rtrim('' ''+pe.segundo_nombre),
								  rtrim('' ''+pe.primer_apellido),
								  rtrim('' ''+pe.segundo_apellido),
								  '', quien obra (EN SU PROPIO NOMBRE O EN NOMBRE Y REPRESENTACIÓN O COMO APODERADO DE), mayor de edad,  identificado(a) como aparece al pie de su firma'',
								  '' y domiciliado(a) en la ciudad de ''
								  +(select top 1 zg.nombre from ubicacion u
									INNER JOIN ZONA_GEOGRAFICA zg ON u.id_zona_geografica = zg.id_zona_geografica
									where u.id_persona = pe.id_persona and u.estado_registro = ''ACT'' order by u.fecha_ultima_modificacion desc),''.</td>'') 
        FROM ROL_PERSONA_CASO rpc
        LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona
		INNER JOIN AUDIENCIA au ON rpc.id_caso = au.id_caso
		WHERE au.id_caso = ?1
		AND au.id_audiencia = ?2
		AND rpc.estado_registro = ''ACT''
		AND rpc.id_persona not in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and estado_registro = ''ACT'' )
        AND rpc.id_rol IN (select r.id_rol from rol r where r.nombre in (''CONVOTE'', ''APODCTE'') and r.estado_registro = ''ACT'')
		AND rpc.estado <> ''INA''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocadosAsistentesP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae informacion de la parte que asistió a la audiencia',
'NATIVE: LIST: select concat( ''<td align="justify">'', pe.primer_nombre_o_razon_social,
								  rtrim('' ''+pe.segundo_nombre),
								  rtrim('' ''+pe.primer_apellido),
								  rtrim('' ''+pe.segundo_apellido),
								  '', quien obra (EN SU PROPIO NOMBRE O EN NOMBRE Y REPRESENTACIÓN O COMO APODERADO DE), mayor de edad,  identificado(a) como aparece al pie de su firma'',
								  '' y domiciliado(a) en la ciudad de ''
								  +(select top 1 zg.nombre from ubicacion u
									INNER JOIN ZONA_GEOGRAFICA zg ON u.id_zona_geografica = zg.id_zona_geografica
									where u.id_persona = pe.id_persona and u.estado_registro = ''ACT'' order by u.fecha_ultima_modificacion desc),''.</td>'') 
        FROM ROL_PERSONA_CASO rpc
        LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona
		INNER JOIN AUDIENCIA au ON rpc.id_caso = au.id_caso
		WHERE au.id_caso = ?1
		AND au.id_audiencia = ?2
		AND rpc.estado_registro = ''ACT''
		AND rpc.id_persona not in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and estado_registro = ''ACT'' )
        AND rpc.id_rol IN (select r.id_rol from rol r where r.nombre in (''CONVODO'',''APODCDO'') and r.estado_registro = ''ACT'')
		AND rpc.estado <> ''INA''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('firmaConvocantesP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocados en una sola linea separados por comas',
'NATIVE: LIST: SELECT concat( ''<br/><td>'', pe.primer_nombre_o_razon_social,
								  rtrim('' ''+pe.segundo_nombre),
								  rtrim('' ''+pe.primer_apellido),
								  rtrim('' ''+pe.segundo_apellido), ''<br/>'',
								  pe.tipo_documento+'': ''+pe.numero_documento+''<br/>'',
								  case when rpc.id_rol = ( select r.id_rol from rol r where r.nombre = ''APODCTE'' and r.estado_registro = ''ACT'' )
										then ''T.P: ''+pe.numero_tarjeta_profesional+''<br/>'' 
								  end, ''</td>'' ) 
FROM ROL_PERSONA_CASO rpc
LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona
INNER JOIN AUDIENCIA au ON rpc.id_caso = au.id_caso
WHERE au.id_caso = ?1
AND au.id_audiencia = ?2
AND rpc.estado_registro = ''ACT''
AND rpc.id_persona not in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and estado_registro = ''ACT'' )
AND rpc.id_rol IN (select r.id_rol from rol r where r.nombre in (''CONVOTE'', ''APODCTE'') and r.estado_registro = ''ACT'')
AND rpc.estado <> ''INA''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('firmaConvocadosP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocados en una sola linea separados por comas',
'LIST: NATIVE: SELECT concat( ''<br/><td>'', pe.primer_nombre_o_razon_social,
								  rtrim('' ''+pe.segundo_nombre),
								  rtrim('' ''+pe.primer_apellido),
								  rtrim('' ''+pe.segundo_apellido), ''<br/>'',
								  pe.tipo_documento+'': ''+pe.numero_documento+''<br/>'',
								  case when rpc.id_rol = ( select r.id_rol from rol r where r.nombre = ''APODCDO'' and r.estado_registro = ''ACT'' )
										then ''T.P: ''+pe.numero_tarjeta_profesional+''<br/>'' 
								  end, ''</td>'' ) 
FROM ROL_PERSONA_CASO rpc
LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona
INNER JOIN AUDIENCIA au ON rpc.id_caso = au.id_caso
WHERE au.id_caso = ?1
AND au.id_audiencia = ?2
AND rpc.estado_registro = ''ACT''
AND rpc.id_persona not in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and estado_registro = ''ACT'' )
AND rpc.id_rol IN (select r.id_rol from rol r where r.nombre in (''CONVODO'',''APODCDO'') and r.estado_registro = ''ACT'')
AND rpc.estado <> ''INA''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION)
VALUES ('IDA', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Identica si la plantilla necesita el id_audiencia con ?2' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('conciliadorP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae el nombre completo del conciliador asignador al caso',
'NATIVE: select concat(p.primer_nombre_o_razon_social, rtrim('' ''+p.segundo_nombre), rtrim('' ''+p.primer_apellido), rtrim('' ''+p.segundo_apellido))
from rol_persona_caso rpc 
    inner join persona p on p.id_persona = rpc.id_persona
where id_rol in (select id_rol from tipo_de_servicio_rol where tipo_servicio = ''PDL'')
and rpc.estado not in (''REC'', ''INA'')
and rpc.tipo_nombramiento = ''PRI''
and id_caso = ?1
and rpc.estado_registro = ''ACT''
and p.estado_registro = ''ACT''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('registroConciliadorP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'registro del conciliador, se encuentra en la tabla persona', '
NATIVE: SELECT p.registro_conciliador FROM persona p 
INNER JOIN rol_persona_caso rpc
ON rpc.id_persona = p.id_persona
AND rpc.estado in ( ''ACE'', ''POR'' )
AND rpc.tipo_nombramiento = ''PRI''
INNER JOIN rol r 
ON rpc.id_rol = r.id_rol
AND r.id_rol in (SELECT id_rol FROM TIPO_DE_SERVICIO_ROL WHERE tipo_servicio = ''PDL'' and estado_registro = ''ACT'')
WHERE rpc.id_caso = ?1
AND p.estado_registro = ''ACT''
AND rpc.estado_registro = ''ACT''
AND r.estado_registro = ''ACT'' ');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('fechaAudienciaP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'fecha en que se hará la audiencia, fecha colsultada de la tabla audiencia del campo hora_inicio', 'NATIVE: SELECT FORMAT(au.hora_inicio,''dd/MM/yyyy'') from AUDIENCIA AU where AU.id_caso = ?1 and au.id_audiencia = ?2');

/*** Fin de los inserts de la plantilla de imposibilidad ***/


/**
 * Permisos CON-F-063
 */

-- permisos

 DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF063ActualizacionCapacitacionenMASC';
DELETE FUNCIONALIDAD where nombre='CONF063ActualizacionCapacitacionenMASC';
insert into FUNCIONALIDAD values ('CONF063ActualizacionCapacitacionenMASC', 'Actualización Capacitación en MASC','app/Conciliacion/CONF063','ADM', 'ANGULAR',NULL,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF063ActualizacionCapacitacionenMASC', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('CON','JEFECON', 'SECCON');
			
			

-- Control de cambios 31
insert into dominio values ('AGRACTCO', 'PLANTILLAS_ACTCON', 'Agrupador actas y constancias', 'Agrupador para las plantillas que corresponden a actas y constancias', null, null)
insert into clasificador_dominio values ('CONSIMPO', 'NOMBRE_PLANTILLA_CARTA', 'AGRACTCO', 'PLANTILLAS_ACTCON', 'USER_SIMASC', GETDATE(), 'ACT')
insert into clasificador_dominio values ('ACTACON', 'NOMBRE_PLANTILLA_CARTA', 'AGRACTCO', 'PLANTILLAS_ACTCON', 'USER_SIMASC', GETDATE(), 'ACT')
insert into clasificador_dominio values ('CONINACE', 'NOMBRE_PLANTILLA_CARTA', 'AGRACTCO', 'PLANTILLAS_ACTCON', 'USER_SIMASC', GETDATE(), 'ACT')
insert into clasificador_dominio values ('CONINASE', 'NOMBRE_PLANTILLA_CARTA', 'AGRACTCO', 'PLANTILLAS_ACTCON', 'USER_SIMASC', GETDATE(), 'ACT')


-- Insercion curador y representante de parte ADM-F-048

INSERT ROL VALUES (getdate(),'RDP','0','USUARIO_SIMASC',getdate(),'ACT', 'PJT')
INSERT DOMINIO VALUES ('RDP','ROL_PERSONA', 'Representante de Parte', 'Representante de Parte', 'APOD', 'AGRUPADOR_ROL_PERSONA')
insert CLASIFICADOR_DOMINIO values ('CAL', 'ROL_PERSONA', 'PARTAPP', 'AGRUPADOR_ROL_PERSONA','USER_SIMASC',GETDATE(),'ACT')
insert CLASIFICADOR_DOMINIO values ('RDP', 'ROL_PERSONA', 'PARTAPP', 'AGRUPADOR_ROL_PERSONA','USER_SIMASC',GETDATE(),'ACT')

-- CON-F-096 integracion firma
update PARAMETROS_GENERALES set valor_texto = 'http://ihsd/WSSIGN-war/WSDigitalPDFService/WEB-INF/wsdl/WSDigitalPDFService.wsdl' where codigo = 'WSPDF'
insert PARAMETROS_GENERALES values('WSTOKEN', null,'URL_TOKEN','Url servicio de token',null,'http://appqas.ccb.org.co/WCFSolicitudClave/Service.svc?wsdl','Url para acceder a la firma del servicio para obtner el token','USUARIO_SIMASC',GETDATE(),'ACT')
update PARAMETROS_GENERALES set valor_texto = 'simasc' where codigo = 'CLIENPDF'
update PARAMETROS_GENERALES set valor_texto = 'XsbmBR66mlM=' where codigo = 'PASSPDF'
update PARAMETROS_GENERALES set valor_texto = '8' where codigo = 'POLPDF'
insert into dominio values ('RADCA', 'TIPO_DOCUMENTO_DIG', 'Documento de radicación caso de convenio', 'Documento generado y firmado para la radicacion de un caso de convenio', null, null)

/**
 * Plantilla reliquidacion
 */
INSERT INTO DOMINIO ( CODIGO, DOMINIO, NOMBRE, DESCRIPCION)
VALUES ( 'RELIQUID', 'NOMBRE_PLANTILLA_CARTA', 'Reliquidación', 'Plantilla para reliquidación de caso');

INSERT INTO PLANTILLA_CARTA ( NOMBRE, TIPO_SERVICIO, PLANTILLA_HTML, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, ID_SERVICIO)
values ('RELIQUID', 'PDL', '<!DOCTYPE html><html><head><meta charset="UTF-8"/></head><body>
<p>ciudadP, dia_cartaP de mes_cartaP de ano_cartaP</p>
<p>Señor(a)</p>
<p><strong> <em> dirigidoP </em> </strong> <br /> <strong> <em> direccionEnvio </em> </strong> <br /> <strong> <em> ciudadEnvio </em> </strong></p>
<p style="text-align: justify;">REFERENCIA: CAMBIO DE CUANTIA EN CONCILIACION DE <strong> <em> convocanteP </em> </strong> PARA SOLUCIONAR LAS DIFERENCIAS SURGIDAS CON <strong> <em> convocadoP </em> </strong></p>
<p>Apreciado(a) señor(a):</p>
<p style="text-align: justify;">tipoReliquidacionP</p>
<table>
<tbody>
detalleReliquidacionP
totalReliquidacionP
</tbody>
</table>
<p style="text-align: justify;">Este pago podrá realizarlo en cualquiera de las Sedes Comerciales de la Cámara de Comercio de Bogotá, presentando esta comunicación. Informamos que de estas sumas ya ha sido deducido el pago inicial efectuado a la presentación de su solicitud, y que se cobran de acuerdo a las tarifas establecidas en el decreto 1069 de 2015, Artículo 2.2.4.2.6.1.3 sobre Reliquidación de la Tarifa de Conciliación.</p>
<p>Cordialmente,</p>
<p><strong> <em> jefeDeConciliacionP </em> </strong> <br /> Jefe Conciliación </p>
<p>CASO No. <strong> <em> codigoCaso </em> </strong></p>
</body></html>', 'USER_SIMASC', GETDATE(), 'ACT', 1)

-- detalleReliquidacionP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('detalleReliquidacionP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'RELIQUID' and tipo_servicio = 'PDL'),
'USER_SIMASC', SYSDATETIME(), 'ACT', 'Detalles de la reliquidación del caso',
'NATIVE: LIST: select concat(case when servicio_caja = ''04040010'' then ''<td style="width: 20%;"></td>'' else ''<td style="width: 20%;">'' + servicio_caja + ''</td>'' end, ''<td style="width: 40%;">'' + d.nombre + ''</td>'', concat(''<td style="width: 40%;">'', isnull(valor, ''0.00''), ''</td>'')) from DETALLE_RELIQUIDACION dr inner join dominio d on dr.servicio_caja = d.codigo and d.dominio = ''TIPO_SERVICIO_CAJA'' where id_reliquidacion = (select TOP 1 id_reliquidacion from reliquidacion where id_caso = ?1 and tipo = ''RELIQUID'' order by fecha desc) order by fecha_ultima_modificacion')

--totalReliquidacionP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('totalReliquidacionP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'RELIQUID' and tipo_servicio = 'PDL'),
'USER_SIMASC', SYSDATETIME(), 'ACT', 'Total de la reliquidación del caso',
'NATIVE: select TOP 1 concat(''<td style="width: 20%;"></td>'', ''<td style="width: 40%;"><strong>TOTAL</strong></td>'', concat(''<td style="width: 20%;"><strong>'', isnull(valor, ''0.00''), ''</strong></td>'')) from reliquidacion where id_caso = ?1 and tipo = ''RELIQUID'' order by fecha desc')

-- tipoReliquidacionP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('tipoReliquidacionP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'RELIQUID' and tipo_servicio = 'PDL'),
'USER_SIMASC', SYSDATETIME(), 'ACT', 'Parrafo dependiendo el metodo de reliquidacion seleccionado (nueva cuantia o porcentaje adicional)',
'NATIVE: select TOP 1 case when nueva_cuantia is not null then ''Teniendo en cuenta que el monto de las diferencias objeto del conflicto es de $<strong><em>nuevaCuantiaP</em></strong>, amablemente le solicitamos cancelar antes de acudir a la audiencia el saldo correspondiente, equivalente a:'' else ''Teniendo en cuenta que el caso ha llegado a las <strong>cantidadAudienciasP</strong> audiencias programadas, amablemente le solicitamos cancelar el valor correspondiente a:'' end from reliquidacion where id_caso = ?1 and tipo = ''RELIQUID'' order by fecha desc')
-- nuevaCuantiaP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('nuevaCuantiaP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'RELIQUID' and tipo_servicio = 'PDL'),
'USER_SIMASC', SYSDATETIME(), 'ACT', 'Valor de la nueva cuantia',
'NATIVE: select TOP 1 isnull(nueva_cuantia, ''0.00'') from reliquidacion where id_caso = ?1 and tipo = ''RELIQUID'' order by fecha desc')
--cantidadAudienciasP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('cantidadAudienciasP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'RELIQUID' and tipo_servicio = 'PDL'),
'USER_SIMASC', SYSDATETIME(), 'ACT', 'Cantidad de audiencias programadas para el caso',
'NATIVE: select count(*) from audiencia a where id_caso = ?1 and estado_registro = ''ACT'' and estado in (''REA'', ''PEN'')')
--jefeDeConciliacionP
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('jefeDeConciliacionP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'RELIQUID' and tipo_servicio = 'PDL'),
'USER_SIMASC', SYSDATETIME(), 'ACT', 'Nombre del jefe de conciliación',
'NATIVE: select TOP 1 concat(p.primer_nombre_o_razon_social, rtrim('' ''+p.segundo_nombre), rtrim('' ''+p.primer_apellido), rtrim('' ''+p.segundo_apellido))
from persona p 
inner join rol_persona rp on p.id_persona = rp.id_persona and rp.estado_registro = ''ACT''
where id_centro in (select s.id_centro from caso c
					inner join sede s on s.id_sede = c.id_sede and s.estado_registro = ''ACT''
					where id_caso = ?1
					and c.estado_registro = ''ACT'')
and p.estado_registro = ''ACT''
and id_rol = (select id_rol from rol where nombre = ''JEFECON'' and estado_registro = ''ACT'')')

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocanteP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'RELIQUID' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocantes en una sola linea separados por comas',
'NATIVE: select stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre in (''CONVOTE'') and estado_registro = ''ACT'' )
		AND rp.estado <> ''INA''
        FOR xml PATH ('''')), 1, 1, '''') AS convocantes
from caso c
where id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocadoP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'RELIQUID' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocados en una sola linea separados por comas',
'NATIVE: select stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
        FROM ROL_PERSONA_CASO rp 
        LEFT JOIN PERSONA pe ON rp.id_persona = pe.id_persona 
        WHERE rp.id_caso = c.id_caso 
        AND rp.estado_registro = ''ACT''
        AND rp.id_rol IN (select id_rol from rol where nombre in (''CONVODO'') and estado_registro = ''ACT'')
		AND rp.estado <> ''INA''
        FOR xml PATH ('''')), 1, 2, '''') AS convocados
from caso c
where id_caso = ?1');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('dirigidoP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'RELIQUID' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'nombre de la persona a la que se envia la carta',
'NATIVE: select concat(p.primer_nombre_o_razon_social, rtrim('' ''+p.segundo_nombre), rtrim('' ''+p.primer_apellido), rtrim('' ''+p.segundo_apellido), ''<br/>'')
from Persona p left join ROL_PERSONA_CASO prpc 
on prpc.id_persona = p.id_persona where prpc.id_caso=?1
AND prpc.estado_registro=''ACT'' and p.id_persona=?2' );

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION)
VALUES ('IPP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'RELIQUID' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'Identica si la plantilla lleva un tag de <dirigido> con id_persona = ?2' );

-- update dominio set nombre = 'Reliquidación - devolución' where codigo = 'DEVODIN' and dominio = 'NOMBRE_PLANTILLA_CARTA'
-- fin inserción plantilla


--CON-F-111 FUNCION

/****** Object:  UserDefinedFunction [dbo].[fechaLimiteEstudioCaso]    Script Date: 28/05/2018 04:51:20 p.m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:			Juan Diego Cepeda Mosquera
-- Create date:		07/04/2018
-- Description:		Obtiene la fecha límite para estudio del caso
-- =============================================
ALTER FUNCTION [dbo].[fechaLimiteEstudioCaso]
(
    @FechaAsignacionAnalista AS DATETIME
)
RETURNS DATETIME
AS
BEGIN
	DECLARE @HoraAsignacionAnalista AS TIME = CONVERT(VARCHAR(5), @FechaAsignacionAnalista, 108)
	DECLARE @InicioJornadaLaboral AS TIME = CAST((SELECT valor_texto FROM PARAMETROS_GENERALES WHERE codigo = 'TEC_IJL' AND tipo = 'TIEMPO_ESTUDIO_CASOS_CON') AS TIME)
	DECLARE @InicioAlmuerzo AS TIME = CAST((SELECT valor_texto FROM PARAMETROS_GENERALES WHERE codigo = 'TEC_IR' AND tipo = 'TIEMPO_ESTUDIO_CASOS_CON') AS TIME)
	DECLARE @FinAlmuerzo AS TIME = CAST((SELECT valor_texto FROM PARAMETROS_GENERALES WHERE codigo = 'TEC_FR' AND tipo = 'TIEMPO_ESTUDIO_CASOS_CON') AS TIME)
	DECLARE @FinJornadaLaboral AS TIME = CAST((SELECT valor_texto FROM PARAMETROS_GENERALES WHERE codigo = 'TEC_FJL' AND tipo = 'TIEMPO_ESTUDIO_CASOS_CON') AS TIME)
	DECLARE @TiempoEstudio AS INT = (SELECT valor_numerico FROM PARAMETROS_GENERALES WHERE codigo = 'TEC_TME' AND tipo = 'TIEMPO_ESTUDIO_CASOS_CON')
	DECLARE @NumeroHoras AS INT = 0
	DECLARE @HorasFaltantes AS INT

	IF (@HoraAsignacionAnalista >= @InicioJornadaLaboral AND @HoraAsignacionAnalista <= @InicioAlmuerzo)
	BEGIN
		SELECT @NumeroHoras = DATEDIFF(HOUR, @HoraAsignacionAnalista, @FinJornadaLaboral) - (DATEDIFF(HOUR, @InicioAlmuerzo, @FinAlmuerzo))
		SELECT @FechaAsignacionAnalista = DATEADD(HOUR, @NumeroHoras + DATEDIFF(HOUR, @InicioAlmuerzo, @FinAlmuerzo), @FechaAsignacionAnalista)
	END
	ELSE
	BEGIN
		IF (@HoraAsignacionAnalista >= @FinAlmuerzo AND @HoraAsignacionAnalista <= @FinJornadaLaboral)
		BEGIN
			SELECT @NumeroHoras = DATEDIFF(HOUR, @HoraAsignacionAnalista, @FinJornadaLaboral)
			SELECT @FechaAsignacionAnalista = DATEADD(HOUR, @NumeroHoras, @FechaAsignacionAnalista)
		END
	END

	IF (@NumeroHoras >= 0 AND (@NumeroHoras < @TiempoEstudio OR DATEPART(HOUR, @FechaAsignacionAnalista) > DATEPART(HOUR, @FinJornadaLaboral)))
	BEGIN
		SELECT @HorasFaltantes = @TiempoEstudio - @NumeroHoras
		SELECT @FechaAsignacionAnalista = DATEADD(HOUR, 15, @FechaAsignacionAnalista)
		SELECT @FechaAsignacionAnalista = DATEADD(HOUR, @HorasFaltantes, @FechaAsignacionAnalista)
	END

	SELECT @HoraAsignacionAnalista = CONVERT(VARCHAR(5), @FechaAsignacionAnalista, 108)
	
	IF (@HoraAsignacionAnalista >= @InicioAlmuerzo AND @HoraAsignacionAnalista <= @FinAlmuerzo)
		SELECT @FechaAsignacionAnalista = DATEADD(HOUR, DATEDIFF(HOUR, @InicioAlmuerzo, @FinAlmuerzo), @FechaAsignacionAnalista)

	RETURN @FechaAsignacionAnalista
END

GO

/*tipo evento CON-F-068*/
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('CDCL','TIPO_EVENTO','Caso devuelto en control de legalidad','Caso devuelto en control de legalidad.',null,null);

/* Solucion de bug de aceptacion 6513 de tfs, no se evidencia informacion de la parte */
--1.
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF050divAsignacionDePartesPDL';
DELETE FUNCIONALIDAD WHERE nombre = 'ARBF050divAsignacionDePartesPDL';
INSERT INTO FUNCIONALIDAD VALUES ('ARBF050divAsignacionDePartesPDL', 'Asignación de partes', 'app/Conciliacion/CONF087','PDL','ANGULAR', null,'SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','ARBF050divAsignacionDePartesPDL',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ( 'CON', 'ACO', 'SECCON', 'JEFECON' );
--2.
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF050divInformacionDeLaPartePDL';
DELETE FUNCIONALIDAD WHERE nombre = 'ARBF050divInformacionDeLaPartePDL';
INSERT INTO FUNCIONALIDAD VALUES ('ARBF050divInformacionDeLaPartePDL', 'Sección información de la parte', 'app/Conciliacion/CONF087','PDL','ANGULAR', 'ARBF050divAsignacionDePartesPDL','SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','ARBF050divInformacionDeLaPartePDL',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ( 'CON', 'ACO', 'SECCON', 'JEFECON' );
--3.
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF050buttonAdicionarPDL';
DELETE FUNCIONALIDAD WHERE nombre = 'ARBF050buttonAdicionarPDL';
INSERT INTO FUNCIONALIDAD VALUES ('ARBF050buttonAdicionarPDL', 'Adicionar parte', 'app/Conciliacion/CONF087','PDL','ANGULAR', 'ARBF050divInformacionDeLaPartePDL','SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','ARBF050buttonAdicionarPDL',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ( 'CON', 'ACO', 'SECCON', 'JEFECON' );
--4.
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF050buttonAnteriorPDL';
DELETE FUNCIONALIDAD WHERE nombre = 'ARBF050buttonAnteriorPDL';
INSERT INTO FUNCIONALIDAD VALUES ('ARBF050buttonAnteriorPDL', 'Volver a lista de partes', 'app/Conciliacion/CONF087','PDL','ANGULAR', 'ARBF050divInformacionDeLaPartePDL','SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','ARBF050buttonAnteriorPDL',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ( 'CON', 'ACO', 'SECCON', 'JEFECON' );
--5.
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF050buttonContinuarPDL';
DELETE FUNCIONALIDAD WHERE nombre = 'ARBF050buttonContinuarPDL';
INSERT INTO FUNCIONALIDAD VALUES ('ARBF050buttonContinuarPDL', 'Continuar radicación del caso', 'app/Conciliacion/CONF087','PDL','ANGULAR', 'ARBF050divInformacionDeLaPartePDL','SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','ARBF050buttonContinuarPDL',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ( 'CON', 'ACO', 'SECCON', 'JEFECON' );
--6.
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF050buttonGuardarPDL';
DELETE FUNCIONALIDAD WHERE nombre = 'ARBF050buttonGuardarPDL';
INSERT INTO FUNCIONALIDAD VALUES ('ARBF050buttonGuardarPDL', 'Guardar datos básicos del caso', 'app/Conciliacion/CONF087','PDL','ANGULAR', 'ARBF050divInformacionDeLaPartePDL','SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','ARBF050buttonGuardarPDL',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ( 'CON', 'ACO', 'SECCON', 'JEFECON' );
--7.
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF050buttonLimpiarPDL';
DELETE FUNCIONALIDAD WHERE nombre = 'ARBF050buttonLimpiarPDL';
INSERT INTO FUNCIONALIDAD VALUES ('ARBF050buttonLimpiarPDL', 'Limpiar datos del formulario', 'app/Conciliacion/CONF087','PDL','ANGULAR', 'ARBF050divInformacionDeLaPartePDL','SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','ARBF050buttonLimpiarPDL',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ( 'CON', 'ACO', 'SECCON', 'JEFECON' );
--8.
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF050divConsultaPartesCasoPDL';
DELETE FUNCIONALIDAD WHERE nombre = 'ARBF050divConsultaPartesCasoPDL';
INSERT INTO FUNCIONALIDAD VALUES ('ARBF050divConsultaPartesCasoPDL', 'Formulario partes del caso', 'app/Conciliacion/CONF087','PDL','ANGULAR', 'ARBF050divInformacionDeLaPartePDL','SIMASC_USER',SYSDATETIME(),'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	SELECT DISTINCT 'USUARIO_SIMASC', SYSDATETIME(),'INA','ARBF050divConsultaPartesCasoPDL',r.id_rol
		FROM ROL r
		JOIN dominio d ON d.codigo = r.nombre
		WHERE d.dominio 
			IN ('ROL_PERSONA') 
			AND d.codigo NOT IN ( 'CON', 'ACO', 'SECCON', 'JEFECON' );

/* Insert dominios TRANS-F-1008*/

INSERT DOMINIO VALUES ('LCCAC','LUGARES_CAPACITACION','CAC','Lugar donde se realizó la capacitación CAC',NULL,NULL)
INSERT DOMINIO VALUES ('LCOTRO','LUGARES_CAPACITACION','Otro','Lugar donde se realizó la capacitación Otro',NULL,NULL)
INSERT DOMINIO VALUES ('CAPMASC','TIPO_CURSO','Capacitación en MASC','Tipo de Curso Capacitación en MASC',NULL,NULL)

/* Permisos CON-F-062 */

 DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF062ActualizacionDisponibilidaddeagenda';
DELETE FUNCIONALIDAD where nombre='CONF062ActualizacionDisponibilidaddeagenda';
insert into FUNCIONALIDAD values ('CONF062ActualizacionDisponibilidaddeagenda', 'Actualización Disponibilidad de Agenda','app/Conciliacion/CONF062','ADM', 'ANGULAR',NULL,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF062ActualizacionDisponibilidaddeagenda', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('CON','JEFECON', 'SECCON');
			
/* Ajustes las plantillas de audiencias de conciliacion */
			--Cconstancia de imposibilidad
delete VALOR_PLANTILLA_CARTA where nombre_valor_dinamico in ( 'convocantesAsistentesP', 'convocadosAsistentesP', 'firmaConvocantesP', 'firmaConvocadosP' )
								and id_plantilla_carta in (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL');
								
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocantesAsistentesP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae informacion de la parte que asistió a la audiencia',
'NATIVE: LIST: select concat( ''<td align="justify">'', pe.primer_nombre_o_razon_social,
								  rtrim('' ''+pe.segundo_nombre),
								  rtrim('' ''+pe.primer_apellido),
								  rtrim('' ''+pe.segundo_apellido),
								  '', quien obra (EN SU PROPIO NOMBRE O EN NOMBRE Y REPRESENTACIÓN O COMO APODERADO DE), mayor de edad,  identificado(a) como aparece al pie de su firma'',
								  '' y domiciliado(a) en la ciudad de ''
								  +(select top 1 zg.nombre from ubicacion u
									INNER JOIN ZONA_GEOGRAFICA zg ON u.id_zona_geografica = zg.id_zona_geografica
									where u.id_persona = pe.id_persona and u.estado_registro = ''ACT'' order by u.fecha_ultima_modificacion desc),''.</td>'') 
        FROM ROL_PERSONA_CASO rpc
        LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona
		INNER JOIN AUDIENCIA au ON rpc.id_caso = au.id_caso
		WHERE au.id_caso = ?1
		AND au.id_audiencia = ?2
		AND rpc.estado_registro = ''ACT''
		AND rpc.id_persona in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and i.tipo = ''IMPOSI'' and i.estado_registro = ''ACT'' )
        AND rpc.id_rol IN (select r.id_rol from rol r where r.nombre in (''CONVOTE'', ''APODCTE'') and r.estado_registro = ''ACT'')
		AND rpc.estado <> ''INA''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocadosAsistentesP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae informacion de la parte que asistió a la audiencia',
'NATIVE: LIST: select concat( ''<td align="justify">'', pe.primer_nombre_o_razon_social,
								  rtrim('' ''+pe.segundo_nombre),
								  rtrim('' ''+pe.primer_apellido),
								  rtrim('' ''+pe.segundo_apellido),
								  '', quien obra (EN SU PROPIO NOMBRE O EN NOMBRE Y REPRESENTACIÓN O COMO APODERADO DE), mayor de edad,  identificado(a) como aparece al pie de su firma'',
								  '' y domiciliado(a) en la ciudad de ''
								  +(select top 1 zg.nombre from ubicacion u
									INNER JOIN ZONA_GEOGRAFICA zg ON u.id_zona_geografica = zg.id_zona_geografica
									where u.id_persona = pe.id_persona and u.estado_registro = ''ACT'' order by u.fecha_ultima_modificacion desc),''.</td>'') 
        FROM ROL_PERSONA_CASO rpc
        LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona
		INNER JOIN AUDIENCIA au ON rpc.id_caso = au.id_caso
		WHERE au.id_caso = ?1
		AND au.id_audiencia = ?2
		AND rpc.estado_registro = ''ACT''
		AND rpc.id_persona in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and i.tipo = ''IMPOSI'' and i.estado_registro = ''ACT'' )
        AND rpc.id_rol IN (select r.id_rol from rol r where r.nombre in (''CONVODO'',''APODCDO'') and r.estado_registro = ''ACT'')
		AND rpc.estado <> ''INA''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('firmaConvocantesP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocados en una sola linea separados por comas',
'NATIVE: LIST: SELECT concat( ''<br/><td>'', pe.primer_nombre_o_razon_social,
								  rtrim('' ''+pe.segundo_nombre),
								  rtrim('' ''+pe.primer_apellido),
								  rtrim('' ''+pe.segundo_apellido), ''<br/>'',
								  pe.tipo_documento+'': ''+pe.numero_documento+''<br/>'',
								  case when rpc.id_rol = ( select r.id_rol from rol r where r.nombre = ''APODCTE'' and r.estado_registro = ''ACT'' )
										then ''T.P: ''+pe.numero_tarjeta_profesional+''<br/>'' 
								  end, ''</td>'' ) 
FROM ROL_PERSONA_CASO rpc
LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona
INNER JOIN AUDIENCIA au ON rpc.id_caso = au.id_caso
WHERE au.id_caso = ?1
AND au.id_audiencia = ?2
AND rpc.estado_registro = ''ACT''
AND rpc.id_persona in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and i.tipo = ''IMPOSI'' and i.estado_registro = ''ACT'' )
AND rpc.id_rol IN (select r.id_rol from rol r where r.nombre in (''CONVOTE'', ''APODCTE'') and r.estado_registro = ''ACT'')
AND rpc.estado <> ''INA''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('firmaConvocadosP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocados en una sola linea separados por comas',
'LIST: NATIVE: SELECT concat( ''<br/><td>'', pe.primer_nombre_o_razon_social,
								  rtrim('' ''+pe.segundo_nombre),
								  rtrim('' ''+pe.primer_apellido),
								  rtrim('' ''+pe.segundo_apellido), ''<br/>'',
								  pe.tipo_documento+'': ''+pe.numero_documento+''<br/>'',
								  case when rpc.id_rol = ( select r.id_rol from rol r where r.nombre = ''APODCDO'' and r.estado_registro = ''ACT'' )
										then ''T.P: ''+pe.numero_tarjeta_profesional+''<br/>'' 
								  end, ''</td>'' ) 
FROM ROL_PERSONA_CASO rpc
LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona
INNER JOIN AUDIENCIA au ON rpc.id_caso = au.id_caso
WHERE au.id_caso = ?1
AND au.id_audiencia = ?2
AND rpc.estado_registro = ''ACT''
AND rpc.id_persona in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and i.tipo = ''IMPOSI'' and i.estado_registro = ''ACT'' )
AND rpc.id_rol IN (select r.id_rol from rol r where r.nombre in (''CONVODO'',''APODCDO'') and r.estado_registro = ''ACT'')
AND rpc.estado <> ''INA''');						
			
-- para acta de conciliacion
delete VALOR_PLANTILLA_CARTA where nombre_valor_dinamico in ( 'convocantesAsistentesP', 'convocadosAsistentesP', 'firmaConvocantesP', 'firmaConvocadosP' )
								and id_plantilla_carta in (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL');
								
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocantesAsistentesP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae informacion de la parte que asistió a la audiencia',
'NATIVE: LIST: select concat( ''<td>'', pe.primer_nombre_o_razon_social,
								  rtrim('' ''+pe.segundo_nombre),
								  rtrim('' ''+pe.primer_apellido),
								  rtrim('' ''+pe.segundo_apellido),
								  '', quien obra (EN SU PROPIO NOMBRE O EN NOMBRE Y REPRESENTACIÓN O COMO APODERADO DE), mayor de edad,  identificado(a) como aparece al pie de su firma'',
								  '' y domiciliado(a) en la ciudad de ''
								  +(select top 1 zg.nombre from ubicacion u
									INNER JOIN ZONA_GEOGRAFICA zg ON u.id_zona_geografica = zg.id_zona_geografica
									where u.id_persona = pe.id_persona and u.estado_registro = ''ACT'' order by u.fecha_ultima_modificacion desc),''.</td>'') 
        FROM ROL_PERSONA_CASO rpc
        LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona
		INNER JOIN AUDIENCIA au ON rpc.id_caso = au.id_caso
		WHERE au.id_caso = ?1
		AND au.id_audiencia = ?2
		AND rpc.estado_registro = ''ACT''
		AND rpc.id_persona not in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and i.tipo = ''INASIS'' and i.estado_registro = ''ACT'' )
        AND rpc.id_rol IN (select r.id_rol from rol r where r.nombre in (''CONVOTE'', ''APODCTE'') and r.estado_registro = ''ACT'')
		AND rpc.estado <> ''INA''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('convocadosAsistentesP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'trae informacion de la parte que asistió a la audiencia',
'NATIVE: LIST: select concat( ''<td>'', pe.primer_nombre_o_razon_social,
								  rtrim('' ''+pe.segundo_nombre),
								  rtrim('' ''+pe.primer_apellido),
								  rtrim('' ''+pe.segundo_apellido),
								  '', quien obra (EN SU PROPIO NOMBRE O EN NOMBRE Y REPRESENTACIÓN O COMO APODERADO DE), mayor de edad,  identificado(a) como aparece al pie de su firma'',
								  '' y domiciliado(a) en la ciudad de ''
								  +(select top 1 zg.nombre from ubicacion u
									INNER JOIN ZONA_GEOGRAFICA zg ON u.id_zona_geografica = zg.id_zona_geografica
									where u.id_persona = pe.id_persona and u.estado_registro = ''ACT'' order by u.fecha_ultima_modificacion desc),''.</td>'') 
        FROM ROL_PERSONA_CASO rpc
        LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona
		INNER JOIN AUDIENCIA au ON rpc.id_caso = au.id_caso
		WHERE au.id_caso = ?1
		AND au.id_audiencia = ?2
		AND rpc.estado_registro = ''ACT''
		AND rpc.id_persona not in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and i.tipo = ''INASIS'' and i.estado_registro = ''ACT'' )
        AND rpc.id_rol IN (select r.id_rol from rol r where r.nombre in (''CONVODO'',''APODCDO'') and r.estado_registro = ''ACT'')
		AND rpc.estado <> ''INA''');
		
INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('firmaConvocantesP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocados en una sola linea separados por comas',
'NATIVE: LIST: SELECT concat( ''<br/><td>'', pe.primer_nombre_o_razon_social,
								  rtrim('' ''+pe.segundo_nombre),
								  rtrim('' ''+pe.primer_apellido),
								  rtrim('' ''+pe.segundo_apellido), ''<br/>'',
								  pe.tipo_documento+'': ''+pe.numero_documento+''<br/>'',
								  case when rpc.id_rol = ( select r.id_rol from rol r where r.nombre = ''APODCTE'' and r.estado_registro = ''ACT'' )
										then ''T.P: ''+pe.numero_tarjeta_profesional+''<br/>'' 
								  end, ''</td>'' ) 
FROM ROL_PERSONA_CASO rpc
LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona
INNER JOIN AUDIENCIA au ON rpc.id_caso = au.id_caso
WHERE au.id_caso = ?1
AND au.id_audiencia = ?2
AND rpc.estado_registro = ''ACT''
AND rpc.id_persona not in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and i.tipo = ''INASIS'' and i.estado_registro = ''ACT'' )
AND rpc.id_rol IN (select r.id_rol from rol r where r.nombre in (''CONVOTE'', ''APODCTE'') and r.estado_registro = ''ACT'')
AND rpc.estado <> ''INA''');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('firmaConvocadosP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'ACTACON' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT', 'coloca los convocados en una sola linea separados por comas',
'LIST: NATIVE: SELECT concat( ''<br/><td>'', pe.primer_nombre_o_razon_social,
								  rtrim('' ''+pe.segundo_nombre),
								  rtrim('' ''+pe.primer_apellido),
								  rtrim('' ''+pe.segundo_apellido), ''<br/>'',
								  pe.tipo_documento+'': ''+pe.numero_documento+''<br/>'',
								  case when rpc.id_rol = ( select r.id_rol from rol r where r.nombre = ''APODCDO'' and r.estado_registro = ''ACT'' )
										then ''T.P: ''+pe.numero_tarjeta_profesional+''<br/>'' 
								  end, ''</td>'' ) 
FROM ROL_PERSONA_CASO rpc
LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona
INNER JOIN AUDIENCIA au ON rpc.id_caso = au.id_caso
WHERE au.id_caso = ?1
AND au.id_audiencia = ?2
AND rpc.estado_registro = ''ACT''
AND rpc.id_persona not in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and i.tipo = ''INASIS'' and i.estado_registro = ''ACT'' )
AND rpc.id_rol IN (select r.id_rol from rol r where r.nombre in (''CONVODO'',''APODCDO'') and r.estado_registro = ''ACT'')
AND rpc.estado <> ''INA''');

--constancia de inasistencia con excusa
delete VALOR_PLANTILLA_CARTA where nombre_valor_dinamico in ( 'parteAsistenciaP' )
								and id_plantilla_carta in (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINACE' and tipo_servicio = 'PDL');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('parteAsistenciaP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINACE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'las partes que asistieron a la audiencia en una sola linea y separados por comas',
'NATIVE: select stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
        FROM ROL_PERSONA_CASO rpc
        LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona 
        WHERE rpc.id_caso = au.id_caso 
        AND rpc.estado_registro = ''ACT''
		AND rpc.id_persona not in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and i.tipo = ''INASIS'' and i.estado_registro = ''ACT'' )
        AND rpc.id_rol IN (select id_rol from rol where nombre in (SELECT codigo_clasificado FROM CLASIFICADOR_DOMINIO  WHERE CODIGO_CLASIFICADOR = ''PARTAPP'')
							and estado <> ''INA'' and estado_registro = ''ACT'')
        FOR xml PATH ('''')), 1, 2, '''') AS asistentes
from AUDIENCIA au
where au.id_caso = ?1
and au.id_audiencia = ?2');								
								
--constancia de inasistencia sin excusa
delete VALOR_PLANTILLA_CARTA where nombre_valor_dinamico in ( 'parteAsistenciaP' )
								and id_plantilla_carta in (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINASE' and tipo_servicio = 'PDL');

INSERT INTO VALOR_PLANTILLA_CARTA ( NOMBRE_VALOR_DINAMICO, ID_PLANTILLA_CARTA, ID_USUARIO_MODIFICACION, FECHA_ULTIMA_MODIFICACION, ESTADO_REGISTRO, DESCRIPCION, CONSULTA)
VALUES ('parteAsistenciaP', (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'CONINASE' and tipo_servicio = 'PDL'), 'USER_SIMASC', SYSDATETIME(), 'ACT',
'las partes que asistieron a la audiencia en una sola linea y separados por comas',
'NATIVE: select stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido), rtrim('' ''+pe.segundo_apellido))
        FROM ROL_PERSONA_CASO rpc
        LEFT JOIN PERSONA pe ON rpc.id_persona = pe.id_persona 
        WHERE rpc.id_caso = au.id_caso 
        AND rpc.estado_registro = ''ACT''
		AND rpc.id_persona not in ( select i.id_persona from INASISTENCIA i where i.id_audiencia = au.id_audiencia and i.tipo = ''INASIS'' and i.estado_registro = ''ACT'' )
        AND rpc.id_rol IN (select id_rol from rol where nombre in (SELECT codigo_clasificado FROM CLASIFICADOR_DOMINIO  WHERE CODIGO_CLASIFICADOR = ''PARTAPP'')
							and estado <> ''INA'' and estado_registro = ''ACT'')
        FOR xml PATH ('''')), 1, 2, '''') AS asistentes
from AUDIENCIA au
where au.id_caso = ?1
and au.id_audiencia = ?2');

/*Solucion de bug 9327 de jazz del caso CON-F-090*/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF090VisualizarTipoAudiencia';
DELETE FUNCIONALIDAD where nombre='CONF090VisualizarTipoAudiencia';
insert into FUNCIONALIDAD values ('CONF090VisualizarTipoAudiencia', 'Visualizar tipo de audiencia','app/Conciliacion/CONF090','PDL', 'ANGULAR','CONF090tabAudiencias','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF090VisualizarTipoAudiencia', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			AND d.codigo NOT IN ( 'ACO', 'SECCON', 'JEFECON');
			
			/*tipo evento CON-F-068*/
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('CDCL','TIPO_EVENTO','Caso devuelto en control de legalidad','Caso devuelto en control de legalidad.',null,null);


-- permisos CON-F-076
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF076SeguimientoCasos';
DELETE FUNCIONALIDAD where nombre='CONF076SeguimientoCasos';
INSERT INTO FUNCIONALIDAD values ('CONF076SeguimientoCasos', 'Control de seguimiento a casos','app/Conciliacion/CONF076','PDL', 'ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF076SeguimientoCasos', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON');
			
/* Seguimiento a casos Alerta Cambio29_seguimiento_a_casos*/
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('CSAA','TIPO_EVENTO','Caso sin asignación de auxiliar','Caso sin asignación de auxiliar.',null,null);


/*Data e informacion de las alertas */


DELETE DOMINIO WHERE dominio ='PERIODICIDAD' AND codigo = 'SEG';

/*
 Estado de la alerta
*/
DELETE DOMINIO WHERE dominio ='ESTADO_ALERTA';
INSERT DOMINIO VALUES('ACT','ESTADO_ALERTA','Activo','Alerta activa',null,null);
INSERT DOMINIO VALUES('INA','ESTADO_ALERTA','Inactivo','Alerta inactiva',null,null);

/**Estado notifiacion **/
DELETE DOMINIO WHERE dominio ='ESTADO_NOTIFICACION';
INSERT DOMINIO VALUES('ENVIAD','ESTADO_NOTIFICACION','Enviada','Notificación enviada',null,null);
INSERT DOMINIO VALUES('NOENVI','ESTADO_NOTIFICACION','No enviada','Notificación no enviada',null,null);
INSERT DOMINIO VALUES('FALLEJE','ESTADO_NOTIFICACION','Fallo de ejecución','Notificación no enviada por fallo interno en la alerta ',null,null);
INSERT DOMINIO VALUES('METNOE','ESTADO_NOTIFICACION','Método no encontrado','Notificación no enviada metodo no encontrado en el orquestador',null,null);
INSERT DOMINIO VALUES('SINREM','ESTADO_NOTIFICACION','Sin remitentes','Notificación no enviada por falta de remitentes',null,null);


/*DOMINIO TIPO ALERTA */

INSERT DOMINIO VALUES('PARAMET','TIPO_ALERTA','Alerta parametrizada','Alertas que se ejecutan una vez al dia a determinada hora',null,null);
INSERT DOMINIO VALUES('NEGOCIO','TIPO_ALERTA','Alerta de ejecución por negocio','Alertas que se ejecutan resultado de una acción de negocio',null,null);
INSERT DOMINIO VALUES('AUTO','TIPO_ALERTA','Alerta de ejecución automatica','Alertas que se ejecutan finalizando un proceso, dentro de un caso de uso.',null,null);

/* DOMINIO ESTADO ALERTA */

INSERT DOMINIO VALUES('PEND','ESTADO_EJECUCION_ALERTA','Alerta pendiente de ejecución','Estado de la alerta en el dia actual',null,null);
INSERT DOMINIO VALUES('EJEC','ESTADO_EJECUCION_ALERTA','Alerta ejecutada','Estado de la alerta en el dia actual',null,null);
INSERT DOMINIO VALUES('FALLO','ESTADO_EJECUCION_ALERTA','Fallo en la ejecución de la alerta','Ocurrio un error en la ejecucion de la alerta',null,null);

/* CODIGOS DIA DE LA SEMANA */

INSERT DOMINIO VALUES('1','DIA_SEMANA','Domingo','Dias de la semana',null,null);
INSERT DOMINIO VALUES('2','DIA_SEMANA','Lunes','Dias de la semana',null,null);
INSERT DOMINIO VALUES('3','DIA_SEMANA','Martes','Dias de la semana',null,null);
INSERT DOMINIO VALUES('4','DIA_SEMANA','Miércoles','Dias de la semana',null,null);
INSERT DOMINIO VALUES('5','DIA_SEMANA','Jueves','Dias de la semana',null,null);
INSERT DOMINIO VALUES('6','DIA_SEMANA','Viernes','Dias de la semana',null,null);
INSERT DOMINIO VALUES('7','DIA_SEMANA','Sábado','Dias de la semana',null,null);

/*DOMINIO_ESTADO_PROGRAMACION_ALERTA */
INSERT DOMINIO VALUES('EJEC','ESTADO_PROGRAMACION_ALERTA','Ejecutada','programacion alerta ejecutada',null,null);
INSERT DOMINIO VALUES('PEND','ESTADO_PROGRAMACION_ALERTA','Pendiente','programacion alerta pendiente',null,null);
INSERT DOMINIO VALUES('NOCUMP','ESTADO_PROGRAMACION_ALERTA','No cumplida','La condicion de la alerta ya no se cumple, al momento de ejecución',null,null);
INSERT DOMINIO VALUES('FALLO','ESTADO_PROGRAMACION_ALERTA','No cumplida','Ocurrio un error al momento de ejecutar la alerta',null,null);

/*Agrupador de roles por caso*/

INSERT DOMINIO VALUES('ROLXCASO','AGRUPADOR_ROL_ENVIO_ALERTA','Roles de un caso','Agrupador con los roles que pertenecen a la tabla de rol persona caso',null,null);
INSERT DOMINIO VALUES('ROLXCONV','AGRUPADOR_ROL_ENVIO_ALERTA','Roles de un convenio','Agrupador con los roles que pertenecen a la tabla relacionado convenio',null,null);



INSERT  CLASIFICADOR_DOMINIO 
 SELECT d.codigo  ,'ROL_PERSONA','ROLXCASO','AGRUPADOR_ROL_ENVIO_ALERTA','SIMASC',GETDATE(),'ACT' FROM 
 DOMINIO d WHERE codigo IN ('CON','ARB') AND dominio = 'ROL_PERSONA' 

 DELETE DIA_EJECUCION;
 DELETE PERSONA_ROL_ALERTA;
 DELETE NOTIFICACION;
 DELETE ALERTA;
 DBCC CHECKIDENT (PERSONA_ROL_ALERTA, RESEED,0);
 DBCC CHECKIDENT (ALERTA, RESEED,0);
 DBCC CHECKIDENT (NOTIFICACION, RESEED,0);



/***ALERTAS**/

INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES ( N'Caso sin asignación de conciliador', N'PDL', N'PARAMET', N'CSINCON', CAST(1 AS Numeric(10, 0)), N'Caso sin asignación de conciliador.', N'Después de radicado el caso si no se pudo asignar a un conciliador ', N'El caso [CODIGO_CASO], [NOMBRE_CASO] no ha sido repartido.', N'DIA', N'HAB', N'ACT', CAST(N'2017-12-27T07:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T15:00:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Cuando el cliente NO selecciona un conciliador especifico ', N'PDL', N'AUTO', N'NOT_NPC', NULL, N'Caso pendiente por aceptación', N'Después de haber asignado el conciliador al caso y si el conciliador no ha aceptado el caso y además el cliente No seleccionó el conciliador.', N'Ha sido designado como conciliador del caso [CODIGO_CASO], [NOMBRE_CASO] del centro de arbitraje y conciliación de [NOMBRE_CENTRO]. Recuerde que tiene  [PLAZO_CONTESTACION] para aceptar el caso', N'HORA', N'CAL', N'ACT', NULL, N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Cuando el cliente SI selecciona un conciliador especifico ', N'PDL', N'AUTO', N'NOT_NPP',NULL, N'Caso pendiente por aceptación', N'Después de haber asignado el conciliador al caso y si el conciliador no ha aceptado el caso y además el cliente No seleccionó el conciliador.', N'Ha sido designado como conciliador del caso [CODIGO_CASO] - [NOMBRE_CASO] del centro de arbitraje y conciliación de [NOMBRE_CENTRO]. Recuerde que tiene [PLAZO_CONTESTACION] para aceptar el caso', N'HORA', N'CAL', N'ACT', NULL, N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Primer Recordatorio  para aceptación del caso ', N'PDL', N'NEGOCIO', N'RECACE1', CAST(1 AS Numeric(10, 0)), N'Primer Recordatorio  para aceptación del caso', N'Después de haber asignado el conciliador al caso y si el conciliador no ha aceptado el caso ', N'Ha sido designado como conciliador del caso [CODIGO_CASO], [NOMBRE_CASO]  del centro de arbitraje y conciliación de [NOMBRE_CENTRO]. Recuerde que tiene [PARAMETRO_HORAS_ACEPTACION] para aceptar el caso.', N'HORA', N'CAL', N'ACT', NULL, N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Ultimo recordatorio  para aceptación del caso', N'PDL', N'NEGOCIO', N'RECACEU', CAST(1 AS Numeric(10, 0)), N'Ultimo recordatorio  para aceptación del caso', N'Antes de vencer el plazo que tiene el conciliador para aceptar el caso y si él no lo ha aceptado el caso', N'Recuerde que le queda 1 hora para aceptar el caso [CODIGO_CASO] - [NOMBRE_CASO] del centro de arbitraje y conciliación de [NOMBRE_CENTRO], de lo contrario se nombrará al conciliador suplente.', N'HORA', N'CAL', N'ACT', NULL, N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Nombramiento conciliador suplente', N'PDL', N'NEGOCIO', N'CSUPLEN', CAST(1 AS Numeric(10, 0)), N'Nombramiento conciliador suplente', N'Después de vencer el plazo para aceptar el caso.', N'Ejecución del proceso SIMASC-CU-Nombrar-suplente-como-principal', N'SEG', N'CAL', N'ACT', NULL, N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Programación de audiencia', N'PDL', N'NEGOCIO', N'PROAUC', CAST(24 AS Numeric(10, 0)), N'Programación de audiencia', N'Después de que el conciliador acepta el caso y si no hay audiencia programada', N'Señor conciliador debe programar la audiencia para el caso [CODIGO_CASO], [NOMBRE_CASO]', N'HORA', N'CAL', N'ACT', NULL, N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Cartas pendientes de impresión', N'PDL', N'PARAMET', N'PENIMP', NULL, N'Cartas pendientes de impresión', N'Todos los días hábiles en la hora especificada y si hay cartas en estado ‘pendiente de impresión’  ', N'Sr. Funcionario se tiene [CANTIDAD_CARTAS] cantidad de cartas pendientes por imprimir, para realizar la impresión de estas, es necesario que ingrese por el menú administración a la opción cartas pendientes por imprimir', N'DIA', N'HAB', N'ACT', CAST(N'2017-12-27T15:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T15:00:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Actualizar estado de cartas', N'PDL', N'PARAMET', N'AESTCAR', CAST(3 AS Numeric(10, 0)), N'Actualizar estado de cartas', N'Después de enviada la carta', N'La siguiente es la lista de las cartas cuyo estado no ha sido actualizado en el sistema [TABLA_CARTAS]', N'DIA', N'HAB', N'ACT',  CAST(N'2017-12-27T07:00:00.000' AS DateTime), N'2000', CAST(N'2018-01-12T15:53:01.973' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES ( N'Cerrar audiencia', N'PDL', N'PARAMET', N'CERAU', CAST(1 AS Numeric(10, 0)), N'Cerrar audiencia', N'Después de la fecha de celebración de la audiencia y el conciliador no ha actualizado el estado de la audiencia. Lo que quiere decir que la audiencia se encuentra en estado ‘PENDIENTE’', N'Señor conciliador, la audiencia del caso [CODIGO_CASO] - [NOMBRE_CASO] debió realizarse el [FECHA_AUDIENCIA], han pasado [NUMERO_DIAS_TRANSCURRIDOS] días hábiles y aún no se ha actualizado el estado en el sistema.', N'DIA', N'HAB', N'ACT', CAST(N'2017-12-27T07:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Generación de constancia de inasistencia  ', N'PDL', N'PARAMET', N'GENINA', CAST(3 AS Numeric(10, 0)), N'Generación de constancia de inasistencia  ', N'Después de la fecha de realización de la audiencia cuando el resultado de la audiencia establecida por el conciliador es  inasistencia y el tipo de documento ‘Constancia de Inasistencia’ en el gestor documental no se encuentre.', N'Sr Conciliador recuerde generar la constancia de inasistencia del caso [CODIGO_CASO], [NOMBRE_CASO]', N'DIA', N'HAB', N'ACT', CAST(N'2017-12-27T07:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Radicación de acta o constancia de imposibilidad', N'PDL', N'PARAMET', N'RADIMP', CAST(2 AS Numeric(10, 0)), N'Radicación de acta o constancia de imposibilidad', N'Después de la fecha de realización de la audiencia cuando el resultado de la audiencia establecida por el conciliador es  inasistencia y el tipo de documento ‘Constancia de Inasistencia’ en el gestor documental no se encuentre.', N'Sr Conciliador recuerde entregar el acta o constancia de imposibilidad del caso [CODIGO_CASO], [NOMBRE_CASO], para su registro.', N'DIA', N'HAB', N'ACT', CAST(N'2017-12-27T07:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Radicación de constancia de inasistencia', N'PDL', N'PARAMET', N'RADINA', CAST(4 AS Numeric(10, 0)), N'Radicación de constancia de inasistencia', N'Después de la fecha de realización de la audiencia y resultado de la audiencia sea inasistencia y no se halla radicado la constancia para digitalización', N'Sr Conciliador recuerde entregar la constancia del caso [CODIGO_CASO] - [NOMBRE_CASO], para su registro', N'DIA', N'HAB', N'ACT', CAST(N'2017-12-27T07:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Control de legalidad', N'PDL', N'NEGOCIO', N'CONTLEG', CAST(8 AS Numeric(10, 0)), N'Control de legalidad', N'Después de realizado el reparto para control de legalidad y si no se ha realizado control de legalidad. ', N'Recuerde que tiene pendiente realizar el control de legalidad  del acta o constancia del caso [CODIGO_CASO], [NOMBRE_CASO]', N'HORA', N'HAB', N'ACT', NULL, N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Corrección de acta o constancia', N'PDL', N'NEGOCIO', N'CORRACT', CAST(2 AS Numeric(10, 0)), N'Corrección de acta o constancia', N'Después de realizado la devolución del acta o constancia por control de legalidad ', N'Sr Conciliador recuerde que tiene pendiente realizar la corrección del acta o constancia del caso [CODIGO_CASO] - [NOMBRE_CASO]', N'DIA', N'HAB', N'ACT', NULL, N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Vencimiento del término legal para cierre de caso', N'PDL', N'PARAMET', N'VENCIE',NULL, N'Vencimiento del término legal para cierre de caso', N'Entre el día 80 y 90 después de radicado el caso , que este no esté cerrado y no exista solicitud de prórroga', N'El caso [CODIGO_CASO], [NOMBRE_CASO] que ingresó en [NOMBRE_CENTRO]  en [FECHA_RADICACION] tiene [TIEMPO_CIERRE] días y no ha sido cerrado. ', N'DIA', N'CAL', N'ACT', CAST(N'2017-12-27T11:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO  ---17
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Vencimiento del término legal para cierre de caso ', N'PDL', N'PARAMET', N'VENCI90', NULL, N'Vencimiento del término legal para cierre de caso', N'Después de 90 días de radicado el caso, que no esté cerrado  y no exista solicitud de prorroga', N'La siguiente es la lista de los casos de [NOMBRE_CENTRO] de conciliación con más de 90 días que no han sido cerrado. [TABLA_CIERRES]', N'DIA', N'CAL', N'ACT', CAST(N'2017-12-27T11:00:00.000' AS DateTime), N'prueba17', CAST(N'2017-12-29T17:05:43.430' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Lista de Casos de convenio radicados en el día por el apoderado', N'PDL', N'PARAMET', N'CONRADD', NULL, N'Lista de Casos de convenio radicados en el día por el apoderado', N'Después de radicado el caso de convenio  por un apoderado, o responsable de convenio. A la persona que radico el caso y que no es un funcionario del centro.', N'La siguiente es la lista de los casos radicados por usted el día de hoy. <br/> <br/>  [TABLA]', N'DIA', N'CAL', N'ACT', CAST(N'2017-12-27T17:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Casos de convenios radicados en el día por los apoderados', N'PDL', N'PARAMET', N'RADAPOS', NULL, N'Casos de convenios radicados en el día por los apoderados', N'Después de radicado los casos de convenio por los apoderados', N'La siguiente es la lista de los casos del convenio [NOMBRE_CONVENIO] radicados por apoderados el día de hoy. <br/> <br/>  [TABLA]', N'DIA', N'CAL', N'ACT', CAST(N'2017-12-27T17:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO--20
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Atención de Peticiones especiales ', N'PDL', N'NEGOCIO', N'ATEPET', NULL, N'Atención de Peticiones especiales ', N'Según el tiempo definido en el parámetro para cada tipo de petición especial y que no se haya dado respuesta ', N'Recuerde que tiene pendiente dar respuesta a la [TIPO_PETICION] del caso [CODIGO_CASO], [NOMBRE_CASO].', N'DIA', N'CAL', N'ACT', NULL, N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Membrecía por vencer', N'PDL', N'PARAMET', N'VENMEMB', NULL, N'Membrecía por vencer', N'Entre el día 20 y 30 antes de vencer la membresía del conciliador', N'La siguiente es la lista de los funcionarios del [NOMBRE_CENTRO] cuya membresía se encuentra próxima a vencer. <br/> <br/> [TABLA]', N'DIA', N'CAL', N'ACT', CAST(N'2017-12-27T11:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Finalización de contrato de convenio ', N'PDL', N'PARAMET', N'FINCONT', CAST(30 AS Numeric(10, 0)), N'Finalización de contrato de convenio ', N'Según el tiempo definido en el parámetro para cada tipo de petición especial y que no se haya dado respuesta ', N'Sr [ANALISTA_ASIGNADO] el contrato del convenio [NOMBRE_CONVENIO] está próximo a vencer.', N'DIA', N'CAL', N'ACT', CAST(N'2017-12-27T07:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Alerta de creación de nuevo contrato de convenio', N'PDL', N'PARAMET', N'ANUEVCO', CAST(1 AS Numeric(10, 0)), N'Alerta de creación de nuevo contrato de convenio', N'Después de la finalización del contrato  y si no existe aún nuevo contrato para el convenio', N'Sr [ANALISTA_ASIGNADO] el contrato al convenio [NOMBRE_CONVENIO] está vencido y no existe un nuevo contrato. ', N'DIA', N'CAL', N'ACT', CAST(N'2017-12-27T07:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro])
 VALUES (N'Alerta de cumplimiento de porcentaje de casos para seguimiento', N'PDL', N'PARAMET', N'PRCUMP', CAST(30 AS Numeric(10, 0)), N'Alerta de cumplimiento de porcentaje de casos para seguimiento', N'No se cumple el 10% de los casos  para seguimiento, cruce de las llamadas del último mes, con el número de casos con acuerdo [NUMERO_CASOS], cuyas obligaciones se vencen en ese mes.', N'No se cumplió con el porcentaje mínimo de casos para seguimiento.', N'DIA', N'CAL', N'ACT', CAST(N'2017-12-27T07:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
--------- Arbitraje 25
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Sin tramitar por el abogado', N'PJT', N'NEGOCIO', N'SINTRA1', CAST(8 AS Numeric(10, 0)), N'Caso sin estudio.', N'Han transcurrido 8 horas hábiles desde la designación del abogado y no se ha realizado el estudio del caso . ', N'Le quedan [NUMERO_HORAS] horas hábiles  para realizar el estudio del caso [CODIGO_CASO], [NOMBRE_CASO] .', N'HORA', N'HAB', N'ACT', NULL, N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro])
VALUES (N'Sin tramitar por el abogado', N'PJT', N'NEGOCIO', N'SINTRA2', CAST(12 AS Numeric(10, 0)), N'Caso sin estudio.', N'Han transcurrido 12 horas hábiles desde la designación del abogado y no se ha realizado el estudio del caso  ', N'Le quedan [NUMERO_HORAS] horas hábiles para realizar el estudio del caso [CODIGO_CASO], [NOMBRE_CASO] ', N'HORA', N'HAB', N'ACT', NULL, N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Trámite inactivo', N'PJT', N'PARAMET', N'TRAINA', NULL, N'Trámite inactivo', N'Hay una suspensión o requerimiento indefinido  y han transcurrido 1 mes desde la creación de la suspensión o requerimiento al cliente  ', N'Recuerde que el caso [CODIGO_CASO] - [NOMBRE_CASO] está inactivo desde hace un mes. ', N'DIA', N'HAB', N'ACT', CAST(N'2017-12-27T08:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Trámite próximo a activarse ', N'PJT', N'PARAMET', N'TRAACT', NULL, N'Trámite próximo a activarse ', N'Cuando hay suspensión definida y falta un día para la fecha de finalización de la misma', N'Recuerde que el caso  [CODIGO_CASO] - [NOMBRE_CASO] se activa [FECHA_FIN_SUSPENSION].  ', N'DIA', N'HAB', N'ACT', CAST(N'2017-12-27T08:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Digitalización de documentos', N'PJT', N'NEGOCIO', N'DOCNODI', CAST(24 AS Numeric(10, 0)), N'Documentos sin digitalizar', N'Después de asignado  el documento al digitalizador ', N'Recuerde que tiene pendiente por digitalizar los documentos del caso [CODIGO_CASO] - [NOMBRE_CASO].', N'HORA', N'CAL', N'ACT', CAST(N'2017-12-27T08:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Vencimiento para programar designación', N'PJT', N'NEGOCIO', N'VENDESG', CAST(2 AS Numeric(10, 0)), N'Vencimiento para programar designación', N'Antes de vencer el plazo para programar designación ', N'Respetado Doctor Para el caso [CODIGO_CASO] - [NOMBRE_CASO] , radicado el [FECHA_RADICACION], está próximo a cumplir el tiempo establecido para programar designación. ', N'DIA', N'HAB', N'ACT', NULL, N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Fijación de audiencia de instalación', N'PJT', N'PARAMET', N'FIJINST', NULL, N'Fijación de audiencia de instalación', N'8 días después de la aceptación de todos los árbitros  y que no se haya programado audiencia de instalación ', N'Respetado Doctor  En el caso [CODIGO_CASO] - [NOMBRE_CASO], radicado el [FECHA_RADICACION] , no se ha programado la audiencia de instalación. ', N'DIA', N'CAL', N'ACT', CAST(N'2017-12-27T08:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Nombrar suplente', N'PJT', N'NEGOCIO', N'SUPARB', CAST(5 AS Numeric(10, 0)), N'Nombrar suplente', N'Procedimiento después de transcurridos 5 días hábiles  y no tener pronunciamiento a la designación del árbitro principal', N'Se ejecuta el caso de uso SIMASC-CU-Ficha-técnica-prearbitral-5.2-Nombrar-arbitro-Suplente-como-principal ', N'DIA', N'HAB', N'ACT', NULL, N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Próximos Sorteos ', N'PJT', N'PARAMET', N'PROXSOR', NULL, N'Próximos Sorteos ', N'Antes de realizar un sorteo ', N'La siguiente es la lista de los sorteos a realizarse el día de mañana [TABLA_SORTEOS]', N'DIA', N'CAL', N'ACT', CAST(N'2017-12-27T16:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Comunicar designación – Actualizar pronunciamiento', N'PJT', N'PARAMET', N'COMDES', NULL, N'Árbitros sin comunicar designación o actualizar pronunciamiento. ', N'Debe validar que haya árbitros,  designados por sorteo a los cuales no se les haya enviado comunicación o que haya árbitros a los que se les haya enviado comunicación pero no se tenga pronunciamiento.', N'Árbitros sin comunicar designación:
La siguiente es la lista de los árbitros a quienes no se ha comunicado su designación 

[TABLA_SIN_COMUNICACION]

Árbitros sin actualizar pronunciamiento:
La siguiente es la lista de los árbitros a quienes se comunicó la designación  pero no se ha actualizado su pronunciamiento.

[TABLA_SIN_ACTUALIZACION]', N'DIA', N'CAL', N'ACT', CAST(N'2017-12-27T16:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Trámite sin movimiento', N'PJT', N'PARAMET', N'TSINMOV', NULL, N'Trámite sin movimiento', N'Han transcurrido N días y no se ha  actualizado el expediente del caso. ', N'La siguiente es la lista de casos en los cuales no se ha actualizado el expediente en los último [NUMERO_DIAS] días.

[TABLA_CASOS] ', N'DIA', N'HAB', N'ACT', CAST(N'2017-12-27T08:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Vencimiento de términos para cerrar caso', N'PJT', N'PARAMET', N'VTERCIE', CAST(15 AS Numeric(10, 0)), N'Vencimiento de términos para cerrar caso', N'Faltando 15 días para vencer el término legal para el cierre de caso  ', N'Respetado Doctor

El caso [CODIGO_CASO] - [NOMBRE_CASO], radicado el [FECHA_RADICACION] instalado el [FECHA_INSTALACION],  está próximo a cumplir el tiempo establecido por ley para cerrarse', N'DIA', N'CAL', N'ACT', CAST(N'2017-12-27T08:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'No Pronunciamiento del arbitro', N'PJT', N'NEGOCIO', N'NPROARB', CAST(3 AS Numeric(10, 0)), N'No Pronunciamiento del arbitro', N'Después de la comunicación de designación', N'Respetado Doctor

Para el caso [CODIGO_CASO] - [NOMBRE_CASO] , el(los) Arbitro(s) no se ha(n )pronunciado frente a la designación:

[TABLA_DESIGNACION]', N'DIA', N'HAB', N'ACT', NULL, N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'No Pronunciamiento del Secretario de tribunal', N'PJT', N'NEGOCIO', N'NPROSEC', CAST(3 AS Numeric(10, 0)), N'No Pronunciamiento del Secretario de tribunal', N'Después de la comunicación de designación ', N'Respetado Doctor

Para el caso [CODIGO_CASO] - [NOMBRE_CASO], el secretario [NOMBRE_SECRETARIO] no se ha pronunciado frente a la designación. ', N'DIA', N'HAB', N'ACT', NULL, N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Registrar resultado de audiencia', N'PJT', N'PARAMET', N'REGRAUD', CAST(1 AS Numeric(10, 0)), N'Registrar resultado de audiencia', N'Después de realizada la audiencia de etapa arbitral ', N'No se ha registrado el resultado de la audiencia  [NUMERO_AUDIENCIA] llevada a cabo el [FECHA_AUDIENCIA] del caso [CODIGO_CASO] - [NOMBRE_CASO].', N'DIA', N'CAL', N'ACT', CAST(N'2017-12-27T08:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Registrar minutos diarios de transcripción', N'PJT', N'PARAMET', N'REGMINDI', CAST(1 AS Numeric(10, 0)), N'Registrar minutos diarios de transcripción', N'Después de no haber realizado el registro diario de transcripción', N'No se ha realizado el registro diario de minutos transcritos, por favor realice el registro lo más pronto posible.', N'DIA', N'CAL', N'ACT', CAST(N'2017-12-27T08:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Sobre asignación de minutos de transcripción', N'PJT', N'AUTO', N'SOBASGM',NULL, N'Sobre asignación de minutos de transcripción', N'Cuando el auxiliar administrativo tenga asignados más de 500  minutos pendientes de audio  de transcripción', N'El auxiliar administrativo [NOMBRE_AUXILIAR], tiene asignados más de 500 minutos pendientes de transcripción.', N'SEG', N'CAL', N'ACT', NULL, N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Rendir cuentas', N'PJT', N'PARAMET', N'RENDCUE', CAST(1 AS Numeric(10, 0)), N'Rendir cuentas', N'Después de la audiencia de laudo', N'Respetado Doctor

Recuerde que tiene pendiente actualizar el control de ingresos y gastos del tribunal   para el caso [CODIGO_CASO] - [NOMBRE_CASO]', N'DIA', N'CAL', N'ACT', CAST(N'2017-12-27T08:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Aviso de 3 días sobre fecha de vencimiento de pago', N'PJT', N'PARAMET', N'VENPAGO', CAST(3 AS Numeric(10, 0)), N'Aviso de 3 días sobre fecha de vencimiento de pago', N'3 días antes del vencimiento del plazo para consignar ', N'Respetado Doctor

Recuerde que tiene plazo para realizar su pago correspondiente a los honorarios para su trámite en la CAC es hasta el día [FECHA_PLAZO_CONSIGNACION]. En caso en que haya realizado su pago, por favor omita esta notificación', N'DIA', N'HAB', N'ACT', CAST(N'2017-12-27T07:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro])
VALUES (N'Aviso de 1 día sobre fecha de vencimiento de pago ', N'PJT', N'PARAMET', N'VENPAGF', NULL, N'Aviso de 1 día sobre fecha de vencimiento de pago ', N'El  día de vencimiento del plazo para consignar', N'Respetado Doctor

Recuerde que hoy [FECHA_PLAZO_CONSIGNACION] es la fecha que vence el plazo para realizar la consignación de los honorarios para su trámite en la CAC. En caso en que haya realizado su pago, por favor omita esta notificación', N'DIA', N'HAB', N'ACT', CAST(N'2017-12-27T08:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES ('Aviso de no pago de la contraparte de los honorarios ', N'PJT', N'PARAMET', N'PAGCONT', NULL, N'Aviso de no pago de la contraparte de los honorarios ', N'Al día siguiente de vencimiento del plazo para consignar', N'Respetado/a Doctor/a

Reciba un cordial saludo. 

De la manera más atenta me permito informarle, que una vez vencido el término de consignación de gastos y honorarios fijado por el Tribunal, 
la parte [ROL_NO_PAGO], no efectúo el pago correspondiente.

Por consiguiente, si es su voluntad podrá consignar la suma restante dentro de los 5 días hábiles posteriores a la fecha de vencimiento del plazo para consignación inicial ', N'DIA', N'CAL', N'ACT', CAST(N'2017-12-27T08:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Fijación de audiencia primera de trámite ', N'PJT', N'AUTO', N'FIJAUT', NULL, N'Fijación de audiencia primera de trámite ', N'El día que se programa la audiencia. ', N'Se programó la audiencia primera de trámite para el caso [CODIGO_CASO] - [NOMBRE_CASO].', N'SEG', N'HAB', N'ACT', NULL, N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro])
 VALUES (N'Alarma de cierre de audiencias ', N'PJT', N'PARAMET', N'ALACAUD', NULL, N'Alarma de cierre de audiencias ', N'El día que se programa la audiencia. ', N'Respetado/a Doctor/a
La audiencia fijada para el día [FECHA_AUDIENCIA] del caso [CODIGO_CASO] - [NOMBRE_CASO] no ha sido cerrada.', N'SEG', N'CAL', N'ACT', CAST(N'2017-12-27T08:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')
GO
INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro], [estado_ejecucion])
 VALUES ( N'Asignación de caso a auxiliar administrativo', N'PDL', N'PARAMET', N'ACA', CAST(15 AS Numeric(10, 0)), N'Asignación de seguimiento', N'Realizar una asignación mensual de los casos cerrados cuyos resultados sean acuerdo  ', N'El auxiliar [NOMBRE_AUXILIAR]  fue asignado al caso [CODIGO_CASO] - [NOMBRE_CASO] para seguimiento del caso.', N'DIA', N'CAL', N'ACT', CAST(N'2018-06-07T07:00:00.000' AS DateTime), N'2000', CAST(N'2018-06-08T12:18:21.847' AS DateTime), N'ACT',NULL)
GO


/***-------**/

/**
Creacion de los dias de ejecucion para las alertas de tipo PARAMETRIZADA
*/

INSERT INTO  DIA_EJECUCION select d.codigo, 'SIMASC',GETDATE(),'ACT', a.id_alerta
FROM DOMINIO d 
INNER JOIN ALERTA a ON a.estado_registro = 'ACT'
WHERE a.estado_registro = 'ACT' AND a.estado = 'ACT'
AND a.tipo_alerta = 'PARAMET'
AND d.dominio = 'DIA_SEMANA'
AND a.codigo IN ('ACA')

/**Se ejecutan todos los dias menos los sabados y domingos*/
INSERT INTO  DIA_EJECUCION select d.codigo, 'SIMASC',GETDATE(),'ACT', a.id_alerta
FROM DOMINIO d 
INNER JOIN ALERTA a ON a.estado_registro = 'ACT'
WHERE a.estado_registro = 'ACT' AND a.estado = 'ACT'
AND a.tipo_alerta = 'PARAMET'
AND d.dominio = 'DIA_SEMANA'
AND a.codigo NOT IN ('PROXSOR','COMDES','VENCI90','ACA')
AND d.codigo NOT IN ('1','7');

/**Ejecucion especifica los dias lunes y miercoles */
INSERT INTO  DIA_EJECUCION select d.codigo, 'SIMASC',GETDATE(),'ACT', a.id_alerta
FROM DOMINIO d 
INNER JOIN ALERTA a ON a.estado_registro = 'ACT'
WHERE a.estado_registro = 'ACT' AND a.estado = 'ACT'
AND d.dominio = 'DIA_SEMANA'
AND a.codigo  IN ('PROXSOR','COMDES')
AND d.codigo  IN ('2','4');

/**Ejecucion solo los viernes**/
INSERT INTO DIA_EJECUCION VALUES ( '6', 'SIMASC',GETDATE(),'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'VENCI90') );


/*SELECCIONES DE ROLES*/
--5
INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT',(SELECT id_alerta FROM ALERTA WHERE codigo = 'CSINCON'),null,r.id_rol
FROM ROL r WHERE nombre IN ('ACO'); 

--11,12,13
INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'GENINA'),null,r.id_rol
FROM ROL r WHERE nombre IN ('CON');
INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo ='RADIMP'),null,r.id_rol
FROM ROL r WHERE nombre IN ('CON');
INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'RADINA'),null,r.id_rol
FROM ROL r WHERE nombre IN ('CON'); 


/*Clasificador de dominio*/
INSERT  CLASIFICADOR_DOMINIO
SELECT d.codigo  ,'ROL_PERSONA','ROLXCASO','AGRUPADOR_ROL_ENVIO_ALERTA','SIMASC',GETDATE(),'ACT' FROM
DOMINIO d WHERE codigo IN ('CON','ARB', 'ABO', 'AMC', 'APD', 'APO', 'APODCDO', 'APODCTE', 'ARBS', 'ARE', 'ARI', 'CONCOM', 'CONINS', 'CONVODO', 'CONVOTE', 'DDA', 'DIG', 'DTE', 'PER' ) AND dominio = 'ROL_PERSONA';
INSERT  CLASIFICADOR_DOMINIO
SELECT d.codigo  ,'ROL_PERSONA','ROLXCONV','AGRUPADOR_ROL_ENVIO_ALERTA','SIMASC',GETDATE(),'ACT' FROM
DOMINIO d WHERE codigo IN ('APOCON', 'ACO') AND dominio = 'ROL_PERSONA';
			
-- TRANS-F-041
insert into PARAMETROS_GENERALES (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values ('URLPASA', null, 'URL_PASARELA', 'Url que lleva a la pasarela de pagos', null, 'http://appqas.ccb.org.co/PasarelaPagos/ResumenPago?servicioId={servId}&solicitudAplicativo={solApp}', 'Url que redirecciona a la pasarela de pago', 'USUARIO_SIMASC', GETDATE(), 'ACT')


-- ADM-F-051

-- Permisos

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ADMF051RealizarSolicituPrestador';
DELETE FUNCIONALIDAD where nombre='ADMF051RealizarSolicituPrestador';
insert into FUNCIONALIDAD values ('ADMF051RealizarSolicituPrestador', 'Realizar Solicitudes por Prestadores de Servicio','app/Administracion/ADMF051','ADM', 'ANGULAR',NULL,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ADMF051RealizarSolicituPrestador', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('CON','ARB','ARI','PER','AMC','CONINS','ARBS');

-- Insert y Update ADM-F-051 Dominio tipo de solicitud

insert DOMINIO values ('ADICAMAT','TIPO_SOLICITUD_PRESTADOR','Tipo de solicitud de prestador cambio o adición de materia','Valor que puede tomar el campo "tipo" de la tabla SOLICITUD_PRESTADOR',null,null)

update dominio set nombre='Solicitud de activación' where codigo='ACTIVACI' and dominio ='TIPO_SOLICITUD_PRESTADOR'
update dominio set nombre='Solicitud de cambio de lista' where codigo='CAMLISTA' and dominio ='TIPO_SOLICITUD_PRESTADOR'
update dominio set nombre='Solicitud de cambio o adición de materia' where codigo='ADICAMAT' and dominio ='TIPO_SOLICITUD_PRESTADOR'
update dominio set nombre='Solicitud de suspensión' where codigo='SUSPENCI' and dominio ='TIPO_SOLICITUD_PRESTADOR'
delete dominio where codigo='CAMESPEC' AND dominio='TIPO_SOLICITUD_PRESTADOR'


-- Update ADM-F-051 Tipo documento

-- Delete permiso secretaria de conciliación
delete from FUNCIONALIDAD_ROL where nombre_funcionalidad='ADMC020MenuAdministracionUsuarios' and id_rol=73

--Alerta C8 Cerrar Audiencia

INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'CERAU'),null,r.id_rol
FROM ROL r WHERE nombre IN ('CON');


-- Correccion bug link administracion usuarios
delete FUNCIONALIDAD_ROL where nombre_funcionalidad='ADMC020SeleccionUsuarioLink' and id_rol=73
/* ESTILOS TABLA ALERTA */
INSERT INTO DOMINIO (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre) VALUES ('TEA','TABLA_ALERTA_ESTILOS','Estilos encabezado de la tabla','<html><head><style> td {padding-left:5px;padding-right:5px;padding-top:1px;padding-bottom:1px;font-size:11pt;}</style></head><body><table cellpadding=0 cellspacing=0 border=1>',null,null);

/* AJUSTES DE CLASIFICADOR DE ROLES DE ENVIO DE ALERTAS */


DELETE CLASIFICADOR_DOMINIO WHERE codigo_clasificador = 'ROLXCASO'
DELETE CLASIFICADOR_DOMINIO WHERE codigo_clasificador = 'ROLXCONV'

INSERT  CLASIFICADOR_DOMINIO
SELECT d.codigo  ,'ROL_PERSONA','ROLXCASO','AGRUPADOR_ROL_ENVIO_ALERTA','SIMASC',GETDATE(),'ACT' FROM
DOMINIO d WHERE codigo IN ('CON','ARB', 'ABO', 'AMC', 'APD', 'APO', 'APODCDO', 'APODCTE', 'ARBS', 'ARE', 'ARI', 'CONCOM', 'CONINS', 'CONVODO', 'CONVOTE', 'DDA', 'DIG', 'DTE', 'PER' ) AND dominio = 'ROL_PERSONA'


INSERT  CLASIFICADOR_DOMINIO
SELECT d.codigo  ,'ROL_PERSONA','ROLXCONV','AGRUPADOR_ROL_ENVIO_ALERTA','SIMASC',GETDATE(),'ACT' FROM
DOMINIO d WHERE codigo IN ('APOCON', 'ACO') AND dominio = 'ROL_PERSONA'


/* INSERTAR PERSONA_ROL_ALERTA - ANALISTA DE CONCILIACIÓN PARA ALERTA C19 */

INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'RADAPOS'),null,r.id_rol
FROM ROL r WHERE nombre IN ('ACO');

-- actualizacion contraseña firma pdf
update parametros_generales set valor_texto = 'goSGa1pOE119JiCOe00Ewg==' where codigo = 'PASSPDF'

/** Permisos del CON-C-014 **/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC014FacturacionConvenios';
DELETE FUNCIONALIDAD where nombre='CONC014FacturacionConvenios';
insert into FUNCIONALIDAD values ('CONC014FacturacionConvenios', 'Facturación de casos de convenios','app/Conciliacion/CONC014','PDL', 'ANGULAR',null,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC014FacturacionConvenios', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			AND d.codigo NOT IN ( 'ACO', 'JEFECON'/* ,aca auxiliar administrativo */);/* INSERTAR PERSONA_ROL_ALERTA - PARA ALERTA C12 Vencimiento Termino Legal */


INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'VENCIE'),null,r.id_rol
FROM ROL r WHERE nombre IN ('CON','ACO');

/* INSERTAR PERSONA_ROL_ALERTA - PARA ALERTA C13 Vencimiento Termino Legal */


INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'VENCI90'),null,r.id_rol
FROM ROL r WHERE nombre IN ('JEFECON');

-- Funcion sumar dias habiles
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:			Juan Nicolás Martinez
-- Create date:		21/06/2018
-- Description:		Adiciona el número de días habiles indicados a la feche recibida.
--					Puede variar dependiendo de los registros que se encuentren en la tabla DIA_FESTIVO
-- =============================================
CREATE FUNCTION [dbo].[SumarDiasHabiles]
(
	@DATE AS DATE, @NDAYS AS INT
) 
RETURNS DATE
AS
BEGIN
	IF @DATE IS NULL
        BEGIN
            RETURN NULL;
        END
    DECLARE @STARTDATE INT = 0
    DECLARE @COUNT INT = 0
    DECLARE @NEWDATE DATE=Dateadd(day, 1, @DATE)

    WHILE @COUNT < @NDAYS
      BEGIN
        IF Datepart(weekday, @NEWDATE) NOT IN ( 1, 7 )
			AND @NEWDATE NOT IN (SELECT FECHA FROM DIA_FESTIVO WHERE ESTADO_REGISTRO = 'ACT')
            SET @COUNT += 1;

            SELECT @NEWDATE = Dateadd(day, 1, @NEWDATE),
                   @STARTDATE += 1;
        END

    RETURN Dateadd(day, @STARTDATE, @DATE);
END 
GO

/* PARAMETROS GENERALES ALERTA VENMEMB*/
INSERT INTO PARAMETROS_GENERALES (codigo,tipo,nombre,valor_numerico,valor_texto,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
	VALUES ('RMI_AVEN','RANGO_EJECUCION_ALERTA_VENMEMB','Día mínimo ejecución alerta Membresía por vencer',20,null,'Día mínimo ejecución alerta Membresía por vencer','USUARIO_SIMASC','2018-06-25 00:00:00.000','ACT');
	
INSERT INTO PARAMETROS_GENERALES (codigo,tipo,nombre,valor_numerico,valor_texto,descripcion,id_usuario_modificacion,fecha_ultima_modificacion,estado_registro) 
	VALUES ('RMA_AVEN','RANGO_EJECUCION_ALERTA_VENMEMB','Día máximo ejecución alerta Membresía por vencer',30,null,'Día máximo ejecución alerta Membresía por vencer','USUARIO_SIMASC','2018-06-25 00:00:00.000','ACT');
	
/* INSERTAR PERSONA_ROL_ALERTA - PARA ALERTA C21 (VENMEMB) Membresía por vencer */
INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'VENMEMB'),null,r.id_rol
FROM ROL r WHERE nombre IN ('JEFECON');

/****** Object:  UserDefinedFunction [dbo].[fechaHoraLaborable]    Script Date: 27/06/2018 4:41:49 p.m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:			Juan Diego Cepeda Mosquera
-- Create date:		07/04/2018
-- Description:		Obtiene la fecha límite para estudio del caso
-- =============================================
CREATE FUNCTION [dbo].[fechaHoraLaborable]
(
    @FechaIngresada AS DATETIME, @HoraIngresado AS INT

)
RETURNS DATETIME
AS
BEGIN
	DECLARE @InicioJornadaLaboral AS TIME = CAST((SELECT valor_texto FROM PARAMETROS_GENERALES WHERE codigo = 'TEC_IJL' AND tipo = 'TIEMPO_ESTUDIO_CASOS_CON') AS TIME)
	DECLARE @InicioAlmuerzo AS TIME = CAST((SELECT valor_texto FROM PARAMETROS_GENERALES WHERE codigo = 'TEC_IR' AND tipo = 'TIEMPO_ESTUDIO_CASOS_CON') AS TIME)
	DECLARE @FinAlmuerzo AS TIME = CAST((SELECT valor_texto FROM PARAMETROS_GENERALES WHERE codigo = 'TEC_FR' AND tipo = 'TIEMPO_ESTUDIO_CASOS_CON') AS TIME)
	DECLARE @FinJornadaLaboral AS TIME = CAST((SELECT valor_texto FROM PARAMETROS_GENERALES WHERE codigo = 'TEC_FJL' AND tipo = 'TIEMPO_ESTUDIO_CASOS_CON') AS TIME)
	DECLARE @DiaFestivo AS DATETIME;
	DECLARE @CalculoFechaHora AS DATETIME;
	DECLARE @ExtraerHora AS TIME;
	DECLARE @DiaHabilFecha AS INT;
	DECLARE @CalculoHora AS TIME;
	DECLARE @LunesFestivo AS DATE;
	DECLARE @ExtraerHoraHH AS INT;

	DECLARE @FechaRetornada AS DATETIME;


	/*Calcula la fecha y hora ingresada y extrae la hora para la comparacion de las horas laborables*/
	BEGIN
		SELECT @CalculoFechaHora = CAST(DATEADD(HH, @HoraIngresado, @FechaIngresada) AS DATETIME)
		SELECT @ExtraerHora = CAST(DATEADD(HH, @HoraIngresado, @FechaIngresada) AS TIME)
		SELECT @ExtraerHoraHH = DATEPART(HH, @CalculoFechaHora)
		SELECT @DiaHabilFecha = DATEPART(WEEKDAY, @CalculoFechaHora)
		SELECT @DiaFestivo = CAST((SELECT FECHA FROM DIA_FESTIVO WHERE CAST(FECHA AS DATE) = @CalculoFechaHora) AS DATETIME);
		
	END

	--IF (@ExtraerHora >=  @InicioJornadaLaboral AND @ExtraerHora <= @InicioAlmuerzo OR @ExtraerHora >= @FinAlmuerzo AND @ExtraerHora <= @FinJornadaLaboral)
	IF (@ExtraerHora BETWEEN @InicioJornadaLaboral AND @InicioAlmuerzo)
	BEGIN
		IF (@DiaHabilFecha BETWEEN 2 AND 6)
		BEGIN
			IF (@DiaFestivo IS NOT NULL)
			BEGIN
				--SELECT @FechaRetornada = DATEADD(d, 1, @CalculoFechaHora) --> inicia 8
				IF (@ExtraerHoraHH < @HoraIngresado)
				BEGIN
					SELECT @FechaRetornada = DATEADD(HH, ABS(@ExtraerHoraHH - 8), DATEADD(d, 1, @CalculoFechaHora))
				END
				ELSE
					SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 1, @CalculoFechaHora))
			END
			ELSE 
				SELECT @FechaRetornada = @CalculoFechaHora					
		END
		ELSE
			SELECT @DiaHabilFecha = DATEPART(WEEKDAY, @CalculoFechaHora)
			--Si es sabado 
			IF (@DiaHabilFecha = 7)
			BEGIN
				--validar si el lunes es festivo
				SELECT @LunesFestivo =  CAST((SELECT FECHA FROM DIA_FESTIVO WHERE CAST(FECHA AS DATE) = DATEADD(d, 2, @CalculoFechaHora)) AS DATETIME);  
				IF (@LunesFestivo IS NOT NULL)
				BEGIN
					IF (@ExtraerHoraHH < @HoraIngresado)
					BEGIN
						SELECT @FechaRetornada = DATEADD(HH, ABS(@ExtraerHoraHH - 8), DATEADD(d, 3, @CalculoFechaHora))
					END
					ELSE
						SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 3, @CalculoFechaHora))
					--SELECT @FechaRetornada = DATEADD(d, 3, @CalculoFechaHora) -->08:00
				END
				ELSE
					--SELECT @FechaRetornada = DATEADD(d, 2, @CalculoFechaHora) -->08:00	
					IF (@ExtraerHoraHH < @HoraIngresado)
					BEGIN
						SELECT @FechaRetornada = DATEADD(HH, ABS(@ExtraerHoraHH - 8), DATEADD(d, 2, @CalculoFechaHora))
					END
					ELSE
						SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 2, @CalculoFechaHora))
			END
			--Si es domingo
			IF (@DiaHabilFecha = 1)
			BEGIN
				--validar si el lunes es festivo
				SELECT @LunesFestivo =  CAST((SELECT FECHA FROM DIA_FESTIVO WHERE CAST(FECHA AS DATE) = DATEADD(d, 1, @CalculoFechaHora)) AS DATETIME);  
				IF (@LunesFestivo IS NOT NULL)
				BEGIN
					--SELECT @FechaRetornada = DATEADD(d, 2, @CalculoFechaHora) -->08:00
					IF (@ExtraerHoraHH < @HoraIngresado)
					BEGIN
						SELECT @FechaRetornada = DATEADD(HH, ABS(@ExtraerHoraHH - 8), DATEADD(d, 2, @CalculoFechaHora))
					END
					ELSE
						SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 2, @CalculoFechaHora))
				END
				ELSE
					--SELECT @FechaRetornada = DATEADD(d, 1, @CalculoFechaHora) -->08:00	
					IF (@ExtraerHoraHH < @HoraIngresado)
					BEGIN
						SELECT @FechaRetornada = DATEADD(HH, ABS(@ExtraerHoraHH - 8), DATEADD(d, 1, @CalculoFechaHora))
					END
					ELSE
						SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 1, @CalculoFechaHora))
			END		
	END
	ELSE
		SELECT @CalculoHora = CAST(DATEADD(HH, @HoraIngresado, @FechaIngresada) AS TIME)
		IF(@CalculoHora >= '00:00:00' AND @CalculoHora <= '07:59:00')
		BEGIN
			IF (@DiaHabilFecha BETWEEN 2 AND 6)
			BEGIN
				IF (@DiaFestivo IS NOT NULL)
				BEGIN
					--SELECT @FechaRetornada = DATEADD(d, 1, @CalculoFechaHora) --> inicia 8
					IF (@ExtraerHoraHH < @HoraIngresado)
					BEGIN
						SELECT @FechaRetornada = DATEADD(HH, ABS(@ExtraerHoraHH - 8), DATEADD(d, 2, @CalculoFechaHora))
					END
					ELSE
						SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 2, @CalculoFechaHora))
				END
				ELSE 
					IF (@ExtraerHoraHH < @HoraIngresado)
					BEGIN
						SELECT @FechaRetornada = DATEADD(HH, ABS(@ExtraerHoraHH - 8), DATEADD(d, 1, @CalculoFechaHora))
					END
					ELSE
						SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 1, @CalculoFechaHora))
					--SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 1, @CalculoFechaHora))
			END
			ELSE
				SELECT @DiaHabilFecha = DATEPART(WEEKDAY, @CalculoFechaHora)
				--Si es sabado 
				IF (@DiaHabilFecha = 7)
				BEGIN
					--validar si el lunes es festivo
					SELECT @LunesFestivo =  CAST((SELECT FECHA FROM DIA_FESTIVO WHERE CAST(FECHA AS DATE) = DATEADD(d, 2, @CalculoFechaHora)) AS DATETIME);  
					IF (@LunesFestivo IS NOT NULL)
					BEGIN
						IF (@ExtraerHoraHH < @HoraIngresado)
						BEGIN
							SELECT @FechaRetornada = DATEADD(HH, ABS(@ExtraerHoraHH - 8), DATEADD(d, 3, @CalculoFechaHora))
						END
						ELSE
							SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 3, @CalculoFechaHora))
						--SELECT @FechaRetornada = DATEADD(d, 3, @CalculoFechaHora) -->08:00
					END
					ELSE
						--SELECT @FechaRetornada = DATEADD(d, 2, @CalculoFechaHora) -->08:00	
						IF (@ExtraerHoraHH < @HoraIngresado)
						BEGIN
							SELECT @FechaRetornada = DATEADD(HH, ABS(@ExtraerHoraHH - 8), DATEADD(d, 2, @CalculoFechaHora))----aqui
						END
						ELSE
							SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 2, @CalculoFechaHora))
				END
				--Si es domingo
				IF (@DiaHabilFecha = 1)
				BEGIN
					--validar si el lunes es festivo
					SELECT @LunesFestivo =  CAST((SELECT FECHA FROM DIA_FESTIVO WHERE CAST(FECHA AS DATE) = DATEADD(d, 1, @CalculoFechaHora)) AS DATETIME);  
					IF (@LunesFestivo IS NOT NULL)
					BEGIN
						--SELECT @FechaRetornada = DATEADD(d, 2, @CalculoFechaHora) -->08:00
						IF (@ExtraerHoraHH < @HoraIngresado)
						BEGIN
							SELECT @FechaRetornada = DATEADD(HH, ABS(@ExtraerHoraHH - 8), DATEADD(d, 2, @CalculoFechaHora))
						END
						ELSE
							SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 2, @CalculoFechaHora))
					END
					ELSE
						--SELECT @FechaRetornada = DATEADD(d, 1, @CalculoFechaHora) -->08:00	
						IF (@ExtraerHoraHH < @HoraIngresado)
						BEGIN
							SELECT @FechaRetornada = DATEADD(HH, ABS(@ExtraerHoraHH - 8), DATEADD(d, 1, @CalculoFechaHora))
						END
						ELSE
							SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 1, @CalculoFechaHora))
		END
	END
	ELSE
		SELECT @CalculoHora = CAST(DATEADD(HH, @HoraIngresado, @FechaIngresada) AS TIME)
		IF(@CalculoHora >= CAST('17:00:00' AS TIME) AND @CalculoHora <= CAST('23:59:00' AS TIME))
		BEGIN
				IF (@DiaHabilFecha BETWEEN 2 AND 6)
				BEGIN
					IF (@DiaFestivo IS NOT NULL)
					BEGIN
						--SELECT @FechaRetornada = DATEADD(d, 1, @CalculoFechaHora) --> inicia 8
						IF (@ExtraerHoraHH < @HoraIngresado)
						BEGIN
							SELECT @FechaRetornada = DATEADD(HH, ABS(@ExtraerHoraHH - 8), DATEADD(d, 2, @CalculoFechaHora))
						END
						ELSE
							SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 2, @CalculoFechaHora))
					END
					ELSE 
						IF (@ExtraerHoraHH < @HoraIngresado)
						BEGIN
							SELECT @FechaRetornada = DATEADD(HH, ABS(@ExtraerHoraHH - 8), DATEADD(d, 1, @CalculoFechaHora))
						END
						ELSE
							SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 1, @CalculoFechaHora))
						--SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 1, @CalculoFechaHora))
				END
				ELSE
					SELECT @DiaHabilFecha = DATEPART(WEEKDAY, @CalculoFechaHora)
					--Si es sabado 
					IF (@DiaHabilFecha = 7)
					BEGIN
						--validar si el lunes es festivo
						SELECT @LunesFestivo =  CAST((SELECT FECHA FROM DIA_FESTIVO WHERE CAST(FECHA AS DATE) = DATEADD(d, 2, @CalculoFechaHora)) AS DATETIME);  
						IF (@LunesFestivo IS NOT NULL)
						BEGIN
							IF (@ExtraerHoraHH < @HoraIngresado)
							BEGIN
								SELECT @FechaRetornada = DATEADD(HH, ABS(@ExtraerHoraHH - 8), DATEADD(d, 3, @CalculoFechaHora))
							END
							ELSE
								SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 3, @CalculoFechaHora))
							--SELECT @FechaRetornada = DATEADD(d, 3, @CalculoFechaHora) -->08:00
						END
						ELSE
							--SELECT @FechaRetornada = DATEADD(d, 2, @CalculoFechaHora) -->08:00	
							IF (@ExtraerHoraHH < @HoraIngresado)
							BEGIN
								SELECT @FechaRetornada = DATEADD(HH, ABS(@ExtraerHoraHH - 8), DATEADD(d, 2, @CalculoFechaHora))----aqui
							END
							ELSE
								SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 2, @CalculoFechaHora))
					END
					--Si es domingo
					IF (@DiaHabilFecha = 1)
					BEGIN
						--validar si el lunes es festivo
						SELECT @LunesFestivo =  CAST((SELECT FECHA FROM DIA_FESTIVO WHERE CAST(FECHA AS DATE) = DATEADD(d, 1, @CalculoFechaHora)) AS DATETIME);  
						IF (@LunesFestivo IS NOT NULL)
						BEGIN
							--SELECT @FechaRetornada = DATEADD(d, 2, @CalculoFechaHora) -->08:00
							IF (@ExtraerHoraHH < @HoraIngresado)
							BEGIN
								SELECT @FechaRetornada = DATEADD(HH, ABS(@ExtraerHoraHH - 8), DATEADD(d, 2, @CalculoFechaHora))
							END
							ELSE
								SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 2, @CalculoFechaHora))
						END
						ELSE
							--SELECT @FechaRetornada = DATEADD(d, 1, @CalculoFechaHora) -->08:00	
							IF (@ExtraerHoraHH < @HoraIngresado)
							BEGIN
								SELECT @FechaRetornada = DATEADD(HH, ABS(@ExtraerHoraHH - 8), DATEADD(d, 1, @CalculoFechaHora))
							END
							ELSE
								SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 1, @CalculoFechaHora))
					END
					ELSE
						SELECT @FechaRetornada = DATEADD(HH, -ABS(@ExtraerHoraHH - 8), DATEADD(d, 1, @CalculoFechaHora))
		END
	RETURN @FechaRetornada
END

GO



/* Ajuste permisos se incluye analista de conciliación caso de uso CON-F-063 - TRANS-F-1008 */

-- permisos

 DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF063ActualizacionCapacitacionenMASC';
DELETE FUNCIONALIDAD where nombre='CONF063ActualizacionCapacitacionenMASC';
insert into FUNCIONALIDAD values ('CONF063ActualizacionCapacitacionenMASC', 'Actualización Capacitación en MASC','app/Conciliacion/CONF063','ADM', 'ANGULAR',NULL,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF063ActualizacionCapacitacionenMASC', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('CON','JEFECON', 'SECCON','ACO');
			

/* Alerta C-15 Actualizar estado cartas */
			
INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'AESTCAR'),null,r.id_rol
FROM ROL r WHERE nombre IN ('SECCON');


/* Alerta A-5 Asignación de un documento a un digitalizador */
INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'DOCNODI'),null,r.id_rol
FROM ROL r WHERE nombre IN ('ASA','DIG');


/* PARAMETRO TIEMPO ESTUDIO CASO ARBITRAJE POR ABOGADO, ALERTAS SINTRA1, SINTRA2 */
INSERT INTO PARAMETROS_GENERALES (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
VALUES ('TECA', 1, 'TIEMPO_ESTUDIO_CASO_ARBITRAJE','Tiempo de estudio de caso de arbitraje',16,null,'Tiempo máximo de estudio de caso de arbitraje','USUARIO_SIMASC','2018-06-28 00:00:00.000','ACT');

/* Alerta C-20 Peticiones especiales */
UPDATE ALERTA SET tipo_alerta = 'PARAMET', hora_ejecucion = '2017-12-27 08:00:00.000', estado_ejecucion = 'PEND'
 WHERE id_alerta IN (SELECT id_alerta FROM ALERTA WHERE codigo = 'ATEPET')
 
 -- Funcion sumar dias habiles
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:			Juan Nicolás Martinez
-- Create date:		21/06/2018
-- Description:		Adiciona el número de días habiles indicados a la feche recibida.
--					Puede variar dependiendo de los registros que se encuentren en la tabla DIA_FESTIVO
-- Actualizacion: Se agrega casteo a fecha de la tabla DIA_FESTIVO para no tener en cuenta las horas
-- Fecha actualización: 06/07/2018
-- =============================================
ALTER FUNCTION [dbo].[SumarDiasHabiles]
(
	@DATE AS DATE, @NDAYS AS INT
) 
RETURNS DATE
AS
BEGIN
	IF @DATE IS NULL
        BEGIN
            RETURN NULL;
        END
    DECLARE @STARTDATE INT = 0
    DECLARE @COUNT INT = 0
    DECLARE @NEWDATE DATE=Dateadd(day, 1, @DATE)

    WHILE @COUNT < @NDAYS
      BEGIN
        IF Datepart(weekday, @NEWDATE) NOT IN ( 1, 7 )
			AND CAST(@NEWDATE as date) NOT IN (SELECT CAST(FECHA as Date) FROM DIA_FESTIVO WHERE ESTADO_REGISTRO = 'ACT')
            SET @COUNT += 1;

            SELECT @NEWDATE = Dateadd(day, 1, @NEWDATE),
                   @STARTDATE += 1;
        END

    RETURN Dateadd(day, @STARTDATE, @DATE);
END 
GO

-- Alerta cartas pendientes de impresión

-- Update alerta C-14 Cartas Pendientes de Impresión
update alerta set texto='Sr. Funcionario se tiene [CANTIDAD_CARTAS] cartas pendientes por imprimir correspondientes al [NOMBRE_CENTRO], para realizar la impresión de estas, es necesario que ingrese por el menú administración a la opción cartas pendientes por imprimir.' where id_alerta=8

-- Codigo: PENIMP

INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'PENIMP'),null,r.id_rol
FROM ROL r WHERE nombre IN ('SECCON');


-- Insert persona rol alerta A-18

INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'SOBASGM'),null,r.id_rol
FROM ROL r WHERE nombre IN ('ASA','JEFEARB');

-- Insert parametro general minutos transcripción alerta A-18
insert parametros_generales values ('MMT',1,'MINUTOS_MAXIMO_TRANSCRIPCION','Minutos maximo de transcripcion',500,null,'Número de minutos maximo de transcripcion alerta sobreasignacion',2000,GETDATE(),'ACT')


-- Alerta C24 Cumplimiento 10% casos para seguimiento 
INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'PRCUMP'),null,r.id_rol
FROM ROL r WHERE nombre IN ('JEFECON');



/**Actulizacion de alertas */ 

UPDATE ALERTA SET texto = 'Ha sido designado como conciliador del caso [CODIGO_CASO], [NOMBRE_CASO] del centro de arbitraje y conciliación de [NOMBRE_CENTRO]. Recuerde que tiene [PLAZO_CONTESTACION] días para aceptar el caso'
WHERE id_alerta IN (SELECT id_alerta FROM ALERTA WHERE codigo = 'NOT_NPC')

UPDATE ALERTA SET texto =  N'Ha sido designado como conciliador del caso [CODIGO_CASO] - [NOMBRE_CASO] del centro de arbitraje y conciliación de [NOMBRE_CENTRO]. Recuerde que tiene [PLAZO_CONTESTACION] días para aceptar el caso'
WHERE id_alerta IN (SELECT id_alerta FROM ALERTA WHERE codigo = 'NOT_NPP')


--Alerta A8 nombrar suplente como principal

UPDATE ALERTA SET tipo_alerta = 'PARAMET', hora_ejecucion = '2017-12-27 08:00:00.000', estado_ejecucion = 'PEND'
 WHERE id_alerta IN (SELECT id_alerta FROM ALERTA WHERE codigo = 'SUPARB')

/**Actulizacion de alertas A13 A14 */ 
 UPDATE ALERTA SET tipo_alerta = 'PARAMET', hora_ejecucion = '2017-12-27 08:00:00.000', estado_ejecucion = 'PEND'
WHERE id_alerta IN (SELECT id_alerta FROM ALERTA WHERE codigo = 'NPROARB')

UPDATE ALERTA SET tipo_alerta = 'PARAMET', hora_ejecucion = '2017-12-27 08:00:00.000', estado_ejecucion = 'PEND'
WHERE id_alerta IN (SELECT id_alerta FROM ALERTA WHERE codigo = 'NPROSEC')

-- Alerta A13 no pronunciamiento del arbitro 
INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'NPROARB'),null,r.id_rol
FROM ROL r WHERE nombre IN ('ABO');

--Alerta A14 no pronunciamiento del secretario 
INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'NPROSEC'),null,r.id_rol
FROM ROL r WHERE nombre IN ('ABO', 'ARE','ARI','ARBS','ARB');

/* Alerta A23 Fijacion de Audiencia Primera de Tramite*/
INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'FIJAUT'),null,r.id_rol
FROM ROL r WHERE nombre IN ('JEFEARB','ASA');

/* Alerta A7 Fijación de audiencia de instalación */ 
INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'FIJINST'),null,r.id_rol
FROM ROL r WHERE nombre IN ('ABO');

/* Alerta A24  Cierre de Audiencias*/
INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'ALACAUD'),null,r.id_rol
FROM ROL r WHERE nombre IN ('ABO');

/* Alerta A12 Vencimiento de terminos legales para cierre del caso*/

delete CLASIFICADOR_DOMINIO where codigo_clasificado='SEC' and codigo_clasificador='ROLXCASO' and dominio_clasificador='AGRUPADOR_ROL_ENVIO_ALERTA'
insert clasificador_dominio values ('SEC','ROL_PERSONA','ROLXCASO','AGRUPADOR_ROL_ENVIO_ALERTA','SIMASC',getdate(),'ACT')

INSERT PERSONA_ROL_ALERTA 
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'VTERCIE'),null,r.id_rol
FROM ROL r WHERE nombre IN ('SEC');

/* Alerta A3 Trámite inactivo*/
update ALERTA set tipo_periodicidad = 'CAL' where codigo = 'TRAINA'

DELETE DIA_EJECUCION WHERE id_alerta = ( select id_alerta from ALERTA where codigo = 'TRAINA' )

INSERT INTO DIA_EJECUCION (dia, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro, id_alerta)
select d.codigo, 'SIMASC',GETDATE(),'ACT', a.id_alerta
FROM DOMINIO d 
INNER JOIN ALERTA a ON a.estado_registro = 'ACT'
WHERE a.estado_registro = 'ACT' AND a.estado = 'ACT'
AND a.tipo_alerta = 'PARAMET'
AND d.dominio = 'DIA_SEMANA'
AND a.codigo = 'TRAINA'

insert into PARAMETROS_GENERALES (codigo, editable, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values ('TIEM_INA', 1, 'ALERTA_TRAINA', 'Tiempo de la alerta de "Tramite inactivo"', 1, null, 'Valor en MESES del tiempo de inactividad que valida el sistema para generar la alerta de "Tramite inactivo"', 'USUARIO_SIMASC', GETDATE(), 'ACT')

INSERT PERSONA_ROL_ALERTA ( id_usuario_modificacion, fecha_ultima_modificacion, estado_registro, id_alerta, id_persona, id_rol )
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'TRAINA'), null, r.id_rol
FROM ROL r WHERE nombre IN ('ABO');

/* Alerta A4 Trámite próximo a activarse*/
update ALERTA set tipo_periodicidad = 'CAL', valor = 1 where codigo = 'TRAACT'

DELETE DIA_EJECUCION WHERE id_alerta = ( select id_alerta from ALERTA where codigo = 'TRAACT' )

INSERT INTO DIA_EJECUCION (dia, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro, id_alerta)
select d.codigo, 'SIMASC',GETDATE(),'ACT', a.id_alerta
FROM DOMINIO d 
INNER JOIN ALERTA a ON a.estado_registro = 'ACT'
WHERE a.estado_registro = 'ACT' AND a.estado = 'ACT'
AND a.tipo_alerta = 'PARAMET'
AND d.dominio = 'DIA_SEMANA'
AND a.codigo = 'TRAACT'

INSERT PERSONA_ROL_ALERTA ( id_usuario_modificacion, fecha_ultima_modificacion, estado_registro, id_alerta, id_persona, id_rol )
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'TRAACT'), null, r.id_rol
FROM ROL r WHERE nombre IN ('ABO');

/* Alerta A6 Vencimiento para programar designación*/
update alerta set tipo_periodicidad = 'CAL', valor = 3, tipo_alerta = 'PARAMET', hora_ejecucion = '2017-12-27 10:00:00.000', descripcion_de_alerta = 'Después de radicado el caso y si no se ha programado audiencia' where codigo = 'VENDESG'

INSERT PERSONA_ROL_ALERTA ( id_usuario_modificacion, fecha_ultima_modificacion, estado_registro, id_alerta, id_persona, id_rol )
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'VENDESG'), null, r.id_rol
FROM ROL r WHERE nombre IN ('ABO');

INSERT INTO DIA_EJECUCION (dia, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro, id_alerta)
select d.codigo, 'SIMASC',GETDATE(),'ACT', a.id_alerta
FROM DOMINIO d 
INNER JOIN ALERTA a ON a.estado_registro = 'ACT'
WHERE a.estado_registro = 'ACT' AND a.estado = 'ACT'
AND a.tipo_alerta = 'PARAMET'
AND d.dominio = 'DIA_SEMANA'
AND a.codigo = 'VENDESG'

/* Alerta A-10-1 Comunicar designación */

update alerta set tipo_periodicidad = 'CAL', tipo_alerta = 'PARAMET', hora_ejecucion = '2017-12-27 16:00:00.000',
nombre = 'Comunicar designación',
asunto = 'Comunicar designación',
descripcion_de_alerta = 'Debe validar que haya árbitros, designados por sorteo a los cuales no se les haya enviado comunicación',
texto = 'La siguiente es la lista de los árbitros a quienes no se ha comunicado su designación: [TABLA_SIN_COMUNICACION]'
where codigo = 'COMDES'

/* Alerta A-10-2 Actualizar pronunciamiento */

INSERT [dbo].[ALERTA] ( [nombre], [tipo_servicio], [tipo_alerta], [codigo], [valor], [asunto], [descripcion_de_alerta], [texto], [periodicidad], [tipo_periodicidad], [estado], [hora_ejecucion], [id_usuario_modificacion], [fecha_ultima_modificacion], [estado_registro]) 
VALUES (N'Actualizar pronunciamiento', N'PJT', N'PARAMET', N'COMDESP', null, N'Actualizar pronunciamiento', N'Que haya árbitros a los que se les haya enviado comunicación pero no se tenga pronunciamiento',
N'La siguiente es la lista de los árbitros a quienes se comunicó la designación  pero no se ha actualizado su pronunciamiento: [TABLA_SIN_ACTUALIZACION]', N'DIA', N'CAL', N'ACT', CAST(N'2017-12-27T16:00:00.000' AS DateTime), N'SIMASC', CAST(N'2017-12-27T10:17:01.777' AS DateTime), N'ACT')

INSERT INTO  DIA_EJECUCION select d.codigo, 'SIMASC',GETDATE(),'ACT', a.id_alerta
FROM DOMINIO d 
INNER JOIN ALERTA a ON a.estado_registro = 'ACT'
WHERE a.estado_registro = 'ACT' AND a.estado = 'ACT'
AND d.dominio = 'DIA_SEMANA'
AND a.codigo  IN ( 'COMDESP' )
AND d.codigo  IN ('2','4');

/** Alerta A-11, consulta de la alerta Trámite sin movimiento, TSINMOV **/
update alerta set valor = 5 where codigo = 'TSINMOV'

INSERT PERSONA_ROL_ALERTA ( id_usuario_modificacion, fecha_ultima_modificacion, estado_registro, id_alerta, id_persona, id_rol )
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'TSINMOV'), null, r.id_rol
FROM ROL r WHERE nombre IN ( 'JEFEARB', 'ASA' );

/** alerta A-16, Registrar resultado de audiencia, REGRAUD **/
update alerta set 
texto = 'No se ha registrado el resultado de la audiencia de [TIPO_AUDIENCIA] llevada a cabo el [FECHA_AUDIENCIA] del caso [CODIGO_CASO] - [NOMBRE_CASO].'
where codigo = 'REGRAUD'

INSERT PERSONA_ROL_ALERTA ( id_usuario_modificacion, fecha_ultima_modificacion, estado_registro, id_alerta, id_persona, id_rol )
SELECT 'SIMASC' , GETDATE(), 'ACT', (SELECT id_alerta FROM ALERTA WHERE codigo = 'REGRAUD'), null, r.id_rol
FROM ROL r WHERE nombre IN ( 'AUX' );

insert clasificador_dominio values ( 'AUX', 'ROL_PERSONA', 'ROLXCASO', 'AGRUPADOR_ROL_ENVIO_ALERTA', 'SIMASC', GETDATE(), 'ACT' )

/* Alerta A-20 Aviso de 3 días sobre fecha de vencimiento de pago */
update alerta set texto= 'Respetado Doctor <br/><br/> Recuerde que tiene plazo para realizar su pago correspondiente a los honorarios para su trámite en la CAC es hasta el día [FECHA_PLAZO_CONSIGNACION]. En caso en que haya realizado su pago, por favor omita esta notificación.'
where codigo = 'VENPAGO'

/* Alerta A-21 Aviso de 1 día sobre fecha de vencimiento de pago */
update alerta set texto= 'Respetado Doctor <br/><br/> Recuerde que hoy [FECHA_PLAZO_CONSIGNACION] es la fecha que vence el plazo para realizar la consignación de los honorarios para su trámite en la CAC. En caso en que haya realizado su pago, por favor omita esta notificación.',
valor = 0
where codigo = 'VENPAGF'

/* Alerta A-22 Aviso de no pago de la contraparte de los honorarios */
update alerta set texto= 'Respetado/a Doctor/a <br/><br/> Reciba un cordial saludo. <br/><br/> De la manera más atenta me permito informarle, que una vez vencido el término de consignación de gastos y honorarios fijado por el Tribunal, la parte [ROL_NO_PAGO], no efectúo el pago correspondiente. <br/><br/> Por consiguiente, si es su voluntad podrá consignar la suma restante dentro de los 5 días hábiles posteriores a la fecha de vencimiento del plazo para consignación inicial.',
valor = 1
where codigo = 'PAGCONT'


/*  Ajuste Permisos CONC027*/

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC027FacturacionCasoTramiteOrdinario';
DELETE FUNCIONALIDAD where nombre='CONC027FacturacionCasoTramiteOrdinario';
insert into FUNCIONALIDAD values ('CONC027FacturacionCasoTramiteOrdinario', 'Facturación Caso Tramite Ordinario','app/Conciliacion/CONC027','ADM', 'ANGULAR',NULL,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC027FacturacionCasoTramiteOrdinario', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON');

--Scripts de homologacion de paises

delete from HOMOLOGACION_SISTEMA_EXTERNO where dominio_simasc='CODIGO_PAIS_MINISTERIO' and sistema_externo='MINS' and estado_registro='ACT'

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0013','CODIGO_PAIS_MINISTERIO','4','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0017','CODIGO_PAIS_MINISTERIO','8','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0023','CODIGO_PAIS_MINISTERIO','276','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0037','CODIGO_PAIS_MINISTERIO','20','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0040','CODIGO_PAIS_MINISTERIO','24','MINS','USER_SIMASC',getdate(),'ACT');


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0053','CODIGO_PAIS_MINISTERIO','682','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0059','CODIGO_PAIS_MINISTERIO','12','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0063','CODIGO_PAIS_MINISTERIO','32','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0069','CODIGO_PAIS_MINISTERIO','36','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0072','CODIGO_PAIS_MINISTERIO','40','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0077','CODIGO_PAIS_MINISTERIO','44','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0080','CODIGO_PAIS_MINISTERIO','48','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0081','CODIGO_PAIS_MINISTERIO','50','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0083','CODIGO_PAIS_MINISTERIO','52','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0087','CODIGO_PAIS_MINISTERIO','56','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0088','CODIGO_PAIS_MINISTERIO','84','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0229','CODIGO_PAIS_MINISTERIO','204','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0090','CODIGO_PAIS_MINISTERIO','60','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0097','CODIGO_PAIS_MINISTERIO','68','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0101','CODIGO_PAIS_MINISTERIO','72','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0105','CODIGO_PAIS_MINISTERIO','76','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0108','CODIGO_PAIS_MINISTERIO','96','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0111','CODIGO_PAIS_MINISTERIO','100','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0031','CODIGO_PAIS_MINISTERIO','854','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0115','CODIGO_PAIS_MINISTERIO','108','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0119','CODIGO_PAIS_MINISTERIO','64','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0127','CODIGO_PAIS_MINISTERIO','132','MINS','USER_SIMASC',getdate(),'ACT');


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0141','CODIGO_PAIS_MINISTERIO','116','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0145','CODIGO_PAIS_MINISTERIO','120','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0149','CODIGO_PAIS_MINISTERIO','124','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0203','CODIGO_PAIS_MINISTERIO','148','MINS','USER_SIMASC',getdate(),'ACT');


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0211','CODIGO_PAIS_MINISTERIO','152','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0215','CODIGO_PAIS_MINISTERIO','156','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0221','CODIGO_PAIS_MINISTERIO','196','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0159','CODIGO_PAIS_MINISTERIO','336','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0169','CODIGO_PAIS_MINISTERIO','170','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0173','CODIGO_PAIS_MINISTERIO','174','MINS','USER_SIMASC',getdate(),'ACT');



INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0187','CODIGO_PAIS_MINISTERIO','408','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0190','CODIGO_PAIS_MINISTERIO','410','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0193','CODIGO_PAIS_MINISTERIO','384','MINS','USER_SIMASC',getdate(),'ACT');


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0196','CODIGO_PAIS_MINISTERIO','188','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0891','CODIGO_PAIS_MINISTERIO','191','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0199','CODIGO_PAIS_MINISTERIO','192','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0232','CODIGO_PAIS_MINISTERIO','208','MINS','USER_SIMASC',getdate(),'ACT');


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0235','CODIGO_PAIS_MINISTERIO','212','MINS','USER_SIMASC',getdate(),'ACT');


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0239','CODIGO_PAIS_MINISTERIO','218','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0240','CODIGO_PAIS_MINISTERIO','818','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0242','CODIGO_PAIS_MINISTERIO','2222','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0244','CODIGO_PAIS_MINISTERIO','784','MINS','USER_SIMASC',getdate(),'ACT');


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0889','CODIGO_PAIS_MINISTERIO','705','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0245','CODIGO_PAIS_MINISTERIO','724','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0249','CODIGO_PAIS_MINISTERIO','840','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0253','CODIGO_PAIS_MINISTERIO','231','MINS','USER_SIMASC',getdate(),'ACT');


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0267','CODIGO_PAIS_MINISTERIO','608','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0271','CODIGO_PAIS_MINISTERIO','246','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0275','CODIGO_PAIS_MINISTERIO','250','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0281','CODIGO_PAIS_MINISTERIO','266','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0285','CODIGO_PAIS_MINISTERIO','270','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0289','CODIGO_PAIS_MINISTERIO','288','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0293','CODIGO_PAIS_MINISTERIO','292','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0301','CODIGO_PAIS_MINISTERIO','300','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0297','CODIGO_PAIS_MINISTERIO','308','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0305','CODIGO_PAIS_MINISTERIO','304','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0309','CODIGO_PAIS_MINISTERIO','312','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0313','CODIGO_PAIS_MINISTERIO','316','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0317','CODIGO_PAIS_MINISTERIO','320','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0325','CODIGO_PAIS_MINISTERIO','254','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0329','CODIGO_PAIS_MINISTERIO','324','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0334','CODIGO_PAIS_MINISTERIO','624','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0331','CODIGO_PAIS_MINISTERIO','226','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0337','CODIGO_PAIS_MINISTERIO','328','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0341','CODIGO_PAIS_MINISTERIO','332','MINS','USER_SIMASC',getdate(),'ACT');


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0345','CODIGO_PAIS_MINISTERIO','340','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0351','CODIGO_PAIS_MINISTERIO','344','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0355','CODIGO_PAIS_MINISTERIO','348','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0361','CODIGO_PAIS_MINISTERIO','356','MINS','USER_SIMASC',getdate(),'ACT');


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0365','CODIGO_PAIS_MINISTERIO','360','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0369','CODIGO_PAIS_MINISTERIO','368','MINS','USER_SIMASC',getdate(),'ACT');

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0372','CODIGO_PAIS_MINISTERIO','364','MINS','USER_SIMASC',getdate(),'ACT');


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0511','CODIGO_PAIS_MINISTERIO','162','MINS','USER_SIMASC',getdate(),'ACT');


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0379','CODIGO_PAIS_MINISTERIO','352','MINS','USER_SIMASC',getdate(),'ACT');



INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0137','CODIGO_PAIS_MINISTERIO','136','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0165','CODIGO_PAIS_MINISTERIO','166','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0183','CODIGO_PAIS_MINISTERIO','184','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0259','CODIGO_PAIS_MINISTERIO','234','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0593','CODIGO_PAIS_MINISTERIO','612','MINS','USER_SIMASC',getdate(),'ACT')



INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0823','CODIGO_PAIS_MINISTERIO','796','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0863','CODIGO_PAIS_MINISTERIO','92','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0866','CODIGO_PAIS_MINISTERIO','850','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0383','CODIGO_PAIS_MINISTERIO','376','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0386','CODIGO_PAIS_MINISTERIO','380','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0391','CODIGO_PAIS_MINISTERIO','388','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0399','CODIGO_PAIS_MINISTERIO','392','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0403','CODIGO_PAIS_MINISTERIO','400','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0410','CODIGO_PAIS_MINISTERIO','404','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0413','CODIGO_PAIS_MINISTERIO','414','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0420','CODIGO_PAIS_MINISTERIO','418','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0426','CODIGO_PAIS_MINISTERIO','426','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0431','CODIGO_PAIS_MINISTERIO','422','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0434','CODIGO_PAIS_MINISTERIO','430','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0438','CODIGO_PAIS_MINISTERIO','434','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0076','CODIGO_PAIS_MINISTERIO','440','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0086','CODIGO_PAIS_MINISTERIO','442','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0447','CODIGO_PAIS_MINISTERIO','446','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0450','CODIGO_PAIS_MINISTERIO','450','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0455','CODIGO_PAIS_MINISTERIO','458','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0458','CODIGO_PAIS_MINISTERIO','454','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0461','CODIGO_PAIS_MINISTERIO','462','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0464','CODIGO_PAIS_MINISTERIO','466','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0467','CODIGO_PAIS_MINISTERIO','470','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0474','CODIGO_PAIS_MINISTERIO','504','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0477','CODIGO_PAIS_MINISTERIO','474','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0485','CODIGO_PAIS_MINISTERIO','480','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0488','CODIGO_PAIS_MINISTERIO','478','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0493','CODIGO_PAIS_MINISTERIO','484','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0497','CODIGO_PAIS_MINISTERIO','496','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0501','CODIGO_PAIS_MINISTERIO','500','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0504','CODIGO_PAIS_MINISTERIO','175','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0505','CODIGO_PAIS_MINISTERIO','508','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0508','CODIGO_PAIS_MINISTERIO','520','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0517','CODIGO_PAIS_MINISTERIO','524','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0521','CODIGO_PAIS_MINISTERIO','558','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0525','CODIGO_PAIS_MINISTERIO','562','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0528','CODIGO_PAIS_MINISTERIO','566','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0538','CODIGO_PAIS_MINISTERIO','578','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0542','CODIGO_PAIS_MINISTERIO','540','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0548','CODIGO_PAIS_MINISTERIO','554','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0556','CODIGO_PAIS_MINISTERIO','512','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0576','CODIGO_PAIS_MINISTERIO','586','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0543','CODIGO_PAIS_MINISTERIO','275','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0580','CODIGO_PAIS_MINISTERIO','591','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0545','CODIGO_PAIS_MINISTERIO','598','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0586','CODIGO_PAIS_MINISTERIO','600','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0589','CODIGO_PAIS_MINISTERIO','604','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0599','CODIGO_PAIS_MINISTERIO','258','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0603','CODIGO_PAIS_MINISTERIO','616','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0607','CODIGO_PAIS_MINISTERIO','620','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0611','CODIGO_PAIS_MINISTERIO','630','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0628','CODIGO_PAIS_MINISTERIO','826','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0640','CODIGO_PAIS_MINISTERIO','140','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0208','CODIGO_PAIS_MINISTERIO','203','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0647','CODIGO_PAIS_MINISTERIO','214','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0670','CODIGO_PAIS_MINISTERIO','642','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0695','CODIGO_PAIS_MINISTERIO','659','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0700','CODIGO_PAIS_MINISTERIO','666','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0710','CODIGO_PAIS_MINISTERIO','654','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0715','CODIGO_PAIS_MINISTERIO','662','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0728','CODIGO_PAIS_MINISTERIO','686','MINS','USER_SIMASC',getdate(),'ACT')

insert INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0731','CODIGO_PAIS_MINISTERIO','690','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0735','CODIGO_PAIS_MINISTERIO','694','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0741','CODIGO_PAIS_MINISTERIO','702','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0744','CODIGO_PAIS_MINISTERIO','760','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0748','CODIGO_PAIS_MINISTERIO','706','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0750','CODIGO_PAIS_MINISTERIO','144','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0756','CODIGO_PAIS_MINISTERIO','710','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0759','CODIGO_PAIS_MINISTERIO','729','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0764','CODIGO_PAIS_MINISTERIO','752','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0767','CODIGO_PAIS_MINISTERIO','756','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0770','CODIGO_PAIS_MINISTERIO','740','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0776','CODIGO_PAIS_MINISTERIO','764','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0780','CODIGO_PAIS_MINISTERIO','834','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0800','CODIGO_PAIS_MINISTERIO','768','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0815','CODIGO_PAIS_MINISTERIO','780','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0820','CODIGO_PAIS_MINISTERIO','788','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0827','CODIGO_PAIS_MINISTERIO','792','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0099','CODIGO_PAIS_MINISTERIO','804','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0833','CODIGO_PAIS_MINISTERIO','800','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0845','CODIGO_PAIS_MINISTERIO','858','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0850','CODIGO_PAIS_MINISTERIO','862','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0855','CODIGO_PAIS_MINISTERIO','704','MINS','USER_SIMASC',getdate(),'ACT')


INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0890','CODIGO_PAIS_MINISTERIO','894','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0900','CODIGO_PAIS_MINISTERIO','716','MINS','USER_SIMASC',getdate(),'ACT')

INSERT INTO HOMOLOGACION_SISTEMA_EXTERNO 
(CODIGO_SIMASC,DOMINIO_SIMASC,CODIGO_SISTEMA_EXTERNO,SISTEMA_EXTERNO,ID_USUARIO_MODIFICACION,FECHA_ULTIMA_MODIFICACION,ESTADO_REGISTRO) 
VALUES ('0783','CODIGO_PAIS_MINISTERIO','262','MINS','USER_SIMASC',getdate(),'ACT')


-- Corrección dominios honorarios conciliador 

-- Delete dominio honorarios conciliador
delete DOMINIO where nombre='HONORARIOS CONCILIADOR' AND dominio='TIPO_SERVICIO_CAJA'

-- Insert Dominios Codigos honorarios por centro
INSERT DOMINIO VALUES ('05030008','TIPO_SERVICIO_CAJA','HONORARIOS CONCILIADOR','HONORARIOS CONCILIADOR',NULL,NULL)
INSERT DOMINIO VALUES ('05030014','TIPO_SERVICIO_CAJA','HONORARIOS CONCILIADOR','HONORARIOS CONCILIADOR',NULL,NULL)
INSERT DOMINIO VALUES ('05030016','TIPO_SERVICIO_CAJA','HONORARIOS CONCILIADOR','HONORARIOS CONCILIADOR',NULL,NULL)

-- Corrección dominios gastos administrativos

-- Delete dominio gastos administrativos
delete dominio where dominio='TIPO_SERVICIO_CAJA' and nombre='GASTOS ADMINISTRATIVOS'

-- Insert dominios gastos administrativo por centro
insert dominio values  ('05030007','TIPO_SERVICIO_CAJA','GASTOS ADMINISTRATIVOS','GASTOS ADMINISTRATIVOS',NULL,NULL)
insert dominio values  ('05030013','TIPO_SERVICIO_CAJA','GASTOS ADMINISTRATIVOS','GASTOS ADMINISTRATIVOS',NULL,NULL)
insert dominio values  ('05030015','TIPO_SERVICIO_CAJA','GASTOS ADMINISTRATIVOS','GASTOS ADMINISTRATIVOS',NULL,NULL)

-- Actualizacion permisos ADM-C-009 

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ADMC009AdministracionParametros';
DELETE FUNCIONALIDAD where nombre='ADMC009AdministracionParametros';
insert into FUNCIONALIDAD values ('ADMC009AdministracionParametros', 'Administración, consulta y actualización de parámetros','app/Administracion/ADMC009','ADM', 'ANGULAR',NULL,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ADMC009AdministracionParametros', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ADMINU','JEFECON','JEFEARB','DCA','SUBDICAC');


------- Modificación permisos ARB-F-063 Documentos Aportados

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063tabDocumentosAportado';
DELETE FUNCIONALIDAD where nombre='ARBF063tabDocumentosAportado';

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063tableDocumentosAportados';
DELETE FUNCIONALIDAD where nombre='ARBF063tableDocumentosAportados';


DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063divRespuestaCargaDocumento';
DELETE FUNCIONALIDAD where nombre='ARBF063divRespuestaCargaDocumento';


DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063buttonAdjuntarDocumento';
DELETE FUNCIONALIDAD where nombre='ARBF063buttonAdjuntarDocumento';


DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063divDocumentosAportados';
DELETE FUNCIONALIDAD where nombre='ARBF063divDocumentosAportados';


DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063PublicarDocumento';
DELETE FUNCIONALIDAD where nombre='ARBF063PublicarDocumento';

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063EliminarDocumento';
DELETE FUNCIONALIDAD where nombre='ARBF063EliminarDocumento';

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF097tabDocumentosAportado';
DELETE FUNCIONALIDAD where nombre='TRANSF097tabDocumentosAportado';

insert into FUNCIONALIDAD values ('ARBF063tabDocAportados', 'Documentos Aportados','app/Arbitraje/ARBF063','PJT', 'ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063tabDocAportados', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('JEFEARB','ABO','ASA','SEC','ARB','AUX','ACO','JEFECON','SECCON','CON');


insert into FUNCIONALIDAD values ('ARBF063tabDocumentosAportadoPDL', 'Documentos Aportados','app/Conciliacion/ARBF063','PDL', 'ANGULAR','ARBF063tabDocAportados','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063tabDocumentosAportadoPDL', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON','SECCON','CON');

insert into FUNCIONALIDAD values ('ARBF063tabDocumentosAportadoPJT', 'Documentos Aportados','app/Arbitraje/ARBF063','PJT', 'ANGULAR','ARBF063tabDocAportados','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063tabDocumentosAportadoPJT', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('JEFEARB','ABO','ASA','SEC','ARB','AUX');


insert into FUNCIONALIDAD values ('ARBF063divDocumentosAportadosPDL', 'Documentos Aportados','app/Conciliacion/ARBF063','PDL', 'ANGULAR','ARBF063tabDocAportados','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063divDocumentosAportadosPDL', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON','SECCON','CON');


insert into FUNCIONALIDAD values ('ARBF063divDocumentosAportadosPJT', 'Documentos Aportados','app/Arbitraje/ARBF063','PJT', 'ANGULAR','ARBF063tabDocAportados','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063divDocumentosAportadosPJT', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('JEFEARB','ABO','ASA','SEC','ARB','AUX');

insert into FUNCIONALIDAD values ('ARBF063tableDocumentosAportadosPDL', 'Documentos Aportados','app/Conciliacion/ARBF063','PDL', 'ANGULAR','ARBF063tabDocAportados','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063tableDocumentosAportadosPDL', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON','SECCON','CON');


insert into FUNCIONALIDAD values ('ARBF063tableDocumentosAportadosPJT', 'Documentos Aportados','app/Arbitraje/ARBF063','PJT', 'ANGULAR','ARBF063tabDocAportados','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063tableDocumentosAportadosPJT', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('JEFEARB','ABO','ASA','SEC','ARB','AUX');


insert into FUNCIONALIDAD values ('ARBF063divRespuestaCargaDocumentoPDL', 'Documentos Aportados','app/Conciliacion/ARBF063','PDL', 'ANGULAR','ARBF063tabDocAportados','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063divRespuestaCargaDocumentoPDL', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON','SECCON','CON');

insert into FUNCIONALIDAD values ('ARBF063divRespuestaCargaDocumentoPJT', 'Documentos Aportados','app/Arbitraje/ARBF063','PJT', 'ANGULAR','ARBF063tabDocAportados','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063divRespuestaCargaDocumentoPJT', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('JEFEARB','ABO','ASA','SEC','ARB','AUX');

insert into FUNCIONALIDAD values ('ARBF063buttonAdjuntarDocumentoPDL', 'Documentos Aportados','app/Conciliacion/ARBF063','PDL', 'ANGULAR','ARBF063tabDocAportados','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063buttonAdjuntarDocumentoPDL', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','JEFECON','SECCON','CON');

insert into FUNCIONALIDAD values ('ARBF063buttonAdjuntarDocumentoPJT', 'Documentos Aportados','app/Arbitraje/ARBF063','PJT', 'ANGULAR','ARBF063tabDocAportados','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063buttonAdjuntarDocumentoPJT', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('JEFEARB','ABO','ASA','SEC','ARB','AUX');

--____________________________________________ Solución Bugs merge________________________________________________
-- ajuste bug 7824

update FUNCIONALIDAD set titulo = 'Consultar fecha de radicación' where nombre = 'ARBF068DatosBasicosFechaRadicacionConsulta'

delete FUNCIONALIDAD where nombre = 'ARBF068DatosBasicosAbogadoAsignado'
delete FUNCIONALIDAD_ROL where nombre_funcionalidad = 'ARBF068DatosBasicosAbogadoAsignado'
insert into funcionalidad values ('ARBF068DatosBasicosAbogadoAsignado', 'Abogado asignado',	'app/Arbitraje/ARBF068', 'PJT',	'ANGULAR',	'TRANSF097divDatosBasicos',	'SIMASC_USER',	GETDATE(),	'ACT')
insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','ARBF068DatosBasicosAbogadoAsignado',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA') and  d.codigo  
		NOT IN ('JEFEARB');

update FUNCIONALIDAD set nombre_funcionalidad_padre = 'TRANSF097divDatosBasicos' where nombre = 'ARBF068DatosBasicosAbogadoAsignadoReadonly'
delete FUNCIONALIDAD_ROL where nombre_funcionalidad = 'ARBF068DatosBasicosAbogadoAsignadoReadonly'
insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','ARBF068DatosBasicosAbogadoAsignadoReadonly',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA') and  d.codigo  
		IN ('JEFEARB');

-- ajuste bug 7840
insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','ARBF063buttonAdjuntarDocumentoPJT',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA') and  d.codigo  
		in ('JEFEARB', 'ASA', 'ARB', 'AUX')
		
		
----Bug de schema
		
		
ALTER TABLE CASO ALTER COLUMN id_sede NUMERIC(18,0)  NULL;



-- Corrección Defectos Regresión

-- Modificación Permisos Opción Sedes

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='ADM019sedes' AND ID_ROL=64
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='ADM019sedes' AND ID_ROL=110
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='ADM019sedes' AND ID_ROL=111
insert FUNCIONALIDAD_ROL values('USUARIO_SIMASC',getdate(),'INA','ADM019sedes',110)
insert FUNCIONALIDAD_ROL values('USUARIO_SIMASC',getdate(),'INA','ADM019sedes',111)

-- Modificación Permisos Opción Salas

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='ADM016salas' AND ID_ROL=64
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad='ADM016salas' AND ID_ROL=110
insert FUNCIONALIDAD_ROL values('USUARIO_SIMASC',getdate(),'INA','ADM016salas',110)


--Agrupadores de partes 
 INSERT AGRUPAMIENTO_ROL 
 SELECT id_rol,s.id_servicio,'ROLPARTE','USUARIO_SIMASC',GETDATE(),'ACT' 
 FROM ROL r INNER JOIN  SERVICIO s ON s.estado_registro = 'ACT'  
 WHERE r.nombre IN ('CONVOTE','CONVODO','APODCTE','APODCDO')
 AND s.id_servicio IN (8,9)

-- Correccion bug 7837
update VALOR_PLANTILLA_CARTA set consulta = 'select concat(cp.primerNombreORazonSocial," ", CONCAT(cp.segundoNombre, " ",concat(cp.primerApellido," ", concat(cp.segundoApellido," ")))) 
from RolPersonaCaso as cr left join cr.persona as cp where cr.esPresidente = true and cr.estado = "ACE" and cr.estadoRegistro = "ACT" and cr.rolPersonaCasoPK.idCaso=:idCaso' 
where nombre_valor_dinamico = 'presidenteP' and id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCCAA')

update VALOR_PLANTILLA_CARTA set consulta = 'select rpc.persona.primerApellido from RolPersonaCaso rpc 
where rpc.caso.idCaso=:idCaso and rpc.rol.idRol IN(select r.idRol from Rol r where r.nombre IN (''ARB'', ''ARI''))  and rpc.estado = ''ACE'' AND rpc.esPresidente = true' 
where nombre_valor_dinamico = 'apellidoPresidenteP' and id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where nombre = 'PCCAA')


-- Corrección Permisos Casos pendientes de Aceptación

 delete from FUNCIONALIDAD_ROL where nombre_funcionalidad='CONF101CasosPendientes' and id_rol=110
 insert FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',getdate(),'INA','CONF101CasosPendientes',110)
 
 
 -- Corrección Permisos Reporte Audiencia por Fecha
 
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC032ReporteAudienciasPorFecha';
DELETE FUNCIONALIDAD where nombre='CONC032ReporteAudienciasPorFecha';
insert into FUNCIONALIDAD values ('CONC032ReporteAudienciasPorFecha', 'Crear Convenios de Conciliacion','app/Conciliacion/CONC032','PDL', 'ANGULAR',null,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC032ReporteAudienciasPorFecha', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON','AUX');
			
			
-- Ajuste estado conciliador activo

INSERT DOMINIO VALUES ('ECA','ESTADO_CONCILIADORES','Activo','Activo',null,null)
delete dominio where codigo='ECACTIVO' AND DOMINIO='ESTADO_CONCILIADORES'

-- Ajuste valor de alerta fijacion audiencia instalación 

   update alerta set valor=8 where codigo='FIJINST'
   
   
 -- Corrección defecto 9757
 
   
update alerta set texto='La siguiente es la lista de los casos radicados por usted el día de hoy. <br/><br/> [TABLA]'   where codigo='CONRADD'
update alerta set texto='La siguiente es la lista de los casos del convenio [NOMBRE_CONVENIO] radicados por apoderados el día de hoy. <br/> <br/> [TABLA]' where codigo='RADAPOS'


-- Ajuste bug 9784 reportado en jazz
insert into CLASIFICADOR_DOMINIO (codigo_clasificado, dominio_clasificado, codigo_clasificador, dominio_clasificador, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values ('SECCON', 'ROL_PERSONA', 'CUSUCONC', 'AGRUPADOR_ROL_PERSONA_CONSULTA_USUARIO', '1', GETDATE(), 'ACT'),
('APOCON', 'ROL_PERSONA', 'CUSUCONC', 'AGRUPADOR_ROL_PERSONA_CONSULTA_USUARIO', '1', GETDATE(), 'ACT')

-- Ajuste bug 9783 reportado en jazz
delete FUNCIONALIDAD_ROL where nombre_funcionalidad = 'CONC1003botonCrear'
delete FUNCIONALIDAD where nombre = 'CONC1003botonCrear'
update FUNCIONALIDAD set nombre_funcionalidad_padre = 'CONC1003ConsultaAsesorias' where nombre IN ('CONC1003LinkdetalleAsesoria', 'CONC1003noLinkdetalleAsesoria', 'CONC006nuevaAsesoria')
update FUNCIONALIDAD set titulo = 'Ver detalle asesoria' where nombre = 'CONC1003LinkdetalleAsesoria'
update FUNCIONALIDAD set titulo = 'Detalle asesoria Consulta' where nombre = 'CONC1003noLinkdetalleAsesoria'
update FUNCIONALIDAD set titulo = 'Crear asesoria' where nombre = 'CONC006nuevaAsesoria'

-- Correccion 9790
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		Juan Diego Cepeda
-- Create date:	25/06/2018
-- Description: Indica si una fecha es día hábil
-- =============================================
CREATE FUNCTION [dbo].[esDiaHabil]
(
    @Fecha AS DATETIME
)
RETURNS BIT 
AS
BEGIN
	DECLARE @DiaHabil AS BIT = 0
	DECLARE @FinDeSemana AS BIT = 0
	DECLARE @DiaFestivo AS BIT = 0
	DECLARE @DiaSemana AS INT = DATEPART(DW, @Fecha)

	SELECT @FinDeSemana = CASE WHEN @DiaSemana = 1 OR @DiaSemana = 7 THEN 1 ELSE 0 END
	SELECT @DiaFestivo = CASE WHEN CAST(@Fecha AS DATE) IN (SELECT CAST(fecha AS DATE) FROM DIA_FESTIVO) THEN 1 ELSE 0 END

	IF (@FinDeSemana <> 1 AND @DiaFestivo <> 1)
		SELECT @DiaHabil = 1

	RETURN @DiaHabil
END

-- Correccion bug  9786
delete from funcionalidad_rol where id_rol = (select id_rol from rol where nombre = 'APOCON')

insert into funcionalidad_rol
select 'USUARIO_SIMASC', GETDATE(), 'INA', nombre, (select id_rol from rol where nombre = 'APOCON')
from funcionalidad where nombre not in ('CONF107RadicarCasoConvenio', 'CONF069CargueCasos', 'TRANSF012divRadicacionDocumentos')
and estado_registro = 'ACT'

-- Corrección bug 7959 reportado en TFS
INSERT INTO PARAMETROS_GENERALES VALUES ('URL_RET', NULL, 'URL_RETOMA_SOLICITUD', 'URL_RETOMA_SOLICITUD', NULL, '//presprivqas1.ccb.org.co/simasc/#/retomarSolicitud', 'Url que redirige al usuario a la página de retoma de solicitudes virtuales', 'USUARIO_SIMASC', GETDATE(), 'ACT')

-- Correccion 9791
delete from funcionalidad_rol where id_rol = (select id_rol from rol where nombre = 'SOLTAN')


-- Corrección defecto 9798

-- Permisos Documentos Aportados defecto 9798

delete from FUNCIONALIDAD_ROL where nombre_funcionalidad='ARBF063divDocumentosAportadosPDL'
delete from funcionalidad where nombre='ARBF063divDocumentosAportadosPDL'

delete from FUNCIONALIDAD_ROL where nombre_funcionalidad='ARBF063divDocumentosAportadosPJT'
delete from funcionalidad where nombre='ARBF063divDocumentosAportadosPJT'

delete from funcionalidad_rol where nombre_funcionalidad='ARBF063divRespuestaCargaDocumentoPDL'
delete from funcionalidad where nombre='ARBF063divRespuestaCargaDocumentoPDL'

delete from funcionalidad_rol where nombre_funcionalidad='ARBF063divRespuestaCargaDocumentoPJT'
delete from funcionalidad where nombre='ARBF063divRespuestaCargaDocumentoPJT'

delete from funcionalidad_rol where nombre_funcionalidad='ARBF063tabDocumentosAportadoPDL'
delete from funcionalidad where nombre='ARBF063tabDocumentosAportadoPDL'

delete from funcionalidad_rol where nombre_funcionalidad='ARBF063tabDocumentosAportadoPJT'
delete from funcionalidad where nombre='ARBF063tabDocumentosAportadoPJT'

delete from funcionalidad_rol where nombre_funcionalidad='TRANSF081tabDocumentosAportado'
delete from funcionalidad where nombre='TRANSF081tabDocumentosAportado'


 DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063TabDocAportadosConciliacion';
DELETE FUNCIONALIDAD where nombre='ARBF063TabDocAportadosConciliacion';
insert into FUNCIONALIDAD values ('ARBF063TabDocAportadosConciliacion', 'Documentos Aportados','app/Conciliacion/ARBF063','PDL', 'ANGULAR','CONF060AccederFichaTecnicaConciliacion','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063TabDocAportadosConciliacion', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO','SECCON','JEFECON','CON');


update FUNCIONALIDAD set nombre_funcionalidad_padre='TRANSF097divFichaTecnica' where nombre='ARBF063tabDocAportados'

update FUNCIONALIDAD set titulo='Botón Adjuntar Documento', 
nombre_funcionalidad_padre='ARBF063TabDocAportadosConciliacion' where nombre='ARBF063buttonAdjuntarDocumentoPDL'

update FUNCIONALIDAD set titulo='Botón Adjuntar Documento' where nombre='ARBF063buttonAdjuntarDocumentoPJT'

update FUNCIONALIDAD set titulo='Tabla Documentos Aportados', nombre_funcionalidad_padre='ARBF063TabDocAportadosConciliacion'
 where nombre='ARBF063tableDocumentosAportadosPDL'

update FUNCIONALIDAD set titulo='Tabla Documentos Aportados' where nombre_funcionalidad_padre='ARBF063tableDocumentosAportadosPJT'


-- Permisos Datos Básicos defecto 9798
update funcionalidad set titulo='Datos Básicos' where nombre='CONF086FichaTecnicaDatosBasicosCaso'
update funcionalidad set titulo='Botón Cambiar Conciliador' where nombre='CONF086ButtonCambiarConciliador'
update funcionalidad set titulo='Botón Reversar Ultimo Resultado' where nombre='CONF086ButtonDeshacerUltimoResultado'
update funcionalidad set titulo='Botón Reabrir Caso' where nombre='CONF086ButtonReabrirCaso'

-- Corrección bug 7957 reportado en TFS
/****** Object:  UserDefinedFunction [dbo].[fechaHoraLaborable]    Script Date: 17/09/2018 04:54:43 p.m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- Batch submitted through debugger: SQLQuery1.sql|0|0|C:\Users\aswadmin\AppData\Local\Temp\2\~vsF453.sql
-- USE [SIMASC_CON]
-- GO
/****** Object:  UserDefinedFunction [dbo].[fechaHoraLaborable]    Script Date: 9/17/2018 3:30:05 PM ******/
-- SET ANSI_NULLS ON
-- GO
-- SET QUOTED_IDENTIFIER ON
-- GO

-- =============================================
-- Author:			Juan Diego Cepeda Mosquera
-- Create date:		05/07/2018
-- Description:		Suma horas hábiles a una fecha especificada
-- =============================================
ALTER FUNCTION [dbo].[fechaHoraLaborable]
(
    @FechaIngresada AS DATETIME, @HoraIngresada AS INT

)
RETURNS DATETIME
AS
BEGIN
	DECLARE @NuevaFecha AS datetime
	DECLARE @HoraNuevaFecha AS int
	DECLARE @MinutosNuevaFecha AS int

	DECLARE @HoraInicioJornadaLaboral AS time = CAST((SELECT valor_texto FROM PARAMETROS_GENERALES WHERE codigo = 'TEC_IJL' AND tipo = 'TIEMPO_ESTUDIO_CASOS_CON') AS time)
	DECLARE @HoraInicioAlmuerzo AS time = CAST((SELECT valor_texto FROM PARAMETROS_GENERALES WHERE codigo = 'TEC_IR' AND tipo = 'TIEMPO_ESTUDIO_CASOS_CON') AS time)
	DECLARE @HoraFinAlmuerzo AS time = CAST((SELECT valor_texto FROM PARAMETROS_GENERALES WHERE codigo = 'TEC_FR' AND tipo = 'TIEMPO_ESTUDIO_CASOS_CON') AS time)
	DECLARE @HoraFinJornadaLaboral AS time = CAST((SELECT valor_texto FROM PARAMETROS_GENERALES WHERE codigo = 'TEC_FJL' AND tipo = 'TIEMPO_ESTUDIO_CASOS_CON') AS time)

	DECLARE @FechaIngresadaSinHora AS datetime = CAST(CAST(@FechaIngresada AS date) AS datetime)

	DECLARE @InicioJornadaLaboral AS datetime = DATEADD(HH, DATEPART(HH, @HoraInicioJornadaLaboral), @FechaIngresadaSinHora)
	DECLARE @InicioAlmuerzo AS datetime = DATEADD(HH, DATEPART(HH, @HoraInicioAlmuerzo), @FechaIngresadaSinHora)
	DECLARE @FinAlmuerzo AS datetime = DATEADD(HH, DATEPART(HH, @HoraFinAlmuerzo), @FechaIngresadaSinHora)
	DECLARE @FinJornadaLaboral AS datetime = DATEADD(HH, DATEPART(HH, @HoraFinJornadaLaboral), @FechaIngresadaSinHora)

	DECLARE @DiferenciaHorario AS int
	DECLARE @FechaDiferenciaHorario AS datetime

	-- Adición de las horas ingresadas a la fecha ingresada
	SET @NuevaFecha = DATEADD(HH, @HoraIngresada, @FechaIngresada)

	-- Valida si la hora de la nueva fecha excede la hora de fin del horario laboral
	IF DATEPART(HH, @NuevaFecha) >= DATEPART(HH, @HoraFinJornadaLaboral) -- AND DATEPART(MI, @NuevaFecha) > 0
	BEGIN
		SET @DiferenciaHorario = DATEPART(HH, @NuevaFecha) - DATEPART(HH, @HoraFinJornadaLaboral)
		SET @NuevaFecha = DATEADD(D, 1, @NuevaFecha)
		SET @NuevaFecha = DATETIMEFROMPARTS(YEAR(@NuevaFecha), MONTH(@NuevaFecha), DAY(@NuevaFecha), DATEPART(HH, @HoraInicioJornadaLaboral), DATEPART(MI, @NuevaFecha), DATEPART(S, @NuevaFecha), DATEPART(MILLISECOND, @NuevaFecha))

		IF @DiferenciaHorario > 0
		BEGIN
			SET @NuevaFecha = DATEADD(HH, @DiferenciaHorario, @NuevaFecha)
		END

		RETURN dbo.fechaHoraLaborable(@NuevaFecha, 0)
	END
	ELSE IF DATEPART(HH, @NuevaFecha) < DATEPART(HH, @HoraInicioJornadaLaboral)
	BEGIN
		IF DATEPART(HH, @FechaIngresada - @FinJornadaLaboral) = 0
		BEGIN
			SET @DiferenciaHorario = DATEPART(HH, @NuevaFecha - @FechaIngresada)
		END
		ELSE
		BEGIN
			SET @DiferenciaHorario = DATEPART(HH, @FechaIngresada - @FinJornadaLaboral)
		END

		IF @DiferenciaHorario > 12
		BEGIN
			SET @DiferenciaHorario = @DiferenciaHorario - 12
		END

		SET @NuevaFecha = DATETIMEFROMPARTS(YEAR(@NuevaFecha), MONTH(@NuevaFecha), DAY(@NuevaFecha), DATEPART(HH, @HoraInicioJornadaLaboral), DATEPART(MI, @NuevaFecha), DATEPART(S, @NuevaFecha), DATEPART(MILLISECOND, @NuevaFecha))

		WHILE dbo.esDiaHabil(@NuevaFecha) = 0
		BEGIN
			SET @NuevaFecha = DATEADD(D, 1, @NuevaFecha)
		END

		IF @DiferenciaHorario > 0
		BEGIN
			SET @NuevaFecha = DATEADD(HH, @DiferenciaHorario, @NuevaFecha)
		END

		RETURN dbo.fechaHoraLaborable(@NuevaFecha, 0)
	END

	SET @HoraNuevaFecha = DATEPART(HH, @NuevaFecha)
	SET @MinutosNuevaFecha = DATEPART(MI, @NuevaFecha)

	-- Valida si la hora de la nueva fecha esta entre el horario de receso del horario laboral
	IF @HoraNuevaFecha >= DATEPART(HH, @HoraInicioAlmuerzo) AND @MinutosNuevaFecha > 0 AND @HoraNuevaFecha < DATEPART(HH, @HoraFinAlmuerzo) 
	BEGIN
		SET @NuevaFecha = DATEADD(HH, 1, @NuevaFecha)
	END

	-- Valida si la hora de la nueva fecha esta en la jornada hábil de la tarde y le suma la hora de descuento del almuerzo
	IF @HoraNuevaFecha BETWEEN DATEPART(HH, @HoraInicioAlmuerzo) AND DATEPART(HH, @HoraFinJornadaLaboral)
	BEGIN
		SET @NuevaFecha = DATEADD(HH, 1, @NuevaFecha)
	END

	-- Valida si la nueva fecha es un día habil
	WHILE dbo.esDiaHabil(@NuevaFecha) = 0
	BEGIN
		SET @NuevaFecha = DATEADD(D, 1, @NuevaFecha)
	END

	RETURN @NuevaFecha
END

GO


-- Corrección Defecto 9798
update funcionalidad set titulo='Cancelar Evento' where nombre='CONF072CancelarEvento'
update funcionalidad set titulo='Crear Evento' where nombre='CONF072CrearEvento'
update funcionalidad set titulo='Registrar Resultado' where nombre='CONF072RegistrarResultado'
delete FUNCIONALIDAD_ROL where nombre_funcionalidad='TRANSF040Peticiones'
delete FUNCIONALIDAD where nombre='TRANSF040Peticiones'

-- Corrección bug 7987 reportado en TFS
UPDATE PARAMETROS_GENERALES SET valor_texto = 'https://presprivqas1.ccb.org.co/simasc/#/retomarSolicitud', fecha_ultima_modificacion = GETDATE() WHERE codigo = 'URL_RET' AND tipo = 'URL_RETOMA_SOLICITUD'

-- Ajuste firma de conciliadores
insert into parametros_generales (codigo, tipo, nombre, valor_numerico, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values ('RDFC', 'GES_DOCU', 'Ruta firmas de conciliadores', null, 'C:\GestorDocumental\firmasConciliadores\', 'Ruta en la que se encuentran las firmas de los conciliadores', 'USER_SIMASC', GETDATE(), 'ACT')

update PLANTILLA_CARTA set plantilla_html = '<html>   <head> <title>Citación audiencia</title> </head>   <body>    <font face="Arial">    <p align="left">     ciudadP, dia_cartaP de mes_cartaP de ano_cartaP    </p>     <p align="left">           senorDoctorRLegalP        <br/>    dirigidoP      <br/>      direccionEnvio      <br/>    telefonoEnvio      <br/>      ciudadEnvio    </p>    <p align="right">      radicadoP    </p>    <p align="justify">      REFERENCIA: SOLICITUD DE CONCILIACION DE convocanteP PARA SOLUCIONAR LAS DIFERENCIAS SURGIDAS CON convocadoP.    </p>   <p align="justify">Apreciado(a) señor(a):</p>  <p align="justify">Nos complace invitarle a una audiencia de conciliación, el fechaAudienciaP, a las horaAudienciaP en nuestras oficinas ubicadas en direccionSedeP.</p>      <p align="justify">         leyAsistenciaP         </p>    <p align="justify">      Para nosotros es importante saber si recibieron esta comunicación, por lo cual les solicitamos que confirmen su asistencia al siguiente correo electrónico: emailConciliadorP. Por favor indique el número de caso.    </p>   <p align="justify">      Para información adicional puede comunicarse al teléfono <!--número de teléfono-->    </p>  <p align="justify">Cordialmente,</p>      <p align="justify">&#160;</p> <img src="firmaP" width="235" height="87" />   <p align="left">      conciliadorP    </p>     <p align="left">Conciliador</p>    <p align="left">      Registro registroConciliadorP    </p>   <p align="right">      Caso codigoCaso    </p>  </font>  </body>  </html>'
where nombre = 'PCADP' and tipo_servicio = 'PDL'

update PLANTILLA_CARTA set plantilla_html = '<html>   <head>  <title>Citación audiencia</title>  </head>   <body>    <font face="Arial">      <p align="left">       ciudadP, dia_cartaP de mes_cartaP de ano_cartaP       </p>     <p align="left">           senorDoctorRLegalP      <br/>       dirigidoP        <br/>        direccionEnvio        <br/>        telefonoEnvio        <br/>        ciudadEnvio      </p>     <p align="right">        radicadoP      </p>      <p align="justify">        REFERENCIA: SOLICITUD DE CONCILIACION DE convocanteP PARA SOLUCIONAR LAS DIFERENCIAS SURGIDAS CON convocadoP.      </p>   <p align="justify">Apreciado(a) señor(a):</p>        <p align="justify">      PCPAUparrafo1P    </p>      <p align="justify">      El nombreCentroP, se creó para ayudar a que las personas resuelvan LEGALMENTE sus problemas sin necesidad de ir a un juzgado. Las personas que vienen al Centro de Conciliación arreglan rápidamente sus conflictos, los solucionan pacíficamente y no se ocasionan más problemas de los que ya tienen.    </p>     <p align="justify">      Queremos comentarle que para este Centro, es un honor prestarle nuestros servicios. Por lo tanto, atentamente lo invitamos a una audiencia de conciliación en nuestras oficinas ubicada en la direccionSedeP, nombreSedeP el fechaAudienciaP a las horaAudienciaP.    </p>    <p align="justify">leyAsistenciaP</p>         <p align="justify">PCPAUparrafo4P</p>       <p align="justify">        Para nosotros es importante saber si recibieron esta comunicación, por lo cual les solicitamos que confirmen su asistencia al siguiente correo electrónico: emailConciliadorP. Por favor indique el número de caso.      </p>    <p align="justify">        Para información adicional puede comunicarse al teléfono: <!--número de teléfono-->      </p>        <p align="justify">Cordialmente,</p>      <p align="justify">&#160;</p>  <img src="firmaP" width="237" height="85" />   <p align="left">        conciliadorP      </p>     <p align="left">Conciliador</p>      <p align="left">        Registro registroConciliadorP      </p>      <p align="right">        Caso codigoCaso      </p>     </font>    </body>   </html>'
where nombre = 'PCPAU' and tipo_servicio = 'PDL'

update PLANTILLA_CARTA set plantilla_html = '<html>  <head>  <title>Peticion especial: Expedición de incumplimiento</title>  </head>  <body>    <font face="Arial" size="3">      <p align="left">       ciudadCentroP, dia_cartaP de mes_cartaP de ano_cartaP       </p>      <p align="justify">        Asunto: Respuesta solicitud de expedición de incumplimiento, caso No. codigoCaso      </p>      <p align="justify">Respetado señor(a): nombreSolicitanteP</p>    <p align="justify">    Se ha respondido su solicitud de expedición de incumplimiento radicada el fechaSolicitudP.   </p>   <p align="justify">    Esperamos haber contestado su solicitud. Para información adicional pueden comunicarse al correo electrónico correoConciliadorP   </p>      <p align="justify">Cordialmente,</p>      <p align="justify">&#160;</p>   <img src="firmaP" width="235" height="87" />   <p align="left">conciliadorP</p>  <p align="left">Conciliador</p>     </font>  </body>  </html>'
where nombre = 'PERESIMC' and tipo_servicio = 'PDL'

update PLANTILLA_CARTA set plantilla_html = '<html>  <head>  <title>Peticion especial: Reprogramación de audiencia</title>  </head>  <body>    <font face="Arial" size="3">      <p align="left">       ciudadCentroP, dia_cartaP de mes_cartaP de ano_cartaP       </p>      <p align="justify">        Asunto: Respuesta solicitud de reprogramación de audiencia, caso No. codigoCaso      </p>      <p align="justify">Respetado señor(a): nombreSolicitanteP</p>    <p align="justify">    En respuesta a su solicitud de reprogramación radicada el fechaSolicitudP se informa que con la anuencia de las partes se procede a reprogramar la audiencia para el próximo fechaAudienciaP a las horaAudienciaP para lo cual se remitirán las comunicaciones respectivas.   </p>   <p align="justify">    Esperamos haber contestado su solicitud. Para información adicional pueden comunicarse al correo electrónico correoConciliadorP   </p>      <p align="justify">Cordialmente,</p>      <p align="justify">&#160;</p>    <img src="firmaP" width="235" height="87" />  <p align="left">conciliadorP</p>  <p align="left">Conciliador</p>    </font>  </body>  </html>'
where nombre = 'PEREPRAU' and tipo_servicio = 'PDL'

update PLANTILLA_CARTA set plantilla_html = '<html>  <head>  <title>Acta de Conciliación</title>  </head>  <body>  <b><font face="Arial"><p align="center">ACTA DE AUDIENCIA DE CONCILIACIÓN</p></font></b>  <p align="justify">&#160;</p>  <p align="justify">En la ciudad de ciudadCentroP a los dia_cartaP días del mes de mes_cartaP ante conciliadorP, quien fue designado(a) por el Director del Centro como Conciliador(a), para que actuara de conformidad con lo dispuesto en el art. 116 de la Constitución Política, Ley 23 de 1991, Ley 446 de 1998, Ley 640 de 2001 y demás normas complementarias, comparecieron las siguientes personas: </p>  <b><p align="left">Parte Convocante:</p></b>  convocantesAsistentesP  <b><p align="left">Parte Convocada:</p></b>  convocadosAsistentesP  <p align="justify">Los comparecientes han manifestado que su asistencia a la diligencia ha sido voluntaria y libre de presiones y que sus decisiones tienen el mismo carácter.</p>  <b><p align="center">HECHOS:</p></b>  <p align="justify">PRIMERO. Que el Señor(a) convocanteP le solicitó el día fechaCasoP a este Centro citar a convocadoP, con el efecto de efectuar una audiencia de conciliación para solucionar diferencias en materia materiaCasoP surgidas con relación a casoHechosP.</p>  <p align="justify">SEGUNDO. </p>  <p align="justify">TERCERO. </p>  <p align="justify">CUARTO. Los comparecientes de manera voluntaria y sin presiones llegan a un acuerdo el cual se plasma a continuación de los considerandos bajo estas condiciones y bajo su responsabilidad, una vez el conciliador explica claramente los efectos del presente documento, quienes manifestaron no tener duda alguna para firmar la presente acta.</p>   <b><p align="center">CONSIDERANDO</p></b>  <p align="justify">Que el nombreCentroP fue autorizado para funcionar por la Resolución resolucionP del Ministerio de Justicia y del Derecho.</p>  <p align="justify">Que el nombreCentroP, en ejercicio de las funciones atribuidas por la Ley 23 de 1991, Ley 446 de 1998, Ley 640 de 2001, ha designado un conciliador que colabore con las facultades otorgadas por el artículo 116 de la Constitución Política de Colombia.</p>  <p align="justify">Una vez escuchadas las manifestaciones de las partes y analizadas y discutidas las propuestas presentadas por ellas, y para que se surtan los efectos previstos en los artículos 2469 y concordantes del Código Civil, Ley 23 de 1991, el Art. 66 de la Ley 446 de 1998, Ley 640 de 2001, y demás disposiciones complementarias, se ha llegado a un acuerdo conciliatorio que hace tránsito a <b>COSA JUZGADA Y PRESTA MERITO EJECUTIVO</b>, en los siguientes términos:</p>  <b><p align="center">ACUERDO</p></b>  <p align="justify">PRIMERO. </p>    obligacionesP    <p align="justify">SEGUNDO. </p>  <p align="justify">TERCERO. </p>  <b><p align="center">PARAGRAFO FINAL</p></b>    <p align="justify">Las partes se declaran a paz y salvo mutuamente y por lo tanto renuncian a iniciar cualquier reclamación judicial y extrajudicial, por la vía civil, comercial, laboral o penal, relativa al diferendo relacionado en este acuerdo siempre y cuando se cumplan por las partes, las obligaciones en este documento consignadas.</p>  <p align="justify">Para constancia se forma por quienes en ella intervinieron en la ciudad de ciudadCentroP a los dia_cartaP días del mes de mes_cartaP de ano_cartaP.</p>  <p align="justify">&#160;</p>  <p align="justify">&#160;</p>  <font face="Arial">   <p align="left">firmaConvocantesP</p>       <p align="left">firmaConvocadosP</p>        <img src="firmaP" width="235" height="87" />   <p align="justify">conciliadorP<br/>Conciliador<br/>Registro: registroConciliadorP</p>     <p align="justify">    Caso: codigoCaso     </p>  </font>  </body>  </html>'
where nombre = 'ACTACON' and tipo_servicio = 'PDL'

update PLANTILLA_CARTA set plantilla_html = '<html>  <head>  <title>Constancia de Inasistencia con Excusa</title>  </head>  <body>  <font FACE="Arial"><b>  <p align="center">CONSTANCIA DE INASISTENCIA</p>  <p align="justify">&#160;</p>  <p align="center">EL (LA) SUSCRITO (A) CONCILIADOR(A) INSCRITO(A) EN LA LISTA OFICIAL DE CONCILIADORES DEL nombreCentroP</p>  <p align="center">&#160;</p>  <p align="center">HACE CONSTAR:</p>  <p align="justify">&#160;</p>  </b><p align="justify">Que de conformidad con lo dispuesto en el artículo 2º de la Ley 640  de 2001, se procede a suscribir la presente constancia en los siguientes términos:</p>  <p align="justify">convocanteP, le solicitó el pasado fechaCasoP, a este Centro, la citación de convocadoP, a una Audiencia de Conciliación, a fin de solucionar diferencias surgidas con ocasión de casoHechosP</p>  <p align="justify">El (La) suscrito (a) conciliador (a) tras haber sido designado (a) por el Director del Centro, procedió a citar a las partes el fechaAudienciaP a las horaAudienciaP reunión a la que asistieron parteAsistenciaP</p>  <p align="justify">Dentro del término legal establecido para el efecto, mediante comunicación (escrita o vía fax, o por email)  (FECHA EXCUSA) de los corrientes, el(la) (PARTE QUE SE EXCUSA), justificó su inasistencia, aduciendo (CAUSA) , que le impidieron asistir a la audiencia.</p>  <p align="justify">En consecuencia y de conformidad con el artículo 22 de la Ley 640 de 2001, se procede a suscribir la presente constancia.</p>  <p align="justify">Dada en ciudadCentroP, dia_cartaP de mes_cartaP de ano_cartaP.</p>  <p align="justify">&#160;</p> <img src="firmaP" width="235" height="87" />  <p align="justify">conciliadorP<br/>Conciliador<br/>Registro: registroConciliadorP</p>    <p align="right">      Caso: codigoCaso    </p>    </font></body>  </html>'
where nombre = 'CONINACE' and tipo_servicio = 'PDL'

update PLANTILLA_CARTA set plantilla_html = '<html>  <head>  <title>Constancia de Inasistencia sin Excusa</title>  </head>  <body>  <font face="Arial">  <b><p align="center">CONSTANCIA DE INASISTENCIA</p>  <p align="justify">&#160;</p>  <p align="center">EL (LA) SUSCRITO (A) CONCILIADOR(A) INSCRITO(A) EN LA LISTA OFICIAL DE CONCILIADORES DEL nombreCentroP</p>  <p align="center">&#160;</p>  <p align="center">HACE CONSTAR:</p>  <p align="justify">&#160;</p>  </b><p align="justify">Que de conformidad con lo dispuesto en el artículo 2º de la Ley 640  de 2001, se procede a suscribir la presente constancia en los siguientes términos:</p>  <p align="justify">convocanteP, le solicitó el pasado fechaCasoP, a este Centro, la citación de convocadoP, a una Audiencia de Conciliación, a fin de solucionar diferencias surgidas con ocasión de casoHechosP</p>  <p align="justify">El (La) suscrito(a) conciliador(a) tras haber sido designado(a) por el Director del Centro, procedió a citar a las partes el fechaAudienciaP a las horaAudienciaP reunión a la que asistieron parteAsistenciaP</p>  <p align="justify">En consecuencia, vencido el término legal establecido para el efecto y no habiéndose presentado excusa alguna por su inasistencia, de parte de (PARTE INASISTENTE) y de conformidad con el artículo 22 de la Ley 640 de 2001, se procede a suscribir la presente constancia.</p>  <p align="justify">Dada en ciudadCentroP, dia_cartaP de mes_cartaP de ano_cartaP.</p>  <p align="justify">&#160;</p>   <img src="firmaP" width="235" height="87" />  <p align="justify">conciliadorP<br/>Conciliador<br/>Registro: registroConciliadorP</p>    <p align="right">      Caso: codigoCaso    </p>    </font></body>  </html>'
where nombre = 'CONINASE' and tipo_servicio = 'PDL'

update PLANTILLA_CARTA set plantilla_html = '<html>  <head>  <title>Constancia de Imposibilidad</title>  </head>  <body>  <b><font face="Arial">  <p align="center">CONSTANCIA DE IMPOSIBILIDAD</p>  <p align="center">&#160;</p>  <p align="center">EL(LA) SUSCRITO(A) CONCILIADOR(A) INSCRITO(A) EN LA LISTA OFICIAL DE CONCILIADORES DEL nombreCentroP</p>  <p align="center">&#160;</p>  <p align="center">HACE CONSTAR:</p>  </font></b>  <font face="Arial">  <p align="justify">&#160;</p>  <p align="justify">Que de conformidad con lo dispuesto en el artículo 2º de la Ley 640 de 2001 se procede a suscribir la presente constancia en los siguientes términos.</p>  <p align="justify">convocanteP,  le solicitó el fechaCasoP a este Centro, la citación a una audiencia de conciliación de convocadoP, a fin de resolver las diferencias presentadas, con ocasión de casoHechosP</p>  <p align="justify">El(La) suscrito(a) conciliador(a) tras haber sido designado(a) por el Director del Centro, procedió a citar a las partes el fechaAudienciaP, reunión a la que asistieron:</p>  <p align="justify">Parte Convocante:</p>  <table>convocantesAsistentesP</table>  <p align="justify">Parte Convocada:</p>  <table>convocadosAsistentesP</table>  </font>  <font face="Arial">  <p align="justify">&#160;</p>  <p align="justify">A quienes en el curso de la audiencia se les dio el uso de la palabra y después de un intercambio de opiniones, quedó clara la imposibilidad de llegar a un acuerdo conciliatorio en relación con los hechos que motivaron la solicitud de conciliación.</p>  <p align="justify">Para constancia se firma el fechaAudienciaP, por quienes asistieron:</p>  </font>    <p align="justify">&#160;</p>  <p align="justify">&#160;</p>  <font face="Arial">   <table>firmaConvocantesP</table>     <table>firmaConvocadosP</table> <p align="justify">&#160;</p>   <img src="firmaP" width="235" height="87" />  <p align="justify">conciliadorP<br/>Conciliador<br/>Registro: registroConciliadorP</p>     <p align="right">    Caso: codigoCaso     </p>  </font>  </body>  </html>'
where nombre = 'CONSIMPO' and tipo_servicio = 'PDL'


-- Correccion Defecto 9852 - 9853


--Corrección Defecto 9852 - 9853

-- Permisos 

update FUNCIONALIDAD set titulo='Reporte Casos no cobrados' where nombre='CONC038ReporteCasosNoCobrados'
update FUNCIONALIDAD set titulo='Reporte Casos devueltos en control de legalidad' where nombre='CONC036ReporteCasosDevueltos'
update FUNCIONALIDAD set titulo='Reporte Casos cobrados' where nombre='CONC035ReporteCasosCobrados'
update FUNCIONALIDAD set titulo='Reporte Casos cerrados de convenios' where nombre='CONC033ReporteCasosCerradosConvenio'
update FUNCIONALIDAD set titulo='Reporte Libro de Actas o Constancias' where nombre='CONF098ImpresionPaginasLibrosRegistro'
update FUNCIONALIDAD set titulo='Reporte Casos Cerrados Conciliación' where nombre='CONC034ReporteCasosCerradosConciliacion'
delete FUNCIONALIDAD_ROL where nombre_funcionalidad='reportesConc' and id_rol=110
delete FUNCIONALIDAD_ROL where nombre_funcionalidad='CONC040ReporteCasosPorApoderado' and id_rol=110
insert funcionalidad_rol values ('USUARIO_SIMASC',getdate(),'INA','CONC039ReporteCasosPagados',110)


DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC042ReporteCasosSinCerrar90Dias';
DELETE FUNCIONALIDAD where nombre='CONC042ReporteCasosSinCerrar90Dias';
insert into FUNCIONALIDAD values ('CONC042ReporteCasosSinCerrar90Dias', 'Reporte de casos sin cerrar más de 90 días','app/Conciliacion/CONC042','PDL', 'ANGULAR','reportesConc','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC042ReporteCasosSinCerrar90Dias', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON','AUX');




DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC051ReporteSeguimientoCaso';
DELETE FUNCIONALIDAD where nombre='CONC051ReporteSeguimientoCaso';
insert into FUNCIONALIDAD values ('CONC051ReporteSeguimientoCaso', 'Reporte de Seguimiento Casos','app/Conciliacion/CONC051','PDL', 'ANGULAR','reportesConc','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC051ReporteSeguimientoCaso', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON');


			

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC044ReporteDeDevolucionDeDinero';
DELETE FUNCIONALIDAD where nombre='CONC044ReporteDeDevolucionDeDinero';
insert into FUNCIONALIDAD values ('CONC044ReporteDeDevolucionDeDinero', 'Reporte de devolución de dinero','app/Conciliacion/CONC044','PDL', 'ANGULAR','reportesConc','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC044ReporteDeDevolucionDeDinero', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON');


DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC049ReporteAsesorias';
DELETE FUNCIONALIDAD where nombre='CONC049ReporteAsesorias';
insert into FUNCIONALIDAD values ('CONC049ReporteAsesorias', 'Reporte de asesorías','app/Conciliacion/CONC049','PDL', 'ANGULAR','reportesConc','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC049ReporteAsesorias', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON');


			
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC045EvaluacionConciliadores';
DELETE FUNCIONALIDAD where nombre='CONC045EvaluacionConciliadores';
insert into FUNCIONALIDAD values ('CONC045EvaluacionConciliadores', 'Reporte evaluación de conciliadores','app/Conciliacion/CONC045','PDL', 'ANGULAR','reportesConc','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC045EvaluacionConciliadores', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON');
			
			
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC047ReporteListaConciliadores';
DELETE FUNCIONALIDAD where nombre='CONC047ReporteListaConciliadores';
insert into FUNCIONALIDAD values ('CONC047ReporteListaConciliadores', 'Reporte Lista de conciliadores','app/Conciliacion/CONC047','PDL', 'ANGULAR','reportesConc','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC047ReporteListaConciliadores', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON');
			

			
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC050ReporteGeneralConciliacion';
DELETE FUNCIONALIDAD where nombre='CONC050ReporteGeneralConciliacion';
insert into FUNCIONALIDAD values ('CONC050ReporteGeneralConciliacion', 'Reporte general','app/Conciliacion/CONC050','PDL', 'ANGULAR','reportesConc','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC050ReporteGeneralConciliacion', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','JEFECON');
			
			

/**
 * Mejora F18
 */
delete FUNCIONALIDAD_ROL where nombre_funcionalidad = 'ADMF053divReportePermisosAplicacion'
delete FUNCIONALIDAD where nombre = 'ADMF053divReportePermisosAplicacion'
INSERT INTO [dbo].[FUNCIONALIDAD]
     VALUES
     ('ADMF053divReportePermisosAplicacion', 'Reporte de permisos aplicación', 'app/Administracion/ADMF053','ADM','ANGULAR','roles','SIMASC_USER',GETDATE(),'ACT')
insert into FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(),'INA','ADMF053divReportePermisosAplicacion',r.id_rol
from ROL r join dominio d on d.codigo = r.nombre
Where d.dominio in ('ROL_PERSONA') and  d.codigo  
        NOT IN ('SUBDICAC', 'AUDIT', 'JEFEARB', 'JEFECON', 'ADMINU', 'ASA');
        



/* Permisos Mejora F19*/
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ADMF052administracionActivacionUsuario';
DELETE FUNCIONALIDAD where nombre='ADMF052administracionActivacionUsuario';
insert into FUNCIONALIDAD values ('ADMF052administracionActivacionUsuario', 'Administración y Activación de usuario','app/Administracion/ADMC052','ADM', 'ANGULAR',NULL,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ADMF052administracionActivacionUsuario', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('JEFEARB','JEFECON','ADMINU','DCA','SUBDICAC');


-- Construccion mejora 24
insert into dominio (codigo, dominio, nombre, descripcion, codigo_dom_padre, dominio_padre)
values ('REUINI', 'TIPO_AUDIENCIA', 'Reunión inicial', 'Reunión inicial (aplica para peritaje)', 'ATAP', 'AGRUPADOR_TIPO_AUDIENCIA')


/* Dominios Mejora F14*/

-- Insert dominios periodicidad
insert dominio values ('PERD','PERIODICIDAD','Día','Periodicidad día',null,null)
insert dominio values ('PERM','PERIODICIDAD','Mes','Periodicidad mes',null,null)
insert dominio values ('PERA','PERIODICIDAD','Año','Periodicidad año',null,null)

-- Insert dominios tipo de periodicidad
insert dominio values ('HABIL','TIPO_PERIODICIDAD','Hábil','Tipo Periodicidad hábil',null,null)
insert dominio values ('CALEN','TIPO_PERIODICIDAD','Calendario','Tipo Periodicidad calendario',null,null)

-- Construccion mejora F15

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:			Juan Nicolás Martinez
-- Create date:		21/06/2018
-- Description:		Adiciona el número de días habiles indicados a la feche recibida.
--					Puede variar dependiendo de los registros que se encuentren en la tabla DIA_FESTIVO
-- Actualizacion: Se agrega casteo a fecha de la tabla DIA_FESTIVO para no tener en cuenta las horas
-- Fecha actualización: 06/07/2018
-- =============================================
CREATE FUNCTION [dbo].[SumarDiasHabiles]
(
	@DATE AS DATE, @NDAYS AS INT
) 
RETURNS DATE
AS
BEGIN
	IF @DATE IS NULL
        BEGIN
            RETURN NULL;
        END
    DECLARE @STARTDATE INT = 0
    DECLARE @COUNT INT = 0
    DECLARE @NEWDATE DATE=Dateadd(day, 1, @DATE)

    WHILE @COUNT < @NDAYS
      BEGIN
        IF Datepart(weekday, @NEWDATE) NOT IN ( 1, 7 )
			AND @NEWDATE NOT IN (SELECT CAST(FECHA as Date) FROM DIA_FESTIVO WHERE ESTADO_REGISTRO = 'ACT')
            SET @COUNT += 1;

            SELECT @NEWDATE = Dateadd(day, 1, @NEWDATE),
                   @STARTDATE += 1;
        END

    RETURN Dateadd(day, @STARTDATE, @DATE);
END 

GO

-- Construccion mejora F13

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF047EnlaceSorteo';
DELETE FUNCIONALIDAD where nombre='TRANSF047EnlaceSorteo';
insert into FUNCIONALIDAD values ('TRANSF047EnlaceSorteo', 'Documentos Aportados','app/Arbitraje/TRANSF047','PJT', 'ANGULAR',NULL,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'TRANSF047EnlaceSorteo', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('JEFEARB','AUX','SECA','JUD','DCA','SUBDICAC','ABO');




-- construccion mejora 17
insert into PARAMETRO_DE_SERVICIO (nombre, tipo_parametro, id_servicio, valor, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values('VISUALIZACION_SORTEO_PUBLICO', 'VISORT', 2, 1, 'Indica si los casos del servicio con sorteos pendientes se deben presentar en el reporte', 'USUARIO_SIMASC', GETDATE(), 'ACT'),
('VISUALIZACION_SORTEO_PUBLICO', 'VISORT', 3, 1, 'Indica si los casos del servicio con sorteos pendientes se deben presentar en el reporte', 'USUARIO_SIMASC', GETDATE(), 'ACT'),
('VISUALIZACION_SORTEO_PUBLICO', 'VISORT', 4, 0, 'Indica si los casos del servicio con sorteos pendientes se deben presentar en el reporte', 'USUARIO_SIMASC', GETDATE(), 'ACT'),
('VISUALIZACION_SORTEO_PUBLICO', 'VISORT', 5, 1, 'Indica si los casos del servicio con sorteos pendientes se deben presentar en el reporte', 'USUARIO_SIMASC', GETDATE(), 'ACT'),
('VISUALIZACION_SORTEO_PUBLICO', 'VISORT', 6, 1, 'Indica si los casos del servicio con sorteos pendientes se deben presentar en el reporte', 'USUARIO_SIMASC', GETDATE(), 'ACT'),
('VISUALIZACION_SORTEO_PUBLICO', 'VISORT', 7, 1, 'Indica si los casos del servicio con sorteos pendientes se deben presentar en el reporte', 'USUARIO_SIMASC', GETDATE(), 'ACT')

insert into PARAMETROS_GENERALES (codigo, tipo, nombre, valor_numerico, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values ('VISENL', 'CONSULTA_SORTEOS_PENDIENTES', 'Habilitar consulta arbitros disponibles', 16, 'USUARIO_SIMASC', GETDATE(), 'ACT')


-- Ajustes permisos Mejora F19


DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ADMF052administracionActivacionUsuario';
DELETE FUNCIONALIDAD where nombre='ADMF052administracionActivacionUsuario';
insert into FUNCIONALIDAD values ('ADMF052administracionActivacionUsuario', 'Administración y Activación de usuario','app/Administracion/ADMC052','ADM', 'ANGULAR',NULL,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ADMF052administracionActivacionUsuario', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('JEFEARB','JEFECON','ADMINU','DCA','SUBDICAC','ACO','ASA');


delete FUNCIONALIDAD_ROL where nombre_funcionalidad='ADMC020SeleccionUsuarioLink' and id_rol= (select id_rol from rol where nombre = 'SECCON')

-- Construccion F8

insert into dominio values 
('PCADP', 'ASUNTO_CARTA', 'Ref:#carta#: #caso# Invitación a #audiencia# caso #nombre#', 'Asunto carta Citación a audiencias diferentes a la primera', null, null),
('PCCAA', 'ASUNTO_CARTA', 'Ref:#carta#: #caso# Comunicación de aceptación de árbitros caso #nombre#', 'Asunto carta Comunicación de aceptación de árbitros', null, null),
('PCCIC', 'ASUNTO_CARTA', 'Ref:#carta#: #caso# Cierre del caso #nombre#', 'Asunto carta Cierre caso', null, null),
('PCCNP', 'ASUNTO_CARTA', 'Ref:#carta#: #caso# Constancia de inasistencia caso #nombre#', 'Asunto carta Plantilla de cartas para constancia no asisten las partes', null, null),
('PCDRA', 'ASUNTO_CARTA', 'Ref:#carta#: #caso# Radicación de documentos caso #nombre#', 'Asunto carta Plantilla de cartas para documentos radicados de arbitraje o de amigable composición', null, null),
('PCFGA', 'ASUNTO_CARTA', 'Ref:#carta#: #caso# caso #nombre#', 'Asunto carta Formato genérico de actas', null, null),
('PCFGDP', 'ASUNTO_CARTA', 'Ref:#carta#: #caso# caso #nombre#', 'Asunto carta Formato genérico de carta dirigida a las partes', null, null),
('PCFGDRA', 'ASUNTO_CARTA', 'Ref:#carta#: #caso# caso #nombre#', 'Asunto carta Formato genérico de carta dirigida a los árbitros', null, null),
('PCNAR', 'ASUNTO_CARTA', 'Ref:#carta#: #caso# Designación  como  #rol# caso #nombre#', 'Asunto carta notificación al árbitro', null, null),
('PCNARI', 'ASUNTO_CARTA', 'Ref:#carta#: #caso# Designación  como  #rol# caso #nombre#', 'Asunto carta Notificación al árbitro internacional', null, null),
('PCNSE', 'ASUNTO_CARTA', 'Ref:#carta#: #caso# Designación  como  #rol# caso #nombre#', 'Asunto carta notificación al secretario', null, null),
('PCPAR', 'ASUNTO_CARTA', 'Ref:#carta#: #caso# Comunicación de aceptación de árbitros caso #nombre#', 'Asunto carta Carta a las partes informando el pronunciamiento de los árbitros', null, null),
('PCPAU', 'ASUNTO_CARTA', 'Ref:#carta#: #caso# Invitación a #audiencia# caso #nombre#', 'Asunto carta citacion a primera audiencia', null, null),
('PCRAP', 'ASUNTO_CARTA', 'Ref:#carta#: #caso# Requerimiento a las partes caso #nombre#', 'Asunto carta Carta para requerimientos a las partes', null, null),
('PCSTD', 'ASUNTO_CARTA', 'Ref:#carta#: #caso# Solicitud de designación caso #nombre#', 'Asunto carta Plantilla de cartas para solicitud a Terceros de designación', null, null),
('CONCILIA', 'ASUNTO_CARTA', 'Ref:#carta#: #caso# #plantilla# caso #nombre#', 'Asunto cartas de conciliacion', null, null)

-- Construccion F2 TRANS-F-046
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF046DocumentosPorDigitalizar';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF046DigitalizarDoc';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF046DigitalizarReadOnly';
DELETE FUNCIONALIDAD where nombre='TRANSF046DigitalizarDoc';
DELETE FUNCIONALIDAD where nombre='TRANSF046DigitalizarReadOnly';
DELETE FUNCIONALIDAD where nombre='TRANSF046DocumentosPorDigitalizar';
insert into FUNCIONALIDAD values ('TRANSF046DocumentosPorDigitalizar', 'Consulta documentos por digitalizar','app/Arbitraje/TRANSF046','PJT', 'ANGULAR',NULL,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'TRANSF046DocumentosPorDigitalizar', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio in('ROL_PERSONA') and d.codigo not in ('DIG');

insert into FUNCIONALIDAD values ('TRANSF046DigitalizarDoc', 'Digitalizar documento', 'app/Arbitraje/TRANSF046', 'PJT', 'ANGULAR', 'TRANSF046DocumentosPorDigitalizar', 'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'TRANSF046DigitalizarDoc', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio in('ROL_PERSONA') and d.codigo not in ('DIG');

insert into FUNCIONALIDAD values ('TRANSF046DigitalizarReadOnly', 'Digitalizar documento consulta', 'app/Arbitraje/TRANSF046', 'PJT', 'ANGULAR', 'TRANSF046DocumentosPorDigitalizar', 'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'TRANSF046DigitalizarReadOnly', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio in('ROL_PERSONA') and d.codigo in ('DIG');
		
		
-- Permisos Publicar mejora F9
		

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063PublicarDocumento';
DELETE FUNCIONALIDAD where nombre='ARBF063PublicarDocumento';
insert into FUNCIONALIDAD values ('ARBF063PublicarDocumento', 'Publicar Documento','app/Arbitraje/ARBF063','PJT', 'ANGULAR','TRANSF097tabDocumentosAportado','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063PublicarDocumento', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ABO');
			

-- Permisos Mejora F25

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063ReversarPublicacion';
DELETE FUNCIONALIDAD where nombre='ARBF063ReversarPublicacion';
insert into FUNCIONALIDAD values ('ARBF063ReversarPublicacion', 'Publicar Documento','app/Arbitraje/ARBF063','PJT', 'ANGULAR','TRANSF097tabDocumentosAportado','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063ReversarPublicacion', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ABO');

-- Insert dominio mejora F25 reversar publicación documento 
INSERT DOMINIO VALUES ('RVD','TIPO_EVENTO','Reversar publicación documento','Reversar Publicación',null,null)

-- Construccion F2 permisos de consulta en ficha tecnica para digitalizador
-- agregar
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF097ColCodigoCaso',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF097CodigoCasoArbitral',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF068buttonDatosBasicosGuardar',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF068divAccionesCasoAbierto',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF068buttonCerrarCaso',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'TRANSF097Cartas',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF070buttonGuardarPacto',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF070divAdicionarTercerosOAutoridades',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF079AplazarAudiencia',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF079HabilitarIngresoCausa',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF079buttonProgramarNuevaAudienciaPRE',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF063buttonAdjuntarDocumento',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF063EliminarDocumento',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF067AdicionarSuspension',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF067ModificarSuspension',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'TRANSF097PreseleccionArbitros',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF097LinkAccesoArbitral',	(select id_rol from rol where nombre = 'DIG'))
	
insert into FUNCIONALIDAD values ('ARBF036Ingresarconsignacion', 'Ingresar consignacion', 'app/Arbitraje/ARBF036', 'PJT', 'ANGULAR', 'TRANSF081tabTarifas', 'SIMASC_USER', GETDATE(), 'ACT')
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF036Ingresarconsignacion',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD values ('ARBF068buttonDatosBasicosGuardarARB', 'Guardar datos básicos', 'app/Arbitraje/ARBF068', 'PJT', 'ANGULAR', 'TRANSF097divDatosBasicos', 'SIMASC_USER', GETDATE(), 'ACT')
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF068buttonDatosBasicosGuardarARB',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD values ('TRANSF097tabcierreCaso', 'Cierre de caso', 'app/Arbitraje/ARBF097', 'PJT', 'ANGULAR', 'TRANSF097divFichaTecnica', 'SIMASC_USER', GETDATE(), 'ACT')
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'TRANSF097tabcierreCaso',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD values ('TRANSFMinEnvioCaso', 'Envio ministerio', 'app/Arbitraje/ARBF097', 'PJT', 'ANGULAR', 'TRANSF081divFichaTecnica', 'SIMASC_USER', GETDATE(), 'ACT')
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'TRANSFMinEnvioCaso',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD values ('ARBF089buttonActualizar', 'Actualizar ruta del caso', 'app/Arbitraje/ARBF089', 'PJT', 'ANGULAR', 'TRANSF097divRutaDeCaso', 'SIMASC_USER', GETDATE(), 'ACT')
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF089buttonActualizar',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD values ('ARBF091GuardarTarifas', 'Guardar calculo tarifas', 'app/Arbitraje/ARBF091', 'PJT', 'ANGULAR', 'TRANSF081tabTarifas', 'SIMASC_USER', GETDATE(), 'ACT')
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF091GuardarTarifas',	(select id_rol from rol where nombre = 'DIG'))

insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'TRANSF044EliminarParte',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'TRANSF044botonGenerarClave',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'TRANSF044ModificarParte',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF079RegistrarResultado',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF079RegistrarResultado',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF079buttonProgramarNuevaAudienciaARB',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'TRANSF097tabArbitros',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'TRANSF044ModificarParte',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'TRANSF044EliminarParte',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'TRANSF044botonGenerarClave',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF041GuardarCierre',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF063PublicarDocumento',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF068buttonDatosBasicosGuardar',	(select id_rol from rol where nombre = 'DIG'))
-- borrar
delete FUNCIONALIDAD_ROL where id_rol = (select id_rol from rol where nombre = 'DIG') and nombre_funcionalidad in (
'ARBF097LinkAccesoFichaTecnica',
'ARBF097LinkAccesoArbitral',
'TRANSF097divFichaTecnica',
'TRANSF044VisualizarNombreParte',
'ARBF097CodigoCasoArbitral',
'TRANSF081divFichaTecnica',
'TRANSF044VisualizarNombreParte',
'TRANSF081tabExpediente',
'ARBF036ControlPagoHonorarios',
'TRANSF081tabTarifas',
'ARBF041divCierrecaso')

-- Construccion F21
insert into PARAMETROS_GENERALES values
('URL_SIMA','URL_ACCESO_SIMASC', 'URL ACCESO SIMASC', NULL,	'http://www.centroarbitrajeconciliacion.com', null, null, 'USUARIO_SIMASC', GETDATE(),	'ACT')

-- Construccion F23
delete FUNCIONALIDAD_ROL where nombre_funcionalidad = 'ARBF068ConsultaNumeroRecibo'
delete FUNCIONALIDAD_ROL where nombre_funcionalidad = 'ARBF068EdicionNumeroRecibo'
delete FUNCIONALIDAD where nombre = 'ARBF068ConsultaNumeroRecibo'
delete FUNCIONALIDAD where nombre = 'ARBF068EdicionNumeroRecibo'

insert into FUNCIONALIDAD values ('ARBF068EdicionNumeroRecibo', 'Editar número recibo', 'app/Arbitraje/ARBF068', 'PJT', 'ANGULAR', 'TRANSF097divDatosBasicos', 'SIMASC_USER', GETDATE(), 'ACT')
insert into FUNCIONALIDAD_ROL 
select 'USUARIO_SIMASC', GETDATE(),	'INA',	'ARBF068EdicionNumeroRecibo', id_rol
from rol r
inner join dominio d on d.codigo = r.nombre and d.dominio = 'ROL_PERSONA'
and d.codigo not in ('SECA', 'ASA')

insert into FUNCIONALIDAD values ('ARBF068ConsultaNumeroRecibo', 'Consultar número recibo', 'app/Arbitraje/ARBF068', 'PJT', 'ANGULAR', 'TRANSF097divDatosBasicos', 'SIMASC_USER', GETDATE(), 'ACT')
insert into FUNCIONALIDAD_ROL 
select 'USUARIO_SIMASC', GETDATE(),	'INA',	'ARBF068ConsultaNumeroRecibo', id_rol
from rol r
inner join dominio d on d.codigo = r.nombre and d.dominio = 'ROL_PERSONA'
and d.codigo in ('SECA', 'ASA')


-- Mejora F1 Permisos Dominio

INSERT DOMINIO VALUES ('EFPERS','HISTORICO EXPEDIENTE','Persona','Historico Expediente persona',null,null)
INSERT DOMINIO VALUES ('EFOTRO','HISTORICO EXPEDIENTE','Otros','Historico Expediente otros',null,null)



DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF120UbicacionFisicaExpediente';
DELETE FUNCIONALIDAD where nombre='ARBF120UbicacionFisicaExpediente';
insert into FUNCIONALIDAD values ('ARBF120UbicacionFisicaExpediente', 'Ubicación física del expediente','app/Arbitraje/ARBF120','PJT', 'ANGULAR','ADM024rolprestador','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF120UbicacionFisicaExpediente', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('DCA','SUBDICAC','JEFEARB','ABO','SECA','ASA','DIG','AUX');

			
			
----------------  Actualización Valor Plantilla Carta corrección defecto 9861


UPDATE VALOR_PLANTILLA_CARTA SET consulta='NATIVE: select concat((case    when r.tipo_persona = ''NAT'' and p.nombre = ''APO'' or p.nombre = ''APD'' 
then ''Doctor(a)</br>''  when r.tipo_persona = ''JUR'' then ''Señor(a)</br>Representante Legal de</br>''  else ''Señor(a)</br>''  end), 
concat(r.primer_nombre_o_razon_social, rtrim('' ''+r.segundo_nombre), rtrim('' ''+r.primer_apellido), rtrim('' ''+r.segundo_apellido))) 
 from Persona r  left join ROL_PERSONA_CASO prpc on prpc.id_persona = r.id_persona                                   
   left join ROL p on p.id_rol = prpc.id_rol                 
      where prpc.id_caso=?1   and prpc.estado_registro=''ACT''  
	   and p.estado_registro = ''ACT''   and r.estado_registro = ''ACT''  and r.id_persona = ?2' where ID_PLANTILLA_CARTA IN
(select id_plantilla_carta from plantilla_carta where nombre='PCCIC') AND nombre_valor_dinamico='dirigidoP'

	  

	   UPDATE VALOR_PLANTILLA_CARTA SET consulta='NATIVE: select case    when r.tipo_persona = ''NAT'' and p.nombre = ''APO'' or p.nombre = ''APD''
	   then ''Doctor(a)</br>''                          when r.tipo_persona = ''JUR'' then ''Señor(a)</br>Representante Legal de</br>'' 
	    else ''Señor(a)</br>''  end  from Persona r  left join ROL_PERSONA_CASO prpc on prpc.id_persona = r.id_persona                      
							    left join ROL p on p.id_rol = prpc.id_rol                 
								   where prpc.id_caso=?1                  
								      and prpc.estado_registro=''ACT''               
									       and p.estado_registro = ''ACT''               
										        and r.estado_registro = ''ACT''  and r.id_persona = ?2' where id_plantilla_carta in
(select id_plantilla_carta from plantilla_carta where nombre='PCCIC')
and nombre_valor_dinamico='respetadoP'




-------  Corrección Defecto  9862 Actualización Valor Plantilla Carta 

	update VALOR_PLANTILLA_CARTA set consulta='
	select concat(cp.primerNombreORazonSocial," ", CONCAT(cp.segundoNombre, " ",
	concat(cp.primerApellido," ",    concat(cp.segundoApellido," "))))     
	from RolPersonaCaso as cr 
	left join cr.persona as cp,
	ParametroServicioSorteo as pss    
	where cr.caso.idServicio = pss.idServicio
	and cr.rol.idRol = pss.rol.idRol
	and cr.rolPersonaCasoPK.idCaso=:idCaso
	and cr.estado = "ACE" 
	and cr.esPresidente = true    
	and cr.estadoRegistro = "ACT" 
	and cr.caso.estadoRegistro = "ACT"
	and pss.estadoRegistro = "ACT"'
	where nombre_valor_dinamico='presidenteP'
	and id_plantilla_carta in (select id_plantilla_carta from PLANTILLA_CARTA where nombre='PCCAA')



	update VALOR_PLANTILLA_CARTA set consulta='
	select rpc.persona.primerApellido 
	from RolPersonaCaso rpc,
	ParametroServicioSorteo as pss           
	where rpc.caso.idServicio = pss.idServicio 
	and rpc.rol.idRol = pss.rol.idRol
	and rpc.caso.idCaso=:idCaso 
	and rpc.estado = "ACE" 
	AND rpc.esPresidente = true
	and rpc.caso.estadoRegistro = "ACT"
	and rpc.estadoRegistro="ACT"
	and pss.estadoRegistro = "ACT"'
	where nombre_valor_dinamico='apellidoPresidenteP'
	and id_plantilla_carta in (select id_plantilla_carta from PLANTILLA_CARTA where nombre='PCCAA')
	
	
	
	-- Corrección defecto 9865 Realizar update para el link de sorteo en CCB
	
	insert PARAMETROS_GENERALES values ('URLSOR','URL_SORTEO','URL APLICACIÓN SORTEO',null,'http://localhost:9080/SimascWeb/faces/login.xhtml',null,null,'SIMASC',GETDATE(),'ACT')
	
	
	
-- Integracion con mejoras II 2018/10/16

-- Funcionalidad TRANSF097

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF097tabDocumentosAportado';
DELETE FUNCIONALIDAD where nombre='TRANSF097tabDocumentosAportado';
insert into FUNCIONALIDAD values ('TRANSF097tabDocumentosAportado', 'Documentos aportados','app/Arbitraje/ARBF097','PJT', 'ANGULAR','TRANSF097divFichaTecnica','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'TRANSF097tabDocumentosAportado', r.id_rol
from ROL r
join dominio d on d.codigo = r.nombre
where d.dominio
in('ROL_PERSONA')
and d.codigo not in ('ARB','SEC','ABO','APO','APD','DIG','AUX','TER','AUJ','ARI','AMC','PER','ARE','DTE','DDA','ASA','PRJ','CAL',
'AGE','CON','DCA','PRO','RCO','ACO','CONINS','CONCOM','ARBS','RADCA','ESTCON','ESTARB','JEFEARB','JEFECON','JEFECCOM','SECCON','SECA','SECCAC',
'SUBDICAC','CONVOTE','CONVODO','APODCTE','APODCDO','SOLTAN','JUD','RDP');



-- Funcionalidad ARBF063buttonAdjuntarDocumento

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063buttonAdjuntarDocumento';
DELETE FUNCIONALIDAD where nombre='ARBF063buttonAdjuntarDocumento';
insert into FUNCIONALIDAD values ('ARBF063buttonAdjuntarDocumento', 'Adjuntar documento','app/Arbitraje/ARBF063','PJT', 'ANGULAR','TRANSF097tabDocumentosAportado','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063buttonAdjuntarDocumento', r.id_rol
from ROL r
join dominio d on d.codigo = r.nombre
where d.dominio
in('ROL_PERSONA')
and d.codigo not in ('ARB','SEC','ABO','APO','APD','DIG','AUX','TER','AUJ','ARI','AMC','PER','ARE','DTE','DDA','ASA','PRJ','CAL',
'AGE','CON','DCA','PRO','RCO','ACO','CONINS','CONCOM','ARBS','RADCA','ESTCON','ESTARB','JEFEARB','JEFECON','JEFECCOM','SECCON','SECA','SECCAC',
'SUBDICAC','CONVOTE','CONVODO','APODCTE','APODCDO','SOLTAN','JUD','RDP');



-- Funcionalidad ARBF063EliminarDocumento

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063EliminarDocumento';
DELETE FUNCIONALIDAD where nombre='ARBF063EliminarDocumento';
insert into FUNCIONALIDAD values ('ARBF063EliminarDocumento', 'Eliminar documento','app/Arbitraje/ARBF063','PJT', 'ANGULAR','TRANSF097tabDocumentosAportado','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063EliminarDocumento', r.id_rol
from ROL r
join dominio d on d.codigo = r.nombre
where d.dominio
in('ROL_PERSONA')
and d.codigo not in ('ARB','SEC','ABO','APO','APD','AUX','TER','AUJ','ARI','AMC','PER','ARE','DTE','DDA','ASA','PRJ','CAL',
'AGE','CON','DCA','PRO','RCO','ACO','CONINS','CONCOM','ARBS','RADCA','ESTCON','ESTARB','JEFEARB','JEFECON','JEFECCOM','SECCON','SECA','SECCAC',
'SUBDICAC','CONVOTE','CONVODO','APODCTE','APODCDO','SOLTAN','JUD','RDP');

	
-- Permisos Publicar mejora F9
		

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063PublicarDocumento';
DELETE FUNCIONALIDAD where nombre='ARBF063PublicarDocumento';
insert into FUNCIONALIDAD values ('ARBF063PublicarDocumento', 'Publicar Documento','app/Arbitraje/ARBF063','PJT', 'ANGULAR','TRANSF097tabDocumentosAportado','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063PublicarDocumento', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ABO');
			

-- Permisos Mejora F25

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063ReversarPublicacion';
DELETE FUNCIONALIDAD where nombre='ARBF063ReversarPublicacion';
insert into FUNCIONALIDAD values ('ARBF063ReversarPublicacion', 'Publicar Documento','app/Arbitraje/ARBF063','PJT', 'ANGULAR','TRANSF097tabDocumentosAportado','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063ReversarPublicacion', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ABO');

insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	'2018-09-10 09:54:21',	'INA',	'ARBF063buttonAdjuntarDocumento',	(select id_rol from rol where nombre = 'DIG'))


-- Construccion F23
delete FUNCIONALIDAD_ROL where nombre_funcionalidad = 'ARBF068ConsultaNumeroRecibo'
delete FUNCIONALIDAD_ROL where nombre_funcionalidad = 'ARBF068EdicionNumeroRecibo'
delete FUNCIONALIDAD where nombre = 'ARBF068ConsultaNumeroRecibo'
delete FUNCIONALIDAD where nombre = 'ARBF068EdicionNumeroRecibo'

insert into FUNCIONALIDAD values ('ARBF068EdicionNumeroRecibo', 'Editar número recibo', 'app/Arbitraje/ARBF068', 'PJT', 'ANGULAR', 'TRANSF097divDatosBasicos', 'SIMASC_USER', GETDATE(), 'ACT')
insert into FUNCIONALIDAD_ROL 
select 'USUARIO_SIMASC', GETDATE(),	'INA',	'ARBF068EdicionNumeroRecibo', id_rol
from rol r
inner join dominio d on d.codigo = r.nombre and d.dominio = 'ROL_PERSONA'
and d.codigo not in ('SECA', 'ASA')

insert into FUNCIONALIDAD values ('ARBF068ConsultaNumeroRecibo', 'Consultar número recibo', 'app/Arbitraje/ARBF068', 'PJT', 'ANGULAR', 'TRANSF097divDatosBasicos', 'SIMASC_USER', GETDATE(), 'ACT')
insert into FUNCIONALIDAD_ROL 
select 'USUARIO_SIMASC', GETDATE(),	'INA',	'ARBF068ConsultaNumeroRecibo', id_rol
from rol r
inner join dominio d on d.codigo = r.nombre and d.dominio = 'ROL_PERSONA'
and d.codigo in ('SECA', 'ASA')

insert PARAMETROS_GENERALES values ('URLSOR', null, 'URL_SORTEO','URL APLICACIÓN SORTEO',null,'http://localhost:9080/SimascWeb/faces/login.xhtml',null,'SIMASC',GETDATE(),'ACT')	insert PARAMETROS_GENERALES values ('URLSOR','URL_SORTEO','URL APLICACIÓN SORTEO',null,'http://localhost:9080/SimascWeb/faces/login.xhtml',null,null,'SIMASC',GETDATE(),'ACT')
	
-- Ajustes Merge
	
update dominio set dominio='PERIODICIDAD_PACTO' where codigo in ('PERA','PERD','PERM')
update dominio set dominio='TIPO_PERIODICIDAD_PACTO' where codigo in ('CALEN','HABIL')


DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF079buttonProgramarNuevaAudienciaPRE';
DELETE FUNCIONALIDAD where nombre='ARBF079buttonProgramarNuevaAudienciaPRE';
insert into FUNCIONALIDAD values ('ARBF079buttonProgramarNuevaAudienciaPRE', 'Programar nueva audiencia - prearbitral','app/Arbitraje/ARBF079','PJT', 'ANGULAR',NULL,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF079buttonProgramarNuevaAudienciaPRE', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ABO','JEFEARB','JEFECON','SEC','AUX','SECA','ASA');

-- actualizacion tabla log de correos
alter table correo_rol_persona_caso alter column asunto varchar(200)

-- Construccion CC007 - F1
update funcionalidad set nombre_funcionalidad_padre = 'TRANSF008divCartasGeneradas' where nombre = 'TRANSF004EditarCarta'
delete FUNCIONALIDAD_ROL where nombre_funcionalidad = 'TRANSF008EdicionCarta'
delete FUNCIONALIDAD where nombre = 'TRANSF008EdicionCarta'

insert into FUNCIONALIDAD (nombre, titulo, url, nombre_tipo_funcionalidad, aplicacion_tipo_funcionalidad, nombre_funcionalidad_padre, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro) 
values ('TRANSF009',	'Consulta de casos',	'app/Arbitraje/TRANSF009'	,'PJT',	'ANGULAR',	NULL,	'SIMASC_USER',	GETDATE(),	'ACT')

update funcionalidad set nombre_funcionalidad_padre = 'TRANSF009' 
where nombre in ('TRANSF081divFichaTecnica', 'TRANSF097divFichaTecnica', 'ARBF097ColCodigoCaso', 'ARBF097CodigoCasoArbitral', 'CONF060AccederFichaTecnicaConciliacion', 'TRANS040LinkAccesoFichaTecnica')
update funcionalidad set titulo = 'Consulta de casos' where nombre = 'TRANSF009'
delete FUNCIONALIDAD_ROL where nombre_funcionalidad = 'TRANSF009divFichaTecnica'
delete FUNCIONALIDAD where nombre = 'TRANSF009divFichaTecnica'

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF009';
INSERT INTO FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'TRANSF009', r.id_rol
from ROL r
join dominio d on d.codigo = r.nombre
where d.dominio
in('ROL_PERSONA')
and d.codigo not in ('CON','JEFECON','SECON','ACO', 'RCO','ARB','DCA','SUBDICAC','JEFEARB','ABO','SEC','DIG','AUX');

DELETE FUNCIONALIDAD_ROL where nombre_funcionalidad = 'ARBF068DatosBasicosAuxiliarDesignadoReadOnly'
DELETE FUNCIONALIDAD_ROL where nombre_funcionalidad = 'ARBF068CambioSecretarioTribunal'
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF068CambioSecretarioTribunal';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF068ConsultaSecretarioTribunal';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF068DatosBasicosSecretarioTribunal';

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF097LinkAccesoFichaTecnica';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF009divDigitalizacionDocumentos';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF097LinkAccesoArbitral';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF009divFichaTecnicaPrearbitral';

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF068DatosBasicosMateriaArbitral';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF068DatosBasicosTipoCasoArbitral';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF081divDatosBasicos';

DELETE FUNCIONALIDAD where nombre='ARBF068DatosBasicosAuxiliarDesignadoReadOnly';
DELETE FUNCIONALIDAD where nombre='ARBF097LinkAccesoFichaTecnica';
DELETE FUNCIONALIDAD where nombre='TRANSF009divDigitalizacionDocumentos';
DELETE FUNCIONALIDAD where nombre='ARBF097LinkAccesoArbitral';
DELETE FUNCIONALIDAD where nombre='TRANSF009divFichaTecnicaPrearbitral';

DELETE FUNCIONALIDAD where nombre='ARBF068DatosBasicosTipoCasoArbitral';
DELETE FUNCIONALIDAD where nombre='ARBF068DatosBasicosMateriaArbitral';
DELETE FUNCIONALIDAD where nombre='ARBF068CambioSecretarioTribunal'
DELETE FUNCIONALIDAD where nombre='ARBF068CambioSecretarioTribunal';
DELETE FUNCIONALIDAD where nombre='ARBF068ConsultaSecretarioTribunal';	
DELETE FUNCIONALIDAD where nombre='ARBF068DatosBasicosSecretarioTribunal';	
DELETE FUNCIONALIDAD where nombre='TRANSF081divDatosBasicos';

insert into FUNCIONALIDAD values ('TRANSF081divDatosBasicos', 'Datos básicos','app/Arbitraje/ARBF068','PJT', 'ANGULAR','TRANSF081divFichaTecnica','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'TRANSF081divDatosBasicos', r.id_rol
from ROL r
join dominio d on d.codigo = r.nombre
where d.dominio
in('ROL_PERSONA')
and d.codigo not in ('ARB','SECA','SUBDICAC','JEFEARB','ABO','ASA','AUX');

insert into FUNCIONALIDAD values ('ARBF068DatosBasicosTipoCasoArbitral', 'Tipo Caso','app/Arbitraje/ARBF068','PJT', 'ANGULAR','TRANSF081divDatosBasicos','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF068DatosBasicosTipoCasoArbitral', r.id_rol
from ROL r
join dominio d on d.codigo = r.nombre
where d.dominio
in('ROL_PERSONA')
and d.codigo not in ('ARB','SECA','SUBDICAC','JEFEARB','ABO','ASA','AUX');

insert into FUNCIONALIDAD values ('ARBF068DatosBasicosMateriaArbitral', 'Materia','app/Arbitraje/ARBF068','PJT', 'ANGULAR','TRANSF081divDatosBasicos','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF068DatosBasicosMateriaArbitral', r.id_rol
from ROL r
join dominio d on d.codigo = r.nombre
where d.dominio
in('ROL_PERSONA')
and d.codigo not in ('ARB','SECA','SUBDICAC','JEFEARB','ABO','ASA','AUX');

update funcionalidad set nombre_funcionalidad_padre = 'TRANSF081divDatosBasicos' where nombre = 'ARBF068buttonDatosBasicosGuardarARB'

---- ARBF068DatosBasicosAuxiliarDesignado - ya existe, titulo = Definir Auxiliar designado
update FUNCIONALIDAD set nombre_funcionalidad_padre = 'TRANSF081divDatosBasicos' where nombre in ('ARBF068DatosBasicosAuxiliarDesignado', 'ARBF068DatosBasicosDiasParaVencimiento')
INSERT INTO FUNCIONALIDAD VALUES ('ARBF068DatosBasicosAuxiliarDesignadoReadOnly', 'Consultar auxiliar designado', 'app/Arbitraje/ARBF082', 'PJT', 'ANGULAR', 'TRANSF081divDatosBasicos', 'SIMASC_USER', GETDATE(), 'ACT')
INSERT INTO FUNCIONALIDAD VALUES ('ARBF068CambioSecretarioTribunal', 'Definir secretario asignado', 'app/Arbitraje/ARBF068', 'PJT', 'ANGULAR', 'TRANSF081divDatosBasicos', 'SIMASC_USER', GETDATE(), 'ACT')
INSERT INTO FUNCIONALIDAD VALUES ('ARBF068ConsultaSecretarioTribunal', 'Consultar secretario asignado', 'app/Arbitraje/ARBF068', 'PJT', 'ANGULAR', 'TRANSF081divDatosBasicos', 'SIMASC_USER', GETDATE(), 'ACT')
INSERT INTO FUNCIONALIDAD VALUES ('ARBF068DatosBasicosSecretarioTribunal', 'Correo del secretario asignado', 'app/Arbitraje/ARBF068', 'PJT', 'ANGULAR', 'TRANSF081divDatosBasicos', 'SIMASC_USER', GETDATE(), 'ACT')

update funcionalidad set nombre_funcionalidad_padre = 'TRANSF081divFichaTecnica' 
where nombre IN ('TRANSF081tabExpediente','ARBF036ControlPagoHonorarios','TRANSF081tabTarifas','TRANSF037FormatosDocumentos','ARBF085EnvioCorreo','ARBF120UbicacionFisicaExpediente')


-- Corrección defecto 9972

delete fr from FUNCIONALIDAD_ROL fr
inner join rol r on fr.id_rol=r.id_rol
 where fr.nombre_funcionalidad='TRANSF009'
 and r.nombre in ('SECA','ASA','SECCON')

 
delete fr from FUNCIONALIDAD_ROL fr
inner join rol r on fr.id_rol=r.id_rol
 where fr.nombre_funcionalidad='ARBF063tabDocAportados'
 and r.nombre='SECA'

-- No pronunciamiento
 INSERT DOMINIO VALUES ('NOP','TIPO_EVENTO','No pronunciamiento','No pronunciamiento de caso por parte del árbitro',null,null)
 
-- Ajuste correccion bug 9972 y 9992
delete fr from FUNCIONALIDAD_ROL fr
inner join rol r on fr.id_rol=r.id_rol
where fr.nombre_funcionalidad='TRANSF081divDatosBasicos'
and r.nombre in ('SEC', 'DIG')
 


-- Correccion 9992
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	GETDATE(),	'INA',	'ARBF097CodigoCasoArbitral',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	GETDATE(),	'INA',	'ARBF068CambioSecretarioTribunal',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	GETDATE(),	'INA',	'ARBF068DatosBasicosAuxiliarDesignado',	(select id_rol from rol where nombre = 'DIG'))
insert into FUNCIONALIDAD_ROL values ('USUARIO_SIMASC',	GETDATE(),	'INA',	'ARBF120UbicacionFisicaExpediente',	(select id_rol from rol where nombre = 'DIG'))
delete fr from FUNCIONALIDAD_ROL fr
inner join rol r on fr.id_rol=r.id_rol
where fr.nombre_funcionalidad='TRANSF097divIrArbitral'
and r.nombre in ('DIG')



-- Corrección Defecto 9897
			
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONF097FirmaActas';
DELETE FUNCIONALIDAD where nombre='CONF097FirmaActas';
insert into FUNCIONALIDAD values ('CONF097FirmaActas', 'Firma de Actas','app/Conciliacion/CONF097','PDL', 'ANGULAR',null,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONF097FirmaActas', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('CON');


-- Corrección defecto 9922

			
update alerta set tipo_servicio='PDL', tipo_alerta='PARAMET', valor=NULL, asunto='Cartas pendientes de impresión',
descripcion_de_alerta='Todos los días hábiles en la hora especificada y si hay cartas en estado ‘pendiente de impresión’',
texto='Sr. Funcionario se tiene [CANTIDAD_CARTAS] cartas pendientes por imprimir correspondientes al [NOMBRE_CENTRO], para realizar la impresión de estas, es necesario que ingrese por el menú administración a la opción cartas pendientes por imprimir.',
periodicidad='DIA',tipo_periodicidad='HAB' where codigo='PENIMP'


update alerta set tipo_servicio='PDL', tipo_alerta='PARAMET', valor=3, asunto='Actualizar estado de cartas',
descripcion_de_alerta='Después de enviada la carta',
texto='La siguiente es la lista de las cartas cuyo estado no ha sido actualizado en el sistema [TABLA_CARTAS]',
periodicidad='DIA',tipo_periodicidad='HAB' where codigo='AESTCAR'


-- Ajuste defecto 9918
	UPDATE ALERTA SET TEXTO='La secretaria de conciliación [NOMBRE_AUXILIAR]  fue asignado al caso [CODIGO_CASO] - [NOMBRE_CASO] para seguimiento del caso.',
		nombre='Asignación de caso a secretaria de conciliación'
		where codigo='ACA'
				
-- Corrección bug 9050 reportado en JAZZ
UPDATE VALOR_PLANTILLA_CARTA SET consulta = 'NATIVE: select  case when r.nombre = ''CONVOTE'' or r.nombre = ''APODCTE'' then ''Hemos recibido su comunicación en la cual nos solicita la citación de convocadoP a una Audiencia de Conciliación a fin de solucionar las diferencias presentadas con ocasión de casoHechosP.''          when r.nombre = ''CONVODO'' or r.nombre = ''APODCDO'' then ''Hemos recibido una solicitud de conciliación, en la que convocanteP nos pide la citación de convocadoP a una Audiencia de Conciliación a fin de solucionar las diferencias presentadas con ocasión de casoHechosP.''        else ''''         end      from ROL r INNER JOIN ROL_PERSONA_CASO rpc on rpc.id_rol = r.id_rol          INNER JOIN PERSONA P on p.id_persona = rpc.id_persona    where rpc.id_caso= ?1     and rpc.estado_registro=''ACT''    and p.estado_registro = ''ACT''    and r.estado_registro = ''ACT''    and p.id_persona = ?2'
WHERE nombre_valor_dinamico = 'PCPAUparrafo1P' AND id_plantilla_carta = (SELECT id_plantilla_carta FROM PLANTILLA_CARTA WHERE nombre = 'PCPAU' and tipo_servicio = 'PDL')


-- Corrección bug 9952 reportado en JAZZ
update alerta set texto = 'Ha sido designado como conciliador del caso [CODIGO_CASO] - [NOMBRE_CASO] del centro de arbitraje y conciliación de [NOMBRE_CENTRO]. Recuerde que tiene [PLAZO_CONTESTACION] horas para aceptar el caso' 
where codigo in ('NOT_NPC','NOT_NPP')
update alerta set texto = 'Ha sido designado como conciliador del caso [CODIGO_CASO] - [NOMBRE_CASO] del centro de arbitraje y conciliación de [NOMBRE_CENTRO]. Recuerde que tiene [PARAMETRO_HORAS_ACEPTACION] horas para aceptar el caso.'
where codigo = 'RECACE1'

-- Corrección bug 9917 reportado en JAZZ
/****** Object:  UserDefinedFunction [dbo].[AlertaActualizacionPronunciamiento]    Script Date: 07/11/2018 06:25:48 p.m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		Juan Diego Cepeda Mosquera
-- Create date: 07/11/2018
-- Description:	Función encargada de obtener los datos de notificación de la alerta de actualización de pronunciamiento
-- =============================================
CREATE FUNCTION [dbo].[AlertaActualizacionPronunciamiento]
(
)
RETURNS 
@Resultado TABLE 
(
	idPersona int,
    tabla text
)
AS
BEGIN
	DECLARE @TablaEncabezado varchar(max) = CONCAT(ISNULL((SELECT
        descripcion
    FROM DOMINIO
    WHERE dominio = 'TABLA_ALERTA_ESTILOS'
    AND codigo = 'TEA')
    ,
    '<html><body><table> '),
    '<tr><td  width= 100px> <b>Código del caso</b></td>' +
    '<td> <b>Nombre del árbitro</b></td>' +
    '<td> <b>Fecha de designación</b></td>' +
    '<td> <b>Fecha de comunicación de designación</b></td></tr>')
    DECLARE @TablaCierre varchar(30) = '</table></body></html>'
    DECLARE @CantidadAbogados int = (SELECT
        COUNT(DISTINCT persona_abogado_caso)
    FROM ARBITROS_SIN_PRONUNCIAR)

    DECLARE @IdCaso varchar(200)
    DECLARE @NombreArbitro varchar(max)
    DECLARE @FechaDesignacion varchar(200)
    DECLARE @FechaComunicacion varchar(200)
    DECLARE @PersonaAbogadoCaso int
    DECLARE @PersonaAbogadoCasoAnterior int
    DECLARE @Tabla varchar(max)

    DECLARE arbitros_sin_pronunciar CURSOR FOR
    SELECT
        *
    FROM ARBITROS_SIN_PRONUNCIAR
    ORDER BY persona_abogado_caso

    OPEN arbitros_sin_pronunciar

    FETCH NEXT FROM arbitros_sin_pronunciar INTO @IdCaso, @NombreArbitro, @FechaDesignacion, @FechaComunicacion, @PersonaAbogadoCaso
    SET @PersonaAbogadoCasoAnterior = @PersonaAbogadoCaso

    IF @CantidadAbogados = 1
    BEGIN
        WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @Tabla = CONCAT(@Tabla, '<tr><td>' + @IdCaso + '</td><td>' + @NombreArbitro + '</td><td>' + @FechaDesignacion + '</td><td>' + @FechaComunicacion + '</td></tr>')

            FETCH NEXT FROM arbitros_sin_pronunciar INTO @IdCaso, @NombreArbitro, @FechaDesignacion, @FechaComunicacion, @PersonaAbogadoCaso
        END

        INSERT INTO @Resultado
            VALUES (@PersonaAbogadoCaso, CONCAT(@TablaEncabezado, @Tabla, @TablaCierre))
    END
    ELSE
    BEGIN
        WHILE @@FETCH_STATUS = 0
        BEGIN
            IF @PersonaAbogadoCaso = @PersonaAbogadoCasoAnterior
                SET @Tabla = CONCAT(@Tabla, '<tr><td>' + @IdCaso + '</td><td>' + @NombreArbitro + '</td><td>' + @FechaDesignacion + '</td><td>' + @FechaComunicacion + '</td></tr>')
            ELSE
            BEGIN
                INSERT INTO @Resultado
                    VALUES (@PersonaAbogadoCasoAnterior, CONCAT(@TablaEncabezado, @Tabla, @TablaCierre))
                SET @Tabla = NULL
                SET @Tabla = CONCAT(@Tabla, '<tr><td>' + @IdCaso + '</td><td>' + @NombreArbitro + '</td><td>' + @FechaDesignacion + '</td><td>' + @FechaComunicacion + '</td></tr>')
                SET @PersonaAbogadoCasoAnterior = @PersonaAbogadoCaso
            END

            FETCH NEXT FROM arbitros_sin_pronunciar INTO @IdCaso, @NombreArbitro, @FechaDesignacion, @FechaComunicacion, @PersonaAbogadoCaso

			IF @@FETCH_STATUS = -1
				INSERT INTO @Resultado 
					VALUES (@PersonaAbogadoCaso, CONCAT(@TablaEncabezado, @Tabla, @TablaCierre))
        END
    END

    CLOSE arbitros_sin_pronunciar
    DEALLOCATE arbitros_sin_pronunciar
	
	RETURN 
END

GO


-- Correccion bug 9824
-- PCADP
update plantilla_carta set plantilla_html = '<html>   <head> <title>Citación audiencia</title><style>body {font-size:9px} @page { margin-top: 116px; margin-bottom: 116px; margin-left: 126px; margin-right: 126px} </style></head>   <body>    <font face="Arial" >    <p align="left">     ciudadP, dia_cartaP de mes_cartaP de ano_cartaP    </p>     <p align="left">           senorDoctorRLegalP        <br/>    dirigidoP      <br/>      direccionEnvio         telefonoEnvio           ciudadEnvio    </p>    <p align="right">      radicadoP    </p>    <p align="justify">      REFERENCIA: SOLICITUD DE CONCILIACION DE convocanteP PARA SOLUCIONAR LAS DIFERENCIAS SURGIDAS CON convocadoP.    </p>   <p align="justify">Apreciado(a) señor(a):</p>  <p align="justify">Nos complace invitarle a una audiencia de conciliación, el fechaAudienciaP, a las horaAudienciaP en nuestras oficinas ubicadas en direccionSedeP.</p>       leyAsistenciaP          <p align="justify">      Para nosotros es importante saber si recibieron esta comunicación, por lo cual les solicitamos que confirmen su asistencia al siguiente correo electrónico: emailConciliadorP. Por favor indique el número de caso.    </p>   <p align="justify">      Para información adicional puede comunicarse al teléfono <!--número de teléfono-->    </p>  <p align="justify">Cordialmente,</p>      <p align="justify">&#160;</p> <img src="firmaP" width="235" height="87" />   <p align="left">      conciliadorP    <br/> Conciliador <br/>   Registro registroConciliadorP    </p>   <p align="right">      Caso codigoCaso    </p>  </font>  </body>  </html>' where tipo_servicio = 'PDL' and nombre = 'PCADP'
update VALOR_PLANTILLA_CARTA set consulta = 'NATIVE: select   case when p.tipo_persona = ''NAT'' and (r.nombre = ''CONVOTE'' or r.nombre = ''CONVODO'') then ''<p align="justify">Así mismo le recordamos que de conformidad con lo dispuesto en el parágrafo 2º del artículo 1º de la Ley 640 de 2001, deberá asistir  a la audiencia de conciliación y podrá hacerlo junto con su apoderado, de lo contrario su inasistencia puede ser considerada como indicio grave en contra de sus pretensiones o de sus excepciones de mérito en un eventual proceso judicial que verse sobre los mismos hechos, de acuerdo a lo establecido en el artículo 22 de la misma ley.</p>''           else ''''       end         from Persona p  left join ROL_PERSONA_CASO prpc on prpc.id_persona = p.id_persona            left join ROL r on r.id_rol = prpc.id_rol       where prpc.id_caso=?1         and prpc.estado_registro=''ACT''       and p.estado_registro = ''ACT''       and r.estado_registro = ''ACT''       and p.id_persona=?2' 
	where nombre_valor_dinamico = 'leyAsistenciaP' and id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where tipo_servicio = 'PDL' and nombre = 'PCADP') 
update VALOR_PLANTILLA_CARTA set consulta = 'NATIVE: select  case  when p.tipo_persona = ''NAT'' and r.nombre = ''APODCTE'' or r.nombre = ''APODCDO'' then ''Doctor(a)''          when p.tipo_persona = ''JUR'' then ''Señor(a)<br/>Representante Legal de''          else ''Señor(a)''           end         from Persona p  left join ROL_PERSONA_CASO prpc on prpc.id_persona = p.id_persona            left join ROL r on r.id_rol = prpc.id_rol       where prpc.id_caso=?1         and prpc.estado_registro=''ACT''       and p.estado_registro = ''ACT''       and r.estado_registro = ''ACT''       and p.id_persona=?2 '
	where nombre_valor_dinamico = 'senorDoctorRLegalP' and id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where tipo_servicio = 'PDL' and nombre = 'PCADP') 
update VALOR_PLANTILLA_CARTA set consulta = 'NATIVE: select concat(p.primer_nombre_o_razon_social,'' '', p.segundo_nombre, '' '', p.primer_apellido,'' '', p.segundo_apellido) from Persona p left join ROL_PERSONA_CASO prpc on prpc.id_persona = p.id_persona where prpc.id_caso=?1 AND prpc.estado_registro=''ACT'' and p.id_persona=?2'
	where nombre_valor_dinamico = 'dirigidoP' and id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where tipo_servicio = 'PDL' and nombre = 'PCADP') 


-- PCPAU
update plantilla_carta set plantilla_html = '<html>   <head>  <title>Citación audiencia</title>  <style>body {font-size:9px} @page { margin-top: 116px; margin-bottom: 116px; margin-left: 126px; margin-right: 126px} </style> </head>   <body>    <font face="Arial">      <p align="left">       ciudadP, dia_cartaP de mes_cartaP de ano_cartaP       </p>     <p align="left">           senorDoctorRLegalP      <br/>       dirigidoP        <br/>        direccionEnvio        telefonoEnvio        ciudadEnvio      </p>     <p align="right">        radicadoP      </p>      <p align="justify">        REFERENCIA: SOLICITUD DE CONCILIACION DE convocanteP PARA SOLUCIONAR LAS DIFERENCIAS SURGIDAS CON convocadoP.      </p>   <p align="justify">Apreciado(a) señor(a):</p>      PCPAUparrafo1P     <p align="justify">      El nombreCentroP, se creó para ayudar a que las personas resuelvan LEGALMENTE sus problemas sin necesidad de ir a un juzgado. Las personas que vienen al Centro de Conciliación arreglan rápidamente sus conflictos, los solucionan pacíficamente y no se ocasionan más problemas de los que ya tienen.    </p>     <p align="justify">      Queremos comentarle que para este Centro, es un honor prestarle nuestros servicios. Por lo tanto, atentamente lo invitamos a una audiencia de conciliación en nuestras oficinas ubicada en la direccionSedeP, nombreSedeP el fechaAudienciaP a las horaAudienciaP.    </p>    leyAsistenciaP        PCPAUparrafo4P       <p align="justify">        Para nosotros es importante saber si recibieron esta comunicación, por lo cual les solicitamos que confirmen su asistencia al siguiente correo electrónico: emailConciliadorP. Por favor indique el número de caso.      </p>    <p align="justify">        Para información adicional puede comunicarse al teléfono: <!--número de teléfono-->      </p>        <p align="justify">Cordialmente,</p>      <p align="justify">&#160;</p>  <img src="firmaP" width="237" height="85" />   <p align="left">        conciliadorP      <br/>     Conciliador<br/>     Registro registroConciliadorP      </p>      <p align="right">        Caso codigoCaso      </p>     </font>    </body>   </html>' where tipo_servicio = 'PDL' and nombre = 'PCPAU'
update VALOR_PLANTILLA_CARTA set consulta = 'NATIVE: select  case  when p.tipo_persona = ''NAT'' and r.nombre = ''APODCTE'' or r.nombre = ''APODCDO'' then ''Doctor(a)''          when p.tipo_persona = ''JUR'' then ''Señor(a)<br/>Representante Legal de''          else ''Señor(a)''          end         from Persona p  left join ROL_PERSONA_CASO prpc on prpc.id_persona = p.id_persona            left join ROL r on r.id_rol = prpc.id_rol       where prpc.id_caso=?1         and prpc.estado_registro=''ACT''       and p.estado_registro = ''ACT''       and r.estado_registro = ''ACT''       and p.id_persona=?2 '
	where nombre_valor_dinamico = 'senorDoctorRLegalP' and id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where tipo_servicio = 'PDL' and nombre = 'PCPAU') 
update VALOR_PLANTILLA_CARTA set consulta = 'NATIVE: select concat(p.primer_nombre_o_razon_social,'' '', p.segundo_nombre, '' '', p.primer_apellido,'' '', p.segundo_apellido) from Persona p left join ROL_PERSONA_CASO prpc on prpc.id_persona = p.id_persona where prpc.id_caso=?1 AND prpc.estado_registro=''ACT'' and p.id_persona=?2'
	where nombre_valor_dinamico = 'dirigidoP' and id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where tipo_servicio = 'PDL' and nombre = 'PCPAU')  
update VALOR_PLANTILLA_CARTA set consulta = 'NATIVE: select  case when r.nombre = ''CONVOTE'' or r.nombre = ''APODCTE'' then ''<p align="justify">Hemos recibido su comunicación en la cual nos solicita la citación de convocadoP a una Audiencia de Conciliación a fin de solucionar las diferencias presentadas con ocasión de convocanteP.</p>''          when r.nombre = ''CONVODO'' or r.nombre = ''APODCDO'' then ''<p align="justify">Hemos recibido una solicitud de conciliación, en la que convocanteP nos pide la citación de convocadoP a una Audiencia de Conciliación a fin de solucionar las diferencias presentadas con ocasión de casoHechosP.</p>''        else ''''         end      from ROL r INNER JOIN ROL_PERSONA_CASO rpc on rpc.id_rol = r.id_rol          INNER JOIN PERSONA P on p.id_persona = rpc.id_persona    where rpc.id_caso= ?1     and rpc.estado_registro=''ACT''    and p.estado_registro = ''ACT''    and r.estado_registro = ''ACT''    and p.id_persona = ?2'
	where nombre_valor_dinamico = 'PCPAUparrafo1P' and id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where tipo_servicio = 'PDL' and nombre = 'PCPAU') 
update VALOR_PLANTILLA_CARTA set consulta = 'NATIVE: select   case when p.tipo_persona = ''NAT'' and (r.nombre = ''CONVOTE'' or r.nombre = ''CONVODO'') then ''<p align="justify">Así mismo le recordamos que de conformidad con lo dispuesto en el parágrafo 2º del artículo 1º de la Ley 640 de 2001, deberá asistir  a la audiencia de conciliación y podrá hacerlo junto con su apoderado, de lo contrario su inasistencia puede ser considerada como indicio grave en contra de sus pretensiones o de sus excepciones de mérito en un eventual proceso judicial que verse sobre los mismos hechos, de acuerdo a lo establecido en el artículo 22 de la misma ley.</p>''           else ''''       end         from Persona p  left join ROL_PERSONA_CASO prpc on prpc.id_persona = p.id_persona            left join ROL r on r.id_rol = prpc.id_rol       where prpc.id_caso=?1         and prpc.estado_registro=''ACT''       and p.estado_registro = ''ACT''       and r.estado_registro = ''ACT''       and p.id_persona=?2'
	where nombre_valor_dinamico = 'leyAsistenciaP' and id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where tipo_servicio = 'PDL' and nombre = 'PCPAU') 
update VALOR_PLANTILLA_CARTA set consulta = 'NATIVE: select  case when r.nombre = ''CONVOTE'' or r.nombre = ''APODCTE'' then ''<p align="justify">Le informamos que habrá lugar a la reliquidación del servicio, por concepto de gastos administrativos u honorarios del conciliador, cuando del estudio de la solicitud, en el desarrollo del trámite conciliatorio o en el acuerdo, se determine que el valor de las diferencias objeto de conflicto, es superior al establecido inicialmente como valor de la solicitud.</p>''          else ''''         end        from ROL r INNER JOIN ROL_PERSONA_CASO rpc on rpc.id_rol = r.id_rol        INNER JOIN PERSONA P on p.id_persona = rpc.id_persona      where rpc.id_caso= ?1      and rpc.estado_registro=''ACT''      and p.estado_registro = ''ACT''      and r.estado_registro = ''ACT''      and p.id_persona = ?2 '
	where nombre_valor_dinamico = 'PCPAUparrafo4P' and id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where tipo_servicio = 'PDL' and nombre = 'PCPAU')
	
-- Correccion bug 9957
/****** Object:  UserDefinedFunction [dbo].[diasHabilesEntreDosFechas]    Script Date: 15/11/2018 11:10:38 a.m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:        Juan Carlos Lores
-- Create date: 28/11/2017
-- Description:    Obtiene el número de días habiles entre dos fechas

-- Author:			Juan Diego Cepeda - Juan Nicolás Martínez Fagua
-- Modified date: 15/11/2018
-- Description:		Se ajusta el modo de obtener los festivos y fines de semana
-- =============================================
ALTER FUNCTION [dbo].[diasHabilesEntreDosFechas]
(
    @fechaInicial AS DATE,@fechaFinal AS DATE
)
RETURNS INT
AS
BEGIN
    DECLARE @DiasHabiles AS INT

   Declare @finesdesemana AS INT = 0;
    WITH RangoFechas(fecha) AS
    (
        SELECT @fechaInicial AS date
        UNION ALL
        SELECT DATEADD(d,1,fecha)
        FROM RangoFechas
        WHERE fecha < @fechaFinal
    )
    SELECT @finesdesemana = @finesdesemana +
    CASE DATEPART(dw,A.fecha) WHEN 1 THEN 1 WHEN 7 THEN 1 ELSE 0 END
    FROM RangoFechas A
    LEFT JOIN (SELECT fec.fecha as fechaFestivo
    FROM DIA_FESTIVO fec where fecha BETWEEN CAST(@fechaInicial AS date) AND
    @fechaFinal) AS B ON A.fecha = B.fechaFestivo
    WHERE b.fechaFestivo IS NULL
    OPTION (MAXRECURSION 0)

   SELECT @DiasHabiles = DATEDIFF(d, @fechaInicial,  @fechaFinal) - (SELECT COUNT(*)
    FROM DIA_FESTIVO where fecha BETWEEN CAST(@fechaInicial AS date) AND @fechaFinal) - @finesdesemana

   RETURN @DiasHabiles
END

GO

-- Actualizacion valor_plantilla_carta PCPAUparrafo1P PCPAU 
update VALOR_PLANTILLA_CARTA set consulta = 'NATIVE: select  case when r.nombre = ''CONVOTE'' or r.nombre = ''APODCTE'' then ''<p align="justify">Hemos recibido su comunicación en la cual nos solicita la citación de convocadoP a una Audiencia de Conciliación a fin de solucionar las diferencias presentadas con ocasión de casoHechosP.</p>''          when r.nombre = ''CONVODO'' or r.nombre = ''APODCDO'' then ''<p align="justify">Hemos recibido una solicitud de conciliación, en la que convocanteP nos pide la citación de convocadoP a una Audiencia de Conciliación a fin de solucionar las diferencias presentadas con ocasión de casoHechosP.</p>''        else ''''         end      from ROL r INNER JOIN ROL_PERSONA_CASO rpc on rpc.id_rol = r.id_rol          INNER JOIN PERSONA P on p.id_persona = rpc.id_persona    where rpc.id_caso= ?1     and rpc.estado_registro=''ACT''    and p.estado_registro = ''ACT''    and r.estado_registro = ''ACT''    and p.id_persona = ?2'
where nombre_valor_dinamico = 'PCPAUparrafo1P' and id_plantilla_carta = (select id_plantilla_carta from PLANTILLA_CARTA where tipo_servicio = 'PDL' and nombre = 'PCPAU')

-- Correccion 9970
update plantilla_carta set plantilla_html = '<html>   <head>  <title>Citación audiencia</title>  <style>body {font-size:12px} @page { margin-top: 116px; margin-bottom: 116px; margin-left: 126px; margin-right: 126px} </style> </head>   <body>    <font face="Arial">      <p align="left">       ciudadP, dia_cartaP de mes_cartaP de ano_cartaP       </p>     <p align="left">           senorDoctorRLegalP      <br/>       dirigidoP        <br/>        direccionEnvio        telefonoEnvio        ciudadEnvio      </p>     <p align="right">        radicadoP      </p>      <p align="justify">        REFERENCIA: SOLICITUD DE CONCILIACION DE convocanteP PARA SOLUCIONAR LAS DIFERENCIAS SURGIDAS CON convocadoP.      </p>   <p align="justify">Apreciado(a) señor(a):</p>      PCPAUparrafo1P     <p align="justify">      El nombreCentroP, se creó para ayudar a que las personas resuelvan LEGALMENTE sus problemas sin necesidad de ir a un juzgado. Las personas que vienen al Centro de Conciliación arreglan rápidamente sus conflictos, los solucionan pacíficamente y no se ocasionan más problemas de los que ya tienen.    </p>     <p align="justify">      Queremos comentarle que para este Centro, es un honor prestarle nuestros servicios. Por lo tanto, atentamente lo invitamos a una audiencia de conciliación en nuestras oficinas ubicada en la direccionSedeP, nombreSedeP el fechaAudienciaP a las horaAudienciaP.    </p>    leyAsistenciaP        PCPAUparrafo4P       <p align="justify">        Para nosotros es importante saber si recibieron esta comunicación, por lo cual les solicitamos que confirmen su asistencia al siguiente correo electrónico: emailConciliadorP. Por favor indique el número de caso.      </p>    <p align="justify">        Para información adicional puede comunicarse al teléfono: <!--número de teléfono-->      </p>        <p align="justify">Cordialmente,</p>      <p align="justify">&#160;</p>  <img src="firmaP" width="237" height="85" />   <p align="left">        conciliadorP      <br/>     Conciliador<br/>     Registro registroConciliadorP      </p>      <p align="right">        Caso codigoCaso      </p>     </font>    </body>   </html>' where tipo_servicio = 'PDL' and nombre = 'PCPAU'
update plantilla_carta set plantilla_html = '<html>   <head> <title>Citación audiencia</title><style>body {font-size:12px} @page { margin-top: 116px; margin-bottom: 116px; margin-left: 126px; margin-right: 126px} </style></head>   <body>    <font face="Arial" >    <p align="left">     ciudadP, dia_cartaP de mes_cartaP de ano_cartaP    </p>     <p align="left">           senorDoctorRLegalP        <br/>    dirigidoP      <br/>      direccionEnvio         telefonoEnvio           ciudadEnvio    </p>    <p align="right">      radicadoP    </p>    <p align="justify">      REFERENCIA: SOLICITUD DE CONCILIACION DE convocanteP PARA SOLUCIONAR LAS DIFERENCIAS SURGIDAS CON convocadoP.    </p>   <p align="justify">Apreciado(a) señor(a):</p>  <p align="justify">Nos complace invitarle a una audiencia de conciliación, el fechaAudienciaP, a las horaAudienciaP en nuestras oficinas ubicadas en direccionSedeP.</p>       leyAsistenciaP          <p align="justify">      Para nosotros es importante saber si recibieron esta comunicación, por lo cual les solicitamos que confirmen su asistencia al siguiente correo electrónico: emailConciliadorP. Por favor indique el número de caso.    </p>   <p align="justify">      Para información adicional puede comunicarse al teléfono <!--número de teléfono-->    </p>  <p align="justify">Cordialmente,</p>      <p align="justify">&#160;</p> <img src="firmaP" width="235" height="87" />   <p align="left">      conciliadorP    <br/> Conciliador <br/>   Registro registroConciliadorP    </p>   <p align="right">      Caso codigoCaso    </p>  </font>  </body>  </html>' where tipo_servicio = 'PDL' and nombre = 'PCADP'
-- Correccion 9975
delete dominio where dominio = 'ROL_DE_PARTE_CONCILIACION' and codigo = 'SOLTAN'



-- Corrección defecto 9980
delete FUNCIONALIDAD_ROL where nombre_funcionalidad='TRANSF009'
delete FUNCIONALIDAD where nombre='TRANSF009'

-- Corrección defecto 9981
insert PARAMETROS_GENERALES values ('URL_CLAV',NULL,'HIPERVINCULO_SIMASC_CLAVE','HIPERVINCULO SIMASC GENERAR CLAVE',NULL,'http://presprivdev1/simasc/#','URL de hipervínculo generar clave','USUARIO_SIMASC',GETDATE(),'ACT')

-- Correccion 9985
delete PARAMETROS_GENERALES where tipo = 'PARAMETROS_PUP' and codigo <> 'SERV'

-- Ajuste constraints tabla detalle_reliquidacion
declare @nombreConstraint varchar(max)
set @nombreConstraint = (SELECT OBJECT_NAME(OBJECT_ID)
FROM sys.objects
WHERE type_desc = 'CHECK_CONSTRAINT'
and OBJECT_NAME(parent_object_id) = 'DETALLE_RELIQUIDACION'
and OBJECT_NAME(OBJECT_ID) LIKE 'CK__DETALLE_R__servi%'
)

IF @nombreConstraint is not null
begin
	exec ('ALTER TABLE [dbo].[DETALLE_RELIQUIDACION] DROP CONSTRAINT [' + @nombreConstraint + ']')
end

--Corrección 9996
update FUNCIONALIDAD set nombre_funcionalidad_padre = 'TRANSF081tabExpediente' where nombre = 'ARBF090divAccionesCrearEliminarCarpeta'

-- Construccion control de cambios 10
insert into dominio values ('FIRM', 'ESTADO_FIRMA_DOCUMENTO', 'Firmado', 'Firmado', null, null)
insert into dominio values ('SOLICI', 'ESTADO_FIRMA_DOCUMENTO', 'Solicitado', 'Solicitado', null, null)
insert into dominio values ('SINSOLI', 'ESTADO_FIRMA_DOCUMENTO', 'Sin solicitar', 'Sin solicitar', null, null)

-- TRANS-F-023
INSERT INTO PARAMETROS_GENERALES VALUES ('USU_CERT', NULL, 'PARAMETROS_CERTICAMARA', 'USUARIO_SERVICIO_VALIDACION_PERSONA_CERTICAMARA', NULL, 'CamaraBogota', 'Usuario servicio validación datos persona Certicamara', 'USUARIO_SIMASC', GETDATE(), 'ACT')
INSERT INTO PARAMETROS_GENERALES VALUES ('PASS_CERT', NULL, 'PARAMETROS_CERTICAMARA', 'CONTRASENA_SERVICIO_VALIDACION_PERSONA_CERTICAMARA', NULL, 'CamaraBogota', 'Contraseña servicio validación datos persona Certicamara', 'USUARIO_SIMASC', GETDATE(), 'ACT')
INSERT INTO PARAMETROS_GENERALES VALUES ('ID_APP_CCB', NULL, 'PARAMETROS_CERTICAMARA', 'ID_APLICACION_CCB_VALIDACION_PERSONA_CERTICAMARA', NULL, '2', 'Id de aplicación asignado a CCB para el consumo del servicio validación datos persona Certicamara', 'USUARIO_SIMASC', GETDATE(), 'ACT')
INSERT INTO PARAMETROS_GENERALES VALUES ('PAGINAS', NULL, 'PARAMETROS_CERTICAMARA', 'VALOR_PAGINAS_VALIDACION_PERSONA_CERTICAMARA', NULL, 'D-VC1-VO-P-AC-EC', 'Valor del parámetro PAGINAS del servicio validación datos persona Certicamara', 'USUARIO_SIMASC', GETDATE(), 'ACT')
INSERT INTO PARAMETROS_GENERALES VALUES ('VALIDACION', NULL, 'PARAMETROS_CERTICAMARA', 'VALOR_VALIDACION_VALIDACION_PERSONA_CERTICAMARA', NULL, '3', 'Valor del parámetro VALIDACION del servicio validación datos persona Certicamara', 'USUARIO_SIMASC', GETDATE(), 'ACT')
INSERT INTO PARAMETROS_GENERALES VALUES ('URL_CERT', NULL, 'URL_SERVICIOS', 'URL_VALIDACION_CERTICAMARA', NULL, 'https://pruebassobreflex.certicamara.com/sobreflex/Autenticacion.aspx', 'Url servicio validación personas Certicamara', 'USUARIO_SIMASC', GETDATE(), 'ACT')

-- Función para control de cambios conteo de términos y suspensiones

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:  Carlos Andrés González
-- Modified date: 05/12/2018
-- Description:  Función para calcular el número de días transcurridos entre dos fechas
-- =============================================
CREATE FUNCTION [dbo].[calcularDiasEntreDosFechas]
(
    @fechaInicial AS DATE,@fechaFinal AS DATE
)
RETURNS INT
AS
BEGIN
    DECLARE @DiasHabiles AS INT
	Declare @numeroDiasTranscurridos as int = 0;
	Declare @fechaInicialDiasTranscurridos as date = @fechaInicial;
    Declare @finesdesemana AS INT = 0;
    WITH RangoFechas(fecha) AS
    (
        SELECT @fechaInicial AS date
        UNION ALL
        SELECT DATEADD(d,1,fecha)
        FROM RangoFechas
        WHERE fecha < @fechaFinal
    )
    SELECT @finesdesemana = @finesdesemana +
    CASE DATEPART(dw,A.fecha) WHEN 1 THEN 1 WHEN 7 THEN 1 ELSE 0 END
    FROM RangoFechas A
    LEFT JOIN (SELECT fec.fecha as fechaFestivo
    FROM DIA_FESTIVO fec where fecha BETWEEN CAST(@fechaInicial AS date) AND
    @fechaFinal) AS B ON A.fecha = B.fechaFestivo
    WHERE b.fechaFestivo IS NULL
    OPTION (MAXRECURSION 0)
	
	while @fechaInicialDiasTranscurridos<=@fechaFinal
	BEGIN
		set @numeroDiasTranscurridos+=1;
		set @fechaInicialDiasTranscurridos=DATEADD(d,1,@fechaInicialDiasTranscurridos);
	end

   SELECT @DiasHabiles = @numeroDiasTranscurridos - (SELECT COUNT(*)
    FROM DIA_FESTIVO where fecha BETWEEN CAST(@fechaInicial AS date) AND @fechaFinal) - @finesdesemana

   return @DiasHabiles
END

GO

-- TRANS-F-023 y CON-F-097
INSERT INTO DOMINIO VALUES ('FELEACT', 'TIPO_EVENTO', 'Firma eléctronica de acta o constancia', 'Firma eléctronica de acta o constancia', NULL, NULL)
INSERT INTO DOMINIO VALUES ('FEACTCON', 'TIPO_EVENTO', 'Firma digital de acta por conciliador', 'Firma digital de acta por conciliador', NULL, NULL)

-- Construccion control de cambios 10
insert into PARAMETROS_GENERALES (codigo, tipo, nombre, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values ('IDAPP', 'PARAMETROS_TOKEN', 'ID aplicación', '2', 'ID aplicación enviada para petición', 'SIMASC_USUARIO', GETDATE(), 'ACT'),
('IDSEDE', 'PARAMETROS_TOKEN', 'ID sede', '03', 'ID aplicación enviada para petición', 'SIMASC_USUARIO', GETDATE(), 'ACT')

insert into PARAMETROS_GENERALES (codigo, tipo, nombre, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values ('TIPOFIR', 'FIRMA_DIGITAL_PDF', 'Tipo firma electronica', '4', 'Tipo firma electronica', 'SIMASC_USUARIO', GETDATE(), 'ACT')

insert into PARAMETROS_GENERALES (codigo, tipo, nombre, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values ('IDSUC', 'FIRMA_DIGITAL_PDF', 'Identificador sucursal', '001', 'Identificador sucursal', 'SIMASC_USUARIO', GETDATE(), 'ACT')

insert into PARAMETROS_GENERALES (codigo, tipo, nombre, valor_texto, descripcion, id_usuario_modificacion, fecha_ultima_modificacion, estado_registro)
values ('POLDIG', 'FIRMA_DIGITAL_PDF', 'Politica firma digital', '9', 'Politica de firma digital', 'SIMASC_USUARIO', GETDATE(), 'ACT')

insert into HOMOLOGACION_SISTEMA_EXTERNO values ('CC', 'TIPO_DOCUMENTO_PERSONA', '1', 'SIREP', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into HOMOLOGACION_SISTEMA_EXTERNO values ('JUR', 'TIPO_DOCUMENTO_PERSONA', '2', 'SIREP', 'USUARIO_SIMASC', GETDATE(), 'ACT')
insert into HOMOLOGACION_SISTEMA_EXTERNO values ('CE', 'TIPO_DOCUMENTO_PERSONA', '3', 'SIREP', 'USUARIO_SIMASC', GETDATE(), 'ACT')

-- Ajuste bug 8138 Regresión valor platilla carta obligacionesP

update VALOR_PLANTILLA_CARTA set consulta=
'NATIVE: LIST: SELECT case when o.tipo_obligacion = ''OBLDAR''   
   then concat( ''<p align="justify">'', stuff((select '', '', concat(pe.primer_nombre_o_razon_social, rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido),
       rtrim('' ''+pe.segundo_apellido))                   
	   FROM OBLIGACION_PARTE op                    
	   INNER JOIN PERSONA pe
	   ON op.id_persona = pe.id_persona                     
	   WHERE op.tipo_parte_resultado = ''OBLPPAGA''         
	   AND op.estado_registro = ''ACT''                     
	   AND op.id_resultado_audiencia = ra.id_resultado_audiencia                       
	   FOR xml PATH ('''')), 1, 2, ''''),                         
	   '' se compromete a pagar a '',                           
	   stuff((select '', '', concat(pe.primer_nombre_o_razon_social,         
	    rtrim('' ''+pe.segundo_nombre), rtrim('' ''+pe.primer_apellido),          
		rtrim('' ''+pe.segundo_apellido))                        
		FROM OBLIGACION_PARTE op                          
		INNER JOIN PERSONA pe                        
		ON op.id_persona = pe.id_persona                         
		WHERE op.tipo_parte_resultado = ''OBLPRECI''                         
		  AND op.estado_registro = ''ACT''                          
		  AND op.id_resultado_audiencia = ra.id_resultado_audiencia                            
		  FOR xml PATH ('''')), 1, 2, ''''),                            
		  '' la suma de '',                            
		  o.valor_total_acuerdo, '' en '',                             
		  (select COUNT(*) from CUOTA where id_resultado_audiencia = ra.id_resultado_audiencia),                               
		   '' cuotas en '',                       
		   (select nombre from DOMINIO where codigo = o.forma_de_pago and dominio = ''FORMA_PAGO_OBLIGACION''),                          
		   '' las cuales serán canceladas en '', o.direccion,                            
		   ''  en las siguientes fechas: <p align="justify">'',                         
		   replace(stuff((select ''.%'' ,          
		    concat( ''Cuota No. '', numero_cuota, '': El '', FORMAT(fecha_prevista,''dd/MM/yyyy'') )                           
			FROM CUOTA                             
			 WHERE id_resultado_audiencia = ra.id_resultado_audiencia                              
			 FOR xml PATH ('''')), 1, 2, ''''), ''%'', ''<br/>''),                              
			  ''.<p align="justify">Observaciones:<br/>'', o.observaciones, ''</p>'')                     
			  when o.tipo_obligacion = ''OBLHACER'' then                   
			  concat( ''<p align="justify">'', stuff((select '', '',                    
			  concat(pe.primer_nombre_o_razon_social,                  
			  rtrim('' ''+pe.segundo_nombre),                   
			  rtrim('' ''+pe.primer_apellido),                   
			  rtrim('' ''+pe.segundo_apellido))                                   
			  FROM OBLIGACION_PARTE op                                  
			  INNER JOIN PERSONA pe                                   
			   ON op.id_persona = pe.id_persona                                   
			   WHERE op.tipo_parte_resultado = ''OBLPHACE''                                     
			   AND op.estado_registro = ''ACT''                                      
			   AND op.id_resultado_audiencia = ra.id_resultado_audiencia                
			   FOR xml PATH ('''')), 1, 2, ''''),                             
			    '' se compromete a '', o.compromiso,                                 
				''. La fecha de cumplimiento del acuerdo es el '',                                
				FORMAT(o.fecha_compromiso,''dd/MM/yyyy''), '' en '',                                  
				 o.direccion, ''.</p><p align="justify">Observaciones:<br/> '', o.observaciones, ''</p>'')                        
				 end  FROM AUDIENCIA au                         
				 INNER JOIN RESULTADO_AUDIENCIA ra  ON ra.id_audiencia = au.id_audiencia                        
				  INNER JOIN OBLIGACION o  ON ra.id_resultado_audiencia = o.id_resultado_audiencia                      
				   WHERE au.id_caso = ?1  AND au.id_audiencia = ?2  AND au.estado_registro = ''ACT''                       
				   AND ra.estado_registro = ''ACT''  AND o.estado_registro = ''ACT'''
  WHERE NOMBRE_VALOR_DINAMICO='obligacionesP' AND ID_PLANTILLA_CARTA = (select id_plantilla_carta from plantilla_carta where nombre='ACTACON')

-- Creación de la tabla temporal ARBITROS_SIN_PRONUNCIAR para mantener los datos de la alerta 'COMDESP'
CREATE TABLE ARBITROS_SIN_PRONUNCIAR (
	id_caso int,
	nombre_arbitro varchar(max),
	fecha_designacion varchar(200),
	fecha_comunicacion varchar(200),
	persona_abogado_caso int
)


-- Correccion permisos para defecto 10043

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'CONC052ActualizarEstadoCorrespondencia';
DELETE FUNCIONALIDAD where nombre='CONC052ActualizarEstadoCorrespondencia';
insert into FUNCIONALIDAD values ('CONC052ActualizarEstadoCorrespondencia', 'Actualizar Estado Correspondencia','app/Administracion/CONC052','ADM', 'ANGULAR',NULL,'SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'CONC052ActualizarEstadoCorrespondencia', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('SECCON','ACO','CON','JEFECON');
			
-- Correccion permisos defecto 10046

DELETE FROM FUNCIONALIDAD_ROL WHERE NOMBRE_FUNCIONALIDAD='TRANSF009' AND ID_ROL IN (select id_rol from rol where nombre in ('CONVOTE','CONVODO'))


-- Corrección defecto 10070
update FUNCIONALIDAD set titulo = 'Acceso sorteo' where nombre = 'TRANSF047EnlaceSorteo'
update FUNCIONALIDAD set titulo = 'Reversar publicación documento', nombre_funcionalidad_padre = 'ARBF063tabDocAportados' where nombre = 'ARBF063ReversarPublicacion'
update FUNCIONALIDAD set nombre_funcionalidad_padre = 'ARBF063tabDocAportados' where nombre = 'ARBF063PublicarDocumento'

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063buttonAdjuntarDocumento';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063buttonAdjuntarDocumentoPDL';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063buttonAdjuntarDocumentoPJT';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063EliminarDocumento';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063tableDocumentosAportadosPDL';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063tableDocumentosAportadosPJT';
DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'TRANSF097tabDocumentosAportado';
DELETE FUNCIONALIDAD where nombre='ARBF063buttonAdjuntarDocumento';
DELETE FUNCIONALIDAD where nombre='ARBF063buttonAdjuntarDocumentoPDL';
DELETE FUNCIONALIDAD where nombre='ARBF063buttonAdjuntarDocumentoPJT';
DELETE FUNCIONALIDAD where nombre='ARBF063EliminarDocumento';
DELETE FUNCIONALIDAD where nombre='ARBF063tableDocumentosAportadosPDL';
DELETE FUNCIONALIDAD where nombre='ARBF063tableDocumentosAportadosPJT';
DELETE FUNCIONALIDAD where nombre='TRANSF097tabDocumentosAportado';

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063EliminarDocumentoPJT';
DELETE FUNCIONALIDAD where nombre='ARBF063EliminarDocumentoPJT';
insert into FUNCIONALIDAD values ('ARBF063EliminarDocumentoPJT', 'Eliminar documento','app/Arbitraje/ARBF063','PJT', 'ANGULAR','ARBF063tabDocAportados','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063EliminarDocumentoPJT', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('JEFEARB', 'ABO', 'ASA', 'SEC', 'ARB', 'AUX');

DELETE FUNCIONALIDAD_ROL WHERE nombre_funcionalidad = 'ARBF063EliminarDocumentoPDL';
DELETE FUNCIONALIDAD where nombre='ARBF063EliminarDocumentoPDL';
insert into FUNCIONALIDAD values ('ARBF063EliminarDocumentoPDL', 'Eliminar documento','app/Arbitraje/ARBF063','PDL', 'ANGULAR','ARBF063TabDocAportadosConciliacion','SIMASC_USER', SYSDATETIME(), 'ACT');
INSERT INTO FUNCIONALIDAD_ROL
	select distinct 'USUARIO_SIMASC', SYSDATETIME(), 'INA', 'ARBF063EliminarDocumentoPDL', r.id_rol
		from ROL r
		join dominio d on d.codigo = r.nombre
		where d.dominio
			in('ROL_PERSONA')
			and d.codigo not in ('ACO', 'JEFECON', 'SECCON', 'CON');

-- Corrección bug 10072
update VALOR_PLANTILLA_CARTA set consulta = 'NATIVE: select TOP 1 ce.direccion  from rol_persona_caso rpc inner join CORREO_ELECTRONICO ce on ce.id_persona = rpc.id_persona where id_rol in (select id_rol from tipo_de_servicio_rol where tipo_servicio = ''PDL'') and rpc.estado not in (''REC'', ''INA'')  and ce.tipo = ''PRI'' and rpc.tipo_nombramiento = ''PRI'' and id_caso = ?1 and rpc.estado_registro = ''ACT'' and ce.estado_registro = ''ACT''' 
where nombre_valor_dinamico = 'emailConciliadorP' 
and id_plantilla_carta in (select id_plantilla_carta from PLANTILLA_CARTA where nombre IN ('PCPAU', 'PCADP') and tipo_servicio = 'PDL')

update VALOR_PLANTILLA_CARTA set consulta = 'NATIVE: select ISNULL(p.primer_nombre_o_razon_social,'''') +'' ''+ ISNULL(p.segundo_Nombre, '''')       +'' ''+ ISNULL(p.primer_Apellido,'''') +'' ''+ isnull(p.segundo_Apellido,'''') from rol_persona_caso rpc inner join persona p on p.id_persona = rpc.id_persona where id_rol in (select id_rol from tipo_de_servicio_rol where tipo_servicio = ''PDL'')  and rpc.estado not in (''REC'', ''INA'') and id_caso = ?1  and rpc.estado_registro = ''ACT''  and p.estado_registro = ''ACT'' and rpc.tipo_nombramiento = ''PRI''' 
where nombre_valor_dinamico = 'conciliadorP' 
and id_plantilla_carta in (select id_plantilla_carta from PLANTILLA_CARTA where nombre IN ('PCPAU', 'PCADP') and tipo_servicio = 'PDL')