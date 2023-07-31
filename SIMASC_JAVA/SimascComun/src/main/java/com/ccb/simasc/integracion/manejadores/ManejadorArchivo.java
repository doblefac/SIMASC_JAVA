package com.ccb.simasc.integracion.manejadores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ccb.simasc.integracion.manejadores.utilidades.ManejadorCrud;
import com.ccb.simasc.transversal.entidades.ParametrosGenerales;
import com.ccb.simasc.transversal.utilidades.UtilDominios;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class ManejadorArchivo extends ManejadorCrud<ParametrosGenerales,Long>{
	private static final Logger logger = LogManager.getLogger(ManejadorArchivo.class.getName());
	private static final String UBICACIONARBITRAJE = "URL_FILESYSTEM_ARBITRAJE";
	private static final String UBICACIONCONCILIACION = "URL_FILESYSTEM_CONCILIACION";
	private static final String NOMBREARCHIVOARBITRAJE = "CONSOLIDADO_FILE_ARBITRAJE.txt";
	private static final String NOMBREARCHIVOCONCILIA = "CONSOLIDADO_FILE_CONCILIACION.txt";
	
	@Resource
	private UserTransaction userTransaction;
	
	private String ubicacionArbitraje;
	private String ubicacionConciliacion;
	private Date fechaSistema = new Date();
		
	public ManejadorArchivo() {
		super(ParametrosGenerales.class);
	}
	
	@PostConstruct
	public void init(){
		ParametrosGenerales param = (ParametrosGenerales) mp.createNamedQuery("ParametrosGenerales.findByNombre")
				.setParameter("nombre", UBICACIONARBITRAJE).getSingleResult();
		this.ubicacionArbitraje = param.getValorTexto();
		param = (ParametrosGenerales) mp.createNamedQuery("ParametrosGenerales.findByNombre")
		.setParameter("nombre", UBICACIONCONCILIACION).getSingleResult();
		this.ubicacionConciliacion = param.getValorTexto();
	}
	
	
	public void moverArchivos()
	{
		this.moverArchivosArbitraje();
		this.moverArchivosConciliacion();
	}
	
	public List consultarArchivosConciliacion()
	{
		List<Object[]> resultado = new ArrayList<Object[]>();
		
		//Conciliacion
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append(" select id_documento, c.nombre, c.id_caso, convert(varchar(10), c.fecha_radicacion, 103), ");
		nativeQuery.append(" isnull(dr.nombre, '') as resultado, d.nombre as documento,");
		nativeQuery.append(" hta.codigo_sistema_externo as tipo_archivo,");
		nativeQuery.append(" htd.codigo_sistema_externo as tipo_documental, d.url");
		nativeQuery.append(" from documento d");
		nativeQuery.append(" inner join caso c on d.id_caso = c.id_Caso");
		nativeQuery.append(" inner join HOMOLOGACION_SISTEMA_EXTERNO htd");
		nativeQuery.append(" on d.tipo_documento = htd.codigo_simasc and");
		nativeQuery.append(" htd.dominio_simasc = '"+UtilDominios.TIPO_DOCUMENTO_CONCILIACION+"'");
		nativeQuery.append(" inner join HOMOLOGACION_SISTEMA_EXTERNO hta");
		nativeQuery.append(" on d.tipo_archivo = hta.codigo_simasc and");
		nativeQuery.append(" hta.dominio_simasc = '"+UtilDominios.DOMINIO_TIPO_ARCHIVO+"'");
		nativeQuery.append(" left join dominio dr");
		nativeQuery.append(" on dr.codigo = c.motivo_cierre");
		nativeQuery.append(" inner join servicio s on s.id_servicio =");
		nativeQuery.append(" c.id_servicio");
		nativeQuery.append(" where s.tipo = '"+UtilDominios.TIPO_SERVICIO_PLAN_DIALOGOS+"'");		
		
		try
		{				
			Query query = mp.createNativeQuery(nativeQuery.toString());
			resultado = query.getResultList();
		}
		catch (Exception e)
		{
			logger.error("Error: ", e);
		}
		
		return resultado;
	}
	
	public List consultarArchivosArbitraje()
	{
		List<Object[]> resultado = new ArrayList<Object[]>();
		
		//Arbitraje
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("select id_documento, c.nombre, c.id_caso, convert(varchar(10), c.fecha_radicacion, 103), ");
		nativeQuery.append(" isnull(dr.nombre, '') as resultado, d.nombre as documento,");
		nativeQuery.append("hta.codigo_sistema_externo as tipo_archivo, ");
		nativeQuery.append(" htd.codigo_sistema_externo as tipo_documental, d.url from documento d ");
		nativeQuery.append(" inner join caso c on d.id_caso = c.id_Caso ");
		nativeQuery.append(" inner join HOMOLOGACION_SISTEMA_EXTERNO htd");
		nativeQuery.append(" on d.tipo_documento = htd.codigo_simasc and");
		nativeQuery.append(" htd.dominio_simasc = ?3 ");
		nativeQuery.append(" inner join HOMOLOGACION_SISTEMA_EXTERNO hta");
		nativeQuery.append(" on d.tipo_archivo = hta.codigo_simasc and");
		nativeQuery.append(" hta.dominio_simasc = ?4");
		nativeQuery.append(" left join dominio dr");
		nativeQuery.append(" on dr.codigo = c.motivo_cierre");
		nativeQuery.append(" inner join servicio s on s.id_servicio =");
		nativeQuery.append(" c.id_servicio");
		nativeQuery.append(" where s.tipo in (?1, ?2) ");		
		
		try
		{				
			Query query = mp.createNativeQuery(nativeQuery.toString());
			query.setParameter(1, UtilDominios.TIPO_SERVICIO_PLAN_JUSTICIA);
			query.setParameter(2, UtilDominios.TIPO_SERVICIO_INTERNACIONAL);
			query.setParameter(3, UtilDominios.TIPO_DOCUMENTO_ARBITRAJE);
			query.setParameter(4, UtilDominios.DOMINIO_TIPO_ARCHIVO);
			resultado = query.getResultList();
		}
		catch (Exception e)
		{
			logger.error("Error: ", e);
		}
		
		return resultado;
	}
	
	public void moverArchivosConciliacion() {
		
		List<Object[]> archivosConciliacion = this.consultarArchivosConciliacion();
		String textoArchivo = "";
		
		for (Object[] documento : archivosConciliacion) 
		{
			
			String id_documento    = String.valueOf((BigDecimal) documento[0]);
			String nombre_caso     = (String)documento[1];
			String id_caso         = String.valueOf((BigDecimal) documento[2]);
			String fechaRadicacion = (String) documento[3];
			String nombre_archivo  = (String)documento[5];
			String tipo_archivo    = (String)documento[6];
			String tipo_documental = (String)documento[7];
			String url             = (String)documento[8];
			
			
	        File origin = new File(url);
	        String name = origin.getName();
	        String destinationUrl = this.ubicacionConciliacion+name;
	        File destination = new File(destinationUrl);
	        boolean isMoved = this.moveFile(origin, destination);
	        if(isMoved)
	        {
	        	//Actualiacion Url Destino
	        	this.updateFileURL(id_documento+"", destinationUrl);
	        	// Actualizacion estado del documento
	        	this.updateFileState(id_documento+"", "ENV");
	        	//Notificacion en archivo Plano
	        	textoArchivo += (id_documento+"|"+nombre_caso+"|"+id_caso+"|"+
	        					fechaRadicacion+"|"+nombre_archivo+"|"+tipo_archivo+"|"+
	        					new SimpleDateFormat("dd/MM/yyyy").format(fechaSistema)+
	        					"|"+tipo_documental+" "+"\n");
	        }	       
		}
		
		try 
        {
			this.createFile(this.ubicacionConciliacion+NOMBREARCHIVOCONCILIA, textoArchivo);
		} 
        catch (IOException e) 
        {
			logger.error("Error: ", e);
		}
	}
	
	public void moverArchivosArbitraje()
	{
		List<Object[]> archivosAbritaje = this.consultarArchivosArbitraje();
		String textoArchivo = "";
		String extension    = "";
		
		
		for (Object[] documento : archivosAbritaje) 
		{
			String id_documento    = String.valueOf((BigDecimal) documento[0]);
			String nombre_caso     = (String)documento[1];
			String id_caso         = String.valueOf((BigDecimal) documento[2]);
			String fechaRadicacion = (String) documento[3];
			String resultado       = (String)documento[4];
			String nombre_archivo  = (String)documento[5];
			String tipo_archivo    = (String)documento[6];
			String tipo_documental = (String)documento[7];
			String url             = (String)documento[8];
			if((String)documento[8] != null) {							
				extension = url.substring(url.lastIndexOf("."),url.length());
			}
			boolean result = url.contains("amazonaws");
			
			if(!result)
			{
				File origin = new File(url);
		        String name = origin.getName();
		        String destinationUrl = this.ubicacionArbitraje+name;
		        File destination = new File(destinationUrl);
		        boolean isMoved = this.moveFile(origin, destination);
		        if(isMoved)
		        {
		        	//Actualizacion url destino
		        	this.updateFileURL(id_documento+"", destinationUrl);
		        	//Actualizacion Estado
		        	this.updateFileState(id_documento+"", "ENV");
		        	//Notificacion en archivo plano
		        	textoArchivo += (id_documento+"|"+nombre_caso+"|"+id_caso+"|"+
  				 			 		fechaRadicacion+"|"+resultado+"|"+nombre_archivo+"|"+
  				 			 		tipo_archivo+"|"+new SimpleDateFormat("dd/MM/yyyy").format(fechaSistema)+
  				 			 		"|"+tipo_documental+" "+"\n");
		        }
		        
			}
			else
			{
				String ubicacionArchivo = this.ubicacionArbitraje + nombre_archivo+extension;
				
				File amazon =  new File(ubicacionArchivo);
				if(amazon.exists())
				{
					this.updateFileURL(id_documento+"", ubicacionArchivo);
					this.updateFileState(id_documento+"", "ENV");
					textoArchivo += (id_documento+"|"+nombre_caso+"|"+id_caso+"|"+
		       				 	     fechaRadicacion+"|"+resultado+"|"+nombre_archivo+"|"+
		       				 		 tipo_archivo+"|"+new SimpleDateFormat("dd/MM/yyyy").format(fechaSistema)+
		       				 		 "|"+tipo_documental+" "+"\n");
				}
			}
		}
		
		try 
        {
			this.createFile(this.ubicacionArbitraje+NOMBREARCHIVOARBITRAJE, textoArchivo);
		} 
        catch (IOException e) 
        {
			logger.error("Error: ", e);
		}
	}
	
	public boolean moveFile(File origin, File destination) 
    {
        if (origin.exists()) {
            try {
                InputStream in = new FileInputStream(origin);
                OutputStream out = new FileOutputStream(destination);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf))	 > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                return origin.delete();
            } catch (IOException ioe) {
                logger.error("Error: ", ioe);
                return false;
            }
        } else {
            return false;
        }
    }
	
	public void createFile(String writeFile, String content) throws IOException 
    { 
		FileWriter writer = null;
			
		try{
			File file = new File(writeFile);
  
			//Create the file
			if (file.createNewFile()){
			  logger.info("File is created!");
			}else{
			  logger.info("File already exists.");
			}
			 
			//Write Content
			writer = new FileWriter(file,true);
			writer.write(content);			
		}
		catch (Exception e){
			logger.error("Error: ", e);
		} finally{
			if(writer != null){
				writer.close();
			}			
		}
          
    }
	
	public void updateFileURL(String id,String url)
	{
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("UPDATE documento set url = ?1");
		nativeQuery.append(" where id_documento = ?2");
		try {
			userTransaction.begin();
			Query query = mp.createNativeQuery(nativeQuery.toString());
			query.setParameter(1, url);
			query.setParameter(2, id);
			query.executeUpdate();
			userTransaction.commit();
		} catch (Exception e) {
			try {
				if (userTransaction.getStatus() == 0) {
					userTransaction.rollback();
				}
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				logger.error("Error: ", e1);
			}
		}			
	}
	
	public void updateFileState(String id,String state)
	{
		StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("UPDATE documento set estado = ?1");
		nativeQuery.append(" where id_documento = ?2");
		try {
			userTransaction.begin();
			Query query = mp.createNativeQuery(nativeQuery.toString());
			query.setParameter(1, state);
			query.setParameter(2, id);
			query.executeUpdate();
			userTransaction.commit();
		} catch (Exception e) {
			try {
				if (userTransaction.getStatus() == 0) {
					userTransaction.rollback();
				}
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				logger.error("Error: ", e1);
			}
		}			
	}
}
