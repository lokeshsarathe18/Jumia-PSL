package com.jumia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jumia.bean.ExceptionMessage;
import com.jumia.bean.Product;
import com.jumia.database.JumiaDB;
import com.jumia.util.OrderNotFoundException;
import com.jumia.util.ProductNotFoundException;

public class ProductDAO {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public Product getProduct(int product_id) throws ProductNotFoundException {
		Product product = null;
		if (conn == null)
			conn = JumiaDB.getConnection();
		try {
			ps = conn.prepareStatement(
					"select product_id, product_code, product_name, product_description, product_price, category_name, units_available "
							+ "from products, category where products.category_id = category.category_id and product_id = ?");
			ps.setInt(1, product_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6), rs.getInt(7));
			}
		} catch (Exception e) {
			System.out.println("+++Exception in getProduct: " + e);
		}
		if (product == null)
			throw new ProductNotFoundException(new ExceptionMessage(String.format("Product not found")));
		return product;
	}

	public Map<Product, Integer> getPopularProductsBetween(String fromDate, String toDate)
			throws OrderNotFoundException, ProductNotFoundException {
		Map<Product, Integer> products = new LinkedHashMap<Product, Integer>();
		Product product = null;
		if (conn == null)
			conn = JumiaDB.getConnection();
		try {
			ps = conn.prepareStatement(
					"SELECT products.product_id, product_code, product_name, product_description, product_price, "
							+ "category_name, units_available, sum(units_purchased) as total_units "
							+ "FROM cart, products, category, orders "
							+ "where cart.product_id = products.product_id and "
							+ "products.category_id = category.category_id and orders.order_id = cart.order_id and "
							+ "(order_status = 'ACTIVE' or order_status = 'Delivered') and "
							+ "ordered_on between ? and ? "
							+ "group by cart.product_id order by total_units desc, category.category_name  limit 20");
			ps.setDate(1, Date.valueOf(fromDate));
			ps.setDate(2, Date.valueOf(toDate));
			rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6), rs.getInt(7));
				products.put(product, rs.getInt(8));
			}
		} catch (Exception e) {
			System.out.println("+++Exception in getPopularProductsBetween: " + e);
		}
		if (products.isEmpty())
			throw new ProductNotFoundException();
		return products;
	}

	public Map<Product, Integer> getProductsConsumptionBetween(String fromDate, String toDate)
			throws OrderNotFoundException, ProductNotFoundException {
		Map<Product, Integer> products = new LinkedHashMap<Product, Integer>();
		Product product = null;
		if (conn == null)
			conn = JumiaDB.getConnection();
		try {
			ps = conn.prepareStatement(
					"SELECT products.product_id, product_code, product_name, product_description, product_price, "
							+ "category_name, units_available, sum(units_purchased) as total_units_delivered "
							+ "FROM cart, products, category, orders "
							+ "where cart.product_id = products.product_id and "
							+ "products.category_id = category.category_id and "
							+ "orders.order_id = cart.order_id and order_status = 'Delivered' and "
							+ "ordered_on between ? and ? "
							+ "group by cart.product_id order by category.category_name, total_units_delivered desc");
			ps.setDate(1, Date.valueOf(fromDate));
			ps.setDate(2, Date.valueOf(toDate));
			rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6), rs.getInt(7));
				products.put(product, rs.getInt(8));
			}
		} catch (Exception e) {
			System.out.println("+++Exception in getProductsConsumptionBetween: " + e);
		}
		if (products.isEmpty())
			throw new ProductNotFoundException();
		return products;
	}
}