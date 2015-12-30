package com.supperarrow.directory.api;

public class ConfigAPI {
	
	
	public static String getTopSites(String lang) {
		switch(lang) {
		case "vi":
			return DirectoryAPI.getViWeb();
		case "en":
			return DirectoryAPI.getEnWeb();
		default:
			return DirectoryAPI.getEnWeb();
		}
	}
	
	
	

}
