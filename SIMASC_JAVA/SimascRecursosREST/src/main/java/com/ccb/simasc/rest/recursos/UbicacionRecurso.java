package com.ccb.simasc.rest.recursos;
// protected region imports recurso on begin
// Escriba en esta sección sus modificaciones


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.integracion.enumeraciones.TipoFiltro;
import com.ccb.simasc.integracion.manejadores.ManejadorTelefono;
import com.ccb.simasc.integracion.manejadores.ManejadorUbicacion;
import com.ccb.simasc.integracion.manejadores.utilidades.InformacionFiltro;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IUbicacionFacade;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcion;
import com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTMensajes;
import com.ccb.simasc.transversal.dto.TelefonoDTO;
import com.ccb.simasc.transversal.dto.UbicacionDTO;
import com.ccb.simasc.transversal.dto.UbicacionDetalladaDTO;
import com.ccb.simasc.transversal.dto.UbicacionPersonaCasoDTO;
import com.ccb.simasc.transversal.entidades.Telefono;
import com.ccb.simasc.transversal.entidades.Ubicacion;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

// protected region imports recurso end

/**
 * Recurso REST Ubicacion
 * @author sMartinez
 */
@Path( "ubicacion" )
@Stateless
public class UbicacionRecurso {
	/**
    * REST resource logging 
    */
    private static final Logger LOG = LogManager.getLogger(UbicacionRecurso.class);
    private static final Class<Ubicacion> enClass= Ubicacion.class;
   
   	// protected region atributos on begin
	// En esta sección se deben incluir los atributos del recurso
	
	@EJB
    private IUbicacionFacade ubicacionFacade; 
	
	@EJB
    private ManejadorTelefono telefonoManejador; 
	
	@EJB
    private ManejadorUbicacion ubicacionManejador; 

	
	// protected region atributos end
	
