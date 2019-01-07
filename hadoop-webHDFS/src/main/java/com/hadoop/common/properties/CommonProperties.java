package com.hadoop.common.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This class will read the Common Properties file for application.
 * 
 * @author Ravinder Reddy.P created on 07/Jan/2019.
 *
 */

public class CommonProperties {
	
	private static CommonProperties instance = null;
	
	private CommonProperties() {
		
	}
	
	public static synchronized CommonProperties getInstance() {
		
		if (instance == null) {
			instance = new CommonProperties();
		}
		return instance;
		
	}
	
	/**
	 * Read all key and values from a properties file.
	 * @param commonPath
	 * @return Map<String,String>
	 * @throws IOException
	 */
	
	public synchronized Map<String,String> getPropertiesMap(String commonPath) throws IOException{
		
		Map<String, String> propMap = new HashMap<String, String>();
		InputStream iCommonStream = new FileInputStream(commonPath);
		Properties prop = new Properties();
		prop.load(iCommonStream);
		
		Enumeration<?> e = prop.propertyNames();
		String key = null;
		while (e.hasMoreElements()){
			
			key = (String) e.nextElement();
			propMap.put(key, prop.getProperty(key));
		}
		iCommonStream.close();
		return propMap;
		
	}
	

}
