/**
 * 
 */
package com.ccb.simasc.transversal.dto.formularios;

import javax.xml.bind.annotation.XmlRootElement;

import com.ccb.simasc.transversal.entidades.Evento;

/**
 * @author dbarrera
 *
 */
@XmlRootElement
public class DevolucionExpedienteDTO {
	
	private Evento evento;
	private EnvioCorreoElectronicoDTO correo;
	
	
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public EnvioCorreoElectronicoDTO getCorreo() {
		return correo;
	}
	public void setCorreo(EnvioCorreoElectronicoDTO correo) {
		this.correo = correo;
	}

}
