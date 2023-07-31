//package co.org.ccb.simasc.tareas.programadas;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import javax.ejb.DependsOn;
//import javax.ejb.EJB;
//import javax.ejb.ScheduleExpression;
//import javax.ejb.Singleton;
//import javax.ejb.Startup;
//import javax.ejb.Timeout;
//import javax.ejb.Timer;
//import javax.ejb.TimerConfig;
//import javax.ejb.TimerService;
//
//import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
//import com.ccb.simasc.transversal.utilidades.UtilParamGenerales;
//
//
//
///**
// * Configuracion del timer principal
// * @author lvalbuena
// *
// */
//@Singleton
//@Startup
//@DependsOn("CartasPendientesImpresion")
//public class CartasPendientesImpresionConfig {
//	
//	private final static String TIMER_BASE = "TAREA CONFIG";
//	private final static long INITIALDURATION  = 120000;	
//	private final static long INTERVALDURATION = 18000000; //se ejecuta cada 5 horas 
//
//	/**
//	 * Timer Java que permite crear las tareas
//	 */
//	@Resource
//	private TimerService timerService;
//
//	/**
//	 * Representa la tarea programada de cartas pendientes de impresion
//	 */
//	@EJB
//	private CartasPendientesImpresion cartasPendientesImpresion;
//
//	/**
//	 * Se alamacena la programacion de la tarea
//	 */
//	private ScheduleExpression schedule;
//
//	/**
//	 * Parametros generales que contienen la informacion de la programacion
//	 * del timer de negocio
//	 */
//	private List<ParametrosGenerales> listParametros;
//
//	
//
//	/**
//	 * Parametrizacion inicial de las tareas
//	 */
//	@PostConstruct
//	public void init() {
//		/*
//		 * Se crea el timer base que valida si la base de datos cambio 
//		 * con respecto a la progrmacion del timer de negocio
//		 */
//		TimerConfig timerConfig = new TimerConfig();
//		timerConfig.setInfo(TIMER_BASE);
//		timerConfig.setPersistent(false);
//		timerService.createIntervalTimer(INITIALDURATION, INTERVALDURATION, timerConfig);		
//		
//		//crea el timer de negocio basado en la informacion de la BD
//		listParametros = cartasPendientesImpresion.cargarParametros();
//		this.generarProgramacion();
//		this.reinitializeTimer(UtilParamGenerales.TAREA_PROGRAMADA_CARTAS_PENDIENTES);		
//	}
//
//	/**
//	 * Ejecucion del timer
//	 * 
//	 * @param timer
//	 */
//	@Timeout
//	public void execute(Timer timer) {
////		System.out.println("========= EJECUTA TIMER:" + new Date() + " =========");			
//		if (UtilParamGenerales.TAREA_PROGRAMADA_CARTAS_PENDIENTES.equals(timer.getInfo())) {
//			this.ejecutarTarea();
//		}		
//		if (this.tareaCambio()) {
//			this.generarProgramacion();
//			this.reinitializeTimer(UtilParamGenerales.TAREA_PROGRAMADA_CARTAS_PENDIENTES);
//		}
//	}
//
//	/**
//	 * Metodo que ejecuta la tarea de negocio de consulta de cartas 
//	 * y envio de email
//	 */
//	private void ejecutarTarea() {
////		System.out.println("========= EJECUTA TAREA OK: " + new Date() + "===========");
//		cartasPendientesImpresion.consultarCartasAudienciaPendientesImpresion(); 
//	}
//
//	/**
//	 * Valida si los parametros de la tarea cambiaron
//	 * @return true si cambiaron, false de lo contrario
//	 */
//	private boolean tareaCambio() {
//		boolean cambio = false;
//		listParametros = cartasPendientesImpresion.cargarParametros();
//		for (ParametrosGenerales param : listParametros) {
//			if (param.getCodigo().equals(UtilParamGenerales.TAREA_PROGRAMADA_CARTAS_PENDIENTES_DIA)) {
//				if (!param.getValorTexto().equalsIgnoreCase(schedule.getDayOfWeek()))
//					cambio = true;
//			} else if (param.getCodigo().equals(UtilParamGenerales.TAREA_PROGRAMADA_CARTAS_PENDIENTES_HORA)) {
//				if (!param.getValorTexto().equalsIgnoreCase(schedule.getHour()))
//					cambio = true;
//			} else if (param.getCodigo().equals(UtilParamGenerales.TAREA_PROGRAMADA_CARTAS_PENDIENTES_MINUTO)) {
//				if (!param.getValorTexto().equalsIgnoreCase(schedule.getMinute()))
//					cambio = true;
//			} else if (param.getCodigo().equals(UtilParamGenerales.TAREA_PROGRAMADA_CARTAS_PENDIENTES_SEGUNDO)) {
//				if (!param.getValorTexto().equalsIgnoreCase(schedule.getSecond()))
//					cambio = true;
//			}
//		}
////		System.out.println("==== TAREA: Validacion tarea, cambio: " + cambio);
//		return cambio;
//	}
//
//	/**
//	 * Genera la programacion de la tarea
//	 * 
//	 * @return
//	 */
//	private void generarProgramacion() {
//		schedule = new ScheduleExpression();
//
//		for (ParametrosGenerales param : listParametros) {
//			//System.out.print("==== TAREA: Programacion: " + param.getValorTexto() + ";");
//			if (param.getCodigo().equals(UtilParamGenerales.TAREA_PROGRAMADA_CARTAS_PENDIENTES_DIA))
//				schedule.dayOfWeek(param.getValorTexto());
//			if (param.getCodigo().equals(UtilParamGenerales.TAREA_PROGRAMADA_CARTAS_PENDIENTES_HORA))
//				schedule.hour(param.getValorTexto());
//			if (param.getCodigo().equals(UtilParamGenerales.TAREA_PROGRAMADA_CARTAS_PENDIENTES_MINUTO))
//				schedule.minute(param.getValorTexto());
//			if (param.getCodigo().equals(UtilParamGenerales.TAREA_PROGRAMADA_CARTAS_PENDIENTES_SEGUNDO))
//				schedule.second(param.getValorTexto());
//		}
//	}
//
//	/**
//	 * Cancela el timer enviado como parametro
//	 * 
//	 * @param timerInfo
//	 */
//	public void cancelTimer(String timerInfo) {
//		if (timerService.getTimers() != null) {
//			for (Timer timer : timerService.getTimers()) {
//				if (timerInfo.equals(timer.getInfo())) {
//					timer.cancel();
//				}
//			}
//		}
//	}
//
//	/**
//	 * Crea un nuevo timer
//	 * 
//	 * @param timerInfo
//	 */
//	public void createTimer(String timerInfo) {
//		TimerConfig timerConfig = new TimerConfig();
//		timerConfig.setInfo(timerInfo);
//		timerConfig.setPersistent(false);
//		timerService.createCalendarTimer(this.schedule, timerConfig);
//	}
//
//	/**
//	 * Reinicia el timer
//	 * 
//	 * @param timerInfo
//	 */
//	public void reinitializeTimer(String timerInfo) {
//		cancelTimer(timerInfo);
//		createTimer(timerInfo);
//	}
//}