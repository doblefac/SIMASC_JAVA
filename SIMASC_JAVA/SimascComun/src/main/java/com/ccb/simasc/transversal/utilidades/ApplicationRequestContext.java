package com.ccb.simasc.transversal.utilidades;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

import com.ccb.simasc.comun.seguridad.servicio.implementacion.ContextoDeSesion;

@RequestScoped
public class ApplicationRequestContext implements Serializable {
    private ContextoDeSesion contextoSesion;

	public ContextoDeSesion getContextoSesion() {
		return contextoSesion;
	}

	public void setContextoSesion(ContextoDeSesion contextoSesion) {
		this.contextoSesion = contextoSesion;
	}
}
