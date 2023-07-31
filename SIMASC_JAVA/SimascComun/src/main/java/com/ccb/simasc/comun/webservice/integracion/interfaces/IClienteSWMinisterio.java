package com.ccb.simasc.comun.webservice.integracion.interfaces;

import javax.ejb.Local;
import javax.xml.bind.JAXBElement;

//import org.datacontract.schemas._2004._07.Usuario;

import com.ccb.simasc.transversal.dto.formularios.DatosCasoMinisterioDTO;
import com.simasc.clientes.ministerio.Mensaje;
import com.simasc.clientes.ministerio.Usuario;

/**
 * 
 * @author fguzman
 *
 */
@Local
public interface IClienteSWMinisterio {

	/**
	 * Registra los casos para arbitraje
	 * @param datosCaso
	 * @return
	 */
	public String agregarCasoArbitrajeMinisterio(DatosCasoMinisterioDTO datosCaso);
	
	/**
	 * Registra los casos para amigable composicion
	 * @param datosCaso
	 * @return
	 */
	public String agregarCasoAmigableComposicionMinisterio(DatosCasoMinisterioDTO datosCaso);
	/**
	 * Consulta los datos del usuario del web service para consumir los servicios en ministerio de justicia
	 * @return Usuario de la libreria com.simasc.clientes.ministerio.Usuario;
	 */
	public Usuario obtenerDatosUsuario(JAXBElement<String> modulo);
	
	/**
	 * Registra en el ministerio los casos de conciliacion
	 * @param datosCaso
	 * @return
	 */
	public Mensaje agregarCasoConciliacionMinisterio(DatosCasoMinisterioDTO datosCaso);
}