	@Context
    private HttpHeaders httpHeaders;
          
    
   // protected region metodos adicionales on begin
   // Escriba en esta sección sus modificaciones
	/**
	 * Servicio encargado de adicionar direcciones a una persona
	 * @param ubicacionDTO
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/adicionarDireccion/")
	public Response adicionarUbicacion(UbicacionDTO ubicacionDTO) {
		Response response = null;
		try {
			ubicacionFacade.adicionarDireccion(ubicacionDTO);
			response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * Servicio encargado de adicionar direcciones a una persona
	 * @param ubicacionDTO
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/adicionarDireccionPersona/")
	public Response adicionarDireccionPersona(UbicacionDTO ubicacionDTO) {
		Response response = null;
		try {
			Long idUbicacionCreada = ubicacionFacade.adicionarDireccion(ubicacionDTO);
			
			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<Long>(idUbicacionCreada) {
					}).header("Access-Control-Allow-Headers", "X-extra-header").build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	/**
	 * Servicio encargado de adicionar direcciones a una persona
	 * @param ubicacionDTO
	 * @return
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/adicionarTelefonosDireccion/")
	public Response adicionarTelefonosDireccion(Telefono telefono) {
		Response response = null;
		try {
			telefonoManejador.crear(telefono);
			response = Response.status(Response.Status.OK).header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);	
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	// Escriba en esta sección sus modificaciones
	/**
	 * Servicio encargado de consultar las direcciones de una persona.
	 * 
	 * @param ubicacionDTO
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultaDireccionesPersona/{idPersona}")
	public Response consultarDireccionesPersona(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {

			List<Ubicacion> ubicacionDTOs = ubicacionManejador.consultarPorIdPersona(idPersona);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<Ubicacion>>(ubicacionDTOs) {
			}).header("Access-Control-Allow-Headers", "X-extra-header").build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * 
	 * 
	 * @param ubicacionPersonaCasoDTO
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarDireccionesRolPersonaCaso/")
	public Response consultarDireccionesRolPersonaCaso(UbicacionPersonaCasoDTO ubicacionPersonaCasoDTO) {
		Response response = null;
		try {

			List<UbicacionDTO> ubicacionDTOs = ubicacionFacade
					.consutarDireccionesRolPersonaCaso(ubicacionPersonaCasoDTO);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<UbicacionDTO>>(ubicacionDTOs) {
			}).header("Access-Control-Allow-Headers", "X-extra-header").build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
		
	/**
	 * Servicio encargado de consultar los telefonos de una dirección de una
	 * persona.
	 * 
	 * @param ubicacionDTO
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultaTelefonoDireccionesPersona/{idUbicacion}")
	public Response consultaTelefonoDireccionesPersona(@PathParam("idUbicacion") String idUbicacion) {
		Response response = null;
		try {

			InformacionFiltro filtroTelefono = new InformacionFiltro(TipoFiltro.EXACTO,
					Telefono.ENTIDAD_TELEFONO_ID_UBICACION, idUbicacion);
			List<InformacionFiltro> filtrosTipoTelefono = new ArrayList<>();
			filtrosTipoTelefono.add(filtroTelefono);

			List<Telefono> telefonos = telefonoManejador.consultar(filtrosTipoTelefono, null, null);
			List<Telefono> telefonoSinDependencia = (List<Telefono>) new TelefonoDTO()
					.transformarColeccionEntidadesSinDependencias(telefonos);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<Telefono>>(telefonoSinDependencia) {
					}).header("Access-Control-Allow-Headers", "X-extra-header").build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Servicio encargado de eliminar una dirección.
	 * 
	 * @param ubicacionDTO
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/eliminarDireccionPersona/{idUbicacion}")
	public Response eliminarDireccionPersona(@PathParam("idUbicacion") Long idUbicacion) {
		Response response = null;
		try {

			ubicacionFacade.eliminarDireccion(idUbicacion);

			response = Response.status(Response.Status.OK)
					.header("Access-Control-Allow-Headers", UtilConstantes.X_EXTRA_HEADER).build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Devuelve las ubicaciones detalladas de la persona CON-C-006
	 * 
	 * @author LRUIZ
	 * @param idPersona
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/UbicacionDetallaByIdpersona/{idPersona}")
	public Response ubicacionDetallaByIdpersona(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {

			List<UbicacionDetalladaDTO> ubicaciones = ubicacionFacade.ubicacionDetallaByIdpersona(idPersona);

			response = Response.status(Response.Status.OK)
					.entity(new GenericEntity<List<UbicacionDetalladaDTO>>(ubicaciones) {
					}).header("Access-Control-Allow-Headers", "X-extra-header").build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}

	/**
	 * Consulta la primera ubicacion de una persona CON-C-006
	 * 
	 * @author LRUIZ
	 * @param idPersona
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarDireccionPersona/{idPersona}")
	public Response consultarDireccionPersona(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {

			UbicacionDTO ubicacion = ubicacionFacade.consultarPrimeraUbicacionPersona(idPersona);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<UbicacionDTO>(ubicacion) {
			}).header("Access-Control-Allow-Headers", "X-extra-header").build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
		
		
   // protected region metodos adicionales end

	/**
	 * 
	 * 
	 * @param ubicacionPersonaCasoDTO
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultarDireccionesEnmascaradasRolPersonaCaso/")
	public Response consultarDireccionesEnmascaradasRolPersonaCaso(UbicacionPersonaCasoDTO ubicacionPersonaCasoDTO) {
		Response response = null;
		try {

			List<UbicacionDTO> ubicacionDTOs = ubicacionFacade
					.consutarDireccionesEnmascaradasRolPersonaCaso(ubicacionPersonaCasoDTO);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<UbicacionDTO>>(ubicacionDTOs) {
			}).header("Access-Control-Allow-Headers", "X-extra-header").build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/consultaDireccionesEnmascaradasPersona/{idPersona}")
	public Response consultaDireccionesEnmascaradasPersona(@PathParam("idPersona") Long idPersona) {
		Response response = null;
		try {

			List<UbicacionDTO> ubicacionDTOs = ubicacionFacade
					.consultaDireccionesEnmascaradasPersona(idPersona);

			response = Response.status(Response.Status.OK).entity(new GenericEntity<List<UbicacionDTO>>(ubicacionDTOs) {
			}).header("Access-Control-Allow-Headers", "X-extra-header").build();
		} catch (SIMASCNegocioExcepcion e) {
			LOG.error(String.format(SIMASCRecursosRESTMensajes.OBTAIN_ENTITY, enClass, ""), e);
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw SIMASCRecursosRESTExcepcion.transformTopException(e);
		}
		return response;
	}
}
