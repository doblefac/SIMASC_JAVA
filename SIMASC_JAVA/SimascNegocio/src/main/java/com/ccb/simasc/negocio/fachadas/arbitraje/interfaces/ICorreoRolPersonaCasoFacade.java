package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.entidades.CartaPersona;
import com.ccb.simasc.transversal.entidades.CorreoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.SolicitudPrestador;
import com.ccb.simasc.transversal.dto.AgendamientoDTO;
import com.ccb.simasc.transversal.dto.CasoDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.CorreoRolPersonaCasoDTO;
import com.ccb.simasc.transversal.dto.DocumentoDTO;
import com.ccb.simasc.transversal.dto.NotificacionEnviadaDTO;
import com.ccb.simasc.transversal.dto.ParametrosEnvioCorreoDTO;
import com.ccb.simasc.transversal.dto.formularios.EnvioCorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.formularios.InformacionCorreoDTO;
import com.ccb.simasc.transversal.entidades.Invitado;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link CorreoRolPersonaCaso}
 * @author sMartinez
 *
 */
@Local
public interface ICorreoRolPersonaCasoFacade extends IAccesoFacade<CorreoRolPersonaCaso, Long, CorreoRolPersonaCasoDTO> {

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones


	 /**
	  * Metodo para envio de correos, usa el servicio web proveido por CCB
	  * @param parametros ParametrosEnvioCorreoDTO para el envío del correo
	  * @return true si el correo fue enviado, false si no
	  */
	public boolean enviarCorreo(ParametrosEnvioCorreoDTO parametrosEnvio);
	 
	 /**
	  * Metodo que retorna todos los correos que se han enviado para un caso
	  * 
	  * @param idCaso
	  * @return
	  */
	 public List<NotificacionEnviadaDTO> buscarCorreosEnviados(Long idCaso);
	 
	 /**
	  * Método que consulta expecifícamente un acuse de un correo enviado  
	  * 
	  * @param idCaso
	  * @param tipoAcuse
	  * @return el html en formato String del acuse
	  */
	 public String obtenerAcuse(Long idCaso, String tipoAcuse);
	 
	/**
	 * Obtiene la informacion de Personas que registran falla en el envio de
	 * correos electronicos.
	 * 
	 * @return List<InformacionCorreoDTO>
	 */
	 public List<InformacionCorreoDTO> obtenerInformacionCorreosDevueltos() throws SIMASCNegocioExcepcion;
	 
	 /**
	 * Metodo que trnasforma un EnvioCorreoElectronicoDTO a ParametrosEnvioCorreoDTO
	 * @param correo
	 * @return
	 */
	public ParametrosEnvioCorreoDTO convertidorParametros(EnvioCorreoElectronicoDTO correo);
	
	/**
	  * envia el correo electronico a la secretaria para informar de una solicitud de agendamiento pendiente.
	  * @param agendamientoDTO
	  * @throws SIMASCNegocioExcepcion
	  */
	 public void enviarCorreoSecretariaSalas(AgendamientoDTO agendamientoDTO) throws SIMASCNegocioExcepcion;
	 
	 /**
	  * Metodo que se encarga del envio del correo
	  * @param asunto
	  * @param persona
	  * @param textoCorreo
	  * @param idCaso
	  * @param receptores
	  * @throws SIMASCNegocioExcepcion
	  */
	 public boolean envioDeCorreo(String asunto, List<Persona> personas, List<RolPersonaCaso> receptores, List<Invitado> invitados, List<String> textoCorreo, Long idCaso, List<DocumentoDTO> adjuntos, Long idAudiencia ,Boolean certificado) throws SIMASCNegocioExcepcion;
	 
	/**
	 * Metodo creado para enviar correo electronico y no persistir la
	 * informacion en SIMASC (Acuse, CorreoRolPersonaCaso, etc) Se recibe un
	 * asunto, un contenido y una lista de destinatarios. Si el servicio de
	 * envio de correo llega a presentar una falla de desconexion se controla
	 * mediante la captura de la excepcion y permite continuar con la
	 * transaccion.
	 * 
	 * ARB-F-108
	 * 
	 * @param destinos
	 * @param asunto
	 * @param contenido
	 * @throws SIMASCNegocioExcepcion
	 */
	public void enviarCorreoSinPersistencia(List<String> destinos, String asunto, String contenido)
			throws SIMASCNegocioExcepcion;
	/**
	 * evnia el correo que le informa a un conciliador que fue asignado a un caso.
	 * @param idCaso
	 * @param idConciliador
	 */
	public void envioCorreoNombramientoConciliador(Long idCaso, Long idConciliador);
	
	/**
	 * envia el correo de caso sin conciliador al secretario de conciacion.
	 * @param idCaso
	 */
	public void envioCorreoCasoSinConciliador(Long idCaso);
	
	/**
	 * método encargado de enviar la citacion por correo, recibe un DTO EnvioCorreoElectronicoDTO 
	 * y retorna una lista de CorreoElectronicoDTO con los correos que les hizo la notificacion.
	 * @param parametrosEnvioCorreoDTO
	 * @return
	 */
	public List<CorreoElectronicoDTO> envioCitacionCorreo( EnvioCorreoElectronicoDTO correo );

	/**
	 * metodo que envia el correo electronico de la info del front
	 * @param correo
	 * @return
	 */
	public String enviarCorreoElectronico(EnvioCorreoElectronicoDTO correo);
	
	/** CON-F-106 12-02-2018
	 * envia una notificacion a las secretarias de conciliacion de la sede 
	 *  del caso para hacer recobro por el numero de audiencias. 
	 * @author prendon
	 * @param CasoDTO, Long idAudiencia
	 */
	public void notificarSecretariaConciliacion( CasoDTO casoDTO, Long idAudiencia );
	
	/** CON-F-118
	 * Metodo que envia una notificacion a los jefes de conciliacion, analistas de conciliacion o 
	 * jefe de arbitraje y asistente de arbitraje que se hizo una solicitud de cambio de lista
	 * @param solicitudCambioLista
	 * @param solicitudDeArbitraje	
	 * @param idCentros
	 */
	public void notificarJefeAnalistaSolicitudCambioLista( SolicitudPrestador solicitudCambioLista, boolean solicitudDeArbitraje, List<Long> idCentros );
	
	/** CON-C-004
	 * Metodo que notifica al solicitante una novedad en la solicitud de cambio de lista
	 * @param solicitudCambioLista
	 */
	public void notificarSolicitanteCambioLista( SolicitudPrestador solicitudPrestador);

	/**
	 * Envia el correo de las alertas sin las demas validaciones de negocio
	 * @param parametrosEnvio
	 * @return
	 * @throws SIMASCNegocioExcepcion
	 */
	public boolean enviarCorreoAlerta(ParametrosEnvioCorreoDTO parametrosEnvio) throws SIMASCNegocioExcepcion;

	/** Envia el correo al analista de conciliacion cuando falla el reparto
	 * @param idCaso
	 * @param detallesNoReparto
	 */
	public void notificarFalloReparto(Long idCaso, String detallesNoReparto);

	/** Envío automático al correo electrónico del conciliador, asignacion caso
	 * @param idCaso
	 * @param idConciliador
	 */
	public void envioCorreoNombramientoConciliadorEquidad(Long idCaso, Long idPersonaAsignada);
	
	// protected region metodos adicionales end
	
	public CorreoRolPersonaCaso persistirCorreoRolPersonaCaso(ParametrosEnvioCorreoDTO parametrosEnvioCorreoDTO,CorreoElectronicoDTO correo );
		
	
}
