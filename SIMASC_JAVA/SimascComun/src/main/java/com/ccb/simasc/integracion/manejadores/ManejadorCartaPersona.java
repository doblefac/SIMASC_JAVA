package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin

// Escriba en esta seccion sus modificaciones

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.CartaAudienciaPendienteImpresionDTO;
import com.ccb.simasc.transversal.dto.CartaPendienteImpresionDTO;
import com.ccb.simasc.transversal.dto.CorrespondenciaDTO;
import com.ccb.simasc.transversal.dto.LlamadaPlanillaCorrespondenciaDTO;
import com.ccb.simasc.transversal.dto.PlanillaCorrespondenciaCartaDTO;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.entidades.CartaPersona;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;

import co.org.ccb.simasc.comun.correos.ArrayOfString;
import co.org.ccb.simasc.comun.correos.EnvioCorreoInDTO;
import co.org.ccb.simasc.comun.correos.EnvioCorreoServiceSoapProxy;
import co.org.ccb.simasc.comun.correos.template.CartasPendientesValues;
import co.org.ccb.simasc.comun.correos.template.TemplateParser;

// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad CartaPersona.
 * 
 * @author jsoto
 */
@Stateless(name = "ManejadorCartaPersonaBean", mappedName = "ManejadorCartaPersonaBean")
public class ManejadorCartaPersona extends ManejadorCrud<CartaPersona, Long> {

	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales
	@PersistenceContext
	private transient EntityManager em;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorParametrosGenerales manejadorParametrosGenerales;
	// protected region atributos end

