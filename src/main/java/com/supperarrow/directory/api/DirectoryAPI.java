package com.supperarrow.directory.api;

import org.json.JSONArray;
import org.json.JSONObject;

import com.supperarrow.directory.util.HttpConnection;

public class DirectoryAPI {
	
	public static final String top_news = "http://hk.napi.ucweb.com/3/classes/vietnam/categories/news/lists/top_news?_app_id=nav_en&_fetch=1&_ch=u3card&_size=";
	public static final String time_news = "http://hk.napi.ucweb.com/3/classes/vietnam/categories/news/lists/news?_app_id=nav_en&_fetch=1&_ch=u3card&_size=";
	public static final String news_site = "http://hk.napi.ucweb.com/3/classes/vietnam/categories/vi_u3_card/lists/u3_card_news_2nd_sites?_app_id=nav_en&_fetch=1&_ch=u3card&_size=";
	
	public static final String top_video = "http://hk.napi.ucweb.com/3/classes/vietnam/categories/news/lists/u3_card_video?_app_id=nav_en&_fetch=1&_ch=u3card&_size=";
	public static final String top_film = "http://hk.napi.ucweb.com/3/classes/vietnam/categories/news/lists/film?_app_id=nav_en&_fetch=1&_ch=u3card&_size=";
	
	public static final String funny_story = "http://hk.napi.ucweb.com/3/classes/vietnam/categories/humor/lists/uc_card_funny_story?_app_id=nav_en&_fetch=1&_ch=u3card&_size=";
	public static final String funny_img = "http://hk.napi.ucweb.com/3/classes/vietnam/categories/funn/lists/funn_img_size_modified?_app_id=nav_en&_fetch=1&_ch=u3card&_size=";
	
	public static final String utilities = "http://hk.napi.ucweb.com/3/classes/vietnam/categories/vi_u3_card/lists/u3_card_utilities_top?_app_id=nav_en&_fetch=1&_ch=u3card&_size=";
	public static final String football = "http://hk.napi.ucweb.com/3/classes/vietnam/categories/vi_u3_card/lists/u3_card_football_cate?_app_id=nav_en&_fetch=1&_ch=u3card&_size=";
	public static final String product = "http://hk.napi.ucweb.com/3/classes/vietnam/categories/lazada/lists/lazada_hot_product?_app_id=nav_en&_fetch=1&_ch=u3card&_size=";
	
	public static String getTopNews(int size) {
		return getHttpData(top_news + size);
	}
	
	public static String getTimeNews(int size) {
		return getHttpData(time_news + size);
	}
	
	public static String getNewsSite(int size) {
		return getHttpData(news_site);
	}
	
	public static String getTopFilm(int size) {
		return getHttpData(top_film + size);
	}
	
	public static String getTopVideo(int size) {
		return getHttpData(top_video + size);
	}
	
	public static String getFunnyStory(int size) {
		return getHttpData(funny_story + size);
	}
	
	public static String getFunnyImg(int size) {
		return getHttpData(funny_img + size);
	}
	
	public static String getUtilities(int size) {
		return getHttpData(utilities + size);
	}
	
	public static String getFootball(int size) {
		return getHttpData(football + size);
	}
	
	public static String getProduct(int size) {
		return getHttpData(product + size);
	}
	
	
	private static String getHttpData(String url) {
		try {
			HttpConnection.HttpResponse response = HttpConnection.sendGet(url);
			if(response.code != 200) {
				return "{}";
			}
			return response.data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "{}";
	}
	
	public static String getViWeb() {
		String data = (getHttpData("http://5play.me/5play/homepage.json"));
		JSONObject json = new JSONObject(data);
		JSONArray web = json.getJSONArray("web");
		
		return web.toString();
	}
	
	public static String getEnWeb() {
		String data = (getHttpData("http://5play.me/5play/homepage_en.json"));
		JSONObject json = new JSONObject(data);
		JSONArray web = json.getJSONArray("web");
		
		return web.toString();
	}

	
	public static void main(String[] args) {
		String data = (getHttpData("http://5play.me/5play/homepage.json"));
		JSONObject json = new JSONObject(data);
		JSONArray web = json.getJSONArray("web");
		System.out.println(web);
	}
}
