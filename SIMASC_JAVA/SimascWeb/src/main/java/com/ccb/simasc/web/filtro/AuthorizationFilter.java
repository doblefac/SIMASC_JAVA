package com.ccb.simasc.web.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {
	
    /** Objeto de configuración de filtro. */
    FilterConfig filterConfig;

	@Override
	public void destroy() {
		this.filterConfig = null;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
//		filterChain.doFilter(request, response);

		String requestedSessionId = request.getRequestedSessionId();
		String activeSessionId = request.getSession().getId();

		boolean validSession = activeSessionId.equals(requestedSessionId);
		String requestedURI = request.getRequestURI();

		if (validSession && (requestedURI.indexOf("/index.xhtml") >= 0)
				|| (request.getSession() != null && request.getSession().getAttribute("USERNAME") != null)
				|| requestedURI.indexOf("/public/") >= 0 || requestedURI.contains("javax.faces.resource")) {
			filterChain.doFilter(request, response);
		}else if( !validSession && (requestedURI.indexOf("/login.xhtml") < 0) ) {
			response.sendRedirect(request.getContextPath() + "/faces/login.xhtml");
		}
		
		if(requestedURI.indexOf("/login.xhtml")>= 0){
			filterChain.doFilter(request, response);	
		}						

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
