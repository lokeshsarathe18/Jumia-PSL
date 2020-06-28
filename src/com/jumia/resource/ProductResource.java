package com.jumia.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jumia.bean.MapWrapper;
import com.jumia.services.JumiaServices;
import com.jumia.util.OrderNotFoundException;
import com.jumia.util.ProductNotFoundException;

@Path("/products")
public class ProductResource {
	@GET
	@Path("1/{fromDate}/{toDate}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public MapWrapper fetchPopularProductsBetween(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate)
			throws OrderNotFoundException, ProductNotFoundException {
		MapWrapper map = new MapWrapper(new JumiaServices().getPopularProductsBetween(fromDate, toDate));
		return map;
	}

	@GET
	@Path("2/{fromDate}/{toDate}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public MapWrapper fetchProductsConsumptionBetween(@PathParam("fromDate") String fromDate, @PathParam("toDate") String toDate)
			throws OrderNotFoundException, ProductNotFoundException {
		MapWrapper map = new MapWrapper(new JumiaServices().getProductsConsumptionBetween(fromDate, toDate));
		return map;
	}
}
