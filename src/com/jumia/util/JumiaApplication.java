package com.jumia.util;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.jumia.resource.AdminResource;
import com.jumia.resource.ProductResource;
import com.jumia.resource.UserResource;

@ApplicationPath("/api")
public class JumiaApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(UserResource.class);
		classes.add(AdminResource.class);
		classes.add(ProductResource.class);
		return classes;
	}
}
