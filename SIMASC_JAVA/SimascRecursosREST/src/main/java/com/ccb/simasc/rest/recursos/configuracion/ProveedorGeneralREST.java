package com.ccb.simasc.rest.recursos.configuracion;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import javax.ejb.EJB;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IDocumentoFacade;
import com.ccb.simasc.negocio.fachadas.arbitraje.interfaces.IParametrosGeneralesFacade;
import com.ccb.simasc.rest.model.ItemData;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProveedorGeneralREST {
	private static final Logger logger = LogManager.getLogger(ProveedorGeneralREST.class.getName());
	@EJB
	private IDocumentoFacade documentoFacade;

	@EJB
	private IParametrosGeneralesFacade parametrosGeneralesFacade;
	
	public static final String RUTA_CERTIFICADO = "/opt/IBM/WebSphere/AppServer/simasc/Certificados/certificado.cer";

	static {
		CertificateFactory certificateFactory;
		try {
			certificateFactory = CertificateFactory.getInstance("X.509");			
				
			InputStream certificadoInput = new BufferedInputStream(new FileInputStream(RUTA_CERTIFICADO));
			Certificate certificado;
			try {
				certificado = certificateFactory.generateCertificate(certificadoInput);			    
			} finally {
				certificadoInput.close();
			}
		
			String keyStoreType = KeyStore.getDefaultType();
			KeyStore keyStore = KeyStore.getInstance(keyStoreType);
			keyStore.load(null, null);
			keyStore.setCertificateEntry("ca", certificado);
	
			// Create a TrustManager that trusts the CAs in our KeyStore
			String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
			tmf.init(keyStore);
	
			// Create an SSLContext that uses our TrustManager
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, tmf.getTrustManagers(), null);
		
			HostnameVerifier trustAllHostnames = new HostnameVerifier() {
			    @Override
			    public boolean verify(String hostname, SSLSession session) {
			        return true;
			    }
			};
		
			HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());		 
		    HttpsURLConnection.setDefaultHostnameVerifier(trustAllHostnames);            
		
		} catch (CertificateException |  IOException | KeyStoreException | NoSuchAlgorithmException | KeyManagementException e1) {			
			logger.error("Error: ", e1);
		}             
    }	

	public String obtenerItemOnBase(Long idCaso, Long idDocumento, String nombreDocumento, String fechaDocumento, String urlOnBase ) throws Exception{             
		try{
			
			String itemtypegroupnum="158,159";

			final String POST_PARAMS = "{\r\n" + 
					"\"iddocumento\":\""+idDocumento+"\",\r\n" +
					"\"idcaso\":\""+idCaso+"\",\r\n" + 
					"\"nombre\":\""+nombreDocumento+"\",\r\n" +
					"\"fecha\":\""+fechaDocumento+"\", \r\n" + 
					"\"itemtypegroupnum\":\""+itemtypegroupnum+"\" \r\n" +
					"}";
			//URL obj = new URL(urlOnBase);
			URL obj = new URL(null, urlOnBase, new sun.net.www.protocol.https.Handler());
			HttpsURLConnection  postConnection = (HttpsURLConnection) obj.openConnection();
			postConnection.setRequestMethod("POST");
			postConnection.setRequestProperty("Content-Type", "application/json");
			postConnection.setDoOutput(true);
			OutputStream os = postConnection.getOutputStream();
			os.write(POST_PARAMS.getBytes());
			os.flush();
			os.close();

			boolean redirect = false;
			int responseCode = postConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { //success
				BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in .readLine()) != null) {
					response.append(inputLine);
				} in .close(); 
				ObjectMapper mapper = new ObjectMapper();
				ItemData[] pp1 = mapper.readValue(response.toString(), ItemData[].class);
				String codDoc = "";
				
				for(ItemData i:pp1) {
					codDoc=i.getItemnum();
					break;		
				}
				return codDoc;
			} else {
				return null;
			}
		}catch(Exception ex){
			logger.error("Error: ", ex);
			return null;
		}			
	}
       

}
