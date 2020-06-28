package com.jumia.dao;

public class MainTest {
	public static void main(String[] args) throws Exception {
		UserDAO udao = new UserDAO();
		//System.out.println(udao.getUser("lokesh"));
		
		OrderDAO odao = new OrderDAO();
		//System.out.println(odao.getOrderDetailsForUser("lokesh", "3135"));
		//System.out.println(odao.getAllOrdersForUser("lokesh"));
		//System.out.println(odao.getAllOrders());
		//System.out.println(odao.getAllOrdersBetween("2020-04-01", "2020-04-25"));
	
		ProductDAO pdao = new ProductDAO();
		//System.out.println(pdao.getProduct(1));
		//System.out.println(pdao.getPopularProductsBetween("2020-04-01", "2020-04-25"));
		//System.out.println(pdao.getProductsConsumptionBetween("2020-04-01", "2020-04-25"));
		
		WishlistDAO wdao = new WishlistDAO();
		//System.out.println(wdao.getWishlistForUser("shiv"));
	}
}