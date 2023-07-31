package com.ccb.simasc.rest.recursos.configuracion;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Clase de configuración de aplicación para API REST 
 * @author sMartinez
 *
 */
@ApplicationPath("/*")
public class SIMASCConfiguracion extends  Application {
	 /**
     * logger
     */
    private static final Logger LOG = LogManager.getLogger(SIMASCConfiguracion.class);
	
    public SIMASCConfiguracion() {
        super();
    }

    /**
     * 
     */
    @Override
    public java.util.Set<Object> getSingletons() {
    	return new HashSet<Object>();
    }
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        LOG.info("Registrando Recursos...");
        //Registro de recursos
        classes.add(com.ccb.simasc.rest.recursos.AcuseRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.AreaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.AsuntoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.AudienciaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.CasoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ClasificacionRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ConvenioRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.CorreoElectronicoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.DetallePagoCasoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.DocumentoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.DominioRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ElegibleRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.EventoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.EventoRolPersonaCasoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.EstadoPersonaTipoServicioRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.EstadoPersonaRolRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.FechasCasoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.GestorDocumentalRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ListaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.MateriaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.NombramientoPersonaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.PagoCasoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.PagoHonorariosRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.PersonaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.PersonaServicioMateriaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.PronunciamientoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ProfesionRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.RefrigerioRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.RequisitoListaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.RequisitoPersonaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.RequisitoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.RolPersonaCasoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.RolPersonaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.RolRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.SalaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.SedeRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ServicioMateriaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ServicioRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.SorteoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.TelefonoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.TelefonoSedeRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.TipoZonaGeograficaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.TranscripcionRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.UbicacionRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.UsuarioRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ZonaGeograficaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.MateriaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.SedeRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ServicioRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.DetallePagoCasoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.DocumentoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.EventoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.PlantillaCartaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.InvitadoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.CartaPersonaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.CorreoRolPersonaCasoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.RecusacionRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.FechaCasoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.AgendamientoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.LogisticaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.InfraestructuraRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.SeguridadRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ParametrosGeneralesRecurso.class); 
        classes.add(com.ccb.simasc.rest.recursos.HonorariosFijadosRecurso.class); 
        classes.add(com.ccb.simasc.rest.recursos.GastoTribunalRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.AlmacenamientoDocumentosRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.SuspensionRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.NotificacionDocumentoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.CarpetaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.LlamadaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.RecursoLaudoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.LaudoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ContestacionDemandaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ParametrizacionTarifasRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.DistribucionTarifaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.SolicitudServicioRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.FuncionalidadRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.TipoServicioSedeRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.PersonaSolicitudRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.NormaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.DesarrolloProfesionalRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.CentroRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.AgendaPersonaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.AuditoriaSistemaExternoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ConvenioCentroRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.DetalleReliquidacionRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.DiaFestivoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.DisponibilidadPersonaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.MembresiaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.RelacionadoConvenioRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ReliquidacionRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.SedeConvenioRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ServicioSedeRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ParametroDeServicioRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.PublicacionRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.PersonaSedeRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.AgrupamientoRolRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ReportesConciliacionRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.TagPlantillaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.AlertaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.TurnoJornadaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.SolicitudProrrogaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.PeticionRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.PartePeticionRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.FacturacionCasoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ObligacionParteRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ObligacionRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.AsesoriaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.SolicitudPrestadorRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ContratoConvenioRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.EventoCcbRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.AreaAsuntoClasificacionRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ResultadoAudienciaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ConvenioRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.TarifaContratoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.DisponibilidadPersonaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.Pruebas.class);

        classes.add(com.ccb.simasc.rest.recursos.EvaluacionConciliadorRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.DetalleEvaluacionConciliadorRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.EntregaDocumentoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.DevolucionDocumentoResultadoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.InasistenciaRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.TipoDeServicioRolRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.FirmaDocumentoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ParametrizarServicioRolRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.UbicacionRolPersonaCasoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.AutorizacionTratamientoDatosRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.DiaSorteoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.HistoricoExpedienteRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.LoteGeneradoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.PersonaLoteRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.CuadernoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.CorrerTrasladoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.DocumentoObligatorioRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.MotivoHabilitacionArbitroRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.HomologacionSistemaExternoRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.CaptchaVerificaSitioRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.TrmRecurso.class);
        classes.add(com.ccb.simasc.rest.recursos.ParametrizacionTarifasInternacionalesRecurso.class);  
        
        //

        // Exception mappers
        LOG.info("Recording features...");
        // Features
        classes.add(com.ccb.simasc.rest.recursos.configuracion.ProveedorGeneralMapeo.class);
        classes.add(com.ccb.simasc.rest.recursos.configuracion.JWTFilter.class);
        classes.add(com.ccb.simasc.rest.recursos.configuracion.manejoDeErrores.MapeadorGeneralDeExcepciones.class);
        classes.add(com.ccb.simasc.rest.transversal.excepciones.SIMASCRecursosRESTExcepcionMapper.class);
        LOG.info("Aplicación configurada correctamente.");
        return classes;
    }
       
}
