package com.ccb.simasc.comun.util;

import java.util.Base64;

import org.json.JSONObject;

/**
 * @version 1
 * @date septiembre 9 2022
 * @author andres.ramirez
 *
 */
public class VerifyToken {
	
	public static int VALUE_HEADER=0;
	public static int VALUE_PAYLOAD=1;
	public static int VALUE_SIGNATURE=2;
	
	/**
	 * Constructor
	 */
	public VerifyToken() {
		
	}

	/**
	 * al encontrar cada puntp del token los parsea para su posterior uso
	 * @param token
	 * @return
	 */
	public String[] partToken(String token) {
		//split into 3 parts with . delimiter
		try {
			String[] parts = token.split("\\.");
			return parts;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * decodificación a lenguaje natural desde el base64 del token
	 * @param encodedString
	 * @return
	 */
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
//			String signature = decode(parts[2]);
			switch (type) {
				case 0:return header;
				case 1:return payload;
				default:return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * retorna la expiración del payload del json parseado del token
	 * @param payload
	 * @return
	 */
	public Long getExp(JSONObject payload) {
//		return payload.getLong("exp") > (System.currentTimeMillis() / 1000);
		return payload.getLong("exp");
	}
}
