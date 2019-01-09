package com.hadoop.webHDFS;

import static com.hadoop.common.properties.Constants.ERROR;
import static com.hadoop.common.properties.Constants.SOURCE_URL;
import static com.hadoop.common.properties.Constants.DESTINATION_URL;
import static com.hadoop.common.properties.Constants.LOG;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.hadoop.common.properties.CommonProperties;

/**
 * HDFS FTP Utilities
 * 
 * @author Ravinder Reddy P created on 07/Jan/2019.
 *
 */
public class HadoopDFSFTP {
	public static Logger log = LogManager.getLogger(HadoopDFSFTP.class.getName());
	private static String process;
	private static String sourceName;
	private static String propDirectory;
	private static String environment;
	private static String fileName;
	static String trouxId = null;
	/**
	 * 
	 * @param args
	 */
	private static Map<String, String> trouxID;

	public static void main(String[] args) {
		
		HadoopDFSFTP hadoopDFSFTP = new HadoopDFSFTP();
		
		try{
			
			String sourcePath = null, targetPath = null;
			process = args[0];
			sourceName = args[1];
			propDirectory = args[2];
			environment = args[3];
			fileName = args[4];
			
			log.info("args:" +Arrays.toString(args));
			
			String propFile = propDirectory + environment + ".properties";
			Map<String, String> propMap = CommonProperties.getInstance().getPropertiesMap(propFile);
			
			trouxId = propMap.get("APPLICATION_TROUX_ID");
			
			if (args.length < 5) {
				String msg = "Mandatory Arguments are missing";
				log.error(ERROR +msg);
				return;
			}
			
			/*
			 * Check for any concrete path
			 */
			
			if(args.length == 7){
				sourcePath = args[5];
				targetPath = args[6];
			}
			
			/*
			 * check for any source path concrete path from ENV file
			 */
			if(sourcePath == null || (sourcePath != null && sourcePath.trim().length() < 1)){
				sourcePath = propMap.get(process+"."+sourceName + SOURCE_URL);
			}
			
			/*
			 * check for any target path concrete path from ENV file
			 */
			if(targetPath == null || (targetPath != null && targetPath.trim().length() < 1)){
				targetPath = propMap.get(process+"."+sourceName + DESTINATION_URL);
			}
			
			if(!LOG.equalsIgnoreCase(sourceName)){
				
				if(process.equals("push")){
					sourcePath = sourcePath + File.separator + fileName;
				}
			}
			
			log.info("sourcePath:" + sourcePath);
			
		} catch (Throwable e) {
			
		}

	}

}
