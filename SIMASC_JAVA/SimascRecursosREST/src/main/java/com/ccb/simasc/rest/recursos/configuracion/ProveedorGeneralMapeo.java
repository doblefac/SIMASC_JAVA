package com.ccb.simasc.rest.recursos.configuracion;

import java.lang.annotation.Annotation;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;


/**
 * Clase proveedora de servicio de mapeo de objetos Jackson
 * @author sMartinez
 *
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ProveedorGeneralMapeo  implements MessageBodyWriter<Object> {
	
	ObjectMapper mapper = configureMapper();
	
	
	private ObjectMapper configureMapper(){
		//Seccion de configuración para JACKSON 
		ObjectMapper mapper = new ObjectMapper(); 
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
	        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true /* force ISO8601 */)
	        .configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true)
	        .configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, false)
	        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
	        .setSerializationInclusion(Include.ALWAYS)
	        .setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		return mapper;
	}
	
	
    @Override
    public long getSize(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public void writeTo(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException {
            mapper.writeValue(entityStream, object);
    }
  
}
