package com.ccb.simasc.negocio.fachadas.arbitraje.implementaciones;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IFalloAlertaFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.ILogAlertasFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.INotificacionFacade;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class LogAlertasFacade  implements ILogAlertasFacade {
	private static final Logger logger = LogManager.getLogger(LogAlertasFacade.class.getName());
	@Resource
	private UserTransaction userTransaction;
	 
	@EJB
	private INotificacionFacade notificacionFacade;
	
	@EJB
	private IFalloAlertaFacade  falloAlertaFacade; 
	 
	@Override
	 public void generarLogCaso() throws Exception {
		userTransaction.begin();
		notificacionFacade.generarLogAlerta("Funciono", UtilDominios.ESTADO_NOTIFICACION_FALLO_EJECUCION,1L, null, new Date());
		userTransaction.commit();
		
	}
	
	@Override
	public void generarLogAlerta(String texto, String estado, Long idAlerta, Long idProgramacion,String codigoEjecucion){
		try{
			logger.info("guardando log alerta idAlerta:" + idAlerta);
			userTransaction.begin();
			if (UtilDominios.ESTADO_NOTIFICACION_FALLO_EJECUCION.equals(codigoEjecucion)
					|| UtilDominios.ESTADO_NOTIFICACION_METODO_NO_ENCONTRADO.equals(codigoEjecucion))
				notificacionFacade.generarLogAlerta(texto, estado, idAlerta, null, null);				
			falloAlertaFacade.crearFalloAlertas(idAlerta, idProgramacion, codigoEjecucion);
			userTransaction.commit();
			
		}catch(Exception e) {
			try {
				if(userTransaction.getStatus() == 0){
					userTransaction.rollback();
				}
			} catch (IllegalStateException | SecurityException | SystemException e1) {	
				logger.error("Error: ", e1);
			}
			
		}

	}
	
	public void modificarEstadoProgrmacion(){
		
	}
	

}
