package com.ccb.simasc.integracion.manejadores;
// protected region imports manejador on begin
// Escriba en esta sección sus modificaciones


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroServicioSorteo;
import com.ccb.simasc.transversal.dto.SorteoArbitrosDisponiblesDTO;
import com.ccb.simasc.transversal.dto.SorteoLiberacionListaDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.ParametroServicioSorteo;
import com.ccb.simasc.transversal.entidades.ParametroSorteo;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaServicioMateria;
import com.ccb.simasc.transversal.entidades.Sorteo;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilSimasc;


// protected region imports manejador end

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre
 * la tabla correspondiente a la entidad Sorteo.
 * 
 * @author jsoto
 */
@Stateless
public class ManejadorSorteo extends ManejadorCrud<Sorteo,Long>{

	// protected region atributos on begin
  	// Escriba en esta sección sus modificaciones
	private static final String SORTEO_NULL_EXCEPTION = "Se intento cancelar un sorteo inexistente.";
	
    @PersistenceContext
    private transient EntityManager em;
    
    @EJB
	private ManejadorParametroServicioSorteo manejadorParametroServicioSorteo;
    @EJB
	private ManejadorPersonaServicioMateria manejadorPersonaSrvmat;
    
    @EJB
    private ManejadorMateria manejadorMateria;
    
    @EJB
	private ManejadorHistoricoPersonaServicioMateria historicoPersSerMateria;
    
    // protected region atributos end
    
    public ManejadorSorteo() {
        super(Sorteo.class);
    }    
    
    // protected region metodos adicionales on begin
   	// Escriba en esta sección sus modificaciones
    /**
     * Coloca el sorteo que se pasa como parámetro en estado cancelado
     * @param sorteo
     * @throws SimascException Si el sorteo es nulo
     */
    public void cancelarSorteo(Sorteo sorteo) throws SimascException{
		if (sorteo != null) {
			sorteo.setEstado(UtilDominios.ESTADO_SORTEO_CANCELADO);
			this.actualizar(sorteo);
		} else {
			throw new SimascException(SORTEO_NULL_EXCEPTION);
		}
	}
        
    /**
     * Consulta que trae todas los Sorteos asociados a un Caso
     * @param caso
     * @return
     */
    public List<Sorteo> consultarSorteosPorCaso(Caso caso) {
    	List<Sorteo> sorteos = em.createQuery("SELECT s FROM Sorteo s WHERE s.idCaso = :idCaso ORDER BY s.fechaRealizacion DESC", Sorteo.class)
    			.setParameter("idCaso", caso.getIdCaso())
    			.getResultList();
    	return sorteos;
    }        
    
    /**
     * Consulta los parametros configurados para el sorteo
     * @return
     * @throws SimascException
     */
    public ParametroSorteo consultarParametrosSorteo() {
    	ParametroSorteo parametrosSorteo = new ParametroSorteo();
    	List<ParametroSorteo> parametros = em.createNamedQuery("ParametroSorteo.findAll").getResultList();
    	if (parametros != null && parametros.size() > 0) {
    		parametrosSorteo = parametros.get(0);
    		//carga lazy
    		parametrosSorteo.getDiaSorteoList();
    		parametrosSorteo.getParametroServicioSorteoList();
    		parametrosSorteo.getParamEstadoArbitroPreseleccionList();
    	}
    	return parametrosSorteo;
    }

