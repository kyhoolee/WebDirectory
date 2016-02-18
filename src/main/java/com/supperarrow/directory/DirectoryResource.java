package com.supperarrow.directory;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.supperarrow.directory.api.ConfigAPI;
import com.supperarrow.directory.api.DirectoryAPI;
import com.supperarrow.directory.api.GrabyoVideoUtils;
import com.supperarrow.directory.api.MeCloudVideoUtils;
import com.supperarrow.directory.api.VimeoVideoUtils;
import com.supperarrow.directory.mysql.Article;
import com.supperarrow.directory.mysql.DbUtils;
import com.supperarrow.directory.util.LoggerUtil;

@Path("/directory")
public class DirectoryResource {
	public static Logger logger = LoggerUtil.getDailyLogger("DirectoryResource_log");

	public DbUtils _dbUtils;
	public DirectoryResource() {
		_dbUtils = new DbUtils();
	}
	
	@Path("/football_video")
	@POST
	@Produces("text/plain;charset=utf-8")
	public Response getFootballNews(String info, @Context HttpServletRequest req) {
		long cid = 1795;
		int start = 0;
		int end = 10;
		try {
			JSONObject jsonObject = new JSONObject(info);


			cid = jsonObject.getInt("cid");
			start = jsonObject.getInt("start");
			end = jsonObject.getInt("end");
			logger.info("getfootballnews: " + info);
		} catch (Exception e) { 
			logger.error(e);
		}
		
		List<Article> data = _dbUtils.getArticleFromDB(cid, start, end);
		logger.info(data.size());
		JSONArray res = new JSONArray(data);
		return Response.ok(res.toString()).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}
	
	@Path("/mecloud_video")
	@GET
	@Produces("text/plain;charset=utf-8")
	public Response getMecloudVideo(
			@QueryParam("videoUrl") String videoUrl,
			@QueryParam("referer") String referer,
			@Context HttpServletRequest req) {

		String data = MeCloudVideoUtils.getVideoMecloud(videoUrl, referer);
		return Response.ok(data).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}
	
	
	@Path("/grabyo_video")
	@GET
	@Produces("text/plain;charset=utf-8")
	public Response getGrabyoVideo(
			@QueryParam("videoUrl") String videoUrl,
			@QueryParam("referer") String referer,
			@Context HttpServletRequest req) {

		String data = GrabyoVideoUtils.getGrabyoSource("https://grabyo.com/video.jsp?shareId=" + videoUrl, referer);
		return Response.ok(data).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}
	
	@Path("/vimeo_video")
	@GET
	@Produces("text/plain;charset=utf-8")
	public Response getVimeoVideo(
			@QueryParam("videoUrl") String videoUrl,
			@QueryParam("referer") String referer,
			@Context HttpServletRequest req) {

		String data = VimeoVideoUtils.getVimeoSource(videoUrl, referer);
		return Response.ok(data).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}

	@Path("/top_sites")
	@POST
	@Produces("text/plain;charset=utf-8")
	public Response getTopSites(String info, @Context HttpServletRequest req) {
		String lang = "en";
		try {
			JSONObject jsonObject = new JSONObject(info);

			lang = jsonObject.getString("language");

			String os = jsonObject.getString("os");
			String did = jsonObject.getString("did");
			String version = jsonObject.getString("version");

			logger.info(os + "\t" + did + "\t" + version + "\t" + "top_news");
		} catch (Exception e) {
			logger.error(e);
		}
		String data = ConfigAPI.getTopSites(lang);
		return Response.ok(data).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}
	
	

	@Path("/top_news")
	@POST
	@Produces("text/plain;charset=utf-8")
	public Response getTopNews(String info, @Context HttpServletRequest req) {
		int size = 10;

		try {
			JSONObject jsonObject = new JSONObject(info);

			size = jsonObject.getInt("size");

			String os = jsonObject.getString("os");
			String did = jsonObject.getString("did");
			String version = jsonObject.getString("version");

			int start = jsonObject.getInt("start");

			logger.info(os + "\t" + did + "\t" + version + "\t" + "top_news");
		} catch (Exception e) {
			logger.error(e);
		}
		String data = DirectoryAPI.getTopNews(size);
		return Response.ok(data).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}

	@Path("/time_news")
	@POST
	@Produces("text/plain;charset=utf-8")
	public Response getTimeNews(String info, @Context HttpServletRequest req) {
		int size = 10;
		try {
			JSONObject jsonObject = new JSONObject(info);
			size = jsonObject.getInt("size");
			String os = jsonObject.getString("os");
			String did = jsonObject.getString("did");
			String version = jsonObject.getString("version");

			int start = jsonObject.getInt("start");

			logger.info(os + "\t" + did + "\t" + version + "\t" + "time_news");
		} catch (Exception e) {
			logger.error(e);
		}
		String data = DirectoryAPI.getTimeNews(size);
		return Response.ok(data).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}

