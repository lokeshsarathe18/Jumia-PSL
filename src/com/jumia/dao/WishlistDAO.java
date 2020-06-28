package com.jumia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import com.jumia.bean.ExceptionMessage;
import com.jumia.bean.Product;
import com.jumia.bean.Wishlist;
import com.jumia.database.JumiaDB;
import com.jumia.util.WishlistNotFoundException;

public class WishlistDAO {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public Wishlist getWishlistForUser(String username) throws WishlistNotFoundException {
		Wishlist wishlist = null;
		Set<Product> wishes = new HashSet<Product>();
		if (conn == null)
			conn = JumiaDB.getConnection();
		try {
			ps = conn.prepareStatement(
					"SELECT wishlist_id FROM wishlist, users where users.username = ? and users.user_id = wishlist.wished_by");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				wishlist = new Wishlist();
				wishlist.setWishListId(rs.getInt(1));
				wishlist.setWishedBy(username);
				ps = conn.prepareStatement(
						"SELECT products.product_id, product_code, product_name, product_description, product_price, category_name, units_available "
								+ "FROM products, wishes, category "
								+ "where products.product_id = wishes.wish and wishes.wishlist_id = ?"
								+ " and category.category_id = products.category_id");
				ps.setInt(1, wishlist.getWishListId());
				rs = ps.executeQuery();
				while (rs.next()) {
					wishes.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getInt(5), rs.getString(6), rs.getInt(7)));
				}
				wishlist.setWishes(wishes);
			}
		} catch (Exception e) {
			System.out.println("+++Exception in getWishlistForUser: " + e);
		}
		if (wishlist == null)
			throw new WishlistNotFoundException(
					new ExceptionMessage(String.format("No wishlist yet for '%s'", username)));
		return wishlist;
	}
}
