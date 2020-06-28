package com.jumia.services;

import java.util.Map;
import java.util.Set;

import com.jumia.bean.Order;
import com.jumia.bean.Product;
import com.jumia.bean.User;
import com.jumia.bean.Wishlist;
import com.jumia.dao.OrderDAO;
import com.jumia.dao.ProductDAO;
import com.jumia.dao.UserDAO;
import com.jumia.dao.WishlistDAO;
import com.jumia.util.OrderNotFoundException;
import com.jumia.util.ProductNotFoundException;
import com.jumia.util.UserNotFoundException;
import com.jumia.util.WishlistNotFoundException;

public class JumiaServices {
	public User getUserDetails(String username) throws UserNotFoundException {
		return new UserDAO().getUser(username);
	}

	public Order getOrderDetailsForUser(String username, String orderNumber) throws OrderNotFoundException {
		return new OrderDAO().getOrderDetailsForUser(username, orderNumber);
	}

	public Set<Order> getAllOrdersForUser(String username) throws OrderNotFoundException {
		return new OrderDAO().getAllOrdersForUser(username);
	}

	public Set<Order> getAllOrders() throws OrderNotFoundException {
		return new OrderDAO().getAllOrders();
	}

	public Set<Order> getAllOrdersBetween(String fromDate, String toDate) throws OrderNotFoundException {
		return new OrderDAO().getAllOrdersBetween(fromDate, toDate);
	}

	public Map<Product, Integer> getPopularProductsBetween(String fromDate, String toDate)
			throws OrderNotFoundException, ProductNotFoundException {
		return new ProductDAO().getPopularProductsBetween(fromDate, toDate);
	}

	public Map<Product, Integer> getProductsConsumptionBetween(String fromDate, String toDate)
			throws OrderNotFoundException, ProductNotFoundException {
		return new ProductDAO().getProductsConsumptionBetween(fromDate, toDate);
	}
	
	public Wishlist getWishlistForUser(String username) throws WishlistNotFoundException {
		return new WishlistDAO().getWishlistForUser(username);
	}
}