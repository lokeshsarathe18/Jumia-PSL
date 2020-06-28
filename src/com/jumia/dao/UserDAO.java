package com.jumia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jumia.bean.ExceptionMessage;
import com.jumia.bean.User;
import com.jumia.database.JumiaDB;
import com.jumia.util.UserNotFoundException;

public class UserDAO {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public User getUser(String username) throws UserNotFoundException {
		User user = null;
		if (conn == null)
			conn = JumiaDB.getConnection();
		try {
			ps = conn.prepareStatement("select * from users where username = ?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), "**********",
						rs.getString(6));
			}
		} catch (Exception e) {
			System.out.println("+++Exception in getUser: " + e);
		}
		if (user == null)
			throw new UserNotFoundException(new ExceptionMessage(String.format("%s not found", username)));
		return user;
	}

	public boolean createUser(User user) {
		boolean flag = false;
		if (conn == null)
			conn = JumiaDB.getConnection();
		try {
			ps = conn.prepareStatement(
					"insert into users(username, email, mobile_number, password,  address) values(?,?,?,?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getMobile_number());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getAddress());
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("+++Exception in createUser: " + e);
		}
		return flag;
	}

	public boolean deleteUser(User user) {
		boolean flag = false;
		if (conn == null)
			conn = JumiaDB.getConnection();
		try {
			ps = conn.prepareStatement("delete from users where usersid = ?");
			ps.setInt(1, user.getUserId());
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("+++Exception in deleteUser: " + e);
		}
		return flag;
	}

	public boolean updateUser(User user) {
		boolean flag = false;
		if (conn == null)
			conn = JumiaDB.getConnection();
		try {
			ps = conn.prepareStatement("update users set username = ?, email = ?,mobile_number = ?, password = ?,  address = ?  where usersid = ?");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getMobile_number());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getAddress());
			ps.setInt(3, user.getUserId());
			if(ps.executeUpdate()>0) {
				flag = true;
			}
		}catch (Exception e) {
			System.out.println("+++Exception in updateUser: " + e);
		}
		return flag;
	}
}
