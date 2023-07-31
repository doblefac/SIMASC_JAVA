
package co.org.ccb.sirep2.clientes.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsultarDatosBasicosClienteWSOutDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultarDatosBasicosClienteWSOutDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mensajeError" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoError" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numCliente" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tipoCliente" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nombreCompleto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primerApellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="segundoApellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primerNombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="segundoNombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idClase" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descIdClase" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="digitoVerificacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numRut" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numRadicaDian" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numMatricula" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idCategoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idOrganizacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idEstadoMatricula" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descEstadoMatricula" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ultAnoRenova" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numInscripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idEstadoInscripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descEstadoInscripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numClientePadre" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numIdClientePadre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idClaseClientePadre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vinculos" type="{http://model.ws.clientes.sirep2.ccb.org.co}ArrayOfResultadoDTO"/>
 *         &lt;element name="ctrChequeDevuelto" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ctrCopropiedad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ctrPropMatriculado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idMunipExp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaExp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idPaisDoc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idNacionalidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="direccion" type="{http://model.ws.clientes.sirep2.ccb.org.co}DireccionDetalleDTO"/>
 *         &lt;element name="direcciones" type="{http://model.ws.clientes.sirep2.ccb.org.co}ArrayOfDireccionDTO"/>
 *         &lt;element name="contacto" type="{http://model.ws.clientes.sirep2.ccb.org.co}ContactoDTO"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarDatosBasicosClienteWSOutDTO", propOrder = {
    "mensajeError",
    "codigoError",
    "numCliente",
    "tipoCliente",
    "nombreCompleto",
    "sigla",
    "primerApellido",
    "segundoApellido",
    "primerNombre",
    "segundoNombre",
    "idClase",
    "descIdClase",
    "numId",
    "digitoVerificacion",
    "numRut",
    "numRadicaDian",
    "numMatricula",
    "idCategoria",
    "idOrganizacion",
    "idEstadoMatricula",
    "descEstadoMatricula",
    "ultAnoRenova",
    "numInscripcion",
    "idEstadoInscripcion",
    "descEstadoInscripcion",
    "numClientePadre",
    "numIdClientePadre",
    "idClaseClientePadre",
    "vinculos",
    "ctrChequeDevuelto",
    "ctrCopropiedad",
    "ctrPropMatriculado",
    "idMunipExp",
    "fechaExp",
    "idPaisDoc",
    "idNacionalidad",
    "direccion",
    "direcciones",
    "contacto"
})
public class ConsultarDatosBasicosClienteWSOutDTO {

    @XmlElement(required = true, nillable = true)
    protected String mensajeError;
    @XmlElement(required = true, nillable = true)
    protected String codigoError;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer numCliente;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer tipoCliente;
    @XmlElement(required = true, nillable = true)
    protected String nombreCompleto;
    @XmlElement(required = true, nillable = true)
    protected String sigla;
    @XmlElement(required = true, nillable = true)
    protected String primerApellido;
    @XmlElement(required = true, nillable = true)
    protected String segundoApellido;
    @XmlElement(required = true, nillable = true)
    protected String primerNombre;
    @XmlElement(required = true, nillable = true)
    protected String segundoNombre;
    @XmlElement(required = true, nillable = true)
    protected String idClase;
    @XmlElement(required = true, nillable = true)
    protected String descIdClase;
    @XmlElement(required = true, nillable = true)
    protected String numId;
    @XmlElement(required = true, nillable = true)
    protected String digitoVerificacion;
    @XmlElement(required = true, nillable = true)
    protected String numRut;
    @XmlElement(required = true, nillable = true)
    protected String numRadicaDian;
    @XmlElement(required = true, nillable = true)
    protected String numMatricula;
    @XmlElement(required = true, nillable = true)
    protected String idCategoria;
    @XmlElement(required = true, nillable = true)
    protected String idOrganizacion;
    @XmlElement(required = true, nillable = true)
    protected String idEstadoMatricula;
    @XmlElement(required = true, nillable = true)
    protected String descEstadoMatricula;
    @XmlElement(required = true, nillable = true)
    protected String ultAnoRenova;
    @XmlElement(required = true, nillable = true)
    protected String numInscripcion;
    @XmlElement(required = true, nillable = true)
    protected String idEstadoInscripcion;
    @XmlElement(required = true, nillable = true)
    protected String descEstadoInscripcion;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer numClientePadre;
    @XmlElement(required = true, nillable = true)
    protected String numIdClientePadre;
    @XmlElement(required = true, nillable = true)
    protected String idClaseClientePadre;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfResultadoDTO vinculos;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer ctrChequeDevuelto;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer ctrCopropiedad;
    @XmlElement(required = true, nillable = true)
    protected String ctrPropMatriculado;
    @XmlElement(required = true, nillable = true)
    protected String idMunipExp;
    @XmlElement(required = true, nillable = true)
    protected String fechaExp;
    @XmlElement(required = true, nillable = true)
    protected String idPaisDoc;
    @XmlElement(required = true, nillable = true)
    protected String idNacionalidad;
    @XmlElement(required = true, nillable = true)
    protected DireccionDetalleDTO direccion;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfDireccionDTO direcciones;
    @XmlElement(required = true, nillable = true)
    protected ContactoDTO contacto;

