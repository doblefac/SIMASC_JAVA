//package co.org.ccb.simasc.tareas.programadas;
//
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.ejb.Singleton;
//
//import com.ccb.simasc.integracion.manejadores.ManejadorCartaPersona;
//import com.ccb.simasc.integracion.manejadores.ManejadorParametrosGenerales;
//import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
//import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;
//
///**
// * Representa la tarea programada para envio de correo de cartas
// * pendientes de impresion
// * @author lvalbuena
// *
// */
//@Singleton
//public class CartasPendientesImpresion {
//
//	/**
//	 * Se obtienen los parametros generales del sistema
//	 */
//	@EJB
//	private ManejadorParametrosGenerales parametrosGeneralesFacade;
//	
//	/**
//	 * Permite realizar la cosnulta y envio de correo
//	 */
//	@EJB
//	private ManejadorCartaPersona manejadorCartaPersona;
//
//	 
//	 
//	 /**
//	  * 
//	  */
//	 @PostConstruct
//	 public void init() {
//		 
//	 }
//
//	/**
//	 * Realiza la carga de los parametros de la DB
//	 * que contienen la informacion de programacion de la tarea
//	 */
//	public List<ParametrosGenerales> cargarParametros() {
//		return parametrosGeneralesFacade.obtenerParametrosPorTipo(
//				UtilParamGenerales.TAREA_PROGRAMADA_CARTAS_PENDIENTES);
//	}
//	
//	/**
//	 * Metodo de negocio que se invoca cuando la tarea se ejecuta
//	 */
//	public void consultarCartasAudienciaPendientesImpresion () {
//		manejadorCartaPersona.evioCorreoCartasPendientesImpresion();
//	}
//}
