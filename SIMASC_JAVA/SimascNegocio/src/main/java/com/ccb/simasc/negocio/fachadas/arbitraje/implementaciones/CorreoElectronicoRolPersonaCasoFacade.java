package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
//protected region imports fachada on begin
//Escriba en esta seccion sus modificaciones

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronicoRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ICorreoElectronicoRolPersonaCasoFacade;
import com.ccb.simasc.transversal.dto.CorreoElectronicoDTO;
import com.ccb.simasc.transversal.dto.CorreoElectronicoRolPersonaCasoDTO;
import com.ccb.simasc.transversal.dto.RolPersonaCasoDTO;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.CorreoElectronicoRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.CorreoElectronicoRolPersonaCasoPK;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilMascaraTexto;

//protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link CorreoElectronicoRolPersonaCaso}
 * @author ftovar
 *
 */
@Stateless
@LocalBean
public class CorreoElectronicoRolPersonaCasoFacade extends AccesoFacade<CorreoElectronicoRolPersonaCaso,CorreoElectronicoRolPersonaCasoPK,CorreoElectronicoRolPersonaCasoDTO>
	implements ICorreoElectronicoRolPersonaCasoFacade {
	
	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada
	
	@EJB
	private ICorreoElectronicoFacade correoElectronicoFacade;
	
	@EJB
	private ManejadorCorreoElectronicoRolPersonaCaso manejadorCorreoElectronicoRolPersonaCaso;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorCorreoElectronicoRolPersonaCaso;
	}

	@Override
	public CorreoElectronicoRolPersonaCasoDTO transformarSinDependencias(CorreoElectronicoRolPersonaCaso obj) {
		CorreoElectronicoRolPersonaCasoDTO dto = new CorreoElectronicoRolPersonaCasoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public CorreoElectronicoRolPersonaCasoDTO transformarConDependencias(CorreoElectronicoRolPersonaCaso obj) {
		CorreoElectronicoRolPersonaCasoDTO dto = new CorreoElectronicoRolPersonaCasoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public CorreoElectronicoRolPersonaCaso transformarEntidadConDependencias(CorreoElectronicoRolPersonaCaso obj) {
		CorreoElectronicoRolPersonaCaso dto = new CorreoElectronicoRolPersonaCaso();
		dto = new CorreoElectronicoRolPersonaCasoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public CorreoElectronicoRolPersonaCaso transformarEntidadSinDependencias(CorreoElectronicoRolPersonaCaso obj) {
		CorreoElectronicoRolPersonaCaso dto = new CorreoElectronicoRolPersonaCaso();
		dto = new CorreoElectronicoRolPersonaCasoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	
	@Override
	public List<CorreoElectronico> consultarCorreosPorRolPersonaCaso(RolPersonaCasoPK rolPersonaCasoPk) {
		return manejadorCorreoElectronicoRolPersonaCaso.consultarCorreosPorRolPersonaCaso(rolPersonaCasoPk);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RolPersonaCaso> consultarRolesPersonaCasoPorCorreo(Long idCorreo) {
		return (List<RolPersonaCaso>) new RolPersonaCasoDTO().transformarColeccionEntidadesSinDependencias(manejadorCorreoElectronicoRolPersonaCaso.consultarRolesPersonaCasoPorCorreo(idCorreo));
	}
	
	@Override
	public CorreoElectronicoRolPersonaCaso crearCorreoElectronicoRolPersonaCaso(RolPersonaCaso rolPersonaCaso, CorreoElectronico correoElectronico) {
			
		// Llave Primaria
		CorreoElectronicoRolPersonaCasoPK correoElectronicoRolPersonaCasoPk = new CorreoElectronicoRolPersonaCasoPK();
		correoElectronicoRolPersonaCasoPk.setIdCaso(rolPersonaCaso.getRolPersonaCasoPK().getIdCaso());
		correoElectronicoRolPersonaCasoPk.setIdPersona(rolPersonaCaso.getRolPersonaCasoPK().getIdPersona());
		correoElectronicoRolPersonaCasoPk.setIdRol(rolPersonaCaso.getRolPersonaCasoPK().getIdRol());		
		correoElectronicoRolPersonaCasoPk.setIdCorreo(correoElectronico.getIdCorreo());
		
		// Crear Entidad
		CorreoElectronicoRolPersonaCaso correoElectronicoRolPersonaCaso = new CorreoElectronicoRolPersonaCaso();
		correoElectronicoRolPersonaCaso.setCorreoElectronicoRolPersonaCasoPK(correoElectronicoRolPersonaCasoPk);
		correoElectronicoRolPersonaCaso.setCorreoElectronico(correoElectronico);
		correoElectronicoRolPersonaCaso.setRolPersonaCaso(rolPersonaCaso);		
		correoElectronicoRolPersonaCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		
		// Persistencia
		manejadorCorreoElectronicoRolPersonaCaso.crear(correoElectronicoRolPersonaCaso);
		
		return correoElectronicoRolPersonaCaso;
	}

	@Override
	public CorreoElectronicoRolPersonaCaso crearCorreoElectronicoRolPersonaCaso(RolPersonaCaso rolPersonaCaso, String direccionCorreoElectronico, String tipoCorreo) {
		CorreoElectronico correoElectronicoPersona = null;
		
		// Consultar Correos Electronicos asociados a la Persona
		List<CorreoElectronico> correosElectronicosList = correoElectronicoFacade.consultaCorreosPersona(rolPersonaCaso.getRolPersonaCasoPK().getIdPersona(), true);
		
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
			correoElectronicoPersona.setIdPersona(rolPersonaCaso.getRolPersonaCasoPK().getIdPersona());
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
		return crearCorreoElectronicoRolPersonaCaso(rolPersonaCaso,correoElectronicoPersona);	
	}

	@Override
	public void asociarCorreosElectronicosRolPersonaCaso(List<CorreoElectronicoDTO> correosElectronicos, RolPersonaCaso rolPersonaCaso) {
		// Validar Parámetros
		if(correosElectronicos == null || rolPersonaCaso == null) return;
		if(correosElectronicos.isEmpty()) return;		
		// Obtener Correos Electronicos asociados al RolPersonaCaso
		List<CorreoElectronico> correosElectronicosRolPersonaCaso = consultarCorreosPorRolPersonaCaso(rolPersonaCaso.getRolPersonaCasoPK());
		
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
					List<CorreoElectronico> correosElectronicosPersona = correoElectronicoFacade.consultaCorreosPersona(rolPersonaCaso.getRolPersonaCasoPK().getIdPersona(), true);
					CorreoElectronico correoElectronicoPrincipalActual = null;
					
					for(CorreoElectronico correoElectronicoPersona : correosElectronicosPersona) {
						if (correoElectronicoPersona.getTipo()
								.equals(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL)
								&& UtilDominios.ESTADO_REGISTRO_ACTIVO
										.equals(correoElectronicoPersona.getEstadoRegistro())) {
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
						correoElectronicoPrincipalActual.setIdPersona(rolPersonaCaso.getRolPersonaCasoPK().getIdPersona());
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
							
							// Persistencia
							correoElectronicoFacade.actualizar(correoElectronicoPrincipalActual);
						}
					}				
				}
				else {
					/*
					 * CORREOS SECUNDARIOS:
					 * 
					 * Si el Correo Electronico procesado es Secundario, se procede a realizar la asociación del mismo a la Persona y al Caso.
					 * En el caso de que el Correo Electronico no este asociado a la Persona, se realiza la 
					 * asociación respectiva, verificando previamente el estado del Correo Electronico, que en el caso de estar Inactivo
					 * se realiza la Activación antes de proceder con la asociación mencionada anteriormente. Por ultimo se realiza 
					 * la asociación al Caso (Rol Persona Caso)
					 * 
					 */
					
					// Verificar si Correo Electronico ya se encuentra asociado a RolPersonaCaso
					Boolean asociadoRPC = false;
					
					for(CorreoElectronico correoElectronicoRPC : correosElectronicosRolPersonaCaso) {
						if(correoElectronicoRPC.getDireccion().equals(correoElectronico.getDireccion())) {
							asociadoRPC = true;
							break;
						}
					}
					
					// Si el Correo Electronico no se encuentra asociado, se verifica la dirección de correo anterior, con el fin de
					// determinar si el Correo Electronico debe ser creado o actualizar un Correo Electronico anterior, lo cual solamente ocurrirá si
					// la dirección de correo esta asociada unicamente al Rol Persona Caso enviado por parámetro 
					if(!asociadoRPC) {
						
						// Verificar si existe una dirección de correo anterior 
						if(correoElectronico.getDireccionAnterior() != null && !correoElectronico.getDireccionAnterior().isEmpty()) {
							// Obtener Correo Electronico actual asociado a RolPersonaCaso a partir de la dirección de correo anterior
							CorreoElectronico correoElectronicoActual = null;
							
							for(CorreoElectronico correoElectronicoRPC : correosElectronicosRolPersonaCaso) {
								if(correoElectronicoRPC.getDireccion().equals(correoElectronico.getDireccionAnterior())) {
									correoElectronicoActual = correoElectronicoRPC;
									break;
								}
							}
							
							// Si se encuentra un Correo Electronico Actual, se procede a verificar si esta unicamente asociado al 
							// Rol Persona Caso enviado por parámetro, para posteriormente realizar la actualización de la dirección de correo
							if(correoElectronicoActual != null) {
								
								// Obtener Roles Persona Caso a partir del Correo Electronico Actual
								List<RolPersonaCaso> rolesPersonaCasoAsociados = consultarRolesPersonaCasoPorCorreo(correoElectronicoActual.getIdCorreo());
								
								// Verificar si el Correo Electronico Actual se encuentra unicamente vinculado al Rol Persona Caso enviado por parámetro,
								// si es asi se procede a actualizar la dirección de correo, en caso contrario se procede con la creación del Correo Electronico
								// asociado a la Persona y al Caso
								if(rolesPersonaCasoAsociados.size() == 1) {
									
									if(correoElectronico.getDireccion() != null && !correoElectronico.getDireccion().isEmpty()) {
										// Actualizar Entidad
										correoElectronicoActual.setDireccion(correoElectronico.getDireccion());
										correoElectronicoActual.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
										
										// Persistencia
										correoElectronicoFacade.actualizar(correoElectronicoActual);
									}
									else {
										// Si la dirección de correo es nula o vacia, se asume como eliminación
										// Teniendo en cuenta que la dirección de correo anterior solo se encuentra asociado a este caso, se procede
										// también con la eliminación del Correo Electronico
										
										// Eliminar asociación Correo Electronico anterior
										CorreoElectronicoRolPersonaCasoPK correoElectronicoRolPersonaCasoPK = new CorreoElectronicoRolPersonaCasoPK();
										correoElectronicoRolPersonaCasoPK.setIdCaso(rolesPersonaCasoAsociados.get(0).getRolPersonaCasoPK().getIdCaso());
										correoElectronicoRolPersonaCasoPK.setIdPersona(rolesPersonaCasoAsociados.get(0).getRolPersonaCasoPK().getIdPersona());
										correoElectronicoRolPersonaCasoPK.setIdRol(rolesPersonaCasoAsociados.get(0).getRolPersonaCasoPK().getIdRol());
										correoElectronicoRolPersonaCasoPK.setIdCorreo(correoElectronicoActual.getIdCorreo());									
										
										manejadorCorreoElectronicoRolPersonaCaso.eliminarPorId(correoElectronicoRolPersonaCasoPK);
										
										// Eliminar Correo Electronico
										correoElectronicoFacade.eliminarPorId(correoElectronicoActual.getIdCorreo());								
									}
								}
								else {
									// Eliminar asociación Correo Electronico anterior
									CorreoElectronicoRolPersonaCasoPK correoElectronicoRolPersonaCasoPK = new CorreoElectronicoRolPersonaCasoPK();
									correoElectronicoRolPersonaCasoPK.setIdCaso(rolPersonaCaso.getRolPersonaCasoPK().getIdCaso());
									correoElectronicoRolPersonaCasoPK.setIdPersona(rolPersonaCaso.getRolPersonaCasoPK().getIdPersona());
									correoElectronicoRolPersonaCasoPK.setIdRol(rolPersonaCaso.getRolPersonaCasoPK().getIdRol());
									correoElectronicoRolPersonaCasoPK.setIdCorreo(correoElectronicoActual.getIdCorreo());									
									
									manejadorCorreoElectronicoRolPersonaCaso.eliminarPorId(correoElectronicoRolPersonaCasoPK);										
									
									// Solo crear la nueva asociación siempre y cuando el Correo Electronico sea diferente de vacio
									if(correoElectronico.getDireccion() != null && !correoElectronico.getDireccion().isEmpty()) {
										// Crear Correo Electronico asociado a la Persona y al Caso
										crearCorreoElectronicoRolPersonaCaso(rolPersonaCaso, correoElectronico.getDireccion(), correoElectronico.getTipo());
									}
								}
							}
							else{
								// Ya que no existe el Correo Electronico anterior, se procede a crear el Correo Electronico
								// asociado a la Persona y al Caso, a partir de la dirección de correo enviada por parámetro
								crearCorreoElectronicoRolPersonaCaso(rolPersonaCaso, correoElectronico.getDireccion(), correoElectronico.getTipo());
							}						
						}
						else {
							// Ya que no se encuentra asociado, se procede a crear el Correo Electronico asociado a la Persona y al Caso
							crearCorreoElectronicoRolPersonaCaso(rolPersonaCaso, correoElectronico.getDireccion(), correoElectronico.getTipo());
						}
					}				
				}
			// Si la dirección de correo electrónico es vacia se procede a
			// eliminarlo
			} else if (correoElectronico.getDireccionAnterior() != null
					&& !correoElectronico.getDireccionAnterior().isEmpty()) {
				eliminarCorreoElectronicoRolPersonaCaso(rolPersonaCaso, correosElectronicosRolPersonaCaso, correoElectronico);
			}
		}
	}

	/**
	 * Método encargadp de la eliminación de correos electrónicos teniendo en cuenta si es principal u otros 
	 * 
	 * @param rolPersonaCaso
	 * @param correosElectronicosRolPersonaCaso
	 * @param correoElectronico
	 */
	private void eliminarCorreoElectronicoRolPersonaCaso(RolPersonaCaso rolPersonaCaso,
			List<CorreoElectronico> correosElectronicosRolPersonaCaso, CorreoElectronicoDTO correoElectronico) {
		CorreoElectronico correoElectronicoEliminar = null;

		// Si el correo electrónico es principal solo se elimina el correo
		// electónico
		if (correoElectronico.getTipo() != null && !correoElectronico.getTipo().isEmpty()
				&& correoElectronico.getTipo().equals(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL)) {

			List<CorreoElectronico> correosElectronicosPersona = correoElectronicoFacade
					.consultaCorreosPersona(rolPersonaCaso.getRolPersonaCasoPK().getIdPersona(), true);

			for (CorreoElectronico correoElectronicoPersona : correosElectronicosPersona) {
				if (correoElectronicoPersona.getDireccion().equals(correoElectronico.getDireccionAnterior())) {
					correoElectronicoEliminar = correoElectronicoPersona;
					break;
				}
			}

			if (correoElectronicoEliminar != null) {
				// Eliminar Correo Electrónico
				correoElectronicoFacade.eliminarPorId(correoElectronicoEliminar.getIdCorreo());
			}
			// Si el correo electrónico es secundario o terciario se elimina de
			// la asociación al caso y el correo electrónico
		} else {
			// Verificar si Correo Electrónico se encuentra asociado a
			// RolPersonaCaso
			Boolean correoElectronicoAsociadoACaso = false;

			for (CorreoElectronico correoElectronicoRPC : correosElectronicosRolPersonaCaso) {
				if (correoElectronicoRPC.getDireccion().equals(correoElectronico.getDireccionAnterior())) {
					correoElectronicoEliminar = correoElectronicoRPC;
					correoElectronicoAsociadoACaso = true;
				}
			}

			if (correoElectronicoAsociadoACaso) {
				CorreoElectronicoRolPersonaCasoPK correoElectronicoRolPersonaCasoPK = new CorreoElectronicoRolPersonaCasoPK();
				correoElectronicoRolPersonaCasoPK.setIdCaso(rolPersonaCaso.getRolPersonaCasoPK().getIdCaso());
				correoElectronicoRolPersonaCasoPK.setIdPersona(rolPersonaCaso.getRolPersonaCasoPK().getIdPersona());
				correoElectronicoRolPersonaCasoPK.setIdRol(rolPersonaCaso.getRolPersonaCasoPK().getIdRol());
				correoElectronicoRolPersonaCasoPK.setIdCorreo(correoElectronicoEliminar.getIdCorreo());
				
				CorreoElectronicoRolPersonaCaso correoElectronicoRolPersonaCaso = manejadorCorreoElectronicoRolPersonaCaso.buscar(correoElectronicoRolPersonaCasoPK);
				manejadorCorreoElectronicoRolPersonaCaso.eliminar(correoElectronicoRolPersonaCaso);

				if (correoElectronicoEliminar != null) {
					// Eliminar Correo Electrónico
					correoElectronicoFacade.eliminarPorId(correoElectronicoEliminar.getIdCorreo());
				}
			}

		}
	}
	
	// protected region metodos adicionales end
}
