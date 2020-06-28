package com.jumia.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.jumia.bean.ExceptionMessage;
import com.jumia.bean.User;
import com.jumia.services.JumiaServices;
import com.jumia.util.UserNotFoundException;

@Path("/users")
public class UserResource {
	@Context
	// dependency injection- object auto created by jersy container
	private ResourceContext resourceContext;

	@GET
	@Produces(value = { MediaType.APPLICATION_JSON})
	@Path("{username}")
	public Response fetchUserDetails(@PathParam("username") String username) throws UserNotFoundException {
		User user = new JumiaServices().getUserDetails(username);
		return Response.ok(user).build();
	}
	
	@Path("{username}/{sub-resource-name}")
	public Object getSubResource(@PathParam("sub-resource-name") String subResourceName) {
		if ("orders".equals(subResourceName)) {
			return resourceContext.getResource(OrderResource.class);
		} else if ("wishlist".equals(subResourceName)) {
			return resourceContext.getResource(WishlistResource.class);
		}
		throw new WebApplicationException(
				Response.status(Status.BAD_REQUEST).entity(new ExceptionMessage("No such resource")).build());
	}
}