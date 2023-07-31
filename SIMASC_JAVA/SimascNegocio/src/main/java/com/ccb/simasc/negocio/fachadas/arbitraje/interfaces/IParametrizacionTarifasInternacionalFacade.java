package com.ccb.simasc.negocio.fachadas.arbitraje.interfaces;

import javax.ejb.Local;

import com.ccb.simasc.comun.fachada.interfaces.IAccesoFacade;
import com.ccb.simasc.transversal.dto.ParametrizacionTarifasDTO;
import com.ccb.simasc.transversal.dto.formularios.ParametrosTarifasInternacionalesDTO;
import com.ccb.simasc.transversal.dto.formularios.ResultadosTarifaInternacionalDTO;
import com.ccb.simasc.transversal.entidades.ParametrizacionTarifas;

@Local
public interface IParametrizacionTarifasInternacionalFacade extends IAccesoFacade<ParametrizacionTarifas, Long, ParametrizacionTarifasDTO> {

	public ResultadosTarifaInternacionalDTO calcularTarifaPublico(ParametrosTarifasInternacionalesDTO parametrosTarifasDTO);

}