    /**
     * /**
     * realiza liberacion de lista para las personas enviadas, 
     * para sorteo del caso enviado.
     * @param caso caso al cual se le va a realizar liberacion de lista
     * @param personas personas preseleccionadas para sorteo
     * @return retorna la lista de persona que se liberaron 
     */
	public List<PersonaServicioMateria> liberarListaSorteo(Caso caso, List<Persona> personas, 
			String usuarioSesion, String tipoSorteo) {
		
		ParametroServicioSorteo pServicioSorteo = manejadorParametroServicioSorteo.buscar(Long.valueOf(tipoSorteo));
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT psm FROM PersonaServicioMateria psm, ParametroServicioSorteo pss ");
		jpqlQuery.append(" WHERE psm.estadoParaSorteo=:" + PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ESTADO_PARA_SORTEO);
		jpqlQuery.append(" AND psm.fechaFinDeVigencia is null");
		jpqlQuery.append(" AND psm.idServicio = pss.idServicio ");
		jpqlQuery.append(" AND pss.idParametrosServiciosSorteo =:" + ParametroServicioSorteo.ENTIDAD_PARAMETRO_SERVICIO_SORTEO_PK);
		jpqlQuery.append(" AND psm.idMateria =:" + PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_MATERIA);
		jpqlQuery.append(" AND psm.idPersona IN :" + PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_PERSONA);

		List<Long> personasList = new ArrayList<Long>();
		for (Persona persona : personas) {
			personasList.add(persona.getIdPersona());
		}		
		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ESTADO_PARA_SORTEO, 
				UtilDominios.ESTADO_PERSONA_SORTEO_INACTIVO);
		query.setParameter(ParametroServicioSorteo.ENTIDAD_PARAMETRO_SERVICIO_SORTEO_PK, 
				Long.valueOf(tipoSorteo));
		
		Long idMateria = caso.getIdMateria();	
		//si el sorteo es sin materia se toma SIN_MATERIA
		if (!pServicioSorteo.getSorteoConMateria()) {
			idMateria = manejadorMateria.consultaSinMateria().getIdMateria();
		}
		query.setParameter(PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_MATERIA, idMateria);
		
		query.setParameter(PersonaServicioMateria.ENTIDAD_PERSONA_SERVICIO_MATERIA_ID_PERSONA, 
				personasList);	
		List<PersonaServicioMateria> perServMateriaList = query.getResultList();
				
