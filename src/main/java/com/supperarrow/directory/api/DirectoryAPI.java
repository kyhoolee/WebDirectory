package com.supperarrow.directory.api;

import org.json.JSONArray;
import org.json.JSONObject;

import com.supperarrow.directory.util.CommonUtil;
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
	
	
	
	
	public static final String sites =  "[{\"cover\":\"http://gamechoandroids.com/wp-content/uploads/2014/06/YouTube.png\",\"title\":\"Download Youtube\",\"url\":\"http://m.youtube.com\"},{\"cover\":\"http://i.utdstc.com/icons/256/viki-tv-android.png\",\"title\":\"Drama Movie\",\"url\":\"https://www.viki.com/\"},{\"cover\":\"http://www.singing-bell.com/wp-content/uploads/2015/05/mp3-icon.png\",\"title\":\"Mp3 Download\",\"url\":\"http://sourcemp3.com\"},{\"cover\":\"http://128.199.148.119/static/bro/vimeo.png\",\"title\":\"Vimeo\",\"url\":\"https://www.vimeo.com/\"},{\"cover\":\"http://128.199.148.119/static/bro/dailymotion.png\",\"title\":\"Dailymotion\",\"url\":\"http://dailymotion.com/\"},{\"cover\":\"http://128.199.148.119/static/bro/facebook.com-1381205514.png\",\"title\":\"Facebook\",\"url\":\"http://m.facebook.com\"},{\"cover\":\"http://128.199.148.119/static/bro/soundcloud.png\",\"title\":\"SoundCloud\",\"url\":\"http://soundcloud.com\"},{\"cover\":\"https://onesearch.library.utoronto.ca/sites/all/themes/UTL_bootstrap/img/mediaicons/tumblr.png\",\"title\":\"Tumblr\",\"url\":\"http://tumblr.com/\"},{\"cover\":\"https://cdn.shopify.com/s/files/1/0070/7032/files/1444853979_Instagram.png\",\"title\":\"Instagram\",\"url\":\"http://instagram.com/\"},{\"cover\":\"http://icons.iconarchive.com/icons/marcus-roberto/google-play/512/Gmail-icon.png\",\"title\":\"Gmail\",\"url\":\"https://mail.google.com\"},{\"cover\":\"http://apkota.com/wp-content/themes/apkota/noimage.png\",\"title\":\"Free apps\",\"url\":\"http://www.9apps.com/\"}]";
	public static final String vi_sites = "[{\"cover\":\"http://128.199.148.119/static/bro/mp3.zing.vn-1372407479.png\",\"title\":\"Zing Mp3\",\"url\":\"http://m.mp3.zing.vn/\"},{\"cover\":\"http://128.199.148.119/static/bro/chiasenhac.jpg\",\"title\":\"Tải Nhạc\",\"url\":\"http://chiasenhac.com/\"},{\"cover\":\"http://128.199.148.119/static/bro/nhaccuatui.png\",\"title\":\"Nhaccuatui\",\"url\":\"http://m.nhaccuatui.com/\"},{\"cover\":\"http://128.199.148.119/static/bro/youtube.com-1381205141.png\",\"title\":\"Tải Youtube\",\"url\":\"http://m.youtube.com\"},{\"cover\":\"http://128.199.148.119/static/bro/vimeo.png\",\"title\":\"Người Lớn\",\"url\":\"https://www.viki.com/\"},{\"cover\":\"http://128.199.148.119/static/bro/dailymotion.png\",\"title\":\"Truyền Hình\",\"url\":\"http://xemphimon.com/\"},{\"cover\":\"http://128.199.148.119/static/bro/facebook.com-1381205514.png\",\"title\":\"Facebook\",\"url\":\"http://m.facebook.com\"},{\"cover\":\"http://128.199.148.119/static/bro/soundcloud.png\",\"title\":\"Nhạc Sàn\",\"url\":\"http://soundcloud.com\"},{\"cover\":\"http://128.199.148.119/static/bro/tv.zing.vn-1381396723.png\",\"title\":\"Xem TV\",\"url\":\"http://tv.zing.vn\"},{\"cover\":\"http://128.199.148.119/static/bro/dantri.com.vn-1381205614.png\",\"title\":\"Dantri\",\"url\":\"http://dantri.com.vn\"},{\"cover\":\"http://128.199.148.119/static/bro/www.123phim.vn-1373438712.png\",\"title\":\"Phim hot\",\"url\":\"http://www.phimmoi.net/\"},{\"cover\":\"http://1.stc.laban.vn/largeicons/24h.com.vn-1381204839.png\",\"title\":\"24h\",\"url\":\"http://24h.com.vn\"}]";
	
	
	
	
	
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
	
	
	public static String getHttpData(String url) {
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
		String data = CommonUtil.readFile("top_sites.txt");
//		JSONObject json = new JSONObject(data);
//		JSONArray web = json.getJSONArray("web");
		
		return vi_sites;
	}
	
	public static String getEnWeb() {
		String data = CommonUtil.readFile("top_sites_en.txt");
//		JSONObject json = new JSONObject(data);
//		JSONArray web = json.getJSONArray("web");
		
		return sites;
	}

	
	public static void main(String[] args) {
		String data = getHttpData("http://api.5play.mobi/news/v0.1/article?lid=10687220&sid=999&cid=999");// CommonUtil.readFile("homepage.json");
		JSONObject json = new JSONObject(data);
		JSONObject dat = json.getJSONObject("data");
		dat = dat.getJSONObject("linfo");
		data = dat.getString("content");
		System.out.println(data);
		
		
	}
}
