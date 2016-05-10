package com.cs548.bookStore.resthandlers;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;

import com.cs548.bookStore.exceptions.InvalidAcctExResolver;
import com.cs548.bookStore.exceptions.UnknownResourceExResolver;

/*   See web.xml file for Jersey configuration  */
/*   Need to register classes with @PATH annotations and other JAX-RS components */
@ApplicationPath("/")
public class RestApplicationConfig extends Application {
	private Set<Class<?>> restClassSet = new HashSet<Class<?>>();
	
	public RestApplicationConfig() {
		/* AuthorizationFilter is a servlet filter that could automatically perform authorization on all incoming requests */
//		restClassSet.add(AuthorizationFilter.class);
		restClassSet.add(JacksonFeature.class);
		restClassSet.add(BookRestHandler.class);
		restClassSet.add(InvalidAcctExResolver.class);
		restClassSet.add(UnknownResourceExResolver.class);
	}
	
	public Set<Class<?>> getClasses() {
		return restClassSet;
	}
}
