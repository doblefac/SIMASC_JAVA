package com.ccb.simasc.negocio.fachadas.reparto.implementaciones;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.interfaces.IDominioFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorAgendaPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorAgendamiento;
import com.ccb.simasc.integracion.manejadores.ManejadorAgrupamientoRol;
import com.ccb.simasc.integracion.manejadores.ManejadorAudiencia;
import com.ccb.simasc.integracion.manejadores.ManejadorCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorConvenio;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorDisponibilidadPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorEvento;
import com.ccb.simasc.integracion.manejadores.ManejadorParametroDeServicio;
import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaServicioMateria;
import com.ccb.simasc.integracion.manejadores.ManejadorProgramacionAlerta;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorSala;
import com.ccb.simasc.integracion.manejadores.ManejadorSede;
import com.ccb.simasc.integracion.manejadores.ManejadorTipoDeServicioRol;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAudienciaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IConvenioFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IEventoRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IOrquestadorAlertasFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IRolPersonaCasoFacade;
import com.ccb.simasc.negocio.fachadas.reparto.interfaces.IRepartoSvc;
import com.ccb.simasc.transversal.dto.ConsultaAgendamientoDTO;
import com.ccb.simasc.transversal.dto.DatosEntradaRepartoDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.FormatoHoraAudienciaDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.dto.UsuarioDTO;
import com.ccb.simasc.transversal.dto.formularios.FiltrosSalasDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.AgendaPersona;
import com.ccb.simasc.transversal.entidades.Agendamiento;
import com.ccb.simasc.transversal.entidades.AgrupamientoRol;
import com.ccb.simasc.transversal.entidades.Audiencia;
import com.ccb.simasc.transversal.entidades.CartaPersona;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Convenio;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.CorreoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.Invitado;
import com.ccb.simasc.transversal.entidades.ParametroDeServicio;
import com.ccb.simasc.transversal.entidades.ParametroDeServicioPK;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.PersonaServicioMateria;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;
import com.ccb.simasc.transversal.entidades.Sala;
import com.ccb.simasc.transversal.entidades.Sede;
import com.ccb.simasc.transversal.entidades.Usuario;
import com.ccb.simasc.transversal.excepciones.EstadosCasoException;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.excepciones.SimascNegocioPruebaException;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilOperaciones;
import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementa los servicios de reparto (asignacion de funcionario o funcionarios a casos)
 * @author Asesoftware - Javier Estévez
 */
@Stateless
@LocalBean
public class RepartoSvc implements IRepartoSvc {

	private static final Logger logger = LogManager.getLogger(RepartoSvc.class.getName());
	

    @EJB
	private ManejadorRol manejadorRol;
    
    @EJB
    private ManejadorCaso manejadorCaso;
    
    @EJB
    private ManejadorPersona manejadorPersona;
    
    @EJB
    private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

    
    @EJB
    private ManejadorTipoDeServicioRol manejadorTipoDeServicioRol;
    
    @EJB
    private ManejadorPersonaServicioMateria manejadorPersonaServicioMateria;
    
    @EJB
    private ManejadorAgendaPersona manejadorAgendaPersona;

    @EJB
    private ManejadorDisponibilidadPersona manejadorDisponibilidadPersona;
    
    @EJB
    private ManejadorSala manejadorSala;
    
    @EJB
    private ManejadorAgendamiento manejadorAgendamiento;

    @EJB
    private ManejadorEvento manejadorEvento;
    
    @EJB
    private ManejadorAgrupamientoRol manejadorAgrupamientoRol;
    
    @EJB
    private ManejadorParametrosGenerales manejadorParametrosGenerales;
    
    @EJB
    private ManejadorParametroDeServicio manejadorParametroDeServicio;
    
    @EJB
    private ManejadorSede manejadorSede;

    @EJB
    private ManejadorRolPersona manejadorRolPersona;

    @EJB
    private IRolPersonaCasoFacade negocioRolPersonaCaso;

    @EJB
    private ManejadorAudiencia manejadorAudiencia;
    
    @EJB
    private ManejadorConvenio manejadorConvenio;
    
    @EJB
    private ICasoFacade casoFacade;

    @EJB
    private IEventoRolPersonaCasoFacade negocioEventoRolPersonaFacade;

    @EJB
    private ICorreoRolPersonaCasoFacade serviciosCorreo;
    
    @EJB
    private IDominioFacade dominioFacade;
    
    @EJB
    private IAudienciaFacade audienciaFacade;
    
    @EJB
    private IConvenioFacade convenioFacade;
    
	@EJB
	private IEventoFacade eventoFacade;
	
	@EJB
	private IOrquestadorAlertasFacade orquestadorAlertasFacade;
	
	@EJB
	private ManejadorProgramacionAlerta manejadorProgramacionAlerta;
	
	@EJB
	private ManejadorCorreoRolPersonaCaso manejadorCorreoRolPersonaCaso;
	
	@EJB
	private IDocumentoFacade documentoFacade;
	
	@EJB
    private ICorreoElectronicoFacade correoElectronicoFacade; 
	
	

    private static SecureRandom rnd = new SecureRandom();
	
    /**
     * Default constructor. 
     */
    public RepartoSvc() {
        
    }
    
    /**
     * Consulta en el sistema (base de datos) los prestadores de servicio, si no encuentra lanza una SIMASCNegocioExcepcion
     * que contiene el mensaje correspondiente indicando por qué no fueron encontrados.
     * @param datos
     * @param caso
     * @param idSede
     * @return
     */
    private List<Object[]> consultarValidarPrestadorParaAsignarEquidad(DatosEntradaRepartoDTO datos, Caso caso, Long idSede){
    	
    	
    	
    	List<Long> lstRoles = new ArrayList<Long>();
    	    	
		//se consulta el rol de acuerdo al servicio definido
    	List<AgrupamientoRol> lstAgrRoles = manejadorAgrupamientoRol.
    												consutarAgrupamiento(caso.getIdServicio(), 
    																	UtilDominios.TIPO_AGRUPAMIENTO_ROL_PRESTADOR_SERVICIO);

    	if(lstAgrRoles == null || lstAgrRoles.isEmpty()){
    		
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR467.toString());
    		
    	}
    	
    	for(AgrupamientoRol aRol: lstAgrRoles){
    		lstRoles.add(aRol.getAgrupamientoRolPK().getIdRol());
    	}
    	