    /**
     * Gets the value of the mensajeError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajeError() {
        return mensajeError;
    }

    /**
     * Sets the value of the mensajeError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajeError(String value) {
        this.mensajeError = value;
    }

    /**
     * Gets the value of the codigoError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoError() {
        return codigoError;
    }

    /**
     * Sets the value of the codigoError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoError(String value) {
        this.codigoError = value;
    }

    /**
     * Gets the value of the numCliente property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumCliente() {
        return numCliente;
    }

    /**
     * Sets the value of the numCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumCliente(Integer value) {
        this.numCliente = value;
    }

    /**
     * Gets the value of the tipoCliente property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipoCliente() {
        return tipoCliente;
    }

    /**
     * Sets the value of the tipoCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipoCliente(Integer value) {
        this.tipoCliente = value;
    }

    /**
     * Gets the value of the nombreCompleto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Sets the value of the nombreCompleto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCompleto(String value) {
        this.nombreCompleto = value;
    }

    /**
     * Gets the value of the sigla property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * Sets the value of the sigla property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigla(String value) {
        this.sigla = value;
    }

    /**
     * Gets the value of the primerApellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * Sets the value of the primerApellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimerApellido(String value) {
        this.primerApellido = value;
    }

    /**
     * Gets the value of the segundoApellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * Sets the value of the segundoApellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegundoApellido(String value) {
        this.segundoApellido = value;
    }

    /**
     * Gets the value of the primerNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimerNombre() {
        return primerNombre;
    }

    /**
     * Sets the value of the primerNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimerNombre(String value) {
        this.primerNombre = value;
    }

    /**
     * Gets the value of the segundoNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegundoNombre() {
        return segundoNombre;
    }

    /**
     * Sets the value of the segundoNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegundoNombre(String value) {
        this.segundoNombre = value;
    }

    /**
     * Gets the value of the idClase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdClase() {
        return idClase;
    }

    /**
     * Sets the value of the idClase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdClase(String value) {
        this.idClase = value;
    }

    /**
     * Gets the value of the descIdClase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescIdClase() {
        return descIdClase;
    }

    /**
     * Sets the value of the descIdClase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescIdClase(String value) {
        this.descIdClase = value;
    }

    /**
     * Gets the value of the numId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumId() {
        return numId;
    }

    /**
     * Sets the value of the numId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumId(String value) {
        this.numId = value;
    }

    /**
     * Gets the value of the digitoVerificacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDigitoVerificacion() {
        return digitoVerificacion;
    }

    /**
     * Sets the value of the digitoVerificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDigitoVerificacion(String value) {
        this.digitoVerificacion = value;
    }

    /**
     * Gets the value of the numRut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumRut() {
        return numRut;
    }

    /**
     * Sets the value of the numRut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumRut(String value) {
        this.numRut = value;
    }

    /**
     * Gets the value of the numRadicaDian property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumRadicaDian() {
        return numRadicaDian;
    }

    /**
     * Sets the value of the numRadicaDian property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumRadicaDian(String value) {
        this.numRadicaDian = value;
    }

    /**
     * Gets the value of the numMatricula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumMatricula() {
        return numMatricula;
    }

    /**
     * Sets the value of the numMatricula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumMatricula(String value) {
        this.numMatricula = value;
    }

    /**
     * Gets the value of the idCategoria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCategoria() {
        return idCategoria;
    }

    /**
     * Sets the value of the idCategoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCategoria(String value) {
        this.idCategoria = value;
    }

    /**
     * Gets the value of the idOrganizacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdOrganizacion() {
        return idOrganizacion;
    }

    /**
     * Sets the value of the idOrganizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdOrganizacion(String value) {
        this.idOrganizacion = value;
    }

    /**
     * Gets the value of the idEstadoMatricula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdEstadoMatricula() {
        return idEstadoMatricula;
    }

    /**
     * Sets the value of the idEstadoMatricula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdEstadoMatricula(String value) {
        this.idEstadoMatricula = value;
    }

    /**
     * Gets the value of the descEstadoMatricula property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescEstadoMatricula() {
        return descEstadoMatricula;
    }

    /**
     * Sets the value of the descEstadoMatricula property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescEstadoMatricula(String value) {
        this.descEstadoMatricula = value;
    }

    /**
     * Gets the value of the ultAnoRenova property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUltAnoRenova() {
        return ultAnoRenova;
    }

    /**
     * Sets the value of the ultAnoRenova property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUltAnoRenova(String value) {
        this.ultAnoRenova = value;
    }

    /**
     * Gets the value of the numInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumInscripcion() {
        return numInscripcion;
    }

    /**
     * Sets the value of the numInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumInscripcion(String value) {
        this.numInscripcion = value;
    }

    /**
     * Gets the value of the idEstadoInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdEstadoInscripcion() {
        return idEstadoInscripcion;
    }

    /**
     * Sets the value of the idEstadoInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdEstadoInscripcion(String value) {
        this.idEstadoInscripcion = value;
    }

    /**
     * Gets the value of the descEstadoInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescEstadoInscripcion() {
        return descEstadoInscripcion;
    }

    /**
     * Sets the value of the descEstadoInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescEstadoInscripcion(String value) {
        this.descEstadoInscripcion = value;
    }

    /**
     * Gets the value of the numClientePadre property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumClientePadre() {
        return numClientePadre;
    }

    /**
     * Sets the value of the numClientePadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumClientePadre(Integer value) {
        this.numClientePadre = value;
    }

    /**
     * Gets the value of the numIdClientePadre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumIdClientePadre() {
        return numIdClientePadre;
    }

    /**
     * Sets the value of the numIdClientePadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumIdClientePadre(String value) {
        this.numIdClientePadre = value;
    }

    /**
     * Gets the value of the idClaseClientePadre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdClaseClientePadre() {
        return idClaseClientePadre;
    }

    /**
     * Sets the value of the idClaseClientePadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdClaseClientePadre(String value) {
        this.idClaseClientePadre = value;
    }

    /**
     * Gets the value of the vinculos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfResultadoDTO }
     *     
     */
    public ArrayOfResultadoDTO getVinculos() {
        return vinculos;
    }

