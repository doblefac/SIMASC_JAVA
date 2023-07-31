package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta seccion sus modificaciones

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

import com.ccb.simasc.transversal.utilidades.UtilDominios;

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="LAUDO")
@NamedQuery(name = "Laudo.findAll", query = "SELECT p FROM Laudo p")
public class Laudo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_LAUDO_PK = "idLaudo";
	public static final String ENTIDAD_LAUDO_FECHA = "fecha";
	public static final String ENTIDAD_LAUDO_RESULTADO = "resultado";
	public static final String ENTIDAD_LAUDO_ID_CASO = "idCaso";
	public static final String ENTIDAD_LAUDO_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_LAUDO_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_LAUDO_ESTADO_REGISTRO_LAUDO = "estadoRegistroLaudo";			
	public static final String ENTIDAD_LAUDO_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_LAUDO_ID_DOCUMENTO = "idDocumento";
    private static final String[] ATRIBUTOS_ENTIDAD_LAUDO
            = {ENTIDAD_LAUDO_ID_CASO, ENTIDAD_LAUDO_PK, ENTIDAD_LAUDO_ID_DOCUMENTO, ENTIDAD_LAUDO_ID_USUARIO_MODIFICACION, ENTIDAD_LAUDO_FECHA_ULTIMA_MODIFICACION, ENTIDAD_LAUDO_ESTADO_REGISTRO, ENTIDAD_LAUDO_RESULTADO, ENTIDAD_LAUDO_FECHA};

	@Id
    @Column(name="id_laudo")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idLaudo;
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha")
	private Date fecha;		
    
	@Column(name="resultado")
	private String resultado;		
    
	@Column(name="id_caso")
	private Long idCaso;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_documento")
	private Long idDocumento;		

	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
		
	@ManyToOne
	@JoinColumn(name="id_documento", referencedColumnName="id_documento", insertable = false, updatable = false)
    private Documento documento;
		
	@OneToMany(mappedBy="laudo")
    private List<RecursoLaudo> recursoLaudoList;
	
	
    public Laudo(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdLaudo(){
		return this.idLaudo;
	}
	
	public void setIdLaudo(Long idLaudo){
		this.idLaudo = idLaudo;
	}
	
	public Date getFecha(){
		return this.fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
		
	public String getResultado(){
		return this.resultado;
	}
	
	public void setResultado(String resultado){
		this.resultado = resultado;
	}
		
	public Long getIdCaso(){
		return this.idCaso;
	}
	
	public void setIdCaso(Long idCaso){
		this.idCaso = idCaso;
	}
		
	public String getIdUsuarioModificacion(){
		return this.idUsuarioModificacion;
	}
	
	public void setIdUsuarioModificacion(String idUsuarioModificacion){
		this.idUsuarioModificacion = idUsuarioModificacion;
	}
		
	public Date getFechaUltimaModificacion(){
		return this.fechaUltimaModificacion;
	}
	
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion){
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
		
	public String getEstadoRegistro(){
		return this.estadoRegistro;
	}
	
	public void setEstadoRegistro(String estadoRegistro){
		this.estadoRegistro = estadoRegistro;
	}
		
	public Long getIdDocumento(){
		return this.idDocumento;
	}
	
	public void setIdDocumento(Long idDocumento){
		this.idDocumento = idDocumento;
	}
		

    public List<RecursoLaudo> getRecursoLaudoList(){
		return this.recursoLaudoList;
	}
	
	public void setRecursoLaudoList(List<RecursoLaudo> recursoLaudoList){
		this.recursoLaudoList = recursoLaudoList;
	}
			
    public Caso getCaso(){
		return this.caso; 
	}
	
	public void setCaso(Caso caso){
		this.caso = caso;
	}
    public Documento getDocumento(){
		return this.documento; 
	}
	
	public void setDocumento(Documento documento){
		this.documento = documento;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_LAUDO) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idLaudo);        
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.resultado);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idDocumento);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Laudo que se pasa
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
        final Laudo other = (Laudo) obj;
        
        if (!Objects.equals(this.idLaudo, other.idLaudo)) {
            return false;
        }
        
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        
        if (!Objects.equals(this.resultado, other.resultado)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoRegistro, other.estadoRegistro)) {
            return false;
        }
        
        return Objects.equals(this.idDocumento, other.idDocumento);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones
    
    public String getEstado(){
    	if(recursoLaudoList.isEmpty()){
    		return UtilDominios.ESTADO_LAUDO_NO_ANULADO;
    	}else{
    		for(RecursoLaudo r:recursoLaudoList){
    			if(UtilDominios.RESULTADO_RECURSO_ANULACION_ANULADO.equals(r.getResultado())){
    				return UtilDominios.ESTADO_LAUDO_ANULADO;
    			}
    			if(UtilDominios.RESULTADO_RECURSO_ANULACION_PENDIENTE.equals(r.getResultado())){
    				return UtilDominios.ESTADO_LAUDO_PENDIENTE_ANULACION;
    			}
    			if(UtilDominios.RESULTADO_RECURSO_ANULACION_NO_ANULADO.equals(r.getResultado())){
    				return UtilDominios.ESTADO_LAUDO_NO_ANULADO;
    			}
    		}
    	}
    	return UtilDominios.ESTADO_LAUDO_NO_ANULADO;
    }

	// protected region metodos adicionales end

} 

