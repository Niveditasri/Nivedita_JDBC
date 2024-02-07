package com.cu.main;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {

	public Connection getConnection() throws ClassNotFoundException{
		// TODO Auto-generated method stub
		Connection con=null;//CONNECTION INTERFACE
		//STEP1 LOAD AND REGISTER DRIVER
		Class.forName("com.mysql.cj.jdbc.Driver");
		//STEP2 CONNECTION BETWEEN MYSQL AND ECLIPSE
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_22","root","nivedita");
		//ConnectionImpl class name which implemented Connection interface of con object
//		System.out.println(con.getClass().getName());
		System.out.println("Connection Established");
		return con;
	}

}