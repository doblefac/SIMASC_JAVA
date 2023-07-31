package com.ccb.simasc.comun.fachada.interfaces;

import java.util.Map;

import javax.ejb.Local;

import com.ccb.simasc.transversal.dto.formularios.FormularioDatosClienteDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioGenerarLiquidacionDTO;
import com.ccb.simasc.transversal.dto.formularios.FormularioGenerarPagoPup;
import com.ccb.simasc.transversal.dto.formularios.ProcesoReliquidacionDTO;
/**
 * 
 * @author fguzman
 *
 */
@Local
public interface IIntegracionSWFacade {

	/**
	 * Agrega el caso de arbitraje a ministerio
	 * @param idCaso
	 * @return
	 */
	public String agregarCasoArbitrajeMinisterio(Long idCaso);
	/**
	 * Agrega el caso de amigable composicion a ministerio
	 * @param idCaso
	 * @return
	 */
	public String agregarCasoAmigableComposicionMinisterio(Long idCaso);
	/**
	 * Crea los datos basicos del cliente en sirep
	 * @param datosBasicos
	 * @return
	 */
	public Map<String, String> crearDatosBasicosClienteSirep(FormularioDatosClienteDTO datosBasicos);
	
	/**
	 * Consulta los datos basicos del cliente
	 * @param String tipoIdentificacion
	 * @param String numeroIdentificacion
	 * @return
	 */
	public FormularioDatosClienteDTO consultarDatosBasicosClienteSirep(String tipoIdentificacion,String numeroIdentificacion);
	
	/**
	 * realiza la liquidacion en el sistema PUP
	 * @param datosLiquidacion
	 * @return
	 */
	public FormularioGenerarLiquidacionDTO realizarLiquidacion(ProcesoReliquidacionDTO reliquidacion);
	
	/**
	 * genera la orden de pago en el sistema PUP
	 * @param datosLiquidacion
	 * @return
	 */
	public String generarOrdenDePagoPup(FormularioGenerarPagoPup datosLiquidacion);
	

	/**
	 *  Agrega el caso de conciliacion a ministerio
	 * @param idCaso
	 * @return
	 */
	public Object[] agregarCasoConciliacionMinisterio(Long idCaso);
	
	public void actualizarDatosContactoClienteSirep(FormularioDatosClienteDTO datosBasicos);
	
}
