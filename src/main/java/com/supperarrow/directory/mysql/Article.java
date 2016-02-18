package com.supperarrow.directory.mysql;

import java.util.Date;

public class Article {

	private int id;
	private String url;
	private String title;
	private String _abstract;
	private String content;
	private String html_Content;
	private String coverUrl;	
	private long publishedTime;
	private long crawledTime;
	private int websiteId;
	private int categoryId;		
	
	public Article() {
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String get_abstract() {
		return _abstract;
	}
	public void set_abstract(String _abstract) {
		this._abstract = _abstract;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHtml_Content() {
		return html_Content;
	}
	public void setHtml_Content(String html_Content) {
		this.html_Content = html_Content;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public long getPublishedTime() {
		return publishedTime;
	}
	public void setPublishedTime(long publishedTime) {
		this.publishedTime = publishedTime;
	}
	public long getCrawledTime() {
		return crawledTime;
	}
	public void setCrawledTime(long crawledTime) {
		this.crawledTime = crawledTime;
	}
	public int getWebsiteId() {
		return websiteId;
	}
	public void setWebsiteId(int websiteId) {
		this.websiteId = websiteId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
			
	public void showObject(){
		System.out.println("id: "+id);
		System.out.println("url: "+url);
		System.out.println("title: "+title);
		System.out.println("abtract: "+_abstract);
		System.out.println("content: "+content);
		//System.out.println("html_content: "+html_Content);
		System.out.println("coverUrl: "+coverUrl);
		System.out.println("publish time: "+ new Date(publishedTime));
		System.out.println("crawler time: "+crawledTime);
		System.out.println("categoryId: "+categoryId);
		System.out.println("websiteId: "+websiteId);
	}
	
	public void showAbstract() {
		System.out.println("id: "+ id);
		System.out.println("url: "+url);
		System.out.println("title: "+title);
		System.out.println("abtract: "+_abstract);
		System.out.println("content: "+content);
		//System.out.println("html_content: "+html_Content);
		System.out.println("coverUrl: "+coverUrl);
		System.out.println("publish time: "+ new Date(publishedTime));
		System.out.println("crawler time: "+crawledTime);
		System.out.println("categoryId: "+categoryId);
		System.out.println("websiteId: "+websiteId);
		System.out.println("\n");
	}
}
