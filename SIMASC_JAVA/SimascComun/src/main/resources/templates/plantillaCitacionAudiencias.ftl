<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
    <p> ${Object.ciudad}, ${Object.fechaActual?string.dd} de ${Object.meses[Object.fechaActual?string.MM?string?eval-1]}  de ${Object.fechaActual?string.yyyy}</p>
    	<p>
		<#if Object.rolParteDirigida == "DTE">
			Señor(a)<br>
		<#elseif Object.rolParteDirigida == "DDA">
		    Señor(a)<br>
		    Representante Legal de<br>
		<#elseif Object.rolParteDirigida == "APD">
			Doctor(a)<br>
		<#elseif Object.rolParteDirigida == "APO">
			Señor<br>
		<#else>
			Señor<br>
		</#if>	
    
    	${Object.nombreParteDirigida} <br>
    	${Object.direccion} <br>
        Ciudad <br>
        </p>
    <p>
        <table>
            <tr>
                <td valign="top" width="15%">REFERENCIA:</td>
                <td width="75%">${Object.casoServicio} DE ${Object.nombreConvocante} CON ${Object.nombreConvocado} - Caso ${Object.casoCodigo}</td>
            </tr>
        </table>
    </p>
    
    <p>Respetado(a) 			
		<#if Object.rolParteDirigida == "DTE">
			Señor(a)	
		<#elseif Object.rolParteDirigida == "DDA">
			Señor(a)
		<#elseif Object.rolParteDirigida == "APD">
			Doctor(a)	
		<#elseif Object.rolParteDirigida == "APO">
			Señor
		<#else>
			Señor
		</#if>	
	</p>

	<#if Object.tipoAudiencia == "INI">
		<p align="justify">
		  Reciba un cordial saludo. Con el prop&oacute;sito de avanzar en el tr&aacute;mite de la referencia, lo(la) invitamos a la audiencia de Instalaci&oacute;n del Tribunal, 
		  		  
		  <#if Object.totalArbitros == 3>
				integrado por los doctores ${Object.nombreArbitros}
		  </#if>
		   que se llevará a cabo el dia ${Object.fechaAudiencia?string.dd} de ${Object.meses[Object.fechaAudiencia?string.MM?string?eval-1]} de ${Object.fechaAudiencia?string.yyyy} a las ${Object.fechaAudiencia?string["HH:mm a"]}, en nuestras oficinas ubicadas en la ${Object.direccionCentro}, de esta ciudad.
		</p>
    </#if>
    
    <#if Object.tipoAudiencia == "SOR">
		<p align="justify">
		  Reciba un cordial saludo. Lo(la) invitamos a la designaci&oacute;n de &aacute;rbitro(s) por sorteo p&uacute;blico, que se llevar&aacute; a cabo el 
		  dia ${Object.fechaAudiencia?string.dd} de ${Object.meses[Object.fechaAudiencia?string.MM?string?eval-1]} de ${Object.fechaAudiencia?string.yyyy} a las ${Object.fechaAudiencia?string["HH:mm a"]}, en nuestras oficinas ubicadas en la ${Object.direccionCentro}, de esta ciudad.
		</p>
    </#if>
    
    <#if Object.tipoAudiencia == "DPP">
		<p align="justify">
		  Reciba un cordial saludo. Lo(la) invitamos a la a la reuni&oacute;n de designaci&oacute;n de &aacute;rbitros, que se llevar&aacute; a cabo el 
		  dia ${Object.fechaAudiencia?string.dd} de ${Object.meses[Object.fechaAudiencia?string.MM?string?eval-1]} de ${Object.fechaAudiencia?string.yyyy} a las ${Object.fechaAudiencia?string["HH:mm a"]}, en nuestras oficinas ubicadas en la ${Object.direccionCentro}, de esta ciudad.
		</p>
    </#if>
    
    <p align="justify">
      Cualquier informaci&oacute;n sobre el particular, no dude en comunicarse con nosotros al correo electr&oacute;nico ${Object.correoDirector} o al tel&eacute;fono 5941000 Ext. 2319/2323.
    </p>
    
    <p>Esperamos contar con su activa y valiosa participaci&oacute;n.</p>
    <p>Cordialmente,</p>
    <p><img src="RUTA_FIRMA"></p>
    <p><b>${Object.nombreDirector}</b><br/>
    <p><b>${Object.cargoDirector}</b><br/>
    <p><font size="1">${Object.inicialesDirector}</font></p>
    <p align="right"><font size="1">${Object.casoCodigo}</font></p>
</body>

</html>