package com.ccb.simasc.transversal.utilidades;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.DocumentException;

public class GeneradorDocumentos {

	private static final Logger logger = LogManager.getLogger(GeneradorDocumentos.class.getName());

	/**
	 * @param file
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void createPdf(String cartaGeneradaTags, String asunto, String rutaHtmlOrigen, String pdfDestino) {
		String file = pdfDestino.concat(asunto);
		file = file.concat(UtilConstantes.CARACTER_PUNTO).concat(UtilConstantes.EXTENSION_ARCHIVO_PDF);
		FileWriter ficheros = null;
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;

		try {
			cartaGeneradaTags = removerTagsVaciosCarta(cartaGeneradaTags);

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(cartaGeneradaTags);

			ficheros = new FileWriter(rutaHtmlOrigen);
			ficheros.write(stringBuilder + UtilConstantes.CARACTER_SALTO_LINEA);
			ficheros.close();

			fileInputStream = new FileInputStream(rutaHtmlOrigen);
			fileOutputStream = new FileOutputStream(file);
			
//			ConverterProperties converterProperties = new ConverterProperties();
//			converterProperties.setCharset("ISO-8859-1");

			HtmlConverter.convertToPdf(fileInputStream, fileOutputStream/*, converterProperties*/);

		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (ficheros != null) {
				try {
					ficheros.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
	}

	/**
	 * Método encargado de remover tags vacios de las cartas (HTML) para evitar
	 * errores al momento de convertilas en PDF
	 * 
	 * @param cartaGeneradaTags
	 * @return
	 */
	private static String removerTagsVaciosCarta(String cartaGeneradaTags) {
		org.jsoup.nodes.Document document = Jsoup.parse(cartaGeneradaTags, UtilConstantes.CADENA_VACIA,
				Parser.xmlParser());

		if (document != null) {
			for (Element element : document.body().getAllElements()) {
				if (!element.hasText() && element.isBlock()) {
					element.remove();
				}
			}
			cartaGeneradaTags = document.html();
		}

		return cartaGeneradaTags;
	}
	
	
	
}