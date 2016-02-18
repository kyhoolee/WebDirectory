package com.supperarrow.directory.mysql;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


public class DatabaseConfig {
	final static Logger logger = Logger.getLogger(DatabaseConfig.class);
	
	public DatabaseConfig(){}
	
	public String[] getConnectionProperty() {
		String[] returnVl = new String[3];	
		InputStream is = getClass().getResourceAsStream("/connection.properties");
		Properties prop = new Properties();
		try {
			prop.load(is);
			returnVl[0] = prop.getProperty("url");	
			returnVl[1] = prop.getProperty("user");
			returnVl[2] = prop.getProperty("password");				
			
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("error when reading connection infor from /connection.properties");
		}
		
		return returnVl;
	}
}
