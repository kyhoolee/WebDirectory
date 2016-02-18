package com.supperarrow.directory.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GrabyoVideoUtils {
//https://grabyo.com/video.jsp?shareId=g6ZkGkUOjZo
	
	public static void main(String[] args) {
		getGrabyoSample();
	}
	
	//curl 'https://player.Grabyo.com/video/152074049' -H 'Host: player.Grabyo.com' -H 'User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:43.0) Gecko/20100101 Firefox/43.0' --compressed -H 
	//'Referer: http://www.bongda365.com.vn/video/real-madrid-5-1-sporting-gijon-vong-20-la-liga/'
	public static void getGrabyoSample() {
		String url = "https://grabyo.com/video.jsp?shareId=g6ZkGkUOjZo";
		String referer = "";
		
		try {
			String data = sendGetWithGzipResponse(url, referer);
			//System.out.println(data);
			data = parseGrabyoSource(data);
			
			System.out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getGrabyoSource(String url, String referer) {
		try {
			String data = sendGetWithGzipResponse(url, referer);
			data = parseGrabyoSource(data);
			
			//System.out.println(data);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String parseGrabyoSource(String data) {
		String result = "";
		//System.out.println(data);
		String signal = "youtubePlayerHttpPlaybackUrl\":\"";
		int begin = data.indexOf(signal);
		System.out.println(begin);
		int end = data.indexOf("mp4", begin + signal.length());
		System.out.println(end);
		
		result = data.substring(begin + signal.length(), end + "mp4".length());
		result = result.replace("\\/", "/");
		return result;
	}

	public static String sendGetWithGzipResponse(String url, String referer) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		//con.setRequestProperty("Referer", referer);
		//con.setRequestProperty("Accept-Encoding", "gzip");

		BufferedReader in = new BufferedReader(new InputStreamReader((con.getInputStream())));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();

	}
}
