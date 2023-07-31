package com.ccb.simasc.rest.recursos.configuracion;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;

import org.apache.logging.log4j.LogManager;
import com.ccb.simasc.comun.seguridad.fachada.interfaces.ISeguridadFacade;
import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;
import com.ccb.simasc.transversal.utilidades.ApplicationRequestContext;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;
/**
 * Filtro de seguridad y validación de JWT (Java Web Token)
 * @author sMartinez
 *
 */
@WebFilter(urlPatterns={"/*"})
public class JWTFilter implements Filter {
	//Fachada de Seguridad
	@EJB
	private ISeguridadFacade seguridadFacade;
	
	@Inject
    private ApplicationRequestContext appContext;
	
	private String currentStage = null;
	
	public void doFilter(ServletRequest request,
                             ServletResponse response,
                             FilterChain chain) throws IOException, ServletException {
		
		try {
			appContext.setContextoSesion(ContextoDeSesion.obtenerContextoDeSesion(((HttpServletRequest) request)));
		} catch (ClassCastException ex) {
			// Si no existe el contexto de sesion en la petición
			LogManager.getLogger(getClass()).error(ex.getMessage());
		}
		
		if(currentStage==null){
			String[] projectProperties = {UtilConstantes.PROJECT_STAGE};
	        Map<String,Object> projectStage = UtilConstantes.getProjectProperties(Arrays.asList(projectProperties));
			currentStage = (String) projectStage.get(UtilConstantes.PROJECT_STAGE);
		}
        if(currentStage!=null 
				&& currentStage.contentEquals(UtilConstantes.PROJECT_STAGE_DEV)){
			chain.doFilter(request, response);
			return;
        }
		HttpServletRequest req = (HttpServletRequest) request;
        String path = ((HttpServletRequest) request).getRequestURI();
        // Manejo de Urls exepcionales de sistema
        if (verificarPath(path)){
        	chain.doFilter(request, response);
        	return;
        }
        String jwt = req.getHeader(HttpHeaders.AUTHORIZATION);
        //Validación de JWT
        if(validateToken(jwt)) {
            chain.doFilter(request, response);
            return;
        }
        else {
        	//Envío de status de no-autorización (403-forbidden)
            HttpServletResponse resp = (HttpServletResponse)response;
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
	
	/**
	 * Verifica que la ruta de la petición se trata de una ruta pública autorizada 
	 * @param path
	 * @return
	 */
	 private boolean verificarPath(String path) {
			Boolean response = false;
			for(String urlAdmitida : UtilConstantes.URLS_ADMITIDAS)
				response = response || path.contains(urlAdmitida);
			
			return response;
		}
	 
	/**
	 * Validación de token JWT usando llave de cifrado y reglas de vencimiento
	 * @param jwt
	 * @return
	 */
	private boolean validateToken(String jwt) {
			return (jwt!=null)?seguridadFacade.validarJWT(jwt):false;
			
	}
	
	
	

		@Override
		public void init(FilterConfig filterConfig) throws ServletException {}

		@Override
		public void destroy() {}
}
