package com.TestSync.Repositry;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConfig {
	protected PreparedStatement pst;
	protected Connection conn;
	protected ResultSet rs;
	public DBConfig()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineexamdb","root","Pass@12345");
		}
		catch(Exception ex)
		{
			System.out.println("problem to make connection: "+ex);
		}
	}
}
