package com.ccb.simasc.transversal.dto;

public class AutenticacionMaucDTO {

	private String issuer; 
	private String idpersona;
	private String nombrepersona;
	private long ttlMillis;
	private boolean isExpired;
	private String maucToken;
	
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getIdpersona() {
		return idpersona;
	}
	public void setIdpersona(String idpersona) {
		this.idpersona = idpersona;
	}
	public String getNombrepersona() {
		return nombrepersona;
	}
	public void setNombrepersona(String nombrepersona) {
		this.nombrepersona = nombrepersona;
	}
	public long getTtlMillis() {
		return ttlMillis;
	}
	public void setTtlMillis(long ttlMillis) {
		this.ttlMillis = ttlMillis;
	}
	public boolean isExpired() {
		return isExpired;
	}
	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}
	public String getMaucToken() {
		return maucToken;
	}
	public void setMaucToken(String maucToken) {
		this.maucToken = maucToken;
	}
	
	
	
	
	
}
