package com.ccb.simasc.rest.transversal.excepciones;

import com.ccb.simasc.transversal.utilidades.MensajesEnum;
import com.ccb.simasc.transversal.utilidades.MensajesSimasc;

/**
 * Clase que encapsula los mensajes para tratamiento de excepciones en capa de servicios REST
 * @author sMartinez
 */
public class SIMASCRecursosRESTMensajes {
	 public static String CREATE_ENTITY_ERROR= MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR023.toString());
	 public static String OBTAIN_ENTITY= MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR024.toString());
	 public static String DELETE_ENTITY= MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR025.toString());
	 public static String UPDATE_ENTITY= MensajesSimasc.getInstancia().getMensajePorKey(MensajesEnum.ERROR026.toString());
}
