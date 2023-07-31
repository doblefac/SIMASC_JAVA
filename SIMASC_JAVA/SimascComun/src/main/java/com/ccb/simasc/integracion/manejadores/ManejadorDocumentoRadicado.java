package com.ccb.simasc.integracion.manejadores;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.DocumentoRadicado;

@Stateless
public class ManejadorDocumentoRadicado extends ManejadorCrud<DocumentoRadicado,Long>{

	@PersistenceContext
	private transient EntityManager em;	
	
    public ManejadorDocumentoRadicado() {
        super(DocumentoRadicado.class);
    }        
}

