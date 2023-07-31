package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.NamedQuery; 
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="MOTIVO_DEVOLUCION")
@NamedQuery(name = "MotivoDevolucion.findAll", query = "SELECT p FROM MotivoDevolucion p")
public class MotivoDevolucion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
			
	public static final String ENTIDAD_MOTIVO_DEVOLUCION_PK_ID_DEVOLUCION = "motivoDevolucionPK.idDevolucion";
			
	public static final String ENTIDAD_MOTIVO_DEVOLUCION_PK_MOTIVO = "motivoDevolucionPK.motivo";
    private static final String[] ATRIBUTOS_ENTIDAD_MOTIVO_DEVOLUCION
            = {ENTIDAD_MOTIVO_DEVOLUCION_PK_MOTIVO, ENTIDAD_MOTIVO_DEVOLUCION_PK_ID_DEVOLUCION};

	@EmbeddedId
	private MotivoDevolucionPK motivoDevolucionPK;

	@ManyToOne
	@JoinColumn(name="id_devolucion", referencedColumnName="id_devolucion_documento_resultado", insertable = false, updatable = false)
    private DevolucionDocumentoResultado devolucionDocumentoResultado;
		
	
	
    public MotivoDevolucion(){
		motivoDevolucionPK = new MotivoDevolucionPK();
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public MotivoDevolucionPK getMotivoDevolucionPK(){
		return this.motivoDevolucionPK;
	}
	
	public void setMotivoDevolucionPK(MotivoDevolucionPK motivoDevolucionPK){
		this.motivoDevolucionPK   = motivoDevolucionPK ;
	}  
	

    public DevolucionDocumentoResultado getDevolucionDocumentoResultado(){
		return this.devolucionDocumentoResultado; 
	}
	
	public void setDevolucionDocumentoResultado(DevolucionDocumentoResultado devolucionDocumentoResultado){
		this.devolucionDocumentoResultado = devolucionDocumentoResultado;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_MOTIVO_DEVOLUCION) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.motivoDevolucionPK);        
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad MotivoDevolucion que se pasa
     * como parametro comprobando que comparten los mismos valores en cada uno
     * de sus atributos. Solo se tienen en cuenta los atributos simples, es
     * decir, se omiten aquellos que definen una relacion con otra tabla.
     *
     * @param obj Instancia de la categoría a comprobar
     * iguales.
     * @return Verdadero si esta instancia y la que se pasan como parametros son
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MotivoDevolucion other = (MotivoDevolucion) obj;
        
        if (!Objects.equals(this.motivoDevolucionPK, other.motivoDevolucionPK)) {
            return false;
        }
        
        return true;
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

