package com.hadoop.webHDFS;

import java.util.Arrays;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.hadoop.common.properties.CommonProperties;
import static com.hadoop.common.properties.Constants.ERROR;

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
			}
			
		} catch (Throwable e) {
			
		}

	}

}