    /**
     * Sets the value of the vinculos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfResultadoDTO }
     *     
     */
    public void setVinculos(ArrayOfResultadoDTO value) {
        this.vinculos = value;
    }

    /**
     * Gets the value of the ctrChequeDevuelto property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCtrChequeDevuelto() {
        return ctrChequeDevuelto;
    }

    /**
     * Sets the value of the ctrChequeDevuelto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCtrChequeDevuelto(Integer value) {
        this.ctrChequeDevuelto = value;
    }

    /**
     * Gets the value of the ctrCopropiedad property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCtrCopropiedad() {
        return ctrCopropiedad;
    }

    /**
     * Sets the value of the ctrCopropiedad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCtrCopropiedad(Integer value) {
        this.ctrCopropiedad = value;
    }

    /**
     * Gets the value of the ctrPropMatriculado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCtrPropMatriculado() {
        return ctrPropMatriculado;
    }

    /**
     * Sets the value of the ctrPropMatriculado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCtrPropMatriculado(String value) {
        this.ctrPropMatriculado = value;
    }

    /**
     * Gets the value of the idMunipExp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMunipExp() {
        return idMunipExp;
    }

    /**
     * Sets the value of the idMunipExp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMunipExp(String value) {
        this.idMunipExp = value;
    }

    /**
     * Gets the value of the fechaExp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaExp() {
        return fechaExp;
    }

    /**
     * Sets the value of the fechaExp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaExp(String value) {
        this.fechaExp = value;
    }

    /**
     * Gets the value of the idPaisDoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPaisDoc() {
        return idPaisDoc;
    }

    /**
     * Sets the value of the idPaisDoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPaisDoc(String value) {
        this.idPaisDoc = value;
    }

    /**
     * Gets the value of the idNacionalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdNacionalidad() {
        return idNacionalidad;
    }

    /**
     * Sets the value of the idNacionalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdNacionalidad(String value) {
        this.idNacionalidad = value;
    }

    /**
     * Gets the value of the direccion property.
     * 
     * @return
     *     possible object is
     *     {@link DireccionDetalleDTO }
     *     
     */
    public DireccionDetalleDTO getDireccion() {
        return direccion;
    }

    /**
     * Sets the value of the direccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link DireccionDetalleDTO }
     *     
     */
    public void setDireccion(DireccionDetalleDTO value) {
        this.direccion = value;
    }

    /**
     * Gets the value of the direcciones property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDireccionDTO }
     *     
     */
    public ArrayOfDireccionDTO getDirecciones() {
        return direcciones;
    }

    /**
     * Sets the value of the direcciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDireccionDTO }
     *     
     */
    public void setDirecciones(ArrayOfDireccionDTO value) {
        this.direcciones = value;
    }

    /**
     * Gets the value of the contacto property.
     * 
     * @return
     *     possible object is
     *     {@link ContactoDTO }
     *     
     */
    public ContactoDTO getContacto() {
        return contacto;
    }

    /**
     * Sets the value of the contacto property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactoDTO }
     *     
     */
    public void setContacto(ContactoDTO value) {
        this.contacto = value;
    }

}
