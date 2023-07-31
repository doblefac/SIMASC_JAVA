package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.ccb.simasc.comun.fachada.implementacion.AccesoFacade;
import com.ccb.simasc.integracion.manejadores.ManejadorTrm;
import com.ccb.simasc.integracion.manejadores.utilidades.IManejadorCrud;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ITrmFacade;
import com.ccb.simasc.transversal.dto.TrmDTO;
import com.ccb.simasc.transversal.entidades.Trm;
import com.ccb.simasc.transversal.excepciones.SIMASCNegocioExcepcion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

@Stateless
@LocalBean
public class TrmFacade extends AccesoFacade<Trm, Long, TrmDTO> implements ITrmFacade {
	
	
	@EJB
	private ManejadorTrm manejadorTrm;
	
	@Inject
    private ApplicationRequestContext appContext;
	
	@Override
	public IManejadorCrud getManejadorCrud() {
		return this.manejadorTrm;
	}

	@Override
	public TrmDTO transformarSinDependencias(Trm obj) {
		TrmDTO dto = new TrmDTO();
		return dto.transformarSinDependencias(obj);
	}

	@Override
	public Trm transformarEntidadConDependencias(Trm obj) {		
		return new TrmDTO().transformarEntidadSinDependencias(obj);		
	}

	@Override
	public Trm transformarEntidadSinDependencias(Trm obj) {
		return new TrmDTO().transformarEntidadSinDependencias(obj);
	}

	@Override
	public TrmDTO transformarConDependencias(Trm obj) {
		TrmDTO dto = new TrmDTO();
		return dto.transformarConDependencias(obj);
	}

	private Trm preparaObjeto(TrmDTO trmDTO) {
		
		Trm nuevoTrm = new Trm();		
		String usuarioActual = appContext.getContextoSesion().getIdUsuario() != null 
				? appContext.getContextoSesion().getNombreUsuario() : UtilConstantes.USUARIO_DEFECTO_SIMASC;
				
		nuevoTrm.setIdUsuarioModificacion(usuarioActual);
		nuevoTrm.setFechaUltimaModificacion(new Date());
		nuevoTrm.setValor(trmDTO.getValor());	
		nuevoTrm.setFecha(trmDTO.getFecha());
		nuevoTrm.setEstadoRegistro(trmDTO.getEstadoRegistro());
		
		return nuevoTrm;
	}

	@Override
	public void crearTRM(TrmDTO trmDTO) {
		Trm nuevoTrm = null;
		try{
			nuevoTrm = preparaObjeto(trmDTO);
			manejadorTrm.crear(nuevoTrm);
		} catch (Exception e) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(e.getMessage()));
		}
	}

	@Override
	public void actualizarTRM(TrmDTO trmDTO) {
		
		Trm trm = null;
		try{
			trm = preparaObjeto(trmDTO);
			trm.setIdTrm(trmDTO.getIdTrm());
			manejadorTrm.actualizar(trm);
		} catch (Exception e) {
			throw new SIMASCNegocioExcepcion(
					MensajesSimasc.getInstancia().getMensajePorKey(e.getMessage()));
		}
	}

	@Override
	public TrmDTO consultarTRMPorId(Long idTrm) {	
		return transformarSinDependencias(manejadorTrm.buscar(idTrm));
	}
	
	@Override
	public List<TrmDTO> obtenerTodosTRM() {
		return (List<TrmDTO>) transformarColeccionSinDependencias(manejadorTrm.consultarTodos(),
				new ArrayList<TrmDTO>());
	}

	@Override
	public TrmDTO consultarTRMActual() {
		return transformarSinDependencias(manejadorTrm.obtenerTrmActual());
	}
}
