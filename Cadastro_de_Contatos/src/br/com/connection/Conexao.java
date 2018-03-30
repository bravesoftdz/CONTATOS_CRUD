package br.com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
public static Connection getConnection() throws SQLException{
		
		Connection con = null;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/contatos";
		String user ="root";
		String password = "";
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Conectão bem sucedida!!");
			return con;
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			return null;
		} 
	}
	
	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