	@Path("/news_site")
	@POST
	@Produces("text/plain;charset=utf-8")
	public Response getNewsSite(String info, @Context HttpServletRequest req) {
		int size = 10;
		try {
			JSONObject jsonObject = new JSONObject(info);
			size = jsonObject.getInt("size");
			String os = jsonObject.getString("os");
			String did = jsonObject.getString("did");
			String version = jsonObject.getString("version");

			int start = jsonObject.getInt("start");

			logger.info(os + "\t" + did + "\t" + version + "\t" + "news_site");
		} catch (Exception e) {
			logger.error(e);
		}
		String data = DirectoryAPI.getNewsSite(size);
		return Response.ok(data).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}

	@Path("/top_film")
	@POST
	@Produces("text/plain;charset=utf-8")
	public Response getTopFilm(String info, @Context HttpServletRequest req) {
		int size = 10;
		try {
			JSONObject jsonObject = new JSONObject(info);
			size = jsonObject.getInt("size");
			String os = jsonObject.getString("os");
			String did = jsonObject.getString("did");
			String version = jsonObject.getString("version");

			int start = jsonObject.getInt("start");

			logger.info(os + "\t" + did + "\t" + version + "\t" + "top_film");
		} catch (Exception e) {
			logger.error(e);
		}
		String data = DirectoryAPI.getTopFilm(size);
		return Response.ok(data).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}

	@Path("/top_video")
	@POST
	@Produces("text/plain;charset=utf-8")
	public Response getTopVideo(String info, @Context HttpServletRequest req) {
		int size = 10;
		try {
			JSONObject jsonObject = new JSONObject(info);
			size = jsonObject.getInt("size");
			String os = jsonObject.getString("os");
			String did = jsonObject.getString("did");
			String version = jsonObject.getString("version");

			int start = jsonObject.getInt("start");

			logger.info(os + "\t" + did + "\t" + version + "\t" + "top_video");
		} catch (Exception e) {
			logger.error(e);
		}
		String data = DirectoryAPI.getTopVideo(size);
		return Response.ok(data).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}

	@Path("/funny_story")
	@POST
	@Produces("text/plain;charset=utf-8")
	public Response getFunnyStory(String info, @Context HttpServletRequest req) {
		int size = 10;
		try {
			JSONObject jsonObject = new JSONObject(info);
			size = jsonObject.getInt("size");
			String os = jsonObject.getString("os");
			String did = jsonObject.getString("did");
			String version = jsonObject.getString("version");

			int start = jsonObject.getInt("start");

			logger.info(os + "\t" + did + "\t" + version + "\t" + "funny_story");
		} catch (Exception e) {
			logger.error(e);
		}
		String data = DirectoryAPI.getFunnyStory(size);
		return Response.ok(data).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}

	@Path("/funny_img")
	@POST
	@Produces("text/plain;charset=utf-8")
	public Response getFunnyImg(String info, @Context HttpServletRequest req) {
		int size = 10;
		try {
			JSONObject jsonObject = new JSONObject(info);
			size = jsonObject.getInt("size");
			String os = jsonObject.getString("os");
			String did = jsonObject.getString("did");
			String version = jsonObject.getString("version");

			int start = jsonObject.getInt("start");

			logger.info(os + "\t" + did + "\t" + version + "\t" + "funny_img");
		} catch (Exception e) {
			logger.error(e);
		}
		String data = DirectoryAPI.getFunnyImg(size);
		return Response.ok(data).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}

	@Path("/utilities")
	@POST
	@Produces("text/plain;charset=utf-8")
	public Response getUtilities(String info, @Context HttpServletRequest req) {
		int size = 10;
		try {
			JSONObject jsonObject = new JSONObject(info);
			size = jsonObject.getInt("size");
			String os = jsonObject.getString("os");
			String did = jsonObject.getString("did");
			String version = jsonObject.getString("version");

			int start = jsonObject.getInt("start");

			logger.info(os + "\t" + did + "\t" + version + "\t" + "utilities");
		} catch (Exception e) {
			logger.error(e);
		}
		String data = DirectoryAPI.getUtilities(size);
		return Response.ok(data).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}

	@Path("/football")
	@POST
	@Produces("text/plain;charset=utf-8")
	public Response getFootball(String info, @Context HttpServletRequest req) {
		int size = 10;
		try {
			JSONObject jsonObject = new JSONObject(info);
			size = jsonObject.getInt("size");
			String os = jsonObject.getString("os");
			String did = jsonObject.getString("did");
			String version = jsonObject.getString("version");

			int start = jsonObject.getInt("start");

			logger.info(os + "\t" + did + "\t" + version + "\t" + "football");
		} catch (Exception e) {
			logger.error(e);
		}
		String data = DirectoryAPI.getFootball(size);
		return Response.ok(data).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}

	@Path("/product")
	@POST
	@Produces("text/plain;charset=utf-8")
	public Response getProduct(String info, @Context HttpServletRequest req) {
		int size = 10;
		try {
			JSONObject jsonObject = new JSONObject(info);
			size = jsonObject.getInt("size");
			String os = jsonObject.getString("os");
			String did = jsonObject.getString("did");
			String version = jsonObject.getString("version");

			int start = jsonObject.getInt("start");

			logger.info(os + "\t" + did + "\t" + version + "\t" + "product");
		} catch (Exception e) {
			logger.error(e);
		}
		String data = DirectoryAPI.getProduct(size);
		return Response.ok(data).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS").build();
	}

}
