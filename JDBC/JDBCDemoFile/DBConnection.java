package com.example.libs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private Properties info;
	
	public DBConnection() { // ������
		this.info = new Properties();
		File file = new File("./dbinfo.properties");
		try {
			this.info.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	
	public Connection getConnection() {
		// 2. step
		try {
			Class.forName(this.info.getProperty("DBDRIVER")); // == oracle.jdbc.driver.OracleDriver
		} catch (ClassNotFoundException e) {
			System.out.println("Oracle Driver Loading Failure");
		} 
		// 3. step
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(this.info.getProperty("DBURL"), 
					this.info.getProperty("DBUSER"),
					this.info.getProperty("DBPASSWD"));
		} catch (SQLException e) {
			System.out.println("Connection Failure");
		}
		return conn;
	}
}
