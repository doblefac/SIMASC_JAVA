package co.org.ccb.simasc.comun.pdf;

import java.util.List;

import com.ccb.simasc.transversal.dto.DocumentoIndiceElectronicoDTO;

public class PlantillaIndicePDF {

	public static final String HTML_INICIO ="<!DOCTYPE html> <html> ";
	public static final String[]  HEADER_COLS = {"Id Documento","Ciudad","Servicio Caso","Serie","Código Caso","Nombre Caso","Parte Demandante","Parte Demandada","Orden","Nombre Documento",
			"Tipo Documento","Cuaderno","Carpeta","Fecha Radicación", "Pag. Incial","Pag. Final","Cant. Paginas","Formato","Peso"};
	public static final String HTML_FINAL = "</table> </body> </html>";
	public StringBuilder HTML_PLANTILLA = new StringBuilder();
	public static final String ABRE_TD = "<td>";
	public static final String CIERRA_TD = "</td>";
	
	
	
	public void generateHeaderTable() {
		HTML_PLANTILLA.append(HTML_INICIO);
		addStyles();
		HTML_PLANTILLA.append("<body> <table> <tr class='head'>");
		for (String header_col : HEADER_COLS) {
			HTML_PLANTILLA.append("<th>"+header_col+"</th>");
		}
		HTML_PLANTILLA.append("</tr>");
	}
	
	public void generateBodyTable(List<DocumentoIndiceElectronicoDTO> indiceElectronico) {
		for (DocumentoIndiceElectronicoDTO documentoIndiceElectronicoDTO : indiceElectronico) {
			HTML_PLANTILLA.append("<tr>");
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getIdDocumento());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getCiudad());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getServicioCaso());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getSerie());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getCodigoCaso());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getNombreCaso());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getParteDemandante());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getParteDemandado());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getOrden());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getNombreDocumento());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getTipoDocumental());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getCuaderno());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getCarpeta());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getFechaDocumento());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getPaginaInicial());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getPaginaFinal());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getCantidadPaginas());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getTipoDocumento());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append(ABRE_TD);
			HTML_PLANTILLA.append(documentoIndiceElectronicoDTO.getPeso());
			HTML_PLANTILLA.append(CIERRA_TD);
			HTML_PLANTILLA.append("</tr>");	
		}		
		HTML_PLANTILLA.append(HTML_FINAL);
	}

	public void addStyles() {
		String styles = "<style> table { text-align: left; width: 100%; border-collapse: collapse; border: 1px solid black;\r\n" + 
				" font-size: small; } tr { text-align: left; } th,td { border: black 1px solid;\r\n" + 
				" font-weight: lighter; text-align: left; } td { width: auto; word-break: break-word;}\r\n" + 
				" tbody tr:nth-child(odd) { background-color: #D9E1F2; }\r\n" + 
				" .head th { font-weight: bold !important; background-color: #b7c7e9; }\r\n" + 
				" @page { size: JIS-B4 landscape; }\r\n" + 
				"</style>";
				HTML_PLANTILLA.append(styles);
	}
	
	public String getPlantillaPDF(List<DocumentoIndiceElectronicoDTO> indiceElectronico) {
		generateHeaderTable();
		generateBodyTable(indiceElectronico);
		return HTML_PLANTILLA.toString();
	}


}
