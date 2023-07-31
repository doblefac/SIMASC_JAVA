package com.ccb.simasc.negocio.fachadas.reparto.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.transversal.dto.DatosEntradaRepartoDTO;
import com.ccb.simasc.transversal.dto.formularios.PersonaBasicaDTO;
import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Documento;
import com.ccb.simasc.transversal.entidades.Persona;


import com.ccb.simasc.transversal.entidades.Caso;
import com.ccb.simasc.transversal.entidades.Persona;
import com.ccb.simasc.transversal.entidades.Rol;


/**
 * Expone los servicios de reparto (asignacion de funcionario o funcionarios a casos)
 * @author Asesoftware - Javier Estévez
 *
 */
@Local
public interface IRepartoSvc {
	
	
    /**
     * Ejecuta el algoritmo de reparto asignando los prestadores de servicio al caso y creando
     * las entidades necesarias según lo especificado en el caso de uso ARB-C-032 SIMASC-CU-Reparto
     * @param datos datos de entrada necesaria para el reparto.
     */
	public void reparto(DatosEntradaRepartoDTO datos) throws Exception;

	/** Metodo que crea el evento de No reparto y envia el correo al analista de conciliacion con
	 * los motivos de no reparto.
	 * @param detallesNoReparto
	 */
	public void fallaReparto(Long idCaso, String detallesNoReparto, String idUsuarioModificacion);
	
	public void repartoEquidad(DatosEntradaRepartoDTO datos) throws Exception;
	
	public void envioCorreoPartes(List<Documento> documentos, List<PersonaBasicaDTO> correo, Long idCaso) throws Exception;

	public void envioCorreDevolucionConciliadroEquidad(Long caso, Long idPersona);
	
	public void envioCorreoRechazo(Long caso, Long idPersona, String observaciones);

}
