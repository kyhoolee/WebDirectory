package com.supperarrow.directory;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;


public class DirectoryApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public DirectoryApplication() {
		singletons.add(new DirectoryResource());
		singletons.add(new BrowserResource());
		singletons.add(new NewsResource());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
