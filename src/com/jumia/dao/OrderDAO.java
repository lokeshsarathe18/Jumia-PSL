package com.jumia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.jumia.bean.ExceptionMessage;
import com.jumia.bean.Order;
import com.jumia.bean.Product;
import com.jumia.bean.User;
import com.jumia.database.JumiaDB;
import com.jumia.util.OrderNotFoundException;
import com.jumia.util.OrderStatus;

public class OrderDAO {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public Order getOrderDetailsForUser(String username, String orderNumber) throws OrderNotFoundException {
		Order order = null;
		if (conn == null)
			conn = JumiaDB.getConnection();
		try {
			Set<Product> cart = new LinkedHashSet<Product>();
			Product p = null;
			User user = new UserDAO().getUser(username);
			ps = conn.prepareStatement(
					"SELECT cart.product_id, product_code, product_name, product_description, product_price, category_name, units_purchased "
							+ "FROM cart, orders, products, category "
							+ "where orders.order_number = ? and orders.ordered_by = ? "
							+ "and orders.order_id = cart.order_id " + "and cart.product_id = products.product_id "
							+ "and category.category_id = products.category_id;");
			ps.setString(1, orderNumber);
			ps.setInt(2, user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()) {
				p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6), rs.getInt(7));
				cart.add(p);
			}

			ps = conn.prepareStatement("SELECT * FROM orders where orders.ordered_by = ? and orders.order_number = ?");
			ps.setInt(1, user.getUserId());
			ps.setString(2, orderNumber);
			rs = ps.executeQuery();
			if (rs.next()) {
				OrderStatus status = OrderStatus.valueOf(rs.getString(5));
				order = new Order(rs.getInt(1), orderNumber, rs.getDate(3), username, status, cart);
			}
		} catch (Exception e) {
			System.out.println("+++Exception in getOrderForUser: " + e);
		}
		if (order == null)
			throw new OrderNotFoundException(
					new ExceptionMessage(String.format("Order %s :: not found for user %s", orderNumber, username)));
		return order;
	}

	public Set<Order> getAllOrdersForUser(String username) throws OrderNotFoundException {
		Set<Order> _orders = new LinkedHashSet<Order>();
		if (conn == null)
			conn = JumiaDB.getConnection();
		try {
			Set<String> order_numbers = new LinkedHashSet<String>();
			ps = conn.prepareStatement(
					"select order_number from orders, users where users.username = ? and orders.ordered_by = users.user_id");
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				order_numbers.add(rs.getString(1));
			}
			Iterator<String> it = order_numbers.iterator();
			while (it.hasNext()) {
				String number = (String) it.next();
				_orders.add(getOrderDetailsForUser(username, number));
			}
		} catch (Exception e) {
			System.out.println("+++Exception in getAllOrdersForUser: " + e);
		}
		if (_orders.isEmpty())
			throw new OrderNotFoundException(new ExceptionMessage("Orders not found for user " + username));
		return _orders;
	}

	public Set<Order> getAllOrders() throws OrderNotFoundException {
		Set<Order> _orders = new LinkedHashSet<Order>();
		if (conn == null)
			conn = JumiaDB.getConnection();
		try {
			Map<String, String> order_numbers = new LinkedHashMap<String, String>();
			ps = conn.prepareStatement(
					"select order_number, username from orders, users where users.user_id = orders.ordered_by order by ordered_on desc");
			rs = ps.executeQuery();
			while (rs.next()) {
				order_numbers.put(rs.getString(1), rs.getString(2));
			}
			for (Entry<String, String> entry : order_numbers.entrySet()) {
				_orders.add(getOrderDetailsForUser(entry.getValue(), entry.getKey()));
			}
		} catch (Exception e) {
			System.out.println("+++Exception in getAllOrders: " + e);
		}
		if (_orders.isEmpty())
			throw new OrderNotFoundException(new ExceptionMessage("Order not found for any user "));
		return _orders;
	}

	public Set<Order> getAllOrdersBetween(String fromDate, String toDate) throws OrderNotFoundException {
		Set<Order> _orders = new LinkedHashSet<Order>();
		if (conn == null)
			conn = JumiaDB.getConnection();
		try {
			Map<String, String> order_numbers = new LinkedHashMap<String, String>();
			ps = conn.prepareStatement(
					"select order_number, username from orders, users where users.user_id = orders.ordered_by "
							+ "and ordered_on between ? and ? order by ordered_on desc");
			ps.setDate(1, Date.valueOf(fromDate));
			ps.setDate(2, Date.valueOf(toDate));
			rs = ps.executeQuery();
			while (rs.next()) {
				order_numbers.put(rs.getString(1), rs.getString(2));
			}
			for (Entry<String, String> entry : order_numbers.entrySet()) {
				_orders.add(getOrderDetailsForUser(entry.getValue(), entry.getKey()));
			}
		} catch (Exception e) {
			System.out.println("+++Exception in getAllOrdersBetween: " + e);
		}
		if (_orders.isEmpty())
			throw new OrderNotFoundException(new ExceptionMessage("Order not found for any user "));
		return _orders;
	}
}