package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta seccion sus modificaciones

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorPersonaSolicitud;
import com.ccb.simasc.integracion.manejadores.ManejadorRol;
import com.ccb.simasc.integracion.manejadores.ManejadorRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.ManejadorTelefono;
import com.ccb.simasc.integracion.manejadores.ManejadorUbicacionRolPersonaCaso;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUbicacionFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUbicacionPersonaSolicitudFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUbicacionRolPersonaCasoFacade;
import com.ccb.simasc.transversal.dto.RolPersonaCasoDTO;
import com.ccb.simasc.transversal.dto.TelefonoDTO;
import com.ccb.simasc.transversal.dto.UbicacionDTO;
import com.ccb.simasc.transversal.dto.UbicacionPersonaCasoDTO;
import com.ccb.simasc.transversal.dto.UbicacionRolPersonaCasoDTO;
import com.ccb.simasc.transversal.entidades.PersonaSolicitud;
import com.ccb.simasc.transversal.entidades.PersonaSolicitudPK;
import com.ccb.simasc.transversal.entidades.Rol;
import com.ccb.simasc.transversal.entidades.RolPersonaCaso;
import com.ccb.simasc.transversal.entidades.RolPersonaCasoPK;
import com.ccb.simasc.transversal.entidades.Telefono;
import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.entidades.UbicacionRolPersonaCaso;
import com.ccb.simasc.transversal.entidades.UbicacionRolPersonaCasoPK;
import com.ccb.simasc.transversal.utilidades.UtilDominios;
import com.ccb.simasc.transversal.utilidades.UtilMascaraTexto;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link UbicacionRolPersonaCaso}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class UbicacionRolPersonaCasoFacade
		extends AccesoFacade<UbicacionRolPersonaCaso, UbicacionRolPersonaCasoPK, UbicacionRolPersonaCasoDTO>
		implements IUbicacionRolPersonaCasoFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	@EJB
	private ManejadorUbicacionRolPersonaCaso manejadorUbicacionRolPersonaCaso;

	@EJB
	private ManejadorRol manejadorRol;

	@EJB
	private ManejadorRolPersonaCaso manejadorRolPersonaCaso;

	@EJB
	private ManejadorPersonaSolicitud manejadorPersonaSolicitud;

	@EJB
	private ManejadorTelefono manejadorTelefono;

	@EJB
	private IUbicacionFacade ubicacionFacade;

	@EJB
	private IUbicacionPersonaSolicitudFacade ubicacionPersonaSolicitudFacade;

	// protected region atributos end

	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorUbicacionRolPersonaCaso;
	}

	@Override
	public UbicacionRolPersonaCasoDTO transformarSinDependencias(UbicacionRolPersonaCaso obj) {
		UbicacionRolPersonaCasoDTO dto = new UbicacionRolPersonaCasoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public UbicacionRolPersonaCasoDTO transformarConDependencias(UbicacionRolPersonaCaso obj) {
		UbicacionRolPersonaCasoDTO dto = new UbicacionRolPersonaCasoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public UbicacionRolPersonaCaso transformarEntidadConDependencias(UbicacionRolPersonaCaso obj) {
		UbicacionRolPersonaCaso dto = new UbicacionRolPersonaCaso();
		dto = new UbicacionRolPersonaCasoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public UbicacionRolPersonaCaso transformarEntidadSinDependencias(UbicacionRolPersonaCaso obj) {
		UbicacionRolPersonaCaso dto = new UbicacionRolPersonaCaso();
		dto = new UbicacionRolPersonaCasoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones	
	@Override
	public List<Ubicacion> consultarUbicacionesPorRolPersonaCaso(RolPersonaCasoPK rolPersonaCasoPk) {
		return obteneUbicacionesRolPersonaCaso(rolPersonaCasoPk);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UbicacionDTO> consultarUbicacionesRolPersonaCaso(UbicacionPersonaCasoDTO ubicacionPersonaCasoDTO) {
		Rol rol = manejadorRol.consultarRolPorNombre(ubicacionPersonaCasoDTO.getNombreRol());
		if (ubicacionPersonaCasoDTO.getEsSolicitud()) {
			PersonaSolicitudPK personaSolicitudPK = new PersonaSolicitudPK(rol.getIdRol(),
					ubicacionPersonaCasoDTO.getIdPersona(), ubicacionPersonaCasoDTO.getIdCasoOSolicitud());
			return (List<UbicacionDTO>) new UbicacionDTO().transformarColeccionEntidadesSinDependencias(
					ubicacionPersonaSolicitudFacade.consultarUbicacionesPorPersonaSolicitud(personaSolicitudPK));
		} else {
			RolPersonaCasoPK rolPersonaCasoPk = new RolPersonaCasoPK();
			
			if(ubicacionPersonaCasoDTO.getIdCasoOSolicitud() == null) {
				List<Ubicacion> ubicaciones = ubicacionFacade.consultarUbicacionesPorPersona(ubicacionPersonaCasoDTO.getIdPersona(), false);
				return (List<UbicacionDTO>) new UbicacionDTO()
						.transformarColeccionEntidadesSinDependencias(ubicaciones);
			}else {
				rolPersonaCasoPk.setIdCaso(ubicacionPersonaCasoDTO.getIdCasoOSolicitud());
				rolPersonaCasoPk.setIdPersona(ubicacionPersonaCasoDTO.getIdPersona());
				rolPersonaCasoPk.setIdRol(rol.getIdRol());
				return (List<UbicacionDTO>) new UbicacionDTO()
						.transformarColeccionEntidadesSinDependencias(obteneUbicacionesRolPersonaCaso(rolPersonaCasoPk));
			}
		}

	}

	@SuppressWarnings("unchecked")
	private List<Ubicacion> obteneUbicacionesRolPersonaCaso(RolPersonaCasoPK rolPersonaCasoPk) {
		return (List<Ubicacion>) new UbicacionDTO().transformarColeccionEntidadesSinDependencias(
				manejadorUbicacionRolPersonaCaso.consultarUbicacionesPorRolPersonaCaso(rolPersonaCasoPk));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RolPersonaCaso> consultarRolesPersonaCasoPorUbicacion(Long idUbicacion) {
		return (List<RolPersonaCaso>) new RolPersonaCasoDTO().transformarColeccionEntidadesSinDependencias(
				manejadorUbicacionRolPersonaCaso.consultarRolesPersonaCasoPorUbicacion(idUbicacion));
	}

	@Override
	public UbicacionRolPersonaCaso crearUbicacionRolPersonaCaso(RolPersonaCaso rolPersonaCaso, Ubicacion ubicacion) {

		// Llave Primaria
		UbicacionRolPersonaCasoPK ubicacionRolPersonaCasoPk = new UbicacionRolPersonaCasoPK();
		ubicacionRolPersonaCasoPk.setIdCaso(rolPersonaCaso.getRolPersonaCasoPK().getIdCaso());
		ubicacionRolPersonaCasoPk.setIdPersona(rolPersonaCaso.getRolPersonaCasoPK().getIdPersona());
		ubicacionRolPersonaCasoPk.setIdRol(rolPersonaCaso.getRolPersonaCasoPK().getIdRol());
		ubicacionRolPersonaCasoPk.setIdUbicacion(ubicacion.getIdUbicacion());

		UbicacionRolPersonaCaso ubicacionRPC = buscar(ubicacionRolPersonaCasoPk);
		UbicacionRolPersonaCaso ubicacionRolPersonaCaso = null;
		if (ubicacionRPC == null) {
			// Crear Entidad
			ubicacionRolPersonaCaso = new UbicacionRolPersonaCaso();
			ubicacionRolPersonaCaso.setUbicacionRolPersonaCasoPK(ubicacionRolPersonaCasoPk);
			ubicacionRolPersonaCaso.setUbicacion(ubicacion);
			ubicacionRolPersonaCaso.setRolPersonaCaso(rolPersonaCaso);
			ubicacionRolPersonaCaso.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);

			// Persistencia
			manejadorUbicacionRolPersonaCaso.crear(ubicacionRolPersonaCaso);
		}

		return ubicacionRolPersonaCaso;
	}

	@Override
	public UbicacionRolPersonaCaso crearUbicacionRolPersonaCaso(RolPersonaCaso rolPersonaCaso, String direccion,
			BigDecimal latitud, BigDecimal longitud, String zonaGeografica) {

		Ubicacion ubicacionPersona = null;

		// Consultar Ubicaciones asociadas a la Persona
		List<Ubicacion> ubicacionesList = ubicacionFacade
				.consultarUbicacionesPorPersona(rolPersonaCaso.getRolPersonaCasoPK().getIdPersona(), true);

		// Obtener identificador de Ubicación a partir de la dirección (Si esta existe)
		for (Ubicacion ubicacion : ubicacionesList) {
			if (ubicacion.getDireccion().equals(direccion) && ubicacion.getLatitud() == latitud
					&& ubicacion.getLongitud() == longitud && ubicacion.getIdZonaGeografica().equals(zonaGeografica)) {
				ubicacionPersona = ubicacion;
				break;
			}
		}

		// Crear Ubicación asociada a la Persona, si esta no existe
		if (ubicacionPersona == null) {
			// Crear Entidad
			ubicacionPersona = new Ubicacion();
			ubicacionPersona.setDireccion(direccion);
			ubicacionPersona.setIdZonaGeografica(zonaGeografica);
			ubicacionPersona.setLatitud(latitud);
			ubicacionPersona.setLongitud(longitud);
			ubicacionPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			ubicacionPersona.setIdPersona(rolPersonaCaso.getRolPersonaCasoPK().getIdPersona());
			ubicacionPersona.setTipo(UtilDominios.TIPO_UBICACION_SECUNDARIA);

			// Persistencia
			ubicacionFacade.crear(ubicacionPersona);
		} else {
			// Si el Ubicación existe y se encuentra Inactivo, se realiza la respectiva
			// Activación
			if (ubicacionPersona.getEstadoRegistro().equals(UtilDominios.ESTADO_REGISTRO_INACTIVO)) {
				// Establecer Estado Activo
				ubicacionPersona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);

				// Persistencia
				ubicacionFacade.actualizar(ubicacionPersona);
			}
		}

		// Crear asociación del Correo Electronico con Rol Persona Caso
		return crearUbicacionRolPersonaCaso(rolPersonaCaso, ubicacionPersona);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.
	 * IUbicacionRolPersonaCasoFacade#asociarUbicacionRolPersonaCaso(com.ccb.
	 * simasc.transversal.dto.UbicacionPersonaCasoDTO)
	 */
	@Override
	public UbicacionDTO asociarUbicacionRolPersonaCaso(UbicacionPersonaCasoDTO ubicacionPersonaCasoDTO) {
		RolPersonaCaso rolPersonaCaso = null;
		PersonaSolicitud personaSolicitud = null;
		Rol rol = null;
		UbicacionDTO ubicacionActualizada;

		rol = manejadorRol.consultarRolPorNombre(ubicacionPersonaCasoDTO.getNombreRol());
		if (ubicacionPersonaCasoDTO.getEsSolicitud()) {
			PersonaSolicitudPK personaSolicitudPK = new PersonaSolicitudPK(rol.getIdRol(),
					ubicacionPersonaCasoDTO.getIdPersona(), ubicacionPersonaCasoDTO.getIdCasoOSolicitud());
			personaSolicitud = manejadorPersonaSolicitud.buscar(personaSolicitudPK);
			
			ubicacionActualizada = ubicacionPersonaSolicitudFacade.asociarUbicacionesPersonaSolicitud(
					Arrays.asList(ubicacionPersonaCasoDTO.getUbicacionDTO()), personaSolicitud);
			return ubicacionActualizada;
		} else {
			RolPersonaCasoPK rolPersonaCasoPK = new RolPersonaCasoPK(ubicacionPersonaCasoDTO.getIdPersona(),
					ubicacionPersonaCasoDTO.getIdCasoOSolicitud(), rol.getIdRol());
			rolPersonaCaso = manejadorRolPersonaCaso.buscar(rolPersonaCasoPK);
			ubicacionActualizada = asociarUbicacionesRolPersona(
					Arrays.asList(ubicacionPersonaCasoDTO.getUbicacionDTO()), rolPersonaCaso);
			return ubicacionActualizada;
		}
	}

	public UbicacionDTO asociarUbicacionesRolPersona(List<UbicacionDTO> ubicaciones, RolPersonaCaso rolPersonaCaso) {
		// Id Ubicacion Actializa o Creada a retornar
		UbicacionDTO idUbicacion = new UbicacionDTO();
		// Validar Parámetros
		if (ubicaciones == null || rolPersonaCaso == null)
			return idUbicacion;
		if (ubicaciones.isEmpty())
			return idUbicacion;

		for (UbicacionDTO ubicacion : ubicaciones) {

			List<UbicacionRolPersonaCaso> list = manejadorUbicacionRolPersonaCaso.validarUbicacionRolPersonaCaso(
					ubicacion.getIdUbicacion(), rolPersonaCaso.getRolPersonaCasoPK().getIdPersona(),
					rolPersonaCaso.getRolPersonaCasoPK().getIdRol(), rolPersonaCaso.getRolPersonaCasoPK().getIdCaso());
			
			if (list.isEmpty() && !UtilMascaraTexto.hasOnlyDots(ubicacion.getDireccion())) {
				
				UbicacionRolPersonaCaso ubicacionCreada = crearUbicacionRolPersonaCaso(rolPersonaCaso,
						ubicacion.getDireccion(), ubicacion.getLatitud(), ubicacion.getLongitud(),
						ubicacion.getIdZonaGeografica());
				idUbicacion.setIdUbicacion(
						ubicacionCreada != null ? ubicacionCreada.getUbicacion().getIdUbicacion() : null);
			}

			if (idUbicacion.getIdUbicacion() != null)
				adicionarTelefonosUbicacion(ubicacion, idUbicacion.getIdUbicacion());
		}
		return idUbicacion;
	}

	@Override
	public UbicacionDTO asociarUbicacionesRolPersonaCaso(List<UbicacionDTO> ubicaciones,
			RolPersonaCaso rolPersonaCaso) {

		// Id Ubicacion Actializa o Creada a retornar
		UbicacionDTO idUbicacion = new UbicacionDTO();

		// Validar Parámetros
		if (ubicaciones == null || rolPersonaCaso == null)
			return idUbicacion;
		if (ubicaciones.isEmpty())
			return idUbicacion;

		// Obtener Ubicaciones asociadas al RolPersonaCaso
		List<Ubicacion> ubicacionesRolPersonaCaso = consultarUbicacionesPorRolPersonaCaso(
				rolPersonaCaso.getRolPersonaCasoPK());

		// Procesar Ubicaciones enviadas como parámetro
		for (UbicacionDTO ubicacion : ubicaciones) {

			if (ubicacion.getDireccion() != null && !ubicacion.getDireccion().isEmpty()
					&& ubicacion.getIdZonaGeografica() != null && !ubicacion.getIdZonaGeografica().isEmpty()
					&& ubicacion.getTipo() != null && !ubicacion.getTipo().isEmpty()) {

				/*
				 * UBICACION PRINCIPAL:
				 * 
				 * Si la Ubicación procesado es Principal, se actualiza la que tiene asociada la
				 * Persona, en caso de que no tenga asociado ninguno, se procede a realizar la
				 * creación respectiva
				 */

				if(ubicacion.getTipo().equals(UtilDominios.TIPO_UBICACION_PRINCIPAL) 
						&& !UtilMascaraTexto.hasOnlyDots(ubicacion.getDireccion())) {
					idUbicacion.setIdUbicacion(ubicacionFacade.gestionarDireccionPrincipal(rolPersonaCaso.getRolPersonaCasoPK().getIdPersona(), ubicacion));

				} else {
					/*
					 * UBICACIONES SECUNDARIAS:
					 * 
					 * Si la Ubicacion procesada es Secundaria, se procede a realizar la asociación
					 * de la misma a la Persona y al Caso. En el caso de que la Ubicación no este
					 * asociada a la Persona, se realiza la asociación respectiva, verificando
					 * previamente el estado de la Ubicación, que en el caso de estar Inactiva se
					 * realiza la Activación antes de proceder con la asociación mencionada
					 * anteriormente. Por ultimo se realiza la asociación al Caso (Rol Persona Caso)
					 * 
					 */

					// Verificar si Ubicación ya se encuentra asociado a RolPersonaCaso
					Boolean asociadoRPC = false;

					for (Ubicacion ubicacionRPC : ubicacionesRolPersonaCaso) {
						if (ubicacionRPC.getDireccion().equals(ubicacion.getDireccion())
								&& ubicacionRPC.getLatitud() == ubicacion.getLatitud()
								&& ubicacionRPC.getLongitud() == ubicacion.getLongitud()
								&& ubicacionRPC.getIdZonaGeografica().equals(ubicacion.getIdZonaGeografica())) {
							asociadoRPC = true;
							break;
						}
					}
					
					// Si la Ubicación no se encuentra asociada, se verifica la dirección de correo anterior, con el fin de
					// determinar si la Ubicación debe ser creada o actualizar una Ubicación anterior, lo cual solamente ocurrirá si
					// la ubicación esta asociada unicamente al Rol Persona Caso enviado por parámetro 
					if(!asociadoRPC) {
						
						// Verificar si la ubicación ya habia sido registrada 
						if(ubicacion.getIdUbicacion() != null && !UtilMascaraTexto.hasOnlyDots(ubicacion.getDireccion())) {

							// Obtener Ubicacion actual asociada a RolPersonaCaso
							Ubicacion ubicacionActual = ubicacionFacade.buscar(ubicacion.getIdUbicacion());

							// Si se encuentra una Ubicacion Actual, se procede a verificar si es diferente
							// a la enviada por parámetro y si esta unicamente
							// asociada al Rol Persona Caso enviado por parámetro, para posteriormente
							// realizar la actualización de datos
							if (ubicacionActual != null) {

								// Verificar si la ubicación actual es diferente a la enviada por parámetro
								if (!ubicacionActual.getDireccion().equals(ubicacion.getDireccion())
										|| ubicacionActual.getLatitud() != ubicacion.getLatitud()
										|| ubicacionActual.getLongitud() != ubicacion.getLongitud() || !ubicacionActual
												.getIdZonaGeografica().equals(ubicacion.getIdZonaGeografica())) {

									// Obtener Roles Persona Caso a partir de la Ubicacion Actual
									List<RolPersonaCaso> rolesPersonaCasoAsociados = consultarRolesPersonaCasoPorUbicacion(
											ubicacionActual.getIdUbicacion());

									// Verificar si la Ubicacion Actual se encuentra unicamente vinculado al Rol
									// Persona Caso enviado por parámetro,
									// si es asi se procede a actualizar los datos asociados a dicha ubicación, en
									// caso contrario se procede con la creación de una nueva Ubicación
									// asociada a la Persona y al Caso
									if (rolesPersonaCasoAsociados.size() == 1) {

										if (ubicacion.getDireccion() != null && !ubicacion.getDireccion().isEmpty()) {
											// Actualizar Entidad
											ubicacionActual.setDireccion(ubicacion.getDireccion());
											ubicacionActual.setLatitud(ubicacion.getLatitud());
											ubicacionActual.setLongitud(ubicacion.getLongitud());
											ubicacionActual.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
											ubicacionActual.setIdZonaGeografica(ubicacion.getIdZonaGeografica());

											// Persistencia
											ubicacionFacade.actualizar(ubicacionActual);
											idUbicacion.setIdUbicacion(ubicacionActual.getIdUbicacion());
										} else {
											// Si la dirección de la ubicación es nula o vacia, se asume como
											// eliminación
											// Teniendo en cuenta que la ubicación anterior solo se encuentra asociada a
											// este caso, se procede
											// también con la eliminación de la Ubicación

											// Eliminar asociación Ubicación anterior
											UbicacionRolPersonaCasoPK ubicacionRolPersonaCasoPK = new UbicacionRolPersonaCasoPK();
											ubicacionRolPersonaCasoPK.setIdCaso(
													rolesPersonaCasoAsociados.get(0).getRolPersonaCasoPK().getIdCaso());
											ubicacionRolPersonaCasoPK.setIdPersona(rolesPersonaCasoAsociados.get(0)
													.getRolPersonaCasoPK().getIdPersona());
											ubicacionRolPersonaCasoPK.setIdRol(
													rolesPersonaCasoAsociados.get(0).getRolPersonaCasoPK().getIdRol());
											ubicacionRolPersonaCasoPK.setIdUbicacion(ubicacionActual.getIdUbicacion());

											manejadorUbicacionRolPersonaCaso.eliminarPorId(ubicacionRolPersonaCasoPK);

											// Eliminar Ubicación
											ubicacionFacade.eliminarPorId(ubicacionActual.getIdUbicacion());
										}
									} else {
										// Eliminar asociación Ubicación anterior
										UbicacionRolPersonaCasoPK ubicacionRolPersonaCasoPK = new UbicacionRolPersonaCasoPK();
										ubicacionRolPersonaCasoPK.setIdCaso(
												rolesPersonaCasoAsociados.get(0).getRolPersonaCasoPK().getIdCaso());
										ubicacionRolPersonaCasoPK.setIdPersona(
												rolesPersonaCasoAsociados.get(0).getRolPersonaCasoPK().getIdPersona());
										ubicacionRolPersonaCasoPK.setIdRol(
												rolesPersonaCasoAsociados.get(0).getRolPersonaCasoPK().getIdRol());
										ubicacionRolPersonaCasoPK.setIdUbicacion(ubicacionActual.getIdUbicacion());

										manejadorUbicacionRolPersonaCaso.eliminarPorId(ubicacionRolPersonaCasoPK);

										// Solo crear la nueva asociación siempre y cuando la dirección sea diferente de
										// vacio
										if (ubicacion.getDireccion() != null && !ubicacion.getDireccion().isEmpty()) {
											// Crear Ubicación asociada a la Persona y al Caso
											UbicacionRolPersonaCaso ubicacionCreada = crearUbicacionRolPersonaCaso(
													rolPersonaCaso, ubicacion.getDireccion(), ubicacion.getLatitud(),
													ubicacion.getLongitud(), ubicacion.getIdZonaGeografica());
											idUbicacion.setIdUbicacion(ubicacionCreada != null
													? ubicacionCreada.getUbicacion().getIdUbicacion()
													: null);
										}
									}
								}
							} else {
								// Ya que no existe la ubicación anterior, se procede a crear la Ubicación
								// asociada a la Persona y al Caso, a partir de los datos enviados por parámetro
								UbicacionRolPersonaCaso ubicacionNueva = crearUbicacionRolPersonaCaso(rolPersonaCaso, ubicacion.getDireccion(), ubicacion.getLatitud(), ubicacion.getLongitud(), ubicacion.getIdZonaGeografica());
								idUbicacion.setIdUbicacion(ubicacionNueva != null ? ubicacionNueva.getUbicacion().getIdUbicacion(): null);
							}						
						}
						else if(!UtilMascaraTexto.hasOnlyDots(ubicacion.getDireccion())){
							// Ya que no se encuentra asociado, se procede a crear la ubicación asociada a la Persona y al Caso							
							UbicacionRolPersonaCaso ubicacionCreada =  crearUbicacionRolPersonaCaso(rolPersonaCaso, ubicacion.getDireccion(), ubicacion.getLatitud(), ubicacion.getLongitud(), ubicacion.getIdZonaGeografica());
							idUbicacion.setIdUbicacion(ubicacionCreada != null ? ubicacionCreada.getUbicacion().getIdUbicacion(): null);
						}
					}
				}
			}

			// Asociación de teléfonos a la ubicación
			if (idUbicacion.getIdUbicacion() != null && !UtilMascaraTexto.hasOnlyDots(ubicacion.getDireccion()))
				adicionarTelefonosUbicacion(ubicacion, idUbicacion.getIdUbicacion());

		}

		return idUbicacion;
	}

	/**
	 * Metodo en cargado de la creacion de los telefonos asociados a una ubicacion
	 * 
	 * @param ubicacion
	 */
	private void adicionarTelefonosUbicacion(UbicacionDTO ubicacion, Long idUbicacion) {
		if (idUbicacion != null && ubicacion.getTelefonoList() != null && !ubicacion.getTelefonoList().isEmpty()) {
			for (TelefonoDTO telefonoDTO : ubicacion.getTelefonoList()) {
				Telefono telefono = new Telefono();
				telefono.setNumero(telefonoDTO.getNumero());
				telefono.setTipoTelefono(telefonoDTO.getTipoTelefono());
				telefono.setIdUbicacion(idUbicacion);
				telefono.setIdUsuarioModificacion(telefonoDTO.getIdUsuarioModificacion());
				telefono.setFechaUltimaModificacion(telefonoDTO.getFechaUltimaModificacion());
				telefono.setEstadoRegistro(telefonoDTO.getEstadoRegistro());
				telefono.setExtension(telefonoDTO.getExtension());
				manejadorTelefono.crear(telefono);
			}
		}
	}

	// protected region metodos adicionales end

}
