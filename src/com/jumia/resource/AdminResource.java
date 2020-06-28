package com.jumia.resource;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jumia.bean.Order;
import com.jumia.services.JumiaServices;
import com.jumia.util.OrderNotFoundException;

@Path("/admin")
public class AdminResource {
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Set<Order> fetchAllOrders() throws OrderNotFoundException {
		return new JumiaServices().getAllOrders();
	}

	@GET
	@Path("{fromDate}/{toDate}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Set<Order> fetchAllOrdersBetween(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate)
			throws OrderNotFoundException {
		return new JumiaServices().getAllOrdersBetween(fromDate, toDate);
	}
}
