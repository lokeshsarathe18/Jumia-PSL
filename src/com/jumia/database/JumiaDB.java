package com.jumia.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class JumiaDB implements DBData {
	private static Connection conn = null;

	private JumiaDB() {
	}

	static {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			System.out.println("+++Exception in getConnection: " + e);
		}
	}

	static public Connection getConnection() {
		return conn;
	}
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}