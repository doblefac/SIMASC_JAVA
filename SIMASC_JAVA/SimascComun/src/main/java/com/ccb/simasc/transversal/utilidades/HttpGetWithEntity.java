package com.ccb.simasc.transversal.utilidades;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public class HttpGetWithEntity extends HttpEntityEnclosingRequestBase {
	
	public final static String METHOD_NAME = "GET";

	public HttpGetWithEntity(URI url) {        
		setURI(url);
	}

	public HttpGetWithEntity(String url) {
		setURI(URI.create(url));
	}
   
	@Override
	public String getMethod() {
		return METHOD_NAME;
	}
}
