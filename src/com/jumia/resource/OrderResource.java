package com.jumia.resource;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jumia.bean.Order;
import com.jumia.services.JumiaServices;
import com.jumia.util.OrderNotFoundException;

public class OrderResource {

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	@Path("{orderNumber}")
	public Response fetchOrderDetailsForUser(@PathParam("username") String username,
			@PathParam("orderNumber") String orderNumber) throws OrderNotFoundException {
		return Response.ok(new JumiaServices().getOrderDetailsForUser(username, orderNumber)).build();
	}

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Set<Order> fetchAllOrdersForUser(@PathParam("username") String username) throws OrderNotFoundException {
		return new JumiaServices().getAllOrdersForUser(username);
	}
}
