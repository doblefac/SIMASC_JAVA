package com.ccb.simasc.negocio.arbitraje;

import static com.ccb.simasc.transversal.utilidades.UtilOperaciones.formatearFechaReporte;
import static com.ccb.simasc.transversal.utilidades.UtilOperaciones.formatearNumeroDecimal;
import static com.ccb.simasc.transversal.utilidades.UtilOperaciones.nvl;
import static com.ccb.simasc.transversal.utilidades.UtilOperaciones.obtenerDiasHabilesEntreFechas;
import static com.ccb.simasc.transversal.utilidades.UtilOperaciones.obtenerFechaComienzoDelDia;
import static com.ccb.simasc.transversal.utilidades.UtilOperaciones.obtenerFechaFinDelDia;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.comun.fachada.interfaces.IRealizacionSorteoFacade;
import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronico;
import com.ccb.simasc.integracion.manejadores.ManejadorDominio;
import com.ccb.simasc.integracion.manejadores.ManejadorEventoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorMateria;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroServicioSorteo;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaServicioMateria;
import com.ccb.simasc.integracion.manejadores.ManejadorRequisitoLista;
import com.ccb.simasc.integracion.manejadores.ManejadorRequisitoPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorSorteo;
import com.ccb.simasc.integracion.manejadores.ManejadorTelefono;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.transversal.FachadaDominios;
import com.ccb.simasc.transversal.dto.ArbitroDTO;
import com.ccb.simasc.transversal.dto.ArbitrosDisponiblesSorteoDTO;
import com.ccb.simasc.transversal.dto.SorteoArbitrosDisponiblesDTO;
import com.ccb.simasc.transversal.dto.SorteoLiberacionListaDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteArbitrosDispSorteoDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteArbitrosDisponiblesParaSorteosDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteAspirantesDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteAudienciaDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteAudienciasProgramacionRefrigeriosDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteAuxiliarPromedioTranscripcionDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCambioEstadoOperadoresDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasoCuantiaDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasoParteDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasoSecretarioDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosAceptadosRechazadosSecretarioDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosCerradosDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosPendientesSorteoPublicoDesignacionDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosPorArbitroDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosSorteadosDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteCasosSuspendidosDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteDeArbitroDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteDigitalizacionDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteEstadosArbitroCasosArbitroDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteEstadosArbitroHistoricoEstadoDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteEstadosArbitroMateriaDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteEstadosArbitroRolDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteGeneralArbitrajeDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteIngresosDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteOperadoresSuspendidosDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteRadicadoDocumentosDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteRepartoPorAbogadoDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteSalasOcupadasDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteSecretariosDTO;
import com.ccb.simasc.transversal.dto.reportes.ReporteTranscripcionesDTO;
import com.ccb.simasc.transversal.entidades.Agendamiento;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.entidades.DominioPK;
import com.ccb.simasc.transversal.entidades.ParametroServicioSorteo;
import com.ccb.simasc.transversal.entidades.ParametroSorteo;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Sede;
import com.ccb.simasc.transversal.entidades.Servicio;
import com.ccb.simasc.transversal.entidades.Sorteo;
import com.ccb.simasc.transversal.entidades.Telefono;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;

/**
 * Fachada encargada de implementar los metodos de consulta de los reportes
 * @author aalvarez
 *
 */
@Stateless
@LocalBean
public class ReporteFacade {

	private static final String EXCEPCION_ARBITRO_CONSULTADO_NULO = "El Arbitro seleccionado no existe en la base de datos.";
	private static final String EXCEPCION_CALCULO_DIAS_HABILES = "Ocurrió un error calculando el promedio diario de minutos transcritos.";
	private static final String ESTADO_CASO_ACTIVO = " AND c.estado_registro = ?1 ";
	private static final String PARAMETRO_TIPO_CASO = "tipoCaso";
	private static final String PARAMETRO_NOMBRE_DOCUMENTO = "nombreDocumento";
	private static final String CONSUMO = "CASE WHEN c.arbitraje_consumo = 1 THEN 'SI' ELSE 'NO' END AS consumo ";
	private static final String SOLICITA_AMPARO = "CASE WHEN c.amparo_de_pobreza = 1 THEN 'SI' ELSE 'NO' END AS solicitaAmparo ";
	private static final String CONCEDE_AMPARO = "CASE WHEN c.concede_amparo = 1 THEN 'SI' ELSE 'NO' END AS concedeAmparo ";

	private static final Logger LOGGER = Logger.getLogger(ReporteFacade.class.getName());
	
	
	@Context
	private HttpHeaders httpHeaders;
	
	@EJB
	private ManejadorTelefono manejadorTelefono; 

	@EJB 
	private ManejadorDominio manejadorDominio;

	@PersistenceContext
	private EntityManager em;

	@EJB
	private FachadaDominios fachadaDominios;
	
	@EJB
	private RequisitoFacade requisitoFacade;
	
	@EJB
	private FechaCasoFacade fechaCasoFacade;
	
	@EJB
	private IRealizacionSorteoFacade sorteoFacade;

	@EJB
	private ManejadorPersona manejadorPersona;
	
	@EJB 
	private ManejadorCaso manejadorCaso;
	
	@EJB 
	private ManejadorSorteo manejadorSorteo;
	
	@EJB
	private GeneradorDTOs generadorDTOs;
	
	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;
	
	@EJB
	private ManejadorPersonaServicioMateria manejadorPersonaServicioMateria; 

	@EJB
	private ManejadorRequisitoPersona manejadorRequisitoPersona; 

	@EJB
	private ManejadorRequisitoLista manejadorRequisitoLista; 
	
	@EJB
	private ManejadorEventoRolPersonaCaso manejadorEventoRolPersonaCaso;
	
	@EJB
	private ManejadorCorreoElectronico manejadorCorreo;
	
	@EJB
	private ManejadorMateria manejadorMateria;
	
	@EJB
	private ManejadorServicio manejadorServicio;
	
	@EJB
    private ManejadorParametroServicioSorteo manejadorParametroServicioSorteo ;

	@EJB
	private IDominioFacade dominioFacade;
	
