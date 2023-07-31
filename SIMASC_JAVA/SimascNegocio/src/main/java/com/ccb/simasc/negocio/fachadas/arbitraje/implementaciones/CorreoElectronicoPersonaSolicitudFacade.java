package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronicoPersonaSolicitud;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoPersonaSolicitudFacade;
import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoPersonaSolicitudDTO;
import com.ccb.simasc.transversal.dto.PersonaSolicitudDTO;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.CorreoElectronicoPersonaSolicitud;
import com.ccb.simasc.transversal.entidades.CorreoElectronicoPersonaSolicitudPK;
import com.ccb.simasc.transversal.entidades.PersonaSolicitud;
import com.ccb.simasc.transversal.entidades.PersonaSolicitudPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilMascaraTexto;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link CorreoElectronicoPersonaSolicitud}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class CorreoElectronicoPersonaSolicitudFacade extends AccesoFacade<CorreoElectronicoPersonaSolicitud, CorreoElectronicoPersonaSolicitudPK, CorreoElectronicoPersonaSolicitudDTO> implements ICorreoElectronicoPersonaSolicitudFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ICorreoElectronicoFacade correoElectronicoFacade;
	
	@EJB
	private ManejadorCorreoElectronicoPersonaSolicitud manejadorCorreoElectronicoPersonaSolicitud;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorCorreoElectronicoPersonaSolicitud;
	}

	@Override
	public CorreoElectronicoPersonaSolicitudDTO transformarSinDependencias(CorreoElectronicoPersonaSolicitud obj) {
		CorreoElectronicoPersonaSolicitudDTO dto = new CorreoElectronicoPersonaSolicitudDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public CorreoElectronicoPersonaSolicitudDTO transformarConDependencias(CorreoElectronicoPersonaSolicitud obj) {
		CorreoElectronicoPersonaSolicitudDTO dto = new CorreoElectronicoPersonaSolicitudDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public CorreoElectronicoPersonaSolicitud transformarEntidadConDependencias(CorreoElectronicoPersonaSolicitud obj) {
		CorreoElectronicoPersonaSolicitud dto = new CorreoElectronicoPersonaSolicitud();
		dto = new CorreoElectronicoPersonaSolicitudDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public CorreoElectronicoPersonaSolicitud transformarEntidadSinDependencias(CorreoElectronicoPersonaSolicitud obj) {
		CorreoElectronicoPersonaSolicitud dto = new CorreoElectronicoPersonaSolicitud();
		dto = new CorreoElectronicoPersonaSolicitudDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	@Override
	public List<CorreoElectronico> consultarCorreosPorPersonaSolicitud(PersonaSolicitudPK personaSolicitudPk) {
		return (List<CorreoElectronico>) new CorreoElectronicoDTO()
				.transformarColeccionEntidadesSinDependencias(manejadorCorreoElectronicoPersonaSolicitud.consultarCorreosPorPersonaSolicitud(personaSolicitudPk));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonaSolicitud> consultarPersonasSolicitudPorCorreo(Long idCorreo) {
		return (List<PersonaSolicitud>) new PersonaSolicitudDTO().transformarColeccionEntidadesSinDependencias(manejadorCorreoElectronicoPersonaSolicitud.consultarPersonasSolicitudPorCorreo(idCorreo));
	}
	
	@Override
	public CorreoElectronicoPersonaSolicitud crearCorreoElectronicoPersonaSolicitud(PersonaSolicitud personaSolicitud, CorreoElectronico correoElectronico) {
					
		// Llave Primaria
		CorreoElectronicoPersonaSolicitudPK correoElectronicoPersonaSolicitudoPk = new CorreoElectronicoPersonaSolicitudPK();
		correoElectronicoPersonaSolicitudoPk.setIdSolicitudServicio(personaSolicitud.getPersonaSolicitudPK().getIdSolicitudServicio());
		correoElectronicoPersonaSolicitudoPk.setIdPersona(personaSolicitud.getPersonaSolicitudPK().getIdPersona());
		correoElectronicoPersonaSolicitudoPk.setIdRol(personaSolicitud.getPersonaSolicitudPK().getIdRol());		
		correoElectronicoPersonaSolicitudoPk.setIdCorreo(correoElectronico.getIdCorreo());		
		
		// Crear Entidad
		CorreoElectronicoPersonaSolicitud correoElectronicoPersonaSolicitud = new CorreoElectronicoPersonaSolicitud();
		correoElectronicoPersonaSolicitud.setCorreoElectronicoPersonaSolicitudPK(correoElectronicoPersonaSolicitudoPk);
		correoElectronicoPersonaSolicitud.setCorreoElectronico(correoElectronico);
		correoElectronicoPersonaSolicitud.setPersonaSolicitud(personaSolicitud);		
		correoElectronicoPersonaSolicitud.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		// Persistencia
		manejadorCorreoElectronicoPersonaSolicitud.crear(correoElectronicoPersonaSolicitud);
		
		return correoElectronicoPersonaSolicitud;
	}

	@Override
	public CorreoElectronicoPersonaSolicitud crearCorreoElectronicoPersonaSolicitud(PersonaSolicitud personaSolicitud, String direccionCorreoElectronico, String tipoCorreo) {
		CorreoElectronico correoElectronicoPersona = null;
		
		// Consultar Correos Electronicos asociados a la Persona
		List<CorreoElectronico> correosElectronicosList = correoElectronicoFacade.consultaCorreosPersona(personaSolicitud.getPersonaSolicitudPK().getIdPersona(), true);
		
		// Obtener identificador de Correo Electronico a partir de la dirección (Si este existe)
		for(CorreoElectronico correoElectronico : correosElectronicosList) {
			if(correoElectronico.getDireccion().equals(direccionCorreoElectronico)) {
				correoElectronicoPersona = correoElectronico;
				break;
			}
		}
		
		// Crear Correo Electronico asociado a la Persona, si este no existe
		if(correoElectronicoPersona == null) {
			// Crear Entidad
			correoElectronicoPersona = new CorreoElectronico();
			correoElectronicoPersona.setDireccion(direccionCorreoElectronico);
			correoElectronicoPersona.setDireccionAnterior(direccionCorreoElectronico);
			correoElectronicoPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			correoElectronicoPersona.setIdPersona(personaSolicitud.getPersonaSolicitudPK().getIdPersona());
			correoElectronicoPersona.setTipo(tipoCorreo);			
			
			// Persistencia
			correoElectronicoFacade.crear(correoElectronicoPersona);
		}
		else {
			// Si el Correo Electronico existe y se encuentra Inactivo, se realiza la respectiva Activación
			if(correoElectronicoPersona.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_INACTIVO)) {
				// Establecer Estado Activo
				correoElectronicoPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				
				// Persistencia
				correoElectronicoFacade.actualizar(correoElectronicoPersona);
			}
		}
		
		// Crear asociación del Correo Electronico con Rol Persona Caso
		return crearCorreoElectronicoPersonaSolicitud(personaSolicitud, correoElectronicoPersona);	
	}

	@Override
	public void asociarCorreosElectronicosPersonaSolicitud(List<CorreoElectronicoDTO> correosElectronicos, PersonaSolicitud personaSolicitud) {
				
		// Validar Parámetros
		if(correosElectronicos == null || personaSolicitud == null) return;
		if(correosElectronicos.size() == 0) return;		
		
		// Obtener Correos Electronicos asociados al PersonaSolicitud
		List<CorreoElectronico> correosElectronicosPersonaSolicitud = consultarCorreosPorPersonaSolicitud(personaSolicitud.getPersonaSolicitudPK());
		
		// Procesar Correos Electronicos enviados como parámetro
		for(CorreoElectronicoDTO correoElectronico : correosElectronicos) {

			if(correoElectronico.getDireccion() != null && !correoElectronico.getDireccion().isEmpty() && 
					!UtilMascaraTexto.hasEmailOnlyDots(correoElectronico.getDireccion())) {		
				/*
				 * CORREO PRINCIPAL:
				 * 
				 * Si el Correo Electronico procesado es Principal, se actualiza el que tiene asociado la Persona,
				 * en caso de que no tenga asociado ninguno, se procede a realizar la creación respectiva  
				 */
				if(correoElectronico.getTipo().equals(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL)) {
					
					// Obtener Correo Principal Actual
					List<CorreoElectronico> correosElectronicosPersona = correoElectronicoFacade.consultaCorreosPersona(personaSolicitud.getPersonaSolicitudPK().getIdPersona(), false);
					CorreoElectronico correoElectronicoPrincipalActual = null;
					
					for(CorreoElectronico correoElectronicoPersona : correosElectronicosPersona) {
						if(correoElectronicoPersona.getTipo().equals(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL)) {
							correoElectronicoPrincipalActual = correoElectronicoPersona;
							break;
						}
					}
					
					// Si la Persona no cuenta con un Correo Principal, se realiza la creación del mismo
					if(correoElectronicoPrincipalActual == null) {
						// Crear Entidad					
						correoElectronicoPrincipalActual = new CorreoElectronico();
						correoElectronicoPrincipalActual.setDireccion(correoElectronico.getDireccion());
						correoElectronicoPrincipalActual.setDireccionAnterior(correoElectronico.getDireccion());
						correoElectronicoPrincipalActual.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
						correoElectronicoPrincipalActual.setIdPersona(personaSolicitud.getPersonaSolicitudPK().getIdPersona());
						correoElectronicoPrincipalActual.setTipo(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
						
						// Persistencia
						correoElectronicoFacade.crear(correoElectronicoPrincipalActual);
					}
					else {
						// Si la Persona cuenta con un Correo Principal, se actualiza la dirección asociada,
						// siempre y cuando esta haya sido modificada					
						if(!correoElectronicoPrincipalActual.getDireccion().equals(correoElectronico.getDireccion())
								|| !correoElectronicoPrincipalActual.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_ACTIVO)) {
							// Actualizar Dirección
							correoElectronicoPrincipalActual.setDireccion(correoElectronico.getDireccion());
							correoElectronicoPrincipalActual.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
							correoElectronicoPrincipalActual.setDireccionAnterior(correoElectronico.getDireccionAnterior());
							// Persistencia
							correoElectronicoFacade.actualizar(correoElectronicoPrincipalActual);
						}
					}				
				}
				else {
					/*
					 * CORREOS SECUNDARIOS:
					 * 
					 * Si el Correo Electronico procesado es Secundario, se procede a realizar la asociación del mismo a la Persona y a la Solicitud.
					 * En el caso de que el Correo Electronico no este asociado a la Persona, se realiza la 
					 * asociación respectiva, verificando previamente el estado del Correo Electronico, que en el caso de estar Inactivo
					 * se realiza la Activación antes de proceder con la asociación mencionada anteriormente. Por ultimo se realiza 
					 * la asociación a la Solicitud (Persona Solicitud)
					 * 
					 */
					
					// Verificar si Correo Electronico ya se encuentra asociado a PersonaSolicitud
					Boolean asociadoRPC = false;
					
					for(CorreoElectronico correoElectronicoRPC : correosElectronicosPersonaSolicitud) {
						if(correoElectronicoRPC.getDireccion().equals(correoElectronico.getDireccion())) {
							asociadoRPC = true;
							break;
						}
					}
					
					// Si el Correo Electronico no se encuentra asociado, se verifica la dirección de correo anterior, con el fin de
					// determinar si el Correo Electronico debe ser creado o actualizar un Correo Electronico anterior, lo cual solamente ocurrirá si
					// la dirección de correo esta asociada unicamente a la Persona Solicitud enviada por parámetro 
					if(!asociadoRPC) {
						
						// Verificar si existe una dirección de correo anterior 
						if(correoElectronico.getDireccionAnterior() != null && !correoElectronico.getDireccionAnterior().isEmpty()) {
							// Obtener Correo Electronico actual asociado a RolPersonaCaso a partir de la dirección de correo anterior
							CorreoElectronico correoElectronicoActual = null;
							
							for(CorreoElectronico correoElectronicoRPC : correosElectronicosPersonaSolicitud) {
								if(correoElectronicoRPC.getDireccion().equals(correoElectronico.getDireccionAnterior())) {
									correoElectronicoActual = correoElectronicoRPC;
									break;
								}
							}
							
							// Si se encuentra un Correo Electronico Actual, se procede a verificar si esta unicamente asociado a la 
							// Persona Solicitud enviada por parámetro, para posteriormente realizar la actualización de la dirección de correo
							if(correoElectronicoActual != null) {
								
								// Obtener Personas Solicitud a partir del Correo Electronico Actual
								List<PersonaSolicitud> personasSolicitudAsociados = consultarPersonasSolicitudPorCorreo(correoElectronicoActual.getIdCorreo());
								
								// Verificar si el Correo Electronico Actual se encuentra unicamente vinculado a la Persona Solicitud enviada por parámetro,
								// si es asi se procede a actualizar la dirección de correo, en caso contrario se procede con la creación del Correo Electronico
								// asociado a la Persona y a la Solicitud
								if(personasSolicitudAsociados.size() == 1) {
									
									if(correoElectronico.getDireccion() != null && !correoElectronico.getDireccion().isEmpty()) {
										// Actualizar Entidad
										correoElectronicoActual.setDireccion(correoElectronico.getDireccion());
										correoElectronicoActual.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
										correoElectronicoActual.setTipo(correoElectronico.getTipo());
										
										// Persistencia
										correoElectronicoFacade.actualizar(correoElectronicoActual);
									}
									else {
										// Si la dirección de correo es nula o vacia, se asume como eliminación
										// Teniendo en cuenta que la dirección de correo anterior solo se encuentra asociado a esta solicitud, se procede
										// también con la eliminación del Correo Electronico
										
										// Eliminar asociación Correo Electronico anterior
										CorreoElectronicoPersonaSolicitudPK correoElectronicoPersonaSolicitudPK = new CorreoElectronicoPersonaSolicitudPK();
										correoElectronicoPersonaSolicitudPK.setIdSolicitudServicio(personasSolicitudAsociados.get(0).getPersonaSolicitudPK().getIdSolicitudServicio());
										correoElectronicoPersonaSolicitudPK.setIdPersona(personasSolicitudAsociados.get(0).getPersonaSolicitudPK().getIdPersona());
										correoElectronicoPersonaSolicitudPK.setIdRol(personasSolicitudAsociados.get(0).getPersonaSolicitudPK().getIdRol());
										correoElectronicoPersonaSolicitudPK.setIdCorreo(correoElectronicoActual.getIdCorreo());									
										
										manejadorCorreoElectronicoPersonaSolicitud.eliminarPorId(correoElectronicoPersonaSolicitudPK);
										
										// Eliminar Correo Electronico
										correoElectronicoFacade.eliminarPorId(correoElectronicoActual.getIdCorreo());								
									}
								}
								else {
									// Eliminar asociación Correo Electronico anterior
									CorreoElectronicoPersonaSolicitudPK correoElectronicoPersonaSolicitudPK = new CorreoElectronicoPersonaSolicitudPK();
									correoElectronicoPersonaSolicitudPK.setIdSolicitudServicio(personaSolicitud.getPersonaSolicitudPK().getIdSolicitudServicio());
									correoElectronicoPersonaSolicitudPK.setIdPersona(personaSolicitud.getPersonaSolicitudPK().getIdPersona());
									correoElectronicoPersonaSolicitudPK.setIdRol(personaSolicitud.getPersonaSolicitudPK().getIdRol());
									correoElectronicoPersonaSolicitudPK.setIdCorreo(correoElectronicoActual.getIdCorreo());									
									
									manejadorCorreoElectronicoPersonaSolicitud.eliminarPorId(correoElectronicoPersonaSolicitudPK);										
									
									// Solo crear la nueva asociación siempre y cuando el Correo Electronico sea diferente de vacio
									if(correoElectronico.getDireccion() != null && !correoElectronico.getDireccion().isEmpty()) {
										// Crear Correo Electronico asociado a la Persona y a la Solicitud
										crearCorreoElectronicoPersonaSolicitud(personaSolicitud, correoElectronico.getDireccion(), correoElectronico.getTipo());
									}
								}
							}
							else{
								// Ya que no existe el Correo Electronico anterior, se procede a crear el Correo Electronico
								// asociado a la Persona y a la Solicitud, a partir de la dirección de correo enviada por parámetro
								crearCorreoElectronicoPersonaSolicitud(personaSolicitud, correoElectronico.getDireccion(), correoElectronico.getTipo());
							}						
						}
						else {
							// Ya que no se encuentra asociado, se procede a crear el Correo Electronico asociado a la Persona y a la Solicitud
							crearCorreoElectronicoPersonaSolicitud(personaSolicitud, correoElectronico.getDireccion(), correoElectronico.getTipo());
						}
					}				
				}
			} else {
				// TODO Eliminar el correo electrónico lógicamente
			}
		}
	}

	// protected region metodos adicionales end
	
}
