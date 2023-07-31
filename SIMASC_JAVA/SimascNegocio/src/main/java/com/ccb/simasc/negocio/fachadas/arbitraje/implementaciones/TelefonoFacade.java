package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
// protected region imports fachada on begin
// Escriba en esta sección sus modificaciones

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorTelefono;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITelefonoFacade;
import com.ccb.simasc.transversal.dto.TelefonoDTO;
import com.ccb.simasc.transversal.dto.formularios.GenericoDTO;
import com.ccb.simasc.transversal.entidades.Telefono;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
import com.ccb.simasc.transversal.utilidades.UtilDominios;



// protected region imports fachada end


/**
 * Implementacion de fachada RESTFULL para {@link Telefono}
 * @author sMartinez
 *
 */
@Stateless
@LocalBean
public class TelefonoFacade extends AccesoFacade<Telefono, Long, TelefonoDTO> implements ITelefonoFacade {
	
	// protected region atributos on begin
	// En esta sección se deben incluir los atributos de la fachada
	
	@EJB
	private ManejadorTelefono manejadorTelefono;
	
	// contexto de sesion de usuario.
	@Inject
	private ApplicationRequestContext appContext;
	
	// protected region atributos end
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorTelefono;
	}

	@Override
	public TelefonoDTO transformarSinDependencias(Telefono obj) {
		TelefonoDTO dto = new TelefonoDTO();
		dto = dto.transformarSinDependencias(obj);
		return dto;
	}

	@Override
	public TelefonoDTO transformarConDependencias(Telefono obj) {
		TelefonoDTO dto = new TelefonoDTO();
		dto = dto.transformarConDependencias(obj);
		return dto;
	}

	@Override
	public Telefono transformarEntidadConDependencias(Telefono obj) {
		Telefono dto = new Telefono();
		dto = new TelefonoDTO().transformarEntidadConDependencias(obj);
		return dto;
	}

	@Override
	public Telefono transformarEntidadSinDependencias(Telefono obj) {
		Telefono dto = new Telefono();
		dto = new TelefonoDTO().transformarEntidadSinDependencias(obj);
		return dto;
	}	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones
	@Override
	public List<GenericoDTO> consultarTelefonosPersona(Long idPersona) throws SIMASCNegocioExcepcion {
		List<GenericoDTO> list = new ArrayList<>();
		List<Telefono> telefonos = manejadorTelefono.consultarTelefonosPersona(idPersona, true);
		for(Telefono it1 : telefonos){
			GenericoDTO dto = new GenericoDTO();
			dto.setId(it1.getIdTelefono().toString());
			dto.setNombre(it1.getNumero());
			dto.setTipo(it1.getTipoTelefono());
			list.add(dto);
		}
		return list;
	}
	
	@Override
	public void eliminarTelefonosPersona(Long idPersona) throws SIMASCNegocioExcepcion {
		List<Telefono> telefonosActivos = manejadorTelefono.consultarTelefonosPersona(idPersona, true);
		for (Telefono telefono : telefonosActivos) {
			String usuarioActual = appContext != null && appContext.getContextoSesion() != null
					&& appContext.getContextoSesion().getNombreUsuario() != null
							? appContext.getContextoSesion().getNombreUsuario() : UtilConstantes.USUARIO_DEFECTO_SIMASC;
			telefono.setIdUsuarioModificacion(usuarioActual);
			telefono.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_CLAVE_INACTIVO);
			telefono.setFechaUltimaModificacion(new Date());
			manejadorTelefono.actualizar(telefono);
		}
	}
	
	@Override
	public void crearTelefonoPersona(String numeroTelefono, String tipoTelefono, Long idPersona, Long idTelefono, String estadoRegistro)
			throws SIMASCNegocioExcepcion {
		Telefono telefono = buscar(idTelefono);
		
		if(telefono == null) {
			telefono = new Telefono();
		}
		telefono.setNumero(numeroTelefono);
		telefono.setTipoTelefono(tipoTelefono);
		telefono.setFechaUltimaModificacion(new Date());
		telefono.setEstadoRegistro(estadoRegistro);
		telefono.setIdPersona(idPersona);
		
		if(telefono.getIdTelefono() != null) 
			actualizar(telefono);
		else
			crear(telefono);
	}
	
	@Override
	public Long buscarTelefonosPersona(Long idPersona, String tipoTelefono) throws SIMASCNegocioExcepcion {
		List<Telefono> telefonosActivos = manejadorTelefono.consultarTelefonosPersona(idPersona, true);
		for (Telefono telefono : telefonosActivos) {
			if(telefono.getTipoTelefono().equals(tipoTelefono) && tipoTelefono.equals(UtilDominios.TIPO_TELEFONO_CELULAR)) {
				return telefono.getIdTelefono();
			}else {
				String usuarioActual = appContext != null && appContext.getContextoSesion() != null
						&& appContext.getContextoSesion().getNombreUsuario() != null
								? appContext.getContextoSesion().getNombreUsuario() : UtilConstantes.USUARIO_DEFECTO_SIMASC;
				telefono.setIdUsuarioModificacion(usuarioActual);
				telefono.setEstadoRegistro(UtilDominios.ESTADO_REGISTRO_CLAVE_INACTIVO);
				telefono.setFechaUltimaModificacion(new Date());
				manejadorTelefono.actualizar(telefono);
			}
		}
		return new Long(0);
	}
	
}
