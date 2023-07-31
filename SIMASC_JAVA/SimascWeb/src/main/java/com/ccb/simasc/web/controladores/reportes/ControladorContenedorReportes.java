package com.ccb.simasc.web.controladores.reportes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ccb.simasc.transversal.dto.reportes.ReporteContenedorDTO;
import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
/**
 * Controlador de vista contenedor de reportes 
 * @author smartinez
 */
@ManagedBean( name = "controladorContenedorReportes")
@ViewScoped
public class ControladorContenedorReportes {
  
	private static final long serialVersionUID = 1L;
	
   private Map<String,String> reportes= new HashMap<>();
   private List<ReporteContenedorDTO> reporteDTO= new ArrayList<ReporteContenedorDTO>();
   private String reporteSeleccionado = "Seleccione un reporte";
   private String reporteUrl = "";
	/**
    * Constructor por defecto
    */
   public ControladorContenedorReportes() {
   }  
   
   @PostConstruct
   public void init() { 
	   String baseUrl = new String("/arbitraje/reportes/");
	   
	   ReporteContenedorDTO reporte1=new ReporteContenedorDTO();
	   reporte1.setClave("Aspirantes");
	   reporte1.setValor(baseUrl+("reporteAspirantes.xhtml").toString());
	   reporteDTO.add(reporte1);
	   
	   ReporteContenedorDTO reporte2=new ReporteContenedorDTO();
	   reporte2.setClave("Audiencias");
	   reporte2.setValor(baseUrl+("reporteAudiencias.xhtml").toString());
	   reporteDTO.add(reporte2);
	   
	   ReporteContenedorDTO reporte3=new ReporteContenedorDTO();
	   reporte3.setClave("Audiencias para programación de refrigerios");
	   reporte3.setValor(baseUrl+("reporteAudienciasProgramacionRefrigerios.xhtml").toString());
	   reporteDTO.add(reporte3);
	   
	   ReporteContenedorDTO reporte4=new ReporteContenedorDTO();
	   reporte4.setClave("Auxiliar promedio de transcripciones");
	   reporte4.setValor(baseUrl+("reporteAuxiliarPromedioTranscripciones.xhtml").toString());
	   reporteDTO.add(reporte4);
	   
	   ReporteContenedorDTO reporte5=new ReporteContenedorDTO();
	   reporte5.setClave("Cambio de estado de operadores");
	   reporte5.setValor(baseUrl+("reporteCambioEstadoOperadores.xhtml"));
	   reporteDTO.add(reporte5);
	   
	   ReporteContenedorDTO reporte6=new ReporteContenedorDTO();
	   reporte6.setClave("Casos aceptados y rechazados por secretario");
	   reporte6.setValor(baseUrl+("reporteCasosAceptadosRechazadosSecretario.xhtml").toString());
	   reporteDTO.add(reporte6);
	   
	   ReporteContenedorDTO reporte7=new ReporteContenedorDTO();
	   reporte7.setClave("Casos asignados a un secretario");
	   reporte7.setValor(baseUrl+("reporteCasoSecretario.xhtml").toString());
	   reporteDTO.add(reporte7);
	   
	   ReporteContenedorDTO reporte8=new ReporteContenedorDTO();
	   reporte8.setClave("Casos cerrados");
	   reporte8.setValor(baseUrl+("reporteCasosCerrados.xhtml").toString());
	   reporteDTO.add(reporte8);	   
	   
	   ReporteContenedorDTO reporte9=new ReporteContenedorDTO();
	   reporte9.setClave("Casos pendientes sorteo");
	   reporte9.setValor(baseUrl+("reporteCasosPendientesSorteoPublicoDesignacion.xhtml").toString());
	   reporteDTO.add(reporte9);
	   
	   ReporteContenedorDTO reporte10=new ReporteContenedorDTO();
	   reporte10.setClave("Casos por operador");
	   reporte10.setValor(baseUrl+("reporteCasosPorArbitro.xhtml").toString());
	   reporteDTO.add(reporte10);

	   ReporteContenedorDTO reporte11=new ReporteContenedorDTO();
	   reporte11.setClave("Casos por parte");
	   reporte11.setValor(baseUrl+("reporteCasoParte.xhtml").toString());
	   reporteDTO.add(reporte11);
	   
	   ReporteContenedorDTO reporte12=new ReporteContenedorDTO();
	   reporte12.setClave("Casos sorteados");
	   reporte12.setValor(baseUrl+("reporteCasosSorteados.xhtml").toString());
	   reporteDTO.add(reporte12);
	   
	   ReporteContenedorDTO reporte13=new ReporteContenedorDTO();
	   reporte13.setClave("Casos suspendidos");
	   reporte13.setValor(baseUrl+("reporteCasosSuspendidos.xhtml").toString());
	   reporteDTO.add(reporte13);
	   
	   ReporteContenedorDTO reporte14=new ReporteContenedorDTO();
	   reporte14.setClave("Cuantía de las pretensiones");
	   reporte14.setValor(baseUrl+("reporteCasoCuantia.xhtml").toString());
	   reporteDTO.add(reporte14);

	   ReporteContenedorDTO reporte15=new ReporteContenedorDTO();
	   reporte15.setClave("Digitalización");
	   reporte15.setValor(baseUrl+("reporteDigitalizacion.xhtml").toString());
	   reporteDTO.add(reporte15);
	   
	   ReporteContenedorDTO reporte16=new ReporteContenedorDTO();
	   reporte16.setClave("Estado actual operador");
	   reporte16.setValor(baseUrl+("reporteDeArbitros.xhtml").toString());
	   reporteDTO.add(reporte16);

	   ReporteContenedorDTO reporte17=new ReporteContenedorDTO();
	   reporte17.setClave("Estado de operadores");
	   reporte17.setValor(baseUrl+("reporteEstadosArbitro.xhtml").toString());
	   reporteDTO.add(reporte17);
	   
	   ReporteContenedorDTO reporte18=new ReporteContenedorDTO();
	   reporte18.setClave("General de casos");
	   reporte18.setValor(baseUrl+("reporteGeneralArbitraje.xhtml").toString());
	   reporteDTO.add(reporte18);
	   
	   ReporteContenedorDTO reporte19=new ReporteContenedorDTO();
	   reporte19.setClave("Ingresos");
	   reporte19.setValor(baseUrl+("reporteIngresos.xhtml").toString());
	   reporteDTO.add(reporte19);

	   ReporteContenedorDTO reporte20=new ReporteContenedorDTO();
	   reporte20.setClave("Liberacion de lista");
	   reporte20.setValor(baseUrl+("reporteLiberacionLista.xhtml"));
	   reporteDTO.add(reporte20);

	   ReporteContenedorDTO reporte21=new ReporteContenedorDTO();
	   reporte21.setClave("Operadores disponibles para sorteo");
	   reporte21.setValor(baseUrl+("reporteArbitrosDisponiblesSorteo.xhtml").toString());
	   reporteDTO.add(reporte21);
	     
	   ReporteContenedorDTO reporte22=new ReporteContenedorDTO();
	   reporte22.setClave("Operadores seleccionados en sorteo público");
	   reporte22.setValor(baseUrl+("reporteDeArbitrosDisponiblesParaSorteo.xhtml").toString());
	   reporteDTO.add(reporte22);

	   ReporteContenedorDTO reporte23=new ReporteContenedorDTO();
	   reporte23.setClave("Operadores suspendidos");
	   reporte23.setValor(baseUrl+("reporteOperadoresSuspendidos.xhtml"));
	   reporteDTO.add(reporte23);
	   
	   ReporteContenedorDTO reporte24=new ReporteContenedorDTO();
	   reporte24.setClave("Radicacion de documentos");
	   reporte24.setValor(baseUrl+("reporteRadicacionDocumentos.xhtml"));
	   reporteDTO.add(reporte24);
	   
	   ReporteContenedorDTO reporte25=new ReporteContenedorDTO();
	   reporte25.setClave("Reparto por abogado");
	   reporte25.setValor(baseUrl+("reporteRepartoPorAbogado.xhtml").toString());
	   reporteDTO.add(reporte25);
	   
	   ReporteContenedorDTO reporte26=new ReporteContenedorDTO();
	   reporte26.setClave("Salas ocupadas");
	   reporte26.setValor(baseUrl+("reporteSalasOcupadas.xhtml").toString());
	   reporteDTO.add(reporte26);
	   
	   ReporteContenedorDTO reporte27=new ReporteContenedorDTO();
	   reporte27.setClave("Secretarios");
	   reporte27.setValor(baseUrl+("reporteSecretarios.xhtml").toString());
	   reporteDTO.add(reporte27);
	   
	   ReporteContenedorDTO reporte28=new ReporteContenedorDTO();
	   reporte28.setClave("Transcripciones");
	   reporte28.setValor(baseUrl+("reporteTranscripciones.xhtml").toString());
	   reporteDTO.add(reporte28);
	   	      
   }
   // Setters and getters
   public List<String> getReportes(){
	   ArrayList<String> reports = new ArrayList<>(reportes.keySet());
	   Collections.sort(reports);
	   return reports;
   }
   
   public String getReporteUrl(){
	   return this.reporteUrl; 
   }
   
   public void setReporteUrl(String url){
	   this.reporteUrl = url; 
   }
   
   public void setReporteSeleccionado(String selected){
	   if(!selected.equals("")){
		   this.reporteSeleccionado = selected;
		   this.reporteUrl=selected;		      
	   }else
	   {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, UtilConstantes.ENCABEZADO_MENSAJE_ADVERTENCIA,
					MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.INFO060.toString())));
	   }
	   
   }
   
   public String getReporteSeleccionado(){
	   return this.reporteSeleccionado;
   }
   
   public void reiniciarUrl(){
	   this.reporteUrl = "";
   }
   
   public void handleChange(){  
   }
   
   

   public List<ReporteContenedorDTO> getReporteDTO() {	
	   ArrayList<String> reports = new ArrayList<>(reportes.keySet());
	   Collections.sort(reports);
	return reporteDTO;
   }

	public void setReporteDTO(List<ReporteContenedorDTO> reporteDTO) {
		this.reporteDTO = reporteDTO;
	}


   
   
   
}