		List<String> parametrosMensaje = new ArrayList<String>();
		parametrosMensaje.add(caso.getIdCaso().toString());
		String observaciones = String.format(
				MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO070.toString()),
				parametrosMensaje.toArray());
		
		Date fechaActual = new Date();
		for (PersonaServicioMateria psm: perServMateriaList) {			
			psm.setEstadoParaSorteo(UtilDominios.ESTADO_PERSONA_SORTEO_ACTIVO);
			psm.setMotivoEstadoSorteo(null);
			psm.setFechaUltimaModificacion(fechaActual);
			psm.setIdUsuarioModificacion(usuarioSesion);
			psm.setIdCaso(null);
			manejadorPersonaSrvmat.actualizar(psm);

			historicoPersSerMateria.registroHistoricoSorteo(psm, observaciones, usuarioSesion);			
		}
		return perServMateriaList;
	}
    
    /**
     * Metodo que retorna verdadero o falso dependiendo si hubo liberacion de lista con los parametros recibidos
     * @param idMateria
     * @param idServicio
     * @param tipoCuantia
     * @param fechaLiberacion
     * @return Boolean
     */
	public Boolean liberacionLista(Long idMateria, Long idServicio, String tipoCuantia, Date fechaLiberacion) {
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append(" SELECT s FROM Sorteo s ");
		jpqlQuery.append(" WHERE s.materiaCaso =: ");
		jpqlQuery.append(Sorteo.ENTIDAD_SORTEO_MATERIA_CASO);
		jpqlQuery.append(" AND s.servicioCaso =: ");
		jpqlQuery.append(Sorteo.ENTIDAD_SORTEO_SERVICIO_CASO);
		jpqlQuery.append(" AND s.tipoCuantia =: ");
		jpqlQuery.append(Sorteo.ENTIDAD_SORTEO_TIPO_CUANTIA);
		jpqlQuery.append(" AND s.liberoLista =: ");
		jpqlQuery.append(Sorteo.ENTIDAD_SORTEO_LIBERO_LISTA);
		jpqlQuery.append(" AND s.horaLiberacion >=: ");
		jpqlQuery.append(Sorteo.ENTIDAD_SORTEO_HORA_LIBERACION);
		jpqlQuery.append(" AND s.estadoRegistro =: ");
		jpqlQuery.append(Sorteo.ENTIDAD_SORTEO_ESTADO_REGISTRO);

		Query query = mp.createQuery(jpqlQuery.toString());
		query.setParameter(Sorteo.ENTIDAD_SORTEO_MATERIA_CASO, idMateria);
		query.setParameter(Sorteo.ENTIDAD_SORTEO_SERVICIO_CASO, idServicio);
		query.setParameter(Sorteo.ENTIDAD_SORTEO_TIPO_CUANTIA, tipoCuantia);
		query.setParameter(Sorteo.ENTIDAD_SORTEO_LIBERO_LISTA, true);
		query.setParameter(Sorteo.ENTIDAD_SORTEO_HORA_LIBERACION, fechaLiberacion);
		query.setParameter(Sorteo.ENTIDAD_SORTEO_ESTADO_REGISTRO, UtilDominios.ESTADO_REGISTRO_ACTIVO);

		return query.getResultList().size() >= 1 ? true : false;
	}
	

	/**
	 * Crea un nuevo sorteo en la base de datos
	 * @param sorteo
	 * @return id del sorteo creado
	 */
	public Sorteo registrarSorteo(Sorteo sorteo) {	
				
		List<Sorteo> consultaAntes = this.consultarSorteosPorCaso(sorteo.getCaso());
		this.crear(sorteo);
		List<Sorteo> consultaDesp = this.consultarSorteosPorCaso(sorteo.getCaso());
		
		Set<Sorteo> before = new HashSet<Sorteo>(consultaAntes);
		Set<Sorteo> after = new HashSet<Sorteo>(consultaDesp);
		after.removeAll(before);
		Sorteo sor = after.iterator().next();		
		return sor;
	}
	
	/**
	 * Consulta los sorteos que se encuentran en estado realizado
	 * en un rago de fechas dadas
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	public List<Sorteo> consultarSorteosRealizados(
			Date fechaInicial, Date fechaFinal, Long tipoCaso) {				
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT s FROM Sorteo s ");
		jpqlQuery.append("WHERE s.fechaRealizacion ");
		jpqlQuery.append("BETWEEN :startDate AND :endDate ");
		jpqlQuery.append("AND s.estado =:estado ");
		jpqlQuery.append("AND s.estadoRegistro =:estadoRegistro ");
		if (tipoCaso > 0) {
			jpqlQuery.append("AND s.servicioCaso = :tipoCaso");
		}
						
		TypedQuery<Sorteo> tipoQuey = em.createQuery(jpqlQuery.toString(), Sorteo.class);
		tipoQuey.setParameter("startDate", fechaInicial);
		tipoQuey.setParameter("endDate", UtilSimasc.agregarDiasAFecha(fechaFinal, 1));
		tipoQuey.setParameter("estado", UtilDominios.ESTADO_SORTEO_REALIZADO);
		tipoQuey.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		if (tipoCaso > 0) {
			tipoQuey.setParameter("tipoCaso", tipoCaso);
		}	
				
    	return tipoQuey.getResultList();
    }
	
	/**
	 * Consulta los sorteos que se encuentran en estado realizado
	 * en un rago de fechas dadas
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	public List<Sorteo> consultarSorteosPublicosRealizados(
			Date fechaInicial, Date fechaFinal) {				
		StringBuilder jpqlQuery = new StringBuilder();
		jpqlQuery.append("SELECT s FROM Sorteo s ");
		jpqlQuery.append("WHERE s.fechaRealizacion ");
		jpqlQuery.append("BETWEEN :startDate AND :endDate ");
		jpqlQuery.append("AND s.estado =:estado ");
		jpqlQuery.append("AND s.estadoRegistro =:estadoRegistro ");		
		jpqlQuery.append("AND s.servicioCaso <> :tipoCaso");
		
				
		TypedQuery<Sorteo> tipoQuey = em.createQuery(jpqlQuery.toString(), Sorteo.class);
		tipoQuey.setParameter("startDate", fechaInicial);
		tipoQuey.setParameter("endDate", UtilSimasc.agregarDiasAFecha(fechaFinal, 1));
		tipoQuey.setParameter("estado", UtilDominios.ESTADO_SORTEO_REALIZADO);
		tipoQuey.setParameter("estadoRegistro", UtilDominios.ESTADO_REGISTRO_ACTIVO);
		tipoQuey.setParameter("tipoCaso", Long.parseLong(UtilConstantes.ID_SERVICIO_ARBITRAJE_INTERNACIONAL));
						
    	return tipoQuey.getResultList();
    }

	/**
	 * 	Consulta el ultimo sorteo de liberacion de lista si no existe fecha consulta el primer 
	 * sorteo para el servicio y manteria seleccionado
	 * @param idServicio Servicio del sorteo
	 * @param idMateria Materia del sorteo
	 * @param conMateria si el caso fue o no sorteado con materia
	 * @param idCaso caso que no se debe tener en cuenta para la consulta
	 * @return
	 */
	public Date consultarFechaSorteoLiberacion(Long idPersona, Long idCaso, Boolean liberacion){
		StringBuilder nativeQuery = new StringBuilder();
		Date fechaSorteo;	
		if(liberacion){
			nativeQuery.append(" SELECT  MAX(sl.fecha_realizacion)  ");			
		}else{
			nativeQuery.append(" SELECT  MIN(sl.fecha_realizacion) ");	
		}
		nativeQuery.append(" FROM  SORTEO sl  ");
		nativeQuery.append(" INNER JOIN SORTEO s ON sl.servicio_caso = s.servicio_caso  ");
		nativeQuery.append(" INNER JOIN ROL_PERSONA_CASO rpc on s.id_sorteo = rpc.id_sorteo  ");
		nativeQuery.append(" INNER JOIN ROL_PERSONA rp on rpc.id_persona = rp.id_persona AND rp.estado_registro = ?1  ");
		nativeQuery.append(" AND CAST (rp.fecha_inicio_vigencia AS DATE) <= CAST (GETDATE() AS DATE)  ");
		nativeQuery.append(" AND rp.fecha_fin_vigencia IS NULL  ");
		nativeQuery.append(" INNER JOIN PARAMETRO_SERVICIO_SORTEO pss ON pss.id_servicio = s.servicio_caso AND pss.id_rol = rp.id_rol  ");
		nativeQuery.append(" WHERE rpc.id_persona = ?4 AND s.id_caso = ?5 AND  ");
		nativeQuery.append(" ( (pss.sorteo_con_lista = 1 AND( (rp.id_lista = 2 AND sl.tipo_cuantia = ?3 OR rp.id_lista = 1 and sl.tipo_cuantia <> ?3 )))  ");
		nativeQuery.append(" OR pss.sorteo_con_lista = 0)  ");
		if(liberacion){
			nativeQuery.append(" AND sl.libero_lista = 1  ");			
		}
		nativeQuery.append(" AND sl.estado = ?2  ");
		nativeQuery.append(" AND sl.estado_registro = ?1  ");
		nativeQuery.append(" AND ((pss.sorteo_con_materia = 1 AND sl.materia_caso = s.materia_caso) OR pss.sorteo_con_materia = 0)  ");

		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), Date.class);		
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.ESTADO_SORTEO_REALIZADO);
		query.setParameter(3, UtilDominios.TIPO_CUANTIA_MENOR);
		query.setParameter(4, idPersona);
		query.setParameter(5, idCaso);
		
		try {
			fechaSorteo = (Date) query.getSingleResult();
		} catch (NonUniqueResultException | NoResultException e) {
			fechaSorteo = null;
		}
		
		return fechaSorteo;
	}
	
	/**
	 * Consulta los sorteos que se encuentran en estado realizado
	 * en un rago de fechas dadas
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	public List<SorteoArbitrosDisponiblesDTO> consultarSorteosArbitrosDisponibles(
			Long numeroCaso, Date fechaSorteo) {				
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select ");
		nativeQuery.append("fecha_realizacion as fechaSorteo, s.id_caso as idCaso, c.nombre as nombreCaso, ");
		nativeQuery.append("ss.nombre as tipoCaso, ");
		nativeQuery.append("case when c.id_servicio = ?4 then ");
		nativeQuery.append("(select nombre from dominio where codigo = s.tipo_cuantia and dominio = ?6 ) ");
		nativeQuery.append("else ");
		nativeQuery.append("(select nombre from dominio where codigo = s.tipo_cuantia and dominio = ?5 ) ");
		nativeQuery.append("end as tipoCuantia, ");
		nativeQuery.append("c.valor_pretensiones as cuantia, m.nombre as materia, ");
		nativeQuery.append("case s.preseleccion when 1 then 'Si' else 'No' end as preseleccion, ");
		nativeQuery.append("isnull(dp.nombre, '') as tipoPreseleccion,  ");
		nativeQuery.append("case s.libero_lista when 1 then 'Si' else 'No' end as liberacionLista, ");
		nativeQuery.append("case  ");
		nativeQuery.append("when fecha_realizacion < '2017/10/13' then (select concat(primer_nombre_o_razon_social, ' ', segundo_nombre, ' ', primer_apellido, ' ', segundo_apellido) from persona where id_persona = s.id_usuario_modificacion) ");
		nativeQuery.append("else (select concat(primer_nombre_o_razon_social, ' ', segundo_nombre, ' ', primer_apellido, ' ', segundo_apellido) from persona where numero_documento = s.id_usuario_modificacion) ");
		nativeQuery.append("end as funcionarioSorteo, ");
		nativeQuery.append("s.id_sorteo  as idSorteo, ");
		nativeQuery.append("CASE WHEN c.arbitraje_consumo = 1 THEN 'SI' ELSE 'NO' END as consumo, ");
		nativeQuery.append("(select da.nombre from dominio da where da.dominio =  ?7 ");
		nativeQuery.append("AND da.codigo = (select a.tipo_sorteo from AUDIENCIA a where a.id_caso = s.id_caso  and a.id_sorteo = s.id_sorteo )) as tipoSorteo ");
		nativeQuery.append("from sorteo s ");
		nativeQuery.append("inner join caso c on s.id_caso = c.id_caso ");
		nativeQuery.append("inner join servicio ss on s.servicio_caso = ss.id_servicio ");
		nativeQuery.append("inner join materia m on s.materia_caso = m.id_materia ");
		nativeQuery.append("left join DOMINIO dp on s.quien_preselecciona = dp.codigo and dominio = ?3 ");
		nativeQuery.append("WHERE ");
		
		if(numeroCaso != null && numeroCaso > 0) {
			nativeQuery.append("s.id_caso ");
			nativeQuery.append("= ?1 ");
		}
		if((numeroCaso != null && numeroCaso > 0) && fechaSorteo != null) {
			nativeQuery.append("and ");
		}
		if(fechaSorteo != null) {
			nativeQuery.append("cast(fecha_realizacion As Date) ");
			nativeQuery.append("= ?2 ");
		}
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), SorteoArbitrosDisponiblesDTO.class);
		if(numeroCaso != null && numeroCaso > 0) {
			query.setParameter(1, numeroCaso);
		}
		if(fechaSorteo != null) {
			query.setParameter(2, fechaSorteo);
		}		

		query.setParameter(3, UtilDominios.DOMINIO_QUIEN_PRESELECCIONA);	
		query.setParameter(4, UtilConstantes.ID_SERVICIO_ARBITRAJE_ABREVIADO);
		query.setParameter(5, UtilDominios.DOMINIO_TIPO_CUANTIA);
		query.setParameter(6, UtilDominios.DOMINIO_TIPO_CUANTIA_ARBITRAJE);
		query.setParameter(7, UtilDominios.DOMINIO_TIPO_DE_SORTEO);					
    	return query.getResultList();
    }

	public String alertaResultadoSorteo(){
		String resultadoSorteos;
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" DECLARE ");
		nativeQuery.append("         @TablaEncabezado VARCHAR(MAX); ");
		nativeQuery.append("         SET @TablaEncabezado = CONCAT( ISNULL ( ");
		nativeQuery.append("         (SELECT descripcion ");
		nativeQuery.append("         FROM    DOMINIO ");
		nativeQuery.append("         WHERE   dominio = ?5 ");
		nativeQuery.append("         AND     codigo  = ?6 ");
		nativeQuery.append("         ) ");
		nativeQuery.append("         , '<html><body><table>'), ''); ");
		nativeQuery.append("         SELECT @TablaEncabezado + + REPLACE(REPLACE(CONVERT(NVARCHAR(MAX), ");
		nativeQuery.append("                (SELECT    concat ('<tr style=''background-color:#CEEFDF''><td colspan=4 align=center><p style=''color:##053F2D;''>', concat(ca.id_caso, ' - ', ca.nombre), '</p></td></tr>')                                                           , ");
		nativeQuery.append("                           '<tr><td><b>Fecha sorteo</b></td><td><b>Tipo de caso</b></td><td><b>Especialidad</b></td><td><b>Tipo de cuantía</b></td></tr>'                                        , ");
		nativeQuery.append("                                      concat('<tr><td>', CONVERT(VARCHAR(10), s.fecha_realizacion, 101), '</b></td><td>', se.nombre ,'</td><td>', ma.nombre,'</td><td>', dc.nombre, '</td></tr>'), ");
		nativeQuery.append("                                      concat('<tr><td colspan=3><b>Conformación previa de las listas por las partes o en virtud del pacto arbitral o de amigable composición</b></td><td>', ");
		nativeQuery.append("                           CASE ca.preseleccion ");
		nativeQuery.append("                                      WHEN 1 ");
		nativeQuery.append("                                      THEN 'Si' ");
		nativeQuery.append("                                      ELSE 'No' ");
		nativeQuery.append("                           END,'</td></tr>')                                                                                                 , ");
		nativeQuery.append("                           ('<tr><td colspan=3><b>Árbitro / Amigable componedor designado</b></td><td><b>Tipo de nombramiento</b></td></tr>'), ");
		nativeQuery.append("                           (SELECT REPLACE(REPLACE(STUFF( ");
		nativeQuery.append("                                   (SELECT    concat(' <tr><td colspan=3>', concat(p.primer_nombre_o_razon_social, ' ', p.segundo_nombre, ' ', p.primer_apellido, ' ', p.segundo_apellido), '</td><td>', d.nombre, '</td></tr>') ");
		nativeQuery.append("                                   FROM       rol_persona_caso rpc ");
		nativeQuery.append("                                              INNER JOIN dominio d ");
		nativeQuery.append("                                              ON         rpc.tipo_nombramiento = d.codigo ");
		nativeQuery.append("                                              AND        d.dominio             = ?2 ");
		nativeQuery.append("                                              INNER JOIN persona p ");
		nativeQuery.append("                                              ON         rpc.id_persona = p.id_persona ");
		nativeQuery.append("                                   WHERE      rpc.id_sorteo             = s.id_sorteo ");
		nativeQuery.append("                                   ORDER BY   tipo_nombramiento, ");
		nativeQuery.append("                                              orden_de_asignacion FOR XML PATH('') ");
		nativeQuery.append("                                   ) ");
		nativeQuery.append("                                   ,1,1,''),'&lt;','<'),'&gt;','>') ");
		nativeQuery.append("                           ) ");
		nativeQuery.append("                FROM       SORTEO s ");
		nativeQuery.append("                           INNER JOIN CASO ca ");
		nativeQuery.append("                           ON         ca.estado_registro = ?1 ");
		nativeQuery.append("                           AND        ca.id_caso         = s.id_caso ");
		nativeQuery.append("                           INNER JOIN SERVICIO se ");
		nativeQuery.append("                           ON         se.id_servicio     = ca.id_servicio ");
		nativeQuery.append("                           AND        se.estado_registro = ?1 ");
		nativeQuery.append("                           AND        se.tipo            = ?4 ");
		nativeQuery.append("                           INNER JOIN SEDE sed ");
		nativeQuery.append("                           ON         sed.id_sede         = ca.id_sede ");
		nativeQuery.append("                           AND        sed.estado_registro = ?1 ");
		nativeQuery.append("                           INNER JOIN MATERIA ma ");
		nativeQuery.append("                           ON         ca.id_materia = ma.id_materia ");
		nativeQuery.append("                           INNER JOIN DOMINIO dc ");
		nativeQuery.append("                           ON         ca.tipo_cuantia          = dc.codigo ");
		nativeQuery.append("                           AND        dc.dominio              in (?7, ?9) ");
		nativeQuery.append("                WHERE      s.estado_registro                   = ?1 ");
		nativeQuery.append("                AND        s.estado                            = ?3 ");
		nativeQuery.append("                AND        s.servicio_caso                    <> ?8 ");
		nativeQuery.append("                AND        CAST (s.fecha_realizacion AS DATE ) = CAST(GETDATE() AS DATE) FOR XML PATH(''), ");
		nativeQuery.append("                           ELEMENTS                                                                       , ");
		nativeQuery.append("                           TYPE ");
		nativeQuery.append("                ) ");
		nativeQuery.append("                ),'&lt;','<'),'&gt;','>') + '</table></body></html>'");
		
		Query query = em.createNativeQuery(nativeQuery.toString(), String.class);
		query.setParameter(1, UtilDominios.ESTADO_REGISTRO_ACTIVO);
		query.setParameter(2, UtilDominios.DOMINIO_TIPO_NOMBRAMIENTO);
		query.setParameter(3, UtilDominios.ESTADO_AUDIENCIA_REALIZADA);
		query.setParameter(4, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
		query.setParameter(5, UtilDominios.DOMINIO_TABLA_ALERTAS_ESTILOS);
		query.setParameter(6, UtilDominios.CODIGO_ESTILOS_ENCABEZADO_TABLA_ALERTA);
		query.setParameter(7, UtilDominios.DOMINIO_TIPO_CUANTIA);
		query.setParameter(8, UtilConstantes.ID_SERVICIO_ARBITRAJE_INTERNACIONAL);
		query.setParameter(9, UtilDominios.DOMINIO_TIPO_CUANTIA_ARBITRAJE);
		try {
			resultadoSorteos = (String) query.getSingleResult();
		} catch (NonUniqueResultException | NoResultException e) {
			resultadoSorteos = null;
		}		
		
		return resultadoSorteos;
	}
	
	public List<SorteoLiberacionListaDTO> consultarSorteosRealizadosHoyConLiberacionLista() {	
		
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT s.id_sorteo as idSorteo, ss.nombre as servicio, m.nombre as materia, ");
		nativeQuery.append("case pss.sorteo_con_lista when 1 then (select case s.tipo_cuantia when 'MEN' then 'B' else 'A' end) else 'No aplica' end as tipoLista, ");
		nativeQuery.append("s.id_Caso as idCaso, c.nombre as nombreCaso ");
		nativeQuery.append("FROM sorteo s ");
		nativeQuery.append("INNER JOIN caso c on s.id_caso = c.id_caso ");		
		nativeQuery.append("INNER JOIN servicio ss on s.servicio_caso = ss.id_servicio ");
		nativeQuery.append("INNER JOIN materia m on s.materia_caso = m.id_materia ");
		nativeQuery.append("INNER JOIN parametro_servicio_sorteo pss on s.servicio_caso = pss.id_servicio ");
		nativeQuery.append("WHERE s.libero_lista = 1 ");
		nativeQuery.append("AND CONVERT(DATE,s.fecha_realizacion) = CONVERT(DATE,GETDATE()) ");
		
		Query query = mp.getEntityManager().createNativeQuery(nativeQuery.toString(), SorteoLiberacionListaDTO.class);		 
						
    	return (List<SorteoLiberacionListaDTO>) query.getResultList();
    }
	
	//protected region metodos adicionales end
}

