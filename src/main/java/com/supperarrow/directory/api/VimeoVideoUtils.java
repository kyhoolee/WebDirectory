package com.supperarrow.directory.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class VimeoVideoUtils {
	
	public static void main(String[] args) {
		getVimeoSample();
	}
	
	//curl 'https://player.vimeo.com/video/152074049' -H 'Host: player.vimeo.com' -H 'User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:43.0) Gecko/20100101 Firefox/43.0' --compressed -H 
	//'Referer: http://www.bongda365.com.vn/video/real-madrid-5-1-sporting-gijon-vong-20-la-liga/'
	public static void getVimeoSample() {
		String url = "https://player.vimeo.com/video/152074049";
		String referer = "http://www.bongda365.com.vn";
		
		try {
			String data = sendGetWithGzipResponse(url, referer);
			data = parseVimeoSource(data);
			
			System.out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getVimeoSource(String url, String referer) {
		try {
			String data = sendGetWithGzipResponse(url, referer);
			data = parseVimeoSource(data);
			
			//System.out.println(data);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String parseVimeoSource(String data) {
		String result = "";
		System.out.println(data);
		String signal = "\"profile\":112,\"width\":640,\"mime\":\"video/mp4\",\"fps\":24,\"url\":\"";
		int begin = data.indexOf(signal);
		System.out.println(begin);
		int end = data.indexOf("\",\"cdn", begin + signal.length());
		System.out.println(end);
		
		result = data.substring(begin + signal.length(), end);
		
		return result;
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
