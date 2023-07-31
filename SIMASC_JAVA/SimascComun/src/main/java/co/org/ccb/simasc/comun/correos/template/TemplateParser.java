/**
 * 
 */
package co.org.ccb.simasc.comun.correos.template;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ccb.simasc.transversal.utilidades.UtilConstantes;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 * @author dbarrera
 *
 */
public class TemplateParser {
	private static final Logger logger = LogManager.getLogger(TemplateParser.class.getName());
	/**
	 * TemplateParser para la implementacion del patron singleton.
	 */
	private static TemplateParser templateParser;
	private Configuration configuration;
	
	/**
	 * Constructor de la clase, carga los parametros de configuracion 
	 */
	private TemplateParser(){
		// 1. Configure FreeMarker
	    // You should do this ONLY ONCE, when your application starts,
	    // then reuse the same Configuration object elsewhere.
		configuration = new Configuration();
		// Where do we load the templates from:
		configuration.setClassForTemplateLoading(this.getClass(), File.separator);
	    // Some other recommended settings:
		configuration.setIncompatibleImprovements(new Version(2, 3, 20));
		configuration.setDefaultEncoding("UTF-8");
		configuration.setLocale(Locale.US);
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}
	
	/**
	 * Metodo del patron singleton para obtener la instancia de la clase, si la
	 * instancia no esta creada se genera una nueva, si existe, se pasa la ya
	 * existente
	 * 
	 * @return
	 */
	public static TemplateParser getInstancia() {
		if (templateParser == null) {
			templateParser = new TemplateParser();
		}
		return templateParser;
	}
	
	/**
	 * Metodo que retorna una plantilla HTML con los valores de los parametros 
	 * @param objeto
	 * @return
	 */
	public String setAtributos(Object objeto, String plantilla){
		// 2. Proccess template(s)
	    Map<String, Object> input = new HashMap<String, Object>();
	    Writer fileWriter = new StringWriter();
	    try {
		    Template template = configuration.getTemplate(UtilConstantes.RUTA_TEMPLATES + plantilla);
		    input.put("Object", objeto);
			template.process(input, fileWriter);
		} catch (TemplateException | IOException e) {
			logger.error(e.getMessage());
		}
	    return fileWriter.toString();
	}

}
