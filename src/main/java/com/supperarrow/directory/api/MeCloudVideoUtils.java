package com.supperarrow.directory.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONObject;

public class MeCloudVideoUtils {

	public static void main(String[] args) {
		//getSample();
		getRenderVideo();
	}

	public static void httpSample(String videoUrl, String referer) {
		String data = DirectoryAPI
				.getHttpData("http://5play.me:8892/WebDirectory-0.0.1-SNAPSHOT/directory/mecloud_video" + "?videoUrl="
						+ videoUrl + "&referer=" + referer);

		System.out.println(data);
	}

	public static void getSample() {
		//System.out.println(getVideoMecloud("http://embed.mecloud.vn/play/rib2DoeT8B", "http://netlife.vn"));
		System.out.println(getVideoMecloud("http://embed.mecloud.vn/custom/CCBPYH", "http://www.tienphong.vn"));
	}

	public static String getVideoByCache(String script, String referer) {
		String result = "";

		boolean check = RedisCacheAPI.checkTime(script);
		if (check) {
			try {
				return RedisCacheAPI.getCache(script);
			} catch (Exception e) {

			}
		}

		result = getVideoMecloud(script, referer);

		try {
			RedisCacheAPI.setCache(script, result);
			RedisCacheAPI.setTime(script, System.currentTimeMillis());
		} catch (Exception e) {

		}

		return result;
	}

	public static String getVideoMecloud(String script, String referer) {
		if (script.contains("play")) {
			//System.out.println("Get play link ");
			String link = getPlayMeCloud(script, referer);
			return link;
		} else if (script.contains("custom")) {
			System.out.println("Get custom link ");
			String link = getCustomMeCloud(script, referer);
			return link;
		} else if (script.contains("render")) {
			String link = getRenderMeCloud(script, referer);
			return link;
		}
		return null;
	}

	public static void getVideoSample() {
		String script = "http://embed.mecloud.vn/play/rib2DoeT8B";
		String urlReferer = "http://netlife.vn";
		String link = getPlayMeCloud(script, urlReferer);
		System.out.println("___: " + link);
	}

	public static void getRenderVideo() {
		String script = "http://embed.mecloud.vn/render/gnQ3oILyyt";
		String urlReferer = "";
		String link = getRenderMeCloud(script, urlReferer);
		System.out.println("___: " + link);
	}

	public static String getRenderMeCloud(String link, String referer) {
		try {
			String first = sendGetWithGzipResponse(link, referer);
			first = parseRenderFirst(first);

			return "http:" + first;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String parseRenderFirst(String data) {
		// get link 360
		StringBuffer buffer = new StringBuffer(data);
		String sample = "\"quality\":\"360p\",\"url\":\"";
		int start = buffer.indexOf(sample);
		// System.out.println(buffer.substring(start));
		int end = buffer.indexOf("\"", start + sample.length() + 1);

		String link360 = buffer.substring(start + sample.length(), end);
		return StringEscapeUtils.unescapeJava(link360);
	}

	public static void getCustomVideo() {
		String script = "http://embed.mecloud.vn/custom/CCBPYH";
		String urlReferer = "http://www.tienphong.vn";
		String link = getCustomMeCloud(script, urlReferer);
		System.out.println("___: " + link);
	}

	public static String getCustomMeCloud(String link, String referer) {
		try {
			String first = sendGetWithGzipResponse(link, referer);
			// System.out.println(first + "\n");
			first = parseCustomFirst(first);
			// System.out.println(first + "\n");

			String second = sendGetWithGzipResponse("http:"+first, referer);
			// System.out.println(second + "\n");
			second = parseCustomSecond(second);
			// System.out.println(second + "\n");

			return "http:"+second;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String parseCustomFirst(String data) {
		int start = data.indexOf("<script");
		start = data.indexOf("<script", start + 1);
		int end = data.indexOf("\" async>", start + 1);
		String result = data.substring(start + "<script src=\"".length(), end);

		return result;
	}

	public static String parseCustomSecond(String data) {
		// get link 360
		StringBuffer buffer = new StringBuffer(data);
		String sample = "\"quality\":\"360p\",\"url\":\"";
		int start = buffer.indexOf(sample);
		// System.out.println(buffer.substring(start));
		int end = buffer.indexOf("\"", start + sample.length() + 1);

		String link360 = buffer.substring(start + sample.length(), end);
		return StringEscapeUtils.unescapeJava(link360);
	}

	public static String getPlayMeCloud(String link, String referer) {

		try {
			String first = sendGetWithGzipResponse(link, referer);
			//System.out.println(first + "\n");
			first = parseFirstSource(first);
			//System.out.println(first + "\n");

			String second = sendGetWithGzipResponse("http:" + first, referer);
			//System.out.println(second + "\n");
			second = parseSecondSource(second);
			//System.out.println(second + "\n");

			return "http:" + second;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static String parseSecondSource(String data) {
		// get link 360
		StringBuffer buffer = new StringBuffer(data);
		String sample = "\"quality\":\"360p\",\"url\":\"";
		int index = buffer.indexOf(sample);

		int end = 1;

		for (int i = index + sample.length(); i < buffer.length(); i++) {
			if (buffer.charAt(i) == '"') {
				end = i;
				break;
			}
		}

		String link360 = buffer.substring(index + sample.length(), end);
		return StringEscapeUtils.unescapeJava(link360);

	}

	private static String parseFirstSource(String data) {
		// get link 240
		Pattern src240 = Pattern.compile("src=\"([^;]+\\&)\"");
		Matcher mtcl240 = src240.matcher(data);
		if (mtcl240.find()) {
			return mtcl240.group(1);
		}

		return null;
	}

	public static String sendGetWithGzipResponse(String url, String referer) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("Referer", referer);
		con.setRequestProperty("Accept-Encoding", "gzip");

		BufferedReader in = new BufferedReader(new InputStreamReader(new GZIPInputStream(con.getInputStream())));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();

	}
}
