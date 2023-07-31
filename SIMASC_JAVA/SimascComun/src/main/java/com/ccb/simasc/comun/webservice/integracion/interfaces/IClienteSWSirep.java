package com.ccb.simasc.comun.webservice.integracion.interfaces;

import java.util.Map;

import javax.ejb.Local;

import com.ccb.simasc.transversal.dto.formularios.FormularioDatosClienteDTO;
import com.ccb.simasc.transversal.excepciones.SimascException;

import co.org.ccb.sirep2.clientes.ws.model.ActualizarClienteDatosContactoOutDTO;
import co.org.ccb.sirep2.clientes.ws.model.CrearClienteDatosContactoOutDTO;

/**
 * Interfaz que gestiona las operaciones de servicio web que se comunica con sirep
 * @author fguzman
 *
 */
@Local
public interface IClienteSWSirep {

	/**
	 * Metodo que crea clientes nuevos en sirep
	 * @param datosBasicosCliente
	 * @return
	 */
	public Map<String, String> crearDatosBasicosClienteSirep(FormularioDatosClienteDTO datosBasicosCliente) throws SimascException;
	/**
	 * Metodo que consulta los datos de los clientes en sirep
	 * @param datosBasicosCliente
	 * @return
	 */
	public FormularioDatosClienteDTO consultarDatosBasicosClienteSirep(String tipoIdentificacion, String numeroIdentificacion);
	/**
	 * Actualiza los datos basicos del cliente en sirep
	 * @return
	 */
	public ActualizarClienteDatosContactoOutDTO ActualizarDatosBasicosClienteSirep(FormularioDatosClienteDTO datosBasicos);
	
	public CrearClienteDatosContactoOutDTO crearDatosContactoClienteSirep(FormularioDatosClienteDTO datosBasicos);
}
