package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin

// Escriba en esta seccion sus modificaciones

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorAsesoria;
import com.ccb.simasc.integracion.manejadores.ManejadorCorreoElectronico;
import com.ccb.simasc.integracion.manejadores.ManejadorPersona;
import com.ccb.simasc.integracion.manejadores.ManejadorTelefono;
import com.ccb.simasc.integracion.manejadores.ManejadorUbicacion;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IAsesoriaFacade;
import com.ccb.simasc.transversal.dto.AsesoriaDTO;
import com.ccb.simasc.transversal.dto.CentroDTO;
import com.ccb.simasc.transversal.dto.formularios.NuevaAsesoriaDTO;
import com.ccb.simasc.transversal.entidades.Asesoria;
import com.ccb.simasc.transversal.entidades.CorreoElectronico;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Telefono;
import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.excepciones.SimascException;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilConsultasSQL;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports fachada end

/**
 * Implementacion de fachada RESTFULL para {@link Asesoria}
 * 
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class AsesoriaFacade extends AccesoFacade<Asesoria, Long, AsesoriaDTO> implements IAsesoriaFacade {

	// protected region atributos on begin
	// En esta seccion se deben incluir los atributos de la fachada

	@EJB
	private ManejadorAsesoria manejadorAsesoria;

	@EJB
	private ManejadorPersona manejadorPersona;

	@EJB
	private ManejadorCorreoElectronico manejadorCorreo;

	@EJB
	private ManejadorUbicacion manejadorUbicacion;

	@EJB
	private ManejadorTelefono manejadorTelefono;

	// protected region atributos end

	@SuppressWarnings("rawtypes")
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorAsesoria;
	}

	@Override
	public AsesoriaDTO transformarSinDependencias(Asesoria obj) {
		AsesoriaDTO dto = new AsesoriaDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public AsesoriaDTO transformarConDependencias(Asesoria obj) {
		AsesoriaDTO dto = new AsesoriaDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Asesoria transformarEntidadConDependencias(Asesoria obj) {
		Asesoria dto = new Asesoria();
		dto = new AsesoriaDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Asesoria transformarEntidadSinDependencias(Asesoria obj) {
		Asesoria dto = new Asesoria();
		dto = new AsesoriaDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	@Override
	public List<AsesoriaDTO> consultaAsesorias(AsesoriaDTO filtros) {
		List<AsesoriaDTO> asesorias = new ArrayList<>();
		List<Long> idsCentros = new ArrayList<>();
		if (!filtros.getCentros().isEmpty())
			for (CentroDTO centro : filtros.getCentros()) {
				idsCentros.add(centro.getIdCentro());
			}
		else
			idsCentros.add((long) 0);
		String inIdCentros = UtilConsultasSQL.clausulaInSQLSNumeros(idsCentros);
		if (filtros.getFechaInicio() != null && filtros.getFechaHasta() != null)
			asesorias = manejadorAsesoria.consultaAsesorias(filtros, inIdCentros);
		else if (filtros.getIdPersonaAsesora() != null)
			asesorias = manejadorAsesoria.consultaAsesorias(filtros, inIdCentros);
		else {
			String mensaje = "Las fechas de las asesorias no deben ser nulas";
			throw new SimascException(mensaje);
		}
		return asesorias;
	}

	@Override
	public List<Persona> listadoAsesores(List<CentroDTO> centros) {
		List<Long> idsCentros = new ArrayList<>();
		if (!centros.isEmpty())
			for (CentroDTO centro : centros) {
				idsCentros.add(centro.getIdCentro());
			}
		else
			idsCentros.add((long) 0);
		String inIdCentros = UtilConsultasSQL.clausulaInSQLSNumeros(idsCentros);
		return manejadorAsesoria.listadoIdAsesores(inIdCentros);
	}

	@Override
	public void nuevaAsesoria(NuevaAsesoriaDTO asesoria) {
		asesoria.setIdPersona(crearPersonaAseroria(asesoria));
		crearCorreoAsesoria(asesoria);
		crearUbicacionAsesoria(asesoria);
		crearTelefonoAsesoria(asesoria);
		Asesoria nuevaAsesoria = new Asesoria();
		nuevaAsesoria.setObservaciones(asesoria.getObservaciones());
		nuevaAsesoria.setTipoAsesoria(asesoria.getTipoAsesoria());
		nuevaAsesoria.setFechaAsesoria(asesoria.getFechaAsesoria());
		nuevaAsesoria.setCuantia(asesoria.getCuantia());
		nuevaAsesoria.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		nuevaAsesoria.setFechaUltimaModificacion(new Date());
		nuevaAsesoria.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		nuevaAsesoria.setIdPersonaAsesora(asesoria.getIdUsuario());
		nuevaAsesoria.setIdPersona(asesoria.getIdPersona());
		nuevaAsesoria.setIdMateria(asesoria.getIdMateria());
		nuevaAsesoria.setIdServicio(asesoria.getIdServicio());
		manejadorAsesoria.crear(nuevaAsesoria);
	}

	private Long crearPersonaAseroria(NuevaAsesoriaDTO datosPersona) {
		Persona persona ;
		if(datosPersona.getIdPersona() != null){
			persona = manejadorPersona.buscar(datosPersona.getIdPersona());
		}else{
			persona = new Persona();
		}

		persona.setTipoPersona(!datosPersona.getTipoPersona().equalsIgnoreCase(UtilConstantes.VALOR_UNDEFINED)
				? datosPersona.getTipoPersona() : null);
		persona.setTipoDocumento(!datosPersona.getTipoDocumento().equalsIgnoreCase(UtilConstantes.VALOR_UNDEFINED)
				? datosPersona.getTipoDocumento() : null);
		persona.setNumeroDocumento(datosPersona.getNumeroDocumento());
		persona.setPrimerNombreORazonSocial(datosPersona.getPrimerNombre());
		persona.setSegundoNombre(datosPersona.getSegundoNombre());
		persona.setPrimerApellido(datosPersona.getPrimerApellido());
		persona.setSegundoApellido(datosPersona.getSegundoApellido());
		persona.setSexo( datosPersona.getSexo() != null && !datosPersona.getSexo().equalsIgnoreCase(UtilConstantes.VALOR_UNDEFINED)
				? datosPersona.getSexo() : null);
		persona.setUbicacionList(null);
		persona.setCorreoElectronicoList(null);
		persona.setTelefonoList(null);
		persona.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
		persona.setEstadoPersona(UtilDominios.ESTADO_PERSONA_ACTIVO);
		persona.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
		persona.setFechaUltimaModificacion(new Date());
		if(persona.getIdPersona() != null){
			manejadorPersona.crear(persona);
		}else{
			persona = manejadorPersona.crearPersona(persona);
			
		}
		return persona.getIdPersona();
	}

	private void crearCorreoAsesoria(NuevaAsesoriaDTO datosEmail) {
		if (datosEmail.getEmail() != null && !datosEmail.getEmail().isEmpty()) {
			
			CorreoElectronico correoPrincipal = manejadorCorreo.traerPrimerCorreoPrimario(datosEmail.getIdPersona());
			String direccionAnterior = datosEmail.getEmail();
			
			if (correoPrincipal != null && datosEmail.getEmail().equalsIgnoreCase(correoPrincipal.getDireccion())) {
				manejadorCorreo.pasarCorreosASecundarios(datosEmail.getIdPersona(), correoPrincipal.getIdCorreo());
				direccionAnterior = correoPrincipal.getDireccionAnterior();
			} else if (correoPrincipal != null) {
				manejadorCorreo.pasarCorreosASecundarios(datosEmail.getIdPersona(), null);
				direccionAnterior = correoPrincipal.getDireccionAnterior();
			}

			CorreoElectronico correo = new CorreoElectronico();
			correo.setIdCorreo(datosEmail.getIdCorreo() != null ? datosEmail.getIdCorreo() : null);
			correo.setDireccion(datosEmail.getEmail());
			correo.setTipo(UtilDominios.TIPO_CORREO_ELECTRONICO_CORREO_PRINCIPAL);
			correo.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			correo.setFechaUltimaModificacion(new Date());
			correo.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			correo.setIdPersona(datosEmail.getIdPersona());
			correo.setDireccionAnterior(direccionAnterior);
			manejadorCorreo.creaOActualizaCorreo(correo);
		}
	}

	private void crearUbicacionAsesoria(NuevaAsesoriaDTO datosUbicacion) {
		if (datosUbicacion.getDireccion() != null
				&& !datosUbicacion.getIdCiudad().equalsIgnoreCase(UtilConstantes.VALOR_UNDEFINED)
				&& !datosUbicacion.getIdPais().equalsIgnoreCase(UtilConstantes.VALOR_UNDEFINED)) {
			Ubicacion ubicacion = new Ubicacion();
			ubicacion.setIdUbicacion(datosUbicacion.getIdUbicacion() != null ? datosUbicacion.getIdUbicacion() : null);
			ubicacion.setDireccion(datosUbicacion.getDireccion());
			ubicacion.setIdZonaGeografica(!datosUbicacion.getIdCiudad().equalsIgnoreCase(UtilConstantes.VALOR_UNDEFINED)
					? datosUbicacion.getIdCiudad() : null);
			ubicacion.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			ubicacion.setFechaUltimaModificacion(new Date());
			ubicacion.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			ubicacion.setIdPersona(datosUbicacion.getIdPersona());
			ubicacion.setTipo(UtilDominios.TIPO_UBICACION_PRINCIPAL);
			manejadorUbicacion.creaOActualizaUbicacion(ubicacion);
		}
	}

	private void crearTelefonoAsesoria(NuevaAsesoriaDTO datosTelefono) {
		if ( datosTelefono.getTipoTelefono() !=null && !datosTelefono.getTipoTelefono().equalsIgnoreCase(UtilConstantes.VALOR_UNDEFINED)
				&& datosTelefono.getTelefono() != null) {
			Telefono telefono = new Telefono();
			telefono.setIdTelefono(datosTelefono.getIdTelefono() != null ? datosTelefono.getIdTelefono() : null);
			telefono.setNumero(datosTelefono.getTelefono());
			telefono.setTipoTelefono(!datosTelefono.getTipoTelefono().equalsIgnoreCase(UtilConstantes.VALOR_UNDEFINED)
					? datosTelefono.getTipoTelefono() : null);
			telefono.setIdUsuarioModificacion(UtilConstantes.USUARIO_DEFECTO_SIMASC);
			telefono.setFechaUltimaModificacion(new Date());
			telefono.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_ACTIVO);
			telefono.setIdPersona(datosTelefono.getIdPersona());
			manejadorTelefono.creaOActualizaTelefono(telefono);
		}
	}

	// protected region metodos adicionales end

}
