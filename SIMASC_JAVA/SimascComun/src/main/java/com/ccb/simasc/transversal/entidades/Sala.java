package com.ccb.simasc.transversal.entidades;
// protected region imports entidad on begin
// Escriba en esta sección sus modificaciones

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
@Table(name="SALA")
@NamedQuery(name = "Sala.findAll", query = "SELECT p FROM Sala p")
public class Sala implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// protected region atributos on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos end
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_SALA_PK = "idSala";
	public static final String ENTIDAD_SALA_NUMERO_SALA = "numeroSala";
	public static final String ENTIDAD_SALA_CAPACIDAD_DE_ASISTENTES = "capacidadDeAsistentes";
	public static final String ENTIDAD_SALA_TIPO_SERVICIO = "tipoServicio";
	public static final String ENTIDAD_SALA_ID_USUARIO_MODIFICACION = "idUsuarioModificacion";
	public static final String ENTIDAD_SALA_FECHA_ULTIMA_MODIFICACION = "fechaUltimaModificacion";
	public static final String ENTIDAD_SALA_ESTADO_REGISTRO_SALA = "estadoRegistroSala";			
	public static final String ENTIDAD_SALA_ESTADO_REGISTRO = "estadoRegistro";
	public static final String ENTIDAD_SALA_ID_SEDE = "idSede";
    private static final String[] ATRIBUTOS_ENTIDAD_SALA
            = {ENTIDAD_SALA_NUMERO_SALA, ENTIDAD_SALA_FECHA_ULTIMA_MODIFICACION, ENTIDAD_SALA_ID_USUARIO_MODIFICACION, ENTIDAD_SALA_TIPO_SERVICIO, ENTIDAD_SALA_CAPACIDAD_DE_ASISTENTES, ENTIDAD_SALA_ESTADO_REGISTRO, ENTIDAD_SALA_PK, ENTIDAD_SALA_ID_SEDE};

	@Id
    @Column(name="id_sala")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long idSala;
    
	@Column(name="numero_sala")
	private String numeroSala;		
    
	@Column(name="capacidad_de_asistentes")
	private Integer capacidadDeAsistentes;		
    
	@Column(name="tipo_servicio")
	private String tipoServicio;		
    
	@Column(name="id_usuario_modificacion")
	private String idUsuarioModificacion;		
    
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="fecha_ultima_modificacion")
	private Date fechaUltimaModificacion;		
    
	@Column(name="estado_registro")
	private String estadoRegistro;		
    
	@Column(name="id_sede")
	private Long idSede;		

	@ManyToOne
	@JoinColumn(name="id_sede", referencedColumnName="id_sede", insertable = false, updatable = false)
    private Sede sede;
		
	@OneToMany(mappedBy="sala")
    private List<Agendamiento> agendamientoList;
	@OneToMany(mappedBy="sala")
    private List<InfraestructuraSala> infraestructuraSalaList;
	
	
    public Sala(){
		// protected region procedimientos adicionales de inicializacion on begin
		// Escriba en esta seccion sus modificaciones

		// protected region procedimientos adicionales de inicializacion end
    }


	public Long getIdSala(){
		return this.idSala;
	}
	
	public void setIdSala(Long idSala){
		this.idSala = idSala;
	}
	
	public String getNumeroSala(){
		return this.numeroSala;
	}
	
	public void setNumeroSala(String numeroSala){
		this.numeroSala = numeroSala;
	}
		
	public Integer getCapacidadDeAsistentes(){
		return this.capacidadDeAsistentes;
	}
	
	public void setCapacidadDeAsistentes(Integer capacidadDeAsistentes){
		this.capacidadDeAsistentes = capacidadDeAsistentes;
	}
		
	public String getTipoServicio(){
		return this.tipoServicio;
	}
	
	public void setTipoServicio(String tipoServicio){
		this.tipoServicio = tipoServicio;
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
		
	public Long getIdSede(){
		return this.idSede;
	}
	
	public void setIdSede(Long idSede){
		this.idSede = idSede;
	}
		

    public List<Agendamiento> getAgendamientoList(){
		return this.agendamientoList;
	}
	
	public void setAgendamientoList(List<Agendamiento> agendamientoList){
		this.agendamientoList = agendamientoList;
	}
			
    public List<InfraestructuraSala> getInfraestructuraSalaList(){
		return this.infraestructuraSalaList;
	}
	
	public void setInfraestructuraSalaList(List<InfraestructuraSala> infraestructuraSalaList){
		this.infraestructuraSalaList = infraestructuraSalaList;
	}
			
    public Sede getSede(){
		return this.sede; 
	}
	
	public void setSede(Sede sede){
		this.sede = sede;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como parametro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_SALA) {
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
        
        hash = 37 * hash + Objects.hashCode(this.idSala);        
        hash = 37 * hash + Objects.hashCode(this.numeroSala);
        hash = 37 * hash + Objects.hashCode(this.capacidadDeAsistentes);
        hash = 37 * hash + Objects.hashCode(this.tipoServicio);
        hash = 37 * hash + Objects.hashCode(this.idUsuarioModificacion);
        hash = 37 * hash + Objects.hashCode(this.fechaUltimaModificacion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        hash = 37 * hash + Objects.hashCode(this.idSede);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad Sala que se pasa
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
        final Sala other = (Sala) obj;
        
        if (!Objects.equals(this.idSala, other.idSala)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroSala, other.numeroSala)) {
            return false;
        }
        
        if (!Objects.equals(this.capacidadDeAsistentes, other.capacidadDeAsistentes)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoServicio, other.tipoServicio)) {
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
        
        return Objects.equals(this.idSede, other.idSede);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

