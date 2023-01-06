package com.biblioteca.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConexion {
	
	public static Connection getConexion() {
		Connection cn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url,user,pass;
			url="jdbc:mysql://localhost/DBProyecto?serverTimezone=UTC";
			user="root";
			pass="Mysql";
			cn=DriverManager.getConnection(url, user, pass);			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}

}
