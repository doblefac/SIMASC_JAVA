package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;
// protected region imports interfaz fachada on begin
// Escriba en esta seccion sus modificaciones


import java.util.List;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.CarpetaDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosCarpetaDTO;
import com.ccb.simasc.transversal.entidades.Carpeta;
import com.ccb.simasc.transversal.entidades.Documento;



// protected region imports interfaz fachada end

/**
 * Contrato que define una entidad de acceso a fachada de {@link Carpeta}
 * @author sMartinez
 *
 */
@Local
public interface ICarpetaFacade extends IAccesoFacade<Carpeta, Long, CarpetaDTO> {


	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
	public List<Carpeta> obtenerCarpetaCuadernoPorCaso(Long casoId);

	public void guardarInformacionDocumento(ParametrosCarpetaDTO carpetaInfo);

	public void generarCuadernos(Long idCaso);

	public void enviarAlExpediente(List<Documento> listDocumento, Long idCarpeta);

	public ParametrosCarpetaDTO obtenerDocumentoCaso(Long casoId, Long codigoDoc);

	public List<Documento> obtenerDocumentoCarpeta(Long casoId);

	public Carpeta crearCarpeta(Long idCuaderno, String nombre);

	public Documento cambiarDestinoDocumento(Long idCarpetaActual, Long idCarpetaDestino, Long idDocumento);

	public void eliminarCarpeta(Long idCarpeta);
	
	public List<Carpeta> obtenerCarpetasPorIdContenedor(Long idCarpetaContenedora);
	
	public void modificarUbicacionDocuemnto(long documento ,long idCarpeta);
	
	public void modificarUbicacionCarpeta(long carpeta, long carpetaContenedora);
	
	public String obtenerCantidadCarpetasIDocumentos(long carpeta);

	// protected region metodos adicionales end
	
}
