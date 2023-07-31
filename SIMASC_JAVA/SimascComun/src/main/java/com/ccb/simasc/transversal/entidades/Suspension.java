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

// protected region imports entidad end


/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="SUSPENSION")
@NamedQuery(name = "Suspension.findAll", query = "SELECT p FROM Suspension p")
public class Suspension implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta seccion los atributos adicionales

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_SUSPENSION_PK = "idSuspension";
	public static final String ENTIDAD_SUSPENSION_TIPO = "tipo";
	public static final String ENTIDAD_SUSPENSION_FECHA_INICIAL = "fechaInicial";
	public static final String ENTIDAD_SUSPENSION_FECHA_FINAL = "fechaFinal";
	public static final String ENTIDAD_SUSPENSION_MOTIVO = "motivo";
	public static final String ENTIDAD_SUSPENSION_QUIEN_SUSPENDE = "quienSuspende";
	public static final String ENTIDAD_SUSPENSION_DEVOLUCION_EXPEDIENTE_A_CAC = "devolucionExpedienteACac";
	public static final String ENTIDAD_SUSPENSION_ID_CASO = "idCaso";
	public static final String ENTIDAD_SUSPENSION_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_SUSPENSION_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_SUSPENSION_ESTADO_REGISTRO_SUSPENSION = "estadoRegistroSuspension";			
	public static final String ENTIDAD_SUSPENSION_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_SUSPENSION
            = {ENTIDAD_SUSPENSION_FECHA_FINAL, ENTIDAD_SUSPENSION_ID_USUARIO_MODIFICACION, ENTIDAD_SUSPENSION_FECHA_ULTIMA_MODIFICACION, ENTIDAD_SUSPENSION_ESTADO_REGISTRO, ENTIDAD_SUSPENSION_ID_CASO, ENTIDAD_SUSPENSION_FECHA_INICIAL, ENTIDAD_SUSPENSION_TIPO, ENTIDAD_SUSPENSION_DEVOLUCION_EXPEDIENTE_A_CAC, ENTIDAD_SUSPENSION_PK, ENTIDAD_SUSPENSION_MOTIVO,ENTIDAD_SUSPENSION_QUIEN_SUSPENDE};

	@Id
    @Column(name="id_suspension")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idSuspension;
    
	@Column(name="tipo")
	private String tipo;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_inicial")
	private Date fechaInicial;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_final")
	private Date fechaFinal;		
    
	@Column(name="motivo")
	private String motivo;		
    
	@Column(name="devolucion_expediente_a_cac")
	private boolean devolucionExpedienteACac;		
    
	@Column(name="id_caso")
	private Long idCaso;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;
	
	@Column(name="quien_suspende")
	private String quienSuspende;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		

	@ManyToOne
	@JoinColumn(name="id_caso", referencedColumnName="id_caso", insertable = false, updatable = false)
    private Caso caso;
		
	@OneToMany(mappedBy="suspension")
    private List<PersonaSuspension> personaSuspensionList;
	
	
    public Suspension(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdSuspension(){
		return this.idSuspension;
	}
	
	public void setIdSuspension(Long idSuspension){
		this.idSuspension = idSuspension;
	}
	
	public String getTipo(){
		return this.tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
		
	public Date getFechaInicial(){
		return this.fechaInicial;
	}
	
	public void setFechaInicial(Date fechaInicial){
		this.fechaInicial = fechaInicial;
	}
		
	public Date getFechaFinal(){
		return this.fechaFinal;
	}
	
	public void setFechaFinal(Date fechaFinal){
		this.fechaFinal = fechaFinal;
	}
		
	public String getMotivo(){
		return this.motivo;
	}
	
	public void setMotivo(String motivo){
		this.motivo = motivo;
	}
		
	public boolean getDevolucionExpedienteACac(){
		return this.devolucionExpedienteACac;
	}
	
	public void setDevolucionExpedienteACac(boolean devolucionExpedienteACac){
		this.devolucionExpedienteACac = devolucionExpedienteACac;
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
		

    public List<PersonaSuspension> getPersonaSuspensionList(){
		return this.personaSuspensionList;
	}
	
	public void setPersonaSuspensionList(List<PersonaSuspension> personaSuspensionList){
		this.personaSuspensionList = personaSuspensionList;
	}
			
    public Caso getCaso(){
		return this.caso; 
	}
	
	public void setCaso(Caso caso){
		this.caso = caso;
	}
	
	public String getQuienSuspende() {
		return quienSuspende;
	}


	public void setQuienSuspende(String quienSuspende) {
		this.quienSuspende = quienSuspende;
	}


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_SUSPENSION) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idSuspension);        
        hash = 37 * hash + Objects.hashCode(this.tipo);
        hash = 37 * hash + Objects.hashCode(this.fechaInicial);
        hash = 37 * hash + Objects.hashCode(this.fechaFinal);
        hash = 37 * hash + Objects.hashCode(this.motivo);
        hash = 37 * hash + (this.devolucionExpedienteACac ? 0 : 1);
        hash = 37 * hash + Objects.hashCode(this.idCaso);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.quienSuspende);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Suspension que se pasa
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
        final Suspension other = (Suspension) obj;
        
        if (!Objects.equals(this.idSuspension, other.idSuspension)) {
            return false;
        }
        
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicial, other.fechaInicial)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFinal, other.fechaFinal)) {
            return false;
        }
        
        if (!Objects.equals(this.motivo, other.motivo)) {
            return false;
        }
        
        if (!Objects.equals(this.devolucionExpedienteACac, other.devolucionExpedienteACac)) {
            return false;
        }
        
        if (!Objects.equals(this.idCaso, other.idCaso)) {
        	return false;
        }
        if (!Objects.equals(this.quienSuspende, other.quienSuspende)) {
            return false;
        }
        
        if (!Objects.equals(this.idUsuarioModificacion, other.idUsuarioModificacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaUltimaModificacion, other.fechaUltimaModificacion)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta seccion sus modificaciones

	// protected region metodos adicionales end

} 

