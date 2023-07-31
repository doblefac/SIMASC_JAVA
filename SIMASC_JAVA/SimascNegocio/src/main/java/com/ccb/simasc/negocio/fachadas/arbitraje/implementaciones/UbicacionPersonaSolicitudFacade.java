package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorUbicacionPersonaSolicitud;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUbicacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUbicacionPersonaSolicitudFacade;
import com.ccb.simasc.transversal.dto.PersonaSolicitudDTO;
import com.ccb.simasc.transversal.dto.UbicacionDTO;
import com.ccb.simasc.transversal.dto.UbicacionPersonaSolicitudDTO;
import com.ccb.simasc.transversal.entidades.PersonaSolicitud;
import com.ccb.simasc.transversal.entidades.PersonaSolicitudPK;
import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.entidades.UbicacionPersonaSolicitud;
import com.ccb.simasc.transversal.entidades.UbicacionPersonaSolicitudPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilMascaraTexto;


// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link UbicacionPersonaSolicitud}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class UbicacionPersonaSolicitudFacade extends AccesoFacade<UbicacionPersonaSolicitud, UbicacionPersonaSolicitudPK, UbicacionPersonaSolicitudDTO> implements IUbicacionPersonaSolicitudFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorUbicacionPersonaSolicitud manejadorUbicacionPersonaSolicitud;
	
	@EJB
	private IUbicacionFacade ubicacionFacade;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorUbicacionPersonaSolicitud;
	}

	@Override
	public UbicacionPersonaSolicitudDTO transformarSinDependencias(UbicacionPersonaSolicitud obj) {
		UbicacionPersonaSolicitudDTO dto = new UbicacionPersonaSolicitudDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public UbicacionPersonaSolicitudDTO transformarConDependencias(UbicacionPersonaSolicitud obj) {
		UbicacionPersonaSolicitudDTO dto = new UbicacionPersonaSolicitudDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public UbicacionPersonaSolicitud transformarEntidadConDependencias(UbicacionPersonaSolicitud obj) {
		UbicacionPersonaSolicitud dto = new UbicacionPersonaSolicitud();
		dto = new UbicacionPersonaSolicitudDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public UbicacionPersonaSolicitud transformarEntidadSinDependencias(UbicacionPersonaSolicitud obj) {
		UbicacionPersonaSolicitud dto = new UbicacionPersonaSolicitud();
		dto = new UbicacionPersonaSolicitudDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ubicacion> consultarUbicacionesPorPersonaSolicitud(PersonaSolicitudPK personaSolicitudPk) {
		return (List<Ubicacion>) new UbicacionDTO()
				.transformarColeccionEntidadesSinDependencias(manejadorUbicacionPersonaSolicitud.consultarUbicacionesPorPersonaSolicitud(personaSolicitudPk));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonaSolicitud> consultarPersonasSolicitudPorUbicacion(Long idUbicacion) {
		return (List<PersonaSolicitud>) new PersonaSolicitudDTO().transformarColeccionEntidadesSinDependencias(manejadorUbicacionPersonaSolicitud.consultarPersonasSolicitudPorUbicacion(idUbicacion));
	}
	
	@Override
	public UbicacionPersonaSolicitud crearUbicacionPersonaSolicitud(PersonaSolicitud personaSolicitud, Ubicacion ubicacion) {
			
		// Llave Primaria
		UbicacionPersonaSolicitudPK ubicacionPersonaSolicitudPk = new UbicacionPersonaSolicitudPK();
		ubicacionPersonaSolicitudPk.setIdSolicitudServicio(personaSolicitud.getPersonaSolicitudPK().getIdSolicitudServicio());
		ubicacionPersonaSolicitudPk.setIdPersona(personaSolicitud.getPersonaSolicitudPK().getIdPersona());
		ubicacionPersonaSolicitudPk.setIdRol(personaSolicitud.getPersonaSolicitudPK().getIdRol());		
		ubicacionPersonaSolicitudPk.setIdUbicacion(ubicacion.getIdUbicacion());
		
		// Crear Entidad
		UbicacionPersonaSolicitud ubicacionPersonaSolicitud = new UbicacionPersonaSolicitud();
		ubicacionPersonaSolicitud.setUbicacionPersonaSolicitudPK(ubicacionPersonaSolicitudPk);
		ubicacionPersonaSolicitud.setUbicacion(ubicacion);
		ubicacionPersonaSolicitud.setPersonaSolicitud(personaSolicitud);		
		ubicacionPersonaSolicitud.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		// Persistencia
		manejadorUbicacionPersonaSolicitud.crear(ubicacionPersonaSolicitud);
		
		return ubicacionPersonaSolicitud;
	}

	@Override
	public UbicacionPersonaSolicitud crearUbicacionPersonaSolicitud(PersonaSolicitud personaSolicitud, UbicacionDTO ubicacionDTO) {
		
		Ubicacion ubicacionPersona = null;
		
		// Consultar Ubicaciones asociadas a la Persona
		List<Ubicacion> ubicacionesList = ubicacionFacade.consultarUbicacionesPorPersona(personaSolicitud.getPersonaSolicitudPK().getIdPersona(), true);
		
		// Obtener identificador de Ubicación a partir de la dirección (Si esta existe)
		for(Ubicacion ubicacion : ubicacionesList) {
			if(ubicacion.getDireccion().equals(ubicacionDTO.getDireccion()) && ubicacion.getLatitud() == ubicacionDTO.getLatitud()
					&& ubicacion.getLongitud() == ubicacionDTO.getLongitud() && ubicacion.getIdZonaGeografica().equals(ubicacionDTO.getIdZonaGeografica())) {				
				ubicacionPersona = ubicacion;
				break;
			}
		}
		
		// Crear Ubicación asociada a la Persona, si esta no existe
		if(ubicacionPersona == null) {
			// Crear Entidad
			ubicacionPersona = new Ubicacion();
			ubicacionPersona.setDireccion(ubicacionDTO.getDireccion());
			ubicacionPersona.setIdZonaGeografica(ubicacionDTO.getIdZonaGeografica());
			ubicacionPersona.setLatitud(ubicacionDTO.getLatitud());
			ubicacionPersona.setLongitud(ubicacionDTO.getLongitud());			
			ubicacionPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			ubicacionPersona.setIdPersona(personaSolicitud.getPersonaSolicitudPK().getIdPersona());
			ubicacionPersona.setBarrio(ubicacionDTO.getBarrio());
			ubicacionPersona.setLocalidad(ubicacionDTO.getLocalidad());
			ubicacionPersona.setTipo(UtilDominios.TIPO_UBICACION_SECUNDARIA);			
			
			// Persistencia
			ubicacionFacade.crear(ubicacionPersona);
		}
		else {
			// Si el Ubicación existe y se encuentra Inactivo, se realiza la respectiva Activación
			if(ubicacionPersona.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_INACTIVO)) {
				// Establecer Estado Activo
				ubicacionPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
				
				// Persistencia
				ubicacionFacade.actualizar(ubicacionPersona);
			}
		}
		
		// Crear asociación del Correo Electronico con Persona Solicitud
		return crearUbicacionPersonaSolicitud(personaSolicitud, ubicacionPersona);	
	}

	@Override
	public UbicacionDTO asociarUbicacionesPersonaSolicitud(List<UbicacionDTO> ubicaciones, PersonaSolicitud personaSolicitud) {
		UbicacionDTO ubicacionDTO = new UbicacionDTO();
		// Validar Parámetros
		if(ubicaciones == null || personaSolicitud == null) return ubicacionDTO;
		if(ubicaciones.size() == 0) return ubicacionDTO;		
		
		// Obtener Ubicaciones asociadas a la PersonaSolicitud
		List<Ubicacion> ubicacionesPersonaSolicitud = consultarUbicacionesPorPersonaSolicitud(personaSolicitud.getPersonaSolicitudPK());
		
		// Procesar Ubicaciones enviadas como parámetro
		for(UbicacionDTO ubicacion : ubicaciones) {
			
			if(ubicacion.getDireccion() != null && !ubicacion.getDireccion().isEmpty() && ubicacion.getIdZonaGeografica() != null
					&& !ubicacion.getIdZonaGeografica().isEmpty() && ubicacion.getTipo() != null && !ubicacion.getTipo().isEmpty()
					&& !UtilMascaraTexto.hasOnlyDots(ubicacion.getDireccion())) {
			
				/*
				 * UBICACION PRINCIPAL:
				 * 
				 * Si la Ubicación procesado es Principal, se actualiza la que tiene asociada la Persona,
				 * en caso de que no tenga asociado ninguno, se procede a realizar la creación respectiva  
				 */
				if(ubicacion.getTipo().equals(UtilDominios.TIPO_UBICACION_PRINCIPAL) && !UtilMascaraTexto.hasOnlyDots(ubicacion.getDireccion())) {
					ubicacionDTO.setIdUbicacion(ubicacionFacade.gestionarDireccionPrincipal(personaSolicitud.getPersonaSolicitudPK().getIdPersona(), ubicacion));				
				} else {
					/*
					 * UBICACIONES SECUNDARIAS:
					 * 
					 * Si la Ubicacion procesada es Secundaria, se procede a realizar la asociación de la misma a la Persona y a la Solicitud.
					 * En el caso de que la Ubicación no este asociada a la Persona, se realiza la 
					 * asociación respectiva, verificando previamente el estado de la Ubicación, que en el caso de estar Inactiva
					 * se realiza la Activación antes de proceder con la asociación mencionada anteriormente. Por ultimo se realiza 
					 * la asociación a la Solicitud (Persona Solicitud)
					 * 
					 */
					
					// Verificar si Ubicación ya se encuentra asociada a la PersonaSolicitud
					Boolean asociadoPS = false;
					
					for(Ubicacion ubicacionPS : ubicacionesPersonaSolicitud) {
						if(ubicacionPS.getDireccion().equals(ubicacion.getDireccion()) && ubicacionPS.getLatitud() == ubicacion.getLatitud()
								&& ubicacionPS.getLongitud() == ubicacion.getLongitud() && ubicacionPS.getIdZonaGeografica().equals(ubicacion.getIdZonaGeografica())) {
							asociadoPS = true;
							break;
						}
					}
					
					// Si la Ubicación no se encuentra asociada, se verifica la dirección de correo anterior, con el fin de
					// determinar si la Ubicación debe ser creada o actualizar una Ubicación anterior, lo cual solamente ocurrirá si
					// la ubicación esta asociada unicamente a la Persona Solicitud enviado por parámetro 
					if(!asociadoPS) {
						
						// Verificar si la ubicación ya habia sido registrada 
						if(ubicacion.getIdUbicacion() != null && !UtilMascaraTexto.hasOnlyDots(ubicacion.getDireccion())) {
							// Obtener Ubicacion actual asociada a PersonaSolicitud
							Ubicacion ubicacionActual = ubicacionFacade.buscar(ubicacion.getIdUbicacion());
							
							// Si se encuentra una Ubicacion Actual, se procede a verificar si es diferente a la enviada por parámetro y si esta unicamente
							// asociada a la Persona Solicitud enviada por parámetro, para posteriormente realizar la actualización de datos
							if(ubicacionActual != null) {
								
								// Verificar si la ubicación actual es diferente a la enviada por parámetro
								if(!ubicacionActual.getDireccion().equals(ubicacion.getDireccion()) || ubicacionActual.getLatitud() != ubicacion.getLatitud()
										|| ubicacionActual.getLongitud() != ubicacion.getLongitud() || !ubicacionActual.getIdZonaGeografica().equals(ubicacion.getIdZonaGeografica())) {
									
									// Obtener Personas Solicitud a partir de la Ubicacion Actual
									List<PersonaSolicitud> personasSolicitudAsociadas = consultarPersonasSolicitudPorUbicacion(ubicacionActual.getIdUbicacion());
									
									// Verificar si la Ubicacion Actual se encuentra unicamente vinculado a la Persona Solicitud enviada por parámetro,
									// si es asi se procede a actualizar los datos asociados a dicha ubicación, en caso contrario se procede con la creación de una nueva Ubicación
									// asociada a la Persona y a la Solicitud
									if(personasSolicitudAsociadas.size() == 1) {
										
										if(ubicacion.getDireccion() != null && !ubicacion.getDireccion().isEmpty()) {
											// Actualizar Entidad
											ubicacionActual.setDireccion(ubicacion.getDireccion());
											ubicacionActual.setLatitud(ubicacion.getLatitud());
											ubicacionActual.setLongitud(ubicacion.getLongitud());
											ubicacionActual.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
											ubicacionActual.setIdZonaGeografica(ubicacion.getIdZonaGeografica());
											ubicacionActual.setBarrio(ubicacion.getBarrio());
											ubicacionActual.setLocalidad(ubicacion.getLocalidad());
											
											// Persistencia
											ubicacionFacade.actualizar(ubicacionActual);
											ubicacionDTO.setIdUbicacion(ubicacionActual.getIdUbicacion());
										}
										else {
											// Si la dirección de la ubicación es nula o vacia, se asume como eliminación
											// Teniendo en cuenta que la ubicación anterior solo se encuentra asociada a este caso, se procede
											// también con la eliminación de la Ubicación
											
											// Eliminar asociación Ubicación anterior
											UbicacionPersonaSolicitudPK ubicacionPersonaSolicitudPK = new UbicacionPersonaSolicitudPK();
											ubicacionPersonaSolicitudPK.setIdSolicitudServicio(personasSolicitudAsociadas.get(0).getPersonaSolicitudPK().getIdSolicitudServicio());
											ubicacionPersonaSolicitudPK.setIdPersona(personasSolicitudAsociadas.get(0).getPersonaSolicitudPK().getIdPersona());
											ubicacionPersonaSolicitudPK.setIdRol(personasSolicitudAsociadas.get(0).getPersonaSolicitudPK().getIdRol());
											ubicacionPersonaSolicitudPK.setIdUbicacion(ubicacionActual.getIdUbicacion());							
											
											manejadorUbicacionPersonaSolicitud.eliminarPorId(ubicacionPersonaSolicitudPK);
											
											// Eliminar Ubicación
											ubicacionFacade.eliminarPorId(ubicacionActual.getIdUbicacion());								
										}
									}
									else {
										// Eliminar asociación Ubicación anterior
										UbicacionPersonaSolicitudPK ubicacionPersonaSolicitudPK = new UbicacionPersonaSolicitudPK();
										ubicacionPersonaSolicitudPK.setIdSolicitudServicio(personaSolicitud.getPersonaSolicitudPK().getIdSolicitudServicio());
										ubicacionPersonaSolicitudPK.setIdPersona(personaSolicitud.getPersonaSolicitudPK().getIdPersona());
										ubicacionPersonaSolicitudPK.setIdRol(personaSolicitud.getPersonaSolicitudPK().getIdRol());
										ubicacionPersonaSolicitudPK.setIdUbicacion(ubicacionActual.getIdUbicacion());
										
										manejadorUbicacionPersonaSolicitud.eliminarPorId(ubicacionPersonaSolicitudPK);										
										
										// Solo crear la nueva asociación siempre y cuando la dirección sea diferente de vacio
										if(ubicacion.getDireccion() != null && !ubicacion.getDireccion().isEmpty()) {
											// Crear Ubicación asociada a la Persona y a la Solicitud
											ubicacionDTO.setIdUbicacion(crearUbicacionPersonaSolicitud(personaSolicitud, ubicacion)
													.getUbicacionPersonaSolicitudPK().getIdUbicacion());
										}
									}
								}
							}
							else{
								// Ya que no existe la ubicación anterior, se procede a crear la Ubicación
								// asociada a la Persona y a la Solicitud, a partir de los datos enviados por parámetro
								ubicacionDTO.setIdUbicacion(crearUbicacionPersonaSolicitud(personaSolicitud,ubicacion)
												.getUbicacionPersonaSolicitudPK().getIdUbicacion());
								
							}						
						}
						else if(!UtilMascaraTexto.hasOnlyDots(ubicacion.getDireccion())){
							// Ya que no se encuentra asociado, se procede a crear la ubicación asociada a la Persona y a la Solicitud
							ubicacionDTO.setIdUbicacion(crearUbicacionPersonaSolicitud(personaSolicitud, ubicacion)
											.getUbicacionPersonaSolicitudPK().getIdUbicacion());
						}
					}				
				}
			}
		}
		
		return ubicacionDTO;
	}

	// protected region metodos adicionales end
	
}
