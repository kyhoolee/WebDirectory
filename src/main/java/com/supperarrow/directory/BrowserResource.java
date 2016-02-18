package com.supperarrow.directory;

import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import com.supperarrow.directory.util.LoggerUtil;

@Path("/browser")
public class BrowserResource {
	public static Logger logger = LoggerUtil.getDailyLogger("BrowserResource_log"); 
	
	public static String browser_config_path = "/config.properties";
	public static String torrent_config_path = "/torrent_config.properties";

	@Path("/config")
	@POST
	@Produces("text/plain;charset=utf-8")
	public Response getBrowserConfig(String info, @Context HttpServletRequest req) {
		
		JSONObject result = new JSONObject();
		int n = 0;
		Properties properties = new Properties();
		try {
			properties.load(BrowserResource.class.getResourceAsStream(browser_config_path));
			
			String url= properties.getProperty("update_url");
			int version = Integer.parseInt(properties.getProperty("current_version"));
			boolean in_review = Boolean.parseBoolean(properties.getProperty("in_review"));
			boolean force_update = Boolean.parseBoolean(properties.getProperty("force_update"));
			boolean enable_youtube = Boolean.parseBoolean(properties.getProperty("enable_youtube"));
			n = Integer.parseInt(properties.getProperty("push_app", "0"));
			
			result.put("current_version", version);
			result.put("in_review", in_review);
			result.put("force_update", force_update);
			result.put("update_url", url);
			result.put("enable_youtube", enable_youtube);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		
		Random rand = new Random();
		int value = rand.nextInt(n);
		if(value == 1) {
			result.put("force_update", true);
			result.put("update_url", "https://play.google.com/store/apps/details?id=com.ccstudio.webbrowser");
		}
		
		String data = result.toString();
		return Response
				.ok(data)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods",
						"PUT, GET, POST, DELETE, OPTIONS").build();
	}
	
	
	@Path("/torrent_config")
	@GET
	@Produces("text/plain;charset=utf-8")
	public Response getTorrentConfig(@Context HttpServletRequest req) {
		
		JSONObject result = new JSONObject();
		//int n = 0;
		Properties properties = new Properties();
		try {
			properties.load(BrowserResource.class.getResourceAsStream(torrent_config_path));
			
			String url= properties.getProperty("update_url");
			int version = Integer.parseInt(properties.getProperty("current_version"));
			boolean in_review = Boolean.parseBoolean(properties.getProperty("in_review"));
			boolean force_update = Boolean.parseBoolean(properties.getProperty("force_update"));
			//boolean enable_youtube = Boolean.parseBoolean(properties.getProperty("enable_youtube"));
			//n = Integer.parseInt(properties.getProperty("push_app", "0"));
			
			result.put("current_version", version);
			result.put("in_review", in_review);
			result.put("force_update", force_update);
			result.put("update_url", url);
			//result.put("enable_youtube", enable_youtube);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		
//		Random rand = new Random();
//		int value = rand.nextInt(n);
//		if(value == 1) {
//			result.put("force_update", true);
//			result.put("update_url", "https://play.google.com/store/apps/details?id=com.ccstudio.webbrowser");
//		}
		
		String data = result.toString();
		return Response
				.ok(data)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods",
						"PUT, GET, POST, DELETE, OPTIONS").build();
	}
}