	/**
	 * Reporte que permite visualizar los datos generales de cada caso
	 * @param filtros	Filtros del reporte
	 * @param fechaIni	Limite inferior de Fecha, corresponde a la fecha de radicación del caso
	 * @param fechaIni	Limite Superior de Fecha, corresponde a la fecha de radicación del caso
	 * @return List<ReporteGeneralArbitrajeDTO>
	 */
	public List<ReporteGeneralArbitrajeDTO> getReporteGeneralArbitraje(
			Map<String,Object> filtros, Date fechaIni, Date fechaFin) {
		
		Long idMateriaLong = null; 
		Long idTipoLong = null;
		String etapa = null;
		String cuantia = null;		
		Long auxAdmin = null;
		Long abogado = null;
		Long sede = null;
		String casoActInactivo = null;
		String tipoSede = null;
		if (filtros.get("km")!=null)
			idMateriaLong = new Long(filtros.get("km").toString());
		if (filtros.get("kti")!=null)
			idTipoLong = new Long(filtros.get("kti").toString());
		if (filtros.get("ke")!=null)
			etapa = filtros.get("ke").toString();
		if (filtros.get("kcu")!=null)
			cuantia = filtros.get("kcu").toString();
		if (filtros.get("kc")!=null)
			casoActInactivo = filtros.get("kc").toString();
		if (filtros.get("kax") != null) 
			auxAdmin = new Long(filtros.get("kax").toString());
		if (filtros.get("ka") != null) 
			abogado = new Long(filtros.get("ka").toString());
		if (filtros.get("kse") != null) 
			sede = new Long(filtros.get("kse").toString());		
		if (filtros.get("kr") != null) 
			tipoSede = filtros.get("kr").toString();
		
		
		DateFormat dateFormat = new SimpleDateFormat(UtilConstantes.FORMATO_FECHA_ANIO_MES_DIA);
		String fechaInicio = dateFormat.format(fechaIni);
		String fechaFinal = dateFormat.format(fechaFin);
		
		StringBuilder personasAsociadas = new StringBuilder();
		personasAsociadas.append("(select stuff((select '- ' + ");
		personasAsociadas.append( "concat (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', ");
		personasAsociadas.append( "pe.segundo_apellido) ");
		personasAsociadas.append( "from ROL_PERSONA_CASO rpc inner join PERSONA pe on rpc.id_persona=pe.id_persona  ");
		personasAsociadas.append( "where rpc.id_caso=ca.id_caso and rpc.estado_registro=?1 ");
		personasAsociadas.append( "and rpc.id_rol in (select id_rol from rol where nombre=  ");

		StringBuilder finPersonasAsociadas = new StringBuilder();
		finPersonasAsociadas.append( ")for xml path('')), 1, 1, '')) ");
			
		
		StringBuilder arbitrosLista = new StringBuilder();
		arbitrosLista.append("(select stuff((select ',  ' + concat (pe.primer_nombre_o_razon_social, ' ', ");
		arbitrosLista.append("pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) ");
		arbitrosLista.append("from ROL_PERSONA_CASO rpc inner join PERSONA pe on rpc.id_persona=pe.id_persona  ");
		arbitrosLista.append("where rpc.id_caso=ca.id_caso and rpc.estado_registro=?1 and rpc.estado in ('ACE') ");
		arbitrosLista.append("and rpc.id_rol in (select DISTINCT rl.id_rol ");
		arbitrosLista.append("from PARAMETRO_SERVICIO_SORTEO pss inner join ROL rl on pss.id_rol = rl.id_rol  ");
		arbitrosLista.append("and rl.nombre <> ?28 ) ");
		arbitrosLista.append("for xml path('')), 1, 1, '')) as arbitrosLista, ");
		
		StringBuilder fechaDecision = new StringBuilder();
		fechaDecision.append("CASE WHEN ca.estado_caso = ?24 ");
		fechaDecision.append("THEN (SELECT CAST(MIN(fc.fecha) AS DATE) as fecha  FROM FECHA_CASO fc WHERE ca.id_caso = fc.id_caso AND fc.tipo_fecha = ?25)");
		fechaDecision.append("ELSE '' END as fechaDecision, ");

		StringBuilder fechaDeCierre = new StringBuilder();
		fechaDeCierre.append("CASE WHEN ca.estado_caso = ?24 ");
		fechaDeCierre.append("THEN (SELECT CAST(MIN(fc.fecha_ultima_modificacion) AS DATE) as fecha FROM FECHA_CASO fc WHERE ca.id_caso = fc.id_caso AND fc.tipo_fecha = ?25)");
		fechaDeCierre.append("ELSE '' END  as fechaDeCierre, ");

		StringBuilder metodoNombramiento = new StringBuilder();
		metodoNombramiento.append("(select stuff((select distinct ',' + nombre ");
		metodoNombramiento.append("FROM dominio dm ");
		metodoNombramiento.append("inner join ROL_PERSONA_CASO rpco on rpco.metodo_nombramiento = dm.codigo ");
		metodoNombramiento.append("WHERE dm.dominio = ?26 ");
		metodoNombramiento.append("and rpco.id_caso = ca.id_caso ");
		metodoNombramiento.append("for xml path('')), 1, 1, '')) as metodoNombramiento ");

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("select distinct ");
		jpqlQuery.append("(select nombre from dominio where codigo=ca.etapa and dominio='" + UtilDominios.DOMINIO_ETAPA_CASO + "') as etapaTramite, ");		
		jpqlQuery.append("ca.id_caso as codigoCaso, ");
		jpqlQuery.append("ca.nombre as nombreCaso, ");
		jpqlQuery.append("(select nombre from servicio where id_servicio=ca.id_servicio) as tipoCaso, ");
		jpqlQuery.append("CAST(ca.fecha_radicacion AS DATE) as fechaRadicacion, ");
		jpqlQuery.append("(select nombre from dominio where codigo=ca.tipo_pacto and dominio='" + UtilDominios.DOMINIO_TIPO_PACTO + "') as tipoPacto, ");
		jpqlQuery.append("(select upper(nombre) from dominio where dominio = ?21 and codigo = ca.tipo_cuantia ) as tipoCuantia, ");
		jpqlQuery.append("ca.valor_pretensiones as valorPretensiones, ");
		jpqlQuery.append("(select nombre from materia where id_materia=ca.id_materia) as materia, ");
		jpqlQuery.append("(select nombre from dominio where codigo = ca.tipo_tramite and dominio = '" + UtilDominios.DOMINIO_TIPO_TRAMITE + "')  as tipoTramite, ");
		jpqlQuery.append(personasAsociadas.toString());
		jpqlQuery.append("?27");
		jpqlQuery.append(finPersonasAsociadas.toString());
		jpqlQuery.append(" as convocante,");
		jpqlQuery.append(personasAsociadas.toString());
		jpqlQuery.append("?5");
		jpqlQuery.append(finPersonasAsociadas.toString());
		jpqlQuery.append(" as apoderadoConvocante,");
		jpqlQuery.append(personasAsociadas.toString());
		jpqlQuery.append("?6");
		jpqlQuery.append(finPersonasAsociadas.toString());
		jpqlQuery.append(" as convocado,");
		jpqlQuery.append(personasAsociadas.toString());
		jpqlQuery.append("?7");
		jpqlQuery.append(finPersonasAsociadas.toString());
		jpqlQuery.append(" as apoderadoConvocado,");
		jpqlQuery.append(personasAsociadas.toString());
		jpqlQuery.append("?8");
		jpqlQuery.append(finPersonasAsociadas.toString());
		jpqlQuery.append(" as procurador,");
		jpqlQuery.append(personasAsociadas.toString());
		jpqlQuery.append("?2");
		jpqlQuery.append(finPersonasAsociadas.toString());
		jpqlQuery.append(" as nombreAbogado,");
		jpqlQuery.append(personasAsociadas.toString());
		jpqlQuery.append("?3");
		jpqlQuery.append(finPersonasAsociadas.toString());
		jpqlQuery.append(" as auxiliarAdministrativo,");
		jpqlQuery.append(arbitrosLista.toString());
		jpqlQuery.append(personasAsociadas.toString());
		jpqlQuery.append("?28");
		jpqlQuery.append(finPersonasAsociadas.toString());
		jpqlQuery.append(" as secretario,");
		jpqlQuery.append(fechaDecision.toString());
		jpqlQuery.append(fechaDeCierre.toString());
		jpqlQuery.append("se.nombre as sede, ");
		jpqlQuery.append("se.tipo_sede as radicacionVirtual, ");
		jpqlQuery.append("(select CAST(MIN(hora_inicio) AS DATE) as fecha from audiencia where id_caso=ca.id_caso and estado_registro=?1) as fechaPrimeraAudiencia, ");
		jpqlQuery.append("(select CAST(MIN(hora_inicio) AS DATE) as fecha from audiencia a left join parametro_servicio_sorteo pss on a.tipo_audiencia = pss.audiencia_libera_suplente where id_caso=ca.id_caso and a.estado=?4 and a.estado_registro=?1) as fechaInstalacion, ");
		jpqlQuery.append("(select nombre from dominio where codigo = ca.estado_caso and dominio = '" + UtilDominios.DOMINIO_ESTADO_CASO + "' ) as casoInactivo, ");
		jpqlQuery.append("d.nombre as motivoCierre, ");
		jpqlQuery.append("(SELECT dbo.diasHabilesEntreDosFechas(ca.fecha_radicacion, (select MIN(hora_inicio) as fecha from audiencia a left join parametro_servicio_sorteo pss on a.tipo_audiencia = pss.audiencia_libera_suplente where id_caso=ca.id_caso and a.estado=?4 and a.estado_registro=?1 ))) as numeroDiasInstalacion, ");
		jpqlQuery.append("(SELECT dbo.diasHabilesEntreDosFechas(ca.fecha_radicacion, (select MIN(hora_inicio) as fecha from audiencia where id_caso=ca.id_caso and estado_registro=?1))) as numeroDiasAtencion, ");
		jpqlQuery.append("(case when ca.arbitraje_consumo = 1 then 'SI' else 'NO' end) as consumo, ");
		jpqlQuery.append("(case when ca.amparo_de_pobreza = 1 then 'SI' else 'NO' end) as amparoPobreza, ");
		jpqlQuery.append("(case when ca.concede_amparo = 1 then 'SI' else 'NO' end) as concedeAmparo, ");
		jpqlQuery.append("(case when ca.medidas_cautelares = 1 then 'SI' else 'NO' end) as medidasCautelares, ");
		jpqlQuery.append(metodoNombramiento.toString());
		jpqlQuery.append("from CASO ca  with (nolock) ");
		jpqlQuery.append("LEFT join DOMINIO d  with (nolock) on d.codigo = ca.motivo_cierre ");
		jpqlQuery.append("inner join SEDE se with (nolock) on ca.id_sede=se.id_sede ");
		jpqlQuery.append("inner join ROL_PERSONA_CASO rpc with (nolock) on ca.id_caso = rpc.id_caso ");
		jpqlQuery.append("inner join PERSONA p with (nolock) on rpc.id_persona = p.id_persona ");
		jpqlQuery.append("inner join SERVICIO ser with (nolock) on ca.id_servicio = ser.id_servicio ");
		jpqlQuery.append("and ser.tipo in (?20, ?23) ");
		jpqlQuery.append("where ca.fecha_radicacion between ?9 and ?10 ");
		jpqlQuery.append("and ca.estado_registro=?1 ");
		jpqlQuery.append("and ca.estado_caso <> ?22 ");
		
		if (sede != null)
			jpqlQuery.append("and ca.id_sede= ?11 ");
		if (idTipoLong != null)
			jpqlQuery.append("and ca.id_servicio= ?12 ");
		if (cuantia != null)
			jpqlQuery.append("and ca.tipo_cuantia= ?13 ");
		if (casoActInactivo != null) 
			jpqlQuery.append("and ca.estado_caso = ?14 ");
		if (abogado != null)
			jpqlQuery.append("and (rpc.estado_registro=?1 and rpc.id_rol in (select id_rol from rol where nombre=?2) and p.id_persona= ?15) ");
		if (tipoSede != null)
			jpqlQuery.append("and se.tipo_sede = ?16 ");
		if (auxAdmin != null)
			jpqlQuery.append("and (rpc.estado_registro=?1 and rpc.id_rol in (select id_rol from rol where nombre=?3) and p.id_persona= ?17) ");
		if(idMateriaLong != null) 
			jpqlQuery.append("and ca.id_materia= ?18 ");
		if (etapa != null)
			jpqlQuery.append("and ca.etapa= ?19 ");
		
		jpqlQuery.append(" ORDER BY ca.id_caso asc");		
		
		Query q = em.createNativeQuery(jpqlQuery.toString(), ReporteGeneralArbitrajeDTO.class);
		q.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(2, UtilDominios.ROL_PERSONA_ABOGADO);
		q.setParameter(3, UtilDominios.ROL_PERSONA_AUXILIAR_ADM);
		q.setParameter(4, UtilDominios.ESTADO_AUDIENCIA_REALIZADA);				
		q.setParameter(5, UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
		q.setParameter(6, UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);
		q.setParameter(7, UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
		q.setParameter(8, UtilDominios.ROL_PERSONA_PROCURADOR_JUDICIAL);
		q.setParameter(20, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		q.setParameter(21, UtilDominios.DOMINIO_TIPO_CUANTIA);
		q.setParameter(22, UtilDominios.ESTADO_CASO_EN_CREACION);
		q.setParameter(23, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);
		q.setParameter(24, UtilDominios.ESTADO_CASO_CERRADO);
		q.setParameter(25, UtilDominios.ESTADO_TIPO_FECHA_CASO_CIERRE);
		q.setParameter(26, UtilDominios.METODOS_DE_NOMBRAMIENTO);
		q.setParameter(27, UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);
		q.setParameter(28, UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
		
		q.setParameter(9, fechaInicio);
		q.setParameter(10, fechaFinal);
		if (sede != null)
			q.setParameter(11, sede);
		if (idTipoLong != null)
			q.setParameter(12, idTipoLong);
		if (cuantia != null)
			q.setParameter(13, cuantia);
		if (casoActInactivo != null) 
			q.setParameter(14, casoActInactivo);
		if (abogado != null)
			q.setParameter(15, abogado);
		if (tipoSede != null)
			q.setParameter(16, tipoSede);
		if (auxAdmin != null)
			q.setParameter(17, auxAdmin);
		if(idMateriaLong != null)
			q.setParameter(18, idMateriaLong);
		if (etapa != null)
			q.setParameter(19, etapa);
		return 	q.getResultList();
	}

	/**
	 * Metodo encargado de generar el reporte del estado de digitalización 
	 * de los documentos
	 * @param filtros	Filtros del reporte
	 * @return List<ReporteDigitalizacionDTO>
	 */	
	@SuppressWarnings("unchecked")
	public List<ReporteDigitalizacionDTO> getReporteDigitalizacion(Map filtros){

		List<ReporteDigitalizacionDTO> reporteDigitalizacionDTOs= new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    	String fechaIncial= (String) filtros.get("fechaInicial");
    	String fechaFinal= (String) filtros.get("fechaFinal");   	
    	String idPersona= (String) filtros.get("idPersona");
		String estado = (String) filtros.get("estado");
		String idCaso = (String) filtros.get("idCaso");
		String idDocumento = (String) filtros.get("codigo");
 		StringBuilder nativeQuery = new StringBuilder();
 		nativeQuery.append("SELECT doc.id_caso, c.nombre as caso, ");
 		nativeQuery.append("doc.nombre as documento,  do.nombre as tipo_documento, ");
 		nativeQuery.append("doc.fecha_asignacion, doc.fecha_digitalizacion, ");
 		nativeQuery.append("case when url is not null or (url is null and estado = 'ENV') then 'Documento cargado' else 'Pendiente de cargue' end as estado, ");
 		nativeQuery.append("concat(doc.descripcion, ' ', doc.observaciones) as observaciones, ");
 		nativeQuery.append("doc.numero_folios, "); 		
 		nativeQuery.append(CONSUMO);
 		nativeQuery.append(", (SELECT s.nombre FROM SERVICIO s ");
		nativeQuery.append("WHERE s.id_servicio = c.id_servicio) AS servicio,"); 		
 		nativeQuery.append("(SELECT STUFF((SELECT ' '+concat (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ',pe.segundo_apellido) FROM PERSONA pe WHERE pe.numero_documento = doc.id_usuario_modificacion for xml path('')),1,1,' ')) AS Digitalizador ");
 		nativeQuery.append("FROM DOCUMENTO doc ");
 		nativeQuery.append("LEFT JOIN CASO c ON doc.id_caso = c.id_caso ");
 		nativeQuery.append("LEFT JOIN DOMINIO do ON do.codigo = doc.tipo_documento ");
 		nativeQuery.append("INNER JOIN SERVICIO s ON c.id_servicio = s.id_servicio ");
 		nativeQuery.append("WHERE convert(varchar(10), doc.fecha_asignacion, 111) BETWEEN ?1 AND ?2 ");
 		nativeQuery.append("AND s.tipo in (?7, ?8) ");
 		if((idPersona!=null && !idPersona.equals(""))) nativeQuery.append("AND doc.id_usuario_modificacion = ?3 ");
 		if((idCaso!=null && !idCaso.equals(""))) nativeQuery.append("AND doc.id_caso = ?4 ");
 		if((estado!=null && !estado.equals(""))) nativeQuery.append("AND doc.estado_digitalizacion = ?5 ");
 		if((idDocumento!=null && !idDocumento.equals(""))) nativeQuery.append("AND doc.tipo_documento = ?6 ");
 		nativeQuery.append("ORDER BY doc.fecha_asignacion ASC ");
 		 		
 		Query query = em.createNativeQuery(nativeQuery.toString());
 		query.setParameter(1, fechaIncial);
 		query.setParameter(2, fechaFinal);
 		query.setParameter(7, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(8, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);
 		if((idPersona!=null && !idPersona.equals(""))){
 			query.setParameter(3, idPersona);
 		}
 		if((idCaso!=null && !idCaso.equals(""))){
 			query.setParameter(4, idCaso);		
 		}		
 		if((estado!=null && !estado.equals(""))){
 			query.setParameter(5, estado);		
 		}		
 		if((idDocumento!=null && !idDocumento.equals(""))){
 			query.setParameter(6, idDocumento);		
 		}		
 		List<Object[]> resultado = query.getResultList();		
 		for (Object[] row : resultado) {
 			ReporteDigitalizacionDTO reporte= new ReporteDigitalizacionDTO();
 			reporte.setCodigoCaso((Long) ((BigDecimal)row[0]).longValue());
 			reporte.setNombreCaso(row[1]==null?"":(String) row[1]);
 			reporte.setDocumento(row[2]==null?"":(String) row[2]);
 			reporte.setTipoDocumento(row[3]==null?"":(String) row[3]);
 			String reportDate1 = row[4]==null?"":df.format(row[4]);
 			String reportDate2 = row[5]==null?"":df.format(row[5]);
 			reporte.setFechaAsignacion(reportDate1);
 			reporte.setFechaDigitalizacion(reportDate2);
 			reporte.setEstadoDigitalizacion(row[6]==null?"":(String) row[6]); 			
 			reporte.setDescripcionDocumento(row[7]==null?"":(String) row[7]);
 			reporte.setNumeroFolios(row[8]==null?0:(Long) ((BigDecimal)row[8]).longValue());
 			reporte.setConsumo(row[9]==null?"":(String) row[9]);
 			reporte.setServicio(row[10]==null?"":(String) row[10]);
 			String nombreFuncionario= "";
 			nombreFuncionario= row[11]==null?"":(String) row[11];
 			reporte.setNombreFuncionario(nombreFuncionario); 
 			reporteDigitalizacionDTOs.add(reporte);
 		}

		return reporteDigitalizacionDTOs;
	}



	/**
	 * Metodo encargado de generar el reporte de los secretarios 
	 * que aceptaron o rechazaron un caso 
	 * @param Map filtros
	 * @return List<ReporteCasosAceptadosRechazadosSecretarioDTO>
	 * @throws ParseException 
	 */	
	@SuppressWarnings("unchecked")
	public List<ReporteCasosAceptadosRechazadosSecretarioDTO> getReporteCasosAceptadosRechazadosSecretario(Map filtros) {

		List<ReporteCasosAceptadosRechazadosSecretarioDTO> reporteCasosAceptadosRechazadosSecretarioDTOs = new ArrayList<>();
		Date fechaIncial = (Date) filtros.get("fechaInicial");
		Date fechaFinal = (Date) filtros.get("fechaFinal");
		String idPersona = (String) filtros.get("idPersona");
		String fechaInicio = UtilOperaciones.formatearFechaFormato(fechaIncial,
				UtilConstantes.FORMATO_FECHA_ANIO_MES_DIA);
		String fechaFin = UtilOperaciones.formatearFechaFormato(fechaFinal, UtilConstantes.FORMATO_FECHA_ANIO_MES_DIA);
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT ");
		nativeQuery.append("  c.id_Caso, "); // 0
		nativeQuery.append("  c.nombre AS caso, "); // 1
		nativeQuery.append("  cast(c.fecha_Radicacion as date) as fecha_Radicacion, "); // 2
		nativeQuery.append("  s.nombre AS servicio, "); // 3
		nativeQuery.append("  (SELECT ");
		nativeQuery.append("    d.nombre ");
		nativeQuery.append("  FROM DOMINIO d ");
		nativeQuery.append("  WHERE d.codigo = c.tipo_cuantia ");
		nativeQuery.append("  AND d.dominio = '" + UtilDominios.DOMINIO_TIPO_CUANTIA + "') ");
		nativeQuery.append("  AS tipo_cuantia, "); // 4
		nativeQuery.append("  m.nombre AS materia, "); // 5
		nativeQuery.append(
				"  CONCAT(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) AS secretario, "); // 6
		nativeQuery.append("  (SELECT ");
		nativeQuery.append("    d.nombre ");
		nativeQuery.append("  FROM DOMINIO d ");
		nativeQuery.append("  WHERE d.codigo = rpc.estado ");
		nativeQuery.append("  AND d.dominio = '" + UtilDominios.DOMINIO_ESTADO_ROL_PERSONA_CASO + "') ");
		nativeQuery.append("  AS pronunciamiento, "); // 7
		nativeQuery.append("  (SELECT ");
		nativeQuery.append("    STUFF((SELECT ");
		nativeQuery.append(
				"      ', ' + concat(pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) ");
		nativeQuery.append("    FROM ROL_PERSONA_CASO rp ");
		nativeQuery.append("    LEFT JOIN PERSONA pe ");
		nativeQuery.append("      ON rp.id_persona = pe.id_persona ");
		nativeQuery.append("    WHERE rp.id_caso = c.id_caso ");
		nativeQuery.append("    AND rp.estado_registro = '" + UtilDominios.ESTADO_REGISTRO_ACTIVO + "' ");
		nativeQuery.append("    AND rp.estado IN ('" + UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR + "','"
				+ UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO + "') ");
		nativeQuery.append("    AND rp.id_rol IN (SELECT DISTINCT ");
		nativeQuery.append("      rl.id_rol ");
		nativeQuery.append("    FROM PARAMETRO_SERVICIO_SORTEO pss ");
		nativeQuery.append("    INNER JOIN ROL rl ");
		nativeQuery.append("      ON pss.id_rol = rl.id_rol) ");
		nativeQuery.append("    FOR xml PATH ('')), 1, 1, '')) ");
		nativeQuery.append("  AS arbitros, "); // 8
		nativeQuery.append("  cast(erpc_not.fecha_de_asignacion as date) AS fecha_notificacion, "); // 9
		nativeQuery.append("  cast(erpc_ace.fecha_de_asignacion as date) AS fecha_aceptacion, "); // 10
		nativeQuery.append(CONSUMO);
		nativeQuery.append(", ");
		nativeQuery.append(SOLICITA_AMPARO);
		nativeQuery.append(", ");
		nativeQuery.append(CONCEDE_AMPARO);
		nativeQuery.append("FROM ROL_PERSONA_CASO rpc ");
		nativeQuery.append("     LEFT JOIN EVENTO_ROL_PERSONA_CASO erpc_not ");
		nativeQuery.append("       ON rpc.id_rol = erpc_not.id_rol ");
		nativeQuery.append("       AND rpc.id_persona = erpc_not.id_persona ");
		nativeQuery.append("       AND rpc.id_caso = erpc_not.id_caso ");
		nativeQuery.append(
				"       AND erpc_not.estado_asignado = '" + UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR + "' ");
		nativeQuery.append("     LEFT JOIN EVENTO_ROL_PERSONA_CASO erpc_ace ");
		nativeQuery.append("       ON rpc.id_rol = erpc_ace.id_rol ");
		nativeQuery.append("       AND rpc.id_persona = erpc_ace.id_persona ");
		nativeQuery.append("       AND rpc.id_caso = erpc_ace.id_caso ");
		nativeQuery.append(
				"       AND erpc_ace.estado_asignado = '" + UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO + "', ");
		nativeQuery.append("     ROL r, ");
		nativeQuery.append("     CASO c, ");
		nativeQuery.append("     MATERIA m, ");
		nativeQuery.append("     SERVICIO s, ");
		nativeQuery.append("     PERSONA p ");
		nativeQuery.append("WHERE rpc.id_rol = r.id_rol ");
		nativeQuery.append("AND rpc.id_caso = c.id_caso ");
		nativeQuery.append("AND c.id_materia = m.id_materia ");
		nativeQuery.append("AND c.id_servicio = s.id_servicio ");
		nativeQuery.append("AND rpc.id_persona = p.id_persona ");
		nativeQuery.append("AND r.nombre = '" + UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL + "' ");
		nativeQuery.append("AND CAST(c.fecha_Radicacion AS date) BETWEEN ?1 AND ?2 ");
		
		if (null != idPersona) {
			nativeQuery.append("AND p.id_persona = ?3 ");
		}
		
		nativeQuery.append("ORDER BY c.id_caso ASC ");
		
		Query query = em.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, fechaInicio);
		query.setParameter(2, fechaFin);
		
		if (null != idPersona) {
			query.setParameter(3, new Long(idPersona));
		}
		
		List<Object[]> resultado = query.getResultList();
		
		for (Object[] objects : resultado) {
			ReporteCasosAceptadosRechazadosSecretarioDTO dto = new ReporteCasosAceptadosRechazadosSecretarioDTO();
			dto.setCodigoCaso(((BigDecimal) objects[0]).longValue());
			dto.setNombreCaso(objects[1] != null ? (String) objects[1] : UtilConstantes.CADENA_VACIA);
			dto.setFechaRadicacionCaso(
					UtilOperaciones.formatearFechaFormato((Date) objects[2], UtilConstantes.FORMATO_FECHA_ANIO_MES_DIA));
			dto.setTipoCaso(objects[3] != null ? (String) objects[3] : UtilConstantes.CADENA_VACIA);
			dto.setTipoCuantia(objects[4] != null ? (String) objects[4] : UtilConstantes.CADENA_VACIA);
			dto.setMateria(objects[5] != null ? (String) objects[5] : UtilConstantes.CADENA_VACIA);
			dto.setNombreSecretario(objects[6] != null ? (String) objects[6] : UtilConstantes.CADENA_VACIA);
			dto.setPronunciamiento(objects[7] != null ? (String) objects[7] : UtilConstantes.CADENA_VACIA);
			dto.setArbitrosLista(objects[8] != null ? (String) objects[8] : UtilConstantes.CADENA_VACIA);
			dto.setFechaNotificacionNombramiento(objects[9] != null ? UtilOperaciones.formatearFechaFormato(
					(Date) objects[9], UtilConstantes.FORMATO_FECHA_ANIO_MES_DIA_HORA) : UtilConstantes.CADENA_VACIA);
			dto.setFechaAceptacionEncargo(objects[10] != null ? UtilOperaciones.formatearFechaFormato(
					(Date) objects[10], UtilConstantes.FORMATO_FECHA_ANIO_MES_DIA_HORA) : UtilConstantes.CADENA_VACIA);
			dto.setConsumo(objects[11] != null ? (String) objects[11] : UtilConstantes.CADENA_VACIA);
			dto.setConcedeAmparo(objects[13] != null ? (String) objects[13] : UtilConstantes.CADENA_VACIA);
			dto.setSolicitaAmparo(objects[12] != null ? (String) objects[12] : UtilConstantes.CADENA_VACIA);
			reporteCasosAceptadosRechazadosSecretarioDTOs.add(dto);
		}
 		
 		return reporteCasosAceptadosRechazadosSecretarioDTOs;

	}
	
	/**
	 * Metodo encargado de generar el reporte de los casos 
	 * que se han suspendido 
	 * @param filtros
	 * @return List<ReporteCasosSuspendidosDTO>
	 */	
	@SuppressWarnings("unchecked")
	public List<ReporteCasosSuspendidosDTO> getReporteCasosSuspendidos(Map filtros){
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    	String fechaIncial= (String) filtros.get("fechaInicial");
    	String fechaFinal= (String) filtros.get("fechaFinal");  
    	String codigoCaso= (String) filtros.get("codigoCaso");
 		List<Object[]> rows=new ArrayList<>();
 		List<ReporteCasosSuspendidosDTO> casosSuspendidos=new ArrayList<>(rows.size());
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT ");
		jpqlQuery.append(" c.id_caso, ");
		jpqlQuery.append(" c.nombre, ");
		jpqlQuery.append(" su.fecha_inicial, ");
		jpqlQuery.append(" su.fecha_final, ");
		jpqlQuery.append(" su.motivo, ");
		jpqlQuery.append(" CASE WHEN c.arbitraje_consumo = 1 THEN 'SI' ");
		jpqlQuery.append(" ELSE 'NO' END AS consumo, ");
		jpqlQuery.append(" (SELECT d.nombre FROM Dominio d WHERE d.codigo= c.etapa AND d.dominio = ?4) AS etapa, ");
		jpqlQuery.append(" (SELECT s.nombre FROM Servicio s WHERE s.id_servicio = c.id_servicio) AS servicio ");
		jpqlQuery.append("FROM Suspension su ");
		jpqlQuery.append("LEFT JOIN caso c ON su.id_caso = c.id_caso ");
		jpqlQuery.append("WHERE ((su.fecha_inicial BETWEEN ?2 AND ?3) OR (su.fecha_final BETWEEN ?2 AND ?3)) ");
		jpqlQuery.append("AND su.tipo IN (?1, ?5, ?6) ");
		
		if(codigoCaso != null) {
			jpqlQuery.append(" AND c.id_caso = ?7 ");
		}
		
		jpqlQuery.append("ORDER BY c.id_caso ASC ");
		
 		Query query = em.createNativeQuery(jpqlQuery.toString()); 		

 		query.setParameter(1, UtilDominios.TIPO_SUSPENSION_SUSPENSION_ARBITRAL);
		query.setParameter(2, fechaIncial);
		query.setParameter(3, fechaFinal);
		query.setParameter(4, UtilDominios.DOMINIO_ETAPA_CASO);
		query.setParameter(5, UtilDominios.TIPO_SUSPENSION_SUSPENSION_PREARBITRAL);
		query.setParameter(6, UtilDominios.TIPO_SUSPENSION_POR_REQUERIMIENTO);
		query.setParameter(7, codigoCaso);
		
 		rows=query.getResultList();

 		for (Object[] row : rows) {
 			ReporteCasosSuspendidosDTO reporte= new ReporteCasosSuspendidosDTO();
 			reporte.setCodigoCaso(Long.valueOf(row[0].toString()));
 			reporte.setNombreCaso((String) row[1]);
 			reporte.setFechaInicialSuspension(row[2]==null?"":df.format(row[2]));
 			reporte.setFechaFinalSuspension(row[3]==null?"":df.format(row[3]));

 			List<RolPersonaCaso> secretarios = manejadorRolPersonaCaso.consultarPersonasAsignadasCasoPorRol(Long.valueOf(row[0].toString()),UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
 			if(!secretarios.isEmpty()){
 				reporte.setSecretario(secretarios.get(0).getPersona().getNombreCompleto()); 				
 			} 			
 			
 			reporte.setMotivoSuspension(row[4]==null?"":(String)row[4]);
 			reporte.setConsumo((String)row[5]);
 			reporte.setEtapa((String) row[6]);
 			reporte.setServicio((String) row[7]);
 			casosSuspendidos.add(reporte);
 		}
 		
 		return casosSuspendidos;
 	}

	/**
	 * Metodo encargado de generar el reporte de los secretarios
	 * @param filtros
	 * @return List<ReporteSecretariosDTO>
	 */	
    @SuppressWarnings("unchecked")
    public List<ReporteSecretariosDTO> getReporteSecretarios(Map filtros){
    	
    	String idPersona= (String) filtros.get("idPersona");
    	String estado= (String) filtros.get("estado");
    	String estadoSecretario=null;
 		List<Object[]> rows=new ArrayList<>();
 		List<ReporteSecretariosDTO> secretarios=new ArrayList<>(rows.size());		
 		StringBuilder nativeQuery = new StringBuilder();
 		nativeQuery.append("SELECT DISTINCT CONCAT(p.primer_nombre_o_razon_social,' ', p.segundo_nombre,' ',p.primer_apellido,' ',p.segundo_apellido) AS nombre, ");
 		nativeQuery.append("(SELECT STUFF((SELECT numero+' ' FROM TELEFONO WHERE id_persona = p.id_persona for xml path('')),1,1,' ')) AS telefonos, ");
 		nativeQuery.append("(SELECT STUFF((SELECT ' '+ CONCAT(ubi.direccion,' ', ");
 		nativeQuery.append("(SELECT STUFF((SELECT ' '+zog.nombre FROM ZONA_GEOGRAFICA zog WHERE zog.id_zona_geografica = ubi.id_zona_geografica for xml path('')),1,1,''))) ");
 		nativeQuery.append(" FROM UBICACION ubi WHERE ubi.id_persona = p.id_persona for xml path('')),1,1,' ')) AS direcciones, ");
 		nativeQuery.append("(SELECT TOP 1 direccion FROM CORREO_ELECTRONICO cc ");
 		nativeQuery.append(" WHERE cc.id_persona = p.id_persona ");
 		nativeQuery.append(" ORDER BY case when tipo = ?1 then 1 ");
 		nativeQuery.append("               when tipo = ?2 then 2 ");
 		nativeQuery.append("               else 3 ");
 		nativeQuery.append("          end asc) AS correo, ");
 		nativeQuery.append("rp.estado_registro ");
 		nativeQuery.append("FROM PERSONA p ");
 		nativeQuery.append("LEFT JOIN ROL_PERSONA rp ON  p.id_persona = rp.id_persona ");
 		nativeQuery.append("LEFT JOIN  ROL r ON r.id_rol = rp.id_rol ");
 		nativeQuery.append("LEFT JOIN UBICACION u ON p.id_persona = u.id_persona ");
 		nativeQuery.append("LEFT JOIN ZONA_GEOGRAFICA zg ON zg.id_zona_geografica = u.id_zona_geografica ");
 		nativeQuery.append("LEFT JOIN TELEFONO t ON t.id_persona = p.id_persona ");
 		nativeQuery.append("LEFT JOIN CORREO_ELECTRONICO c ON c.id_persona = p.id_persona ");
 		nativeQuery.append("WHERE r.nombre = ?3 ");
 		if((idPersona!=null && !idPersona.equals(""))){
 			nativeQuery.append("AND p.id_persona = ?4 ");
	 	 			
		}
		if(estado!=null && !estado.equals("")){
			nativeQuery.append("AND rp.estado_registro = ?5 "); 	 			
		}
 		nativeQuery.append("ORDER BY nombre ASC ");	
 		Query query = em.createNativeQuery(nativeQuery.toString());
 		query.setParameter(1, UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
 		query.setParameter(2, UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_SECUNDARIO);
 		query.setParameter(3, UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
 		query.setParameter(4, idPersona);
 		query.setParameter(5, estado);
 		rows=query.getResultList(); 		
 		
 		for (Object[] row : rows) {

 			ReporteSecretariosDTO reporte= new ReporteSecretariosDTO();
 			String nombreSecretario= (String) row[0];
 			reporte.setNombreSecretario(nombreSecretario);
 			reporte.setDireccion((String) row[2]);
 			reporte.setTelefono((String)row[1]);
 			estadoSecretario=(String) row[4];
 			if(estadoSecretario.equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)){
 	 			reporte.setEstado("ACTIVO"); 				
 			}else if (estadoSecretario.equals(UtilDominios.ESTADO_REGISTRO_INACTIVO)) {
 				reporte.setEstado("INACTIVO"); 				
			}
 			reporte.setCorreoElectronico((String)row[3]);
 			secretarios.add(reporte);
 		}
 		
 		return secretarios;
	}

	/**
	 * Retorna el correo principal si este exixte o uno correo secundario de lo
	 * contrario
	 * 
	 * @param correos
	 * @return
	 */
	public String correoPrincipal(List<CorreoElectronico> correos) {
		String correo = " ";
		for (int i = 0; i < correos.size(); i++) {
			if (UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL.equals(correos.get(i).getTipo())) {
				correo = correos.get(i).getDireccion();
			}
		}
		if (correo.equals(" ") && correos.size() > 0)
			correo = correos.get(0).getDireccion();

		return correo;
	}
    
    
    /**
	 * Metodo encargado de generar el reporte de las audiencias ordenadas por el Id del Caso
	 * @param filtros
	 * @return List<ReporteAudienciaDTO>
	 */	

    public List<ReporteAudienciaDTO> getDatosReporteAudiencia(Map filtros){	


		String codigoCaso = nvl(filtros.get("codigoCaso"),"").toString();
		String nombreCaso = nvl(filtros.get("nombreCaso"),"").toString();
		Date fechaIni = filtros.get("fechaIni") == null?null:(Date)filtros.get("fechaIni");
		Date fechaFin = filtros.get("fechaFin") == null?null:(Date)filtros.get("fechaFin");
		String abogado = filtros.get("abogado").toString();
		String secretario = filtros.get("secretario").toString();	
		String arbitro = filtros.get("arbitro").toString();
		String estado = nvl(filtros.get("estado"),"0").toString();
		String tipoAudiencia = nvl(filtros.get("tipoAudiencia"),"0").toString();		
		List<Object[]> rowsArb;
		List<Object[]> rowsCaso;
		StringBuilder jpqlQueryArb = new StringBuilder();
		StringBuilder jpqlQueryCaso = new StringBuilder();
		String listCasos="";

		if(!arbitro.equals("0")){
			jpqlQueryCaso.append(" SELECT c.idCaso, c.nombre ");
			jpqlQueryCaso.append(" FROM Caso c");
			jpqlQueryCaso.append(" LEFT JOIN c.rolPersonaCasoList rpcArb");
			jpqlQueryCaso.append(" WHERE ");
			jpqlQueryCaso.append(" rpcArb.persona.idPersona = "+arbitro+"  ");
			jpqlQueryCaso.append(" ORDER BY c.idCaso ASC ");
			
			Query query1 = em.createQuery(jpqlQueryCaso.toString());			
			rowsCaso = query1.getResultList();
			Long dato=null;
			for (Object[] caso : rowsCaso){
				dato= (Long) caso[0];
				listCasos=listCasos.concat(Long.toString(dato));
				listCasos=listCasos.concat(",");				
			}
			listCasos= listCasos.substring(0, listCasos.length()-1);

		}

		jpqlQueryArb.append(" SELECT c.idCaso,");
		jpqlQueryArb.append("  	  arb.primerNombreORazonSocial,");
		jpqlQueryArb.append("  	  arb.segundoNombre,");
		jpqlQueryArb.append("  	  arb.primerApellido,");		
		jpqlQueryArb.append("  	  arb.segundoApellido");
		jpqlQueryArb.append(" FROM Caso c");
		jpqlQueryArb.append(" LEFT JOIN c.rolPersonaCasoList rpcArb");
		jpqlQueryArb.append(" LEFT JOIN rpcArb.persona arb");
		jpqlQueryArb.append(" LEFT JOIN rpcArb.rol rolArb");
		jpqlQueryArb.append(" WHERE ");
		jpqlQueryArb.append(" (rolArb.nombre = :nombreRolArb OR rolArb.nombre IS NULL) ");

		if(!arbitro.equals("0")){
			jpqlQueryArb.append("and c.idCaso IN (" + listCasos + ") ");

		}

		jpqlQueryArb.append(" ORDER BY c.idCaso ASC ");

		Query query = em.createQuery(jpqlQueryArb.toString());
		query.setParameter("nombreRolArb", com.ccb.simasc.transversal.utilidades.UtilDominios.ROL_PERSONA_ARBITRO);		
		
		rowsArb = query.getResultList();


		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT c.idCaso,");
		jpqlQuery.append("  	  c.nombre,");
		jpqlQuery.append("  	  c.fechaRadicacion,");
		jpqlQuery.append("  	  a.tipoAudiencia,");
		jpqlQuery.append("  	  a.horaInicio,");
		jpqlQuery.append("  	  a.estado,");		
		jpqlQuery.append("  	  a.observaciones ");
		jpqlQuery.append(" FROM Audiencia a");
		jpqlQuery.append(" LEFT JOIN a.caso c ");
		jpqlQuery.append(" LEFT JOIN c.idServicio s ");
		jpqlQuery.append(" WHERE s.tipo = :tipoServicio ");
		
		jpqlQuery.append(" AND");	

		
		if(!codigoCaso.equals("") && !codigoCaso.equals("0")){
			jpqlQuery.append(" c.idCaso = :idCaso AND");
			
		}
		if(!nombreCaso.equals(""))
			jpqlQuery.append(" LOWER(c.nombre) LIKE :nombreCaso AND");
		

		if(fechaIni != null && fechaFin != null)
			jpqlQuery.append(" a.horaInicio BETWEEN :fechaInicial AND :fechaFinal AND");

		if(!estado.equals("0"))
			jpqlQuery.append(" a.estado = :estado AND");

		if(!tipoAudiencia.equals("0"))
			jpqlQuery.append(" a.tipoAudiencia = :tipoAudiencia AND");

		jpqlQuery.delete(jpqlQuery.length()-4, jpqlQuery.length());
		jpqlQuery.append(" ORDER BY a.horaInicio ASC ");


		Query query2 = em.createQuery(jpqlQuery.toString());
		
		query2.setParameter(Servicio.ENTIDAD_SERVICIO_TIPO,UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA );
		
		if(!codigoCaso.equals("") && !codigoCaso.equals("0"))
			query2.setParameter(Caso.ENTIDAD_CASO_PK ,Long.valueOf(codigoCaso));

		if(!nombreCaso.equals(""))
			query2.setParameter("nombreCaso","%" + nombreCaso.toLowerCase() + "%");

		if(fechaIni != null && fechaFin != null){
			query2.setParameter("fechaInicial",new Date(fechaIni.getTime()),TemporalType.DATE);
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaFin);
			cal.add(Calendar.DATE, 1); 
			query2.setParameter("fechaFinal",new Date(cal.getTime().getTime()),TemporalType.DATE);
		}

		if(!estado.equals("0"))
			query2.setParameter(Audiencia.ENTIDAD_AUDIENCIA_ESTADO,estado );

		if(!tipoAudiencia.equals("0"))
			query2.setParameter(Audiencia.ENTIDAD_AUDIENCIA_TIPO_AUDIENCIA,tipoAudiencia );

		List<Object[]> listObj = query2.getResultList();
		List<ReporteAudienciaDTO> datosReporte = new ArrayList<>();
		SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy/M/dd");
		for (Object[] campo : listObj) {
			boolean equalSecretario=true;
			boolean equalAbogado=true;
			Persona secretarioc = manejadorRolPersonaCaso.consultarSecretarioDelCaso((Long)campo[0]) == null?null:manejadorRolPersonaCaso.consultarSecretarioDelCaso((Long)campo[0]).getPersona();
			Persona abogadoc = manejadorRolPersonaCaso.consultarAbogadoDelCaso((Long)campo[0]) == null?null:manejadorRolPersonaCaso.consultarAbogadoDelCaso((Long)campo[0]).getPersona();
			
			if(!secretario.equals("0"))
				if(secretarioc != null){
					if(!secretarioc.getIdPersona().toString().equals(secretario))
						equalSecretario = false;
				}else{
					equalSecretario = false;
				} 
					
			
			if(!abogado.equals("0"))
				if(abogadoc != null){
					if(!abogadoc.getIdPersona().toString().equals(abogado))
						equalAbogado=false;
				}else{
					equalAbogado=false;
				}
					
					
			
			if(equalSecretario && equalAbogado){
					ReporteAudienciaDTO dto1 = new ReporteAudienciaDTO();
					dto1.setCodigoCaso(campo[0].toString());
					dto1.setNombreCaso(nvl(campo[1],"").toString());
					dto1.setFechaRadicacionCaso(campo[2]==null?"":formatofecha.format((Date)campo[2]));
					dto1.setTipoAudiencia((campo[3] == null?"":fachadaDominios.getNombreDominio(UtilDominios.DOMINIO_TIPO_AUDIENCIA,campo[3].toString())));
					dto1.setFechaAudiencia(campo[4]==null?"":UtilOperaciones.formatearFechaHora((Date)campo[4]));
					dto1.setNombreSecretario(manejadorRolPersonaCaso.consultarSecretarioDelCaso((Long)campo[0]) == null?null:manejadorRolPersonaCaso.consultarSecretarioDelCaso((Long)campo[0]).getPersona().getNombreCompleto());
					
					String arbitros="";
					String arbitroSTR="";
					for (Object[] rowA : rowsArb) {
						if(campo[0].equals(rowA[0])){
							if (rowA[1] != null) {
								arbitroSTR = (String) rowA[1] + " ";
							}
							if (rowA[2] != null) {
								arbitroSTR = arbitroSTR.concat((String) rowA[2] + " ");
							}
							if (rowA[3] != null) {
								arbitroSTR = arbitroSTR.concat((String) rowA[3] + " ");
							}
							if (rowA[4] != null) {
								arbitroSTR = arbitroSTR.concat((String) rowA[4] + " ");
							}
							arbitros=arbitros.concat(arbitroSTR);
							arbitros=arbitros.concat(",");
						}
					}
					if(!arbitros.equals("")){
		 				arbitros=arbitros.substring(0, arbitros.length()-1);
		 			}
					dto1.setNombreArbitro(arbitros);
					dto1.setAbogado(manejadorRolPersonaCaso.consultarAbogadoDelCaso((Long)campo[0]) == null?null:manejadorRolPersonaCaso.consultarAbogadoDelCaso((Long)campo[0]).getPersona().getNombreCompleto());
					dto1.setEstado((campo[5] == null?"":fachadaDominios.getNombreDominio(UtilDominios.DOMINIO_ESTADO_AUDIENCIA,campo[5].toString())));
					dto1.setObservaciones(nvl(campo[6],"").toString());
					
					datosReporte.add(dto1);						
			}
		}

		return datosReporte;	

	}
    
    public boolean validarFiltro(String key, Map<String, Object> filtros) {
    	return filtros.get(key) != null && !filtros.get(key).toString().isEmpty()
				&& !("0").equals(filtros.get(key).toString());
    }

    /**
     * 
     * 
     * @param filtros
     * @return
     */
	public List<ReporteAudienciaDTO> getDatosReporteAudienciaNuevo(Map<String, Object> filtros) {
		List<ReporteAudienciaDTO> datosReporteAudiencia = new ArrayList<>();
		List<String> parametros = new ArrayList<>();		
		
		boolean filtroAbogado = filtros.get("abogado") != null && !filtros.get("abogado").toString().isEmpty()
				&& !("0").equals(filtros.get("abogado").toString());
		
		boolean filtroArbitro = filtros.get("arbitro") != null && !filtros.get("arbitro").toString().isEmpty()
				&& !("0").equals(filtros.get("arbitro").toString());
				
		boolean filtroSecre = filtros.get("secretario") != null && !filtros.get("secretario").toString().isEmpty()
				&& !("0").equals(filtros.get("secretario").toString());
		
		boolean filtroFechas = ((filtros.get("fechaIni") != null && !filtros.get("fechaIni").toString().isEmpty()
				&& !("0").equals(filtros.get("fechaIni").toString()))
				&& (filtros.get("fechaFin") != null && !filtros.get("fechaFin").toString().isEmpty()
						&& !("0").equals(filtros.get("fechaFin").toString())));  
		
		boolean filtroCodigoCaso = filtros.get("codigoCaso") != null && !filtros.get("codigoCaso").toString().isEmpty();
		boolean filtroNombreCaso = filtros.get("nombreCaso") != null && !filtros.get("nombreCaso").toString().isEmpty();
		boolean filtroTipoAudiencia = filtros.get("tipoAudiencia") != null && !filtros.get("tipoAudiencia").toString().isEmpty();
		boolean filtroEstado = filtros.get("estado") != null && !filtros.get("estado").toString().isEmpty();	

		StringBuilder nativeQuery = new StringBuilder();

		nativeQuery.append("SELECT distinct ");
		nativeQuery.append("       au.id_audiencia, ");
		nativeQuery.append("       au.id_caso, ");
		nativeQuery.append("       ca.nombre, ");
		nativeQuery.append("       cast(ca.fecha_radicacion as date) as fecha_radicacion, ");
		nativeQuery.append("       (select dta.nombre ");
		nativeQuery.append("          from DOMINIO dta ");
		nativeQuery.append("         where dta.codigo = au.tipo_audiencia ");
		nativeQuery.append("           and dta.dominio = '" + UtilDominios.DOMINIO_TIPO_AUDIENCIA + "') as tipo_audiencia,");
		nativeQuery.append("       au.hora_inicio as fecha_audiencia,");
		nativeQuery.append("       (select ");
		nativeQuery.append("			stuff(( ");
		nativeQuery.append("				select ', ' + concat (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) ");
		nativeQuery.append("				  from ROL_PERSONA_CASO rp ");
		nativeQuery.append("				  left join PERSONA pe on rp.id_persona=pe.id_persona ");
		nativeQuery.append("				 where rp.id_caso=ca.id_caso ");
		nativeQuery.append("   				   and rp.id_rol=(select id_rol from rol where nombre='" + UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL + "') ");
		nativeQuery.append("            for");
		nativeQuery.append("				xml path('') ");
		nativeQuery.append("			), 1, 1, '')) as secretario, ");
		nativeQuery.append("	   (select ");
		nativeQuery.append("			stuff(( ");
		nativeQuery.append("				select ', ' + concat (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) ");
		nativeQuery.append("                  from ROL_PERSONA_CASO rp");
		nativeQuery.append("		  		  left join PERSONA pe on rp.id_persona=pe.id_persona");
		nativeQuery.append("		         where rp.id_caso=ca.id_caso ");
		nativeQuery.append("and rp.estado_registro = '" + UtilDominios.ESTADO_REGISTRO_ACTIVO + "' ");
		nativeQuery.append("and rp.estado in ('" + UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR + "','" + UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO + "') ");
		nativeQuery.append("            	   and rp.id_rol in (select DISTINCT rl.id_rol from PARAMETRO_SERVICIO_SORTEO pss inner join ROL rl on pss.id_rol = rl.id_rol) ");
		nativeQuery.append("			for");
		nativeQuery.append("				xml path('')");
		nativeQuery.append("			), 1, 1, '')) as arbitros,");
		nativeQuery.append("	   (select ");
		nativeQuery.append("			stuff(( ");
		nativeQuery.append("				select ', ' + concat (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) ");
		nativeQuery.append("				  from ROL_PERSONA_CASO rp ");
		nativeQuery.append("				  left join PERSONA pe on rp.id_persona=pe.id_persona ");
		nativeQuery.append("			     where rp.id_caso=ca.id_caso ");
		nativeQuery.append("			     and rp.estado_registro = '" + UtilDominios.ESTADO_REGISTRO_ACTIVO + "' ");		
		nativeQuery.append("				   and rp.id_rol=(select id_rol from rol where nombre='ABO') ");
		nativeQuery.append("			for ");
		nativeQuery.append("				xml path('') ");
		nativeQuery.append("	   		), 1, 1, '')) as abogado, ");
		nativeQuery.append("       (select dta.nombre ");
		nativeQuery.append("          from DOMINIO dta ");
		nativeQuery.append("         where dta.codigo = au.estado ");
		nativeQuery.append("       	   and dominio = '" + UtilDominios.DOMINIO_ESTADO_AUDIENCIA + "') as estado_audiencia, ");
		nativeQuery.append("	   au.observaciones, ");
		nativeQuery.append("       CASE WHEN ca.arbitraje_consumo = 1 THEN 'SI'" );
		nativeQuery.append("       ELSE 'NO' END AS consumo,");
		nativeQuery.append("       (SELECT s.nombre FROM SERVICIO s ");
		nativeQuery.append("         WHERE s.id_servicio = ca.id_servicio) AS servicio");
		nativeQuery.append("  FROM audiencia au ");
		nativeQuery.append("  LEFT JOIN CASO ca on au.id_caso = ca.id_caso ");
		nativeQuery.append("  LEFT JOIN SERVICIO s on ca.id_servicio = s.id_servicio ");
		if (filtroAbogado) {
			nativeQuery.append("  LEFT JOIN ROL_PERSONA_CASO rpAbo on ca.id_caso = rpAbo.id_caso ");
		}
		if (filtroArbitro) {
			nativeQuery.append("  LEFT JOIN ROL_PERSONA_CASO rpArb on ca.id_caso = rpArb.id_caso ");
			
		}
		if (filtroSecre) {
			nativeQuery.append("  LEFT JOIN ROL_PERSONA_CASO rpSecre on ca.id_caso = rpSecre.id_caso ");
		}
		if(filtroAbogado || filtroArbitro || filtroSecre){
			String condiciones = "";
			nativeQuery.append("  LEFT JOIN ROL rol on ");
			if (filtroAbogado) condiciones = condiciones+" rol.id_rol = rpAbo.id_rol OR";
			if (filtroArbitro) condiciones = condiciones+" rol.id_rol = rpArb.id_rol OR";
			if (filtroSecre) condiciones = condiciones+" rol.id_rol = rpSecre.id_rol OR";
			nativeQuery.append(condiciones.substring(0, condiciones.length()-3));
		}
		
		nativeQuery.append(" WHERE au.estado_registro = '" + UtilDominios.ESTADO_REGISTRO_ACTIVO + "' ");
		nativeQuery.append(" AND s.tipo in (?12, ?13) ");
		
		if (filtroFechas) {
			nativeQuery.append(" AND cast(au.hora_inicio as date) BETWEEN ?1 and ?2 ");
		}				
		if (filtroCodigoCaso) {
			nativeQuery.append("AND au.id_caso = ?3 ");		
		}			
		if (filtroNombreCaso) {
			nativeQuery.append(" AND ca.nombre like ?4 ");		
		}
		if (filtroAbogado) {
			nativeQuery.append(" AND rpAbo.id_persona = ?5 ");	
			parametros.add("ABO");
		}
		if (filtroArbitro) {
			nativeQuery.append(" AND rpArb.id_persona = ?6 ");	
			nativeQuery.append(" AND rpArb.estado NOT IN( ?10 , ?11  )");	
			parametros.add("ARB");
		}
		if (filtroSecre) {
			nativeQuery.append(" AND rpSecre.id_persona = ?7 ");	
			parametros.add("SEC");
		}
		if(filtroAbogado || filtroArbitro || filtroSecre){
			nativeQuery.append(" AND rol.nombre ").append(UtilConsultasSQL.clausulaInSQLStrings(parametros));	
		}
		if (filtroTipoAudiencia) {
			nativeQuery.append(" and au.tipo_audiencia = ?8 ");		
		}
		if (filtroEstado) {
			nativeQuery.append(" and au.estado = ?9 ");
		}
		nativeQuery.append(" ORDER BY id_caso ");
		
		Query query = em.createNativeQuery(nativeQuery.toString());
		
		if (filtroFechas) {
			Date fechaIni = (Date) filtros.get("fechaIni");
			Date fechaFin = (Date) filtros.get("fechaFin");
			query.setParameter(1, new Date(fechaIni.getTime()), TemporalType.DATE);
			query.setParameter(2, new Date(fechaFin.getTime()), TemporalType.DATE);
		}
		
		// --------------------------------------------
		if (filtroCodigoCaso)
			query.setParameter(3, filtros.get("codigoCaso"));
		if (filtroNombreCaso)
			query.setParameter(4, "%".concat(filtros.get("nombreCaso").toString()).concat("%"));		
		if (filtroAbogado)
			query.setParameter(5, filtros.get("abogado"));
		if (filtroArbitro)
			query.setParameter(6, filtros.get("arbitro"));
		if (filtroSecre)
			query.setParameter(7, filtros.get("secretario"));	
		if (filtroTipoAudiencia)
			query.setParameter(8, filtros.get("tipoAudiencia"));			
		if (filtroEstado)
			query.setParameter(9, filtros.get("estado"));
		
		query.setParameter(10, UtilDominios.ESTADO_ROL_PERSONA_CASO_RECHAZADO);
		query.setParameter(11, UtilDominios.ESTADO_ROL_PERSONA_CASO_INACTIVO);
		query.setParameter(12, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(13, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);
		//---------------------------------------------

		@SuppressWarnings("unchecked")
		List<Object[]> listObj = query.getResultList();
		SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy/M/dd");
		
		for (Object[] object : listObj) {
			ReporteAudienciaDTO reporteAudienciaDTO = new ReporteAudienciaDTO();
			reporteAudienciaDTO.setCodigoCaso(object[1] != null ? object[1].toString() : null);
			reporteAudienciaDTO.setNombreCaso(object[2] != null ? object[2].toString() : null);
			reporteAudienciaDTO
					.setFechaRadicacionCaso(object[3] != null ? formatofecha.format((Date) object[3]) : null);
			reporteAudienciaDTO.setTipoAudiencia(object[4] != null ? object[4].toString() : null);
			reporteAudienciaDTO
					.setFechaAudiencia(object[5] != null ? UtilOperaciones.formatearFechaHora((Date) object[5]) : null);
			reporteAudienciaDTO.setNombreSecretario(object[6] != null ? object[6].toString() : null);
			reporteAudienciaDTO.setNombreArbitro(object[7] != null ? object[7].toString() : null);
			reporteAudienciaDTO.setAbogado(object[8] != null ? object[8].toString() : null);
			reporteAudienciaDTO.setEstado(object[9] != null ? object[9].toString(): null);
			reporteAudienciaDTO.setObservaciones(object[10] != null ? object[10].toString() : null);
			reporteAudienciaDTO.setConsumo(object[11] != null ? object[11].toString() : null);
			reporteAudienciaDTO.setServicio(object[12] != null ? object[12].toString() : null);
			
			datosReporteAudiencia.add(reporteAudienciaDTO);
		}

		return datosReporteAudiencia;
	}

    /**
	 * Metodo encargado de generar el reporte de las Arbitros por caso, lista todos los árbitros que estan
	 * asignados a un Caso, con información del caso y de cada árbitro
	 * @param filtros
	 * @return List<ReporteCasosPorArbitroDTO>
	 */	
    
    
	public List<ReporteCasosPorArbitroDTO> getReporteCasosPorArbitro(Map filtros){	
		String arbitro = filtros.get("arbitro").toString();
		String materiaSeleccionada = filtros.get("materiaSeleccionada").toString();
		String pronunciamientoSeleccionado = filtros.get("pronunciamientoSeleccionado")==null?"0":filtros.get("pronunciamientoSeleccionado").toString();
		String tipoNombramientoSeccionado = filtros.get("tipoNombramientoSeccionado")==null?"0":filtros.get("tipoNombramientoSeccionado").toString();

		StringBuilder jpqlQuery = new StringBuilder(); 
		
		String asignacion = " (SELECT MAX(fecha_de_asignacion) FROM EVENTO_ROL_PERSONA_CASO WHERE estado_asignado = ?9 "
				+" and id_persona = pe.id_persona  AND id_caso = c.id_caso  AND estado_registro = ?1 ) AS fechaDesignacion, ";
		
		String comunicacion = " (SELECT MAX(fecha_de_asignacion) FROM EVENTO_ROL_PERSONA_CASO WHERE estado_asignado = ?10 "
				+" and id_persona = pe.id_persona  AND id_caso = c.id_caso AND estado_registro = ?1 ) AS fechaComunicacion, ";
		
		String tipoPronunciamiento = " (SELECT nombre FROM DOMINIO WHERE dominio = ?8 AND codigo= pr.pronunciamiento ) AS pronunciamiento, "
				+" pr.fecha AS fechaPronunciamiento ";
		
		
		jpqlQuery.append(" SELECT ");
		jpqlQuery.append(" c.id_caso AS codigoCaso, ");
		jpqlQuery.append(" s.nombre AS tipoCaso, ");
		jpqlQuery.append(" c.nombre AS nombreCaso, ");
		jpqlQuery.append(" c.fecha_radicacion  AS fechaRadicacionCaso, ");
		jpqlQuery.append(" (SELECT nombre FROM DOMINIO WHERE dominio = ?5 AND codigo = c.estado_caso) AS estadoCaso, ");
		jpqlQuery.append(" (SELECT nombre FROM DOMINIO WHERE dominio = ?6 AND codigo = c.tipo_cuantia) AS cuantia, ");
		jpqlQuery.append(" m.nombre AS materia, ");
		jpqlQuery.append(" concat (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) AS arbitro, ");
		jpqlQuery.append(" (SELECT nombre FROM DOMINIO WHERE dominio = ?7 AND codigo = rpc.metodo_nombramiento)  AS tipoNombramiento, ");
		jpqlQuery.append(asignacion);
		jpqlQuery.append(comunicacion);
		jpqlQuery.append(tipoPronunciamiento);
		jpqlQuery.append(", ");
		jpqlQuery.append(CONSUMO);
		jpqlQuery.append(", ");
		jpqlQuery.append(SOLICITA_AMPARO);
		jpqlQuery.append(", ");
		jpqlQuery.append(CONCEDE_AMPARO);
		jpqlQuery.append(" FROM ROL_PERSONA_CASO rpc ");
		jpqlQuery.append(" INNER JOIN CASO c ON rpc.id_caso = c.id_caso ");
		jpqlQuery.append(" INNER JOIN SERVICIO s ON s.id_servicio = c.id_servicio and s.tipo in (?16, ?17) ");
		jpqlQuery.append(" INNER JOIN PERSONA pe ON pe.id_persona = rpc.id_persona ");
		jpqlQuery.append(" LEFT JOIN MATERIA m ON m.id_materia = c.id_materia AND m.estado_registro = ?1 ");
		jpqlQuery.append(" LEFT JOIN PRONUNCIAMIENTO pr ON pr.id_pronunciamiento = rpc.id_pronunciamiento AND pr.estado_registro = ?1 ");
		jpqlQuery.append(" WHERE c.estado_registro = ?1 ");
		jpqlQuery.append(" AND pe.estado_registro = ?1 ");
		jpqlQuery.append(" AND rpc.estado_registro = ?1 ");
		jpqlQuery.append(" AND rpc.tipo_nombramiento = ?11 ");
		jpqlQuery.append(" AND c.estado_caso != ?4 ");
		jpqlQuery.append(" AND cast( c.fecha_radicacion as DATE)  BETWEEN  ");
		jpqlQuery.append(" cast( ?2 as DATE) AND cast( ?3 as DATE)  ");
		jpqlQuery.append(" AND rpc.id_rol in (select DISTINCT rl.id_rol from PARAMETRO_SERVICIO_SORTEO pss inner join ROL rl on pss.id_rol = rl.id_rol) ");
		
		if(!arbitro.equals("0"))
			jpqlQuery.append("  AND pe.id_persona = ?12");		
		
		if(!materiaSeleccionada.equals("0"))
			jpqlQuery.append(" AND m.id_materia = ?13 ");
		
		if(!tipoNombramientoSeccionado.equals("0"))
			jpqlQuery.append(" AND rpc.metodo_nombramiento = ?14");
		
		if (!pronunciamientoSeleccionado.equals("0"))
			jpqlQuery.append(" AND pr.pronunciamiento = ?15 ");

		jpqlQuery.append(" ORDER BY c.id_caso ");
		
		Query query = em.createNativeQuery(jpqlQuery.toString(), ReporteCasosPorArbitroDTO.class);
		
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2,  filtros.get("fechaIni"));
		query.setParameter(3,  filtros.get("fechaFin"));
		query.setParameter(4, UtilConstantes.ESTADO_CASO_EN_CREACION);
		query.setParameter(5, UtilDominios.DOMINIO_ESTADO_CASO);
		query.setParameter(6, UtilDominios.DOMINIO_TIPO_CUANTIA);
		query.setParameter(7, UtilDominios.METODOS_DE_NOMBRAMIENTO);
		query.setParameter(8, UtilDominios.DOMINIO_TIPO_PRONUNCIAMIENTO);
		query.setParameter(9, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		query.setParameter(10, UtilDominios.ESTADO_ROL_PERSONA_CASO_COMUNICADO);
		query.setParameter(11, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(12, Long.valueOf(arbitro));
		query.setParameter(13, Long.valueOf(materiaSeleccionada));
		query.setParameter(14, tipoNombramientoSeccionado);
		query.setParameter(15, pronunciamientoSeleccionado);
		query.setParameter(16, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(17, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);

		return query.getResultList();
	}

	/**
	 * Metodo encargado de generar el reporte de las Arbitros por caso, lista todos los árbitros que estan
	 * asignados a un Caso, adicionalmente la disponibilidad para sorteo en la materia del caso
	 * @param filtros
	 * @return List<ReporteDeArbitroDTO>
	 */	
	@SuppressWarnings("unchecked")
	public List<ReporteDeArbitroDTO> getDatosReporteArbitros(Map filtros){	

		String arbitro = nvl(filtros.get("arbitro"),UtilConstantes.CADENA_VACIA).toString();
		String tipoCasoCodigo = nvl(filtros.get("tipoCasoCodigo"),UtilConstantes.CADENA_VACIA).toString();
		String estadoCodigo = nvl(filtros.get("estadoCodigo"),UtilConstantes.CADENA_VACIA).toString();
		String motivoCodigo = nvl(filtros.get("motivoCodigo"),UtilConstantes.CADENA_VACIA).toString();
		String tipoListaCodigo = nvl(filtros.get("tipoListaCodigo"),UtilConstantes.CADENA_VACIA).toString();
		String materiaSeleccionada = filtros.get("materiaSeleccionada").toString();
		String disponibilidad = UtilConstantes.CADENA_VACIA;
		if (!motivoCodigo.equals(UtilConstantes.CADENA_VACIA) && motivoCodigo.equals(UtilDominios.ESTADO_DESIGNADO_EN_CASO)) {
			motivoCodigo = UtilDominios.ESTADO_ARBITROS_HABILITADO;
			disponibilidad = UtilDominios.ESTADO_PERSONA_SORTEO_INACTIVO;
		}
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT DISTINCT concat (p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) AS nombre, ");
		nativeQuery.append("                 dr.nombre                                                                                                       AS rol, ");
		nativeQuery.append("                 de.nombre                                                                                                       AS estado, ");
		nativeQuery.append("                 CASE emd.estado ");
		nativeQuery.append("                                 WHEN '"+ UtilDominios.DOMINIO_ESTADO_NO_SORTEABLE + "' ");
		nativeQuery.append("                                 THEN ");
		nativeQuery.append("                                                 CASE emd.id_motivo_estado ");
		nativeQuery.append("                                                                 WHEN '"+ UtilDominios.ESTADO_ARBITROS_HABILITADO + "' ");
		nativeQuery.append("                                                                 THEN dms.nombre ");
		nativeQuery.append("                                                                 ELSE dm.nombre ");
		nativeQuery.append("                                                 END ");
		nativeQuery.append("                                 ELSE '' ");
		nativeQuery.append("                 END                  AS motivo   , ");
		nativeQuery.append("                 s.nombre             AS tipoCaso , ");
		nativeQuery.append("                 ISNULL(l.nombre, '') AS tipoLista, ");
		nativeQuery.append("                 m.nombre             AS materia  , ");
		nativeQuery.append("                 CASE epr.id_motivo ");
		nativeQuery.append("                                 WHEN '"+ UtilDominios.ESTADO_ARBITROS_HABILITADO + "' ");
		nativeQuery.append("                                 THEN psm.estado_para_sorteo ");
		nativeQuery.append("                                 ELSE '' ");
		nativeQuery.append("                 END AS disponibilidad ");
		nativeQuery.append(" FROM            PERSONA p ");
		nativeQuery.append("                 INNER JOIN ROL_PERSONA rp ");
		nativeQuery.append("                 ON              p.id_persona = rp.id_persona ");
		nativeQuery.append("                 INNER JOIN ROL r ");
		nativeQuery.append("                 ON              r.id_rol        = rp.id_rol ");
		nativeQuery.append("                 AND             r.tipo_servicio in (?3, ?14) ");
		nativeQuery.append("                 INNER JOIN dominio dr ");
		nativeQuery.append("                 ON              r.nombre = dr.codigo ");
		nativeQuery.append("                 AND             dr.dominio = ?15 ");
		nativeQuery.append("                 INNER JOIN PERSONA_SERVICIO_MATERIA psm ");
		nativeQuery.append("                 ON              psm.id_persona = p.id_persona ");
		nativeQuery.append("                 INNER JOIN SERVICIO s ");
		nativeQuery.append("                 ON              psm.id_servicio = s.id_servicio ");
		nativeQuery.append("                 AND             s.tipo          = r.tipo_servicio ");
		nativeQuery.append("                 INNER JOIN MATERIA m ");
		nativeQuery.append("                 ON              psm.id_materia = m.id_materia ");
		nativeQuery.append("                 INNER JOIN PARAMETRO_SERVICIO_SORTEO pss ");
		nativeQuery.append("                 ON              s.id_servicio   = pss.id_servicio ");
		nativeQuery.append("                 AND             psm.id_servicio = pss.id_servicio ");
		nativeQuery.append("                 AND             rp.id_rol       = pss.id_rol ");
		nativeQuery.append("                 INNER JOIN ESTADO_MOTIVO_DISPONIBILIDAD emd ");
		nativeQuery.append("                 ON              psm.estado_para_sorteo = emd.estado_para_sorteo ");
		nativeQuery.append("                 INNER JOIN ESTADO_PERSONA_ROL epr ");
		nativeQuery.append("                 ON              p.id_persona    = epr.id_persona ");
		nativeQuery.append("                 AND             epr.id_servicio = s.id_servicio ");
		nativeQuery.append("                 AND             epr.id_rol      = r.id_rol ");
		nativeQuery.append("                 AND             epr.id_motivo = emd.id_motivo_estado ");
		nativeQuery.append("                 INNER JOIN dominio de ");
		nativeQuery.append("                 ON              emd.estado = de.codigo ");
		nativeQuery.append("                 AND             de.dominio = ?4 ");
		nativeQuery.append("                 LEFT JOIN dominio dm ");
		nativeQuery.append("                 ON              emd.id_motivo_estado = dm.codigo ");
		nativeQuery.append("                 AND             dm.dominio           = ?5 ");
		nativeQuery.append("                 LEFT JOIN DOMINIO dms ");
		nativeQuery.append("                 ON              psm.motivo_estado_sorteo = dms.codigo ");
		nativeQuery.append("                 AND             dms.dominio              = ?5 ");
		nativeQuery.append("                 LEFT JOIN lista l ");
		nativeQuery.append("                 ON              l.id_lista              = rp.id_lista ");
		nativeQuery.append(" WHERE           CAST(rp.fecha_inicio_vigencia AS DATE) <= CAST(GETDATE() AS DATE) ");
		nativeQuery.append(" AND ");
		nativeQuery.append("                 ( ");
		nativeQuery.append("                                 rp.fecha_fin_vigencia          IS NULL ");
		nativeQuery.append("                 OR              CAST(rp.fecha_fin_vigencia AS DATE) >= CAST(GETDATE() AS DATE) ");
		nativeQuery.append("                 ) ");
		nativeQuery.append(" AND             rp.estado_registro              = ?6 ");
		nativeQuery.append(" AND             psm.estado_registro             = ?6 ");
		nativeQuery.append(" AND             psm.fecha_fin_de_vigencia IS NULL ");
		nativeQuery.append(" AND             p.estado_registro               = ?6	");	
			
		if(!arbitro.equals(UtilConstantes.CADENA_VACIA))
			nativeQuery.append("    AND p.id_persona = ?7 ");

		if(!tipoCasoCodigo.equals(UtilConstantes.CADENA_VACIA))
			nativeQuery.append("    AND s.id_servicio = ?8 ");

		if(!estadoCodigo.equals(UtilConstantes.CADENA_VACIA))
			nativeQuery.append("    AND emd.estado = ?9 ");

		if(!motivoCodigo.equals(UtilConstantes.CADENA_VACIA))
			nativeQuery.append("    AND epr.id_motivo = ?10 ");

		if(!tipoListaCodigo.equals(UtilConstantes.CADENA_VACIA))
			nativeQuery.append("    AND l.id_lista = ?11 ");

		if(!materiaSeleccionada.equals("0"))
			nativeQuery.append("    AND m.id_materia = ?12 ");
		
		if(!disponibilidad.equals(UtilConstantes.CADENA_VACIA))
			nativeQuery.append("    AND psm.estado_para_sorteo = ?13 ");

		nativeQuery.append(" ORDER BY nombre ASC ");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), ReporteDeArbitroDTO.class);
		
		query.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(4, UtilDominios.DOMINIO_ESTADO_ARBITROS);
		query.setParameter(5, UtilDominios.DOMINIO_MOTIVO_ESTADO);
		query.setParameter(6, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(14, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);
		query.setParameter(15, UtilDominios.DOMINIO_ROL_PERSONA);
		
		if (!arbitro.equals(UtilConstantes.CADENA_VACIA))
			query.setParameter(7, Long.parseLong(arbitro));

		if (!tipoCasoCodigo.equals(UtilConstantes.CADENA_VACIA))
			query.setParameter(8, Long.valueOf(tipoCasoCodigo));

		if (!estadoCodigo.equals(UtilConstantes.CADENA_VACIA))
			query.setParameter(9, estadoCodigo);

		if (!motivoCodigo.equals(UtilConstantes.CADENA_VACIA))
			query.setParameter(10, motivoCodigo);
		
		if (!tipoListaCodigo.equals(UtilConstantes.CADENA_VACIA))
			query.setParameter(11, Long.valueOf(tipoListaCodigo));

		if (!materiaSeleccionada.equals("0"))
			query.setParameter(12, Long.valueOf(materiaSeleccionada));

		if (!disponibilidad.equals(""))
			query.setParameter(13, disponibilidad);
		
		return query.getResultList();
	}

	/**
	 * Metodo encargado de generar el reporte de 
	 * los pagos definidos en HONORARIOS_FIJADOS 
	 * @param filtros
	 * @return List<ReporteIngresosDTO>
	 */	
    @SuppressWarnings("unchecked")
	public List<ReporteIngresosDTO> getReporteIngresos(Map filtros){
		List<ReporteIngresosDTO> ingresos = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String fechaIncial = (String) filtros.get("fechaInicial");
		String fechaFinal = (String) filtros.get("fechaFinal");
		String pagoSeleccionado = (String) filtros.get("codigo");
		List<Object[]> rows;
		List<Object[]> rowsSec;
		InformacionFiltro filtro = new InformacionFiltro(TipoFiltro.EXACTO, Dominio.ENTIDAD_DOMINIO_PK_DOMINIO, UtilConstantes.ROL_PERSONA_EXTERNO);
		List<InformacionFiltro> filtrosDominio = new ArrayList<>();
		filtrosDominio.add(filtro);

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(
				"select c.idCaso, cr.persona.primerNombreORazonSocial, cr.persona.segundoNombre, cr.persona.primerApellido, cr.persona.segundoApellido  ");
		jpqlQuery.append("from Caso c join ");
		jpqlQuery.append("c.idCaso as cev ");
		jpqlQuery.append("left join c.rolPersonaCasoList as cr ");
		jpqlQuery.append("left join cr.rol as cl ");
		jpqlQuery.append("where cl.nombre = 'SEC' ");
		jpqlQuery.append("ORDER BY c.idCaso ASC ");
		Query query = em.createQuery(jpqlQuery.toString());

		rowsSec = query.getResultList();
		
		StringBuilder jpqlQuery1 = new StringBuilder();		  
		jpqlQuery1.append("SELECT c.nombre, ph.parte_que_paga, '' , hf.valor_fijado_pretensiones, hf.fecha_fijacion, ");
		jpqlQuery1.append(" hf.fecha_limite_de_pago, ph.valor_pagado, ph.fecha_pago, c.id_caso ");	
		jpqlQuery1.append(" FROM HONORARIOS_FIJADOS hf ");
		jpqlQuery1.append(" LEFT JOIN CASO c on c.id_caso = hf.id_caso  ");	
		jpqlQuery1.append(" LEFT JOIN PAGO_HONORARIOS ph ");
		
		
		DominioPK dominioPK=new DominioPK();
		dominioPK.setCodigo(pagoSeleccionado);
		dominioPK.setDominio(UtilDominios.DOMINIO_PAGADOS);
		Dominio dominio = manejadorDominio.buscar(dominioPK);
		
		if ((pagoSeleccionado != null && !pagoSeleccionado.equals("")) ) {
			if(dominio.getNombre().equals("SI")){	
				jpqlQuery1.append(" ON c.id_caso = ph.id_caso ");
			}else if (dominio.getNombre().equals("NO")) {			
				jpqlQuery1.append(" ON ph.id_caso IS NULL ");
			}
		}else{
			jpqlQuery1.append(" ON (c.id_caso = ph.id_caso OR ph.id_caso IS NULL) ");
			
		}		
		jpqlQuery1.append(" WHERE hf.fecha_limite_de_pago BETWEEN :fechaInicial and :fechaFinal ");		
		jpqlQuery1.append(" ORDER BY c.id_caso ASC ");
		
		Query query1 = em.createQuery(jpqlQuery1.toString());
		query1.setParameter("fechaInicial", fechaIncial);
		query1.setParameter("fechaFinal", fechaFinal);
		rows = query1.getResultList();

		for (Object[] row : rows) {

			ReporteIngresosDTO reporte = new ReporteIngresosDTO();
			reporte.setNombreCaso((String) row[0]);
			if(row[1] != null){
				reporte.setTipoParte(fachadaDominios.getNombreDominio(UtilDominios.DOMINIO_ROL_PERSONA,row[1].toString()));
				List<RolPersonaCaso> ps = manejadorRolPersonaCaso.consultarPersonasAsignadasCasoPorRol(Long.valueOf(row[8].toString()),row[1].toString());
				if(!ps.isEmpty()){
	 				List<String> tipos = new ArrayList<>();
	 				tipos.add(UtilDominios.TIPO_TELEFONO_CELULAR);
	 				List<Telefono> tel = manejadorTelefono.consultarPorTipoYPersona(tipos,ps.get(0).getPersona().getIdPersona());
					reporte.setTelefono(tel.isEmpty() ? "" : tel.get(0).getNumero());
				}
			}
			
			String reportDate1 = df.format(row[4]);
			reporte.setFechaDeFijacion(reportDate1);
			reporte.setValorFijado((BigDecimal)row[3]);
			String reportDate = row[5]==null?"":df.format(row[5]);
			String reportDate2 = row[7]==null?"":df.format(row[7]);
			reporte.setFechaLimiteDePago(reportDate); 
			reporte.setValorPagado(row[6]==null?BigDecimal.valueOf(0.0):(BigDecimal)row[6]);
			reporte.setFechaDePago(reportDate2);

			String secretario="";
			for (Object[] rowA : rowsSec) {
				if (row[8].equals(rowA[0])) {
					if (rowA[1] != null) {
						secretario = (String) rowA[1] + " ";
					}
					if (rowA[2] != null) {
						secretario = secretario.concat((String) rowA[2] + " ");
					}
					if (rowA[3] != null) {
						secretario = secretario.concat((String) rowA[3] + " ");
					}
					if (rowA[4] != null) {
						secretario = secretario.concat((String) rowA[4] + " ");
					}
					reporte.setSecretario(secretario);
				}				
			}			
			ingresos.add(reporte);
		}


		return ingresos;
	}

	/**
	 * Metodo encargado de generar el reporte de 
	 * los casos cerrados 
	 * @param filtros
	 * @return List<ReporteCasosCerradosDTO>
	 * @throws ParseException 
	 */	
   	public List<ReporteCasosCerradosDTO> getReporteCasosCerrados(Map filtros) {    	
   		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
   		Date fechaInicial = UtilOperaciones.obtenerFechaComienzoDelDia((Date) filtros.get("fechaInicial"));
		Date fechaFinal =  UtilOperaciones.obtenerFechaFinDelDia((Date) filtros.get("fechaFinal"));
		Long idMateria = null;
		if ((filtros.get("materia") != null && !filtros.get("materia").equals("")) ) {
			idMateria = Long.parseLong((String) filtros.get("materia"));
		}
		String motivoCierre = (String) filtros.get("codigo");		
	   
		List<ReporteCasosCerradosDTO> reporteCasosCerrados;
			
		String subConsultaApodDemandante = " (select stuff((select ', ' + concat (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) "
				+" from ROL_PERSONA_CASO rp "
				+" left join PERSONA pe on rp.id_persona=pe.id_persona "
				+" where rp.id_caso=c.id_caso "
				+" and rp.estado_registro = ?1 "
				+" and rp.id_rol=(select id_rol from rol where nombre= ?2 ) for xml path('')), 1, 1, '')) as apoderadoDemandante, "; 
			
		String subConsultaApodDemandado = " (select stuff((select ', ' + concat (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) "
				+" from ROL_PERSONA_CASO rp "
				+" left join PERSONA pe on rp.id_persona=pe.id_persona "
				+" where rp.id_caso=c.id_caso "
				+" and rp.estado_registro = ?1 " 
				+" and rp.id_rol=(select id_rol from rol where nombre= ?3 ) for xml path('')), 1, 1, '')) as apoderadoDemandado, ";
			
		String subConsultaArbitros = " (select stuff((select ', ' + concat (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) "
				+" from ROL_PERSONA_CASO rp "
				+" left join PERSONA pe on rp.id_persona=pe.id_persona " 
				+" where rp.id_caso = c.id_caso "
				+" and rp.estado_registro = ?1 " 
				+" and rp.estado in ( ?4 , ?5 ) "
				+" and rp.id_rol in (select DISTINCT rl.id_rol from PARAMETRO_SERVICIO_SORTEO pss inner join ROL rl on pss.id_rol = rl.id_rol) for xml path('')), 1, 1, '')) as arbitros, ";

		String subConsultaSecretarios = " (select stuff((select ', ' + concat (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) "
				+" from ROL_PERSONA_CASO rp "
				+" left join PERSONA pe on rp.id_persona=pe.id_persona " 
				+" where rp.id_caso=c.id_caso "
				+" and rp.estado_registro = ?1 "
				+" and rp.estado in ( ?4 , ?5 ) " 
				+" and rp.id_rol=(select id_rol from rol where nombre= ?6 ) for xml path('')), 1, 1, '')) as secretario, ";
			
			
		StringBuilder jpqlQuery = new StringBuilder();
			
		jpqlQuery.append(" SELECT ");
		jpqlQuery.append(" c.id_caso AS  codigoCaso, ");
		jpqlQuery.append("(SELECT nombre FROM SERVICIO WHERE id_servicio = c.id_servicio AND estado_registro = ?1 ) AS servicioCaso, ");
		jpqlQuery.append(" c.nombre AS nombreCaso, ");
		jpqlQuery.append(" cast(c.fecha_radicacion as date) AS fechaRadicacion, ");
		jpqlQuery.append(" c.valor_pretensiones AS valorPretensiones, ");
		jpqlQuery.append(" m.nombre AS materia, ");
		jpqlQuery.append(subConsultaApodDemandante);
		jpqlQuery.append(subConsultaApodDemandado);
		jpqlQuery.append(subConsultaArbitros);
		jpqlQuery.append(subConsultaSecretarios);
		jpqlQuery.append("(SELECT nombre FROM DOMINIO WHERE DOMINIO = ?7 AND codigo = c.motivo_cierre) AS motivoCierre,	 ");
		jpqlQuery.append(" cast(e.fecha_evento as date) AS fechaCierre, ");
		jpqlQuery.append(" e.observaciones AS observacionesCierre, ");
		jpqlQuery.append(CONSUMO);
		jpqlQuery.append(" FROM CASO c ");
		jpqlQuery.append(" LEFT JOIN  EVENTO e ON c.id_caso = e.id_caso ");
		jpqlQuery.append(" LEFT JOIN  MATERIA m ON m.id_materia = c.id_materia ");
		jpqlQuery.append(" WHERE c.estado_caso = ?8 ");
		jpqlQuery.append(ESTADO_CASO_ACTIVO);
		jpqlQuery.append(" AND e.estado_registro = ?1 ");
		jpqlQuery.append(" AND m.estado_registro = ?1 ");
		jpqlQuery.append(" AND e.tipo_evento = ?9 ");
		jpqlQuery.append(" AND e.fecha_evento BETWEEN  ?10 AND ?11  ");
		if (null != idMateria) {
			jpqlQuery.append(" AND m.id_materia = ?12 ");
		}

		if (null != motivoCierre && !motivoCierre.isEmpty()) {
			jpqlQuery.append("AND c.motivo_cierre = ?13 ");
		}
		
		jpqlQuery.append(" ORDER BY e.fecha_evento DESC ");
			
		
		Query query = em.createNativeQuery(jpqlQuery.toString(), ReporteCasosCerradosDTO.class);
		
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
		query.setParameter(3, UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
		query.setParameter(4, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(5, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		query.setParameter(6, UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
		query.setParameter(7, UtilDominios.DOMINIO_MOTIVO_CIERRE);
		query.setParameter(8, UtilDominios.ESTADO_CASO_CERRADO);
		query.setParameter(9, UtilDominios.TIPO_EVENTO_CASO_CERRADO);
		query.setParameter(10, dateFormat.format(fechaInicial));
		query.setParameter(11, dateFormat.format(fechaFinal));
		query.setParameter(12, idMateria);
		query.setParameter(13, motivoCierre);

		
		reporteCasosCerrados = query.getResultList();

    	return reporteCasosCerrados;
    }
    
   	
	/**
	 * Metodo encargado de generar el reporte de 
	 * las audiencias programadas, con un listado de las preferencias de refrigerio de los asistentes
	 * @param filtros
	 * @return List<ReporteAudienciasProgramacionRefrigeriosDTO>
	 */	
	@SuppressWarnings("unchecked")
	public List<ReporteAudienciasProgramacionRefrigeriosDTO> getReporteAudienciasProgramacionRefrigerios(Map filtros) {

		List<ReporteAudienciasProgramacionRefrigeriosDTO> audienciasProgRefrigeriosDTOs = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		
		try {
			Date ffinal = df.parse(filtros.get("fechaFin").toString());			
			String fechaFinal = df.format(UtilSimasc.agregarDiasAFecha(ffinal, 1));
			
			StringBuilder jpqlQueryAudiencia = new StringBuilder();
			jpqlQueryAudiencia.append("select ");
			jpqlQueryAudiencia.append("c.id_caso as codigoCaso, "); // 0
			jpqlQueryAudiencia.append("c.nombre as nombreCaso, "); // 1
			jpqlQueryAudiencia.append("au.hora_inicio as fechaHoraAudiencia, "); // 2
			jpqlQueryAudiencia.append("(select nombre from dominio where dominio='TIPO_AUDIENCIA' and codigo = au.tipo_audiencia) as tipoAudiencia, "); // 3
			jpqlQueryAudiencia.append("au.cantidad_asistentes AS cantidadAsistentes, "); // 4
			jpqlQueryAudiencia.append(CONSUMO);
			jpqlQueryAudiencia.append(", (SELECT s.nombre FROM SERVICIO s ");
			jpqlQueryAudiencia.append("WHERE s.id_servicio = c.id_servicio) AS servicio,");
			jpqlQueryAudiencia.append("(select stuff((select ', ' + concat (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) ");
			jpqlQueryAudiencia.append("from ROL_PERSONA_CASO rp ");
			jpqlQueryAudiencia.append("left join PERSONA pe on rp.id_persona=pe.id_persona ");
			jpqlQueryAudiencia.append("where rp.id_caso = c.id_caso ");
			jpqlQueryAudiencia.append("and rp.estado_registro = ?5 ");
			jpqlQueryAudiencia.append("and rp.estado in (?6,?7) ");		
			jpqlQueryAudiencia.append("and rp.id_rol in (select DISTINCT rl.id_rol from PARAMETRO_SERVICIO_SORTEO pss inner join ROL rl on pss.id_rol = rl.id_rol) for xml path('')), 1, 1, '')) as nombreArbitros, "); // 5
			jpqlQueryAudiencia.append("(select stuff((select ', ' + concat (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) ");			
			jpqlQueryAudiencia.append("from ROL_PERSONA_CASO rp ");
			jpqlQueryAudiencia.append("left join PERSONA pe on rp.id_persona=pe.id_persona ");
			jpqlQueryAudiencia.append("where rp.id_caso=c.id_caso ");
			jpqlQueryAudiencia.append("and rp.estado_registro = ?5 ");
			jpqlQueryAudiencia.append("and rp.id_rol=(select id_rol from rol where nombre='SEC') for xml path('')), 1, 1, '')) as nombreSecretarios "); // 6
			jpqlQueryAudiencia.append("from audiencia au ");
			jpqlQueryAudiencia.append("inner join CASO c on au.id_caso = c.id_caso ");
			jpqlQueryAudiencia.append("inner join SERVICIO s on c.id_servicio = s.id_servicio  ");
			jpqlQueryAudiencia.append("where au.hora_inicio between ?1 AND ?2 ");
			jpqlQueryAudiencia.append("and s.tipo in (?3, ?8) ");
			jpqlQueryAudiencia.append("and au.estado = ?4 ");
			jpqlQueryAudiencia.append("and au.estado_registro = ?5 ");
			
			Query query = em.createNativeQuery(jpqlQueryAudiencia.toString(), ReporteAudienciasProgramacionRefrigeriosDTO.class);
			query.setParameter(1, filtros.get("fechaIni"));
			query.setParameter(2, fechaFinal);
			query.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
			query.setParameter(4, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
			query.setParameter(5, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(6, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
			query.setParameter(7, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
			query.setParameter(8, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);
			audienciasProgRefrigeriosDTOs = query.getResultList();
			
		} catch (ParseException e) {
			LOGGER.info(e.getMessage());
		}
		return audienciasProgRefrigeriosDTOs;
	}
	
	
	/**
	 * Metodo encargado de generar el reporte de 
	 * las salas que estarán ocupadas o están ocupadas, esto mediante la tabla AGENDAMIENTO
	 * @param filtros
	 * @return List<ReporteAudienciasProgramacionRefrigeriosDTO>
	 */	
	public List<ReporteSalasOcupadasDTO> getReporteSalasOcupadas(Map filtros){	
		Date fecha = filtros.get("fechaIni")==null?null:(Date)filtros.get("fechaIni");
		String idCaso = filtros.get("codigoCaso")==null?"":filtros.get("codigoCaso").toString();
		String nombreCaso =filtros.get("nombreCaso")==null?"":filtros.get("nombreCaso").toString();
		String idSede = filtros.get("sedeSeleccionada")==null?"":filtros.get("sedeSeleccionada").toString();

		SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy/M/dd");
		SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a");
		
		List<ReporteSalasOcupadasDTO> salas = new ArrayList<>();
		
		String[] rolArbitros = UtilConstantes.ROLES_ARBITROS;
		
		StringBuilder nombreArbitros;
		StringBuilder secretarios;

		StringBuilder jpqlQuery = new StringBuilder(); 

		jpqlQuery.append(" SELECT age FROM Agendamiento age ");
		jpqlQuery.append(" LEFT JOIN age.sala s");
		jpqlQuery.append(" LEFT JOIN s.sede d");
		jpqlQuery.append(" LEFT JOIN age.audiencia a");
		jpqlQuery.append(" LEFT JOIN a.caso c");
		jpqlQuery.append(" WHERE age.estadoRegistro =: ");
		jpqlQuery.append(Agendamiento.ENTIDAD_AGENDAMIENTO_ESTADO_REGISTRO);
		
		if(fecha != null){
			jpqlQuery.append(" AND (age.horaInicio BETWEEN :fechaIni AND :fechaFin OR age.horaFin BETWEEN :fechaIni AND :fechaFin) ");
		}	
		
		
		if(nombreCaso != null && !nombreCaso.equals("")){
			jpqlQuery.append(" AND LOWER(c.nombre) LIKE : ");
			jpqlQuery.append(Caso.ENTIDAD_CASO_NOMBRE);
		}
		if(idCaso != null && !idCaso.equals("") && !idCaso.equals("0")){
			jpqlQuery.append(" AND c.idCaso=: ");
			jpqlQuery.append(Caso.ENTIDAD_CASO_PK);
		}
		if(idSede != null && !idSede.equals("") && !idSede.equals("0")){
			jpqlQuery.append(" AND d.idSede=:");
			jpqlQuery.append(Sede.ENTIDAD_SEDE_PK);
		}
		
		
			
		
		Query query = em.createQuery(jpqlQuery.toString(), Agendamiento.class);
		
		if(fecha != null){
			Date fechaIni = obtenerFechaComienzoDelDia(fecha);
			query.setParameter("fechaIni", fechaIni,TemporalType.DATE);
			Date fechaFin = obtenerFechaFinDelDia(fecha);
			query.setParameter("fechaFin", fechaFin,TemporalType.DATE);
		}
		
		if(nombreCaso != null && !nombreCaso.equals(""))
			query.setParameter(Caso.ENTIDAD_CASO_NOMBRE, "%" +  nombreCaso.toLowerCase() + "%");
		
		if(idCaso != null && !idCaso.equals("") && !idCaso.equals("0"))
			query.setParameter(Caso.ENTIDAD_CASO_PK, Long.valueOf(idCaso));
		
		if(idSede != null && !idSede.equals("") && !idSede.equals("0"))
			query.setParameter(Sede.ENTIDAD_SEDE_PK, Long.valueOf(idSede));
			
		
		query.setParameter(Agendamiento.ENTIDAD_AGENDAMIENTO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		List<Agendamiento> agendamientos = query.getResultList();
		
		for(Agendamiento ag : agendamientos){
			nombreArbitros = new StringBuilder();
			secretarios = new StringBuilder();
			ReporteSalasOcupadasDTO dto = new ReporteSalasOcupadasDTO();
			Sede sede = ag.getSala().getSede() != null ? ag.getSala().getSede() : new Sede();
			Caso caso = ag.getCaso() != null ? ag.getCaso() : new Caso();
			Audiencia audiencia = ag.getAudiencia() != null ? ag.getAudiencia() : null;
			
			dto.setSede(sede.getNombre() != null ? sede.getNombre() : null);
			if(ag.getSala() != null && ag.getSala().getNumeroSala() != null){
				dto.setNumeroSala(String.valueOf(ag.getSala().getNumeroSala()));
			}
			
			dto.setNombreCaso(caso.getNombre() != null ? caso.getNombre() : null);
			dto.setCodigoCaso(caso.getIdCaso() != null ? caso.getIdCaso().toString() : null);
			
			if(audiencia != null){
				dto.setFechaAudiencia(formatofecha.format(audiencia.getHoraInicio()));
				dto.setHoraInicial(formatoHora.format(audiencia.getHoraInicio()));
				dto.setHoraFinal(formatoHora.format(audiencia.getHoraFin()));
			}
						
			List<RolPersonaCaso> rolPersonaCasos =
					ag.getCaso() != null && !ag.getCaso().getRolPersonaCasoList().isEmpty() ? ag.getCaso().getRolPersonaCasoList() : new ArrayList<RolPersonaCaso>();
			
			for(RolPersonaCaso rpc : rolPersonaCasos){
				for(String rol : rolArbitros){
					if (rol.equalsIgnoreCase(rpc.getRol().getNombre()) && UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO
							.equalsIgnoreCase(rpc.getEstado())) {
						nombreArbitros.append(rpc.getPersona().getNombreCompleto()).append(UtilConstantes.CARACTER_ESPACIO);
						break;
					}
				}
				if(UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL.equalsIgnoreCase(rpc.getRol().getNombre())
						&& UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO.equalsIgnoreCase(rpc.getEstado())){
					secretarios.append(rpc.getPersona().getNombreCompleto()).append(UtilConstantes.CARACTER_ESPACIO);
				}
			}
			dto.setArbitros(nombreArbitros.toString());
			dto.setSecretario(secretarios.toString());
			salas.add(dto);
		}		
		return salas;
	} 
	
	/**
	 * Metodo encargado de generar el reporte caso por secretario, genera información del caso que tiene 
	 * asignado el secretario que consulta el reporte, es decir, del usuario logueado
	 * 
	 * @param fechaInicial	Fecha de radicación del caso
	 * @param fechaFinal	Fecha de radicación del caso
	 * @param idCaso	identificador del Caso
	 * @param nombreCaso	Palabras clave que hagan parte de la cadena del nombre del caso
	 * @return List<ReporteCasoSecretarioDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<ReporteCasoSecretarioDTO> getReporteCasoSecretario(Date fechaInicial, Date fechaFinal, Long idCaso,
			String nombreCaso, String usuarioSesion) {
		Date fechaInicioDia = null;
		Date fechaFinDia = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		if(fechaInicial != null){
			fechaInicioDia = UtilOperaciones.obtenerFechaComienzoDelDia(fechaInicial);
		}
		
		if(fechaFinal != null){
			fechaFinDia = UtilOperaciones.obtenerFechaFinDelDia(fechaFinal);
		}
		
		List<ReporteCasoSecretarioDTO> reporteCasosQry;
		List<ReporteCasoSecretarioDTO> reporteCasoSecretarioDTO = new ArrayList<>();
		
		String nombreAuxiliar = "(select stuff((select ', ' + concat (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) "
				+" from ROL_PERSONA_CASO rp "
				+" left join PERSONA pe on rp.id_persona=pe.id_persona " 
				+" where rp.id_caso = c.id_caso "
				+" and rp.estado_registro = ?1 " 
				+" and rp.estado = ?2 "	
				+" and rp.id_rol = (SELECT id_rol FROM ROL WHERE nombre = ?16 ) for xml path('')), 1, 1, '')) as nombreAuxiliar, ";
		
		String diasSuspension = "(SELECT SUM (DATEDIFF(day,s.fecha_inicial,isnull(s.fecha_final,SYSDATETIME()))) "
				+ " FROM SUSPENSION  s WHERE id_caso = c.id_caso AND estado_registro = ?1 "
				+ " AND tipo IN ( ?3 , ?4 )) AS diasSuspension, ";
		
		String diasInterrupacion = "(SELECT SUM (DATEDIFF(day,s.fecha_inicial,isnull(s.fecha_final,SYSDATETIME()))) "
				+ " FROM SUSPENSION  s WHERE id_caso = c.id_caso AND tipo = ?5 AND estado_registro = ?1 ) AS diasInterrupcion, ";
		
		StringBuilder jpqlQuery = new StringBuilder();
		

		jpqlQuery.append(" SELECT ");
		jpqlQuery.append(" c.id_caso AS codigoCaso, ");
		jpqlQuery.append(" c.nombre AS nombreCaso, ");
		jpqlQuery.append(" cast(c.fecha_radicacion as date) AS fechaCaso, ");
		jpqlQuery.append(" (SELECT nombre FROM DOMINIO WHERE  codigo = c.etapa AND dominio = ?6 ) AS etapa, ");
		jpqlQuery.append(" DATEDIFF(day,c.fecha_radicacion, SYSDATETIME()) AS diasTranscurridos,  ");
		jpqlQuery.append(diasSuspension);
		jpqlQuery.append(diasInterrupacion);
		jpqlQuery.append(nombreAuxiliar);
		jpqlQuery.append(" (SELECT nombre FROM DOMINIO WHERE codigo = c.estado_caso AND dominio = ?7 ) AS estado,  ");
		jpqlQuery.append(" c.dias_para_proferir_laudo AS diasProferirLaudo ");
		jpqlQuery.append(" FROM CASO c  ");
		jpqlQuery.append(" LEFT JOIN  ROL_PERSONA_CASO rpc ON c.id_caso = rpc.id_Caso  ");
		jpqlQuery.append(" WHERE rpc.id_rol = (SELECT id_rol FROM ROL WHERE nombre= ?8 )  ");
		jpqlQuery.append(" AND rpc.id_persona = (SELECT id_persona FROM USUARIO WHERE usuario_login = ?9 )   ");
		jpqlQuery.append(" AND rpc.estado_registro = ?1 ");		
		jpqlQuery.append(" AND rpc.estado IN ( ?10 , ?11 ) ");
		jpqlQuery.append(ESTADO_CASO_ACTIVO);
		jpqlQuery.append(" AND c.estado_caso != ?17 ");
    	if(idCaso != null && idCaso > 0)
    		jpqlQuery.append(" AND c.id_caso = ?12  ");
    	
    	if(fechaInicial != null && fechaFinal != null)
    		jpqlQuery.append("AND c.fecha_radicacion BETWEEN  ?13 AND ?14 ");  
	    	
    	
		if(nombreCaso != null && !nombreCaso.equals(""))
			jpqlQuery.append(" AND  LOWER(c.nombre) like ?15 ");
			
		
		jpqlQuery.append(" ORDER BY c.id_caso ASC  ");
		
		Query query = em.createNativeQuery(jpqlQuery.toString(),ReporteCasoSecretarioDTO.class);
		
		
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO);
		query.setParameter(3, UtilDominios.TIPO_SUSPENSION_SUSPENSION_ARBITRAL);
		query.setParameter(4, UtilDominios.TIPO_SUSPENSION_SUSPENSION_PREARBITRAL);
		query.setParameter(5, UtilDominios.TIPO_SUSPENSION_INTERRUPCION);
		query.setParameter(6, UtilDominios.DOMINIO_ETAPA_CASO);
		query.setParameter(7, UtilDominios.DOMINIO_ESTADO_CASO);
		query.setParameter(8, UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
		query.setParameter(9, usuarioSesion);
		query.setParameter(10, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(11, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		query.setParameter(12, idCaso);
		if(fechaInicial != null && fechaFinal != null ){
			query.setParameter(13, dateFormat.format(fechaInicioDia));
			query.setParameter(14, dateFormat.format(fechaFinDia));
		}


		query.setParameter(15, "%" + nombreCaso.toLowerCase().replaceAll("\\s", "") + "%");
		query.setParameter(16, UtilDominios.ROL_PERSONA_AUXILIAR_ADM);
		query.setParameter(17, UtilConstantes.ESTADO_CASO_EN_CREACION);

	
		reporteCasosQry = query.getResultList();	
		for (ReporteCasoSecretarioDTO reporteFor : reporteCasosQry) {
			Integer diasParaProferirLaudo;
			Date fechaLimite = fechaCasoFacade.calcularFechaLimiteParaCierreDeCaso(reporteFor.getCodigoCaso().longValue());
    		if(fechaLimite != null){
    			try{
    				diasParaProferirLaudo = reporteFor.getDiasProferirLaudo() == null?0:Integer.valueOf(reporteFor.getDiasProferirLaudo().intValue());	
    			}catch(Exception e){
    				diasParaProferirLaudo =0;
    			}
    			reporteFor.setFechaCierre(fechaLimite);
	    		reporteFor.setFechaLaudo(UtilOperaciones.adicionarDiasFecha(fechaLimite,diasParaProferirLaudo*-1));
    		}else{
    			reporteFor.setFechaCierre(null);
    			reporteFor.setFechaLaudo(null);
    		}    		

    		if( reporteFor.getFechaCierre() != null){
    			Long diasFaltantes = ( reporteFor.getFechaCierre().getTime() - new Date().getTime()) / (1000 * 60 * 60 * 24);
    			reporteFor.setDiasFaltantes(diasFaltantes.toString());
    		}
    		
    		if(reporteFor.getDiasSuspension() == null)
    			reporteFor.setDiasSuspension(new BigDecimal(0));
    		
    		if(reporteFor.getDiasInterrupcion() == null)
    			reporteFor.setDiasInterrupcion(new BigDecimal(0));
    		
    		if(reporteFor.getDiasTranscurridos() == null)
    			reporteFor.setDiasTranscurridos(new BigDecimal(0));

 	
    		reporteCasoSecretarioDTO.add(reporteFor);
			
		}
		
		return reporteCasoSecretarioDTO;	
	
	}	
	
	/**
	 * Metodo encargado de consultar casos dependiendo de los parametros definidos por
	 * el usuario, entre ellos el tipo de la cuantía
	 * 
	 * @param fechaInicial	Fecha radicación del caso
	 * @param fechaFinal	Fecha radicación del caso
	 * @param tipoCaso	Servicio del caso
	 * @param tipoCuantia	Mayor, Menor o indeterminada
	 * @return List<ReporteCasoCuantiaDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<ReporteCasoCuantiaDTO> getReporteCasoCuantia(Date fechaInicial, Date fechaFinal, Long tipoCaso,
			String tipoCuantia) {
		
		List<Object[]> rows=new ArrayList<>();		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		List<ReporteCasoCuantiaDTO> reporteCasoCuantiaDTOs = new ArrayList<>(rows.size());
		
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT c.nombre, c.fechaRadicacion, s.nombre, m.nombre, c.tipoCuantia, c.valorPretensiones, ");
		jpqlQuery.append("CASE WHEN c.arbitrajeConsumo = '1' THEN 'SI'");
		jpqlQuery.append("ELSE 'NO'END AS consumo, ");
		jpqlQuery.append("CASE WHEN c.amparoDePobreza = '1' THEN 'SI' ");
		jpqlQuery.append("ELSE 'NO' END AS amparoDePobreza, ");
		jpqlQuery.append("CASE WHEN c.concedeAmparo = '1' THEN 'SI' ");
		jpqlQuery.append("ELSE 'NO' END AS concedeAmparo ");
		jpqlQuery.append("FROM Caso c ");
		jpqlQuery.append("INNER JOIN c.servicioMateria csm ");
		jpqlQuery.append("INNER JOIN csm.servicio s ");
		jpqlQuery.append("INNER JOIN csm.materia m ");
		jpqlQuery.append("WHERE c.estadoCaso <> : estado ");
		jpqlQuery.append("AND c.estadoRegistro = 'ACT' ");
		jpqlQuery.append("AND s.tipo = :tipoServicio ");
		jpqlQuery.append("AND c.fechaRadicacion BETWEEN '"+
						dateFormat.format(UtilOperaciones.obtenerFechaComienzoDelDia(fechaInicial))+
						"' AND '"+
						dateFormat.format(UtilOperaciones.obtenerFechaFinDelDia(fechaFinal))+"' ");
				
		if(tipoCaso > 0L)
			jpqlQuery.append("AND c.idServicio = :tipoCaso ");
		
		if(!tipoCuantia.equals("TODOS"))
			jpqlQuery.append("AND c.tipoCuantia = :cuantia ");
		
		jpqlQuery.append("ORDER BY c.idCaso ");
		
		Query query = em.createQuery(jpqlQuery.toString());
		
		query.setParameter("estado", UtilConstantes.ESTADO_CASO_EN_CREACION);
		query.setParameter("tipoServicio", UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		if(tipoCaso > 0L)
			query.setParameter(PARAMETRO_TIPO_CASO, tipoCaso);
		
		if(!tipoCuantia.equals("TODOS"))
			query.setParameter("cuantia", tipoCuantia);
		
		rows = query.getResultList(); 
		
		for(Object[] row : rows){	
			ReporteCasoCuantiaDTO reporte = new ReporteCasoCuantiaDTO();
			reporte.setNombreCaso((String) row[0]);
			reporte.setFechaCaso((Date) row[1]);
			reporte.setTipoCaso((String) row[2]);
			reporte.setMateria((String) row[3]);
			String tipoCuantiaReporte = "";
			if (row[4] != null)
				tipoCuantiaReporte = UtilDominios.TIPO_CUANTIA_MAYOR.equals(row[4].toString()) ? "Mayor" : "Menor";
			
			reporte.setTipoCuantia(tipoCuantiaReporte);			
			reporte.setValorPretension((String)row[5]);
			reporte.setConsumo((String) row[6]);
			reporte.setAmparoDePobreza((String) row[7]);
			reporte.setConcedeAmparo((String) row[8]);
			reporteCasoCuantiaDTOs.add(reporte);
    	}
		return reporteCasoCuantiaDTOs;
	}	
	
	/**
	 * Metodo encargado de generar el reporte de 
	 * los casos que han sido sorteados 
	 * @param filtros
	 * @return List<ReporteCasosSorteadosDTO>
	 */	
    @SuppressWarnings("unchecked")	
	public List<ReporteCasosSorteadosDTO> getReporteCasosSorteados(Date fechaInicial, Date fechaFinal, Long tipoCaso, Boolean consumo) {
    	List<ReporteCasosSorteadosDTO> casosSorteados = new ArrayList<>();
    	
    	List<Sorteo> sorteos = manejadorSorteo.consultarSorteosRealizados(fechaInicial, fechaFinal, tipoCaso);    
    	
    
    	
    	for (Sorteo sorteo : sorteos) {    		    		
    		Caso caso = sorteo.getCaso();    		    		    		
    		if (caso != null) {				
    			ReporteCasosSorteadosDTO reporteDto = new ReporteCasosSorteadosDTO();
    			reporteDto.setIdCaso(caso.getIdCaso());
    			reporteDto.setNombreCaso(caso.getNombre());
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");	            
        		reporteDto.setFechaSorteo(sorteo.getFechaRealizacion() != null ? 
        				dateFormat.format(sorteo.getFechaRealizacion()) : null);
        		reporteDto.setTipoCaso(caso.getServicioMateria().getServicio().getNombre());
        		reporteDto.setMateria(caso.getServicioMateria().getMateria().getNombre());
        		reporteDto.setValorPretensiones(caso.getValorPretensiones());
        		reporteDto.setTipoCuantia(sorteo.getTipoCuantia());
        		reporteDto.setConsumo((caso.isArbitrajeConsumo())?"SI":"NO");
        		if(caso.getQuienPreselecciona() != null && !caso.getQuienPreselecciona().isEmpty()) {
        			if(caso.getQuienPreselecciona().equals("PCCB")) {
        				reporteDto.setTipoPreseleccion("Por la CCB");
        			}else {
        				reporteDto.setTipoPreseleccion("Por las Partes");
        			}        			
        		}else {
        			reporteDto.setTipoPreseleccion((caso.getQuienPreselecciona()));
        		}
        		reporteDto.setPreseleccionString((caso.getPreseleccion())?"SI":"NO");
        		//se filtran los arbitros a mostrar
        		List<RolPersonaCaso> arbitrosSelec = obtenerArbitrosSorteoCaso(sorteo, caso);
        		
				reporteDto.setArbitros(arbitrosSelec);
        		reporteDto.setPreseleccion(caso.getPreseleccion());
        		//se busca el tipo sorteo por la audiencia		
				List<Audiencia> audiencias = sorteo.getAudienciaList();
				String tipoSorteo = null;
				for (Audiencia a : audiencias) {
					if(a.getTipoSorteo()!= null && !a.getTipoSorteo().equals("")) {
						tipoSorteo =manejadorDominio.consultarTipoSorteo(a.getTipoSorteo());
						break;
					}					
				}				
				reporteDto.setTipoSorteo(tipoSorteo);
				if(consumo != null){
					if(consumo.equals(caso.isArbitrajeConsumo())){
						casosSorteados.add(reporteDto);
					}
				}else{
					casosSorteados.add(reporteDto);
				}     
				
				
    		}    		
    	}
		return casosSorteados;
	}

	/**
	 * Metodo encargado de consultar casos dependiendo de las partes implicadas
	 * @param fechaInicial	fecha de radicación del caso
	 * @param fechaFinal	fecha de radicación del caso
	 * @param rol	Rol de la parte
	 * @return List<ReporteCasoParteDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<ReporteCasoParteDTO> getReporteCasoParte(Date fechaInicial, Date fechaFinal, String rol, String primerNombre, String primerApellido) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date fechaInicioDia = UtilOperaciones.obtenerFechaComienzoDelDia(fechaInicial);
		Date fechaFinDia = UtilOperaciones.obtenerFechaFinDelDia(fechaFinal);
		
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT ");
		jpqlQuery.append(" ca.id_caso AS codigoCaso,");
		jpqlQuery.append(" ca.nombre AS nombreCaso,");
		jpqlQuery.append(" ca.fecha_radicacion AS fechaRadicacion,");
		jpqlQuery.append(" (select nombre from DOMINIO WHERE dominio = ?2 AND codigo IN(ca.tipo_cuantia)) AS tipoCuantia,");
		jpqlQuery.append(" ca.valor_pretensiones AS valorPretension,");
		jpqlQuery.append(" (SELECT descripcion from dominio where dominio = ?3 and codigo in (select nombre from rol where id_rol = rpc.id_rol)) as rol,");
		jpqlQuery.append(" (SELECT nombre FROM DOMINIO WHERE dominio = ?8 AND codigo IN (per.tipo_de_empresa)) AS tipoEmpresa,");
		jpqlQuery.append(" (SELECT nombre FROM DOMINIO WHERE dominio = ?9 AND codigo IN (per.tipo_persona))  AS tipoPersona,");
		jpqlQuery.append(" (SELECT nombre FROM DOMINIO WHERE dominio = ?10 AND codigo IN (per.tipo_documento))  AS tipoIdentificacion,");
		jpqlQuery.append(" per.numero_documento AS numeroIdentificacion,");
		jpqlQuery.append(" per.numero_tarjeta_profesional AS tarjetaProfesional,");
		jpqlQuery.append(" CONCAT(per.primer_nombre_o_razon_social, ' ' ,per.segundo_nombre, ' ' , per.primer_apellido, ' ' , per.segundo_apellido) AS nombre,");
		jpqlQuery.append(" (SELECT STUFF ((select ' ' + direccion FROM UBICACION WHERE id_persona IN (per.id_persona) AND estado_registro = ?4 FOR XML PATH('')), 1, 1, '')) AS direccion,");
		jpqlQuery.append(" (SELECT STUFF ((select ' ' + (SELECT NOMBRE FROM ZONA_GEOGRAFICA  WHERE estado_registro = ?4 AND id_zona_geografica = U.id_zona_geografica) FROM UBICACION U WHERE id_persona IN (per.id_persona) AND estado_registro = ?4 FOR XML PATH('')), 1, 1, '')) AS ciudad,");
		jpqlQuery.append(" (select stuff((select ' ' + numero from TELEFONO where id_persona=per.id_persona and estado_registro= ?4 AND tipo_telefono != ?5 for xml path('')), 1, 1, '')) as telefono_uno,");
		jpqlQuery.append(" (select stuff((select ' ' + numero from TELEFONO where id_persona=per.id_persona and estado_registro= ?4 AND tipo_telefono = ?5 for xml path('')), 1, 1, '')) as fax, ");
		jpqlQuery.append(" (select stuff((select ' ' + direccion from CORREO_ELECTRONICO where id_persona=per.id_persona and estado_registro= ?4  for xml path('')), 1, 1, '')) as correo_uno, ");
		jpqlQuery.append(" CASE WHEN ca.arbitraje_consumo = 1 THEN 'SI' ELSE 'NO' END AS consumo, ");
		jpqlQuery.append(" ser.nombre AS servicio ");
		jpqlQuery.append(" from ROL_PERSONA_CASO rpc");
		jpqlQuery.append(" LEFT JOIN CASO ca on rpc.id_caso = ca.id_caso");
		jpqlQuery.append(" LEFT JOIN SERVICIO ser on ca.id_servicio = ser.id_servicio");
		jpqlQuery.append(" LEFT JOIN PERSONA per on rpc.id_persona = per.id_persona");
		jpqlQuery.append(" LEFT JOIN ROL r on rpc.id_rol = r.id_rol");
		jpqlQuery.append(" where ca.fecha_radicacion between ?6 and ?7 ");
		jpqlQuery.append(" AND ca.estado_caso !=  ?11 ");
		jpqlQuery.append(" AND rpc.estado_registro = ?4 ");
		jpqlQuery.append(" AND ca.estado_registro =  ?4 ");
		jpqlQuery.append(" AND per.estado_registro = ?4 ");
		jpqlQuery.append(" AND r.estado_registro =   ?4 ");
		jpqlQuery.append(" AND ser.tipo in (?18, ?19) ");
		if(rol.equals("TODOS")){
			jpqlQuery.append("AND (r.nombre = ?14 ");
			jpqlQuery.append(" OR r.nombre = ?15 ");
			jpqlQuery.append(" OR r.nombre = ?16 ");
			jpqlQuery.append(" OR r.nombre = ?17 ");
			jpqlQuery.append(" ) ");
		}else{
			jpqlQuery.append("AND r.nombre = ?1 ");	
		}
		
		if(primerNombre != null && !primerNombre.equals("")){
			jpqlQuery.append("AND LOWER(per.primer_nombre_o_razon_social) like ?12 ");
		}
		if(primerApellido != null && !primerApellido.equals("")){
			jpqlQuery.append("AND LOWER(per.primer_apellido) like ?13 ");
		}

		jpqlQuery.append(" ORDER BY ca.fecha_radicacion desc");
		


		Query query = em.createNativeQuery(jpqlQuery.toString(),ReporteCasoParteDTO.class);
		if(!rol.equals("TODOS"))
			query.setParameter(1, rol);
		
		query.setParameter(2, UtilDominios.DOMINIO_TIPO_CUANTIA);
		query.setParameter(3, UtilDominios.DOMINIO_ROL_PERSONA);
		query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(5, UtilDominios.TIPO_TELEFONO_FAX);
		query.setParameter(6, dateFormat.format(fechaInicioDia));
		query.setParameter(7, dateFormat.format(fechaFinDia));
		query.setParameter(8, UtilDominios.DOMINIO_TIPO_EMPRESA);
		query.setParameter(9, UtilDominios.DOMINIO_TIPO_PERSONA);
		query.setParameter(10, UtilDominios.DOMINIO_TIPO_DOCUMENTO_PERSONA);
		query.setParameter(11, UtilConstantes.ESTADO_CASO_EN_CREACION);
		if(primerNombre != null)
			query.setParameter(12, "%".concat(primerNombre).concat("%"));
		if(primerApellido != null)
			query.setParameter(13, "%".concat(primerApellido).concat("%"));
		
		query.setParameter(14, UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
		query.setParameter(15, UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
		query.setParameter(16, UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);
		query.setParameter(17, UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);
		query.setParameter(18, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);
		query.setParameter(19, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
			
		return query.getResultList();
	}
	
	/**
	 * Metodo encargado de generar el reporte de 
	 * los casos casos pendientes por sorteo público de designación, estado PEN
	 * @param filtros
	 * @return List<ReporteCasosPendientesSorteoPublicoDesignacionDTO>
	 */	
    @SuppressWarnings("unchecked")	
	public List<ReporteCasosPendientesSorteoPublicoDesignacionDTO> getReporteCasosPendientesSorteoPublicoDesignacion() {					
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT ca.id_caso AS codigoCaso, ");
		jpqlQuery.append("ca.nombre AS nombreCaso, ");
		jpqlQuery.append("do.nombre AS tipoCuantia, ");
		jpqlQuery.append("ca.valor_pretensiones AS valorPretensiones, ");
		jpqlQuery.append("ser.nombre AS tipoServicio, ");
		jpqlQuery.append("ma.nombre AS materia, ");
		jpqlQuery.append("au.hora_inicio AS fechaSorteo, ");
		jpqlQuery.append("au.cantidad_principales AS arbitrosPrincipales, ");
		jpqlQuery.append("au.cantidad_suplentes AS arbitrosSuplentes, ");
		jpqlQuery.append("ca.preseleccion AS preseleccionData, ");
		jpqlQuery.append("CASE WHEN ca.arbitraje_consumo = 1 THEN 'SI'");
		jpqlQuery.append("ELSE 'NO' END AS consumo, ");
		jpqlQuery.append("CASE WHEN ca.quien_preselecciona IS NOT NULL THEN (select UPPER(d.nombre) from DOMINIO d where d.codigo = ca.quien_preselecciona)");
		jpqlQuery.append("ELSE ca.quien_preselecciona END AS tipoPreseleccion ");
		jpqlQuery.append("FROM AUDIENCIA au ");
		jpqlQuery.append("INNER JOIN CASO ca ON au.id_caso = ca.id_caso ");
		jpqlQuery.append("INNER JOIN SERVICIO ser ON ca.id_servicio = ser.id_servicio ");
		jpqlQuery.append("INNER JOIN MATERIA ma ON ca.id_materia = ma.id_materia ");
		jpqlQuery.append("INNER JOIN DOMINIO do ON ca.tipo_cuantia = do.codigo ");
		jpqlQuery.append("AND do.dominio in (?1, ?2) ");
		jpqlQuery.append("INNER JOIN NOMBRAMIENTO_PERSONA np ON ca.id_caso = np.id_caso ");
		jpqlQuery.append("AND np.metodo_de_nombramiento = ?3 ");
		jpqlQuery.append("AND np.estado_registro = ?4 ");
		jpqlQuery.append("WHERE au.estado = ?5 ");
		jpqlQuery.append("AND au.tipo_audiencia = ?6 ");
		jpqlQuery.append("AND au.estado_registro = ?4 ");
		jpqlQuery.append("AND au.id_sorteo is null ");
		jpqlQuery.append("ORDER BY au.hora_inicio ");
		
		Query query = em.createNativeQuery(jpqlQuery.toString(), ReporteCasosPendientesSorteoPublicoDesignacionDTO.class);		
		query.setParameter(1, UtilDominios.DOMINIO_TIPO_CUANTIA);
		query.setParameter(2, UtilDominios.DOMINIO_TIPO_CUANTIA_ARBITRAJE);
		query.setParameter(3, UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
		query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(5, UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
		query.setParameter(6, UtilDominios.TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_DE_DESIGNACION);
		
		return query.getResultList();
	}
    
    /**Genera datos para el reporte de reparto por abogado, muestra datos del caso y del abogado designado
	 * @param filtros
	 * @return List<ReporteRepartoPorAbogadoDTO>
	 */	    
    @SuppressWarnings("unchecked")
    public List<ReporteRepartoPorAbogadoDTO> getReporteRepartoPorAbogado(Map filtros){
		String abogado = filtros.get("abogado").toString();
		Date fechaIni = (Date)filtros.get("fechaIni");
		Date fechaFin = (Date)filtros.get("fechaFin");
		
		StringBuilder nativeQuery = new StringBuilder(); 
	
		nativeQuery.append(" SELECT     s.nombre as tipo, convert(varchar(15), c.id_Caso)                                                                            AS codigoCaso                     , ");
		nativeQuery.append("            c.nombre                                                                                                   AS nombreCaso                     , ");
		nativeQuery.append("            d.nombre                                                                                                   AS estadoCaso                     , ");
		nativeQuery.append("            convert(varchar(10), c.fecha_radicacion, 103)                                                              AS fechaRadicacionCaso            , ");
		nativeQuery.append("            convert(varchar(10), designacion.hora_inicio, 103)                                                         AS fechaAudienciaDesignacion      , ");
		nativeQuery.append("            convert(varchar(3), dbo.diasHabilesEntreDosFechas(fecha_radicacion, designacion.hora_inicio) - ISNULL(dias_suspension.dias, 0)) AS numeroDiasRadicacionDesignacion, ");
		nativeQuery.append("            CASE ");
		nativeQuery.append("                       WHEN dbo.diasHabilesEntreDosFechas(fecha_radicacion, designacion.hora_inicio) - ISNULL(dias_suspension.dias, 0) <= ps.valor ");
		nativeQuery.append("                       THEN 'SI' ");
		nativeQuery.append("                       ELSE 'NO' ");
		nativeQuery.append("            END                                                                                                        AS indicadorCumplimiento, ");
		nativeQuery.append("            convert(varchar(10), instalacion.hora_inicio, 103)                                                         AS fechaAudienciaInstalacion, ");
		nativeQuery.append("            concat(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) AS nombreAbogado, ");
		nativeQuery.append(CONSUMO);
		nativeQuery.append(", ");
		nativeQuery.append(SOLICITA_AMPARO);
		nativeQuery.append(", ");
		nativeQuery.append("            CASE WHEN c.medidas_cautelares = 1 THEN 'SI'" );
		nativeQuery.append("            ELSE 'NO' END AS medidasCautelares");
		nativeQuery.append(" FROM       ROL_PERSONA_CASO rpc ");
		nativeQuery.append("            INNER JOIN rol r ");
		nativeQuery.append("            ON         rpc.id_rol = r.id_rol ");
		nativeQuery.append("            INNER JOIN persona p ");
		nativeQuery.append("            ON         rpc.id_persona = p.id_persona ");
		nativeQuery.append("            INNER JOIN caso c ");
		nativeQuery.append("            ON         c.id_caso = rpc.id_caso ");
		nativeQuery.append("            INNER JOIN SERVICIO s ");
		nativeQuery.append("            ON         c.id_servicio = s.id_servicio ");
		nativeQuery.append("            INNER JOIN PARAMETRO_DE_SERVICIO ps ");
		nativeQuery.append("            ON         c.id_servicio = ps.id_servicio ");
		nativeQuery.append("            AND        ps.nombre     = ?1 ");
		nativeQuery.append("            INNER JOIN DOMINIO d ");
		nativeQuery.append("            ON         d.codigo  = c.estado_caso ");
		nativeQuery.append("            AND        d.dominio = ?2 ");
		nativeQuery.append("            OUTER apply ");
		nativeQuery.append("                       (SELECT top 1 hora_inicio ");
		nativeQuery.append("                       FROM    AUDIENCIA ad ");
		nativeQuery.append("                       WHERE   ad.id_caso = c.id_caso ");
		nativeQuery.append("                       AND     ad.tipo_audiencia IN (?3, ?4, ?5) ");
		nativeQuery.append("                       ) ");
		nativeQuery.append("                       designacion");
		nativeQuery.append("            OUTER apply ");
		nativeQuery.append("                       (SELECT top 1 hora_inicio ");
		nativeQuery.append("                       FROM    AUDIENCIA ai ");
		nativeQuery.append("                       WHERE   ai.id_caso        = c.id_caso ");
		nativeQuery.append("                       AND     ai.tipo_audiencia = ?6 ");
		nativeQuery.append("                       ) ");
		nativeQuery.append("                       instalacion ");
		nativeQuery.append("            OUTER apply ");
		nativeQuery.append("                       ( SELECT dbo.diasHabilesEntreDosFechas(fecha_inicial, fecha_final) + 1 AS dias ");
		nativeQuery.append("                       FROM    SUSPENSION s ");
		nativeQuery.append("                       WHERE   s.id_caso         = c.id_caso ");
		nativeQuery.append("                       AND     s.fecha_inicial  >= c.fecha_radicacion ");
		nativeQuery.append("                       AND     fecha_final      <= designacion.hora_inicio ");
		nativeQuery.append("                       AND     s.estado_registro = ?7 ");
		nativeQuery.append("                       ) ");
		nativeQuery.append("                       dias_suspension ");
		nativeQuery.append(" WHERE      rpc.estado_registro = ?7 ");
		nativeQuery.append(" AND        r.nombre            = ?8 ");
		nativeQuery.append(" AND cast( c.fecha_radicacion as DATE)  BETWEEN  ");
		nativeQuery.append(" cast( ?9 as DATE) AND cast( ?10 as DATE)  ");
		if(!abogado.equals("0"))
			nativeQuery.append(" AND        p.id_persona = ?11 ");
		nativeQuery.append(" ORDER BY   c.id_caso");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), ReporteRepartoPorAbogadoDTO.class);
		query.setParameter(1, UtilConstantes.INDICADOR_PROGRAMA_DESIGNA);
		query.setParameter(2, UtilDominios.DOMINIO_ESTADO_CASO);
		query.setParameter(3, UtilDominios.TIPO_AUDIENCIA_PREARBITRAL_DESIGNACION_POR_PARTES);
		query.setParameter(4, UtilDominios.TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_DE_DESIGNACION);
		query.setParameter(5, UtilDominios.TIPO_AUDIENCIA_DE_SORTEO_PUBLICO_INTERNACIONAL_DE_DESIGNACION);
		query.setParameter(6, UtilDominios.TIPO_AUDIENCIA_AUDIENCIA_DE_INSTALACION);
		query.setParameter(7, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(8, UtilDominios.ROL_PERSONA_ABOGADO);
		query.setParameter(9,new Date(fechaIni.getTime()));
		query.setParameter(10,new Date(fechaFin.getTime()));
				
		if(!abogado.equals("0"))
			query.setParameter(11,Long.valueOf(abogado));
		
		return query.getResultList();		
	}
    
    /**Genera datos para el reporte de aspirantes, los resultados los realiza agrupando por lista, mostrando los requisitosPersona que se 
     * cumplen en cada RequisitoLista
	 
	 * @return List<ReporteAspirantesDTO>
	 */	 
    public List<ReporteAspirantesDTO> getDatosReporteAspirantes(){
    	
  		StringBuilder jpqlQuery = new StringBuilder();
  		jpqlQuery.append(" SELECT COUNT(rp.requisito.idRequisito),");
  		jpqlQuery.append("  p.numeroDocumento,");
  		jpqlQuery.append("  p.primerNombreORazonSocial,");
  		jpqlQuery.append("  p.segundoNombre,");
  		jpqlQuery.append("  p.primerApellido,");
  		jpqlQuery.append("  p.segundoApellido, ");
  		jpqlQuery.append("  rl.lista.idLista ");
  		jpqlQuery.append(" FROM RequisitoPersona rp, RequisitoLista rl, Persona p ");
  		jpqlQuery.append(" WHERE ");
  		jpqlQuery.append(" rp.requisito.idRequisito = rl.requisito.idRequisito AND");
  		jpqlQuery.append(" rp.persona.idPersona = p.idPersona AND");
  		
  		// ******************** Estado de los registros ***********
  		jpqlQuery.append(" p.estadoRegistro = :estadoRegistro AND");
  		jpqlQuery.append(" rp.estadoRegistro = :estadoRegistro AND");
  		jpqlQuery.append(" rl.estadoRegistro = :estadoRegistro ");
  		// *********************************************************
  		
  		jpqlQuery.append(" GROUP BY p.numeroDocumento,p.primerNombreORazonSocial,p.segundoNombre,p.primerApellido,p.segundoApellido,rl.lista.idLista ");
  		jpqlQuery.append(" ORDER BY p.primerNombreORazonSocial ");	
  		
  		
  		Query query = em.createQuery(jpqlQuery.toString());
  		
  		query.setParameter("estadoRegistro", com.ccb.simasc.transversal.utilidades.UtilDominios.ESTADO_REGISTRO_ACTIVO);
  		
  		
  		List<Object[]> listObj = query.getResultList();
  		List<ReporteAspirantesDTO> resultado = new ArrayList<>();	

  		for (Object[] campo : listObj) {
  			ReporteAspirantesDTO aspiranteDTO = new ReporteAspirantesDTO();			
  			aspiranteDTO.setIdentificacion((nvl(campo[1],"").toString()));
  			aspiranteDTO.setNombres(nvl(campo[2].toString(),"") + " " + nvl(campo[3].toString(),"") + " " + nvl(campo[4].toString(),"") + " " + nvl(campo[5].toString(),""));
  			aspiranteDTO.setReqCumplidos((nvl(campo[0],"").toString()));
  			if(campo[0]!=null && campo[6]!=null){
  				aspiranteDTO.setReqNoCumplidos(String.valueOf((long)requisitoFacade.getRequisitos(campo[6].toString()) - (long)campo[0]));				
  			}else{
  				aspiranteDTO.setReqNoCumplidos("");
  			}
  			resultado.add(aspiranteDTO);
  		} 
  		return resultado;
  	}
    
    
    /**
	 * Metodo encargado de generar el reporte de los arbitros
	 * disponibles para sorteo 
	 * @param filtros
	 * @return List<ReporteArbitrosDispSorteoDTO>
	 */	
    @SuppressWarnings("unchecked")
    public List<ReporteArbitrosDispSorteoDTO> getReporteArbitrosDisponiblesSorteo(Map filtros) {
    	List<ReporteArbitrosDispSorteoDTO> reporteArbitrosDispSorteoDTOs = new ArrayList<ReporteArbitrosDispSorteoDTO>();
    	String codigo   = (String) filtros.get("codigo");
    	String lista  = (String) filtros.get("dominio");
    	String materia  = (String) filtros.get("materia");
    	String codCaso	= (String) filtros.get("codigoCaso");
		String tipoSorteo = (String) filtros.get("tipoSorteo");
 		Long idMateria = null;
		Long idCaso = null;
		ParametroServicioSorteo pServicioSorteo = manejadorParametroServicioSorteo.buscar(Long.valueOf(tipoSorteo));
		
		if(codigo != null && (codigo.equals(UtilDominios.TIPO_CUANTIA_MAYOR) || 
				codigo.equals(UtilDominios.TIPO_CUANTIA_INDETERMINADO))) {
			lista = UtilDominios.TIPO_LISTA_A;
		} else if(null!=codigo && codigo.equals(UtilDominios.TIPO_CUANTIA_MENOR)) {
			lista = UtilDominios.TIPO_LISTA_B;
		} 		
		
		if(!pServicioSorteo.getSorteoConMateria()){
			idMateria = UtilConstantes.ID_SIN_MATERIA;
		}else{
			if (materia != null) {
				idMateria = Long.valueOf(materia);
				}
		}

		if(codCaso != null) {
			idCaso = Long.valueOf(codCaso);
		}

		if(codCaso == null){
			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append(" SELECT DISTINCT ROW_NUMBER() OVER(ORDER BY p.primer_nombre_o_razon_social) AS consecutivo   , ");
			jpqlQuery.append("                 p.primer_nombre_o_razon_social AS primerNombre   , ");
			jpqlQuery.append("                 ISNULL(p.segundo_nombre, '')   AS segundoNombre  , ");
			jpqlQuery.append("                 p.primer_apellido              AS primerApellido , ");
			jpqlQuery.append("                 ISNULL(p.segundo_apellido, '') AS segundoApellido, ");
			jpqlQuery.append("                 case when pss.sorteo_con_materia = 1 then ");
			jpqlQuery.append("                 m.nombre ELSE '' END           AS materia        , ");			
			jpqlQuery.append("                 l.nombre                       AS lista            ");
			jpqlQuery.append(" FROM            PERSONA p ");
			jpqlQuery.append("                 INNER JOIN ROL_PERSONA rp ");
			jpqlQuery.append("                 ON              p.id_persona = rp.id_persona ");
			jpqlQuery.append("                 INNER JOIN ROL r ");
			jpqlQuery.append("                 ON              r.id_rol        = rp.id_rol ");
			jpqlQuery.append("                 AND             r.tipo_servicio in (?5, ?8) ");
			jpqlQuery.append("                 INNER JOIN PERSONA_SERVICIO_MATERIA psm ");
			jpqlQuery.append("                 ON              psm.id_persona = p.id_persona ");
			jpqlQuery.append("                 INNER JOIN SERVICIO s ");
			jpqlQuery.append("                 ON              psm.id_servicio = s.id_servicio ");
			jpqlQuery.append("                 AND             s.tipo          = r.tipo_servicio ");
			jpqlQuery.append("                 INNER JOIN MATERIA m ");
			jpqlQuery.append("                 ON              psm.id_materia = m.id_materia ");
			jpqlQuery.append("                 INNER JOIN PARAMETRO_SERVICIO_SORTEO pss ");
			jpqlQuery.append("                 ON              s.id_servicio   = pss.id_servicio ");
			jpqlQuery.append("                 AND             psm.id_servicio = pss.id_servicio ");
			jpqlQuery.append("                 AND             rp.id_rol       = pss.id_rol ");
			jpqlQuery.append("                 INNER JOIN ESTADO_PERSONA_ROL epr ");
			jpqlQuery.append("                 ON p.id_persona  = epr.id_persona ");
			jpqlQuery.append("                 AND epr.id_rol    = r.id_rol ");
			jpqlQuery.append("                 AND epr.id_servicio = s.id_servicio    ");
			jpqlQuery.append("                 LEFT JOIN lista l ");
			jpqlQuery.append("                 ON              l.id_lista             = rp.id_lista ");
			jpqlQuery.append(" WHERE           CAST(rp.fecha_inicio_vigencia AS DATE) <= CAST(GETDATE() AS DATE) ");
			jpqlQuery.append(" AND             rp.fecha_fin_vigencia          IS NULL ");
			jpqlQuery.append(" AND             rp.estado_registro  = ?4 ");
			jpqlQuery.append(" AND             psm.estado_registro = ?4 ");
			jpqlQuery.append(" AND             psm.fecha_fin_de_vigencia is null ");
			jpqlQuery.append(" AND             p.estado_registro   = ?4 ");
			jpqlQuery.append(" AND             epr.id_motivo       = ?6 ");
			jpqlQuery.append(" AND             psm.estado_para_sorteo = ?7 ");	
			jpqlQuery.append(" AND             pss.id_parametros_servicios_sorteo = ?1 ");
			
			if(!pServicioSorteo.getSorteoConMateria() || materia != null){
				jpqlQuery.append(" AND             psm.id_materia      = ?2 ");
			}
			
			if (pServicioSorteo.getSorteoConLista() && codigo != null) {
				jpqlQuery.append(" AND             l.nombre            = ?3");
			}
	
			Query q = em.createNativeQuery(jpqlQuery.toString(), ReporteArbitrosDispSorteoDTO.class); 
			q.setParameter(1, tipoSorteo);
			q.setParameter(2, idMateria);			
			q.setParameter(3, lista);
			q.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			q.setParameter(5, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
			q.setParameter(6, UtilDominios.ESTADO_ARBITROS_HABILITADO);
			q.setParameter(7, UtilDominios.ESTADO_PERSONA_SORTEO_ACTIVO);
			q.setParameter(8, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);
			
			reporteArbitrosDispSorteoDTOs = q.getResultList();
		}else{
			Caso caso = manejadorCaso.buscar(idCaso);
			List<Persona> personas;
			if(!caso.getPreseleccion()){
				personas = manejadorPersonaServicioMateria.consultaPersonasParaSorteo(caso, false, tipoSorteo);			
				
			}else{
				ParametroSorteo parametrosSort = sorteoFacade.consultarParametrosSorteo();
				for (ParametroServicioSorteo paramServicio : parametrosSort.getParametroServicioSorteoList()) {
					if (paramServicio.getIdServicio() == caso.getIdServicio()) {
						caso.setParametroServSorteo(paramServicio);
						break;
					}
				}
				caso.setNombramientoSorteo(this.sorteoFacade.obtenerNombramientoPersonaCaso(caso));

				personas = sorteoFacade.obtenerPreseleccionados(caso, null);
			}
			int consecutivo = 1;
			for (Persona persona : personas) {
				ReporteArbitrosDispSorteoDTO reporteArbitrosDispSorteoDTO = new ReporteArbitrosDispSorteoDTO();
				Long idMateriaPersona = manejadorPersonaServicioMateria.buscar(persona.getIdPersonaServicioMateria()).getIdMateria();
				String nombreMateria = manejadorMateria.buscar(idMateriaPersona).getNombre();
				
				reporteArbitrosDispSorteoDTO.setConsecutivo(consecutivo++);
				reporteArbitrosDispSorteoDTO.setPrimerNombre(persona.getPrimerNombreORazonSocial());
				reporteArbitrosDispSorteoDTO.setSegundoNombre(persona.getSegundoNombre() == null ? "" : persona.getSegundoNombre());
				reporteArbitrosDispSorteoDTO.setPrimerApellido(persona.getPrimerApellido());
				reporteArbitrosDispSorteoDTO.setSegundoApellido(persona.getSegundoApellido() == null ? "" : persona.getSegundoApellido());
				reporteArbitrosDispSorteoDTO.setMateria(nombreMateria);
				reporteArbitrosDispSorteoDTO.setPreseleccion(caso.getPreseleccion() ? "SI" : "NO");
				reporteArbitrosDispSorteoDTO.setTipoPreseleccion(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_QUIEN_PRESELECCIONA,caso.getQuienPreselecciona()));
				reporteArbitrosDispSorteoDTO.setDesignadoPreviamente(persona.isMarcaAzulSorteo() ? "SI" : "NO");
								
				reporteArbitrosDispSorteoDTOs.add(reporteArbitrosDispSorteoDTO);
			}
		}		
		return reporteArbitrosDispSorteoDTOs;
 	}
    

    /**
	 * Metodo encargado de generar el reporte del promedio
	 * de los minutos de las transcripciones por auxiliar 
	 * @param filtros
	 * @return List<ReporteAuxiliarPromedioTranscripcionDTO>
	 */	    
	@SuppressWarnings("unchecked")
	public List<ReporteAuxiliarPromedioTranscripcionDTO> getReporteAuxiliarPromedioTranscripciones(Map filtros) {
		List<ReporteAuxiliarPromedioTranscripcionDTO> reporteAuxiliarPromedioTranscripcionDTOs = new ArrayList<ReporteAuxiliarPromedioTranscripcionDTO>();
		DateFormat dateFormat = new SimpleDateFormat(UtilConstantes.FORMATO_FECHA_ANIO_MES_DIA);
		String fechaIncial = (String) filtros.get("fechaInicial");
		String fechaHoraIncial = fechaIncial + UtilConstantes.CARACTER_ESPACIO + UtilConstantes.FORMATO_HORA_INICIO;
		String fechaFinal = (String) filtros.get("fechaFinal");
		String fechaHoraFinal = fechaFinal + UtilConstantes.CARACTER_ESPACIO + UtilConstantes.FORMATO_HORA_FIN;
		String idPersona = (String) filtros.get("idPersona");

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT ");
		nativeQuery.append(
				"  CONCAT(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) nombre_auxiliar, "); // 0
		nativeQuery.append("  COUNT(d.id_audiencia) numero_audiencias, "); // 1
		nativeQuery.append("  SUM(t.tiempo_transcrito) tiempo_transcrito "); // 2
		nativeQuery.append("FROM TRANSCRIPCION t ");
		nativeQuery.append("JOIN DOCUMENTO d ");
		nativeQuery.append("  ON t.id_doc_audio_fuente = d.id_documento ");
		nativeQuery.append("  AND d.tipo_documento = ?5 ");
		nativeQuery.append("  AND d.estado_registro = ?4 ");
		nativeQuery.append("JOIN AUDIENCIA a ");
		nativeQuery.append("  ON d.id_audiencia = a.id_audiencia ");
		nativeQuery.append("  AND a.estado_registro = ?4 ");
		nativeQuery.append("JOIN CASO c ");
		nativeQuery.append("  ON a.id_caso = c.id_caso ");
		nativeQuery.append("  AND c.estado_registro = ?4 ");
		nativeQuery.append("JOIN PERSONA p ");
		nativeQuery.append("  ON t.id_persona = p.id_persona ");
		nativeQuery.append("  AND p.estado_registro = ?4 ");
		nativeQuery.append("  AND p.estado_persona = ?4 ");
		nativeQuery.append("JOIN ROL_PERSONA rp ");
		nativeQuery.append("  ON p.id_persona = rp.id_persona ");
		nativeQuery.append("  AND rp.estado_registro = ?4 ");
		nativeQuery.append("  AND rp.id_rol = (SELECT ");
		nativeQuery.append("    r.id_rol ");
		nativeQuery.append("  FROM ROL r ");
		nativeQuery.append("  WHERE r.nombre = ?6 ");
		nativeQuery.append("  AND r.estado_registro = ?4) ");
		nativeQuery.append("WHERE t.fecha_entrega_transcripcion IS NOT NULL ");
		nativeQuery.append("AND t.fecha_entrega_transcripcion BETWEEN ?1 AND ?2 ");

		if (idPersona != null && !idPersona.equals("")) {
			nativeQuery.append("AND t.id_persona = ?3 ");
		}

		nativeQuery.append("GROUP BY p.primer_nombre_o_razon_social, ");
		nativeQuery.append("         p.segundo_nombre, ");
		nativeQuery.append("         p.primer_apellido, ");
		nativeQuery.append("         p.segundo_apellido ");

		Query query = em.createNativeQuery(nativeQuery.toString());
		query.setParameter(1, fechaHoraIncial);
		query.setParameter(2, fechaHoraFinal);
		query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(5, UtilDominios.TIPO_DOCUMENTO_DIG_AUDIO);
		query.setParameter(6, UtilDominios.ROL_PERSONA_AUXILIAR_ADM);

		if (idPersona != null && !idPersona.equals("")) {
			query.setParameter(3, idPersona);
		}

		List<Object[]> objects = query.getResultList();

		try {
			Date initialDate = dateFormat.parse(fechaIncial);
			Date finalDate = dateFormat.parse(fechaFinal);

			int diasHabilesPeriodo = obtenerDiasHabilesEntreFechas(initialDate, finalDate);

			for (Object[] object : objects) {
				ReporteAuxiliarPromedioTranscripcionDTO auxiliarPromedioTranscripcionDTO = new ReporteAuxiliarPromedioTranscripcionDTO();
				auxiliarPromedioTranscripcionDTO
						.setNombreAuxiliar((String) nvl(object[0], UtilConstantes.CADENA_VACIA));
				auxiliarPromedioTranscripcionDTO.setPeriodo(fechaIncial + " - " + fechaFinal);
				auxiliarPromedioTranscripcionDTO.setNumeroAudienciasAtendidas(((Number) nvl(object[1], 0)).longValue());

				if (object[2] != null) {
					Double promedio = ((Number) object[2]).doubleValue() / diasHabilesPeriodo;
					auxiliarPromedioTranscripcionDTO.setPromedioDiarioMinutos(formatearNumeroDecimal(promedio, 2));
				}

				reporteAuxiliarPromedioTranscripcionDTOs.add(auxiliarPromedioTranscripcionDTO);
			}

		} catch (ParseException e) {
			throw new SimascException(EXCEPCION_CALCULO_DIAS_HABILES);
		}

		return reporteAuxiliarPromedioTranscripcionDTOs;
	}
	
	
	/**
	 * Metodo encargado de generar el reporte de transcripciones de documentos,
	 * con datos del caso, audiencia, documento y secretario del caso
	 * 
	 * @param filtros
	 * @return List<ReporteTranscripcionesDTO>
	 */
	public List<ReporteTranscripcionesDTO> getReporteTranscripciones(Map filtros) {

		String codigoCaso = nvl(filtros.get("codigoCaso"), "").toString();
		String nombreCaso = nvl(filtros.get("nombreCaso"), "").toString();
		Date fechaIni = filtros.get("fechaIni") == null ? null : (Date) filtros.get("fechaIni");
		Date fechaFin = filtros.get("fechaFin") == null ? null : (Date) filtros.get("fechaFin");
		String auxiliar = filtros.get("auxiliar").toString();

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT ");
		jpqlQuery.append("  c.id_caso, "); // 0
		jpqlQuery.append("  c.nombre, "); // 1
		jpqlQuery.append(
				"  concat(p_aux.primer_nombre_o_razon_social, ' ', p_aux.segundo_nombre, ' ', p_aux.primer_apellido, ' ', p_aux.segundo_apellido) auxiliar_caso, "); // 2
		jpqlQuery.append("  (SELECT ");
		jpqlQuery.append(
				"    concat(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) ");
		jpqlQuery.append("  FROM ROL_PERSONA_CASO rpc ");
		jpqlQuery.append("  JOIN ROL r ");
		jpqlQuery.append("    ON rpc.id_rol = r.id_rol ");
		jpqlQuery.append("  JOIN PERSONA p ");
		jpqlQuery.append("    ON rpc.id_persona = p.id_persona ");
		jpqlQuery.append("  WHERE rpc.id_caso = c.id_caso ");
		jpqlQuery.append("  AND rpc.estado = '" + UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO + "' ");
		jpqlQuery.append("  AND rpc.estado_registro = ?6 ");
		jpqlQuery.append("  AND r.nombre = '" + UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL + "' ");
		jpqlQuery.append("  AND r.estado_registro = ?6 ");
		jpqlQuery.append("  AND p.estado_registro = ?6 ");
		jpqlQuery.append("  AND p.estado_persona = ?6) ");
		jpqlQuery.append("  secretario_caso, "); // 3
		jpqlQuery.append("  (t.tiempo_final - t.tiempo_inicial - t.tiempo_transcrito) / 60 tiempo_pendiente_transcribir, "); // 4
		jpqlQuery.append("  d.fecha_de_grabacion, "); // 5
		jpqlQuery.append("  t.fecha_inicio_transcripcion, "); // 6
		jpqlQuery.append("  t.fecha_entrega_transcripcion, "); // 7
		jpqlQuery.append("  t.id_transcripcion, "); // 8
		jpqlQuery.append("  (t.tiempo_final - t.tiempo_inicial) / 60 tiempo_a_transcribir "); // 9
		jpqlQuery.append("FROM TRANSCRIPCION t ");
		jpqlQuery.append("JOIN DOCUMENTO d ");
		jpqlQuery.append("  ON t.id_doc_audio_fuente = d.id_documento ");
		jpqlQuery.append("JOIN AUDIENCIA a ");
		jpqlQuery.append("  ON d.id_audiencia = a.id_audiencia ");
		jpqlQuery.append("JOIN CASO c ");
		jpqlQuery.append("  ON a.id_caso = c.id_caso ");
		jpqlQuery.append("LEFT JOIN ROL_PERSONA_CASO rpc ");
		jpqlQuery.append("  ON c.id_caso = rpc.id_caso ");
		jpqlQuery.append("  AND rpc.estado_registro = ?6 ");
		jpqlQuery.append("  AND rpc.estado = '" + UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO + "' ");
		jpqlQuery.append("  AND rpc.id_rol = (SELECT ");
		jpqlQuery.append("                     r.id_rol ");
		jpqlQuery.append("                   FROM ROL r ");
		jpqlQuery.append("                   WHERE r.nombre = '" + UtilDominios.ROL_PERSONA_AUXILIAR_ADM + "' ");
		jpqlQuery.append("                   AND r.estado_registro = ?6 ) ");
		jpqlQuery.append("LEFT JOIN PERSONA p_aux ");
		jpqlQuery.append("  ON rpc.id_persona = p_aux.id_persona ");
		jpqlQuery.append("  AND p_aux.estado_registro = ?6 ");
		jpqlQuery.append("  AND p_aux.estado_persona = '" + UtilDominios.ESTADO_PERSONA_ACTIVO + "' ");
		jpqlQuery.append("WHERE t.fecha_entrega_transcripcion IS NOT NULL ");
		jpqlQuery.append("AND t.estado_registro = ?6 ");
		jpqlQuery.append("AND d.estado_registro = ?6 ");
		jpqlQuery.append("AND d.tipo_documento = '" + UtilDominios.TIPO_DOCUMENTO_DIG_AUDIO + "' ");
		jpqlQuery.append("AND a.estado_registro = ?6 ");
		jpqlQuery.append("AND c.estado_registro = ?6 ");

		if (!codigoCaso.equals("") && !codigoCaso.equals("0"))
			jpqlQuery.append("AND c.id_caso = ?1 ");

		if (!nombreCaso.equals(""))
			jpqlQuery.append("AND LOWER(REPLACE(c.nombre, ' ', '')) LIKE ?2 ");

		if (fechaIni != null && fechaFin != null)
			jpqlQuery.append("AND t.fecha_entrega_transcripcion BETWEEN ?3 AND ?4 ");

		if (!auxiliar.equals("0")) {
			jpqlQuery.append("AND p_aux.id_persona = ?5 ");
		}

		jpqlQuery.append("ORDER BY c.id_caso ");

		Query query = em.createNativeQuery(jpqlQuery.toString());

		if (!codigoCaso.equals("") && !codigoCaso.equals("0"))
			query.setParameter(1, Long.valueOf(codigoCaso));

		if (!nombreCaso.equals(""))
			query.setParameter(2, "%" + nombreCaso.toLowerCase().replaceAll("\\s", "") + "%");

		if (fechaIni != null && fechaFin != null) {
			DateFormat dateFormat = new SimpleDateFormat(UtilConstantes.FORMATO_FECHA_ANIO_MES_DIA);
			String fechaInicio = dateFormat.format(fechaIni);
			String fechaFinal = dateFormat.format(fechaFin);

			fechaInicio = fechaInicio + UtilConstantes.CARACTER_ESPACIO + UtilConstantes.FORMATO_HORA_INICIO;
			fechaFinal = fechaFinal + UtilConstantes.CARACTER_ESPACIO + UtilConstantes.FORMATO_HORA_FIN;

			query.setParameter(3, fechaInicio);
			query.setParameter(4, fechaFinal);
		}

		if (!auxiliar.equals("0")) {
			query.setParameter(5, auxiliar);
		}

		query.setParameter(6, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		List<Object[]> listObj = query.getResultList();
		List<ReporteTranscripcionesDTO> datosReporte = new ArrayList<>();

		for (Object[] campo : listObj) {
			ReporteTranscripcionesDTO dto = new ReporteTranscripcionesDTO();
			dto.setCodigoCaso(nvl(campo[0], UtilConstantes.CADENA_VACIA).toString());
			dto.setNombreCaso(nvl(campo[1], UtilConstantes.CADENA_VACIA).toString());
			dto.setAuxiliar(nvl(campo[2], UtilConstantes.CADENA_VACIA).toString());
			dto.setSecretario(nvl(campo[3], UtilConstantes.CADENA_VACIA).toString());
			dto.setMinutosPendientesTranscripcion(campo[4] != null
					? UtilOperaciones.formatearNumeroDecimal(((BigDecimal) campo[4]).doubleValue(), 2).toString()
					: UtilConstantes.CADENA_VACIA);
			dto.setFechaGrabacion(
					campo[5] != null ? formatearFechaReporte((Date) campo[5]) : UtilConstantes.CADENA_VACIA);
			dto.setFechaInicioTranscripcion(
					campo[6] != null ? formatearFechaReporte((Date) campo[6]) : UtilConstantes.CADENA_VACIA);
			if (campo[5] != null && campo[6] != null) {
				int difGrabacionIniTranscripcion = obtenerDiasHabilesEntreFechas((Date) campo[5], (Date) campo[6]);
				dto.setDifGrabacionIniTranscripcion(String.valueOf(difGrabacionIniTranscripcion > 0
						? difGrabacionIniTranscripcion - 1 : difGrabacionIniTranscripcion));
			}
			dto.setFechaEntregaTranscripcion(
					campo[7] != null ? formatearFechaReporte((Date) campo[7]) : UtilConstantes.CADENA_VACIA);
			if (campo[5] != null && campo[7] != null) {
				int difGrabacionEntTranscripcion = obtenerDiasHabilesEntreFechas((Date) campo[5], (Date) campo[7]);
				dto.setDifGrabacionEntTranscripcion(String.valueOf(difGrabacionEntTranscripcion > 0
						? difGrabacionEntTranscripcion - 1 : difGrabacionEntTranscripcion));
			}
			dto.setMinutosATranscribir(campo[9] != null
					? UtilOperaciones.formatearNumeroDecimal(((BigDecimal) campo[9]).doubleValue(), 2).toString()
					: UtilConstantes.CADENA_VACIA);
			datosReporte.add(dto);
		}

		return datosReporte;
	}
	
	/**
	 * Asigna un estado Sorteable o No sorteable según el nombre del motivo asociado a  un arbitro
	 * @param arbitroDTO
	 * @return
	 */
	public List<ReporteEstadosArbitroHistoricoEstadoDTO> consultarEstadosServicioMateriaArbitroMotivo(ArbitroDTO arbitroDTO) {
		
		List<ReporteEstadosArbitroHistoricoEstadoDTO> estadosArbitro = this.consultarEstadosServicioMateriaArbitro(arbitroDTO);

		if (estadosArbitro!=null && !estadosArbitro.isEmpty()) {
			for (Iterator iterator = estadosArbitro.iterator(); iterator
					.hasNext();) {
				ReporteEstadosArbitroHistoricoEstadoDTO reporteEstado = (ReporteEstadosArbitroHistoricoEstadoDTO) iterator
						.next();
				if (!reporteEstado.getMotivo().equals(UtilConstantes.ARBITRO_HABILITADO_SERVICIO_MATERIA)) {
					reporteEstado.setEstado(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ESTADO_ARBITROS, 
							UtilDominios.DOMINIO_ESTADO_NO_SORTEABLE));
				}else {
					reporteEstado.setEstado(dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ESTADO_ARBITROS, 
							UtilDominios.DOMINIO_ESTADO_SORTEABLE));
					reporteEstado.setMotivo(UtilConstantes.CADENA_VACIA);
				}
			}
		}
		return estadosArbitro;
	}
    /**
     * Consulta el estado del arbitro seleccionado en cada uno de los servicio-materia del arbitro que se pasa como parámetro, 
     * lo ordena por la fecha del estado.
     * @param arbitroDTO
     * @return List<ReporteEstadosArbitroEstadoArbitroEnServicioMateriaDTO> La lista de los arbitros (Vacia si no hay asignaciones para el arbitro
     * @throws SimascException
     */
	public List<ReporteEstadosArbitroHistoricoEstadoDTO> consultarEstadosServicioMateriaArbitro(ArbitroDTO arbitroDTO){
    	
    	Persona arbitro = manejadorPersona.buscar(arbitroDTO.getIdPersona());
    	if(arbitro == null){
    		throw new SimascException(EXCEPCION_ARBITRO_CONSULTADO_NULO);
    	}
    	StringBuilder nativeQuery = new StringBuilder();		
		nativeQuery.append("SELECT     distinct s.nombre as servicio, dr.nombre as rol, ");
		nativeQuery.append("           m.nombre  AS materia      , ");
		nativeQuery.append("           de.nombre AS estado          , ");
		nativeQuery.append("           CASE emd.estado ");
		nativeQuery.append("                      WHEN '" + UtilDominios.DOMINIO_ESTADO_NO_SORTEABLE + "' ");
		nativeQuery.append("                      THEN ");
		nativeQuery.append("                                 CASE hem.id_motivo ");
		nativeQuery.append("                                            WHEN '"+ UtilDominios.ESTADO_ARBITROS_HABILITADO + "' ");
		nativeQuery.append("                                            THEN dme.nombre ");
		nativeQuery.append("                                            ELSE do.nombre ");
		nativeQuery.append("                                 END ");
		nativeQuery.append("                      ELSE '' ");
		nativeQuery.append("           END       AS motivo       , ");
		nativeQuery.append("           cast(hem.fecha_ultima_modificacion as date) as fechaAsignacion ");
		nativeQuery.append("FROM       HISTORICO_ESTADO_MOTIVO_PERSONA hem ");
		nativeQuery.append("           INNER JOIN servicio s "); 
		nativeQuery.append("           on s.id_servicio = hem.id_servicio ");		
		nativeQuery.append("           INNER JOIN ROL r ");
		nativeQuery.append("           ON         r.id_rol        = hem.id_rol ");
		nativeQuery.append("           AND        r.tipo_servicio in (?3, ?6) ");
		nativeQuery.append("           INNER JOIN dominio dr ");
		nativeQuery.append("           ON         r.nombre = dr.codigo ");
		nativeQuery.append("           AND        dr.dominio  = ?5 ");
		nativeQuery.append("           INNER JOIN MATERIA m ");
		nativeQuery.append("           ON         hem.id_materia = m.id_materia ");
		nativeQuery.append("           INNER JOIN ESTADO_MOTIVO_DISPONIBILIDAD emd ");
		nativeQuery.append("           ON         hem.estado_para_sorteo = emd.estado_para_sorteo ");
		nativeQuery.append("           AND        hem.id_motivo          = emd.id_motivo_estado ");
		nativeQuery.append("           INNER JOIN dominio de ");
		nativeQuery.append("           ON         emd.estado = de.codigo ");
		nativeQuery.append("           AND        de.dominio  = ?2 ");
		nativeQuery.append("           LEFT JOIN dominio do ");
		nativeQuery.append("           ON         hem.id_motivo = do.codigo ");
		nativeQuery.append("           AND        do.dominio    = ?4 ");
		nativeQuery.append("           LEFT JOIN dominio dme ");
		nativeQuery.append("           ON         hem.motivo_estado_sorteo = dme.codigo ");
		nativeQuery.append("           AND        dme.dominio    = ?4 ");
		nativeQuery.append("WHERE      id_persona            = ?1 ");
		nativeQuery.append("order by   rol, materia, fechaAsignacion ");
		
		Query q = em.createNativeQuery(nativeQuery.toString(), ReporteEstadosArbitroHistoricoEstadoDTO.class);
		q.setParameter(1, arbitroDTO.getIdPersona()); 		
		q.setParameter(2, UtilDominios.DOMINIO_ESTADO_ARBITROS);
		q.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		q.setParameter(4, UtilDominios.DOMINIO_MOTIVO_ESTADO);
		q.setParameter(5, UtilDominios.DOMINIO_ROL_PERSONA);
		q.setParameter(6, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);
		
		return q.getResultList();
    }
    
    /**
     * Consulta la informacion de los casos en los que se encuentra asignado el arbittro seleccionado. Ordena por el código del caso.
     * @param arbitroDTO
     * @return
     * @throws List<ReporteEstadosArbitroCasosArbitroDTO>
     */
    public List<ReporteEstadosArbitroCasosArbitroDTO> consultarCasosArbitro(
    		ArbitroDTO arbitroDTO) {	    	
    	
    	StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT ");
		jpqlQuery.append("c.id_caso AS codigoCaso ");
		jpqlQuery.append(", s.nombre AS tipoCaso ");
		jpqlQuery.append(", c.nombre AS nombreCaso ");
		jpqlQuery.append(", fecha_radicacion AS fechaRadicacionCaso ");
		jpqlQuery.append(", m.nombre as materiaCaso ");
		jpqlQuery.append(", c.valor_pretensiones AS cuantia ");
		jpqlQuery.append(", de.nombre AS metodoNombramiento ");
		jpqlQuery.append(", dn.nombre AS tipoNombramiento ");
		jpqlQuery.append(", CASE ");
		jpqlQuery.append("  WHEN c.preseleccion = 1 THEN 'SI' ");
		jpqlQuery.append("  ELSE 'NO' ");
		jpqlQuery.append("  END AS preseleccion");
		jpqlQuery.append(", CASE " );
		jpqlQuery.append("  WHEN c.quien_preselecciona IS NOT NULL THEN (select UPPER(d.nombre) from DOMINIO d where d.codigo = c.quien_preselecciona) ");
		jpqlQuery.append("  ELSE c.quien_preselecciona ");
		jpqlQuery.append("  END AS tipoPreseleccion");
		jpqlQuery.append(", (select max(fecha_de_asignacion) from EVENTO_ROL_PERSONA_CASO er where rp.id_persona = er.id_persona ");
		jpqlQuery.append("AND c.id_caso = er.id_caso AND er.estado_asignado = ?3 ");
		jpqlQuery.append("AND rp.id_rol = er.id_rol) as fechaNombramiento, ");
		jpqlQuery.append(CONSUMO);
		jpqlQuery.append(", dr.nombre as rol ");
		jpqlQuery.append("FROM CASO c ");
		jpqlQuery.append("INNER JOIN SERVICIO s ON c.id_servicio = s.id_servicio ");
		jpqlQuery.append("INNER JOIN MATERIA m ON c.id_materia = m.id_materia ");
		jpqlQuery.append("INNER JOIN ROL_PERSONA_CASO rp ON rp.id_caso = c.id_caso ");
		jpqlQuery.append("INNER JOIN ROL r ON rp.id_rol = r.id_rol ");
		jpqlQuery.append("INNER JOIN DOMINIO de ON de.codigo = rp.metodo_nombramiento AND de.dominio = ?8 ");
		jpqlQuery.append("INNER JOIN DOMINIO dn ON dn.codigo = rp.tipo_nombramiento AND dn.dominio = ?10 ");
		jpqlQuery.append("INNER JOIN DOMINIO dr ON dr.codigo = r.nombre AND dr.dominio = ?12 ");
		jpqlQuery.append("WHERE c.estado_caso <> ?5 ");
		jpqlQuery.append("AND c.estado_registro = ?6 ");
		jpqlQuery.append("AND rp.estado in (?1, ?2, ?3, ?4) ");
		jpqlQuery.append("AND rp.estado_registro = ?6 ");
		jpqlQuery.append("AND rp.id_persona = ?7 ");
		jpqlQuery.append("AND r.tipo_servicio in (?9, ?11) ");
		jpqlQuery.append("AND rp.id_rol in (select id_rol from PARAMETRO_SERVICIO_SORTEO) ");
		jpqlQuery.append(" order by c.id_caso");
				
		Query q = em.createNativeQuery(jpqlQuery.toString(), ReporteEstadosArbitroCasosArbitroDTO.class);
		q.setParameter(1, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		q.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO);
		q.setParameter(3, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		q.setParameter(4, UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO);
		q.setParameter(5, UtilDominios.ESTADO_CASO_CERRADO);
		q.setParameter(6, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(7, arbitroDTO.getIdPersona());
		q.setParameter(8, UtilDominios.METODOS_DE_NOMBRAMIENTO);
		q.setParameter(9, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		q.setParameter(10, UtilDominios.DOMINIO_TIPO_NOMBRAMIENTO);
    	q.setParameter(11, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);
		q.setParameter(12, UtilDominios.DOMINIO_ROL_PERSONA);
		return q.getResultList();
    }
    
    public List<ReporteEstadosArbitroRolDTO> consultarRolesArbitro(ArbitroDTO arbitroDTO) {
    	StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append("select distinct de.nombre as rol ");
    	jpqlQuery.append(", l.nombre as lista ");
    	jpqlQuery.append(", rp.fecha_inicio_vigencia as fechaAsignacion ");
    	jpqlQuery.append(", rp.fecha_fin_vigencia as fechaFinalizacion ");
    	jpqlQuery.append("from rol_persona rp ");
    	jpqlQuery.append("inner join rol r on r.id_rol = rp.id_rol and r.tipo_servicio in (?3, ?4) ");
    	jpqlQuery.append("inner join PARAMETRO_SERVICIO_SORTEO ps on r.id_rol = ps.id_rol ");
    	jpqlQuery.append("left join lista l on l.id_lista = rp.id_lista ");
    	jpqlQuery.append("INNER JOIN DOMINIO de on de.codigo = r.nombre AND de.dominio = ?1 ");
    	jpqlQuery.append("where rp.id_persona = ?2 ");
    	jpqlQuery.append("order by de.nombre ");
    	
    	Query q = em.createNativeQuery(jpqlQuery.toString(), ReporteEstadosArbitroRolDTO.class);
    	q.setParameter(1, UtilDominios.DOMINIO_ROL_PERSONA);
    	q.setParameter(2, arbitroDTO.getIdPersona());
    	q.setParameter(3, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
    	q.setParameter(4, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);
    	
    	return q.getResultList();
    }
    
    public List<ReporteEstadosArbitroMateriaDTO> consultarMateriasArbitro(ArbitroDTO arbitroDTO) {
    	StringBuilder jpqlQuery = new StringBuilder();
    	jpqlQuery.append("select dr.nombre as rol, m.nombre as nombreMateria ");
    	jpqlQuery.append(", psm.fecha_inicio_vigencia as fechaAsignacion ");
    	jpqlQuery.append(", psm.fecha_fin_de_vigencia as fechaFinalizacion ");
    	jpqlQuery.append(", de.nombre AS disponibleSorteo ");
    	jpqlQuery.append("from rol_persona rp ");
    	jpqlQuery.append("inner join rol r on r.id_rol = rp.id_rol and r.tipo_servicio in (?5, ?7) ");
    	jpqlQuery.append("inner join PARAMETRO_SERVICIO_SORTEO ps on rp.id_rol = ps.id_rol ");
    	jpqlQuery.append("inner join PERSONA_SERVICIO_MATERIA psm on ps.id_servicio = psm.id_servicio and psm.id_persona = rp.id_persona ");
    	jpqlQuery.append("inner join materia m on m.id_materia = psm.id_materia ");
    	jpqlQuery.append("INNER JOIN DOMINIO dr on dr.codigo = r.nombre AND dr.dominio = ?2 ");
    	jpqlQuery.append("left JOIN ESTADO_PERSONA_ROL e on rp.id_persona = e.id_persona and e.id_rol = r.id_rol ");
    	jpqlQuery.append("left join ESTADO_MOTIVO_DISPONIBILIDAD em on em.id_motivo_estado = e.id_motivo and em.estado_para_sorteo = psm.estado_para_sorteo ");
    	jpqlQuery.append("left join DOMINIO de on de.codigo = em.estado and de.dominio = ?6 ");
    	jpqlQuery.append("where rp.id_persona = ?3 ");
    	jpqlQuery.append("and rp.estado_registro = ?4 ");
    	jpqlQuery.append("order by dr.nombre ");
    	
    	Query q = em.createNativeQuery(jpqlQuery.toString(), ReporteEstadosArbitroMateriaDTO.class);
    	q.setParameter(1, UtilDominios.ESTADO_ARBITROS_HABILITADO);
    	q.setParameter(2, UtilDominios.DOMINIO_ROL_PERSONA);
    	q.setParameter(3, arbitroDTO.getIdPersona());
    	q.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	q.setParameter(5, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
    	q.setParameter(6, UtilDominios.DOMINIO_ESTADO_ARBITROS);
    	q.setParameter(7, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);
    	return q.getResultList();
    }
    
    /**
	 * Método encargado de obtener la lista de árbitros de un caso nombrados por
	 * sorteo y ordenados por el tipo de nombramiento al momento de la
	 * realización del mismo
	 * 
	 * @param sorteo
	 * @param caso
	 * @return
	 */
	private List<RolPersonaCaso> obtenerArbitrosSorteoCaso(Sorteo sorteo, Caso caso) {
		List<RolPersonaCaso> arbitrosSelec = manejadorRolPersonaCaso.obtenerArbitrosSorteoCaso(caso.getIdCaso(),
				sorteo.getIdSorteo());

		// Obtiene el tipo de nombramiento del árbitro resultado del sorteo y no
		// el actual
		for (RolPersonaCaso rolPersonaCaso : arbitrosSelec) {
			rolPersonaCaso.obtenerNombramientoPorSorteo();
		}

		List<RolPersonaCaso> arbitrosSelecOrdenados = new ArrayList<>(arbitrosSelec);

		// Ordena los árbitros de acuerdo al nombramiento resultado del sorteo
		Collections.sort(arbitrosSelecOrdenados, new Comparator<RolPersonaCaso>() {

			@Override
			public int compare(RolPersonaCaso o1, RolPersonaCaso o2) {
				return o1.getNombramientoSorteo().compareTo(o2.getNombramientoSorteo());
			}
		});
		return arbitrosSelecOrdenados;
	}
	
	/**
	 * Metodo encargado de generar el reporte de 
	 * los arbitros disponibles en un caso
	 * @param filtros
	 * @return List<ReporteCasosSorteadosDTO>
	 */	
    @SuppressWarnings("unchecked")	
	public List<ReporteArbitrosDisponiblesParaSorteosDTO> getReporteArbitrosDisponiblesSorteo (Long numeroCaso,Date fechasorteo) {
    	List<ReporteArbitrosDisponiblesParaSorteosDTO> arbitrosDisponibles = new ArrayList<>();
    	
    	List<SorteoArbitrosDisponiblesDTO> sorteos = manejadorSorteo.consultarSorteosArbitrosDisponibles(numeroCaso, fechasorteo);
    	
    	for (SorteoArbitrosDisponiblesDTO sorteo : sorteos) {
    		
    		ReporteArbitrosDisponiblesParaSorteosDTO reporteDto = new ReporteArbitrosDisponiblesParaSorteosDTO();
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");	            
    		reporteDto.setFechaSorteo(sorteo.getFechaSorteo() != null ? 
    				dateFormat.format(sorteo.getFechaSorteo()) : null);
			reporteDto.setIdCaso(sorteo.getIdCaso());
			reporteDto.setNombreCaso(sorteo.getNombreCaso());
			reporteDto.setTipoCaso(sorteo.getTipoCaso());
			reporteDto.setTipoCuantia(sorteo.getTipoCuantia()); 
			reporteDto.setCuantia(sorteo.getCuantia());
			reporteDto.setMateria(sorteo.getMateria());
			reporteDto.setPreseleccion(sorteo.getPreseleccion());
			reporteDto.setTipoPreseleccion(sorteo.getTipoPreseleccion());
			reporteDto.setLiberacionLista(sorteo.getLiberacionLista());
			reporteDto.setFuncionarioSorteo(sorteo.getFuncionarioSorteo());
			reporteDto.setConsumo(sorteo.getConsumo());
			reporteDto.setTipoSorteo(sorteo.getTipoSorteo());
			
			Caso caso = new Caso();
			caso.setIdCaso(sorteo.getIdCaso());
			
			Sorteo sorteoArb = new Sorteo();
			sorteoArb.setIdSorteo(sorteo.getIdSorteo());
    		       		        
    		//se filtran los arbitros a mostrar
    		List<ArbitrosDisponiblesSorteoDTO> arbitrosSelec = obtenerArbitrosDisponiblesSorteoCaso(sorteo);
    		
			reporteDto.setArbitros(arbitrosSelec);
    		
    		
    		arbitrosDisponibles.add(reporteDto);
    				
		}
    	
    	return arbitrosDisponibles;
		
	}
    
    /**
	 * Método encargado de obtener la lista de árbitros de un caso nombrados por
	 * sorteo y ordenados por el tipo de nombramiento al momento de la
	 * realización del mismo
	 * 
	 * @param sorteo
	 * @param caso
	 * @return
	 */
	private List<ArbitrosDisponiblesSorteoDTO> obtenerArbitrosDisponiblesSorteoCaso(SorteoArbitrosDisponiblesDTO sorteo) {

		return manejadorRolPersonaCaso.obtenerArbitrosDisponiblesSorteoCaso(sorteo.getIdCaso(),
				sorteo.getIdSorteo());
	}
	

	/**
	 * Metodo encargado de generar el reporte de 
	 * los documentos radicados
	 * @param filtros
	 * @return List<ReporteRadicadoDocumentoDTO>
	 * @throws ParseException 
	 */	
	@SuppressWarnings("unchecked")	
   	public List<ReporteRadicadoDocumentosDTO> getReporteRadicadoDocumentos(Map filtros) {    		   
   		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
   		Date fechaInicial = new Date();
		Date fechaFinal = new Date();
		Long idCaso = null;
		if ((filtros.get("codigoCaso") != null && !filtros.get("codigoCaso").equals("")) ) {
			idCaso = Long.parseLong((String) filtros.get("codigoCaso"));
		}
		String nombreCaso = (String) filtros.get("nombreCaso");
		String nombreDocumento = (String) filtros.get(PARAMETRO_NOMBRE_DOCUMENTO);
		boolean filtroFechas = ((filtros.get("fechaInicial") != null && !filtros.get("fechaInicial").toString().isEmpty()
				&& !("0").equals(filtros.get("fechaInicial").toString()))
				&& (filtros.get("fechaFinal") != null && !filtros.get("fechaFinal").toString().isEmpty()
						&& !("0").equals(filtros.get("fechaFinal").toString())));  
		
		boolean filtroCodigoCaso = filtros.get("codigoCaso") != null && !filtros.get("codigoCaso").toString().isEmpty();
		boolean filtroNombreCaso = filtros.get("nombreCaso") != null && !filtros.get("nombreCaso").toString().isEmpty();
		boolean filtroNombreDocumento = filtros.get(PARAMETRO_NOMBRE_DOCUMENTO) != null && !filtros.get(PARAMETRO_NOMBRE_DOCUMENTO).toString().isEmpty();	   	
		
		StringBuilder consultaRolPersonaCaso = new StringBuilder();
		consultaRolPersonaCaso.append(" (select stuff((select ', ' + concat (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) ");
		consultaRolPersonaCaso.append(" from ROL_PERSONA_CASO rpc ");
		consultaRolPersonaCaso.append(" left join PERSONA pe on rpc.id_persona=pe.id_persona ");
		consultaRolPersonaCaso.append(" where rpc.id_caso=c.id_caso ");
		consultaRolPersonaCaso.append(" and rpc.estado_registro = ?1 ");	

		String estadoRolPersona = " and rpc.estado in ( ?4 , ?5 ) ";

		String subConsultaApodDemandanteD = consultaRolPersonaCaso.toString() 
				+" and rpc.id_rol=(select id_rol from rol where nombre= ?2 ) for xml path('')), 1, 1, '')) as apoderadoDemandante, "; 
			
		String subConsultaApodDemandadoD = consultaRolPersonaCaso.toString()
				+" and rpc.id_rol=(select id_rol from rol where nombre= ?3 ) for xml path('')), 1, 1, '')) as apoderadoDemandado, ";
			
		String subConsultaArbitrosD = consultaRolPersonaCaso.toString()
				+ estadoRolPersona
				+" and rpc.id_rol in (select DISTINCT rl.id_rol from PARAMETRO_SERVICIO_SORTEO pss inner join ROL rl on pss.id_rol = rl.id_rol) for xml path('')), 1, 1, '')) as arbitros, ";

		String subConsultaSecretariosD = consultaRolPersonaCaso.toString()
				+ estadoRolPersona 
				+" and rpc.id_rol=(select id_rol from rol where nombre= ?6 ) for xml path('')), 1, 1, '')) as secretario, ";
		
		String subConsultaAbogadosD = consultaRolPersonaCaso.toString()
				+" and rpc.id_rol=(select id_rol from rol where nombre= ?7 ) for xml path('')), 1, 1, '')) as abogados ";			
			
		StringBuilder jpqlQuery = new StringBuilder();
			
		jpqlQuery.append(" SELECT ");
		jpqlQuery.append(" c.id_caso AS  codigoCaso, ");
		jpqlQuery.append(CONSUMO);
		jpqlQuery.append(", (SELECT nombre FROM SERVICIO WHERE id_servicio = c.id_servicio AND estado_registro = ?1 ) AS servicioCaso, ");
		jpqlQuery.append(" c.nombre AS nombreCaso, ");
		jpqlQuery.append(" cast(c.fecha_radicacion as date) AS fechaRadicacion, ");
		jpqlQuery.append("tipoDoc.nombre as tipoDocumento, d.nombre as nombreDocumento, cast(dr.fecha_radicacion as date) as fechaComunicacion, ");		
		jpqlQuery.append(subConsultaApodDemandanteD);
		jpqlQuery.append(subConsultaApodDemandadoD);
		jpqlQuery.append(subConsultaArbitrosD);
		jpqlQuery.append(subConsultaSecretariosD);
		jpqlQuery.append(subConsultaAbogadosD);		
		jpqlQuery.append(" FROM CASO c ");
		jpqlQuery.append(" INNER JOIN  DOCUMENTO d ON c.id_caso = d.id_caso ");		
		jpqlQuery.append(" INNER JOIN documento_radicado dr on dr.id_documento  = d.id_documento ");
		jpqlQuery.append(" INNER JOIN DOMINIO tipoDoc ON d.tipo_documento=tipoDoc.codigo AND tipoDoc.dominio= ?13 "); 
		jpqlQuery.append(" INNER JOIN SERVICIO s ON c.id_servicio = s.id_servicio ");		
		jpqlQuery.append(" WHERE ");
		jpqlQuery.append(" s.tipo in (?14, ?15) ");
		jpqlQuery.append(ESTADO_CASO_ACTIVO);
		jpqlQuery.append(" AND d.estado_registro = ?1 ");
		if (filtroFechas) { 	
			fechaInicial = UtilOperaciones.obtenerFechaComienzoDelDia((Date) filtros.get("fechaInicial"));
			fechaFinal =  UtilOperaciones.obtenerFechaFinDelDia((Date) filtros.get("fechaFinal"));
			jpqlQuery.append(" AND dr.fecha_radicacion BETWEEN  ?8 AND ?9  ");
		}
		if (filtroCodigoCaso)		
			jpqlQuery.append(" AND d.id_Caso =  ?10 ");
		if (filtroNombreCaso)	{		
			jpqlQuery.append(" AND UPPER(c.nombre) LIKE   ?11 ");
			nombreCaso = nombreCaso.toUpperCase();
		}
		if(filtroNombreDocumento) {
			jpqlQuery.append(" AND UPPER(d.nombre) LIKE   ?12 ");	
			nombreDocumento = nombreDocumento.toUpperCase();
		}
		jpqlQuery.append(" AND dr.estado_registro = ?1 ");
		jpqlQuery.append(" ORDER BY dr.fecha_radicacion DESC ");

		Query query = em.createNativeQuery(jpqlQuery.toString(), ReporteRadicadoDocumentosDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ROL_PERSONA_APODERADO_DEMANDANTE);
		query.setParameter(3, UtilDominios.ROL_PERSONA_APODERADO_DEMANDADO);
		query.setParameter(4, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(5, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		query.setParameter(6, UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL);
		query.setParameter(7, UtilDominios.ROL_PERSONA_ABOGADO);					
		query.setParameter(8, dateFormat.format(fechaInicial));
		query.setParameter(9, dateFormat.format(fechaFinal));
		query.setParameter(10, idCaso);
		query.setParameter(11, "%"+nombreCaso+ "%");
		query.setParameter(12, "%"+nombreDocumento+"%");	
		query.setParameter(13, UtilDominios.DOMINIO_TIPO_DOCUMENTO_DIG);	
    	query.setParameter(14, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);	
		query.setParameter(15, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);	
    	return query.getResultList();
    }
	
	 /**
		 * Metodo encargado de generar el reporte de liberacion lista
		 * @param filtros
		 * @return List<SorteoLiberacionListaDTO>
		 */	
	    @SuppressWarnings("unchecked")
	    public List<SorteoLiberacionListaDTO> getReporteLiberacionLista(Map filtros) {
	    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");
	   		Date fechaInicialReporte = new Date();
			Date fechaFinalReporte = new Date();
			String fechaFinalLibList = "fechaFinalLibList";
			String fechaInicialLibList = "fechaInicialLibList";
			boolean filtroFechas = ((filtros.get(fechaInicialLibList) != null && !filtros.get(fechaInicialLibList).toString().isEmpty()
					&& !("0").equals(filtros.get(fechaInicialLibList).toString()))
					&& (filtros.get(fechaFinalLibList) != null && !filtros.get(fechaFinalLibList).toString().isEmpty()
							&& !("0").equals(filtros.get(fechaFinalLibList).toString())));  
			
	    	
	    	String tipoCaso = (String) filtros.get("tipoCasoLibList");
	    	String materia  = (String) filtros.get("materiaLibList");
	    	String lista   = (String) filtros.get("tipoListaCodigoLibList");	    	

	 		Long servicio = null;
	 		Long idMateria = null;				
			if(tipoCaso != null) {
				servicio = Long.valueOf(tipoCaso);
			}
			if(materia != null) {
				idMateria = Long.valueOf(materia);
			}
		
			
			/**/
			
			StringBuilder jpqlQueryLiberacionLista = new StringBuilder();
			jpqlQueryLiberacionLista.append(" select ss.nombre as servicio, m.nombre as materia,  ");
			jpqlQueryLiberacionLista.append(" (select case pss.sorteo_con_lista when 1 then (select case s.tipo_cuantia when 'MEN' then 'B' else 'A' end) else 'No aplica' end) as 'tipoLista',  ");
			jpqlQueryLiberacionLista.append(" s.fecha_realizacion as fechaRealizacion, s.id_Caso as idCaso, c.nombre as nombreCaso,  ");
			jpqlQueryLiberacionLista.append(" (select count(id_persona) from elegible e where e.id_sorteo = s.id_sorteo and e.elegido_por_liberacion_lista = 1) as numeroOperadoresLiberados,  ");
			jpqlQueryLiberacionLista.append("(select da.nombre from dominio da where da.dominio =  ?6 ");
			jpqlQueryLiberacionLista.append("AND da.codigo = (select a.tipo_sorteo from AUDIENCIA a where a.id_caso = s.id_caso  and a.id_sorteo = s.id_sorteo )) as tipoSorteo, ");			
			jpqlQueryLiberacionLista.append(CONSUMO);
			jpqlQueryLiberacionLista.append(" from sorteo s   ");
			jpqlQueryLiberacionLista.append(" inner join caso c on s.id_caso = c.id_caso ");
			jpqlQueryLiberacionLista.append(" inner join servicio ss on s.servicio_caso = ss.id_servicio ");
			jpqlQueryLiberacionLista.append(" inner join materia m on s.materia_caso = m.id_materia ");
			jpqlQueryLiberacionLista.append(" inner join AUDIENCIA a on s.id_sorteo = a.id_sorteo ");
			jpqlQueryLiberacionLista.append(" inner join parametro_servicio_sorteo pss on a.tipo_sorteo = pss.id_parametros_servicios_sorteo ");
			jpqlQueryLiberacionLista.append(" where s.libero_lista = 1  ");
			if(servicio!=null) {
				jpqlQueryLiberacionLista.append(" and   s.servicio_caso      = ?1  ");
			}
			if (idMateria != null) {
				jpqlQueryLiberacionLista.append(" and   s.materia_caso  = ?2  ");
			}
			if (filtroFechas) { 	
				fechaInicialReporte = UtilOperaciones.obtenerFechaComienzoDelDia((Date) filtros.get(fechaInicialLibList));
				fechaFinalReporte =  UtilOperaciones.obtenerFechaFinDelDia((Date) filtros.get(fechaFinalLibList));
				jpqlQueryLiberacionLista.append(" and s.fecha_realizacion BETWEEN  ?3 AND ?4   ");
			}
			if(lista != null && lista.equals(UtilConstantes.LISTA_B.toString())){
				jpqlQueryLiberacionLista.append(" and  s.tipo_cuantia  =   ?5  and  pss.sorteo_con_lista =1 ");
			}
			if(lista != null && !lista.equals(UtilConstantes.LISTA_B.toString())){
				jpqlQueryLiberacionLista.append(" and  s.tipo_cuantia  <>    ?5  and  pss.sorteo_con_lista =1 ");
			}
			jpqlQueryLiberacionLista.append(" order by fechaRealizacion ");

			Query q = em.createNativeQuery(jpqlQueryLiberacionLista.toString(), SorteoLiberacionListaDTO.class); 
			q.setParameter(1, servicio);
			q.setParameter(2, idMateria);
			q.setParameter(3, dateFormat.format(fechaInicialReporte));
			q.setParameter(4, dateFormat.format(fechaFinalReporte));
			q.setParameter(5, UtilDominios.TIPO_CUANTIA_MENOR);
			q.setParameter(6, UtilDominios.DOMINIO_TIPO_DE_SORTEO);		

	 		return q.getResultList();
	 	}
	    
	    
	    /**
		 * Metodo encargado de generar el reporte de cambio estdos operadores
		 * @param filtros
		 * @return List<ReporteCambioEstadoOperadoresDTO>
		 */	
	    @SuppressWarnings("unchecked")
	    public List<ReporteCambioEstadoOperadoresDTO> getReporteCambioEstadoOperadores(Map filtros) {
	    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");
	   		Date fechaInicialFiltro = new Date();
			Date fechaFinalFiltro = new Date();
			boolean filtroFechas = ((filtros.get("fechaInicial") != null && !filtros.get("fechaInicial").toString().isEmpty()
					&& !("0").equals(filtros.get("fechaInicial").toString()))
					&& (filtros.get("fechaFinal") != null && !filtros.get("fechaFinal").toString().isEmpty()
							&& !("0").equals(filtros.get("fechaFinal").toString())));  
			
	    	
	    	String tipoCaso = (String) filtros.get(PARAMETRO_TIPO_CASO);
	    	String materia  = (String) filtros.get("materia");
	    	String lista   = (String) filtros.get("tipoListaCodigo");
	    	Long operador  =  (Long) filtros.get("operador");

	 		Long servicio = null;
	 		Long idMateria = null;	 	
	 		Long idPersona = null;
			if(tipoCaso != null) {
				servicio = Long.valueOf(tipoCaso);
			}
			if(materia != null) {
				idMateria = Long.valueOf(materia);
			}
			if(operador != null) {
				idPersona = operador;
			}
			
			StringBuilder jpqlCambioEstadoOperadores = new StringBuilder();
			jpqlCambioEstadoOperadores.append(" SELECT     distinct concat(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido) as operador,   ");
			jpqlCambioEstadoOperadores.append("   s.nombre as servicio,  ");
			jpqlCambioEstadoOperadores.append("isnull(l.nombre, 'No aplica') as lista,  ");
			jpqlCambioEstadoOperadores.append("   m.nombre  AS materia      ,  ");
			jpqlCambioEstadoOperadores.append("   de.nombre AS estado          ,  ");
			jpqlCambioEstadoOperadores.append("   dr.nombre as rol        ,  ");
			jpqlCambioEstadoOperadores.append("CASE emd.estado  ");
			jpqlCambioEstadoOperadores.append("WHEN 'NSORT'  ");
			jpqlCambioEstadoOperadores.append("              THEN  "); 
			jpqlCambioEstadoOperadores.append("CASE hem.id_motivo  ");
			jpqlCambioEstadoOperadores.append("                                    WHEN 'EAH'  "); 
			jpqlCambioEstadoOperadores.append("                                    THEN dme.nombre "); 
			jpqlCambioEstadoOperadores.append("                                    ELSE do.nombre  ");
			jpqlCambioEstadoOperadores.append("                         END  ");
			jpqlCambioEstadoOperadores.append("              ELSE ''  ");
			jpqlCambioEstadoOperadores.append("   END       AS motivo       , "); 
			jpqlCambioEstadoOperadores.append("   h.observaciones, ");
			jpqlCambioEstadoOperadores.append("   hem.fecha_ultima_modificacion as fechaCambio, ");
			jpqlCambioEstadoOperadores.append("   case hem.id_usuario_modificacion  ");
			jpqlCambioEstadoOperadores.append("   when 'SIMASC_USUARIO' then 'SIMASC' ");
			jpqlCambioEstadoOperadores.append("   else concat(pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) ");
			jpqlCambioEstadoOperadores.append("   end as usuario, ");
			jpqlCambioEstadoOperadores.append("   CASE WHEN ca.arbitraje_consumo = 1 THEN 'SI' ELSE 'NO' END AS consumo ");
			jpqlCambioEstadoOperadores.append("FROM       PERSONA p ");
			jpqlCambioEstadoOperadores.append("   inner join HISTORICO_ESTADO_MOTIVO_PERSONA hem "); 
			jpqlCambioEstadoOperadores.append("   on p.id_persona = hem.id_persona ");
			jpqlCambioEstadoOperadores.append("   inner join servicio s ");
			jpqlCambioEstadoOperadores.append(" on s.id_servicio = hem.id_servicio ");
			jpqlCambioEstadoOperadores.append("   INNER JOIN ROL_PERSONA rp ");
			jpqlCambioEstadoOperadores.append("   ON rp.id_persona = p.id_persona ");		   
			jpqlCambioEstadoOperadores.append("   INNER JOIN ROL r  ");
			jpqlCambioEstadoOperadores.append("   ON         r.id_rol = hem.id_rol "); 
			jpqlCambioEstadoOperadores.append("   AND        r.id_rol = rp.id_rol ");
			jpqlCambioEstadoOperadores.append("   AND        r.tipo_servicio = 'PJT'  ");
			jpqlCambioEstadoOperadores.append("   INNER JOIN dominio dr  ");
			jpqlCambioEstadoOperadores.append("   ON         r.nombre = dr.codigo "); 
			jpqlCambioEstadoOperadores.append("   AND        dr.dominio  = 'ROL_PERSONA' "); 
			jpqlCambioEstadoOperadores.append("   INNER JOIN MATERIA m  ");
			jpqlCambioEstadoOperadores.append("   ON         hem.id_materia = m.id_materia "); 
			jpqlCambioEstadoOperadores.append("   INNER JOIN ESTADO_MOTIVO_DISPONIBILIDAD emd  ");
			jpqlCambioEstadoOperadores.append("   ON         hem.id_persona = p.id_persona ");
			jpqlCambioEstadoOperadores.append("   and hem.estado_para_sorteo = emd.estado_para_sorteo "); 
			jpqlCambioEstadoOperadores.append("   AND        hem.id_motivo          = emd.id_motivo_estado "); 
			jpqlCambioEstadoOperadores.append("   INNER JOIN dominio de  ");
			jpqlCambioEstadoOperadores.append("   ON         emd.estado = de.codigo "); 
			jpqlCambioEstadoOperadores.append("   AND        de.dominio  = 'ESTADO_ARBITROS' "); 
			jpqlCambioEstadoOperadores.append("LEFT JOIN dominio do  ");
			jpqlCambioEstadoOperadores.append("ON         hem.id_motivo = do.codigo "); 
			jpqlCambioEstadoOperadores.append("AND        do.dominio    = 'MOTIVO_ESTADO' "); 
			jpqlCambioEstadoOperadores.append("   LEFT JOIN dominio dme  ");
			jpqlCambioEstadoOperadores.append("   ON         hem.motivo_estado_sorteo = dme.codigo "); 
			jpqlCambioEstadoOperadores.append("   AND        dme.dominio    = 'MOTIVO_ESTADO'   ");
			jpqlCambioEstadoOperadores.append("left join LISTA l ");
			jpqlCambioEstadoOperadores.append("   on rp.id_lista = l.id_lista ");
			jpqlCambioEstadoOperadores.append("   inner join PERSONA_SERVICIO_MATERIA psm ");		
			jpqlCambioEstadoOperadores.append("   on psm.id_persona = p.id_persona ");
			jpqlCambioEstadoOperadores.append("   and psm.id_servicio = hem.id_servicio "); 
			jpqlCambioEstadoOperadores.append("   and psm.id_materia = hem.id_materia ");
			jpqlCambioEstadoOperadores.append("   left join HISTORICO_PERSONA_SERVICIO_MATERIA h ");
			jpqlCambioEstadoOperadores.append("   on psm.id_persona_servicio_materia = h.id_persona_servicio_materia "); 
			jpqlCambioEstadoOperadores.append("   and convert(varchar(20), h.fecha_asignacion_estado, 120) = convert(varchar(20), hem.fecha_ultima_modificacion, 120) ");
			jpqlCambioEstadoOperadores.append("   left join PERSONA pe ");
			jpqlCambioEstadoOperadores.append("   on pe.numero_documento = hem.id_usuario_modificacion ");
			jpqlCambioEstadoOperadores.append("   LEFT JOIN CASO ca ");
			jpqlCambioEstadoOperadores.append("   on h.id_caso = ca.id_caso ");
			jpqlCambioEstadoOperadores.append("WHERE      hem.fecha_ultima_modificacion >= rp.fecha_inicio_vigencia and (rp.fecha_fin_vigencia is null or hem.fecha_ultima_modificacion <= rp.fecha_fin_vigencia) ");
			 			
						
			if(servicio!=null) {
				jpqlCambioEstadoOperadores.append(" and   hem.id_servicio     = ?1  ");
			}
			if (idMateria != null) {
				jpqlCambioEstadoOperadores.append(" and    hem.id_materia   = ?2  ");
			}
			if (filtroFechas) { 	
				fechaInicialFiltro = UtilOperaciones.obtenerFechaComienzoDelDia((Date) filtros.get("fechaInicial"));
				fechaFinalFiltro =  UtilOperaciones.obtenerFechaFinDelDia((Date) filtros.get("fechaFinal"));
				jpqlCambioEstadoOperadores.append("and hem.fecha_ultima_modificacion  BETWEEN  ?3 AND ?4  ");
			}			
			if (idPersona != null) {
				jpqlCambioEstadoOperadores.append(" and   p.id_persona    = ?5  ");
			}
			if(lista != null && lista.equals(UtilConstantes.LISTA_B.toString())){
				jpqlCambioEstadoOperadores.append(" and l.id_lista = 2  ");
			}
			if(lista != null && !lista.equals(UtilConstantes.LISTA_B.toString())){
				jpqlCambioEstadoOperadores.append(" and l.id_lista = 1  ");
			}
			jpqlCambioEstadoOperadores.append("order by   servicio, materia, fechaCambio, operador  ");
			Query q = em.createNativeQuery(jpqlCambioEstadoOperadores.toString(), ReporteCambioEstadoOperadoresDTO.class); 
			q.setParameter(1, servicio);
			q.setParameter(2, idMateria);
			q.setParameter(3, dateFormat.format(fechaInicialFiltro));
			q.setParameter(4, dateFormat.format(fechaFinalFiltro));			
			q.setParameter(5, idPersona);

	 		return q.getResultList();
	 	}
	    
	    /**
	 		 * Metodo encargado de generar el reporte de operadores suspendidos
	 		 * @param filtros
	 		 * @return List<ReporteOperdoresSuspendidosDTO>
	 		 */	
	 	    @SuppressWarnings("unchecked")
	 	    public List<ReporteOperadoresSuspendidosDTO> getReporteOperadoresSuspendidos(Map filtros) {
	 	    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");
	 	   		Date fechaInicial = new Date();
	 			Date fechaFinal = new Date();
	 			boolean filtroFechasO = ((filtros.get("fechaInicial") != null && !filtros.get("fechaInicial").toString().isEmpty()
	 					&& !("0").equals(filtros.get("fechaInicial").toString()))
	 					&& (filtros.get("fechaFinal") != null && !filtros.get("fechaFinal").toString().isEmpty()
	 							&& !("0").equals(filtros.get("fechaFinal").toString())));  
	 			
	 	    	
	 	    	String tipoCasoO = (String) filtros.get(PARAMETRO_TIPO_CASO);
	 	    	String motivoO  = (String) filtros.get("motivo");	 	    	
	 	    	Long operadorO  =  (Long) filtros.get("operador");

	 	 		Long servicioO = null;	 	 		
	 	 		Long idPersonaO = null;
	 			if(tipoCasoO != null) {
	 				servicioO = Long.valueOf(tipoCasoO);
	 			}	 			
	 			if(operadorO != null) {
	 				idPersonaO = operadorO;
	 			}
	 			
	 			StringBuilder queryOperadoresSusp = new StringBuilder();
	 			
	 			queryOperadoresSusp.append("select distinct ss.nombre as servicio, dr.nombre as rol, CONCAT(pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) as operador,  "); 
	 			queryOperadoresSusp.append("dm.nombre as motivo, cast(he.fecha as date) as fechaInicial, cast(DATEADD(MM, 6, he.fecha) as date) as fechaFinal,  ");
	 			queryOperadoresSusp.append("pa.id_caso as codigoCaso, c.nombre as nombreCaso  ");
	 			queryOperadoresSusp.append("from HISTORICO_ESTADO_PERSONA_ROL he  ");
	 			queryOperadoresSusp.append("inner join PERSONA pe on he.id_persona = pe.id_persona  ");
	 			queryOperadoresSusp.append("inner join ROL r on he.id_rol = r.id_rol  ");
	 			queryOperadoresSusp.append("inner join DOMINIO dr on dr.codigo = r.nombre and dr.dominio = 'ROL_PERSONA'  ");
	 			queryOperadoresSusp.append("inner join DOMINIO dm on dm.codigo = he.id_motivo and dm.dominio = 'MOTIVO_ESTADO' ");
	 			queryOperadoresSusp.append("left join PROGRAMACION_ALERTA pa on id_alerta = 49 and pa.id_persona = he.id_persona and cast(pa.fecha_ejecucion as date) = cast(DATEADD(MM, 6, he.fecha) as date) ");
	 			queryOperadoresSusp.append("inner join CASO c on pa.id_caso = c.id_caso  ");
	 			queryOperadoresSusp.append("inner join servicio ss on  ss.id_servicio = c.id_servicio  ");
	 			queryOperadoresSusp.append("where he.id_motivo in ('" + UtilDominios.MOTIVO_CONTESTACION_EXTEMPORANEA + "','" + UtilDominios.MOTIVO_NO_ACEPTACION + "','" + UtilDominios.MOTIVO_NO_CONTESTACION + "')  ");
	 						
	 			if(servicioO!=null) {
	 				queryOperadoresSusp.append(" and   c.id_servicio      = ?1  ");
	 			}
	 			if(motivoO!=null) {
	 				queryOperadoresSusp.append(" and   he.id_motivo     = ?2  ");
		 		}
	 			
	 			if (filtroFechasO) { 	
	 				fechaInicial = UtilOperaciones.obtenerFechaComienzoDelDia((Date) filtros.get("fechaInicial"));
	 				fechaFinal =  UtilOperaciones.obtenerFechaFinDelDia((Date) filtros.get("fechaFinal"));
	 				queryOperadoresSusp.append("and he.fecha  BETWEEN  ?3 AND ?4  ");
	 			}			
	 			if (idPersonaO != null) {
	 				queryOperadoresSusp.append(" and he.id_persona    = ?5  ");
	 			}	 			
	 			
	 			Query qOS = em.createNativeQuery(queryOperadoresSusp.toString(), ReporteOperadoresSuspendidosDTO.class); 
	 			qOS.setParameter(1, servicioO);
	 			qOS.setParameter(2, motivoO);
	 			qOS.setParameter(3, dateFormat.format(fechaInicial));
	 			qOS.setParameter(4, dateFormat.format(fechaFinal));			
	 			qOS.setParameter(5, idPersonaO);

	 	 		return qOS.getResultList();
	 	 	}
	    

}


