package br.appReport.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private Connection connection  = null;
	
	public Connection getConnection() {
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://192.168.99.100/DbFinancas", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}

	
	
	
	public void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
