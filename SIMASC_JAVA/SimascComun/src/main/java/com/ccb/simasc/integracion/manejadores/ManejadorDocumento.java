package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.enumeraciones.TipoOperador;
import com.ccb.simasc.integracion.enumeraciones.TipoOrdenamiento;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionOrdenamiento;
import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.DocumentoIndiceElectronicoDTO;
import com.ccb.simasc.transversal.dto.alertas.InfoBasicaAlertasDTO;
import com.ccb.simasc.transversal.dto.formularios.BusquedaSemanticaDTO;
import com.ccb.simasc.transversal.dto.formularios.DocumentosDigitalizadorDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltroCasosAsignadosDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosCarpetaDTO;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.Dominio;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Documento.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorDocumento extends ManejadorCrud<Documento,Long>{

	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones
	@PersistenceContext
	private transient EntityManager em;
	
	private static final Logger LOGGER = Logger.getLogger(ManejadorDocumento.class.getName());
	private static final String CONSULTA_DOCUMENTOS_DTO = "SELECT id_documento as idDocumento, nombre, tipo_documento as tipoDocumento, publicado, tipo_archivo as tipoArchivo, estado_digitalizacion as estadoDigitalizacion, fecha_asignacion as fechaAsignacion, fecha_digitalizacion as fechaDigitalizacion, descripcion, observaciones, numero_folios as numeroFolios, radicado, fecha_radicacion as fechaRadicacion, codigo_gestor_documental as codigoGestorDocumental, url, duracion, fecha_de_grabacion as fechaDeGrabacion, numero_de_pista as numeroDePista, version, id_usuario_modificacion as idUsuarioModificacion, fecha_ultima_modificacion as fechaUltimaModificacion, estado_registro as estadoRegistro, id_caso as idCaso, id_audiencia as idAudiencia, id_digitalizador as idDigitalizador, id_carpeta as idCarpeta, id_rol_remitente as idRolRemitente, id_persona_remitente as idPersonaRemitente, id_caso_remitente as idCasoRemitente, id_persona_documento as idPersonaDocumento, id_solicitud_servicio as idSolicitudServicio, id_peticion as idPeticion, id_evento_ccb as idEventoCcb, fecha_cargue as fechaCargue, estado from documento ";
	private static final String CONSULTA_DOCUMENTOS = "SELECT * from documento ";
	private static final String CLAUSULA_DOCUMENTOS_ACTIVOS_CASO = "where estado_registro = ?1 and id_caso = ?2 ";	
	
	// protected region atributos end
    
    public ManejadorDocumento() {
        super(Documento.class);
    }    
    
    // protected region metodos adicionales on begin
    // Escriba en esta sección sus modificaciones
	@SuppressWarnings("unchecked")
	public List<Documento> consultarDocumentosActivosConOrdenamiento(Long idCaso, String ordenamiento) {
    	try {

			StringBuilder nativeQuery = new StringBuilder();
			nativeQuery.append(CONSULTA_DOCUMENTOS);		
			nativeQuery.append(CLAUSULA_DOCUMENTOS_ACTIVOS_CASO);		
			nativeQuery.append(ordenamiento);
			 
			Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), Documento.class);
	
			query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(2, idCaso);		

			return query.getResultList();    		
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }
    
    public List<Documento> consultarDocumentosActivos(Long idCaso) {
    	try {
			return consultarDocumentosActivosConOrdenamiento(idCaso, " order by tipo_documento ASC ");
    		
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()), e);
		}
    }
    
	/**
	 * 
	 * 
	 * @param idCaso
	 * @param tipoServicio
	 * @return
	 * @
	 */
	@SuppressWarnings("unchecked")
	public List<Documento> consultarDocumentosActivos(Long idCaso, String tipoServicio)  {

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select d.* from documento d ");		
		nativeQuery.append("inner join caso c on d.id_caso = c.id_caso ");		
		nativeQuery.append("inner join servicio s on c.id_servicio = s.id_servicio ");		
		nativeQuery.append("where d.estado_registro = ?1 and d.id_caso = ?2 ");
		nativeQuery.append("and s.tipo = ?3 ");
		nativeQuery.append("order by fecha_radicacion DESC");
		 
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), Documento.class);

		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idCaso);	
		query.setParameter(3, tipoServicio);		

		return query.getResultList(); 
	}
	
	
	/**
	 * 
	 * 
	 * @param idCaso
	 * @param tipoServicio
	 * @return
	 * @
	 */
	@SuppressWarnings("unchecked")
	public List<Documento> consultarDocumentosCasoTipo(Long idCaso, List<String> tipo)  {
		
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT doc FROM Documento doc");
		jpqlQuery.append(" WHERE doc.idCaso=:").append(Documento.ENTIDAD_DOCUMENTO_ID_CASO);
		jpqlQuery.append(" AND doc.estadoRegistro = :").append(Documento.ENTIDAD_DOCUMENTO_ESTADO_REGISTRO);
		jpqlQuery.append(" AND doc.tipoDocumento IN :").append(Documento.ENTIDAD_DOCUMENTO_TIPO_DOCUMENTO);		
		jpqlQuery.append(" ORDER BY doc.fechaRadicacion DESC ");

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Documento.ENTIDAD_DOCUMENTO_ID_CASO, idCaso);	
		query.setParameter(Documento.ENTIDAD_DOCUMENTO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(Documento.ENTIDAD_DOCUMENTO_TIPO_DOCUMENTO, tipo);
		
		return query.getResultList();
	}
	
	/**
	 * 
	 * 
	 * @param idCaso
	 * @param tipoServicio
	 * @return
	 * @
	 */
	public int obtenerVersionDocumento(Long idCaso, String tipo)  {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT max(d.version) FROM Documento d ");
		jpqlQuery.append("JOIN d.caso c ");
		jpqlQuery.append("WHERE d.idCaso = ?1 ");
		jpqlQuery.append("AND d.tipoDocumento = ?2 ");
		jpqlQuery.append("AND d.estadoRegistro = ?3 ");

		Query query = em.createQuery(jpqlQuery.toString(), Documento.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, tipo);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		Object max = query.getSingleResult() == null?0:query.getSingleResult();
		return (int)max;
	}
	
	
    
    /**
     * Consulta un documento por el nombre del documento, por el codigo del caso y por el tipo de servicio
     * @param idCaso
     * @param nombreDocumento
     * @param tipoServicio
     * @return Documento
     */
   public Documento consultarPorNombreServicioCaso(Documento documento){
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(CONSULTA_DOCUMENTOS);		
		nativeQuery.append(CLAUSULA_DOCUMENTOS_ACTIVOS_CASO);		
		nativeQuery.append("AND nombre = ?3 ");
		nativeQuery.append("AND tipo_documento = ?4 ");
		nativeQuery.append("AND id_documento = ?5 ");
		 
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), Documento.class);

		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, documento.getIdCaso());
		query.setParameter(3, documento.getNombre());
		query.setParameter(4, documento.getTipoDocumento());
		query.setParameter(5, documento.getIdDocumento());	

		return (Documento) query.getSingleResult();
   }  
   
	/**
	 * 
	 * Digitaliza un documento y actualiza su estado digitalizado
	 * @param documento
	 * @return
	 */
	public Documento actualizarDocumentoDigitalizado(Documento documento) {
		return (Documento) mp.updateObject(documento);
	}
	
	/**
	 * Consulta si la audiencia ya cuenta con un acta
	 * @param dto
	 * @return
	 * @
	 */
	public Documento obtenerActaAudiencia(DocumentoDTO dto) {
		Documento doc = new Documento();
		try {		
			StringBuilder nativeQuery = new StringBuilder();
			nativeQuery.append(CONSULTA_DOCUMENTOS);		
			nativeQuery.append(CLAUSULA_DOCUMENTOS_ACTIVOS_CASO);		
			nativeQuery.append("AND tipo_documento = ?3 ");
			nativeQuery.append("AND id_audiencia = ?4 ");
			
			Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), Documento.class);
	
			query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(2, dto.getIdCaso());
			query.setParameter(3, UtilDominios.TIPO_DOCUMENTO_DIG_ACTA);
			query.setParameter(4,  dto.getIdAudiencia());

			doc = (Documento) query.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString()));
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString()));
		}
		return doc;
		
	}
	
	public java.util.List<Documento> obtenerAudiosAudiencia(DocumentoDTO dto) {
		java.util.List<Documento> lstAudios = new ArrayList<Documento>();
		try {
			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append("SELECT d FROM Documento d ");
			jpqlQuery.append("WHERE d.idCaso =: ").append(Documento.ENTIDAD_DOCUMENTO_ID_CASO)
					.append(" AND d.idAudiencia =: ").append(Documento.ENTIDAD_DOCUMENTO_ID_AUDIENCIA)
					.append(" AND UPPER(d.tipoArchivo) IN (SELECT UPPER(dom.dominioPK.codigo) FROM Dominio dom WHERE dom.codigoDomPadre =: ")
					.append(Dominio.ENTIDAD_DOMINIO_CODIGO_DOM_PADRE).append(" AND dom.dominioPadre =: ")
					.append(Dominio.ENTIDAD_DOMINIO_DOMINIO_PADRE).append(") AND d.audiencia.estadoRegistro =: ")
					.append(Audiencia.ENTIDAD_AUDIENCIA_ESTADO_REGISTRO)
					.append(" AND d.caso.estadoRegistro =: ")
					.append(Caso.ENTIDAD_CASO_ESTADO_REGISTRO)
					.append(" AND d.estadoRegistro =: ")
					.append(Documento.ENTIDAD_DOCUMENTO_ESTADO_REGISTRO);
			
			Query query = em.createQuery(jpqlQuery.toString(), Documento.class);
			query.setParameter(Documento.ENTIDAD_DOCUMENTO_ID_CASO, dto.getIdCaso());
			query.setParameter(Documento.ENTIDAD_DOCUMENTO_ID_AUDIENCIA, dto.getIdAudiencia());
			query.setParameter(Dominio.ENTIDAD_DOMINIO_CODIGO_DOM_PADRE, UtilDominios.AGRUPADOR_TIPO_ARCHIVO_AUDIO);
			query.setParameter(Dominio.ENTIDAD_DOMINIO_DOMINIO_PADRE, UtilDominios.DOMINIO_AGRUPADOR_TIPO_ARCHIVO);
			query.setParameter(Documento.ENTIDAD_DOCUMENTO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(Caso.ENTIDAD_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(Audiencia.ENTIDAD_AUDIENCIA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			lstAudios = query.getResultList();
		} catch (NoResultException e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString()));
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString()));
		}
		
		return lstAudios;
	}
	
	public List<Documento> obtenerAudiosAudiencia(Long idCaso, Long idAudiencia)  {
		List<Documento> lstAudios = new ArrayList<Documento>();
		try {
			StringBuilder jpqlQuery = new StringBuilder();
			jpqlQuery.append("SELECT d FROM Documento d ");
			jpqlQuery.append("WHERE d.idCaso =: ").append(Documento.ENTIDAD_DOCUMENTO_ID_CASO)
					.append(" AND d.idAudiencia =: ").append(Documento.ENTIDAD_DOCUMENTO_ID_AUDIENCIA)
					.append(" AND UPPER(d.tipoArchivo) IN (SELECT UPPER(dom.dominioPK.codigo) FROM Dominio dom WHERE dom.codigoDomPadre =: ")
					.append(Dominio.ENTIDAD_DOMINIO_CODIGO_DOM_PADRE).append(" AND dom.dominioPadre =: ")
					.append(Dominio.ENTIDAD_DOMINIO_DOMINIO_PADRE).append(") AND d.audiencia.estadoRegistro =: ")
					.append(Audiencia.ENTIDAD_AUDIENCIA_ESTADO_REGISTRO)
					.append(" AND d.caso.estadoRegistro =: ")
					.append(Caso.ENTIDAD_CASO_ESTADO_REGISTRO)
					.append(" AND d.estadoRegistro =: ")
					.append(Documento.ENTIDAD_DOCUMENTO_ESTADO_REGISTRO);

			Query query = em.createQuery(jpqlQuery.toString(), Documento.class);
			query.setParameter(Documento.ENTIDAD_DOCUMENTO_ID_CASO, idCaso);
			query.setParameter(Documento.ENTIDAD_DOCUMENTO_ID_AUDIENCIA, idAudiencia);
			query.setParameter(Dominio.ENTIDAD_DOMINIO_CODIGO_DOM_PADRE, UtilDominios.AGRUPADOR_TIPO_ARCHIVO_AUDIO);
			query.setParameter(Dominio.ENTIDAD_DOMINIO_DOMINIO_PADRE, UtilDominios.DOMINIO_AGRUPADOR_TIPO_ARCHIVO);
			query.setParameter(Documento.ENTIDAD_DOCUMENTO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(Caso.ENTIDAD_CASO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			query.setParameter(Audiencia.ENTIDAD_AUDIENCIA_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);
			lstAudios = query.getResultList();
		} catch (NoResultException e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString()));
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO034.toString()));
		}

		return lstAudios;
	}
	
	public List<Documento> consultarDocumentosSecretaria(ParametrosCarpetaDTO dto) {

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT d.* from documento d ");
		nativeQuery.append(" where d.id_caso=?1 ");
		if(dto.getFechaInicio() != null && dto.getFechaFin() != null){
			nativeQuery.append(" and cast(d.fecha_radicacion as Date) between cast(?2 as Date) and cast(?3 as Date) ");	
		}
		if(dto.getIdRemitente()!=0 ){
			nativeQuery.append(" AND d.id_persona_remitente = ?4 ");	
		}
		if(dto.getNombreDocumento()!=null && !dto.getNombreDocumento().equals("")){
			nativeQuery.append(" and d.nombre LIKE ?8 ");
		}
		if(dto.getIdCuaderno()!=0){
			nativeQuery.append(" and d.id_carpeta = ?5");
		}
		
		nativeQuery.append(" and d.publicado = ?6");
		nativeQuery.append(" and d.estado_registro=?7");
		nativeQuery.append(" order by d.fecha_radicacion desc ");
			
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), Documento.class);

		query.setParameter(1, dto.getIdCaso());
		query.setParameter(6, dto.isPublicado());
		query.setParameter(7, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(8, "%"+dto.getNombreDocumento()+"%");
		
		if(dto.getFechaInicio() != null && dto.getFechaFin() != null){
			query.setParameter(2, dto.getFechaInicio());
			query.setParameter(3, dto.getFechaFin());
		}
		if(dto.getIdRemitente()!=0 ){
			query.setParameter(4, dto.getIdRemitente());
		}
		if(dto.getIdCuaderno()!=0){
			query.setParameter(5, dto.getIdCuaderno());
		}
		
		return query.getResultList();
	}    
	
	public boolean consultarPorCasoYtipo(Long idCaso, String tipoDocumento) {
		boolean resultado = true;

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(CONSULTA_DOCUMENTOS);		
		nativeQuery.append(CLAUSULA_DOCUMENTOS_ACTIVOS_CASO);		
		nativeQuery.append("AND tipo_documento = ?3 ");
		 
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), Documento.class);

		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idCaso);
		query.setParameter(3, tipoDocumento);

		List<Documento> documentos = query.getResultList();

		if (documentos == null || documentos.isEmpty()) {
			resultado = false;
		}

		return resultado;
	}
	
	/**
	 * Obtiene un documento dado el caso al que pertenece y su identificador
	 * 
	 * @param idCaso
	 * @param idDocumento
	 * @return
	 */
	public Documento obtenerDocumentoCaso(Long idCaso, Long idDocumento) {
		Documento documento = null;

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(CONSULTA_DOCUMENTOS);		
		nativeQuery.append(CLAUSULA_DOCUMENTOS_ACTIVOS_CASO);		
		nativeQuery.append("AND id_documento = ?3 ");
		 
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), Documento.class);

		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idCaso);
		query.setParameter(3, idDocumento);

		try {
			documento = (Documento) query.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
		}

		return documento;
	}
	
	/**
	 *  Obtiene el ultimo documento de una persona por tipo
	 * 
	 * @param idPersona
	 * @param tipo
	 * @return
	 */
	public Documento obtenerDocumentoPersonaPorTipo(Long idPersona, String tipo) {
		List<Documento> documentos = null;
		Documento documento = null;
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT d FROM Documento d ");
		jpqlQuery.append(" WHERE d.idPersonaDocumento = :");
		jpqlQuery.append(Documento.ENTIDAD_DOCUMENTO_ID_PERSONA_DOCUMENTO);
		jpqlQuery.append("   AND d.tipoDocumento = :");
		jpqlQuery.append(Documento.ENTIDAD_DOCUMENTO_TIPO_DOCUMENTO);
		jpqlQuery.append("   AND d.estadoRegistro = :");
		jpqlQuery.append(Documento.ENTIDAD_DOCUMENTO_ESTADO_REGISTRO);
		jpqlQuery.append(" order by d.fechaUltimaModificacion desc ");

		try {
			Query query = em.createQuery(jpqlQuery.toString(), Documento.class);
			query.setParameter(Documento.ENTIDAD_DOCUMENTO_ID_PERSONA_DOCUMENTO, idPersona);
			query.setParameter(Documento.ENTIDAD_DOCUMENTO_TIPO_DOCUMENTO, tipo);
			query.setParameter(Documento.ENTIDAD_DOCUMENTO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

			documentos = query.getResultList();
			
			if(!documentos.isEmpty()){
				documento = documentos.get(0);
			}
			
		} catch (NoResultException e) {
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
		}

		return documento;
	}
	
	/**
	 * Obtiene los documentos activos asociados a una solicitud de servicio
	 * 
	 * @param idSolicitud
	 * @return
	 * @
	 */
	public List<Documento> consultarDocumentosActivosPorSolicitud(Long idSolicitud)  {
		try {
			List<InformacionFiltro> filtros = new ArrayList<>();
			filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, Documento.ENTIDAD_DOCUMENTO_ESTADO_REGISTRO,
					UtilDominios.ESTADO_REGISTRO_ACTIVO, TipoOperador.AND));
			filtros.add(new InformacionFiltro(TipoFiltro.EXACTO, Documento.ENTIDAD_DOCUMENTO_ID_SOLICITUD_SERVICIO,
					idSolicitud));

			List<InformacionOrdenamiento> orders = new ArrayList<>();
			orders.add(new InformacionOrdenamiento(TipoOrdenamiento.ASCENDENTE,
					Documento.ENTIDAD_DOCUMENTO_TIPO_DOCUMENTO));

			return this.consultar(filtros, orders, null);
		} catch (Exception e) {
			throw new SimascException(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR017.toString()),
					e);
		}
	}
	
	
	public boolean consultarDocumentoCasoTipoDocumento(Long idCaso, String tipoDocumento) {
		return consultarPorCasoYtipo(idCaso, tipoDocumento);
	}
	
	/**
	 * Método que obtiene la cantidad de actas y constancias generadas por el conciliador
	 * o la cantidad de documentos devueltos dependiendo del valor del parametro devueltos 
	 * @param devueltos si es true se retorna la cantidad de documentos devueltos del conciliador
	 * @param periodoDesde
	 * @param periodoHasta
	 * @param idPersona
	 * @return cantidad de documentos
	 */
	public BigDecimal obtenerTotalesCriterioCalidad(boolean devueltos, Date periodoDesde, Date periodoHasta, Long idPersona) {
		StringBuilder nativeQuery = new StringBuilder();
		String selectDatos = " select d.id_documento from documento d ";
		String selectCount = " select count(*) from documento d ";
		String unionResultado = " inner join RESULTADO_AUDIENCIA ra on d.id_documento = ra.id_documento and ra.estado_registro = ?1 ";
		String unionAudiencia = " inner join audiencia a on a.id_audiencia = ra.id_audiencia ";
		String condicionAudiencia = " and CAST(a.hora_inicio as Date) between CAST(?2 as date) and CAST(?3 as date) and a.estado_registro = ?1 ";
		String unionPersona = " inner join rol_persona_caso rpc on rpc.id_caso = a.id_caso and rpc.estado = 'ACE' and rpc.id_persona = ?4 and rpc.estado_registro = ?1 ";
		String unionTipoServicio = " inner join TIPO_DE_SERVICIO_ROL t on rpc.id_rol = t.id_rol and tipo_servicio = ?5 and t.estado_registro = ?1 ";
		String where = " where d.estado_registro = ?1 and d.tipo_documento in (select codigo_clasificado from CLASIFICADOR_DOMINIO where codigo_clasificador = ?6 and dominio_clasificador = ?7 and estado_registro = ?1) ";
		if (devueltos) {
			nativeQuery.append(selectCount);
			nativeQuery.append(unionResultado);
			nativeQuery.append(unionAudiencia);
			nativeQuery.append(condicionAudiencia);
			nativeQuery.append(unionPersona);
			nativeQuery.append(unionTipoServicio);
			nativeQuery.append(where);
			nativeQuery.append(" and exists (select 1 from DEVOLUCION_DOCUMENTO_RESULTADO where id_documento = d.id_documento and estado_registro = ?1) ");
		} else {
			nativeQuery.append("select count(*) from ( ");
			nativeQuery.append(selectDatos);
			nativeQuery.append(unionResultado);
			nativeQuery.append(unionAudiencia);
			nativeQuery.append(condicionAudiencia);
			nativeQuery.append(unionPersona);
			nativeQuery.append(unionTipoServicio);
			nativeQuery.append(where);
			nativeQuery.append(" union ");
			nativeQuery.append(selectDatos);
			nativeQuery.append(" inner join audiencia a on a.id_audiencia = d.id_audiencia ");
			nativeQuery.append(condicionAudiencia);
			nativeQuery.append(unionPersona);
			nativeQuery.append(unionTipoServicio);
			nativeQuery.append(where);
			nativeQuery.append(" ) d");
		}
		
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), BigDecimal.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, periodoDesde);
		query.setParameter(3, periodoHasta);
		query.setParameter(4, idPersona);
		query.setParameter(5, UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS);
		query.setParameter(6, UtilDominios.CODIGO_AGRUPADOR_ACTAS_CONSTANCIAS);
		query.setParameter(7, UtilDominios.DOMINIO_AGRUPADOR_ACTAS_CONSTANCIAS);
		
		return (BigDecimal) query.getSingleResult();
	}

	
	/**
	 * Método que consulta los documentos pendientes por digitalizar por caso
	 * @return 
	 */
	public List<InfoBasicaAlertasDTO> alertaDocumentosSinDigitalizar(Long idCaso, Long idPersona, Long idDocumento) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select c.id_caso as idCaso, c.nombre as nombreCaso, ce.id_centro as idCentro from documento d ");
		nativeQuery.append(" inner join caso c on c.id_caso=d.id_caso,  ");
		nativeQuery.append(" centro ce ");
		nativeQuery.append(" where d.id_caso=?1 and d.estado_digitalizacion=?2 and d.id_digitalizador=?3 ");
		nativeQuery.append(" and ce.id_centro = (case when c.id_servicio=?4 then (select co.id_centro from convenio co where co.id_convenio=c.id_convenio and co.estado_registro=?5) else  ");
		nativeQuery.append(" (SELECT se.id_centro FROM CASO cas INNER JOIN SEDE se ON se.id_sede = cas.id_sede WHERE cas.id_caso = c.id_caso AND se.estado_registro = ?5 ) end )  ");
		nativeQuery.append(" and c.estado_registro=?5 ");
		nativeQuery.append(" and d.estado_registro=?5 ");
		nativeQuery.append(" and d.id_documento=?6 ");
		
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), InfoBasicaAlertasDTO.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, UtilDominios.ESTADO_DIGITALIZACION_POR_DIGITALIZAR);
		query.setParameter(3, idPersona);
		query.setParameter(4, UtilConstantes.ID_SERVICIO_CONCILIACION_JORNADA);
		query.setParameter(5, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(6, idDocumento);
		
		return query.getResultList();
	}

	public List<DocumentosDigitalizadorDTO> consultarDocumentosDigitalizar(FiltroCasosAsignadosDTO filtros) {

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT distinct c.id_caso as idCaso, ");
		nativeQuery.append("  c.nombre as nombreCaso, ");
		nativeQuery.append("  tipoDoc.nombre as tipoDocumento, ");
		nativeQuery.append("  doc.nombre as nombreDocumento, ");
		nativeQuery.append("  doc.numero_folios as numeroFolios, ");
		nativeQuery.append("  cuad.nombre as cuaderno, ");
		nativeQuery.append("  doc.id_documento as idDocumento ");
		nativeQuery.append("FROM CASO AS c ");
		nativeQuery.append(" INNER JOIN  DOCUMENTO doc ");
		nativeQuery.append("  ON c.id_caso=doc.id_caso ");
		nativeQuery.append(" INNER JOIN DOMINIO tipoDoc ");
		nativeQuery.append("  ON doc.tipo_documento=tipoDoc.codigo AND tipoDoc.dominio= ?9 ");	
		nativeQuery.append(" LEFT JOIN CARPETA car ");
		nativeQuery.append("   ON c.id_caso = car.id_caso ");
		nativeQuery.append("   AND doc.id_carpeta = car.id_carpeta ");
		nativeQuery.append(" LEFT JOIN CARPETA cuad ");
		nativeQuery.append("   ON car.id_carpeta_contenedora = cuad.id_carpeta ");
		nativeQuery.append("   AND c.id_caso = cuad.id_caso ");
		nativeQuery.append("   AND cuad.es_cuaderno = 1 ");
		nativeQuery.append(" INNER JOIN SERVICIO ser ");
		nativeQuery.append("  ON c.id_servicio=ser.id_servicio ");
		nativeQuery.append(" WHERE ");
		nativeQuery.append(" c.estado_registro  = ?1 ");
		nativeQuery.append(" AND doc.estado_registro  = ?1 ");
		nativeQuery.append(" AND ser.estado_registro= ?1 ");
		nativeQuery.append(" AND doc.estado_digitalizacion= ?2 ");

		if (filtros.tieneNombreCaso()) {
			nativeQuery.append(" AND c.nombre LIKE ?5 ");
		}
		if (filtros.tieneCodigoCaso()) {
			nativeQuery.append(" AND c.id_caso = ?6 ");
		}
		
		if(filtros.getFechaDesde()!=null && filtros.getFechaHasta()!=null){
			nativeQuery.append(" AND c.fecha_radicacion between ?7 and ?8 ");
		}

		nativeQuery.append(" ORDER BY c.id_caso desc ");

		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), DocumentosDigitalizadorDTO.class);

		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_DIGITALIZACION_POR_DIGITALIZAR);
		
		if (filtros.tieneNombreCaso()) {
			query.setParameter(5, '%'+filtros.getNombreCaso()+'%' );
		}
		if (filtros.tieneCodigoCaso()) {
			query.setParameter(6, filtros.getCodigoCaso());
		}
		if(filtros.getFechaDesde()!=null && filtros.getFechaHasta()!=null){
			query.setParameter(7, filtros.getFechaDesde());
			query.setParameter(8, filtros.getFechaHasta());
		}
		query.setParameter(9,UtilDominios.DOMINIO_TIPO_DOCUMENTO_DIG);
		
		return query.getResultList();
	}    
	
	public List<DocumentoDTO> consultarDocumento(Long idCaso, String nombreDocumento, String tipoDocumento) {

		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" SELECT id_documento as idDocumento, nombre as nombre, tipo_documento as tipoDocumento, "
				+ "id_audiencia as idAudiencia, url, tipo_archivo as tipoArchivo, estado, id_caso as idCaso, "
				+ "fecha_cargue as fechaCargue from documento ");
		nativeQuery.append(" where estado_registro=?1 ");
		if (idCaso!=null) {
			nativeQuery.append(" and id_caso=?2 ");
		}
		if (nombreDocumento!=null) {
			nativeQuery.append(" and nombre=?3 ");		
		}
		if (tipoDocumento!=null) {
			nativeQuery.append(" and tipo_documento=?4 ");
		}
		nativeQuery.append(" order by fecha_ultima_modificacion desc ");
		 
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), DocumentoDTO.class);

		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		if (idCaso!=null) {
			query.setParameter(2, idCaso);
		}
		if (nombreDocumento!=null) {
			query.setParameter(3, nombreDocumento);
		}
		if (tipoDocumento!=null) {
			query.setParameter(4, tipoDocumento);
		}
		
		return query.getResultList();
	}    
	
	public List<DocumentoDTO> consultarDocumentosExpediente(Long idCaso) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(CONSULTA_DOCUMENTOS_DTO);		
		nativeQuery.append(CLAUSULA_DOCUMENTOS_ACTIVOS_CASO);		
		nativeQuery.append(" order by id_carpeta, convert(varchar(10), fecha_radicacion,111), nombre ");
		 
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), DocumentoDTO.class);

		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idCaso);		

		return query.getResultList(); 		

	}    
	
	public List<DocumentoDTO> consultarDocumentosPorIdCarpeta(Long idCarpeta) {
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(CONSULTA_DOCUMENTOS_DTO);
		nativeQuery.append(" where estado_registro=?1 ");
		nativeQuery.append(" and id_carpeta=?2 ");		
		nativeQuery.append(" order by descripcion, convert(varchar(10), fecha_radicacion,111) ");
		 
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString(), DocumentoDTO.class);

		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, idCarpeta);		
		
		return query.getResultList();
	}   

	/**
	 * Método que realiza el borrado logico de los eventos que no se encuentren en los tipos de evento
	 * enviados por parametro para el caso
	 * @param idCaso
	 * @param tipos lista con los eventos que no se borraran
	 */
	public void modificarUbicacionDocuemnto(Long idCarpeta, Long idDocumento) {
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("update DOCUMENTO set id_carpeta = ?1 ");
		nativeQuery.append("where id_documento = ?2 ");
		nativeQuery.append(" and estado_registro = ?3");
		
		Query query = getEntityManager().createNativeQuery(nativeQuery.toString());
		query.setParameter(1,idCarpeta);
		query.setParameter(2, idDocumento);
		query.setParameter(3, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		query.executeUpdate();
	}
	
        
	public List<BusquedaSemanticaDTO> consultarBusqueda(Long idCaso, String keyword, String tipoDocumento, String fechaInicial, String fechaFinal) {

		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT d.id_documento as idDocumento, d.nombre as nombreDocumento, d.descripcion as descripcionDocumento, d.tipo_archivo as tipoArchivo,d.estado as estado, d.url as url, ");
		jpqlQuery.append("d.fecha_cargue as fechaCargue, d.fecha_radicacion as fechaRadicacion,cu.nombre AS nombreCuaderno, cp.nombre AS nombreCarpeta ");
		jpqlQuery.append("FROM DOCUMENTO d LEFT JOIN CARPETA cp ON d.id_caso = cp.id_caso AND d.id_carpeta =cp.id_carpeta ");
		jpqlQuery.append("LEFT JOIN CARPETA cu ON  cp.id_carpeta_contenedora = cu.id_carpeta AND d.id_caso = cu.id_caso AND cu.es_cuaderno =1 ");
		jpqlQuery.append("WHERE d.id_caso =?1 ");
		jpqlQuery.append(" AND d.estado_registro = ?6 ");
		
		if (keyword!=null) {
			if(keyword.startsWith("'") && keyword.endsWith("'")) {
				keyword = keyword.substring(1, keyword.length()-1);
				jpqlQuery.append(" AND d.descripcion LIKE CONCAT('%',?2,'%')");
			}else {
				jpqlQuery.append("AND (");
				String[] words = keyword.split(" ");
				for (int i = 0; i < words.length; i++) {
					String word = words[i];
					if(i == words.length-1) {
						jpqlQuery.append("d.descripcion LIKE CONCAT('%','"+word+"','%') ");
					}else {
						jpqlQuery.append("d.descripcion LIKE CONCAT('%','"+word+"','%') OR ");
					}
				}
				jpqlQuery.append(" )");
			}
		}
		if (tipoDocumento!=null) {
			jpqlQuery.append(" AND d.tipo_documento=?3 ");
			
		}
		
		if (fechaInicial !=null && fechaFinal!=null ) {
			jpqlQuery.append(" AND d.fecha_radicacion >= ?4");
			jpqlQuery.append(" AND d.fecha_radicacion <= ");
			jpqlQuery.append("CONCAT(?5,' 23:59:59.999')");
		}
		Query query = getEntityManager().createNativeQuery(jpqlQuery.toString(), BusquedaSemanticaDTO.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, keyword);
		query.setParameter(3, tipoDocumento);
		query.setParameter(4, fechaInicial);
		query.setParameter(5, fechaFinal);
		query.setParameter(6, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		return query.getResultList();
	}  
	
	
	public List<DocumentoIndiceElectronicoDTO> generarIndiceElectronico(Long idCaso) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT     d.id_documento AS idDocumento, ");
		jpqlQuery.append("           (SELECT zg.nombre ");
		jpqlQuery.append("           FROM    ZONA_GEOGRAFICA zg ");
		jpqlQuery.append("           WHERE   id_zona_geografica = ");
		jpqlQuery.append("                   (SELECT c2.id_ciudad ");
		jpqlQuery.append("                   FROM    CASO c1, ");
		jpqlQuery.append("                           SEDE s , ");
		jpqlQuery.append("                           CENTRO c2 ");
		jpqlQuery.append("                   WHERE   C1.ID_CASO  = ?1 ");
		jpqlQuery.append("                   AND     c1.id_sede  = s.id_sede ");
		jpqlQuery.append("                   AND     s.id_centro = c2.id_centro ");
		jpqlQuery.append("                   ) ");
		jpqlQuery.append("           ) ");
		jpqlQuery.append("           AS ciudad, ");
		jpqlQuery.append("           (SELECT nombre ");
		jpqlQuery.append("           FROM    SERVICIO ");
		jpqlQuery.append("           WHERE   id_servicio     = c.id_servicio ");
		jpqlQuery.append("           AND     estado_registro = ?2 ");
		jpqlQuery.append("           ) ");
		jpqlQuery.append("                                            AS servicioCaso  , ");
		jpqlQuery.append("           c.id_caso                        AS codigoCaso    , ");
		jpqlQuery.append("           c.nombre                         AS nombreCaso    , ");
		jpqlQuery.append("           CAST(d.fecha_radicacion AS DATE) AS fechaDocumento, ");
		jpqlQuery.append("           tipoDoc.nombre                   AS tipoDocumento , ");
		jpqlQuery.append("           COALESCE ( ");
		jpqlQuery.append("                      (SELECT nombre ");
		jpqlQuery.append("                      FROM    CUADERNO c2 ");
		jpqlQuery.append("                      WHERE   c2.id_cuaderno = car.id_cuaderno ");
		jpqlQuery.append("                      ) ");
		jpqlQuery.append("                     ,'')                       cuaderno           , ");
		jpqlQuery.append("           car.nombre                           AS carpeta         , ");
		jpqlQuery.append("           d.tipo_archivo                       AS formato         , ");
		jpqlQuery.append("           d.nombre                             AS nombreDocumento , ");
		jpqlQuery.append("           COALESCE(d.numero_folios,1)             cantidadPaginas , ");
		jpqlQuery.append("           CAST (COALESCE(d.peso,0) AS VARCHAR) AS peso            , ");
		jpqlQuery.append("           (SELECT stuff( ");
		jpqlQuery.append("                   (SELECT   ', ' + concat (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) ");
		jpqlQuery.append("                   FROM      ROL_PERSONA_CASO rp ");
		jpqlQuery.append("                             LEFT JOIN PERSONA pe ");
		jpqlQuery.append("                             ON        rp.id_persona=pe.id_persona ");
		jpqlQuery.append("                   WHERE     rp.id_caso             =c.id_caso ");
		jpqlQuery.append("                   AND       rp.estado_registro     = ?2 ");
		jpqlQuery.append("                   AND       rp.id_rol              = ");
		jpqlQuery.append("                             (SELECT id_rol ");
		jpqlQuery.append("                             FROM    rol ");
		jpqlQuery.append("                             WHERE   nombre= ?4 ");
		jpqlQuery.append("                             ) ");
		jpqlQuery.append("                             FOR xml path('') ");
		jpqlQuery.append("                   ) ");
		jpqlQuery.append("                   , 1, 1, '') ");
		jpqlQuery.append("           ) ");
		jpqlQuery.append("           AS parteDemandante, ");
		jpqlQuery.append("           (SELECT stuff( ");
		jpqlQuery.append("                   (SELECT   ', ' + concat (pe.primer_nombre_o_razon_social, ' ', pe.segundo_nombre, ' ', pe.primer_apellido, ' ', pe.segundo_apellido) ");
		jpqlQuery.append("                   FROM      ROL_PERSONA_CASO rp ");
		jpqlQuery.append("                             LEFT JOIN PERSONA pe ");
		jpqlQuery.append("                             ON        rp.id_persona=pe.id_persona ");
		jpqlQuery.append("                   WHERE     rp.id_caso             =c.id_caso ");
		jpqlQuery.append("                   AND       rp.estado_registro     = ?2 ");
		jpqlQuery.append("                   AND       rp.id_rol              = ");
		jpqlQuery.append("                             (SELECT id_rol ");
		jpqlQuery.append("                             FROM    rol ");
		jpqlQuery.append("                             WHERE   nombre= ?5 ");
		jpqlQuery.append("                             ) ");
		jpqlQuery.append("                             FOR xml path('') ");
		jpqlQuery.append("                   ) ");
		jpqlQuery.append("                   , 1, 1, '') ");
		jpqlQuery.append("           ) ");
		jpqlQuery.append("           AS parteDemandado ");
		jpqlQuery.append("FROM       CASO c ");
		jpqlQuery.append("           LEFT JOIN DOCUMENTO d ");
		jpqlQuery.append("           ON         c.id_caso = d.id_caso ");
		jpqlQuery.append("           INNER JOIN DOMINIO tipoDoc ");
		jpqlQuery.append("           ON         d.tipo_documento=tipoDoc.codigo ");
		jpqlQuery.append("           AND        tipoDoc.dominio = ?3 ");
		jpqlQuery.append("           LEFT JOIN CARPETA car ");
		jpqlQuery.append("           ON         c.id_caso    = car.id_caso ");
		jpqlQuery.append("           AND        d.id_carpeta = car.id_carpeta ");
		jpqlQuery.append("WHERE      c.estado_registro       = ?2 ");
		jpqlQuery.append("AND        d.estado_registro       = ?2 ");
		jpqlQuery.append("AND        c.id_Caso               = ?1 ");
		jpqlQuery.append("ORDER BY   d.fecha_radicacion ASC");

		Query query = getEntityManager().createNativeQuery(jpqlQuery.toString(), DocumentoIndiceElectronicoDTO.class);
		query.setParameter(1, idCaso);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(3, UtilDominios.DOMINIO_TIPO_DOCUMENTO_DIG);
		query.setParameter(4, UtilDominios.ROL_PERSONA_PARTE_DEMANDANTE);
		query.setParameter(5, UtilDominios.ROL_PERSONA_PARTE_DEMANDADA);
		
		return query.getResultList();
	}
	
	public int obtenerCantidadDocumentoPorCarpeta(Long idCarpeta)  {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("select COUNT(id_documento) as documentos ");
		jpqlQuery.append("from DOCUMENTO d where d.id_carpeta = ?1  and estado_registro = ?2 ");

		Query query = getEntityManager().createNativeQuery(jpqlQuery.toString(), Integer.class);
		query.setParameter(1, idCarpeta);
		query.setParameter(2, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.getSingleResult();
		return (int)query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public int consultarCantidadDocumentosIguales(Long idDocumento) {
		
		StringBuilder nativeQueryCantDoc = new StringBuilder();
		nativeQueryCantDoc.append(" SELECT COUNT(*) as count ");
		nativeQueryCantDoc.append(" FROM DOCUMENTO d ");
		nativeQueryCantDoc.append(" WHERE url = ");
		nativeQueryCantDoc.append(" (SELECT d2.url from DOCUMENTO d2 WHERE d2.id_documento=?1) ");
		nativeQueryCantDoc.append(" GROUP BY d.url ");
		
		Query query = getEntityManager().createNativeQuery(nativeQueryCantDoc.toString(), Integer.class);		
		query.setParameter(1, idDocumento);
		
		List<Integer> resultList = (List<Integer>) query.getResultList();
		if (resultList.isEmpty()) {
			return 0; 
		}else { 			
			return resultList.get(0); 	
		}
	}
}

