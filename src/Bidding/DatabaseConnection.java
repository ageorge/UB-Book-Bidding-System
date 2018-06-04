package Bidding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
	private static Connection connection;
	private static Statement stmt;
	private static ResultSet rs;
	private static String url;
//	private String query;
	private User user;
	
	public DatabaseConnection() {
		url = "jdbc:mysql://35.196.90.27:3306/project";
//		query = "select * from USER";
	}
	
	public static void ConnectToDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, "root", "12345");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (connection != null) {
            System.out.println("You made it, take control your database now!");
		} 
		
	}
	
	public Object getData(String query) {
		try {
			stmt = connection.createStatement();
		    rs = stmt.executeQuery(query);
		    while (rs.next()) {
			    	String name = rs.getString("name");
			    	String ssn =  rs.getString("ssn");
		    		user = new User(name, ssn);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static void main(String[] args) {
		DatabaseConnection con = new DatabaseConnection();
		con.ConnectToDB();
	}
	
}
