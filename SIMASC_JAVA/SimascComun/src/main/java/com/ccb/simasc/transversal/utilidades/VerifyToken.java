package com.ccb.simasc.transversal.utilidades;

import java.util.Base64;

import org.json.JSONObject;


public class VerifyToken {
	
	public static int VALUE_HEADER=0;
	public static int VALUE_PAYLOAD=1;
	public static int VALUE_SIGNATURE=2;
	
	public VerifyToken() {
		
	}

	public String[] partToken(String token) {
		//split into 3 parts with . delimiter
		try {
			String[] parts = token.split("\\.");
			return parts;
		} catch (Exception e) {
			return null;
		}
	}

	public String decode(String encodedString) {
	    return new String(Base64.getUrlDecoder().decode(encodedString));
	}
	
	/**
	 * 	public static int VALUE_HEADER=0;
	 *	public static int VALUE_PAYLOAD=1;
	 *	public static int VALUE_SIGNATURE=2;
	 * @param parts
	 * @param typo
	 * @return Object(JSONPObject 0 y 1 o String 2) or null no encontró
	 */
	public Object pasarToJsonObject(String[] parts, int type) {
		try {
			JSONObject header = new JSONObject(decode(parts[0]));
			JSONObject payload = new JSONObject(decode(parts[1]));
			String signature = decode(parts[2]);
			switch (type) {
			case 0:
				return header;
			case 1:
				return payload;
			case 2:
				return signature;
			default:
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	public Long getExp(JSONObject payload) {
//		return payload.getLong("exp") > (System.currentTimeMillis() / 1000);
		return payload.getLong("exp");
	}
}
