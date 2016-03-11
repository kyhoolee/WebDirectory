package com.supperarrow.directory;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.supperarrow.directory.model.Article;
import com.supperarrow.directory.mysql.DbUtils;
import com.supperarrow.directory.util.LoggerUtil;


@Path("/news")
public class NewsResource {
	public static Logger logger = LoggerUtil.getDailyLogger("NewsResource_log");
	
	public DbUtils _dbUtils;
	public NewsResource() {
		_dbUtils = new DbUtils();
		
	}
	
	@Path("/football_video")
	@GET
	@Produces("text/plain;charset=utf-8")
	public Response getFootballNews(
			@QueryParam("cid") @DefaultValue("1795") int cid, 
			@QueryParam("start") @DefaultValue("0") int start, 
			@QueryParam("end") @DefaultValue("1") int end, 
			@Context HttpServletRequest req) {
		
		
		List<Article> data = _dbUtils.getArticleFromDB(cid, start, end);
		logger.info(data.size());
		JSONArray res = new JSONArray(data);
		return Response.ok(res.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}
}