	public ManejadorCartaPersona() {
		super(CartaPersona.class);
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	public Long obtenerConsecutivo() {

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select max(c.id_carta_persona) ");
		nativeQuery.append("FROM CARTA_PERSONA c ");

		Query query = em.createNativeQuery(nativeQuery.toString());
		BigDecimal consecutivo = (BigDecimal) query.getSingleResult();

		return (consecutivo != null) ? consecutivo.longValue() : 1l;
	}

	public CartaPersona actualizarCarta(CartaPersona cartaPersona) {
		return (CartaPersona) mp.updateObject(cartaPersona);
	}

	/**
	 * Realiza envio de correo sobre la cantidad de cartas pendientes para
	 * impresion
	 */
	public void evioCorreoCartasPendientesImpresion() {
		List<CartaAudienciaPendienteImpresionDTO> pendientes = consultarCartasAudienciaPendientesImpresion();
		if (pendientes != null && pendientes.size() > 0) {

			// consulta de los parametros para el envio de correo
			String llave = "";
			String tipoContenido = "";
			String de = "";
			List<ParametrosGenerales> parametros = manejadorParametrosGenerales
					.obtenerParametrosPorTipo(UtilParamGenerales.TIPO_CORREO);
			for (ParametrosGenerales parametrosGenerales : parametros) {
				switch (parametrosGenerales.getCodigo()) {
				case UtilParamGenerales.CORREO_LLAVE:
					llave = parametrosGenerales.getValorTexto();
					break;
				case UtilParamGenerales.CORREO_TIPO_CONTENIDO:
					tipoContenido = parametrosGenerales.getValorTexto();
					break;
				case UtilParamGenerales.CORREO_ARBITRAJE:
					de = parametrosGenerales.getValorTexto();
					break;
				default:
					break;
				}
			}
			for (CartaAudienciaPendienteImpresionDTO pendiente : pendientes) {

				// Validación :: Se realiza el envío de la carta solo si existe el correo del Secretario
				if(pendiente.direccionSecretario != null && !pendiente.direccionSecretario.trim().isEmpty())
				{				
					//genera el cuerpo del correo basado en un template
					CartasPendientesValues valores = new CartasPendientesValues();
					valores.setTotalPendientes(pendiente.getCartasPendientes());
					String cuerpoCorreo = TemplateParser.getInstancia().setAtributos(
							valores, UtilConstantes.TEMPLATE_CARTAS_IMPRESION);
								
					EnvioCorreoServiceSoapProxy servicio = new EnvioCorreoServiceSoapProxy();
					EnvioCorreoInDTO inDTO = new EnvioCorreoInDTO();
					inDTO.setAsunto(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO901.toString()));
					ArrayOfString listaDestinos = new ArrayOfString();
					listaDestinos.getString().add(pendiente.direccionSecretario);
					inDTO.setPara(listaDestinos);
					inDTO.setContenido(cuerpoCorreo);
					inDTO.setDe(de);
					inDTO.setLlave(llave);
					inDTO.setTipoContenido(tipoContenido);
					servicio.envioCorreo(inDTO);
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<CartaPendienteImpresionDTO> consultarCartasPendientesImpresion() {
		StringBuilder nativeQuery = new StringBuilder();

		nativeQuery.append("select distinct c.id_carta_persona, c.id_caso, p.id_persona, ");
		nativeQuery.append("pc.id_persona,d.descripcion ");
		nativeQuery.append("from carta_persona c ");
		nativeQuery.append("left join persona p on c.id_persona=p.id_persona ");
		nativeQuery.append("left join rol_persona_caso rpc on c.id_caso=rpc.id_caso ");
		nativeQuery.append("left join rol r on rpc.id_rol=r.id_rol ");
		nativeQuery.append("left join persona pc on rpc.id_persona=pc.id_persona ");
		nativeQuery.append("left join correo_rol_persona_caso crpc on crpc.id_persona_receptor = p.id_persona ");
		nativeQuery.append("left join acuse a on a.id_correo_rol_persona_caso = crpc.id_correo_rol_persona_caso ");
		nativeQuery.append("left join dominio d on d.codigo = a.tipo ");
		nativeQuery.append("left join dominio dd on dd.codigo = d.codigo_dom_padre ");
		nativeQuery.append("where r.nombre='" + UtilDominios.ROL_PERSONA_CONCILIADOR + "' and ");
		nativeQuery.append("c.estado_carta='" + UtilDominios.ESTADO_CARTA_PENDIENTE_IMPRESION + "' ");

		Query q = em.createNativeQuery(nativeQuery.toString());
		// Mapear objetos resultado
		List<CartaPendienteImpresionDTO> listaCartasPendientes = new ArrayList<CartaPendienteImpresionDTO>();
		CartaPendienteImpresionDTO pivote = null;
		for (Object resultRow : q.getResultList()) {
			pivote = new CartaPendienteImpresionDTO();
			pivote.setIdCarta(((Number) ((Object[]) resultRow)[0]).longValue());
			pivote.setNumeroCaso(((Number) ((Object[]) resultRow)[1]).longValue());
			Persona p = manejadorPersona.buscar(((Number) ((Object[]) resultRow)[2]).longValue());
			pivote.setNombreParte(p.getNombreCompleto());
			Persona pc = manejadorPersona.buscar(((Number) ((Object[]) resultRow)[3]).longValue());
			pivote.setConciliador(pc.getNombreCompleto());
			pivote.setIndicadorCorreoDevuelto((String) ((Object[]) resultRow)[4]);
			listaCartasPendientes.add(pivote);
			pivote = null;
		}
		return listaCartasPendientes;
	}

	public List<CartaAudienciaPendienteImpresionDTO> consultarCartasAudienciaPendientesImpresion() {
		List<CartaAudienciaPendienteImpresionDTO> list = new ArrayList<>();
		List<Object[]> rows = new ArrayList<>();
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(
				"select count(c.id_carta_persona), ce.direccion,cs.id_caso,cea.direccion, p.id_persona, a.id_audiencia from CARTA_PERSONA c ");
		nativeQuery.append("left join audiencia a on a.id_audiencia = c.id_audiencia ");
		nativeQuery.append("left join caso cs on cs.id_caso = a.id_caso ");
		nativeQuery.append("left join rol_persona_caso rpc on cs.id_caso=rpc.id_caso ");
		nativeQuery.append("left join rol r on rpc.id_rol=r.id_rol  ");
		nativeQuery.append("left join persona p on p.id_persona=rpc.id_persona ");
		nativeQuery.append("left join correo_electronico ce on ce.id_persona = p.id_persona ");

		nativeQuery.append("left join rol_persona_caso rpca on cs.id_caso=rpca.id_caso ");
		nativeQuery.append("left join rol ra on rpca.id_rol=ra.id_rol ");
		nativeQuery.append("left join persona pa on pa.id_persona=rpca.id_persona ");
		nativeQuery.append("left join correo_electronico cea on cea.id_persona = pa.id_persona ");
		nativeQuery.append("where c.id_audiencia is not null and c.estado_carta='PEN' and r.nombre='"
				+ UtilDominios.ROL_PERSONA_SECRETARIO_DE_TRIBUNAL + "'  ");
		nativeQuery.append("and ce.tipo='" + UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL + "' ");
		nativeQuery.append("and ra.nombre='" + UtilDominios.ROL_PERSONA_ABOGADO + "' and cea.tipo='"
				+ UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL
				+ "' group by ce.direccion, cs.id_caso, cea.direccion, p.id_persona, a.id_audiencia ");

		Query q = em.createNativeQuery(nativeQuery.toString());
		rows = q.getResultList();
		for (Object[] row : rows) {
			CartaAudienciaPendienteImpresionDTO dto = new CartaAudienciaPendienteImpresionDTO();
			dto.setCartasPendientes((Integer) row[0]);
			dto.setDireccionSecretario((String) row[1]);
			dto.setIdCaso((BigDecimal) row[2]);
			dto.setDireccionAbogado((String) row[3]);
			dto.setIdPersona((BigDecimal) row[4]);
			dto.setIdAudiencia((BigDecimal) row[5]);
			list.add(dto);
		}
		return list;
	}

	/**
	 * Metodo que permite consultar el estado de correspondencia de un caso de
	 * la ficha tecnica de conciliacion (CONF-094).
	 * 
	 * @author aperez.
	 * 
	 * @param idCaso:
	 *            Identificador del caso.
	 * @param estadosCarta:
	 *            Estados de carta de envio fisico.
	 * @return List<CorrespondenciaDTO>: Lista de correspondencias.
	 */
	@SuppressWarnings("unchecked")
	public List<CorrespondenciaDTO> consultarCorrespondencia(Long idCaso, List<String> estadosCarta) {
		StringBuilder nativeQuery = new StringBuilder();

		String destinatario = "(SELECT CONCAT(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', "
				+ "p.primer_apellido, ' ', p.segundo_apellido) "
				+ "FROM PERSONA p  WHERE p.id_persona = cp.id_persona AND p.estado_registro = ?1 ) AS destinatario, ";

		String sede = "(SELECT se.nombre FROM AGENDAMIENTO ag, SALA s, SEDE se WHERE ag.id_audiencia = cp.id_audiencia "
				+ "AND ag.id_sala = s.id_sala AND s.id_sede = se.id_sede AND ag.estado_registro = ?1 "
				+ "AND s.estado_registro = ?1 AND se.estado_registro = ?1) AS sedeAudiencia, ";

		nativeQuery.append("SELECT cp.fecha_envio AS fechaEnvio, ");
		nativeQuery.append("a.hora_inicio AS fechaAudiencia, ");
		nativeQuery.append("cp.estado_carta AS estado, ");
		nativeQuery.append(destinatario);
		nativeQuery.append(sede);
		nativeQuery.append("ll.fecha AS fechaLlamada, ");
		nativeQuery.append("ll.observaciones AS observaciones ");
		nativeQuery.append("FROM CARTA_PERSONA cp ");
		nativeQuery.append("LEFT JOIN AUDIENCIA a ON a.id_audiencia= cp.id_audiencia AND a.estado_registro = ?1 ");
		nativeQuery.append("LEFT JOIN LLAMADA ll ON cp.id_carta_persona = ll.id_carta_persona ");
		nativeQuery.append("AND ll.estado_registro=?1 ");
		nativeQuery.append("WHERE cp.estado_carta ").append(UtilConsultasSQL.clausulaInSQLStrings(estadosCarta));
		nativeQuery
				.append("AND cp.id_caso = ?2 AND cp.estado_registro = ?1 ");
		nativeQuery.append("ORDER BY CASE WHEN cp.fecha_envio IS NULL THEN 1 ELSE 0 END ");

		Query q = em.createNativeQuery(nativeQuery.toString(), CorrespondenciaDTO.class);

		q.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		q.setParameter(2, idCaso);

		return q.getResultList();

	}
	
	
	/**
	 * Metodo que permite consultar la información asociada a la carta fisica
	 *  (CONC-052).
	 * 
	 * @author cagonzalez
	 * 
	 * @param filtroBusqueda:
	 *            Informacion de idConvenio, idCarta, destinatario, nombre del caso, idSede, fechaEnvio si aplica.

	 * @return PlanillaCorrespondenciaDTO.
	 */
	@SuppressWarnings("unchecked")
	public List<LlamadaPlanillaCorrespondenciaDTO> consultarInformacionEstadoCorrespondencia(PlanillaCorrespondenciaCartaDTO filtroBusqueda) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT C.ID_CASO as codigoCaso, ");
		nativeQuery.append(" p.id_persona as idPersona, ");
		nativeQuery.append(" concat(rtrim(p.primer_nombre_o_razon_social), rtrim(' '+p.segundo_nombre), rtrim(' '+p.primer_apellido), rtrim(' '+p.segundo_apellido)) as nombreParte, ");
		nativeQuery.append(" d.nombre as rolParte, ");
		nativeQuery.append(" cp.direccion_correspondencia as direccionEnviada, ");
		nativeQuery.append(" cp.ciudad_correspondencia as ciudadDireccion, ");
		nativeQuery.append(" (select stuff((   ");
		nativeQuery.append(" SELECT ', ' +t.numero FROM telefono t  WHERE t.id_persona = p.id_persona  ");
		nativeQuery.append(" for xml path('')), 1, 1, '')) as telefono, ");
		nativeQuery.append(" au.hora_inicio as fechaAudiencia,  ");
		nativeQuery.append(" sed.nombre as sedeAudiencia, ");
		nativeQuery.append(" (select stuff((   ");
		nativeQuery.append(" SELECT ', '+concat(rtrim(pe.primer_nombre_o_razon_social), rtrim(' '+pe.segundo_nombre), rtrim(' '+pe.primer_apellido), rtrim(' '+pe.segundo_apellido))  ");
		nativeQuery.append(" from persona pe inner join ROL_PERSONA_CASO rolpc on rolpc.id_persona=pe.id_persona  WHERE  ");
		nativeQuery.append(" rolpc.tipo_nombramiento=?1 AND rolpc.estado in (?2,?3) AND rolpc.id_rol in  ");
		nativeQuery.append(" (select tps.ID_ROL from TIPO_DE_SERVICIO_ROL tps where tps.tipo_servicio=?4 AND tps.estado_registro=?5) ");
		nativeQuery.append(" and rolpc.id_caso=cp.id_caso and pe.estado_registro=?5 and rolpc.estado_registro=?5  ");
		nativeQuery.append("  for xml path('')), 1, 1, '')) as conciliador, ");
		nativeQuery.append(" cp.id_carta_persona as numeroCarta ");
		nativeQuery.append(" FROM CASO C ");
		nativeQuery.append(" INNER JOIN CARTA_PERSONA CP ON C.ID_CASO=CP.ID_CASO ");
		nativeQuery.append(" INNER JOIN PERSONA  P ON P.ID_PERSONA=CP.ID_PERSONA ");
		nativeQuery.append(" INNER JOIN rol_persona_caso rpc on rpc.id_persona=p.id_persona and c.id_caso=rpc.id_caso ");
		nativeQuery.append(" INNER JOIN rol r on r.id_rol=rpc.id_rol ");
		nativeQuery.append(" INNER JOIN dominio d on r.nombre=d.codigo and d.dominio=?6 ");
		nativeQuery.append(" INNER JOIN AUDIENCIA au on au.id_audiencia=cp.id_audiencia ");
		nativeQuery.append(" INNER JOIN agendamiento ag on ag.id_audiencia=au.id_audiencia ");
		nativeQuery.append(" INNER JOIN sala sa on sa.id_sala=ag.id_sala ");
		nativeQuery.append(" INNER JOIN sede sed on sed.id_sede=sa.id_sede ");
		nativeQuery.append(" WHERE ");
		nativeQuery.append(" cp.estado_carta=?7 ");
		
		if(filtroBusqueda.getFechaEnvio()!=null){
			nativeQuery.append(" and cast(cp.fecha_envio as DATE) = ?8 ");	
		}		
		nativeQuery.append(" and sed.id_centro ").append(UtilConsultasSQL.clausulaInSQLSNumeros(filtroBusqueda.getIdCentros()));
		if(filtroBusqueda.getIdSede()!=null){
			nativeQuery.append(" and c.id_sede= ?9 ");	
		}
		if(filtroBusqueda.getDestinatario()!=null){
			String nombres  = "((CONCAT(p.primer_nombre_o_razon_social,p.segundo_nombre,p.primer_apellido,p.segundo_apellido) like %?14% ) or "+
					"(CONCAT(p.primer_nombre_o_razon_social,p.segundo_nombre) like  %?14%) or "+  
					"(CONCAT(p.primer_apellido,p.primer_nombre_o_razon_social) like %?14%) or "+
					"(CONCAT(p.primer_nombre_o_razon_social,p.primer_apellido) like %?14%)  )";
			nativeQuery.append(" AND ").append(nombres);	
		}
		if(filtroBusqueda.getCaso()!=null){
			nativeQuery.append(" and c.nombre LIKE %?13% "); 	
		}			
		if(filtroBusqueda.getNumeroRadicado()!=null){
			nativeQuery.append(" and cp.id_carta_persona = ?10 ");	
		}
		if(filtroBusqueda.getIdConvenio()!=null){
			nativeQuery.append(" and c.id_convenio = ?11 ");	
		}
		if(filtroBusqueda.getIdCaso()!=null){
			nativeQuery.append(" and c.id_caso = ?12 "); 	
		}	
		nativeQuery.append(" and c.estado_registro=?5 ");
		nativeQuery.append(" AND CP.estado_registro=?5 ");
		nativeQuery.append(" AND RPC.estado_registro=?5 ");
		nativeQuery.append(" AND R.estado_registro=?5 ");
		nativeQuery.append(" AND AU.estado_registro=?5 ");
		nativeQuery.append(" AND AG.estado_registro=?5 ");
		nativeQuery.append(" AND SA.estado_registro=?5 ");
		nativeQuery.append(" AND SED.estado_registro=?5 ");
		nativeQuery.append(" order by cp.fecha_ultima_modificacion desc ");
							
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), LlamadaPlanillaCorrespondenciaDTO.class);
		query.setParameter(1, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL);
		query.setParameter(2, UtilDominios.ESTADO_ROL_PERSONA_CASO_ACEPTADO);
		query.setParameter(3, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
		query.setParameter(4, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(5, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(6, UtilDominios.DOMINIO_ROL_PERSONA);
		query.setParameter(7, UtilDominios.ESTADO_CARTA_ENVIADA);
		query.setParameter(13, filtroBusqueda.getCaso());
		query.setParameter(14, filtroBusqueda.getDestinatario());
		if(filtroBusqueda.getFechaEnvio()!=null){
			query.setParameter(8, filtroBusqueda.getFechaEnvio());	
		}
		if(filtroBusqueda.getIdSede()!=null){
			query.setParameter(9, filtroBusqueda.getIdSede());	
		}
		query.setParameter(10, filtroBusqueda.getNumeroRadicado());
		query.setParameter(11, filtroBusqueda.getIdConvenio());
		if(filtroBusqueda.getIdCaso()!=null){
			query.setParameter(12, filtroBusqueda.getIdCaso());	
		}
				
		return query.getResultList();
	}
	


	/**
     * Consulta el número de cartas pendientes de impresión por centro
     * @return
     */
    public List<InfoBasicaAlertasDTO> alertaCartasPendientesImpresion(){
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT count(*) as numeroCartas, ce.id_centro as idCentro, ce.nombre AS nombreCentro FROM CARTA_PERSONA CP  ");
		nativeQuery.append(" INNER JOIN CASO C ON C.ID_CASO=CP.ID_CASO ");
		nativeQuery.append(" INNER JOIN SERVICIO s ON s.id_servicio = C.id_servicio, centro ce ");
		nativeQuery.append(" WHERE ce.id_centro = (case when c.id_servicio=?2 then (select co.id_centro from convenio co where co.id_convenio=c.id_convenio and co.estado_registro=?1) else ");
		nativeQuery.append(" (SELECT se.id_centro FROM CASO cas INNER JOIN SEDE se ON se.id_sede = cas.id_sede WHERE cas.id_caso = c.id_caso AND se.estado_registro = ?1 ) end ) ");
		nativeQuery.append(" AND CP.estado_carta=?3 ");
		nativeQuery.append(" AND C.estado_registro=?1 ");
		nativeQuery.append(" AND C.estado_caso not in (?5, ?6) ");
		nativeQuery.append(" AND CP.estado_registro=?1 ");
		nativeQuery.append(" AND s.tipo=?4 ");
		nativeQuery.append(" group by ce.id_centro, ce.nombre ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA);
		query.setParameter(3, UtilDominios.ESTADO_CARTA_PENDIENTE_IMPRESION);
		query.setParameter(4, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(5, UtilDominios.ESTADO_CASO_REGISTRADO);
		query.setParameter(6, UtilDominios.ESTADO_CASO_CERRADO);

		return query.getResultList();
	}
    
    /**
     * Consulta las cartas enviadas y que no han sido actualizadas
     * @return
     */
    public List<InfoBasicaAlertasDTO> alertaActualizarEstadoCarta(String metodoNombramiento, Long valor){
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" with consultaEstadoCartas as ( ");
		nativeQuery.append(" select cp.id_caso as idCaso, cp.fecha_envio as fechaEnvio, cp.direccion_correspondencia as direccion, ce.id_centro as idCentro, cp.id_carta_persona as idCartaPersona, ");
		nativeQuery.append(" (CASE WHEN ?1 = ?2 THEN dbo.diasHabilesEntreDosFechas(cp.fecha_envio, GETDATE()) else  ");
		nativeQuery.append(" DATEDIFF ( dd , cp.fecha_envio , GETDATE()) END ) AS diasHabiles, ");
		nativeQuery.append(" concat(rtrim(p.primer_nombre_o_razon_social), rtrim(' '+p.segundo_nombre), rtrim(' '+p.primer_apellido), rtrim(' '+p.segundo_apellido)) AS nombrePersona, ");
		nativeQuery.append(" ISNULL((case WHEN cp.id_audiencia is not null ");
		nativeQuery.append(" THEN  (select s.nombre from sede s ");
		nativeQuery.append(" inner join AUDIENCIA aud on aud.id_audiencia=cp.id_audiencia ");
		nativeQuery.append(" inner join AGENDAMIENTO ag on ag.id_audiencia=aud.id_audiencia ");
		nativeQuery.append(" inner join sala sa on sa.id_sala=ag.id_sala and s.id_sede=sa.id_sede ");
		nativeQuery.append(" where aud.estado_registro=?4 ");
		nativeQuery.append(" AND ag.estado_registro=?4 ");
		nativeQuery.append(" AND sa.estado_registro=?4)");
		nativeQuery.append(" ELSE (select  top 1 se.nombre from audiencia au ");
		nativeQuery.append(" inner join AGENDAMIENTO age on age.id_audiencia=au.id_audiencia ");
		nativeQuery.append(" inner join sala sal on sal.id_sala=age.id_sala ");
		nativeQuery.append(" inner join sede se on se.id_sede=sal.id_sede ");
		nativeQuery.append(" where au.id_caso=cp.id_caso ");
		nativeQuery.append(" and au.estado_registro=?4 ");
		nativeQuery.append(" and age.estado_registro=?4 ");
		nativeQuery.append(" and sal.estado_registro=?4 ");
		nativeQuery.append(" order by au.hora_inicio desc) ");
		nativeQuery.append(" END ),'') as nombreSede ");
		nativeQuery.append(" from CARTA_PERSONA cp ");
		nativeQuery.append(" inner join persona p on p.id_persona=cp.id_persona ");
		nativeQuery.append(" inner join caso c on cp.id_caso=c.id_caso ");
		nativeQuery.append(" inner join servicio s on s.id_servicio=c.id_servicio and s.tipo=?7, ");
		nativeQuery.append(" centro ce ");
		nativeQuery.append(" where cp.estado_carta=?3 ");
		nativeQuery.append(" and ce.id_centro = (case when c.id_servicio=?5 then (select co.id_centro from convenio co where co.id_convenio=c.id_convenio and co.estado_registro=?4) else ");
		nativeQuery.append(" (SELECT se.id_centro FROM CASO cas INNER JOIN SEDE se ON se.id_sede = cas.id_sede WHERE cas.id_caso = c.id_caso AND se.estado_registro = ?4 ) end )  ");
		nativeQuery.append(" and cp.estado_registro=?4 ");
		nativeQuery.append(" and c.estado_registro=?4 ");
		nativeQuery.append(" and c.estado_caso not in (?8, ?9) ");
		nativeQuery.append(" and p.estado_registro=?4 ) ");
		
		nativeQuery.append(" select * from consultaEstadoCartas ");
		nativeQuery.append(" where diasHabiles > ?6 ");
		nativeQuery.append(" order by fechaEnvio desc ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, metodoNombramiento);
		query.setParameter(2, UtilDominios.TIPO_PERIODICIDAD_HABIL);
		query.setParameter(3, UtilDominios.ESTADO_CARTA_ENVIADA);
		query.setParameter(4, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(5, UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA);
		query.setParameter(6, valor);
		query.setParameter(7, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(8, UtilDominios.ESTADO_CASO_REGISTRADO);
		query.setParameter(9, UtilDominios.ESTADO_CASO_CERRADO);

		return query.getResultList();
	}
	// protected region metodos adicionales end

}
