package com.jobs.services;

import org.springframework.util.Base64Utils;

public class ConvertImage {
	
	public static String getImageString(byte[] image) {
		
		return Base64Utils.encodeToString(image);
		
	}
	
}
