package com.hexaware.util;

import java.sql.*;


public class DbConnUtil {
	static Connection con;
	public static Connection getDBConn()
	{
		try {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/v_art_gallery", "root", "password");
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		return con;
	}
	public static void main(String[] args)
	{
		System.out.println(getDBConn());
	}
}