    	//se consultan los prestadores de servicio
    	List<Object[]> lstIdPersonasAsignar = manejadorPersona.consultarPrestadorParaAsignarCasoEquidad(lstRoles, idSede, caso);
		
    	
    	//si no se obtuvieron prestadores de servicio
		if(lstIdPersonasAsignar == null || lstIdPersonasAsignar.isEmpty()){
			
			
			//se realizan las validaciones para lanzar el error
			manejadorPersona.validarRolParaAsignarCaso(lstRoles);
			manejadorPersona.validarRolSedeParaAsignarCaso(lstRoles, idSede);
			//manejadorPersona.validarRolSedeMateriaParaAsignarCaso(lstRoles, idSede, caso);
			//manejadorPersona.validarRolSedeMateriaListaParaAsignarCaso(lstRoles, idSede, caso);
			
			//en caso que por alguna razón no lance la excepción en las validaciones, se lanza una excepcion generica
			throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR454.toString());
		
		}
    	if (lstIdPersonasAsignar != null) {
    		System.out.println(lstIdPersonasAsignar.size());
    		
    	}
		
		return lstIdPersonasAsignar;
		
    }

    
    /**
     * Consulta en el sistema (base de datos) los prestadores de servicio, si no encuentra lanza una SIMASCNegocioExcepcion
     * que contiene el mensaje correspondiente indicando por qué no fueron encontrados.
     * @param datos
     * @param caso
     * @param idSede
     * @return
     */
    private List<Object[]> consultarValidarPrestadorParaAsignar(DatosEntradaRepartoDTO datos, Caso caso, Long idSede){
    	
    	//se consultan los parametros para determinar la lista
    	ParametrosGenerales valorSalarioMinimo = manejadorParametrosGenerales.buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_SALARIO_MINIMO);
    	ParametrosGenerales parValorPretenciones = manejadorParametrosGenerales.buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_VALOR_PRETENCIONES);
    	Long parValorPretencionesCalculado = valorSalarioMinimo.getValorNumerico()*parValorPretenciones.getValorNumerico();
    	Long valorPretencionesCaso = null;
    	List<Long> lstRoles = new ArrayList<Long>();
    	
    	try{
    		if( caso.getValorPretensiones() != null )
    			valorPretencionesCaso = Long.parseLong(caso.getValorPretensiones());
    		
    	}catch(NumberFormatException e){
			
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR458.toString());
    	}
    	
    	
		//se consulta el rol de acuerdo al servicio definido
    	List<AgrupamientoRol> lstAgrRoles = manejadorAgrupamientoRol.
    												consutarAgrupamiento(caso.getIdServicio(), 
    																	UtilDominios.TIPO_AGRUPAMIENTO_ROL_PRESTADOR_SERVICIO);

    	if(lstAgrRoles == null || lstAgrRoles.isEmpty()){
    		
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR467.toString());
    		
    	}
    	
    	for(AgrupamientoRol aRol: lstAgrRoles){
    		lstRoles.add(aRol.getAgrupamientoRolPK().getIdRol());
    	}
    	
    	//se consultan los prestadores de servicio
    	List<Object[]> lstIdPersonasAsignar = manejadorPersona.consultarPrestadorParaAsignarCaso(lstRoles, idSede, caso,
    																	parValorPretencionesCalculado, 
														    			valorPretencionesCaso);
		
		//si no se obtuvieron prestadores de servicio
		if(lstIdPersonasAsignar == null || lstIdPersonasAsignar.isEmpty()){
			
			//se realizan las validaciones para lanzar el error
			manejadorPersona.validarRolParaAsignarCaso(lstRoles);
			manejadorPersona.validarRolSedeParaAsignarCaso(lstRoles, idSede);
			manejadorPersona.validarRolSedeMateriaParaAsignarCaso(lstRoles, idSede, caso);
			manejadorPersona.validarRolSedeMateriaListaParaAsignarCaso(lstRoles, idSede, caso, 
																			parValorPretencionesCalculado, 
																			valorPretencionesCaso);
			
			//en caso que por alguna razón no lance la excepción en las validaciones, se lanza una excepcion generica
			throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR454.toString());
			
		}
    	
		
		return lstIdPersonasAsignar;
    }
    /**
     * Envía correo informando la asignación al prestador de servcio seleccionado
     * @param caso
     * @param rol
     * @param idPersona
     */
    private void enviarCorreoAsignacionCalidad(Caso caso, Rol rol, Long idPersona){
		
		   	
    	String asunto;
    	String cuerpo;
    	String plazoAceptacion = "2";
    	
    	ParametrosGenerales parGralPlazo;		
		parGralPlazo = manejadorParametrosGenerales.
					buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_PLAZO_ACEPTACION_CONCILIADOR_CCB_EQUIDAD);
		
		if(parGralPlazo != null){
			plazoAceptacion = parGralPlazo.getValorNumerico().toString();			
		}	
    	
    	Sede sede = manejadorSede.buscar(caso.getIdSede());
		asunto = String
				.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO609.toString()));
						

		cuerpo = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO610.toString()),
				new Object[] {String.valueOf(caso.getIdCaso()), caso.getNombre(), idPersona, plazoAceptacion});
		
    			 
    	List<Persona> lstPersonas = new ArrayList<Persona>();
    	lstPersonas.add(manejadorPersona.buscar(idPersona));
    	
    	List<String> lstCuerpo = new ArrayList<String>();
    	lstCuerpo.add(cuerpo);
    	
    	
    	serviciosCorreo.envioDeCorreo(asunto, lstPersonas, null, null, lstCuerpo, caso.getIdCaso(), null, null, Boolean.FALSE);
    	
    }
      
    /**
     * Envía correo informando la asignación al prestador de servcio seleccionado
     * @param caso
     * @param rol
     * @param idPersona
     */
    private void enviarCorreoAsignacion(Caso caso, Rol rol, Long idPersona){
    	
    	String asunto;
    	String cuerpo;
    	ParametrosGenerales urlAccesoSIMASC = manejadorParametrosGenerales.buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_URL_ACCESO_SIMASC);
    	
		if (rol.getNombre().equals(UtilDominios.ROL_PERSONA_ABOGADO)
				|| rol.getNombre().equals(UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION)
				|| rol.getNombre().equals(UtilDominios.ROL_PERSONA_AUXILIAR_ADM)) {
			asunto = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO451.toString()),
					new Object[] { String.valueOf(caso.getIdCaso()) });
			
			cuerpo = String
					.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO186.toString()),
							new Object[] {
									dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ROL_PERSONA, rol.getNombre()),
									String.valueOf(caso.getIdCaso()), caso.getNombre() });
		} else if(rol.getNombre().equals(UtilDominios.ROL_PERSONA_CONCILIADOR_EQUIDAD)){
			
			String plazoAceptacion = "2";
			Sede sede = manejadorSede.buscar(caso.getIdSede());
			ParametrosGenerales parGralPlazo;		
			parGralPlazo = manejadorParametrosGenerales.
						buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_PLAZO_ACEPTACION_CONCILIADOR_CCB_EQUIDAD);			
			if(parGralPlazo != null){
				plazoAceptacion = parGralPlazo.getValorNumerico().toString();			
			}		
			
			asunto = String
					.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO282.toString()));
							

			cuerpo = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO283.toString()),
					new Object[] { String.valueOf(caso.getIdCaso()), caso.getNombre(), sede.getNombre(), plazoAceptacion});
			
		}else {
			Sede sede = manejadorSede.buscar(caso.getIdSede());
			asunto = String
					.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO450.toString()),
							new Object[] {
									dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ROL_PERSONA, rol.getNombre()),
									sede.getCentro().getNombre() });

			cuerpo = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO185.toString()),
					new Object[] { dominioFacade.getNombreDominio(UtilDominios.DOMINIO_ROL_PERSONA, rol.getNombre()),
							caso.getNombre(), sede.getCentro().getNombre(),
							"<a href='" + urlAccesoSIMASC.getValorTexto() + "' target='_blank'>Aqui</a>" });
		}
    	
    	List<Persona> lstPersonas = new ArrayList<Persona>();
    	lstPersonas.add(manejadorPersona.buscar(idPersona));
    	
    	List<String> lstCuerpo = new ArrayList<String>();
    	lstCuerpo.add(cuerpo);
    	
    	serviciosCorreo.envioDeCorreo(asunto, lstPersonas, null, null, lstCuerpo, caso.getIdCaso(), null, null, Boolean.FALSE);

    }
    
    /**
     * Envía correo informando la devolucion al conciliador de equidad 
     * @param caso
     * @param idPersona
     */
    public void envioCorreDevolucionConciliadroEquidad(Long caso, Long idPersona) {
    	String asunto= "";
    	String cuerpo = "";
    	    	   
 		 
    					
		asunto = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO613.toString()));
							
		cuerpo = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO614.toString()),
				new Object[] {caso});
			
  				
		List<Persona> lstPersonas = new ArrayList<Persona>();
	    lstPersonas.add(manejadorPersona.buscar(idPersona));
	    
	    List<String> lstCuerpo = new ArrayList<String>();
	    lstCuerpo.add(cuerpo);
	    	
	    serviciosCorreo.envioDeCorreo(asunto, lstPersonas, null, null, lstCuerpo, caso, null, null, Boolean.FALSE);
    }
    
    /**
     * Envía correo informando a las partes sobre caso y envio de documento
     * @param List Documento
     * @param List PersonaBasicaDTO
     */
    
    @Override
    public void envioCorreoPartes(List<Documento> documentos, List<PersonaBasicaDTO> correo, Long idCaso) {
    	String asunto;
    	String cuerpo;
    	String radicado = "";
    	ParametrosGenerales urlAccesoSIMASC = manejadorParametrosGenerales.buscar(UtilDominios.CODIGO_PARAMETRO_GRAL_URL_ACCESO_SIMASC);
    	String nombrePartes = "";
    	Long idPersona = null;
    	String correroReceptor = "";
		Long rolReceptor = null;
		List<Persona> lstPersonas = new ArrayList<Persona>();
		List<DocumentoDTO> documentosList = new ArrayList<DocumentoDTO>();
		List<CorreoElectronico> correosRolPersonaCaso =  new ArrayList<CorreoElectronico>();
					
    	try {
    		for (PersonaBasicaDTO it1: correo) {
        		it1.getNombreCompleto();
        		nombrePartes = nombrePartes + " " + it1.getNombreCompleto();
        		idPersona = it1.getIdPersona();
            	lstPersonas.add(manejadorPersona.buscar(idPersona));
            	correroReceptor = it1.getCorreoElectronico();            	
           	}
         	for (Documento documento : documentos) {
           		DocumentoDTO documentoDto = new DocumentoDTO();
				documentoDto.setIdDocumento(documento.getIdDocumento());
				documentoDto.setNombre(documento.getNombre());
				documentoDto.setTipoDocumento(documento.getTipoDocumento());
				documentoDto.setDescripcion(documento.getDescripcion());
				documentoDto.setUrl(documento.getUrl());
				documentoDto.setFechaUltimaModificacion(documento.getFechaUltimaModificacion());
				documentoDto.setEstadoRegistro(documento.getEstadoRegistro());
				documentoDto.setIdCaso(documento.getIdCaso());
				documentoDto.setFechaRadicacion(documento.getFechaRadicacion());
				radicado = documento.getIdCaso().toString();
				documentosList.add(documentoDto);
			}
           	
           	asunto = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO611.toString()));
        	cuerpo = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO612.toString()),
        				new Object[] { String.valueOf(nombrePartes), radicado });
           	
           	List<String> lstCuerpo = new ArrayList<String>();
    	    lstCuerpo.add(cuerpo);
            serviciosCorreo.envioDeCorreo(asunto, lstPersonas, null, null, lstCuerpo, null, documentosList, null, Boolean.FALSE);
        	CorreoRolPersonaCaso crpc = new CorreoRolPersonaCaso();
			
        	List<Persona> remitente = manejadorRolPersonaCaso.consultarPersonasPorRolCaso(idCaso, UtilDominios.ROL_PERSONA_CONCILIADOR_EQUIDAD);
        	RolPersonaCaso rpc = manejadorRolPersonaCaso.estaPersonaAsignadaCaso(idPersona,
        			idCaso);
			if (rpc != null) {
				crpc.setIdRolReceptor(rpc.getRol().getIdRol());	
			}				
        	correosRolPersonaCaso = correoElectronicoFacade.consultaCorreosPersona(remitente.get(0).getIdPersona());
			crpc.setIdPersonaReceptor(idPersona);			
			crpc.setCorreoReceptor(correroReceptor);
			crpc.setIdCasoReceptor(idCaso);
			crpc.setAsunto(asunto);
			crpc.setCuerpoCorreo(cuerpo);
			crpc.setCorreoRemitente(correosRolPersonaCaso.get(0).getDireccion());
			crpc.setIdPersonaEnvio(remitente.get(0).getIdPersona());
			crpc.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			crpc.setFechaEnvio(new Date());
			crpc.setIdUsuarioModificacion(remitente.get(0).getIdPersona().toString());
			crpc.setFechaUltimaModificacion(new Date());
			validarEnvioCorreoRolPersonaCaso(crpc);
			StringBuilder cuerpoM = new StringBuilder();
			for (String cuerpoCorreo : lstCuerpo) {
				cuerpoM.append(cuerpoCorreo);
			}		
			crpc.setMensaje(cuerpoM.toString());
			manejadorCorreoRolPersonaCaso.crear(crpc);
		} catch (Exception e) {
			System.out.println(e);
		}
    }
    
      
    
    
    /**
     * Retorna la posición el prestador de servicios con menos casos seleccionado aleatoreamente, se asume que el listado
     * viene ordenado por cantidad de casos.
     * @param lstIdPersonasAsignar en la primera posición de cada vector recibido se encuentra un objeto Long con el identificador de la persona, en la segunda
     * 								posición se encuentra un objeto Integer con la cantidad de casos asignados. No se validará si esta dato es null o vacío.
     * @return
     */
    private int seleccionarAleatoreamentePrestadorConMenosCasos(List<Object[]> lstIdPersonasAsignar){
    	
    	int posicion = UtilConstantes.CERO;
    	
    	BigDecimal cantidadCasosMenor = (BigDecimal)lstIdPersonasAsignar.get(posicion)[UtilConstantes.UNO];
    	
    	cantidadCasosMenor = cantidadCasosMenor == null? new BigDecimal(UtilConstantes.CERO): cantidadCasosMenor;
    	
    	posicion++;
    	
    	while(posicion < lstIdPersonasAsignar.size()){
    		
    		BigDecimal cantidadCasosRegistro = (BigDecimal)lstIdPersonasAsignar.get(posicion)[UtilConstantes.UNO];
    		
    		cantidadCasosRegistro = cantidadCasosRegistro == null? new BigDecimal(UtilConstantes.CERO): cantidadCasosRegistro;
    		
    		if(cantidadCasosRegistro.intValue() != cantidadCasosMenor.intValue()){
    			break;
    		}
    		
    		posicion++;
    		
    	}
    	
    	return rnd.nextInt(posicion);
    }
    
    /**
     * Incrementa el contador de casos asignados en la tabla 
     */
    private void incrementarContadorCasosAsignados(Long idServicio, Long idMateria, Long idPersona){
    	
    	PersonaServicioMateria psm;
    	try {
    		psm = manejadorPersonaServicioMateria
    				.consultarPersonaServicioMateria(idServicio, idMateria, idPersona);    		
    	} catch (SimascNegocioPruebaException e) {
    		throw new SIMASCNegocioExcepcion(e.getMessage());
    	}
    	

		Long cantidadCasos = psm.getCantidadCasosAsignados();
		
		if(cantidadCasos == null){
			cantidadCasos = new Long(UtilConstantes.CERO);
		}
		
		cantidadCasos++;
		psm.setCantidadCasosAsignados(cantidadCasos);
		psm.setFechaUltimaModificacion(new Date());
		manejadorPersonaServicioMateria.actualizar(psm);
    	
    }
    
    /**
     * Asigna un funcionario a un caso creando el registro en la tabla ROL_PERSONA_CASO
     * @param idPersonaPrincipal
     * @param idCaso
     * @param idRol
     */
    private void crearAsignacion(Long idPersona,
    								Long idCaso, Long idRol, 
    								String usrModificacion, String metodoNombramiento,
    								String tipoNombramiento, String estado){
    	
    	RolPersonaCasoPK rpcPk = new RolPersonaCasoPK();
    	
    	rpcPk.setIdCaso(idCaso);
    	rpcPk.setIdPersona(idPersona);
    	rpcPk.setIdRol(idRol);
    	
    	RolPersonaCaso rpc = manejadorRolPersonaCaso.buscar( rpcPk );
    	if( rpc == null ){    		
    		rpc = new RolPersonaCaso();
    		rpc.setRolPersonaCasoPK(rpcPk);
    	}
    	rpc.setEsPresidente(Boolean.FALSE);
    	rpc.setIdUsuarioModificacion(usrModificacion);
    	rpc.setFechaUltimaModificacion(new Date());
    	rpc.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
    	rpc.setMetodoNombramiento(metodoNombramiento);
    	rpc.setTipoNombramiento(tipoNombramiento);
    	rpc.setEstado(estado);
    	
    	manejadorRolPersonaCaso.actualizar(rpc);
    	
    	//se registra el evento
    	negocioEventoRolPersonaFacade.crearEventoRolPersonaCaso(estado, null, new Date(),
                UtilDominios.ESTADO_REGISTRO_ACTIVO, idRol, idPersona, idCaso);
    	
    }
    /**
     * Asigna el prestador de servicio asegurandose que el caso sea asignado al que menos casos tiene.
     * @param procesarSuplente si este parametro es verdadero se crea el suplente realizando las validaciones necesarias 
     * @param lstIdPersonasAsignar en la primera posición de cada vector recibido se encuentra un objeto Long con el identificador de la persona, en la segunda
     * 								posición se encuentra un objeto Integer con la cantidad de casos asignados. No se validará si esta dato es null o vacío.
     */
    private Long asignarPrestadorMenosCasosEquidad( List<Object[]> lstIdPersonasAsignar, Caso caso, Rol rol, String usr){
    	
    	
    	
    	Long idPersonaPrincipal;
    	//boolean requiereSuplente = false;
    	List<Object[]> lstIdPersonas = new ArrayList<Object[]>(lstIdPersonasAsignar);
    	 
    	
    	int indicePrestadorMenosCasos = seleccionarAleatoreamentePrestadorConMenosCasos(lstIdPersonas);
    	
    	idPersonaPrincipal = ((BigDecimal) lstIdPersonas.get(indicePrestadorMenosCasos)[UtilConstantes.CERO]).longValue();
 
    	//se crea el principal y registra el evento
    	crearAsignacion(idPersonaPrincipal, caso.getIdCaso()
    							, rol.getIdRol(), usr
    							, UtilDominios.NOMBRAMIENTO_POR_LA_CCB
    							, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL
    							, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
    	
    	//se incrementa el contador de casos asignados para el servicio y la materia
    	incrementarContadorCasosAsignados(caso.getServicioMateria().getServicioMateriaPK().getIdServicio()
					, caso.getServicioMateria().getServicioMateriaPK().getIdMateria()
					, idPersonaPrincipal);
    	
    	

    	return idPersonaPrincipal;
    	
    }
    
    /**
     * Asigna el prestador de servicio asegurandose que el caso sea asignado al que menos casos tiene.
     * @param procesarSuplente si este parametro es verdadero se crea el suplente realizando las validaciones necesarias 
     * @param lstIdPersonasAsignar en la primera posición de cada vector recibido se encuentra un objeto Long con el identificador de la persona, en la segunda
     * 								posición se encuentra un objeto Integer con la cantidad de casos asignados. No se validará si esta dato es null o vacío.
     */
    private Long asignarPrestadorMenosCasos(boolean procesarSuplente, List<Object[]> lstIdPersonasAsignar, Caso caso, Rol rol, String usr){
    	
    	Convenio convenio = null;
    	Long idPersonaPrincipal;
    	boolean requiereSuplente = false;
    	List<Object[]> lstIdPersonas = new ArrayList<Object[]>(lstIdPersonasAsignar);
    	if(UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO.equals(caso.getIdServicio())) {
    		convenio = manejadorConvenio.buscar(caso.getIdConvenio());
    		requiereSuplente = convenio.getRequiereSuplente();
    	} else {
    		requiereSuplente = caso.getServicioMateria().getServicio().getRequiereSuplente();
    	}
    	
    	//si se requiere suplente
		if (procesarSuplente && requiereSuplente) {
			if (lstIdPersonasAsignar.size() < UtilConstantes.DOS)
				throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR465.toString());
		} else if (lstIdPersonasAsignar.size() < UtilConstantes.UNO) {
			throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR461.toString());
		}
    	
    	int indicePrestadorMenosCasos = seleccionarAleatoreamentePrestadorConMenosCasos(lstIdPersonas);
    	
    	idPersonaPrincipal = ((BigDecimal) lstIdPersonas.get(indicePrestadorMenosCasos)[UtilConstantes.CERO]).longValue();
 
    	//se crea el principal y registra el evento
    	crearAsignacion(idPersonaPrincipal, caso.getIdCaso()
    							, rol.getIdRol(), usr
    							, UtilDominios.NOMBRAMIENTO_POR_LA_CCB
    							, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL
    							, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
    	
    	//se incrementa el contador de casos asignados para el servicio y la materia
    	incrementarContadorCasosAsignados(caso.getServicioMateria().getServicioMateriaPK().getIdServicio()
					, caso.getServicioMateria().getServicioMateriaPK().getIdMateria()
					, idPersonaPrincipal);
    	
    	if(procesarSuplente && requiereSuplente){
    		
    		//se elimina el principal ya designado del listado
    		lstIdPersonas.remove(indicePrestadorMenosCasos);
    		
    		//se busca el funcionario con menos casos para asignar el suplente.
    		int indiceSuplenteMenosCasos = seleccionarAleatoreamentePrestadorConMenosCasos(lstIdPersonas);
    		
    		Long idSuplente = ((BigDecimal) lstIdPersonas.get(indiceSuplenteMenosCasos)[UtilConstantes.CERO]).longValue();
    		
    		//se crea el suplente
        	crearAsignacion(idSuplente, caso.getIdCaso(), rol.getIdRol()
        							, usr, UtilDominios.NOMBRAMIENTO_POR_LA_CCB
        							, UtilDominios.TIPO_NOMBRAMIENTO_SUPLENTE
        							, UtilDominios.ESTADO_ROL_PERSONA_CASO_DESIGNADO);
    		        	
    	}

    	return idPersonaPrincipal;
    	
    }
    
    /**
     * Actualiza el estado del caso a conciliador designado
     */
    private void actualizarEstadoCasoDesignado(Caso caso, Long idPersonaDesignada){
    	Persona persona = manejadorPersona.consultaBasicaPersona(idPersonaDesignada);
    	
    	String strObs = MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO274.toString());
    	strObs = String.format(strObs, new String[]{persona.getNombreCompleto()});
    	
    	try {
			casoFacade.cambiarEstadoCaso(caso.getIdCaso(), 
												UtilDominios.ESTADO_CASO_CONCILIADOR_DESIGNADO, 
												new Date(), 
												UtilDominios.TIPO_EVENTO_REPARTO, 
												strObs);
		} catch (EstadosCasoException e) {
			
			logger.error("Error al cambiar el estado: " + e.getMessage(), e);
			
			throw new SIMASCNegocioExcepcion(e.getMessage());
		}

    }
    /**
     * Ejecuta el proceso de reparto en Equidad cuando el servicio no tiene convenio, y no se ha seleccionado
     * ningún dato previamente (conciliador, fecha audiencia, hora audiencia)
     * @param datos
     * @param caso
     * @param idSede
     */
    private void repartirPrestadorServicioNoConvenioNoDatosEquidad(DatosEntradaRepartoDTO datos, Caso caso, Rol rol) {
    	
    	//si la sede no viene en los datos de entrada se toma la sede del caso
    	Long idSede = datos.getIdSede()!=null?datos.getIdSede():caso.getSede().getIdSede();
    	
    	List<Object[]> lstIdPersonasAsignar = consultarValidarPrestadorParaAsignarEquidad(datos, caso, idSede); 
    	
    	Long idPersonaAsignada = asignarPrestadorMenosCasosEquidad(lstIdPersonasAsignar, caso, rol, datos.getUsuario());
    	
    	
    	//Se actualiza el estado del caso 'conciliador designado'
    	actualizarEstadoCasoDesignado(caso, idPersonaAsignada);
    	    	
    	//Seguarda la programacion de las notificaciones
    	manejadorProgramacionAlerta.crearProgramacionesAceptacionConciliacionEquidad(caso.getIdCaso(), idPersonaAsignada, UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
    	
    	//se envía correo
    	enviarCorreoAsignacion(caso, rol, idPersonaAsignada); 
    	
    	
    	    	
    }
    /**
     * Ejecuta el proceso de reparto cuando el servicio no tiene convenio, y no se ha seleccionado
     * ningún dato previamente (conciliador, fecha audiencia, hora audiencia)
     * @param datos
     * @param caso
     * @param idSede
     */
    private void repartirPrestadorServicioNoConvenioNoDatos(DatosEntradaRepartoDTO datos, Caso caso, Rol rol) {
    	
    	//si la sede no viene en los datos de entrada se toma la sede del caso
    	Long idSede = datos.getIdSede()!=null?datos.getIdSede():caso.getSede().getIdSede();
    	
    	List<Object[]> lstIdPersonasAsignar = consultarValidarPrestadorParaAsignar(datos, caso, idSede);
    	
    	Long idPersonaAsignada = asignarPrestadorMenosCasos(Boolean.TRUE.booleanValue(), lstIdPersonasAsignar, caso, rol, datos.getUsuario());
    	
    	//Se actualiza el estado del caso 'conciliador designado'
    	actualizarEstadoCasoDesignado(caso, idPersonaAsignada);
    	
    	//Seguarda la programacion de las notificaciones
    	manejadorProgramacionAlerta.crearProgramacionesAceptacionConciliacion(caso.getIdCaso(), idPersonaAsignada, UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
    	
    	//se envía correo
    	enviarCorreoAsignacion(caso, rol, idPersonaAsignada);    	
    	
    	
    	//Notificacion de alerta
    	orquestadorAlertasFacade.alertaNOT_NPC(caso.getIdCaso(),idPersonaAsignada, UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
    	
    	
    }
    
    /**
     * Ejecuta el proceso de reparto cuando el servicio no tiene convenio, y se ha seleccionado solamente el prestador
     * @param datos
     * @param caso
     * @param idSede
     */
    private void repartirPrestadorServicioNoConvenioPrestador(DatosEntradaRepartoDTO datos, Caso caso, Rol rol) {
    	
    	crearAsignacion(datos.getIdConciliador(), caso.getIdCaso(), rol.getIdRol(), datos.getUsuario()
    							, UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES
    							, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL
    							, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
    	
    	incrementarContadorCasosAsignados(caso.getIdServicio(), caso.getIdMateria(), datos.getIdConciliador());
    	
    	//Se actualiza el estado del caso 'conciliador designado'
    	actualizarEstadoCasoDesignado(caso, datos.getIdConciliador());
    	
    	//Seguarda la programacion de las notificaciones
    	manejadorProgramacionAlerta.crearProgramacionesAceptacionConciliacion(caso.getIdCaso(),  datos.getIdConciliador(), UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES);
    	
    	
    	//se envía correo
    	enviarCorreoAsignacion(caso, rol, datos.getIdConciliador());
    	
    	//Notificacion de alerta
    	orquestadorAlertasFacade.alertaNOT_NPC(caso.getIdCaso(),datos.getIdConciliador(), UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES);
    	
    	
    }
    
    private int obtenerDuracionAudienciaEnMinutos(String duracion) {
    	int duracionTurno;
    	switch(duracion){
		case UtilDominios.DURACION_AUDIENCIA_DOS_HORAS:
			duracionTurno = UtilConstantes.MINUTOS_DOS_HORAS;
			break;
		case UtilDominios.DURACION_AUDIENCIA_UNA_HORA:
			duracionTurno = UtilConstantes.MINUTOS_UNA_HORA;
			break;
		case UtilDominios.DURACION_AUDIENCIA_MEDIA_HORA:
			duracionTurno = UtilConstantes.MINUTOS_MEDIA_HORA;
			break;
		default: 
			throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR469.toString());
    	}
    	
    	return duracionTurno;
    }
    
    /**
     * Retorna los turnos que se pueden obtener arrancando la hora de inicio entre la hora inicial y final del turno recibido por parametro
     * @param turno 
     * @param fechaHoraFinalizaAgenda hora de finalizacion de la agenda (ningun turno puede terminar después de esta hora)
     * @param minutosAdicionar duración del turno
     * @return
     */
    private List<FormatoHoraAudienciaDTO> obetenerTurnosEntreTurno(FormatoHoraAudienciaDTO turno, Date fechaHoraFinalizaAgenda, int minutosAdicionar){
    	
    	List<FormatoHoraAudienciaDTO> lstTurnosEntre = new ArrayList<FormatoHoraAudienciaDTO>();
    	
    	Date fechaHoraInicial = turno.getFechaInicio();
    	Date fechaHoraFinalTurno = turno.getFechaFin();
    	
    	fechaHoraInicial = UtilOperaciones.adicionarMinutos(fechaHoraInicial, UtilConstantes.MINUTOS_ENTRE_TURNOS);
    	
    	while(!fechaHoraInicial.equals(fechaHoraFinalTurno)
    			&& fechaHoraInicial.before(fechaHoraFinalTurno)){
    		
    		Date fechaHoraFinal = UtilOperaciones.adicionarMinutos(fechaHoraInicial, minutosAdicionar);
    		
    		if(fechaHoraFinal.after(fechaHoraFinalizaAgenda)){
    			break;
    		}
    		
    		FormatoHoraAudienciaDTO turnoEntre = new FormatoHoraAudienciaDTO();
    		turnoEntre.setFechaInicio(fechaHoraInicial);
    		turnoEntre.setFechaFin(fechaHoraFinal);
    		lstTurnosEntre.add(turnoEntre);
    		
    		fechaHoraInicial = UtilOperaciones.adicionarMinutos(fechaHoraInicial, UtilConstantes.MINUTOS_ENTRE_TURNOS);
    	}
    	
    	return lstTurnosEntre;
    	
    }
    
    /**
     * Retorna los turnos (disponibles y no disponibles) para la fecha que se recibe por parametro
     * @param fecha
     * @return
     */
    private List<FormatoHoraAudienciaDTO> consultarTurnosParaFecha(Long idServicio, Date fecha){
    	
    	List<FormatoHoraAudienciaDTO> lstTurnos = new ArrayList<FormatoHoraAudienciaDTO>();
    	
    	ParametroDeServicioPK psPk = new ParametroDeServicioPK();
    	psPk.setIdServicio(idServicio);
    	psPk.setTipoParametro(UtilDominios.TIPO_PARAMETRO_PROGRAMACION_AUDIENCIA);
    	psPk.setNombre(UtilDominios.PARAMETRO_DE_SERVICIO_HORA_INICIO);
    	
    	ParametroDeServicio psHoraInicioAgenda = manejadorParametroDeServicio.buscar(psPk);
    	
    	psPk.setNombre(UtilDominios.PARAMETRO_DE_SERVICIO_HORA_FIN);
    	
    	ParametroDeServicio psHoraFinAgenda = manejadorParametroDeServicio.buscar(psPk);
    	
    	psPk.setNombre(UtilDominios.PARAMETRO_DE_SERVICIO_DURACION_DE_AUDIENCIA);
    	
    	ParametroDeServicio psDuracionAudiencia = manejadorParametroDeServicio.buscar(psPk);
    	
    	if(psDuracionAudiencia == null 
    			|| psHoraInicioAgenda == null
    			|| psHoraFinAgenda == null){
    		
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR472.toString());
    	}
    	
    	int duracionTurno = obtenerDuracionAudienciaEnMinutos(psDuracionAudiencia.getValor());
    	
    	int horaInicio;
    	int minutoInicio;
    	
    	String[] vctHora = psHoraInicioAgenda.getValor().split(UtilConstantes.DOS_PUNTOS);
        try{
        	
        	horaInicio = Integer.parseInt(vctHora[UtilConstantes.CERO]);
        	minutoInicio = Integer.parseInt(vctHora[UtilConstantes.UNO]);
        	
        }catch(Exception e){
        	
        	throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR470.toString());
        	
        }
        
        int horaFin;
    	int minutoFin;
    	
    	vctHora = psHoraFinAgenda.getValor().split(UtilConstantes.DOS_PUNTOS);
        try{
        	
        	horaFin = Integer.parseInt(vctHora[UtilConstantes.CERO]);
        	minutoFin = Integer.parseInt(vctHora[UtilConstantes.UNO]);
        	
        }catch(Exception e){
        	
        	throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR470.toString());
        	
        }
    	
    	Calendar cal = Calendar.getInstance();
    	
    	cal.setTime(UtilOperaciones.removerHora(fecha));
    	cal.add(Calendar.HOUR, horaInicio);
    	cal.add(Calendar.MINUTE, minutoInicio);
    	Date fechaHoraInicioAgenda = cal.getTime();
    	
    	cal.setTime(UtilOperaciones.removerHora(fecha));
    	cal.add(Calendar.HOUR, horaFin);
    	cal.add(Calendar.MINUTE, minutoFin);
    	Date fechaHoraFinalizaAgenda = cal.getTime();
    	
    	Date fechaHoraInicial = fechaHoraInicioAgenda;
    	
    	while(!fechaHoraInicial.equals(fechaHoraFinalizaAgenda)){
    		
    		cal.setTime(fechaHoraInicial);
    		cal.add(Calendar.MINUTE, duracionTurno);
    		
    		Date fechaHoraFinal = cal.getTime();
    		
    		if(fechaHoraFinal.after(fechaHoraFinalizaAgenda)){
    			break;
    		}
    		
    		FormatoHoraAudienciaDTO turno = new FormatoHoraAudienciaDTO();
    		turno.setFechaInicio(fechaHoraInicial);
    		turno.setFechaFin(fechaHoraFinal);
    		lstTurnos.add(turno);
    		lstTurnos.addAll(obetenerTurnosEntreTurno(turno, fechaHoraFinalizaAgenda, duracionTurno));
    		
    		fechaHoraInicial = fechaHoraFinal;
    	}
    	
    	
    	return lstTurnos;

    }
    
    /**
     * Elimina del listado lstTurnosTodos aquellos que se encuentren en lstTurnosOcupados y retorna el resultado
     * @param lstTurnosTodos
     * @param lstTurnosOcupados
     * @return
     */
    private List<FormatoHoraAudienciaDTO> obtenerTurnosDisponibles(List<FormatoHoraAudienciaDTO> lstTurnosTodos, 
    																List<FormatoHoraAudienciaDTO> lstTurnosOcupados){
    	
    	if(lstTurnosOcupados == null || lstTurnosOcupados.isEmpty()){
    		return lstTurnosTodos;
    	}
    	
    	for(FormatoHoraAudienciaDTO turnoOcupado : lstTurnosOcupados){
    		
    		for(int i=0; i<lstTurnosTodos.size(); i++){
    			
    			FormatoHoraAudienciaDTO turnoDisponible = lstTurnosTodos.get(i);
    			
    			if(turnoDisponible.getFechaInicio().after(turnoOcupado.getFechaInicio())){
    				break;
    			}
    			
    			//si la hora inicial del turno ocupado está entre un rango de turnos desocupados
    			if( (turnoOcupado.getFechaInicio().equals(turnoDisponible.getFechaInicio())
    					|| turnoOcupado.getFechaInicio().after(turnoDisponible.getFechaInicio())
    				) && turnoOcupado.getFechaInicio().before(turnoDisponible.getFechaFin())){
    				
    				lstTurnosTodos.remove(i);
    				i--;
    				
    			}
    			
    			
    			
    		}
    	}
    	
    	for(FormatoHoraAudienciaDTO turnoOcupado : lstTurnosOcupados){
    		
    		for(int i=0; i<lstTurnosTodos.size(); i++){
    			
    			FormatoHoraAudienciaDTO turnoDisponible = lstTurnosTodos.get(i);
    			
    			Calendar cal = Calendar.getInstance();
    			cal.setTime(turnoOcupado.getFechaFin());
    			cal.add(Calendar.MINUTE, -1);
    			Date fechaOcupadaFinal = cal.getTime();
    			
    			if(turnoDisponible.getFechaInicio().after(fechaOcupadaFinal)){
    				break;
    			}
    			    			
    			//si la hora inicial del turno ocupado está entre un rango de turnos desocupados
    			if( (fechaOcupadaFinal.equals(turnoDisponible.getFechaInicio())
    					|| fechaOcupadaFinal.after(turnoDisponible.getFechaInicio())
    				) && fechaOcupadaFinal.before(turnoDisponible.getFechaFin())){
    				
    				lstTurnosTodos.remove(i);
    				i--;
    				
    			}
    			
    		}
    	}
    	
    	return lstTurnosTodos;
    }
    
    /**
     * Retorna los turnos disponibles para una persona en AgendaPersona en el día (fecha) que se recibe por parámetro
     * @return
     */
    private List<FormatoHoraAudienciaDTO> consultarTurnosDisponiblesPersona(Long idPersona, Long idServicio, Date fecha){
    
    	List<FormatoHoraAudienciaDTO> lstTurnosTodos = consultarTurnosParaFecha(idServicio, fecha);
    	
    	List<AgendaPersona> lstTurnosOcupados = manejadorAgendaPersona.consultarTurnos(idPersona, fecha);
    	
    	List<FormatoHoraAudienciaDTO> lstTurnosOcup = new ArrayList<FormatoHoraAudienciaDTO>();
    	
    	for(AgendaPersona ap: lstTurnosOcupados){
    		FormatoHoraAudienciaDTO turnoOc = new FormatoHoraAudienciaDTO();
    		turnoOc.setFechaInicio(ap.getFechaInicio());
    		turnoOc.setFechaFin(ap.getFechaFin());
    		lstTurnosOcup.add(turnoOc);
    	}
    	
    	return obtenerTurnosDisponibles(lstTurnosTodos, lstTurnosOcup);
    	
    }
    
    /**
     * Retorna los turnos disponibles para una persona en AgendaPersona en el día (fecha) que se recibe por parámetro
     * @return
     */
    private List<FormatoHoraAudienciaDTO> consultarTurnosDisponiblesSala(Sala sala, Long idServicio, Date fecha){
    	List<FormatoHoraAudienciaDTO> lstTurnosTodos = consultarTurnosParaFecha(idServicio, fecha);
    	FiltrosSalasDTO filtros = new FiltrosSalasDTO();
    	filtros.setIdSede(sala.getIdSede());
    	filtros.setIdSala(sala.getIdSala());
    	filtros.setFechaSolicitud(UtilOperaciones.removerHora(fecha));
    	List<Agendamiento> lstTurnosOcupados = manejadorAgendamiento.buscarAgendamientoSalaSede(filtros);
    	List<FormatoHoraAudienciaDTO> lstTurnosOcup = new ArrayList<FormatoHoraAudienciaDTO>();
    	
    	for(Agendamiento ag: lstTurnosOcupados){
    		FormatoHoraAudienciaDTO turnoOc = new FormatoHoraAudienciaDTO();
    		turnoOc.setFechaInicio(ag.getHoraInicio());
    		turnoOc.setFechaFin(ag.getHoraFin());
    		lstTurnosOcup.add(turnoOc);
    	}
    	
    	return obtenerTurnosDisponibles(lstTurnosTodos, lstTurnosOcup);
    	
    }

    /**
     * Retorna los turnos disponibles para una persona en AgendaPersona en el día (fecha) que se recibe por parámetro
     * @return
     */
    private List<FormatoHoraAudienciaDTO> consultarTurnosDisponiblesSala(Sala sala, Long idServicio, Date fecha, Date hora){
    	List<FormatoHoraAudienciaDTO> lstTurnosDisponibles = consultarTurnosDisponiblesSala(sala, idServicio, fecha);
    	
    	//se buscan solo aquellos turnos que tengan la hora asignada
    	for(int i=0; i<lstTurnosDisponibles.size(); i++){
    		FormatoHoraAudienciaDTO turno = lstTurnosDisponibles.get(i);
    		Date date = null;
    		if( hora !=null ){
    			Calendar cal = Calendar.getInstance();
    			cal.setTime(hora);
    			// Set time fields to zero
    			cal.set(Calendar.SECOND,0);
    			cal.set(Calendar.MILLISECOND,0);
    			// Put it back in the Date object
    			date = cal.getTime(); 
    		}
    		
    		if(!turno.getFechaInicio().equals(date)){
    			lstTurnosDisponibles.remove(i);
    			i--;
    		}
    	}
    	return lstTurnosDisponibles;
    	
    }

    /**
     * Ejecuta el proceso de reparto cuando el servicio no tiene convenio, y se han seleccionado
     * los datos previamente (conciliador, fecha audiencia, hora audiencia)
     * @param datos
     * @param caso
     * @param idSede
     */
    private void repartirPrestadorServicioNoConvenioPrestadorFecha(DatosEntradaRepartoDTO datos, Caso caso, Rol rol) {
    	
    	//se consultan los turnos  y la disponibilidad
    	List<FormatoHoraAudienciaDTO> lstTurnosDisp = consultarTurnosDisponiblesPersona(datos.getIdConciliador(), caso.getIdServicio(), datos.getFechaAudiencia());
    	Long idSede = datos.getIdSede() != null?datos.getIdSede(): caso.getIdSede();
    	if(lstTurnosDisp == null || lstTurnosDisp.isEmpty()){
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR461.toString());
    	}
    	
    	for(int i=0; i<lstTurnosDisp.size(); i++){
    		Date fecha;
    		FormatoHoraAudienciaDTO turno = lstTurnosDisp.get(i);
    		fecha = UtilOperaciones.removerHora(turno.getFechaFin());
			boolean disponible = manejadorDisponibilidadPersona.validarDisponibilidadPersona(fecha,
					turno.getFechaInicio(), turno.getFechaFin(), idSede, datos.getIdConciliador());
    		if(!disponible){
    			lstTurnosDisp.remove(i);
    			i--;
    		}
    	}
    	
    	if(lstTurnosDisp.isEmpty())
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR461.toString());
    	
    	boolean salaDisponible = Boolean.FALSE;
    	//se verifica la sala en el turno
    	for(int i=0; i<lstTurnosDisp.size(); i++){
    		FormatoHoraAudienciaDTO turno = lstTurnosDisp.get(i);
    		salaDisponible = manejadorSala.existenSalasDisponibles(idSede, turno.getFechaInicio(), turno.getFechaFin());
    		if(salaDisponible){
    			//se registra el prestador de servicio
    			crearAsignacion(datos.getIdConciliador(), caso.getIdCaso(), 
    									rol.getIdRol(), datos.getUsuario(), 
    									UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES, 
    									UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL, 
    									UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);	
    			incrementarContadorCasosAsignados(caso.getIdServicio(), caso.getIdMateria(), datos.getIdConciliador());
    	    	//Se actualiza el estado del caso 'conciliador designado'
    	    	actualizarEstadoCasoDesignado(caso, datos.getIdConciliador());
    	    	
    	    	// se programa la audiencia
    	    	programarAudiencia(turno.getFechaInicio(), turno.getFechaFin(), datos.getIdConciliador(), idSede, caso.getIdCaso(), null);
    	    	
    	    	//Seguarda la programacion de las notificaciones
    	    	manejadorProgramacionAlerta.crearProgramacionesAceptacionConciliacion(caso.getIdCaso(),  datos.getIdConciliador(), UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES);
    	    	    	    	
    	    	//se envía correo
    	    	enviarCorreoAsignacion(caso, rol, datos.getIdConciliador());
    	    	
    	    	//Notificacion de alerta
    	    	orquestadorAlertasFacade.alertaNOT_NPC(caso.getIdCaso(),datos.getIdConciliador(), UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES);
    	    	
    	    	

    	    	break;//abandona el ciclo
    		}
    	}
    	
    	if(!salaDisponible){
    		
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR462.toString());
    		
    	}
    	
    	
    }
    
    /**
     * Ejecuta el proceso de reparto cuando el servicio no tiene convenio, y se han seleccionado
     * los datos previamente (conciliador, fecha audiencia, hora audiencia)
     * @param datos
     * @param caso
     * @param idSede
     */
    private void repartirPrestadorServicioNoConvenioPrestadorFechaHora(DatosEntradaRepartoDTO datos, Caso caso, Rol rol) {
    	Long idSede = datos.getIdSede() != null?datos.getIdSede(): caso.getIdSede();
		Date fecha;
		
		ParametroDeServicioPK psPk = new ParametroDeServicioPK();
    	psPk.setIdServicio(caso.getIdServicio());
    	psPk.setTipoParametro(UtilDominios.TIPO_PARAMETRO_PROGRAMACION_AUDIENCIA);
    	psPk.setNombre(UtilDominios.PARAMETRO_DE_SERVICIO_DURACION_DE_AUDIENCIA);
    	ParametroDeServicio psDuracionAudiencia = manejadorParametroDeServicio.buscar(psPk);    	
    	if(psDuracionAudiencia == null)    		
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR472.toString());
    	
    	int minutosAdicionar = obtenerDuracionAudienciaEnMinutos(psDuracionAudiencia.getValor());    		
		fecha = UtilOperaciones.removerHora(datos.getFechaAudiencia());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(datos.getHoraAudiencia());
		cal.add(Calendar.MINUTE, minutosAdicionar);
		Date fechaHoraFin = cal.getTime();
		
		boolean disponible = manejadorDisponibilidadPersona.validarDisponibilidadPersona(fecha,
				datos.getHoraAudiencia(), fechaHoraFin, idSede, datos.getIdConciliador())
				&& manejadorAgendaPersona.validarDisponibilidadPersona(datos.getIdConciliador(),
						datos.getHoraAudiencia(), fechaHoraFin);
		if(!disponible)
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR463.toString());
    	
		boolean salaDisponible = manejadorSala.existenSalasDisponibles(idSede, datos.getHoraAudiencia(),
				fechaHoraFin);
    		
   		if(salaDisponible){
			// se registra el prestador de servicio
			crearAsignacion(datos.getIdConciliador(), caso.getIdCaso(), rol.getIdRol(), datos.getUsuario(),
					UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL,
					UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);	
			
			incrementarContadorCasosAsignados(caso.getIdServicio(), caso.getIdMateria(), datos.getIdConciliador());
			
	   		//Se actualiza el estado del caso 'conciliador designado'
	    	actualizarEstadoCasoDesignado(caso, datos.getIdConciliador());

	    	// se programa la audiencia
	    	programarAudiencia(datos.getHoraAudiencia(), fechaHoraFin, datos.getIdConciliador(), idSede, caso.getIdCaso(), null);
	    	
	    	//se envía correo
	    	enviarCorreoAsignacion(caso, rol, datos.getIdConciliador());
	    	
	    	//Seguarda la programacion de las notificaciones
	    	manejadorProgramacionAlerta.crearProgramacionesAceptacionConciliacion(caso.getIdCaso(),  datos.getIdConciliador(), UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES);
	    	
	    	
	    	//Notificacion de alerta
	    	orquestadorAlertasFacade.alertaNOT_NPC(caso.getIdCaso() , datos.getIdConciliador(), UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES);
			
		} else {    	
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR464.toString());
    	}
    }
    
    /**
     * Ejecuta el proceso de reparto cuando el servicio no tiene convenio, y se ha seleccionado
     * solo la fecha y o la hora de la audiencia. 
     * @param datos
     * @param caso
     * @param idSede
     */
    private void repartirPrestadorServicioNoConvenioFecha_Y_U_Hora(DatosEntradaRepartoDTO datos, Caso caso, Rol rol) {
    	Long idSede = datos.getIdSede() != null?datos.getIdSede(): caso.getIdSede();    	
    	Sede sede = manejadorSede.buscar(idSede);
    	//se obtienen los prestadores de servicio disponibles
    	List<Object[]>  lstIdPrestadores = consultarValidarPrestadorParaAsignar(datos, caso, idSede);
    	if(sede.getSalaList() == null || sede.getSalaList().isEmpty())
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR462.toString());
    	
    	boolean salaConTurnos = Boolean.FALSE;
    	boolean asignadoPrestador = Boolean.FALSE;
    	
    	//Por cada sala de la sede se evalua si  tiene turnos disponibles
    	for(Sala sala: sede.getSalaList()){
	    	List<FormatoHoraAudienciaDTO> lstTurnosSalas;
	    	if(datos.getHoraAudiencia() != null)
	    		lstTurnosSalas = consultarTurnosDisponiblesSala(sala, caso.getIdServicio(), datos.getFechaAudiencia(), datos.getHoraAudiencia());
	    	else
	    		lstTurnosSalas = consultarTurnosDisponiblesSala(sala, caso.getIdServicio(), datos.getFechaAudiencia());
	    	
	    	if(lstTurnosSalas == null || lstTurnosSalas.isEmpty())
	    		continue;
	    	
	    	salaConTurnos = Boolean.TRUE;
	    	
	    	for(int i=0; i<lstTurnosSalas.size(); i++){
	    		FormatoHoraAudienciaDTO turnoSala = lstTurnosSalas.get(i);
	    		List<Object[]>  lstIdPrestadoresDisponibles = new ArrayList<Object[]>();
	    		for (Object[] regPrestador: lstIdPrestadores){
	    			Long idPersona = ((BigDecimal) regPrestador[UtilConstantes.CERO]).longValue();
					boolean disponible = manejadorDisponibilidadPersona.validarDisponibilidadPersona(
							UtilOperaciones.removerHora(datos.getFechaAudiencia()), turnoSala.getFechaInicio(),
							turnoSala.getFechaFin(), idSede, idPersona)
							&& manejadorAgendaPersona.validarDisponibilidadPersona(idPersona,
									turnoSala.getFechaInicio(), turnoSala.getFechaFin());
	    			if(disponible)
	    				lstIdPrestadoresDisponibles.add(regPrestador);
	    		}
	    		
	    		try{
	    			Long idPersonaAsignada = null;
	    			if (!lstIdPrestadoresDisponibles.isEmpty()) {
	    				idPersonaAsignada = asignarPrestadorMenosCasos(Boolean.TRUE, lstIdPrestadoresDisponibles, caso, rol, datos.getUsuario());
	    				//Se actualiza el estado del caso 'conciliador designado'
	    				actualizarEstadoCasoDesignado(caso, idPersonaAsignada);
	    				
	    				// se programa la audiencia del caso
	    				programarAudiencia(turnoSala.getFechaInicio(), turnoSala.getFechaFin(), idPersonaAsignada, idSede, caso.getIdCaso(), null);
	    				
	    				//se envía correo
	    				enviarCorreoAsignacion(caso, rol, idPersonaAsignada);
	    				
	    		    	//Seguarda la programacion de las notificaciones
	    		    	manejadorProgramacionAlerta.crearProgramacionesAceptacionConciliacion(caso.getIdCaso(),  idPersonaAsignada, UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
	    		  
	    		    	//Notificacion de alerta
	    		    	orquestadorAlertasFacade.alertaNOT_NPC(caso.getIdCaso(),idPersonaAsignada, UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
	    				asignadoPrestador = Boolean.TRUE;
	    				break;
	    			}
	    		}catch(SIMASCNegocioExcepcion ex){
	    			if(!ex.getMessage().equals(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR459.toString())))
	    				throw ex;
	    			//de lo contrario se sigue con el siguiente turno.
	    		}
	    		
	    	}
	    	
	    	if(asignadoPrestador)
	    		break;
    	
    	}
    	
    	if(!salaConTurnos)
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR462.toString());
    	
    	if(!asignadoPrestador)
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR465.toString());
    }
    
    private void repartirPrestadorServicioNoConvenioEquidad(DatosEntradaRepartoDTO datos, Caso caso, Rol rol) {
	   	
    	
    		
    		repartirPrestadorServicioNoConvenioNoDatosEquidad(datos, caso, rol);
    		
    }
    /**
     * Ejecuta el proceso de reparto cuando el servicio no tiene convenio
     * @param datos
     * @param caso
     */
    private void repartirPrestadorServicioNoConvenio(DatosEntradaRepartoDTO datos, Caso caso, Rol rol) {
    	   	
    	if(datos.getIdConciliador() == null 
    			&& datos.getFechaAudiencia() == null
    			&& datos.getHoraAudiencia() == null){
    		
    		repartirPrestadorServicioNoConvenioNoDatos(datos, caso, rol);
    		
    	} else if(datos.getIdConciliador() != null 
    			&& datos.getFechaAudiencia() == null
    			&& datos.getHoraAudiencia() == null){    		
    		repartirPrestadorServicioNoConvenioPrestador(datos, caso, rol);
    		
    	} else if(datos.getIdConciliador() != null 
    			&& datos.getFechaAudiencia() != null
    			&& datos.getHoraAudiencia() == null){
    		// llamado CON-F-106
    		repartirPrestadorServicioNoConvenioPrestadorFecha(datos, caso, rol);
    		
    	} else if(datos.getIdConciliador() != null 
    			&& datos.getFechaAudiencia() != null
    			&& datos.getHoraAudiencia() != null){
    		
    		// llamado CON-F-106
    		repartirPrestadorServicioNoConvenioPrestadorFechaHora(datos, caso, rol);
    		
    	} else if(datos.getIdConciliador() == null 
    			&& datos.getFechaAudiencia() != null
    			&& datos.getHoraAudiencia() == null){
    		// llamado CON-F-106
    		repartirPrestadorServicioNoConvenioFecha_Y_U_Hora(datos, caso, rol);
    		
    	} else if(datos.getIdConciliador() == null 
    			&& datos.getFechaAudiencia() != null
    			&& datos.getHoraAudiencia() != null){
    		
    		// llamado CON-F-106
    		repartirPrestadorServicioNoConvenioFecha_Y_U_Hora(datos, caso, rol);
    		
    	}
    	
    	
    }
    
    /**
     * Ejecuta el proceso de reparto cuando el servicio tiene convenio
     * @param datos
     * @param caso
     */
    private void repartirPrestadorServicioConvenio(DatosEntradaRepartoDTO datos, Caso caso, Rol rol){
    	if(datos.getIdConciliador() == null)
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR466.toString());
    	
    	//se asigna el prestador y se registra el evento
    	crearAsignacion(datos.getIdConciliador(), caso.getIdCaso(), datos.getIdRol(), datos.getUsuario(), 
    			UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
    	
    	//se incrementa el contador de casos asignados
    	incrementarContadorCasosAsignados(caso.getIdServicio(), caso.getIdMateria(), datos.getIdConciliador());
    	
    	//Se actualiza el estado del caso 'conciliador designado'
    	actualizarEstadoCasoDesignado(caso, datos.getIdConciliador());
    	
    	// se programa la audiencia del caso
    	programarAudiencia(datos.getHoraAudiencia(), null, datos.getIdConciliador(), datos.getIdSede(), caso.getIdCaso(), caso.getIdConvenio());
    	
    	//Notificacion de alerta
    	orquestadorAlertasFacade.alertaNOT_NPC(caso.getIdCaso(), datos.getIdConciliador(), UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES);
    	
    	//Seguarda la programacion de las notificaciones
    	manejadorProgramacionAlerta.crearProgramacionesAceptacionConciliacion(caso.getIdCaso(),  datos.getIdConciliador(), UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES);
  
    	
    	//se envía correo
    	enviarCorreoAsignacion(caso, rol, datos.getIdConciliador());
    	
    }
    
	/**
	 * Retorna el parámetro general con el último asignado a un caso segú el rol
	 * @param rol
	 * @return
	 */
    private ParametrosGenerales obtenerParametroGeneralUltimoAsignado(Rol rol) {
		ParametrosGenerales paramG = new ParametrosGenerales();
		switch (rol.getNombre()) {
		case UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION:
			paramG = manejadorParametrosGenerales
					.buscar(UtilParamGenerales.ACCR_ANALISTA_CONCILIACION);
			break;
		case UtilDominios.ROL_PERSONA_ABOGADO:
			paramG = manejadorParametrosGenerales
					.buscar(UtilParamGenerales.ACCR_ABOGADO_ARBITRAL);
			break;
		case UtilDominios.ROL_PERSONA_AUXILIAR_ADM:
			paramG = manejadorParametrosGenerales.buscar(UtilParamGenerales.ACCR_AUX_AUXILIAR_ADMINISTRATIVO);
			break;
		case UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION_EQUIDAD:
			paramG = manejadorParametrosGenerales
					.buscar(UtilParamGenerales.ACCR_ANALISTA_CONCILIACION_EQUIDAD);
			break;
			
		default:
			return null;
		}
		return paramG;
	}

    /**
     * Ejecuta el proceso de reparto cuando el rol a repartir es 
     * @param datos
     * @param caso
     * @param idSede
     */
 private void repartirOtrosRolesCalidad(DatosEntradaRepartoDTO datos, Caso caso, Rol rol){
    	
    	List<Number> lstIdsRolPersona = manejadorRolPersona.consultarRolesPersonaPorRolYCentro(datos.getIdRol(), caso.getSede().getIdCentro());
    	
    	if(lstIdsRolPersona == null || lstIdsRolPersona.isEmpty()){
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR468.toString());
    	}
    	
    	ParametrosGenerales pgUltimaAsignacion = obtenerParametroGeneralUltimoAsignado(rol);
    	
    	if(pgUltimaAsignacion == null){
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR471.toString());
    	}
    	
    	RolPersona rp = negocioRolPersonaCaso.realizarAsignacion(lstIdsRolPersona, pgUltimaAsignacion);
    
    	if(rp == null){
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR468.toString());
    	}
    	//Pendiente analizar 
    	crearAsignacion(rp.getIdPersona(), caso.getIdCaso(), rol.getIdRol(), datos.getUsuario(), 
    								null, null, UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO);
    	
    	//se envía correo
    			
    	enviarCorreoAsignacionCalidad(caso, rol, rp.getIdPersona());
    	
    		
    	}
    
    /**
     * Ejecuta el proceso de reparto cuando el rol a repartir es 'Analista de conciliación', 
     * 'Abogado de arbitraje' o 'Auxiliar Administrativo'
     * @param datos
     * @param caso
     * @param idSede
     */
    private void repartirOtrosRoles(DatosEntradaRepartoDTO datos, Caso caso, Rol rol){
    	
    	List<Number> lstIdsRolPersona = manejadorRolPersona.consultarRolesPersonaPorRolYCentro(datos.getIdRol(), caso.getSede().getIdCentro());
    	
    	if(lstIdsRolPersona == null || lstIdsRolPersona.isEmpty()){
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR468.toString());
    	}
    	
    	ParametrosGenerales pgUltimaAsignacion = obtenerParametroGeneralUltimoAsignado(rol);
    	
    	if(pgUltimaAsignacion == null){
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR471.toString());
    	}
    	
    	RolPersona rp = negocioRolPersonaCaso.realizarAsignacion(lstIdsRolPersona, pgUltimaAsignacion);
    	
    	if(rp == null){
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR468.toString());
    	}
    	
    	crearAsignacion(rp.getIdPersona(), caso.getIdCaso(), rol.getIdRol(), datos.getUsuario(), 
    								null, null, UtilDominios.ESTADO_ROL_PERSONA_CASO_ASIGNADO);
    	
    	//se envía correo
    	enviarCorreoAsignacion(caso, rol, rp.getIdPersona());
    	
    	//
    	if (rol.getNombre().equals(UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION_EQUIDAD)) {
    		
    		enviarCorreoAsignacionCalidad(caso, rol, rp.getIdPersona());
    	}
    		
    	//Alerta CONTLEG, caso pendiente por realizacion de control de legalidad 
    	if ( rol.getNombre().equals(UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION) )
    		manejadorProgramacionAlerta.crearProgramacionAlerta( caso.getIdCaso(), rp.getIdPersona(),
    															UtilDominios.CODIGO_ALERTA_CONTROL_LEGALIDAD,null );
    }
    
    
    /**
     * Método que se ejecuta cuando el proceso de reparto se invoca desde el caso de uso 
     * de cambio de conciliador y se ha seleccionado el prestador de servicio (se recibe
     * en los datos de entrada). 
     */
    public void repartoManual(DatosEntradaRepartoDTO datos, Caso caso, Rol rol){
    	String metodoNombramiento;
    	Audiencia audiencia = null;
    	Long idSede = datos.getIdSede() != null?datos.getIdSede(): caso.getIdSede();
		List<Audiencia> lstAudiencia = manejadorAudiencia.consultarAudienciasCasoPorTipoYEstado(caso.getIdCaso(), null,
				UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
    	
    	if(lstAudiencia != null && !lstAudiencia.isEmpty())
    		audiencia = lstAudiencia.get(UtilConstantes.CERO);
    	
    	//si no se tiene audiencia programada
    	if (audiencia == null) {    		
    		//se crea la asignación y el evento
			crearAsignacion(datos.getIdConciliador(), caso.getIdCaso(), rol.getIdRol(), datos.getUsuario(),
					UtilDominios.NOMBRAMIENTO_POR_LA_CCB, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL,
					UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
			metodoNombramiento =  UtilDominios.NOMBRAMIENTO_POR_LA_CCB;
    	} else { //se tiene audiencia programada    		
			boolean disponible = manejadorAgendaPersona.validarDisponibilidadPersona(datos.getIdConciliador(),
					audiencia.getHoraInicio(), audiencia.getHoraFin())
					&& manejadorDisponibilidadPersona.validarDisponibilidadPersona(
							UtilOperaciones.removerHora(audiencia.getHoraInicio()), audiencia.getHoraInicio(),
							audiencia.getHoraFin(), idSede, datos.getIdConciliador());
    		if(!disponible)
    			throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR476.toString());
    		crearAsignacion(datos.getIdConciliador(), caso.getIdCaso(), rol.getIdRol(), datos.getUsuario()
					, UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES
					, UtilDominios.TIPO_NOMBRAMIENTO_PRINCIPAL
					, UtilDominios.ESTADO_ROL_PERSONA_CASO_POR_ACEPTAR);
    		metodoNombramiento =  UtilDominios.NOMBRAMIENTO_POR_LAS_PARTES;
    		
    		//se registra el evento en la agenda del conciliador
			AgendaPersona agendaPersona = new AgendaPersona();
			
			agendaPersona.setEstado(UtilDominios.ESTADO_AGENDA_PERSONA_PROGRAMADA);
			agendaPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			agendaPersona.setFechaFin(audiencia.getHoraFin());
			agendaPersona.setFechaInicio(audiencia.getHoraInicio());
			agendaPersona.setFechaUltimaModificacion(new Date());
			agendaPersona.setIdAudiencia(audiencia.getIdAudiencia());
			agendaPersona.setIdPersona(datos.getIdConciliador());
			agendaPersona.setIdUsuarioModificacion(datos.getUsuario());
			agendaPersona.setTipoActividadAgenda(UtilDominios.TIPO_ACTIVIDAD_AGENDA_AUDIENCIA_CONCILIACION);
			
			manejadorAgendaPersona.crear(agendaPersona);
    	}
    	
    	//se incrementa el contador de casos asignados
    	//incrementarContadorCasosAsignados(caso.getIdServicio(), caso.getIdMateria(), datos.getIdConciliador());
    	
    	//Se actualiza el estado del caso 'conciliador designado'
    	actualizarEstadoCasoDesignado(caso, datos.getIdConciliador());
    	
    	//se envía correo
    	if(caso.getIdServicio() != UtilConstantes.ID_SERVICIO_CONCILIACION_MEDIACION)
    		enviarCorreoAsignacion(caso, rol, datos.getIdConciliador());
    	
       	//Seguarda la programacion de las notificaciones
    	manejadorProgramacionAlerta.crearProgramacionesAceptacionConciliacion(caso.getIdCaso(),  datos.getIdConciliador(), metodoNombramiento);
  
    	
    	//Notificacion de alerta
    	orquestadorAlertasFacade.alertaNOT_NPC( caso.getIdCaso() ,datos.getIdConciliador(), metodoNombramiento);
    	
    }
    
    /**
     * retorna del istado de personas recibido por parámetro aquellos que tienen disponibilidad
     * para la fecha y hora de la audiencia recibida por parámetro
     * @param lstIdPersonas
     * @param audiencia
     * @return
     */
    private List<Object[]> obtenerPrestadoresConDisponibilidad(List<Object[]> lstIdPersonas, Audiencia audiencia, Long idSede){
		List<Object[]> lstIdPersonasAsignar = new ArrayList<Object[]>();
		//se obtienen solo los prestadores con disponibilidad en la fecha y hora de la audiencia
		for (Object[] regPrestador: lstIdPersonas){			
			Long idPersona = ((BigDecimal) regPrestador[UtilConstantes.CERO]).longValue();
			boolean disponible = manejadorDisponibilidadPersona.validarDisponibilidadPersona(
					UtilOperaciones.removerHora(audiencia.getHoraInicio()), audiencia.getHoraInicio(),
					audiencia.getHoraFin(), idSede, idPersona)
					&& manejadorAgendaPersona.validarDisponibilidadPersona(idPersona, audiencia.getHoraInicio(),
							audiencia.getHoraFin());
			if(disponible)
				lstIdPersonasAsignar.add(regPrestador);
		}
		return lstIdPersonasAsignar;
    	
    }
    
    /**
     * Método que se ejecuta cuando el proceso de reparto se invoca desde el caso de uso 
     * de cambio de conciliador y no se ha seleccionado el prestador de servicio (no se recibe
     * en los datos de entrada) y el caso no es de convenio. 
     */
    public void repartoAutomaticoNoConvenio(DatosEntradaRepartoDTO datos, Caso caso, Rol rol) {
    	Audiencia audiencia = null;
		List<Audiencia> lstAudiencia = manejadorAudiencia.consultarAudienciasCasoPorTipoYEstado(caso.getIdCaso(), null,
				UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
    	
    	if(lstAudiencia != null && !lstAudiencia.isEmpty())
    		audiencia = lstAudiencia.get(UtilConstantes.CERO);
    		
    	//si la sede no viene en los datos de entrada se toma la sede del caso
    	Long idSede = datos.getIdSede()!=null?datos.getIdSede():caso.getSede().getIdSede();
    	
    	//si se tiene audiencia programada
    	if (audiencia != null) {
    		
    		List<Object[]> lstIdPersonasAsignar = consultarValidarPrestadorParaAsignar(datos, caso, idSede); 
    		lstIdPersonasAsignar = obtenerPrestadoresConDisponibilidad(lstIdPersonasAsignar, audiencia, idSede);
    		Long idPersonaAsignada = asignarPrestadorMenosCasos(Boolean.TRUE, lstIdPersonasAsignar, caso, rol, datos.getUsuario());
    		//Se actualiza el estado del caso 'conciliador designado'
        	actualizarEstadoCasoDesignado(caso, idPersonaAsignada);
			//se crea el evento en la agenda de la persona
        	AgendaPersona agendaPersona = new AgendaPersona();
			agendaPersona.setEstado(UtilDominios.ESTADO_AGENDA_PERSONA_PROGRAMADA);
			agendaPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			agendaPersona.setFechaFin(audiencia.getHoraFin());
			agendaPersona.setFechaInicio(audiencia.getHoraInicio());
			agendaPersona.setFechaUltimaModificacion(new Date());
			agendaPersona.setIdAudiencia(audiencia.getIdAudiencia());
			agendaPersona.setIdPersona(idPersonaAsignada);
			agendaPersona.setIdUsuarioModificacion(datos.getUsuario());
			agendaPersona.setTipoActividadAgenda(UtilDominios.TIPO_ACTIVIDAD_AGENDA_AUDIENCIA_CONCILIACION);
			
			manejadorAgendaPersona.crear(agendaPersona);
        	
        	//se envía correo
        	enviarCorreoAsignacion(caso, rol, idPersonaAsignada);
        	
           	//Seguarda la programacion de las notificaciones
        	manejadorProgramacionAlerta.crearProgramacionesAceptacionConciliacion(caso.getIdCaso(),  idPersonaAsignada,  UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
      	
        	//Notificacion de alerta
        	orquestadorAlertasFacade.alertaNOT_NPC(caso.getIdCaso(),idPersonaAsignada, UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
        	
    	} else { //no se tiene audiencia programada
    		repartirPrestadorServicioNoConvenioNoDatos(datos, caso, rol);
    	}

    }
    

    /**
     * Método que se ejecuta cuando el proceso de reparto se invoca desde el caso de uso 
     * de cambio de conciliador y no se ha seleccionado el prestador de servicio (no se recibe
     * en los datos de entrada) y el caso es de convenio. 
     */
    public void repartoAutomaticoConvenio(DatosEntradaRepartoDTO datos, Caso caso, Rol rol){
    	Audiencia audiencia = null;
    	if(caso.getIdConvenio() == null)
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR475.toString());
    		
		List<Audiencia> lstAudiencia = manejadorAudiencia.consultarAudienciasCasoPorTipoYEstado(caso.getIdCaso(), null,
				UtilDominios.ESTADO_AUDIENCIA_PENDIENTE);
    	
    	if(lstAudiencia != null && !lstAudiencia.isEmpty())
    		audiencia = lstAudiencia.get(UtilConstantes.CERO);
    		
    	//si la sede no viene en los datos de entrada se toma la sede del caso
    	Long idSede = datos.getIdSede()!=null?datos.getIdSede():caso.getSede().getIdSede();
    	List<Object[]> lstIdPersonasAsignar = 
    			manejadorPersona.consultarPrestadoresActivosAsignadosConvenio(
    										rol.getIdRol(), caso);
    	
    	if(lstIdPersonasAsignar == null || lstIdPersonasAsignar.isEmpty())
    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR477.toString());
    	
    	//si se tiene audiencia programada
    	if (audiencia != null) {
    		lstIdPersonasAsignar = obtenerPrestadoresConDisponibilidad(lstIdPersonasAsignar, audiencia, idSede);
    		if(lstIdPersonasAsignar.isEmpty())
    			throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR463.toString());
    	} 

    	Long idPersonaAsignada = asignarPrestadorMenosCasos(Boolean.TRUE.booleanValue(), lstIdPersonasAsignar, caso, rol, datos.getUsuario());
    	//Se actualiza el estado del caso 'conciliador designado'
    	actualizarEstadoCasoDesignado(caso, idPersonaAsignada);
    	
    	if (audiencia != null) { //se registra el evento al principal
    		//se registra el evento en la agenda del conciliador
			AgendaPersona agendaPersona = new AgendaPersona();
			
			agendaPersona.setEstado(UtilDominios.ESTADO_AGENDA_PERSONA_PROGRAMADA);
			agendaPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			agendaPersona.setFechaFin(audiencia.getHoraFin());
			agendaPersona.setFechaInicio(audiencia.getHoraInicio());
			agendaPersona.setFechaUltimaModificacion(new Date());
			agendaPersona.setIdAudiencia(audiencia.getIdAudiencia());
			agendaPersona.setIdPersona(idPersonaAsignada);
			agendaPersona.setIdUsuarioModificacion(datos.getUsuario());
			agendaPersona.setTipoActividadAgenda(UtilDominios.TIPO_ACTIVIDAD_AGENDA_AUDIENCIA_CONCILIACION);
    			
			manejadorAgendaPersona.crear(agendaPersona);
    	
    	} 

    	//se envía correo
    	enviarCorreoAsignacion(caso, rol, idPersonaAsignada);
    	
       	//Seguarda la programacion de las notificaciones
    	manejadorProgramacionAlerta.crearProgramacionesAceptacionConciliacion(caso.getIdCaso(),  idPersonaAsignada,  UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
  
    	
    	//Notificacion de alerta
    	orquestadorAlertasFacade.alertaNOT_NPC(caso.getIdCaso(), idPersonaAsignada,UtilDominios.NOMBRAMIENTO_POR_LA_CCB);
    }
    
    /**
     * Método que se ejecuta cuando el proceso de reparto se invoca desde el caso de uso 
     * de cambio de conciliador.
     */
    private void repartoAutomatico(DatosEntradaRepartoDTO datos, Caso caso, Rol rol) {
    	//si el servicio es de convenio
		if(caso.getServicioMateria().getServicio().getIdServicio().longValue() 
				== UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO.longValue()){
			repartoAutomaticoConvenio(datos, caso, rol);
		} else { //el servicio no es de convenio
			repartoAutomaticoNoConvenio(datos, caso, rol);
		}
    }
   
    
    /**
     * @see IRepartoSvc#reparto(DatosEntradaRepartoDTO)
     * @param datos
     * @throws Exception 
     */
    @Override
    public void repartoEquidad(DatosEntradaRepartoDTO datos) throws Exception {
    	
    	try{
	    	manejadorParametrosGenerales.iniciarProcesoReparto();
	    	//validaciones
	    	if(datos.getIdCaso() == null)
	    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR450.toString());
	    	if(datos.getIdRol() == null)
	    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR451.toString());
	    	if(datos.getUsuario() == null || datos.getUsuario().isEmpty())
	    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR474.toString());
	    	
	    	Rol rol = manejadorRol.buscar(datos.getIdRol());
	    	Caso caso = manejadorCaso.buscar(datos.getIdCaso());
	    	if(caso == null){
	    		String[] vctParametros = new String[]{datos.getIdCaso().toString()};
	    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR452.toString(), vctParametros);
	    	}
	    	if(rol == null){
	    		String[] vctParametros = new String[]{datos.getIdRol().toString()};
	    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR453.toString(), vctParametros);
	    	}
			if(datos.getInvocadoDesdeCambioConciliador() != null 
					&& datos.getInvocadoDesdeCambioConciliador().booleanValue()){
								
					repartoAutomatico(datos, caso, rol);  
			}else if(rol.getNombre().equals(UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION_EQUIDAD)) {
					
					repartirOtrosRolesCalidad(datos, caso, rol);	    		
			}else { //reparto normal de equidad			
					
					repartirPrestadorServicioNoConvenioEquidad(datos, caso, rol);		    							
			}			
			
		} catch (SimascException e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		} catch (SIMASCNegocioExcepcion e) {
			throw new Exception(e.getMessage());
		}
    	
    	
    }
    
    /**
     * @see IRepartoSvc#reparto(DatosEntradaRepartoDTO)
     * @param datos
     * @throws Exception 
     */
    @Override
    public void reparto(DatosEntradaRepartoDTO datos) throws Exception {
    	
    	try{
	    	manejadorParametrosGenerales.iniciarProcesoReparto();
	    	//validaciones
	    	if(datos.getIdCaso() == null)
	    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR450.toString());
	    	if(datos.getIdRol() == null)
	    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR451.toString());
	    	if(datos.getUsuario() == null || datos.getUsuario().isEmpty())
	    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR474.toString());
	    	
	    	Rol rol = manejadorRol.buscar(datos.getIdRol());
	    	Caso caso = manejadorCaso.buscar(datos.getIdCaso());
	    	if(caso == null){
	    		String[] vctParametros = new String[]{datos.getIdCaso().toString()};
	    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR452.toString(), vctParametros);
	    	}
	    	if(rol == null){
	    		String[] vctParametros = new String[]{datos.getIdRol().toString()};
	    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR453.toString(), vctParametros);
	    	}
			if(datos.getInvocadoDesdeCambioConciliador() != null 
					&& datos.getInvocadoDesdeCambioConciliador().booleanValue()){
				if(datos.getIdConciliador() != null)
					repartoManual(datos, caso, rol);
				else					
					repartoAutomatico(datos, caso, rol);
			} else if(manejadorTipoDeServicioRol.esPrestadorServicio(rol.getIdRol())){ //si el rol es prestador de servicio
	    		
	    		//si el servicio es de convenio
	    		if(caso.getIdServicio().longValue() 
	    				== UtilConstantes.ID_SERVICIO_CONCILIACION_CONVENIO.longValue())
	    			repartirPrestadorServicioConvenio(datos, caso, rol);
	    		else //el servicio no es de convenio
	    			repartirPrestadorServicioNoConvenio(datos, caso, rol);
	    		
	    	} else if (rol.getNombre().equals(UtilDominios.ROL_PERSONA_ANALISTA_DE_CONCILIACION) 
	    					|| rol.getNombre().equals(UtilDominios.ROL_PERSONA_ABOGADO) 
	    					|| rol.getNombre().equals(UtilDominios.ROL_PERSONA_AUXILIAR_ADM)) {
	    		
	    		repartirOtrosRoles(datos, caso, rol);
	    		
	    	} else {
	    		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR460.toString());
	    	}
			if (datos.getEvento() != null) {
				eventoFacade.crear(datos.getEvento());				
			}
		} catch (SimascException e) {
			throw new SIMASCNegocioExcepcion(e.getMessage());
		} catch (SIMASCNegocioExcepcion e) {
			throw new Exception(e.getMessage());
		}
    }
    
   
    /**
     * Método para programar la audiencia del caso
     * @param horaInicio
     * @param horaFin
     * @param idConciliador
     * @param idSede
     * @param idCaso
     * @param idConvenio
     */
    private void programarAudiencia(Date horaInicio, Date horaFin, Long idConciliador, Long idSede, Long idCaso, Long idConvenio) {
    	ConsultaAgendamientoDTO audienciaPorProgramar = new ConsultaAgendamientoDTO();
    	audienciaPorProgramar.setFechaInicio(horaInicio);
    	audienciaPorProgramar.setIdCaso(idCaso);
    	audienciaPorProgramar.setIdConciliador(idConciliador);
    	audienciaPorProgramar.setIdSede(idSede);
    	if (horaFin != null)
    		audienciaPorProgramar.setFechaFin(horaFin);
    	else
    		audienciaPorProgramar.setFechaFin(obtenerHoraFin(horaInicio, idConvenio));
    	try {
    		audienciaFacade.programarAudienciaConciliacion(audienciaPorProgramar);    	    		
    	} catch (SimascNegocioPruebaException e) {
    		throw new SIMASCNegocioExcepcion(e.getMessage());
    	}
    }
    
    /**
     * Método para obtener la hora final de la audiencia
     * @param horaInicio
     * @param idConvenio
     * @return
     */
    private Date obtenerHoraFin(Date horaInicio, Long idConvenio ) {
    	Date horaFin;
    	if (idConvenio != null) {
    		Convenio convenio = convenioFacade.buscar(idConvenio);
    		horaFin = UtilOperaciones.adicionarMinutos(horaInicio,
    				obtenerDuracionAudienciaEnMinutos(convenio.getDuracionAudiencias()));    		
    	} else {
    		ParametroDeServicioPK psPk = new ParametroDeServicioPK();
        	psPk.setIdServicio(UtilConstantes.ID_SERVICIO_CONCILIACION_TRAMITE_ORDINARIO);
        	psPk.setTipoParametro(UtilDominios.TIPO_PARAMETRO_PROGRAMACION_AUDIENCIA);
        	psPk.setNombre(UtilDominios.PARAMETRO_DE_SERVICIO_DURACION_DE_AUDIENCIA);
        	ParametroDeServicio psDuracionAudiencia = manejadorParametroDeServicio.buscar(psPk);    	
        	if(psDuracionAudiencia == null)    		
        		throw UtilOperaciones.crearSIMASCNegocioExcepcion(MensajesEnum.ERROR472.toString());
        	horaFin = UtilOperaciones.adicionarMinutos(horaInicio,
    				obtenerDuracionAudienciaEnMinutos(psDuracionAudiencia.getValor()));
    	}
    	
		return horaFin;
    }
    
    /**
     * @see IRepartoSvc#fallaReparto( void )
     * @param String detallesNoReparto
     */
    @Override
    public void fallaReparto( Long idCaso, String detallesNoReparto, String idUsuarioModificacion ){
    	String cadenaConvertida = detallesNoReparto;
    	if(detallesNoReparto.contains("exception")) {
    		cadenaConvertida = detallesNoReparto.split(UtilConstantes.DOS_PUNTOS)[1];
    	}
    	cadenaConvertida = cadenaConvertida.replace("\"", "");
    	eventoFacade.registrarEvento( idCaso, UtilDominios.TIPO_EVENTO_REPARTO_NO_EXITOSO,
    									cadenaConvertida, idUsuarioModificacion );
    	
    	serviciosCorreo.notificarFalloReparto( idCaso, cadenaConvertida );
    }
    
    
    /**
     * Envía correo informando la devolucion al conciliador de equidad 
     * @param caso
     * @param idPersona
     */
    public void envioCorreoRechazo(Long caso, Long idPersona, String observaciones) {
    	String asunto= "";
    	String cuerpo = "";
    	    	   
 		
    					
		asunto = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO615.toString()));
							
		cuerpo = String.format(MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO616.toString()),
				new Object[] {caso, observaciones}); 
			
  				
		List<Persona> lstPersonas = new ArrayList<Persona>();
	    lstPersonas.add(manejadorPersona.buscar(idPersona));
	    
	    List<String> lstCuerpo = new ArrayList<String>();
	    lstCuerpo.add(cuerpo);
	    	
	    serviciosCorreo.envioDeCorreo(asunto, lstPersonas, null, null, lstCuerpo, caso, null, null, Boolean.FALSE);
    }
    
    private void validarEnvioCorreoRolPersonaCaso(CorreoRolPersonaCaso crpc) {

		if (crpc.getIdRolReceptor() != null) {
			crpc.setInvitado(null);
			crpc.setPersonaQueRecibe(null);
			crpc.setIdPersonaQueRecibe(null);
		} else if (crpc.getIdInvitado() != null || crpc.getInvitado() != null) {
			if (crpc.getInvitado() != null) {
				crpc.setIdInvitado(crpc.getInvitado().getIdInvitado());
				crpc.setCorreoReceptor(crpc.getInvitado().getCorreo());
			}
			crpc.setIdRolReceptor(null);
			crpc.setIdPersonaReceptor(null);
			crpc.setIdCasoReceptor(null);
			crpc.setPersonaQueRecibe(null);
			crpc.setIdPersonaQueRecibe(null);
			crpc.setRolPersonaCaso(null);
		} else if (crpc.getIdPersonaQueRecibe() != null || crpc.getPersonaQueRecibe() != null) {
			if (crpc.getPersonaQueRecibe() != null)
				crpc.setIdPersonaQueRecibe(crpc.getPersonaQueRecibe().getIdPersona());
			crpc.setIdRolReceptor(null);
			crpc.setPersonaQueRecibe(null);
			crpc.setIdPersonaQueRecibe(null);
			crpc.setIdCasoReceptor(null);
			crpc.setInvitado(null);
			crpc.setRolPersonaCaso(null);
		}
	}
    
}
