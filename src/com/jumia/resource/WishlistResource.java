package com.jumia.resource;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jumia.services.JumiaServices;
import com.jumia.util.WishlistNotFoundException;

public class WishlistResource {
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response fetchWishlistForUser(@PathParam("username") String username) throws WishlistNotFoundException {
		return Response.ok(new JumiaServices().getWishlistForUser(username)).build();
	}
}